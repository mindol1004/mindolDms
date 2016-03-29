package dms.sc.scsbase.biz;

import java.util.Map;

import fwk.utils.HpcUtils;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;
import nexcore.framework.core.util.StringUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: FSCTlgmMgmt</li>
 * <li>설  명 : 전문관리</li>
 * <li>작성일 : 2014-10-24 11:28:32</li>
 * <li>작성자 : 이유미 (leeyoumee)</li>
 * </ul>
 *
 * @author 이유미 (leeyoumee)
 */
public class FSCTlgmMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCTlgmMgmt() {
		super();
	}

	/**
	 * 온라인공통영역 입력값 체크 (필수입력 / 입력항목 TYPE)
	 *
	 * @author 이유미 (leeyoumee)
	 * @since 2014-10-24 11:28:32
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : RCGN_KEY [제휴사식별키]
	 *	- field : INST_CD [기관코드]
	 *	- field : TLGM_NO [전문번호]
	 *	- field : TLGM_CHNL_CD [전문채널코드]
	 *	- field : TRS_DT [전송일자]
	 *	- field : TRS_TM [전송시각]
	 *	- field : TRAC_NO [추적번호]
	 *	- field : TLGM_CL_CD [전문구분코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fCheckOnlnCmArea(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 제휴사식별키(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("RCGN_KEY")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("RCGN_KEY") });    // 필수입력항목 {0}이/가 누락되었습니다.
			}
			// 제휴사식별키 유효성 검증(공통영역) TODO HPC- 하드코딩 필요여부 확인
			if ( !StringUtils.equals(requestData.getField("RCGN_KEY"), HpcUtils.encryptTextBySHA256ToStr("HPC-" + requestData.getField("INST_CD"))) ) {
				throw new BizRuntimeException("HPC00235", new String[] { HpcUtils.getLangMsg("RCGN_KEY") });    // 입력항목 {0}이/가 유효하지 않습니다.
			}
			// 기관코드(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("INST_CD")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("INST_CD") });    // 필수입력항목 {0}이/가 누락되었습니다.
			}
			// 전문번호(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TLGM_NO")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TLGM_NO") });    // 필수입력항목 {0}이/가 누락되었습니다.
			}
			// 전문번호 자릿수 체크 : 4자리
			if ( StringUtils.length(StringUtils.trim(requestData.getField("TLGM_NO"))) != 4 ) {
				throw new BizRuntimeException("HPC00251", new String[] { HpcUtils.getLangMsg("TLGM_NO"), "4" });    //항목 {0}은/는 {1}자리여야 합니다.
			}
			// 전문채널코드(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TLGM_CHNL_CD")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TLGM_CHNL_CD") });    // 필수입력항목 {0}이/가 누락되었습니다.
			}
			// 전송일자(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TRS_DT")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TRS_DT") });    // 필수입력항목 {0}이/가 누락되었습니다.
			}
			// 전송일자 유효성 체크
			if ( !DateUtils.isDateFormat(requestData.getField("TRS_DT"), "yyyyMMdd") ) {
				throw new BizRuntimeException("HPC00231", new String[] { HpcUtils.getLangMsg("TRS_DT") });    // 입력항목 {0}이/가 일자 형식 'YYYYMMDD'과 맞지 않습니다.
			}
			// 전송시각(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TRS_TM")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TRS_TM") });    // 필수입력항목 {0}이/가 누락되었습니다.
			}
			// 전송시각 유효성 체크
			if ( !DateUtils.isDateFormat(requestData.getField("TRS_TM"), "HHmmss") ) {
				throw new BizRuntimeException("HPC00233", new String[] { HpcUtils.getLangMsg("TRS_TM") });    // 입력항목 {0}이/가 시각 형식 'HHMISS'과 맞지 않습니다.
			}
			// 추적번호(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TRAC_NO")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TRAC_NO") });    // 필수입력항목 {0}이/가 누락되었습니다.
			}
			// 전문구분코드(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TLGM_CL_CD")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TLGM_CL_CD") });    // 필수입력항목 {0}이/가 누락되었습니다.
			}
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;

	}

	/**
	 * 온라인공통영역체크POS
	 *
	 * @author 이유미 (leeyoumee)
	 * @since 2014-10-24 11:28:32
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : INST_CD [기관코드]
	 *	- field : TLGM_NO [전문번호]
	 *	- field : TLGM_CHNL_CD [전문채널코드]
	 *	- field : TRS_DT [전송일자]
	 *	- field : TRS_TM [전송시각]
	 *	- field : TRAC_NO [추적번호]
	 *	- field : DATA_LEN [데이터길이]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fCheckOnlnCmAreaPos(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 기관코드(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("INST_CD")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("INST_CD") });    // 필수입력항목 {0}이/가 누락되었습니다.
			}
			// 전문번호(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TLGM_NO")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TLGM_NO") });    // 필수입력항목 {0}이/가 누락되었습니다.
			}
			// 전문번호 자릿수 체크 : 4자리
			if ( StringUtils.length(StringUtils.trim(requestData.getField("TLGM_NO"))) != 4 ) {
				throw new BizRuntimeException("HPC00251", new String[] { HpcUtils.getLangMsg("TLGM_NO"), "4" });    //항목 {0}은/는 {1}자리여야 합니다.
			}
			// 전문채널코드(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TLGM_CHNL_CD")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TLGM_CHNL_CD") });    // 필수입력항목 {0}이/가 누락되었습니다.
			}
			// 전송일자(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TRS_DT")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TRS_DT") });    // 필수입력항목 {0}이/가 누락되었습니다.
			}
			// 전송일자 유효성 체크
			if ( !DateUtils.isDateFormat(requestData.getField("TRS_DT"), "yyyyMMdd") ) {
				throw new BizRuntimeException("HPC00231", new String[] { HpcUtils.getLangMsg("TRS_DT") });    // 입력항목 {0}이/가 일자 형식 'YYYYMMDD'과 맞지 않습니다.
			}
			// 전송시각(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TRS_TM")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TRS_TM") });    // 필수입력항목 {0}이/가 누락되었습니다.
			}
			// 전송시각 유효성 체크
			if ( !DateUtils.isDateFormat(requestData.getField("TRS_TM"), "HHmmss") ) {
				throw new BizRuntimeException("HPC00233", new String[] { HpcUtils.getLangMsg("TRS_TM") });    // 입력항목 {0}이/가 시각 형식 'HHMISS'과 맞지 않습니다.
			}
			// 추적번호(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TRAC_NO")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TRAC_NO") });    // 필수입력항목 {0}이/가 누락되었습니다.
			}
			// 데이터길이(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("DATA_LEN")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("DATA_LEN") });    // 필수입력항목 {0}이/가 누락되었습니다.
			}
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 온라인공통영역체크IVR
	 *
	 * @author 이유미 (leeyoumee)
	 * @since 2014-10-24 11:28:32
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : RCGN_KEY [제휴사식별키]
	 *	- field : INST_CD [기관코드]
	 *	- field : TLGM_NO [전문번호]
	 *	- field : TLGM_CHNL_CD [전문채널코드]
	 *	- field : TRS_DT [전송일자]
	 *	- field : TRS_TM [전송시각]
	 *	- field : TRAC_NO [추적번호]
	 *	- field : TLGM_CL_CD [전문구분코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fCheckOnlnCmAreaIvr(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 제휴사식별키(공통영역)
			//if (StringUtils.isEmpty(requestData.getField("RCGN_KEY"))) {
			//    throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("[review]제휴사식별키") });    // 입력항목 {0}를  확인하세요.
			//}
			// 기관코드(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("INST_CD")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("INST_CD") });    // 입력항목 {0}를  확인하세요.
			}
			// 전문번호(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TLGM_NO")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TLGM_NO") });    // 입력항목 {0}를  확인하세요.
			}
			// 전문번호 자릿수 체크 : 4자리
			if ( StringUtils.length(StringUtils.trim(requestData.getField("TLGM_NO"))) != 4 ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TLGM_NO"), "4" });    //입력항목 {0}를  확인하세요.
			}
			// 전문채널코드(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TLGM_CHNL_CD")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TLGM_CHNL_CD") });    // 입력항목 {0}를  확인하세요.
			}
			// 전송일자(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TRS_DT")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TRS_DT") });    // 입력항목 {0}를  확인하세요.
			}
			// 전송일자 유효성 체크
			if ( !DateUtils.isDateFormat(requestData.getField("TRS_DT"), "yyyyMMdd") ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TRS_DT") });    // 입력항목 {0}를  확인하세요.
			}
			// 전송시각(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TRS_TM")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TRS_TM") });    // 입력항목 {0}를  확인하세요.
			}
			// 전송시각 유효성 체크
			if ( !DateUtils.isDateFormat(requestData.getField("TRS_TM"), "HHmmss") ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TRS_TM") });    // 입력항목 {0}를  확인하세요.
			}
			// 추적번호(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TRAC_NO")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TRAC_NO") });    // 입력항목 {0}를  확인하세요.
			}
			// 전문구분코드(공통영역)
			if ( StringUtils.isEmpty(requestData.getField("TLGM_CL_CD")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TLGM_CL_CD") });    // 입력항목 {0}를  확인하세요.
			}
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

}
