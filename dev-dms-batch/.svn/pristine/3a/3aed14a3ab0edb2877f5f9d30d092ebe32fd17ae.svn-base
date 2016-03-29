package dms.nr;

import java.util.HashMap;
import java.util.Map;

import nexcore.framework.bat.IBatchContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;

import dms.utils.SAPUtils;
import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;

/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>서브 업무명 : XCR006</li>
 * <li>설  명 : <pre>정산채권</pre></li>
 * <li>작성일 : 2015-08-07 14:50:02</li>
 * <li>작성자 : 진병수 (greatjin)</li>
 * </ul>
 */
public class XCR006 extends AbsBatchComponent {
    private Log log;
	private int processCnt = 0;
	private String taskNo = "";
	private String procFileName = "";
    
    public XCR006() {
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

		callOnlineBizComponent("sc.SCSBase", "fRegBatTaskOpHst", reqDS,onlineCtx);

		log = getLog(context);
		if (log.isDebugEnabled()) {
			log.debug("XCR006(단말기월렌탈료)_컴포넌트 호출 결과:");
			log.debug(resDS);
		}
		
    }
    
    /**
     * 입력파라미터 준비
     * @param context
     * @return
     */
    private Map<String, String> prepareInputParam(IBatchContext context)
    {
    	log = context.getLogger();
    	Map<String, String> paramMap = new HashMap<String, String>();
     	log.debug("prepareInputParam() context :"+context);
     	paramMap.putAll(context.getInParameters());
    	paramMap.put("PROC_DT", context.getInParameter("PROC_DT")); //실행일
     	log.debug("prepareInputParam() paramMap :"+paramMap);
     	return paramMap;
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

		Log log = getLog(context);
		if (log.isDebugEnabled()) {
			log.debug("XCR007(대리점단말기매입정산)_컴포넌트 호출 결과:");
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
		
		//입력파라미터를 받음
     	Map<String, String> paramMap = this.prepareInputParam(context);// 기준년월
		try {
			IRecordSet sumList = dbSelectMulti("SRecvRfndSum", paramMap);
			
			if(SAPUtils.isNotEmpty(sumList))
			{
				Map deletemMap = SAPUtils.convertRecord2Map(sumList.getRecord(0));
				SAPUtils.debug("execute() deleteMap:"+deletemMap); 
				int cntDel = dbDelete("DXclBond", deletemMap);
				SAPUtils.debug("execute() cntDel:"+cntDel);
				for(int i=0; i<sumList.getRecordCount();i++)
				{
					dbInsert("IXclBond", sumList.getRecordMap(i));
				}
			
				IRecordSet list = dbSelectMulti("SRecvRfnd", paramMap);
				if(SAPUtils.isNotEmpty(list)) 
				{
					for(int i=0; i<list.getRecordCount();i++)
					{
						dbUpdate("URecvRfnd", list.getRecordMap(i));
					}
				}

			}
			
    	} catch (Exception e) {
    		e.printStackTrace();
            throw new BizRuntimeException("M00001", e);
        }
		
		// 트랜잭션 커밋
		dbEndBatch();
		dbEndSession();
		txCommit();
    }
    
    

}
