package dms.sc.scbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.utils.PagenateUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: FSCAlrtSvcMgmt</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-09-22 16:37:16</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class FSCAlrtSvcMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCAlrtSvcMgmt() {
		super();
	}

	/**
	 * 알림서비스목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqAlrtSvcLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet alrtSvcListRs = null;
		int alrtSvcTotCnt = 0;
		try {
			// 1. DU lookup
			DSCAlrtSvcMgmt dSCAlrtSvcMgmt = (DSCAlrtSvcMgmt) lookupDataUnit(DSCAlrtSvcMgmt.class);
			// 2. TOTAL COUNT DM호출
			dsCnt = dSCAlrtSvcMgmt.dSAlrtSvcLstTotCnt(requestData, onlineCtx);
			alrtSvcTotCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);
			// 3. LISTDM호출
			responseData = dSCAlrtSvcMgmt.dSAlrtSvcLstPaging(reqDs, onlineCtx);
			alrtSvcListRs = responseData.getRecordSet("RS_ALRT_SVC_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(alrtSvcListRs, reqDs, alrtSvcTotCnt);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 알림서비스등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegAlrtSvc(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCAlrtSvcMgmt dSCAlrtSvcMgmt = (DSCAlrtSvcMgmt) lookupDataUnit(DSCAlrtSvcMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dSCAlrtSvcMgmt.dSAlrtSvcLstValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) > 0 ) {
				throw new BizRuntimeException("DMS00003"); //중복 된 데이터가 존재합니다.
			}
			// 3. 업무 DM호출
			responseData = dSCAlrtSvcMgmt.dIAlrtSvc(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 알림서비스수정
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdAlrtSvc(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCAlrtSvcMgmt dSCAlrtSvcMgmt = (DSCAlrtSvcMgmt) lookupDataUnit(DSCAlrtSvcMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dSCAlrtSvcMgmt.dSAlrtSvcLstValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004"); // 데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 3. 업무 DM호출
			responseData = dSCAlrtSvcMgmt.dUAlrtSvc(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 알림요청브랜드목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqAlrtReqBrndLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet ReqBrnListRs = null;
		int ReqBrndTotCnt = 0;
		try {
			// 1. DU lookup
			DSCAlrtSvcReqBrnMgmt dSCAlrtSvcReqBrnMgmt = (DSCAlrtSvcReqBrnMgmt) lookupDataUnit(DSCAlrtSvcReqBrnMgmt.class);
			// 2. TOTAL COUNT DM호출
			dsCnt = dSCAlrtSvcReqBrnMgmt.dSAlrtReqBrndLstTotCnt(requestData, onlineCtx);
			ReqBrndTotCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);
			// 3. LISTDM호출
			responseData = dSCAlrtSvcReqBrnMgmt.dSAlrtReqBrndLstPaging(reqDs, onlineCtx);
			ReqBrnListRs = responseData.getRecordSet("RS_REQ_BRN_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(ReqBrnListRs, reqDs, ReqBrndTotCnt);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 알림요청브랜드등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegAlrtReqBrnd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCAlrtSvcReqBrnMgmt dSCAlrtSvcReqBrnMgmt = (DSCAlrtSvcReqBrnMgmt) lookupDataUnit(DSCAlrtSvcReqBrnMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dSCAlrtSvcReqBrnMgmt.dSAlrtReqBrndLstValidate2(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) > 0 ) {
				throw new BizRuntimeException("DMS00003"); //중복입력 된 데이터가 존재합니다.
			}
			// 3. 업무 DM호출
			responseData = dSCAlrtSvcReqBrnMgmt.dIAlrtReqBrnd(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 알림요청브랜드수정
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdAlrtReqBrnd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCAlrtSvcReqBrnMgmt dSCAlrtSvcReqBrnMgmt = (DSCAlrtSvcReqBrnMgmt) lookupDataUnit(DSCAlrtSvcReqBrnMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dSCAlrtSvcReqBrnMgmt.dSAlrtReqBrndLstValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004"); // 데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			IDataSet valDS2 = dSCAlrtSvcReqBrnMgmt.dSAlrtReqBrndLstValidate2(requestData, onlineCtx);
			if ( Integer.parseInt(valDS2.getField("ROW_CNT")) > 0 ) {
				throw new BizRuntimeException("DMS00003"); //중복입력 된 데이터가 존재합니다.
			}
			// 3. 업무 DM호출
			responseData = dSCAlrtSvcReqBrnMgmt.dUAlrtReqBrnd(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 알림요청브랜드삭제
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fDelAlrtReqBrnd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCAlrtSvcReqBrnMgmt dSCAlrtSvcReqBrnMgmt = (DSCAlrtSvcReqBrnMgmt) lookupDataUnit(DSCAlrtSvcReqBrnMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dSCAlrtSvcReqBrnMgmt.dSAlrtReqBrndLstValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004");  //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 3. 업무 DM호출
			responseData = dSCAlrtSvcReqBrnMgmt.dDAlrtReqBrnd(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

}
