package dms.ep.epsesbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]B2B상품판매관리</li>
 * <li>설  명 : <pre>B2B상품판매관리</pre></li>
 * <li>작성일 : 2015-08-18 16:27:11</li>
 * <li>작성자 : 박민정 (dangnagu2)</li>
 * </ul>
 *
 * @author 박민정 (dangnagu2)
 */
public class DEPB2BSaleMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPB2BSaleMgmt(){
		super();
	}

	/**
	 * <pre>B2B상품판매관리조회</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-08-18 16:27:11
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSB2bSaleList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
		
	    // 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SB2bSaleList", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_B2B_SALE_LIST", rsReturn);
	
	    return responseData;
	}

    /**
	 * <pre>B2B상품판매등록상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-07 10:24:43
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSB2bSaleMasterDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SB2bSaleMasterDtl", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_B2B_MASTER_DTL", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매등록상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-07 10:25:12
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSB2bSaleItemDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SB2bSaleItemDtl", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_B2B_ITEM_DTL", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매등록상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-07 10:25:38
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSB2bSalePaymentDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SB2bSalePaymentDtl", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_B2B_PAYMENT_DTL", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매등록전송여부조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-10 09:17:41
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSB2bSaleTrmsYn(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SB2bSaleTrmsYn", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_TRMS_YN", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매등록일반판매저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-10 09:46:10
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIB2bGnrlSale(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IB2bGnrlSale", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매등록수납저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-10 09:46:59
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIB2bPayment(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IB2bPayment", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매등록현금매출저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-10 09:47:26
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIB2bSaleAmt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IB2bSaleAmt", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매번호채번</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-10 15:15:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSB2bSaleGnrlNo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SB2bSaleGnrlNo", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매단말기판매저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-10 16:31:44
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIB2bSaleEquipment(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        final IRecordSet recordSet = requestData.getRecordSet("RS_SALE_LIST");
        
        // SQL 배치 모드 실행
        long totalCount = dbExecuteBatch(new DbBatchModeExecutor(1000) {
            
            @Override
            protected void execute() {
                
                for(int i=0; i<recordSet.getRecordCount(); i++){
                    addBatch("IB2bSaleEquipment", recordSet.getRecord(i));
                }
                
            }
            
        }, onlineCtx);
        
        responseData.putField("totalCount", totalCount);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매REPORT조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-24 10:22:49
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSB2bSaleReportDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SB2bSaleReportDtl", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_B2B_REPORT_DTL", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매취소총건수</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-24 17:24:38
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSB2bSaleCnclListTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SB2bSaleCnclListTotCnt", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매취소조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-24 17:25:12
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSB2bSaleCnclListPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SB2bSaleCnclListPaging", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_B2B_CNCL_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매취소일반판매저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-30 09:54:46
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIB2bGnrlSaleCncl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IB2bGnrlSaleCncl", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매취소단말기판매저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-30 09:55:22
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIB2bSaleEquipmentCncl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IB2bSaleEquipmentCncl", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매취소현금매출저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-30 09:56:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIB2bSaleAmtCncl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IB2bSaleAmtCncl", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매취소수납저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-30 09:56:45
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIB2bPaymentCncl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IB2bPaymentCncl", requestData, onlineCtx); 
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매변경순번채번</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-30 15:36:08
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSB2bSaleGnrlChgNo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SB2bSaleGnrlChgNo", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매취소단말기판매수정</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-30 16:30:22
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUB2bSaleEquipmentCncl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbUpdate("UB2bSaleEquipmentCncl", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매건수조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-01 10:14:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSB2bGnrlSaleCount(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SB2bGnrlSaleCount", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매등록상세총건수</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-02 10:52:48
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSB2bSaleItemDtlTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SB2bSaleItemDtlTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>B2B상품판매단말기판매순번채번</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-03 15:58:58
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSB2bSaleEquipmentSellSeq(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SB2bSaleEquipmentSellSeq", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }
  
}
