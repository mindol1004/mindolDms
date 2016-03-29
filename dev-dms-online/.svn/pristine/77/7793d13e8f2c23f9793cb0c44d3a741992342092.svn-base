package dms.sc.scsbase.biz;

import fwk.utils.HpcUtils;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: FSCMbrInfoIntgMgmt</li>
 * <li>설  명 : [FU]회원정보통합관리</li>
 * <li>작성일 : 2015-01-23 16:07:26</li>
 * <li>작성자 : 김석영 (kimsukyoung)</li>
 * </ul>
 *
 * @author 김석영 (kimsukyoung)
 */
public class FSCMbrInfoIntgMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCMbrInfoIntgMgmt() {
		super();
	}

	/**
	 * 회원정보통합_서비스공통
	 *
	 * @author 김석영 (kimsukyoung)
	 * @since 2015-01-23 16:07:26
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdMbrInfoIntgSc(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 입력값 체크 (구회원번호)
			if ( StringUtils.isEmpty(requestData.getField("BF_MBR_NO")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("BF_MBR_NO") }); // 필수입력항목 {0}이/가 누락되었습니다.
			}
			// 입력값 체크 (신회원번호)
			if ( StringUtils.isEmpty(requestData.getField("AF_MBR_NO")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("AF_MBR_NO") }); // 필수입력항목 {0}이/가 누락되었습니다.
			}

			// 1. SC_개인정보조회이력
			//   - 회원번호 A->B로 Update
			DSCMbrInfoIntgMgmt dSCMbrInfoIntgMgmt = (DSCMbrInfoIntgMgmt) lookupDataUnit(DSCMbrInfoIntgMgmt.class);
			dSCMbrInfoIntgMgmt.dUPrnInfoInqHstIntg(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

}
