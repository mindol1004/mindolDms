package dms.fw.fwsbase.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.util.DateUtils;
import nexcore.framework.core.util.StringUtils;

import org.apache.commons.logging.Log;

import fwk.common.TrtmRsltMsg;
import fwk.common.internal.CommonAreaHelper;
import fwk.common.internal.ImplCommonArea;
import fwk.flat.FlatHeaderHelper;


/**
 * <ul>
 * <li>업무 그룹명 : HPC/프레임워크</li>
 * <li>단위업무명: FFWKCMgmtLog</li>
 * <li>설  명 : 거래로그 및 에러로그에 대한 등록요청을 실시하는 클래스</li>
 * <li>작성일 : 2014-06-30 17:45:56</li>
 * <li>작성자 : admin (admin)</li>
 * </ul>
 *
 * @author admin (admin)
 */
public class FFWKCMgmtLog extends fwk.base.FunctionUnit  {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FFWKCMgmtLog(){
		super();
	}

	/**
	 *
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fWriteTrLog(IDataSet requestData, IOnlineContext onlineCtx){
		IDataSet responseData = new DataSet();
		
	    Log log = getLog(onlineCtx);

	    // 디퍼드는 로그를 기록하지 않음.
	    if(onlineCtx.getTransaction().isDefferedProcess()){
	    	if(log.isInfoEnabled()){
		    	log.info("디퍼드 서비스는 거래로그를 기록하지 않습니다.");
		    }
	    	return responseData;
	    }
	    
	    // 거래로그를 기록하는 거래아이디에 부합하지 않으면 처리하지 않는다.
//	    if(onlineCtx.getTransaction().getTxId().length() > 8){
//	    	return responseData;
//	    }
	    
	    if(log.isDebugEnabled()){
	    	log.debug("거래로그 처리 시작");
	    }

	    IDataSet logDataSet = _toLogDataSet(onlineCtx, true);

	    //TODO 임시
	    logDataSet.putField("LOG_BZOP_DT", DateUtils.getCurrentDate());

	    DXYZTTrLog dXYZTTrLog = (DXYZTTrLog)lookupDataUnit(DXYZTTrLog.class);
	    dXYZTTrLog.dITrLog(logDataSet, onlineCtx);
	    
	    if(log.isDebugEnabled()){
	    	log.debug("거래로그 처리 완료");
	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    return responseData;
	}

	/**
	 *
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fWriteErLog(IDataSet requestData, IOnlineContext onlineCtx){
		IDataSet responseData = new DataSet();

	    // 거래로그를 기록하는 거래아이디에 부합하지 않으면 처리하지 않는다.
//	    if(onlineCtx.getTransaction().getTxId().length() > 8){
//	    	return responseData;
//	    }
	    
	    Log log = getLog(onlineCtx);
	    if(log.isDebugEnabled()){
	    	log.debug("에러로그 처리 시작");
	    }

	    // 예외 정보 분석
	    Throwable throwable = (Throwable)requestData.getObjectField("throwable");
	    if(throwable == null){
	    	throwable = (Exception)requestData.getObjectField("exception");
	    }
	    
    	ImplCommonArea ca = CommonAreaHelper.getImpl(onlineCtx);
    	if(ca != null){
	    	if(ca.getMsgList() == null || ca.getMsgCcnt() < 1){
	    		ca.setMsgList(new ArrayList<TrtmRsltMsg>(10));
		    	FlatHeaderHelper.addMessage(ca.getMsgList(), null, throwable);
	    	}
    	}

	    IDataSet logDataSet = _toLogDataSet(onlineCtx, false);

	    DXYZTErLog dXYZTErLog = (DXYZTErLog)lookupDataUnit(DXYZTErLog.class);
	    dXYZTErLog.dIErLog(logDataSet, onlineCtx);

	    if(log.isDebugEnabled()){
	    	log.debug("에러로그 처리 완료");
	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    return responseData;
	}
  
	/**
	 * CommonArea 정보를 Map으로 변환한다.
	 */
	private IDataSet _toLogDataSet(IOnlineContext onlineCtx, boolean trlog){
		IDataSet dataSet = new DataSet();
		
		String yyyyMMddHHmmssSSS = DateUtils.getCurrentDate("yyyyMMddHHmmssSSS");
		String yyyyMMdd = yyyyMMddHHmmssSSS.substring(0, 8);
		String partitionKey = null;
		
		// CommonArea ==> DataSet
	   	ImplCommonArea ca = CommonAreaHelper.getImpl(onlineCtx);
	   	int msgCnt = 0;
		if(ca != null){
			Map map = new HashMap();
			ca.setSvcEndDttm(yyyyMMddHHmmssSSS);
			CommonAreaHelper.toMap(map, ca);
			dataSet.putFieldMap(map);
			
			if(!trlog) {
			    String msgCntn = "";
			    msgCnt = dataSet.getIntField("MSG_CCNT");
			    for(int i=1; i<=msgCnt; i++) {
			        msgCntn = dataSet.getField("MSG_CNTN"+i);
			        //평상시에는 null이 나올 수 없으나, PM에서 Exception을 catch해서 프로젝트에 정의된 메시지로 throw하지않는 경우에는
			        //해당 Message 설명이 나오지 않을 수 있음. 
			        if(msgCntn!=null) {
			            if(msgCntn.length() > 1000) {
	                        dataSet.putField("MSG_CNTN"+i, dataSet.getField("MSG_CNTN"+i).substring(0,1000));
	                    } else {
	                        dataSet.putField("MSG_CNTN"+i, dataSet.getField("MSG_CNTN"+i));
	                    }
			        }
			    }
			}
//			
			long svcStartTime = DateUtils.stringToDate(ca.getSvcStrnDttm(), "yyyyMMddHHmmssSSS").getTime();
			long svcEndTime = DateUtils.stringToDate(ca.getSvcEndDttm(), "yyyyMMddHHmmssSSS").getTime();
			dataSet.putField("SVC_DUR_TM", (svcEndTime-svcStartTime));

			if(ca.getGlobId() != null && ca.getGlobId().length() > 0){
				partitionKey = ca.getGlobId().substring(ca.getGlobId().length()-1);
			}
		}
		if(partitionKey == null || !StringUtils.isNumeric(partitionKey)){
			partitionKey = "0";
		}

		if(StringUtils.isEmpty(dataSet.getField("LOG_BZOP_DT"))){
			dataSet.putField("LOG_BZOP_DT", yyyyMMdd);
		}
		dataSet.putField("TRN_DT", yyyyMMdd);
		dataSet.putField("WAS_INSTANCE_ID", nexcore.framework.core.util.BaseUtils.getCurrentWasInstanceId());
		dataSet.putField("RGSTRN_DT", yyyyMMdd);
		dataSet.putField("_PARTITION_KEY", partitionKey);
		dataSet.putField("TRN_PTRN_DVCD", ca.getTrnPtrnDvcd());

		return dataSet;
	}
}
