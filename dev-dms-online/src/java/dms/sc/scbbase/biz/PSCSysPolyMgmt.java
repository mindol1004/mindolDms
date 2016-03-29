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
 * <li>단위업무명: [PU]시스템정책관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-07-30 10:56:50</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class PSCSysPolyMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PSCSysPolyMgmt() {
		super();
	}

	/**
	 *
	 *
	 * @author 이유미 (leeyoumee)
	 * @since 2014-07-30 10:56:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SYS_POLY_NO [시스템정책번호]
	 *	- field : SYS_POLY_NM [시스탬정책명]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_SYS_POLY_MGMT_LIST
	 *		- field : SYS_POLY_NO [시스템정책번호]
	 *		- field : SYS_POLY_NM [시스탬정책명]
	 *		- field : USE_YN [사용여부]
	 *		- field : IPUT_CON_DESC1 [입력조건설명1]
	 *		- field : IPUT_CON_DESC2 [입력조건설명2]
	 *		- field : IPUT_CON_DESC3 [입력조건설명3]
	 *		- field : IPUT_CON_DESC4 [입력조건설명4]
	 *		- field : IPUT_CON_DESC5 [입력조건설명5]
	 *		- field : IPUT_CON_DESC6 [입력조건설명6]
	 *		- field : IPUT_CON_DESC7 [입력조건설명7]
	 *		- field : IPUT_CON_DESC8 [입력조건설명8]
	 *		- field : IPUT_CON_DESC9 [입력조건설명9]
	 *		- field : OP_RULE_DESC1 [처리규칙설명1]
	 *		- field : OP_RULE_DESC2 [처리규칙설명2]
	 *		- field : OP_RULE_DESC3 [처리규칙설명3]
	 *		- field : OP_RULE_DESC4 [처리규칙설명4]
	 *		- field : OP_RULE_DESC5 [처리규칙설명5]
	 *		- field : OP_RULE_DESC6 [처리규칙설명6]
	 *		- field : OP_RULE_DESC7 [처리규칙설명7]
	 *		- field : OP_RULE_DESC8 [처리규칙설명8]
	 *		- field : OP_RULE_DESC9 [처리규칙설명9]
	 * </pre>
	 */
	public IDataSet pInqSysPolyMgmtLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCSysPolyMgmt fSC005 = (FSCSysPolyMgmt) lookupFunctionUnit(FSCSysPolyMgmt.class);
			// 2. FM 호출
			responseData = fSC005.fInqSysPolyLst(requestData, onlineCtx);
			// 3. 결과값 리턴
			responseData.setOkResultMessage("DMS00001", null);  //정상 조회되었습니다. 
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

	/**
	 * 시스템정책저장
	 *
	 * @author 이유미 (leeyoumee)
	 * @since 2014-07-30 10:56:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SYS_POLY_MGMT_LIST
	 *		- field : SYS_POLY_NO [시스템정책번호]
	 *		- field : SYS_POLY_NM [시스탬정책명]
	 *		- field : USE_YN [사용여부]
	 *		- field : IPUT_CON_DESC1 [입력조건설명1]
	 *		- field : IPUT_CON_DESC2 [입력조건설명2]
	 *		- field : IPUT_CON_DESC3 [입력조건설명3]
	 *		- field : IPUT_CON_DESC4 [입력조건설명4]
	 *		- field : IPUT_CON_DESC5 [입력조건설명5]
	 *		- field : IPUT_CON_DESC6 [입력조건설명6]
	 *		- field : IPUT_CON_DESC7 [입력조건설명7]
	 *		- field : IPUT_CON_DESC8 [입력조건설명8]
	 *		- field : IPUT_CON_DESC9 [입력조건설명9]
	 *		- field : OP_RULE_DESC1 [처리규칙설명1]
	 *		- field : OP_RULE_DESC2 [처리규칙설명2]
	 *		- field : OP_RULE_DESC3 [처리규칙설명3]
	 *		- field : OP_RULE_DESC4 [처리규칙설명4]
	 *		- field : OP_RULE_DESC5 [처리규칙설명5]
	 *		- field : OP_RULE_DESC6 [처리규칙설명6]
	 *		- field : OP_RULE_DESC7 [처리규칙설명7]
	 *		- field : OP_RULE_DESC8 [처리규칙설명8]
	 *		- field : OP_RULE_DESC9 [처리규칙설명9]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveSysPolyMgmt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);
		try {
			// 1. FU lookup
			FSCSysPolyMgmt fSC005 = (FSCSysPolyMgmt) lookupFunctionUnit(FSCSysPolyMgmt.class);
			// 2. 입력 RS설정
			requestData.putFieldMap(requestData.getRecordSet("RS_SYS_POLY_MGMT_LIST").getRecordMap(0));
			requestData.putField("USERNO", ca.getUserNo());
			// 3. 레코드셋 상태에 따른 분기
			if ( "I".equals(ca.getTrnPtrnDvcd()) ) { // INSERT	
				fSC005.fRegSysPoly(requestData, onlineCtx);
			} else if ( "U".equals(ca.getTrnPtrnDvcd()) ) { // UPDATE	
				fSC005.fUpdSysPoly(requestData, onlineCtx);
			}
			// 4. 결과값 리턴
			responseData.setOkResultMessage("DMS00000", null);// 정상 처리되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

	/**
	 * 시스템정책상세저장
	 *
	 * @author 이유미 (leeyoumee)
	 * @since 2014-07-30 10:56:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SYS_POLY_DTL_MGMT_LIST
	 *		- field : SYS_POLY_NO [시스템정책번호]
	 *		- field : SYS_POLY_SNO [시스템정책일련번호]
	 *		- field : APLY_STA_DT [적용시작일자]
	 *		- field : APLY_END_DT [적용종료일자]
	 *		- field : IPUT_CON_VAL1 [입력조건값1]
	 *		- field : IPUT_CON_VAL2 [입력조건값2]
	 *		- field : IPUT_CON_VAL3 [입력조건값3]
	 *		- field : IPUT_CON_VAL4 [입력조건값4]
	 *		- field : IPUT_CON_VAL5 [입력조건값5]
	 *		- field : IPUT_CON_VAL6 [입력조건값6]
	 *		- field : IPUT_CON_VAL7 [입력조건값7]
	 *		- field : IPUT_CON_VAL8 [입력조건값8]
	 *		- field : IPUT_CON_VAL9 [입력조건값9]
	 *		- field : OP_RULE_VAL1 [처리규칙값1]
	 *		- field : OP_RULE_VAL2 [처리규칙값2]
	 *		- field : OP_RULE_VAL3 [처리규칙값3]
	 *		- field : OP_RULE_VAL4 [처리규칙값4]
	 *		- field : OP_RULE_VAL5 [처리규칙값5]
	 *		- field : OP_RULE_VAL6 [처리규칙값6]
	 *		- field : OP_RULE_VAL7 [처리규칙값7]
	 *		- field : OP_RULE_VAL8 [처리규칙값8]
	 *		- field : OP_RULE_VAL9 [처리규칙값9]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_SYS_POLY_SNO
	 *		- field : SYS_POLY_SNO [시스템정책일련번호]
	 * </pre>
	 */
	public IDataSet pSaveSysPolyDtl(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);
		try {
			// 1. FU lookup                                                                                     
			FSCSysPolyDtlMgmt fSC006 = (FSCSysPolyDtlMgmt) lookupFunctionUnit(FSCSysPolyDtlMgmt.class);
			// 2. 입력 RS설정
			requestData.putFieldMap(requestData.getRecordSet("RS_SYS_POLY_DTL_MGMT_LIST").getRecordMap(0));
			requestData.putField("USERNO", ca.getUserNo());
			// 3. 레코드셋 상태에 따른 분기                                                                 
			if ( "I".equals(ca.getTrnPtrnDvcd()) ) { // INSERT	                                
				responseData = fSC006.fRegSysPolyDtl(requestData, onlineCtx);
			} else if ( "U".equals(ca.getTrnPtrnDvcd()) ) { // UPDATE	                        
				fSC006.fUpdSysPolyDtl(requestData, onlineCtx);
			}
			// 4. 결과값 리턴                                                                                   
			responseData.setOkResultMessage("DMS00000", null);// 정상 처리되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

	/**
	 * 시스템정책상세목록조회
	 *
	 * @author 이유미 (leeyoumee)
	 * @since 2014-07-30 10:56:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SYS_POLY_NO [시스템정책번호]
	 *	- field : SYS_POLY_SNO [시스템정책일련번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_SYS_POLY_DTL_MGMT_LIST
	 *		- field : SYS_POLY_NO [시스템정책번호]
	 *		- field : SYS_POLY_SNO [시스템정책일련번호]
	 *		- field : APLY_STA_DT [적용시작일자]
	 *		- field : APLY_END_DT [적용종료일자]
	 *		- field : IPUT_CON_VAL1 [입력조건값1]
	 *		- field : IPUT_CON_VAL2 [입력조건값2]
	 *		- field : IPUT_CON_VAL3 [입력조건값3]
	 *		- field : IPUT_CON_VAL4 [입력조건값4]
	 *		- field : IPUT_CON_VAL5 [입력조건값5]
	 *		- field : IPUT_CON_VAL6 [입력조건값6]
	 *		- field : IPUT_CON_VAL7 [입력조건값7]
	 *		- field : IPUT_CON_VAL8 [입력조건값8]
	 *		- field : IPUT_CON_VAL9 [입력조건값9]
	 *		- field : OP_RULE_VAL1 [처리규칙값1]
	 *		- field : OP_RULE_VAL2 [처리규칙값2]
	 *		- field : OP_RULE_VAL3 [처리규칙값3]
	 *		- field : OP_RULE_VAL4 [처리규칙값4]
	 *		- field : OP_RULE_VAL5 [처리규칙값5]
	 *		- field : OP_RULE_VAL6 [처리규칙값6]
	 *		- field : OP_RULE_VAL7 [처리규칙값7]
	 *		- field : OP_RULE_VAL8 [처리규칙값8]
	 *		- field : OP_RULE_VAL9 [처리규칙값9]
	 * </pre>
	 */
	public IDataSet pInqSysPolyDtlLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup                                                                                     
			FSCSysPolyDtlMgmt fSC006 = (FSCSysPolyDtlMgmt) lookupFunctionUnit(FSCSysPolyDtlMgmt.class);
			// 2. FM 호출                                                                                       
			responseData = fSC006.fInqSysPolyDtlLst(requestData, onlineCtx);
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
