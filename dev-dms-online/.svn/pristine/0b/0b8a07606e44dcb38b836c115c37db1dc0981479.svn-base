package dms.sc.scsbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: DTB_SEON_01</li>
 * <li>설  명 : 세션정보관리</li>
 * <li>작성일 : 2014-09-03 20:52:02</li>
 * <li>작성자 : 김석영 (kimsukyoung)</li>
 * </ul>
 *
 * @author 김석영 (kimsukyoung)
 */
public class DSCUserLginSessInfoMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCUserLginSessInfoMgmt() {
		super();
	}

	/**
	 * 사용자로그인세션정보조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSUserLginSessInfo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecord record = dbSelectSingle("SUserLginSessInfo", requestData, onlineCtx);
		if ( record != null ) {
			responseData.putFieldMap(record);
		}
		return responseData;
	}

	/**
	 * 사용자로그인세션정보등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIUserLginSessInfo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		dbInsert("IUserLginSessInfo", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * 사용자로그인세션정보수정
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUUserLginSessInfo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		dbUpdate("UUserLginSessInfo", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * 사용자로그인세션정보중복체크
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSUserLginSessInfoChk(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecordSet rs = dbSelect("SUserLginSessInfoChk", requestData, onlineCtx);
		responseData.putRecordSet("RS_SESS", rs);
		return responseData;
	}

}
