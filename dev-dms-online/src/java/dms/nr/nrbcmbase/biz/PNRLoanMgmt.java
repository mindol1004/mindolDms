package dms.nr.nrbcmbase.biz;

import fwk.common.CommonArea;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [PU]SKN여신관리</li>
 * <li>설  명 : <pre>[PU]SKN여신관리</pre></li>
 * <li>작성일 : 2015-07-22 18:20:28</li>
 * <li>작성자 : 박홍서 (uni9401)</li>
 * </ul>
 *
 * @author 박홍서 (uni9401)
 */
public class PNRLoanMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNRLoanMgmt(){
		super();
	}

    /**
	 * <pre>SKN여신 리스트 조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-22 18:20:28
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : AGN_CD [대리점코드]
	 *	- field : PAY_FR_YM [기준시작년월]
	 *	- field : PAY_TO_YM [기준종료년월]
	 *	- field : PAY_YM_TS [지급차수]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_SKN_PG
	 *		- field : AGN_CD [대리점코드]
	 *		- field : PAY_YM [지급년월]
	 *		- field : PAY_YM_TS [지급년월차수]
	 *		- field : AGN_NM [대리점명]
	 *		- field : AGN_CRD_AMT [대리점여신금액]
	 *		- field : AGN_SALE_AMT [대리점판매금액]
	 *		- field : CRD_SALE_DAMT [여신판매차액]
	 *		- field : DEALCO_CD [대리점코드-공통팝용]
	 *		- field : PRCH_CNT [판매건수]
	 *		- field : SPLY_PRC [공급가액]
	 *		- field : SURTAX_AMT [부가세]
	 *		- field : CNT [환수건수]
	 *		- field : SPLY [환수가액]
	 *		- field : SURTAX [환수부가세]
	 *		- field : BANK [은행]
	 *		- field : COIL [계좌]
	 * </pre>
	 */
	public IDataSet pInqSKNLoanLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        try {
            // 1. FM 호출 ("RS_SKN_PG")
            responseData = callSharedBizComponentByDirect("nr.NRSCMBase", "fInqSKNLoanLst", requestData, onlineCtx);
            // 2. 결과값 리턴
            responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
        return responseData;
    }

    /**
	 * <pre>SKN여신 리스트 모두 조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-22 18:20:28
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : AGN_CD [대리점코드]
	 *	- field : PAY_FR_YM [기준시작년월]
	 *	- field : PAY_TO_YM [기준종료년월]
	 *	- field : PAY_YM_TS [지급차수]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_SKN_PG
	 *		- field : AGN_CD [대리점코드]
	 *		- field : PAY_YM [지급년월]
	 *		- field : PAY_YM_TS [지급년월차수]
	 *		- field : AGN_NM [대리점명]
	 *		- field : AGN_CRD_AMT [대리점여신금액]
	 *		- field : AGN_SALE_AMT [대리점판매금액]
	 *		- field : CRD_SALE_DAMT [여신판매차액]
	 *		- field : FS_REG_USER_ID [최초등록자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 * </pre>
	 */
	public IDataSet pInqAllSKNLoanLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        try {
            // 1. FM 호출 ("RS_SKN_LST")
            responseData = callSharedBizComponentByDirect("nr.NRSCMBase", "fInqAllSKNLoanLst", requestData, onlineCtx);
            // 2. 결과값 리턴
            responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
        return responseData;
    }

    /**
	 * <pre>SKN여신 상세 모두 조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-22 18:20:28
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : AGN_CD [대리점코드]
	 *	- field : CNTRT_YM_TS [지급차수]
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : NR_CNTRTR_NM [가입자명]
	 *	- field : CNTRT_FR_DTM [계약조회시작일자]
	 *	- field : CNTRT_TO_DTM [계약조회종료일자]
	 *	- field : DEALCO_BLICENS_NO [사업자번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_SKN_DTL_PG
	 *		- field : AGN_CD [판매대리점코드]
	 *		- field : AGN_NM [판매대리점명]
	 *		- field : AGN_SEQ [판매대리점SEQ]
	 *		- field : CNTRT_YM [기준년월]
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : CNTRT_YM_TS [지급차수]
	 *		- field : NR_CNTRTR_NM [가입자명]
	 *		- field : RENTAL_SER_NUM [랜탈일련번호]
	 *		- field : NR_CNTRT_DT [계약일자]
	 *		- field : CNTRT_MTH [계약개월]
	 *		- field : OUT_PRC [출고가]
	 *		- field : FS_REG_USER_ID [최초등록자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 *		- field : SUM_PRC [합계금액]
	 *		- field : SPLY_PRC [공급가액]
	 *		- field : DEALCO_BLICENS_NO [사업자번호]
	 * </pre>
	 */
	public IDataSet pInqAllSKNLoanDtlLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        try {
            // 1. FM 호출 ("RS_SKN_DTL_LST")
            responseData = callSharedBizComponentByDirect("nr.NRSCMBase", "fInqAllSKNLoanDtlLst", requestData, onlineCtx);
           
            // 2. 결과값 리턴
            responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
        return responseData;
    }

    /**
	 * <pre>SKN여신 상세 리스트 조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-22 18:20:28
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : AGN_CD [대리점코드]
	 *	- field : CNTRT_YM_TS [지급차수]
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : NR_CNTRTR_NM [가입자명]
	 *	- field : DEALCO_BLICENS_NO [사업자번호]
	 *	- field : CNTRT_FR_DTM [계약조회시작일자]
	 *	- field : CNTRT_TO_DTM [계약조회종료일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_SKN_DTL_PG
	 *		- field : AGN_CD [판매대리점코드]
	 *		- field : AGN_NM [판매대리점명]
	 *		- field : AGN_SEQ [판매대리점SEQ]
	 *		- field : CNTRT_YM [기준년월]
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : CNTRT_YM_TS [지급차수]
	 *		- field : NR_CNTRTR_NM [가입자명]
	 *		- field : RENTAL_SER_NUM [랜탈일련번호]
	 *		- field : NR_CNTRT_DT [계약일자]
	 *		- field : CNTRT_MTH [계약개월]
	 *		- field : OUT_PRC [출고가]
	 *		- field : FS_REG_USER_ID [최초등록자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 *		- field : SUM_PRC [합계금액]
	 *		- field : SPLY_PRC [공급가액]
	 *		- field : DEALCO_BLICENS_NO [사업자번호]
	 * </pre>
	 */
	public IDataSet pInqSKNLoanDtlLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        try {
            // 1. FM 호출 ("RS_SKN_DTL_PG")
            responseData = callSharedBizComponentByDirect("nr.NRSCMBase", "fInqSKNLoanDtlLst", requestData, onlineCtx);
            // 2. 결과값 리턴
            responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
        return responseData;
    }

    /**
	 * <pre>신청서항목체크 리스트 조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-22 18:20:28
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : APPLF_TYP [신청서유형]
	 *	- field : AGN_CD [대리점코드]
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : RENTAL_CNTRT_STA_DT [계약시작일]
	 *	- field : RENTAL_CNTRT_END_DT [계약종료일]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_NR_APPLF_PG
	 *		- field : AGN_CD [대리점코드]
	 *		- field : AGN_NM [대리점명]
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : CNTRT_NO [계약번호]
	 *		- field : RENTAL_SER_NUM [랜탈일련번호]
	 *		- field : NR_CNTRTR_NM [가입자명]
	 *		- field : NR_CNTRT_BIZ_NUM [사업자번호]
	 *		- field : APPLF_ST [신청서상태]
	 *		- field : APPLF_ITM [신청서항목]
	 *		- field : APPLF_RMK [보완내용]
	 * </pre>
	 */
	public IDataSet pInqApplfChkLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        try {
            // 1. FM 호출 ("RS_NR_APPLF_PG")
            responseData = callSharedBizComponentByDirect("nr.NRSCMBase", "fInqApplfChkLst", requestData, onlineCtx);
            // 2. 결과값 리턴
            responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
        return responseData;
    }

    /**
	 * <pre>신청서항목체크 리스트 모두 조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-22 18:20:28
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : APPLF_TYP [신청서유형]
	 *	- field : AGN_CD [대리점코드]
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_NR_APPLF
	 *		- field : AGN_CD [대리점코드]
	 *		- field : AGN_NM [대리점명]
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : RENTAL_SER_NUM [랜탈일련번호]
	 *		- field : NR_CNTRTR_NM [가입자명]
	 *		- field : NR_CNTRT_BIZ_NUM [사업자번호]
	 *		- field : APPLF_TYP [신청서유형]
	 *		- field : APPLF_IMG_URL_YN [이미지누락]
	 *		- field : APPLF_IMG_ERR_YN [이미지오인입]
	 *		- field : ITM_01_ERR_YN [보완항목1]
	 *		- field : ITM_02_ERR_YN [보완항목2]
	 *		- field : ITM_03_ERR_YN [보완항목3]
	 *		- field : ITM_04_ERR_YN [보완항목4]
	 *		- field : ITM_05_ERR_YN [보완항목5]
	 *		- field : ITM_06_ERR_YN [보완항목6]
	 *		- field : ITM_07_ERR_YN [보완항목7]
	 *		- field : ITM_08_ERR_YN [보완항목8]
	 *		- field : ITM_09_ERR_YN [보완항목9]
	 *		- field : ITM_10_ERR_YN [보완항목10]
	 *		- field : APPLF_RMK [보완내용]
	 *		- field : FS_REG_USER_ID [최초등록자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 * </pre>
	 */
	public IDataSet pInqAllApplfChkLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        try {
            // 1. FM 호출 ("RS_NR_APPLF")
            responseData = callSharedBizComponentByDirect("nr.NRSCMBase", "fInqAllApplfChkLst", requestData, onlineCtx);
            // 2. 결과값 리턴
            responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
        return responseData;
    }

    /**
	 * <pre>대리점웹사이트에서 조회시 해당 pm 호출</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-22 18:20:28
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pInqSKNLoanLstWeb(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx); 
        try {
            //if ( StringUtils.equals(ca.getUserClCd(), "10")) { requestData.putField("AGN_CD", ca.getDealCd()); }
            //requestData.putField("AGN_CD", ca.getDealCd()); 
            // 1. FM 호출 ("RS_SKN_PG")
            responseData = callSharedBizComponentByDirect("nr.NRSCMBase", "fInqSKNLoanLst", requestData, onlineCtx);
            
            // 2. 결과값 리턴
            responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
        return responseData;
    }

    /**
	 * <pre>대리점Web 호출용 SKN여신 상세리스트 조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-09-10 15:10:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pInqSKNLoanDtlLstWeb(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx); 
        try {            
            //requestData.putField("AGN_CD", ca.getDealCd()); 
            // 1. FM 호출 ("RS_SKN_DTL_PG")
            responseData = callSharedBizComponentByDirect("nr.NRSCMBase", "fInqSKNLoanDtlLst", requestData, onlineCtx);
            // 2. 결과값 리턴
            responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
        return responseData;
    }

    /**
	 * <pre>대리점Web호출용 신청서항목체크리스트조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-09-10 16:27:33
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pInqApplfChkLstWeb(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx); 
        try {          
            //requestData.putField("AGN_CD", ca.getDealCd());  
            // 1. FM 호출 ("RS_NR_APPLF_PG")
            responseData = callSharedBizComponentByDirect("nr.NRSCMBase", "fInqApplfChkLst", requestData, onlineCtx);
            // 2. 결과값 리턴
            responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
        return responseData;
    }

    /**
	 * <pre>신청서항목체크 리스트 조회상세</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-22 18:20:28
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CNTRT_NO [계약번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_NR_APPLF_DTL
	 *		- field : CNTRT_NO [계약번호]
	 *		- field : AA [필드2]
	 *		- field : AB [필드3]
	 *		- field : AC [필드4]
	 *		- field : AD [필드5]
	 *		- field : BA [필드6]
	 *		- field : BB [필드7]
	 *		- field : BC [필드8]
	 *		- field : BD [필드9]
	 *		- field : CA [필드10]
	 *		- field : CB [필드11]
	 *		- field : CC [필드12]
	 *		- field : CD [필드13]
	 *		- field : DMS253_01 [필드14]
	 *		- field : DMS253_02 [필드15]
	 *		- field : DMS253_03 [필드16]
	 *		- field : DMS253_04 [필드17]
	 *		- field : DMS253_05 [필드18]
	 *		- field : DMS254_01 [필드19]
	 *		- field : DMS254_02 [필드20]
	 *		- field : DMS254_03 [필드21]
	 *		- field : DMS254_04 [필드22]
	 *		- field : AC_IMGYN [필드23]
	 *		- field : AD_IMGYN [필드24]
	 *		- field : DMS255_01 [필드25]
	 *		- field : DMS255_02 [필드26]
	 *		- field : DMS255_03 [필드27]
	 *		- field : DMS255_04 [필드28]
	 *		- field : DMS255_05 [필드29]
	 *		- field : DMS256_01 [필드30]
	 *		- field : DMS256_02 [필드31]
	 *		- field : DMS256_03 [필드32]
	 *		- field : DMS256_04 [필드33]
	 *		- field : BC_IMGYN [필드34]
	 *		- field : BD_IMGYN [필드35]
	 *		- field : CA_IMGYN [필드36]
	 *		- field : CB_IMGYN [필드37]
	 *		- field : CC_IMGYN [필드38]
	 *		- field : CD_IMGYN [필드39]
	 *		- field : APPLF_RMK [필드40]
	 *		- field : APPLF_SUBMIT_DTM [신청서제출일]
	 * </pre>
	 */
    public IDataSet pInqApplfChkLstDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        try {
            // 1. FM 호출 ("RS_NR_APPLF_PG")
            responseData = callSharedBizComponentByDirect("nr.NRSCMBase", "fInqApplfChkLstDtl", requestData, onlineCtx);
            // 2. 결과값 리턴
            responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
        return responseData;
    }
  
}