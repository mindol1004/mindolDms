package dms.sc.scbbase.biz;

import fwk.utils.HpcUtils;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

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
import nexcore.framework.core.util.BaseUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: FSCFrmMgmt</li>
 * <li>설 명 : 화면관리</li>
 * <li>작성일 : 2014-07-21 11:25:13</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 * 
 * @author 심상준 (simair)
 */
public class FSCFrmMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCFrmMgmt() {
		super();
	}

	/**
	 * 화면목록조회
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqFrmLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet frmListRs = null;
		int frmTotalCnt = 0;

		try {
			// 1. DU lookup
			DSCFrmMgmt dTB_FRM01 = (DSCFrmMgmt) lookupDataUnit(DSCFrmMgmt.class);

			// 2. TOTAL COUNT DM호출
			dsCnt = dTB_FRM01.dSFrmListCnt(requestData, onlineCtx);
			frmTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);

			// 3. LISTDM호출
			responseData = dTB_FRM01.dSFrmListInq(reqDs, onlineCtx);
			frmListRs = responseData.getRecordSet("RS_FRM_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(frmListRs, reqDs, frmTotalCnt);
		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;

	}

	/**
	 * 화면등록
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegFrm(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCFrmMgmt dTB_FRM01 = (DSCFrmMgmt) lookupDataUnit(DSCFrmMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dTB_FRM01.dSFrmListChk(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) > 0 ) {
				throw new BizRuntimeException("DMS00003"); //중복입력 된 데이터가 존재합니다.
			}
			// 3. 업무 DM호출
			responseData = dTB_FRM01.dIFrmListReg(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;

	}

	/**
	 * 화면수정
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdFrm(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCFrmMgmt dTB_FRM01 = (DSCFrmMgmt) lookupDataUnit(DSCFrmMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dTB_FRM01.dSFrmListChk(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004"); //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 3. 업무 DM호출
			responseData = dTB_FRM01.dUFrmListUpd(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;

	}

	/**
	 * 화면삭제
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fDelFrm(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCFrmMgmt dTB_FRM01 = (DSCFrmMgmt) lookupDataUnit(DSCFrmMgmt.class);
			// 2-1. Validation DM호출
			IDataSet valDS = dTB_FRM01.dSFrmListChk(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004"); //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 2-2. Validation DM호출(메뉴)
			IDataSet valDS2 = dTB_FRM01.dSFrmValidateMenu(requestData, onlineCtx);
			if ( Integer.parseInt(valDS2.getField("ROW_CNT")) != 0 ) {
				throw new BizRuntimeException("DMS00005", new String[] { HpcUtils.getLangMsg("MENU_MGMT") }); //{0}에 데이터가 존재합니다.\n\n해당 데이터 삭제 후 재작업 하십시오.
			}
			// 2-3. Validation DM호출(화면권한역할)
			IDataSet valDS3 = dTB_FRM01.dSFrmValidateFrmAutr(requestData, onlineCtx);
			if ( Integer.parseInt(valDS3.getField("ROW_CNT")) != 0 ) {
				throw new BizRuntimeException("DMS00005", new String[] { HpcUtils.getLangMsg("FRM_AUTR_ROL_MGMT") }); //{0}에 데이터가 존재합니다.\n\n해당 데이터 삭제 후 재작업 하십시오.
			}
			// 3. 업무 DM호출
			responseData = dTB_FRM01.dDFrmListDel(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;

	}

	/**
	 * 화면권한목록전체조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqFrmlAll(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		try {
			// 1. DU lookup
			DSCFrmMgmt dTB_FRM01 = (DSCFrmMgmt) lookupDataUnit(DSCFrmMgmt.class);
			// 2. LISTDM호출
			responseData = dTB_FRM01.dSFrmListInqAll(reqDs, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;
	}

}
