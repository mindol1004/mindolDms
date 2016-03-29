package dms.nr;

import java.util.HashMap;

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
 * <li>서브 업무명 : DBR019</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2016-02-15 15:34:14</li>
 * <li>작성자 : 안진갑 (bella21cjk)</li>
 * </ul>
 */
public class DBR019 extends AbsBatchComponent {
    private Log log;
    
    private int processCnt = 0;
    private String taskNo = "";
    private String procFileName = "";
    
    public DBR019() {
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
        
        IOnlineContext onlineCtx = makeOnlineContext(context);
        
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
       	// 트랜잭션 시작
    	txBegin();  
    	dbStartSession();
    	dbBeginBatch();
    	
    	/* SQL ParmaMap 설정 */
    	HashMap<String, Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("PROC_DT", context.getInParameter("PROC_DT"));
    	
    	dbSelect("SInsuInvRslt", paramMap, makeRecordHandler(context), context); //해지요청
        	
		// 트랜잭션 커밋
		dbEndBatch();
		dbEndSession();
		txCommit(); 
    }
    
    public AbsRecordHandler makeRecordHandler(IBatchContext context) {
    	
    	AbsRecordHandler rh = new AbsRecordHandler(context) {
    		
    		int ifSeq = 1;
    		String ifFileNm = "R019.SKCC."+context.getInParameter("PROC_DT")+"_"+context.getInParameter("FILE_SEQ");	
    		IDataSet reqDs = new DataSet();
    		
			public void handleRecord(IRecord row) {
							
				context.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시
															
				/* 단말기구성품등록 I/F 데이터 가공 */                
                reqDs.putFieldMap(row);
                reqDs.putField("IF_PROC_DT", context.getInParameter("PROC_DT"));	// IF_처리일자
                reqDs.putField("IF_FILE_NM", ifFileNm);	// IF_파일명
                reqDs.putField("IF_SEQ", ifSeq++);	// IF_순번
							
				dbInsert("IInsuInvRslt", reqDs.getFieldMap(), context);
					
				
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
            log.debug("공유컴포넌트 호출 결과:");
            log.debug(resDS);
        }
    }

}
