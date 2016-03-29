package dms.sc.scbbase.biz;

import fwk.utils.PagenateUtils;

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
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: FSCAlrtSvcHstMgmp</li>
 * <li>설  명 : 알림서비스이력관리</li>
 * <li>작성일 : 2014-09-24 18:13:25</li>
 * <li>작성자 : 정혜미 (junghaemi)</li>
 * </ul>
 *
 * @author 정혜미 (junghaemi)
 */
public class FSCAlrtSvcHstMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCAlrtSvcHstMgmt() {
		super();
	}

	/**
	 * 알림서비스이력조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqAlrtSvcHst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone(); //원거래의 DataSet 훼손을 막기 위한 clone
		IRecordSet alrtSvcHstRs = null;
		int alrtSvcHstTtCnt = 0;
		try {
			// 1. DU lookup
			DSCAlrtSvcHstMgmt dSCAlrtSvcHst = (DSCAlrtSvcHstMgmt) lookupDataUnit(DSCAlrtSvcHstMgmt.class);
			// 2. TOTAL COUNT DM호출
			dsCnt = dSCAlrtSvcHst.dSAlrtSvcHstTotCnt(requestData, onlineCtx);
			alrtSvcHstTtCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);
			// 3. LIST DM 호출
			responseData = dSCAlrtSvcHst.dSAlrtSvcHstPasing(reqDs, onlineCtx);
			alrtSvcHstRs = responseData.getRecordSet("RS_ALRT_SVC_HST");
			PagenateUtils.setPagenatedParamToRecordSet(alrtSvcHstRs, reqDs, alrtSvcHstTtCnt);
		} catch ( BizRuntimeException e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

}
