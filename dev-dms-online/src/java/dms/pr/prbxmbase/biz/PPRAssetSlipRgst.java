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
 * <li>단위업무명: [PU]재고정산전표등록</li>
 * <li>설  명 : <pre>[PU]재고정산전표등록</pre></li>
 * <li>작성일 : 2015-10-05 17:56:53</li>
 * <li>작성자 : 김상오 (myneti99)</li>
 * </ul>
 *
 * @author 김상오 (myneti99)
 */
public class PPRAssetSlipRgst extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PPRAssetSlipRgst(){
		super();
	}

	/**
	 * <pre>[PM]재고정산전표대상조회</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-10-05 17:56:53
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
	 *		- field : ASSET_DISPO_ACNTB_AMT [자산처분장부금액]
	 *		- field : ASSET_DISPO_REM_AMT [자산처분잔여금액]
	 *		- field : DISPO_PRE_PRFITLS_AMT [처분선손익금액]
	 *		- field : DISPO_AMT [처분금액]
	 *		- field : DISPO_PRFITLS_AMT [처분손익금액]
	 * </pre>
	 */
	public IDataSet pInqAssetSlipList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
//	    Log logger = LogManager.getFwkLog(); 
//	    logger.debug("\n\n\n  >>>>>>>>> fieldMap : "+ requestData.getFieldMap());
	    //FM 호출
	    responseData = callSharedBizComponentByDirect("pr.PRSXMBase", "fInqAssetSlipList", requestData, onlineCtx);
        //결과값 리턴
        responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
	
	    return responseData;
	}

	/**
	 * <pre>[PM]재고정산등록엑셀리스트</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-10-05 17:56:53
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : IN_DT [검색년월]
	 *	- field : XCL_GUBUN [매출구분항목]
	 *	- field : PRCHCO_CD [거래처코드]
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
	 *		- field : EQP_PRCH_DT [매입일자]
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
	 *		- field : SLIP_NO [전표번호]
	 *		- field : SLIP_ST_CD [전표상태]
	 *		- field : ASSET_DISPO_ACNTB_AMT [자산처분장부금액]
	 *		- field : ASSET_DISPO_REM_AMT [자산처분잔여금액]
	 *		- field : DISPO_PRE_PRFITLS_AMT [처분선손익금액]
	 *		- field : DISPO_AMT [처분금액]
	 *		- field : DISPO_PRFITLS_AMT [처분손익금액]
	 *		- field : SLIP_DT [전표일자]
	 *		- field : PRCHCO_NM [거래처명]
	 *		- field : DEALCO_BLICENS_NO [사업자번호]
	 *		- field : PRCHCO_CD [사업자코드]
	 *		- field : SPLY_PRC [공급가]
	 *		- field : SURTAX_AMT [부가세]
	 * </pre>
	 */
	public IDataSet pInqAssetExcelList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
//	    Log logger = LogManager.getFwkLog(); 
//	    logger.debug("\n\n\n  >>>>>>>>> fieldMap : "+ requestData.getFieldMap());
	    //FM 호출
	    responseData = callSharedBizComponentByDirect("pr.PRSXMBase", "fInqAssetSlipExcelList", requestData, onlineCtx);
        //결과값 리턴
        responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
	
	    return responseData;
	}

	/**
	 * <pre>[PM]자산전표발행</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-10-05 17:56:53
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : IN_DT [검색년월]
	 *	- field : XCL_GUBUN [정산구분코드]
	 *	- field : XCL_NM [정산항목명]
	 *	- field : TOT_CNT [대상건수]
	 *	- field : TOT_AMT [대상금액합계]
	 *	- field : SLIP_TYPE [전표구분]
	 *	- field : ASSET_DISPO_ACNTB_AMT [자산처분장부금액]
	 *	- field : ASSET_DISPO_REM_AMT [자산처분잔여금액]
	 *	- field : DISPO_PRE_PRFITLS_AMT [처분선손익금액]
	 *	- field : DISPO_AMT [처분금액]
	 *	- field : DISPO_PRFITLS_AMT [처분손익금액]
	 *	- field : SPLY_PRC [공급가]
	 *	- field : SURTAX_AMT [부가세]
	 *	- field : DEALCO_BLICENS_NO [사업자번호]
	 *	- field : PRCHCO_CD [매입처코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveAssetXclSlipNo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
	
	    Log logger = LogManager.getFwkLog(); 
	    logger.debug("\n\n\n  >>>>>>>>> fieldMap : "+ requestData.getFieldMap());
	    CommonArea ca = getCommonArea(onlineCtx); 
        // 2. FM 호출
	    // 배치 호출을 위한 파라미터 설정
	    Map inParam = new HashMap();
	    inParam = requestData.getFieldMap();

	    inParam.put("SLIP_TYPE", "PR_"+inParam.get("XCL_GUBUN"));
	    
	    inParam.put("TASK_NM", "전표발행");
	    inParam.put("TASK_ID", "EPR010");
	    inParam.put("DEALCO_BLICENS_NO", "1048137225");
	    inParam.put("SALE_DEALCO_CD", "0000000000");
	    inParam.put("XCL_DEALCO_CD", "0000000000");
	    
	    if("AP".equals(inParam.get("XCL_GUBUN") )){
	    	inParam.put("DEALCO_BLICENS_NO", requestData.getField("DEALCO_BLICENS_NO"));
	    	inParam.put("XCL_DEALCO_CD", requestData.getField("PRCHCO_CD"));
	    	inParam.put("PRCHCO_CD", requestData.getField("PRCHCO_CD"));
	    }
	    requestData.putFieldMap(inParam);
	    //거래처가 이알피에 존재하는지 확인
	    IDataSet delcoDs = callSharedBizComponentByDirect("nr.NRSXMBase", "fInqVendorAtERP", requestData, onlineCtx);
	    
	    if(!"S".equals(delcoDs.getField("ERP_MSG_CD"))){
	    	throw new BizRuntimeException("DMS00166");
	    }
	    inParam.put("USER_NO", ca.getUserNo()); 
	    inParam.put("COMPONENTNAME_LOCAL_ONLY", "dms.inf.EPR010");
	    
	    logger.debug("\n\n\n  >>>>>>>>> inParam : "+ inParam);
	    // 배치 호출. 리턴값은 Job Execution Id. 
	    String jobExeId = callBatchJob("EPR010", inParam, onlineCtx);
	    
	    waitBatchJobEnd(jobExeId, 9000);
	    int result = getJobReturnCode(jobExeId);
	    
	    if(result == 0){
	    	responseData.setOkResultMessage("DMS00001", null);	
	    }else{
	    	throw new BizRuntimeException("DMS00009"); 
	    }
	    
	    
	    return responseData;
	
	}

	/**
	 * <pre>전표상태재조회</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-10-05 17:56:53
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : YYYYMM [전표재조회년월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pReStateCall(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();

		try {
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("nr.NRSXMBase", "fSlipInfoSynchronization", requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}

		return responseData;}

	/**
	 * <pre>[PM]자산매입전표발행리스트</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-10-05 17:56:53
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
	 *		- field : ASSET_DISPO_ACNTB_AMT [자산처분장부금액]
	 *		- field : ASSET_DISPO_REM_AMT [자산처분잔여금액]
	 *		- field : DISPO_PRE_PRFITLS_AMT [처분선손익금액]
	 *		- field : DISPO_AMT [처분금액]
	 *		- field : DISPO_PRFITLS_AMT [처분손익금액]
	 *		- field : PRCHCO_NM [거래처명]
	 *		- field : DEALCO_BLICENS_NO [거래처사업자번호]
	 *		- field : PRCHCO_CD [거래처코드]
	 *		- field : SPLY_PRC [공급가]
	 *		- field : SURTAX_AMT [부가세]
	 * </pre>
	 */
	public IDataSet pInqAssetPrchSlipList(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();

		try {
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("pr.PRSXMBase", "fInqAssetPrchSlipList", requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}

		return responseData;}
  
}
