package dms.pr;

import java.util.HashMap;

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
 * <li>서브 업무명 : BPR005</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-11 09:27:00</li>
 * <li>작성자 : 김윤환 (kyh0904)</li>
 * </ul>
 */
public class DBP007 extends AbsBatchComponent {
	private int processCnt = 0;
	private String taskNo = "";
	private String procFileName = "";
	
	/**
	 * 배치 생성자. 상위클래스 생성자 호출
	 */
    public DBP007() {
        super();
    }

    /**
     * 배치 전처리 메소드. 
     * 여기서 Exception 발생시 execute() 메소드는 실행되지 않고, afterExecute() 는 실행됨
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
			log.debug("BPR005(단말기평가정보등록)_컴포넌트 호출 시작 :");
			log.debug("배치 오늘 일자 :" + context.getInParameter("PROC_DT"));

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
    	    dbSelect("SEqpJdg", paramMap, makeRecordHandler(context), context); // 단말기평가정보조회 및 I/F 단말기평가정보등록        	        
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
			log.debug("BPR005(단말기평가정보등록)_컴포넌트 호출 결과:");
			log.debug(resDS);
		}
		*/
    }
    
    /**
     * 단말기 평가 정보 조회 후 레코드 단위로  I/F 단말기 평가 정보 테이블에 입력
     * 
     */
    public AbsRecordHandler makeRecordHandler(IBatchContext context) {
    	AbsRecordHandler rh = new AbsRecordHandler(context) {
			int i = 1;	// I/F 채번순번 
			String cmptInfo =""; // 구성품정보			 
			@Override
			public void handleRecord(IRecord row) {																
				String ifFileNm = "";
				ifFileNm = "P007.SKCC."+context.getInParameter("PROC_DT")+"_"+context.getInParameter("FILE_SEQ");				
				context.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시
				//context.getLogger().debug("단말기평가정보등록_IF_파일명  : " + ifFileNm);
															
				if(null != row  ){					
				    IRecord recordInfo = dbSelectSingle("SCmptInfo", row, context);   // 구성품정보 조회
				    
				    if(null != recordInfo)
				        cmptInfo = StringUtils.nvlObject(recordInfo.get("CMPT_INFO"),"");
				    else
				        cmptInfo = StringUtils.rpad(cmptInfo,410," "); 				    
				    
				    IRecord record = dbSelectSingle("SDmgRsn", row, context);	      // 단말기평가정보 파손사유 조회				
					/* I/F 단말기평가정보 데이터 가공 */					
					HashMap<String, Object> paramMap = new HashMap<String,Object>();					
					paramMap.put("IF_PROC_DT", context.getInParameter("PROC_DT"));	// IF_처리일자
					paramMap.put("IF_FILE_NM", ifFileNm);							// IF_파일명
					paramMap.put("IF_SEQ", i++);									// IF_순번					
					paramMap.put("EQP_MDL_CD", row.get("EQP_MDL_CD"));				// 단말기모델코드
					paramMap.put("EQP_SER_NO", row.get("EQP_SER_NO"));			    // 단말기일련번호
					paramMap.put("SVC_MGMT_NO", row.get("SVC_MGMT_NO"));             // 서비스관리번호										
					paramMap.put("CMPT_INDF_BACK_AMT", row.get("CONS_ITM_CMP_RTN_AMT"));		// 구성품변상반환금액
					paramMap.put("EQP_BKAG_INDF_AMT", row.get("EQP_CMP_AMT_TOT"));				// 단말기파손변상금액
					paramMap.put("EQP_BKAG_RSN", StringUtils.nvlObject(record.get("DSM_RSN"),""));		// 단말기파손사유
					paramMap.put("CMPT_INFO", cmptInfo);                                       // 구성품정보
					
					//paramMap.put("CMPT_CD", row.get("CMPT_CD"));					// 구성품코드_인터페이스대상삭제
					//paramMap.put("CMPT_RTN_YN", row.get("RTN_YN"));					// 구성품반납여부_인터페이스대상삭제
					//paramMap.put("CMPT_INV_AMT", row.get("CMPT_UPRC"));			    // 구성품청구금액_인터페이스대상삭제
					
					//context.getLogger().debug("########### : " + paramMap.toString());					
					dbInsert("ITfPrEqpEvalInfoReG", paramMap, context);	 	// TF_PR_EQP_EVAL_INFO_REG 테이블에 등록															    								          			       
				}
				
				processCnt++;
			}
		};
    	return rh;
    }
}
