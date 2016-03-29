package dms.nr.nrsplbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [DU]단말기준정보관리</li>
 * <li>설  명 : <pre>단말기준정보관리DU</pre></li>
 * <li>작성일 : 2015-07-09 16:59:48</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class DNREqpStdMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DNREqpStdMgmt(){
		super();
	}

	/**
	 * <pre>단말기준정보조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-09 17:06:37
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpStdLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
 		IRecordSet rsReturn = dbSelect("SEqpStdLstPaging", requestData, onlineCtx);
 		// 2.결과값 셋팅
 		responseData.putRecordSet("RS_EQP_STD_LIST", rsReturn);
	
	    return responseData;
	}

	/**
	 * <pre>단말기준정보총건수</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-09 17:07:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpStdTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출	    
 		IRecord record = dbSelectSingle("SEqpStdTotCnt", requestData, onlineCtx);
 		// 2.결과값 셋팅	    
 		responseData.putFieldMap(record); 
	
	    return responseData;
	}

	/**
	 * <pre>단말기준정보저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-09 17:21:40
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIEqpStd(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
 		dbInsert("IEqpStd", requestData, onlineCtx);
	
	    return responseData;
	}

	/**
	 * <pre>단말기준정보수정</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-09 17:21:57
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUEqpStd(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
 		dbInsert("UEqpStd", requestData, onlineCtx);
	
	    return responseData;
	}

	/**
	 * <pre>단말기기준정보저장사전체크</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-15 20:10:33
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpStdListChk(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
		IRecord record = dbSelectSingle("SEqpStdListChk", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putFieldMap(record);
	
	    return responseData;
	}
  
}
