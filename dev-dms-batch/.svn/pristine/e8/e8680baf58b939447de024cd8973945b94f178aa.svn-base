package dms.nr;

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
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.util.DateUtils;

/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>서브 업무명 : DBR009</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-24 10:58:19</li>
 * <li>작성자 : 진병수 (greatjin)</li>
 * </ul>
 */
public class DBR009 extends AbsBatchComponent {
    private Log log;
    private int processCnt = 0;
    private String taskNo = "";
    private int totCnt = 0;
    private String procFileName = "";
    
    public DBR009() {
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
     * 당월청구는(1~말일)은 익월 8일날 (ukey 7일밤계산) 데이터 들어옴
     * 기변일때는 일련번호가 다를수있음
     * 부분청구가 있을수도 있음
     * 부가세는 100 + 10 따로계산
     * 해지 핫빌(서비스해지,  번호이동, 정산 명의변경)시 즉시 수납되어 수납데이터(준실시간)가 청구데이터(월배치) 보다 먼저 들어올수 있음
     * 
     * 청구가 먼저들어오는 건에 대해서는 분리가 필요함..
     *  
     * 배치 메인 메소드
     * 
     * CRRNTUS : 렌탈서비스 이용금액
CRRNTHA : 렌탈서비스 중도해지위약금
CRRNTDE : 렌탈서비스 반납지연위약금
CRPREGA : 렌탈서비스 연체가산금
CRRNBAP : 렌탈서비스 손해배상금(단말파손)
CRRNBME : 렌탈서비스 손해배상금(단말미반납)

     * 수납은 30분단위 배치 -> 보험제외
     */
    public void execute(final IBatchContext context) {
    	log = context.getLogger();
        // 트랜잭션 시작
     	txBegin();  
     	dbStartSession();
     	dbBeginBatch();
     	
     	//입력파라미터 셋팅
     	Map<String, String> paramMap = this.prepareInputParam(context);
     	
     	log.debug("청구데이터를 보고 매출테이블에 넣는다.");
     	paramMap.put("SALE_ITM_CD", "CRRNTUS");
     	
     	IRecordSet rs = dbSelectMulti("SClaimSumLst", paramMap);
     	if(rs!=null)
     	{
	     	for(int i=0 ; i<rs.getRecordCount(); i++)
	     	{
	     		context.getLogger().debug("########### execute () URentMonthAmt rs.getRecordMap(i), : " + rs.getRecordMap(i));
	    		dbInsert("ISaleInfo", rs.getRecordMap(i), context); //렌탈매출
	    		dbUpdate("URentMonthAmt", rs.getRecordMap(i), context);//렌탈료에 청구데이터 업데이트 TB_EQP_CNTRT_DTL INV_DT, INV_AMT
	     	}
     	}
     	
     	IRecordSet uRs = dbSelectMulti("SClaimLst", paramMap);
     	if(uRs!=null)
     	{
	     	for(int i=0 ; i<uRs.getRecordCount(); i++)
	     	{
	     		context.getLogger().debug("########### execute () URentMonthAmt uRs.getRecordMap(i), : " + uRs.getRecordMap(i));
	    		dbUpdate("UIFCntrt", uRs.getRecordMap(i), context);//데이터 업데이트
	     	}
     	}
     	
     	
     	//위약금 Ukey 금액 업데이트 TB_RENTAL_PEN Ukey_pen_amt
     	//1 중도해지
     	paramMap.put("SALE_ITM_CD", "CRRNTHA");
     	rs = dbSelectMulti("SClaimSumLst", paramMap);
     	if(rs!=null)
     	{
     		Map map = null;
	     	for(int i=0 ; i<rs.getRecordCount(); i++)
	     	{
	     		map = rs.getRecordMap(i);
	     		map.put("PEN_POLY_CL", "01");
	    		map.put("XCL_ITM_CD", "PE");
	     		context.getLogger().debug("########### execute () URentalPenAmt rs.getRecordMap(i), : " + map);
	    		dbUpdate("URentalPenAmt", map, context);
	     	}
     	}
     	
     	uRs = dbSelectMulti("SClaimLst", paramMap);
     	if(uRs!=null)
     	{
	     	for(int i=0 ; i<uRs.getRecordCount(); i++)
	     	{
	     		context.getLogger().debug("########### execute () URentalPenAmt uRs.getRecordMap(i), : " + uRs.getRecordMap(i));
	    		dbUpdate("UIFCntrt", uRs.getRecordMap(i), context);//데이터 업데이트
	     	}
     	}
     	
     	
     	//2 반납지연
     	paramMap.put("SALE_ITM_CD", "CRRNTDE");
     	rs = dbSelectMulti("SClaimSumLst", paramMap);
     	if(rs!=null)
     	{
     		Map map = null;
	     	for(int i=0 ; i<rs.getRecordCount(); i++)
	     	{
	     		map = rs.getRecordMap(i);
	     		map.put("PEN_POLY_CL", "02");
	    		map.put("XCL_ITM_CD", "PX");
	     		context.getLogger().debug("########### execute () URentalPenAmt rs.getRecordMap(i), : " + map);
	    		dbUpdate("URentalPenAmt", map, context);
	     	}
     	}
     	
     	uRs = dbSelectMulti("SClaimLst", paramMap);
     	if(uRs!=null)
     	{
	     	for(int i=0 ; i<uRs.getRecordCount(); i++)
	     	{
	     		context.getLogger().debug("########### execute () URentalPenAmt uRs.getRecordMap(i), : " + uRs.getRecordMap(i));
	    		dbUpdate("UIFCntrt", uRs.getRecordMap(i), context);//데이터 업데이트
	     	}
     	}
     	
     	//3 연체가산금  연체가산금은 발행할수 가 없으니 없으면 인서트 해야함..
     	paramMap.put("SALE_ITM_CD", "CRPREGA");
     	rs = dbSelectMulti("SClaimSumLst", paramMap);
     	if(rs!=null)
     	{
     		Map map = null;
	     	for(int i=0 ; i<rs.getRecordCount(); i++)
	     	{
	     		map = rs.getRecordMap(i);
	     		map.put("PEN_POLY_CL", "03");
	    		map.put("XCL_ITM_CD", "PY");
	     		context.getLogger().debug("########### execute () URentalPenAmt rs.getRecordMap(i), : " + map);
	    		dbUpdate("URentalPenAmt", map, context);
	    		//해당계약에 대한 데이터가 잇는지 확인해야함
	     	}
     	}
     	
     	uRs = dbSelectMulti("SClaimLst", paramMap);
     	if(uRs!=null)
     	{
	     	for(int i=0 ; i<uRs.getRecordCount(); i++)
	     	{
	     		context.getLogger().debug("########### execute () URentalPenAmt uRs.getRecordMap(i), : " + uRs.getRecordMap(i));
	    		dbUpdate("UIFCntrt", uRs.getRecordMap(i), context);//데이터 업데이트
	     	}
     	}
     	
     	//손해배상금
     	//1 단말파손 CRRNBAP : 렌탈서비스 손해배상금(단말파손) 03
     	
     	paramMap.put("SALE_ITM_CD", "CRRNBAP");
     	rs = dbSelectMulti("SClaimSumLst", paramMap);
     	if(rs!=null)
     	{
     		Map map = null;
	     	for(int i=0 ; i<rs.getRecordCount(); i++)
	     	{
	     		map = rs.getRecordMap(i);
	    		map.put("DCP_CL", "03");
	     		context.getLogger().debug("########### execute () URentalDcpAmt rs.getRecordMap(i), : " + map);
	    		dbUpdate("URentalDcp", map, context);
	     	}
     	}
     	uRs = dbSelectMulti("SClaimLst", paramMap);
     	if(uRs!=null)
     	{
	     	for(int i=0 ; i<uRs.getRecordCount(); i++)
	     	{
	     		context.getLogger().debug("########### execute () URentalDcpAmt uRs.getRecordMap(i), : " + uRs.getRecordMap(i));
	    		dbUpdate("UIFCntrt", uRs.getRecordMap(i), context);//데이터 업데이트
	     	}
     	}
     	
     	
     	//2 CRRNBME : 렌탈서비스 손해배상금(단말미반납) 02
     	paramMap.put("SALE_ITM_CD", "CRRNBME");
     	rs = dbSelectMulti("SClaimSumLst", paramMap);
     	if(rs!=null)
     	{
     		Map map = null;
	     	for(int i=0 ; i<rs.getRecordCount(); i++)
	     	{
	     		map = rs.getRecordMap(i);
	    		map.put("DCP_CL", "02");
	     		context.getLogger().debug("########### execute () URentalPenAmt rs.getRecordMap(i), : " + map);
	    		dbUpdate("URentalDcp", map, context);
	     	}
     	}
     	uRs = dbSelectMulti("SClaimLst", paramMap);
     	if(uRs!=null)
     	{
	     	for(int i=0 ; i<uRs.getRecordCount(); i++)
	     	{
	     		context.getLogger().debug("########### execute () URentalDcpAmt uRs.getRecordMap(i), : " + uRs.getRecordMap(i));
	    		dbUpdate("UIFCntrt", uRs.getRecordMap(i), context);//데이터 업데이트
	     	}
     	}
     	
     	
     	
 		// 트랜잭션 커밋
 		dbEndBatch();
 		dbEndSession();
 		txCommit(); 
     }
    

     
    /**
     * makeRecordHandler
     * @param context
     * @return
     */
     public AbsRecordHandler makeRecordHandler(IBatchContext context) {
     	AbsRecordHandler rh = new AbsRecordHandler(context) {
 			
 			@Override
 			public void handleRecord(IRecord row) {
 				context.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시
 				context.getLogger().debug("########### : " + row);
 				
 				dbInsert("ISaleInfo", row, context);
 				processCnt++;
 			}
 		};
     	return rh;
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
