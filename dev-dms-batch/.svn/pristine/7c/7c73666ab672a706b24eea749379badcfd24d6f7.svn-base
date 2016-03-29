package dms.nr;

import java.util.HashMap;
import java.util.Map;

import nexcore.framework.bat.IBatchContext;

import org.apache.commons.logging.Log;

import bsh.StringUtil;
import dms.utils.SAPUtils;
import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.bat.base.AbsRecordHandler;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.util.DateUtils;

/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>서브 업무명 : DBS004</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-10-27 17:25:57</li>
 * <li>작성자 : 김혁범 (kezmain1)</li>
 * </ul>
 */
public class DBS004 extends AbsBatchComponent {
    private Log log;
    private int processCnt = 0;
    private String taskNo = "";
    private int totCnt = 0;
    private String procFileName = "";
    
    public DBS004() {
        super();
    }

    /**
     * 배치 전처리 메소드. 
     * 여기서 Exception 발생시 execute() 메소드는 실행되지 않고, afterExecute() 는 실행됨
     */
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
        paramMap.put("PROC_DT", context.getInParameter("PROC_DT"));    //처리일
        
        dbSelect("SIFInsuDmislList", paramMap, makeRecordHandler(context), context); //DB 조회
  
        dbUpdate("UIFInsuInvDmislList", paramMap, context);   //인터페이스테이블 처리상태 변경
        
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
                IDataSet reqDs = new DataSet();
                reqDs.putFieldMap(row);
                
                //보증보험번호 구해서  기각테이블에 넣는다.
                IRecord record = dbSelectSingle("SInSUInvInfo", row, context);
                reqDs.putField("SGI_INSU_NO", record.get("SGI_INSU_NO"));                
                dbInsert("IInsuInvDmisl", reqDs.getFieldMap(), context);
                
                //보증보험관리번호로 청구테이블 '기각'상태 업데이트
                dbUpdate("UInsuInv", reqDs.getFieldMap(), context);
                
                //보증보험관리번호로 청구이력테이블 '기각'상태 insert
                
                dbInsert("IInsuInvHst", reqDs.getFieldMap(), context);
                
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
        reqDS.putField("OP_FILE_NM", procFileName);
        reqDS.putField("LS_CHG_USER_ID", "BAT");
        
        if (super.exceptionInExecute == null) {
            // execute() 정상인 경우
            reqDS.putField("BAT_TASK_PROC_ST_CD", "S");
        }else {
            // execute() 에서 에러 발생할 경우
            reqDS.putField("BAT_TASK_PROC_ST_CD", "F");
            processCnt = 0;
        }
        reqDS.putField("PROC_CNT", ""+processCnt);
        IDataSet resDS = callOnlineBizComponent("sc.SCSBase", "fUpdBatTaskOpHst", reqDS, onlineCtx);

        log = getLog(context);
        if(log.isDebugEnabled()) {
            log.debug("보증보험 기각대상 배치결과:");
            log.debug(resDS);
        }
        
    }

}
