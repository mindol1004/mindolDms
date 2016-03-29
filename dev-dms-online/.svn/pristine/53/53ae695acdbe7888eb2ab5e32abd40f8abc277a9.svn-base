package dms.ep.epsimbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]재고상품관리</li>
 * <li>설  명 : <pre>재고상품관리 DU</pre></li>
 * <li>작성일 : 2015-09-08 14:40:56</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class DEPInveProdDisMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPInveProdDisMgmt(){
		super();
	}

    /**
	 * <pre>재고상품조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-08 16:14:13
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInveProdDisPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInveProdDisPaging", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_INVE_DIS_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>재고상품조회총건수</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-08 16:14:35
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInveProdDisTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SInveProdDisTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>재고상품체크</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-09 16:17:21
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInveProdDisChkList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInveProdDisChkList", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_INVE_CHK_LIST", rsReturn);
    
        return responseData;
    }
  
}
