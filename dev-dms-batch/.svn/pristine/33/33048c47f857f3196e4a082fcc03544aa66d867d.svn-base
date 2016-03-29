package dms.nr;

import java.util.HashMap;
import java.util.Map;

import nexcore.framework.bat.IBatchContext;
import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.bat.base.AbsRecordHandler;
import nexcore.framework.bat.base.AutoCommitRecordHandler;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>서브 업무명 : BNR005</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-05 09:42:37</li>
 * <li>작성자 : 박홍서 (uni9401)</li>
 * </ul>
 */
public class DBT001 extends AbsBatchComponent {
    private Log log;
    
    private int processCnt = 0;
    private String taskNo = "";
    private String procFileName = "";
    @SuppressWarnings("unused")
	private int totCnt = 0;
    private AutoCommitRecordHandler rh = null;
    /**
     * 배치 생성자. 
     * 상위클래스 생성자 호출
     */
    public DBT001() {
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
        //dbBeginBatch();
        
        try {
        	Map<String, String> paramMap = new HashMap<String, String>();
        	paramMap.put("PROC_DT", context.getInParameter("PROC_DT"));    //처리일
            
        	dbSelect("SSKNInfo", paramMap, makeRecordHandler(context), context); //단말기회수정보IF조회
        	
        } catch (Exception e) {
            throw new BizRuntimeException("XYZE0000", new String[]{"SKN여신 인터페이스"}, e);
        }
        
        // 트랜잭션 커밋
        //dbEndBatch();
        dbEndSession();
        txCommit();
    }
    
    /**
     * 배치 후처리 메소드. 
     * beforeExecute(), execute() 의 Exception 발생 여부와 관계없이 이 메소드는 실행됨
     */
    public void afterExecute(IBatchContext context) {
    	IOnlineContext    onlineCtx  = makeOnlineContext(context);
        
    	IDataSet reqDS = new DataSet();
        reqDS.putField("TASK_NO", taskNo);
        reqDS.putField("PROC_CNT",rh.getCalledCount()-rh.getExceptionCount());
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

        Log log = getLog(context);
        if(log.isDebugEnabled()) {
            log.debug("공유컴포넌트 호출 결과:");
            log.debug(resDS);
        }
    }
    /**
     * SKN여신정보조회후 건건히 처리
     * 
     */
    public AbsRecordHandler makeRecordHandler(IBatchContext context) {
    	rh = new AutoCommitRecordHandler(this, context,  dbNewSession(context, false)) {
			
			@SuppressWarnings("unchecked")
			@Override
			public void handleRecord(IRecord row) {
				context.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시
				context.getLogger().debug("########### : " + row);
				
				IDataSet reqDs = new DataSet();
				reqDs.putFieldMap(row);
				
				dbInsert("ISKNInfo", reqDs.getFieldMap(), context); //SKN 여신등록
				dbUpdate("USKNInfo", reqDs.getFieldMap(), context); //IF테이블 상태 UPDATE
				processCnt++;
			}
			
		};
		rh.setAddBatchMode(false);
        rh.setCommitCount(1);
        rh.setStopWhenException(false);
    	return rh;

    }    
    
    
    /**
     * 단말기모델IF조회 후 레코드 단위로 단말기모델IF PROC_ST_CD 업데이트
     * 
     */
//    public AbsRecordHandler makeRecordHandlerDelete(IBatchContext context) {
//        AbsRecordHandler rh = new AbsRecordHandler(context) {
//            
//            @Override
//            public void handleRecord(IRecord row) {
//                try {
//                    dbDelete("DSKNInfo", row, context);
//                } catch (Exception e) {
//                    throw new BizRuntimeException("XYZE0000", new String[]{"SKN여신 인터페이스"}, e);
//                }
//            }
//        };
//        return rh;
//    }

}
