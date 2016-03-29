package dms.nr.nrbisbase.biz;

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
 * <li>단위업무명: [PU]보증보험해지현황</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-10-12 17:18:17</li>
 * <li>작성자 : 안진갑 (bella21cjk)</li>
 * </ul>
 *
 * @author 안진갑 (bella21cjk)
 */
public class PNRInsuTermPrstMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNRInsuTermPrstMgmt(){
		super();
	}

    /**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-10-12 17:18:17
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : RENTAL_END_STA_DT [필드1]
	 *	- field : RENTAL_END_END_DT [필드2]
	 *	- field : SVC_MGMT_NO [필드3]
	 *	- field : EQP_MDL_CD [필드4]
	 *	- field : INSURE_MGMT_NO [필드5]
	 *	- field : OP_FNSH_YN [필드6]
	 *	- field : INCMP_ITM [필드7]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pInqInsuTermPrstLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSISBase", "fInqInsuTermPrstLst", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        // 3. 결과값 리턴
        responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
        
        return responseData;
    }
  
}
