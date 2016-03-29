package dms.nr.nrsxmbase.biz;

import java.util.Map;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.RecordHeader;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [DU]대리점단말기정산정보관리</li>
 * <li>설  명 : <pre>대리점단말기정산정보관리DU</pre></li>
 * <li>작성일 : 2015-08-18 10:07:38</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class DNRAgnEqpStlMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DNRAgnEqpStlMgmt(){
		super();
	}

    /**
	 * <pre>대리점단말기정산정보조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-08-18 10:11:10
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAgnEqpStlLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SAgnEqpStlLstPaging", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_AGN_EQP_STL_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>대리점단말기정산정보총건수</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-08-18 10:11:40
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAgnEqpStlTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
	    // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SAgnEqpStlTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record); 
        
        return responseData;
    }

    /**
	 * <pre>대리점단말기정산정보상세</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-08-18 10:12:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAgnEqpStlDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SAgnEqpStlDtl", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_AGN_EQP_STL_DTL", rsReturn);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-09-16 09:29:39
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAgnEqpStlSumTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SAgnEqpStlSumTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SUM_LIST", rsReturn);
    
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-09-16 13:22:04
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAgnreBatchDt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출	    
  		IRecord record = dbSelectSingle("SAgnreBatchDt", requestData, onlineCtx);
  		// 2.결과값 셋팅	    
  		responseData.putFieldMap(record);
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-21 12:21:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpStlAllExcel(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    IRecordSet rsReturn = dbSelect("SEqpStlAllExcel", requestData.getFieldMap(), onlineCtx);
	    // 2.결과값 셋팅
        responseData.putRecordSet("RS_ALL_EXCEL_LIST", rsReturn);
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-18 10:07:38
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAgnEqpStlSumTotCnt2(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SAgnEqpStlSumTotCnt2", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SUM_LIST2", rsReturn);
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-10-01 09:39:20
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAgnEqpStlSumTotCnt3(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SAgnEqpStlSumTotCnt3", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SUM_LIST3", rsReturn); 
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-10-06 09:53:36
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAgnEqpStlDtlSumTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SAgnEqpStlDtlSumTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SUM_LIST", rsReturn); 
	
	    return responseData;
	}
  
}
