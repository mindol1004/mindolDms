package dms.pr.prsxmbase.biz;

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
 * <li>업무 그룹명 : dms/임대R</li>
 * <li>단위업무명: [FU]매출정산등록</li>
 * <li>설  명 : <pre>[FU]매출정산등록</pre></li>
 * <li>작성일 : 2015-08-07 16:31:30</li>
 * <li>작성자 : 김상오 (myneti99)</li>
 * </ul>
 *
 * @author 김상오 (myneti99)
 */
public class FPRSaleXclRgst extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FPRSaleXclRgst(){
		super();
	}

	/**
	 * <pre>[FM]매출정산등록리스트</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-08-07 16:31:30
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : IN_DT [검색년월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_SALE_XCL_LIST
	 *		- field : XCL_YM [정산년월]
	 *		- field : XCL_NM [정산항목]
	 *		- field : XCL_QTY [건수]
	 *		- field : XCL_AMT [금액]
	 *		- field : XCL_DT [생성일자]
	 *		- field : XCL_CRTR_NM [생성자]
	 * </pre>
	 */
	public IDataSet fInqSaleXclList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        try {
            // 1. DU lookup
        	DPRSaleXclRgst dPRSaleXclRgst = (DPRSaleXclRgst) lookupDataUnit(DPRSaleXclRgst.class);
            // 2. 업무 DM호출
            responseData = dPRSaleXclRgst.dSInqSaleXclList(requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        }
    
	    
	    return responseData;
	}

	/**
	 * <pre>[FM]매출정산등록엑셀리스트</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-08-07 16:31:30
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqSaleXclExcelList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
            // 1. DU lookup
        	DPRSaleXclRgst dPRSaleXclRgst = (DPRSaleXclRgst) lookupDataUnit(DPRSaleXclRgst.class);
            // 2. 업무 DM호출
            responseData = dPRSaleXclRgst.dSInqSaleXclRgstExcelList(requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        }
	
	    return responseData;
	}

	/**
	 * <pre>[FM]정산등록 대상 데이터 카운트</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-08-18 16:56:13
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqSaleXclCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
            // 1. DU lookup
        	DPRSaleXclRgst dPRSaleXclRgst = (DPRSaleXclRgst) lookupDataUnit(DPRSaleXclRgst.class);
            // 2. 업무 DM호출
            responseData = dPRSaleXclRgst.dInqSaleXclCnt(requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        }
	
	    return responseData;
	}

	/**
	 * <pre>[FM]정산전표발행체크</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-10-16 16:41:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqSaleXclSlipCheck(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
            // 1. DU lookup
        	DPRSaleXclRgst dPRSaleXclRgst = (DPRSaleXclRgst) lookupDataUnit(DPRSaleXclRgst.class);
            // 2. 업무 DM호출
            responseData = dPRSaleXclRgst.dSSaleXclSlipCheck(requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        }
	
	    return responseData;
	}
  
}
