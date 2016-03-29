package dms.ep.epbsxbase.biz;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.RecordSet;
import nexcore.framework.core.data.xml.DataSetXmlTransformer;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.log.LogManager;
import nexcore.framework.core.util.StringUtils;

import org.apache.commons.logging.Log;

import fwk.common.CommonArea;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [PU]대리점수수료관리</li>
 * <li>설  명 : <pre>[PU]대리점수수료관리</pre></li>
 * <li>작성일 : 2015-10-05 10:35:55</li>
 * <li>작성자 : 김윤환 (kyh0904)</li>
 * </ul>
 *
 * @author 김윤환 (kyh0904)
 */
public class PEPAgnCmmsMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PEPAgnCmmsMgmt(){
		super();
	}

    /**
	 * <pre>[PM]대리점수수료목록조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-22 10:26:46
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : INCEN_STRD_YM [인센티브 기준 년월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_AGN_CMMS_LIST
	 *		- field : ROWNO [순번]
	 *		- field : INCEN_XCL_NO [인센티브 정산 번호]
	 *		- field : INCEN_STRD_YM [인센티브 기준 년월]
	 *		- field : INCEN_BIZ_REG_NO [인센티브 사업자 등록 번호]
	 *		- field : INCEN_CORP_NM [인센티브 법인 명]
	 *		- field : INCEN_AMT [인센티브 금액]
	 *		- field : SPLY_PRC [공급가격]
	 *		- field : SURTAX_AMT [부가세금액]
	 *		- field : INCEN_SLIP_DT [인센티브 전표 일자]
	 *		- field : INCEN_SLIP_NO [인센티브 전표 번호]
	 *		- field : INCEN_SLIP_ST [인센티브 전표 상태]
	 *		- field : SLIP_NO [전표번호]
	 *		- field : SLIP_ST_CD [전표상태코드]
	 *		- field : TAX_BILL_NO [세금계산서번호]
	 *		- field : TAX_BILL_ST_CD [세금계산서상태코드]
	 *		- field : DEALCO_BLICENS_NO [사업자등록번호]
	 *		- field : DEALCO_FISCL_ST_CD [거래처 회계 상태 코드]
	 * </pre>
	 */
	public IDataSet pSAgnCmmsLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
        try {
            // 1. FM 호출            
            responseData.putRecordSet(callSharedBizComponentByDirect("ep.EPSSXBase", "fSAgnCmmsLst", requestData, onlineCtx).getRecordSet("RS_AGN_CMMS_LIST"));  // SKT수수료목록조회          
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
	 * <pre>[PM]대리점수수료등록</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-22 10:26:46
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : INCEN_STRD_YM [정산기준년월]
	 *	- record : RS_AGN_CMMS_LIST
	 *		- field : INCEN_BIZ_REG_NO [인센티브 사업자 등록 번호]
	 *		- field : INCEN_CORP_NM [인센티브 법인 명]
	 *		- field : INCEN_AMT [인센티브 금액]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveAgnCmms(IDataSet requestData, IOnlineContext onlineCtx) {
	    Log logger = LogManager.getFwkLog();	    
	    IDataSet responseData = new DataSet();
        IRecordSet rsList = requestData.getRecordSet("RS_AGN_CMMS_LIST");
        CommonArea ca = getCommonArea(onlineCtx);        
        IRecord rec = null;
        IDataSet rowDs = new DataSet();
        long incenAmt = 0;  // 인센티브 금액
        long splyPrc = 0;   // 공급가액
        long surtaxAmt = 0; // 부가세금액
        try{
            for (int i = 0; i < rsList.getRecordCount(); i++) {
                rec = rsList.getRecord(i);
                rowDs.putFieldMap(rec);
                /* 등록된 인센티브 금액으로 공급가격, 부가세 금액 계산 */
                incenAmt = Long.parseLong(rowDs.getField("INCEN_AMT"));
                splyPrc = Math.round((incenAmt*100)/110);                
                surtaxAmt = incenAmt - splyPrc;
                
                rowDs.putField("USER_ID", ca.getUserNo());
                rowDs.putField("INCEN_STRD_YM",StringUtils.nvlObject(requestData.getField("INCEN_STRD_YM"),""));                                                  
                rowDs.putField("INCEN_AMT",splyPrc+surtaxAmt);    // 인센티브 금액
                rowDs.putField("SPLY_PRC",splyPrc);    // 공급가격
                rowDs.putField("SURTAX_AMT",surtaxAmt);    // 부가세금액
                /*
                if (logger.isDebugEnabled()) {
                    //logger.debug("#### INCEN_STRD_YM #### =======>> "+StringUtils.nvlObject(requestData.getField("INCEN_STRD_YM"),"")+"\n");
                    //logger.debug("#### rowDs.toString #### =======>> "+rowDs.toString()+"\n");
                    logger.debug("#### incenAmt #### =======>> "+incenAmt+"\n");
                    logger.debug("#### splyPrc #### =======>> "+splyPrc+"\n");
                    logger.debug("#### surtaxAmt #### =======>> "+surtaxAmt+"\n");
                }
                */
                callSharedBizComponentByDirect("ep.EPSSXBase", "fIAgnCmms", rowDs, onlineCtx);
            }
                                    
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        
        return responseData;
    }

    /**
	 * <pre>[PM]대리점수수료전표등록</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-22 10:26:46
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : INCEN_STRD_YM [인센티브 기준 년월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveAgnCmmsSlip(IDataSet requestData, IOnlineContext onlineCtx) {
	    Log logger = LogManager.getFwkLog();
        IDataSet responseData = new DataSet();        
        CommonArea ca = getCommonArea(onlineCtx);                  
        String jobExecutionId = ""; // 실행 JOB_ID
        
        try {            
            
            HashMap params = new HashMap<String,String>(); // 배치 파라미터 값 정보.                        
            params.put("SLIP_TYPE", "EP_XA");   // 중고폰_대리점수수료.(공통코드: DMS145)
            params.put("YYYYMM", StringUtils.nvlObject(requestData.getField("INCEN_STRD_YM"),""));   // 년월 (인센티브 기준)
            params.put("YYYY", StringUtils.nvlObject(requestData.getField("INCEN_STRD_YM"),"").substring(0, 4));   // 년 (인센티브 기준)
            params.put("USER_NO", ca.getUserNo());                       
            params.put("TASK_ID", "EPR010");
            params.put("TASK_NM", "전표발행");                
            params.put("COMPONENTNAME_LOCAL_ONLY", "dms.inf.EPR010");
            jobExecutionId = callBatchJob("EPR010", params, onlineCtx);
            
            waitBatchJobEnd(jobExecutionId, 30000);
            
            int result = getJobReturnCode(jobExecutionId);
            
            if(result != 0) throw new BizRuntimeException("XYZE0010"); // 시스템 오류가 발생하였습니다.             
            /*                 
            if (logger.isDebugEnabled()) {                
                logger.debug("#### result #### =======>> "+result);
                logger.debug("#### params.toString #### =======>> "+params.toString());                                
            }
            */
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00167", e); // 시스템 오류가 발생하였습니다.
        }
         
    
        return responseData;
    }

    /**
	 * <pre>[PM]대리점수수료오류검사</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-22 10:26:46
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_AGN_CMMS_LIST
	 *		- field : INCEN_BIZ_REG_NO [인센티브 사업자 등록 번호]
	 *		- field : INCEN_CORP_NM [인센티브 법인 명]
	 *		- field : INCEN_AMT [인센티브 금액]
	 *	- field : INCEN_STRD_YM [정산기준년월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_RETRUN_LIST
	 *		- field : INCEN_BIZ_REG_NO [인센티브 사업자 등록 번호]
	 *		- field : INCEN_CORP_NM [인센티브 법인 명]
	 *		- field : INCEN_AMT [인센티브 금액]
	 *		- field : ERR_DESC [오류검사내용]
	 * </pre>
	 */
	public IDataSet pSAgnCmmsErrExmn(IDataSet requestData, IOnlineContext onlineCtx) {
	    Log logger = LogManager.getFwkLog();        
        IDataSet responseData = new DataSet();
        IRecordSet rsList = requestData.getRecordSet("RS_AGN_CMMS_LIST");
        CommonArea ca = getCommonArea(onlineCtx);        
        IRecord rec = null;
        IDataSet rowDs = new DataSet();
        
        // 오류 검사후 리턴 시켜줄 레코드셋 생성
        IRecordSet rsReturn = new RecordSet("RS_RETRUN_LIST", new String[]{"INCEN_BIZ_REG_NO", "INCEN_CORP_NM", "INCEN_AMT", "ERR_DESC"});
        
        try{            
            
            for (int i = 0; i < rsList.getRecordCount(); i++) {
                rec = rsList.getRecord(i);
                rowDs.putFieldMap(rec);                
                rowDs.putField("INCEN_STRD_YM",StringUtils.nvlObject(requestData.getField("INCEN_STRD_YM"),""));
                
                if("".equals(StringUtils.nvlObject(rowDs.getField("INCEN_BIZ_REG_NO"),""))){
                    rowDs.putField("ERR_DESC","사업자번호가 없습니다.");                    
                }else if("".equals(StringUtils.nvlObject(rowDs.getField("INCEN_AMT"),""))){
                    rowDs.putField("ERR_DESC","금액이 존재하지 않습니다.");                    
                }else if("Y".equals(StringUtils.nvlObject(callSharedBizComponentByDirect("ep.EPSSXBase", "fSAgnRpetErr", rowDs, onlineCtx).getField("RPET_YN"),""))){
                    rowDs.putField("ERR_DESC","정산기준년월에 해당하는 사업자번호가 존재합니다.");
                }else{
                    rowDs.putField("ERR_DESC","");
                }
                
                IRecord record = rsReturn.newRecord();
                record.set("INCEN_BIZ_REG_NO",StringUtils.nvlObject(rowDs.getField("INCEN_BIZ_REG_NO"),""));
                record.set("INCEN_CORP_NM",StringUtils.nvlObject(rowDs.getField("INCEN_CORP_NM"),""));
                record.set("INCEN_AMT",StringUtils.nvlObject(rowDs.getField("INCEN_AMT"),""));
                record.set("ERR_DESC",StringUtils.nvlObject(rowDs.getField("ERR_DESC"),""));                                
            }            
            responseData.putRecordSet("RS_RETRUN_LIST", rsReturn);            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>[PM]대리점수수료전표취소</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-22 10:26:46
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : SLIP_NO [전표번호]
	 *		- field : YYYY [회계연도]
	 *	- field : INCEN_STRD_YM [인센티브 기준 연월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pCnclAgnCmmsSlip(IDataSet requestData, IOnlineContext onlineCtx) {
	    Log logger = LogManager.getFwkLog();
        IDataSet responseData = new DataSet();        
        CommonArea ca = getCommonArea(onlineCtx);                  
        String jobExecutionId = ""; // 실행 JOB_ID
        
        try {            
            ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
            DataSetXmlTransformer.dataSetToXml(requestData, bout, "UTF-8");
            String dsXml = bout.toString("UTF-8");
            
            HashMap params = new HashMap<String,String>(); // 배치 파라미터 값 정보.                        
            params.put("SLIP_TYPE", "EP_XA");   // 중고폰_대리점수수료.(공통코드: DMS145)
            params.put("YYYYMM", StringUtils.nvlObject(requestData.getField("INCEN_STRD_YM"),""));   // 년월 (인센티브 기준)
            params.put("YYYY", StringUtils.nvlObject(requestData.getField("INCEN_STRD_YM"),"").substring(0, 4));   // 년 (인센티브 기준)
            params.put("USER_NO", ca.getUserNo());           
            params.put("TASK_ID", "EPR011");
            params.put("TASK_NM", "전표삭제");                
            params.put("COMPONENTNAME_LOCAL_ONLY", "dms.inf.EPR011");
            params.put("POST_SLIP_DATASET", dsXml);
            
            jobExecutionId = callBatchJob("EPR011", params, onlineCtx);
                        
            waitBatchJobEnd(jobExecutionId, 10000);
            
            int result = getJobReturnCode(jobExecutionId);
            
            if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.             
            /*                 
            if (logger.isDebugEnabled()) {                
                logger.debug("#### result #### =======>> "+result);
                logger.debug("#### params.toString #### =======>> "+params.toString());                                
            }
            */
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
         
    
        return responseData;
    }

    /**
	 * <pre>[PM]대리점수수료전표상태재조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-11-05 17:28:48
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : YYYYMM [연월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pInqAgnCmmsSlipReSearh(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();  
	    Log logger = LogManager.getFwkLog();
        try {            
            if (logger.isDebugEnabled()) { 
                logger.debug("#### YYYYMM #### =======>> "+requestData.getField("YYYYMM"));
                
            }
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSXMBase", "fSlipInfoSynchronization", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-10-22 10:26:46
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : YYYYMM [세금계산서조회년월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pInqAgnCmmsTaxBillSearch(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        try {
               // 1. FM 호출
               responseData = callSharedBizComponentByDirect("ep.EPSSXBase", "fInqAgnCmmsTaxBillSearch", requestData, onlineCtx);
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
	 * <pre>[PM]대리점수수료삭제</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-10-22 10:26:46
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RQ_AGN_CMMS_LIST
	 *		- field : INCEN_XCL_NO [인센티브 정산 번호]
	 *		- field : INCEN_STRD_YM [인센티브 기준 년월]
	 *		- field : INCEN_BIZ_REG_NO [인센티브 사업자 등록 번호]
	 *		- field : INCEN_CORP_NM [인센티브 법인 명]
	 *		- field : INCEN_AMT [인센티브 금액]
	 *		- field : SPLY_PRC [공급가격]
	 *		- field : SURTAX_AMT [부가세금액]
	 *		- field : INCEN_SLIP_DT [인센티브 전표 일자]
	 *		- field : INCEN_SLIP_NO [인센티브 전표 번호]
	 *		- field : INCEN_SLIP_ST [인센티브 전표 상태]
	 *		- field : SLIP_NO [전표번호]
	 *		- field : SLIP_ST_CD [전표상태코드]
	 *		- field : TAX_BILL_NO [세금계산서번호]
	 *		- field : TAX_BILL_ST_CD [세금계산서상태코드]
	 *		- field : DEALCO_BLICENS_NO [사업자등록번호]
	 *		- field : DEALCO_FISCL_ST_CD [거래처 회계 상태 코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pUAgnCmmsDel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSSXBase", "fUAgnCmmsDel", requestData, onlineCtx);
            // 2. 결과값 리턴
            responseData.setOkResultMessage("DMS00000", null); //정상 처리되었습니다.
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }
					
}
