package dms.nr.nrsxmbase.biz;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import fwk.common.CommonArea;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.xml.DataSetXmlTransformer;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]렌탈비정산정보조회</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-09-07 19:14:02</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class FNRRentalFeeCctlMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNRRentalFeeCctlMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-07 19:14:02
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CL [구분]
	 *	- field : RENTAL_YM [렌탈년월]
	 *	- field : OP_TYP_CD [계약상태]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_RENTAL_FEE_INFO_LIST
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : RENTAL_FEE [DMS금액 = 렌탈료]
	 *		- field : INV_AMT [UKEY금액 = 청구금액]
	 *		- field : RECV_AMT [수납금액]
	 *	- record : RS_RENTAL_FEE_INFO_DETAIL
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : RENT_STA_DT [계약시작일]
	 *		- field : RENT_END_SCHD_DT [계약종료예정일]
	 *		- field : RENT_END_DT [실 계약종료일]
	 *		- field : INV_AMT [UKEY금액 = 청구금액]
	 *		- field : RECV_AMT [수납금액]
	 *		- field : ARREAR_PRC [미납금액]
	 *		- field : RENTAL_FEE [DMS금액 =렌탈료]
	 *		- field : CNT [회차]
	 *	- record : RS_SUM_LIST
	 *		- field : M_CNT [메인총카운트]
	 *		- field : M_PRC [메인총금액]
	 *		- field : S_CNT [서브총카운트]
	 *		- field : S_PRC [서브총금액]
	 *		- field : ST_CNT [전표용카운트]
	 *		- field : ST_PRC [전표용총금액]
	 *	- record : RS_RENTAL_FEE_TOTAL_LIST
	 *		- field : DEALCO_BLICENS_NO [사업자등록번호]
	 *		- field : DEALCO_NM [법인명]
	 *		- field : RECV_AMT [수납요금]
	 *		- field : SELEC [구분]
	 * </pre>
	 */
	public IDataSet fInqRentalFeeCctlInfoLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IDataSet paramData = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		 
		try {
			// 1. DU lookup
			DNRRentalFeeCctlMgmt dNRRentalFeeCctlMgmt = (DNRRentalFeeCctlMgmt) lookupDataUnit(DNRRentalFeeCctlMgmt.class);

			// 2. main list
			paramData = dNRRentalFeeCctlMgmt.dSRentalFeeCctlInfoLstPaging(reqDs, onlineCtx);
			IRecordSet usrListRs = paramData.getRecordSet("RS_RENTAL_FEE_INFO_LIST");
			paramData.putField("RENTAL_YM", reqDs.getField("RENTAL_YM"));
			paramData.putField("OP_TYP_CD", reqDs.getField("OP_TYP_CD"));
			paramData.putField("CL", reqDs.getField("CL"));
			
			responseData.putRecordSet("RS_RENTAL_FEE_INFO_LIST",usrListRs);
			//3. second list						
			if(usrListRs == null){
				return responseData;
			}
			
			paramData = dNRRentalFeeCctlMgmt.dSRentalFeeCctlInfoLstPagingDtl(paramData, onlineCtx);
			paramData.putField("RENTAL_YM", reqDs.getField("RENTAL_YM"));
			paramData.putField("OP_TYP_CD", reqDs.getField("OP_TYP_CD"));
			paramData.putField("CL", reqDs.getField("CL"));
			
			responseData.putRecordSet("RS_RENTAL_FEE_INFO_DETAIL",paramData.getRecordSet("RS_RENTAL_FEE_INFO_DETAIL"));
			//4. 전표발행용 list
			paramData = dNRRentalFeeCctlMgmt.dSRentalFeeTotalLstPaging(paramData, onlineCtx);

			responseData.putRecordSet("RS_RENTAL_FEE_TOTAL_LIST",paramData.getRecordSet("RS_RENTAL_FEE_TOTAL_LIST"));
			responseData.putRecordSet("RS_SUM_LIST", paramData.getRecordSet("RS_SUM_LIST") );
			} catch ( BizRuntimeException e ) {
				throw e; //시스템 오류가 발생하였습니다.
			}
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-07 19:14:02
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CNTRT_NO [계약번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_RENTAL_FEE_DTL
	 *		- field : RENTAL_FEE [DMS금액]
	 *		- field : INV_AMT [UKEY금액]
	 *		- field : RECV_AMT [수납금액]
	 *		- field : ARREAR_PRC [미납금액]
	 *		- field : RENT_YM [렌트년월]
	 *	- record : RS_RENTAL_INFO
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : NEW_CNTRTR_NM [고객명]
	 *		- field : SVC_NO [전화번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : RENTAL_PRN [렌탈원금]
	 *		- field : OP_TYP_CD [계약코드]
	 *		- field : CNT [회차]
	 *		- field : SLIP_ST [전표상태]
	 *		- field : SLIP_NO [전표코드]
	 *		- field : RENT_STA_DT [계약시작일]
	 *		- field : RENT_END_SCHD_DT [종료예정일]
	 *		- field : RENT_END_DT [종료일]
	 * </pre>
	 */
	public IDataSet fInqRentalFeeCctlInfoLstDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
	    
	    try {
			// 1. DU lookup
			DNRRentalFeeCctlMgmt dNRRentalFeeCctlMgmt = (DNRRentalFeeCctlMgmt) lookupDataUnit(DNRRentalFeeCctlMgmt.class);

			// 2.main list
			responseData = dNRRentalFeeCctlMgmt.dSRentalFeeCctlInfoLstDtlM(reqDs, onlineCtx);
			
			//3.grid list						
			
			IDataSet dsRtn = dNRRentalFeeCctlMgmt.dSRentalFeeCctlInfoLstDtlG(reqDs, onlineCtx);
			responseData.putRecordSet("RS_RENTAL_INFO",dsRtn.getRecordSet("RS_RENTAL_INFO"));	
			} catch ( BizRuntimeException e ) {
				throw e; //시스템 오류가 발생하였습니다.
			}
	    
	    
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-07 19:14:02
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRentalFeeBatchCallOnline(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
		   
	    try {
	    	 
			ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
			DataSetXmlTransformer.dataSetToXml(requestData, bout, "UTF-8");
			String dsXml = bout.toString("UTF-8");

			// call on-demand batch job
			HashMap params = new HashMap<String,String>();
			params.put("TASK_ID", "XCR006");
			params.put("TASK_NM", "월렌탈료정산");
		    params.put("LGIN_ID", onlineCtx.getUserInfo().getLoginId());
		    params.put("USER_NO", ca.getUserNo());
		    params.put("PROC_DT", requestData.getField("RENTAL_YM")+"01");    //렌탈년월
		    params.put("COMPONENTNAME_LOCAL_ONLY", "dms.nr.XCR006");
			params.put("ONDEMAND_DATASET", dsXml);
			String jobExecutionId = callBatchJob("XCR006", params, onlineCtx);
		    waitBatchJobEnd(jobExecutionId, 10000);
		    int result = getJobReturnCode(jobExecutionId);
		    
		    if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.
    			
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
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-09-07 19:14:02
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : RENTAL_YM [렌탈년월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_ALL_EXCEL_LIST
	 *		- field : NO [NO]
	 *		- field : CL [구분]
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : EQP_SER_NO [단말일련번호]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : RENT_STA_DT [렌탈시작일]
	 *		- field : RENT_END_SCHD_DT [렌탈종료예정일]
	 *		- field : RENT_END_DT [실종료일]
	 *		- field : RENTAL_FEE_DMS [DMS렌탈요금]
	 *		- field : INV_SPLY_PRC [UKEY청구요금]
	 *		- field : RECV_SPLY_PRC [수납금액]
	 *		- field : PRC [미납금액]
	 *		- field : CNT [회차]
	 *		- field : CNTRT_PRD [계약기간]
	 *		- field : POLY_NM [정책명]
	 *		- field : OUT_PRC [출고가]
	 *		- field : RENTAL_PRN [렌탈원금]
	 *		- field : RENTAL_FEE [렌탈요금]
	 * </pre>
	 */
	public IDataSet fInqRentalFeeAllExcel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet temp = new DataSet();
        
        try {
      
            // 1. DU lookup
            DNRRentalFeeCctlMgmt dNRRentalFeeCctlMgmt = (DNRRentalFeeCctlMgmt) lookupDataUnit(DNRRentalFeeCctlMgmt.class);

            // 2. LISTDM호출
            responseData = dNRRentalFeeCctlMgmt.dSRentalFeeAllExcel(requestData, onlineCtx); 
            
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-07 19:14:02
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet fInqRentalFeeCctlInfoLstAddDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        
        try {
    		// 1. DU lookup
    		DNRRentalFeeCctlMgmt dNRRentalFeeCctlMgmt = (DNRRentalFeeCctlMgmt) lookupDataUnit(DNRRentalFeeCctlMgmt.class);
    
    		// 2.main list
    		responseData = dNRRentalFeeCctlMgmt.dSRentalFeeCctlInfoLstDtlM(reqDs, onlineCtx);
    		
    		//3.grid list						
    		
    		IDataSet dsRtn = dNRRentalFeeCctlMgmt.dSRentalFeeCctlInfoLstDtlAddG(reqDs, onlineCtx);
    		responseData.putRecordSet("RS_RENTAL_INFO",dsRtn.getRecordSet("RS_RENTAL_INFO"));	
    		} catch ( BizRuntimeException e ) {
    			throw e; //시스템 오류가 발생하였습니다.
    		}
        
        
        return responseData;
    }

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-12-10 13:25:18
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRentalFeeSlipCreate(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	    
	    try {
	    	 
	    	IDataSet reqDs = this.fInqRentalFeeCctlInfoLst(requestData, onlineCtx);
	    	IRecordSet deprRs = reqDs.getRecordSet("RS_RENTAL_FEE_TOTAL_LIST");
	    	IRecordSet sumRs = reqDs.getRecordSet("RS_SUM_LIST");
	    	
	    	if( deprRs == null ){
	    		return responseData;
	    	}else{
				
				for(int i=0; i<deprRs.getRecordCount(); i++){
					IRecord ir = deprRs.getRecord(i);
					if("10".equals(ir.get("SLIP_ST_CD")) || "20".equals(ir.get("SLIP_ST_CD")) || "30".equals(ir.get("SLIP_ST_CD")) ){
						throw new BizRuntimeException("DMS00071"); // 이미 처리된 건이 있습니다.
					}
				}
				
			}
	    	
	    	requestData.putRecordSet(deprRs);
	    	requestData.putRecordSet(sumRs);

	    	requestData.putField("SLIP_TYPE", "ASSET_DEPRECIATION");//전표유형
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
	 * @since 2015-12-10 13:26:15
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRentalFeeSlipCancle(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	    
	    try {
	    	IDataSet reqDs = this.fInqRentalFeeCctlInfoLst(requestData, onlineCtx);
	    	IRecordSet deprRs = reqDs.getRecordSet("RS_RENTAL_FEE_TOTAL_LIST");
	    	IRecordSet sumRs = reqDs.getRecordSet("RS_SUM_LIST");
	    	
	    	if( deprRs == null ){
					return responseData;
			}else {
				
				for(int i=0; i<deprRs.getRecordCount(); i++){
					IRecord ir = deprRs.getRecord(i);
					
					if(StringUtils.isEmpty(ir.get("SLIP_ST_CD")) || "00".equals(ir.get("SLIP_ST_CD")) || "05".equals(ir.get("SLIP_ST_CD")) ){
						
						throw new BizRuntimeException("DMS00071"); // 이미 처리된 건이 있습니다.
					
					}else if( "20".equals(ir.get("SLIP_ST_CD")) || "30".equals(ir.get("SLIP_ST_CD"))){
						
						throw new BizRuntimeException("DMS00087"); //{0} 건은 처리가 불가능 합니다.--승인완료인
					}
				}
			}
	    	
	    	requestData.putRecordSet(deprRs);
	    	requestData.putRecordSet(sumRs);
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    requestData.getRecordSet("RS_DEPR_LIST").setId("RS_SLIP_DELETE");//SLIP_NO,YYYY들어있음
	    IRecord totalSum = requestData.getRecordSet("RS_SUM_LIST").getRecord(0);
	    
	    requestData.putField("DEPR_AMT", totalSum.get("DEPR_AMT"));//감가상각액총액
	    requestData.putField("ALLWN_AMT", totalSum.get("ALLWN_AMT"));//충당부채액총액
	    requestData.putField("SLIP_TYPE", "AGENCY_BILLING");//전표유형
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
	
	    } catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
	    return responseData;
	}
  
}
