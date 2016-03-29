package dms.nr.nrseabase.biz;

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
 * <li>단위업무명: [DM]단말기감정내역관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-11 17:09:51</li>
 * <li>작성자 : 김혁범 (kezmain1)</li>
 * </ul>
 *
 * @author 김혁범 (kezmain1)
 */
public class DNREqpJdgDTlInfoMgnt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DNREqpJdgDTlInfoMgnt(){
		super();
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-11 17:10:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpJdgDTlInfoTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출	    
  		IRecord record = dbSelectSingle("SEqpJdgDTlInfoTotCnt", requestData, onlineCtx);
  		// 2.결과값 셋팅	    
  		responseData.putFieldMap(record); 
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-11 17:11:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpJdgDTlInfoLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
 		IRecordSet rsReturn = dbSelect("SEqpJdgDTlInfoLstPaging", requestData, onlineCtx);
 		// 2.결과값 셋팅
 		responseData.putRecordSet("RS_JDG_DTL_INFO_LIST", rsReturn);
	
	    return responseData;
	}
  
}
