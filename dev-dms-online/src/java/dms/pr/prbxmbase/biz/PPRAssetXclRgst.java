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
 * <li>단위업무명: [PU]재고정산등록</li>
 * <li>설  명 : <pre>[PU]재고정산등록</pre></li>
 * <li>작성일 : 2015-10-05 17:56:53</li>
 * <li>작성자 : 김상오 (myneti99)</li>
 * </ul>
 *
 * @author 김상오 (myneti99)
 */
public class PPRAssetXclRgst extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PPRAssetXclRgst(){
		super();
	}

	/**
	 * <pre>[PM]재고정산대상조회</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-10-08 10:54:43
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
	 *		- field : SLIP_NO [전표번호]
	 *		- field : SLIP_ST_CD [전표상태]
	 * </pre>
	 */
	public IDataSet pInqAssetXclList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	//	    Log logger = LogManager.getFwkLog(); 
	//	    logger.debug("\n\n\n  >>>>>>>>> fieldMap : "+ requestData.getFieldMap());
	    //FM 호출
	    responseData = callSharedBizComponentByDirect("pr.PRSXMBase", "fInqAssetXclList", requestData, onlineCtx);
	    //결과값 리턴
	    responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
	
	    return responseData;
	}

	/**
	 * <pre>[PM]임대감가상각집계생성</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-10-08 10:54:43
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : IN_DT [입력년월]
	 *	- field : XCL_GUBUN [정산구분]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveAssetXcl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    Log logger = LogManager.getFwkLog(); 
	    CommonArea ca = getCommonArea(onlineCtx); 
	    int result = 0;
	    responseData = callSharedBizComponentByDirect("pr.PRSXMBase", "fInqAssetXclSlipCheck", requestData, onlineCtx);
	    logger.debug("\n\n\n >>>>>>>>>>>> SLIP_ST_CD:"+responseData.getField("SLIP_ST_CD")+"\n\n\n");

	    if("AS".equals(requestData.getField("XCL_GUBUN"))){
	    	if("10".equals(responseData.getField("SLIP_ST_CD")) ){
	    		throw new BizRuntimeException("DMS00150");
	    	}
	    }else{
	    	if(!"00".equals(responseData.getField("SLIP_ST_CD"))&&!"05".equals(responseData.getField("SLIP_ST_CD"))  )
	    		throw new BizRuntimeException("DMS00150");
	    }
	    
	    if(!"AC".equals(requestData.getField("XCL_GUBUN"))){
	    	
		    responseData = callSharedBizComponentByDirect("pr.PRSXMBase", "fInqAssetXclCntCheck", requestData, onlineCtx);
		    logger.debug("\n\n\n >>>>>>>>>>>> CNT:"+responseData.getField("CNT")+"\n");
	    	if("0".equals(responseData.getField("CNT"))){
			    throw new BizRuntimeException("DMS00092");	    	
		    }
	    }
	    
	    //대상카운트가 있는지 체크 
	//	    responseData = callSharedBizComponentByDirect("pr.PRSXMBase", "fInqSaleXclCnt", requestData, onlineCtx);
	//	    if("0".equals(responseData.getField("CNT"))){
	//		    throw new BizRuntimeException("DMS00092");	    	
	//	    }
	    
	    

	    // 배치 호출을 위한 파라미터 설정
	    Map inParam = new HashMap();
	    inParam = requestData.getFieldMap();
	    inParam.put("LOGIN_ID", onlineCtx.getUserInfo().getLoginId());
	    inParam.put("USER_NO", ca.getUserNo());
	    if( "AC".equals(inParam.get("XCL_GUBUN") ) ){
	    	//자산감가상각
	    	inParam.put("TASK_NM", "임대감가상각");
	    	inParam.put("TASK_ID", "XCP001");
	    	inParam.put("STD_YM", inParam.get("IN_DT"));
	    	inParam.put("COMPONENTNAME_LOCAL_ONLY", "dms.pr.XCP001");
		    // 배치 호출. 리턴값은 Job Execution Id. 
		    String jobExeId = callBatchJob("XCP001", inParam, onlineCtx);
		    logger.debug(">>>>>>>>>>>>>>>>> jobExeId: "+jobExeId+ "");
		    waitBatchJobEnd(jobExeId, 10000);
		    result = getJobReturnCode(jobExeId);
		    

	    }
	    else if( "AS".equals(inParam.get("XCL_GUBUN") ) ){
	    	inParam.put("TASK_NM", "임대단말매각");
	    	inParam.put("TASK_ID", "XCP004");
	    	inParam.put("COMPONENTNAME_LOCAL_ONLY", "dms.pr.XCP002");
		    // 배치 호출. 리턴값은 Job Execution Id. 
		    String jobExeId = callBatchJob("XCP002", inParam, onlineCtx);
		    logger.debug(">>>>>>>>>>>>>>>>> jobExeId: "+jobExeId+ "");
		    waitBatchJobEnd(jobExeId, 10000);
		    result = getJobReturnCode(jobExeId);
		    
	    	
	    }
	    else if( "AD".equals(inParam.get("XCL_GUBUN") ) ){
	    	inParam.put("TASK_NM", "임대단말제각");
	    	inParam.put("TASK_ID", "XCP003");
	    	inParam.put("COMPONENTNAME_LOCAL_ONLY", "dms.pr.XCP003");
	    	// 배치 호출. 리턴값은 Job Execution Id. 
		    String jobExeId = callBatchJob("XCP003", inParam, onlineCtx);
		    logger.debug(">>>>>>>>>>>>>>>>> jobExeId: "+jobExeId+ "");
		    waitBatchJobEnd(jobExeId, 10000);
		    result = getJobReturnCode(jobExeId);
	    }
	    
	    
	    logger.debug("\n\n  >>>>>>>>>>>>>>>>> 배치실행 결과 : "+result + "\n\n");
	    if(result == 0){
	    	responseData.setOkResultMessage("DMS00001", null);	
	    }else{
	    	throw new BizRuntimeException("DMS00009"); 
	    }
	    
	    return responseData;
	}

	/**
	 * <pre>[PM]재고정산등록엑셀리스트</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-10-08 10:54:43
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
	 *		- field : EQP_PRCH_DT [매입일자]
	 *		- field : RENT_STA_DT [대여시작일]
	 *		- field : RENT_END_DT [대여종료일]
	 *		- field : STA_DT [정산시작일]
	 *		- field : END_DT [정산종료일]
	 *		- field : XCL_STA_DT [정산시작일]
	 *		- field : XCL_END_DT [정산종료일]
	 *		- field : MM_RENTAL_FEE [월렌탈요금]
	 *		- field : CMPT_OUT_DTL_CD [구성품상세코드]
	 *		- field : XCL_CL_CD [정상항목구분코드]
	 *		- field : SLIP_NO [전표번호]
	 *		- field : SLIP_ST_CD [전표상태]
	 *		- field : SLIP_DT [전표일자]
	 *		- field : DEPR_DTL_SVCL [감가상각상세내용연수]
	 *		- field : DEPR_OBJ_AMT [감가상각대상금액]
	 *		- field : DEPR_DTL_REM_AMT [감가상각상세잔여금액]
	 *		- field : DEPR_DTL_AMT [감가상각상세금액]
	 *		- field : DEPR_DTL_SUM_AMT [감가상각상세누계금액]
	 *		- field : DEPR_DTL_CROVR_SUM_AMT [감가상각상세이월누계금액]
	 *		- field : DEPR_DTL_ACNTB_AMT [감가상각상세장부금액]
	 *		- field : DEPR_DTL_TS [감가상각상세차수]
	 * </pre>
	 */
	public IDataSet pInqAssetXclExcelList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	//	    Log logger = LogManager.getFwkLog(); 
	//	    logger.debug("\n\n\n  >>>>>>>>> fieldMap : "+ requestData.getFieldMap());
	    //FM 호출
	    responseData = callSharedBizComponentByDirect("pr.PRSXMBase", "fInqAssetXclExcelList", requestData, onlineCtx);
	    //결과값 리턴
	    responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
	
	    return responseData;
	}
  
}