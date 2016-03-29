package dms.ep.epscsbase.biz;

import java.util.Map;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.log.LogManager;

import org.apache.commons.logging.Log;

import fwk.utils.HpcUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]반송대상관리</li>
 * <li>설  명 : <pre>반송대상관리</pre></li>
 * <li>작성일 : 2015-09-10 11:02:02</li>
 * <li>작성자 : 박민정 (dangnagu2)</li>
 * </ul>
 *
 * @author 박민정 (dangnagu2)
 */
public class DEPRtnMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPRtnMgmt(){
		super();
	}

    /**
	 * <pre>검수반송대상총건수</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-09-10 11:02:33
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSJdgRtnObjListTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SJdgRtnObjListTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
        
        return responseData;
    }

    /**
	 * <pre>검수반송대상조회</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-09-10 11:02:02
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSJdgRtnObjListPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecordSet rsReturn = dbSelect("SJdgRtnObjListPaging", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putRecordSet("RQ_JDG_RTN_OBJ_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>검수반송등록</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-09-10 11:06:36
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIJdgRtn(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {       
            dbInsert("UJdgRtn", requestData, onlineCtx);    
        }
        catch ( BizRuntimeException e ) {
            throw e;
        } 
    
        return responseData;
    }

    /**
	 * <pre>고객반송대상총건수</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-09-10 11:02:02
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSCustRtnObjListTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SCustRtnObjListTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
        return responseData;
    }

    /**
	 * <pre>고객반송대상조회</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-09-10 11:02:02
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSCustRtnObjListPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecordSet rsReturn = dbSelect("SCustRtnObjListPaging", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putRecordSet("RS_CUST_RTN_OBJ_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>고객반송등록</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-09-10 11:02:02
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dICustRtnReg(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {       
            dbInsert("UCustRtnReg", requestData, onlineCtx);    
        }
        catch ( BizRuntimeException e ) {
            throw e;
        } 
    
        return responseData;
    }

    /**
	 * <pre>대리점반송대상총건수</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-09-10 13:04:17
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAgnRtnObjListTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SAgnRtnObjListTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
        
        return responseData;
    }

    /**
	 * <pre>대리점반송대상조회</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-09-10 11:02:02
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAgnRtnObjListPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecordSet rsReturn = dbSelect("SAgnRtnObjListPaging", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putRecordSet("RS_AGN_RTN_OBJ_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>대리점반송등록</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-09-10 13:08:08
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIAgnRtnReg(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {       
            dbInsert("UAgnRtnReg", requestData, onlineCtx);    
        }
        catch ( BizRuntimeException e ) {
            throw e;
        } 
    
        return responseData;
    }

    /**
	 * <pre>고객반송등록취소</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-09-11 10:19:55
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUCustRtnUpd(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {       
            dbInsert("UCustRtnUpd", requestData, onlineCtx);    
        }
        catch ( BizRuntimeException e ) {
            throw e;
        } 
    
        return responseData;
    }

    /**
	 * <pre>대리점반송등록취소</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-09-10 11:02:02
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUAgnRtnUpd(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {       
            dbInsert("UAgnRtnUpd", requestData, onlineCtx);    
        }
        catch ( BizRuntimeException e ) {
            throw e;
        } 
    
        return responseData;
    }

    /**
	 * <pre>대리점반송상담등록</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-09-21 11:21:49
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIAgnRtnConReg(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
        try {       
            dbInsert("UAgnRtnConReg", requestData, onlineCtx);    
        }
        catch ( BizRuntimeException e ) {
            throw e;
        } 
    
        return responseData;
    }

    /**
	 * <pre>대리점반송상담등록취소</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-09-21 11:22:13
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUAgnRtnConUpd(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {       
            dbInsert("UAgnRtnConUpd", requestData, onlineCtx);    
        }
        catch ( BizRuntimeException e ) {
            throw e;
        } 
    
        return responseData;
    }

	/**
	 * <pre>반송등록박스조회</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-10 11:02:02
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dInqRtnRegBoxList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출     
        IRecordSet rsReturn = null;
        if("01".equals(requestData.getField("SR_GUBUN")))//고객
    		rsReturn = dbSelect("SgetBoxPrchsLst", requestData, onlineCtx);
        else if("02".equals(requestData.getField("SR_GUBUN"))) //대리점
        	rsReturn = dbSelect("SgetBoxLst", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putRecordSet("RS_AGN_RTN_OBJ_LIST", rsReturn);
        
	    return responseData;
	}

	/**
	 * <pre>[DM]반송등록대상조회 </pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-23 17:43:42
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dInqRtnRegObjList(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		/// logger
		Log logger = LogManager.getFwkLog(); 
		requestData.putField("DPSTR_ENPT", HpcUtils.encodeByAES(requestData.getField("DPSTR")));
		// 1.쿼리문 호출     
		IRecordSet rsReturn = null;
		if("01".equals(requestData.getField("SR_GUBUN")))//고객
			rsReturn = dbSelect("SRtnRegCustObjList", requestData, onlineCtx);
		else if("02".equals(requestData.getField("SR_GUBUN"))) //대리점
			rsReturn = dbSelect("SRtnRegAgnObjList", requestData, onlineCtx);
		// 2.결과값 셋팅     
		responseData.putRecordSet("RS_RTN_OBJ_LIST", rsReturn);

		return responseData;
	}

	/**
	 *
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-09-24 18:20:46
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIRegRtnRegObjList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    // logger
	  	Log logger = LogManager.getFwkLog(); 
	    IRecordSet rs = requestData.getRecordSet("RQ_AGN_RTN_LIST");
	    Map mSave = null;
	    
	    //고객
	    if("01".equals(requestData.getField("SR_GUBUN"))){
	    	for (int i = 0; i < rs.getRecordCount(); i++) {
	    		mSave = rs.getRecordMap(i);
	    		if ("1".equals(mSave.get("CHK"))) {
	    			dbUpdate("UPrchsMgmtRtn", mSave,onlineCtx);

	    		}
	    	}
	    }
    	//대리점
	    else if("02".equals(requestData.getField("SR_GUBUN"))){
	    	for (int i = 0; i < rs.getRecordCount(); i++) {
	    		mSave = rs.getRecordMap(i);
	    		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> DATA_GB :"+mSave.get("DATA_GB"));
	    		if ("1".equals(mSave.get("CHK"))) {
	    			if ("C".equals(mSave.get("DATA_GB"))) {
	    				dbUpdate("UConsultRtn", mSave, onlineCtx);
	    			} else if ("P".equals(mSave.get("DATA_GB"))) {
	    				dbUpdate("UPrchsMgmtRtn", mSave,onlineCtx);
	    			}
	    		}
	    	}

	    }
	    return responseData;
	}
  
}
