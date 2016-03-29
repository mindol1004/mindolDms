package dms.sc.scbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/시스템공통</li>
 * <li>단위업무명: [DU]공지사항관리</li>
 * <li>설  명 : <pre>공지사항관리</pre></li>
 * <li>작성일 : 2015-06-29 11:07:12</li>
 * <li>작성자 : 개발자 (developer)</li>
 * </ul>
 *
 * @author 개발자 (developer)
 */
public class DSCBrdMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCBrdMgmt(){
		super();
	}

	/**
	 * <pre>일반게시판 총건수 조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-02 17:17:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSBrdTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    // 1.쿼리문 호출     
	    IRecord record = dbSelectSingle("SBrdTotCnt", requestData, onlineCtx);
		// 2.결과값 셋팅     
		responseData.putFieldMap(record);
	
	    return responseData;
	}

	/**
	 * <pre>일반게시판 페이징 조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-02 17:17:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSBrdPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    // 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SBrdPaging", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_BRD_LIST", rsReturn);
	    return responseData;
	}

	/**
	 * <pre>일반게시판 입력</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-02 17:17:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIBrd(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    // 1.쿼리문 호출
	 	dbInsert("IBrd", requestData, onlineCtx);
	    return responseData;
	}

	/**
	 * <pre>일반게시판 수정</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-02 17:17:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUBrd(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    // 1.쿼리문 호출
	 	dbUpdate("UBrd", requestData, onlineCtx);
	    return responseData;
	}

	/**
	 * <pre>일반게시판 조회수 증가</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-02 17:17:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUBrdIncreBrwsCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    // 1.쿼리문 호출
	 	dbUpdate("UBrdIncreBrwsCnt", requestData, onlineCtx);
	    return responseData;
	}

	/**
	 * <pre>일반게시판 번호 채번</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-02 17:17:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSBrdSeq(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IRecord record = dbSelectSingle("SBrdSeq", requestData, onlineCtx);
		if ( record != null ) {
			responseData.putFieldMap(record);
		}
	    return responseData;
	}
  
}
