package dms.nr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nexcore.framework.bat.IBatchContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;

import dms.utils.SAPUtils;
import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.bat.base.AbsRecordHandler;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;

/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>서브 업무명 : XCR008</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-12 13:21:56</li>
 * <li>작성자 : 진병수 (greatjin)</li>
 * </ul>
 */
public class XCR017 extends AbsBatchComponent {
    private Log log;
    private int processCnt = 0;
    private String taskNo = "";
    private int totCnt = 0;
    private String procFileName = "";
    
    public XCR017() {
        super();
    }

    /**
     * 배치 전처리 메소드. 
     * 여기서 Exception 발생시 execute() 메소드는 실행되지 않고, afterExecute() 는 실행됨
     */
    public void beforeExecute(IBatchContext context) {
    	log = getLog(context);
		
		processCnt = 0;
		taskNo = "";
		totCnt = 0;
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
        reqDS.putField("OP_CNT", "0");
        reqDS.putField("FS_REG_USER_ID", "BAT");
        reqDS.putField("LS_CHG_USER_ID", "BAT");
        
        IDataSet resDS2 = callOnlineBizComponent("sc.SCSBase", "fRegBatTaskOpHst", reqDS, onlineCtx);

        Log log = getLog(context);
        if(log.isDebugEnabled()) {
            log.debug("공유컴포넌트 호출 결과:");
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
     * 배치 메인 메소드
     * 월1회 계산
     * 회수수수료 정산화면과 일치하게 데이터를 만듦
     * 회수대행수수료 대리점 반납일자
     */
    public void execute(final IBatchContext context) {
    	  // 트랜잭션 시작
     	txBegin();  
     	dbStartSession();
     	dbBeginBatch();
     	
     	//입력파라미터 셋팅
     	Map<String, String> paramMap = this.prepareInputParam(context);
     	
     	IRecordSet existEtcList = SAPUtils.nvl(dbSelectMulti("SXclEtcInfo",paramMap));
        List<Map> doNotEtcDeleteList = new ArrayList();
        Map tmpMap = null;
        for(int i=0; i<existEtcList.getRecordCount();i++)
        {
            tmpMap = existEtcList.getRecordMap(i);
            if("05".equals(SAPUtils.nvl(tmpMap,"SLIP_ST_CD","")))
            {
                dbDelete("DXclEtc", tmpMap);
            }
            else
            {
                doNotEtcDeleteList.add(tmpMap);
            }
        }
        
        SAPUtils.debug("doNotEtcDeleteList :" +doNotEtcDeleteList);
        
        if(CollectionUtils.isNotEmpty(doNotEtcDeleteList))
        {
            IRecordSet rs = dbSelectMulti("STagetLst", paramMap);
            boolean flagContinue = false;
            for(int i=0; i<rs.getRecordCount(); i++) //모수
            {
                for(Map one:doNotEtcDeleteList) //비교 기존 집계 자료에서 전표진행중인것들
                {
                    if(this.equalsMap4Key(one, rs.getRecordMap(i), new String[]{"CNTRT_NO","XCL_DEPT_CD","XCL_DEALCO_CD","XCL_ITM_CD"}))
                    {
                        flagContinue = true;
                        break;
                    }
                }
                
                if(flagContinue) 
                {
                    flagContinue = false;
                    continue;
                }
                else
                {
                    SAPUtils.debug("rs.getRecordMap(i) :" +rs.getRecordMap(i));
                    dbInsert("IEtcXcl", rs.getRecordMap(i));   
                }
            }
            
        }
        else
        {
            this.upsertHandler(context, paramMap, "STagetLst", "IEtcXcl");
        }
                       
        dbDelete("DXclDebtByYM", paramMap);
        dbEndBatch();
        txCommit();
        dbBeginBatch();
        this.upsertHandler(context, paramMap, "STagetSumLst", "IDebt");
 		
 		// 트랜잭션 커밋
 		dbEndBatch();
 		dbEndSession();
 		txCommit(); 
     }
    
    /**
     * upsertHandler
     * @param context
     * @param paramMap
     * @param selectStmt
     * @param upsertStmt
     */
    private void upsertHandler(IBatchContext context, Map paramMap, String selectStmt, String upsertStmt)
    {
    	log.debug("*******************************************************************************************" );
    	log.debug(selectStmt + " : IN" );
		try {
			context.setAttribute("upsertStmt", upsertStmt);
        	dbSelect(selectStmt, paramMap, upsertRecordHandler(context), context);         	        
    	} catch (Exception e) {
            throw new BizRuntimeException("M00001", e);
        }
    }
    
    /**
     * upsertRecordHandler
     * @param context
     * @return
     */
     private AbsRecordHandler upsertRecordHandler(IBatchContext context) {
     	AbsRecordHandler rh = new AbsRecordHandler(context) {
 			@Override
 			public void handleRecord(IRecord row) {
 				String upsertStmt  = context.getAttribute("upsertStmt").toString();
 				context.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시
 				context.getLogger().debug("updateRecordHandler()"+upsertStmt+" : " + row);
 				//SAPUtils.debug("updateRecordHandler()"+updateStmt+" : " + row);
 				//processWithinUpsertRecordHandler(context, row);
 				if(upsertStmt.startsWith("U"))
 				{
 					dbUpdate(upsertStmt, row, context);
 				}
 				else if(upsertStmt.startsWith("I"))
 				{
 					dbInsert(upsertStmt, row, context);
 				}
 				else if(upsertStmt.startsWith("D"))
 				{
 					dbDelete(upsertStmt, row, context);
 				}
 				processCnt++;
 			}
 		};
     	return rh;
     }
     
     /**
      * 해당 key에 해당하는 값이 
      * @param fromMap
      * @param toMap
      * @param keys
      * @return
      */
     private boolean equalsMap4Key(Map fromMap, Map toMap, String[] keys)
     {
     	boolean returnFlag = false;
     	
     	if(ArrayUtils.isEmpty(keys))
     	{
     		returnFlag = fromMap.equals(toMap);
     	}
     	else
     	{
     		returnFlag = true;
     		for(String key:keys)
     		{
     			//SAPUtils.debug(key+ ") fromMap key :" +SAPUtils.nvl(fromMap, key, "") + ", toMap key :" +SAPUtils.nvl(toMap, key, "")+ "=" +(!SAPUtils.nvl(fromMap, key, "").equals(SAPUtils.nvl(toMap, key, ""))));
     			if(  !SAPUtils.nvl(fromMap, key, "").equals(SAPUtils.nvl(toMap, key, ""))
     			   ) 
     			{
     				
     				returnFlag = false;
     				return returnFlag;
     			}
     		}
     	}
     	return returnFlag;
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

}
