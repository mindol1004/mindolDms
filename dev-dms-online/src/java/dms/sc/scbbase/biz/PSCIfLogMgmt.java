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
 * <li>단위업무명: [PU]Interface로그조회</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-11-24 09:15:23</li>
 * <li>작성자 : 진병수 (greatjin)</li>
 * </ul>
 *
 * @author 진병수 (greatjin)
 */
public class PSCIfLogMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PSCIfLogMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 진병수 (greatjin)
	 * @since 2015-11-24 09:15:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : OP_CL_CD [업무구분코드]
	 *	- field : SLIP_TYP_CD [전표유형]
	 *	- field : DMS_NO [DMS키]
	 *	- field : SAP_NO [SAP전표번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_IF_LOG_LIST
	 *		- field : DMS_NO [DMS_NO]
	 *		- field : SAP_NO [SAP_NO]
	 *		- field : SAP_TYP_CD [SAP_TYP_CD]
	 *		- field : SAP_TYP_NM [SAP_TYP_NM]
	 *		- field : COL_KEY [COL_KEY]
	 *		- field : COL_VALUE [COL_VALUE]
	 *		- field : ITEM_KEY [ITEM_KEY]
	 *		- field : ITEM_VALUE [ITEM_VALUE]
	 *		- field : FS_REG_USER_ID [FS_REG_USER_ID]
	 *		- field : FS_REG_USER_NM [FS_REG_USER_NM]
	 *		- field : FS_REG_DTM [FS_REG_DTM]
	 * </pre>
	 */
	public IDataSet pInqIfErpLogLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCIfLogMgmt fSCIfLogMgmt = (FSCIfLogMgmt) lookupFunctionUnit(FSCIfLogMgmt.class);
		    responseData = fSCIfLogMgmt.fInqIfErpLogLst(requestData, onlineCtx);
		    		
			// 3. 결과값 리턴
			responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

	/**
	 *
	 *
	 * @author 진병수 (greatjin)
	 * @since 2015-11-24 09:15:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : OP_CL_CD [업무구분코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_COMBO_LIST
	 *		- field : CM_CD_ID [코드ID]
	 *		- field : CM_CD_NM [코드명]
	 * </pre>
	 */
	public IDataSet pInqSlipTyp4Combo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCIfLogMgmt fSCIfLogMgmt = (FSCIfLogMgmt) lookupFunctionUnit(FSCIfLogMgmt.class);
		    responseData = fSCIfLogMgmt.fInqSlipTyp4Combo(requestData, onlineCtx);
		    		
			// 3. 결과값 리턴
			responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}
  
}
