package dms.sc.scbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: DTB_MENU01</li>
 * <li>설 명 : 메뉴관리</li>
 * <li>작성일 : 2014-07-30 13:20:19</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 * 
 * @author 심상준 (simair)
 */
public class DSCMenuMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCMenuMgmt() {
		super();
	}

	/**
	 * 메뉴목록조회
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSMenuLst(IDataSet requestData, IOnlineContext onlineCtx) {

		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SMenuLst", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_MENU_LIST", rsReturn);
		return responseData;
	}

	/**
	 * 메뉴등록
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIMenu(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		dbInsert("IMenu", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * 메뉴수정
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUMenu(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		dbInsert("UMenu", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * 메뉴삭제
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDMenu(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		dbDelete("DMenu", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * 메뉴저장사전체크
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSMenuValidate(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecord record = dbSelectSingle("SMenuValidate", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putFieldMap(record);
		return responseData;

	}
}
