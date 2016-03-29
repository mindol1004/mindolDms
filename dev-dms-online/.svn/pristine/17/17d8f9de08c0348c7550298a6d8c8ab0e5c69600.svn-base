package dms.nr.nrsisbase.biz;

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
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [DU]보증보험해지현황</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-10-12 17:19:30</li>
 * <li>작성자 : 안진갑 (bella21cjk)</li>
 * </ul>
 *
 * @author 안진갑 (bella21cjk)
 */
public class DNRInsuTermPrstMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DNRInsuTermPrstMgmt(){
		super();
	}

    /**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-10-12 17:19:30
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInsuTermPrstLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInsuTermPrstLstPaging", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_INSU_INFO_LIST", rsReturn);
        
        return responseData;
    }

    /**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-10-12 17:28:48
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInsuTermPrstLstTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SInsuTermPrstLstTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record); 
        
        return responseData;
    }

    /**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-10-14 16:39:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSCBackInsureSum(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SCBackInsureSum", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SUM_LIST", rsReturn);
        
        return responseData;
    }
  
}
