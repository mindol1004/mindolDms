package dms.sc.scbbase.biz;

import org.apache.commons.logging.Log;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.utils.PagenateUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: [FU]공통코드관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-07-25 16:01:53</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class FSCCmCdMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCCmCdMgmt() {
		super();
	}

	/**
	 * [FU 공통코드관리] 공통코드 목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqCmCdLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet cmCdListRs = null;
		int cmCdTotalCnt = 0;

		try {
			// 1. DU lookup
			DSCCmCdMgmt dTB_CM_CD01 = (DSCCmCdMgmt) lookupDataUnit(DSCCmCdMgmt.class);

			// 2. TOTAL COUNT DM호출
			dsCnt = dTB_CM_CD01.dSCmCdLstTotCnt(requestData, onlineCtx);
			cmCdTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);

			// 3. LISTDM호출
			responseData = dTB_CM_CD01.dSCmCdLstPaging(reqDs, onlineCtx);
			cmCdListRs = responseData.getRecordSet("RS_CM_CD_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(cmCdListRs, reqDs, cmCdTotalCnt);
		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;
	}

	/**
	 * [FU 공통코드관리] 공통코드 등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegCmCd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCCmCdMgmt dTB_CM_CD01 = (DSCCmCdMgmt) lookupDataUnit(DSCCmCdMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dTB_CM_CD01.dSCmCdLstValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) > 0 ) {
				throw new BizRuntimeException("DMS00003"); //중복 된 데이터가 존재합니다
			}
			// 3. 업무 DM호출
			responseData = dTB_CM_CD01.dICmCd(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * [FU 공통코드관리] 공통코드 수정
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdCmCd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();

		try {
			// 1. DU lookup
			DSCCmCdMgmt dTB_CM_CD01 = (DSCCmCdMgmt) lookupDataUnit(DSCCmCdMgmt.class);
			DSCCmCdHst dTH_CM_CD_HST01 = (DSCCmCdHst) lookupDataUnit(DSCCmCdHst.class);
			// 2. Validation DM호출
			IDataSet valDS = dTB_CM_CD01.dSCmCdLstValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004"); //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			/*
			 * if (Integer.parseInt(valDS.getField("ROW_CNT")) > 0){ throw new BizRuntimeException("DMS00003"); //중복 된 데이터가 존재합니다 }
			 */
			// 3. 업무 DM호출
			dTH_CM_CD_HST01.dICmCdHst(requestData, onlineCtx);
			responseData = dTB_CM_CD01.dUCmCd(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * [FU 공통코드관리] 공통코드 검색
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSrchCmCd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone(); // 원거래의 DataSet의 훼손을 막기 위한 Clone 
		IRecordSet supCmCdLstRs = null;
		int supCmCdTtCnt = 0;
		try {
			// 1. DU lookup
			DSCCmCdMgmt dTB_CM_CD01 = (DSCCmCdMgmt) lookupDataUnit(DSCCmCdMgmt.class);

			// 2. TOTAL COUNT DM호출
			dsCnt = dTB_CM_CD01.dSSupCmCdLstTotCnt(requestData, onlineCtx);
			supCmCdTtCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);

			// 3. LISTDM호출
			responseData = dTB_CM_CD01.dSSupCmCdLstPaging(reqDs, onlineCtx);
			supCmCdLstRs = responseData.getRecordSet("RS_SUP_CM_CD_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(supCmCdLstRs, reqDs, supCmCdTtCnt);
		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;
	}
}
