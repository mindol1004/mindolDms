package dms.sc.scbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: FSCUserFaviMgmt</li>
 * <li>설  명 : 사용자즐겨찾기관리</li>
 * <li>작성일 : 2014-08-07 11:07:12</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class FSCUserFaviMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCUserFaviMgmt() {
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
	public IDataSet fUpdUserFavi(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1. DU lookup
		DSCUserFavi dSCUserFavi = (DSCUserFavi) lookupDataUnit(DSCUserFavi.class);
		// 2. DM호출
		responseData = dSCUserFavi.dIUserFavi(requestData, onlineCtx);
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
	public IDataSet fDelUserFavi(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1. DU lookup
		DSCUserFavi dSCUserFavi = (DSCUserFavi) lookupDataUnit(DSCUserFavi.class);
		// 2. DM호출
		responseData = dSCUserFavi.dDUserFaviLst(requestData, onlineCtx);
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
	 * <pre>
	 *	- record : RS_USER_FAVI_LIST
	 *		- field : FAVI_NO [필드1]
	 *		- field : USER_NO [필드2]
	 *		- field : FRM_ID [필드3]
	 *		- field : FRM_URL [필드4]
	 *		- field : FRM_CL_CD [필드5]
	 *		- field : MENU_ID [필드6]
	 *		- field : MENU_NM [필드7]
	 * </pre>
	 */
	public IDataSet fInqUserFaviLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1. DU lookup
		DSCUserFavi dSCUserFavi = (DSCUserFavi) lookupDataUnit(DSCUserFavi.class);
		// 2. DM호출
		responseData = dSCUserFavi.dSUserFaviLst(requestData, onlineCtx);
		return responseData;
	}

}
