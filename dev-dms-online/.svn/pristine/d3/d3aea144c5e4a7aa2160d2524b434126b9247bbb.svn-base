package dms.ep.epssxbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]재고이관정산관리</li>
 * <li>설  명 : <pre>재고이관정산관리 DU</pre></li>
 * <li>작성일 : 2015-11-19 18:23:58</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class DEPInveTrnsfXclMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPInveTrnsfXclMgmt(){
		super();
	}

    /**
	 * <pre>재고이관정산관리조회총건수</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-20 09:44:46
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInveTrnsfXclTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SInveTrnsfXclTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record); 
    
        return responseData;
    }

    /**
	 * <pre>재고이관정산관리조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-20 09:45:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInveTrnsfXclPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInveTrnsfXclPaging", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_INVE_XCL_LIST", rsReturn);
    
        return responseData;
    }
  
}
