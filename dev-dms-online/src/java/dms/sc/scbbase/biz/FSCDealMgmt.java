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
 * <li>단위업무명: [FU]대리점정보조회</li>
 * <li>설  명 : <pre>대리점웹 대리점정보조회</pre></li>
 * <li>작성일 : 2015-09-14 08:18:21</li>
 * <li>작성자 : 박홍서 (uni9401)</li>
 * </ul>
 *
 * @author 박홍서 (uni9401)
 */
public class FSCDealMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCDealMgmt(){
		super();
	}

    /**
	 * <pre>대리점web 기본 대리점 정보조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-09-14 08:20:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqDealInfo(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        try {
            // 1. DU lookup
            DSCDealMgmt dSCDealMgmt = (DSCDealMgmt) lookupDataUnit(DSCDealMgmt.class);

            // 2. 업무 DM호출
            responseData = dSCDealMgmt.dInqDealInfo(requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        }
        return responseData;
    }
  
}
