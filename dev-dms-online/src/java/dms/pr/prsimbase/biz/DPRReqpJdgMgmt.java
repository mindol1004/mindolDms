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
 * <li>단위업무명: 임대폰감정관리</li>
 * <li>설  명 : <pre>임대폰감정관리</pre></li>
 * <li>작성일 : 2015-07-21 16:46:05</li>
 * <li>작성자 : 이영진 (newnofixing)</li>
 * </ul>
 *
 * @author 이영진 (newnofixing)
 */
public class DPRReqpJdgMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DPRReqpJdgMgmt(){
		super();
	}

    /**
	 * <pre>임대폰감정목록총건수</pre>
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-07-21 16:49:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSReqpJdgLstTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SReqpJdgLstTotCnt", requestData, onlineCtx);
        
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);

        return responseData;
    }

    /**
	 * <pre>임대폰감정목록조회</pre>
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-07-21 16:51:25
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSReqpJdgLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();

        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SReqpJdgLstPaging", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_REQP_JDG_LIST", rsReturn);

        return responseData;
    }
  
}
