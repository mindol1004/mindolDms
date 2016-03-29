package dms.ep.epsfibase.biz;

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
 * <li>단위업무명: [DU]FPA가용재고관리</li>
 * <li>설  명 : <pre>FPA가용재고관리</pre></li>
 * <li>작성일 : 2016-02-17 12:14:53</li>
 * <li>작성자 : 양재석 (jsyang)</li>
 * </ul>
 *
 * @author 양재석 (jsyang)
 */
public class DEPFPAAvailInveMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPFPAAvailInveMgmt(){
		super();
	}

    /**
	 * <pre>FPA가용재고조회목록총건수</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2016-02-17 13:42:40
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqFPAAvailInveListTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SInqFPAAvailInveListTotCnt", requestData, onlineCtx);
        
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);     
    
        return responseData;
    }

    /**
	 * <pre>FPA가용재고조회목록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2016-02-17 12:14:53
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqFPAAvailInveListPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInqFPAAvailInveListPaging", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_FPA_AVAIL_INVE_LIST", rsReturn);
    
        return responseData;
    }
  
}
