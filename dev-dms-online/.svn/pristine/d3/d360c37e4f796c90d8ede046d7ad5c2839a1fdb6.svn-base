package dms.bi.bibbase.biz;

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
 * <li>업무 그룹명 : dms/기준정보</li>
 * <li>단위업무명: [DU]단말기모델관리</li>
 * <li>설  명 : <pre>단말기모델관리</pre></li>
 * <li>작성일 : 2015-06-29 11:03:59</li>
 * <li>작성자 : 개발자 (developer)</li>
 * </ul>
 *
 * @author 개발자 (developer)
 */
public class DBIEqpMdlMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DBIEqpMdlMgmt(){
		super();
	}

	/**
	 * <pre>단말기총건수</pre>
	 *
	 * @author 개발자 (developer)
	 * @since 2015-06-29 11:39:48
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpMdlTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출	    
		IRecord record = dbSelectSingle("SEqpMdlTotCnt", requestData, onlineCtx);
		// 2.결과값 셋팅	    
		responseData.putFieldMap(record);
		return responseData;
	}

	/**
	 * <pre>단말기모델조회</pre>
	 *
	 * @author 개발자 (developer)
	 * @since 2015-06-29 11:42:30
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpMdlPaging(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SEqpMdlPaging", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_EQP_MDL_LIST", rsReturn);
		return responseData;
	}

	/**
	 * <pre>단말기모델 입력</pre>
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-06-30 17:57:20
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIEqpMdl(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		dbInsert("IEqpMdl", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * <pre>모델저장사전체크</pre>
	 *
	 * @author admin (admin)
	 * @since 2015-06-29 11:03:59
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpMdlListChk(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecord record = dbSelectSingle("SEqpMdlListChk", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putFieldMap(record);
		return responseData;
	}

	/**
	 * <pre>단말기모델수정</pre>
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-06-29 11:03:59
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUEqpMdl(IDataSet requestData, IOnlineContext onlineCtx) {
		DataSet responseData = new DataSet();
		// 1.쿼리문 호출
		dbInsert("UEqpMdl", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * <pre>단말기모델삭제</pre>
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-07-01 08:43:57
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDEqpMdl(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		dbDelete("DEqpMdl", requestData, onlineCtx);
		return responseData;
	}

    /**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-08-04 15:06:06
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpColorLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SEqpColorLst", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_EQP_COLOR_LIST", rsReturn);
        return responseData;
    }

    /**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-08-04 17:40:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDEqpColor(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        dbDelete("DEqpColor", requestData, onlineCtx);
        return responseData;
    }

    /**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-08-04 17:41:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIEqpColor(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        dbInsert("IEqpColor", requestData, onlineCtx);
        return responseData;
    }
  
}
