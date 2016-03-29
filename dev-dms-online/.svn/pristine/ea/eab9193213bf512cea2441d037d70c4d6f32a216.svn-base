package dms.ep.epscsbase.biz;

import java.util.Map;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DM]재감정품질검사관리</li>
 * <li>설  명 : <pre>[DM]재감정품질검사관리</pre></li>
 * <li>작성일 : 2015-08-28 14:32:54</li>
 * <li>작성자 : 양재석 (jsyang)</li>
 * </ul>
 *
 * @author 양재석 (jsyang)
 */
public class DEPRJdgQltyJdgMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPRJdgQltyJdgMgmt(){
		super();
	}

    /**
	 * <pre>[DM]재감정품질검사대상조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-28 14:32:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqRJdgQltyJdgObjListPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInqRJdgQltyJdgObjListPaging", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("QLTY_JDG_OBJ_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>재감정품질검사대상조회총건수</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-28 15:45:37
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : TOTAL_CNT [총건수]
	 * </pre>
	 */
	public IDataSet dSInqRJdgQltyJdgObjListTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SInqRJdgQltyJdgObjListTotCnt", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>재감정품질검사확인조회총건수</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-02 15:13:00
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : TOTAL_CNT [필드1]
	 * </pre>
	 */
	public IDataSet dSInqRJdgQltyJdgRegListTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SInqRJdgQltyJdgRegListTotCnt", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>재감정품질검사확인조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-02 15:13:25
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqRJdgQltyJdgRegListPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInqRJdgQltyJdgRegListPaging", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("QLTY_JDG_REG_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>재감정품질검사대상상세조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-07 09:35:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqRJdgQltyJdgObjDtlList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInqRJdgQltyJdgObjDtlList", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("QLTY_JDG_OBJ_DTL_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>[DM]재감정품질검사확인상세조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-07 13:39:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqRJdgQltyJdgRegDtlList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInqRJdgQltyJdgRegDtlList", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("QLTY_JDG_REG_DTL_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>재감정품질검사대상검수확인수정</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-08 09:57:19
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dURJdgQltyJdgRegUpd(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {       
            dbInsert("URJdgQltyJdgRegUpd", requestData, onlineCtx); 
        }
        catch ( BizRuntimeException e ) {
            throw e;
        } 
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-08 11:11:28
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : DIS_CNT [재고건수]
	 * </pre>
	 */
	public IDataSet dSRJdgQJdgInqEqpDisList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SRJdgQJdgInqEqpDisListCnt", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-08 11:26:04
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : PROD_DIS_CNT [상품재고건수]
	 * </pre>
	 */
	public IDataSet dSRJdgQJdgInqEqpProdDisList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SRJdgQJdgInqEqpProdDisListCnt", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-08 13:47:02
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : ORD_CNT [오더건수]
	 * </pre>
	 */
	public IDataSet dSRJdgQJdgInqEqpInOrdListCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SRJdgQJdgInqEqpInOrdListCnt", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>입고관리번호채번</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-08 15:43:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : IN_MGMT_NO [입고 관리 번호]
	 * </pre>
	 */
	public IDataSet dSInqDisInMgmtNo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SInqDisInMgmtNo", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>재감정품질검사입고등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-08 16:30:48
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIRjdgQltyJdgInRgst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IRjdgQltyJdgInRgst", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-08 17:04:04
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : IN_MGMT_NO [입고 관리 번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : IN_SEQ [입고 순번]
	 * </pre>
	 */
	public IDataSet dSInqDisInDtlSeq(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SInqDisInDtlSeq", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>재감정품질검사입고상세등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-08 17:30:16
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIRjdgQltyJdgInDtlRgst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IRjdgQltyJdgInDtlRgst", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>재감정품질검사입고구성품등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-08 18:02:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIRjdgQltyJdgInCpntRgst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IRjdgQltyJdgInCpntRgst", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>재감정품질검사재고금액등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-08 20:01:46
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIRjdgQltyJdgDisAmtRgst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IRjdgQltyJdgDisAmtRgst", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>[DM]재감정품질검사단말기재고등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-28 14:32:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIRJdgQltyJdgEqpDisRgst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IRJdgQltyJdgEqpDisRgst", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>재감정품질검사입출고이력등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-09 14:36:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIRJdgQltyJdgDisInoutHstRgst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IRJdgQltyJdgDisInoutHstRgst", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>재감정품질검사재고이력등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-09 14:44:34
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIRJdgQltyJdgDisHstRgst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IRJdgQltyJdgDisHstRgst", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>재감정품질검사대상입고정보조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-10 15:17:43
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqRJdgQltyJdgInInfo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInqRJdgQltyJdgInInfo", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("QLTY_JDG_IN_INFO_LIST", rsReturn);
    
        return responseData;
    }
  
}
