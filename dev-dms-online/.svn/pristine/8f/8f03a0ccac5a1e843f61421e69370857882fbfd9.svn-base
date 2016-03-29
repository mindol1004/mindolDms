package dms.ep.epbcsbase.biz;

import java.util.Map;

import org.apache.commons.logging.Log;

import fwk.common.CommonArea;
import fwk.utils.HpcUtils;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.log.LogManager;
import nexcore.framework.core.util.StringUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [PU]재감정재고관리</li>
 * <li>설  명 : <pre>[PU]재감정재고관리</pre></li>
 * <li>작성일 : 2015-08-19 13:29:43</li>
 * <li>작성자 : 김윤환 (kyh0904)</li>
 * </ul>
 *
 * @author 김윤환 (kyh0904)
 */
public class PEPRJdgInveMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PEPRJdgInveMgmt(){
		super();
	}

	/**
	 * <pre>[PM]재감정현황조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 13:29:43
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : DT_GB [일자구분]
	 *	- field : FROM_DT [시작일자]
	 *	- field : TO_DT [종료일자]
	 *	- field : AFFIL_AGN_NM [대리점명]
	 *	- field : AFFIL_AGN [대리점코드]
	 *	- field : CNSL_DEALCO_NM [상담처코드]
	 *	- field : CNSL_DEALCO_CD [상담처명]
	 *	- field : EQP_MDL_NM [단말기 모델코드]
	 *	- field : EQP_MDL_CD [단말기 모델명]
	 *	- field : EQP_SER_NO [단말기 일련번호]
	 *	- field : DPSTR [고객명]
	 *	- field : CNSL_MGMT_NO [상담관리번호]
	 *	- field : SRH_GB [찾기구분]
	 *	- field : PRE_DC_CL [선할인여부]
	 *	- field : SCRB_MTHD [가입방법]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_JDG_PRST_LST
	 *		- field : ROWNO [번호]
	 *		- field : PROGR_ST [진행상태]
	 *		- field : CNSL_DT [상담일자]
	 *		- field : IN_CONF_DT [입고확정일자]
	 *		- field : CLCT_DT [회수일자]
	 *		- field : PROD_CHRTIC_1 [상품특성]
	 *		- field : MFACT_CD [제조사 코드]
	 *		- field : EQP_MDL_NM [단말기 모델명]
	 *		- field : EQP_MDL_CD [단말기 모델코드]
	 *		- field : EQP_SER_NO [단말기 일련번호]
	 *		- field : AFFIL_ORG_ID [소속조직코드(조직)]
	 *		- field : AFFIL_ORG_NM [소속조직명(조직)]
	 *		- field : AGN_ORG_CD [대리점 조직 코드]
	 *		- field : AFFIL_AGN [소속 대리점코드(팀코드)]
	 *		- field : AFFIL_AGN_NM [소속 대리점명(팀명)]
	 *		- field : CNSL_DEALCO_CD [상담 거래처코드(상담처)]
	 *		- field : CNSL_DEALCO_NM [상담 거래처명(상담처)]
	 *		- field : DPSTR [예금주]
	 *		- field : DPSTR_ENPT [예금주 암호화]
	 *		- field : ONING_DT [개통일자]
	 *		- field : SKN_EQP_ST [SKN 단말기상태]
	 *		- field : EQP_PRC [단말기 가격]
	 *		- field : U_STRD_INCEN [UKEY 기준 인센티브]
	 *		- field : STRD_INCEN_SCOP_AMT [기준 인센티브 범위금액]
	 *		- field : SKN_JDG_AMT [SKN 상담금액]
	 *		- field : EQP_ST [단말기상태]
	 *		- field : EQP_ST_DTAIL [단말기 상태 세부]
	 *		- field : SALE_EQP_ST [판매등급]
	 *		- field : ADJ_AMT [조정금액]
	 *		- field : PRCH_AMT [매입금액]
	 *		- field : STRD_INCEN [기준 인센티브]
	 *		- field : DEC_INCV_AMT [확정인센티브]
	 *		- field : SKN_SMPL_JDG_DAMT [SKN 감정 차액]
	 *		- field : TOT_AMT [총금액]
	 *		- field : SKN_JDG_CL [SKN 감정구분]
	 *		- field : SKN_JDG_CL_SUB [SKN 감정구분 서브]
	 *		- field : EVALCNSLR_SUGG [평가원의견]
	 *		- field : INPT [검수자]
	 *		- field : TLY_DT [검수일자]
	 *		- field : SKN_PROC_YN [SKN 처리여부]
	 *		- field : SKN_PROC_DT [SKN 처리일자]
	 *		- field : CNSL_MGMT_NO [상담 관리 번호]
	 *		- field : OLDEQ_PAY_DT_1 [송금일자]
	 *		- field : OLDEQ_PAY_DT_2 [요금공제일자]
	 *		- field : BOX_NO [박스 번호]
	 *		- field : IMEI [IMEI]
	 *		- field : PRE_DC_CL [선할인구분]
	 *		- field : IN_USER_NM [입고처리담당자]
	 *		- field : BOX_PROC_CHRGR [박스처리담당자]
	 *		- field : BOX_DT [박스처리일자]
	 *		- field : SCRN_AFIMG_YN [화면잔상여부]
	 *		- field : CMCT_CO [통신사]
	 *		- field : SCRB_MTHD [가입방법]
	 *		- field : HEADQ_NM [본부 명]
	 *		- field : MKT_TEAM_CD [마케팅 팀 코드]
	 *		- field : MKT_TEAM_NM [마케팅 팀 명]
	 *		- field : PRCH_CHG_DAMT_AMT [매입 변경 차액 금액]
	 *		- field : BOX_RMK [박스코멘트]
	 *		- field : PWR_ST [전원불량]
	 *		- field : RECG_ST [충전불량]
	 *		- field : USIM_ST [USIM불량]
	 *		- field : SBELL_ST [벨소리불량]
	 *		- field : VIBR_ST [진동불량]
	 *		- field : SPK_ST [스피커불량]
	 *		- field : ILRM_SENSOR_ST [조도센서불량]
	 *		- field : GRCP_ST [자이로센서불량]
	 *		- field : APRC_SENSOR_ST [근접센서불량]
	 *		- field : CAMR_ST [카메라불량]
	 *		- field : BATER_ST [B/T불량]
	 *		- field : AFIMG_ST [잔상불량]
	 *		- field : TOUCH_ST [터치불량]
	 *		- field : WIFI_ST [WIFI불량]
	 *		- field : STAIN_ST [얼룩]
	 *		- field : BRUI_ST [멍]
	 *		- field : SQUS_ST [눌림]
	 *		- field : LQFD_INFLO_ST [액상유입]
	 *		- field : LED_ST [LED파손]
	 *		- field : BUTN_ST [버튼불량]
	 *		- field : BOTM_TOUCH_ST [하단터치메뉴]
	 *		- field : BODY_ST [몸체미세휨]
	 *		- field : DMB_ST [DMB안테나손상]
	 *		- field : LCGLS_ST [액정유리]
	 *		- field : CHIP_ST [이나감]
	 *		- field : FGSC_ST [지문인식]
	 *		- field : CAMR_WINDO_ST [카메리윈도우손상]
	 *		- field : EDGE_ST [테두리및몸체]
	 *		- field : EQP_ST_RMK [대표오감정사유]
	 *		- field : UKEY_SUB_CD [매장코드]
	 *		- field : EQP_COLOR_CD [단말기색상코드]
	 *		- field : AGN_DDCT_YN [대리점차감여부]
	 * </pre>
	 */
	public IDataSet pInqRJdgPrstList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
        try {
            // 1. FM 호출
           requestData.putField("DPSTR_ENPT", HpcUtils.encodeByAES( requestData.getField("DPSTR") ));
           responseData = callSharedBizComponentByDirect("ep.EPSCSBase", "fInqRJdgPrstList", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        // 2. 결과값 리턴
        responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
    
        return responseData;
	}

    /**
	 * <pre>[PM]재감정재고취소목록조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 13:29:43
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CNSL_MGMT_NO [상담관리번호]
	 *	- field : BOX_NO [박스번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_RJDG_INVE_CNCL_LIST
	 *		- field : ROWNO [순번]
	 *		- field : CNSL_MGMT_NO [상담관리번호]
	 *		- field : EQP_SER_NO [단말기 일련 번호]
	 *		- field : EQP_MDL_CD [단말기 모델 코드]
	 *		- field : EQP_MDL_NM [단말기 모델 명]
	 *		- field : EQP_COLOR_CD [단말기 색상 코드]
	 *		- field : SKN_CL [SKN 구분]
	 *		- field : PROGR_ST [진행상태]
	 *		- field : SKN_PROC_DT [SKN 처리 일자]
	 *		- field : G_EQP_SER_NO [G_단말기일련번호]
	 * </pre>
	 */
	public IDataSet pSRJdgInveCnclLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            responseData = callSharedBizComponentByDirect("ep.EPSCSBase", "fSRJdgInveCnclLst", requestData, onlineCtx);                                                                               
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        // 2. 결과값 리턴        
        responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다. 
    
        return responseData;
    }

    /**
	 * <pre>[PM]재감정재고취소저장</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 13:29:43
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_RJDG_INVE_CNCL_LIST
	 *		- field : ROWNO [순번]
	 *		- field : CNSL_MGMT_NO [상담관리번호]
	 *		- field : EQP_SER_NO [단말기 일련 번호]
	 *		- field : EQP_MDL_CD [단말기 모델 코드]
	 *		- field : EQP_MDL_NM [단말기 모델 명]
	 *		- field : EQP_COLOR_CD [단말기 색상 코드]
	 *		- field : SKN_CL [SKN 구분]
	 *		- field : PROGR_ST [진행상태]
	 *		- field : SKN_PROC_DT [SKN 처리 일자]
	 *		- field : G_EQP_SER_NO [G_단말기일련번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveJdgInveCncl(IDataSet requestData, IOnlineContext onlineCtx) {
	    Log logger = LogManager.getFwkLog();
        IDataSet responseData = new DataSet();
        IRecordSet rs = requestData.getRecordSet("RS_RJDG_INVE_CNCL_LIST");                
        CommonArea ca = getCommonArea(onlineCtx);  
        IRecord rec = null;
        IDataSet rowDs = new DataSet();
        
        try {
            for(int i = 0 ; i < rs.getRecordCount() ; i++){
                rec = rs.getRecord(i);                            
                rowDs.putFieldMap(rec);
                rowDs.putField("USER_ID", ca.getUserNo());
                if (logger.isDebugEnabled()) {                
                    logger.debug("***** rowDs =>> " +rowDs.toString()+"\n");                    
                }                
                //callSharedBizComponentByDirect("ep.EPSCSBase", "fDTbEpDisDis", rowDs, onlineCtx);   // 재고삭제
                //callSharedBizComponentByDirect("ep.EPSCSBase", "fDTbEpDisProdDis", rowDs, onlineCtx);   // 상품재고삭제
                //callSharedBizComponentByDirect("ep.EPSCSBase", "fDTbEpDisDisAmt", rowDs, onlineCtx);    // 재고금액삭제
                callSharedBizComponentByDirect("ep.EPSCSBase", "fDTbEpDisCnclProc", rowDs, onlineCtx);   // 재고취소처리
                callSharedBizComponentByDirect("ep.EPSCSBase", "fUTbEpCstPrchsProgrSt", rowDs, onlineCtx);  // 매입관리진행상태수정                                                                
            }
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        
        
        
        return responseData;
    }
  
}
