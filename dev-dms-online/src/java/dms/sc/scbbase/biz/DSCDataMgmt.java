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
 * <li>단위업무명: [DU]사용자권한역할관리 </li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-12-11 13:34:07</li>
 * <li>작성자 : 임지후 (jihooyim)</li>
 * </ul>
 *
 * @author 임지후 (jihooyim)
 */
public class DSCDataMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCDataMgmt() {
		super();
	}

	/**
	 * 데이터권한역할목록을 조회한다.
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-12-11 13:34:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSDataLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SDataLstInq", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_DATA_AUTR_ROL", rsReturn);
		return responseData;
	}

	/**
	 * 데이터권한역할등록
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-12-11 18:00:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIDataLstReg(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		dbInsert("IDataLstReg", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * 데이터권한역할수정
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-12-11 18:00:33
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUDataLstUpd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		dbInsert("UDataLstUpd", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * 데이터권한역할삭제
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-12-11 18:00:47
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDDataLstDel(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		dbDelete("DDataLstDel", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * 데이터권한역할 정보 수정시 데이터가 있는지 확인한다.
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-12-11 13:34:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSDataLstChk(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecord record = dbSelectSingle("SDataLstChk", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putFieldMap(record);
		return responseData;
	}

}
