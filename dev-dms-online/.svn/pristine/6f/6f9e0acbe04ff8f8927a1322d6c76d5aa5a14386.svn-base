package dms.sc.scbbase.biz;

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
 * <li>단위업무명: DSCAlrtSvcHstMgmp</li>
 * <li>설  명 : 알림서비스이력관리</li>
 * <li>작성일 : 2014-09-24 18:13:47</li>
 * <li>작성자 : 정혜미 (junghaemi)</li>
 * </ul>
 *
 * @author 정혜미 (junghaemi)
 */
public class DSCAlrtSvcHstMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCAlrtSvcHstMgmt() {
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
	public IDataSet dSAlrtSvcHstPasing(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SAlrtSvcHstPasing", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_ALRT_SVC_HST", rsReturn);
		return responseData;
	}

	/**
	 * 알림서비스이력전체건수조회
	 * 
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAlrtSvcHstTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecord record = dbSelectSingle("SAlrtSvcHstTotCnt", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putFieldMap(record);
		return responseData;
	}

}
