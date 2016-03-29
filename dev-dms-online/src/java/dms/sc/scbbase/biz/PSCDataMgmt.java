package dms.sc.scbbase.biz;

import fwk.common.CommonArea;

import java.util.Map;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.BizRuntimeException;
import org.apache.commons.lang.StringUtils;
import fwk.common.CommonArea;
import fwk.constants.DmsConstants;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: [PU]데이터접근권한관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-12-11 13:08:00</li>
 * <li>작성자 : 임지후 (jihooyim)</li>
 * </ul>
 *
 * @author 임지후 (jihooyim)
 */
public class PSCDataMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PSCDataMgmt() {
		super();
	}

	/**
	 * 데이터권한역할 목록을 조회한다.
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-12-11 13:08:00
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : AUTR_ROL_ID [권한역할ID]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_DATA_AUTR_ROL
	 *		- field : AUTR_ROL_ID [권한역할ID]
	 *		- field : AUTR_SNO [권한일련번호]
	 *		- field : AUTR_OBJ_CO_CD [권한대상회사코드]
	 *		- field : AUTR_OBJ_CO_CD_NM [권한대상회사코드명]
	 *		- field : AUTR_OBJ_BRND_CD [권한대상브랜드코드]
	 *		- field : AUTR_OBJ_BRND_NM [권한대상브랜드코드명]
	 *		- field : AUTR_OBJ_MCHT_NO [권한대상가맹점번호]
	 *		- field : AUTR_OBJ_MCHT_NM [권한대상가맹점명]
	 * </pre>
	 */
	public IDataSet pInqDataLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCDataMgmt fSCDataMgmt = (FSCDataMgmt) lookupFunctionUnit(FSCDataMgmt.class);
			// 2. FM 호출
			responseData = fSCDataMgmt.fInqDataLst(requestData, onlineCtx);
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
	 *
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-12-11 13:08:00
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_DATA_AUTR_ROL
	 *		- field : AUTR_ROL_ID [권한역할ID]
	 *		- field : AUTR_OBJ_CO_CD [권한대상회사코드]
	 *		- field : AUTR_OBJ_BRND_CD [권한대상브랜드코드]
	 *		- field : AUTR_OBJ_MCHT_NO [권한대상가맹점번호]
	 *		- field : AUTR_SNO [권한일련번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveDataLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecord record = null;
		CommonArea ca = getCommonArea(onlineCtx);
		try {
			// 1. FU lookup
			FSCDataMgmt fSCDataMgmt = (FSCDataMgmt) lookupFunctionUnit(FSCDataMgmt.class);

			// 2. 입력 RS설정
			IRecordSet rs = requestData.getRecordSet("RS_DATA_AUTR_ROL");
			int reqCnt = rs.getRecordCount();
			IDataSet reqDataSet = new DataSet();
			reqDataSet.putFieldMap(requestData.getFieldMap());
			reqDataSet.putField("userNo", ca.getUserNo());

			for ( int i = 0 ; i < reqCnt ; i++ ) {
				record = rs.getRecord(i);
				reqDataSet.putFieldMap(record);
				// 3. 레코드셋 상태에 따른 분기
				if ( StringUtils.equals(DmsConstants.STATUS_INSERTED, record.get(DmsConstants.RECORD_STATUS)) ) { // INSERT
					fSCDataMgmt.fRegDataLst(reqDataSet, onlineCtx);
				} else if ( StringUtils.equals(DmsConstants.STATUS_UPDATED, record.get(DmsConstants.RECORD_STATUS)) ) { // UPDATE
					fSCDataMgmt.fUpdDataLst(reqDataSet, onlineCtx);
				} else if ( StringUtils.equals(DmsConstants.STATUS_DELETED, record.get(DmsConstants.RECORD_STATUS)) ) { // DELETE
					fSCDataMgmt.fDelDataLst(reqDataSet, onlineCtx);
				}
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
