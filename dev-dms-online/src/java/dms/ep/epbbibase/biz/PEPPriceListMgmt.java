package dms.ep.epbbibase.biz;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;

import fwk.common.CommonArea;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.RecordSet;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.log.LogManager;
import nexcore.framework.core.util.StringUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [PU]가격표관리</li>
 * <li>설  명 : <pre>[PU]가격표관리</pre></li>
 * <li>작성일 : 2015-08-19 14:14:45</li>
 * <li>작성자 : 김윤환 (kyh0904)</li>
 * </ul>
 *
 * @author 김윤환 (kyh0904)
 */
public class PEPPriceListMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PEPPriceListMgmt(){
		super();
	}

	/**
	 * <pre>가격표관리목록조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:14:45
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : STA_DT [적용년월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_PRICE_LIST
	 *		- field : ROWNO [순번]
	 *		- field : PRCLST_MGMT_NO [가격표관리번호]
	 *		- field : STA_DT [시작일자]
	 *		- field : PRCLST_QTY [가격표수량]
	 *		- field : RMK [비고]
	 * </pre>
	 */
	public IDataSet pSPriceListMgmtLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		
		try {
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("ep.EPSBIBase", "fSPriceListMgmtLst", requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 3. 결과값 리턴
		responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
		
		return responseData;
	}

	/**
	 * <pre>[PM]가격표오류검사</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:14:45
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_ERR_LIST
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : GRADE_N [등급N]
	 *		- field : GRADE_A [등급A]
	 *		- field : GRADE_B [등급B]
	 *		- field : GRADE_E [등급E]
	 *		- field : ERR_DESC [오류내용]
	 *		- field : STRD_DT [기준일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_RETRUN_LIST
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : GRADE_N [등급N]
	 *		- field : GRADE_A [등급A]
	 *		- field : GRADE_B [등급B]
	 *		- field : GRADE_E [등급E]
	 *		- field : ERR_DESC [오류내용]
	 *		- field : STRD_DT [기준일자]
	 * </pre>
	 */
	public IDataSet pSPriceErrExmn(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IRecordSet rs = requestData.getRecordSet("RS_ERR_LIST");
	    CommonArea ca = getCommonArea(onlineCtx);
	    /*Log logger = LogManager.getFwkLog();*/
	    String dsRegYn ="";
	    IDataSet rowDs = new DataSet();
	    
	    // 오류 검사후 리턴 시켜줄 레코드셋 생성
        IRecordSet rsReturn = new RecordSet("RS_RETRUN_LIST", new String[]{"EQP_MDL_CD", "EQP_MDL_NM", "GRADE_N", "GRADE_A", "GRADE_B", "GRADE_E", "ERR_DESC", "STRD_DT"});
        
	    try {
	        for (int i = 0; i < rs.getRecordCount(); i++) {
	            Map recordMap = rs.getRecordMap(i);
	            rowDs.putFieldMap(rs.getRecordMap(i));  	            
	            dsRegYn = callSharedBizComponentByDirect("ep.EPSBIBase", "fSPriceStrdAmtLst", rowDs, onlineCtx).getField("IN_YN");
	            
	            if("".equals(StringUtils.nvlObject(recordMap.get("EQP_MDL_CD"), ""))){
                    recordMap.put("ERR_DESC", "상품코드가 없습니다.");                                   
                }else if("N".equals(StringUtils.nvlObject(callSharedBizComponentByDirect("ep.EPSBIBase", "fSPriceEqpCdErrExmn", rowDs, onlineCtx).getField("IS_YN"),""))){
                    recordMap.put("ERR_DESC", "유효한 상품코드가 아닙니다.");                     
                }else if(!StringUtils.isNumeric((String) recordMap.get("GRADE_N")) || !StringUtils.isNumeric((String) recordMap.get("GRADE_A")) 
                    || !StringUtils.isNumeric((String) recordMap.get("GRADE_B")) || !StringUtils.isNumeric((String) recordMap.get("GRADE_E"))){
                    recordMap.put("ERR_DESC", "유효한 금액이 아닙니다."); 
                }else if("N".equals(dsRegYn)){
                    recordMap.put("ERR_DESC", "기준일자에 해당하는 가격표가 존재합니다.");
                }else {
                    recordMap.put("ERR_DESC", "");
                }
	            
	            IRecord record = rsReturn.newRecord();
                record.set("EQP_MDL_CD", StringUtils.nvlObject(recordMap.get("EQP_MDL_CD"), ""));
                record.set("EQP_MDL_NM", StringUtils.nvlObject(recordMap.get("EQP_MDL_NM"), ""));
                record.set("GRADE_N", StringUtils.nvlObject(recordMap.get("GRADE_N"), ""));
                record.set("GRADE_A", StringUtils.nvlObject(recordMap.get("GRADE_A"), ""));
                record.set("GRADE_B", StringUtils.nvlObject(recordMap.get("GRADE_B"), ""));
                record.set("GRADE_E", StringUtils.nvlObject(recordMap.get("GRADE_E"), ""));
                record.set("ERR_DESC", StringUtils.nvlObject(recordMap.get("ERR_DESC"), ""));
                record.set("STRD_DT", StringUtils.nvlObject(recordMap.get("STRD_DT"), ""));
                
            }	    	        
	        responseData.putRecordSet("RS_RETRUN_LIST", rsReturn);	        
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 3. 결과값 리턴
		responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
		
	    return responseData;
	}

	/**
	 * <pre>[PM]가격표등록</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:14:45
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_FIX_PRICE_LST
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_MDL_NM [단말기명]
	 *		- field : GRADE_N [등급N]
	 *		- field : GRADE_A [등급A]
	 *		- field : GRADE_B [등급B]
	 *		- field : GRADE_E [등급E]
	 *		- field : ERR_DESC [오류내용]
	 *		- field : STRD_DT [기준일자]
	 *	- record : RS_PRICE_MST_LST
	 *		- field : STRD_DT [기준일자]
	 *		- field : RMK [비고]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pRegPriceList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IDataSet rowDs = new DataSet();
	    IRecordSet rsMst = requestData.getRecordSet("RS_PRICE_MST_LST");
	    IRecordSet rsPrice = requestData.getRecordSet("RS_FIX_PRICE_LST");
	    CommonArea ca = getCommonArea(onlineCtx);	   
	    String prclstMgmtNo ="";   // 가격표관리번호
        int cnt = 0; // 가격표 수량                        
        /* Log logger = LogManager.getFwkLog();*/        
	    try {	    		    		        
	        // 1.가격표관리번호 조회
	        prclstMgmtNo = StringUtils.nvlObject(callSharedBizComponentByDirect("ep.EPSBIBase", "fSPrclstMgmtNo", rowDs, onlineCtx).getField("PRCLST_MGMT_NO"),"");	        
	        // 2.상품가격표 등록
	        if(null != rsPrice && !"".equals(prclstMgmtNo)){
	            for (int i = 0; i < rsPrice.getRecordCount(); i++) {
	                Map recordMap = rsPrice.getRecordMap(i);
                    Map paramMap = new HashMap<String,String>();                    
                    rowDs.putFieldMap(recordMap);
                    
                    // 3.상품가격종료일자 수정
                    callSharedBizComponentByDirect("ep.EPSBIBase", "fUProdPriceTodt", rowDs, onlineCtx);
                    
                    // 4. 상품 가격표 N 등급 데이터 등록                  
                    paramMap.put("EQP_MDL_CD", StringUtils.nvlObject(recordMap.get("EQP_MDL_CD"),""));
                    paramMap.put("GRADE", "N");                 
                    paramMap.put("UPRC", StringUtils.nvlObject(recordMap.get("GRADE_N"),""));
                    paramMap.put("FST_REG_USER_ID", ca.getUserNo());
                    paramMap.put("LAST_CHG_USER_ID", ca.getUserNo());
                    paramMap.put("FRDT", StringUtils.nvlObject(recordMap.get("STRD_DT"),""));
                    paramMap.put("PRCLST_MGMT_NO", prclstMgmtNo);
                    rowDs.putFieldMap(paramMap);
                    
                    callSharedBizComponentByDirect("ep.EPSBIBase", "fIProdPiceListMgmt", rowDs, onlineCtx);
                    
                    // 5. 상품 가격표 A 등급 데이터 등록
                    paramMap.remove("GRADE");
                    paramMap.remove("UPRC");
                    paramMap.put("GRADE", "A");                 
                    paramMap.put("UPRC", StringUtils.nvlObject(recordMap.get("GRADE_A"),""));                   
                    rowDs.putFieldMap(paramMap);
                    
                    callSharedBizComponentByDirect("ep.EPSBIBase", "fIProdPiceListMgmt", rowDs, onlineCtx);
                    
                    // 6. 상품 가격표 B 등급 데이터 등록
                    paramMap.remove("GRADE");
                    paramMap.remove("UPRC");
                    paramMap.put("GRADE", "B");
                    paramMap.put("UPRC", StringUtils.nvlObject(recordMap.get("GRADE_B"),""));                   
                    rowDs.putFieldMap(paramMap);
                    
                    callSharedBizComponentByDirect("ep.EPSBIBase", "fIProdPiceListMgmt", rowDs, onlineCtx);
                    
                    // 7. 상품 가격표 E 등급 데이터 등록
                    paramMap.remove("GRADE");
                    paramMap.remove("UPRC");
                    paramMap.put("GRADE", "E");
                    paramMap.put("UPRC", StringUtils.nvlObject(recordMap.get("GRADE_E"),""));                   
                    rowDs.putFieldMap(paramMap);    
                    
                    callSharedBizComponentByDirect("ep.EPSBIBase", "fIProdPiceListMgmt", rowDs, onlineCtx);
                    
                    cnt++;                    
	            }
	            
	            // 8. 상품가격표관리 등록
                if(null != rsMst && !"".equals(prclstMgmtNo)){
                    for (int i = 0; i < rsMst.getRecordCount(); i++) {
                        Map recordMap = rsMst.getRecordMap(i);
                        recordMap.put("PRCLST_MGMT_NO", prclstMgmtNo);
                        recordMap.put("PRCLST_QTY", cnt);
                        recordMap.put("FST_REG_USER_ID", ca.getUserNo());
                        recordMap.put("LAST_CHG_USER_ID", ca.getUserNo());
                        rowDs.putFieldMap(recordMap);
                        
                        callSharedBizComponentByDirect("ep.EPSBIBase", "fIPiceListMgmt", rowDs, onlineCtx);                        
                    }
                }
	        }	        														
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 2. 결과값 리턴
	    responseData.setOkResultMessage("DMS00000", null); //정상 처리되었습니다.
		
	    return responseData;
	}

	/**
	 * <pre>[PM]상품가격표금액조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:14:45
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : EQP_MDL_CD [단말기모델코드]
	 *	- field : EQP_MDL_NM [단말기모델명]
	 *	- field : PRCLST_MGMT_NO [가격표관리번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_PRICE_AMT_LIST
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : N_GRADE [N등급가격]
	 *		- field : A_GRADE [A등급가격]
	 *		- field : B_GRADE [B등급가격]
	 *		- field : C_GRADE [C등급가격]
	 *		- field : D_GRADE [D등급가격]
	 *		- field : E_GRADE [E등급가격]
	 *		- field : FRDT [시작일자]
	 *		- field : TODT [종료일자]
	 * </pre>
	 */
	public IDataSet pSProdPriceAmtLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		
		try {
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("ep.EPSBIBase", "fSProdPriceAmtLst", requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 3. 결과값 리턴
		responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
		
		return responseData;
	}

	/**
	 * <pre>[PM]상품고정가격관리삭제</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:14:45
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_MNGT_LIST
	 *		- field : PRCLST_MGMT_NO [가격표관리번호]
	 *		- field : STA_DT [기준일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pDProdFixPriceMst(IDataSet requestData, IOnlineContext onlineCtx) {
		/*Log logger = LogManager.getFwkLog();*/
		
	    IDataSet responseData = new DataSet();
	    IRecordSet rsMngt = requestData.getRecordSet("RS_MNGT_LIST");	    
	    
	    try {	    	
	    	requestData.putRecordSet(rsMngt);	// RS_MNGT_LIST 레코드셋을 requestData 담아 FU 전송 후 처리(FU_n건처리)	    	
	    	
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("ep.EPSBIBase", "fDProdFixPriceMst", requestData, onlineCtx);
									
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 2. 결과값 리턴
	    responseData.setOkResultMessage("DMS00000", null); //정상 처리되었습니다.
	
	    return responseData;
	}
  
}
