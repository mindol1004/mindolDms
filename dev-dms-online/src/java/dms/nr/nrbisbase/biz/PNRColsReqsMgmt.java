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
 * <li>단위업무명: [PU]추심정보관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-11-11 09:15:42</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class PNRColsReqsMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNRColsReqsMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-11-11 09:15:42
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : UKEY_INSURE_MGMT_NO [보험관리번호]
	 *	- field : INV_ST_CD [추심상태코드]
	 *	- field : RSN_CD [사유코드]
	 *	- field : UKEY_SVC_MGMT_NO [서비스관리번호]
	 *	- field : CONF_INV_CNCL_STA_DT [확정청구취소시작일]
	 *	- field : CONF_INV_CNCL_END_DT [확정청구취소종료일]
	 *	- field : COLS_REQS_STA_DT [처리시작일]
	 *	- field : COLS_REQS_END_DT [처리종료일]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pInqColsReqsLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSISBase", "fInqColsReqsLst", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        // 3. 결과값 리턴
        responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-11-11 09:15:42
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : INSU_INV_NO [보험청구번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pInqColsReqsLstDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
	    	// 1. FM 호출
	    	responseData = callSharedBizComponentByDirect("nr.NRSISBase", "fInqColsReqsLstDtl", requestData, onlineCtx);
	    } catch ( BizRuntimeException e ) {
	    	throw e;
	    } catch ( Exception e ) {
	    	throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
	    }
	    // 3. 결과값 리턴
	    responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-11-11 09:15:42
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_COLS_LIST
	 *		- field : INSU_INV_NO [필드1]
	 *		- field : UKEY_INSU_NO [필드2]
	 *		- field : UKEY_INSURE_MGMT_NO [필드3]
	 *		- field : INV_ST_CD [필드4]
	 *		- field : UNPD_RENTAL_FEE [필드5]
	 *		- field : DMG_CMP_UNRTN_AMT [필드6]
	 *		- field : ARR_ADD_AMT [필드7]
	 *		- field : RECV_ST_CD [필드8]
	 *		- field : UKEY_RECV_DT [필드9]
	 *		- field : UKEY_RECV_AMT [필드10]
	 *		- field : RSN_CD [필드1]
	 *		- field : UKEY_SVC_MGMT_NO [필드2]
	 *		- field : INSU_INV_MEMO [필드3]
	 *		- field : CONF_INV_CNCL_DT [필드4]
	 *		- field : COLS_REQS_DT [필드5]
	 *		- field : MID_TERM_PEN_AMT [필드6]
	 *		- field : RTN_DELAY_PEN_AMT [필드1]
	 *		- field : DMG_CMP_BKAG_AMT [필드2]
	 *		- field : COLS_PRC [필드3]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pUpdateColsReqs(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
	    	// 1. FM 호출
	    	responseData = callSharedBizComponentByDirect("nr.NRSISBase", "fUpdateColsReqs", requestData, onlineCtx);
	    } catch ( BizRuntimeException e ) {
	    	throw e;
	    } catch ( Exception e ) {
	    	throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
	    }
	
	    return responseData;
	}
  
}
