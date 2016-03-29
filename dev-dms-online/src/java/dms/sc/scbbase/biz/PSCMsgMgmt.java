package dms.sc.scbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.RecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.common.CommonArea;
import fwk.utils.HpcUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: [PU]메시지관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-07-17 16:28:06</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class PSCMsgMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PSCMsgMgmt() {
		super();
	}

	/**
	 *
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-07-17 16:28:06
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : MESSAGE_ID [메시지ID]
	 *	- field : MESSAGE_NAME [메시지내용]
	 *	- field : MESSAGE_TYPE_XD [메시지타입]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_MSG_LIST
	 *		- field : MESSAGE_ID [메시지ID]
	 *		- field : MESSAGE_NAME [메시지내용]
	 *		- field : MESSAGE_TYPE_XD [메시지타입]
	 *		- field : POS_RESP_CD [POS응답코드]
	 *		- field : CO_RESP_CD [제휴응답코드]
	 *		- field : CO_RESP_DTL_CD [제휴응답상세코드]
	 * </pre>
	 */
	public IDataSet pInqMsgList(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet rsData = new DataSet();
		IRecordSet rsRecord = null;
		IRecord record = null;
		String sMsgName = null;
		try {
			// 1. FM 호출
			rsData = callSharedBizComponentByDirect("fw.FWSBase", "fSelectMessageList", requestData, onlineCtx);
			rsRecord = rsData.getRecordSet("RS_MSG_LIST");

			//2. 화면 출력데이터 특수문자 변환처리 (예:  "& #40;" -> "("  )  
			/*
			 * for(int i=0; i< rsRecord.getRecordCount(); i++){ record = rsRecord.getRecord(i); //메세지명 컬럼 sMsgName = record.get("MESSAGE_NAME");
			 * record.set("MESSAGE_NAME", HpcUtils.restoreSpecialChar(sMsgName)); }
			 */

			// 3. 결과값 리턴
			responseData.putRecordSet(rsRecord);
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
	 * @author 임지후 (jihooyim)
	 * @since 2014-07-17 16:28:06
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_MSG_LIST
	 *		- field : MESSAGE_ID [메시지ID]
	 *		- field : MESSAGE_NAME [메시지내용]
	 *		- field : MESSAGE_TYPE_XD [메시지타입]
	 *		- field : POS_RESP_CD [POS응답코드]
	 *		- field : CO_RESP_CD [제휴응답코드]
	 *		- field : CO_RESP_DTL_CD [제휴응답상세코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */

	public IDataSet pSaveMsg(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);
		try {
			// 1. 입력 RS설정
			requestData.putFieldMap(requestData.getRecordSet("RS_MSG_LIST").getRecordMap(0));
			requestData.putField("USER_NO", ca.getUserNo());
			// 2. 레코드셋 상태에 따른 분기
			if ( "I".equals(ca.getTrnPtrnDvcd()) ) { // INSERT
				responseData = callSharedBizComponentByDirect("fw.FWSBase", "fRegMessage", requestData, onlineCtx);
			} else if ( "U".equals(ca.getTrnPtrnDvcd()) ) { // UPDATE
				responseData = callSharedBizComponentByDirect("fw.FWSBase", "fUpdateMessage", requestData, onlineCtx);
			} else if ( "D".equals(ca.getTrnPtrnDvcd()) ) { // DELETE
				responseData = callSharedBizComponentByDirect("fw.FWSBase", "fDeleteMessage", requestData, onlineCtx);
			}
			// 3. 결과값 리턴
			responseData.setOkResultMessage("DMS00000", null);//정상 처리되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;

	}

	/**
	 * 메시지반영
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pAplyMsg(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		HpcUtils.msgRefresh();
		return responseData;
	}
}