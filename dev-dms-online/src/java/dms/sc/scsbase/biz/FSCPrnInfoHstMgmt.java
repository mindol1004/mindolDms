package dms.sc.scsbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;
import fwk.common.CommonArea;
import fwk.common.CommonUtils;
import fwk.utils.HpcUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: FSCPrnInfoHstMgmt</li>
 * <li>설  명 : 개인정보조회이력관리</li>
 * <li>작성일 : 2014-08-11 11:11:26</li>
 * <li>작성자 : 김석영 (kimsukyoung)</li>
 * </ul>
 *
 * @author 김석영 (kimsukyoung)
 */
public class FSCPrnInfoHstMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCPrnInfoHstMgmt() {
		super();
	}

	/**
	 * 개인정보조회이력등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegPrnInfoInqHst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();
		try {

			// 개인정보조회이력 파라미터 셋팅
			CommonArea ca = CommonUtils.getCommonArea(onlineCtx);

			reqDs.putField("USER_NO", ca.getUserNo()); 
			reqDs.putField("IP_ADDR", ca.getIpad());
			reqDs.putField("MAC_ADDR", ca.getPrcmMac());
			reqDs.putField("GLB_ID", ca.getGlobId());
			reqDs.putField("INQ_REQ_DTM", ca.getSvcStrnDttm());

			//개인정보조회내용 마스킹
			String prnInfoInqCtt = reqDs.getField("PRN_INFO_INQ_CTT");
			String prnInfoInqItemCd = reqDs.getField("PRN_INFO_INQ_ITEM_CD");

			if ( StringUtils.equals(prnInfoInqItemCd, "01") ) {    // 성명
                prnInfoInqCtt = HpcUtils.maskingName(prnInfoInqCtt);
            } else if ( StringUtils.equals(prnInfoInqItemCd, "02") ) {   // 생년월일
                prnInfoInqCtt = HpcUtils.maskingBirthYmd(prnInfoInqCtt);
            } else if ( StringUtils.equals(prnInfoInqItemCd, "03") ) {   // 전체주소
                prnInfoInqCtt = HpcUtils.maskingAddress(prnInfoInqCtt);
            } else if ( StringUtils.equals(prnInfoInqItemCd, "04") ) {   // 상세주소
                prnInfoInqCtt = HpcUtils.maskingAddrDtl(prnInfoInqCtt);
            } else if ( StringUtils.equals(prnInfoInqItemCd, "05") ) {   // 주민번호
                prnInfoInqCtt = HpcUtils.maskingJuminNo(prnInfoInqCtt);
            } else if ( StringUtils.equals(prnInfoInqItemCd, "06") ) {   // 이메일주소
                prnInfoInqCtt = HpcUtils.maskingEmail(prnInfoInqCtt);
            } else if ( StringUtils.equals(prnInfoInqItemCd, "07") ) {   // 계좌번호
                prnInfoInqCtt = HpcUtils.maskingAccountNo(prnInfoInqCtt);                                
            } else if ( StringUtils.equals(prnInfoInqItemCd, "10") ||           // 휴대전화번호
				StringUtils.equals(prnInfoInqItemCd, "11") ||        // 일반전화번호
				StringUtils.equals(prnInfoInqItemCd, "12") ||        // 팩스번호
                StringUtils.equals(prnInfoInqItemCd, "13") ) {        // 서비스번호                
				prnInfoInqCtt = HpcUtils.maskingTelNo(prnInfoInqCtt);
			}
			reqDs.putField("PRN_INFO_INQ_CTT", prnInfoInqCtt);

			DSCPrnInfoInqHstMgmt dSCPrnInfoInqHstMgmt = (DSCPrnInfoInqHstMgmt) lookupDataUnit(DSCPrnInfoInqHstMgmt.class);
			responseData = dSCPrnInfoInqHstMgmt.dIPrnInfoInqHst(reqDs, onlineCtx);
		} catch ( BizRuntimeException e ) {
			// 개인정보조회이력 등록에 실패하였습니다.\n조회항목 : {0}
			throw e;
			//throw new BizRuntimeException("HPC00276", new String[]{HpcUtils.getCode("SCC005", reqDs.getField("개인정보조회이력항목")).getCodeNm()}, e);
		}
		return responseData;
	}

}
