package dms.ep.epbcsbase.biz;

import java.util.Map;

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


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [PU]재감정품질검사대상조회목록</li>
 * <li>설  명 : <pre>재감정품질검사대상조회목록</pre></li>
 * <li>작성일 : 2015-08-28 16:24:30</li>
 * <li>작성자 : 양재석 (jsyang)</li>
 * </ul>
 *
 * @author 양재석 (jsyang)
 */
public class PEPRJdgQltyJdgMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PEPRJdgQltyJdgMgmt(){
		super();
	}

    /**
	 * <pre>[PM]재감정품질검사대상조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-28 16:24:30
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : IN_FR_DT [조회검수시작일자]
	 *	- field : IN_TO_DT [조회검수종료일자]
	 *	- field : AFFIL_AGN [대리점코드]
	 *	- field : CNSL_DEALCO_CD [상담처코드]
	 *	- field : EQP_SER_NO [일련번호]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : DPSTR [예금주(고객명)]
	 *	- field : CNSL_MGMT_NO [접수관리번호]
	 *	- field : BOX_NO [BOX_NO]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : QLTY_JDG_OBJ_LIST
	 *		- field : PRCH_MGMT_NO [매입 관리 번호]
	 *		- field : CNSL_MGMT_NO [상담 관리 번호(접수 관리 번호)]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : TLY_DT [검수일자]
	 *		- field : CNSL_DT [상담일자]
	 *		- field : EQP_COLOR_CD [단말기색상코드]
	 *		- field : AFFIL_ORG_ID [소속 조직 ID]
	 *		- field : ORG_NM [조직명]
	 *		- field : AGN_ORG_CD [대리점 조직 코드]
	 *		- field : AFFIL_AGN_NM [대리점명]
	 *		- field : CNSL_DEALCO_NM [상담처명]
	 *		- field : CNSL_DEALCO_CD [상담처코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : PRCH_AMT [매입금액]
	 *		- field : BANK_CD [은행코드]
	 *		- field : ACCO_NO [계좌번호]
	 *		- field : DPSTR [예금주(고객명)]
	 *		- field : TEL_NO [연락처]
	 *		- field : CUST_IDEA [고객의사]
	 *		- field : AFFIL_AGN [소속대리점]
	 *		- field : PROGR_ST [진행상태]
	 *		- field : EQP_ST [단말기 상태(등급)]
	 *		- field : SKN_EQP_ST [SKN단말기상태]
	 *		- field : SKN_JDG_CL [SKN감정구분]
	 *		- field : SKN_JDG_AMT [SKN감정금액]
	 *		- field : SKN_SMPL_JDG_DAMT [SKN샘플강정차액]
	 *		- field : SKN_PROC_YN [SKN처리유무]
	 *		- field : SKN_PROC_DT [SKN처리일자]
	 *		- field : CHECK_USER_NM [검수자]
	 *		- field : SKN_DTL_SUGG [SKN상세의견]
	 *		- field : AGN_DDCT_YN [대리점차감여부]
	 *		- field : BOX_NO [BOX_NO]
	 *		- field : CLCT_DT [회수일자]
	 *		- field : DIS_YN [구성품확인여부]
	 *		- field : UBO_AMT [유보금액]
	 *		- field : TOT_AMT [총금액]
	 *		- field : CHECKED [CHECKED]
	 *		- field : ACCO_NO_ENPT [계좌번호_암호화]
	 *		- field : DPSTR_ENPT [예금주(고객명)_암호화]
	 *		- field : TEL_NO_ENPT [연락처_암호화]
	 *		- field : PRCH_CHG_DAMT_AMT [매입변경차액금액]
	 *		- field : IN_CONF_DT [입고확정일자]
	 *		- field : QULT_EXMN_CHK_PROC_CHRGR_NM [품질 검사 확인 처리 담당자]
	 *		- field : QULT_EXMN_CHK_CNCL_CHRGR_NM [품질 검사 확인 취소 처리 담당자]
	 *		- field : SCRB_MTHD [가입방법]
	 *	- record : QLTY_JDG_OBJ_DTL_LIST
	 *		- field : PRCH_MGMT_NO [매입관리번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : PROD_CL [상품 구분]
	 *		- field : IN_QTY [입고 수량]
	 *		- field : IN_AMT [입고 금액]
	 * </pre>
	 */
	public IDataSet pInqRJdgQltyJdgObjList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FU lookup
            //FPRReqpClctInRgst fPRReqpClctInRgst = (FPRReqpClctInRgst) lookupFunctionUnit(FPRReqpClctInRgst.class);
            // 2. FM 호출
            if(requestData != null && requestData.getField("DPSTR") != null && !"".equals(requestData.getField("DPSTR").toString())){
                requestData.putField("DPSTR_ENPT", HpcUtils.encodeByAES(requestData.getField("DPSTR")));
            }
            responseData = callSharedBizComponentByDirect("ep.EPSCSBase", "fInqRJdgQltyJdgObjList", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        return responseData;
    }

    /**
	 * <pre>[PM]재감정품질검사확인조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-28 16:24:30
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : IN_FR_DT [조회검수시작일자]
	 *	- field : IN_TO_DT [조회검수종료일자]
	 *	- field : AFFIL_AGN [대리점코드]
	 *	- field : CNSL_DEALCO_CD [상담처코드]
	 *	- field : EQP_SER_NO [일련번호]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : DPSTR [예금주(고객명)]
	 *	- field : CNSL_MGMT_NO [접수관리번호]
	 *	- field : BOX_NO [BOX_NO]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : QLTY_JDG_REG_LIST
	 *		- field : PRCH_MGMT_NO [매입 관리 번호]
	 *		- field : CNSL_MGMT_NO [상담 관리 번호(접수 관리 번호)]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : TLY_DT [검수일자]
	 *		- field : CNSL_DT [상담일자]
	 *		- field : EQP_COLOR_CD [단말기색상코드]
	 *		- field : AFFIL_ORG_ID [소속 조직 ID]
	 *		- field : ORG_NM [조직명]
	 *		- field : AGN_ORG_CD [대리점 조직 코드]
	 *		- field : AFFIL_AGN_NM [대리점명]
	 *		- field : CNSL_DEALCO_NM [상담처명]
	 *		- field : CNSL_DEALCO_CD [상담처코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : PRCH_AMT [매입금액]
	 *		- field : BANK_CD [은행코드]
	 *		- field : ACCO_NO [계좌번호]
	 *		- field : DPSTR [예금주(고객명)]
	 *		- field : TEL_NO [연락처]
	 *		- field : CUST_IDEA [고객의사]
	 *		- field : AFFIL_AGN [소속대리점]
	 *		- field : PROGR_ST [진행상태]
	 *		- field : EQP_ST [단말기 상태(등급)]
	 *		- field : SKN_EQP_ST [SKN단말기상태]
	 *		- field : SKN_JDG_CL [SKN감정구분]
	 *		- field : SKN_JDG_AMT [SKN감정금액]
	 *		- field : SKN_SMPL_JDG_DAMT [SKN샘플강정차액]
	 *		- field : SKN_PROC_YN [SKN처리유무]
	 *		- field : SKN_PROC_DT [SKN처리일자]
	 *		- field : CHECK_USER_NM [검수자]
	 *		- field : SKN_DTL_SUGG [SKN상세의견]
	 *		- field : AGN_DDCT_YN [대리점차감여부]
	 *		- field : BOX_NO [BOX_NO]
	 *		- field : CLCT_DT [회수일자]
	 *		- field : DIS_YN [구성품확인여부]
	 *		- field : UBO_AMT [유보금액]
	 *		- field : TOT_AMT [총금액]
	 *		- field : CHECKED [CHECKED]
	 *		- field : ACCO_NO_ENPT [계좌번호_암호화]
	 *		- field : DPSTR_ENPT [예금주(고객명)_암호화]
	 *		- field : TEL_NO_ENPT [연락처_암호화]
	 *		- field : PRCH_CHG_DAMT_AMT [매입변경차액금액]
	 *		- field : IN_CONF_DT [입고확정일자]
	 *		- field : QULT_EXMN_CHK_PROC_CHRGR_NM [품질 검사 확인 처리 담당자]
	 *		- field : QULT_EXMN_CHK_CNCL_CHRGR_NM [품질 검사 확인 취소 처리 담당자]
	 *		- field : SCRB_MTHD [가입방법]
	 *	- record : QLTY_JDG_REG_DTL_LIST
	 *		- field : PRCH_MGMT_NO [매입관리번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : PROD_CL [상품 구분]
	 *		- field : IN_QTY [입고 수량]
	 *		- field : IN_AMT [입고 금액]
	 * </pre>
	 */
	public IDataSet pInqRJdgQltyJdgRegList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FU lookup
            //FPRReqpClctInRgst fPRReqpClctInRgst = (FPRReqpClctInRgst) lookupFunctionUnit(FPRReqpClctInRgst.class);
            // 2. FM 호출
            if(requestData != null && requestData.getField("DPSTR") != null && !"".equals(requestData.getField("DPSTR").toString())){
                requestData.putField("DPSTR_ENPT", HpcUtils.encodeByAES(requestData.getField("DPSTR")));
            }
            responseData = callSharedBizComponentByDirect("ep.EPSCSBase", "fInqRJdgQltyJdgRegList", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>재감정품질검사대상검수확인수정</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-07 16:01:11
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : QLTY_JDG_OBJ_LIST
	 *		- field : PRCH_MGMT_NO [매입 관리 번호]
	 *		- field : CNSL_MGMT_NO [상담 관리 번호(접수 관리 번호)]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : TLY_DT [검수일자]
	 *		- field : CNSL_DT [상담일자]
	 *		- field : EQP_COLOR_CD [단말기색상코드]
	 *		- field : AFFIL_ORG_ID [소속 조직 ID]
	 *		- field : ORG_NM [조직명]
	 *		- field : AGN_ORG_CD [대리점 조직 코드]
	 *		- field : AFFIL_AGN_NM [대리점명]
	 *		- field : CNSL_DEALCO_NM [상담처명]
	 *		- field : CNSL_DEALCO_CD [상담처코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : PRCH_AMT [매입금액]
	 *		- field : BANK_CD [은행코드]
	 *		- field : ACCO_NO [계좌번호]
	 *		- field : DPSTR [예금주(고객명)]
	 *		- field : TEL_NO [연락처]
	 *		- field : CUST_IDEA [고객의사]
	 *		- field : AFFIL_AGN [소속대리점]
	 *		- field : PROGR_ST [진행상태]
	 *		- field : EQP_ST [단말기 상태(등급)]
	 *		- field : SKN_EQP_ST [SKN단말기상태]
	 *		- field : SKN_JDG_CL [SKN감정구분]
	 *		- field : SKN_JDG_AMT [SKN감정금액]
	 *		- field : SKN_SMPL_JDG_DAMT [SKN샘플강정차액]
	 *		- field : SKN_PROC_YN [SKN처리유무]
	 *		- field : SKN_PROC_DT [SKN처리일자]
	 *		- field : CHECK_USER_NM [검수자]
	 *		- field : SKN_DTL_SUGG [SKN상세의견]
	 *		- field : AGN_DDCT_YN [대리점차감여부]
	 *		- field : BOX_NO [BOX_NO]
	 *		- field : CLCT_DT [회수일자]
	 *		- field : DIS_YN [구성품확인여부]
	 *		- field : UBO_AMT [유보금액]
	 *		- field : TOT_AMT [총금액]
	 *		- field : CHECKED [CHECKED]
	 *	- record : QLTY_JDG_OBJ_DTL_LIST
	 *		- field : PRCH_MGMT_NO [매입관리번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : PROD_CL [상품 구분]
	 *		- field : IN_QTY [입고 수량]
	 *		- field : IN_AMT [입고 금액]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pURJdgQltyJdgRegUpd(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSCSBase", "fURJdgQltyJdgRegUpd", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        // 2. 결과값 리턴
        responseData.setOkResultMessage("DMS00000", null); //정상 처리되었습니다.
    
        return responseData;
    }
  
}
