package dms.nr.nrsisbase.biz;

import java.util.Map;

import fwk.utils.HpcUtils;
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
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]보증보험해지현황</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-10-12 17:19:04</li>
 * <li>작성자 : 안진갑 (bella21cjk)</li>
 * </ul>
 *
 * @author 안진갑 (bella21cjk)
 */
public class FNRInsuTermPrstMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNRInsuTermPrstMgmt(){
		super();
	}

    /**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-10-12 17:27:00
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqInsuTermPrstLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        IDataSet dsCnt = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        IRecordSet cmpPolyListRs = null;
        int intTotalCnt = 0;

        try {
            // 1. DU lookup
            DNRInsuTermPrstMgmt dNRInsuTermPrstMgmt = (DNRInsuTermPrstMgmt) lookupDataUnit(DNRInsuTermPrstMgmt.class);
            
            responseData = dNRInsuTermPrstMgmt.dSCBackInsureSum(reqDs, onlineCtx);
            
            // 2. TOTAL COUNT DM호출
            dsCnt = dNRInsuTermPrstMgmt.dSInsuTermPrstLstTotCnt(requestData, onlineCtx);
            intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
            PagenateUtils.setPagenatedParamsToDataSet(reqDs);

            // 3. LISTDM호출
            cmpPolyListRs = dNRInsuTermPrstMgmt.dSInsuTermPrstLstPaging(reqDs, onlineCtx).getRecordSet("RS_INSU_INFO_LIST");
            PagenateUtils.setPagenatedParamToRecordSet(cmpPolyListRs, reqDs, intTotalCnt);
            responseData.putRecordSet( cmpPolyListRs );
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }
  
}
