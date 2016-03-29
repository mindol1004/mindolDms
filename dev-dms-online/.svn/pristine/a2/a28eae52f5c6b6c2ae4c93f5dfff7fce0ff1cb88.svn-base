package dms.sc.scbbase.biz;

import fwk.utils.HpcUtils;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.utils.PagenateUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: [FU]시스템정책상세관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-07-31 15:49:04</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class FSCSysPolyDtlMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCSysPolyDtlMgmt() {
		super();
	}

	/**
	 * [FU 시스템정책상세관리] 시스템정책상세 목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqSysPolyDtlLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet sysPolyDtlLstRs = null;
		int sysPolyDtlTtCnt = 0;
		try {
			// 1. DU lookup
			DSCSysPolyDtlMgmt dTB_SYS_POLY_DTL01 = (DSCSysPolyDtlMgmt) lookupDataUnit(DSCSysPolyDtlMgmt.class);
			// 2. TOTAL COUNT DM호출
			dsCnt = dTB_SYS_POLY_DTL01.dSSysPolyDtlLstTotCnt(requestData, onlineCtx);
			sysPolyDtlTtCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);
			// 3. LIST DM호출
			responseData = dTB_SYS_POLY_DTL01.dSSysPolyDtlLstPaging(reqDs, onlineCtx);
			sysPolyDtlLstRs = responseData.getRecordSet("RS_SYS_POLY_DTL_MGMT_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(sysPolyDtlLstRs, reqDs, sysPolyDtlTtCnt);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/** 
	 * [FU 시스템정책상세관리] 시스템정책상세 등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegSysPolyDtl(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		try {
			// 1. DU lookup
			DSCSysPolyDtlMgmt dTB_SYS_POLY_DTL01 = (DSCSysPolyDtlMgmt) lookupDataUnit(DSCSysPolyDtlMgmt.class);
			/*
			 * // 2. Validation DM호출 IDataSet valDS = dTB_SYS_POLY_DTL01.dTB_SYS_POLY_DTL_S003(requestData, onlineCtx); if
			 * (Integer.parseInt(valDS.getField("ROW_CNT")) > 0){ throw new BizRuntimeException("DMS00003"); //중복 된 데이터가 존재합니다. }
			 */// 시스템정책 일련번호 자동 채번되므로 필요없음.
				// 2. Validation DM2호출
			/*
			 * IDataSet valDS = dTB_SYS_POLY_DTL01.dSSyPolyDtlDtmValidate(requestData, onlineCtx); if (Integer.parseInt(valDS.getField("DTM_CNT")) > 0){ throw
			 * new BizRuntimeException("HPC00120",new String[]{HpcUtils.getLangMsg("[review]일자")}); //중복 된 {0}이/가 존재합니다. }
			 */// 요건변경으로 인해 사용하지 않음.
			// 3. 시스템정책 일련번호 채번 DM
			IDataSet GtNoDS = dTB_SYS_POLY_DTL01.dSSysPolyDtlSnoGtno(requestData, onlineCtx);
			reqDs.putField("SYS_POLY_SNO", GtNoDS.getRecordSet("RS_SYS_POLY_SNO").get(0, "SYS_POLY_SNO"));
			// 4. 업무 DM호출
			responseData = dTB_SYS_POLY_DTL01.dISysPolyDtl(reqDs, onlineCtx);
			responseData.putRecordSet("RS_SYS_POLY_SNO", GtNoDS.getRecordSet("RS_SYS_POLY_SNO"));

		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * [FU 시스템정책상세관리] 시스템정책상세 수정
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdSysPolyDtl(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone(); //원거래의 DataSet의 훼손을 막기 위한 Clone
		try {
			// 1. DU lookup
			DSCSysPolyDtlMgmt dTB_SYS_POLY_DTL01 = (DSCSysPolyDtlMgmt) lookupDataUnit(DSCSysPolyDtlMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dTB_SYS_POLY_DTL01.dSSysPolyDtlLstValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004"); //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			/*
			 * // 2. Validation DM2호출 IDataSet valDS2 = dTB_SYS_POLY_DTL01.dSSyPolyDtlDtmValidate(requestData, onlineCtx); if
			 * (Integer.parseInt(valDS2.getField("DTM_CNT")) > 0){ throw new BizRuntimeException("HPC00120",new String[]{HpcUtils.getLangMsg("[review]일자")});
			 * //중복 된 {0}이/가 존재합니다. }
			 *///요건변경으로 인해 사용하지 않음.
			// 3. 업무 DM호출
			reqDs.removeRecordSet("RS_SYS_POLY_DTL_MGMT_LIST");
			responseData = dTB_SYS_POLY_DTL01.dUSysPolyDtl(reqDs, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}
}
