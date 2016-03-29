package dms.nr.nrsisbase.biz;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.xml.DataSetXmlTransformer;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.common.CommonArea;
import fwk.utils.PagenateUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]보증보험청구정보관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-10-08 11:04:01</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class FNRInsuInvMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNRInsuInvMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-10-08 10:49:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqInsuInvInfoLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IDataSet dsCnt = new DataSet();
		IRecordSet usrListRs = null;
		int intTotalCnt = 0;

		try {
			// 1. DU lookup
			DNRInsuInvMgmt dNRInsuInvMgmt = (DNRInsuInvMgmt) lookupDataUnit(DNRInsuInvMgmt.class);

			// 2. TOTAL COUNT DM호출
			dsCnt = dNRInsuInvMgmt.dSInsuInvInfoTotCnt(requestData, onlineCtx);
			IRecordSet sumRs = dsCnt.getRecordSet("RS_SUM_LIST");
			
			intTotalCnt = Integer.parseInt(sumRs.get(0,"TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(requestData);
			
			// 3. LISTDM호출
			responseData = dNRInsuInvMgmt.dSInsuInvInfoLstPaging(requestData, onlineCtx);
			usrListRs = responseData.getRecordSet("RS_INSU_INV_LIST");
			
			responseData.putRecordSet("RS_SUM_LIST", sumRs);
			PagenateUtils.setPagenatedParamToRecordSet(sumRs, requestData, intTotalCnt);
			
			responseData.putRecordSet("RS_INSU_INV_LIST",usrListRs);
			PagenateUtils.setPagenatedParamToRecordSet(usrListRs, requestData, intTotalCnt);
			
	    } catch ( BizRuntimeException e ) {
	        throw e; //시스템 오류가 발생하였습니다.
	    }
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-10-12 11:31:06
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqInsuInvInfoLstDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
			// 1. DU lookup
			DNRInsuInvMgmt dNRInsuInvMgmt = (DNRInsuInvMgmt) lookupDataUnit(DNRInsuInvMgmt.class);

			// 2. LIST DM호출
			responseData = dNRInsuInvMgmt.dSInsuInvInfoLstPagingDtl(requestData, onlineCtx);
			
	    } catch ( BizRuntimeException e ) {
	        throw e; //시스템 오류가 발생하였습니다.
	    }
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-10-08 11:04:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_RSV_LIST
	 *		- field : UKEY_INSURE_MGMT_NO [보증보험관리번호]
	 *		- field : INV_ST_CD [처리상태코드]
	 *		- field : INSU_INV_NO [보증보험청구번호]
	 *		- field : RSN_CD [사유코드]
	 *		- field : INSU_INV_MEMO [메모]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdateInsuInvRsv(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	    
	    try {
	    	// 1. DU lookup
	    	DNRInsuInvMgmt dNRInsuInvMgmt = (DNRInsuInvMgmt) lookupDataUnit(DNRInsuInvMgmt.class);
	    	requestData.putField("USER_NO", ca.getUserNo());
	    	// 2. LIST DM호출
	    	dNRInsuInvMgmt.dUpdateInsuInvRsv(requestData, onlineCtx);
	    	
	    } catch ( BizRuntimeException e ) {
	    	throw e; //시스템 오류가 발생하였습니다.
	    }
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-10-12 17:08:57
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdateInsuInvclaim(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	    
	    try {
	    	// 1. DU lookup
	    	DNRInsuInvMgmt dNRInsuInvMgmt = (DNRInsuInvMgmt) lookupDataUnit(DNRInsuInvMgmt.class);
	    	requestData.putField("USER_NO", ca.getUserNo());
	    	// 2. LIST DM호출
	    	dNRInsuInvMgmt.dUpdateInsuInvclaim(requestData, onlineCtx);
	    	
	    } catch ( BizRuntimeException e ) {
	    	throw e; //시스템 오류가 발생하였습니다.
	    }
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-10-12 17:09:19
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdateInsuInvclaimCancle(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	    
	    try {
	    	// 1. DU lookup
	    	DNRInsuInvMgmt dNRInsuInvMgmt = (DNRInsuInvMgmt) lookupDataUnit(DNRInsuInvMgmt.class);
	    	requestData.putField("USER_NO", ca.getUserNo());
	    	// 2. LIST DM호출
	    	dNRInsuInvMgmt.dUpdateInsuInvclaimCancle(requestData, onlineCtx);
	    	
	    } catch ( BizRuntimeException e ) {
	    	throw e; //시스템 오류가 발생하였습니다.
	    }
	
	    return responseData;
	}

    /**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-10-08 11:04:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : PROC_DT [처리일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqInsuInvStSync(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);
        
        try {
             
            ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
            DataSetXmlTransformer.dataSetToXml(requestData, bout, "UTF-8");
            String dsXml = bout.toString("UTF-8");

            // call on-demand batch job
            HashMap params = new HashMap<String,String>();
            params.put("TASK_ID", "DBR027");
            params.put("TASK_NM", "수납/환불등록(30분)");
            params.put("LGIN_ID", onlineCtx.getUserInfo().getLoginId());
            params.put("USER_NO", ca.getUserNo());
            params.put("PROC_DT", requestData.getField("PROC_DT"));    //처리일자
            params.put("COMPONENTNAME_LOCAL_ONLY", "dms.nr.DBR027");
            params.put("ONDEMAND_DATASET", dsXml);
            String jobExecutionId = callBatchJob("DBR027", params, onlineCtx);
            waitBatchJobEnd(jobExecutionId, 10000);
            int result = getJobReturnCode(jobExecutionId);
            
            if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.
            
            // 1. DU lookup
            DNRInsuInvMgmt dNRInsuInvMgmt = (DNRInsuInvMgmt) lookupDataUnit(DNRInsuInvMgmt.class);
            // 2. LIST DM호출
            responseData = dNRInsuInvMgmt.dSInsuInvStSync(requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }
  
}
