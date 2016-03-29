package dms.sc.scbbase.biz;

import java.util.Map;

import fwk.utils.PagenateUtils;
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
 * <li>단위업무명: FSCBatJobOpHst</li>
 * <li>설  명 : 배치작업처리이력</li>
 * <li>작성일 : 2014-09-24 11:06:11</li>
 * <li>작성자 : 정혜미 (junghaemi)</li>
 * </ul>
 *
 * @author 정혜미 (junghaemi)
 */
public class FSCBatJobOpHst extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCBatJobOpHst() {
		super();
	}

	/**
	 *
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqBatJobOpHst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone(); //원거래의 DataSet 훼손을 막기 위한 clone
		IRecordSet batJobOpHstRs = null;
		int batJobOpHstTtCnt = 0;
		try {
			// 1. DU lookup 
			DSCBatJobOpHst dSCBatJobOpHst = (DSCBatJobOpHst) lookupDataUnit(DSCBatJobOpHst.class);
			// 2. TOTAL COUNT DM호출
			dsCnt = dSCBatJobOpHst.dSBatJobOpHstTotCnt(requestData, onlineCtx);
			batJobOpHstTtCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);
			// 3. LIST DM 호출
			responseData = dSCBatJobOpHst.dSBatJobOpHstPasing(reqDs, onlineCtx);
			batJobOpHstRs = responseData.getRecordSet("RS_BAT_JOB_PROC_HST");
			PagenateUtils.setPagenatedParamToRecordSet(batJobOpHstRs, reqDs, batJobOpHstTtCnt);
		} catch ( BizRuntimeException e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

}
