package dms.pr.prsimbase.biz;

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
 * <li>단위업무명: [DU]임대폰분실관리</li>
 * <li>설  명 : <pre>[DU]임대폰분실관리</pre></li>
 * <li>작성일 : 2015-07-22 18:18:21</li>
 * <li>작성자 : 이준우 (elmsliw)</li>
 * </ul>
 *
 * @author 이준우 (elmsliw)
 */
public class DPRReqpClctInfoLst extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DPRReqpClctInfoLst(){
		super();
	}

	/**
	 * <pre>[FM]임대폰회수정보조회목록</pre>
	 *
	 * @author 이준우 (elmsliw)
	 * @since 2015-07-23 21:23:13
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSReqpClctInfoLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SReqpClctInfoLstPaging", requestData, onlineCtx);
		
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_RCIBM_LIST", rsReturn);
	
	    return responseData;
	}

	/**
	 * <pre>[FM]임대폰회수정보조회총건수</pre>
	 *
	 * @author 이준우 (elmsliw)
	 * @since 2015-07-23 21:23:13
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSReqpClctInfoLstTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출     
		IRecord record = dbSelectSingle("SReqpClctInfoLstTotCnt", requestData, onlineCtx);
		
		// 2.결과값 셋팅     
		responseData.putFieldMap(record);
	
	    return responseData;
	}
  
}
