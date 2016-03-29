package dms.ep.epsbibase.biz;

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
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]가격표관리</li>
 * <li>설  명 : <pre>[DU]가격표관리</pre></li>
 * <li>작성일 : 2015-08-19 14:15:27</li>
 * <li>작성자 : 김윤환 (kyh0904)</li>
 * </ul>
 *
 * @author 김윤환 (kyh0904)
 */
public class DEPPriceListMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPPriceListMgmt(){
		super();
	}

	/**
	 * <pre>가격표관리목록총건수</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:15:27
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSPriceListMgmtTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
	    // 1.쿼리문 호출     
 		IRecord record = dbSelectSingle("SEpBasProdFixPriceMstTotCnt", requestData, onlineCtx);
 		// 2.결과값 셋팅     
 		responseData.putFieldMap(record);
	
	    return responseData;
	}

	/**
	 * <pre>가격표관리목록조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:15:27
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSPriceListMgmtPaging(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
	    // 1.쿼리문 호출
 		IRecordSet rsReturn = dbSelect("SEpBasProdFixPriceMstPaging", requestData, onlineCtx);
 		// 2.결과값 셋팅
 		responseData.putRecordSet("RS_PRICE_LIST", rsReturn);
	    return responseData;
	}

	/**
	 * <pre>[DM]가격표기준금액조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-20 14:23:47
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSPriceStrdAmtLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();	
	    // 1.쿼리문 호출     
 		IRecord record = dbSelectSingle("SPriceStrdAmtLst", requestData, onlineCtx);
 		// 2.결과값 셋팅     
 		responseData.putFieldMap(record);
 		
	    return responseData;
	}

	/**
	 * <pre>[DM]가격표관리번호조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-21 11:17:03
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSPrclstMgmtNo(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출     
	  	IRecord record = dbSelectSingle("SPrclstMgmtNoSeq", requestData, onlineCtx);
	  	// 2.결과값 셋팅     
	  	responseData.putFieldMap(record); 
	
	    return responseData;
	}

	/**
	 * <pre>[DM]가격표관리등록</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-21 13:24:22
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIPiceListMgmt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
	    // 1.쿼리문 호출
	 	dbInsert("IEpBasProdFixPriceMst", requestData, onlineCtx);
	    return responseData;
	}

	/**
	 * <pre>[DM]상품가격표등록</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-21 13:25:22
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIProdPiceListMgmt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
	    // 1.쿼리문 호출
	 	dbInsert("IEpBasProdFixPrice", requestData, onlineCtx);
	    return responseData;
	}

	/**
	 * <pre>[DM]상품가격종료일자수정</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-21 13:26:52
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUProdPriceTodt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
	    // 1.쿼리문 호출
	 	dbUpdate("UPriceTodt", requestData, onlineCtx);
	    return responseData;
	}

	/**
	 * <pre>[DM]상품가격표금액조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:15:27
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSProdPriceAmtLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
	    // 1.쿼리문 호출
 		IRecordSet rsReturn = dbSelect("SProdPriceAmtLst", requestData, onlineCtx);
 		// 2.결과값 셋팅
 		responseData.putRecordSet("RS_PRICE_AMT_LIST", rsReturn);
	    return responseData;
	}

	/**
	 * <pre>[DM]상품고정가격관리삭제</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:15:27
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDProdFixPriceMst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
	 	dbUpdate("DEpBasProdFixPriceMst", requestData, onlineCtx);
	
	    return responseData;
	}

	/**
	 * <pre>[DM]상품고정가격삭제</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:15:27
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDProdFixPrice(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
	 	dbUpdate("DEpBasProdFixPrice", requestData, onlineCtx);
	
	    return responseData;
	}

	/**
	 * <pre>[DM]상품고정가격종료일자수정</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:15:27
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUProdFixPriceTodt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
	 	dbUpdate("UEpBasProdFixPriceTodt", requestData, onlineCtx);
	 	
	    return responseData;
	}

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-09-02 19:40:18
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSPriceEqpCdErrExmn(IDataSet requestData, IOnlineContext onlineCtx) { 
        IDataSet responseData = new DataSet();  
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SPriceEqpCdErrExmn", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
        
        return responseData;
    }
  
}
