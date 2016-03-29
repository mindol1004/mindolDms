package dms.nr.nrsisbase.biz;

import java.util.Map;

import fwk.common.CommonArea;
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
 * <li>단위업무명: [FU]보증보험청구취소정보관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-10-19 16:51:14</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class FNRInsuInvCnclMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNRInsuInvCnclMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-10-19 16:52:25
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqInsuInvCnclInfoLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IDataSet dsCnt = new DataSet();
		IRecordSet usrListRs = null;
		int intTotalCnt = 0;

		try {
			// 1. DU lookup
			DNRInsuInvCnclMgmt dNRInsuInvCnclMgmt = (DNRInsuInvCnclMgmt) lookupDataUnit(DNRInsuInvCnclMgmt.class);

			// 2. TOTAL COUNT DM호출
			dsCnt = dNRInsuInvCnclMgmt.dSInsuInvCnclTotCnt(requestData, onlineCtx);
			IRecordSet sumRs = dsCnt.getRecordSet("RS_SUM_LIST");
			
			intTotalCnt = Integer.parseInt(sumRs.get(0,"TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(requestData);
			
			// 3. LISTDM호출
			responseData = dNRInsuInvCnclMgmt.dSInsuInvCnclLstPaging(requestData, onlineCtx);
			usrListRs = responseData.getRecordSet("RS_INSU_INV_CNCL_LIST");
			
			responseData.putRecordSet("RS_SUM_LIST", sumRs);
			PagenateUtils.setPagenatedParamToRecordSet(sumRs, requestData, intTotalCnt);
			
			responseData.putRecordSet("RS_INSU_INV_CNCL_LIST",usrListRs);
			PagenateUtils.setPagenatedParamToRecordSet(usrListRs, requestData, intTotalCnt);
			
	    } catch ( BizRuntimeException e ) {
	        throw e; //시스템 오류가 발생하였습니다.
	    }
	    
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-10-19 16:51:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdateIncuInvCnclFix(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	    
	    try {
	    	// 1. DU lookup
	    	DNRInsuInvCnclMgmt dNRInsuInvCnclMgmt = (DNRInsuInvCnclMgmt) lookupDataUnit(DNRInsuInvCnclMgmt.class);
	    	requestData.putField("USER_NO", ca.getUserNo());
	    	// 2. LIST DM호출
	    	dNRInsuInvCnclMgmt.dUpdateIncuInvCnclFix(requestData, onlineCtx);
	    	
	    } catch ( BizRuntimeException e ) {
	    	throw e; //시스템 오류가 발생하였습니다.
	    }
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-10-26 11:18:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdateIncuInvCnclNotYet(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	    
	    try {
	    	// 1. DU lookup
	    	DNRInsuInvCnclMgmt dNRInsuInvCnclMgmt = (DNRInsuInvCnclMgmt) lookupDataUnit(DNRInsuInvCnclMgmt.class);
	    	requestData.putField("USER_NO", ca.getUserNo());
	    	// 2. LIST DM호출
	    	dNRInsuInvCnclMgmt.dUpdateIncuInvCnclNotYet(requestData, onlineCtx);
	    	
	    } catch ( BizRuntimeException e ) {
	    	throw e; //시스템 오류가 발생하였습니다.
	    }
	
	    return responseData;
	}
  
}
