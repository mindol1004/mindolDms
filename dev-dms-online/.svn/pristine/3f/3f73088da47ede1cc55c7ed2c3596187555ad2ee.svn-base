package dms.nr.nrbisbase.biz;

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
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [PU]보증보험관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-18 12:25:20</li>
 * <li>작성자 : 안진갑 (bella21cjk)</li>
 * </ul>
 *
 * @author 안진갑 (bella21cjk)
 */
public class PNRInsuMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNRInsuMgmt(){
		super();
	}

    /**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-08-18 12:25:20
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : RENTAL_CNTRT_STA_DT [계약일조회 시작일]
	 *	- field : RENTAL_CNTRT_END_DT [계약일조회 종료일]
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : INSURE_MGMT_NO [보험관리번호]
	 *	- field : USCAN_FNSH_YN [USCAN완료 여부]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : SCRB_FNSH_YN [가입완료여부]
	 *	- field : INCMP_ITM [미완료 항목]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_INSU_INFO_LIST
	 *		- field : RENTAL_CNTRT_STA_DT [계약일자]
	 *		- field : SVC_MGMT_NO [서비스관리버호]
	 *		- field : INSURE_MGMT_NO [보험관리번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : SCRB_FNSH_YN [가입완료여부]
	 *		- field : UKEY_SCRB_DT [UKEY가입일자]
	 *		- field : UKEY_INSURE_SCRB_AMT [UKEY보험가입금액]
	 *		- field : UKEY_INSURE_FEE [UKEY보험요금]
	 *		- field : SGI_SCRB_DT [SGI가입일자]
	 *		- field : SGI_INSURE_SCRB_AMT [SGI보험가입금액]
	 *		- field : SGI_INSURE_FEE [SGI보험요금]
	 *		- field : USCAN_FNSH_YN [USCAN완료여부]
	 *	- record : RS_INSU_INFO_SUM
	 *		- field : SUM_UKEY_INSURE_SCRB_AMT [Ukey보증보험가입금액]
	 *		- field : SUM_UKEY_INSURE_FEE [Ukey보험료금액]
	 *		- field : SUM_SGI_INSURE_SCRB_AMT [SGI보증보험가입금액]
	 *		- field : SUM_SGI_INSURE_FEE [SGI보험료금액]
	 * </pre>
	 */
	public IDataSet pInqInsuInfoLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSISBase", "fInqInsuInfoLst", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        // 3. 결과값 리턴
        responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
        return responseData;
    }
  
}
