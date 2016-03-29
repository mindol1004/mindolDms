package dms.sc.scbbase.biz;

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
 * <li>업무 그룹명 : dms/시스템공통</li>
 * <li>단위업무명: [FU]Interface로그조회</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-11-24 09:16:59</li>
 * <li>작성자 : 진병수 (greatjin)</li>
 * </ul>
 *
 * @author 진병수 (greatjin)
 */
public class FSCIfLogMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCIfLogMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 진병수 (greatjin)
	 * @since 2015-11-24 09:16:59
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqIfErpLogLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();                                                         
		IDataSet dsCnt = new DataSet();                                                              
		IDataSet reqDs = (IDataSet) requestData.clone();  //원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet sysLogListRs = null;                                                              
		int sysLogTotalCnt = 0;                                                                      
		// 1. DU lookup                                                                              
		DSCIfLogMgmt dTR_IF_LOG01 = (DSCIfLogMgmt) lookupDataUnit(DSCIfLogMgmt.class);     
		try {                                                                                          
			// 2-1. 거래로그 TOTAL COUNT DM호출                                                          
			dsCnt = dTR_IF_LOG01.dSIfErpLogLstTotCnt(requestData, onlineCtx);                    
			sysLogTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));                              
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);                                            
			// 2-2. 거래로그 LISTDM호출                                                                  
			responseData = dTR_IF_LOG01.dSIfErpLogLstPaging(reqDs, onlineCtx);               
			sysLogListRs = responseData.getRecordSet("RS_IF_LOG_LIST");                                  
			PagenateUtils.setPagenatedParamToRecordSet(sysLogListRs, reqDs, sysLogTotalCnt);             
		} catch ( BizRuntimeException e ) {                                                            
			throw e;                                                                                     
		}                                                                                              
		return responseData;               
	}

	/**
	 *
	 *
	 * @author 진병수 (greatjin)
	 * @since 2015-11-24 11:10:38
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqSlipTyp4Combo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();                                                         
		IDataSet dsCnt = new DataSet();                                                              
		IDataSet reqDs = (IDataSet) requestData.clone();  //원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet sysLogListRs = null;                                                              
		int sysLogTotalCnt = 0;                                                                      
		// 1. DU lookup                                                                              
		DSCIfLogMgmt dTR_IF_LOG01 = (DSCIfLogMgmt) lookupDataUnit(DSCIfLogMgmt.class);     
		try {                                                                                          
			// 2-1. 거래로그 LISTDM호출                                                                  
			responseData = dTR_IF_LOG01.dSSlipTyp4Combo(reqDs, onlineCtx);               
		} catch ( BizRuntimeException e ) {                                                            
			throw e;                                                                                     
		}                                                                                              
		return responseData;  
	}
  
}
