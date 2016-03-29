package dms.nr.nrseabase.biz;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.xml.DataSetXmlTransformer;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;
import fwk.common.CommonArea;
import fwk.utils.PagenateUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]단말매각관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-06 08:55:36</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class FNREqpDspslMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNREqpDspslMgmt(){
		super();
	}

	/**
	 * <pre>[FM]단말매각현황조회</pre>
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-08-06 08:55:36
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : DEPR_DT [매각일]
	 *	- field : ASSET_SLIP_NO [전표번호]
	 *	- field : ASSET_SLIP_ST [전표상태]
	 *	- field : INVE_ST_CD [단말상태]
	 *	- field : DISPO_DT [마감자산처분일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_DSPSL_LIST
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_PRCH_AMT [매입가]
	 *		- field : DEPR_DT [검색기준일]
	 *		- field : REM_PRC [회계용잔존가]
	 *		- field : DEPR_PRC [회계용감가상각]
	 *		- field : SALE_PRC [예상매각가]
	 *		- field : EQP_PRCH_DT [계약일 = 입고일]
	 *		- field : CAPA_CD [용량]
	 *		- field : ASSET_SLIP_ST [전표상태]
	 *		- field : ASSET_SLIP_NO [전표번호]
	 * </pre>
	 */
	public IDataSet fInqEqpDspslLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet temp = new DataSet();
        
        try {
      
           // 1. DU lookup
            DNREqpDspslMgmt dNREqpDspslMgmt = (DNREqpDspslMgmt) lookupDataUnit(DNREqpDspslMgmt.class);

            // 2. LISTDM호출
            responseData = dNREqpDspslMgmt.dSEqpDspslLst(requestData, onlineCtx); 
                
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
	    
//      IDataSet responseData = new DataSet();
//      IDataSet paramData = new DataSet();
//      IDataSet dsCnt = new DataSet();
//      IRecordSet usrListRs = null;
//      int intTotalCnt = 0;
//
//      try {
//          // 1. DU lookup
//          DNREqpDspslMgmt dNREqpDspslMgmt = (DNREqpDspslMgmt) lookupDataUnit(DNREqpDspslMgmt.class);
//          
//          // 2. TOTAL COUNT DM호출
//          dsCnt = dNREqpDspslMgmt.dSEqpDspslTotCnt(requestData, onlineCtx);
//          IRecordSet sumRs = dsCnt.getRecordSet("RS_SUM_LIST");
//          
//          intTotalCnt = Integer.parseInt(sumRs.get(0,"TOTAL_CNT"));
//          intTotalCnt = Integer.parseInt(cnt);
//          PagenateUtils.setPagenatedParamsToDataSet(requestData);
//          
//          // 3. LISTDM호출
//          responseData = dNREqpDspslMgmt.dSEqpDspslLst(requestData, onlineCtx);
//          usrListRs = responseData.getRecordSet("RS_EQP_DSPSL_LIST");
//          
//          responseData.putRecordSet("RS_SUM_LIST", sumRs);
//          PagenateUtils.setPagenatedParamToRecordSet(sumRs, requestData, intTotalCnt);
//
//          responseData.putRecordSet("RS_EQP_DSPSL_LIST",usrListRs);
//          PagenateUtils.setPagenatedParamToRecordSet(usrListRs, requestData, intTotalCnt);
//      } catch ( BizRuntimeException e ) {
//      throw e; //시스템 오류가 발생하였습니다.
//      } 
//
//  return responseData;
        
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-17 14:55:42
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fEqpDspslBatchCallOnline(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
		   
	    try {
//            IDataSet reqDs = this.fInqEqpDspslLst(requestData, onlineCtx);
//            IRecordSet deprRs = reqDs.getRecordSet("RS_EQP_DSPSL_LIST");
//            deprRs.setId("RS_SLIP_LIST");
//            IRecordSet sumRs = reqDs.getRecordSet("RS_SUM_LIST");
//            
//            if(deprRs==null){
//                throw new BizRuntimeException("DMS00092"); // 생성 대상데이터가 없습니다.
//            }
//            
//            requestData.putRecordSet(deprRs);
//            requestData.putRecordSet(sumRs);
		
			if("01".equals(requestData.getField("CASE_WHEN"))){//재집계
			    ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
				DataSetXmlTransformer.dataSetToXml(requestData, bout, "UTF-8");
				String dsXml = bout.toString("UTF-8");

				// call on-demand batch job
				HashMap params = new HashMap<String,String>();
				params.put("TASK_ID", "XCR002");
				params.put("TASK_NM", "단말기매각등록");
			    params.put("LGIN_ID", onlineCtx.getUserInfo().getLoginId());
			    params.put("USER_NO", ca.getUserNo());
			    params.put("COMPONENTNAME_LOCAL_ONLY", "dms.nr.XCR002");
				params.put("ONDEMAND_DATASET", dsXml);
				String jobExecutionId = callBatchJob("XCR002", params, onlineCtx);
			    waitBatchJobEnd(jobExecutionId, 10000);
			    int result = getJobReturnCode(jobExecutionId);
			    
			    if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.
	    			
	    	}else if("02".equals(requestData.getField("CASE_WHEN"))) {//전표생성
//	    		if(deprRs==null){
//	    			throw new BizRuntimeException("DMS00092"); // 생성 대상데이터가 없습니다.
//	    		}
//	    		
//	    		for(int i=0; i<deprRs.getRecordCount(); i++){
//		    		IRecord ir = deprRs.getRecord(i);
//		    		
//	    			if("10".equals(ir.get("ASSET_SLIP_ST")) || "20".equals(ir.get("ASSET_SLIP_ST")) || "30".equals(ir.get("ASSET_SLIP_ST"))){
//		    			throw new BizRuntimeException("DMS00071"); // 이미 처리된 건이 있습니다.
//		    		}
//	    		}

                requestData.putField("ASSET_DISPO_STRD_YM", requestData.getField("DEPR_DT").substring(0,6));    //자산처분기준년월
                requestData.putField("DSPSL_DIS_CL", "AS"); //매각
                requestData.putField("OP_CL_CD", "NR");     //업무구분코드
                requestData.putField("DISPO_DT", requestData.getField("DEPR_DT"));
                
                requestData.putField("SLIP_TYPE", "NR_AS");  //전표유형:매각 */
                requestData.putField("USER_NO", ca.getUserNo());
                //배치로직호출 -- 전표생성
//	    	    requestData.putField("SLIP_TYPE", "AGENCY_BILLING");//전표유형
//	    		requestData.putField("USER_NO", ca.getUserNo());
	    		//배치로직호출 -- 전표생성
	    		ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
	    		DataSetXmlTransformer.dataSetToXml(requestData, bout, "UTF-8");
	    		String dsXml = bout.toString("UTF-8");

				// call on-demand batch job
				HashMap params = new HashMap<String,String>();
				params.put("TASK_ID", "EPR010");
			    params.put("TASK_NM", "전표발행");
			    params.put("USER_NO", ca.getUserNo());
			    params.put("COMPONENTNAME_LOCAL_ONLY", "dms.inf.EPR010");				
				params.put("POST_SLIP_DATASET", dsXml);
				String jobExecutionId = callBatchJob("EPR010", params, onlineCtx);
			    waitBatchJobEnd(jobExecutionId, 10000);
			    int result = getJobReturnCode(jobExecutionId);
		    
			    if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.
	    	}else if("03".equals(requestData.getField("CASE_WHEN"))){ //전표삭제
//	    	    if(deprRs==null){
//	    			throw new BizRuntimeException("DMS00092"); // 생성 대상데이터가 없습니다.
//	    		}
//	    		
//	    		for(int i=0; i<deprRs.getRecordCount(); i++){
//		    		IRecord ir = deprRs.getRecord(i);
//		    		
//		    		if(StringUtils.isEmpty(ir.get("ASSET_SLIP_ST")) || "00".equals(ir.get("ASSET_SLIP_ST")) || "05".equals(ir.get("ASSET_SLIP_ST"))){
//				    	throw new BizRuntimeException("DMS00071"); // 이미 처리된 건이 있습니다.
//	    	    	}else if("20".equals(ir.get("ASSET_SLIP_ST")) || "30".equals(ir.get("ASSET_SLIP_ST"))){
//	    	    		throw new BizRuntimeException("DMS00087"); //{0} 건은 처리가 불가능 합니다.--승인완료인
//	    	    	}
//	    		}
//	    		deprRs.setId("RS_SLIP_DELETE");
//	    		requestData.getRecordSet("RS_DEPR_LIST").setId("RS_SLIP_DELETE");//SLIP_NO,YYYY들어있음
//	    		IRecord totalSum = requestData.getRecordSet("RS_SUM_LIST").getRecord(0);
//	    		
//	    		requestData.putField("DEPR_AMT", totalSum.get("DEPR_PRC"));//감가상각액총액
//	    		requestData.putField("ALLWN_AMT", totalSum.get("PROV_PRC"));//충당부채액총액
//	    		requestData.putField("SLIP_TYPE", "AGENCY_BILLING");//전표유형
//	    		requestData.putField("USER_NO", ca.getUserNo());
	    		
                //requestData.putField("YYYY", requestData.getField("YYYY"));                 //전표년도
                requestData.putField("SLIP_NO", requestData.getField("ASSET_SLIP_NO"));   //전표번호
                requestData.putField("USER_NO", ca.getUserNo());

	    		ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
	    		DataSetXmlTransformer.dataSetToXml(requestData, bout, "UTF-8");
	    		String dsXml = bout.toString("UTF-8");

				// call on-demand batch job
				HashMap params = new HashMap<String,String>();
				params.put("TASK_ID", "EPR011");
			    params.put("TASK_NM", "전표삭제");
			    params.put("USER_NO", ca.getUserNo());
			    params.put("COMPONENTNAME_LOCAL_ONLY", "dms.inf.EPR011");				
				params.put("POST_SLIP_DATASET", dsXml);
				String jobExecutionId = callBatchJob("EPR011", params, onlineCtx);
			    waitBatchJobEnd(jobExecutionId, 10000);
			    int result = getJobReturnCode(jobExecutionId);
		    
			    if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.
			
	    	}
	    		
	    	
			
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-08-06 08:55:36
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fPowerDspslController(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	
	    try {
			// 1. DU lookup
			DNREqpDspslMgmt dNREqpDspslMgmt = (DNREqpDspslMgmt) lookupDataUnit(DNREqpDspslMgmt.class);
			
			
			// 2. LISTDM호출
			requestData.putField("USER_NO", ca.getUserNo());
			responseData= dNREqpDspslMgmt.dUPowerDspslController(requestData, onlineCtx);
			
			
		} catch ( BizRuntimeException e ) {
			throw e; //시스템 오류가 발생하였습니다.
		} 
	    
	
	    return responseData;
	}

    /**
	 * <pre>[FM]단말매각현황전체다운로드</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-10-26 17:54:57
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqEqpDspslAllExcel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet temp = new DataSet();
        
        try {
      
           // 1. DU lookup
            DNREqpDspslMgmt dNREqpDspslMgmt = (DNREqpDspslMgmt) lookupDataUnit(DNREqpDspslMgmt.class);

            // 2. LISTDM호출
            responseData = dNREqpDspslMgmt.dSEqpDspslAllExcel(requestData, onlineCtx); 
                
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>[FM]단말매각전표처리현황</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-08-06 08:55:36
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : DEPR_DT [매각일자]
	 *	- field : ASSET_SLIP_NO [전표번호]
	 *	- field : ASSET_SLIP_ST [전표상태]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqEqpDspslSum(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
      
           // 1. DU lookup
            DNREqpDspslMgmt dNREqpDspslMgmt = (DNREqpDspslMgmt) lookupDataUnit(DNREqpDspslMgmt.class);

            // 2. LISTDM호출
            responseData = dNREqpDspslMgmt.dSEqpDspslSum(requestData, onlineCtx); 
                
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>[FM]매각마감자산처분삭제</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-08-06 08:55:36
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fDelDspslClsAssetDispo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. DU lookup
            DNREqpDspslMgmt dNREqpDspslMgmt = (DNREqpDspslMgmt) lookupDataUnit(DNREqpDspslMgmt.class);
            
            responseData = dNREqpDspslMgmt.dDDspslClsAssetDispo(requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>[FM]매각마감자산처분등록</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-08-06 08:55:36
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegDspslClsAssetDispo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. DU lookup
            DNREqpDspslMgmt dNREqpDspslMgmt = (DNREqpDspslMgmt) lookupDataUnit(DNREqpDspslMgmt.class);
            
            responseData = dNREqpDspslMgmt.dIDspslClsAssetDispo(requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>[FM]매각마감자산처분상세등록</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-08-06 08:55:36
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegDspslClsAssetDispoDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. DU lookup
            DNREqpDspslMgmt dNREqpDspslMgmt = (DNREqpDspslMgmt) lookupDataUnit(DNREqpDspslMgmt.class);
            
            responseData = dNREqpDspslMgmt.dIDspslClsAssetDispoDtl(requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }
  
}
