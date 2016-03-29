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
 * <li>단위업무명: [FU]공통그룹코드관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-07-24 09:55:58</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class FSCCmGrpCdMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCCmGrpCdMgmt() {
		super();
	}

	/**
	 * [FU 공통그룹코드관리] 공통그룹코드 목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqCmGrpCdLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet cmGrpListRs = null;
		int cmGrpTotalCnt = 0;

		try {
			// 1. DU lookup
			DSCCmGrpCdMgmt dTB_CM_GRP_CD01 = (DSCCmGrpCdMgmt) lookupDataUnit(DSCCmGrpCdMgmt.class);

			// 2. TOTAL COUNT DM호출
			dsCnt = dTB_CM_GRP_CD01.dSCmGrpCdLstTotCnt(requestData, onlineCtx);
			cmGrpTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);

			// 3. LISTDM호출
			responseData = dTB_CM_GRP_CD01.dSCmGrpCdLstPaging(reqDs, onlineCtx);
			cmGrpListRs = responseData.getRecordSet("RS_CM_GRP_CD_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(cmGrpListRs, reqDs, cmGrpTotalCnt);
		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;
	}

	/**
	 * [FU 공통그룹코드관리] 공통그룹코드 등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegCmGrpCd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCCmGrpCdMgmt dTB_CM_GRP_CD01 = (DSCCmGrpCdMgmt) lookupDataUnit(DSCCmGrpCdMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dTB_CM_GRP_CD01.dSCmGrpCdLstValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) > 0 ) {
				throw new BizRuntimeException("DMS00003"); //중복 된 데이터가 존재합니다.
			}
			// 3. 업무 DM호출
			responseData = dTB_CM_GRP_CD01.dICmGrpCd(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * [FU 공통그룹코드관리] 공통그룹코드 수정
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdCmGrpCd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet responseData1 = new DataSet(); //History responseData

		try {
			// 1. DU lookup
			DSCCmGrpCdMgmt dTB_CM_GRP_CD01 = (DSCCmGrpCdMgmt) lookupDataUnit(DSCCmGrpCdMgmt.class);
			DSCCmGrpCdHst dTH_CM_GRP_CD_HST01 = (DSCCmGrpCdHst) lookupDataUnit(DSCCmGrpCdHst.class);
			// 2. Validation DM호출
			IDataSet valDS = dTB_CM_GRP_CD01.dSCmGrpCdLstValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004"); //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 3. 업무 DM호출
			responseData1 = dTH_CM_GRP_CD_HST01.dICmGrpCdHst(requestData, onlineCtx);
			responseData = dTB_CM_GRP_CD01.dUCmGrpCd(requestData, onlineCtx);

		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}
}
