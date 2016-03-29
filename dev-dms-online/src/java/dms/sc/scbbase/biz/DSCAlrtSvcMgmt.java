package dms.sc.scbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: DSCAlrtSvcMgmt</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-09-22 16:48:18</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class DSCAlrtSvcMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCAlrtSvcMgmt() {
		super();
	}

	/**
	 * 알림서비스목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAlrtSvcLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SAlrtSvcLstPaging", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_ALRT_SVC_LIST", rsReturn);
		return responseData;
	}

	/**
	 * 알림서비스전체건수조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAlrtSvcLstTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecord record = dbSelectSingle("SAlrtSvcLstTotCnt", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putFieldMap(record);
		return responseData;
	}

	/**
	 * 알림서비스사전체크
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAlrtSvcLstValidate(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		//1. 쿼리문 호출
		IRecord record = dbSelectSingle("SAlrtSvcLstValidate", requestData, onlineCtx);
		//2. 결과값 셋팅
		responseData.putFieldMap(record);
		return responseData;
	}

	/**
	 * 알림서비스등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIAlrtSvc(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		dbInsert("IAlrtSvc", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * 알림서비스수정
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUAlrtSvc(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		dbUpdate("UAlrtSvc", requestData, onlineCtx);
		return responseData;
	}

}
