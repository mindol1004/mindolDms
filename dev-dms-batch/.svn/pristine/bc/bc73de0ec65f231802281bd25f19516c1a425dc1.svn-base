package dms.nr;

import java.util.HashMap;
import java.util.Map;

import nexcore.framework.bat.IBatchContext;

import org.apache.commons.logging.Log;

import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.bat.base.AbsRecordHandler;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.util.DateUtils;

/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>서브 업무명 : DBR030</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2016-01-06 17:21:15</li>
 * <li>작성자 : 안진갑 (bella21cjk)</li>
 * </ul>
 */
public class DBR030 extends AbsBatchComponent {
    private Log log;
    private int processCnt = 0;
    private String taskNo = "";
    private int totCnt = 0;
    private String procFileName = "";
    
    public DBR030() {
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
        totCnt = 0; 
        procFileName = "";
        
        IOnlineContext    onlineCtx  = makeOnlineContext(context);
        IDataSet reqDS = new DataSet();
        IDataSet resDS = callOnlineBizComponent("sc.SCSBase", "fInqTaskNoSeq", reqDS, onlineCtx);
        taskNo = resDS.getField("TASK_NO");
        
        reqDS.putField("TASK_DT", DateUtils.getCurrentDate());
        reqDS.putField("TASK_ID", context.getInParameter("TASK_ID"));
        reqDS.putField("TASK_NO", taskNo);
        reqDS.putField("TASK_NM", context.getInParameter("TASK_NM"));
        reqDS.putField("GRP_ID", "NR");
        reqDS.putField("INST_CD", "DMS");
        reqDS.putField("BAT_TASK_PROC_ST_CD", "B");
        reqDS.putField("PROC_CNT", "0");
        reqDS.putField("FS_REG_USER_ID", "BAT");
        reqDS.putField("LS_CHG_USER_ID", "BAT");
        
        IDataSet resDS2 = callOnlineBizComponent("sc.SCSBase", "fRegBatTaskOpHst", reqDS, onlineCtx);

        Log log = getLog(context);
        if(log.isDebugEnabled()) {
            log.debug("공유컴포넌트 호출 결과:");
            log.debug(resDS);
        }
    }

    /**
     * 배치 메인 메소드
     */
    public void execute(final IBatchContext context) {
        // 트랜잭션 시작
        txBegin();  
        dbStartSession();
        dbBeginBatch();
        
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("FILE_DT", context.getInParameter("FILE_DT"));    //처리일
        
        dbDelete("DEqpInsuCmms", paramMap, context); //전표처리 안된 데이타 삭제
        dbSelect("SEqpInsuCmms", paramMap, makeRecordHandler(context), context); //DB 조회        

        // 트랜잭션 커밋      
        dbEndBatch();
        dbEndSession();
        txCommit();        
    }
    
    public AbsRecordHandler makeRecordHandler(IBatchContext context) {
        AbsRecordHandler rh = new AbsRecordHandler(context) {
            
            @SuppressWarnings("unchecked")
            @Override
            public void handleRecord(IRecord row) {
                context.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시
                context.getLogger().debug("########### : " + row);
               
                dbUpdate("IEqpInsuCmms", row, context);
                processCnt++;
            }              
        };
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
        reqDS.putField("PROC_CNT", ""+processCnt);
        reqDS.putField("OP_FILE_NM", procFileName);
        reqDS.putField("LS_CHG_USER_ID", "BAT");
         
        if (super.exceptionInExecute == null) {
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
