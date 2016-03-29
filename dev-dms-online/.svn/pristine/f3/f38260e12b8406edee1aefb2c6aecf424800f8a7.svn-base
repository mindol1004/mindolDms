package dms.sc.scbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: DTB_FRM_AUTR_ROL01</li>
 * <li>설 명 : 화면권한역할관리</li>
 * <li>작성일 : 2014-07-21 15:39:56</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 * 
 * @author 심상준 (simair)
 */
public class DSCFrmAutrRolMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCFrmAutrRolMgmt() {
		super();
	}

	/**
	 * 화면권한역할목록조회(권한O)
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFrmRolListbyYes(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SFrmRolListbyYes", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_FRM_ROL_LIST_Y", rsReturn);
		return responseData;
	}

	/**
	 * 화면권한역할목록조회(권한X)
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFrmRolListbyNo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SFrmRolListbyNo", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_FRM_ROL_LIST_N", rsReturn);
		return responseData;
	}

	/**
	 * 화면권한역할등록
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUFrmRolListReg(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		dbUpdate("UFrmRolListReg", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * 화면권한역할수정
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUFrmRolListUpd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		dbUpdate("UFrmRolListUpd", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * 화면권한역할삭제
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDFrmRolListDel(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		dbUpdate("DFrmRolListDel", requestData.getFieldMap(), onlineCtx);
		return responseData;
	}

	/**
	 * 화면권한역할전체건수조회(권한O)
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFrmRolListCntbyYes(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecord record = dbSelectSingle("SFrmRolListCntbyYes", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putFieldMap(record);
		return responseData;
	}

	/**
	 * 화면권한역할전체건수조회(권한X)
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFrmRolListCntbyNo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecord record = dbSelectSingle("SFrmRolListCntbyNo", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putFieldMap(record);
		return responseData;
	}

	/**
	 * 화면권한세부역할삭제
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDFrmRolListDtlDel(IDataSet requestData, IOnlineContext onlineCtx) {

		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		dbDelete("DFrmRolListDtlDel", requestData.getFieldMap(), onlineCtx);
		return responseData;
	}

	/**
	 * 
	 * 
	 * @author 심상준 (simair)
	 * @since 2014-12-12 13:31:16
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFrmRolbyUser(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SFrmRolbyUser", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_FRM_AUTR", rsReturn);
		return responseData;
	}

	/**
	 *
	 *
	 * @author 심상준 (simair)
	 * @since 2014-12-16 15:02:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSMenuRolLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SMenuRolByUser", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_SUB_MENU", rsReturn);
		return responseData;
	}

	/**
	 * 권한있는메뉴리스트를 조회한다.
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2015-03-04 16:47:22
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAutrRolMenubyYes(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SAutrRolMenu", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_FRM_ROL_LIST_Y", rsReturn);
		return responseData;
	}

	/**
	 * 권한없는메뉴리스트를 조회한다.
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-07-21 15:39:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAutrRolMenuPopup(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SAutrRolMenuPopup", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_FRM_ROL_LIST_P", rsReturn);
		return responseData;
	}

	/**
	 * 화면상세권한조회
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2015-03-06 13:55:40
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFrmDtlAutr(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SFrmDtlAutr", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_FRM_ROL_LIST", rsReturn);
		return responseData;
	}

}
