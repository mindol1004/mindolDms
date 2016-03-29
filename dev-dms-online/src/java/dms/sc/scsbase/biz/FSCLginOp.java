package dms.sc.scsbase.biz;

import org.apache.commons.lang.StringUtils;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: FSCLginOp</li>
 * <li>설  명 : 로그인처리</li>
 * <li>작성일 : 2014-08-25 13:16:48</li>
 * <li>작성자 : 김석영 (kimsukyoung)</li>
 * </ul>
 *
 * @author 김석영 (kimsukyoung)
 */
public class FSCLginOp extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCLginOp() {
		super();
	}

	/**
	 * 패스워드오류횟수변경
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdPwdErrCnt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			DSCLginOp dSCLginOp = (DSCLginOp) lookupDataUnit(DSCLginOp.class);
			dSCLginOp.dUPwdErrCntChg(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 세션정보조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqSeonInfo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			DSCUserLginSessInfoMgmt dSCUserLginSessInfoMgmt = (DSCUserLginSessInfoMgmt) lookupDataUnit(DSCUserLginSessInfoMgmt.class);
			responseData = dSCUserLginSessInfoMgmt.dSUserLginSessInfo(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 세션정보등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegSeonInfo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			DSCUserLginSessInfoMgmt dSCUserLginSessInfoMgmt = (DSCUserLginSessInfoMgmt) lookupDataUnit(DSCUserLginSessInfoMgmt.class);
			dSCUserLginSessInfoMgmt.dIUserLginSessInfo(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 세션정보수정
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdSeonInfo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			DSCUserLginSessInfoMgmt dSCUserLginSessInfoMgmt = (DSCUserLginSessInfoMgmt) lookupDataUnit(DSCUserLginSessInfoMgmt.class);
			dSCUserLginSessInfoMgmt.dUUserLginSessInfo(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 세션중복체크
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqSeonDup(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet responseData1 = new DataSet();
		try {
			DSCUserLginSessInfoMgmt dSCUserLginSessInfoMgmt = (DSCUserLginSessInfoMgmt) lookupDataUnit(DSCUserLginSessInfoMgmt.class);
			responseData = dSCUserLginSessInfoMgmt.dSUserLginSessInfoChk(requestData, onlineCtx);
			if ( responseData.getRecordSet("RS_SESS").getRecordCount() > 0 ) {
				// 강제로그인시 이전세션 연결끊음
				if ( StringUtils.equals(requestData.getField("FORCED_YN"), "Y") ) {
					//같은 소스 내의 method 호출 : 2014.10.30 by 이유미
					responseData1 = fUpdSeonInfo(requestData, onlineCtx);
					//callSharedBizComponentByRequiresNew("sc.SCSBase", "fUpdSeonInfo", requestData, onlineCtx);
				} else {  // 강제연결아닌경우 에러
					throw new BizRuntimeException("DMS00016"); // 이미 로그인중인 세션이 존재합니다.이전 연결을 끊고 접속하시겠습니까? 
				}

			}
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

}
