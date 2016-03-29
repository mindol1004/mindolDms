package dms.ep.epsrpbase.biz;

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
 * <li>단위업무명: [DU]미정산내역관리</li>
 * <li>설  명 : <pre>미정산내역관리</pre></li>
 * <li>작성일 : 2015-12-02 18:06:04</li>
 * <li>작성자 : 박민정 (dangnagu2)</li>
 * </ul>
 *
 * @author 박민정 (dangnagu2)
 */
public class DEPXclMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPXclMgmt(){
		super();
	}

    /**
	 * <pre>미정산내역조회</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-12-02 18:07:46
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSXclListPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecordSet rsReturn = dbSelect("SXclListPaging", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putRecordSet("RS_XCL_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>미정산내역조회총건수</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-12-02 18:08:39
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSXclListTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SXclListTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }
  
}
