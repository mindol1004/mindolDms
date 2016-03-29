package dms.sc.scbbase.biz;

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
 * <li>업무 그룹명 : dms/시스템공통</li>
 * <li>단위업무명: [DU]Interface로그조회</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-11-24 09:43:31</li>
 * <li>작성자 : 진병수 (greatjin)</li>
 * </ul>
 *
 * @author 진병수 (greatjin)
 */
public class DSCIfLogMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCIfLogMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 진병수 (greatjin)
	 * @since 2015-09-14 16:33:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSIfErpLogLstTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecord record = dbSelectSingle("SIfErpLogLstTotCnt", requestData, onlineCtx);
		// 2. 결과값 셋팅
		responseData.putFieldMap(record);
		return responseData;
	}

	/**
	 *
	 *
	 * @author 진병수 (greatjin)
	 * @since 2015-11-23 14:38:21
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSIfErpLogLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1. 쿼리문 호출
		IRecordSet rsReturn = dbSelect("SIfErpLogLstPaging", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_IF_LOG_LIST", rsReturn);
		return responseData;
	}

	/**
	 *
	 *
	 * @author 진병수 (greatjin)
	 * @since 2015-11-24 11:12:06
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSSlipTyp4Combo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1. 쿼리문 호출
		IRecordSet rsReturn = dbSelect("SSlipTyp4Combo", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_COMBO_LIST", rsReturn);
		return responseData;
	}
  
}
