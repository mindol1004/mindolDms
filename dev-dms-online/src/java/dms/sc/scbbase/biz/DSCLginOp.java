package dms.sc.scbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import fwk.constants.DmsConstants;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: DTB_USER02</li>
 * <li>설  명 : 로그인처리</li>
 * <li>작성일 : 2014-07-30 11:01:27</li>
 * <li>작성자 : 김석영 (kimsukyoung)</li>
 * </ul>
 *
 * @author 김석영 (kimsukyoung)
 */
public class DSCLginOp extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCLginOp() {
		super();
	}

	/**
	 * 사용자조회(ID)
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSUserById(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecordSet rs = dbSelect("SUserById", requestData, onlineCtx);
		responseData.putRecordSet("RS_USER", rs);
		return responseData;
	}

	/**
	 * 비밀번호확인
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSPwd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecordSet rs = dbSelect("SPwd", requestData, onlineCtx);
		responseData.putRecordSet("RS_USER", rs);
		return responseData;
	}

	/**
	 * 권한목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAutrLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecordSet rsReturn = dbSelect("SAutrLst", requestData, onlineCtx);
		responseData.putRecordSet("RS_AUTR", rsReturn);
		return responseData;
	}

	/**
	 * 로그인이력수정
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dULginHst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		dbUpdate("ULginHst", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * 그룹웨어사용자정보조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSGwUserInfo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecord record = dbSelectSingle("SGwUserInfo", requestData, onlineCtx);
		if ( record != null ) {
			responseData.putFieldMap(record);
		}
		return responseData;
	}

	/**
	 * 사용자등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIUser(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		int rowCnt = dbInsert("IUser", requestData, onlineCtx);
		responseData.putField(DmsConstants.IS_SUCCESS, rowCnt > 0);
		return responseData;
	}

	/**
	 * 그룹웨어사용자정보동기화
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUGwUserInfo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		dbInsert("UGwUserInfo", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * 사용자권한역할조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSUserAutrRol(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecordSet rsReturn = dbSelect("SUserAutrRol", requestData, onlineCtx);
		responseData.putRecordSet("RS_AUTR", rsReturn);
		return responseData;
	}

	/**
	 * 사용자권한역할삭제
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDUserAutrRol(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		int rowCnt = dbDelete("DUserAutrRol", requestData, onlineCtx);
		responseData.putField(DmsConstants.IS_SUCCESS, rowCnt > 0);
		return responseData;
	}

	/**
	 * 사용자권한역할등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIUserAutrRol(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		int rowCnt = dbInsert("IUserAutrRol", requestData, onlineCtx);
		responseData.putField(DmsConstants.IS_SUCCESS, rowCnt > 0);
		return responseData;
	}

	/**
	 * 사용자상태변경
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUUserSt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		int rowCnt = dbInsert("UUserSt", requestData.getFieldMap(), onlineCtx);
		responseData.putField(DmsConstants.IS_SUCCESS, rowCnt > 0);
		return responseData;
	}

	/**
	 * 사용자번호채번
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSUserNo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecord record = dbSelectSingle("SUserNo", requestData, onlineCtx);
		if ( record != null ) {
			responseData.putFieldMap(record);
		}
		return responseData;
	}

	/**
	 * 사용자조회(사용자번호)
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSUserByUserNo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecordSet rs = dbSelect("SUserByUserNo", requestData, onlineCtx);
		responseData.putRecordSet("RS_USER", rs);
		return responseData;
	}

	/**
	 * 사용자권한역할이력등록
	 *
	 * @author 이유미 (leeyoumee)
	 * @since 2014-11-24 19:54:11
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIUserAutrRolHis(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		int rowCnt = dbInsert("IUserAutrRolHis", requestData.getFieldMap(), onlineCtx);
		responseData.putField(DmsConstants.IS_SUCCESS, rowCnt > 0);
		return responseData;
	}

	/**
	 * 데이터권한역할조회(팀장) - 로그인시 팀장이 속한 부서에 속한 SV들의 담당 가맹점 목록 조회
	 *
	 * @author 이유미 (leeyoumee)
	 * @since 2014-12-10 17:09:33
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSDataAutrRolByTeamMgr(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecordSet rs = dbSelect("SDataAutrRolByTeamMgr", requestData.getFieldMap(), onlineCtx);
		responseData.putRecordSet("RS_MCHT_LST", rs);
		return responseData;
	}

	/**
	 * 데이터권한역할조회(SV) - SV가 담당한 가맹점목록 조회
	 *
	 * @author 이유미 (leeyoumee)
	 * @since 2014-12-10 17:12:55
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSDataAutrRolBySv(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecordSet rs = dbSelect("SDataAutrRolBySv", requestData, onlineCtx);
		responseData.putRecordSet("RS_MCHT_LST", rs);
		return responseData;
	}

	/**
	 * SV목록조회(팀장) : 팀장의 데이터권한을 Setting하기위해 같은 부서의 SV목록을 조회한다
	 *
	 * @author 이유미 (leeyoumee)
	 * @since 2014-07-30 11:01:27
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSSvListByTeamMgr(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecordSet rs = dbSelect("SSvListByTeamMgr", requestData, onlineCtx);
		responseData.putRecordSet("RS_SV_LST", rs);
		return responseData;
	}

}
