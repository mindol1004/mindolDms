package dms.nr.nrsisbase.biz;

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
 * <li>단위업무명: [DU]추심회수금 조회 및 수수료 정산</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-11-13 10:12:56</li>
 * <li>작성자 : 김혁범 (kezmain1)</li>
 * </ul>
 *
 * @author 김혁범 (kezmain1)
 */
public class DNRColsClctCmmsXclMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DNRColsClctCmmsXclMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-11-13 10:12:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSColsClctCmmsXclSlipLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
		 IDataSet responseData = new DataSet();
			
		 // 1.쿼리문 호출
	 	 IRecordSet rsReturn = dbSelect("SColsClctCmmsXclSlipLstPaging", requestData, onlineCtx);
	 	 // 2.결과값 셋팅
	 	 responseData.putRecordSet("RS_SLIP_LIST", rsReturn);
		
		 return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-11-13 10:17:28
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSColsClctCmmsXclLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
	 	IRecordSet rsReturn = dbSelect("SColsClctCmmsXclLstPaging", requestData, onlineCtx);
	 	// 2.결과값 셋팅
	 	responseData.putRecordSet("RS_COLS_CLCT_CMMS_XCL_LIST", rsReturn);
		
		return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-11-13 10:22:41
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSColsClctCmmsXclTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출	    
  		IRecord record = dbSelectSingle("SColsClctCmmsXclTotCnt", requestData, onlineCtx);
  		// 2.결과값 셋팅	    
  		responseData.putFieldMap(record); 
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-11-16 13:08:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSColsClctCmmsXclSlipTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출	    
  		IRecord record = dbSelectSingle("SColsClctCmmsXclSlipTotCnt", requestData, onlineCtx);
  		// 2.결과값 셋팅	    
  		responseData.putFieldMap(record); 
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-11-13 10:12:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dScolsClctSum(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출	    
	    IRecordSet rsReturn = dbSelect("ScolsClctSum", requestData, onlineCtx);
  		// 2.결과값 셋팅	    
  		responseData.putRecordSet("RS_COLS_SUM_LIST", rsReturn);
  		
	    return responseData;
	}
  
}
