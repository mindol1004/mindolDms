package dms.bi.bibbase.biz;

import fwk.common.CommonArea;
import fwk.utils.HpcUtils;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.BaseUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/기준정보</li>
 * <li>단위업무명: 거래처관리 </li>
 * <li>설  명 : <pre>거래처관리</pre></li>
 * <li>작성일 : 2015-07-02 17:10:52</li>
 * <li>작성자 : 이영진 (newnofixing)</li>
 * </ul>
 *
 * @author 이영진 (newnofixing)
 */
public class PBIDealCoMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PBIDealCoMgmt(){
		super();
	}

	/**
	 * <pre>거래처목록조회</pre>
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-07-02 17:10:52
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : DEALCO_NM [거래처명]
	 *	- field : DEALCO_CL_1 [거래처구분]
	 *	- field : DEALCO_ST_CD [거래처상태코드]
	 *	- field : DEALCO_BLICENS_NO [사업자번호]
	 *	- field : DEALCO_CD [거래처코드]
	 *	- field : AFFIL_AGN [소속대리점]
	 *	- field : AGN_ORG_CD [대리점조직코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_DEALCO_LIST
	 *		- field : DEALCO_CD [거래처코드]
	 *		- field : DEALCO_NM [거래처명]
	 *		- field : DEALCO_CL_1 [거래처구분1]
	 *		- field : DEALCO_CL_2 [거래처구분2]
	 *		- field : AGN_ORG_CD [대리점조직코드]
	 *		- field : ORG_NM [대리점조직명]
	 *		- field : DEALCO_BLICENS_NO [사업자번호]
	 *		- field : DEALCO_REPVE_NM [대표자명]
	 *		- field : BIZTYP [업태]
	 *		- field : EVNT [종목]
	 *		- field : DEALCO_ZIPCD [우편번호]
	 *		- field : DEALCO_ADDR [주소]
	 *		- field : DEALCO_MBLPHON_NO [이동전화번호]
	 *		- field : DEALCO_TEL_NO [전화번호]
	 *		- field : DEALCO_EMAIL_ADDR [이메일]
	 *		- field : DEALCO_FAX_NO [FAX]
	 *		- field : DEALCO_ST_CD [거래처상태코드]
	 *		- field : DEAL_STA_DT [거래개시일]
	 *		- field : DEAL_END_DT [거래종료일]
	 *		- field : CHRGR_NM [담당자명]
	 *		- field : CHRGR_MBLPHON_NO [담당자이동전화]
	 *		- field : EFF_STA_DT [유효시작일자]
	 *		- field : EFF_END_DT [유효종료일자]
	 *		- field : DEALCO_RMK [비고]
	 *		- field : UKEY_AGN_CD [UKEY대리점코드]
	 *		- field : UKEY_SUB_CD [UKEY서브코드]
	 *		- field : UKEY_CHNL_CD [UKEY채널코드]
	 *		- field : AFFIL_AGN [소속대리점]
	 *		- field : AFFIL_AGN_NM [소속대리점명]
	 *		- field : HIGHR_DEALCO_CD [상위거래처]
	 *		- field : DEL_YN [삭제여부]
	 *		- field : FS_REG_USER_ID [최초등록사용자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경사용자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 *		- field : DEALCO_EMAIL_ADDR_ENPT [이메일암호화]
	 *		- field : DEALCO_REPVE_NM_ENPT [대표자명암호화]
	 *		- field : DEALCO_MBLPHON_NO_ENPT [대표자핸드폰암호화]
	 *		- field : DEALCO_TEL_NO_ENPT [대표전화암호화]
	 *		- field : DEALCO_FAX_NO_ENPT [대표팩스번호암호화]
	 *		- field : DEALCO_ADDR_ENPT [주소암호화]
	 *		- field : CHRGR_MBLPHON_NO_ENPT [담당자핸드폰암호화]
	 *		- field : CHRGR_NM_ENPT [담당자명암호화]
	 *		- field : BANK_CD [은행코드]
	 *		- field : DEALCO_ACCO_NO [거래처 계좌번호]
	 *		- field : DEALCO_ACCO_NO_ENPT [거래처 계좌번호 암호화]
	 *		- field : DEALCO_FISCL_ST_CD [거래처 회계 상태코드]
	 * </pre>
	 */
	public IDataSet pInqDealCoLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FBIDealCoMgmt fBIDealCoMgmt = (FBIDealCoMgmt) lookupFunctionUnit(FBIDealCoMgmt.class);
			// 2. FM 호출
			responseData = fBIDealCoMgmt.fInqDealCoLst(requestData, onlineCtx);
//			if (!"L".equals(BaseUtils.getRuntimeMode())) {
//			    callSharedBizComponentByDirect("nr.NRSXMBase", "fInqVendorAtERP", requestData, onlineCtx);
//			}
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 3. 결과값 리턴
		responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
		return responseData;
	}

	/**
	 * <pre>거래처저장</pre>
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-07-02 17:10:52
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_DEALCO_LIST
	 *		- field : DEALCO_CD [거래처코드]
	 *		- field : DEALCO_NM [거래처명]
	 *		- field : DEALCO_CL_1 [거래처구분1]
	 *		- field : DEALCO_CL_2 [거래처구분2]
	 *		- field : AGN_ORG_CD [대리점조직코드]
	 *		- field : DEALCO_BLICENS_NO [사업자번호]
	 *		- field : DEALCO_REPVE_NM [대표자명]
	 *		- field : BIZTYP [업태]
	 *		- field : EVNT [종목]
	 *		- field : DEALCO_ZIPCD [우편번호]
	 *		- field : DEALCO_ADDR [주소]
	 *		- field : DEALCO_MBLPHON_NO [이동전화번호]
	 *		- field : DEALCO_TEL_NO [전화번호]
	 *		- field : DEALCO_EMAIL_ADDR [이메일]
	 *		- field : DEALCO_FAX_NO [FAX]
	 *		- field : DEALCO_ST_CD [거래처상태코드]
	 *		- field : DEAL_STA_DT [거래개시일]
	 *		- field : DEAL_END_DT [거래종료일]
	 *		- field : CHRGR_NM [담당자명]
	 *		- field : CHRGR_MBLPHON_NO [담당자이동전화]
	 *		- field : EFF_STA_DT [유효시작일자]
	 *		- field : EFF_END_DT [유효종료일자]
	 *		- field : DEALCO_RMK [비고]
	 *		- field : UKEY_SUB_CD [UKEY서브코드]
	 *		- field : UKEY_CHNL_CD [UKEY채널코드]
	 *		- field : AFFIL_AGN [소속대리점]
	 *		- field : AFFIL_AGN_NM [소속대리점명]
	 *		- field : HIGHR_DEALCO_CD [상위거래처]
	 *		- field : DEL_YN [삭제여부]
	 *		- field : FS_REG_USER_ID [최초등록사용자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경사용자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 *		- field : DEALCO_EMAIL_ADDR_ENPT [이메일암호화]
	 *		- field : DEALCO_REPVE_NM_ENPT [대표자명암호화]
	 *		- field : DEALCO_MBLPHON_NO_ENPT [대표자핸드폰암호화]
	 *		- field : DEALCO_TEL_NO_ENPT [대표전화암호화]
	 *		- field : DEALCO_FAX_NO_ENPT [대표팩스번호암호화]
	 *		- field : DEALCO_ADDR_ENPT [주소암호화]
	 *		- field : CHRGR_MBLPHON_NO_ENPT [담당자핸드폰암호화]
	 *		- field : CHRGR_NM_ENPT [담당자명암호화]
	 *		- field : BANK_CD [은행코드]
	 *		- field : DEALCO_ACCO_NO [거래처 계좌번호]
	 *		- field : DEALCO_ACCO_NO_ENPT [거래처 계좌번호 암호화]
	 *		- field : DEALCO_FISCL_ST_CD [거래처회계상태코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveDealCo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);

		try {
			// 1. FU lookup
			FBIDealCoMgmt fBIDealCoMgmt = (FBIDealCoMgmt) lookupFunctionUnit(FBIDealCoMgmt.class);
			// 2. 입력 RS설정
			requestData.putFieldMap(requestData.getRecordSet("RS_DEALCO_LIST").getRecordMap(0));
			requestData.putField("USERNO", ca.getUserNo());
			// 3. 레코드셋 상태에 따른 분기
			requestData.putField("DEALCO_REPVE_NM_ENPT", HpcUtils.encodeByAES(requestData.getField("DEALCO_REPVE_NM")));
            requestData.putField("DEALCO_REPVE_NM", HpcUtils.maskingName(requestData.getField("DEALCO_REPVE_NM")));
            requestData.putField("DEALCO_ADDR_ENPT", HpcUtils.encodeByAES(requestData.getField("DEALCO_ADDR")));
            requestData.putField("DEALCO_ADDR", HpcUtils.maskingAddress(requestData.getField("DEALCO_ADDR")));
            requestData.putField("DEALCO_MBLPHON_NO_ENPT", HpcUtils.encodeByAES(requestData.getField("DEALCO_MBLPHON_NO")));
            requestData.putField("DEALCO_MBLPHON_NO", HpcUtils.maskingTelNo(requestData.getField("DEALCO_MBLPHON_NO")));
            requestData.putField("DEALCO_TEL_NO_ENPT", HpcUtils.encodeByAES(requestData.getField("DEALCO_TEL_NO")));
            requestData.putField("DEALCO_TEL_NO", HpcUtils.maskingTelNo(requestData.getField("DEALCO_TEL_NO")));
            requestData.putField("DEALCO_EMAIL_ADDR_ENPT", HpcUtils.encodeByAES(requestData.getField("DEALCO_EMAIL_ADDR")));
            requestData.putField("DEALCO_EMAIL_ADDR", HpcUtils.maskingEmail(requestData.getField("DEALCO_EMAIL_ADDR")));
            requestData.putField("DEALCO_FAX_NO_ENPT", HpcUtils.encodeByAES(requestData.getField("DEALCO_FAX_NO")));
            requestData.putField("DEALCO_FAX_NO", HpcUtils.maskingTelNo(requestData.getField("DEALCO_FAX_NO")));
            requestData.putField("CHRGR_NM_ENPT", HpcUtils.encodeByAES(requestData.getField("CHRGR_NM")));
            requestData.putField("CHRGR_NM", HpcUtils.maskingName(requestData.getField("CHRGR_NM")));
            requestData.putField("CHRGR_MBLPHON_NO_ENPT", HpcUtils.encodeByAES(requestData.getField("CHRGR_MBLPHON_NO")));
            requestData.putField("CHRGR_MBLPHON_NO", HpcUtils.maskingTelNo(requestData.getField("CHRGR_MBLPHON_NO")));
            requestData.putField("DEALCO_ACCO_NO_ENPT", HpcUtils.encodeByAES(requestData.getField("DEALCO_ACCO_NO")));
            requestData.putField("DEALCO_ACCO_NO", HpcUtils.maskingAccountNo(requestData.getField("DEALCO_ACCO_NO")));
			if ( "I".equals(ca.getTrnPtrnDvcd()) ) { // INSERT
				fBIDealCoMgmt.fRegDealCo(requestData, onlineCtx);
			} else if ( "U".equals(ca.getTrnPtrnDvcd()) ) { // UPDATE
				fBIDealCoMgmt.fUpdDealCo(requestData, onlineCtx);
			}
			// 4. 결과값 리턴
			responseData.setOkResultMessage("DMS00000", null); //정상 처리되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}
  
}
