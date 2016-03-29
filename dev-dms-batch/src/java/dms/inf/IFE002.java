package dms.inf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nexcore.framework.bat.IBatchContext;
import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.bat.base.AbsRecordHandler;
import nexcore.framework.bat.base.RecordHandlerExecutor;
import nexcore.framework.bat.util.SAMFileTool;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;
import nexcore.framework.core.util.BaseUtils;

import org.apache.commons.logging.Log;

import fwk.utils.HpcUtils;
import fwk.utils.IFFileToDBUtils;

/**
 * <ul>
 * <li>업무 그룹명 : DMS-IF/인터페이스</li>
 * <li>서브 업무명 : BIF006</li>
 * <li>설  명 : <pre>[IF]상담관리일반상세</pre></li>
 * <li>작성일 : 2015-08-07 16:40:57</li>
 * <li>작성자 : 이영진 (newnofixing)</li>
 * </ul>
 */
public class IFE002 extends AbsBatchComponent {
    private int processCnt = 0;
    private String taskNo = "";
    private String procFileName = "";
    private String batTaskProcStCd = "";

    /**
     * 배치 생성자. 
     * 상위클래스 생성자 호출
     */
    public IFE002() {
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
		batTaskProcStCd = "";
		
		IOnlineContext    onlineCtx  = makeOnlineContext(context);
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

        if(log.isDebugEnabled()) {
            log.debug("공유컴포넌트 호출 결과:");
            log.debug(resDS);
        }
		
    }

    /**
     * 배치 메인 메소드
     */
    public void execute(final IBatchContext context) {
        Log log = getLog(context);
        
        // 트랜잭션 시작
        txBegin();  
        dbStartSession();
        dbBeginBatch();
        
        // SAM 파일 본문부 레이아웃 정의
        SAMFileTool samTool = new SAMFileTool();
        samTool.addColumnInfo("CNSL_MGMT_NO",			12,SAMFileTool.TYPE_STRING);
        samTool.addColumnInfo("CNSL_MGMT_SEQ",			 3,SAMFileTool.TYPE_BIGDECIMAL);
        samTool.addColumnInfo("DTL_CL",  				 2,SAMFileTool.TYPE_STRING);
        samTool.addColumnInfo("EQP_MDL_CD",				15,SAMFileTool.TYPE_STRING);
        samTool.addColumnInfo("CL_CD",  			 	15,SAMFileTool.TYPE_STRING);
        samTool.addColumnInfo("QTY",			 		 7,SAMFileTool.TYPE_BIGDECIMAL);
        samTool.addColumnInfo("NORM_QTY",			 	 7,SAMFileTool.TYPE_BIGDECIMAL);
        samTool.addColumnInfo("HLD_QTY",			 	 7,SAMFileTool.TYPE_BIGDECIMAL);
        samTool.addColumnInfo("DDCT_AMT",			 	15,SAMFileTool.TYPE_BIGDECIMAL);
        samTool.addColumnInfo("DEL_YN",				 	 1,SAMFileTool.TYPE_STRING);
        samTool.addColumnInfo("UPDATE_CNT",		 		 7,SAMFileTool.TYPE_BIGDECIMAL);
        samTool.addColumnInfo("UKEY_REG_DTM",		 	14,SAMFileTool.TYPE_STRING);
        samTool.addColumnInfo("UKEY_REG_USER_ID",		10,SAMFileTool.TYPE_STRING);
        samTool.addColumnInfo("UKEY_CHG_DTM",		 	14,SAMFileTool.TYPE_STRING);
        samTool.addColumnInfo("UKEY_CHG_USER_ID",	 	10,SAMFileTool.TYPE_STRING);
        samTool.addColumnInfo("CMPT_PROD_CD",			15,SAMFileTool.TYPE_STRING);

        samTool.addColumnInfo("IF_PROC_DT",        		 0, SAMFileTool.TYPE_STRING);
        samTool.addColumnInfo("IF_FILE_NM",        		 0, SAMFileTool.TYPE_STRING);
        samTool.addColumnInfo("IF_SEQ",            		 0, SAMFileTool.TYPE_STRING);
        // 파일 레이아웃 정의
        samTool.setEncoding(BaseUtils.getConfiguration("interface.file.encoding"));

        Map<String, String> paramMap = new HashMap<String, String>();
        //paramMap.put("PROC_DT", context.getInParameter("PROC_DT"));
        paramMap.put("TASK_ID", context.getInParameter("TASK_ID"));
        paramMap.put("FILE_LOC", context.getInParameter("FILE_LOC"));
        paramMap.put("IF_SEQ", context.getInParameter("IF_SEQ"));
        paramMap.put("REC_LENG", "154");
        
        try {
            RecordHandlerExecutor rhe = new RecordHandlerExecutor(makeRecordHandler(context));
            IFFileToDBUtils fileUtil = new IFFileToDBUtils();
            // 전문 양식에 헤더, 테일 없이 DATA만 있는 인터페이스용  fileUtil.listFileData 사용
            // 전문 양식에 헤더, 테일 없이 DATA만 있는 인터페이스 + 파일명 체크 없이 무조건 파일읽기  fileUtil.listFileDataNoName 사용
            IDataSet responseData = fileUtil.listFileDataNoName(samTool, paramMap, log, rhe);

            processCnt = responseData.getIntField("TOT_PROC_CNT");
            procFileName = responseData.getField("PROC_FILE_NM");
            batTaskProcStCd = responseData.getField("BAT_TASK_PROC_ST_CD");
            if ("F".equals(batTaskProcStCd)) throw new BizRuntimeException("DMS00009"); //오류가 발생했습니다.
        } catch(Exception e) {
            log.debug("Exception:["+e.toString()+"]");
            throw new BizRuntimeException("DMS00009", e); //오류가 발생했습니다.
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
            reqDS.putField("BAT_TASK_PROC_ST_CD", batTaskProcStCd);
            if ("F".equals(batTaskProcStCd)) processCnt = 0;
        }else {
            // execute() 에서 에러 발생할 경우
            reqDS.putField("BAT_TASK_PROC_ST_CD", "F");
            processCnt = 0;
        }
        reqDS.putField("PROC_CNT", ""+processCnt);
        IDataSet resDS = callOnlineBizComponent("sc.SCSBase", "fUpdBatTaskOpHst", reqDS, onlineCtx);

        Log log = getLog(context);
        if(log.isDebugEnabled()) {
            log.debug("공유컴포넌트 호출 결과:");
            log.debug(resDS);
        }
        
    }
    
    /**
     * 레코드셋 핸들러
     */
    public AbsRecordHandler makeRecordHandler(IBatchContext context) {
        AbsRecordHandler rh = new AbsRecordHandler(context) {
            
            @Override
            public void handleRecord(IRecord row) {
                context.getLogger().debug("########### : " + row);
                
                //본업무 처리로직 구현
                dbInsert("ICnrlMgmtGnrlDtl", row, context);

                if (getCurrentRecordCount()%1000 == 0) {
                	dbEndBatch();
                	dbBeginBatch();
                }
            }
        };
        return rh;
    } 

}