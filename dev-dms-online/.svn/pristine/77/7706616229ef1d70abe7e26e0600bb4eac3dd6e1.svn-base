package dms.sc.scsbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: DSCCmCdMgmt</li>
 * <li>설  명 : 공통코드관리</li>
 * <li>작성일 : 2014-09-25 19:02:57</li>
 * <li>작성자 : 김석영 (kimsukyoung)</li>
 * </ul>
 *
 * @author 김석영 (kimsukyoung)
 */
public class DSCCmCdMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCCmCdMgmt() {
		super();
	}

	/**
	 * 공통코드조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSCmCd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecordSet rsReturn = dbSelect("SCmCd", requestData, onlineCtx);
		responseData.putRecordSet("RS_CM_CD_LST", rsReturn);
		return responseData;
	}

}
