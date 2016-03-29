package dms.sc.scsbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: DTB_DATA_AUTR_ROL01</li>
 * <li>설  명 : 데이터권한역할</li>
 * <li>작성일 : 2014-09-11 19:28:25</li>
 * <li>작성자 : 김석영 (kimsukyoung)</li>
 * </ul>
 *
 * @author 김석영 (kimsukyoung)
 */
public class DSCDataAutrRolMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCDataAutrRolMgmt() {
		super();
	}

	/**
	 * 데이터권한역할조회(SV)
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSDataAutrRolBySv(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecordSet rs = dbSelect("SDataAutrRolBySv", requestData, onlineCtx);
		responseData.putRecordSet("RS_DATA_AUTR_ROL", rs);
		return responseData;
	}

	/**
	 * 데이터권한역할조회(팀장)
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSDataAutrRolByTeamMgr(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecordSet rs = dbSelect("SDataAutrRolByTeamMgr", requestData, onlineCtx);
		responseData.putRecordSet("RS_DATA_AUTR_ROL", rs);
		return responseData;
	}

	/**
	 * 데이터권한역할조회(일반)
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSDataAutrRolByBase(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecordSet rs = dbSelect("SDataAutrRolByBase", requestData, onlineCtx);
		responseData.putRecordSet("RS_DATA_AUTR_ROL", rs);
		return responseData;
	}

}
