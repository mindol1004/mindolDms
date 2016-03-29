package dms.bi.bibbase.biz;

import java.util.Map;

import org.apache.commons.logging.Log;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.RecordSet;
import nexcore.framework.core.log.LogManager;


/**
 * <ul>
 * <li>업무 그룹명 : dms/기준정보</li>
 * <li>단위업무명: [DU]박스바코드관리</li>
 * <li>설  명 : <pre>박스바코드관리</pre></li>
 * <li>작성일 : 2015-06-29 11:07:12</li>
 * <li>작성자 : 개발자 (developer)</li>
 * </ul>
 *
 * @author 개발자 (developer)
 */
public class DBIBoxBarcdMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DBIBoxBarcdMgmt(){
		super();
	}

	/**
	 * <pre>박스바코드 상세조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-09 15:06:43
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dSBoxBarcdDtlLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
    	IRecordSet rsReturn = dbSelect("SBoxBarcdDtlLst", requestData, onlineCtx);
    	// 2.결과값 셋팅
    	responseData.putRecordSet("RS_BOXDTL_LST", rsReturn);
    	
    	// 중고폰 업무구분 추가
    	String boxBarcode = requestData.getField("BOX_BAR_CD");
    	if (boxBarcode != null && boxBarcode.length() > 0){
            IRecordSet rsSet = dbSelect("SEpInfo", requestData, onlineCtx);
            responseData.putRecordSet("RS_EP_LST", rsSet);
        } else {
            IRecordSet rsSet = new RecordSet("RS_EP_LST", new String[]{"MGMT_BAR_CD","BOX_BAR_CD","S_GB"});
            responseData.putRecordSet("RS_EP_LST", rsSet);
        }
    	
    	  
        return responseData;
    }

    /**
	 * <pre>박스바코드 입력</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-09 15:06:43
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dIBoxBarcd(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
     	dbInsert("IBoxBarcd", requestData, onlineCtx);
        return responseData;
    }

    /**
	 * <pre>박스바코드 세팅</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-09 15:06:43
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dUBoxBarcdSet(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
     	dbUpdate("UBoxBarcdSet", requestData, onlineCtx);
        return responseData;
    }

    /**
	 * <pre>박스바코드 채번</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-09 15:06:43
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dSBoxBarcdSeq(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IRecord record = dbSelectSingle("SBoxBarcdSeq", requestData, onlineCtx);
    	if ( record != null ) {
    		responseData.putFieldMap(record);
    	}
        return responseData;
    }

    /**
	 * <pre>박스바코드 조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-13 09:32:28
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSBoxBarcd(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SBoxBarcd", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_BOXBAR", rsReturn);
        return responseData;
    }

    /**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-08-31 16:20:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpseq(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SEqpseq", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_EQP_SEQ_LIST", rsReturn);
        return responseData;
    }

	/**
	 *
	 *
	 * @author 이하나 (hana526)
	 * @since 2015-09-14 18:20:36
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSProdInfo(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    Log logger = LogManager.getFwkLog(); 
	    IDataSet req = (IDataSet) requestData.clone();
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SProdInfo", requestData, onlineCtx);
        
        
        if (rsReturn.getRecordCount() > 0) {
        // 2.결과값 셋팅
//        logger.debug("\n\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  "+rsReturn);
       	req.putFieldMap(rsReturn.getRecordMap(0));
//       	logger.debug("\n\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  "+req);
        IRecordSet rsReturn2 = dbSelect("SProdPrcCheck", req, onlineCtx);
        rsReturn.getRecord(0).set("CHECK_YN", rsReturn2.get(0, "CHECK_YN")  );
//        logger.debug("\n\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  "+rsReturn2);
//        logger.debug("\n\nSProdPrcCheck end");
//        logger.debug("\n\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  \n\n");
        }
        
        
        responseData.putRecordSet("RS_PRODINFO", rsReturn);
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 이하나 (hana526)
	 * @since 2015-12-09 15:19:58
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUBoxBarcd(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IDataSet req = new DataSet();
	    req.putField("USER_NO", requestData.getField("USER_NO"));
	    req.putField("BOX_NO", requestData.getField("BOX_NO"));
	    req.putField("PROD_QTY", requestData.getField("PROD_QTY"));
	    
        // 1.쿼리문 호출
     	dbUpdate("UBoxBarcd", req, onlineCtx);
        return responseData;
	}
  
}
