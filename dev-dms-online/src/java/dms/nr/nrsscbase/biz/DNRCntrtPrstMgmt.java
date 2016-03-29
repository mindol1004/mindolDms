package dms.nr.nrsscbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [DU]계약현황관리</li>
 * <li>설  명 : <pre>계약현황관리DU</pre></li>
 * <li>작성일 : 2015-07-17 10:29:24</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class DNRCntrtPrstMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DNRCntrtPrstMgmt(){
		super();
	}

	/**
	 * <pre>계약현황조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 10:32:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSCntrtPrstLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
  		IRecordSet rsReturn = dbSelect("SCntrtPrstLstPaging", requestData, onlineCtx);
  		// 2.결과값 셋팅
  		responseData.putRecordSet("RS_CNTRT_PRST_LIST", rsReturn);
	
	    return responseData;
	}

	/**
	 * <pre>계약현황총건수</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 10:33:40
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSCntrtPrstTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출	    
  		IRecord record = dbSelectSingle("SCntrtPrstTotCnt", requestData, onlineCtx);
  		// 2.결과값 셋팅	    
  		responseData.putFieldMap(record);  
	
	    return responseData;
	}

	/**
	 * <pre>계약현황상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 10:40:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSCntrtPrstDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
  		IRecordSet rsReturn = dbSelect("SCntrtPrstDtl", requestData, onlineCtx);
  		// 2.결과값 셋팅
  		responseData.putRecordSet("RS_CNTRT_PRST_DTL", rsReturn);
	
	    return responseData;
	}

	/**
	 * <pre>계약현황이력조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-24 14:46:38
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSCntrtPrstHisLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
  		IRecordSet rsReturn = dbSelect("SCntrtPrstHisLstPaging", requestData, onlineCtx);
  		// 2.결과값 셋팅
  		responseData.putRecordSet("RS_CNTRT_HIS_LIST", rsReturn);
	
	    return responseData;
	}

	/**
	 * <pre>계약현황이력총건수</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-24 14:47:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSCntrtPrstHisTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출	    
  		IRecord record = dbSelectSingle("SCntrtPrstHisTotCnt", requestData, onlineCtx);
  		// 2.결과값 셋팅	    
  		responseData.putFieldMap(record);  
	
	    return responseData;
	}

	/**
	 * <pre>계약현황이력상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-24 14:47:53
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSCntrtPrstHisDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
  		IRecordSet rsReturn = dbSelect("SCntrtPrstHisDtl", requestData, onlineCtx);
  		// 2.결과값 셋팅
  		responseData.putRecordSet("RS_CNTRT_HIS_DTL", rsReturn);
	
	    return responseData;
	}

	/**
	 * <pre>계약현황이력메모저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-08-04 13:12:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUCntrtPrstHisRmk(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		
	    // 1.쿼리문 호출
 		dbInsert("UCntrtPrstHisRmk", requestData, onlineCtx);
	
	    return responseData;
	}

    /**
	 * <pre>계약현황USCAN조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 10:29:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSCntrtPrstUscanLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SCntrtPrstUscanLstPaging", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_CNTRT_USCAN_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>계약현황USCAN총건수</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-08-17 14:53:47
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSCntrtPrstUscanTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SCntrtPrstUscanTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);  
    
        return responseData;
    }

    /**
	 * <pre>계약현황USCAN오류항목수정</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 10:29:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUCntrtPrstUscan(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbUpdate("UCntrtPrstUscan", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-08-27 11:13:36
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSCntrtPrstUscanImgLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SCntrtPrstUscanImgLst", requestData.getFieldMap(), onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_CNTRT_USCAN_IMG_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>계약현황USCAN오류항목등록</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 10:29:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dICntrtPrstUscan(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("ICntrtPrstUscan", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>계약현황USCAN존재여부</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 10:29:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dSCntrtPrstUscanExistYN(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SCntrtPrstUscanExistYN", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);  
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-10-22 14:56:19
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpCmptRtnLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SEqpCmptRtnLst", requestData.getFieldMap(), onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_EQP_CMPT_RTN_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-10-29 10:17:11
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDCntrtPrstUscanDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        // 1.쿼리문 호출
        dbInsert("DCntrtPrstUscanDtl", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>계약현황USCAN오류상세항목등록</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 10:29:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dICntrtPrstUscanDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("ICntrtPrstUscanDtl", requestData, onlineCtx);
    
        return responseData;
    }
  
}
