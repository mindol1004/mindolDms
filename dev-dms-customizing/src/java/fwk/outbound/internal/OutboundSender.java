package fwk.outbound.internal;

import nexcore.framework.core.ioc.ComponentRegistry;
import nexcore.framework.core.service.outbound.IOutboundDataLog;
import nexcore.framework.core.util.ByteArrayWrap;
import fwk.common.FlatData;
import fwk.common.OutboundHeader;
import fwk.flat.FlatHeader;
import fwk.flat.FlatHelper;
import fwk.outbound.OutboundRuntimeException;

/**
 * 아웃바운드 전문 송신 모듈
 */
public class OutboundSender {

	private final static ByteArrayWrap OK = new ByteArrayWrap("OK".getBytes());
	
	/**
	 * 동기 전문 송신
	 * @param target 대상 채널
	 * @param header 아웃바운드 헤더
	 * @param userByteArrayWrap 업무 데이타부
	 * @param timeout 전문 타임아웃
	 * @return 응답 전문
	 */
	public static FlatData call(OutboundTargetInternal target, OutboundHeader header, ByteArrayWrap userByteArrayWrap, int timeout) {
		// 헤더 포함한 전문 생성
		ByteArrayWrap requestTotalByteArrayWrap = toTotalByteArrayWrap(header, userByteArrayWrap);
		
		long startTime = System.currentTimeMillis();
		getOutboundDataLogger().logRequest(target.getChannelCode(), "S", header.getOrgTxId(), header.getOrgGlobId(), startTime, requestTotalByteArrayWrap);
		OutboundConnection conn = null;
		try {
			// 대상 채널의 Connection 조회
			conn = OutboundConnectionFactory.get(getTargetName(target, header.getFlatHeader()));
			
			// 전문 송신
			ByteArrayWrap responseTotalByteArrayWrap = new ByteArrayWrap(conn.call(requestTotalByteArrayWrap, timeout));
			long endTime = System.currentTimeMillis();
			getOutboundDataLogger().logResponse(target.getChannelCode(), "S", header.getOrgTxId(), header.getOrgGlobId(), endTime, (endTime - startTime), responseTotalByteArrayWrap);
			
			// 응답전문 분석
			return toFlatData(responseTotalByteArrayWrap);
		} catch (Exception e) {
			OutboundConnectionFactory.destroy(conn);
			long endTime = System.currentTimeMillis();
			getOutboundDataLogger().logResponse(target.getChannelCode(), "S", header.getOrgTxId(), header.getOrgGlobId(), endTime, (endTime - startTime), new ByteArrayWrap(("Error Message:" + e.getMessage()).getBytes()));
			throw new OutboundRuntimeException("SKFS1005", new String[] { e.getMessage() }, e);
		}
		finally {
			if(conn != null){
				OutboundConnectionFactory.release(conn);
			}
		}
	}
	
	/**
	 * 비동기 전문 송신
	 * @param target 대상 채널
	 * @param header 아웃바운드 헤더
	 * @param userByteArrayWrap 업무 데이타부 
	 */
	public static void send(OutboundTargetInternal target, OutboundHeader header, ByteArrayWrap userByteArrayWrap){
		// 헤더 포함한 전문 생성
		ByteArrayWrap totalByteArrayWrap = toTotalByteArrayWrap(header, userByteArrayWrap);
		
		long startTime = System.currentTimeMillis();
		getOutboundDataLogger().logRequest(target.getChannelCode(), "A", header.getOrgTxId(), header.getOrgGlobId(), startTime, totalByteArrayWrap);
		OutboundConnection conn = null;
		try {
			// 대상 채널의 Connection 조회
			conn = OutboundConnectionFactory.get(getTargetName(target, header.getFlatHeader()));
			
			// 전문 송신
			conn.send(totalByteArrayWrap);
			long endTime = System.currentTimeMillis();
			getOutboundDataLogger().logResponse(target.getChannelCode(), "A", header.getOrgTxId(), header.getOrgGlobId(), endTime, (endTime - startTime), OK);
		} catch (Exception e) {
			OutboundConnectionFactory.destroy(conn);
			long endTime = System.currentTimeMillis();
			getOutboundDataLogger().logResponse(target.getChannelCode(), "A", header.getOrgTxId(), header.getOrgGlobId(), endTime, (endTime - startTime), new ByteArrayWrap(("Error Message:" + e.getMessage()).getBytes()));
			throw new OutboundRuntimeException("SKFS1005", new String[] { e.getMessage() }, e);
		}
		finally {
			if(conn != null){
				OutboundConnectionFactory.release(conn);
			}
		}
	}

	/**
	 * 헤더부를 포함한 전문을 생성
	 * @param header 아웃바운드 헤더
	 * @param userByteArrayWrap 업무 데이타부
	 * @return
	 */
	private static ByteArrayWrap toTotalByteArrayWrap(OutboundHeader header, ByteArrayWrap userByteArrayWrap){
		return FlatHelper.transformTotalByteArray(header, userByteArrayWrap);
	}
	
	/**
	 * 응답 전문을 헤더부와 업무데이타부를 분할
	 * @param totalByteArrayWrap 응답전문
	 * @return 분석 데이타
	 */
	private static FlatData toFlatData(ByteArrayWrap totalByteArrayWrap){
		return FlatHelper.transformFlatData(totalByteArrayWrap);
	}

	/**
	 * 대상 시스템명 조회
	 * @param target 대상 채널
	 * @param header 아웃바운드 헤더
	 * @return 대상 시스템명
	 */
	public static String getTargetName(OutboundTargetInternal target, FlatHeader header){
		String targetName = null;
		// MCI 인경우 MCI노드번호를 포함하여 처리한다.
		if(OutboundTargetInternal.MCI_PUSH.equals(target) || OutboundTargetInternal.MCI_MASS.equals(target)){
			targetName = target.getName() + header.getMciTrnmNodeNo();
			if(!OutboundConnectionFactory.isExist(targetName)){
				throw new RuntimeException(target.getChannelCode() + " [Type=" + target.getChannelType() + ", Node=" + header.getMciTrnmNodeNo() + "] is not registered.");
			}
		}
		else {
			targetName = target.getName();
			if(!OutboundConnectionFactory.isExist(targetName)){
				throw new RuntimeException(target.getChannelCode() + " [Type=" + target.getChannelType() + "] is not registered.");
			}
		}
		return targetName;
	}
	
	private static IOutboundDataLog getOutboundDataLogger() {
		if (outboundDataLogger == null) {
			outboundDataLogger = (IOutboundDataLog) ComponentRegistry.lookup(IOutboundDataLog.COMPONENT_ID);
		}
		return outboundDataLogger;
	}

	private static IOutboundDataLog outboundDataLogger;

}
