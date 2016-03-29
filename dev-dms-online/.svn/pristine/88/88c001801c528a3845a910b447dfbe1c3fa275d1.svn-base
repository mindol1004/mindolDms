package dms.sc.scbbase.biz;

import fwk.utils.PagenateUtils;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: [FU]화면항목관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-07-31 11:06:47</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class FSCFrmItemMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCFrmItemMgmt() {
		super();
	}

	/**
	 * [FU 화면항목관리] 화면항목 목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqFrmItemLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone(); //원거래의 DataSet 훼손을 막기 위한 clone
		IRecordSet frmItemLstRs = null;
		int frmItemTotalCnt = 0;

		try {
			// 1. DU lookup
			DSCFrmItemMgmt dTB_FRM_ITEM01 = (DSCFrmItemMgmt) lookupDataUnit(DSCFrmItemMgmt.class);
			// 2. TOTAL COUNT DM호출
			dsCnt = dTB_FRM_ITEM01.dSFrmItemLstTotCnt(requestData, onlineCtx);
			frmItemTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);
			// 3. LIST DM 호출
			responseData = dTB_FRM_ITEM01.dSFrmItemLstPaging(reqDs, onlineCtx);
			frmItemLstRs = responseData.getRecordSet("RS_FRM_ITEM_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(frmItemLstRs, reqDs, frmItemTotalCnt);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * [FU 화면항목관리] 화면항목 등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegFrmItem(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCFrmItemMgmt dTB_FRM_ITEM01 = (DSCFrmItemMgmt) lookupDataUnit(DSCFrmItemMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dTB_FRM_ITEM01.dSFrmItemLstValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) > 0 ) {
				throw new BizRuntimeException("DMS00003"); //중복 된 데이터가 존재합니다. 
			}
			// 3. 업무 DM호출
			responseData = dTB_FRM_ITEM01.dIFrmItem(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * [FU 화면항목관리] 화면항목 수정
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdFrmItem(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCFrmItemMgmt dTB_FRM_ITEM01 = (DSCFrmItemMgmt) lookupDataUnit(DSCFrmItemMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dTB_FRM_ITEM01.dSFrmItemLstValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004"); // 데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 3. 업무 DM호출
			responseData = dTB_FRM_ITEM01.dUFrmItem(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * [FU 화면항목관리] 화면항목 삭제
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fDelFrmItem(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCFrmItemMgmt dTB_FRM_ITEM01 = (DSCFrmItemMgmt) lookupDataUnit(DSCFrmItemMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dTB_FRM_ITEM01.dSFrmItemLstValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004");  //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 3. 업무 DM호출
			responseData = dTB_FRM_ITEM01.dDFrmItem(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

}
