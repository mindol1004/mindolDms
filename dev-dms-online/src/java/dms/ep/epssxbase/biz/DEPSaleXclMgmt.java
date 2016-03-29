package dms.ep.epssxbase.biz;

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
 * <li>단위업무명: [DU]판매정산관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2016-01-25 15:45:41</li>
 * <li>작성자 : 김용진 (iamkim77)</li>
 * </ul>
 *
 * @author 김용진 (iamkim77)
 */
public class DEPSaleXclMgmt extends fwk.base.DataUnit {

	/**
	 * <pre>판매정산관리조회</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2016-01-25 15:45:41
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSSaleXclMgmtList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
		
	    // 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SSaleXclMgmtList", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_SALE_XCL_MGMT_LIST", rsReturn);
	
	    return responseData;
	}


  
}
