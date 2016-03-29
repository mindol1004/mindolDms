package dms.nr.nrbplbase.biz;

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
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [PU]단말기정책관리</li>
 * <li>설  명 : <pre>단말기정책관리PU</pre></li>
 * <li>작성일 : 2015-07-10 10:02:51</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class PNREqpPolyMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNREqpPolyMgmt(){
		super();
	}

	/**
	 * <pre>단말기별 정책정보를 조회한다</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-10 11:29:52
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : EQP_MDL_CD [단말기코드]
	 *	- field : POLY_NM [정책명]
	 *	- field : RENTAL_POLY_EFF_PRD_STA_DT [유효기간시작일]
	 *	- field : RENTAL_POLY_EFF_PRD_END_DT [유효기간종료일]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : TB_RENTAL_SALE_POLY
	 *		- field : RENTAL_POLY_NO [렌탈정책번호]
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : POLY_NM [정책명]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : OUT_PRC [출고가]
	 *		- field : EXPT_DSPSL_PRC [예상매각가]
	 *		- field : RENTAL_PRN [렌탈원금]
	 *		- field : RENTAL_FEE [렌탈요금]
	 *		- field : CNTRT_PRD [계약기간코드]
	 *		- field : RENTAL_POLY_EFF_PRD_STA_DT [유효기간시작일]
	 *		- field : RENTAL_POLY_EFF_PRD_END_DT [유효기간종료일]
	 *		- field : FS_REG_USER_ID [최초등록사용자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경사용자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 *		- field : OP_CL_CD [업무구분코드]
	 *		- field : CAPA_CD [용량코드]
	 *		- field : RENTAL_POLY_END_YN [정책사용여부]
	 *		- field : URGT_YN [정책긴급적용여부]
	 * </pre>
	 */
	public IDataSet pInqEqpPolyLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("nr.NRSPLBase", "fInqEqpPolyLst", requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 3. 결과값 리턴
		responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
	
	    return responseData;
	}

	/**
	 * <pre>단말기정책정보를 등록/수정 저장한다.</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-10 11:29:52
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_POLY_LIST
	 *		- field : POLY_NM [정책명]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : RENTAL_FEE [렌탈요금]
	 *		- field : CNTRT_PRD [계약기간코드]
	 *		- field : RENTAL_POLY_EFF_PRD_STA_DT [유효기간시작일]
	 *		- field : RENTAL_POLY_EFF_PRD_END_DT [유효기간종료일]
	 *		- field : EQP_MDL_CD [단말모델코드]
	 *		- field : OUT_PRC [출고가]
	 *		- field : EXPT_DSPSL_PRC [예상매각가]
	 *		- field : RENTAL_POLY_NO [렌탈정책번호]
	 *		- field : OP_CL_CD [업무구분코드]
	 *		- field : RENTAL_PRN [렌탈원금]
	 *		- field : CAPA_CD [용량코드]
	 *		- field : RENTAL_POLY_END_YN [정책종료여부]
	 *		- field : URGT_YN [정책긴급적용여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveEqpPoly(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);

		try {
			// 1. 입력 RS설정
			requestData.putFieldMap(requestData.getRecordSet("RS_EQP_POLY_LIST").getRecordMap(0));
			requestData.putField("USERNO", ca.getUserNo());
			// 2. 레코드셋 상태에 따른 분기
			if ( "I".equals(ca.getTrnPtrnDvcd()) ) { // INSERT
				callSharedBizComponentByDirect("nr.NRSPLBase", "fRegEqpPoly", requestData, onlineCtx);
			} else if ( "U".equals(ca.getTrnPtrnDvcd()) ) { // UPDATE
				callSharedBizComponentByDirect("nr.NRSPLBase", "fUpdEqpPoly", requestData, onlineCtx);
			}
			// 3. 결과값 리턴
			responseData.setOkResultMessage("DMS00000", null); //정상 처리되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
	
	    return responseData;
	}
  
}
