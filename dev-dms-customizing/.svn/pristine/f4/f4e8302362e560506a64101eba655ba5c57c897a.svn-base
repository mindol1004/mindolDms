package fwk.base;

import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.util.ByteArrayWrap;
import fwk.common.CommonArea;
import fwk.common.CommonUtils;
import fwk.common.FlatData;
import fwk.common.OutboundHeader;
import fwk.constants.DmsConstants;
import fwk.outbound.internal.OutboundSender;
import fwk.outbound.internal.OutboundTargetInternal;
import fwk.utils.HpcLogUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dev-hpc-customizing</li>
 * <li>서브 업무명 : fwk.base</li>
 * <li>설  명 : ProcessUnit</li>
 * <li>작성일 : 2015. 1. 28.</li>
 * <li>작성자 : Administrator</li>
 * </ul>
 */
public class ProcessUnit extends nexcore.framework.coreext.pojo.biz.ProcessUnit {
	public static final String RECORD_STATUS="ncRecStatus";
	public static final int ERROR_LEVEL=HpcLogUtils.ERROR_LEVEL;
    public static final int INFO_LEVEL=HpcLogUtils.INFO_LEVEL;
    public static final int DEBUG_LEVEL=HpcLogUtils.DEBUG_LEVEL;

	/**
	 * CommonArea 조회
	 * 
	 * @param onlineCtx
	 *            온라인 컨텍스트
	 * @return 조회된 CommonArea
	 */
    protected final CommonArea getCommonArea(IOnlineContext onlineCtx) {
		return CommonUtils.getCommonArea(onlineCtx);
	}
    
    /**
	 * MCI에 동기방식 데이터 전송
	 * @param header 아웃바운드 헤더
	 * @param requestData 전송 데이터
	 * @param onlineCtx 온라인 컨텍스트
	 * @return 응답 데이터
	 */
//	protected final FlatData callMCI(OutboundHeader header, ByteArrayWrap requestData, IOnlineContext onlineCtx) {
//		return OutboundSender.call(OutboundTargetInternal.MCI_PUSH, header, requestData, -1);
//	}

	/**
	 * MCI에 동기방식 데이터 전송
	 * @param header 아웃바운드 헤더
	 * @param requestData 전송 데이터
	 * @param timeout 타임아웃(초)
	 * @param onlineCtx 온라인 컨텍스트
	 * @return 응답 데이터
	 */
//	protected final FlatData callMCI(OutboundHeader header, ByteArrayWrap requestData, int timeout, IOnlineContext onlineCtx){
//		return OutboundSender.call(OutboundTargetInternal.MCI_PUSH, header, requestData, timeout * 1000);
//	}

	/**
	 * MCI에 비동기방식 데이터 전송 (XA 모드)<br>
	 * 전문은 현재 거래가 성공시 전송된다.
	 * @param header 아웃바운드 헤더
	 * @param requestData 전송 데이터
	 * @param onlineCtx 온라인 컨텍스트
	 */
//	protected final void sendMCIByXA(OutboundHeader header, ByteArrayWrap requestData, IOnlineContext onlineCtx) {
//		OutboundAsyncQueueSender.putQueue(OutboundTargetInternal.MCI_PUSH, header, requestData, onlineCtx);
//	}

	/**
	 * MCI에 비동기방식 데이터 전송 (NON-XA 모드)<br>
	 * 전문은 즉시 송신된다.
	 * @param header 아웃바운드 헤더
	 * @param requestData 전송 데이터
	 * @param onlineCtx 온라인 컨텍스트
	 */
//	protected final void sendMCIByNonXA(OutboundHeader header, ByteArrayWrap requestData, IOnlineContext onlineCtx){
//		OutboundSender.send(OutboundTargetInternal.MCI_PUSH, header, requestData);
//	}

	/**
	 * FEP에 동기방식 데이터 전송
	 * @param header 아웃바운드 헤더
	 * @param requestData 전송 데이터
	 * @param onlineCtx 온라인 컨텍스트
	 * @return 응답 데이터
	 */
	protected final FlatData callFEP(OutboundHeader header, ByteArrayWrap requestData, IOnlineContext onlineCtx){
		return OutboundSender.call(OutboundTargetInternal.FEP_SYNC, header, requestData, -1);
	}

	/**
	 * FEP에 동기방식 데이터 전송
	 * @param header 아웃바운드 헤더
	 * @param requestData 전송 데이터
	 * @param timeout 타임아웃(초)
	 * @param onlineCtx 온라인 컨텍스트
	 * @return 응답 데이터
	 */
	protected final FlatData callFEP(OutboundHeader header, ByteArrayWrap requestData, int timeout, IOnlineContext onlineCtx){
		return OutboundSender.call(OutboundTargetInternal.FEP_SYNC, header, requestData, timeout * 1000);
	}

	/**
	 * FEP에 비동기방식 데이터 전송 (XA 모드)<br>
	 * 전문은 현재 거래가 성공시 전송된다.
	 * @param header 아웃바운드 헤더
	 * @param requestData 전송 데이터
	 * @param onlineCtx 온라인 컨텍스트
	 */
//	protected final void sendFEPByXA(OutboundHeader header, ByteArrayWrap requestData, IOnlineContext onlineCtx){
//		OutboundAsyncQueueSender.putQueue(OutboundTargetInternal.FEP_ASYNC, header, requestData, onlineCtx);
//	}	

	/**
	 * FEP에 비동기방식 데이터 전송 (NON-XA 모드)<br>
	 * 전문은 즉시 송신된다.
	 * @param header 아웃바운드 헤더
	 * @param requestData 전송 데이터
	 * @param onlineCtx 온라인 컨텍스트
	 */
//	protected final void sendFEPByNonXA(OutboundHeader header, ByteArrayWrap requestData, IOnlineContext onlineCtx){
//		OutboundSender.send(OutboundTargetInternal.FEP_ASYNC, header, requestData);
//	}	


	/**
	 * EAI에 동기식 방식 데이터 전송
	 * @param header 아웃바운드 헤더
	 * @param requestData 전송 데이터
	 * @param onlineCtx 온라인 컨텍스트
	 * @return 응답 데이터
	 */
//	protected final FlatData callEAI(OutboundHeader header, ByteArrayWrap requestData, IOnlineContext onlineCtx) {
//		return OutboundSender.call(OutboundTargetInternal.EAI_SYNC, header, requestData, -1);
//	}
//	

	protected final org.apache.commons.logging.Log getOperLog(IOnlineContext context) {
        return HpcLogUtils.getOperLog(context);
    }
	
	/**
	 * DataSet에 담긴 정보들을 출력하기 위한 메소드
	 *  
	 * @param dataSet 업무파라미터들이 정의되어 있는 DataSet
	 * @param onlineCtx
	 * @param onlyPrintField 필드만 출력할 것인지 여부
	 */
	protected final void writeOperLog(IDataSet dataSet, IOnlineContext onlineCtx, boolean onlyPrintField, int logLevel) {
	   HpcLogUtils.writeOperLog(dataSet, onlineCtx, onlyPrintField, logLevel);
	}
	
	/**
	 * RecordSet에 담긴 정보들을 출력하기 위한 메소드
	 *  
	 * @param recordSet
	 * @param onlineCtx 
	 */
	protected final void writeOperLog(IRecordSet recordSet, IOnlineContext onlineCtx, int logLevel) {
	    HpcLogUtils.writeOperLog(recordSet, onlineCtx, logLevel);
	}
	
	/**
	 * DataSet의 key 배열을 넘겨주면 DataSet에서 해당 key값에 대한 value를 출력한다. 
	 * @param keyArr
	 * @param dataSet
	 * @param onlineCtx void
	 */
	protected final void writeOperLog(String[] keyArr, IDataSet dataSet,  IOnlineContext onlineCtx,  int logLevel) {
	    HpcLogUtils.writeOperLog(keyArr, dataSet,  onlineCtx,  logLevel);
	}
	
	/**
	 * 넘겨받은 String 파라미터를 운영자 로그에 바로 출력한다.
	 *  
	 * @param str
	 * @param onlineCtx void
	 */
	protected final void writeOperLog(String str, IOnlineContext onlineCtx, int logLevel) {
	    HpcLogUtils.writeOperLog(str, onlineCtx, logLevel);
	}
	
	/**
	 * 에러가 발생시에도 return전문을 보내야 하는 경우 해당 메소드에 PM의 Output IO와 동일한 key로 저장된 DataSet을 
	 * 전달하면, response 전문의 body 영역에 해당 DataSet에 담긴 value를 전문으로 내보낸다.
	 *  
	 * @param onlineCtx
	 * @param dataSet void
	 */
//	protected final void setRtnErrDataSet(IOnlineContext onlineCtx, IDataSet dataSet) {
//	    setRtnErrDataSet(onlineCtx, dataSet, "", null, null);
//	}
//	

	/**
	 * 에러가 발생시에도 return전문을 보내야 하는 경우 해당 메소드에 PM의 Output IO와 동일한 key로 저장된 DataSet을 
     * 전달하면, response 전문의 body 영역에 해당 DataSet에 담긴 value를 전문으로 내보낸다.
	 * @param onlineCtx
	 * @param dataSet
	 * @param msgId
	 * @param msgParam
	 * @param e void
	 */
	protected final void setRtnErrDataSet(IOnlineContext onlineCtx, IDataSet dataSet, String msgId, String[] msgParam, Exception e) {
	    dataSet.setResultMessage(IResultMessage.FAIL, msgId, msgParam, e);
	    onlineCtx.setAttribute(DmsConstants.RETURN_DATASET, dataSet);
	    onlineCtx.setAttribute(DmsConstants.IS_RTN_ERR_DS, true);
	}
}
