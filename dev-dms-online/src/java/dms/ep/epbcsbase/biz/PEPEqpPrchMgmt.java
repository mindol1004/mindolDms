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
 * <li>단위업무명: [PU]단말기매입관리</li>
 * <li>설  명 : <pre>[PU]단말기매입관리</pre></li>
 * <li>작성일 : 2015-08-24 11:12:36</li>
 * <li>작성자 : 양재석 (jsyang)</li>
 * </ul>
 *
 * @author 양재석 (jsyang)
 */
public class PEPEqpPrchMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PEPEqpPrchMgmt(){
		super();
	}

    /**
	 * <pre>단말기매입대상목록조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-24 11:12:36
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
	 *	- field : PROGR_ST [진행상태]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : PRCH_OBJ_LIST
	 *		- field : CNSL_MGMT_NO [상담 관리 번호(접수 관리 번호)]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : TLY_DT [검수일자]
	 *		- field : EQP_COLOR_CD [모델색상코드]
	 *		- field : AFFIL_ORG_ID [소속 조직 ID]
	 *		- field : ORG_NM [조직명]
	 *		- field : AGN_ORG_CD [대리점 조직 코드]
	 *		- field : AFFIL_AGN_NM [대리점명]
	 *		- field : DEALCO_NM [상담처명]
	 *		- field : CNSL_DEALCO_CD [상담처거래처코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : PRCH_AMT [매입금액]
	 *		- field : BANK_CD [은행코드]
	 *		- field : ACCO_NO [계좌번호]
	 *		- field : DPSTR [예금주(고객명)]
	 *		- field : TEL_NO [연락처]
	 *		- field : EQP_ST [단말기 상태(등급)]
	 *		- field : CUST_IDEA [고객 의사]
	 *		- field : CUST_IDEA_T [고객 의사_T]
	 *		- field : PRC_CHECK [PRC_CHECK]
	 *		- field : AFFIL_AGN [소속 대리점]
	 *		- field : PROGR_ST [진행 상태]
	 *		- field : PRCH_MGMT_NO [매입 관리 번호]
	 *		- field : ACCO_NO_ENPT [계좌번호_암호화]
	 *		- field : DPSTR_ENPT [예금주(고객명)_암호화]
	 *		- field : TEL_NO_ENPT [연락처_암호화]
	 *		- field : SCRB_MTHD [가입방법]
	 * </pre>
	 */
	public IDataSet pInqEqpPrchObjList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. FU lookup
            //FPRReqpClctInRgst fPRReqpClctInRgst = (FPRReqpClctInRgst) lookupFunctionUnit(FPRReqpClctInRgst.class);
            // 2. FM 호출
            if(requestData != null && requestData.getField("DPSTR") != null && !"".equals(requestData.getField("DPSTR").toString())){
                requestData.putField("DPSTR_ENPT", HpcUtils.encodeByAES(requestData.getField("DPSTR")));
            }
            responseData = callSharedBizComponentByDirect("ep.EPSCSBase", "fInqEqpPrchObjList", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        return responseData;
    }

    /**
	 * <pre>[PM]단말기매입확인목록조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-24 11:12:36
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : IN_FR_DT [조회검수시작일자]
	 *	- field : IN_TO_DT [조회검수종료일자]
	 *	- field : AFFIL_AGN [대리점코드]
	 *	- field : CNSL_DEALCO_CD [상담처코드]
	 *	- field : EQP_SER_NO [상담 관리 일련 번호]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : DPSTR [예금주(고객명)]
	 *	- field : CNSL_MGMT_NO [접수관리번호]
	 *	- field : PROGR_ST [진행상태]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : PRCH_REG_LIST
	 *		- field : PRCH_MGMT_NO [매입 관리 번호]
	 *		- field : CNSL_MGMT_NO [상담 관리 번호]
	 *		- field : EQP_MDL_CD [단말기 모델 코드]
	 *		- field : EQP_SER_NO [상담 관리 일련 번호]
	 *		- field : CNSL_DT [상담 일자]
	 *		- field : TLY_DT [검수 일자]
	 *		- field : EQP_COLOR_CD [단말기 색상 코드]
	 *		- field : AFFIL_ORG_ID [소속조직ID]
	 *		- field : ORG_NM [조직 명]
	 *		- field : HEADQ_NM [본부명]
	 *		- field : MKT_TEAM_CD [마케팅팀코드]
	 *		- field : MKT_TEAM_NM [마케팅팀명]
	 *		- field : AGN_ORG_CD [대리점 조직 코드]
	 *		- field : UKEY_SUB_CD [UKEY 서브 코드]
	 *		- field : AFFIL_AGN_NM [대리점명]
	 *		- field : DEALCO_NM [상담처명]
	 *		- field : CNSL_DEALCO_CD [상담 거래처 코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : PRCH_AMT [매입금액]
	 *		- field : BANK_CD [은행코드]
	 *		- field : ACCO_NO [계좌번호]
	 *		- field : DPSTR [예금주(고객명)]
	 *		- field : TEL_NO [전화번호]
	 *		- field : EQP_ST [단말기 상태]
	 *		- field : PRCH_CL [매입 구분]
	 *		- field : CUST_IDEA [고객 의사]
	 *		- field : PROGR_ST [진행 상태]
	 *		- field : EVALCNSLR_SUGG [평가원 의견]
	 *		- field : ACCO_NO_ENPT [계좌번호_암호화]
	 *		- field : DPSTR_ENPT [예금주(고객명)_암호화]
	 *		- field : TEL_NO_ENPT [연락처_암호화]
	 *		- field : SCRB_MTHD [가입방법]
	 * </pre>
	 */
	public IDataSet pInqEqpPrchRegList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. FU lookup
            //FPRReqpClctInRgst fPRReqpClctInRgst = (FPRReqpClctInRgst) lookupFunctionUnit(FPRReqpClctInRgst.class);
            // 2. FM 호출
            if(requestData != null && requestData.getField("DPSTR") != null && !"".equals(requestData.getField("DPSTR").toString())){
                requestData.putField("DPSTR_ENPT", HpcUtils.encodeByAES(requestData.getField("DPSTR")));
            }
            responseData = callSharedBizComponentByDirect("ep.EPSCSBase", "fInqEqpPrchRegList", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }       
        return responseData;
    }
  
}
