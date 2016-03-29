package dms.sc.scbbase.biz;

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
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: PSCAlrtSvcHstMgmp</li>
 * <li>설  명 : 알림서비스이력관리</li>
 * <li>작성일 : 2014-09-24 18:12:58</li>
 * <li>작성자 : 정혜미 (junghaemi)</li>
 * </ul>
 *
 * @author 정혜미 (junghaemi)
 */
public class PSCAlrtSvcHstMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PSCAlrtSvcHstMgmt() {
		super();
	}

	/**
	 * <pre>알림서비스이력조회</pre>
	 *
	 * @author admin (admin)
	 * @since 2015-06-22 16:59:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ALRT_SND_REQ_DTM [알림발송요청일시]
	 *	- field : ALRT_SND_NO [알림발송번호]
	 *	- field : REVR_CL_CD [수신자구분코드]
	 *	- field : RETN_TEL_NO [수신전화번호]
	 *	- field : LAST_DAY [월력마지막날]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_ALRT_SVC_HST
	 *		- field : ALRT_SND_REQ_DTM [알림요청일시]
	 *		- field : ALRT_SND_NO [알림발송번호]
	 *		- field : REVR_CL_CD [수신자구분코드]
	 *		- field : RETN_TEL_NO [수신전화번호]
	 *		- field : RETN_TEL_NO_ENPT [수신전화번호암호화]
	 *		- field : ALRT_MSG_CTT [알림메시지내용]
	 *		- field : TRMS_RSLT_CD [전송결과코드]
	 *		- field : TRMS_RSLT_YN [전송결과여부]
	 * </pre>
	 */
	public IDataSet pInqAlrtSvcHst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();
		try {
			//1. FU lookup
		    FSCAlrtSvcHstMgmt fRM0023M = (FSCAlrtSvcHstMgmt) lookupFunctionUnit(FSCAlrtSvcHstMgmt.class);
			// 2. FM 호출 
			if ( requestData.getField("RETN_TEL_NO") != null ) {
				requestData.putField("RETN_TEL_NO_ENPT", HpcUtils.encodeByAES(reqDs.getField("RETN_TEL_NO")));
			}
			responseData = fRM0023M.fInqAlrtSvcHst(requestData, onlineCtx);
			// 3. 결과값 리턴
			responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

    /**
	 * <pre>알림서비스결과조회</pre>
	 *
	 * @author admin (admin)
	 * @since 2015-06-22 16:59:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ALRT_SND_REQ_DTM [알림발송요청일시]
	 *	- field : ALRT_SND_NO [알림발송번호]
	 *	- field : REVR_CL_CD [수신자구분코드]
	 *	- field : RETN_TEL_NO [수신전화번호]
	 *	- field : LAST_DAY [월력마지막날]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_ALRT_SVC_HST
	 *		- field : ALRT_SND_REQ_DTM [알림요청일시]
	 *		- field : ALRT_SND_NO [알림발송번호]
	 *		- field : REVR_CL_CD [수신자구분코드]
	 *		- field : RETN_TEL_NO [수신전화번호]
	 *		- field : RETN_TEL_NO_ENPT [수신전화번호암호화]
	 *		- field : ALRT_MSG_CTT [알림메시지내용]
	 * </pre>
	 */
    public IDataSet pInqAlrtRslt(IDataSet requestData, IOnlineContext onlineCtx) {
    	IDataSet responseData = new DataSet();
    	CommonArea ca = getCommonArea(onlineCtx);
    	IDataSet reqDs = (IDataSet) requestData.clone();
    	try {
    		reqDs.putField("USERNO", ca.getUserNo());
    		
    		callSharedBizComponentByDirect("sc.SCSBase", "fInqAlrtRslt", reqDs, onlineCtx);
    		// 3. 결과값 리턴
    		responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
    	} catch ( BizRuntimeException e ) {
    		throw e;
    	} catch ( Exception e ) {
    		throw new BizRuntimeException("DMS00009", e); //시스템 오류
    	}
    	return responseData;
    }

}
