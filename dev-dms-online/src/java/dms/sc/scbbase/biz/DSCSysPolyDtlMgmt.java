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
 * <li>단위업무명: [DU]시스템정책상세관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-07-31 15:50:28</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class DSCSysPolyDtlMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCSysPolyDtlMgmt() {
		super();
	}

	/**
	 * 시스템정책상세목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSSysPolyDtlLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SSysPolyDtlLstPaging", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_SYS_POLY_DTL_MGMT_LIST", rsReturn);
		return responseData;
	}

	/**
	 * 시스템정책상세등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dISysPolyDtl(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		dbInsert("ISysPolyDtl", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * 시스템정책상세수정
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUSysPolyDtl(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		dbUpdate("USysPolyDtl", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * 시스템정책상세전체건수조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSSysPolyDtlLstTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출	    
		IRecord record = dbSelectSingle("SSysPolyDtlLstTotCnt", requestData, onlineCtx);
		// 2.결과값 셋팅	    
		responseData.putFieldMap(record);
		return responseData;
	}

	/**
	 * 시스템정책상세저장사전체크
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSSysPolyDtlLstValidate(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecord record = dbSelectSingle("SSysPolyDtlLstValidate", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putFieldMap(record);
		return responseData;
	}

	/**
	 * 시스템정책상세저장사전체크2
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSSyPolyDtlDtmValidate(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecord record = dbSelectSingle("SSyPolyDtlDtmValidate", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putFieldMap(record);
		return responseData;
	}

	/**
	 * 시스템정책상세일련번호채번
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSSysPolyDtlSnoGtno(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SSysPolyDtlSnoGtno", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_SYS_POLY_SNO", rsReturn);
		return responseData;
	}

}
