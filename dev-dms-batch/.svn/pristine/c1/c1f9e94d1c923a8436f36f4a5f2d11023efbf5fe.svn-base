package dms.sc;

import java.util.ArrayList;
import java.util.List;

import nexcore.framework.bat.IBatchContext;
import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.bat.base.AbsRecordHandler;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.RecordHeader;
import nexcore.framework.core.data.RecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;

import org.apache.commons.logging.Log;

import fwk.utils.HpcUtils;

/**
 * <ul>
 * <li>업무 그룹명 : DMS-BI/기준정보</li>
 * <li>서브 업무명 : BEDU001</li>
 * <li>설  명 : <pre>[SC]R서비스손해배상금SMS안내</pre></li>
 * <li>작성일 : 2015-08-04</li>
 * <li>작성자 : 이영진 (newnofixing)</li>
 * </ul>
 */
public class DBC002 extends AbsBatchComponent {
    private int processCnt = 0;
    private String taskNo = "";
    private String procFileName = "";
	
    /**
     * 배치 생성자. 
     * 상위클래스 생성자 호출
     */
    public DBC002() {
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
        reqDS.putField("GRP_ID", "SC");
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
    	
    	dbSelect("SCntrtDmgLst", null, makeRecordHandler(context), context); //계약종료목록
		
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
     * 단말기모델IF조회 후 레코드 단위로 단말기모델 테이블에 입력
     * 
     */
    public AbsRecordHandler makeRecordHandler(IBatchContext context) {
        AbsRecordHandler rh = new AbsRecordHandler(context) {
            IOnlineContext    onlineCtx  = makeOnlineContext(context);
			@Override
			public void handleRecord(IRecord row) {
				context.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시
				context.getLogger().debug("########### : " + row);
				
				String month = String.valueOf(Integer.parseInt(row.get("RENTAL_CNTRT_END_DT").substring(4, 6)));
				String day = String.valueOf(Integer.parseInt(row.get("RENTAL_CNTRT_END_DT").substring(6)));
				
				IDataSet reqDs = new DataSet();
				
				/* 20151110 임지후 [파라미터입력값 수정]   [기존 소스]
				 *   reqDs.putField("ALRT_WORK_CL_CD", "S");
    	            reqDs.putField("ALRT_MSG_ID", row.get("ALRT_MSG_ID"));
    	            reqDs.putField("REVR_CL_CD", "10");
    	            reqDs.putField("ALRT_MSG_CD", "M"); 알림전송구분코드 (*메세지 글자수 및 첨부파일여부에 따라 fSndAlrt fm에서 직접 판단함 )
    	            reqDs.putField("RETN_TEL_NO_ENPT", HpcUtils.encodeByAES(row.get("SVC_NO_ENPT"))); */
	            
	            //20151110 임지후 [파라미터입력값 수정]                
                //알림업무구분   공통코드그룹 : DMS259  / 코드값 : SMS_자동발송
                reqDs.putField("ALRT_WORK_CL_CD", "AUTO");                
                reqDs.putField("ALRT_MSG_ID", row.get("ALRT_MSG_ID"));                
                //알림수신자구분 공통코드그룹 : DMS014  / 코드값 : 계약자
                reqDs.putField("REVR_CL_CD", "10");
                //과금부서구분코드 : DMS258  / 코드값 : 신규
                reqDs.putField("CHRG_DEPT_CL_CD", "NR");
                //수신번호 (* fSndAlrt fm에서 직접 암호화처리함 )
                //수신번호복호화
                reqDs.putField("RETN_TEL_NO", HpcUtils.decodeByAES(row.get("SVC_NO_ENPT")));
	            
	            String[] sSvcList = new String[]{"렌탈", row.get("DMS_DMG_CMP_AMT")};
	            
	            reqDs.putField("MSG_PARAMS", sSvcList);
	            reqDs.putField("USER_NO", "BAT");
	            
	            callOnlineBizComponent("sc.SCSBase", "fSndAlrt", reqDs, onlineCtx);	
				
				processCnt++;
			}
		};
    	return rh;
    }

}
