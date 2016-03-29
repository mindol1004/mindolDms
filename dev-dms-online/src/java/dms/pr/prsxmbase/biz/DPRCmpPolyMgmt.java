package dms.pr.prsxmbase.biz;

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
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [DU]손해배상금정책 관리</li>
 * <li>설  명 : <pre>[DU]손해배상금정책 관리 </pre></li>
 * <li>작성일 : 2015-07-10 09:44:09</li>
 * <li>작성자 : 안진갑 (bella21cjk)</li>
 * </ul>
 *
 * @author 안진갑 (bella21cjk)
 */
public class DPRCmpPolyMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DPRCmpPolyMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-15 11:17:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dICmpPoly(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
		dbInsert("ICmpPoly", requestData, onlineCtx);
		
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-15 11:17:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUCmpPoly(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    // 1.쿼리문 호출
		dbInsert("UCmpPoly", requestData, onlineCtx);
		
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-15 11:17:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	@SuppressWarnings("unchecked")
	public IDataSet dSCmpPolyTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    // 1.쿼리문 호출	    
		IRecord record = dbSelectSingle("SCmpPolyTotCnt", requestData, onlineCtx);
		// 2.결과값 셋팅	    
		responseData.putFieldMap(record); 
		
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-15 11:17:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSCmpPolyLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    // 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SCmpPolyLstPaging", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_CMP_POLY_LIST", rsReturn);
		
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-15 11:17:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpConsPen(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    // 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SEqpConsPen", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_EPQ_CONS_PEN", rsReturn);
		
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-15 11:17:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	@SuppressWarnings("unchecked")
	public IDataSet dSCmpPolyNo(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
		IRecord record = dbSelectSingle("SCmpPolyNo", requestData, onlineCtx);
		// 2.결과값 셋팅	    
		responseData.putFieldMap(record); 
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-15 11:17:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSCmpPolyDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    // 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SCmpPolyDtl", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_CMP_POLY_DTL", rsReturn);
		
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-15 11:17:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dICmpPolyDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    dbInsert("ICmpPolyDtl", requestData, onlineCtx);
	    
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-15 11:17:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUCmpPolyDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    dbInsert("UCmpPolyDtl", requestData, onlineCtx);
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-15 11:17:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUPreCmpPoly(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    dbInsert("UPreCmpPoly", requestData, onlineCtx);
	    return responseData;
	}

	/**
	 * <pre>[DM]손해배상금유효시작일 중복체크(등록)</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-15 11:17:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	@SuppressWarnings("unchecked")
	public IDataSet dSChkDupFrDt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    // 1.쿼리문 호출     
	    IRecord record = dbSelectSingle("SChkDupFrDt", requestData, onlineCtx);
	    // 2.결과값 셋팅     
	    responseData.putFieldMap(record); 
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-15 11:17:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSChkDupFrDt2(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    // 1.쿼리문 호출    
	    IRecord record = dbSelectSingle("SChkDupFrDt2", requestData, onlineCtx);
	    // 2.결과값 셋팅     
	    responseData.putFieldMap(record); 
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-15 11:17:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDCmpPoly(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    dbDelete("DCmpPoly", requestData, onlineCtx);
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-15 11:17:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDCmpPolyDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    dbDelete("DCmpPolyDtl", requestData, onlineCtx);      
	    return responseData;
	}
  
}
