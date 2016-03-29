package dms.sc.scbbase.biz;

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
 * <li>단위업무명: PSCBatJobOpHst</li>
 * <li>설  명 : 배치작업처리이력</li>
 * <li>작성일 : 2014-09-24 11:05:20</li>
 * <li>작성자 : 정혜미 (junghaemi)</li>
 * </ul>
 *
 * @author 정혜미 (junghaemi)
 */
public class PSCBatJobOpHst extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PSCBatJobOpHst() {
		super();
	}

	/**
	 * <pre>배치작업처리이력목록조회</pre>
	 *
	 * @author admin (admin)
	 * @since 2015-06-22 16:59:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : TASK_STA_DTM [작업일자FROM]
	 *	- field : TASK_END_DTM [작업일자TO]
	 *	- field : TASK_ID [프레임웍잡ID]
	 *	- field : TASK_NM [작업명]
	 *	- field : INST_CD [기관코드]
	 *	- field : BAT_TASK_OP_ST_CD [배치작업처리상태코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_BAT_JOB_OP_HST
	 *		- field : TASK_DT [작업일자]
	 *		- field : TASK_ID [프레임웍잡ID]
	 *		- field : TASK_NO [작업일련번호]
	 *		- field : TASK_NM [작업명]
	 *		- field : GRP_ID [그룹ID]
	 *		- field : INST_CD [기관코드]
	 *		- field : BAT_TASK_OP_ST_CD [배치작업처리상태코드]
	 *		- field : OP_STA_DTM [처리시작일시]
	 *		- field : OP_END_DTM [처리종료일시]
	 *		- field : OP_CNT [처리건수]
	 *		- field : FS_REG_USER_ID [최초등록사용자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종수정사용자ID]
	 *		- field : LS_CHG_DTM [최종수정일시]
	 * </pre>
	 */
	public IDataSet pInqBatJobOpHst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			//1. FU lookup
			FSCBatJobOpHst fRM0148M = (FSCBatJobOpHst) lookupFunctionUnit(FSCBatJobOpHst.class);
			// 2. FM 호출
			responseData = fRM0148M.fInqBatJobOpHst(requestData, onlineCtx);
			// 3. 결과값 리턴
			responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

}
