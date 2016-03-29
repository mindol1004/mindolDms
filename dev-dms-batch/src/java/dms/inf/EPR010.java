package dms.inf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nexcore.framework.bat.IBatchContext;
import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.bat.base.AbsRecordHandler;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
//import nexcore.framework.core.data.IBatchContext;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.RecordHeader;
import nexcore.framework.core.data.RecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.core.util.DateUtils;
import nexcore.framework.core.util.StringUtils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;

import com.skcc.hpw.webservice.ServiceProxy;

import dms.constants.enums.sapjco.SAP_SLIP_ITEM;
import dms.constants.enums.sapjco.elem.SAP_SLIP_KINDS;
import dms.constants.enums.sapjco.elem.SAP_TAX_CD;
import dms.constants.enums.sapjco.elem.SAP_WBS_ELEM;
import dms.utils.SAPUtils;
import dms.utils.sapjco.domain.AssetDisposalSlip;
import dms.utils.sapjco.domain.AssetRetirementSlip;
import dms.utils.sapjco.domain.CancelChargeSlip;
import dms.utils.sapjco.domain.CommonSlipHeader;
import dms.utils.sapjco.domain.PanaltyFeeSlip;
import dms.utils.sapjco.domain.RentalARSlip;
import dms.utils.sapjco.domain.RentalBillingSlip;
import dms.utils.sapjco.domain.ReturnCommissionSlip;
import dms.utils.sapjco.domain.SalesCommissionSlip;
import dms.utils.sapjco.domain.ep.AccttrfEPSlip;
import dms.utils.sapjco.domain.ep.AgencyCommissionEPSlip;
import dms.utils.sapjco.domain.ep.E4UAPEPSlip;
import dms.utils.sapjco.domain.ep.IncostEPSlip;
import dms.utils.sapjco.domain.ep.InsalesEPSlip;
import dms.utils.sapjco.domain.ep.OutsalesEPSlip;
import dms.utils.sapjco.domain.ep.PrediscEPSlip;
import dms.utils.sapjco.domain.ep.PrepaidEPSlip;
import dms.utils.sapjco.domain.ep.StockEPSlip;
import dms.utils.sapjco.domain.ep.StockFixEPSlip;
import dms.utils.sapjco.domain.ep.StockYetEPSlip;
import dms.utils.sapjco.domain.nr.AgencyAmtAPNRSlip;
import dms.utils.sapjco.domain.nr.AssetAmtAANRSlip;
import dms.utils.sapjco.domain.nr.AssetDepreciationNRSlip;
import dms.utils.sapjco.domain.nr.AssetDeptTransferNRSlip;
import dms.utils.sapjco.domain.nr.BondDisposal1NRSlip;
import dms.utils.sapjco.domain.nr.BondDisposal2NRSlip;
import dms.utils.sapjco.domain.nr.CommissionARNRSlip;
import dms.utils.sapjco.domain.nr.CommissionNRSlip;
import dms.utils.sapjco.domain.nr.InsRefundARNRSlip;
import dms.utils.sapjco.domain.nr.InsfeeARNRSlip;
import dms.utils.sapjco.domain.pr.AgencyAmtAPPRSlip;
import dms.utils.sapjco.domain.pr.AssetDepreciationPRSlip;
import dms.utils.sapjco.domain.pr.AssetDisposalPRSlip;
import dms.utils.sapjco.domain.pr.AssetRetirementPRSlip;
import dms.utils.sapjco.domain.pr.RentalPRSlip;
import dms.utils.sapjco.domain.pr.TransAmtPRSlip;
import fwk.constants.SlipConstants;
import fwk.constants.enums.sapjco.SAP_SLIP_ELEM;
import fwk.constants.enums.sapjco.SAP_SLIP_RETURN;
import fwk.erfif.sapjco.SapCaller;
import fwk.erfif.sapjco.domain.CommonSlipItem;
import fwk.utils.DomainUtils;

/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>서브 업무명 : EPR010</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-09-04 10:52:23</li>
 * <li>작성자 : 진병수 (greatjin)</li>
 * </ul>
 */
public class EPR010 extends AbsBatchComponent {
    private Log log;
    private int processCnt = 0;
    private String taskNo = "";
    private int totCnt = 0;
    private String procFileName = "";
    private String userNo       = "";
    
    private String dmsSlipSeq = "";
    private String sapSlipNo  = "";
    
    public EPR010() {
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
		
		IOnlineContext    batchCtx  = makeOnlineContext(context);
		IDataSet reqDS = new DataSet();
		IDataSet resDS = callOnlineBizComponent("sc.SCSBase", "fInqTaskNoSeq", reqDS, batchCtx);
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
        
        //TODO Greatjin 온라인 타임아웃으로 인해서 임시로 주석처리함.
        //IDataSet resDS2 = callOnlineBizComponent("sc.SCSBase", "fRegBatTaskOpHst", reqDS, batchCtx);

        Log log = getLog(context);
        if(log.isDebugEnabled()) {
            SAPUtils.debug("공유컴포넌트 호출 결과:");
            SAPUtils.debug(resDS);
        }		
		
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
        //TODO Greatjin 온라인 타임아웃으로 인해서 임시로 주석처리함.
	     IDataSet resDS = null; //callOnlineBizComponent("sc.SCSBase", "fUpdBatTaskOpHst", reqDS, onlineCtx);
	
	     log = getLog(context);
	     if(log.isDebugEnabled()) {
	         log.debug("보증보험료정산 BATCH 호출 결과:");
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
     	paramMap.putAll(context.getInParameters());
      	paramMap.put("PROC_DT", context.getInParameter("PROC_DT")); //실행일
     	SAPUtils.debug("prepareInputParam() paramMap :"+paramMap);
     	return paramMap;
    }
    
    
    /**
     * 입력파라미터 준비
     * @param context
     * @return
     */
    private Map<String, String> prepareParentParam(IBatchContext context)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.putAll(SAPUtils.extractMapByPrefix(context.getInParameters(),"PARENT.",true));
        paramMap.putAll(SAPUtils.extractMapByPrefix(context.getInParameters(),"PARENT.OUT.",true));
        SAPUtils.debug("prepareParentParam() paramMap :"+paramMap);
        return paramMap;
    }
    

    /**
     * 배치 메인 메소드
     */
    public void execute(final IBatchContext context) {
        Map requestMap = prepareInputParam (context);
        IDataSet requestData = new DataSet();
        if("OND".equals(context.getOperatorType()))
        {
            IOnlineContext onlineCtx =  getOnlineContextFromOndemand();
            requestData = SAPUtils.prepareXMLInputParam (context,"POST_SLIP_DATASET");
        }
        else //SCH나 USR일때 전표수동으로 치거나 트리거에 걸려있는 경우.
        {
            Map paramMap  = prepareParentParam (context);
            requestData = SAPUtils.convertMap2DataSet(paramMap);
        }
        
        SAPUtils.debug("execute() context.getOperatorType() :"+ context.getOperatorType());
        SAPUtils.debug("execute() requestData :"+ requestData);
        SAPUtils.debug("execute() requestMap :"+ requestMap);
        
        this.postingSlip(requestMap,requestData, context);
    }
    
    
    /**
     * 전표발행
     * @param requestData
     * @param batchCtx
     * @return
     */
    private IDataSet postingSlip(Map requestMap, IDataSet requestData, IBatchContext batchCtx)
    {
    	IDataSet responseData = new DataSet();
    	
    	userNo = SAPUtils.nvl(requestMap, "USER_NO",""); 

    	String slipType = "";
    	
		if(requestData!=null && requestData.containsField("SLIP_TYPE"))
		{
	    	SAPUtils.debug("postingSlip() requestData :"+ requestData);
	    	SAPUtils.debug("postingSlip() requestMap :"+ requestMap);
			slipType = StringUtils.nvl(requestData.getField("SLIP_TYPE"),"");
		}
		else if(requestMap!=null && requestMap.containsKey("SLIP_TYPE"))
		{
	    	SAPUtils.debug("postingSlip() requestMap :"+ requestMap);
			slipType = SAPUtils.nvl(requestMap, "SLIP_TYPE","");
		}
		
		if(StringUtils.isNotEmpty(slipType))
		{
			responseData = this.postingSlipEachOther(slipType, requestMap, requestData, batchCtx);
		}
		return responseData;
    }

    /**
     * sliptype으로 분기처리
     * @param slipType
     * @param requestMap
     * @param requestData
     * @param batchCtx
     * @return
     */
    private IDataSet postingSlipEachOther(String slipType, Map requestMap, IDataSet requestData, IBatchContext batchCtx)
    {
    	IDataSet responseData = new DataSet();

    	if(slipType.startsWith("PR")) //임대
    	{
    		responseData = this.postingSlipEachOtherPR(slipType, requestMap, requestData, batchCtx);
    	}
    	else if (slipType.startsWith("EP")) //중고
    	{
    		responseData = this.postingSlipEachOtherEP(slipType, requestMap, requestData, batchCtx);
    	} 
    	else //신규
    	{
    	    responseData = this.postingSlipEachOtherNR(slipType, requestMap, requestData, batchCtx);  
    	}
    	return responseData;
    	
    }
    
    /**
     * sliptype으로 분기처리
     * @param slipType
     * @param requestMap
     * @param requestData
     * @param batchCtx
     * @return
     */
    private IDataSet postingSlipEachOtherNR(String slipType, Map requestMap, IDataSet requestData, IBatchContext batchCtx)
    {
        IDataSet responseData = new DataSet();

        if("ASSET_AA".equals(slipType)) //자산취득전표
        {
            responseData = fRegAssetAASlip(requestData, batchCtx);
        }
        else if("ASSET_DEPRECIATION".equals(slipType)) //자산감가상각전표
        {
            responseData = fRegAssetDepreciationSlip(requestData, batchCtx);
        }
        else if("NR_AC".equals(slipType)) //자산감가상각전표
        {
            responseData = fRegAssetDepreciationNRSlip(requestData, batchCtx);
        }
        else if("NR_AS".equals(slipType)) //자산매각
        {
            responseData = fRegAssetDisposalSlip(requestData, batchCtx);
        }
        else if("NR_AD".equals(slipType)) //자산제각
        {
            responseData = fRegAssetRetirementSlip(requestData, batchCtx);
        }
        
        //신규 매출
        else if("AGENCY_AMT_AP".equals(slipType)) //대리점단말기 정산)
        {
            responseData = fRegAgentAmtAPSlip(requestData, batchCtx);
        }
        else if("SALE_ASMPT".equals(slipType))  //매출추정전표
        {
            responseData = fRegSaleAsmptSlip(requestData, batchCtx);
        }
        else if("RENTAL_SALE".equals(slipType)) //매출확정전표
        {
            responseData = fRegSaleFixSlip(requestData, batchCtx);
        }
        else if("SALES_COMMISSION".equals(slipType)) //판매수수료전표
        {
            responseData = fRegSalesCommissionSlip(requestData, batchCtx);
        }
        else if("RETURN_COMMISSION".equals(slipType)) //회수수수료전표
        {
            responseData = fRegReturnCommissionSlip(requestData, batchCtx);
        }
        else if("NR_IP".equals(slipType)) //보증보험료 AP
        {
            requestData.putField("SLIP_BU_TYPE", SAP_SLIP_KINDS.INS_COMMISSION_AP_NR);
            responseData = fRegCommissionAPSlip(requestData, batchCtx);
        }       
        else if("NR_IC".equals(slipType)) //보증보험료 AR
        {
            requestData.putField("SLIP_BU_TYPE", SAP_SLIP_KINDS.INS_COMMISSION_AR_NR);
            responseData = fRegInsfeeARSlip(requestData, batchCtx);
        }       
        else if("NR_P".equals(slipType)) //위약금
        {
            responseData = fRegCancelChargeSlip(requestData, batchCtx);
        }
        else if("NR_J".equals(slipType)) //손해배상금
        {
            responseData = fRegPenaltyFeeSlip(requestData, batchCtx);
        }
        else if("NR_HP".equals(slipType)) //보증보험금 AP_지급
        {
            requestData.putField("SLIP_BU_TYPE", SAP_SLIP_KINDS.INS_BENEFIT_AR_NR);
            responseData = fRegInsRefundARSlip(requestData, batchCtx);
        }    
        else if("NR_HR".equals(slipType)) //보증보험금 AR_환급
        {
            requestData.putField("SLIP_BU_TYPE", SAP_SLIP_KINDS.INS_BENEFIT_AP_NR);
            responseData = fRegCommissionAPSlip(requestData, batchCtx);
        }    
        else if("NR_FP".equals(slipType)) //FP수수료
        {
            requestData.putField("SLIP_BU_TYPE", SAP_SLIP_KINDS.FPA_COMMISSION_NR);
            responseData = fRegCommissionOneFromRSlip(requestData, batchCtx);
        }    
        else if("NR_TC".equals(slipType)) //단말보험료
        {
            requestData.putField("SLIP_BU_TYPE", SAP_SLIP_KINDS.MBINS_COMMISSION_NR);
            responseData = fRegCommissionOneFromRSlip(requestData, batchCtx);
        }
        else if("NR_CD".equals(slipType)) //추심수수료 AP
        {
            requestData.putField("SLIP_BU_TYPE", SAP_SLIP_KINDS.COLLECTION_COMIISSION_NR);
            responseData = fRegCommissionAPSlip(requestData, batchCtx);
        }       
        else if("NR_BC".equals(slipType)) //미납채권
        {
            responseData = fRegBondDesposalNRSlip(requestData, batchCtx);
        }
        else if("NR_OA".equals(slipType)) //자산취득취소
        {
            responseData = fRegAssetAACancelSlip(requestData, batchCtx);
        }
        else if("NR_OC".equals(slipType)) //자산감가상각전표취소
        {
            responseData = fRegAssetDepreciationCancelSlip(requestData, batchCtx);
        }
        else if("NR_AE".equals(slipType)) //자산이관전표
        {
            responseData = fRegAssetTransferNRSlip(requestData, batchCtx);
        }
        return responseData;
    }
    
    
    /**
     * sliptype으로 분기처리
     * @param slipType
     * @param requestMap
     * @param requestData
     * @param batchCtx
     * @return
     */
    private IDataSet postingSlipEachOtherPR(String slipType, Map requestMap, IDataSet requestData, IBatchContext batchCtx)
    {
    	IDataSet responseData = new DataSet();

    	//[임대]--------------------------------------------------------------------------------------
    	//임대 매출
		if("PR_RENT".equals(slipType)) //[임대]렌탈매충
		{
			requestMap.put("SLIP_BU_TYPE", SAP_SLIP_KINDS.RENTAL_PR);
			responseData = fRegSalePRSlip(SAPUtils.convertMap2DataSet(requestMap), batchCtx);
		}
		else if("PR_RR".equals(slipType)) //[임대]구성품매출
		{
			requestMap.put("SLIP_BU_TYPE", SAP_SLIP_KINDS.REPROD_PR);
			responseData = fRegSalePRSlip(SAPUtils.convertMap2DataSet(requestMap), batchCtx);
		}
		else if("PR_JN".equals(slipType)) //[임대]미반납
		{
			requestMap.put("SLIP_BU_TYPE", SAP_SLIP_KINDS.REPAIR_PR);
			responseData = fRegSalePRSlip(SAPUtils.convertMap2DataSet(requestMap), batchCtx);
		}
		else if("PR_JD".equals(slipType)) //[임대]손해배상금 단말파손
		{
			requestMap.put("SLIP_BU_TYPE", SAP_SLIP_KINDS.REPAIR_PR);
			responseData = fRegSalePRSlip(SAPUtils.convertMap2DataSet(requestMap), batchCtx);
		}
		else if("PR_HD".equals(slipType)) //[임대]운송비정산
		{
			responseData = fRegTransAmtPRSlip(SAPUtils.convertMap2DataSet(requestMap), batchCtx);
		}
    	
    	//잌대 자산
		else if("PR_AP".equals(slipType)) //[임대] 자산취득
		{
			responseData = this.fRegAgentAmtAPPRSlip(SAPUtils.convertMap2DataSet(requestMap), batchCtx);
		}
		else if("PR_AC".equals(slipType)) //[임대]자산감가상각전표
		{
			responseData = fRegAssetDepreciationPRSlip(SAPUtils.convertMap2DataSet(requestMap), batchCtx);
		}
		else if("PR_AS".equals(slipType)) //[임대] 자산매각
		{
			responseData = fRegAssetDisposalPRSlip(SAPUtils.convertMap2DataSet(requestMap), batchCtx);
		}
		else if("PR_AD".equals(slipType)) //[임대] 자산제각
		{
			responseData = fRegAssetRetirementPRSlip(SAPUtils.convertMap2DataSet(requestMap), batchCtx);
		}
    	
    	return responseData;
    	
    }
    
    /**
     * sliptype으로 분기처리
     * @param slipType
     * @param requestMap
     * @param requestData
     * @param batchCtx
     * @return
     */
    private IDataSet postingSlipEachOtherEP(String slipType, Map requestMap, IDataSet requestData, IBatchContext batchCtx)
    {
    	IDataSet responseData = new DataSet();

    	
    	//[재고]--------------------------------------------------------------------------------------
    	if("EP_ZJ".equals(slipType)) //[중고] 재고_확정	
		{
			responseData = fRegStockFixEPSlip(requestData, batchCtx);
		}
    	else if("EP_ZI".equals(slipType)) //[중고] 재고_미착	
    	{
    		responseData = fRegStockYetEPSlip(requestData, batchCtx);
    	}
    	//[재고 취소]--------------------------------------------------------------------------------------
    	else if("EP_OJ".equals(slipType)) //[중고] 재고_확정	_취수
		{
			responseData = fRegStockFixRevEPSlip(requestData, batchCtx);
		}
    	else if("EP_OI".equals(slipType)) //[중고] 재고_미착	_취소
    	{
    		responseData = fRegStockYetRevEPSlip(requestData, batchCtx);
    	}
    	
    	//[판매정산]--------------------------------------------------------------------------------------
    	else if(SAPUtils.in("EP_Y", slipType)) //[중고] 국,내외 판매	
		{
    		responseData = fRegInOutSaleEPSlip(requestData, batchCtx);
		}
        else if(SAPUtils.in("EP_D", slipType)) //[중고] 원가전표   
        {
            responseData = fRegCostEPSlip(requestData, batchCtx);
        }
    	//[SKT&대리점 수수료]--------------------------------------------------------------------------------------
    	else if("EP_XA".equals(slipType)) //[중고] 대리점 수수료	
		{
    		IDataSet reqData  = SAPUtils.convertMap2DataSet(requestMap);
    		reqData.putField("SLIP_BU_TYPE", SAP_SLIP_KINDS.AGENCY_COMMISSION_EP);
			responseData = fRegAgencyCommissionEPSlip(reqData, batchCtx);
		}
    	//[단말기대금]--------------------------------------------------------------------------------------
    	else if("EP_XD".equals(slipType)) //[중고] 단말기대금-선할인정산	
		{
    		IDataSet reqData  = SAPUtils.convertMap2DataSet(requestMap);
    		reqData.putField("SLIP_BU_TYPE", SAP_SLIP_KINDS.PREDISC_EP);
			responseData = fRegPrediscEPSlip(reqData, batchCtx);
		}
    	else if("EP_XP".equals(slipType)) //[중고] 단말기대금-요금선납	
		{
    		requestData.putField("SLIP_BU_TYPE", SAP_SLIP_KINDS.PREPAID_EP);
			responseData = fRegPrepaid_ClutTEPSlip(requestData, batchCtx);
		}
    	else if("EP_XR".equals(slipType)) //[중고] 단말기대금-계좌송금	
		{
			responseData = fRegAccttrfEPSlip(requestData, batchCtx);
		}
    	else if("EP_XU".equals(slipType)) //[중고] 단말기대금-E4U	
		{
			responseData = fRegE4UAPEPSlip(requestData, batchCtx);
		}
    	else if("EP_XT".equals(slipType)) //[중고] 단말기대금-요금선납	
		{
    		requestData.putField("SLIP_BU_TYPE", SAP_SLIP_KINDS.CLUBT_EP);
			responseData = fRegPrepaid_ClutTEPSlip(requestData, batchCtx);
		}

    	else if("EP_OR".equals(slipType)) //[중고] 단말기대금-계좌송금	매입취소
    	{
    		responseData = fRegAccttrfRevEPSlip(requestData, batchCtx);
    	}
    	else if("EP_OU".equals(slipType)) //[중고] 단말기대금-E4U	매입취소 
    	{
    		responseData = fRegE4UAPRevEPSlip(requestData, batchCtx);
    	}

    	if("EP_II".equals(slipType)) //[중고] 재고이관	
		{
			responseData = fRegStockEPSlip(requestData, batchCtx);
		}
    	
    	return responseData;
    	
    }
    



		/**
		* sliptable기록
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private String fInqSlipSeq(IDataSet requestData, IBatchContext batchCtx)
		{
			String slipNo = "";
			
			slipNo = ""+Calendar.getInstance().getTimeInMillis();
			slipNo = "DMS" + slipNo.substring(slipNo.length() -7 ,slipNo.length());
			
			SAPUtils.debug("fInqSlipSeq requestData :"+ requestData);

			IRecord rec  = dbSelectSingle("SSlipSeq", new HashMap());
			
			slipNo = rec.get(SlipConstants.SLIP_NO);
			
			this.dmsSlipSeq = slipNo;
			
			return slipNo;
		}
		
		
		/**
		* 국,해외판매
		* SELL_CL_CD 10 국내, 20 해외
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegInOutSaleEPSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			SAPUtils.debug("fRegInOutSaleEPSlip() requestData :"+ requestData);

			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "INVE_PCOST_SLIP_NO"))
				rs.addHeader(new RecordHeader("INVE_PCOST_SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));
            if(!SAPUtils.hasHeaderName(rs, "USER_ID"))
                rs.addHeader(new RecordHeader("USER_ID"));  			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_ST"))
				rs.addHeader(new RecordHeader("SLIP_ST"));	
			if(!SAPUtils.hasHeaderName(rs, "SALE_AMT"))
				rs.addHeader(new RecordHeader("SALE_AMT"));	
			
			InsalesEPSlip insalesOne;
			OutsalesEPSlip outsalesOne;
			
			IRecord tmpRec = null;
			String sellType = null;
			String sellClCd = null; //국내해외 세금코드
			String sellDtm  = null; 
			String evdcDt  = null; 
			String strAmt    = null;
			String taxAmt    = null;
			String foreignAmt = null;
			String costAmt  = null;
			String sellUprcAmt  = null; //공급가액
			String custCd  = null;
			String custNm  = null;
			String slipType = null;
			String currencyType =null;
			String currencyDate =null;
			
			for(int i=0;i<rs.getRecordCount();i++)
			{
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				
				tmpRec = rs.getRecord(i);
				//SELL_PRC   //판매액
				//SELL_PCOST //판매원가
				//XRATE_APPLY_AMT //외화금액
				//SELL_CL_CD //10국내 A9
				sellType    =  StringUtils.nvl(rs.get(i, "SELL_TYP")) ; //판매구분  SELL_TYP = '03' 개인
				sellClCd    =  StringUtils.nvl(rs.get(i, "SELL_CL_CD")) ;
				sellDtm     =  StringUtils.nvl(rs.get(i, "SELL_DTM")) ;
				slipType    =  StringUtils.nvl(rs.get(i, "SLIP_TYPE")) ; 
				strAmt      =  StringUtils.nvlEmpty(rs.get(i, "SELL_PRC"), "0") ;   //판매금액(부가세 포함)
				costAmt     =  StringUtils.nvlEmpty(rs.get(i, "SELL_PCOST"), "0") ; //원가이다.
				sellUprcAmt     =  StringUtils.nvlEmpty(rs.get(i, "SELL_UPRC"), "0") ; //공급가액
				foreignAmt  =  StringUtils.nvlEmpty(rs.get(i, "XRATE_APPLY_AMT"), "0") ;
				custCd      =  StringUtils.nvl(rs.get(i, "SALEPLC_BLICENS_NO")) ;
				custNm      =  StringUtils.nvl(rs.get(i, "SALEPLC_NM")) ;
				taxAmt      =  StringUtils.nvlEmpty(rs.get(i, "SURTAX"), "0") ;   //부가세 
				
				currencyType = StringUtils.nvl(rs.get(i, "CUR_CL_CD")) ;
				currencyDate = StringUtils.nvl(rs.get(i, "XRATE_APPLY_DT")) ;
				
				evdcDt = (sellDtm.length()>8)?sellDtm.substring(0,8):sellDtm;

				
				if("EP_YE".equals(slipType))
				{
					insalesOne = new InsalesEPSlip(zserial,userId, null,  evdcDt,  custCd, custNm, bindingSellClCd2TaxCd(sellClCd) ,sellUprcAmt
					                               , isTax2SellClCd(bindingSellClCd2TaxCd(sellClCd))?taxAmt:"0"
					                               , sellType.equals("03"));
					responseData = this.sendSlip(insalesOne.getFunctionName(), insalesOne.getHeader(), insalesOne.getItems());
				}
				else if("EP_YU".equals(slipType))
				{
					outsalesOne = new OutsalesEPSlip(zserial,userId, null, evdcDt,  custCd, custNm, currencyType,currencyDate, foreignAmt);
					responseData = this.sendSlip(outsalesOne.getFunctionName(), outsalesOne.getHeader(), outsalesOne.getItems());
				}
				SAPUtils.debug("fRegInOutSaleEPSlip() responseData :"+ responseData);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				this.sapSlipNo = responseData.getField ("SLIP_NO");
			
				tmpRec.set("SLIP_NO", sapSlipNo);
				tmpRec.set("USER_NO", this.userNo);
				tmpRec.set("SLIP_ST", "10");
				tmpRec.set("SALE_AMT", strAmt);
				tmpRec.set("USER_ID", userId);
				
				dbUpdate("UEPEqpSale", SAPUtils.convertRecord2Map(tmpRec));
				
				//webservice 방식
//				String hisk_doc_id = sendTaxBillToHiSk(SAPUtils.convertRecord2Map(tmpRec));
//				Map hiskMap = SAPUtils.convertRecord2Map(tmpRec);
//				hiskMap.put("EAPRV_SYS_NO", hisk_doc_id);
//				dbUpdate("USlipInfo", hiskMap);
				//http://hpwapv.dskcc.net/Approval/Document/DocFrame?DocID=024E0755176Z&HostAuth=Y
				
				rs.setRecord(i, tmpRec);
			}
			
			return responseData;
		}
		
	  /**
        * 국,해외 원가전표
        * SELL_CL_CD 10 국내, 20 해외
        *   
        * @param requestData
        * @param batchCtx
        * @return
        */
        private IDataSet fRegCostEPSlip(IDataSet requestData, IBatchContext batchCtx) {
            
            IDataSet responseData = new DataSet();
            
            String zserial = "";
            String userId  = fLoginId(this.userNo, batchCtx);
            
            IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
            
            SAPUtils.debug("fRegInOutSaleEPSlip() requestData :"+ requestData);

            if(rs==null || rs.getRecordCount()==0) return responseData;
            
            if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
                rs.addHeader(new RecordHeader("SLIP_NO"));
            if(!SAPUtils.hasHeaderName(rs, "INVE_PCOST_SLIP_NO"))
                rs.addHeader(new RecordHeader("INVE_PCOST_SLIP_NO"));
            if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
                rs.addHeader(new RecordHeader("USER_NO"));
            if(!SAPUtils.hasHeaderName(rs, "USER_ID"))
                rs.addHeader(new RecordHeader("USER_ID"));              
            if(!SAPUtils.hasHeaderName(rs, "SLIP_ST"))
                rs.addHeader(new RecordHeader("SLIP_ST"));  
            if(!SAPUtils.hasHeaderName(rs, "SALE_AMT"))
                rs.addHeader(new RecordHeader("SALE_AMT")); 
            
            IncostEPSlip   incostOne;
            IncostEPSlip   outcostOne;
            
            IRecord tmpRec = null;
            String sellType = null;
            String slipNo   = null;
            String sellClCd = null; //국내해외 세금코드
            String sellDtm  = null; 
            String evdcDt  = null; 
            String strAmt    = null;
            String taxAmt    = null;
            String foreignAmt = null;
            String costAmt  = null;
            String sellUprcAmt  = null; //공급가액
            String custCd  = null;
            String custNm  = null;
            String slipType = null;
            String currencyType =null;
            String currencyDate =null;
            
            for(int i=0;i<rs.getRecordCount();i++)
            {
                
                zserial = this.fInqSlipSeq(requestData, batchCtx);
                
                tmpRec = rs.getRecord(i);
                //SELL_PRC   //판매액
                //SELL_PCOST //판매원가
                //XRATE_APPLY_AMT //외화금액
                //SELL_CL_CD //10국내 A9
                slipNo      =  StringUtils.nvl(rs.get(i, "SLIP_NO")) ; //판매구분  SELL_TYP = '03' 개인
                sellType    =  StringUtils.nvl(rs.get(i, "SELL_TYP")) ; //판매구분  SELL_TYP = '03' 개인
                sellClCd    =  StringUtils.nvl(rs.get(i, "SELL_CL_CD")) ;
                sellDtm     =  StringUtils.nvl(rs.get(i, "SELL_DTM")) ;
                slipType    =  StringUtils.nvl(rs.get(i, "SLIP_TYPE")) ; 
                strAmt      =  StringUtils.nvlEmpty(rs.get(i, "SELL_PRC"), "0") ;   //판매금액(부가세 포함)
                costAmt     =  StringUtils.nvlEmpty(rs.get(i, "SELL_PCOST"), "0") ; //원가이다.
                sellUprcAmt     =  StringUtils.nvlEmpty(rs.get(i, "SELL_UPRC"), "0") ; //공급가액
                foreignAmt  =  StringUtils.nvlEmpty(rs.get(i, "XRATE_APPLY_AMT"), "0") ;
                custCd      =  StringUtils.nvl(rs.get(i, "SALEPLC_BLICENS_NO")) ;
                custNm      =  StringUtils.nvl(rs.get(i, "SALEPLC_NM")) ;
                taxAmt      =  StringUtils.nvlEmpty(rs.get(i, "SURTAX"), "0") ;   //부가세 
                
                currencyType = StringUtils.nvl(rs.get(i, "CUR_CL_CD")) ;
                currencyDate = StringUtils.nvl(rs.get(i, "XRATE_APPLY_DT")) ;
                
                evdcDt = (sellDtm.length()>8)?sellDtm.substring(0,8):sellDtm;

                //원가전표
                if("EP_YE".equals(slipType))
                {
                    incostOne = new IncostEPSlip(SAP_SLIP_KINDS.INCOST_EP, zserial,userId, null, evdcDt, costAmt, slipNo,sellType.equals("03"));
                    responseData = this.sendSlip(incostOne.getFunctionName(), incostOne.getHeader(), incostOne.getItems());
                }
                else if("EP_YU".equals(slipType))
                {
                    outcostOne = new IncostEPSlip(SAP_SLIP_KINDS.OUTCOST_EP, zserial,userId, null, evdcDt , costAmt, slipNo,false);
                    responseData = this.sendSlip(outcostOne.getFunctionName(), outcostOne.getHeader(), outcostOne.getItems());
                }
                SAPUtils.debug("fRegInOutSaleEPSlip() responseData :"+ responseData);
                
                //slip table update
                responseData.putField("FISCL_SYS_SEQ", zserial);
                this.fRegSlipData(responseData, batchCtx);
                
                this.sapSlipNo = responseData.getField ("SLIP_NO");
//            
                tmpRec.set("INVE_PCOST_SLIP_NO", sapSlipNo);
                tmpRec.set("USER_NO", this.userNo);
//                tmpRec.set("SLIP_ST", "10");
//                tmpRec.set("SALE_AMT", strAmt);
//                
                dbUpdate("UEPEqpSale", SAPUtils.convertRecord2Map(tmpRec));
                
                rs.setRecord(i, tmpRec);
            }
            
            return responseData;
        }
	        
		
		
		/**
		 * 판매구분에 따라 세금코드를 돌려준다.
		 * bindingSellClCd2TaxCd
		 *     ,  OUTTAX10        ("A0") //매출부가세(10%)-세금계산서(일반) 10
               ,  OUTTAX0_LOCAL   ("A1") //매출부가세(0%)-세금계산서(영세율-local) 11
              ,  OUTTAX10_DIRECT ("A9") //매출부가세(10%)-세금계산서(기타수익)  19
		 * @param taxCd
		 * @return String
		 */
		private boolean isTax2SellClCd(String taxCd)
		{
		    boolean taxFlag = false;
		    if(   SAP_TAX_CD.OUTTAX10.getCode().equals(taxCd)
		       || SAP_TAX_CD.OUTTAX10_DIRECT.getCode().equals(taxCd)
		       ) //OUTTAX10        ("A0") //매출부가세(10%)-세금계산서(일반) 10
		    {
		        taxFlag = true;
		    }
		    return taxFlag;
		}
		
	      /**
         * 판매구분에 따라 세금코드를 돌려준다.
         * bindingSellClCd2TaxCd
         *     ,  OUTTAX10        ("A0") //매출부가세(10%)-세금계산서(일반) 10
               ,  OUTTAX0_LOCAL   ("A1") //매출부가세(0%)-세금계산서(영세율-local) 11
              ,  OUTTAX10_DIRECT ("A9") //매출부가세(10%)-세금계산서(기타수익)  19
         * @param SellClCd
         * @return String
         */
        private String bindingSellClCd2TaxCd(String SellClCd)
        {
            String taxCd = SAP_TAX_CD.OUTTAX10.getCode();
            if("10".equals(SellClCd)) //OUTTAX10        ("A0") //매출부가세(10%)-세금계산서(일반) 10
            {
                taxCd = SAP_TAX_CD.OUTTAX10.getCode();
            }
            else if("11".equals(SellClCd)) //OUTTAX0_LOCAL   ("A1") //매출부가세(0%)-세금계산서(영세율-local) 11
            {
                taxCd = SAP_TAX_CD.OUTTAX0_LOCAL.getCode();
            }
            else if("19".equals(SellClCd)) //OUTTAX10_DIRECT ("A9") //매출부가세(10%)-세금계산서(기타수익)  19
            {
                taxCd = SAP_TAX_CD.OUTTAX10_DIRECT.getCode();
            }
            return taxCd;
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
                    context.getLogger().debug("updateRecordHandler()"+upsertStmt+" : " + SAPUtils.convertRecord2Map(row));
                    SAPUtils.debug("updateRecordHandler()"+upsertStmt+" : " + SAPUtils.convertRecord2Map(row));
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
	     * 하이에스케이 시스템에 세금계산서관련 전문을 발송한다.
	     * @param sipNo 전표번호
	     */
	    private String sendTaxBillToHiSk(Map<String, Object> taxInfoMap) {
	        String message = null;
	        String doc_id = "";
	        
	        SAPUtils.debug("taxInfoMap:  " + taxInfoMap);

	        try 
	        {
	            HashMap<String, Object> map = new HashMap<String, Object>();

	            // 해외인보이스 
	            if ("EP_YU".equals(taxInfoMap.get("SLIP_TYPE"))) {
	                map.put("doc_type", "FI02");//빌링요청
	            } else {
	                map.put("doc_type", "FI01");//빌링요청
	            }

	            map.put("doc_id", taxInfoMap.get("SLIP_NO"));
	            map.put("contents", makeXMLDoc(taxInfoMap));
	            map.put("emp_id", SAPUtils.nvl(taxInfoMap, "USER_ID","")  );
	            map.put("matnr", "");
	            SAPUtils.debug("sendTaxBillToHiSk() this.userNo : " + this.userNo);

	            String systemId = BaseUtils.getRuntimeMode();
	            String serviceUrl = "";
	            String namespace = "";
	            String methodName = "";
	            
	            
                SAPUtils.debug("sendTaxBillToHiSk() this.systemId : " +systemId);
                
                //Runtime/EarContent/APP-INF/classes/config/parameter/configure.properties  확인
                serviceUrl = BaseUtils.getConfiguration("approval.service.url."+systemId); 
                namespace =  BaseUtils.getConfiguration("approval.service.namespace."+systemId); 
                methodName = BaseUtils.getConfiguration("approval.service.methodname."+systemId);
	            
	            SAPUtils.debug("map : " + map + "serviceUrl : " + serviceUrl + "namespace : " + namespace + "methodName : " + methodName );

	            // 전자결재 웹서비스를 호출한다.
	            // SaveDocument method의 인수형태로 넘긴다.
	            message = (String) ServiceProxy.CallService(serviceUrl, namespace,  methodName, map);

	            if (message != null && message.indexOf("|") > -1
	                    && message.length() > 3) {
	                String[] rtnArr = message.split("[|]");//정규식 검색
	                String rtnCode = rtnArr[0];
	                String[] msgArr = null;

	                //성공일때 메시지 형식
	                if ("00".equals(rtnCode)) {
	                    msgArr = rtnArr[1].split("=");
	                    doc_id = msgArr[1];
	                }

                    SAPUtils.debug("sendTaxBillToHiSk() HI-SK return msgArr[0] :" + msgArr[0]);
                    SAPUtils.debug("sendTaxBillToHiSk() HI-SK return rtnArr[0] :" + rtnArr[0]);
	                SAPUtils.debug("sendTaxBillToHiSk() HI-SK return message   :" + ArrayUtils.toString(msgArr));
	                SAPUtils.debug("sendTaxBillToHiSk() HI-SK return rtnArr    :" + ArrayUtils.toString(rtnArr));

	                if (!"00".equals(rtnCode)) {
	                    throw new BizRuntimeException("XYZE0000");
	                }
	            }

	        } 
	        catch (Exception e) 
	        {
	            SAPUtils.debug(e.getMessage());
	            throw new BizRuntimeException("XYZE0000");
	        }

	        return doc_id;
	    }
		
		/**
	     * 세금계산서관련 전문 XML 문서를 만든다.
	     * @param param 빌링요청 정보
	     * @return xml 형식의 전문
	     */
	    private String makeXMLDoc(Map<String, Object> param) {
	        StringBuffer xmlDoc = new StringBuffer();

	        // 해외인보이스 항목 추가_2012.12.13
	        if ("EP_YU".equals(SAPUtils.nvl(param, "SLIP_TYPE","") )) {
	            xmlDoc.append("<FI02>\n");
	        } else {
	            xmlDoc.append("<FI01>\n");
	        }

	        //문서번호 : 전표번호
	        xmlDoc.append("\t<UNIQUE_KEY>");
	        xmlDoc.append(SAPUtils.nvl(param, "SLIP_NO","")   );
	        xmlDoc.append("</UNIQUE_KEY>\n");

	        //Billing 요청자 : 기안자
	        xmlDoc.append("\t<EMP_ID>");
	        xmlDoc.append(SAPUtils.nvl(param, "USER_ID","")   );
	        xmlDoc.append("</EMP_ID>\n");

	        
	        //Interface Type
	        xmlDoc.append("\t<INTERFACE_TYPE>");
	        xmlDoc.append("DMS");
	        xmlDoc.append("</INTERFACE_TYPE>\n");

	        //프로젝트 번호
	        xmlDoc.append("\t<PJT_NO>");
	        xmlDoc.append(SAP_WBS_ELEM.ECO_R_BIZ.getCode());
	        xmlDoc.append("</PJT_NO>\n");

	        //프로젝트 명
	        String altered_Pjt_name = SAPUtils.nvl(param, "PJT_NAME","")    ;
	        altered_Pjt_name = altered_Pjt_name.replaceAll("&", "&amp;");
	        altered_Pjt_name = altered_Pjt_name.replaceAll("<", "&lt;").replaceAll(
	                ">", "&gt;");

	        xmlDoc.append("\t<PJT_NAME>");
	        xmlDoc.append(altered_Pjt_name);
	        xmlDoc.append("</PJT_NAME>\n");

	        //고객사 명
	        //특수기호 처리
	        String altered_Cust = SAPUtils.nvl(param, "SALEPLC_NM","");
	        altered_Cust = altered_Cust.replaceAll("&", "&amp;");
	        altered_Cust = altered_Cust.replaceAll("<", "&lt;").replaceAll(">",
	                "&gt;");

	        xmlDoc.append("\t<CUST_NAME>");
	        xmlDoc.append(altered_Cust);
	        xmlDoc.append("</CUST_NAME>\n");

	        //고객사 사업자 등록번호
	        xmlDoc.append("\t<CUST_TAX_NUMBER>");
	        xmlDoc.append(SAPUtils.nvl(param, "SALEPLC_BLICENS_NO","")     );
	        xmlDoc.append("</CUST_TAX_NUMBER>\n");

	        //과세 구분 코드
	        xmlDoc.append("\t<TAX_CODE>");
	        xmlDoc.append(SAPUtils.nvl(param, "TAX_CODE","영세")  );
	        xmlDoc.append("</TAX_CODE>\n");

	        //과세 구분 명
	        xmlDoc.append("\t<TAX_NAME>");
	        xmlDoc.append(SAPUtils.nvl(param, "TAX_NAME","영세")   );
	        xmlDoc.append("</TAX_NAME>\n");

	        //세금계산서 일자
	        xmlDoc.append("\t<TAX_BILL_DAY>");
	        xmlDoc.append(SAPUtils.nvl(param, "TAX_BILL_DAY",DateUtils.getCurrentDate())    );
	        xmlDoc.append("</TAX_BILL_DAY>\n");

	        //공급가액 - 외화구분이 KRW가 아닌경우 외화 금액을 보냅니다
	        xmlDoc.append("\t<BILLING_REQ_AMT>");
            if ("EP_YU".equals(param.get("SLIP_TYPE"))) {
                xmlDoc.append(SAPUtils.nvl(param, "XRATE_APPLY_AMT","0")     );
            }else{
                xmlDoc.append(SAPUtils.nvl(param, "SELL_PRC","0")     );
            }
            xmlDoc.append("</BILLING_REQ_AMT>\n");
	           
	        if ("EP_YU".equals(param.get("SLIP_TYPE"))) {
	            //외화 구분 
	            xmlDoc.append("\t<FRCR_DS_CD>");
	            xmlDoc.append(SAPUtils.nvl(param, "FRCR_DS_CD","0")     );
	            xmlDoc.append("</FRCR_DS_CD>\n");
	        }else{
	            //세액 - FI02는 세액을 제거 FI01을 원래 그대로 변경 없음.
	            xmlDoc.append("\t<TAX_COST>");
	            xmlDoc.append(SAPUtils.nvl(param, "TAX_COST","0")     );
	            xmlDoc.append("</TAX_COST>\n");
	        }

	        //요청빌링사유구분
	        xmlDoc.append("\t<CLL_BLLG_RSN_DS_NM>");
	        xmlDoc.append(SAPUtils.nvl(param, "CLL_BLLG_RSN_DS_NM","중고폰판매")      );
	        xmlDoc.append("</CLL_BLLG_RSN_DS_NM>\n");

	        // 해외인보이스 항목 추가_2012.12.13
	        if ("EP_YU".equals(SAPUtils.nvl(param, "SLIP_TYPE","")      )) {
	            xmlDoc.append("</FI02>");
	        } else {
	            xmlDoc.append("</FI01>");
	        }

	        SAPUtils.debug("생성된 XML 파일 : " + xmlDoc);

	        return xmlDoc.toString();
	    }
		
		/**
		* 대리점 수수료
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegAgencyCommissionEPSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = requestData;
			SAPUtils.debug("fRegAgencyCommissionEPSlip() requestData :"+ requestData);
			dbSelect("SEPIncenxcl", requestData.getFieldMap(), fRegAgencyCommissionEPSlipRH(batchCtx), batchCtx); //DB 조회
			return responseData;
		}
		
	     
	    /**
	     * 대리점 수수료 처리
	     * @param context
	     * @return
	     */
	     public AbsRecordHandler fRegAgencyCommissionEPSlipRH(IBatchContext context) {
	     	AbsRecordHandler rh = new AbsRecordHandler(context) {
	 			
	 			@Override
	 			public void handleRecord(IRecord row) {
	 				
	 				context.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시
	 				SAPUtils.debug("fRegAgencyCommissionEPSlipRH row : " + row);
	 				
	 		    	Map<String, String> paramMap = new HashMap<String, String>();
	 		     	paramMap.putAll(context.getInParameters());
	 				
	 				String zserial = "";
	 				String userId  =  fLoginId(userNo, context);
	 				
	 				IDataSet requestData = SAPUtils.convertMap2DataSet(paramMap);
	 				
	 				AgencyCommissionEPSlip one;
	 				
	 				String netAmt    = null;
	 				String allAmt     = null;
	 				String taxAmt     = null;
	 				String custCd  = null;
	 				
	 				
 					zserial = fInqSlipSeq(requestData, context);
 					
 					
 					allAmt      =  StringUtils.nvlEmpty(row.get("INCEN_AMT"), "0") ;
 					netAmt      =  StringUtils.nvlEmpty(row.get("SPLY_PRC"), "0") ;
 					taxAmt      =  StringUtils.nvlEmpty(row.get("SURTAX_AMT"), "0") ;
 					custCd      =  StringUtils.nvl(row.get("INCEN_BIZ_REG_NO")) ;
 					
 					one = new AgencyCommissionEPSlip(zserial,userId, null, custCd, netAmt,taxAmt, allAmt);
 					IDataSet responseData = sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
 					SAPUtils.debug("fRegAgencyCommissionEPSlipRH() responseData :"+ responseData);
 					
 					//slip table update
 					responseData.putField("FISCL_SYS_SEQ", zserial);
 					fRegSlipData(responseData, context);
 					
 					sapSlipNo = responseData.getField ("SLIP_NO");
 					
 					Map uMap = SAPUtils.convertRecord2Map(row);
 		
 					uMap.put("SLIP_NO", sapSlipNo);
 					uMap.put("USER_NO", userNo);
 					
 					SAPUtils.debug("UEPIncenxcl () paramMap:"+uMap);
 					dbUpdate("UEPIncenxcl", uMap);
 					
	 				processCnt++;
	 				
	 			}
	 		};
	     	return rh;
	     }
	     
		
		/**
		* 단말기대금_선할인
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegPrediscEPSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = requestData;
			SAPUtils.debug("fRegPrediscEPSlip() requestData :"+ requestData);
			dbSelect("SEPdcinad", requestData.getFieldMap(), fRegPrediscEPSlipRH(batchCtx), batchCtx); //DB 조회
			return responseData;
		}
		
	     
	    /**
	     * 처리
	     * @param context
	     * @return
	     */
	     public AbsRecordHandler fRegPrediscEPSlipRH(IBatchContext context) {
	     	AbsRecordHandler rh = new AbsRecordHandler(context) {
	 			
	 			@Override
	 			public void handleRecord(IRecord row) {
	 				
	 				context.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시
	 				SAPUtils.debug("fRegPrediscEPSlipRH row : " + row);
	 				
	 		    	Map<String, String> paramMap = new HashMap<String, String>();
	 		     	paramMap.putAll(context.getInParameters());
	 				
	 				String zserial = "";
	 				String userId  =  fLoginId(userNo, context);
	 				
	 				IDataSet requestData = SAPUtils.convertMap2DataSet(paramMap);
	 				
	 				PrediscEPSlip one;
	 				
	 				String strAmt    = null;
	 				String allAmt     = null;
	 				String custCd  = null;
	 				
	 				
 					zserial = fInqSlipSeq(requestData, context);
 					
 					
 					strAmt      =  StringUtils.nvlEmpty(row.get("SPLY_PRC"), "0") ;
 					allAmt      =  StringUtils.nvlEmpty(row.get("DCINAD_AMT"), "0") ;
 					custCd      =  StringUtils.nvl(row.get("DEALCO_BLICENS_NO")) ;
 					
 					one = new PrediscEPSlip(SAP_SLIP_KINDS.PREDISC_EP,zserial,userId, null, custCd, strAmt,allAmt);
 					IDataSet responseData = sendSlipIgnoreError(one.getFunctionName(), one.getHeader(), one.getItems());
 					SAPUtils.debug("fRegPrediscEPSlip() responseData :"+ responseData);
 					
 					//slip table update
 					responseData.putField("FISCL_SYS_SEQ", zserial);
 					fRegSlipData(responseData, context);
 					
 					sapSlipNo = responseData.getField ("SLIP_NO");
 					
 					Map uMap = SAPUtils.convertRecord2Map(row);
 		
 					uMap.put("SLIP_NO", sapSlipNo);
 					uMap.put("USER_NO", userNo);
 					uMap.put("DCINAD_SLIP_ST", "10");
 					
 					SAPUtils.debug("UEPDcinad () paramMap:"+uMap);
 					dbUpdate("UEPDcinad", uMap);
 					
	 				processCnt++;
	 				
	 			}
	 		};
	     	return rh;
	     }
	     
		
		/**
		* 단말기대금_요금선납 , 클럽T
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegPrepaid_ClutTEPSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			SAPUtils.debug("fRegPrepaid_ClutTEPSlip() requestData :"+ requestData);

			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			PrepaidEPSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			if(!SAPUtils.hasHeaderName(rs, "FEE_PPAY_SLIP_ST"))
				rs.addHeader(new RecordHeader("FEE_PPAY_SLIP_ST"));	
			
			IRecord tmpRec = null;
			String strAmt    = null;
			long   amt       =0;
			String custCd  = null;
			
			zserial = this.fInqSlipSeq(requestData, batchCtx);
			
		
			
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				strAmt      =  StringUtils.nvlEmpty(rs.get(i, "FEE_PPAY_AMT"), "0") ;
				amt        +=  Long.parseLong(strAmt);
				custCd      =  StringUtils.nvl(rs.get(i, "DEALCO_BLICENS_NO")) ;
			}
			one = new PrepaidEPSlip((SAP_SLIP_KINDS) requestData.getObjectField("SLIP_BU_TYPE"),zserial,userId, null, custCd, amt+"");
			responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
			SAPUtils.debug("fRegPrepaid_ClutTEPSlip() responseData :"+ responseData);
			
			//slip table update
			responseData.putField("FISCL_SYS_SEQ", zserial);
			this.fRegSlipData(responseData, batchCtx);
			
			this.sapSlipNo = responseData.getField ("SLIP_NO");
			
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				tmpRec.set("SLIP_NO", sapSlipNo);
				tmpRec.set("USER_NO", this.userNo);
				tmpRec.set("FEE_PPAY_SLIP_ST", "10");
				rs.setRecord(i, tmpRec);
			}
			
			this.fUpdEPFeePPayTable(rs, batchCtx);
			
			return responseData;
		}
		
		/**
		* 단말기대금_계좌송금
		* 날짜별로 전표번호 다름
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegAccttrfEPSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			SAPUtils.debug("fRegAccttrfEPSlip() requestData :"+ requestData);

			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			AccttrfEPSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			if(!SAPUtils.hasHeaderName(rs, "SLIP_ST"))
				rs.addHeader(new RecordHeader("SLIP_ST"));	
			
			IRecord tmpRec = null;
			String strAmt    = null;
			long   amt       =0;
			String custCd  = null;
			
			List<Map> postSlips  = new ArrayList<Map>();
			Map postSlip    = new HashMap();
			List<String> postBizSeqNo  = new ArrayList<String>();
			
			String beforeStaDtEndDt = null;
			String staDtEndDt = null;
			long sumAmt    =0;

			
			
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				strAmt       =  StringUtils.nvlEmpty(rs.get(i, "ACCO_RMT_AMT"), "0") ;
				sumAmt    +=  Long.parseLong(strAmt);
				custCd     =  StringUtils.nvlEmpty(rs.get(i, "DEALCO_BLICENS_NO"),"3148216576") ;
				postBizSeqNo.add( rs.get(i, "ACCO_RMT_XCL_NO")) ; 
				beforeStaDtEndDt =  rs.get(i, "XCL_STRD_STA_DT")  +  rs.get(i, "XCL_STRD_END_DT") ;
				
				if(    (i==0 && rs.getRecordCount()==1)  //한줄짜리 
					|| (StringUtils.isEmpty(beforeStaDtEndDt) && StringUtils.isEmpty(staDtEndDt) && !beforeStaDtEndDt.equals(staDtEndDt))
					|| (i>0 && i==rs.getRecordCount()-1)  //마지막줄 
				   )//바뀔때 //마지막
				{
					postSlip.put("AMT", sumAmt+"");
					postSlip.put("CUST_CD", custCd);
					postSlip.put("XCL_NOS", postBizSeqNo);
					postSlips.add(postSlip);
					
					postBizSeqNo = new ArrayList();
					sumAmt = 0;
				}
				
				staDtEndDt = beforeStaDtEndDt;
			}
			
			SAPUtils.debug("fRegAccttrfEPSlip() postSlips :"+ postSlips);
			
			for(int i=0; i<postSlips.size();i++)
			{
				Map slip = (Map) postSlips.get(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
					
				if(StringUtils.isEmpty((String)slip.get("CUST_CD"))) continue;
				
				one = new AccttrfEPSlip(zserial,userId, null,  (String)slip.get("CUST_CD"), (String)slip.get("AMT"));
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				SAPUtils.debug("fRegAccttrfEPSlip() slip :"+ slip);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				this.sapSlipNo = responseData.getField ("SLIP_NO");
				
				slip.put("SLIP_NO", this.sapSlipNo );
				postSlips.set(i, slip);
			}
			
			int updateCnt =0;
			
			for(int i=0; i<postSlips.size();i++)
			{
				Map slip = (Map) postSlips.get(i);
				List list = (List) slip.get("XCL_NOS");
				
				for(Object xclNo :list)
				{
					if(StringUtils.isEmpty((String) slip.get("SLIP_NO"))) continue;
					slip.put("ACCO_RMT_XCL_NO", xclNo);
					slip.put("USER_NO", this.userNo);
					slip.put("SLIP_ST", "10");
					
					SAPUtils.debug("fUpdEPAccRmtTable () slip:"+slip);
					updateCnt = dbUpdate("UEPAccRmt", slip);
					updateCnt +=updateCnt;
					updateCnt = dbUpdate("UEPAccRmtDtl", slip);
				}
			}
			
			return responseData;
		}
		
		/**
		* 단말기대금_계좌송금 매입취소
		* 날짜별로 전표번호 다름
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegAccttrfRevEPSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			SAPUtils.debug("fRegAccttrfRevEPSlip() requestData :"+ requestData);

			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			AccttrfEPSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			if(!SAPUtils.hasHeaderName(rs, "SLIP_ST"))
				rs.addHeader(new RecordHeader("SLIP_ST"));	
			
			IRecord tmpRec = null;
			String strAmt    = null;
			long   amt       =0;
			String custCd  = null;
			
			List<Map> postSlips  = new ArrayList<Map>();
			Map postSlip    = new HashMap();
			List<String> postBizSeqNo  = new ArrayList<String>();
			
			String beforeStaDtEndDt = null;
			String staDtEndDt = null;
			long sumAmt    =0;

			
			
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				strAmt       =  StringUtils.nvlEmpty(rs.get(i, "ACCO_RMT_AMT"), "0") ;
				sumAmt    +=  Long.parseLong(strAmt);
				custCd     =  StringUtils.nvlEmpty(rs.get(i, "DEALCO_BLICENS_NO"),"3148216576") ;
				postBizSeqNo.add( rs.get(i, "ACCO_RMT_XCL_NO")) ; 
				beforeStaDtEndDt =  rs.get(i, "XCL_STRD_STA_DT")  +  rs.get(i, "XCL_STRD_END_DT") ;
				
				if(    (i==0 && rs.getRecordCount()==1)  //한줄짜리 
					|| (StringUtils.isEmpty(beforeStaDtEndDt) && StringUtils.isEmpty(staDtEndDt) && !beforeStaDtEndDt.equals(staDtEndDt))
					|| (i>0 && i==rs.getRecordCount()-1)  //마지막줄 
				   )//바뀔때 //마지막
				{
					postSlip.put("AMT", sumAmt+"");
					postSlip.put("CUST_CD", custCd);
					postSlip.put("XCL_NOS", postBizSeqNo);
					postSlips.add(postSlip);
					
					postBizSeqNo = new ArrayList();
					sumAmt = 0;
				}
				
				staDtEndDt = beforeStaDtEndDt;
			}
			
			SAPUtils.debug("fRegAccttrfRevEPSlip() postSlips :"+ postSlips);
			
			for(int i=0; i<postSlips.size();i++)
			{
				Map slip = (Map) postSlips.get(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
					
				if(StringUtils.isEmpty((String)slip.get("CUST_CD"))) continue;
				
				one = new AccttrfEPSlip(zserial,userId, null,  (String)slip.get("CUST_CD"), (String)slip.get("AMT"), true);
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				SAPUtils.debug("fRegAccttrfRevEPSlip() slip :"+ slip);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				this.sapSlipNo = responseData.getField ("SLIP_NO");
				
				slip.put("SLIP_NO", this.sapSlipNo );
				postSlips.set(i, slip);
			}
			
			int updateCnt =0;
			
			for(int i=0; i<postSlips.size();i++)
			{
				Map slip = (Map) postSlips.get(i);
				List list = (List) slip.get("XCL_NOS");
				
				for(Object xclNo :list)
				{
					if(StringUtils.isEmpty((String) slip.get("SLIP_NO"))) continue;
					slip.put("ACCO_RMT_XCL_NO", xclNo);
					slip.put("USER_NO", this.userNo);
					slip.put("SLIP_ST", "10");
					
					SAPUtils.debug("fUpdEPAccRmtTable () slip:"+slip);
					updateCnt = dbUpdate("UEPAccRmt", slip);
					updateCnt +=updateCnt;
					updateCnt = dbUpdate("UEPAccRmtDtl4Rev", slip);
				}
			}
			
			return responseData;
		}
		
		/**
		* 단말기대금_E4U
		* 날짜별로 전표번호 다름
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegE4UAPEPSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			SAPUtils.debug("fRegE4UAPEPSlip() requestData :"+ requestData);

			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			E4UAPEPSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			if(!SAPUtils.hasHeaderName(rs, "SLIP_ST"))
				rs.addHeader(new RecordHeader("SLIP_ST"));	
			
			IRecord tmpRec = null;
			String strAmt    = null;
			long   amt       =0;
			String custCd  = null;
			
			List<Map> postSlips  = new ArrayList<Map>();
			Map postSlip    = new HashMap();
			List<String> postBizSeqNo  = new ArrayList<String>();
			
			String beforeStaDtEndDt = null;
			String staDtEndDt = null;
			long sumAmt    =0;

			
			
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				strAmt       =  StringUtils.nvlEmpty(rs.get(i, "ACCO_RMT_AMT"), "0") ;
				sumAmt    +=  Long.parseLong(strAmt);
				custCd     =  StringUtils.nvlEmpty(rs.get(i, "DEALCO_BLICENS_NO"),"3148216576") ;
				postBizSeqNo.add( rs.get(i, "ACCO_RMT_XCL_NO")) ; 
				beforeStaDtEndDt =  rs.get(i, "XCL_STRD_STA_DT")  +  rs.get(i, "XCL_STRD_END_DT") ;
				
				
				if(    (i==0 && rs.getRecordCount()==1)  //한줄짜리 
					|| (StringUtils.isEmpty(beforeStaDtEndDt) && StringUtils.isEmpty(staDtEndDt) && !beforeStaDtEndDt.equals(staDtEndDt))
					|| (i>0 && i==rs.getRecordCount()-1)  //마지막줄 
				   )//바뀔때 //마지막
				{
					postSlip.put("AMT", sumAmt+"");
					postSlip.put("CUST_CD", custCd);
					postSlip.put("XCL_NOS", postBizSeqNo);
					postSlips.add(postSlip);
					
					postBizSeqNo = new ArrayList();
					sumAmt = 0;
				}
				
				staDtEndDt = beforeStaDtEndDt;
			}
			
			SAPUtils.debug("fRegE4UAPEPSlip() postSlips :"+ postSlips);
			
			for(int i=0; i<postSlips.size();i++)
			{
				Map slip = (Map) postSlips.get(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
					
				if(StringUtils.isEmpty((String)slip.get("CUST_CD"))) continue;
				
				one = new E4UAPEPSlip(zserial,userId, null,  (String)slip.get("CUST_CD"), (String)slip.get("AMT"));
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				SAPUtils.debug("fRegE4UAPEPSlip() slip :"+ slip);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				this.sapSlipNo = responseData.getField ("SLIP_NO");
				
				slip.put("SLIP_NO", this.sapSlipNo );
				postSlips.set(i, slip);
			}
			
			int updateCnt =0;
			
			for(int i=0; i<postSlips.size();i++)
			{
				Map slip = (Map) postSlips.get(i);
				List list = (List) slip.get("XCL_NOS");
				
				for(Object xclNo :list)
				{
					if(StringUtils.isEmpty((String) slip.get("SLIP_NO"))) continue;
					slip.put("ACCO_RMT_XCL_NO", xclNo);
					slip.put("USER_NO", this.userNo);
					slip.put("SLIP_ST", "10");
					
					SAPUtils.debug("fUpdEPAccRmtTable () slip:"+slip);
					updateCnt = dbUpdate("UEPAccRmt", slip);
					updateCnt +=updateCnt;
					updateCnt = dbUpdate("UEPAccRmtDtl4Rev", slip);
				}
			}
			
			return responseData;
		}

		/**
		* 단말기대금_E4U 매입취소
		* 날짜별로 전표번호 다름
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegE4UAPRevEPSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			SAPUtils.debug("fRegE4UAPRevEPSlip() requestData :"+ requestData);

			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			E4UAPEPSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			if(!SAPUtils.hasHeaderName(rs, "SLIP_ST"))
				rs.addHeader(new RecordHeader("SLIP_ST"));	
			
			IRecord tmpRec = null;
			String strAmt    = null;
			long   amt       =0;
			String custCd  = null;
			
			List<Map> postSlips  = new ArrayList<Map>();
			Map postSlip    = new HashMap();
			List<String> postBizSeqNo  = new ArrayList<String>();
			
			String beforeStaDtEndDt = null;
			String staDtEndDt = null;
			long sumAmt    =0;

			
			
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				strAmt       =  StringUtils.nvlEmpty(rs.get(i, "ACCO_RMT_AMT"), "0") ;
				sumAmt    +=  Long.parseLong(strAmt);
				custCd     =  StringUtils.nvlEmpty(rs.get(i, "DEALCO_BLICENS_NO"),"3148216576") ;
				postBizSeqNo.add( rs.get(i, "ACCO_RMT_XCL_NO")) ; 
				beforeStaDtEndDt =  rs.get(i, "XCL_STRD_STA_DT")  +  rs.get(i, "XCL_STRD_END_DT") ;
				
				
				if(    (i==0 && rs.getRecordCount()==1)  //한줄짜리 
					|| (StringUtils.isEmpty(beforeStaDtEndDt) && StringUtils.isEmpty(staDtEndDt) && !beforeStaDtEndDt.equals(staDtEndDt))
					|| (i>0 && i==rs.getRecordCount()-1)  //마지막줄 
				   )//바뀔때 //마지막
				{
					postSlip.put("AMT", sumAmt+"");
					postSlip.put("CUST_CD", custCd);
					postSlip.put("XCL_NOS", postBizSeqNo);
					postSlips.add(postSlip);
					
					postBizSeqNo = new ArrayList();
					sumAmt = 0;
				}
				
				staDtEndDt = beforeStaDtEndDt;
			}
			
			SAPUtils.debug("fRegE4UAPRevEPSlip() postSlips :"+ postSlips);
			
			for(int i=0; i<postSlips.size();i++)
			{
				Map slip = (Map) postSlips.get(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
					
				if(StringUtils.isEmpty((String)slip.get("CUST_CD"))) continue;
				
				one = new E4UAPEPSlip(zserial,userId, null,  (String)slip.get("CUST_CD"), (String)slip.get("AMT"), true);
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				SAPUtils.debug("fRegE4UAPRevEPSlip() slip :"+ slip);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				this.sapSlipNo = responseData.getField ("SLIP_NO");
				
				slip.put("SLIP_NO", this.sapSlipNo );
				postSlips.set(i, slip);
			}
			
			int updateCnt =0;
			
			for(int i=0; i<postSlips.size();i++)
			{
				Map slip = (Map) postSlips.get(i);
				List list = (List) slip.get("XCL_NOS");
				
				for(Object xclNo :list)
				{
					if(StringUtils.isEmpty((String) slip.get("SLIP_NO"))) continue;
					slip.put("ACCO_RMT_XCL_NO", xclNo);
					slip.put("USER_NO", this.userNo);
					slip.put("SLIP_ST", "10");
					
					SAPUtils.debug("fUpdEPAccRmtTable () slip:"+slip);
					updateCnt = dbUpdate("UEPAccRmt", slip);
					updateCnt +=updateCnt;
					updateCnt = dbUpdate("UEPAccRmtDtl4Rev", slip);
				}
			}
			
			return responseData;
		}
		

		/**
		* 재고이관
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegStockEPSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			SAPUtils.debug("fRegStockEPSlip() requestData :"+ requestData);

			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			List<StockEPSlip> slipList = new ArrayList<StockEPSlip>();
			StockEPSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			if(!SAPUtils.hasHeaderName(rs, "SLIP_ST"))
				rs.addHeader(new RecordHeader("SLIP_ST"));	
			
			IRecord tmpRec = null;
			String amt    = null;
			
		
			
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				amt      =  StringUtils.nvlEmpty(rs.get(i, "INVE_AMT"), "0") ;
				
				one = new StockEPSlip(zserial,userId, null, null, amt);
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				SAPUtils.debug("fRegStockEPSlip() responseData :"+ responseData);

				slipList.add(one);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				this.sapSlipNo = responseData.getField ("SLIP_NO");
				
				Map paramMap = SAPUtils.convertRecord2Map(tmpRec);
				paramMap.put("SLIP_NO", sapSlipNo);
				paramMap.put("USER_NO", this.userNo);
				paramMap.put("INVE_MOV_SLIP_ST", "10");
				
				dbUpdate("UEPInveMovXcl", paramMap);
			}
			
			return responseData;
		}
		
		
		/**
		* 재고정산_확정
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegStockFixEPSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			SAPUtils.debug("fRegStockFixSlip() requestData :"+ requestData);

			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			List<StockFixEPSlip> slipList = new ArrayList<StockFixEPSlip>();
			StockFixEPSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			if(!SAPUtils.hasHeaderName(rs, "INVE_SLIP_ST"))
				rs.addHeader(new RecordHeader("INVE_SLIP_ST"));	
			
			IRecord tmpRec = null;
			String amt    = null;
			String lossAmt = null;
			
			String inve_cl_nm   = null;
			String dedc_typ_cd = null;
			String dedc_typ_nm = null;
			String skn_cl      = null;
			String skn_cl_nm   = null;
            
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				amt      =  StringUtils.nvlEmpty(rs.get(i, "XCL_AMT"), "0") ;
				lossAmt  =  StringUtils.nvlEmpty(rs.get(i, "ASMT_DMG_AMT"), "0") ;
				inve_cl_nm  =  StringUtils.nvlEmpty(rs.get(i, "INVE_CL_NM"), "") ;
				dedc_typ_cd =  StringUtils.nvlEmpty(rs.get(i, "DEDC_TYP_CD"), "") ;
				dedc_typ_nm =  StringUtils.nvlEmpty(rs.get(i, "DEDC_TYP_NM"), "") ;
				skn_cl      =  StringUtils.nvlEmpty(rs.get(i, "SKN_CL"), "") ;
				skn_cl_nm   =  StringUtils.nvlEmpty(rs.get(i, "SKN_CL_NM"), "") ;
                boolean sknClFlag = "04".equals(dedc_typ_cd) || "N".equals(skn_cl);
				
				one = new StockFixEPSlip(zserial,userId, null, null, amt, lossAmt, ((inve_cl_nm.length()>1)?inve_cl_nm.substring(0,1):"")+"_"+dedc_typ_nm+skn_cl_nm,sknClFlag);
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				SAPUtils.debug("fRegStockFixEPSlip() responseData :"+ responseData);

				slipList.add(one);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				this.sapSlipNo = responseData.getField ("SLIP_NO");
				tmpRec.set("SLIP_NO", sapSlipNo);
				tmpRec.set("USER_NO", this.userNo);
				tmpRec.set("INVE_SLIP_ST", "10");
				rs.setRecord(i, tmpRec);
			}
			fUpdEPInveTable(rs, batchCtx, false);
			
			return responseData;
		}
		
		
		/**
		* 재고정산_확정_취소
		*   
		* @param requestData 
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegStockFixRevEPSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			SAPUtils.debug("fRegStockFixRevEPSlip() requestData :"+ requestData);

			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			List<StockFixEPSlip> slipList = new ArrayList<StockFixEPSlip>();
			StockFixEPSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			if(!SAPUtils.hasHeaderName(rs, "INVE_SLIP_ST"))
				rs.addHeader(new RecordHeader("INVE_SLIP_ST"));	
			
			IRecord tmpRec = null;
			String amt    = null;
			String lossAmt = null;
			
			String inve_cl_nm   = null;
            String dedc_typ_cd = null;
            String dedc_typ_nm = null;
            String skn_cl      = null;
            String skn_cl_nm   = null;
		
			
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				amt      =  StringUtils.nvlEmpty(rs.get(i, "XCL_AMT"), "0") ;
				lossAmt  =  StringUtils.nvlEmpty(rs.get(i, "ASMT_DMG_AMT"), "0") ;
				
				inve_cl_nm  =  StringUtils.nvlEmpty(rs.get(i, "INVE_CL_NM"), "") ;
                dedc_typ_cd =  StringUtils.nvlEmpty(rs.get(i, "DEDC_TYP_CD"), "") ;
                dedc_typ_nm =  StringUtils.nvlEmpty(rs.get(i, "DEDC_TYP_NM"), "") ;
                skn_cl      =  StringUtils.nvlEmpty(rs.get(i, "SKN_CL"), "") ;
                skn_cl_nm   =  StringUtils.nvlEmpty(rs.get(i, "SKN_CL_NM"), "") ;
                
                boolean sknClFlag = "04".equals(dedc_typ_cd) || "N".equals(skn_cl);
                
				one = new StockFixEPSlip(zserial,userId, null, null, amt, lossAmt, ((inve_cl_nm.length()>1)?inve_cl_nm.substring(0,1):"")+"_"+dedc_typ_nm+skn_cl_nm,sknClFlag, true);
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				SAPUtils.debug("fRegStockFixRevEPSlip() responseData :"+ responseData);

				slipList.add(one);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				this.sapSlipNo = responseData.getField ("SLIP_NO");
				tmpRec.set("SLIP_NO", sapSlipNo);
				tmpRec.set("USER_NO", this.userNo);
				tmpRec.set("INVE_SLIP_ST", "10");
				rs.setRecord(i, tmpRec);
			}
			fUpdEPInveTable(rs, batchCtx, true);
			
			return responseData;
		}
		
		
		/**
		* 재고정산_미착
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegStockYetEPSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			SAPUtils.debug("fRegStockYetEPSlip() requestData :"+ requestData);

			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			List<StockYetEPSlip> slipList = new ArrayList<StockYetEPSlip>();
			StockYetEPSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			if(!SAPUtils.hasHeaderName(rs, "INVE_SLIP_ST"))
				rs.addHeader(new RecordHeader("INVE_SLIP_ST"));	
			
			IRecord tmpRec = null;
			String amt    = null;
			String inve_cl_nm   = null;
			String dedc_typ_nm = null;
			String skn_cl_nm   = null;
            String headerText = null;
			
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				amt        =  StringUtils.nvlEmpty(rs.get(i, "XCL_AMT"), "0") ;
				inve_cl_nm  =  StringUtils.nvlEmpty(rs.get(i, "INVE_CL_NM"), "") ;
				dedc_typ_nm =  StringUtils.nvlEmpty(rs.get(i, "DEDC_TYP_NM"), "0") ;
				skn_cl_nm   =  StringUtils.nvlEmpty(rs.get(i, "SKN_CL_NM"), "") ;
				headerText = dedc_typ_nm + skn_cl_nm ;
				
				one = new StockYetEPSlip(zserial,userId, null, null, amt, headerText);
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				SAPUtils.debug("fRegStockYetEPSlip() responseData :"+ responseData);

				slipList.add(one);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				this.sapSlipNo = responseData.getField ("SLIP_NO");
				tmpRec.set("SLIP_NO", sapSlipNo);
				tmpRec.set("USER_NO", this.userNo);
				tmpRec.set("INVE_SLIP_ST", "10");
				rs.setRecord(i, tmpRec);
			}
			fUpdEPInveTable(rs, batchCtx, false);
			
			return responseData;
		}

		
		/**
		* 재고정산_미착_취소
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegStockYetRevEPSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			SAPUtils.debug("fRegStockYetRevEPSlip() requestData :"+ requestData);

			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			List<StockYetEPSlip> slipList = new ArrayList<StockYetEPSlip>();
			StockYetEPSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			if(!SAPUtils.hasHeaderName(rs, "INVE_SLIP_ST"))
				rs.addHeader(new RecordHeader("INVE_SLIP_ST"));	
			
			IRecord tmpRec = null;
			String amt    = null;
			
            String inve_cl_nm   = null;
            String dedc_typ_nm = null;
            String skn_cl_nm = null;
            String headerText = null;
			
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				amt      =  StringUtils.nvlEmpty(rs.get(i, "XCL_AMT"), "0") ;

                inve_cl_nm  =  StringUtils.nvlEmpty(rs.get(i, "INVE_CL_NM"), "") ;
                dedc_typ_nm =  StringUtils.nvlEmpty(rs.get(i, "DEDC_TYP_NM"), "") ;
                skn_cl_nm   =  StringUtils.nvlEmpty(rs.get(i, "SKN_CL_NM"), "") ;
                headerText = dedc_typ_nm + skn_cl_nm  ;
                

				
				one = new StockYetEPSlip(zserial,userId, null, null, amt, headerText,  true);
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				SAPUtils.debug("fRegStockYetRevEPSlip() responseData :"+ responseData);

				slipList.add(one);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				this.sapSlipNo = responseData.getField ("SLIP_NO");
				tmpRec.set("SLIP_NO", sapSlipNo);
				tmpRec.set("USER_NO", this.userNo);
				tmpRec.set("INVE_SLIP_ST", "10");
				rs.setRecord(i, tmpRec);
			}
			fUpdEPInveTable(rs, batchCtx, true);
			
			return responseData;
		}

		
		/**
		* 감가제각
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegAssetRetirementSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			
			SAPUtils.debug("fRegAssetRetirementSlip() requestData :"+ requestData);
			
			IRecordSet rs = dbSelectMulti("SDispo", requestData.getFieldMap());
			for(int i=0; i<rs.getRecordCount(); i++)
			{
			    IRecord ir = rs.getRecord(i);
	            SAPUtils.debug("fRegAssetRetirementSlip() ir :"+ SAPUtils.convertRecord2Map(ir));
	            String amt       = StringUtils.nvl(ir.get("ASSET_DISPO_ACNTB_AMT"),"0"); //매입가
	            String prcAmt    = StringUtils.nvl(ir.get("DISPO_AMT"            ),"0"); //감가상각누계액
	            String ldtaAmt   = StringUtils.nvl(ir.get("ASSET_DISPO_REM_AMT"  ),"0"); //잔존가
	            String allWinAmt = StringUtils.nvl(ir.get("DISPO_PRE_PRFITLS_AMT"),"0"); //충당부채
	            

	            if(Long.parseLong(amt)!= (Long.parseLong(prcAmt)+Long.parseLong(ldtaAmt)+Long.parseLong(allWinAmt)))
	            {
	                long ldataAmtL = Long.parseLong(amt) - Long.parseLong(prcAmt)-Long.parseLong(allWinAmt);
	                ldtaAmt = ldataAmtL+"";
	                ldtaAmt   = StringUtils.nvl(ir.get("DISPO_PRFITLS_AMT"  ),"0"); //잔존가
	            }
	            
	            SAPUtils.debug("fRegAssetRetirementSlip() amt :"+ amt);
	            SAPUtils.debug("fRegAssetRetirementSlip() prcAmt :"+ prcAmt);
	            SAPUtils.debug("fRegAssetRetirementSlip() ldtaAmt :"+ ldtaAmt);
	            SAPUtils.debug("fRegAssetRetirementSlip() allWinAmt :"+ allWinAmt);
	            
	            zserial = this.fInqSlipSeq(requestData, batchCtx);
	            
	            AssetRetirementSlip one = new AssetRetirementSlip(zserial,userId, null, null, amt, prcAmt,  ldtaAmt, allWinAmt);
	            responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());

	            //slip table update
	            responseData.putField("FISCL_SYS_SEQ", zserial);
	            this.fRegSlipData(responseData, batchCtx);
	            
	            this.sapSlipNo = responseData.getField ("SLIP_NO");
	            
	            //XCL_GUBUN=AD, IN_DT=201510
	            Map paramMap = new HashMap();
	            paramMap.putAll(requestData.getFieldMap());
	            paramMap.put("SLIP_NO", this.sapSlipNo);
	            paramMap.put("SLIP_YM", StringUtils.nvl(requestData.getField("ASSET_DISPO_STRD_YM")));
	            
	            SAPUtils.debug("UClsAssetDispo () paramMap:"+paramMap);
	            int uClsDepr = dbUpdate("UClsAssetDispo", paramMap);
	            int uClsDeprDtl= dbUpdate("UClsAssetDispoDtl", paramMap);
	            
	            this.upsertHandler(batchCtx, paramMap, "SAsset4Dispo", "IAssetSlipInfo");
	            this.upsertHandler(batchCtx, paramMap, "SAsset4Dispo", "UEqpAssetDisdt");
			}
			return responseData;
		}
		
		/**
		* 자산매각;
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegAssetDisposalSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			
			SAPUtils.debug("fRegAssetDisposalSlip() requestData :"+ requestData);
			
			IRecord ir = dbSelectSingle("SDispo", requestData.getFieldMap());
			
			SAPUtils.debug("fRegAssetDisposalSlip() ir :"+ SAPUtils.convertRecord2Map(ir));
			
			String amt       = StringUtils.nvl(ir.get("ASSET_DISPO_ACNTB_AMT"),"0"); //매입가
			String prcAmt    = StringUtils.nvl(ir.get("DISPO_AMT"            ),"0"); //감가상각누계액
			String ldtaAmt   = StringUtils.nvl(ir.get("ASSET_DISPO_REM_AMT"  ),"0"); //잔존가
			String allWinAmt = StringUtils.nvl(ir.get("DISPO_PRE_PRFITLS_AMT"),"0"); //충당부채
			
			SAPUtils.debug("fRegAssetDisposalSlip() amt :"+ amt);
			SAPUtils.debug("fRegAssetDisposalSlip() prcAmt :"+ prcAmt);
			SAPUtils.debug("fRegAssetDisposalSlip() ldtaAmt :"+ ldtaAmt);
			SAPUtils.debug("fRegAssetDisposalSlip() allWinAmt :"+ allWinAmt);
			
			zserial = this.fInqSlipSeq(requestData, batchCtx);
			
			AssetDisposalSlip one = new AssetDisposalSlip(zserial,userId, null, null, amt, prcAmt,  ldtaAmt, allWinAmt);
			responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());

			//slip table update
			responseData.putField("FISCL_SYS_SEQ", zserial);
			this.fRegSlipData(responseData, batchCtx);
			
			this.sapSlipNo = responseData.getField ("SLIP_NO");
			
			//XCL_GUBUN=AD, IN_DT=201510
			Map paramMap = new HashMap();
			paramMap.putAll(requestData.getFieldMap());
			paramMap.put("SLIP_NO", this.sapSlipNo);
			paramMap.put("SLIP_YM", StringUtils.nvl(requestData.getField("ASSET_DISPO_STRD_YM")));
			
			SAPUtils.debug("UClsAssetDispo () paramMap:"+paramMap);
			int uClsDepr = dbUpdate("UClsAssetDispo", paramMap);
			int uClsDeprDtl= dbUpdate("UClsAssetDispoDtl", paramMap);
			

			List<String> assetNums = new ArrayList(); 
			IRecordSet irs = dbSelectMulti("SAsset4Dispo", paramMap);
			for(int i=0; i<irs.getRecordCount();i++)
			{
				assetNums.add(irs.get(i, "ASSET_NO"));
			}
			fRegAssetSlipAfter(assetNums, sapSlipNo,  batchCtx, "AS");
			
			return responseData;
		}
		
		
		/**
		* 자산매입(임대);
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegAgentAmtAPPRSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			
			SAPUtils.debug("fRegAgentAmtAPPRSlip() requestData :"+ requestData);
			
			
			
			String custCd = StringUtils.nvl(requestData.getField("DEALCO_BLICENS_NO"),"");
			String netAmt = StringUtils.nvl(requestData.getField("SPLY_PRC"),"0");
			String taxAmt = StringUtils.nvl(requestData.getField("SURTAX_AMT"),"0");
			String headerTxt ="";
			String itmTxt    = "";
			
			
			headerTxt   = this.makeSlipHeaderYM( StringUtils.nvl(requestData.getField("IN_DT"),""))+ " "
				       + " 단말기 정산_" + this.formatBizRegNo(custCd)
			           + "_" +StringUtils.nvl(requestData.getField("TOT_CNT"),"0") + "대"
				       ;

			itmTxt   =    this.makeSlipHeaderYM( StringUtils.nvl(requestData.getField("IN_DT"),"")) + " "
			           + " 단말기 정산_" + this.formatBizRegNo(custCd)
			           ;
				
			
			zserial = this.fInqSlipSeq(requestData, batchCtx);
			
			AgencyAmtAPPRSlip one = new AgencyAmtAPPRSlip(zserial, userId,  null, custCd, netAmt, taxAmt,  headerTxt, itmTxt);
			responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());

			//slip table update
			responseData.putField("FISCL_SYS_SEQ", zserial);
			this.fRegSlipData(responseData, batchCtx);
			
			this.sapSlipNo = responseData.getField ("SLIP_NO");
			
			Map paramMap = new HashMap();
			paramMap.put("USER_NO", StringUtils.nvl(requestData.getField("USER_NO")));
			paramMap.put("SLIP_NO", this.sapSlipNo);
			paramMap.put("OP_CL_CD", "PR");
			paramMap.put("SLIP_YM", StringUtils.nvl(requestData.getField("IN_DT")));
			paramMap.put("PRCHCO_CD", StringUtils.nvl(requestData.getField("PRCHCO_CD")));
			
			SAPUtils.debug("fRegAgentAmtAPPRSlip() paramMap:"+paramMap);
			
			//greatjin

			List<String> assetNums = new ArrayList(); 
			
			IRecordSet ir = dbSelectMulti("SAssetNo4PRSlip", paramMap);
			for(int i=0; i<ir.getRecordCount();i++)
			{
				assetNums.add(ir.get(i, "ASSET_NO"));
				Map uMap = SAPUtils.newMap("SLIP_NO", this.sapSlipNo);
				uMap.putAll(ir.getRecordMap(i));;
				SAPUtils.debug("fRegAgentAmtAPPRSlip() uMap:"+uMap);
				dbUpdate("UPrchSlipInfoByPrchNo", uMap);
				dbUpdate("UEqpAsset", uMap);
			}
			fRegAssetSlipAfter(assetNums, sapSlipNo,  batchCtx, "AP");
			fRegAssetSlipAfter(assetNums, sapSlipNo,  batchCtx, "AA");
			
			return responseData;
		}
		
		
		/**
		* 자산매각(임대);
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegAssetDisposalPRSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			
			SAPUtils.debug("fRegAssetDisposalPRSlip() requestData :"+ requestData);
			
			//TOT_AMT=19289, DISPO_PRFITLS_AMT=0, ASSET_DISPO_ACNTB_AMT=300000, ASSET_DISPO_REM_AMT=280711

			String amt     = StringUtils.nvl(requestData.getField("ASSET_DISPO_REM_AMT"),"0");
			String deprAmt = StringUtils.nvl(requestData.getField("TOT_AMT"),"0");
			String ldtaAmt = StringUtils.nvl(requestData.getField("ASSET_DISPO_ACNTB_AMT"),"0");

			zserial = this.fInqSlipSeq(requestData, batchCtx);
			
			AssetDisposalPRSlip one = new AssetDisposalPRSlip(zserial,userId, null, null, amt, deprAmt, ldtaAmt);
			responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());

			//slip table update
			responseData.putField("FISCL_SYS_SEQ", zserial);
			this.fRegSlipData(responseData, batchCtx);
			
			this.sapSlipNo = responseData.getField ("SLIP_NO");
			
			//XCL_GUBUN=AD, IN_DT=201510
			Map paramMap = new HashMap();
			paramMap.put("USER_NO", StringUtils.nvl(requestData.getField("USER_NO")));
			paramMap.put("SLIP_NO", this.sapSlipNo);
			paramMap.put("OP_CL_CD", "PR");
			paramMap.put("DSPSL_DIS_CL", StringUtils.nvl(requestData.getField("XCL_GUBUN")));
			paramMap.put("SLIP_YM", StringUtils.nvl(requestData.getField("IN_DT")));
			
			SAPUtils.debug("UClsAssetDispo () paramMap:"+paramMap);
			int uClsDepr = dbUpdate("UClsAssetDispo", paramMap);
			int uClsDeprDtl = dbUpdate("UClsAssetDispoDtl", paramMap);
			

			List<String> assetNums = new ArrayList(); 
			IRecordSet ir = dbSelectMulti("SAsset4Dispo", paramMap);
			for(int i=0; i<ir.getRecordCount();i++)
			{
				assetNums.add(ir.get(i, "ASSET_NO"));
			}
			fRegAssetSlipAfter(assetNums, sapSlipNo,  batchCtx, "AS");
			
			return responseData;
		}
		
		/**
		* 자산제각(임대);
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegAssetRetirementPRSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			
			SAPUtils.debug("fRegAssetRetirementPRSlip() requestData :"+ requestData);
			
			//TOT_AMT=19289, DISPO_PRFITLS_AMT=0, ASSET_DISPO_ACNTB_AMT=300000, ASSET_DISPO_REM_AMT=280711

			String amt     = StringUtils.nvl(requestData.getField("ASSET_DISPO_REM_AMT"),"0");
			String deprAmt = StringUtils.nvl(requestData.getField("TOT_AMT"),"0");
			String ldtaAmt = StringUtils.nvl(requestData.getField("ASSET_DISPO_ACNTB_AMT"),"0");

			zserial = this.fInqSlipSeq(requestData, batchCtx);
			
			AssetRetirementPRSlip one = new AssetRetirementPRSlip(zserial,userId, null, null, amt, deprAmt, ldtaAmt);
			responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());

			//slip table update
			responseData.putField("FISCL_SYS_SEQ", zserial);
			this.fRegSlipData(responseData, batchCtx);
			
			this.sapSlipNo = responseData.getField ("SLIP_NO");
			
			//XCL_GUBUN=AD, IN_DT=201510
			Map paramMap = new HashMap();
			paramMap.put("USER_NO", StringUtils.nvl(requestData.getField("USER_NO")));
			paramMap.put("SLIP_NO", this.sapSlipNo);
			paramMap.put("OP_CL_CD", "PR");
			paramMap.put("DSPSL_DIS_CL", StringUtils.nvl(requestData.getField("XCL_GUBUN")));
			paramMap.put("SLIP_YM", StringUtils.nvl(requestData.getField("IN_DT")));
			
			SAPUtils.debug("UClsAssetDispo () paramMap:"+paramMap);
			int uClsDepr = dbUpdate("UClsAssetDispo", paramMap);
			uClsDepr = dbUpdate("UClsAssetDispoDtl", paramMap);
			

			List<String> assetNums = new ArrayList(); 
			IRecordSet ir = dbSelectMulti("SAsset4Dispo", paramMap);
			for(int i=0; i<ir.getRecordCount();i++)
			{
				assetNums.add(ir.get(i, "ASSET_NO"));
			}
			fRegAssetSlipAfter(assetNums, sapSlipNo,  batchCtx, "AD");
			
			return responseData;
		}
		
		/**
	        * 감가상각
	        * ROWNO | DEBT_DEALCO_CD | ORG_NM | DEALCO_BLICENS_NO | DEBT_PRCH_TS | XCL_CL | DEBT_AMT | DEBT_ITM_AMT | DEBT_SURTAX | DEBT_SLIP_NO | DEBT_SLIP_DT | DEBT_XCL_YM
	        *   
	        * @param requestData
	        * @param batchCtx
	        * @return
	        */
	        private IDataSet fRegAssetDepreciationNRSlip(IDataSet requestData, IBatchContext batchCtx) {
	            
	            IDataSet responseData = new DataSet();
	            
	            String zserial = "";
	            String userId  = fLoginId(this.userNo, batchCtx);
	            IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
	            
	            SAPUtils.debug("fRegAssetDepreciationSlip() requestData :"+ requestData);

	            if(rs==null || rs.getRecordCount()==0) return responseData;
	            
	            List<AssetDepreciationNRSlip> slipList = new ArrayList<AssetDepreciationNRSlip>();
	            AssetDepreciationNRSlip one;
	            
	            if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
	                rs.addHeader(new RecordHeader("SLIP_NO"));
	            if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
	                rs.addHeader(new RecordHeader("USER_NO"));  
	            if(!SAPUtils.hasHeaderName(rs, "SLIP_YM"))
	                rs.addHeader(new RecordHeader("SLIP_YM"));
	            if(!SAPUtils.hasHeaderName(rs, "DEPR_DEPT_CD"))
	                rs.addHeader(new RecordHeader("DEPR_DEPT_CD"));
	            
	            IRecord tmpRec = null;
	            String deprAmt = null;
	            String allwnAmt = null;
	            
	            for(int i=0;i<rs.getRecordCount();i++)
	            {
	                tmpRec = rs.getRecord(i);
	                
	                zserial = this.fInqSlipSeq(requestData, batchCtx);
	                deprAmt  =  StringUtils.nvlEmpty(rs.get(i, "DEPR_AMT"), "0") ;
	                allwnAmt  =  StringUtils.nvlEmpty(rs.get(i, "DISPO_PRE_PRFITS_AMT"), "0");
	                
	                one = new AssetDepreciationNRSlip(zserial,userId, null, null, deprAmt , allwnAmt, null);
	                responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
	                SAPUtils.debug("fRegAssetDepreciationSlip() responseData :"+ responseData);

	                slipList.add(one);
	                
	                //slip table update
	                responseData.putField("FISCL_SYS_SEQ", zserial);
	                this.fRegSlipData(responseData, batchCtx);
	                
	                tmpRec.set("SLIP_NO", responseData.getField ("SLIP_NO"));
	                tmpRec.set("USER_NO", this.userNo);
	                tmpRec.set("DEPR_DEPT_CD", "NR");
	                tmpRec.set("SLIP_YM", requestData.getField("DEPR_STRD_YM"));
	                rs.setRecord(i, tmpRec);
	            }
	            fUpdACTable(rs, false, batchCtx);
	            
	            return responseData;
	        }
	        
        /**
         * 미납채권 제각
         *   
         * @param requestData
         * @param batchCtx
         * @return
         */
         private IDataSet fRegBondDesposalNRSlip(IDataSet requestData, IBatchContext batchCtx) {
             
             IDataSet responseData = new DataSet();
             
             String zserial = "";
             String userId  = fLoginId(this.userNo, batchCtx);
             IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
             
             SAPUtils.debug("fRegBondDesposalNRSlip() requestData :"+ requestData);

             if(rs==null || rs.getRecordCount()==0) return responseData;
             
             BondDisposal1NRSlip one;
             BondDisposal2NRSlip two;
             
             if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
                 rs.addHeader(new RecordHeader("SLIP_NO"));
             if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
                 rs.addHeader(new RecordHeader("USER_NO"));  
             if(!SAPUtils.hasHeaderName(rs, "OCCR_YM"))
                 rs.addHeader(new RecordHeader("OCCR_YM"));
             if(!SAPUtils.hasHeaderName(rs, "DEPR_DEPT_CD"))
                 rs.addHeader(new RecordHeader("DEPR_DEPT_CD"));
             
             IRecord tmpRec = null;
             String bizRegNo = null;
             String amt     = null;
             String afbdAmt = null;
             String bdeAmt = null;
             String frInvProcDt =null;
             
             
             for(int i=0;i<rs.getRecordCount();i++)
             {
                 tmpRec = rs.getRecord(i);
                 
                 zserial = this.fInqSlipSeq(requestData, batchCtx);
                 
                 bizRegNo =  StringUtils.nvlEmpty(rs.get(i, "BIZ_REG_NO"), "") ;
                 
                 amt     =  StringUtils.nvlEmpty(rs.get(i, "UNPAID"), "0") ;
                 afbdAmt =  StringUtils.nvlEmpty(rs.get(i, "DMG_ALLWN_AMT"), "0");//대손충당금
                 bdeAmt  =  StringUtils.nvlEmpty(rs.get(i, "DMG_DEPR_AMT"), "0") ;//대손상각비
                 frInvProcDt = StringUtils.nvlEmpty(rs.get(i, "FR_INV_PROC_DT"), "") ;//대손상각비
                 
                 if("0".equals(bdeAmt))
                 {
                     one = new BondDisposal1NRSlip(zserial,userId, null, bizRegNo, amt , null);
                     responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
                 }
                 else
                 {
                     two = new BondDisposal2NRSlip(zserial,userId, null, bizRegNo, amt, afbdAmt,bdeAmt , null);
                     responseData = this.sendSlip(two.getFunctionName(), two.getHeader(), two.getItems());
                 }
                 SAPUtils.debug("fRegBondDesposalNRSlip() responseData :"+ responseData);

                 //slip table update
                 responseData.putField("FISCL_SYS_SEQ", zserial);
                 this.fRegSlipData(responseData, batchCtx);
                 
                 tmpRec.set("SLIP_NO", responseData.getField ("SLIP_NO"));
                 tmpRec.set("USER_NO", this.userNo);
                 tmpRec.set("DEPR_DEPT_CD", "NR");
                 tmpRec.set("OCCR_YM", frInvProcDt.substring(0, 6));
                 SAPUtils.debug("fRegBondDesposalNRSlip()  SAPUtils.convertRecord2Map(tmpRec) :"+  SAPUtils.convertRecord2Map(tmpRec));
                 dbUpdate("UUnpdDisSlip", SAPUtils.convertRecord2Map(tmpRec));
             }
             
             return responseData;
         }
         	        
		
		/**
		* 감가상각
		* ROWNO | DEBT_DEALCO_CD | ORG_NM | DEALCO_BLICENS_NO | DEBT_PRCH_TS | XCL_CL | DEBT_AMT | DEBT_ITM_AMT | DEBT_SURTAX | DEBT_SLIP_NO | DEBT_SLIP_DT | DEBT_XCL_YM
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegAssetDepreciationSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SUM_LIST");
			
			SAPUtils.debug("fRegAssetDepreciationSlip() requestData :"+ requestData);

			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			List<AssetDepreciationNRSlip> slipList = new ArrayList<AssetDepreciationNRSlip>();
			AssetDepreciationNRSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			if(!SAPUtils.hasHeaderName(rs, "SLIP_YM"))
				rs.addHeader(new RecordHeader("SLIP_YM"));
			if(!SAPUtils.hasHeaderName(rs, "DEPR_DEPT_CD"))
				rs.addHeader(new RecordHeader("DEPR_DEPT_CD"));
			
			IRecord tmpRec = null;
			String deprAmt = null;
			String allwnAmt = null;
			
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				deprAmt  =  StringUtils.nvlEmpty(rs.get(i, "DEPR_AMT"), "0") ;
				allwnAmt  =  StringUtils.nvlEmpty(rs.get(i, "ALLWN_AMT"), "0");
				
				one = new AssetDepreciationNRSlip(zserial,userId, null, null, deprAmt , allwnAmt, null);
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				SAPUtils.debug("fRegAssetDepreciationSlip() responseData :"+ responseData);

				slipList.add(one);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				tmpRec.set("SLIP_NO", responseData.getField ("SLIP_NO"));
				tmpRec.set("USER_NO", this.userNo);
				tmpRec.set("DEPR_DEPT_CD", "NR");
				tmpRec.set("SLIP_YM", requestData.getField("DEPR_STRD_YM"));
				rs.setRecord(i, tmpRec);
			}
			fUpdACTable(rs, false, batchCtx);
			
			return responseData;
		}
		
		/**
		* 감가상각취소
		* ROWNO | DEBT_DEALCO_CD | ORG_NM | DEALCO_BLICENS_NO | DEBT_PRCH_TS | XCL_CL | DEBT_AMT | DEBT_ITM_AMT | DEBT_SURTAX | DEBT_SLIP_NO | DEBT_SLIP_DT | DEBT_XCL_YM
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegAssetDepreciationCancelSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			
			SAPUtils.debug("fRegAssetDepreciationCancelSlip() requestData :"+ requestData);

			AssetDepreciationNRSlip one;
			
			IRecord tmpRec = null;
			String deprAmt = null;
			String allwnAmt = null;
			String assetNo  = null;
			
			zserial = this.fInqSlipSeq(requestData, batchCtx);
			deprAmt  =  StringUtils.nvlEmpty(requestData.getField("DEPR_DTL_SUM_AMT"), "0") ;
			allwnAmt  =  StringUtils.nvlEmpty(requestData.getField("DISPO_PRE_PRFITLS_AMT"), "0");
			assetNo   =  StringUtils.nvlEmpty(requestData.getField("ASSET_NO"), "");
			
			one = new AssetDepreciationNRSlip(zserial,userId, null, null, deprAmt , allwnAmt, null,true);
			responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
			SAPUtils.debug("fRegAssetDepreciationCancelSlip() responseData :"+ responseData);

			//slip table update
			responseData.putField("FISCL_SYS_SEQ", zserial);
			this.fRegSlipData(responseData, batchCtx);
			
			requestData.putField("DEPR_CNCL_SLIP_NO", responseData.getField ("SLIP_NO"));
			requestData.putField("USER_NO", this.userNo);
			requestData.putField("DEPR_DEPT_CD", "NR");
			requestData.putField("SLIP_YM", requestData.getField("DEPR_STRD_YM"));
			//requestData.putField("SLIP_YM",requestData.getField("DEPR_END_DT").substring(0, 6));
			requestData.putField("ASSET_NO", assetNo);
			
			fUpdACTable(SAPUtils.convertMap2IRecordSet("REC", requestData.getFieldMap()), true,  batchCtx);
			
			return responseData;
		}
		
		/**
		* 감가상각 (임대)
		* ASK_ID=EPR010, TASK_NM=전표발행, PROC_DT=20151011, FILE_SEQ=01, XCL_NM=자산상각, TOT_CNT=1682, XCL_GUBUN=AC, TOT_AMT=19604433, SLIP_TYPE=PR_AC, IN_DT=201510, PAGE=1, PAGE_SIZE=50, USER_NO=USR000000009, COMPONENTNAME_LOCAL_ONLY=dms.inf.EPR010, TIME=101157, DATE=20151012, DATETIME=20151012101157, PROC_DATE=20151012, BASE_DATE=20151012, RUN_COUNT=1
		*   
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegAssetDepreciationPRSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			SAPUtils.debug("fRegAssetDepreciationPRSlip() requestData :"+ requestData);

			AssetDepreciationPRSlip one;
			
			Map tmpMap = new HashMap();
			String deprAmt = null;
			
			zserial = this.fInqSlipSeq(requestData, batchCtx);
			deprAmt  =  StringUtils.nvlEmpty(requestData.getField("TOT_AMT"), "0") ;
				
			one = new AssetDepreciationPRSlip(zserial,userId, null, null, deprAmt , null);
			responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
			SAPUtils.debug("fRegAssetDepreciationPRSlip() responseData :"+ responseData);
			

			//slip table update
			responseData.putField("FISCL_SYS_SEQ", zserial);
			this.fRegSlipData(responseData, batchCtx);
			
			tmpMap.put("SLIP_NO", responseData.getField ("SLIP_NO"));
			tmpMap.put("USER_NO", this.userNo);
			tmpMap.put("DEPR_DEPT_CD", "PR");
			tmpMap.put("SLIP_YM", requestData.getField("IN_DT"));
			
			dbUpdate("UClsDepr", tmpMap);
			
			//TODO insert asset slip
			
			return responseData;
		}
		
		/**
		* 매출확정
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegSalePRSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			SAPUtils.debug("fRegSalePRSlip() requestData :"+ requestData);

			RentalPRSlip one;
			
			Map tmpMap = requestData.getFieldMap();
			String amt = null;
			String custCd = "";
			
			zserial = this.fInqSlipSeq(requestData, batchCtx);
			amt  =  StringUtils.nvlEmpty(requestData.getField("TOT_AMT"), "0") ;
			SAP_SLIP_KINDS slipKind = (SAP_SLIP_KINDS) requestData.getObjectField("SLIP_BU_TYPE");
				
			one = new RentalPRSlip(slipKind, zserial,userId, null, custCd, amt , null);
			responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
			SAPUtils.debug("fRegSalePRSlip() responseData :"+ responseData);
			

			//slip table update
			responseData.putField("FISCL_SYS_SEQ", zserial);
			this.fRegSlipData(responseData, batchCtx);
			
			tmpMap.put("SLIP_NO", responseData.getField ("SLIP_NO"));
			tmpMap.put("USER_NO", this.userNo);
			tmpMap.put("DEPT_CD", "PR");
			tmpMap.put("SLIP_YM", requestData.getField("IN_DT"));
			
			int updCnt =0;
			
			if(slipKind.equals(SAP_SLIP_KINDS.RENTAL_PR))
			{
				updCnt = dbUpdate("USale", tmpMap);
			}
			else
			{
				updCnt = dbUpdate("UEtcXcl", tmpMap);
			}
			SAPUtils.debug("fRegSalePRSlip() updCnt :"+ updCnt);
			SAPUtils.debug("fRegSalePRSlip() tmpMap :"+ tmpMap);

			
			return responseData;
			
		}
		
		
		/**
		* 운송비정산
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegTransAmtPRSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			SAPUtils.debug("fRegTransAmtPRSlip() requestData :"+ requestData);

			TransAmtPRSlip one;
			
			Map tmpMap = requestData.getFieldMap();
			String amt = null;
			String custCd = "";
			
			zserial = this.fInqSlipSeq(requestData, batchCtx);
			amt  =  StringUtils.nvlEmpty(requestData.getField("TOT_AMT"), "0") ;
				
			one = new TransAmtPRSlip(zserial,userId, null, custCd, amt , null);
			responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
			SAPUtils.debug("fRegTransAmtPRSlip() responseData :"+ responseData);
			

			//slip table update
			responseData.putField("FISCL_SYS_SEQ", zserial);
			this.fRegSlipData(responseData, batchCtx);
			
			tmpMap.put("SLIP_NO", responseData.getField ("SLIP_NO"));
			tmpMap.put("USER_NO", this.userNo);
			tmpMap.put("DEPT_CD", "PR");
			tmpMap.put("SLIP_YM", requestData.getField("IN_DT"));
			
			int updCnt = dbUpdate("UEtcXcl", tmpMap);
			
			SAPUtils.debug("fRegTransAmtPRSlip() updCnt :"+ updCnt);
			SAPUtils.debug("fRegTransAmtPRSlip() tmpMap :"+ tmpMap);

			
			return responseData;
			
		}
		
		
		/**
		 * 매출추정  업데이트
		 * @param recordSet
		 * @param batchCtx
		 * @return
		 */
		private int fUpdAsmptTable(IRecordSet recordSet, String asmptType, IBatchContext batchCtx) 
		{
			int updateCnt = 0;
			if(recordSet==null || recordSet.getRecordCount()==0) return updateCnt;
			
			Map paramMap = null;
			for(int i=0; i<recordSet.getRecordCount(); i++)
			{
				paramMap = recordSet.getRecordMap(i);
				paramMap.put("SALE_ASMPT_CONF_CL", asmptType);
				SAPUtils.debug("fUpdAsmptTable () paramMap:"+paramMap);
				int uClsDepr = dbUpdate("UAsmptSlipInfo", paramMap);
				updateCnt ++;
			}
			return updateCnt;
		}
		
		
		/**
		 * 마감자산상각테이블  업데이트
		 * @param recordSet
		 * @param batchCtx
		 * @return
		 */
		private int fUpdACTable(IRecordSet recordSet, boolean cancelFlag, IBatchContext batchCtx) 
		{
			int updateCnt = 0;
			if(recordSet==null || recordSet.getRecordCount()==0) return updateCnt;
			
			Map paramMap = null;
			for(int i=0; i<recordSet.getRecordCount(); i++)
			{
				paramMap = recordSet.getRecordMap(i);
				SAPUtils.debug("fUpdACTable () paramMap:"+paramMap);
				
				if(!cancelFlag) dbUpdate("UClsDepr", paramMap);
				dbUpdate("UClsDeprDtl", paramMap);
				
				updateCnt ++;
			}
			return updateCnt;
		}

		/**
		 * 계좌송금 테이블 업데이트
		 * @param recordSet
		 * @param batchCtx
		 * @return
		 */
		private int fUpdEPAccRmtTable(IRecordSet recordSet, IBatchContext batchCtx) 
		{
			int updateCnt = 0;
			if(recordSet==null || recordSet.getRecordCount()==0) return updateCnt;
			
			Map paramMap = null;
			for(int i=0; i<recordSet.getRecordCount(); i++)
			{
				paramMap = recordSet.getRecordMap(i);
				SAPUtils.debug("fUpdEPAccRmtTable () paramMap:"+paramMap);
				updateCnt = dbUpdate("UEPAccRmt", paramMap);
				updateCnt +=updateCnt;
			}
			return updateCnt;
		}
		
		
		/**
		 * 요금선납 테이블 업데이트
		 * @param recordSet
		 * @param batchCtx
		 * @return
		 */
		private int fUpdEPFeePPayTable(IRecordSet recordSet, IBatchContext batchCtx) 
		{
			int updateCnt = 0;
			if(recordSet==null || recordSet.getRecordCount()==0) return updateCnt;
			
			Map paramMap = null;
			for(int i=0; i<recordSet.getRecordCount(); i++)
			{
				paramMap = recordSet.getRecordMap(i);
				SAPUtils.debug("fUpdEPFeePPayTable () paramMap:"+paramMap);
				updateCnt = dbUpdate("UEPFeePPay", paramMap);
				updateCnt +=updateCnt;
			}
			return updateCnt;
		}
		
		
		/**
		 * 제고 테이블 업데이트
		 * @param recordSet
		 * @param batchCtx
		 * @return
		 */
		private int fUpdEPInveTable(IRecordSet recordSet, IBatchContext batchCtx, boolean cancelSlipFlag) 
		{
			int updateCnt = 0;
			if(recordSet==null || recordSet.getRecordCount()==0) return updateCnt;
			
			Map paramMap = null;
			for(int i=0; i<recordSet.getRecordCount(); i++)
			{
				paramMap = recordSet.getRecordMap(i);
				SAPUtils.debug("fUpdEPInveTable () paramMap:"+paramMap);
				if(cancelSlipFlag)
				{
					updateCnt = dbUpdate("UEPInve", paramMap);
					updateCnt +=updateCnt;
					updateCnt = dbUpdate("UEPInveDtl4Rev", paramMap);
				}
				else
				{
					updateCnt = dbUpdate("UEPInve", paramMap);
					updateCnt +=updateCnt;
					updateCnt = dbUpdate("UEPInveDtl", paramMap);
				}
				
			}
			return updateCnt;
		}
		
		
		/**
		 * 자산처분테이블업데이트
		 * @param recordSet
		 * @param batchCtx
		 * @return
		 */
		private int fUpdADTable(IRecordSet recordSet, IBatchContext batchCtx) 
		{
			int updateCnt = 0;
			if(recordSet==null || recordSet.getRecordCount()==0) return updateCnt;
			
			Map paramMap = null;
			for(int i=0; i<recordSet.getRecordCount(); i++)
			{
				paramMap = recordSet.getRecordMap(i);
				SAPUtils.debug("fUpdADTable () paramMap:"+paramMap);
				int uClsDepr = dbUpdate("fUpdADTable", paramMap);
				updateCnt ++;
			}
			return updateCnt;
		}
		
		/**
		* 회수수수료
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegReturnCommissionSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			long   debtItmAmt = 0;
			String custCd;
			
			List<ReturnCommissionSlip> slipList = new ArrayList<ReturnCommissionSlip>();
			ReturnCommissionSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			
			IRecord tmpRec = null;
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				debtItmAmt  =  Long.parseLong(StringUtils.nvlEmpty(rs.get(i, "DEBT_ITM_AMT"), "0")) ;
				
				custCd     = tmpRec.get("DEBT_BIZ_REG_NO");
				String slipDt = null;
				
				one = new ReturnCommissionSlip(zserial, userId, slipDt, custCd, debtItmAmt+"");
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				slipList.add(one);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				tmpRec.set("SLIP_NO", responseData.getField ("SLIP_NO"));
				tmpRec.set("USER_NO", this.userNo);
				rs.setRecord(i, tmpRec);
				
				IDataSet paramDS = SAPUtils.convertRecord2DataSet(tmpRec);
				
				paramDS.putField("XCL_ITM_CL", "ER");
				SAPUtils.debug("fUpdBizTable UDebtSlipInfo() paramDS:"+paramDS);
				int uDebtSlipInfo = dbUpdate("UDebtSlipInfo", paramDS.getFieldMap());
				int uXclEtcSlipInfo = dbUpdate("UEtcXcl", paramDS.getFieldMap());
			}
			

			return responseData;
		}
		
		/**
		* 위약금
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegCancelChargeSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			long   debtItmAmt = 0;
			String custCd;
			
			List<CancelChargeSlip> slipList = new ArrayList<CancelChargeSlip>();
			CancelChargeSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			
			IRecord tmpRec = null;
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				debtItmAmt  =  Long.parseLong(StringUtils.nvlEmpty(rs.get(i, "AMT"), "0")) ;
				
				custCd     = tmpRec.get("DEALCO_BLICENS_NO");
				String slipDt = null;
				String refTxt ="";
				
				one = new CancelChargeSlip(zserial, userId, slipDt, custCd, debtItmAmt+"", refTxt);
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				slipList.add(one);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				tmpRec.set("SLIP_NO", responseData.getField ("SLIP_NO"));
				tmpRec.set("USER_NO", this.userNo);
				rs.setRecord(i, tmpRec);
				
				IDataSet paramDS = SAPUtils.convertRecord2DataSet(tmpRec);
				
				
				paramDS.putField("XCL_ITM_CL", tmpRec.get("PEN_POLY_CL"));
				
				SAPUtils.debug("fRegCancelChargeSlip UBondSlipInfo() paramDS:"+paramDS);
				int UBondSlipInfo = dbUpdate("UBondSlipInfo",  paramDS.getFieldMap());
				int uXclEtcSlipInfo = dbUpdate("UEtcXcl", paramDS.getFieldMap());
			}
			

			return responseData;
		}
		
		/**
		* 변상금
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegPenaltyFeeSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			long   debtItmAmt = 0;
			String custCd;
			
			List<PanaltyFeeSlip> slipList = new ArrayList<PanaltyFeeSlip>();
			PanaltyFeeSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			
			IRecord tmpRec = null;
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				debtItmAmt  =  Long.parseLong(StringUtils.nvlEmpty(rs.get(i, "AMT"), "0")) ;
				
				custCd     = tmpRec.get("DEALCO_BLICENS_NO");
				String slipDt = null;
				String refTxt ="";
				
				one = new PanaltyFeeSlip(zserial, userId, slipDt, custCd, debtItmAmt+"", refTxt);
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				slipList.add(one);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				tmpRec.set("SLIP_NO", responseData.getField ("SLIP_NO"));
				tmpRec.set("USER_NO", this.userNo);
				rs.setRecord(i, tmpRec);
				
				IDataSet paramDS = SAPUtils.convertRecord2DataSet(tmpRec);
				
				paramDS.putField("XCL_ITM_CL", tmpRec.get("DMG_STL_INFO_CL"));

                SAPUtils.debug("fRegPenaltyFeeSlip UBondSlipInfo() paramDS:"+paramDS);

				int UBondSlipInfo = dbUpdate("UBondSlipInfo",  paramDS.getFieldMap());
				int uXclEtcSlipInfo = dbUpdate("UEtcXcl", paramDS.getFieldMap());
			}
			

			return responseData;
		}
		
		
		/**
		* 판매수수료
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegSalesCommissionSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			long   debtItmAmt = 0;
			String custCd;
			
			List<SalesCommissionSlip> slipList = new ArrayList<SalesCommissionSlip>();
			SalesCommissionSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			
			IRecord tmpRec = null;
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				debtItmAmt  =  Long.parseLong(StringUtils.nvlEmpty(rs.get(i, "DEBT_ITM_AMT"), "0")) ;
				
				custCd     = tmpRec.get("DEBT_BIZ_REG_NO");
				String slipDt = null;
				
				one = new SalesCommissionSlip(SAP_SLIP_KINDS.SALES_COMMISSION, zserial, userId, slipDt, custCd, debtItmAmt+"");
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				slipList.add(one);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				tmpRec.set("SLIP_NO", responseData.getField ("SLIP_NO"));
				tmpRec.set("USER_NO", this.userNo);
				rs.setRecord(i, tmpRec);
				
				IDataSet paramDS = SAPUtils.convertRecord2DataSet(tmpRec);
				
				paramDS.putField("XCL_ITM_CL", "AS");
				
				SAPUtils.debug("fUpdBizTable UDebtSlipInfo() paramDS:"+paramDS);
				int uDebtSlipInfo = dbUpdate("UDebtSlipInfo",  paramDS.getFieldMap());
				int uXclEtcSlipInfo = dbUpdate("UEtcXcl", paramDS.getFieldMap());
			}
			

			return responseData;
		}
		
        /**
        * 보험료 AR
        * @param requestData
        * @param batchCtx
        * @return
        */
        private IDataSet fRegInsfeeARSlip(IDataSet requestData, IBatchContext batchCtx) {
            
            IDataSet responseData = new DataSet();
            
            String zserial = "";
            String userId  = fLoginId(this.userNo, batchCtx);
            IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
            
            if(rs==null || rs.getRecordCount()==0) return responseData;
            
            long   debtItmAmt = 0;
            String custCd;
            
            List<InsfeeARNRSlip> slipList = new ArrayList<InsfeeARNRSlip>();
            InsfeeARNRSlip one;
            
            if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
                rs.addHeader(new RecordHeader("SLIP_NO"));
            if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
                rs.addHeader(new RecordHeader("USER_NO"));  
            
            IRecord tmpRec = null;
            for(int i=0;i<rs.getRecordCount();i++)
            {
                tmpRec = rs.getRecord(i);
                
                zserial = this.fInqSlipSeq(requestData, batchCtx);
                debtItmAmt  =  Long.parseLong(StringUtils.nvlEmpty(rs.get(i, "M_PRC"), "0")) ;
                
                custCd     =  SAPUtils.hasValue(rs, i, "BIZ_REG_NO")?rs.get(i, "BIZ_REG_NO"): "1208113002";
                String slipDt = null;
                
                one = new InsfeeARNRSlip((SAP_SLIP_KINDS) requestData.getObjectField("SLIP_BU_TYPE") ,zserial, userId, slipDt, custCd, debtItmAmt+"");
                responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
                slipList.add(one);
                
                //slip table update
                responseData.putField("FISCL_SYS_SEQ", zserial);
                this.fRegSlipData(responseData, batchCtx);
                
                tmpRec.set("SLIP_NO", responseData.getField ("SLIP_NO"));
                tmpRec.set("USER_NO", this.userNo);
                rs.setRecord(i, tmpRec);
                
                IDataSet paramDS = SAPUtils.convertRecord2DataSet(tmpRec);
                
                String slipType = StringUtils.nvl(requestData.getField("SLIP_TYPE"),"");
                
                String[] tokens = slipType.split("_");
                
                String xclItmCl = "";
                
                if(tokens.length>0)
                {
                    xclItmCl = tokens[tokens.length-1];
                }
                
                paramDS.putField("XCL_ITM_CL", xclItmCl);
                
                SAPUtils.debug("fUpdBizTable UBondSlipInfo() paramDS:"+paramDS);
                int uDebtSlipInfo = dbUpdate("UBondSlipInfo",  paramDS.getFieldMap());
                int uXclEtcSlipInfo = dbUpdate("UEtcXclByDealcoCd", paramDS.getFieldMap());
            }
            

            return responseData;
        }		
        
        /**
         * 보험금 AR_지급
         * @param requestData
         * @param batchCtx
         * @return
         */
         private IDataSet fRegInsRefundARSlip(IDataSet requestData, IBatchContext batchCtx) {
             
             IDataSet responseData = new DataSet();
             
             String zserial = "";
             String userId  = fLoginId(this.userNo, batchCtx);
             IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
             
             if(rs==null || rs.getRecordCount()==0) return responseData;
             
             long   debtItmAmt = 0;
             String custCd;
             
             List<InsRefundARNRSlip> slipList = new ArrayList<InsRefundARNRSlip>();
             InsRefundARNRSlip one;
             
             if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
                 rs.addHeader(new RecordHeader("SLIP_NO"));
             if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
                 rs.addHeader(new RecordHeader("USER_NO"));  
             
             IRecord tmpRec = null;
             for(int i=0;i<rs.getRecordCount();i++)
             {
                 tmpRec = rs.getRecord(i);
                 
                 zserial = this.fInqSlipSeq(requestData, batchCtx);
                 debtItmAmt  =  Long.parseLong(StringUtils.nvlEmpty(rs.get(i, "M_PRC"), "0")) ;
                 
                 custCd     =  SAPUtils.hasValue(rs, i, "BIZ_REG_NO")?rs.get(i, "BIZ_REG_NO"): "1208113002";
                 String slipDt = null;
                 
                 one = new InsRefundARNRSlip((SAP_SLIP_KINDS) requestData.getObjectField("SLIP_BU_TYPE") ,zserial, userId, slipDt, custCd, debtItmAmt+"");
                 responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
                 slipList.add(one);
                 
                 //slip table update
                 responseData.putField("FISCL_SYS_SEQ", zserial);
                 this.fRegSlipData(responseData, batchCtx);
                 
                 tmpRec.set("SLIP_NO", responseData.getField ("SLIP_NO"));
                 tmpRec.set("USER_NO", this.userNo);
                 rs.setRecord(i, tmpRec);
                 
                 IDataSet paramDS = SAPUtils.convertRecord2DataSet(tmpRec);
                 
                 String slipType = StringUtils.nvl(requestData.getField("SLIP_TYPE"),"");
                 
                 String[] tokens = slipType.split("_");
                 
                 String xclItmCl = "";
                 
                 if(tokens.length>0)
                 {
                     xclItmCl = tokens[tokens.length-1];
                 }
                 
                 paramDS.putField("XCL_ITM_CL", xclItmCl);
                 
                 SAPUtils.debug("fUpdBizTable UBondSlipInfo() paramDS:"+paramDS);
                 int uDebtSlipInfo = dbUpdate("UBondSlipInfo",  paramDS.getFieldMap());
                 int uXclEtcSlipInfo = dbUpdate("UEtcXclByDealcoCd", paramDS.getFieldMap());
             }
             

             return responseData;
         }       
		
		/**
		* 수수료 AR
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegCommissionARSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			long   debtItmAmt = 0;
			String custCd;
			
			List<CommissionARNRSlip> slipList = new ArrayList<CommissionARNRSlip>();
			CommissionARNRSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			
			IRecord tmpRec = null;
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				debtItmAmt  =  Long.parseLong(StringUtils.nvlEmpty(rs.get(i, "M_PRC"), "0")) ;
				
				custCd     =  SAPUtils.hasValue(rs, i, "BIZ_REG_NO")?rs.get(i, "BIZ_REG_NO"): "1208113002";
				String slipDt = null;
				
				one = new CommissionARNRSlip((SAP_SLIP_KINDS) requestData.getObjectField("SLIP_BU_TYPE") ,zserial, userId, slipDt, custCd, debtItmAmt+"");
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				slipList.add(one);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				tmpRec.set("SLIP_NO", responseData.getField ("SLIP_NO"));
				tmpRec.set("USER_NO", this.userNo);
				rs.setRecord(i, tmpRec);
				
				IDataSet paramDS = SAPUtils.convertRecord2DataSet(tmpRec);
				
				String slipType = StringUtils.nvl(requestData.getField("SLIP_TYPE"),"");
				
				String[] tokens = slipType.split("_");
				
				String xclItmCl = "";
				
				if(tokens.length>0)
				{
					xclItmCl = tokens[tokens.length-1];
				}
				
				paramDS.putField("XCL_ITM_CL", xclItmCl);
				
				SAPUtils.debug("fUpdBizTable UBondSlipInfo() paramDS:"+paramDS);
				int uDebtSlipInfo = dbUpdate("UBondSlipInfo",  paramDS.getFieldMap());
				int uXclEtcSlipInfo = dbUpdate("UEtcXcl", paramDS.getFieldMap());
			}
			

			return responseData;
		}
		
		/**
		* 수수료 AP
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegCommissionAPSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			long   xclAmt = 0;
			String custCd;
			
			List<SalesCommissionSlip> slipList = new ArrayList<SalesCommissionSlip>();
			SalesCommissionSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			
			IRecord tmpRec = null;
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				xclAmt  =  Long.parseLong(StringUtils.nvlEmpty(rs.get(i, "M_PRC"), "0")) ;
				if(xclAmt==0) xclAmt = Long.parseLong(StringUtils.nvlEmpty(rs.get(i, "XCL_AMT"), "0")) ;
						
				
				custCd     =  SAPUtils.hasValue(rs, i, "BIZ_REG_NO")?rs.get(i, "BIZ_REG_NO"): "1208113002";
				String slipDt = null;
				
				one = new SalesCommissionSlip((SAP_SLIP_KINDS) requestData.getObjectField("SLIP_BU_TYPE") ,zserial, userId, slipDt, custCd, xclAmt+"");
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				slipList.add(one);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				tmpRec.set("SLIP_NO", responseData.getField ("SLIP_NO"));
				tmpRec.set("USER_NO", this.userNo);
				rs.setRecord(i, tmpRec);
				
				IDataSet paramDS = SAPUtils.convertRecord2DataSet(tmpRec);
				
				String slipType = StringUtils.nvl(requestData.getField("SLIP_TYPE"),"");
				
				String[] tokens = slipType.split("_");
				
				String xclItmCl = "";
				
				if(tokens.length>0)
				{
					xclItmCl = tokens[tokens.length-1];
				}
				
				paramDS.putField("XCL_ITM_CL", xclItmCl);
				
				SAPUtils.debug("fUpdBizTable UDebtSlipInfo() paramDS:"+paramDS);
				int uDebtSlipInfo = dbUpdate("UDebtSlipInfo",  paramDS.getFieldMap());
				int uXclEtcSlipInfo = dbUpdate("UEtcXcl", paramDS.getFieldMap());
			}
			

			return responseData;
		}
		
		
		/**
		* 수수료 AP 하나의 전표만
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegCommissionOneFromMAPSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			SAPUtils.debug("fUpdBizTable fRegCommissionOneFromMSlip() userNo:"+userNo);
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			
			long   amt = 0;
			String custCd;
			
			List<CommissionNRSlip> slipList = new ArrayList<CommissionNRSlip>();
			CommissionNRSlip one;
			
			
			SAPUtils.debug("fUpdBizTable fRegCommissionOneFromMSlip() requestData:"+requestData);

			
			
			IRecord tmpRec = null;
				
			zserial = this.fInqSlipSeq(requestData, batchCtx);
			amt  =  Long.parseLong(StringUtils.nvlEmpty(requestData.getField("AMT"), "0")) ;
			
			custCd     =  StringUtils.nvlEmpty(requestData.getField("BIZ_REG_NO"), "501000");
			String slipDt = null;
			
			one = new CommissionNRSlip(SAP_SLIP_KINDS.MBINS_COMMISSION_NR ,zserial, userId, slipDt, custCd, amt+"");
			responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
			slipList.add(one);
			
			//slip table update
			responseData.putField("FISCL_SYS_SEQ", zserial);
			this.fRegSlipData(responseData, batchCtx);
			
			tmpRec.set("SLIP_NO", responseData.getField ("SLIP_NO"));
			tmpRec.set("USER_NO", this.userNo);
			
			IDataSet paramDS = SAPUtils.convertRecord2DataSet(tmpRec);
			
			String slipType = StringUtils.nvl(requestData.getField("SLIP_TYPE"),"");
			
			String[] tokens = slipType.split("_");
			
			String xclItmCl = "";
			
			if(tokens.length>0)
			{
				xclItmCl = tokens[tokens.length-1];
			}
			
			paramDS.putField("XCL_ITM_CL", xclItmCl);
			paramDS.putField("XCL_DEALCO_CD", custCd);
			
			SAPUtils.debug("fUpdBizTable fRegCommissionOneFromMSlip() paramDS:"+paramDS);
			int uDebtSlipInfo = dbUpdate("UDebtSlipInfo",  paramDS.getFieldMap());
			int uXclEtcSlipInfo = dbUpdate("UEtcXcl", paramDS.getFieldMap());
			
			if(SAP_SLIP_KINDS.MBINS_COMMISSION_NR.equals(requestData.getObjectField("SLIP_BU_TYPE")))
			{
				dbUpdate("UInsureCmms", paramDS.getFieldMap());
			}
			

			return responseData;
		}
		
		
		/**
		* 수수료 AP 하나의 전표만
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegCommissionOneFromRSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			SAPUtils.debug("fUpdBizTable fRegCommissionOneFromRSlip() userNo:"+userNo);
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SUM_LIST");
			
			String updateQuery ="";
			String fieldAmt    ="AMT";
			
			SAP_SLIP_KINDS slip = (SAP_SLIP_KINDS) requestData.getObjectField("SLIP_BU_TYPE");
			
			if(SAP_SLIP_KINDS.FPA_COMMISSION_NR.equals(slip))
			{
				updateQuery = "UFpaCmms";
				fieldAmt    = "FPA_CMMS_AMT";
			}
			else if(SAP_SLIP_KINDS.MBINS_COMMISSION_NR.equals(slip))
			{
				updateQuery = "UInsureCmms";
			}

			
			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			long   amt = 0;
			String custCd;
			
			List<CommissionNRSlip> slipList = new ArrayList<CommissionNRSlip>();
			CommissionNRSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			
			SAPUtils.debug("fUpdBizTable fRegCommissionOneFromRSlip() requestData:"+requestData);

			
			
			IRecord tmpRec = null;
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				amt  =  Long.parseLong(StringUtils.nvlEmpty(rs.get(i, fieldAmt), "0")) ;
				
				custCd     =  StringUtils.nvlEmpty(requestData.getField("BIZ_REG_NO"), "501000");
				String slipDt = null;
				
				one = new CommissionNRSlip(slip ,zserial, userId, slipDt, custCd, amt+"");
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				slipList.add(one);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				tmpRec.set("SLIP_NO", responseData.getField ("SLIP_NO"));
				tmpRec.set("USER_NO", this.userNo);
				rs.setRecord(i, tmpRec);
				
				IDataSet paramDS = SAPUtils.convertRecord2DataSet(tmpRec);
				
				String slipType = StringUtils.nvl(requestData.getField("SLIP_TYPE"),"");
				
				String[] tokens = slipType.split("_");
				
				String xclItmCl = "";
				
				if(tokens.length>0)
				{
					xclItmCl = tokens[tokens.length-1];
				}
				
				paramDS.putField("XCL_ITM_CL", xclItmCl);
				paramDS.putField("XCL_DEALCO_CD", custCd);
				if(SAP_SLIP_KINDS.MBINS_COMMISSION_NR.equals(slip))
				{
				    paramDS.putField("YYYYMM", requestData.getField("DEPR_STRD_YM"));
				}
				
				SAPUtils.debug("fUpdBizTable fRegCommissionOneFromRSlip() paramDS:"+paramDS);
				int uDebtSlipInfo = dbUpdate("UDebtSlipInfo",  paramDS.getFieldMap());
				int uXclEtcSlipInfo = dbUpdate("UEtcXcl", paramDS.getFieldMap());
				
	            
				dbUpdate(updateQuery, paramDS.getFieldMap());
				
			}
			

			return responseData;
		}
		
		
		/**
		* 매출확정
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegSaleFixSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_RENTAL_SALE_FIX_SLIP_LIST");
			
			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			long   debtItmAmt = 0;
			String custCd;
			
			List<RentalBillingSlip> slipList = new ArrayList<RentalBillingSlip>();
			RentalBillingSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			
			IRecord tmpRec = null;
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				debtItmAmt  =  Long.parseLong(StringUtils.nvlEmpty(rs.get(i, "MTH_RENTAL_FEE"), "0")) ;
				
				custCd     = tmpRec.get("DEALCO_BLICENS_NO");
				String refTxt = null;
				String slipDt = null;
				
				one = new RentalBillingSlip(zserial, userId, slipDt, custCd, debtItmAmt+"", refTxt);
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				slipList.add(one);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				tmpRec.set("SLIP_NO", responseData.getField ("SLIP_NO"));
				tmpRec.set("USER_NO", this.userNo);
				rs.setRecord(i, tmpRec);
				
			}
			fUpdAsmptTable(rs, "FX", batchCtx);
			
			return responseData;
		}
		
		
		/**
		* 매출추정
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegSaleAsmptSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_SALE_ASMPT_LIST");
			
			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			long   debtItmAmt = 0;
			String custCd;
			
			List<RentalARSlip> slipList = new ArrayList<RentalARSlip>();
			RentalARSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
			
			IRecord tmpRec = null;
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				debtItmAmt  =  Long.parseLong(StringUtils.nvlEmpty(rs.get(i, "MTH_RENTAL_FEE"), "0")) ;
				
				custCd     = tmpRec.get("DEALCO_BLICENS_NO");
				String refTxt = null;
				String slipDt = null;
				
				one = new RentalARSlip(zserial, userId, slipDt, custCd, debtItmAmt+"", refTxt);
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				slipList.add(one);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				tmpRec.set("SLIP_NO", responseData.getField ("SLIP_NO"));
				tmpRec.set("USER_NO", this.userNo);
				rs.setRecord(i, tmpRec);
				
			}
			fUpdAsmptTable(rs, "AS", batchCtx);
			
			return responseData;
		}

		/**
		* 단말기 대금 전표
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegAgentAmtAPSlip(IDataSet requestData, IBatchContext batchCtx) {
			
			IDataSet responseData = new DataSet();
			
			String zserial = "";
			String userId  = fLoginId(this.userNo, batchCtx);
			IRecordSet rs = requestData.getRecordSet("RS_AGN_EQP_STL_LIST");
			
			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			long   debtItmAmt = 0;
			long   debtSurtax = 0;
			String headerTxt;
			String itmTxt;
			String custCd;
			String xclClCd;
			String payAltCd=null;
			
			List<AgencyAmtAPNRSlip> slipList = new ArrayList<AgencyAmtAPNRSlip>();
			AgencyAmtAPNRSlip one;
			
			if(!SAPUtils.hasHeaderName(rs, "SLIP_NO"))
				rs.addHeader(new RecordHeader("SLIP_NO"));
			if(!SAPUtils.hasHeaderName(rs, "USER_NO"))
				rs.addHeader(new RecordHeader("USER_NO"));	
	        if(!SAPUtils.hasHeaderName(rs, "XCL_CL"))
	                rs.addHeader(new RecordHeader("XCL_CL"));  
			
			IRecord tmpRec = null;
			for(int i=0;i<rs.getRecordCount();i++)
			{
				tmpRec = rs.getRecord(i);
				
				zserial = this.fInqSlipSeq(requestData, batchCtx);
				
				custCd     = tmpRec.get("DEALCO_BLICENS_NO");
				debtItmAmt  =  Long.parseLong(StringUtils.nvlEmpty(rs.get(i, "DEBT_ITM_AMT"), "0")) ;
				debtSurtax  =  Long.parseLong(StringUtils.nvlEmpty(rs.get(i, "DEBT_SURTAX"), "0"));
				headerTxt   =    StringUtils.nvlEmpty(rs.get(i, "DEBT_XCL_YM"), "") + " "
						       + StringUtils.nvlEmpty(rs.get(i, "DEBT_PRCH_TS"), "1") + "차" 
					           + " 단말기 정산_" + this.formatBizRegNo(custCd)
					           + "_" +StringUtils.nvlEmpty(rs.get(i, "M_CNT"), "0") + "대"
						       ;

				itmTxt   =    this.makeSlipHeaderYM(rs.get(i, "DEBT_XCL_YM")) + " "
					       + StringUtils.nvlEmpty(rs.get(i, "DEBT_PRCH_TS"), "") + "차" 
				           + " 단말기 정산_" + this.formatBizRegNo(custCd)
				           ;
				
				
				xclClCd     =  tmpRec.get("XCL_ITM_CL");
				
				//지급대체인코드
				if("KN".equals(xclClCd))
				{
					payAltCd=StringUtils.nvlEmpty(tmpRec.get("PROXY_DEALCO_CD"),"1248100718");
					itmTxt+="_SKN정산";
				}
				else
				{
					payAltCd = null;
				}
				
				if(debtItmAmt>=0)
				{
				    one = new AgencyAmtAPNRSlip(zserial,userId, null, custCd , debtItmAmt+"", debtSurtax+"" , payAltCd, headerTxt,itmTxt);
				    tmpRec.set("XCL_CL", "P");
				}
				else
				{
				    one = new AgencyAmtAPNRSlip(zserial,userId, null, custCd , Math.abs(debtItmAmt)+"", Math.abs(debtSurtax)+"" , payAltCd, headerTxt,itmTxt, true);
				    tmpRec.set("XCL_CL", "C");
				}
				responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
				slipList.add(one);
				
				//slip table update
				responseData.putField("FISCL_SYS_SEQ", zserial);
				this.fRegSlipData(responseData, batchCtx);
				
				tmpRec.set("SLIP_NO", responseData.getField ("SLIP_NO"));
				tmpRec.set("USER_NO", this.userNo);
				rs.setRecord(i, tmpRec);
				
				Map paramMap = SAPUtils.convertRecord2Map(tmpRec);
                SAPUtils.debug("fUpdAPTable () paramMap:"+paramMap);
                int uDebtSlipInfo = dbUpdate("UDebtSlipInfo", paramMap);
                SAPUtils.debug("fUpdAPTable uDebtSlipInfo("+uDebtSlipInfo+") paramMap:"+paramMap);
                if("KN".equals(paramMap.get("XCL_ITM_CL")))
                {
                        paramMap.put("PRCH_SLIP_NO", paramMap.get("SLIP_NO"));
                        paramMap.put("CRD_PRCH_SLIP_NO", paramMap.get("SLIP_NO"));
                }
                else
                {
                    if(debtItmAmt>=0)
                    {
                        paramMap.put("PRCH_CASH_SLIP_NO", paramMap.get("SLIP_NO"));
                        paramMap.put("SALE_DAMT_SLIP_NO", paramMap.get("SLIP_NO"));
                    }
                    else
                    {
                        paramMap.put("CNCL_CASH_SLIP_NO", paramMap.get("SLIP_NO"));
                        paramMap.put("SALE_DAMT_CNCL_SLIP_NO", paramMap.get("SLIP_NO"));
                    }
                }
        
                int UPrchSlipInfoAtCustA000 = dbUpdate("UPrchSlipInfoAtCustA000", paramMap);
                SAPUtils.debug("fUpdAPTable UPrchSlipInfoAtCustA000("+UPrchSlipInfoAtCustA000+") paramMap:"+paramMap);
                
                int uSKNCrdSlipInfo = dbUpdate("USKNCrdSlipInfo", paramMap);
                SAPUtils.debug("fUpdAPTable uSKNCrdSlipInfo("+uSKNCrdSlipInfo+") paramMap:"+paramMap);
			}
			
			//fUpdAPTable(rs,  batchCtx);
			fRegAssetAPSlipAfter(rs,  batchCtx, "AP");
			
			return responseData;
		}
	
		/**
		 * 사업자등록번호 포맷팅
		 * @param yyyyMM
		 * @return
		 */
		private String formatBizRegNo(String bizRegNo)
		{
			String returnStr =bizRegNo;
			
			if(StringUtils.isEmpty(returnStr) || returnStr.length()<10) return returnStr;
			
			returnStr =        bizRegNo.substring(0,3) 
					    +"-" + bizRegNo.substring(3,5)
		                +"-" + bizRegNo.substring(5,10)
		                ;
			
			return returnStr;
		}
	
		

		/**
		 * yyyyMM을  yyyy년  MM월
		 * @param yyyyMM
		 * @return
		 */
		private String makeSlipHeaderYM(String yyyyMM)
		{
			String returnStr =yyyyMM;
			
			if(StringUtils.isEmpty(returnStr) || returnStr.length()<6) return returnStr;
			
			returnStr = yyyyMM.substring(0, 4) +"년 " + yyyyMM.substring(4) +"월";
			return returnStr;
		}
	
		
		/**
		* 자산취득 취소전표
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegAssetAACancelSlip(IDataSet requestData,IBatchContext batchCtx) {
			
			log = getLog(batchCtx);
			IDataSet responseData = new DataSet();
			
			String zserial = this.fInqSlipSeq(requestData, batchCtx);
			String userId  = fLoginId(this.userNo, batchCtx);
			String headerTxt ;
			String itemTxt;
			String assetNum;
			
			SAPUtils.debug("fRegAssetAACancelSlip () requestData:"+requestData); 
			
			String eqpPrchAmt ="0";
			String eqpPrchSlip ="";
			
			eqpPrchAmt  = requestData.getField("SPLY_PRC");
			eqpPrchSlip = requestData.getField("ACQR_XCL_SLIP_NO");
			assetNum    = requestData.getField("ASSET_NO");
			
			headerTxt   =    "단말기 취득 취소 /"+ eqpPrchSlip + "";
			itemTxt   =   headerTxt;
			
			AssetAmtAANRSlip one = new AssetAmtAANRSlip(zserial, userId, null, null, eqpPrchAmt+"",headerTxt,itemTxt, true);
			responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
			
			//slip table update
			responseData.putField("FISCL_SYS_SEQ", zserial);
			this.fRegSlipData(responseData, batchCtx);
			
			
			//afterslipposting
			String slipNo = responseData.getField("SLIP_NO");
			
			SAPUtils.debug("fRegAssetAACancelSlip () assetNums:"+assetNum);
			
			fUpdAssetAASlipAfter(SAPUtils.newList(assetNum),slipNo, true, batchCtx);
			
			fRegAssetSlipAfter(SAPUtils.newList(assetNum), slipNo,  batchCtx, "OA");
			
			return responseData;
		}
		

		/**
        * 자산이관
        * @param requestData
        * @param batchCtx
        * @return
        */
        private IDataSet fRegAssetTransferNRSlip(IDataSet requestData,IBatchContext batchCtx) {
            
            log = getLog(batchCtx);
            IDataSet responseData = new DataSet();
            String userId  = fLoginId(this.userNo, batchCtx);
            String headerTxt = "" ;
            String itemTxt   = "";

            //TODO test  why 2?
            //requestData.putField("INV_MOV_REQ_SEQ", "1");
            
            
            SAPUtils.debug("fRegAssetTransferNRSlip () requestData:"+requestData); 
           

            IRecordSet rs = dbSelectMulti("SEqpUsedDspslSum", requestData.getFieldMap());
            
            for(int i=0; i<rs.getRecordCount(); i++)
            {
                String zserial = this.fInqSlipSeq(requestData, batchCtx);
                
                IRecord ir = rs.getRecord(i);
                
                String amt        = SAPUtils.nvl(ir.get("PRCH_PRC"), "0");
                String lossEAmt   = SAPUtils.nvl(ir.get("INVE_ASMT_DMT_AMT"), "0");
                String lossBAmt   = SAPUtils.nvl(ir.get("EQP_BKAG_AMT"), "0");
                String ecoAmt     = SAPUtils.nvl(ir.get("SELL_PRC"), "0");
                String slipOkType = ir.get("SLIP_OK_TYPE");
                
                Map paramMap = requestData.getFieldMap();
                SAPUtils.debug("fRegAssetTransferNRSlip () rs:"+SAPUtils.convertRecord2Map(ir));
                
                //BER에 등급은 잔존가로 넘김
                if("N".equals(slipOkType))
                {
                    lossEAmt = lossBAmt = "0";
                    amt = ecoAmt;
                }
                else //TODO 금액확인
                {
                    amt = (Long.parseLong(lossEAmt)+Long.parseLong(lossBAmt)+Long.parseLong(ecoAmt))+"";
                }
                
                AssetDeptTransferNRSlip one = new AssetDeptTransferNRSlip(zserial, userId, null, null, amt, lossEAmt, lossBAmt, ecoAmt,headerTxt,itemTxt);
                //AssetAmtAANRSlip one = new AssetAmtAANRSlip(zserial, userId, null, null, amt+"",headerTxt,itemTxt);
                responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
                
                //slip table update
                responseData.putField("FISCL_SYS_SEQ", zserial);
                this.fRegSlipData(responseData, batchCtx);
                
                
                //afterslipposting
                String slipNo = responseData.getField("SLIP_NO");
                paramMap.put("SLIP_NO", slipNo);
                paramMap.put("USER_NO", userNo);
                paramMap.put("SLIP_OK_TYPE", slipOkType);
                
                SAPUtils.debug("fRegAssetTransferNRSlip() paramMap:"+paramMap);
                
                this.upsertHandler(batchCtx, paramMap, "SEqpUsedDspsl4UpdateSlip", "UEqpUsedDspslSlip");
    
                this.upsertHandler(batchCtx, paramMap, "SEqpUsedDspsl4UpdateSlip", "IAssetSlipInfo");
            }

            return responseData;
        }
        
		
		/**
		* 자산취득전표
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegAssetAASlip(IDataSet requestData,IBatchContext batchCtx) {
			
			log = getLog(batchCtx);
			IDataSet responseData = new DataSet();
			
			String zserial = this.fInqSlipSeq(requestData, batchCtx);
			String userId  = fLoginId(this.userNo, batchCtx);
			String headerTxt ;
			String itemTxt;
			
			
			IRecordSet rs = requestData.getRecordSet("RS_SLIP_LIST");
			
			SAPUtils.debug("fRegAssetAASlip () rs:"+rs); 
			
			if(rs==null || rs.getRecordCount()==0) return responseData;
			
			//T_CNT, SLIP_NO, SLIP_ST_CD, YYYYMM으로 체크하고, asset_no를 구해온다.
			//CHECK START
			List<String> assetNums = new ArrayList(); 
			for(int i=0; i<rs.getRecordCount();i++)
			{
				IRecordSet rss = dbSelectMulti("SAABeforeCheck", rs.getRecordMap(i));
				SAPUtils.debug("갯수  rs"+rs.getRecordMap(i));
				if(rss!=null) SAPUtils.debug("갯수  rss :"+rss.getRecordCount());				
				if(    rss==null 
				   || rss.getRecordCount()==0
				   || !rs.get(i, "T_CNT").equals(rss.getRecordCount()+"")
				   )
				{
					if(rs!=null)  SAPUtils.debug("갯수가틀림 rs"+rs.getRecordMap(i));
					if(rss!=null)  SAPUtils.debug("갯수가틀림 rss :"+rss.getRecordCount());
					//throw new BizRuntimeException("XYZE0000");
				}
//				 else //TODO exception은 던지지만 전표는 발행되는 것에 대해서 재검토
//				{
//					
//					for(int j=0; j<rss.getRecordCount(); j++)
//					{
//						assetNums.add(rss.get(j, "ASSET_NO"));
//					}
//				}
				if(rss!=null)
				{
					
					for(int j=0; j<rss.getRecordCount(); j++)
					{
						assetNums.add(rss.get(j, "ASSET_NO"));
					}
				}
				
			}
			//CHECK END
			String eqpPrchCnt = "0";
			long   eqpPrchCntAmt = 0;
			String eqpPrchAmt ="0";
			long   eqpPrchSumAmt = 0;
			String yyyymm ="";
			String endYMD ="";
			String ts     = "1";
			
			
			for(int i=0;i<rs.getRecordCount();i++)
			{
				eqpPrchCnt = rs.get(i, "T_CNT");
				eqpPrchCntAmt += Long.parseLong(eqpPrchCnt) ;
				eqpPrchAmt = rs.get(i, "T_PRC");
				eqpPrchSumAmt += Long.parseLong(eqpPrchAmt) ;
				yyyymm = StringUtils.nvlEmpty(rs.get(i, "YYYYMM"), "0");
				ts     = StringUtils.nvlEmpty(rs.get(i, "CHK"), "0");
				endYMD = StringUtils.nvlEmpty(rs.get(i, "END_DT"), "0");
			}
			
			headerTxt   =    yyyymm + " "+ "단말기 매입 "+ ts + "차";
			itemTxt   =   endYMD + " 단말기 "+ eqpPrchCntAmt+ "대 매입" ;
			
			AssetAmtAANRSlip one = new AssetAmtAANRSlip(zserial, userId, null, null, eqpPrchSumAmt+"",headerTxt,itemTxt);
			responseData = this.sendSlip(one.getFunctionName(), one.getHeader(), one.getItems());
			
			//slip table update
			responseData.putField("FISCL_SYS_SEQ", zserial);
			this.fRegSlipData(responseData, batchCtx);
			
			
			//afterslipposting
			String slipNo = responseData.getField("SLIP_NO");
			
			SAPUtils.debug("fRegAssetAASlip () assetNums:"+assetNums); 
			fUpdAssetAASlipAfter(assetNums,slipNo, false, batchCtx);
			
			fRegAssetSlipAfter(assetNums, slipNo,  batchCtx, "AA");
			
			return responseData;
		}
		


		
		/**
		 * 로그인ID가져오기
		 * @param requestData
		 * @param batchCtx
		 * @return
		 */
		private String fLoginId(String userNo, IBatchContext batchCtx)
		{
			String lginId = "06218";
			IRecord rec = dbSelectSingle("SUserInfo", SAPUtils.newMap("USER_NO", userNo));
			if(rec!=null ) lginId = rec.get("EMP_NO");
			return lginId;
		}
		
		/**
		 * 자산 테이블에  취득 전표 번호 업데이트
		 * @param recordSet
		 * @param batchCtx
		 * @return
		 */
		private int fUpdAssetAASlipAfter(List assetNums, String slipNo, boolean cancelFlag, IBatchContext batchCtx) 
		{
			int updateCnt = 0;
			if(assetNums==null || assetNums.size()==0) return updateCnt;
			
			Map paramMap = null;
			for(int i=0; i<assetNums.size(); i++)
			{
				paramMap = new HashMap();
				paramMap.put("ASSET_NO", assetNums.get(i));
				if(cancelFlag)
				{
					paramMap.put("ACQR_XCL_CNCL_SLIP_NO", slipNo);
				}
				else
				{
					paramMap.put("ACQR_XCL_SLIP_NO", slipNo);
				}
				paramMap.put("USER_NO", this.userNo);
				SAPUtils.debug("fUpdAssetAASlipAfter() paramMap:"+paramMap); 
				dbInsert("UEqpAsset",paramMap);
				updateCnt ++;
			}
			return updateCnt;
		}		
		
		

		/**
		 * 단말 자산 전표테이블에 업데이트
		 * @param recordSet
		 * @param batchCtx
		 * @return
		 */
		private int fRegAssetSlipAfter(List assetNums, String slipNo, IBatchContext batchCtx, String AssetSlipClCd) 
		{
			int insertCnt = 0;
			if(assetNums==null || assetNums.size()==0) return insertCnt;
			
			Map paramMap = null;
			for(int i=0; i<assetNums.size(); i++)
			{
				paramMap = new HashMap();
				paramMap.put("ASSET_NO", assetNums.get(i));
				paramMap.put("SLIP_NO", slipNo);
				paramMap.put("ASSET_SLIP_CL_CD", AssetSlipClCd); //자산취득
				paramMap.put("USER_NO", this.userNo);
				SAPUtils.debug("fRegAssetAASlipAfter() paramMap:"+paramMap); 
				dbInsert("IAssetSlipInfo",paramMap);
				insertCnt ++;
			}
			return insertCnt;
		}		
		
		
		
		/**
		 * 단말 자산 전표테이블에 업데이트
		 * @param recordSet
		 * @param batchCtx
		 * @return
		 */
		private int fRegAssetAASlipEachAfter(IRecordSet recordSet, IBatchContext batchCtx, String AssetSlipClCd) 
		{
			int insertCnt = 0;
			if(recordSet==null || recordSet.getRecordCount()==0) return insertCnt;
			
			Map paramMap = null;
			for(int i=0; i<recordSet.getRecordCount(); i++)
			{
				paramMap = recordSet.getRecordMap(i);
				paramMap.put("ASSET_SLIP_CL_CD", AssetSlipClCd); //자산취득
				dbInsert("IAssetSlipInfo",paramMap);
				insertCnt ++;
			}
			return insertCnt;
		}		
		
		/**
		 * 단말 자산 전표테이블에 업데이트
		 * @param recordSet
		 * @param batchCtx
		 * @return
		 */
		private int fRegAssetAPSlipAfter(IRecordSet recordSet, IBatchContext batchCtx, String AssetSlipClCd) 
		{
			log = getLog(batchCtx);
			
			int insertCnt = 0;
			if(recordSet==null || recordSet.getRecordCount()==0) return insertCnt;
			
			Map paramMap = null;
			for(int i=0; i<recordSet.getRecordCount(); i++)
			{
				paramMap = recordSet.getRecordMap(i);
				paramMap.put("ASSET_SLIP_CL_CD", AssetSlipClCd); //자산취득
				
				IRecordSet rs = dbSelectMulti("SAssetNo4Slip", paramMap);
				if(rs!=null)
				{
					for(int j=0 ; j<rs.getRecordCount(); j++)
					{
						String assetNo = rs.get(j, "ASSET_NO");
						paramMap.put("ASSET_NO", assetNo);
						SAPUtils.debug("fRegAssetAPSlipAfter() paramMap :"+ paramMap);
						if("KN".equals(paramMap.get("XCL_ITM_CL")))
						{
							dbInsert("IAssetSlipInfo",paramMap);
						}
					}
					insertCnt ++;
				}
			}
			return insertCnt;
		}
	 

		/**
		* sliptable기록
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private IDataSet fRegSlipData(IDataSet requestData, IBatchContext batchCtx)
		{
			IDataSet responseData = new DataSet();
			requestData.putField("DEAL_TYP_CD", requestData.getField("BU_TYPE"));
			dbInsert("ISlipData", requestData.getFieldMap());
			return responseData;
		}
		
		
		/**
		* TransMessage
		* @param responseData
		* @return
		*/
		private String getMessageFromSAP(IDataSet responseData)
		{
			return this.getMessageFromSAP(responseData, false);
		}
		
		
		/**
		* TransMessage
		* @param responseData
		* @return
		*/
		private String getMessageFromSAP(IDataSet responseData,boolean flagIgnoreError)
		{
			String slipNo  = null;
			IRecordSet  rs  = responseData.getRecordSet(SAP_SLIP_ELEM.SLIP_RETURN.getPart());
			String messageType= rs.get(0, SAP_SLIP_RETURN.MSGTYP.getSapCol());
			if(SlipConstants.SAP_SLIP_RETURN_MSG_TYP_SUCCESS.equals(messageType))
			{
				slipNo     = rs.get(0, SAP_SLIP_RETURN.BELNR.getSapCol());
				this.sapSlipNo = slipNo;
			}
			else
			{
				String message   =  rs.get(0, SAP_SLIP_RETURN.MSG.getSapCol());
				if(!flagIgnoreError) throw new BizRuntimeException("XYZE0000", new String[] {message});
			}
			return slipNo;
		}
		



		/**
		* 해더 맵핑
		* @param rs
		* @return
		*/
		private IRecordSet fMakeItems(CommonSlipItem[] items)
		{
			IRecordSet itDmsHeader = new RecordSet(SAP_SLIP_ELEM.SLIP_ITEM.getPart());
			
			for(SAP_SLIP_ITEM one: SAP_SLIP_ITEM.values())
			{
				itDmsHeader.addHeader(new RecordHeader(one.getSapCol()));
			}
			
			for(CommonSlipItem item:items)
			{
			    IRecord recordContents = itDmsHeader.newRecord();
			    
			    HashMap map = DomainUtils.invokeDomainToMap(item);
			    
				for(SAP_SLIP_ITEM one: SAP_SLIP_ITEM.values())
				{
					recordContents.put(one.getSapCol(), map.get(one.getVar()));
				}
				
			    itDmsHeader.addRecord(recordContents);
			}
			
			return itDmsHeader;
		}


		/**
		* 전표처리
		* @param slip
		* @return
		*/
		private IDataSet sendSlipIgnoreError(String functionName, CommonSlipHeader header, CommonSlipItem[] items)
		{
			IDataSet responseData = new DataSet();
			IDataSet inDataSet = null;
			inDataSet  = new DataSet();
			
			inDataSet.putRecordSet(SAPUtils.makeHeader(header));
			inDataSet.putRecordSet(fMakeItems(items));
			
			List seqKeys= this.fRegSlipLog(functionName, inDataSet, SAPUtils.makeCallInfo());
			
			SapCaller caller = new SapCaller(functionName);
			
			responseData = caller.sendData(inDataSet, SAPUtils.makeCallInfo(),true);
			
			this.fRegSlipResponseLog(seqKeys, responseData, new String[] {SAP_SLIP_ELEM.SLIP_RETURN.getPart()});

			responseData.putFieldMap(SAPUtils.convertRecord2DataSet(responseData.getRecordSet(SAP_SLIP_ELEM.SLIP_RETURN.getPart()).getRecord(0)).getFieldMap());

			responseData.putField("USER_NO", this.userNo);
			responseData.putField(SlipConstants.SLIP_NO, getMessageFromSAP(responseData, true));
			
			return responseData;
		}
		

		/**
		* 전표처리
		* @param slip
		* @return
		*/
		private IDataSet sendSlip(String functionName, CommonSlipHeader header, CommonSlipItem[] items)
		{
			IDataSet responseData = new DataSet();
			IDataSet inDataSet = null;
			inDataSet  = new DataSet();
			
			//fRegSlipLog(functionName, header,items);
			
			inDataSet.putRecordSet(SAPUtils.makeHeader(header));
			inDataSet.putRecordSet(fMakeItems(items));
			
			List seqKeys= this.fRegSlipLog(functionName, inDataSet, SAPUtils.makeCallInfo());
			
			SapCaller caller = new SapCaller(functionName);
			
			responseData = caller.sendData(inDataSet, SAPUtils.makeCallInfo(),false);
			
			this.fRegSlipResponseLog(seqKeys, responseData, new String[] {SAP_SLIP_ELEM.SLIP_RETURN.getPart()});

			responseData.putFieldMap(SAPUtils.convertRecord2DataSet(responseData.getRecordSet(SAP_SLIP_ELEM.SLIP_RETURN.getPart()).getRecord(0)).getFieldMap());

			responseData.putField("USER_NO", this.userNo);
			responseData.putField(SlipConstants.SLIP_NO, getMessageFromSAP(responseData));
			
			return responseData;
		}
		
		
		/**
		* sliptable기록
		* @param requestData
		* @param batchCtx
		* @return
		*/
		private  long fInqSlipLogHstSeq()
		{
			Long slipLogHstNo = Calendar.getInstance().getTimeInMillis();
			
			IRecord rec  = dbSelectSingle("SSlipLogHstSeq", new HashMap());
			
			slipLogHstNo = rec.getLong(0);
			
			return slipLogHstNo;
		}
		
		/**
		 * 로깅
		 * @param functionName
		 * @param erpRequestData
		 * @param callInfo
		 */
		private List<Long> fRegSlipLog(String functionName, IDataSet erpRequestData,HashMap callInfo) {
			
			List<Long> seqNums = new ArrayList();
			String dmsTypeCd = "DE";
			for (SAP_SLIP_KINDS one:SAP_SLIP_KINDS.values())
			{
				if(functionName.equals(one.getFuncName()))
				{
					dmsTypeCd = one.getDmsType();
					break;
				}
			}
			
			Long sequenceKey = this.fInqSlipLogHstSeq();
			seqNums.add(sequenceKey);
			
			Map slipTrmsMap = new HashMap();
			slipTrmsMap.put("DMS_SLIP_NO", this.dmsSlipSeq);
			slipTrmsMap.put("SLIP_TRMS_HST_NO", ""+sequenceKey);
			slipTrmsMap.put("SLIP_TYP_CD",  dmsTypeCd);
			dbInsert("ISlipTrmsHst", slipTrmsMap);
			
			Iterator<String> infoList = callInfo.keySet().iterator();
			
			int idxDtl =1;
			
			while(infoList.hasNext()) {
				String key = infoList.next();
				
				if("params".equals(key))
				{
					HashMap paramMap = (HashMap) callInfo.get("params");
					if(paramMap!=null && paramMap.size()>0)
					{
						Iterator ir = paramMap.entrySet().iterator();
						
						while(ir.hasNext())
						{
							String key4Map = (String) ir.next();
							String value4Map = (String) paramMap.get(key4Map);
							
							//header
							Map slipTrmsDtlMap = new HashMap();
							slipTrmsDtlMap.put("SLIP_TRMS_HST_NO", ""+sequenceKey);
							slipTrmsDtlMap.put("SLIP_TRMS_DTL_HST_NO", ""+idxDtl);
							slipTrmsDtlMap.put("SLIP_TRMS_DTL_EVNT", key4Map);
							slipTrmsDtlMap.put("SLIP_TRMS_DTL_CONT", value4Map);
							dbInsert("ISlipTrmsDtlHst", slipTrmsDtlMap);
						}
						idxDtl++;
					}
				}
				else
				{
					String RECORD_SET_ID = key;
					String TABLE_ID = (String)callInfo.get(key);
					
					
					/* record set */
					IRecordSet recordSet = erpRequestData.getRecordSet(RECORD_SET_ID);
					Iterator records = recordSet.getRecords();
					
					int j=0;
					while(records.hasNext()) {
						//header
						Map slipTrmsDtlMap = new HashMap();
						slipTrmsDtlMap.put("SLIP_TRMS_HST_NO", ""+sequenceKey);
						slipTrmsDtlMap.put("SLIP_TRMS_DTL_HST_NO", ""+idxDtl);
						slipTrmsDtlMap.put("SLIP_TRMS_DTL_EVNT", TABLE_ID);
						dbInsert("ISlipTrmsDtlHst", slipTrmsDtlMap);
						
						Iterator headers = recordSet.getHeaders();
						IRecord record = (IRecord)records.next();
						int colIdx =1;
						while(headers.hasNext())
						{
							RecordHeader header = (RecordHeader) headers.next();
							String headerName   = header.getName();
							String value        = record.get(headerName);
							
							Map slipTrmsDtlDtailMap = new HashMap();
							slipTrmsDtlDtailMap.put("SLIP_TRMS_DTL_DTAIL_HST_NO", ""+colIdx);
							slipTrmsDtlDtailMap.put("SLIP_TRMS_DTL_DTAIL_HST_EVNT" ,headerName);
							slipTrmsDtlDtailMap.put("SLIP_TRMS_DTL_DTAIL_HST_CONT", value);
							slipTrmsDtlDtailMap.put("SLIP_TRMS_HST_NO", ""+sequenceKey);
							slipTrmsDtlDtailMap.put("SLIP_TRMS_DTL_HST_NO", ""+idxDtl);
							dbInsert("ISlipTrmsDtlDtailHst", slipTrmsDtlDtailMap);
							
							colIdx++;
						}
						idxDtl++;
					}
				}
			}
			
			seqNums.add((long) idxDtl);
			
			return seqNums;
		}
		
		/**
		 * 로깅
		 * @param seqKeys.get(0)
		 * @param erpResponseData
		 * @param rsList
		 */
		private Long fRegSlipResponseLog(List<Long> seqKeys, IDataSet erpResponseData,String[] rsList) {
			
			Long sequenceKey = seqKeys.get(0);
			
			Iterator<String> infoFieldList =erpResponseData.getFieldKeys();
			
			Long idxDtl = seqKeys.get(1);
			
			//필드처리
			while(infoFieldList.hasNext()) {
				String key4DS = (String) infoFieldList.next();
				String value4DS = (String) erpResponseData.getField(key4DS);
				
				//header
				Map slipTrmsDtlMap = new HashMap();
				slipTrmsDtlMap.put("SLIP_TRMS_HST_NO", ""+sequenceKey);
				slipTrmsDtlMap.put("SLIP_TRMS_DTL_HST_NO", ""+idxDtl);
				slipTrmsDtlMap.put("SLIP_TRMS_DTL_EVNT", key4DS);
				slipTrmsDtlMap.put("SLIP_TRMS_DTL_CONT", value4DS);
				dbInsert("ISlipTrmsDtlHst", slipTrmsDtlMap);
				
				idxDtl++;
			}
			
			for(String key:rsList)
			{
				String TABLE_ID = key;
					
				
				/* record set */
				IRecordSet recordSet = erpResponseData.getRecordSet(TABLE_ID);
				Iterator records = recordSet.getRecords();
				
				int j=0;
				while(records.hasNext()) {
					//header
					Map slipTrmsDtlMap = new HashMap();
					slipTrmsDtlMap.put("SLIP_TRMS_HST_NO", ""+sequenceKey);
					slipTrmsDtlMap.put("SLIP_TRMS_DTL_HST_NO", ""+idxDtl);
					slipTrmsDtlMap.put("SLIP_TRMS_DTL_EVNT", TABLE_ID);
					dbInsert("ISlipTrmsDtlHst", slipTrmsDtlMap);
					
					Iterator headers = recordSet.getHeaders();
					IRecord record = (IRecord)records.next();
					int colIdx =1;
					while(headers.hasNext())
					{
						RecordHeader header = (RecordHeader) headers.next();
						String headerName   = header.getName();
						String value        = record.get(headerName);
						
						Map slipTrmsDtlDtailMap = new HashMap();
						slipTrmsDtlDtailMap.put("SLIP_TRMS_DTL_DTAIL_HST_NO", ""+colIdx);
						slipTrmsDtlDtailMap.put("SLIP_TRMS_DTL_DTAIL_HST_EVNT" ,headerName);
						slipTrmsDtlDtailMap.put("SLIP_TRMS_DTL_DTAIL_HST_CONT", value);
						slipTrmsDtlDtailMap.put("SLIP_TRMS_HST_NO", ""+sequenceKey);
						slipTrmsDtlDtailMap.put("SLIP_TRMS_DTL_HST_NO", ""+idxDtl);
						dbInsert("ISlipTrmsDtlDtailHst", slipTrmsDtlDtailMap);
						
						colIdx++;
					}
					idxDtl++;
				}
				
			}
			
			return sequenceKey;
		}
		
}