package dms.nr.nrseibase.biz;

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
 * <li>단위업무명: [DM]손해배상금정책 관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-07-10 09:44:09</li>
 * <li>작성자 : 안진갑 (bella21cjk)</li>
 * </ul>
 *
 * @author 안진갑 (bella21cjk)
 */
public class DNREqpInsuMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DNREqpInsuMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-10-14 13:35:39
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIEqpInsu(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    // 1.쿼리문 호출
 		dbInsert("IEqpInsu", requestData, onlineCtx);
 		
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-10-14 13:35:39
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUEqpInsu(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    // 1.쿼리문 호출
 		dbUpdate("UEqpInsu", requestData, onlineCtx);
 		
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-10-14 13:35:39
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	@SuppressWarnings("unchecked")
	public IDataSet dSEqpInsuTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    // 1.쿼리문 호출	    
 		IRecord record = dbSelectSingle("SEqpInsuTotCnt", requestData, onlineCtx);
 		// 2.결과값 셋팅	    
 		responseData.putFieldMap(record); 
 		
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-10-14 13:35:39
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpInsuLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    // 1.쿼리문 호출
 		IRecordSet rsReturn = dbSelect("SEqpInsuLstPaging", requestData, onlineCtx);
 		// 2.결과값 셋팅
 		responseData.putRecordSet("RS_EQP_INSU_LIST", rsReturn);
 		
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-10-14 13:35:39
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	@SuppressWarnings("unchecked")
    public IDataSet dSEqpInsuNo(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
 		IRecord record = dbSelectSingle("SEqpInsuNo", requestData, onlineCtx);
 		// 2.결과값 셋팅	    
 		responseData.putFieldMap(record); 
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-10-14 13:35:39
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSChkDupFrDt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SChkDupFrDt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record); 
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2016-02-16 17:57:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSChkDupFrDt2(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
        // 1.쿼리문 호출    
        IRecord record = dbSelectSingle("SChkDupFrDt2", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record); 
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-10-14 13:35:39
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUPreEquInsu(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    dbUpdate("UPreEquInsu", requestData, onlineCtx);
	
	    return responseData;
	}
  
}
