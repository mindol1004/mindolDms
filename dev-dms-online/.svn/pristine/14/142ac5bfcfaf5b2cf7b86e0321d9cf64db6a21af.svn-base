package dms.ep.epsimbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]재고이관관리</li>
 * <li>설  명 : <pre>재고이관관리 DU</pre></li>
 * <li>작성일 : 2015-11-17 11:09:47</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class DEPInveTrnsfMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPInveTrnsfMgmt(){
		super();
	}

    /**
	 * <pre>재고이관조회총건수</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-17 14:26:17
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInveTrnsfTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SInveTrnsfTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>재고이관조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-17 14:26:36
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInveTrnsfPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInveTrnsfPaging", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_PR_DIS_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>단말기자산번호채번</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-18 10:33:03
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInveTrnsfEqpAssetSeq(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SInveTrnsfEqpAssetSeq", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record); 
    
        return responseData;
    }

    /**
	 * <pre>단말기자산이관</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-18 10:33:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIInveTrnsfEqpAsset(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        final IRecordSet recordSet = requestData.getRecordSet("RS_PR_DIS_LIST");
        
        // SQL 배치 모드 실행
        long totalCount = dbExecuteBatch(new DbBatchModeExecutor(1000) {
            
            @Override
            protected void execute() {
                
                for(int i=0; i<recordSet.getRecordCount(); i++){
                    addBatch("IInveTrnsfEqpAsset", recordSet.getRecord(i));
                }
                
            }
            
        }, onlineCtx);
        
        responseData.putField("totalCount", totalCount);
    
        return responseData;
    }

    /**
	 * <pre>단말기입고이관</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-18 10:33:41
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIInveTrnsfEqpIn(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        final IRecordSet recordSet = requestData.getRecordSet("RS_PR_DIS_LIST");
        
        // SQL 배치 모드 실행
        long totalCount = dbExecuteBatch(new DbBatchModeExecutor(1000) {
            
            @Override
            protected void execute() {
                
                for(int i=0; i<recordSet.getRecordCount(); i++){
                    addBatch("IInveTrnsfEqpIn", recordSet.getRecord(i));
                }
                
            }
            
        }, onlineCtx);
        
        responseData.putField("totalCount", totalCount);
    
        return responseData;
    }

    /**
	 * <pre>단말기입고번호채번</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-18 13:08:20
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInveTrnsfEqpInSeq(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SInveTrnsfEqpInSeq", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record); 
    
        return responseData;
    }

    /**
	 * <pre>재고이관정산번호채번</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-19 13:30:39
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInveTrnsfXclSeq(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SInveTrnsfXclSeq", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);  
    
        return responseData;
    }

    /**
	 * <pre>재고이관정산등록</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-19 13:31:12
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIInveTrnsfXcl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        final IRecordSet recordSet = requestData.getRecordSet("RS_PR_DIS_LIST");
        
        // SQL 배치 모드 실행
        long totalCount = dbExecuteBatch(new DbBatchModeExecutor(1000) {
            
            @Override
            protected void execute() {
                
                for(int i=0; i<recordSet.getRecordCount(); i++){
                    addBatch("IInveTrnsfXcl", recordSet.getRecord(i));
                }
                
            }
            
        }, onlineCtx);
        
        responseData.putField("totalCount", totalCount);
    
        return responseData;
    }
  
}
