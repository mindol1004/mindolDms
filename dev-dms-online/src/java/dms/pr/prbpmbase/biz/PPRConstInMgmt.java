package dms.pr.prbpmbase.biz;

import java.util.Map;

import fwk.common.CommonArea;
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
 * <li>업무 그룹명 : dms/임대R</li>
 * <li>단위업무명: [PU]구성품입고관리</li>
 * <li>설  명 : <pre>구성품입고관리PU</pre></li>
 * <li>작성일 : 2015-07-10 09:24:16</li>
 * <li>작성자 : 박민정 (dangnagu2)</li>
 * </ul>
 *
 * @author 박민정 (dangnagu2)
 */
public class PPRConstInMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PPRConstInMgmt(){
		super();
	}

	/**
	 * <pre>구성품입고조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-10 11:20:26
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : STA_IN_DT [조회입고시작일]
	 *	- field : END_IN_DT [조회입고종료일]
	 *	- field : PRCHCO_CD [매입처코드]
	 *	- field : PRCHCO_NM [매입처명]
	 *	- field : CMPT_CD [구성품코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_CMPT_IN_LIST
	 *		- field : CMPT_CD [구성품코드]
	 *		- field : PRCHCO_CD [매입처코드]
	 *		- field : CMPT_IN_DT [구성품입고일자]
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : CMPT_PRCH_QTY [구성품매입수량]
	 *		- field : CMPT_PRCH_UPRC [구성품매입단가]
	 *		- field : DEL_YN [삭제여부]
	 *		- field : FS_REG_USER_ID [최초등록사용자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경사용자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 *		- field : PRCH_CO_NM [매입처명]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 * </pre>
	 */
	public IDataSet pInqConstInLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
		try {
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("pr.PRSPMBase", "fInqConstInLst", requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 2. 결과값 리턴
		responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
		
		return responseData;
	}

	/**
	 * <pre>구성품입고입력</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-07-10 11:20:26
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_CONST_IN_LIST
	 *		- field : CMPT_IN_NO [구성품입고번호]
	 *		- field : CMPT_IN_DT [구성품입고일자]
	 *		- field : PRCHCO_CD [매입처코드]
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : CMPT_CD [구성품코드]
	 *		- field : CMPT_PRCH_QTY [구성품매입수량]
	 *		- field : CMPT_PRCH_UPRC [구성품매입단가]
	 *		- field : DEL_YN [삭제여부]
	 *		- field : FS_REG_USER_ID [최초등록사용자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경사용자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveConstIn(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IRecordSet rs = requestData.getRecordSet("RS_CONST_IN_LIST");
	    CommonArea ca = getCommonArea(onlineCtx);

		try {		
			// 레코드셋 상태에 따른 분기
			for (int i=0; i<rs.getRecordCount(); i++) {
				requestData.putFieldMap(rs.getRecordMap(i));
				requestData.putField("FS_REG_USER_ID", ca.getUserNo());
				requestData.putField("LS_CHG_USER_ID", ca.getUserNo());
				
				if ( "I".equals(ca.getTrnPtrnDvcd()) ) { // INSERT
					callSharedBizComponentByDirect("pr.PRSPMBase", "fRegConstIn", requestData, onlineCtx);
				} 
			}
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 결과값 리턴
		responseData.setOkResultMessage("DMS00000", null); //정상 처리되었습니다.
	    return responseData;
	}
  
}