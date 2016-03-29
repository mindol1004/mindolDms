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
 * <li>단위업무명: DTB_USER_AUTR_ROL01</li>
 * <li>설  명 : 사용자권한역할관리</li>
 * <li>작성일 : 2014-08-11 09:26:05</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class DSCUserAutrRolMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCUserAutrRolMgmt() {
		super();
	}

	/**
	 * 사용자권한역할목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSUserRolListInq(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SUserRolListInq", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_USER_ROL_LIST", rsReturn);
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
	public IDataSet dIUserRolReg(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		int rowCnt = dbInsert("IUserRolReg", requestData.getFieldMap(), onlineCtx);

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
	 * 사용자권한역할수정
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUUserRolUpd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		int rowCnt = dbUpdate("UUserRolUpd", requestData.getFieldMap(), onlineCtx);

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
	 * 사용자권한역할삭제
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDUserRolDel(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		int rowCnt = dbDelete("DUserRolDel", requestData.getFieldMap(), onlineCtx);

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
	 * 사용자권한역할목록전체건수조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSUserListCnt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출	    
		IRecord record = dbSelectSingle("SUserListCnt", requestData, onlineCtx);
		// 2.결과값 셋팅	    
		responseData.putFieldMap(record);
		return responseData;

	}

	/**
	 * 사용자권한역할복사
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIUserRolCopy(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		dbInsert("IUserRolCopy", requestData, onlineCtx);
		return responseData;

	}

	/**
	 * 사용자권한역할복사사전체크
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSUserRolCPChk(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecord record = dbSelectSingle("SUserRolCPChk", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putFieldMap(record);
		return responseData;
	}

	/**
	 * 사용자화면권한역할사전체크
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSUserRolChk(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecord record = dbSelectSingle("SUserRolChk", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putFieldMap(record);
		return responseData;
	}

}
