package dms.ep.epssxbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]단말기대금요금선납정산관리</li>
 * <li>설  명 : <pre>단말기대금요금선납정산관리 DU</pre></li>
 * <li>작성일 : 2015-10-23 09:56:50</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class DEPEqpPpayXclMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPEqpPpayXclMgmt(){
		super();
	}

    /**
	 * <pre>단말기대금요금선납조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 10:25:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpPpayXclList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SEqpPpayXclList", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_EQP_PPAY_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>단말기대금요금선납상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 10:25:30
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpPpayXclDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SEqpPpayXclDtl", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_EQP_PPAY_DTL", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>단말기대금요금선납재집계</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-26 11:24:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIEqpPpayXcl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IEqpPpayXcl", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>단말기대금요금선납재집계상세</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-26 11:24:36
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIEqpPpayXclDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IEqpPpayXclDtl", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>단말기대금요금선납재집계전표발행체크</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:56:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpPpayXclChk(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SEqpPpayXclChk", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>단말기대금요금선납시퀀스조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-26 14:23:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpPpayXclSeq(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SEqpPpayXclSeq", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_EQP_PPAY_SEQ", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>단말기대금요금선납재집계삭제</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-26 14:25:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUEqpPpayXclDel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbUpdate("UEqpPpayXclDel", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>단말기대금요금선납재집계상세삭제</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-26 14:26:16
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUEqpPpayXclDtlDel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbUpdate("UEqpPpayXclDtlDel", requestData, onlineCtx); 
    
        return responseData;
    }

    /**
	 * <pre>단말기대금클럽T재집계</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-28 16:03:42
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIEqpClubTXcl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IEqpClubTXcl", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>단말기대금클럽T재집계상세</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-28 16:03:58
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIEqpClubTXclDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IEqpClubTXclDtl", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>단말기대금클럽T엑셀업로드검증조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-01-21 10:46:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpClubTXclExcelList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SEqpClubTXclExcelList", requestData, onlineCtx);
        
        // 2.결과값 셋팅     
        if ( record != null ) {
            responseData.putFieldMap(record);
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금클럽T엑셀저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-01-21 11:23:42
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIEqpClubTXclExcel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IEqpClubTXclExcel", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>단말기대금클럽T엑셀상세저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-01-21 11:24:08
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIEqpClubTXclExcelDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IEqpClubTXclExcelDtl", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>단말기대금클럽T시퀀스채번</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-01-21 11:26:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpClubTXclSeq(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SEqpClubTXclSeq", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }
  
}
