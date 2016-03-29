package dms.fw.fwsbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: DTR_HPC_FRW_TRLOG01</li>
 * <li>설  명 : [DU]시스템로그조회</li>
 * <li>작성일 : 2014-09-16 14:38:52</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class DSCSysLogMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCSysLogMgmt() {
		super();
	}

	/**
	 * <pre>거래로그목록조회</pre>
	 *
	 * @author admin (admin)
	 * @since 2015-09-14 16:33:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dSSysTLogLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
    	IDataSet responseData = new DataSet();
    	// 1. 쿼리문 호출
    	IRecordSet rsReturn = dbSelect("SSysTLogLstPaging", requestData, onlineCtx);
    	// 2.결과값 셋팅
    	responseData.putRecordSet("RS_SYS_LOG_LIST", rsReturn);
    	return responseData;
    }

    /**
	 * <pre>에러로그목록조회</pre>
	 *
	 * @author admin (admin)
	 * @since 2015-09-14 16:33:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dSSysELogLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
    	IDataSet responseData = new DataSet();
    	// 1. 쿼리문 호출
    	IRecordSet rsReturn = dbSelect("SSysELogLstPaging", requestData, onlineCtx);
    	// 2.결과값 셋팅
    	responseData.putRecordSet("RS_SYS_LOG_LIST", rsReturn);
    	return responseData;
    }

    /**
	 * <pre>거래로그 전체 건수조회</pre>
	 *
	 * @author admin (admin)
	 * @since 2015-09-14 16:33:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dSSysTLogLstTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
    	IDataSet responseData = new DataSet();
    	// 1.쿼리문 호출
    	IRecord record = dbSelectSingle("SSysTLogLstTotCnt", requestData, onlineCtx);
    	// 2. 결과값 셋팅
    	responseData.putFieldMap(record);
    	return responseData;
    }

    /**
	 * <pre>에러로그 전체 건수조회</pre>
	 *
	 * @author admin (admin)
	 * @since 2015-09-14 16:33:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dSSysELogLstTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
    	IDataSet responseData = new DataSet();
    	// 1.쿼리문 호출
    	IRecord record = dbSelectSingle("SSysELogLstTotCnt", requestData, onlineCtx);
    	// 2. 결과값 셋팅
    	responseData.putFieldMap(record);
    	return responseData;
    }

}