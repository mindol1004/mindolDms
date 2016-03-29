package dms.sc.scbbase.biz;

import fwk.utils.HpcUtils;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;

import org.apache.commons.lang.StringUtils;

import fwk.common.CommonArea;
import fwk.constants.DmsConstants;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: PSCAutrRolMgmt</li>
 * <li>설 명 : 권한역할관리</li>
 * <li>작성일 : 2014-07-21 16:02:10</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 * 
 * @author 심상준 (simair)
 */
public class PSCAutrRolMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PSCAutrRolMgmt() {
		super();
	}

	/**
	 * 권한역할목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : AUTR_ROL_ID [권한역할ID]
	 *	- field : AUTR_ROL_NM [권한역할명]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_AUTR_ROL_LIST
	 *		- field : AUTR_ROL_ID [권한역할ID]
	 *		- field : AUTR_ROL_NM [권한역할명]
	 *		- field : USE_YN [사용여부]
	 * </pre>
	 */
	public IDataSet pInqAutrRolLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();

		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// TODO 김석영 : 개발 완료후 필요없는 주석 및 샘플 삭제
		//
		// 페이징 변수명 : page(조회페이지I/O), page_size(페이지당건수I/O), total_record_cnt(총건수O)
		//
		// 공통코드 존재 체크 
		// HpcCode cmCd = HpcUtils.getCode("SCC999", "10");
		// if (cmCd == null) {
		//     throw new BizRuntimeException("HPC00235", new String[] { HpcUtils.getLangMsg("[review]SCC911.10") }); // 입력항목 {0}이/가 유효하지 않습니다.
		// }
		//
		// 암복호화
		// String encodeStr = HpcUtils.encodeByAES("평문");
		// String decodeStr = HpcUtils.decodeByAES("암호문");
		// String pwd = HpcUtils.encryptTextBySHA256("비밀번호평문");
		// 
		// 마스킹
		// String accountNo = HpcUtils.maskingAccountNo("계좌번호");
		// String detailAddr = HpcUtils.maskingAddress("상세주소");
		// String cardNo = HpcUtils.maskingCardNo("카드번호");
		// String email = HpcUtils.maskingEmail("이메일");
		// String name = HpcUtils.maskingName("이름");
		// String telNo = HpcUtils.maskingTelNo("전화번호");
		// 
		/////////////////////////////////////////////////////////////////////////////////////////////////////

		try {
			// FM 호출
			FSCAutrRolMgmt fSCAutrRolMgmt = (FSCAutrRolMgmt) lookupFunctionUnit(FSCAutrRolMgmt.class);
			responseData = fSCAutrRolMgmt.fInqAutrRolLst(requestData, onlineCtx);

			// 샘플 : SC.FM호출
			// responseData = callSharedBizComponentByDirect("sc.SCSBase", "fInqSeonDup", requestData, onlineCtx);         // 트랜잭션 연동
			// responseData = callSharedBizComponentByRequiresNew("sc.SCSBase", "fInqSeonDup", requestData, onlineCtx);    // 트랜잭션 분리

			// 결과값 리턴
			responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류
		}
		return responseData;
	}

	/**
	 * 권한역할저장
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_AUTR_ROL_LIST
	 *		- field : AUTR_ROL_ID [권한역할ID]
	 *		- field : AUTR_ROL_NM [권한역할명]
	 *		- field : USE_YN [사용여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveAutrRol(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);

		try {
			// 필수값 체크
			if ( StringUtils.isEmpty(requestData.getRecordSet("RS_AUTR_ROL_LIST").get(0, "AUTR_ROL_ID")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("AUTR_ROL_ID") }); // 필수입력항목 {0}이/가 누락되었습니다.
			}

			// RecordSet -> DataSet에 셋팅
			requestData.putFieldMap(requestData.getRecordSet("RS_AUTR_ROL_LIST").getRecordMap(0));
			requestData.putField("USER_NO", ca.getUserNo());

			// FM lookup
			FSCAutrRolMgmt fSCAutrRolMgmt = (FSCAutrRolMgmt) lookupFunctionUnit(FSCAutrRolMgmt.class);

			// 단건 : 레코드셋 상태에 따른 분기
			if ( StringUtils.equals("I", ca.getTrnPtrnDvcd()) ) { // INSERT
				fSCAutrRolMgmt.fRegAutrRol(requestData, onlineCtx);
			} else if ( StringUtils.equals("U", ca.getTrnPtrnDvcd()) ) { // UPDATE
				fSCAutrRolMgmt.fUpdAutrRol(requestData, onlineCtx);
			} else if ( StringUtils.equals("D", ca.getTrnPtrnDvcd()) ) { // DELETE
				fSCAutrRolMgmt.fDelAutrRol(requestData, onlineCtx);
			}

			// 다건 샘플
			//			IRecordSet rs = requestData.getRecordSet("RS_AUTR_ROL_LIST");
			//			IDataSet reqDS = (IDataSet) requestData.clone();
			//            IRecord record = null;
			//            for (int i = 0; i < rs.getRecordCount(); i++) {
			//                record = rs.getRecord(i);
			//                reqDS.putFieldMap(record);
			//                if (StringUtils.equals(HpcConstants.STATUS_INSERTED, record.get(HpcConstants.RECORD_STATUS))) { // INSERT
			//                    fSCAutrRolMgmt.fRegAutrRol(reqDS, onlineCtx);
			//                } else if (StringUtils.equals(HpcConstants.STATUS_UPDATED, record.get(HpcConstants.RECORD_STATUS))) { // UPDATE
			//                    fSCAutrRolMgmt.fUpdAutrRol(reqDS, onlineCtx);
			//                } else if (StringUtils.equals(HpcConstants.STATUS_DELETED, record.get(HpcConstants.RECORD_STATUS))) { // DELETE
			//                    fSCAutrRolMgmt.fDelAutrRol(reqDS, onlineCtx);
			//                }
			//            }

			// 결과값 리턴
			responseData.setOkResultMessage("DMS00000", null); // 정상 처리되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류
		}
		return responseData;
	}

	/**
	 * 권한역할목록조회(백오피스)
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : AUTR_ROL_ID [권한역할ID]
	 *	- field : AUTR_ROL_NM [권한역할명]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_AUTR_ROL_LIST
	 *		- field : AUTR_ROL_ID [권한역할ID]
	 *		- field : AUTR_ROL_NM [권한역할명]
	 *		- field : USE_YN [사용여부]
	 * </pre>
	 */
	public IDataSet pInqAutrRolLstBkof(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			responseData = callService("PSCAutrRolMgmt_pInqAutrRolLst", requestData, onlineCtx);

			// 결과값 리턴
			responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류
		}
		return responseData;
	}

	/**
	 * 권한역할저장(백오피스)
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_AUTR_ROL_LIST
	 *		- field : AUTR_ROL_ID [권한역할ID]
	 *		- field : AUTR_ROL_NM [권한역할명]
	 *		- field : USE_YN [사용여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveAutrRolBkof(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 필수값 체크
			if ( StringUtils.isEmpty(requestData.getRecordSet("RS_AUTR_ROL_LIST").get(0, "AUTR_ROL_ID")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("AUTR_ROL_ID") }); // 필수입력항목 {0}이/가 누락되었습니다.
			}
			responseData = callService("PSCAutrRolMgmt_pSaveAutrRol", requestData, onlineCtx);
			// 결과값 리턴
			responseData.setOkResultMessage("DMS00000", null); // 정상 처리되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
			// Exception없이 응답코드를 외부로 내보내고 싶은 경우
			// responseData.putField("rpsCd", e.getMessageId().substring(2,4));
			// responseData.putField("rpsDtlCd", e.getMessageId().substring(4));
			// responseData.putField("rpsMsgCtt", e.getMessage());
			// return responseData;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류
		}
		return responseData;
	}

}
