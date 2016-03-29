package dms.inf;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import nexcore.framework.bat.IBatchContext;
import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.bat.base.AbsRecordHandler;
import nexcore.framework.bat.util.SAMFileTool;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;
import nexcore.framework.core.util.BaseUtils;

import org.apache.commons.logging.Log;

import fwk.utils.IFDBToFileUtils;

/**
 * <ul>
 * <li>업무 그룹명 : dms/인터페이스</li>
 * <li>서브 업무명 : BIF017</li>
 * <li>설  명 : <pre>[IF]분실습득 전조정 요청</pre></li>
 * <li>작성일 : 2015-08-11 16:03:10</li>
 * <li>작성자 : 안한모 (ahmlee)</li>
 * </ul>
 */
public class IFP017 extends AbsBatchComponent {
    private int processCnt = 0;
    private String taskNo = "";
    private String procFileName = "";
    //20150911
    private String preTaskSTCD = "";     
    
    /**
     * 배치 생성자. 
     * 상위클래스 생성자 호출
     */
    public IFP017() {
        super();
    }

    /**
     * 배치 전처리 메소드. 
     * 여기서 Exception 발생시 execute() 메소드는 실행되지 않고, afterExecute() 는 실행됨
     */
    public void beforeExecute(IBatchContext context) {
        Log log = getLog(context);
        
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
        reqDS.putField("GRP_ID", "PR");
        reqDS.putField("INST_CD", "DMS");
        reqDS.putField("BAT_TASK_PROC_ST_CD", "B");
        reqDS.putField("PROC_CNT", "0");
        reqDS.putField("FS_REG_USER_ID", "BAT");
        reqDS.putField("LS_CHG_USER_ID", "BAT");
        
        callOnlineBizComponent("sc.SCSBase", "fRegBatTaskOpHst", reqDS, onlineCtx);

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
        
        try {
            
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("PROC_DT", context.getInParameter("PROC_DT"));
            paramMap.put("TASK_ID", context.getInParameter("TASK_ID"));
            paramMap.put("FILE_LOC", context.getInParameter("FILE_LOC"));
            paramMap.put("FILE_SEQ", context.getInParameter("FILE_SEQ"));
            
            IFDBToFileUtils dbToFile = new IFDBToFileUtils();
            OutputStream fout = dbToFile.HeaderParse(paramMap);
            
            StringBuffer bf = new StringBuffer();
            bf.append(context.getInParameter("TASK_ID"));
            bf.append(".SKCC.");
            bf.append(context.getInParameter("PROC_DT"));
            procFileName = bf.toString()+".dat"; 
            bf.append("_");
            bf.append(context.getInParameter("FILE_SEQ"));
            String ifFileNmSeq = bf.toString();
            
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("IF_PROC_DT", context.getInParameter("PROC_DT"));
            queryParam.put("IF_FILE_NM", ifFileNmSeq);
            
            
            SAMFileTool body = new SAMFileTool();
            body.setOutputStream(fout);
            // 파일 레이아웃 정의
            body.setEncoding(BaseUtils.getConfiguration("interface.file.encoding"));

            body.addColumnInfo("REC_CL_CD",                 1, SAMFileTool.TYPE_STRING);

            body.addColumnInfo("SVC_MGMT_NO",              10, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("SALE_ITM_CD",               7, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("ADJ_REQS_AMT",             10, SAMFileTool.TYPE_BIGDECIMAL);
            body.addColumnInfo("ADJ_RSN_CD",                2, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("ADJ_DTAIL_RSN_CD",          2, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("FILLER",                   98, SAMFileTool.TYPE_STRING);
            body.initialize();
            
            dbSelect("SLossFndBajtReqsLst", queryParam, makeRecordHandler(body), context);  //DB 조회
            
            //20150911 start
            preTaskSTCD = "S";
            IOnlineContext    onlineCtx  = makeOnlineContext(context);
            IDataSet reqDS = new DataSet();
            reqDS.putField("TASK_ID", "DBP017");
            reqDS.putField("TASK_DT", DateUtils.getCurrentDate());
            if (processCnt == 0) {
                IDataSet resDS = callOnlineBizComponent("sc.SCSBase", "fInqTaskSTCD", reqDS, onlineCtx);
                if ("".equals(resDS.getField("BAT_TASK_PROC_ST_CD"))) preTaskSTCD = "F"; 
                else preTaskSTCD = resDS.getField("BAT_TASK_PROC_ST_CD");
            }
            paramMap.put("PROC_ST_CD", preTaskSTCD);
            //20150911 end            
            
            paramMap.put("TOT_REC_CNT", processCnt+"");
            paramMap.put("FILE_NM_SEQ", ifFileNmSeq);
            dbToFile.tailParse(paramMap, fout);
            
            dbUpdate("ULossFndBajtReqs", queryParam, context);	// 처리상태코드 Update
        
        } catch (Exception e) {
            throw new BizRuntimeException("DMS00009", e);
        }
        
        // 트랜잭션 커밋
        dbEndBatch();
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
        reqDS.putField("PROC_FILE_NM", procFileName);
        reqDS.putField("LS_CHG_USER_ID", "BAT");
        
        if (super.exceptionInExecute == null) {
            // execute() 정상인 경우
            reqDS.putField("BAT_TASK_PROC_ST_CD", "S");
        }else {
            // execute() 에서 에러 발생할 경우
            reqDS.putField("BAT_TASK_PROC_ST_CD", "F");
            processCnt = 0; //20150911
        }
        
        //20150911
        if ("F".equals(preTaskSTCD)) reqDS.putField("BAT_TASK_PROC_ST_CD", "E");
        reqDS.putField("PROC_CNT", ""+processCnt);
        
        IDataSet resDS = callOnlineBizComponent("sc.SCSBase", "fUpdBatTaskOpHst", reqDS, onlineCtx);

        Log log = getLog(context);
        if(log.isDebugEnabled()) {
            log.debug("공유컴포넌트 호출 결과:");
            log.debug(resDS);
        }
    }
    
    /**
     * 레코드 핸들러
     */
    public AbsRecordHandler makeRecordHandler(final SAMFileTool samTool) {
        
        AbsRecordHandler rh = new AbsRecordHandler(batchContext) {
            
            @Override
            public void handleRecord(IRecord arg0) {
                try {
                    // 필요시 데이터 가공...
                    
                    batchContext.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시 설정
                    samTool.writeRecordToOut(arg0); // file write...
                    processCnt++;
                    
                } catch (Exception e) {
                    throw new BizRuntimeException("DMS00009", e); //오류가 발생했습니다.
                }
            }
        };
        
        return rh;
    }
}
