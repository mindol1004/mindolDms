package dms.sc.scsbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: DSCMbrInfoIntgMgmt</li>
 * <li>설  명 : [DU]회원정보통합관리</li>
 * <li>작성일 : 2015-01-27 19:06:23</li>
 * <li>작성자 : 김석영 (kimsukyoung)</li>
 * </ul>
 *
 * @author 김석영 (kimsukyoung)
 */
public class DSCMbrInfoIntgMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCMbrInfoIntgMgmt() {
		super();
	}

	/**
	 * 개인정보조회이력통합
	 *
	 * @author 김석영 (kimsukyoung)
	 * @since 2015-01-27 19:49:58
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUPrnInfoInqHstIntg(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		int rowCnt = dbUpdate("UPrnInfoInqHstIntg", requestData, onlineCtx);
		responseData.putField("ROW_CNT", rowCnt);
		return responseData;
	}

}
