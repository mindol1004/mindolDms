package dms.sc.scsbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import fwk.constants.DmsConstants;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: DSCBatTaskOpHstMgmt</li>
 * <li>설  명 : 일괄작업처리이력관리</li>
 * <li>작성일 : 2014-09-30 12:16:45</li>
 * <li>작성자 : 임정택 (jtlim)</li>
 * </ul>
 *
 * @author 임정택 (jtlim)
 */
public class DSCBatTaskOpHstMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCBatTaskOpHstMgmt() {
		super();
	}

	/**
	 * 작업번호시퀀스조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : TASK_NO [작업번호]
	 *	- field : TASK_DT [작업일자]
	 * </pre>
	 */
	public IDataSet dSTaskNoSeq(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecord record = dbSelectSingle("STaskNoSeq", requestData, onlineCtx);

		if ( record != null ) {
			responseData.putFieldMap(record);
		}
		return responseData;
	}

	/**
	 * 일괄작업처리이력수정
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUBatTaskOpHst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		int updCnt = dbUpdate("UBatTaskOpHst", requestData.getFieldMap(), onlineCtx);

		responseData.putField(DmsConstants.IS_SUCCESS, true);

		//update실패
		if ( updCnt != 1 ) {
			responseData.putField(DmsConstants.IS_SUCCESS, false);
		}
		return responseData;
	}

	/**
	 * 일괄작업처리이력등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIBatTaskOpHst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		int isertCnt = dbInsert("IBatTaskOpHst", requestData, onlineCtx);

		responseData.putField(DmsConstants.IS_SUCCESS, true);

		//insert실패
		if ( isertCnt != 1 ) {
			responseData.putField(DmsConstants.IS_SUCCESS, false);
		}
		return responseData;
	}

    /**
	 * <pre>작업상태조회</pre>
	 *
	 * @author admin (admin)
	 * @since 2015-07-13 12:41:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : TASK_NO [작업번호]
	 *	- field : TASK_DT [작업일자]
	 * </pre>
	 */
    public IDataSet dSTaskSTCD(IDataSet requestData, IOnlineContext onlineCtx) {
    	IDataSet responseData = new DataSet();
    	IRecord record = dbSelectSingle("STaskSTCD", requestData, onlineCtx);
    
    	if ( record != null ) {
    		responseData.putFieldMap(record);
    	}
    	return responseData;
    }

}
