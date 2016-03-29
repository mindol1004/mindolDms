package dms.nr.nrsisbase.biz;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.xml.DataSetXmlTransformer;
import nexcore.framework.core.exception.BizRuntimeException;

import org.apache.commons.logging.Log;

import fwk.common.CommonArea;
import fwk.utils.PagenateUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]보증보험금 지급관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-10-14 10:19:25</li>
 * <li>작성자 : 김혁범 (kezmain1)</li>
 * </ul>
 *
 * @author 김혁범 (kezmain1)
 */
public class FNRInsuMxclMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNRInsuMxclMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-10-14 10:19:25
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqInsuMxclLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
	    IDataSet dsCnt = new DataSet();
	    IRecordSet usrListRs =  null;
	    IRecordSet usrListRs2 =  null;
	    IRecordSet rsList = null;
	    int intTotalCnt = 0;
	    
	    try {
		    // 1. DU lookup
	    	DNRInsuMxclMgmt dNRInsuMxclMgmt = (DNRInsuMxclMgmt) lookupDataUnit(DNRInsuMxclMgmt.class);

			// 3. LISTDM호출
			responseData = dNRInsuMxclMgmt.dSInsuMxclSlipLstPaging(requestData, onlineCtx);
			usrListRs = responseData.getRecordSet("RS_SLIP_LIST");
			
			// 2. TOTAL COUNT DM호출
		    IDataSet dsRtn = dNRInsuMxclMgmt.dSInsuMxclSumTotCnt(requestData, onlineCtx);
		    rsList = dsRtn.getRecordSet("RS_SUM_LIST");
		    
			responseData.putRecordSet("RS_SLIP_LIST", usrListRs);
			responseData.putRecordSet("RS_SUM_LIST", rsList);
		
	    } catch ( BizRuntimeException e ) {
	    	throw e; //시스템 오류가 발생하였습니다.
	    }
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-10-20 10:12:17
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSaveInqInsuMxclSlip(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);

        try {
			
        	// 1. 입력 RS설정
    		requestData.putField("USERNO", ca.getUserNo());
        	requestData.putField("SLIP_TYPE", "NR_HP");

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
			
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
    
    
        return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-10-20 10:39:30
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSaveInqInsuMxclSlipDel(IDataSet requestData, IOnlineContext onlineCtx) {

	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	    
	    try{

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

		    if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다

	    } catch(BizRuntimeException e){
	    	throw e;
	    } catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-10-20 11:10:45
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqInsuMxclAllExcel(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IDataSet temp = new DataSet();
	    
	    try {
	    	
            // 3. DU lookup
	    	DNRInsuMxclMgmt dNRInsuMxclMgmt = (DNRInsuMxclMgmt) lookupDataUnit(DNRInsuMxclMgmt.class);

            // 4. LISTDM호출
            responseData = dNRInsuMxclMgmt.dSInqInsuMxclAllExcel(requestData, onlineCtx);
	            
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
	    
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-10-21 17:06:42
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqInsuRfndXclLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
	    IDataSet dsCnt = new DataSet();
	    IRecordSet usrListRs =  null;
	    IRecordSet usrListRs2 =  null;
	    IRecordSet rsList = null;
	    int intTotalCnt = 0;
	    
	    try {
		    // 1. DU lookup
	    	DNRInsuMxclMgmt dNRInsuMxclMgmt = (DNRInsuMxclMgmt) lookupDataUnit(DNRInsuMxclMgmt.class);
	    	
	    	
		    // 2. TOTAL COUNT DM호출            
			dsCnt = dNRInsuMxclMgmt.dSInsuRfndXclTotCnt(requestData, onlineCtx);
	        intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
	        PagenateUtils.setPagenatedParamsToDataSet(requestData);
	       			
			// 3. LISTDM호출
			responseData = dNRInsuMxclMgmt.dSInsuRfndXclSlipLstPaging(requestData, onlineCtx);
			usrListRs = responseData.getRecordSet("RS_SLIP_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(usrListRs, requestData, intTotalCnt);
			
			// 2. TOTAL COUNT DM호출
		    IDataSet dsRtn = dNRInsuMxclMgmt.dSInsuRfndXclSumTotCnt(requestData, onlineCtx);
		    rsList = dsRtn.getRecordSet("RS_SUM_LIST");
		    PagenateUtils.setPagenatedParamToRecordSet(rsList, requestData, intTotalCnt);
		    
			responseData.putRecordSet("RS_SLIP_LIST", usrListRs);
			responseData.putRecordSet("RS_SUM_LIST", rsList);
		
	    } catch ( BizRuntimeException e ) {
	    	throw e; //시스템 오류가 발생하였습니다.
	    }
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-10-22 10:52:02
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSaveInqInsuRfndXclSlip(IDataSet requestData, IOnlineContext onlineCtx) {

	    IDataSet responseData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);

        try {
        	// 1. 입력 RS설정
    		requestData.putField("USERNO", ca.getUserNo());
        	requestData.putField("SLIP_TYPE", "NR_HR");

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

		    if(result == -1){
		    	throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.    			
		    }

			
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-10-22 13:49:26
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqInsuRfndXclAllExcel(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    IDataSet temp = new DataSet();
	    
	    try {
	    	
            // 3. DU lookup
	    	DNRInsuMxclMgmt dNRInsuMxclMgmt = (DNRInsuMxclMgmt) lookupDataUnit(DNRInsuMxclMgmt.class);

            // 4. LISTDM호출
            responseData = dNRInsuMxclMgmt.dSInqInsuRfndXclAllExcel(requestData, onlineCtx);
	            
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
	    
	    return responseData; 
	    
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-10-22 14:13:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSaveInqInsuRfndXclSlipDel(IDataSet requestData, IOnlineContext onlineCtx) {
	
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	    
	    try{
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

			    if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다
	    	
	    } catch(BizRuntimeException e){
	    	throw e;
	    } catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-10-14 10:19:25
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqInsuMxclReBatch(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	    

	    try {	    	
    		// 1. DU lookup
	    	//DNRAgnEqpStlMgmt dNRAgnEqpStlMgmt = (DNRAgnEqpStlMgmt) lookupDataUnit(DNRAgnEqpStlMgmt.class);
    	
    		// 2. LISTDM호출
    		//IDataSet dsRtn = dNRAgnEqpStlMgmt.dSAgnreBatchDt(requestData, onlineCtx);
    	
    		
	    	//if(dsRtn.getIntField("CNT") > 0 && dsRtn.getIntField("REBAT_DT") == 0){
    			/* TODO : 에러코드입력 필요함({유효기간}에  주복 데이터가 있습니다). 현재는 중복데이타가 있습니다로 사용.*/
    		//throw new BizRuntimeException("DMS00117"); //재집계가 불가합니다.
    		//}
	    	
		    ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
			DataSetXmlTransformer.dataSetToXml(requestData, bout, "UTF-8");
			String dsXml = bout.toString("UTF-8");
		
			// call on-demand batch job
			HashMap params = new HashMap<String,String>();
			params.put("TASK_ID", "XCR015");
		    params.put("TASK_NM", "보증보험금(지급)");
		    params.put("LGIN_ID", onlineCtx.getUserInfo().getLoginId());
		    params.put("USER_NO", ca.getUserNo());
		    params.put("PROC_DT", requestData.getField("XCL_YM"));
		    params.put("COMPONENTNAME_LOCAL_ONLY", "dms.nr.XCR015");				
			params.put("ONDEMAND_DATASET", dsXml);
			
			String jobExecutionId = callBatchJob("XCR015", params, onlineCtx);
		    waitBatchJobEnd(jobExecutionId, 10000);
		    int result = getJobReturnCode(jobExecutionId);
		    
		    if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.
		    
	} catch ( BizRuntimeException e ) {
		throw e; //시스템 오류가 발생하였습니다.
	
	} catch (UnsupportedEncodingException ee) {
		throw new BizRuntimeException("DMS00009",ee); //시스템 오류가 발생하였습니다.
	}
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-11-13 09:39:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqInsuRfndXclReBatch(IDataSet requestData, IOnlineContext onlineCtx) {
		 IDataSet responseData = new DataSet();
		    CommonArea ca = getCommonArea(onlineCtx);
		    

		    try {	    	
	    		// 1. DU lookup
		    	//DNRAgnEqpStlMgmt dNRAgnEqpStlMgmt = (DNRAgnEqpStlMgmt) lookupDataUnit(DNRAgnEqpStlMgmt.class);
	    	
	    		// 2. LISTDM호출
	    		//IDataSet dsRtn = dNRAgnEqpStlMgmt.dSAgnreBatchDt(requestData, onlineCtx);
	    	
	    		
		    	//if(dsRtn.getIntField("CNT") > 0 && dsRtn.getIntField("REBAT_DT") == 0){
	    			/* TODO : 에러코드입력 필요함({유효기간}에  주복 데이터가 있습니다). 현재는 중복데이타가 있습니다로 사용.*/
	    		//throw new BizRuntimeException("DMS00117"); //재집계가 불가합니다.
	    		//}
		    	
			    ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
				DataSetXmlTransformer.dataSetToXml(requestData, bout, "UTF-8");
				String dsXml = bout.toString("UTF-8");
			
				// call on-demand batch job
				HashMap params = new HashMap<String,String>();
				params.put("TASK_ID", "XCR017");
			    params.put("TASK_NM", "보증보험금(환급)");
			    params.put("LGIN_ID", onlineCtx.getUserInfo().getLoginId());
			    params.put("USER_NO", ca.getUserNo());
			    params.put("PROC_DT", requestData.getField("XCL_YM"));
			    params.put("COMPONENTNAME_LOCAL_ONLY", "dms.nr.XCR017");				
				params.put("ONDEMAND_DATASET", dsXml);
				
				String jobExecutionId = callBatchJob("XCR017", params, onlineCtx);
			    waitBatchJobEnd(jobExecutionId, 10000);
			    int result = getJobReturnCode(jobExecutionId);
			    
			    if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.
			    
		} catch ( BizRuntimeException e ) {
			throw e; //시스템 오류가 발생하였습니다.
		
		} catch (UnsupportedEncodingException ee) {
			throw new BizRuntimeException("DMS00009",ee); //시스템 오류가 발생하였습니다.
		}
		    return responseData;
		}

	/**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2016-01-19 18:17:52
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqInsuRfndXclDtlLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
	    
	    try {
		    // 1. DU lookup
	    	DNRInsuMxclMgmt dNRInsuMxclMgmt = (DNRInsuMxclMgmt) lookupDataUnit(DNRInsuMxclMgmt.class);
	    	
			// 2. LISTDM호출
			responseData = dNRInsuMxclMgmt.dSInsuRfndXclDtlLst(requestData, onlineCtx);
		
	    } catch ( BizRuntimeException e ) {
	    	throw e; //시스템 오류가 발생하였습니다.
	    }
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-10-14 10:19:25
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqInsuMxclDtlLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
	    
	    try {
		    // 1. DU lookup
	    	DNRInsuMxclMgmt dNRInsuMxclMgmt = (DNRInsuMxclMgmt) lookupDataUnit(DNRInsuMxclMgmt.class);
	    	
			// 2. LISTDM호출
			responseData = dNRInsuMxclMgmt.dSInsuMxclLstDtlLst(requestData, onlineCtx);
		
	    } catch ( BizRuntimeException e ) {
	    	throw e; //시스템 오류가 발생하였습니다.
	    }
	
	    return responseData;
	}
  
}