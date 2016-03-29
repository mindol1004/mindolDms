package dms.nr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nexcore.framework.bat.IBatchContext;

import org.apache.commons.logging.Log;

import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.bat.base.AbsRecordHandler;
import nexcore.framework.bat.base.AutoCommitRecordHandler;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;
import nexcore.framework.core.util.StringUtils;

/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>서브 업무명 : DBR031</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2016-02-24 18:09:56</li>
 * <li>작성자 : 안진갑 (bella21cjk)</li>
 * </ul>
 */
public class DBR031 extends AbsBatchComponent {
    private Log log;
    
    private int processCnt = 0;
    private String taskNo = "";
    private String procFileName = "";

    private AutoCommitRecordHandler rh = null;
    
    public DBR031() {
        super();
    }

    /**
     * 배치 전처리 메소드. 
     * 여기서 Exception 발생시 execute() 메소드는 실행되지 않고, afterExecute() 는 실행됨
     */
    public void beforeExecute(IBatchContext context) {
		log = getLog(context);
		
		processCnt = 0;
		taskNo = "";
		procFileName = "";
		
		IOnlineContext onlineCtx  = makeOnlineContext(context);
		IDataSet reqDS = new DataSet();
		IDataSet resDS = callOnlineBizComponent("sc.SCSBase", "fInqTaskNoSeq", reqDS, onlineCtx);
		taskNo = resDS.getField("TASK_NO");
		
		reqDS.putField("TASK_DT", DateUtils.getCurrentDate());
        reqDS.putField("TASK_ID", context.getInParameter("TASK_ID"));
        reqDS.putField("TASK_NO", taskNo);
        reqDS.putField("TASK_NM", context.getInParameter("TASK_NM"));
        reqDS.putField("GRP_ID", "EP");
        reqDS.putField("INST_CD", "DMS");
        reqDS.putField("BAT_TASK_PROC_ST_CD", "B");
        reqDS.putField("PROC_CNT", "0");
        reqDS.putField("FS_REG_USER_ID", "BAT");
        reqDS.putField("LS_CHG_USER_ID", "BAT");

        callOnlineBizComponent("sc.SCSBase", "fRegBatTaskOpHst", reqDS, onlineCtx);

        log = getLog(context);
        if(log.isDebugEnabled()) {
            log.debug("공유컴포넌트 호출 결과:");
            log.debug(resDS);
        }
    }

    /**
     * 배치 메인 메소드
     */
    public void execute(final IBatchContext context) {
        txBegin();  
        dbStartSession();
        //dbBeginBatch();
        
        String INV_MOV_REQ_SEQ = null;
        
        /* SQL ParmaMap 설정 */
        HashMap<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("FPA_DSPSL_DT", context.getInParameter("FPA_DSPSL_DT"));
        
        try {
        	// 평균 working-damaged 차액 존재 유무 체크
        	IRecord rc = dbSelectSingle("SBkagAmt", paramMap, context);
        	
        	if( rc.getInt("CNT") > 0 ){
        		throw new BizRuntimeException("DMS00173"); // working-damaged 차액이 존재하지 않은 단말기가 있습니다.
        	}
        	
        	INV_MOV_REQ_SEQ = dbSelectSingle("SInvMovReqSeq", paramMap).get("INV_MOV_REQ_SEQ");        	
        	context.setAttribute("INV_MOV_REQ_SEQ", INV_MOV_REQ_SEQ);
        	
            dbSelect("STargetList", paramMap, makeRecordHandler(context), context);            
            
    	} catch ( BizRuntimeException e ) {
    		throw e;
    	} catch ( Exception e ) {
    		throw new BizRuntimeException("DMS00009", e); //시스템 오류
    	}
        
        // 트랜잭션 커밋
        //dbEndBatch();
        dbEndSession();
        txCommit();
        
        context.setReturnValue("FPA_DSPSL_DT", context.getInParameter("FPA_DSPSL_DT"));
        context.setReturnValue("INV_MOV_REQ_SEQ", INV_MOV_REQ_SEQ);
        context.setReturnValue("SLIP_TYPE", "NR_AE");
        context.setReturnValue("USER_NO", context.getInParameter("USER_NO"));
    }
    
    public AutoCommitRecordHandler makeRecordHandler(IBatchContext context) {

    	rh = new AutoCommitRecordHandler(this, context,  dbNewSession(context, false)) {

            
            @SuppressWarnings("unchecked")
            @Override
			public void handleRecord(IRecord row) {
				context.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시
				context.getLogger().debug("########### : " + row);

	            IDataSet reqDs = new DataSet();
	            IDataSet sendDs = new DataSet();
	            IDataSet rtnDs = null;
	            
				reqDs.putFieldMap(row);
				reqDs.putField("INV_MOV_REQ_SEQ", (String)context.getAttribute("INV_MOV_REQ_SEQ"));
				reqDs.putField("USER_NO",(String)context.getInParameter("USER_NO"));
				
				if( StringUtils.equals(reqDs.getField("MID_TERM_YN"), "Y") || StringUtils.equals(reqDs.getField("FPA_GRADE"), "E") || StringUtils.equals(reqDs.getField("FPA_GRADE"), "A") ){
					reqDs.putField( "EQP_BKAG_AMT", "0" );
				}
				
				if( !StringUtils.equals(reqDs.getField("MID_TERM_YN"), "Y") ){
					if( StringUtils.equals(reqDs.getField("FPA_GRADE"), "A") ){
						reqDs.putField( "SELL_PRC", reqDs.getIntField("SELL_DT_FPA_AMT") );
						reqDs.putField( "INVE_ASMT_DMT_AMT", reqDs.getIntField("PLAN_EXPT_DSPSL_AMT")-reqDs.getIntField("SELL_DT_FPA_AMT"));
					}else if( StringUtils.equals(reqDs.getField("FPA_GRADE"), "B") ){
						reqDs.putField( "SELL_PRC", reqDs.getIntField("SELL_DT_FPA_AMT")-reqDs.getIntField("EQP_BKAG_AMT") );
						reqDs.putField( "INVE_ASMT_DMT_AMT", reqDs.getIntField("PLAN_EXPT_DSPSL_AMT")-(reqDs.getIntField("SELL_DT_FPA_AMT")-reqDs.getIntField("EQP_BKAG_AMT")) );
					}
				}
				
				IOnlineContext onlineCtx  = makeOnlineContext(context); 
				
				sendDs.putField("CNSL_MGMT_NO", reqDs.getField("ASSET_NO"));
				sendDs.putField("PRCH_AMT", reqDs.getField("PRCH_PRC"));
				sendDs.putField("SELL_GRADE", reqDs.getField("FPA_GRADE"));
				sendDs.putField("SELL_PRC", reqDs.getField("SELL_PRC"));
				sendDs.putField("PRCH_CONF_YN", "");
				sendDs.putField("PRCH_CONF_DT", "");
				/*
				try{
					rtnDs = callOnlineBizComponent("ep.EPSFEBase", "fUFPAEqpInfoUpd", sendDs, onlineCtx);
				}catch(Exception e){
				}finally{
					if( StringUtils.isNotEmpty(rtnDs.getField("RESULT")) ){
						dbUpdate("UEqpUsedDspslSt", reqDs.getFieldMap(), context);
						throw new BizRuntimeException("XYZE0000", new String[]{"인터페이스 전송"});
					}
				}
				*/
                dbUpdate("UEqpUsedDspsl", reqDs.getFieldMap(), context);
                dbUpdate("UEqpAsstSt", reqDs.getFieldMap(), context);
                
				processCnt++;				
			}
		};

		rh.setAddBatchMode(false);
        rh.setCommitCount(1);
        rh.setStopWhenException(false);

        return rh;
    }	
    /**
     * 배치 후처리 메소드. 
     * beforeExecute(), execute() 의 Exception 발생 여부와 관계없이 이 메소드는 실행됨
     */
    public void afterExecute(IBatchContext context) {
        IOnlineContext onlineCtx = makeOnlineContext(context);
        
        IDataSet reqDS = new DataSet();
        reqDS.putField("TASK_NO", taskNo);
        reqDS.putField("PROC_CNT", rh.getCalledCount()-rh.getExceptionCount());
        reqDS.putField("OP_FILE_NM", procFileName);
        reqDS.putField("LS_CHG_USER_ID", "BAT");

        if (rh.getExceptionCount() == 0) {
            // execute() 정상인 경우
            reqDS.putField("BAT_TASK_PROC_ST_CD", "S");
        }else {
            // execute() 에서 에러 발생할 경우
            reqDS.putField("BAT_TASK_PROC_ST_CD", "F");
        }
        IDataSet resDS = callOnlineBizComponent("sc.SCSBase", "fUpdBatTaskOpHst", reqDS, onlineCtx);

        log = getLog(context);
        if(log.isDebugEnabled()) {
            log.debug("단말기평가정보등록 BATCH 호출 결과:");
            log.debug(resDS);
        }
    }

}
