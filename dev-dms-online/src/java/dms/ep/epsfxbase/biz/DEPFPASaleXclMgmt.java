package dms.ep.epsfxbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]FPA판매정산관리</li>
 * <li>설  명 : <pre>FPA판매정산관리 DU</pre></li>
 * <li>작성일 : 2016-02-23 10:04:56</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class DEPFPASaleXclMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPFPASaleXclMgmt(){
		super();
	}

    /**
	 * <pre>FPA판매정산관리조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-23 10:07:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaSaleXclMgmtList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SFpaSaleXclMgmtList", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_FPA_SALE_XCL_LIST", rsReturn); 
    
        return responseData;
    }

    /**
	 * <pre>FPA판매정산관리상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-02-24 10:10:45
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaSaleXclMgmtDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SFpaSaleXclMgmtDtl", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_FPA_SALE_XCL_DTL", rsReturn); 
    
        return responseData;
    }
  
}
