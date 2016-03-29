package dms.ep.epbimbase.biz;

import java.util.Map;

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
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [PU]가용재고관리</li>
 * <li>설  명 : <pre>가용재고관리</pre></li>
 * <li>작성일 : 2015-08-24 09:16:30</li>
 * <li>작성자 : 김상오 (myneti99)</li>
 * </ul>
 *
 * @author 김상오 (myneti99)
 */
public class PEPAvailInveMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PEPAvailInveMgmt(){
		super();
	}

    /**
	 * <pre>가용재고조회</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-08-24 09:16:30
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : HLD_DEALCO_ID [보유처]
	 *	- field : AFFIL_AGN [보유팀]
	 *	- field : SELLYN [판매여부]
	 *	- field : EQP_MDL_CD [단말기 모델코드]
	 *	- field : MFACT_CD [제조사]
	 *	- field : EQP_ST [단말기등급]
	 *	- field : EQP_SER_NO [단말기일련번호]
	 *	- field : BF_SER_NO [단말기 이전일련번호]
	 *	- field : CNSL_MGMT_NO [상담관리번호]
	 *	- field : BOX_NO [Box No]
	 *	- field : SALEFROMDTM [판매기간 시작]
	 *	- field : SALETODTM [판매기간 종료]
	 *	- field : SKN_ST [재고상태]
	 *	- field : ECO_FU_YN [에코폰4U 접수여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_AVAIL_INVE_LIST
	 *		- field : CHEKED [체크]
	 *		- field : PROD_CL [상품구분]
	 *		- field : MFACT_CD [제조사코드]
	 *		- field : EQP_MDL_CD [단말기 모델코드]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : EQP_COLOR_CD [단말기 색상코드]
	 *		- field : COLOR_NM [모델색상명]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : BF_SER_NO [단말기 이전일련번호]
	 *		- field : HLD_DEALCO_ID [보유처 ID]
	 *		- field : HLD_DEALCO_NM [보유처명]
	 *		- field : EQP_ST [단말기 상태등급]
	 *		- field : EQP_ST_DTAIL [단말기상태세부]
	 *		- field : SELLYN [판매여부]
	 *		- field : SALEPLC [매출처코드]
	 *		- field : SALEPLC_NM [매출처명]
	 *		- field : SALEPLC_UKEY [판매처]
	 *		- field : CONF_SELL_AMT [확정판매금액]
	 *		- field : CONF_SELL_DT [확정판매일자]
	 *		- field : HOLD_AMT [유보금액]
	 *		- field : INVE_AMT [재고금액]
	 *		- field : TOT_DIS_AMT [재고금액2]
	 *		- field : CNSL_MGMT_NO [상담 관리 번호]
	 *		- field : PRCHS_DT [매입일자]
	 *		- field : SKN_ST [재고상태]
	 *		- field : PROGR_ST [진행상태]
	 *		- field : BOX_NO [박스 번호]
	 *		- field : LAST_IN_OUT_DT [반품일자]
	 *		- field : IMEI [IMEI]
	 *		- field : CNSGN_SELL_YN [위탁 판매 여부]
	 *		- field : CNSGN_SELL_DT [위탁 판매 일자]
	 *		- field : CNSGN_SELL_CNCL_DT [위탁 판매 취소 일자]
	 *		- field : CNSGNPLC [위탁처코드]
	 *		- field : CNSGNPLC_NM [위탁처명]
	 *		- field : CNSL_DEALCO_CD [상담처코드]
	 *		- field : CNSL_DEALCO_NM [상담처명]
	 *		- field : AFFIL_AGN [대리점코드]
	 *		- field : AFFIL_AGN_NM [대리점명]
	 *		- field : AGN_ORG_CD [대리점UkeyCode]
	 *		- field : CNSL_DT [상담일자]
	 *		- field : IN_CONF_DT [입고확정일자]
	 *		- field : TLY_DT [검수일자]
	 *		- field : RMT_DT [송금일자]
	 *		- field : FEE_DEDC_PROC_DT [요금공제처리일자]
	 *		- field : BOX_PROC_CHRGR [박스 처리 담당자]
	 *		- field : BOX_PROC_DT [박스 처리 일자]
	 *		- field : SCRN_AFIMG_YN [화면잔상여부]
	 *		- field : EVALCNSLR_SUGG [평가원의견]
	 *		- field : SELL_GRADE [판매등급]
	 *		- field : HEADQ_NM [마케팅본부]
	 *		- field : MKT_TEAM_NM [마케팅팀명]
	 * </pre>
	 */
	/**
	 * <pre>가용재고조회</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-08-24 09:16:30
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : HLD_DEALCO_ID [보유처]
	 *	- field : AFFIL_AGN [보유팀]
	 *	- field : SELLYN [판매여부]
	 *	- field : EQP_MDL_CD [단말기 모델코드]
	 *	- field : MFACT_CD [제조사]
	 *	- field : EQP_ST [단말기등급]
	 *	- field : EQP_SER_NO [단말기일련번호]
	 *	- field : BF_SER_NO [단말기 이전일련번호]
	 *	- field : CNSL_MGMT_NO [상담관리번호]
	 *	- field : BOX_NO [Box No]
	 *	- field : SALEFROMDTM [판매기간 시작]
	 *	- field : SALETODTM [판매기간 종료]
	 *	- field : SKN_ST [재고상태]
	 *	- field : ECO_FU_YN [에코폰4U 접수여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_AVAIL_INVE_LIST
	 *		- field : CHEKED [체크]
	 *		- field : PROD_CL [상품구분]
	 *		- field : MFACT_CD [제조사코드]
	 *		- field : EQP_MDL_CD [단말기 모델코드]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : EQP_COLOR_CD [단말기 색상코드]
	 *		- field : COLOR_NM [모델색상명]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : BF_SER_NO [단말기 이전일련번호]
	 *		- field : HLD_DEALCO_ID [보유처 ID]
	 *		- field : HLD_DEALCO_NM [보유처명]
	 *		- field : EQP_ST [단말기 상태등급]
	 *		- field : EQP_ST_DTAIL [단말기상태세부]
	 *		- field : SELLYN [판매여부]
	 *		- field : SALEPLC [매출처코드]
	 *		- field : SALEPLC_NM [매출처명]
	 *		- field : SALEPLC_UKEY [판매처]
	 *		- field : CONF_SELL_AMT [확정판매금액]
	 *		- field : CONF_SELL_DT [확정판매일자]
	 *		- field : HOLD_AMT [유보금액]
	 *		- field : INVE_AMT [재고금액]
	 *		- field : TOT_DIS_AMT [재고금액2]
	 *		- field : SELL_AMT [판매가]
	 *		- field : CNSL_MGMT_NO [상담 관리 번호]
	 *		- field : PRCHS_DT [매입일자]
	 *		- field : SKN_ST [재고상태]
	 *		- field : PROGR_ST [진행상태]
	 *		- field : BOX_NO [박스 번호]
	 *		- field : LAST_IN_OUT_DT [반품일자]
	 *		- field : IMEI [IMEI]
	 *		- field : CNSGN_SELL_YN [위탁 판매 여부]
	 *		- field : CNSGN_SELL_DT [위탁 판매 일자]
	 *		- field : CNSGN_SELL_CNCL_DT [위탁 판매 취소 일자]
	 *		- field : CNSGNPLC [위탁처코드]
	 *		- field : CNSGNPLC_NM [위탁처명]
	 *		- field : CNSL_DEALCO_CD [상담처코드]
	 *		- field : CNSL_DEALCO_NM [상담처명]
	 *		- field : AFFIL_AGN [대리점코드]
	 *		- field : AFFIL_AGN_NM [대리점명]
	 *		- field : AGN_ORG_CD [대리점UkeyCode]
	 *		- field : CNSL_DT [상담일자]
	 *		- field : IN_CONF_DT [입고확정일자]
	 *		- field : TLY_DT [검수일자]
	 *		- field : RMT_DT [송금일자]
	 *		- field : FEE_DEDC_PROC_DT [요금공제처리일자]
	 *		- field : BOX_PROC_CHRGR [박스 처리 담당자]
	 *		- field : BOX_PROC_DT [박스 처리 일자]
	 *		- field : SCRN_AFIMG_YN [화면잔상여부]
	 *		- field : EVALCNSLR_SUGG [평가원의견]
	 *		- field : SELL_GRADE [판매등급]
	 *		- field : HEADQ_NM [마케팅본부]
	 *		- field : MKT_TEAM_NM [마케팅팀명]
	 * </pre>
	 */
	public IDataSet pInqAvailInveList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        try {
            //  FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSIMBase", "fInqAvailInveList", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        
        return responseData;
    }

    /**
	 * <pre>상품코드변경목록수정</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-08-24 09:16:30
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_PROD_CD_CHG_LIST
	 *		- field : BF_EQP_MDL_CD [이전모델코드]
	 *		- field : BF_EQP_SER_NO [이전단말기일련번호]
	 *		- field : EQP_COLOR_CD [단말기 색상코드]
	 *		- field : CNSL_MGMT_NO [상담관리번호]
	 *		- field : EQP_SER_NO [상품일련번호]
	 *		- field : AF_EQP_MDL_CD [이후모델코드]
	 *		- field : AF_EQP_SER_NO [이후단말기일련번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveCdChgLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSIMBase", "fSaveCdChgLst", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }
  
}
