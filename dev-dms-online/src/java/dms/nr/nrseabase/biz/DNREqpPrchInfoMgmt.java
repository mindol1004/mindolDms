package dms.nr.nrseabase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.RecordHeader;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [DU]단말자산취득관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-09-22 17:08:16</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class DNREqpPrchInfoMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DNREqpPrchInfoMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-22 17:08:16
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpPrchInfoLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SEqpPrchInfoLstPaging", requestData, onlineCtx);

        // 2.결과값 셋팅
        responseData.putRecordSet("RS_EQP_PRCH_LIST", rsReturn);
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-22 17:08:16
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpPrchInfoTotalSlip(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    IRecordSet rsCntcall = requestData.getRecordSet("RS_SUM_LIST");
	    
	    // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SEqpPrchInfoTotalSlip", requestData, onlineCtx);
        IRecordSet totRs = dbSelect("SEqpPrchInfoTotalSlipTotCnt", requestData, onlineCtx);
	    
        responseData.putRecordSet("RS_SLIP_LIST",rsReturn);
	    
        IRecord ir = rsCntcall.getRecord(0);
        
        if(rsCntcall.getHeader("T_CNT") == null){
        	rsCntcall.addHeader(new RecordHeader("T_CNT"));
        }
        
        	ir.put("T_CNT", totRs.get(0, "T_CNT"));
        	
        if(rsCntcall.getHeader("T_PRC") == null){
        	rsCntcall.addHeader(new RecordHeader("T_PRC"));
        }
        	ir.put("T_PRC", totRs.get(0,"T_PRC"));
        
        responseData.putRecordSet("RS_SUM_LIST", rsCntcall);
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-23 12:56:59
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpPrchInfoLstTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
	    IRecordSet rsCnt = dbSelect("SEqpPrchInfoTotCnt", requestData, onlineCtx);
	    responseData.putRecordSet("RS_SUM_LIST", rsCnt);
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-23 17:31:36
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpPrchAllExcel(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();

	    //1. 쿼리문호출
	    IRecordSet rsReturn = dbSelect("SEqpPrchAllExcel", requestData.getFieldMap(), onlineCtx);
	    // 2.결과값 셋팅
        responseData.putRecordSet("RS_ALL_EXCEL_LIST", rsReturn);
	
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-25 12:55:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpPrchAssetNo(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    //1. 쿼리문호출
	    IRecordSet rsReturn = dbSelect("SEqpPrchAssetNo", requestData.getFieldMap(), onlineCtx);
	    // 2.결과값 셋팅
        responseData.putRecordSet("RS_ASSET_LIST", rsReturn);
	
	    return responseData;
	}
  
}
