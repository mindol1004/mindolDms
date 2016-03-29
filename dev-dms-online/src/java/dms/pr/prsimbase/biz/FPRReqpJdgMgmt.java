package dms.pr.prsimbase.biz;

import java.util.Map;

import fwk.utils.PagenateUtils;
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
 * <li>업무 그룹명 : dms/임대R</li>
 * <li>단위업무명: [FU]임대폰감정관리</li>
 * <li>설  명 : <pre>[FU]임대폰감정관리</pre></li>
 * <li>작성일 : 2015-07-21 16:44:58</li>
 * <li>작성자 : 이영진 (newnofixing)</li>
 * </ul>
 *
 * @author 이영진 (newnofixing)
 */
public class FPRReqpJdgMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FPRReqpJdgMgmt(){
		super();
	}

    /**
	 * <pre>임대폰감정목록조회</pre>
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-07-21 16:47:16
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqReqpJdgLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        IDataSet dsCnt = new DataSet();
        IRecordSet rsPagingList = null;
        int intTotalCnt = 0;
        try {
            // 1. DU lookup
            DPRReqpJdgMgmt dPRReqpJdgMgmt = (DPRReqpJdgMgmt) lookupDataUnit(DPRReqpJdgMgmt.class);
            // 2. TOTAL COUNT DM호출
            dsCnt = dPRReqpJdgMgmt.dSReqpJdgLstTotCnt(requestData, onlineCtx);
            intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
            PagenateUtils.setPagenatedParamsToDataSet(requestData);
            //PagenateUtils.setPagenatedParamsToDataSet(reqDs);
            // 3. LISTDM호출
            responseData = dPRReqpJdgMgmt.dSReqpJdgLstPaging(requestData, onlineCtx);

            rsPagingList = responseData.getRecordSet("RS_REQP_JDG_LIST");
            PagenateUtils.setPagenatedParamToRecordSet(rsPagingList, requestData, intTotalCnt);
            

        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }       
        return responseData;
    }
  
}
