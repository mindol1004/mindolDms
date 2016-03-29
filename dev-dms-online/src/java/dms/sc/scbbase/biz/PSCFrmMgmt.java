package dms.sc.scbbase.biz;

import java.util.Map;

import org.apache.commons.logging.Log;

import fwk.common.CommonArea;
import fwk.utils.HpcUtils;
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
 * <li>단위업무명: PSCFrmMgmt</li>
 * <li>설 명 : 화면관리</li>
 * <li>작성일 : 2014-07-21 11:23:34</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 * 
 * @author 심상준 (simair)
 */
public class PSCFrmMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PSCFrmMgmt() {
		super();
	}

	/**
	 * <pre>화면목록조회</pre>
	 *
	 * @author admin (admin)
	 * @since 2015-06-22 16:59:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : FRM_ID [화면ID]
	 *	- field : FRM_NM [화면명]
	 *	- field : FRM_URL [화면URL]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_FRM_LIST
	 *		- field : FRM_ID [화면ID]
	 *		- field : FRM_NM [화면명]
	 *		- field : FRM_DESC [화면설명]
	 *		- field : FRM_CL_CD [화면구분코드]
	 *		- field : FRM_URL [화면URL]
	 * </pre>
	 */
	public IDataSet pInqFrmLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCFrmMgmt fSCFrmMgmt = (FSCFrmMgmt) lookupFunctionUnit(FSCFrmMgmt.class);
			// 2. FM 호출
			responseData = fSCFrmMgmt.fInqFrmLst(requestData, onlineCtx);
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
	 * 화면저장
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_FRM_LIST
	 *		- field : FRM_ID [화면ID]
	 *		- field : FRM_NM [화면명]
	 *		- field : FRM_DESC [화면설명]
	 *		- field : FRM_CL_CD [화면구분코드]
	 *		- field : FRM_URL [화면URL]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveFrm(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);

		try {
			// 1. FU lookup
			FSCFrmMgmt fSCFrmMgmt = (FSCFrmMgmt) lookupFunctionUnit(FSCFrmMgmt.class);
			// 2. 입력 RS설정
			requestData.putFieldMap(requestData.getRecordSet("RS_FRM_LIST").getRecordMap(0));
			requestData.putField("USERNO", ca.getUserNo());
			// 3. 레코드셋 상태에 따른 분기
			if ( "I".equals(ca.getTrnPtrnDvcd()) ) { // INSERT
				fSCFrmMgmt.fRegFrm(requestData, onlineCtx);
			} else if ( "U".equals(ca.getTrnPtrnDvcd()) ) { // UPDATE
				fSCFrmMgmt.fUpdFrm(requestData, onlineCtx);
			} else if ( "D".equals(ca.getTrnPtrnDvcd()) ) { // DELETE
				fSCFrmMgmt.fDelFrm(requestData, onlineCtx);
			}
			// 4. 결과값 리턴
			responseData.setOkResultMessage("DMS00000", null); //정상 처리되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;

	}

}
