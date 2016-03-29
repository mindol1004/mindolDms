package dms.sc.scbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: DTB_USER_FAVI01</li>
 * <li>설  명 : 사용자즐겨찾기관리</li>
 * <li>작성일 : 2014-08-07 11:09:33</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class DSCUserFavi extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCUserFavi() {
		super();
	}

	/**
	 * 사용자즐겨찾기등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIUserFavi(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		int rowCnt = dbUpdate("IUserFavi", requestData.getFieldMap(), onlineCtx);
		if ( rowCnt == 1 ) {
			responseData.putField("isSuccess", true);
		} else {
			responseData.putField("isSuccess", false);
		}
		// 2.결과값 셋팅
		responseData.setOkResultMessage("SKFI0001", null);//별도의 파라미터 없이 메시지 그대로 내보내는 경우
		return responseData;
	}

	/**
	 * 사용자즐겨찾기삭제
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDUserFaviLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		int rowCnt = dbDelete("DUserFavi", requestData.getFieldMap(), onlineCtx);
		if ( rowCnt == 1 ) {
			responseData.putField("isSuccess", true);
		} else {
			responseData.putField("isSuccess", false);
		}
		// 2.결과값 셋팅
		responseData.setOkResultMessage("SKFI0001", null);//별도의 파라미터 없이 메시지 그대로 내보내는 경우
		return responseData;
	}

	/**
	 * 사용자즐겨찾기목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSUserFaviLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();

		IRecordSet rsReturn = dbSelect("SUserFaviLst", requestData, onlineCtx);
		if ( rsReturn != null ) {
			responseData.putField("USER_FAVI_LIST_CNT", rsReturn.getRecordCount());
		} else {
			responseData.putField("USER_FAVI_LIST_CNT", 0);
		}
		// 3.결과값 셋팅
		responseData.putRecordSet("RS_USER_FAVI_LIST", rsReturn);

		return responseData;
	}

}
