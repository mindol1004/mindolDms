package dms.nr.nrscsbase.biz;

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
 * <li>단위업무명: [DU]위약금시뮬레이션</li>
 * <li>설  명 : <pre>[DU]위약금시뮬레이션</pre></li>
 * <li>작성일 : 2015-08-10 18:48:07</li>
 * <li>작성자 : 문재웅 (jaiwoong)</li>
 * </ul>
 *
 * @author 문재웅 (jaiwoong)
 */
public class DNRPenSimul extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DNRPenSimul(){
		super();
	}

    /**
	 * <pre>[DM]위약금기본정보조회</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-08-10 18:51:04
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSPenBaseInfo(IDataSet requestData, IOnlineContext onlineCtx) {
	    
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출SPenBaseInfo
        IRecordSet rsReturn = dbSelect("SPenBaseInfo", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_PEN_BASE_INFO", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>[DM]위약금시뮬레이션조회</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-08-10 18:52:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSPenSimul(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출SPenBaseInfo
        IRecordSet rsReturn = dbSelect("SPenSimul", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_PEN_SIMUL", rsReturn);
    
        return responseData;
    }
  
}
