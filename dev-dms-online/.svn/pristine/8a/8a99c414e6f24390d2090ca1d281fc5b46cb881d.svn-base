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
 * <li>단위업무명: [DU]취소재고반제전표생성</li>
 * <li>설  명 : <pre>취소재고반제전표생성</pre></li>
 * <li>작성일 : 2015-11-17 16:54:11</li>
 * <li>작성자 : 양재석 (jsyang)</li>
 * </ul>
 *
 * @author 양재석 (jsyang)
 */
public class DEPCnclInveRevdSlipRgst extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPCnclInveRevdSlipRgst(){
		super();
	}

    /**
	 * <pre>취소재고단말기정산전표정보조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-11-17 18:15:22
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqCnclInveEqpXclSlipInfo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        IRecordSet rsReturn = dbSelect("SInqCnclInveEqpXclSlipInfo", requestData, onlineCtx);
        responseData.putRecordSet("RS_CNCL_INVE_EQP_XCL_INFO", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>취소재고정산상세등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-11-18 15:43:51
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dICnclInveXclDtlRgst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("ICnclInveXclDtlRgst", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>취소재고정산일련번호채번</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-11-18 15:47:52
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqCnclInveInveXclSeq(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SInqCnclInveInveXclSeq", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>취소재고정산합계조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-11-18 16:04:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqCnclInveXclSumList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInqCnclInveXclSumList", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("INVE_XCL_SUM_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>취소재고정산등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-11-18 16:25:47
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dICnclInveXclRgst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("ICnclInveXclRgst", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>취소재고단말기정산전표정보조회상담</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-11-18 16:51:58
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqCnclInveEqpXclSlipInfoCnsl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        IRecordSet rsReturn = dbSelect("SInqCnclInveEqpXclSlipInfoCnsl", requestData, onlineCtx);
        responseData.putRecordSet("RS_CNCL_INVE_EQP_XCL_INFO", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>취소재고계좌송금정산정보조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-11-18 17:50:47
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqCnclInveAccoRmtXclInfo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        IRecordSet rsReturn = dbSelect("SInqCnclInveAccoRmtXclInfo", requestData, onlineCtx);
        responseData.putRecordSet("RS_CNCL_INVE_ACCO_XCL_INFO", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>[DM]취소재고계좌송금정산일련번호채번</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-11-18 18:35:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqCnclInveAccoRmtXclSeq(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SInqCnclInveAccoRmtXclSeq", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>취소재고계좌송금정산상세등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-11-18 19:29:38
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dICnclInveAccoRmtXclDtlRgst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("ICnclInveAccoRmtXclDtlRgst", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>취소재고계좌송금정산합계조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-11-18 19:36:19
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqCnclInveAccoRmtXclSumList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInqCnclInveAccoRmtXclSumList", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("ACCO_RMT_XCL_SUM_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>취소재고계좌송금정산등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-11-18 19:42:39
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dICnclInveAccoRmtXcl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("ICnclInveAccoRmtXcl", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>취소재고계좌송금정산정보조회상담</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-11-19 13:33:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqCnclInveAccoRmtXclInfoCnsl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        IRecordSet rsReturn = dbSelect("SInqCnclInveAccoRmtXclInfoCnsl", requestData, onlineCtx);
        responseData.putRecordSet("RS_CNCL_INVE_ACCO_XCL_INFO", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>취소재고요금공제정산정보조회상담</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-11-17 16:54:11
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqCnclInveFeeDedcXclInfoCnsl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        IRecordSet rsReturn = dbSelect("SInqCnclInveFeeDedcXclInfoCnsl", requestData, onlineCtx);
        responseData.putRecordSet("RS_CNCL_INVE_FEE_XCL_INFO", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>취소재고요금공제정산정보조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-11-26 15:55:26
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqCnclInveFeeDedcXclInfo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        IRecordSet rsReturn = dbSelect("SInqCnclInveFeeDedcXclInfo", requestData, onlineCtx);
        responseData.putRecordSet("RS_CNCL_INVE_FEE_XCL_INFO", rsReturn);
    
        return responseData;
    }
  
}
