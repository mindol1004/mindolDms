package dms.ep.epscsbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]재감정회수일자누락목록관리</li>
 * <li>설  명 : <pre>재감정회수일자누락목록관리</pre></li>
 * <li>작성일 : 2015-08-27 11:26:29</li>
 * <li>작성자 : 정동현 (jjddhh123)</li>
 * </ul>
 *
 * @author 정동현 (jjddhh123)
 */
public class DEPEqpRJdgMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPEqpRJdgMgmt(){
		super();
	}

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-08-27 11:26:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpClctOmitListTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SEqpClctOmitListTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-08-27 11:26:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpClctOmitListPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
	    IRecordSet rsReturn = dbSelect("SEqpClctOmitListPaging", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putRecordSet("RS_EQP_CLCT_LST", rsReturn);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-08-27 11:26:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUEqpClctOmit(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        // 1.쿼리문 호출
	    dbUpdate("UEqpClctOmit", requestData, onlineCtx);
	    
        return responseData;
    }

    /**
	 * <pre>재감정회수일자누락데이터조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2016-02-02 17:18:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpClctOmitList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecordSet rsReturn = dbSelect("SEqpClctOmitList", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putRecordSet("RS_EQP_CLCT_LST", rsReturn);
    
        return responseData;
    }
  
}
