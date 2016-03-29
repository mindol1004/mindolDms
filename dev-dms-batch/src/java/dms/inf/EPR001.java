package dms.inf;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import nexcore.framework.bat.IBatchContext;
import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.RecordHeader;
import nexcore.framework.core.data.RecordSet;
import nexcore.framework.core.util.DateUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;

import fwk.constants.SlipConstants;
import fwk.erfif.sapjco.SapCaller;
import fwk.utils.HpcUtils;
import fwk.utils.SAPUtils;

/**
 * <ul>
 * <li>업무 그룹명 : dms/인터페이스</li>
 * <li>서브 업무명 : EPR001</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-24 15:34:17</li>
 * <li>작성자 : 진병수 (greatjin)</li>
 * </ul>
 */
public class EPR001 extends AbsBatchComponent {
    private Log log;
    private int processCnt = 0;
    private String taskNo = "";
    private int totCnt = 0;
    private String procFileName = "";
    private String userNo       = "";
    
    
    public EPR001() {
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
    	Map<String, String> paramMap = new HashMap<String, String>();
     	SAPUtils.debug("prepareInputParam() context :"+context);
     	paramMap.putAll(context.getInParameters());
      	paramMap.put("PROC_DT", context.getInParameter("PROC_DT")); //실행일
     	SAPUtils.debug("prepareInputParam() paramMap :"+paramMap);
     	return paramMap;
    }
    
    /**
     * 배치 메인 메소드
     */
    public void execute(final IBatchContext context) {
    	Map paramMap = this.prepareInputParam(context);
    	context.setReturnValue("TRANS_VALUE", "TRIGGER_TEST");
    	String toDate = SAPUtils.nvl(paramMap,"PROC_DT",HpcUtils.getCurrentDate(Locale.KOREAN, SlipConstants.YYYYMMDD));
		String fromDate = DateUtils.addMonth(toDate, -2);
		
		if(    StringUtils.isNotEmpty(SAPUtils.nvl(paramMap,"STA_DT",null))
		    && StringUtils.isNotEmpty(SAPUtils.nvl(paramMap,"END_DT",null))
		   )
		{
			fromDate = SAPUtils.nvl(paramMap,"STA_DT",null);
			toDate   = SAPUtils.nvl(paramMap,"END_DT",null);
					
		}
		
		this.userNo = SAPUtils.nvl(paramMap,"USER_NO","EPR001");
		
		paramMap.put("I_DATE_FROM", fromDate);
		paramMap.put("I_DATE_TO"  , toDate  );
		
		String operType = SAPUtils.nvl(paramMap, "OPER_TYPE","");
    	String syncType;
    	
    	if("OND".equals(operType))
    	{
    		syncType = SAPUtils.nvl(paramMap, "SYNC_TYPE","");
    	}
    	else
    	{
    		syncType =SAPUtils.nvl(paramMap, "SYNC_TYPE4BS","");
    	}
    		
    	if(StringUtils.isEmpty(syncType)) syncType="XD";
    	paramMap.put("SYNC_TYPE", syncType);
    	
    	
    	SAPUtils.debug("execute ==================> execute paramMap:"+paramMap);
    	if(    "XD".equals(syncType)
    	    || "XA".equals(syncType) 
    	   )
    	{
    		SAPUtils.debug("execute ==================> (callDocumentStatus) paramMap:"+paramMap);
    		syncDocumentStatus(paramMap); //전표삭제 테스트
    	}
    	
    	if(    "XT".equals(syncType)
            || "XA".equals(syncType) 
       	   )
       	{

    		SAPUtils.debug("execute ==================> (callErpTaxInvoice) paramMap:"+paramMap); 
	
	    	IDataSet responseData = this.callErpTaxInvoice(paramMap);
	    	IRecordSet ir = responseData.getRecordSet("IT_TAX_INFO");
	    	
	    	log = getLog(context);
	    	SAPUtils.debug("execute ==================> (ir):"+ir);
	    	
       	}
    }

    /**
     * 전표상태 동기화
     */
    private void syncDocumentStatus(Map paramMap)
    {
    	IRecordSet rs = dbSelectMulti("SSlip", paramMap);
    	if(rs!=null) callErpDocumentStatus(rs, paramMap);
    }
    
    /**
     * 전표상태 조회
     */
    private void callErpDocumentStatus(IRecordSet rs,Map paramMap)
    {
    	String functionName = "Z_FI_RFC_DMS_DOCUMENT_STATUS";
    	
		IDataSet responseData = new DataSet();
		
		HashMap params = new HashMap();
		params.putAll(paramMap);;
		SAPUtils.debug("callDocumentStatus ==================> (params):"+params);
		
		IRecordSet itDmsHeader = new RecordSet("IT_STATUS_DATA");

		itDmsHeader.addHeader(new RecordHeader("BUKRS    ".trim()));
		itDmsHeader.addHeader(new RecordHeader("GJAHR    ".trim()));
		itDmsHeader.addHeader(new RecordHeader("BELNR    ".trim()));
		
		for(int i=0 ; i<rs.getRecordCount(); i++)
		{
			IRecord recordContents = itDmsHeader.newRecord();
	        recordContents.put("BUKRS    ".trim(), SlipConstants.CO_CD_SKCC);
	        recordContents.put("GJAHR    ".trim(), ((String) rs.get(i, "SLIP_DT")).substring(0, 4)); //yyyymm
	        recordContents.put("BELNR    ".trim(), rs.get(i, "SLIP_NO"));
	        itDmsHeader.addRecord(recordContents);
		}
        
        IDataSet erpRequestData = new DataSet();
        erpRequestData.putRecordSet("IT_STATUS_DATA", itDmsHeader);
        
    	HashMap callInfo = new HashMap();
		callInfo.put("params", params);
		callInfo.put("IT_STATUS_DATA", "IT_STATUS_DATA");

		SAPUtils.debug("callDocumentStatus ==================> (callInfo):"+callInfo); 

		SapCaller caller = new SapCaller(functionName);
   	 	responseData = caller.sendData(erpRequestData, callInfo ,false);
   	 	
		SAPUtils.debug("callDocumentStatus ==================> (responseData):"+responseData); 
		
		this.updateSlipStatus(responseData.getRecordSet("IT_ERP_RETURN"));
			
    }
    
    
    /**전표상태업데이트*/
    
    private void updateSlipStatus(IRecordSet erpRs)
    {
    	for(int i=0; i<erpRs.getRecordCount(); i++)
    	{
    		Map<String, Object> map = erpRs.getRecordMap(i);
    		
    		map.put("SLIP_NO", map.get("BELNR"));
    		
    		String sapStat = map.get("BSTAT").toString();
    		if("R".equals(sapStat))
    		{
    			map.put("ORGL_SLIP_NO", map.get("STBLG"));
    			map.put("SLIP_NORM_CNCL_CL", "Y");
    		}
    		
    		map.put("SLIP_TRMS_YN", sapStat);
    		map.put("USER_NO", this.userNo);
    		
    		SAPUtils.debug("updateSlipStatus ==================> (map):"+map);
    		
    		dbUpdate("USlip", map); //전표상태 동기화
    		dbUpdate("UAssetSlip", map); //자산전표상태동기화
    		dbUpdate("UAsmptSlip", map); //매출추정상태동기화
    		
    		
    		//중고
    		dbUpdate("UEPInve", map);
    		dbUpdate("UEPFeePPay", map);
    		dbUpdate("UEPAccRmt", map);
    		dbUpdate("UEPEqpSale", map);
    		dbUpdate("UEPInveMovXcl", map);
    	}
    	
    	//IT_ERP_RETURN
    	//ZFISERIA|BELNR|LBSTAT | DDTEXT | STBLG | MSGTYP | MSG 
    }
    
    
    
    /**
     * 세금계산서 호출
     * @return
     */
    @SuppressWarnings("unchecked")
	private IDataSet callErpTaxInvoice(Map map)
    {
    	String functionName = "Z_FI_RFC_DMS_TAX_INVOICE";
		IDataSet responseData = new DataSet();
		
		HashMap params = new HashMap();
		params.put("I_DATE_FROM", SAPUtils.nvl(map,"I_DATE_FROM",""));
		params.put("I_DATE_TO", SAPUtils.nvl(map,"I_DATE_TO",""));
		
		
		SAPUtils.debug("callErpTaxInvoice ==================> (params):"+params); 

    	HashMap callInfo = new HashMap();
		callInfo.put("params", params);
		
		
		SAPUtils.debug("callErpTaxInvoice ==================> (callInfo):"+callInfo); 
		
		SapCaller caller = new SapCaller(functionName);
   	 	responseData = caller.sendData(new DataSet(), callInfo ,false);
   	 	
   	 	SAPUtils.debug("callErpTaxInvoice ==================> (responseData):"+responseData); 
   	 	
   	 	
   	 	this.updateTaxInfo(responseData.getRecordSet("IT_TAX_INFO"));
   	 	
   	 	return responseData;
    }
    
    
    
    /**세금계산서 정보 업데이트*/
    private void updateTaxInfo(IRecordSet erpRs)
    {
    	for(int i=0; i<erpRs.getRecordCount(); i++)
    	{
    		Map<String, Object> map = erpRs.getRecordMap(i);
    		
    		map.put("SLIP_NO", map.get("BELNR"));
    		map.put("TAX_BILL_NO", map.get("ZETNO"));
    		//map.put("TAX_BILL_ST_CD", map.get("ZRFDT")); //참조주문일
    		map.put("TAX_BILL_ST_CD", map.get("ZSTAT"));
    		map.put("USER_NO", this.userNo);
    		
    		SAPUtils.debug("updateTaxInfo ==================> (map):"+map);
    		
    		dbUpdate("UTaxInfo", map);
    		dbUpdate("UPrchTaxInfo", map);
    	}
    	
    }
    
    /**
     * addMonths
     * @param inputDate
     * @param vector
     * @return
     */
    private Date addMonths(Date inputDate, int vector)
    {
    	Calendar c = Calendar.getInstance();
    	c.setTimeInMillis(inputDate.getTime());
    	
    	c.add(Calendar.MONTH, vector);
    	return c.getTime();
    }
    
    
    /**
     * 해당 날짜를 포맷팅되 데이터로 만든다.
     * @param inputDate
     * @param localeNo
     * @param pattern
     * @return
     */
    private String getFormattedDate(Date inputDate, Locale locale, String pattern) {
        FastDateFormat fdf = FastDateFormat.getInstance (pattern, locale);
        
        return fdf.format(inputDate);
    }
    
    
    
    /**
     * 배치 후처리 메소드. 
     * beforeExecute(), execute() 의 Exception 발생 여부와 관계없이 이 메소드는 실행됨
     */
    public void afterExecute(IBatchContext context) {
	   	 IOnlineContext onlineCtx = makeOnlineContext(context);
	     
	     IDataSet reqDS = new DataSet();
	     reqDS.putField("TASK_NO", taskNo);
	     reqDS.putField("OP_FILE_NM", procFileName);
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
	
	     log = getLog(context);
	     if(log.isDebugEnabled()) {
	         log.debug("보증보험료정산 BATCH 호출 결과:");
	         log.debug(resDS);
	     }
    }

}
