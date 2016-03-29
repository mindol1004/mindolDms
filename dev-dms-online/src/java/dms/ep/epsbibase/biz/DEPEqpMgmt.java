package dms.ep.epsbibase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]단말기상품관리</li>
 * <li>설  명 : <pre>단말기상품관리 DU</pre></li>
 * <li>작성일 : 2015-09-22 10:12:15</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class DEPEqpMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPEqpMgmt(){
		super();
	}

    /**
	 * <pre>상품조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-22 10:12:15
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpMgmtLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SEqpMgmtLst", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_EQP_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>단말기색상조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-22 10:39:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpMgmtColorLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SEqpMgmtColorLst", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_COLOR_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>단말기전송여부조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-22 10:43:00
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpMgmtTrmsYnLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SEqpMgmtTrmsYnLst", requestData, onlineCtx);
        
        // 2.결과값 셋팅     
        if ( record != null ) {
            responseData.putFieldMap(record);
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기재고조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-22 11:21:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpMgmtDisLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SEqpMgmtDisLst", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_EQP_DIS_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>단말기판매상품조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-01 13:15:26
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpMgmtSaleLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SEqpMgmtSaleLst", requestData, onlineCtx);
        
        // 2.결과값 셋팅     
        if ( record != null ) {
            responseData.putFieldMap(record);
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기재고조회총건수</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-12-30 12:21:11
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpMgmtDisTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SEqpMgmtDisTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }
  
}
