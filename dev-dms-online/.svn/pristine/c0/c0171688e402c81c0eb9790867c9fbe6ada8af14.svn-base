package dms.ep.epbcsbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [PU]중고단말기매입매출현황관리</li>
 * <li>설  명 : <pre>중고단말기매입매출현황관리 PU</pre></li>
 * <li>작성일 : 2015-09-01 12:41:01</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class PEPEqpPrslMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PEPEqpPrslMgmt(){
		super();
	}

    /**
	 * <pre>중고단말기매입매출현황조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-01 12:41:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : DT_GB [일자구분]
	 *	- field : PRSL_STA_DT [시작일]
	 *	- field : PRSL_END_DT [종료일]
	 *	- field : AFFIL_AGN [대리점코드]
	 *	- field : CNSL_DEALCO_CD [상담처코드]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : EQP_SER_NO [일련번호]
	 *	- field : DPSTR [고객명]
	 *	- field : CNSL_MGMT_NO [접수관리번호]
	 *	- field : SRH_GB [구분]
	 *	- field : PRE_DC_CL [할인여부]
	 *	- field : ECO_YN [에코폰4U접수여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_PRSL_LIST
	 *		- field : PROGR_ST [진행상태]
	 *		- field : CNSL_MGMT_NO [접수관리번호]
	 *		- field : CNSL_DT [접수일자]
	 *		- field : IN_CONF_DT [입고일자]
	 *		- field : PROD_CHRTIC_1 [단말그룹]
	 *		- field : MFACT_CD [제조사]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : EQP_COLOR_CD [색상코드]
	 *		- field : HQ_NM [마케팅본부]
	 *		- field : MARKET_CD [마케팅팀코드]
	 *		- field : MARKET_NM [마케팅팀명]
	 *		- field : ORG_NM [조직명]
	 *		- field : AGN_ORG_CD [U.Key 코드]
	 *		- field : UKEY_SUB_CD [매장코드]
	 *		- field : AFFIL_AGN_NM [팀]
	 *		- field : CNSL_DEALCO_NM [상담처]
	 *		- field : DPSTR [고객명]
	 *		- field : DPSTR_ENPT [고객명(암호화)]
	 *		- field : ONING_DT [개통일자]
	 *		- field : USE_MONTH [사용월]
	 *		- field : BATT [BATT]
	 *		- field : BATT_CASE [BATT_CASE]
	 *		- field : GENDAR [GENDAR]
	 *		- field : CHARGER [CHARGER]
	 *		- field : EARPHONE [EARPHONE]
	 *		- field : EQP_ST [등급]
	 *		- field : EQP_ST_AMT [등급산정가]
	 *		- field : DDCT_AMT [차감금액]
	 *		- field : ADJ_AMT [조정금액]
	 *		- field : PRCH_AMT [매입금액]
	 *		- field : PRCHS_DT [매입일자]
	 *		- field : CUST_IDEA [고객의사]
	 *		- field : FEE_DEDC_YN [요금공제여부]
	 *		- field : AMT_RMT_YN [금액송금여부]
	 *		- field : RMT_DT [입금처리일자]
	 *		- field : FEE_DEDC_PROC_YN [요금공제처리여부]
	 *		- field : FEE_DEDC_PROC_DT [요금공제처리일자]
	 *		- field : FORCING_CH_PROC_YN [강제매입처리여부]
	 *		- field : FORCING_PRCH_PROC_DT [강제매입처리일자]
	 *		- field : POLY_OBJ_PROC_YN [정책대상처리여부]
	 *		- field : POLY_PROC_DT [정책처리일자]
	 *		- field : CHECK_USER_NM [검수자]
	 *		- field : TLY_DT [검수일자]
	 *		- field : CUST_USER_NM [확인자]
	 *		- field : CUST_CHK_DT [고객확인일자]
	 *		- field : COMC [통신방식]
	 *		- field : BOX_NO [BOX NO]
	 *		- field : EQP_ST_DTAIL [등급(SUB)]
	 *		- field : PRE_DC_CL [선할인구분]
	 *		- field : UBO_AMT [유보금액]
	 *		- field : TOT_AMT [총금액]
	 *		- field : IMEI [IMEI]
	 *		- field : CUST_GRP [단체명]
	 *		- field : IN_PROC_CHRGR_NM [입고처리자]
	 *		- field : BOX_PROC_CHRGR_NM [박스처리자]
	 *		- field : BOX_PROC_DT [박스처리일]
	 *		- field : BOX_TIME [박스처리시간]
	 *		- field : SCRN_AFIMG_YN [잔상여부]
	 *		- field : EVALCNSLR_SUGG [검수의견]
	 *		- field : ASIANA_CARD_NO [아시아나카드번호]
	 *		- field : SCRB_MTHD [가입방법]
	 *		- field : CMCT_CO [통신사]
	 *		- field : SELL_GRADE [판매등급]
	 *		- field : PRCH_CHG_DAMT_AMT [매입변경차액금액]
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
	 *		- field : CAMR_WINDO_ST [카메라윈도우손상]
	 *		- field : EDGE_ST [테두리및몸체]
	 *		- field : BACK_ST [매입불가]
	 *		- field : BACK_CL [매입불가여부]
	 *		- field : EQP_ST_RMK [대표오감정사유]
	 *		- field : CLCT_DT [회수일자]
	 * </pre>
	 */
	public IDataSet pInqEqpPrslList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSCSBase", "fInqEqpPrslList", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }
  
}
