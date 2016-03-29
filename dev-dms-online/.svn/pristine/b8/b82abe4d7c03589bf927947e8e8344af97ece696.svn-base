package dms.sc.scbbase.biz;

import fwk.utils.PagenateUtils;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: FSCPrnInfoApcLogMgmt</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-07-31 14:59:34</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 정혜미 (junghaemi)
 */
public class FSCPrnInfoInqHstMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCPrnInfoInqHstMgmt() {
		super();
	}

	/**
	 * 개인정보 조회이력 목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqPrnInfoInqHst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone(); //원거래의 DataSet 훼손을 막기 위한 clone
		IRecordSet prnInfoInqHstRs = null;
		int prnInfoInqHstTtCnt = 0;
		try {
			// 1. DU lookup
			DSCPrnInfoInqHstMgmt dSCPrnInfoInqHst = (DSCPrnInfoInqHstMgmt) lookupDataUnit(DSCPrnInfoInqHstMgmt.class);
			// 2. TOTAL COUNT DM호출
			dsCnt = dSCPrnInfoInqHst.dSPrnInfoInqHstTotCnt(requestData, onlineCtx);
			prnInfoInqHstTtCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);
			// 3. LIST DM 호출
			responseData = dSCPrnInfoInqHst.dSPrnInfoInqHstPasing(reqDs, onlineCtx);
			prnInfoInqHstRs = responseData.getRecordSet("RS_PRN_INFO_INQ_HST");
			PagenateUtils.setPagenatedParamToRecordSet(prnInfoInqHstRs, reqDs, prnInfoInqHstTtCnt);
		} catch ( BizRuntimeException e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

}
