package fwk.flat;

import java.util.Date;
import java.util.Map;

import nexcore.framework.core.ioc.ComponentRegistry;
import nexcore.framework.core.log.LogManager;
import nexcore.framework.core.service.front.IFrontDataLog;
import nexcore.framework.core.transform.IFlatBulkSplitSender;
import nexcore.framework.core.util.ByteArrayWrap;
import nexcore.framework.core.util.DateUtils;

import org.apache.commons.logging.Log;

import fwk.common.internal.CommonAreaHelper;
import fwk.common.internal.ImplCommonArea;
import fwk.outbound.internal.OutboundConnection;
import fwk.outbound.internal.OutboundConnectionFactory;
import fwk.outbound.internal.OutboundSender;
import fwk.outbound.internal.OutboundTargetInternal;

/**
 * 대량출력 전문 송신 클래스<br>
 * 전문 응답시 대량출력이라고 판단된 경우 전문이 분할되어 호출된다.
 */
public class FlatBulkSplitSender implements IFlatBulkSplitSender {

	private Log logger;
	private IFrontDataLog frontLogger;
	private ThreadLocal<OutboundConnection> connectionPool = new ThreadLocal<OutboundConnection>();
	
	public void setFontDataLog(IFrontDataLog frontLogger){
		this.frontLogger = frontLogger;
	}

	public void execute(Map headers, ByteArrayWrap data) {
		prepare();
		
		// CommonArea 조회
		ImplCommonArea ca = CommonAreaHelper.getImpl(headers);

		// 전송채널이 MCI인 경우만 지원한다.
		if (OutboundTargetInternal.MCI_MASS.getChannelCode().equals(ca.getTrnmChnlCd())){
			// 응답시 동일한 세션에 보내야 하기 때문에 ThreadLocal로 관리한다.
			OutboundConnection conn = connectionPool.get();
			if (conn == null) {
				conn = OutboundConnectionFactory.get(OutboundSender.getTargetName(OutboundTargetInternal.MCI_MASS, ca.getFlatHeader()));
				connectionPool.set(conn);
			}
			
			// 응답 전문 송신
			conn.send(data);

			// 인바운드 전문 로그에 기록한다.
			if (frontLogger != null) {
				Date startTime = DateUtils.parseDate(ca.getSvcStrnDttm(), "yyyyMMddHHmmssSSS"); //시작시각
				long endTime = System.currentTimeMillis(); //종료시각
				frontLogger.logResponse(ca.getTrnmChnlCd(), ca.getTrnCd(), ca.getGlobId(), endTime, (endTime - startTime.getTime()), data);
			}
		}
		else {
			if (logger.isErrorEnabled()) {
				logger.error(">>>>FLAT MASS OUTPUT SEND : TRNM_CHNL_CD[" + ca.getTrnmChnlCd() + "] can not support mass output.");
			}
			throw new RuntimeException("TRNM_CHNL_CD[" + ca.getTrnmChnlCd() + "] can not support mass output.");
		}
	}

	/**
	 * 자원 해제
	 */
	public void release() {
		try{
			OutboundConnection conn = connectionPool.get();
			OutboundConnectionFactory.release(conn);
		}finally{
			connectionPool.remove();
		}
	}

	private void prepare() {
		if (logger == null) {
			logger = LogManager.getFwkLog();
		}
		if (frontLogger == null) {
			frontLogger = (IFrontDataLog) ComponentRegistry.lookup(IFrontDataLog.COMPONENT_ID);
		}
	}

}
