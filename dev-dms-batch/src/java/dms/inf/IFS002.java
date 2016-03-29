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

import fwk.utils.HpcUtils;
import fwk.utils.IFDBToFileUtils;

/**
 * <ul>
 * <li>업무 그룹명 : dms/시스템공통</li>
 * <li>서브 업무명 : BSC002</li>
 * <li>설  명 : <pre>[IF]보증보험청구내역(SGI)</pre></li>
 * <li>작성일 : 2015-07-30 15:18:31</li>
 * <li>작성자 : 이영진 (newnofixing)</li>
 * </ul>
 */
public class IFS002 extends AbsBatchComponent {
    private int processCnt = 0;
    private String taskNo = "";
    private String procFileName = "";
    private String preTaskSTCD = "";
    
    /**
     * 배치 생성자. 
     * 상위클래스 생성자 호출
     */
    public IFS002() {
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
        reqDS.putField("GRP_ID", "NR");
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
            paramMap.put("FILE_DT", context.getInParameter("FILE_DT"));
            
            IFDBToFileUtils dbToFile = new IFDBToFileUtils();
            OutputStream fout = dbToFile.HeaderParse(paramMap);
            
            StringBuffer bf = new StringBuffer();
            bf.append(context.getInParameter("TASK_ID"));
            bf.append(".SKCC.");
            bf.append(context.getInParameter("FILE_DT"));
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

            body.addColumnInfo("REC_CL_CD",              		  1, SAMFileTool.TYPE_STRING);

            body.addColumnInfo("OP_CL_CD",                        2, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("TRMS_CO_CL_CD",                   3, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("INSURE_INV_REQT_NO",             14, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("RSLT_CD",                         2, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("ERR_RSLT_CONT",                   2, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("INSU_SCUR_NO",                   18, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("INSU_MGMT_NO",                   14, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("SVC_MGMT_NO",                    10, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("CUST_TYP_CD",                     1, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("CTZ_COPREG_NO",                  13, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("CUST_NM",                        40, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("BIZ_REG_NO",                     10, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("LAST_EQP_MDL_NM",                12, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("LAST_EQP_SER_NO",                20, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("MBL_PHON_NUM",                   20, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("ATHO_TERM_DT",                    8, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("INSPAY_TERM_YN",                  1, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("TTUL_CHG_DT",                     8, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("SVC_SCRB_DT",                     8, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("INSU_STA_DT",                     8, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("INSU_END_DT",                     8, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("ALLOT_TOT_FREQ",                  2, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("ALLOT_PAYIN_FREQ",                2, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("INSU_SCRB_AMT",                   9, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("INV_DT",                          8, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("UNPD_RENTAL_AMT",                 9, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("UNPD_DMG_CMP_AMT",                9, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("UNPD_ADD_AMT",                    9, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("GROSS_INV_AMT",                   9, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("FST_ARR_DT",                      8, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("FST_ARR_TTR",                     2, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("RESD_TEL_NO",                    20, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("RESD_ZIPCD",                      6, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("BADDR_TEL_NO",                   20, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("BADDR_ZIPCD",                     6, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("RESD_ADDR_CL_CD",                 1, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("RESD_BAS_ADDR",                  70, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("RESD_DTL_ADDR",                 100, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("BADDR_ADDR_CL_CD",                1, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("BADDR_BAS_ADDR",                 70, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("BADDR_DTL_ADDR",                100, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("LAST_RECV_DT",                    8, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("LAST_RECV_TOT",                  10, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("TEEN_NM",                        10, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("TEEN_NM_ENPT",                  300, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("TEEN_CTZ_REG_NO",                13, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("DRT_CALC_YN",                     1, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("PROC_DT",                         8, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("PROC_TMS",                        6, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("SVC_CD",                          1, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("PSNL_RGEN_YN",                    1, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("PSNL_RGEN_STEP",                  1, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("PSNL_RGEN_RIBR_YN",               1, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("PSNL_RGEN_RIBR_AMT",             10, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("BRC_EXM_YN",                      1, SAMFileTool.TYPE_STRING);
            body.addColumnInfo("FILLER",                        100, SAMFileTool.TYPE_STRING);
            body.initialize();
            
            dbSelect("SInsuInvListLst", queryParam, makeRecordHandler(body), context);  //DB 조회
            
            preTaskSTCD = "S";
            IOnlineContext    onlineCtx  = makeOnlineContext(context);
            IDataSet reqDS = new DataSet();
            reqDS.putField("TASK_ID", "DBS002");
            reqDS.putField("TASK_DT", DateUtils.getCurrentDate());
            if (processCnt == 0) {
                IDataSet resDS = callOnlineBizComponent("sc.SCSBase", "fInqTaskSTCD", reqDS, onlineCtx);
                if ("".equals(resDS.getField("BAT_TASK_PROC_ST_CD"))) preTaskSTCD = "F"; 
                else preTaskSTCD = resDS.getField("BAT_TASK_PROC_ST_CD");
            }
            paramMap.put("PROC_ST_CD", preTaskSTCD);
            
            paramMap.put("TOT_REC_CNT", processCnt+"");
            paramMap.put("FILE_NM_SEQ", ifFileNmSeq);
            dbToFile.tailParse(paramMap, fout);
            
            dbUpdate("UInsuInvList", queryParam, context);	// 처리상태코드 Update
        
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
                	// 복호화
                	// 암호화 값이 없는 경우 처리로직 추가필요
                    arg0.set("CTZ_COPREG_NO", HpcUtils.decodeByAES(arg0.get("CTZ_COPREG_NO_ENPT")));
                    arg0.set("CUST_NM", HpcUtils.decodeByAES(arg0.get("CUST_NM_ENPT")));
                    arg0.set("MBL_PHON_NUM", HpcUtils.decodeByAES(arg0.get("MBL_PHON_NUM_ENPT")));
                    arg0.set("RESD_TEL_NO", HpcUtils.decodeByAES(arg0.get("RESD_TEL_NO_ENPT")));
                    arg0.set("BADDR_TEL_NO", HpcUtils.decodeByAES(arg0.get("BADDR_TEL_NO_ENPT")));
                    arg0.set("RESD_DTL_ADDR", HpcUtils.decodeByAES(arg0.get("RESD_DTL_ADDR_ENPT")));
                    arg0.set("BADDR_DTL_ADDR", HpcUtils.decodeByAES(arg0.get("BADDR_DTL_ADDR_ENPT")));
                    arg0.set("TEEN_NM", HpcUtils.decodeByAES(arg0.get("TEEN_NM_ENPT")));
                    arg0.set("TEEN_CTZ_REG_NO", HpcUtils.decodeByAES(arg0.get("TEEN_CTZ_REG_NO_ENPT")));
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
