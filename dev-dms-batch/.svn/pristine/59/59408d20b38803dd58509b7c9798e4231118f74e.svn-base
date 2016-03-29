package dms.ep;

import java.util.HashMap;

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
import nexcore.framework.core.util.StringUtils;

import org.apache.commons.logging.Log;

/**
 * <ul>
 * <li>업무 그룹명 : DMS-IF/인터페이스</li>
 * <li>서브 업무명 : BIF110</li>
 * <li>설  명 : <pre>[EP]상담관리일반상세</pre></li>
 * <li>작성일 : 2015-08-10 11:07:57</li>
 * <li>작성자 : 이준우 (newnofixing)</li>
 * </ul>
 */
public class DBE002 extends AbsBatchComponent {
    private int processCnt = 0;
    private String taskNo = "";
    private String procFileName = "";

    /**
     * 배치 생성자. 상위클래스 생성자 호출
     */
    public DBE002() {
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
		
		IOnlineContext  onlineCtx  = makeOnlineContext(context);
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
        // 트랜잭션 시작
        txBegin();  
        dbStartSession();
//        dbBeginBatch();
        
        /* SQL ParmaMap 설정 */
        HashMap<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("PROC_DT", context.getInParameter("PROC_DT"));             //파라메터 날짜받기
//        String sCurDate = "";
//        sCurDate = context.getInParameter("SPECI_PROC_DT");
        
        try {
            dbSelect("SIfConsultDtl", paramMap, makeRecordHandler(context), context); // 상담관리상세정보 조회  
            
        } catch (Exception e) {         
            throw new BizRuntimeException("M00001", e);
        }
        
        // 트랜잭션 커밋
//        dbEndBatch();
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
        reqDS.putField("PROC_CNT", "" + processCnt);
        reqDS.putField("PROC_FILE_NM", procFileName);
        reqDS.putField("LS_CHG_USER_ID", "BAT");
        if (super.exceptionInExecute == null) {
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
     * 상담관리상세대상IF조회 후 레코드 단위로 상담관리상세대상 테이블에 입력
     * 
     */
    public AutoCommitRecordHandler makeRecordHandler(IBatchContext context) {
    
        AutoCommitRecordHandler rh = new AutoCommitRecordHandler(this,context,  dbNewSession(context, false)) {
            
            @Override
            public void handleRecord(IRecord row) {

                context.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시
                                 
                HashMap<String, Object> paramMap = new HashMap<String,Object>();                    
                paramMap.put("CNSL_MGMT_NO", StringUtils.nvlObject(row.get("CNSL_MGMT_NO"),""));
                paramMap.put("CNSL_SER_NO", StringUtils.nvlObject(row.get("CNSL_MGMT_SEQ"),""));
                
                paramMap.put("IF_PROC_DT", StringUtils.nvlObject(row.get("IF_PROC_DT"),""));
                paramMap.put("IF_FILE_NM", StringUtils.nvlObject(row.get("IF_FILE_NM"),""));
                paramMap.put("IF_SEQ", StringUtils.nvlObject(row.get("IF_SEQ"),""));
                
                paramMap.put("DTL_CL", StringUtils.nvlObject(row.get("DTL_CL"),""));
                paramMap.put("CL_CD", StringUtils.nvlObject(row.get("CL_CD"),""));
                paramMap.put("EQP_MDL_CD", StringUtils.nvlObject(row.get("EQP_MDL_CD"),""));
                paramMap.put("CMPT_PROD_CD", StringUtils.nvlObject(row.get("CMPT_PROD_CD"),""));
                paramMap.put("QTY", StringUtils.nvlObject(row.get("QTY"),""));
                paramMap.put("NORM_QTY", StringUtils.nvlObject(row.get("NORM_QTY"),""));
                paramMap.put("HLD_QTY", StringUtils.nvlObject(row.get("HLD_QTY"),""));
                paramMap.put("DDCT_AMT", StringUtils.nvlObject(row.get("DDCT_AMT"),""));
                paramMap.put("DEL_YN", StringUtils.nvlObject(row.get("DEL_YN"),""));
                paramMap.put("UKEY_UPDATE_COUNT", StringUtils.nvlObject(row.get("UPDATE_CNT"),""));
                paramMap.put("UKEY_INPUT_DTM", StringUtils.nvlObject(row.get("UKEY_REG_DTM"),""));
                paramMap.put("UKEY_INSR_ID", StringUtils.nvlObject(row.get("UKEY_REG_USER_ID"),""));
                
                String rv_prcCl = "";
                
//                context.getLogger().debug("///상담관리상세카운트///");                
                IRecord Record1 = dbSelectSingle("SConsultDtlCnt", paramMap, context);  // 상담관리상세 카운트
                
                if (Record1.getInt("CNT",0) > 0){                               // 상담관리상세 카운트 있음
                    
//                    context.getLogger().debug("///매입관리카운트///");
                    IRecord Record2 = dbSelectSingle("SPrchsMgmtCnt", paramMap, context);  // 매입관리 카운트
                    
                    if (Record2.getInt("CNT",0) > 0){               //매입관리자료 있을때 N 작업종료
//                        context.getLogger().debug("///매입관리 정보가 있습니다///");
                        rv_prcCl      = "N";
                    }else{                                          //상담상세정보 있고 매입관리자료 없을때 U
//                        context.getLogger().debug("///매입관리정보가 없습니다///");
                        rv_prcCl      = "U";
                    }
                }else{
//                    context.getLogger().debug("///등록대상 Y///");
                    rv_prcCl = "Y";                                         //상담상세X,매입상세X
                } 
                
                if (!"N".equals(rv_prcCl)) {   //Y & U 일경우

                    //                    context.getLogger().debug("///상담관리카운트///");
                    IRecord Record3 = dbSelectSingle("SConsultMgmtCnt", paramMap, context);  // 상담관리 카운트
                    
                    if (Record3.getInt("CNT",0) > 0){                          
                        if ("U".equals(rv_prcCl)){

//                            context.getLogger().debug("///상담관리상세 정보 삭제 등록///");
                            dbDelete("DConsultDtl", paramMap, context);//상담관리상세 삭제 U
                            dbInsert("IConsultDtl", paramMap, context);//상담관리상세등록
                        }
                        if ("Y".equals(rv_prcCl)){
//                            context.getLogger().debug("상담관리상세등록");
                            dbInsert("IConsultDtl", paramMap, context);//상담관리상세등록, 상태Y
                        }
                    }
                    
                    if(row.get("IF_PROC_DT").length() > 0){
//                        context.getLogger().debug("IF상담관리상세수정");
                        dbUpdate("UIfConsultDtl", paramMap, context);  // IF상담관리상세수정
                    }
                }else{
                	if(row.get("IF_PROC_DT").length() > 0){
//                      context.getLogger().debug("IF상담관리상세수정");
                	  //처리되지 않는 건들은 08로 미처리 상태로 만든다.
                      dbUpdate("UIfConsultDtlProcNot", paramMap, context);  // IF상담관리상세수정
                	}
                }
                processCnt++;

            }
        };
        
        rh.setAddBatchMode(true);
        rh.setCommitCount(1000);
        rh.setStopWhenException(false);
        
        return rh;
    }

}



