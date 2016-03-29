package dms.ep.epsfsbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]FPA상품판매관리</li>
 * <li>설  명 : <pre>FPA상품판매관리</pre></li>
 * <li>작성일 : 2016-02-16 09:59:19</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class DEPFPASaleMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPFPASaleMgmt(){
		super();
	}

    /**
	 * <pre>FPA상품판매관리조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-16 10:00:51
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaSaleList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SFpaSaleList", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_FPA_SALE_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>FPA상품판매등록상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-16 10:02:42
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaSaleMasterDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SFpaSaleMasterDtl", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_FPA_MASTER_DTL", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>FPA상품판매등록상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-16 10:03:40
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaSaleItemDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SFpaSaleItemDtl", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_FPA_ITEM_DTL", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>FPA상품판매번호채번</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-16 10:05:35
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaSaleGnrlNo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SFpaSaleGnrlNo", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>FPA상품판매변경순번채번</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-16 10:06:30
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaSaleGnrlChgNo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SFpaSaleGnrlChgNo", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>FPA상품판매단말기판매순번채번</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-16 10:07:49
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaSaleEquipmentSellSeq(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SFpaSaleEquipmentSellSeq", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>FPA상품판매등록일반판매저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-16 10:09:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIFpaGnrlSale(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IFpaGnrlSale", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>FPA상품판매등록단말기판매저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-16 10:10:51
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIFpaSaleEquipment(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
        final IRecordSet recordSet = requestData.getRecordSet("RS_SALE_LIST");
        
        // SQL 배치 모드 실행
        long totalCount = dbExecuteBatch(new DbBatchModeExecutor(1000) {
            
            @Override
            protected void execute() {
                
                for(int i=0; i<recordSet.getRecordCount(); i++){
                    addBatch("IFpaSaleEquipment", recordSet.getRecord(i));
                }
                
            }
            
        }, onlineCtx);
        
        responseData.putField("totalCount", totalCount);
    
        return responseData;
    }

    /**
	 * <pre>FPA재고상품총건수</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-16 16:43:16
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaSaleDisTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SFpaSaleDisTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record); 
    
        return responseData;
    }

    /**
	 * <pre>FPA재고상품조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-16 16:43:37
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaSaleDisLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SFpaSaleDisLst", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_FPA_DIS_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>FPA단말기판매정보수정</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-17 11:22:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUFpaEqpMgmt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
        final IRecordSet recordSet = requestData.getRecordSet("RS_SALE_LIST");
        
        // SQL 배치 모드 실행
        long totalCount = dbExecuteBatch(new DbBatchModeExecutor(1000) {
            
            @Override
            protected void execute() {
                
                for(int i=0; i<recordSet.getRecordCount(); i++){
                    addBatch("UFpaEqpMgmt", recordSet.getRecord(i));
                }
                
            }
            
        }, onlineCtx);
        
        responseData.putField("totalCount", totalCount);
    
        return responseData;
    }

    /**
	 * <pre>FPA상품판매재고조회총건수</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-17 16:39:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaSaleDisListTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SFpaSaleDisListTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record); 
    
        return responseData;
    }

    /**
	 * <pre>FPA상품판매재고조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-17 16:40:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaSaleDisListPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SFpaSaleDisListPaging", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_FPA_SALE_DIS_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>FPA상품판매취소총건수</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-25 10:04:45
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaSaleCnclListTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SFpaSaleCnclListTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>FPA상품판매취소조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-25 10:05:18
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaSaleCnclListPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SFpaSaleCnclListPaging", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_FPA_CNCL_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>FPA상품판매취소일반판매저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-25 13:16:06
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIFpaGnrlSaleCncl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IFpaGnrlSaleCncl", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>FPA상품판매취소단말기판매저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-25 13:16:42
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIFpaSaleEquipmentCncl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IFpaSaleEquipmentCncl", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>FPA상품판매취소단말기판매수정</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-25 13:31:30
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUFpaSaleEquipmentCncl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbUpdate("UFpaSaleEquipmentCncl", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>FPA상품판매건수조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-25 13:35:52
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaGnrlSaleCount(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SFpaGnrlSaleCount", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }
  
}
