package dms.sc.scsbase.biz;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;

import org.apache.commons.logging.Log;

import fwk.constants.DmsConstants;
import fwk.utils.HpcUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/업무공통</li>
 * <li>단위업무명: FSCOcpyAuthMgmt</li>
 * <li>설  명 : 점유인증</li>
 * <li>작성일 : 2014-08-27 10:58:18</li>
 * <li>작성자 : 김석영 (kimsukyoung)</li>
 * </ul>
 *
 * @author 김석영 (kimsukyoung)
 */
public class FSCOcpyAuthMgmt extends fwk.base.FunctionUnit {

    //알림업무구분
	private final static String ALRT_WORK_CL_OCPY_AUTH           = "11"; //점유인증
    //수신자구분코드
	private final static String REVR_CL_MBR          = "10";//회원
	private final static String REVR_CL_USER         = "20";//사용자
	private final static String REVR_CL_MCHT_OWNER   = "30";//가맹점주
	private final static String REVR_CL_COP          = "40";//제휴사	
    //수신채널구분코드(RETN_CHNL_CL_CD)
    private final static String RETN_CHNL_CL_SMS     = "S";//SMS
    private final static String RETN_CHNL_CL_EML     = "E";//이메일
    private final static String RETN_CHNL_CL_DM      = "D";//DM
    private final static String RETN_CHNL_CL_TM      = "T";//TM	
    //점유인증  
    private final static String OCPY_AUTH_CHK_MM     = "3"; //점유인증확인시간(분)
    private final static String OCPY_AUTH_ERR_CNT    = "3" ;//점유인증확인오류횟수	
	/**
	 * Default Constructor
	 */
	public FSCOcpyAuthMgmt() {
		super();
	}

	/**
	 * 점유인증번호발송
	 *
	 * @author 이유미 (leeyoumee)
	 * @since 2014-08-27 10:58:18
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : OCPY_AUTH_REQ_CHNL_CD [점유인증요청채널코드]
	 *	- field : MBR_NO [회원번호]
	 *	- field : RETN_TEL_NO [수신전화번호]
	 *	- field : REQ_BRND_CD [요청브랜드코드]
	 *	- field : REQ_USER_ID [요청사용자ID]
	 *	- field : REQ_CHNL_CD [요청채널코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * @throws NoSuchAlgorithmException 
	 */
	public IDataSet fSndOcpyAuthNo(IDataSet requestData, IOnlineContext onlineCtx) throws NoSuchAlgorithmException {
		IDataSet responseData = new DataSet();

		if ( StringUtils.isEmpty(requestData.getField("OCPY_AUTH_REQ_CHNL_CD")) ) {
			throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("OCPY_AUTH_REQ_CHNL_CD") });  // 필수입력항목 {0}이/가 누락되었습니다.
		}
		if ( StringUtils.isEmpty(requestData.getField("MBR_NO")) ) {
			throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("MBR_NO") });
		}
		if ( StringUtils.isEmpty(requestData.getField("RETN_TEL_NO")) ) {
			throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TEL_NO") });
		}
		if ( StringUtils.isEmpty(requestData.getField("REQ_BRND_CD")) ) {
			throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("REQ_BRND_CD") });
		}
		if ( StringUtils.isEmpty(requestData.getField("REQ_USER_ID")) ) {
			throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("REQ_USER_ID") });
		}

		IDataSet reqData = (IDataSet) requestData.clone();
		try {
			reqData.putField("ALRT_WORK_CL_CD", ALRT_WORK_CL_OCPY_AUTH);      // 알림서비스번호(점유인증)
			reqData.putField("REVR_CL_CD", REVR_CL_MBR);              // 수신자구분코드(회원)
			//reqData.putField("MBR_NO", "");                            // 수신자번호(회원번호)
			reqData.putField("RETN_AGR_YN", "N");                        // 수신적용동의여부(N)
			reqData.putField("RETN_TEL_NO", HpcUtils.maskingTelNo(requestData.getField("RETN_TEL_NO")));        // 수신전화번호(마스킹)
			reqData.putField("RETN_TEL_NO_ENPT", HpcUtils.encodeByAES(requestData.getField("RETN_TEL_NO")));    // 수신전화번호(암호화)
			SecureRandom rand;
            try {
                rand = SecureRandom.getInstance("SHA1PRNG");
                rand.setSeed(new Date().getTime());
            } catch (NoSuchAlgorithmException e) {
                throw  e ;
            }
			String authNo = String.valueOf(StringUtils.rpad(String.valueOf(rand.nextDouble()* 1000000000000L / 1000000L), 6, "0"));    // 인증번호생성(6자리)

			// 점유인증테이블 등록
			reqData.putField("AUTH_NO", authNo);
			reqData.putField("USER_NO", requestData.getField("REQ_USER_ID"));
			DSCOcpyAuthMgmt dTH_OCPY_AUTH_HST01 = (DSCOcpyAuthMgmt) lookupDataUnit(DSCOcpyAuthMgmt.class);
			dTH_OCPY_AUTH_HST01.dIOcpyAuthNo(reqData, onlineCtx);

			// SMS 발송
			reqData.putField("REVR_NO", requestData.getField("MBR_NO"));
			//reqData.putField("RETN_TEL_NO", HpcUtils.decodeByAES(reqData.getField("RETN_TEL_NO_ENPT")));    // 수신전화번호(복호화)
			reqData.putField("MSG_PARAMS", new String[] { authNo });  // 파라미터(인증번호)
			reqData.putField("ALRT_CHNL_CL_CD", RETN_CHNL_CL_SMS);            // SMS
			reqData.putField("ALRT_SND_REQ_CHNL_CD", reqData.getField("OCPY_AUTH_REQ_CHNL_CD"));
			FSCAlrtMgmt fBSBCAlrtMgmt = (FSCAlrtMgmt) lookupFunctionUnit(FSCAlrtMgmt.class);
			responseData = fBSBCAlrtMgmt.fSndAlrt(reqData, onlineCtx);

		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 점유인증확인
	 *
	 * @author 이유미 (leeyoumee)
	 * @since 2014-08-27 10:58:18
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : MBR_NO [회원번호]
	 *	- field : AUTH_NO [인증번호]
	 *	- field : RETN_TEL_NO [전화번호]
	 *	- field : OCPY_AUTH_REQ_CHNL_CD [점유인증요청채널코드]
	 *	- field : REQ_USER_ID [요청사용자ID]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : SUCCESS [성공여부(Boolean)]
	 * </pre>
	 */
	public IDataSet fCnfmOcpyAuth(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		Log log = getLog(onlineCtx);

		if ( StringUtils.isEmpty(requestData.getField("MBR_NO")) ) {
			throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("MBR_NO") });  // 필수입력항목 {0}이/가 누락되었습니다.
		}
		if ( StringUtils.isEmpty(requestData.getField("AUTH_NO")) ) {
			throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("AUTH_NO") });
		}
		if ( StringUtils.isEmpty(requestData.getField("RETN_TEL_NO")) ) {
			throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("TEL_NO") });
		}
		if ( StringUtils.isEmpty(requestData.getField("OCPY_AUTH_REQ_CHNL_CD")) ) {
			throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("OCPY_AUTH_REQ_CHNL_CD") });
		}
		if ( StringUtils.isEmpty(requestData.getField("REQ_USER_ID")) ) {
			throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("REQ_USER_ID") });
		}

		IDataSet reqData = (IDataSet) requestData.clone();
		reqData.putField("RETN_TEL_NO_ENPT", HpcUtils.encodeByAES(reqData.getField("RETN_TEL_NO"))); // 전화번호암호화
		reqData.putField("USER_NO", reqData.getField("REQ_USER_ID"));
		reqData.putField("OCPY_AUTH_CHK_MM", OCPY_AUTH_CHK_MM); // 점유인증확인시간 (분) // TO_DO 이유미 Constants로 변경
		String strAuthSuccYn = ""; // 인증성공여부
		int iAuthErrCnt = 0; // 인증오류횟수
		try {
			// 점유인증테이블 조회
			DSCOcpyAuthMgmt dTH_OCPY_AUTH_HST01 = (DSCOcpyAuthMgmt) lookupDataUnit(DSCOcpyAuthMgmt.class);
			IDataSet ocpyAuthHstDs = dTH_OCPY_AUTH_HST01.dSOcpyAuthNo(reqData, onlineCtx);

			if ( ocpyAuthHstDs.getRecordSet("RS_AUTH").getRecordCount() < 1 ) {
				throw new BizRuntimeException("HPC00275");    // 인증에 실패하였습니다.
			}

			// 점유인증확인 오류 횟수가 이미 초과일 경우 오류 Return
			iAuthErrCnt = Integer.parseInt(ocpyAuthHstDs.getRecordSet("RS_AUTH").get(0, "AUTH_ERR_CNT"));

			if ( iAuthErrCnt >= Integer.parseInt(OCPY_AUTH_ERR_CNT) ) {  // TO_DO 이유미 Constants로 변경
				throw new BizRuntimeException("HPC00602", new String[] { OCPY_AUTH_ERR_CNT });    // 인증 오류 횟수 ({0}회) 초과하였습니다. 
			}

			// 인증번호 Check
			if ( StringUtils.equals(ocpyAuthHstDs.getRecordSet("RS_AUTH").get(0, "AUTH_NO"), reqData.getField("AUTH_NO")) ) {
				strAuthSuccYn = "Y";
			} else {
				strAuthSuccYn = "N";
				iAuthErrCnt++;
			}

			// 점유인증 업데이트
			reqData.putField("AUTH_SUCC_YN", strAuthSuccYn);  // 인증성공여부
			reqData.putField("AUTH_ERR_CNT", iAuthErrCnt);    // 인증오류횟수
			reqData.putField("AUTH_NO_REQ_DTM", ocpyAuthHstDs.getRecordSet("RS_AUTH").get(0, "AUTH_NO_REQ_DTM"));    // 인증요청일시
			IDataSet ocpyAuthUpd = dTH_OCPY_AUTH_HST01.dUOcpyAuthSt(reqData, onlineCtx);
			if ( !(Boolean) ocpyAuthUpd.getObjectField(DmsConstants.IS_SUCCESS) ) {
				throw new BizRuntimeException("HPC00275");    // 인증에 실패하였습니다.
			}
			responseData.putField("AUTH_SUCC_YN", strAuthSuccYn);
			responseData.putField("AUTH_ERR_CNT", iAuthErrCnt);

		} catch ( BizRuntimeException e ) {
			throw e;
		}
		responseData.putField("SUCCESS", true);
		return responseData;
	}

}
