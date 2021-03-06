package dms.ep.epsesbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]상품관리</li>
 * <li>설  명 : <pre>상품관리 DU</pre></li>
 * <li>작성일 : 2015-09-11 12:36:11</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class DEPDisSaleMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPDisSaleMgmt(){
		super();
	}
	
	/**
     * <pre>입고관리번호채번</pre>
     *
     * @author 이민재 (mindol1004)
     * @since 2015-09-11 12:36:11
     *
     * @param requestData 요청정보 DataSet 객체
     * @param onlineCtx   요청 컨텍스트 정보
     * @return 처리결과 DataSet 객체
     */
    public IDataSet dSDisProdInNo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SDisProdInNo", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record); 
    
        return responseData;
    }
    
    /**
     * <pre>출고관리번호채번</pre>
     *
     * @author 이민재 (mindol1004)
     * @since 2015-09-11 18:15:17
     *
     * @param requestData 요청정보 DataSet 객체
     * @param onlineCtx   요청 컨텍스트 정보
     * @return 처리결과 DataSet 객체
     */
    public IDataSet dSDisProdOutNo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SDisProdOutNo", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record); 
    
        return responseData;
    }

    /**
	 * <pre>상품출고변경순번수정</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-11 12:44:31
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUDisProdOut(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        final IRecordSet recordSet = requestData.getRecordSet("RS_SALE_LIST");
        
        // SQL 배치 모드 실행
        long totalCount = dbExecuteBatch(new DbBatchModeExecutor(1000) {
            
            @Override
            protected void execute() {
                
                for(int i=0; i<recordSet.getRecordCount(); i++){
                    addBatch("UDisProdOut", recordSet.getRecord(i));
                }
                
            }
            
        }, onlineCtx);
        
        responseData.putField("totalCount", totalCount);
    
        return responseData;
    }

    /**
	 * <pre>상품입고저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-11 16:02:38
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIDisProdIn(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        final IRecordSet recordSet = requestData.getRecordSet("RS_SALE_LIST");
        
        // SQL 배치 모드 실행
        long totalCount = dbExecuteBatch(new DbBatchModeExecutor(1000) {
            
            @Override
            protected void execute() {
                
                for(int i=0; i<recordSet.getRecordCount(); i++){
                    addBatch("IDisProdIn", recordSet.getRecord(i));
                }
                
            }
            
        }, onlineCtx);
        
        responseData.putField("totalCount", totalCount);
    
        return responseData;
    }

    /**
	 * <pre>상품입고상세저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-11 16:03:06
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIDisProdInDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        final IRecordSet recordSet = requestData.getRecordSet("RS_SALE_LIST");
        
        // SQL 배치 모드 실행
        long totalCount = dbExecuteBatch(new DbBatchModeExecutor(1000) {
            
            @Override
            protected void execute() {
                
                for(int i=0; i<recordSet.getRecordCount(); i++){
                    addBatch("IDisProdInDtl", recordSet.getRecord(i));
                }
                
            }
            
        }, onlineCtx);
        
        responseData.putField("totalCount", totalCount);
    
        return responseData;
    }

    /**
	 * <pre>상품입출고상세저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-11 16:03:36
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIDisProdInOutHst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        final IRecordSet recordSet = requestData.getRecordSet("RS_SALE_LIST");
        
        // SQL 배치 모드 실행
        long totalCount = dbExecuteBatch(new DbBatchModeExecutor(1000) {
            
            @Override
            protected void execute() {
                
                for(int i=0; i<recordSet.getRecordCount(); i++){
                    addBatch("IDisProdInOutHst", recordSet.getRecord(i));
                }
                
            }
            
        }, onlineCtx);
        
        responseData.putField("totalCount", totalCount);
    
        return responseData;
    }

    /**
	 * <pre>상품재고수정</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-11 16:03:59
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUDisProdDis(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        final IRecordSet recordSet = requestData.getRecordSet("RS_SALE_LIST");
        
        // SQL 배치 모드 실행
        long totalCount = dbExecuteBatch(new DbBatchModeExecutor(1000) {
            
            @Override
            protected void execute() {
                
                for(int i=0; i<recordSet.getRecordCount(); i++){
                    addBatch("UDisProdDis", recordSet.getRecord(i));
                }
                
            }
            
        }, onlineCtx);
        
        responseData.putField("totalCount", totalCount);
    
        return responseData;
    }

    /**
	 * <pre>상품출고상세저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-11 17:30:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIDisProdOutDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        final IRecordSet recordSet = requestData.getRecordSet("RS_SALE_LIST");
        
        // SQL 배치 모드 실행
        long totalCount = dbExecuteBatch(new DbBatchModeExecutor(1000) {
            
            @Override
            protected void execute() {
                
                for(int i=0; i<recordSet.getRecordCount(); i++){
                    addBatch("IDisProdOutDtl", recordSet.getRecord(i));
                }
                
            }
            
        }, onlineCtx);
        
        responseData.putField("totalCount", totalCount);
    
        return responseData;
    }

    /**
	 * <pre>상품출고저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-15 17:53:16
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIDisProdOut(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        final IRecordSet recordSet = requestData.getRecordSet("RS_SALE_LIST");
        
        // SQL 배치 모드 실행
        long totalCount = dbExecuteBatch(new DbBatchModeExecutor(1000) {
            
            @Override
            protected void execute() {
                
                for(int i=0; i<recordSet.getRecordCount(); i++){
                    addBatch("IDisProdOut", recordSet.getRecord(i));
                }
                
            }
            
        }, onlineCtx);
        
        responseData.putField("totalCount", totalCount);
    
        return responseData;
    }

    /**
	 * <pre>상품입출고상세입출고번호채번</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-11 12:36:11
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSDisProdInOutHstSeq(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SDisProdInOutHstSeq", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record); 
    
        return responseData;
    }

    /**
	 * <pre>상품입출고상세상품입고번호채번</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-04 15:50:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSDisProdInOutHstInSeq(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SDisProdInOutHstInSeq", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record); 
    
        return responseData;
    }

}
