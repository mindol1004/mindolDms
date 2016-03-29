package dms.ep;

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
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;
import nexcore.framework.core.util.StringUtils;

import org.apache.commons.logging.Log;

/**
 * <ul>
 * <li>업무 그룹명 : DMS-IF/인터페이스</li>
 * <li>서브 업무명 : BIF110</li>
 * <li>설  명 : <pre>[EP]유통망정보-BR코드</pre></li>
 * <li>작성일 : 2015-08-10 11:07:57</li>
 * <li>작성자 : 이준우 (newnofixing)</li>
 * </ul>
 */
public class DBE009 extends AbsBatchComponent {
    private int processCnt = 0;
    private String taskNo = "";
    private String procFileName = "";

    /**
     * 배치 생성자. 상위클래스 생성자 호출
     */
    public DBE009() {
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
        dbBeginBatch();
        
        /* SQL ParmaMap 설정 */
        HashMap<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("PROC_DT", context.getInParameter("PROC_DT"));             //파라메터 날짜받기
        
        try {
            dbSelect("STfDsnetInfoBrCd", paramMap, makeRecordHandler(context), context); // IF_유통망정보 조회
            
        } catch (Exception e) {         
            throw new BizRuntimeException("M00001", e);
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
     * 유통망정보 IF조회 후 레코드 단위로 마켓팅 테이블에 입력,수정
     * 
     */
    public AutoCommitRecordHandler makeRecordHandler(IBatchContext context) {
    
        AutoCommitRecordHandler rh = new AutoCommitRecordHandler(this,context,  dbNewSession(context, false)) {
                        
            @Override
            public void handleRecord(IRecord row) {

                context.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시
                String S_ORG_CD = "";
//                context.getLogger().debug("XXXXXXXXXXXXX " + row);
                                 
                HashMap<String, Object> paramMap = new HashMap<String,Object>();                    
                                
                paramMap.put("IF_PROC_DT", StringUtils.nvlObject(row.get("IF_PROC_DT"),""));
                paramMap.put("IF_FILE_NM", StringUtils.nvlObject(row.get("IF_FILE_NM"),""));
                paramMap.put("IF_SEQ"    , StringUtils.nvlObject(row.get("IF_SEQ"),""));
                
                paramMap.put("UKEY_AGN_CD"   , StringUtils.nvlObject(row.get("ORG_CD"        ),""));  /*UKEY 대리점 코드  */
                paramMap.put("HEADQ_CD"      , StringUtils.nvlObject(row.get("HEADQ_ORG_CD"  ),""));  /*본부 코드  */
                paramMap.put("HEADQ_NM"      , StringUtils.nvlObject(row.get("HEADQ_ORG_NM"  ),""));  /*본부 명  */
                paramMap.put("MKT_TEAM_CD"   , StringUtils.nvlObject(row.get("HIGHR_ORG_CD"    ),""));  /*마케팅 팀 코드  */
                paramMap.put("MKT_TEAM_NM"   , StringUtils.nvlObject(row.get("HIGHR_ORG_NM"  ),""));  /*마케팅 팀 명  */
                paramMap.put("STA_DT"        , StringUtils.nvlObject(row.get("APPLY_STA_DT"   ),""));  /*시작 일자  */
                paramMap.put("END_DT"        , StringUtils.nvlObject(row.get("APPLY_END_DT"   ),""));  /*종료 일자  */
                
//                context.getLogger().debug("###paramMap####### : "+paramMap.toString());
                S_ORG_CD = row.get("SUB_ORG_CD");  
                if("A000".equals(S_ORG_CD)){
                    
//                    context.getLogger().debug("###팀코드카운트#### : ");
                    IRecord Record = dbSelectSingle("SDealCoMarketigCnt", paramMap, context); //마켓팅 팀코드 카운트 & MAX(AGN_SEQ)
                    
                    if(Record.getInt("CNT",0) > 0){//CNT > 0
                        paramMap.put("AGN_SEQ"        , StringUtils.nvlObject(Record.get("AGN_SEQ"   ),""));  /*SEQ */    
//                        context.getLogger().debug("###마켓팅업데이트#### : ");
                        dbUpdate("UDealCoMarketing", paramMap, context);  //Update
                    }else{
                        paramMap.put("AGN_SEQ"        , 0);  /*SEQ */
                    }
//                    context.getLogger().debug("###마켓팅저장#### : ");
                    dbInsert("IDealCoMarketing", paramMap, context);  //Insert

                }    
//                context.getLogger().debug("###IF 마켓팅업데이트#### : ");                
                dbUpdate("UTfDsnetInfoBrCd", paramMap, context);  //TF update
                              
                processCnt++;

            }
        };
        
        rh.setAddBatchMode(true);
        rh.setCommitCount(1000);
        rh.setStopWhenException(false);
        
        return rh;
    }

}
