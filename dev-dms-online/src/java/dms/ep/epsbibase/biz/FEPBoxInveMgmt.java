package dms.ep.epsbibase.biz;

import java.util.Map;

import fwk.utils.PagenateUtils;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/중고폰</li>
 * <li>단위업무명: [FU]박스재고관리</li>
 * <li>설  명 : <pre>[FU]박스재고관리</pre></li>
 * <li>작성일 : 2015-07-22 18:09:54</li>
 * <li>작성자 : 이준우 (elmsliw)</li>
 * </ul>
 *
 * @author 이준우 (elmsliw)
 */
public class FEPBoxInveMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FEPBoxInveMgmt(){
		super();
	}

	/**
	 * <pre>[FM]박스재고조회</pre>
	 *
	 * @author 이준우 (elmsliw)
	 * @since 2015-08-24 09:12:06
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : BOX_NO [박스번호]
	 *	- field : CNSL_MGMT_NO [상담관리번호]
	 *	- field : PROD_CNT [상품수량]
	 *	- field : BOX_CNT [박스수량]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_BIL_LIST
	 *		- field : CHECKED [분실일자]
	 *		- field : CNSL_MGMT_NO [접수번호]
	 *		- field : CNSL_DT [접수일]
	 *		- field : BOX_RMK [박스코멘트]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : MFACT_CD [제조사]
	 *		- field : PROD_CHRTIC_1 [단말그룹]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_ST [등급]
	 *		- field : EQP_ST_DTL [등급상세]
	 *		- field : CNSL_DEALCO_CD [상담처코드]
	 *		- field : CNSL_DEALCO_NM [상담처명]
	 *		- field : DPSTR [예금주(고객명)]
	 *		- field : CTZ_NO [주민번호]
	 *		- field : DTL_1 [DTL_1]
	 *		- field : DTL_2 [DTL_2]
	 *		- field : DTL_3 [DTL_3]
	 *		- field : DTL_4 [DTL_4]
	 *		- field : DTL_5 [DTL_5]
	 *		- field : S_GB [S_GB]
	 *		- field : PRCH_AMT [매입가]
	 *		- field : UBO_AMT [유보금액]
	 *		- field : TOT_AMT [총금액]
	 *		- field : IMEI [IMEI]
	 *		- field : SELL_GRADE [판매 등급]
	 *	- record : RS_BOX_INFO
	 *		- field : BOX_NO [필드1]
	 *		- field : CNSL_MGMT_NO [필드2]
	 *		- field : PROD_QTY [상품수량]
	 *		- field : BOX_CNT [박스수량]
	 * </pre>
	 */
    public IDataSet fInqBoxInveLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet dsCnt = new DataSet();
        
    	IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
    	IDataSet reqDs1 = (IDataSet) requestData.clone();
    	IRecordSet rsPagingList = null;
    	int intTotalCnt = 0;
    	
    	try {
    		// 1. DU lookup
    	    DEPBoxInveMgmt dEPBoxInveMgmt = (DEPBoxInveMgmt) lookupDataUnit(DEPBoxInveMgmt.class);
    		
    		// 2. TOTAL COUNT DM호출
    		dsCnt = dEPBoxInveMgmt.dSBoxInveLstTotCnt(reqDs, onlineCtx);
    		intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
    		PagenateUtils.setPagenatedParamsToDataSet(reqDs);
    		
    		// 3. LISTDM호출
    		responseData = dEPBoxInveMgmt.dSBoxInveLstPaging(reqDs, onlineCtx);
    
    		rsPagingList = responseData.getRecordSet("RS_BIL_LIST");
    		PagenateUtils.setPagenatedParamToRecordSet(rsPagingList, reqDs, intTotalCnt);
    		
    	} catch ( BizRuntimeException e ) {
    		throw e;
    	} catch ( Exception e ) {
    		throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
    	}	
    
        return responseData;
    }

    /**
	 * <pre>[FM]박스재고조회수정</pre>
	 *
	 * @author 이준우 (elmsliw)
	 * @since 2015-08-24 09:12:06
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_BIL_LIST
	 *		- field : CHECKED [CHECKED]
	 *		- field : CNSL_MGMT_NO [접수번호]
	 *		- field : CNSL_DT [접수일]
	 *		- field : BOX_RMK [박스코멘트]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : MFACT_NM [제조사]
	 *		- field : PROD_CHRTIC_1 [단말그룹]
	 *		- field : EQP_MDL_NM [모델]
	 *		- field : EQP_ST [등급]
	 *		- field : CNSL_DEALCO_CD [상담처코드]
	 *		- field : DEALCO_NM [상담처명]
	 *		- field : DPSTR [고객명]
	 *		- field : CTZ_NO [생년월일]
	 *		- field : DTL_1 [기본배터리]
	 *		- field : DTL_2 [보조배터리]
	 *		- field : DTL_3 [배터리케이스]
	 *		- field : DTL_4 [USB케이블]
	 *		- field : DTL_5 [충전기]
	 *		- field : S_GB [ㅁㅁㅁ]
	 *		- field : PRCH_AMT [매입가]
	 *		- field : UBO_AMT [유보금액]
	 *		- field : TOT_AMT [총금액]
	 *		- field : IMEI [IMEI]
	 *		- field : LAST_CHG_USER_ID [필드1]
	 *	- record : RS_BOX_INFO
	 *		- field : BOX_NO [필드1]
	 *		- field : CNSL_MGMT_NO [필드2]
	 *		- field : PROD_CNT [필드3]
	 *		- field : BOX_CNT [필드4]
	 *		- field : LAST_CHG_USER_ID [필드1]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet fUpdBoxInveLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
    		// 1. DU lookup
            DEPBoxInveMgmt dEPBoxInveMgmt = (DEPBoxInveMgmt) lookupDataUnit(DEPBoxInveMgmt.class);
        	
    		// 3. 박스재고 정보 수정(상담관리, 매입관리 , 박스재고상세)
        	
    		responseData = dEPBoxInveMgmt.dUBoxInveTbECCM(requestData, onlineCtx);   	 //상담
    		responseData = dEPBoxInveMgmt.dUBoxInveTbECPM(requestData, onlineCtx);       //매입
    		responseData = dEPBoxInveMgmt.dUBoxInveTbEFEM(requestData, onlineCtx);       //FPA
    		responseData = dEPBoxInveMgmt.dUBoxBarCdDtlBoxInit(requestData, onlineCtx);
    		
    	} catch ( BizRuntimeException e ) {
    		throw e;
    	} 
       
        return responseData;
    }

    /**
	 * <pre>[FM]박스재고정보수정</pre>
	 *
	 * @author 이준우 (elmsliw)
	 * @since 2015-08-24 09:12:06
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_BIL_LIST
	 *		- field : CHECKED [CHECKED]
	 *		- field : CNSL_MGMT_NO [접수번호]
	 *		- field : CNSL_DT [접수일]
	 *		- field : BOX_RMK [박스코멘트]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : MFACT_NM [제조사]
	 *		- field : PROD_CHRTIC_1 [단말그룹]
	 *		- field : EQP_MDL_NM [모델]
	 *		- field : EQP_ST [등급]
	 *		- field : CNSL_DEALCO_CD [상담처코드]
	 *		- field : DEALCO_NM [상담처명]
	 *		- field : DPSTR [고객명]
	 *		- field : CTZ_NO [생년월일]
	 *		- field : DTL_1 [기본배터리]
	 *		- field : DTL_2 [보조배터리]
	 *		- field : DTL_3 [배터리케이스]
	 *		- field : DTL_4 [USB케이블]
	 *		- field : DTL_5 [충전기]
	 *		- field : S_GB [ㅁㅁㅁ]
	 *		- field : PRCH_AMT [매입가]
	 *		- field : UBO_AMT [유보금액]
	 *		- field : TOT_AMT [총금액]
	 *		- field : IMEI [IMEI]
	 *	- record : RS_BOX_INFO
	 *		- field : BOX_NO [필드1]
	 *		- field : CNSL_MGMT_NO [필드2]
	 *		- field : PROD_QTY [필드3]
	 *		- field : QTY [필드4]
	 *		- field : LAST_CHG_USER_ID [필드1]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet fUpdBoxInveInfoLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
    		// 1. DU lookup
            DEPBoxInveMgmt dEPBoxInveMgmt = (DEPBoxInveMgmt) lookupDataUnit(DEPBoxInveMgmt.class);
        	
    		// 2. 박스재고 정보 수정(바코드관리 정보 수정)
        	
    		responseData = dEPBoxInveMgmt.dUBoxInveTbEBBL(requestData, onlineCtx);    
    		
    	} catch ( BizRuntimeException e ) {
    		throw e;
    	} 
       
        return responseData;
    }

    /**
	 * <pre>[FM]박스재고정보조회</pre>
	 *
	 * @author 이준우 (elmsliw)
	 * @since 2015-08-24 09:12:06
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : BOX_NO [박스번호]
	 *	- field : CNSL_MGMT_NO [상담관리번호]
	 *	- field : PROD_CNT [상품수량]
	 *	- field : BOX_CNT [박스수량]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_BIL_LIST
	 *		- field : CHECKED [분실일자]
	 *		- field : CNSL_MGMT_NO [접수번호]
	 *		- field : CNSL_DT [접수일]
	 *		- field : BOX_RMK [박스코멘트]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : MFACT_CD [제조사]
	 *		- field : PROD_CHRTIC_1 [단말그룹]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_ST [등급]
	 *		- field : EQP_ST_DTL [등급상세]
	 *		- field : CNSL_DEALCO_CD [상담처코드]
	 *		- field : CNSL_DEALCO_NM [상담처명]
	 *		- field : DPSTR [예금주(고객명)]
	 *		- field : CTZ_NO [주민번호]
	 *		- field : DTL_1 [DTL_1]
	 *		- field : DTL_2 [DTL_2]
	 *		- field : DTL_3 [DTL_3]
	 *		- field : DTL_4 [DTL_4]
	 *		- field : DTL_5 [DTL_5]
	 *		- field : S_GB [S_GB]
	 *		- field : PRCH_AMT [매입가]
	 *		- field : UBO_AMT [유보금액]
	 *		- field : TOT_AMT [총금액]
	 *		- field : IMEI [IMEI]
	 *	- record : RS_BOX_INFO
	 *		- field : BOX_NO [필드1]
	 *		- field : CNSL_MGMT_NO [필드2]
	 *		- field : PROD_QTY [상품수량]
	 *		- field : BOX_CNT [박스수량]
	 * </pre>
	 */
    public IDataSet fInqBoxInveInfo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet dsCnt = new DataSet();

    	IDataSet reqDs1 = (IDataSet) requestData.clone();
    	
    	try {
    		// 1. DU lookup
    	    DEPBoxInveMgmt dEPBoxInveMgmt = (DEPBoxInveMgmt) lookupDataUnit(DEPBoxInveMgmt.class);

    		// 2. 박스재고정보 조회
            responseData = dEPBoxInveMgmt.dSBoxInveInfoLst(reqDs1, onlineCtx);
            
    	} catch ( BizRuntimeException e ) {
    		throw e;
    	} catch ( Exception e ) {
    		throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
    	}	
    
        return responseData;
    }
  
}
