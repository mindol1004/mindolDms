package dms.sc.scbbase.biz;

import java.util.Map;

import fwk.common.CommonArea;
import fwk.constants.DmsConstants;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: PSCUserAutRolMgmt</li>
 * <li>설  명 : 사용자권한역할관리</li>
 * <li>작성일 : 2014-08-11 09:23:50</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class PSCUserAutRolMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PSCUserAutRolMgmt() {
		super();
	}

	/**
	 * 사용자권한역할목록조회
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-08-11 09:23:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : USER_NO [사용자번호]
	 *	- field : USER_NM [사용자명]
	 *	- field : AUTR_ROL_ID [권한ID]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_USER_ROL_LIST
	 *		- field : USER_NO [사용자번호]
	 *		- field : AUTR_ROL_ID [권한역할ID]
	 *		- field : AUTR_ROL_NM [권한역할명]
	 *		- field : APLY_STA_DT [적용시작일자]
	 *		- field : APLY_END_DT [적용종료일자]
	 *		- field : USE_YN [사용여부]
	 *		- field : USER_NM [사용자명]
	 *		- field : LGIN_ID [로그인아이디]
	 * </pre>
	 */
	public IDataSet pInqUserAutrRolLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCUserAutrRolMgmt fSCUserAutrRolMgmt = (FSCUserAutrRolMgmt) lookupFunctionUnit(FSCUserAutrRolMgmt.class);
			// 2. FM 호출
			responseData = fSCUserAutrRolMgmt.fInqUsrAutrRolLst(requestData, onlineCtx);
			// 3. 결과값 리턴
			responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.			
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

	/**
	 * 사용자권한역할저장
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_USER_ROL_LIST
	 *		- field : USER_NO [사용자번호]
	 *		- field : AUTR_ROL_ID [권한역할ID]
	 *		- field : AUTR_ROL_NM [권한역할명]
	 *		- field : APLY_STA_DT [적용시작일자]
	 *		- field : APLY_END_DT [작용종료일자]
	 *		- field : USE_YN [작용종료일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveUserAutrRol(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);
		try {
			// 1. FU lookup
			FSCUserAutrRolMgmt fSCUserAutrRolMgmt = (FSCUserAutrRolMgmt) lookupFunctionUnit(FSCUserAutrRolMgmt.class);
			IRecordSet rs = requestData.getRecordSet("RS_USER_ROL_LIST");
			IDataSet reqDS = (IDataSet) requestData.clone();
			IRecord record = null;
			for ( int i = 0 ; i < rs.getRecordCount() ; i++ ) {
				record = rs.getRecord(i);
				reqDS.putFieldMap(record);
				reqDS.putField("CHG_USER_NO", ca.getUserNo()); //변경사용자컬럼 추가
				if ( StringUtils.equals(DmsConstants.STATUS_INSERTED, record.get(DmsConstants.RECORD_STATUS)) ) { // INSERT
					fSCUserAutrRolMgmt.fRegUsrAutrRol(reqDS, onlineCtx);
				} else if ( StringUtils.equals(DmsConstants.STATUS_UPDATED, record.get(DmsConstants.RECORD_STATUS)) ) { // UPDATE
					fSCUserAutrRolMgmt.fUpdUsrAutrRol(reqDS, onlineCtx);
				} else if ( StringUtils.equals(DmsConstants.STATUS_DELETED, record.get(DmsConstants.RECORD_STATUS)) ) { // DELETE
					fSCUserAutrRolMgmt.fDelUsrAutrRol(reqDS, onlineCtx);
				}
			}
			// 4. 결과값 리턴
			responseData.setOkResultMessage("DMS00000", null); //정상 처리되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e);   //시스템 오류
		}
		return responseData;
	}

	/**
	 * 사용자권한역할복사
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_COPY_ROL
	 *		- field : USER_NO [필드1]
	 *		- field : USER_NO_CP [필드2]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pCpUserAutrRol(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);

		try {
			// 1. FU lookup
			FSCUserAutrRolMgmt fSCUserAutrRolMgmt = (FSCUserAutrRolMgmt) lookupFunctionUnit(FSCUserAutrRolMgmt.class);
			// 2. 입력 RS설정
			requestData.putFieldMap(requestData.getRecordSet("RS_COPY_ROL").getRecordMap(0));
			requestData.putField("REQ_USER_ID", ca.getUserNo());
			// 3. FM 호출
			responseData = fSCUserAutrRolMgmt.fCpUserAutrRol(requestData, onlineCtx);
			// 4. 결과값 리턴
			responseData.setOkResultMessage("DMS00000", null); //정상 처리되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;

	}

	/**
	 * 사용자데이터권한역할목록조회
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-08-11 09:23:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : USER_NO [사용자번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_USER_DATA_ROL_LIST
	 *		- field : AUTR_ROL_ID [권한역할ID]
	 *		- field : AUTR_ROL_NM [권한역할명]
	 *		- field : AUTR_SNO [권한일련번호]
	 *		- field : AUTR_OBJ_CO_CD [권한대상회사코드]
	 *		- field : AUTR_OBJ_CO_CD_NM [권한대상회사코드명]
	 *		- field : AUTR_OBJ_BRND_CD [권한대상브랜드코드]
	 *		- field : AUTR_OBJ_BRND_NM [권한대상브랜드코드명]
	 *		- field : AUTR_OBJ_MCHT_NO [권한대상가맹점번호]
	 *		- field : AUTR_OBJ_MCHT_NM [권한대상가맹점명]
	 * </pre>
	 */
	public IDataSet pInqUserDataRolLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCUserAutrRolMgmt fSCUserAutrRolMgmt = (FSCUserAutrRolMgmt) lookupFunctionUnit(FSCUserAutrRolMgmt.class);
			// 2. FM 호출
			responseData = fSCUserAutrRolMgmt.fInqUsrDataRolLst(requestData, onlineCtx);
			//responseData.putRecordSet("RS_USER_DATA_ROL_LIST", responseData.getRecordSet("rsUseDatarolList"));
			// 3. 결과값 리턴
			responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

	/**
	 * 사용자 접근가능한 브랜드 및 가맹점조회
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-08-11 09:23:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : FLAG [조회타입]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_AUTR_BRND
	 *		- field : BRND_CD [브랜드코드]
	 *		- field : BRND_NM [브랜드명]
	 *	- record : RS_AUTR_MCHT
	 *		- field : MCHT_NO [가맹점코드]
	 *		- field : MCHT_NM [가맹점명]
	 * </pre>
	 */
	public IDataSet pUsrBrndMchtLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCUserAutrRolMgmt fSCUserAutrRolMgmt = (FSCUserAutrRolMgmt) lookupFunctionUnit(FSCUserAutrRolMgmt.class);
			// 2. FM 호출
			responseData = fSCUserAutrRolMgmt.fInqUsrDataRolBrndMchtLst(requestData, onlineCtx);
			// 3. 결과값 리턴
			responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

}
