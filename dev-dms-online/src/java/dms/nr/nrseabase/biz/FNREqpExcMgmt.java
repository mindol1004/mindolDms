package dms.nr.nrseabase.biz;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.xml.DataSetXmlTransformer;
import nexcore.framework.core.exception.BizRuntimeException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import fwk.common.CommonArea;
import fwk.utils.PagenateUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]단말제각관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-06 08:55:03</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class FNREqpExcMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNREqpExcMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-08-06 08:55:03
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : ASSET_SLIP_NO [전표번호]
	 *	- field : ASSET_SLIP_ST [전표상태코드]
	 *	- field : LST_DEPR_DT [LST_제각일]
	 *	- field : LST_INVE_ST_CD [LST_단말상태]
	 *	- field : LST_ASSET_SLIP_NO [LST_전표]
	 *	- field : LST_ASSET_SLIP_ST [LST_전표처리상태]
	 *	- field : LST_DISPO_DT [LST_마감자산처분일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_EXC_LIST
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_PRCH_AMT [매입가]
	 *		- field : REM_PRC [회계용잔존가]
	 *		- field : DEPR_PRC [회계용감가상각]
	 *		- field : EQP_LOSS_DT [분실일자]
	 *		- field : EQP_PRCH_DT [입고일=계약일]
	 *		- field : CAPA_CD [용량]
	 *		- field : PROV_PRC [충당부채]
	 *		- field : ASSET_SLIP_ST [전표상태]
	 *		- field : ASSET_SLIP_NO [전표번호]
	 * </pre>
	 */
	public IDataSet fInqEqpExcLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet temp = new DataSet();
        
        try {
      
            // 1. DU lookup
            DNREqpExcMgmt dNREqpExcMgmt = (DNREqpExcMgmt) lookupDataUnit(DNREqpExcMgmt.class);
            
            // 2. LISTDM호출
            responseData = dNREqpExcMgmt.dSEqpExcLst(requestData, onlineCtx); 
                
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;

	    //	    IDataSet responseData = new DataSet();
//	    IDataSet reqDs = (IDataSet) requestData.clone();
//	    IDataSet dsCnt = new DataSet();
//		IRecordSet usrListRs = null;
//		int intTotalCnt = 0;
//
//		try {
//			// 1. DU lookup
//			DNREqpExcMgmt dNREqpExcMgmt = (DNREqpExcMgmt) lookupDataUnit(DNREqpExcMgmt.class);
//			
//			// 2. TOTAL COUNT DM호출
//			dsCnt = dNREqpExcMgmt.dSEqpExcTotCnt(requestData, onlineCtx);
//			IRecordSet sumRs = dsCnt.getRecordSet("RS_SUM_LIST");
//			
//			String cnt = sumRs.get(0,"TOTAL_CNT");
//			intTotalCnt = Integer.parseInt(cnt);
//			PagenateUtils.setPagenatedParamsToDataSet(requestData);
//
//			// 3. LISTDM호출
//			responseData = dNREqpExcMgmt.dSEqpExcLstPaging(requestData, onlineCtx);
//			usrListRs = responseData.getRecordSet("RS_EQP_EXC_LIST");
//			
//			responseData.putRecordSet("RS_SUM_LIST", sumRs);
//			PagenateUtils.setPagenatedParamToRecordSet(sumRs, requestData, intTotalCnt);
//			
//			responseData.putRecordSet("RS_EQP_EXC_LIST",usrListRs);
//			PagenateUtils.setPagenatedParamToRecordSet(usrListRs, requestData, intTotalCnt);	
//			
//			
//		} catch ( BizRuntimeException e ) {
//			throw e; //시스템 오류가 발생하였습니다.
//		}
//	
//	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-17 14:54:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fEqpExcBatchCallOnline(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	    Log log = getLog(onlineCtx);
		   
	    try {

	        log.debug("fEqpExcBatchCallOnline() requestData:"+requestData);
			if("01".equals(requestData.getField("CASE_WHEN")) ){//재집계
				ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
				DataSetXmlTransformer.dataSetToXml(requestData, bout, "UTF-8");
				String dsXml = bout.toString("UTF-8");

				// call on-demand batch job
				HashMap params = new HashMap<String,String>();
				params.put("TASK_ID", "XCR003");
				params.put("TASK_NM", "단말기제각등록");
			    params.put("LGIN_ID", onlineCtx.getUserInfo().getLoginId());
			    params.put("USER_NO", ca.getUserNo());
			    params.put("COMPONENTNAME_LOCAL_ONLY", "dms.nr.XCR003");
				params.put("ONDEMAND_DATASET", dsXml);
				String jobExecutionId = callBatchJob("XCR003", params, onlineCtx);
			    waitBatchJobEnd(jobExecutionId, 10000);
			    int result = getJobReturnCode(jobExecutionId);
			    
			    if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.
	    			
	    	}else if("02".equals(requestData.getField("CASE_WHEN"))) {//전표생성
	    		
                requestData.putField("DSPSL_DIS_CL", "AD"); //제각
                requestData.putField("OP_CL_CD", "NR");     //업무구분코드
                requestData.putField("DISPO_DT", requestData.getField("LST_DEPR_DT"));
	    		
	    		requestData.putField("SLIP_TYPE", "NR_AD");  //전표유형:제각
	    		requestData.putField("USER_NO", ca.getUserNo());
	    		//배치로직호출 -- 전표생성
	    		ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
	    		DataSetXmlTransformer.dataSetToXml(requestData, bout, "UTF-8");
	    		String dsXml = bout.toString("UTF-8");

				// call on-demand batch job
				HashMap params = new HashMap<String,String>();
				params.put("TASK_ID", "EPR010");
			    params.put("TASK_NM", "전표발행");
			    params.put("USER_NO", ca.getUserNo());
			    params.put("COMPONENTNAME_LOCAL_ONLY", "dms.inf.EPR010");				
				params.put("POST_SLIP_DATASET", dsXml);
				String jobExecutionId = callBatchJob("EPR010", params, onlineCtx);
			    waitBatchJobEnd(jobExecutionId, 10000);
			    int result = getJobReturnCode(jobExecutionId);
		    
			    if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.
			    
	    	}else if("03".equals(requestData.getField("CASE_WHEN"))){ //전표삭제

	    	    requestData.putField("SLIP_NO", requestData.getField("LST_ASSET_SLIP_NO"));   //전표번호
                requestData.putField("USER_NO", ca.getUserNo());

                ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
	    		DataSetXmlTransformer.dataSetToXml(requestData, bout, "UTF-8");
	    		String dsXml = bout.toString("UTF-8");

				// call on-demand batch job
				HashMap params = new HashMap<String,String>();
				params.put("TASK_ID", "EPR011");
			    params.put("TASK_NM", "전표삭제");
			    params.put("USER_NO", ca.getUserNo());
			    params.put("COMPONENTNAME_LOCAL_ONLY", "dms.inf.EPR011");				
				params.put("POST_SLIP_DATASET", dsXml);
				String jobExecutionId = callBatchJob("EPR011", params, onlineCtx);
			    waitBatchJobEnd(jobExecutionId, 10000);
			    int result = getJobReturnCode(jobExecutionId);
		    
			    if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.
	    	}
	    	
			
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-30 15:39:04
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fPowerExcController(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	    
	    try {
			// 1. DU lookup
			DNREqpExcMgmt dNREqpExcMgmt = (DNREqpExcMgmt) lookupDataUnit(DNREqpExcMgmt.class);
			
			// 2. LISTDM호출
			String userNo = "";
			if(ca!=null && ca.getUserNo()==null)
			{
				userNo = ca.getUserNo();
			}
			else if (requestData.containsField("USER_NO") && requestData.getField("USER_NO") !=null)
			{
				userNo = requestData.getField("USER_NO") ;
			}
			requestData.putField("USER_NO", userNo);
			responseData = dNREqpExcMgmt.dUPowerExcController(requestData, onlineCtx);
			
		} catch ( BizRuntimeException e ) {
			throw e; //시스템 오류가 발생하였습니다.
		}
	
	    return responseData;
	}

    /**
	 * <pre>[FM]단말제각현황전체다운로드</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-10-26 11:07:51
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqEqpExcAllExcel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet temp = new DataSet();
        
        try {
      
           // 1. DU lookup
            DNREqpExcMgmt dNREqpExcMgmt = (DNREqpExcMgmt) lookupDataUnit(DNREqpExcMgmt.class);

            // 2. LISTDM호출
            responseData = dNREqpExcMgmt.dSEqpExcAllExcel(requestData, onlineCtx); 
                
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>[FM]단말제각전표처리현황</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-11-06 10:02:49
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqEqpExcSum(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. DU lookup
            DNREqpExcMgmt dNREqpExcMgmt = (DNREqpExcMgmt) lookupDataUnit(DNREqpExcMgmt.class);
            // 2. LISTDM호출
            responseData = dNREqpExcMgmt.dSEqpExcSum(requestData, onlineCtx); 
                
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>[FM]제각마감자산처분삭제</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-11-06 13:39:08
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fDelExcClsAssetDispo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. DU lookup
            DNREqpExcMgmt dNREqpExcMgmt = (DNREqpExcMgmt) lookupDataUnit(DNREqpExcMgmt.class);
            
            responseData = dNREqpExcMgmt.dDExcClsAssetDispo(requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>[FM]제각마감자산처분상세등록</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-11-06 13:48:41
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegExcClsAssetDispoDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. DU lookup
            DNREqpExcMgmt dNREqpExcMgmt = (DNREqpExcMgmt) lookupDataUnit(DNREqpExcMgmt.class);
            
            responseData = dNREqpExcMgmt.dIExcClsAssetDispoDtl(requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>[FM]제각마감자산처분등록</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-11-06 13:49:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegExcClsAssetDispo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. DU lookup
            DNREqpExcMgmt dNREqpExcMgmt = (DNREqpExcMgmt) lookupDataUnit(DNREqpExcMgmt.class);
            
            responseData = dNREqpExcMgmt.dIExcClsAssetDispo(requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }
  
}
