package dms.sc.scsbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import fwk.constants.DmsConstants;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/업무공통</li>
 * <li>단위업무명: DTH_OCPY_AUTH_HST01</li>
 * <li>설  명 : 점유인증</li>
 * <li>작성일 : 2014-08-27 11:40:33</li>
 * <li>작성자 : 김석영 (kimsukyoung)</li>
 * </ul>
 *
 * @author 김석영 (kimsukyoung)
 */
public class DSCOcpyAuthMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCOcpyAuthMgmt() {
		super();
	}

	/**
	 * 점유인증번호등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIOcpyAuthNo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		dbInsert("IOcpyAuthNo", requestData, onlineCtx);
		return responseData;
	}

	/**
	 * 점유인증상태업데이트
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUOcpyAuthSt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		int rowCnt = dbUpdate("UOcpyAuthSt", requestData, onlineCtx);
		responseData.putField(DmsConstants.IS_SUCCESS, rowCnt > 0);
		return responseData;
	}

	/**
	 * 인증번호확인
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSOcpyAuthNo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecordSet rs = dbSelect("SOcpyAuthNo", requestData, onlineCtx);
		responseData.putRecordSet("RS_AUTH", rs);

		return responseData;
	}

}
