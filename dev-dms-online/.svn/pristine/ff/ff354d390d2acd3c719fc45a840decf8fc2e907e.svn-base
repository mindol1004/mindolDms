package dms.ep.epscsbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]중고단말기매입매출현황관리</li>
 * <li>설  명 : <pre>중고단말기매입매출현황관리 DU</pre></li>
 * <li>작성일 : 2015-09-01 12:43:39</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class DEPEqpPrslMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPEqpPrslMgmt(){
		super();
	}

    /**
	 * <pre>중고단말기매입매출현황조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-01 12:45:41
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpPrslListPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SEqpPrslListPaging", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_EQP_PRSL_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>중고단말기매입매출현황총건수</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-01 12:46:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpPrslListTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SEqpPrslListTotCnt", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }
  
}
