package dms.sc.scbbase.biz;

import java.util.Map;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.common.CommonArea;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: [PU]화면항목관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-07-31 11:03:04</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class PSCFrmItemMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PSCFrmItemMgmt() {
		super();
	}

	/**
	 *
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : FRM_ID [화면ID]
	 *	- field : FRM_NM [화면명]
	 *	- field : FRM_ITEM_NM [화면항목명]
	 *	- field : USE_YN [사용유무]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_FRM_ITEM_LIST
	 *		- field : FRM_ID [화면ID]
	 *		- field : FRM_ITEM_NM [화면항목명]
	 *		- field : FRM_ITEM_CTT [화면항목내용]
	 *		- field : USE_YN [사용유무]
	 * </pre>
	 */
	public IDataSet pInqFrmItemLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCFrmItemMgmt fSC013 = (FSCFrmItemMgmt) lookupFunctionUnit(FSCFrmItemMgmt.class);
			// 2. FM 호출
			responseData = fSC013.fInqFrmItemLst(requestData, onlineCtx);
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
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_FRM_ITEM_LIST
	 *		- field : FRM_ID [화면ID]
	 *		- field : FRM_ITEM_NM [화면항목명]
	 *		- field : FRM_ITEM_CTT [화면항목내용]
	 *		- field : USE_YN [사용유무]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveFrmItem(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);

		try {
			// 1. FU lookup
			FSCFrmItemMgmt fSC013 = (FSCFrmItemMgmt) lookupFunctionUnit(FSCFrmItemMgmt.class);
			// 2. 입력 RS설정
			requestData.putFieldMap(requestData.getRecordSet("RS_FRM_ITEM_LIST").getRecordMap(0));
			requestData.putField("USERNO", ca.getUserNo());
			// 3. 레코드셋 상태에 따른 분기
			if ( "I".equals(ca.getTrnPtrnDvcd()) ) { // INSERT	
				fSC013.fRegFrmItem(requestData, onlineCtx);
			} else if ( "U".equals(ca.getTrnPtrnDvcd()) ) { // UPDATE	
				fSC013.fUpdFrmItem(requestData, onlineCtx);
			} else if ( "D".equals(ca.getTrnPtrnDvcd()) ) { // DELETE
				fSC013.fDelFrmItem(requestData, onlineCtx);
			}
			// 4. 결과값 리턴
			responseData.setOkResultMessage("DMS00000", null);  //정상 처리되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e);  //시스템 오류
		}
		return responseData;
	}

}
