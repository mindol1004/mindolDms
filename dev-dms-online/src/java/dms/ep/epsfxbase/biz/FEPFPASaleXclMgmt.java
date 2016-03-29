package dms.ep.epsfxbase.biz;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.xml.DataSetXmlTransformer;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.common.CommonArea;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [FU]FPA판매정산관리</li>
 * <li>설  명 : <pre>FPA판매정산관리 FU</pre></li>
 * <li>작성일 : 2016-02-23 10:04:23</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class FEPFPASaleXclMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FEPFPASaleXclMgmt(){
		super();
	}

    /**
	 * <pre>FPA판매정산관리조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-23 10:04:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SELL_STA_DTM [판매시작일자]
	 *	- field : SELL_END_DTM [판매종료일자]
	 *	- field : SALEPLC [매출처]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqFpaSaleXclMgmtList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
           
        try {
            // 1. DU lookup
            DEPFPASaleXclMgmt dEPFPASaleXclMgmt = (DEPFPASaleXclMgmt) lookupDataUnit(DEPFPASaleXclMgmt.class);

            // 2. LISTDM호출
            responseData = dEPFPASaleXclMgmt.dSFpaSaleXclMgmtList(reqDs, onlineCtx);
           
        } catch ( BizRuntimeException e ) {
           throw e; //시스템 오류가 발생하였습니다.
        }
   
        return responseData;
    }

    /**
	 * <pre>FPA판매정산관리전표발행</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-23 10:04:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SLIP_TYPE [전표구분]
	 *	- record : RS_SLIP_LIST
	 *		- field : GNRL_SELL_NO [일반판매번호]
	 *		- field : GNRL_SELL_CHG_SEQ [일반판매변경순번]
	 *		- field : SELL_CHG_HST_CL [판매변경이력구분]
	 *		- field : SELL_DTM [판매일자]
	 *		- field : SELL_CHG_DTM [변경일자]
	 *		- field : SALEPLC [매출처코드]
	 *		- field : SALEPLC_NM [매출처]
	 *		- field : SALEPLC_BLICENS_NO [매출처사업자번호]
	 *		- field : SALEPLC_UKEY [매출처U.KEY코드]
	 *		- field : PROD_CL [상품구분]
	 *		- field : SALE_QTY [수량]
	 *		- field : SELL_PRC [판매가]
	 *		- field : SELL_PCOST [매입가(판매원가)]
	 *		- field : SURTAX [부가세]
	 *		- field : SELL_UPRC [공급가액(판매단가)]
	 *		- field : SELL_CL_CD [판매구분]
	 *		- field : CUR_CL_CD [화폐 구분 코드]
	 *		- field : XRATE_APPLY_AMT [환율 적용 금액]
	 *		- field : XRATE_APPLY_DT [환율 적용 일자]
	 *		- field : SLIP_DT [전표일자]
	 *		- field : SLIP_NO [전표번호]
	 *		- field : SLIP_ST [전표상태]
	 *		- field : CNCL_SLIP_DT [취소전표일자]
	 *		- field : CNCL_SLIP_NO [취소전표번호]
	 *		- field : INVE_PCOST_SLIP_NO [원가전표번호]
	 *		- field : SLIP_ST_CD [원가전표상태]
	 *		- field : SLIP_TYPE [전표항목구분]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegFpaSaleXclSlip(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        CommonArea ca = getCommonArea(onlineCtx);
        
        try {
                        
            reqDs.putField("USER_NO", ca.getUserNo());
            reqDs.putField("SLIP_TYPE", reqDs.getField("SLIP_TYPE"));

            ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
            DataSetXmlTransformer.dataSetToXml(reqDs, bout, "UTF-8");
            String dsXml = bout.toString("UTF-8");

            // call on-demand batch job
            Map<String ,String> params = new HashMap<String ,String>();
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
	 * <pre>FPA판매정산관리전표취소</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-23 10:04:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SLIP_TYPE [전표구분]
	 *	- record : RS_SLIP_LIST
	 *		- field : GNRL_SELL_NO [일반판매번호]
	 *		- field : GNRL_SELL_CHG_SEQ [일반판매변경순번]
	 *		- field : SELL_CHG_HST_CL [판매변경이력구분]
	 *		- field : SELL_DTM [판매일자]
	 *		- field : SELL_CHG_DTM [변경일자]
	 *		- field : SALEPLC [매출처코드]
	 *		- field : SALEPLC_NM [매출처]
	 *		- field : SALEPLC_BLICENS_NO [매출처사업자번호]
	 *		- field : SALEPLC_UKEY [매출처U.KEY코드]
	 *		- field : PROD_CL [상품구분]
	 *		- field : SALE_QTY [수량]
	 *		- field : SELL_PRC [판매가]
	 *		- field : SELL_PCOST [매입가(판매원가)]
	 *		- field : SURTAX [부가세]
	 *		- field : SELL_UPRC [공급가액(판매단가)]
	 *		- field : SELL_CL_CD [판매구분]
	 *		- field : CUR_CL_CD [화폐 구분 코드]
	 *		- field : XRATE_APPLY_AMT [환율 적용 금액]
	 *		- field : XRATE_APPLY_DT [환율 적용 일자]
	 *		- field : SLIP_DT [전표일자]
	 *		- field : SLIP_NO [전표번호]
	 *		- field : SLIP_ST [전표상태]
	 *		- field : CNCL_SLIP_DT [취소전표일자]
	 *		- field : CNCL_SLIP_NO [취소전표번호]
	 *		- field : INVE_PCOST_SLIP_NO [원가전표번호]
	 *		- field : SLIP_ST_CD [원가전표상태]
	 *		- field : SLIP_TYPE [전표항목구분]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegFpaSaleXclSlipDel(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        CommonArea ca = getCommonArea(onlineCtx);
        
        try {
            
            reqDs.putField("USER_NO", ca.getUserNo());
            reqDs.putField("SLIP_TYPE", reqDs.getField("SLIP_TYPE"));

            ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
            DataSetXmlTransformer.dataSetToXml(reqDs, bout, "UTF-8");
            String dsXml = bout.toString("UTF-8");

            // call on-demand batch job
            Map<String ,String> params = new HashMap<String ,String>();
            params.put("TASK_ID", "EPR011");
            params.put("TASK_NM", "전표취소");
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
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
        
        return responseData;
    }

    /**
	 * <pre>FPA판매정산관리상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-23 10:04:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : GNRL_SELL_NO [판매번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqFpaSaleXclMgmtDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
           
        try {
            // 1. DU lookup
            DEPFPASaleXclMgmt dEPFPASaleXclMgmt = (DEPFPASaleXclMgmt) lookupDataUnit(DEPFPASaleXclMgmt.class);

            // 2. LISTDM호출
            responseData = dEPFPASaleXclMgmt.dSFpaSaleXclMgmtDtl(reqDs, onlineCtx);
           
        } catch ( BizRuntimeException e ) {
           throw e; //시스템 오류가 발생하였습니다.
        }
   
        return responseData;
    }
  
}
