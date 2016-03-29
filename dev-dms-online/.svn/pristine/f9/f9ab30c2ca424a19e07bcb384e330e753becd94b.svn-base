package dms.nr.nrscsbase.biz;

import java.util.Map;

//import dms.nr.nrsscbase.biz.DNRCntrtPrstMgmt;
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
 * <li>단위업무명: [FU]위약금시뮬레이션</li>
 * <li>설  명 : <pre>[FU]위약금시뮬레이션</pre></li>
 * <li>작성일 : 2015-08-10 18:43:34</li>
 * <li>작성자 : 문재웅 (jaiwoong)</li>
 * </ul>
 *
 * @author 문재웅 (jaiwoong)
 */
public class FNRPenSimul extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNRPenSimul(){
		super();
	}

    /**
	 * <pre>[FM]위약금기본정보조회</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-08-10 18:45:03
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fPenBaseInfo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. DU lookup
            DNRPenSimul dNRPenSimul = (DNRPenSimul) lookupDataUnit(DNRPenSimul.class);
            // 2. LISTDM호출
            responseData = dNRPenSimul.dSPenBaseInfo(requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>[FM]위약금시뮬레이션조회</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-08-10 18:43:34
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fPenSimul(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. DU lookup
            DNRPenSimul dNRPenSimul = (DNRPenSimul) lookupDataUnit(DNRPenSimul.class);
            // 2. LISTDM호출
            responseData = dNRPenSimul.dSPenSimul(requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }
  
}
