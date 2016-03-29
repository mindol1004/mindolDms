package dms.nr;

import java.util.HashMap;
import java.util.Map;

import nexcore.framework.bat.IBatchContext;
import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.bat.base.AbsRecordHandler;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;

import org.apache.commons.logging.Log;

/**
 * <ul>
 * <li>업무 그룹명 : DMS-BI/기준정보</li>
 * <li>서브 업무명 : BEDU001</li>
 * <li>설  명 : <pre>[NR]유통망정보 동기화</pre></li>
 * <li>작성일 : 2015-08-04</li>
 * <li>작성자 : 이영진 (newnofixing)</li>
 * </ul>
 */
public class DBR002 extends AbsBatchComponent {
    private int processCnt = 0;
    private String taskNo = "";
    private String procFileName = "";
	
    /**
     * 배치 생성자. 
     * 상위클래스 생성자 호출
     */
    public DBR002() {
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
        reqDS.putField("GRP_ID", "BI");
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
    	
    	Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("PROC_DT", context.getInParameter("PROC_DT"));    //처리일
    	
    	dbSelect("SDsnetInfo", paramMap, makeRecordHandler(context), context); //유통망정보IF조회후 거래처입력 및 수정
		
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
     * 유통망정보IF조회후 거래처입력 및 수정
     * 
     */
    public AbsRecordHandler makeRecordHandler(IBatchContext context) {
    	AbsRecordHandler rh = new AbsRecordHandler(context) {
			
			@Override
			public void handleRecord(IRecord row) {
				context.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시
				context.getLogger().debug("########### : " + row);
				
				String procClCd = row.get("PROC_CL_CD");
				int agnOrgCnt = Integer.parseInt(row.get("AGN_ORG_CNT"));

				// Ukey 초기데이타에 빠진 조직코드 때문에  처리구분코드(procClCd) 값과 상관 없이 거래처테이블에 조직코드 존재여부로 신규/수정 구분하는 로직 구현
				// 처리구분코드(procClCd) ; 1 신규, 2 수정, 3 삭제/해제
                if ("3".equals(procClCd)) {
                    row.set("DEALCO_ST_CD", "05");
                    row.set("DEAL_END_DT",   DateUtils.getCurrentDate());
                } else {
                    row.set("DEALCO_ST_CD", "01");
                	row.set("DEAL_END_DT",   "99991231");
                }

                if (agnOrgCnt == 0) {
					dbInsert("IDealCo", row, context);   //거래처등록
				} else if (agnOrgCnt > 1) {
					throw new BizRuntimeException("DMS00102"); //DMS에 두개 이상의 대리점조직코드가 존재합니다.
				} else {
                    dbInsert("IDealCoHst", row, context);   //거래처이력생성
                    
                    dbUpdate("UDealCo", row, context);      //거래처수정
				}
				
				// 처리구분코드로 처리하는 로직 막음
				/*
                if ("1".equals(procClCd)) {
                    if (agnOrgCnt > 0) {
                        throw new BizRuntimeException("DMS00100"); //DMS에 이미 존재하는 거래처입니다.
                    }
                    dbInsert("IDealCo", row, context);   //거래처등록
                } else {
                    if (agnOrgCnt == 0) {
                        throw new BizRuntimeException("DMS00101"); //DMS에 존재하지 않는 대리점입니다.
                    }
                    if (agnOrgCnt > 1) {
                        throw new BizRuntimeException("DMS00102"); //DMS에 두개 이상의 대리점조직코드가 존재합니다.
                    }
                    dbInsert("IDealCoHst", row, context);   //거래처이력생성
                    if ("3".equals(procClCd)) {
                        row.set("DEALCO_ST_CD", "05");
                        row.set("DEAL_END_DT",   DateUtils.getCurrentDate());
                    }
                    
                    dbUpdate("UDealCo", row, context);      //거래처수정
                }
                */
				
                dbUpdate("UDsnetInfo", row, context);   //유통망정보업데이트
				processCnt++;
			}
		};
    	return rh;
    }

}
