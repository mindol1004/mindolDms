package dms.pr.prbxmbase.biz;

import java.util.HashMap;
import java.util.Map;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.log.LogManager;

import org.apache.commons.logging.Log;

import fwk.common.CommonArea;




/**
 * <ul>
 * <li>업무 그룹명 : dms/임대R</li>
 * <li>단위업무명: [PU]매출정산등록</li>
 * <li>설  명 : <pre>[PU]매출정산등록</pre></li>
 * <li>작성일 : 2015-08-07 16:30:31</li>
 * <li>작성자 : 김상오 (myneti99)</li>
 * </ul>
 *
 * @author 김상오 (myneti99)
 */
public class PPRSaleXclRgst extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PPRSaleXclRgst(){
		super();
	}

	/**
	 *
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-08-07 16:30:31
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : IN_DT [검색년월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_SALE_XCL_LIST
	 *		- field : XCL_YM [정산년월]
	 *		- field : XCL_NM [정산항목]
	 *		- field : XCL_QTY [건수]
	 *		- field : XCL_AMT [금액]
	 *		- field : XCL_DT [생성일자]
	 *		- field : XCL_CRTR_NM [생성자]
	 *		- field : XCL_CD [정산코드]
	 * </pre>
	 */
	public IDataSet pInqSaleXclList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1. FU lookup
	    // 2. FM 호출
//	    FPRSaleXclRgst fPRPRSaleXclRgst = (FPRSaleXclRgst) lookupFunctionUnit(FPRSaleXclRgst.class);
//	    responseData.putRecordSet("RS_SALE_XCL_LIST",fPRPRSaleXclRgst.fInqSaleXclList(requestData, onlineCtx).getRecordSet("RS_SALE_XCL_LIST"));
	    responseData = callSharedBizComponentByDirect("pr.PRSXMBase", "fInqSaleXclList", requestData, onlineCtx);
	                                                    
        // 3. 결과값 리턴
        responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-08-07 16:30:31
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : IN_DT [검색년월]
	 *	- field : XCL_GUBUN [매출구분항목]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_SALE_XCL_LIST
	 *		- field : XCL_YM [정산년월]
	 *		- field : XCL_NM [정산항목]
	 *		- field : XCL_QTY [건수]
	 *		- field : XCL_AMT [금액]
	 *		- field : XCL_DT [생성일자]
	 *		- field : XCL_CRTR_NM [생성자]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : RENT_STA_DT [대여시작일]
	 *		- field : RENT_END_DT [대여종료일]
	 *		- field : STA_DT [정산시작일]
	 *		- field : END_DT [정산종료일]
	 *		- field : XCL_STA_DT [정산시작일]
	 *		- field : XCL_END_DT [정산종료일]
	 *		- field : MM_RENTAL_FEE [월렌탈요금]
	 *		- field : CMPT_OUT_DTL_CD [구성품상세코드]
	 *		- field : XCL_CL_CD [정상항목구분코드]
	 * </pre>
	 */
	public IDataSet pInqSaleXclExcelList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    //FM호출
	    responseData = callSharedBizComponentByDirect("pr.PRSXMBase", "fInqSaleXclExcelList", requestData, onlineCtx);
        // 결과값 리턴
        responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
	
	    return responseData;
	}

	/**
	 * <pre>매출정산생성</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-08-07 16:30:31
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : IN_DT [입력년월]
	 *	- field : XCL_GUBUN [정산구분]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */ 
	public IDataSet pSaveSaleXcl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx); 
	    Log logger = LogManager.getFwkLog();
        //FM 호출
	    
	    responseData = callSharedBizComponentByDirect("pr.PRSXMBase", "fInqSaleXclSlipCheck", requestData, onlineCtx);
	    logger.debug("\n\n\n >>>>>>>>>>>> SLIP_ST_CD:"+responseData.getField("SLIP_ST_CD")+"\n\n\n");
	    //String aaa[] = {"111","222"};
	    if(!"00".equals(responseData.getField("SLIP_ST_CD"))&&!"05".equals(responseData.getField("SLIP_ST_CD"))  ){
	    	throw new BizRuntimeException("DMS00150");
	    }
	    	
	    responseData = callSharedBizComponentByDirect("pr.PRSXMBase", "fInqSaleXclCnt", requestData, onlineCtx);

	    if("0".equals(responseData.getField("CNT"))){
		    throw new BizRuntimeException("DMS00092");	    	
	    }
	    
	    // 배치 호출을 위한 파라미터 설정
	    Map inParam = new HashMap();
	    inParam = requestData.getFieldMap();

//	    if( "RENT".equals(inParam.get("XCL_GUBUN") ) )
//	    	inParam.put("TASK_NM", "임대 랜트료매출 정산");
//	    else if( "JD".equals(inParam.get("XCL_GUBUN") ) )
//	    	inParam.put("TASK_NM", "임대 손해배상금매출 정산");
//	    else if( "RC".equals(inParam.get("XCL_GUBUN") ) )
//	    	inParam.put("TASK_NM", "임대 재상품화수수료매출 정산");
//	    else if( "HD".equals(inParam.get("XCL_GUBUN") ) )
//	    	inParam.put("TASK_NM", "임대 운송비매출 정산");
//	    else if( "JN".equals(inParam.get("XCL_GUBUN") ) )
//	    	inParam.put("TASK_NM", "임대 손해배상금미반납매출  정산");
//	    else if( "RR".equals(inParam.get("XCL_GUBUN") ) )
//	    	inParam.put("TASK_NM", "임대 재상품화 구성품매출  정산");
	    
	    inParam.put("TASK_NM", "매출정산등록");
	    inParam.put("TASK_ID", "DBP101");
	    inParam.put("USER_ID", ca.getUserNo());
	    
	    inParam.put("COMPONENTNAME_LOCAL_ONLY", "dms.pr.DBP101");
//	    inParam.put("COMPONENTNAME_LOCAL_ONLY", "dms.pr.BIF001");
	    
	    // 배치 호출. 리턴값은 Job Execution Id. 
	    String jobExeId = callBatchJob("DBP101", inParam, onlineCtx);
//	    log.debug(">>>>>>>>>>>>>>>>> jobExeId: "+jobExeId+ "");
	    waitBatchJobEnd(jobExeId, 9000);
	    int result = getJobReturnCode(jobExeId);
	    
	    logger.debug("\n\n  >>>>>>>>>>>>>>>>> 배치실행 결과 : "+result + "\n\n");
	    if(result == 0){
	    	responseData.setOkResultMessage("DMS00001", null);	
	    }else{
	    	throw new BizRuntimeException("DMS00009"); 
	    }
	    
	    return responseData;
	}
  
}