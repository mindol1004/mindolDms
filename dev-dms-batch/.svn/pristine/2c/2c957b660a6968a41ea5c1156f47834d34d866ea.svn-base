package dms.pr;

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
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;
import nexcore.framework.core.util.StringUtils;

/**
 * <ul>
 * <li>업무 그룹명 : dms/임대R</li>
 * <li>서브 업무명 : BPR001</li>
 * <li>설 명 :</li>
 * <li>작성일 : 2015-08-05 15:07:33</li>
 * <li>작성자 : 김윤환 (kyh0904)</li>
 * </ul>
 */
public class DBP005 extends AbsBatchComponent {
	private int processCnt = 0;
	private String taskNo = "";
	private String procFileName = "";
	
	/**
     * 배치 생성자. 
     * 상위클래스 생성자 호출
     */
	public DBP005() {
		super();
	}

	/**
	 * 배치 전처리 메소드. 여기서 Exception 발생시 execute() 메소드는 실행되지 않고, afterExecute() 는
	 * 실행됨
	 */
	public void beforeExecute(IBatchContext context) {
	
		processCnt = 0;
		taskNo = "";
		procFileName = "";

		IOnlineContext onlineCtx = makeOnlineContext(context);
		IDataSet reqDS = new DataSet();
		IDataSet resDS = callOnlineBizComponent("sc.SCSBase", "fInqTaskNoSeq",
				reqDS, onlineCtx);
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

		callOnlineBizComponent("sc.SCSBase", "fRegBatTaskOpHst", reqDS,
				onlineCtx);
		/*
		Log log = getLog(context);
		if (log.isDebugEnabled()) {
			log.debug("BPR001(단말기회수정보대상등록)_컴포넌트 호출 결과:");
			log.debug(resDS);
		}
		*/
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
        
		try {
        	dbSelect("STfEqpOpClctObj", paramMap, makeRecordHandler(context), context); // 단말기회수정보대상 IF조회 및 등록        	        
    	} catch (Exception e) {
            throw new BizRuntimeException("M00001", e);
        }
		
		// 트랜잭션 커밋
		dbEndBatch();
		dbEndSession();
		txCommit();
	}

	/**
	 * 배치 후처리 메소드. beforeExecute(), execute() 의 Exception 발생 여부와 관계없이 이 메소드는 실행됨
	 */
	public void afterExecute(IBatchContext context) {
		IOnlineContext onlineCtx = makeOnlineContext(context);
		IDataSet reqDS = new DataSet();
		reqDS.putField("TASK_NO", taskNo);
		reqDS.putField("PROC_FILE_NM", procFileName);
		reqDS.putField("LS_CHG_USER_ID", "BAT");
		if (super.exceptionInExecute == null) {
			// execute() 정상인 경우
			reqDS.putField("BAT_TASK_PROC_ST_CD", "S");
		} else {
			// execute() 에서 에러 발생할 경우
			reqDS.putField("BAT_TASK_PROC_ST_CD", "F");
			processCnt = 0;
		}
		reqDS.putField("PROC_CNT", "" + processCnt);
		IDataSet resDS = callOnlineBizComponent("sc.SCSBase", "fUpdBatTaskOpHst",
				reqDS, onlineCtx);
		/*
		Log log = getLog(context);
		if (log.isDebugEnabled()) {
			log.debug("BPR001(단말기회수정보대상등록)_컴포넌트 호출 결과:");
			log.debug(resDS);
		}
		*/
	}
	
	/**
     * 단말기회수정보대상IF조회 후 레코드 단위로 단말기회수정보대상 테이블에 입력
     * 
     */
    public AbsRecordHandler makeRecordHandler(IBatchContext context) {
    	AbsRecordHandler rh = new AbsRecordHandler(context) {
			
			@Override
			public void handleRecord(IRecord row) {																
				context.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시
				
				IRecord record = dbSelectSingle("SEqpOpClctObjSeq", row, context);	// 단말기입고대상번호 조회		
				
				if( null != record  ){
					row.set("EQP_IN_CLCT_NO", StringUtils.nvlObject(record.get("EQP_IN_CLCT_NO"), ""));	// 단말기입고대상번호 저장
															
					//context.getLogger().debug("EQP_IN_CLCT_NO_채번 결과  : " + row.get("EQP_IN_CLCT_NO"));
					//context.getLogger().debug("########### : " + row);							
					
					dbInsert("IEqpOpClctObj", row, context);	 	// TB_EQP_CLCT_OBJ 테이블에 등록
					dbUpdate("UTfEqpClctObjInfo", row, context);	// I/F 테이블(TF_EQP_CLCT_OBJ_INFO) 처리완료 상태 변경										
				}
				
				processCnt++;
			}
		};
    	return rh;
    }

}
