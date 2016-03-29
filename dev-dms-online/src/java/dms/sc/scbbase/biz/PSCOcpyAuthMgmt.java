package dms.sc.scbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;
import fwk.common.CommonArea;
import fwk.utils.HpcUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/업무공통</li>
 * <li>단위업무명: PSCOcpyAuthMgmt</li>
 * <li>설  명 : 점유인증</li>
 * <li>작성일 : 2014-08-28 19:32:20</li>
 * <li>작성자 : 김석영 (kimsukyoung)</li>
 * </ul>
 *
 * @author 김석영 (kimsukyoung)
 */
public class PSCOcpyAuthMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */
    //점유인증  
    private final static  String OCPY_AUTH_CHK_MM     = "3"; //점유인증확인시간(분)
    private final static String OCPY_AUTH_ERR_CNT    = "3" ;//점유인증확인오류횟수
	/**
	 * Default Constructor
	 */
	public PSCOcpyAuthMgmt() {
		super();
	}

	/**
	 * 점유인증번호발송
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : MBR_NO [회원번호]
	 *	- field : RETN_TEL_NO [수신전화번호]
	 *	- field : OCPY_AUTH_REQ_CHNL_CD [점유인증요청채널]
	 *	- field : RESEND_YN [재전송여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSndOcpyAuthNo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();     //원거래의 DataSet의 훼손을 막기 위한 Clone
		CommonArea ca = getCommonArea(onlineCtx);

		if ( StringUtils.isEmpty(requestData.getField("MBR_NO")) ) {
			throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("MBR_NO") });  // 필수입력항목 {0}이/가 누락되었습니다.
		}
		if ( StringUtils.isEmpty(requestData.getField("RETN_TEL_NO")) ) {
			throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TEL_NO") });
		}
		if ( StringUtils.isEmpty(requestData.getField("OCPY_AUTH_REQ_CHNL_CD")) ) {
			throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("OCPY_AUTH_REQ_CHNL_CD") });
		}

		try {
			reqDs.putField("REQ_BRND_CD", ca.getReqBrndCd());
			reqDs.putField("REQ_USER_ID", ca.getUserNo());
			responseData = callSharedBizComponentByDirect("sc.SCSBase", "fSndOcpyAuthNo", reqDs, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류
		}
		responseData.setOkResultMessage("HPC00116", null);  // 인증번호를 발송하였습니다.
		return responseData;
	}

	/**
	 * 점유인증확인
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : MBR_NO [회원번호]
	 *	- field : AUTH_NO [인증번호]
	 *	- field : RETN_TEL_NO [수신전화번호]
	 *	- field : OCPY_AUTH_REQ_CHNL_CD [점유인증요청채널]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : SUCCESS [필드1]
	 * </pre>
	 */
	public IDataSet pCnfmOcpyAuth(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);
		try {
			requestData.putField("REQ_USER_ID", ca.getUserNo());
			responseData = callSharedBizComponentByRequiresNew("sc.SCSBase", "fCnfmOcpyAuth", requestData, onlineCtx);
			// 점유인증 성공여부에 따라 Exception 발생
			if ( StringUtils.equals(responseData.getField("AUTH_SUCC_YN"), "N") ) {
				if ( Integer.parseInt(responseData.getField("AUTH_ERR_CNT")) >= Integer.parseInt(OCPY_AUTH_ERR_CNT) ) {
					throw new BizRuntimeException("HPC00602", new String[] {OCPY_AUTH_ERR_CNT });    // 인증 오류 횟수 ({0}회) 초과하였습니다. 
				} else {
					throw new BizRuntimeException("HPC00275");    // 인증에 실패하였습니다.
				}
			}
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류
		}
		responseData.setOkResultMessage("HPC00117", null);  // 인증에 성공하였습니다.
		return responseData;
	}

}
