package dms.pr.prspmbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.log.LogManager;
import nexcore.framework.core.util.StringUtils;

import org.apache.commons.logging.Log;

import fwk.common.CommonArea;
import fwk.utils.PagenateUtils;

/**
 * <ul>
 * <li>업무 그룹명 : dms/임대R</li>
 * <li>단위업무명: [FU]단말기입고관리</li>
 * <li>설 명 :
 * 
 * <pre>
 * 단말기입고관리FU
 * </pre>
 * 
 * </li>
 * <li>작성일 : 2015-07-10 13:08:13</li>
 * <li>작성자 : 김상오 (myneti99)</li>
 * </ul>
 * 
 * @author 김상오 (myneti99)
 */
public class FPREqpReqpInMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FPREqpReqpInMgmt() {
		super();
	}

	/**
	 * <pre>
	 * 단말기입고등록
	 * </pre>
	 * 
	 * @author 김상오 (myneti99)
	 * @since 2015-07-10 13:08:13
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegEqpReqpIn(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet rsCmpt = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);

		try {
			// 1. DU lookup
			DPREqpReqpInMgmt dPREqpReqpInMgmt = (DPREqpReqpInMgmt) lookupDataUnit(DPREqpReqpInMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dPREqpReqpInMgmt.dSInveEqpChk(requestData,
					onlineCtx);
			if (Integer.parseInt(valDS.getField("ROW_CNT")) > 0) {
				throw new BizRuntimeException("DMS00003"); // 중복입력 된 데이터가 존재합니다.
			}

			IDataSet valDS1 = dPREqpReqpInMgmt.dSEqpAssetImeiChk(requestData,
					onlineCtx);
			if (Integer.parseInt(valDS1.getField("ROW_CNT")) > 0) {
				throw new BizRuntimeException("DMS00003"); // 중복입력 된 데이터가 존재합니다.
			}

			// 단말기자산 일련번호조회
			valDS = dPREqpReqpInMgmt.dSInveNum(requestData, onlineCtx);
			requestData.putField("ASSET_NO", valDS.getField("ASSET_NO"));
			String strAssetNo = "";
			strAssetNo = valDS.getField("ASSET_NO").toString();
			// 단말기입고 일련번호조회
			valDS = dPREqpReqpInMgmt.dSEqpInNum(requestData, onlineCtx);
			requestData.putField("EQP_IN_NO", valDS.getField("EQP_IN_NO"));
			// 최종입출고번호
			requestData.putField("LAST_IN_OUT_NO", valDS.getField("EQP_IN_NO"));

			// 3. 업무 DM호출 단말기자산입력,단말기입고입력,구성품출고

			// 매입 테이블 인서트 2015.10.19 김기열

			// requestData.putField("SPLY_PRC",
			// requestData.getField("PRCH_AMT"));
			// requestData.putField("SURTAX_AMT",
			// requestData.getField("PRCH_AMT"));
			valDS = dPREqpReqpInMgmt.dIEqpInPrch(requestData, onlineCtx);
			requestData.putField("PRCH_NO", valDS.getField("PRCH_NO"));

			// String strPrchNo = "";
			// strPrchNo = val;
			responseData = dPREqpReqpInMgmt.dIEqpInve(requestData, onlineCtx);
			responseData = dPREqpReqpInMgmt.dIEqpIn(requestData, onlineCtx);
			IRecordSet rsCmptOut = requestData.getRecordSet("CMPT_OUT");
			for (int i = 0; i < rsCmptOut.getRecordCount(); i++) {
				rsCmpt = new DataSet();
				rsCmpt.putFieldMap(rsCmptOut.getRecordMap(i));
				if (!StringUtils.isEmpty(requestData.getField("PAR_NO"))
						&& !StringUtils.isEmpty(rsCmpt.getField("PAR_NO"))
						&& requestData.getField("PAR_NO").toString()
								.equals(rsCmpt.getField("PAR_NO").toString())
						&& !StringUtils.isEmpty(rsCmpt.getField("CHECKED"))
						&& "1".equals(rsCmpt.getField("CHECKED").toString())) {
					// 구성품출고일련번호채번
					valDS = dPREqpReqpInMgmt.dSCmptOutNo(rsCmpt, onlineCtx);
					rsCmpt.putField("CMPT_OUT_NO",
							valDS.getField("CMPT_OUT_NO"));
					// 구성품출고
					rsCmpt.putField("ASSET_NO", strAssetNo);
					rsCmpt.putField("FS_REG_USER_ID", ca.getUserNo());
					rsCmpt.putField("LS_CHG_USER_ID", ca.getUserNo());
					responseData = dPREqpReqpInMgmt
							.dICmptOut(rsCmpt, onlineCtx);
				}
			}
		} catch (BizRuntimeException e) {
			throw e;
		}

		return responseData;
	}

	/**
	 * <pre>
	 * 단말기입고상세조회
	 * </pre>
	 * 
	 * @author 김상오 (myneti99)
	 * @since 2015-07-10 13:10:55
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqEqpReqpInDtlLst(IDataSet requestData,
			IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DPREqpReqpInMgmt dPREqpReqpInMgmt = (DPREqpReqpInMgmt) lookupDataUnit(DPREqpReqpInMgmt.class);
			// 3. 업무 DM호출
			responseData = dPREqpReqpInMgmt.dSEqpReqpIn(requestData, onlineCtx);
		} catch (BizRuntimeException e) {
			throw e;
		}
		return responseData;
	}

	/**
	 * <pre>
	 * 단말기 입고 조회
	 * </pre>
	 * 
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-15 16:54:55
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqEqpReqpInLst(IDataSet requestData,
			IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IRecordSet rsPagingList = null;
		int intTotalCnt = 0;

		try {
			// 1. DU lookup
			DPREqpReqpInMgmt dPREqpReqpInMgmt = (DPREqpReqpInMgmt) lookupDataUnit(DPREqpReqpInMgmt.class);
			// 2. TOTAL COUNT DM호출
			dsCnt = dPREqpReqpInMgmt.dSEqpReqpInLstGrpTotCnt(requestData,
					onlineCtx);
			intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(requestData);

			// 3. LISTDM호출
			responseData = dPREqpReqpInMgmt.dSEqpReqpInLstGrpPaging(
					requestData, onlineCtx);

			rsPagingList = responseData.getRecordSet("RS_EQP_REQP_IN_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(rsPagingList,
					requestData, intTotalCnt);
		} catch (BizRuntimeException e) {
			throw e;
		}
		return responseData;
	}

	/**
	 * <pre>
	 * [FM]입고단말기삭제
	 * </pre>
	 * 
	 * @author 양재석 (jsyang)
	 * @since 2015-07-16 13:35:50
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * 
	 *            <pre>
	 * - record : RQ_EQP_LIST
	 * 	- field : FST_IN_DT [입고일자]
	 * 	- field : IN_DEALCO_CD [매입처코드]
	 * 	- field : ASSET_NO [자산번호]
	 * 	- field : EQP_MDL_CD [모델코드]
	 * 	- field : EQP_SER_NO [일련번호]
	 * 	- field : EQP_COLOR_CD [색상코드]
	 * 	- field : DEPT_CD [담당부서코드]
	 * 	- field : PRCH_AMT [매입단가]
	 * 	- field : EQP_IMEI_NO [IMEI]
	 * 	- field : DEL_YN [삭제여부]
	 * 	- field : FS_REG_USER_ID [최초등록사용자ID]
	 * 	- field : FS_REG_DTM [최초등록일시]
	 * 	- field : LS_CHG_USER_ID [최종변경사용자ID]
	 * 	- field : LS_CHG_DTM [최종변경일시]
	 * 	- field : EQP_IN_NO [단말기입고번호]
	 * 	- field : BOX_NO [BOX NO]
	 * 	- field : OP_CL_CD [업무구분코드]
	 * 	- field : INVE_ST_CD [재고상태코드]
	 * 	- field : CMPT_CD [구성품코드]
	 * 	- field : CMPT_OUT_NO [구성품출고번호]
	 * - record : CMPT_OUT
	 * 	- field : EQP_MDL_CD [모델코드]
	 * 	- field : EQP_SER_NO [일련번호]
	 * 	- field : CMPT_CD [구성품코드]
	 * 	- field : OUT_YN [구성품출고여부]
	 * 	- field : DEL_YN [삭제여부]
	 * 	- field : FS_REG_USER_ID [최초등록사용자ID]
	 * 	- field : FS_REG_DTM [최초등록일시]
	 * 	- field : LS_CHG_USER_ID [최종변경사용자ID]
	 * 	- field : LS_CHG_DTM [최종변경일시]
	 * </pre>
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fDelInEqpLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecordSet rsList = null;
		IDataSet rsDset = new DataSet();

		try {
			CommonArea ca = getCommonArea(onlineCtx);
			// 1. DU lookup
			DPREqpReqpInMgmt dPREqpReqpInMgmt = (DPREqpReqpInMgmt) lookupDataUnit(DPREqpReqpInMgmt.class);
			// 2. Validation DM호출
			requestData.putField("LS_CHG_USER_ID", ca.getUserNo());
			if (requestData.getField("ASSET_NO") == null
					|| "".equals(requestData.getField("ASSET_NO"))) {
				throw new BizRuntimeException("DMS00002"); // 필수항목누락
			}
			// 입고단말기정보삭제
			if (requestData.getField("EQP_IN_NO") != null
					&& !"".equals(requestData.getField("EQP_IN_NO"))) {
				dPREqpReqpInMgmt.dDEqpInInfoDel(requestData, onlineCtx);
			} else {
				responseData = dPREqpReqpInMgmt.dSAssetInEqpInLst(requestData,
						onlineCtx);
				rsList = responseData.getRecordSet("RS_EQP_IN_LIST");
				if (rsList != null) {
					for (int j = 0; j < rsList.getRecordCount(); j++) {
						rsDset.putFieldMap(rsList.getRecordMap(j));
						if (rsDset.getField("EQP_IN_NO") != null
								&& !"".equals(rsDset.getField("EQP_IN_NO"))) {
							dPREqpReqpInMgmt.dDEqpInInfoDel(rsDset, onlineCtx);
						}
					}
				}
			}
			// 구성품출고정보
			dPREqpReqpInMgmt.dDCmptOutInfoDel(requestData, onlineCtx);
			// 자산정보삭제
			dPREqpReqpInMgmt.dDEqpAssetInfoDel(requestData, onlineCtx);
		} catch (BizRuntimeException e) {
			throw e;
		}

		return responseData;
	}

	/**
	 * <pre>
	 * [FM]구성품출고목록조회
	 * </pre>
	 * 
	 * @author 양재석 (jsyang)
	 * @since 2015-07-16 13:35:50
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * 
	 *            <pre>
	 * - field : ASSET_NO [자산번호]
	 * </pre>
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 *         <pre>
	 * - record : CMPT_OUT_LIST
	 * 	- field : CMPT_OUT_NO [필드1]
	 * 	- field : OUT_DTL_CD [필드2]
	 * 	- field : CMPT_CD [필드3]
	 * 	- field : CMPT_USE_QTY [필드4]
	 * 	- field : CMPT_OUT_DT [필드5]
	 * 	- field : ASSET_NO [필드6]
	 * 	- field : FS_REG_USER_ID [필드7]
	 * 	- field : LS_CHG_USER_ID [필드8]
	 * 	- field : CHECKED [필드9]
	 * </pre>
	 */
	public IDataSet fInqCmptOutLst(IDataSet requestData,
			IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();

		try {
			// 1. DU lookup
			DPREqpReqpInMgmt dPREqpReqpInMgmt = (DPREqpReqpInMgmt) lookupDataUnit(DPREqpReqpInMgmt.class);
			// 2. LISTDM호출
			responseData = dPREqpReqpInMgmt.dSInqCmptOutLst(requestData,
					onlineCtx);
		} catch (BizRuntimeException e) {
			throw e;
		}

		return responseData;
	}

	/**
	 * <pre>
	 * [FM]자산단말기이동내역조회
	 * </pre>
	 * 
	 * @author 양재석 (jsyang)
	 * @since 2015-07-16 13:35:50
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * 
	 *            <pre>
	 * - field : ASSET_NO [자산번호]
	 * </pre>
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 *         <pre>
	 * - record : RS_EQP_MOV_DTL
	 * 	- field : ROWNO [ROWNO]
	 * 	- field : IN_OUT_GB [입출고구분]
	 * 	- field : IN_OUT_DTL_NM [입출고상세구분]
	 * 	- field : IN_OUT_DT [입출고일자]
	 * 	- field : IN_OUT_PLC_NM [입출고처]
	 * </pre>
	 */
	public IDataSet fInqAssetEqpMovDtl(IDataSet requestData,
			IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();

		try {
			// 1. DU lookup
			DPREqpReqpInMgmt dPREqpReqpInMgmt = (DPREqpReqpInMgmt) lookupDataUnit(DPREqpReqpInMgmt.class);
			// 2. LISTDM호출
			responseData = dPREqpReqpInMgmt.dSInqAssetEqpMovDtl(requestData,
					onlineCtx);
		} catch (BizRuntimeException e) {
			throw e;
		}

		return responseData;
	}

	/**
	 * <pre>
	 * [FM]자산단말기상세정보조회
	 * </pre>
	 * 
	 * @author 양재석 (jsyang)
	 * @since 2015-07-16 13:35:50
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * 
	 *            <pre>
	 * - field : ASSET_NO [자산번호]
	 * </pre>
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 *         <pre>
	 * - record : RS_ASSET_EQP_DTL_INFO
	 * 	- field : EQP_MDL_CD [단말기모델코드]
	 * 	- field : EQP_MDL_NM [단말기모델명]
	 * 	- field : EQP_SER_NO [단말기일련번호]
	 * 	- field : EQP_IMEI_NO [단말기IMEI번호]
	 * 	- field : EQP_COLOR_CD [단말기색상코드]
	 * 	- field : ASSET_NO [자산번호]
	 * 	- field : INVE_ST_CD [재고상태코드]
	 * 	- field : PRCH_AMT [매입금액]
	 * 	- field : IN_PLC_NM [매입처명]
	 * 	- field : CMPHS_CNTRT_NM [계약명]
	 * </pre>
	 */
	public IDataSet fInqAssetEqpDtlInfo(IDataSet requestData,
			IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();

		try {
			// 1. DU lookup
			DPREqpReqpInMgmt dPREqpReqpInMgmt = (DPREqpReqpInMgmt) lookupDataUnit(DPREqpReqpInMgmt.class);
			// 2. LISTDM호출
			responseData = dPREqpReqpInMgmt.dSInqAssetEqpDtlInfo(requestData,
					onlineCtx);
		} catch (BizRuntimeException e) {
			throw e;
		}

		return responseData;
	}

	/**
	 * <pre>
	 * 단말기자산 시리얼 및 IMEI 중복여부 체크
	 * </pre>
	 * 
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-16 13:35:50
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * 
	 *            <pre>
	 * - record : RS_EQP_ASSET_CHK
	 * 	- field : EQP_SER_NO [일련번호]
	 * 	- field : EQP_IMEI_NO [IMEI번호]
	 * 	- field : EQP_SER_CNT [일련번호중복건수]
	 * 	- field : EQP_IMEI_CNT [IMEI중복건수]
	 * </pre>
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 *         <pre>
	 * - record : RS_EQP_ASSET_CHK
	 * 	- field : EQP_SER_NO [일련번호]
	 * 	- field : EQP_IMEI_NO [IMEI번호]
	 * 	- field : EQP_SER_CNT [일련번호중복건수]
	 * 	- field : EQP_IMEI_CNT [IMEI중복건수]
	 * </pre>
	 */
	public IDataSet fChkEqpAssetSerImei(IDataSet requestData,
			IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet reqChk = new DataSet();

		try {
			// 1. DU lookup
			DPREqpReqpInMgmt dPREqpReqpInMgmt = (DPREqpReqpInMgmt) lookupDataUnit(DPREqpReqpInMgmt.class);
			// 2. Validation DM호출
			IRecordSet rs = requestData.getRecordSet("RS_EQP_ASSET_CHK");

			for (int i = 0; i < rs.getRecordCount(); i++) {
				reqChk.putFieldMap(rs.getRecordMap(i));

				IDataSet valDS = dPREqpReqpInMgmt.dSInveEqpChk(reqChk,
						onlineCtx);
				rs.getRecord(i).set("EQP_SER_CNT", valDS.getField("ROW_CNT"));

				IDataSet valDS1 = dPREqpReqpInMgmt.dSEqpAssetImeiChk(reqChk,
						onlineCtx);
				rs.getRecord(i).set("EQP_IMEI_CNT", valDS1.getField("ROW_CNT"));
			}

			responseData.putRecordSet(rs);

		} catch (BizRuntimeException e) {
			throw e;
		}
		return responseData;
	}

}