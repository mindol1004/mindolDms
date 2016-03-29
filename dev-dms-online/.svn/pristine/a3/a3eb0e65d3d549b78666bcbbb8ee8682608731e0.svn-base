package dms.ep.epsbibase.biz;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;

import fwk.common.CommonArea;
import fwk.utils.PagenateUtils;
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
 * <li>단위업무명: [FU]가격표관리</li>
 * <li>설  명 : <pre>[FU]가격표관리</pre></li>
 * <li>작성일 : 2015-08-19 14:15:08</li>
 * <li>작성자 : 김윤환 (kyh0904)</li>
 * </ul>
 *
 * @author 김윤환 (kyh0904)
 */
public class FEPPriceListMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FEPPriceListMgmt(){
		super();
	}

	/**
	 * <pre>가격표관리목록조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:15:08
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
	public IDataSet fSPriceListMgmtLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
	    IDataSet dsCnt = new DataSet();
		IRecordSet rsPagingList = null;
		int intTotalCnt = 0;

		try {
			// 1. DU lookup
			DEPPriceListMgmt dEPPriceListMgmt =  (DEPPriceListMgmt) lookupDataUnit(DEPPriceListMgmt.class);
			// 2. TOTAL COUNT DM호출
			dsCnt = dEPPriceListMgmt.dSPriceListMgmtTotCnt(requestData, onlineCtx);
			intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));			
			PagenateUtils.setPagenatedParamsToDataSet(requestData);
			
			// 3. LISTDM호출
			responseData = dEPPriceListMgmt.dSPriceListMgmtPaging(requestData, onlineCtx);
			
			rsPagingList = responseData.getRecordSet("RS_PRICE_LIST");			
			PagenateUtils.setPagenatedParamToRecordSet(rsPagingList, requestData, intTotalCnt);			
		} catch ( BizRuntimeException e ) {
			throw e;
		}
	
	    return responseData;
	}

	/**
	 * <pre>[FM]상품가격표금액조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:15:08
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
	public IDataSet fSProdPriceAmtLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
			// 1. DU lookup
			DEPPriceListMgmt dEPPriceListMgmt =  (DEPPriceListMgmt) lookupDataUnit(DEPPriceListMgmt.class);						
			// 2. LIST 호출
			responseData = dEPPriceListMgmt.dSProdPriceAmtLst(requestData, onlineCtx);								
		} catch ( BizRuntimeException e ) {
			throw e;
		}
	
	    return responseData;
	}

	/**
	 * <pre>[FM]상품고정가격관리삭제</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:15:08
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
	public IDataSet fDProdFixPriceMst(IDataSet requestData, IOnlineContext onlineCtx) {
		/*Log logger = LogManager.getFwkLog();*/
	    IDataSet responseData = new DataSet();
	    IRecordSet rs = requestData.getRecordSet("RS_MNGT_LIST");
	    CommonArea ca = getCommonArea(onlineCtx);	    
	    
	    IDataSet rowDs = new DataSet();
	    
	    try {
			// 1. DU lookup
			DEPPriceListMgmt dEPPriceListMgmt =  (DEPPriceListMgmt) lookupDataUnit(DEPPriceListMgmt.class);		
			responseData = dEPPriceListMgmt.dSProdPriceAmtLst(requestData, onlineCtx);			
			// 2. 상품가격관리 및 상품가격 삭제
			if(null != rs){
				for (int i = 0; i < rs.getRecordCount(); i++) {
					Map recordMap = rs.getRecordMap(i);
					recordMap.put("LAST_CHG_USER_ID", ca.getUserNo());	
					/*
					if (logger.isDebugEnabled()) {
						logger.debug("** 상품가격관리 정보 ==>> " + recordMap.toString());
					}
					*/																	
					rowDs.putFieldMap(recordMap);
					dEPPriceListMgmt.dDProdFixPriceMst(rowDs, onlineCtx);	// 상품가격관리 정보 삭제
					dEPPriceListMgmt.dDProdFixPrice(rowDs, onlineCtx);	// 상품가격정보 삭제													
				}
			}			
			rs = responseData.getRecordSet("RS_PRICE_AMT_LIST");	// 상품가격정보 조회
			if(null != rs){
				for (int i = 0; i < rs.getRecordCount(); i++) {
					Map recordMap = rs.getRecordMap(i);
					recordMap.put("LAST_CHG_USER_ID", ca.getUserNo());
					recordMap.put("STA_DT", StringUtils.nvlObject(requestData.getRecordSet("RS_MNGT_LIST").get(0, "STA_DT"),""));
					/*
					if (logger.isDebugEnabled()) {
						logger.debug("** 상품가격 정보 ==>> " + recordMap.toString());
					}
					*/							
					rowDs.putFieldMap(recordMap);
					dEPPriceListMgmt.dUProdFixPriceTodt(rowDs, onlineCtx);
				}
			}				
			
	    } catch ( BizRuntimeException e ) {
			throw e;
		}
	
	    return responseData;
	}

    /**
	 * <pre>[FM]가격표기준금액조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:15:08
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : EQP_MDL_CD [단말기모델코드]
	 *	- field : EQP_MDL_NM [단말기모델명]
	 *	- field : GRADE_N [등급N]
	 *	- field : GRADE_A [등급A]
	 *	- field : GRADE_B [등급B]
	 *	- field : GRADE_E [등급E]
	 *	- field : ERR_DESC [오류내용]
	 *	- field : STRD_DT [기준일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : IN_YN [가격존재여부]
	 * </pre>
	 */
	public IDataSet fSPriceStrdAmtLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. DU lookup
            DEPPriceListMgmt dEPPriceListMgmt =  (DEPPriceListMgmt) lookupDataUnit(DEPPriceListMgmt.class);     
            responseData = dEPPriceListMgmt.dSPriceStrdAmtLst(requestData, onlineCtx);                          
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } 
        
    
        return responseData;
    }

    /**
	 * <pre>[FM]가격표상품코드오류검사</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:15:08
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : EQP_MDL_CD [단말기모델코드]
	 *	- field : EQP_MDL_NM [단말기모델명]
	 *	- field : GRADE_N [등급N]
	 *	- field : GRADE_A [등급A]
	 *	- field : GRADE_B [등급B]
	 *	- field : GRADE_E [등급E]
	 *	- field : ERR_DESC [오류내용]
	 *	- field : STRD_DT [기준일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : IS_YN [단말기존재여부]
	 * </pre>
	 */
	public IDataSet fSPriceEqpCdErrExmn(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. DU lookup
            DEPPriceListMgmt dEPPriceListMgmt =  (DEPPriceListMgmt) lookupDataUnit(DEPPriceListMgmt.class);     
            responseData = dEPPriceListMgmt.dSPriceEqpCdErrExmn(requestData, onlineCtx);  
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } 
    
        return responseData;
    }

    /**
	 * <pre>[FM]가격표관리번호조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:15:08
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : EQP_MDL_CD [단말기모델코드]
	 *	- field : EQP_MDL_NM [단말기모델명]
	 *	- field : GRADE_N [등급N]
	 *	- field : GRADE_A [등급A]
	 *	- field : GRADE_B [등급B]
	 *	- field : GRADE_E [등급E]
	 *	- field : ERR_DESC [오류내용]
	 *	- field : STRD_DT [기준일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : PRCLST_MGMT_NO [가격표관리번호]
	 * </pre>
	 */
	public IDataSet fSPrclstMgmtNo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. DU lookup
            DEPPriceListMgmt dEPPriceListMgmt =  (DEPPriceListMgmt) lookupDataUnit(DEPPriceListMgmt.class);     
            responseData = dEPPriceListMgmt.dSPrclstMgmtNo(requestData, onlineCtx);  
            
        } catch ( BizRuntimeException e ) {
            throw e;
        }  
    
        return responseData;
    }

    /**
	 * <pre>[FM]상품가격종료일자수정</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:15:08
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : EQP_MDL_CD [단말기모델코드]
	 *	- field : EQP_MDL_NM [단말기모델명]
	 *	- field : GRADE_N [등급N]
	 *	- field : GRADE_A [등급A]
	 *	- field : GRADE_B [등급B]
	 *	- field : GRADE_E [등급E]
	 *	- field : ERR_DESC [오류내용]
	 *	- field : STRD_DT [기준일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUProdPriceTodt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. DU lookup
            DEPPriceListMgmt dEPPriceListMgmt =  (DEPPriceListMgmt) lookupDataUnit(DEPPriceListMgmt.class);     
            responseData = dEPPriceListMgmt.dUProdPriceTodt(requestData, onlineCtx);  
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } 
    
        return responseData;
    }

    /**
	 * <pre>[FM]상품가격표등록</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:15:08
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : EQP_MDL_CD [단말기모델코드]
	 *	- field : EQP_MDL_NM [단말기모델명]
	 *	- field : GRADE_N [등급N]
	 *	- field : GRADE_A [등급A]
	 *	- field : GRADE_B [등급B]
	 *	- field : GRADE_E [등급E]
	 *	- field : ERR_DESC [오류내용]
	 *	- field : STRD_DT [기준일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fIProdPiceListMgmt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. DU lookup
            DEPPriceListMgmt dEPPriceListMgmt =  (DEPPriceListMgmt) lookupDataUnit(DEPPriceListMgmt.class);     
            responseData = dEPPriceListMgmt.dIProdPiceListMgmt(requestData, onlineCtx);  
            
        } catch ( BizRuntimeException e ) {
            throw e;
        }  
    
        return responseData;
    }

    /**
	 * <pre>[FM]가격표관리등록</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 14:15:08
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : EQP_MDL_CD [단말기모델코드]
	 *	- field : EQP_MDL_NM [단말기모델명]
	 *	- field : GRADE_N [등급N]
	 *	- field : GRADE_A [등급A]
	 *	- field : GRADE_B [등급B]
	 *	- field : GRADE_E [등급E]
	 *	- field : ERR_DESC [오류내용]
	 *	- field : STRD_DT [기준일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fIPiceListMgmt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. DU lookup
            DEPPriceListMgmt dEPPriceListMgmt =  (DEPPriceListMgmt) lookupDataUnit(DEPPriceListMgmt.class);     
            responseData = dEPPriceListMgmt.dIPiceListMgmt(requestData, onlineCtx);  
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } 
    
        return responseData;
    }
  
}
