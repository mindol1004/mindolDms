package dms.sc.scbbase.biz;

import fwk.utils.PagenateUtils;

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
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: FSCZipMgmt</li>
 * <li>설  명 : 우편번호관리</li>
 * <li>작성일 : 2014-08-21 15:09:31</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class FSCZipMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCZipMgmt() {
		super();
	}

	/**
	 * 우편번호조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqZip(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet frmListRs = null;
		int frmTotalCnt = 0;

		try {
			// 1. DU lookup
			DSCZipMgmt dTB_ADDR01 = (DSCZipMgmt) lookupDataUnit(DSCZipMgmt.class);
			// 2. TOTAL COUNT DM호출
			dsCnt = dTB_ADDR01.dSCntAddr(requestData, onlineCtx);
			frmTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);

			// 3. LISTDM호출
			responseData = dTB_ADDR01.dSInqZip(reqDs, onlineCtx);
			// responseData = dTB_ADDR01.dSInqDiv(reqDs,onlineCtx);

			frmListRs = responseData.getRecordSet("RS_ADDR_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(frmListRs, reqDs, frmTotalCnt);
		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;
	}

	/**
	 * 우편번호조회(시/도)
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqZipDiv(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		try {
			// 1. DU lookup
			DSCZipMgmt dTB_ADDR01 = (DSCZipMgmt) lookupDataUnit(DSCZipMgmt.class);

			// 2. LISTDM호출
			responseData = dTB_ADDR01.dSInqDiv(reqDs, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;
	}

	/**
	 * 우편번호(시/군/구)조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqZipDivDtl(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();

		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone

		try {
			// 1. DU lookup
			DSCZipMgmt dTB_ADDR01 = (DSCZipMgmt) lookupDataUnit(DSCZipMgmt.class);

			// 2. LISTDM호출
			responseData = dTB_ADDR01.dSInqDivDtl(reqDs, onlineCtx);

		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;
	}

}
