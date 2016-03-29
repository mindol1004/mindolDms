package dms.sc.scbbase.biz;

import fwk.utils.HpcUtils;
import org.apache.commons.lang.StringUtils;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.utils.PagenateUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: FSCAutrRolMgmt</li>
 * <li>설 명 : 권한역할관리</li>
 * <li>작성일 : 2014-07-21 16:03:17</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 * 
 * @author 심상준 (simair)
 */
public class FSCAutrRolMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCAutrRolMgmt() {
		super();
	}

	/**
	 * 권한역할목록조회
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqAutrRolLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone(); // 원거래의 DataSet의 훼손을 막기위한 Clone

		try {
			// 1. DU lookup
			DSCAutrRolMgmt dSCAutrRolMgmt = (DSCAutrRolMgmt) lookupDataUnit(DSCAutrRolMgmt.class);

			// 2. TOTAL COUNT DM호출
			IDataSet dsCnt = dSCAutrRolMgmt.dSAutrRolTotCnt(requestData, onlineCtx);
			int msgTotalCnt = dsCnt.getIntField("TOTAL_CNT");
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);

			// 3. LISTDM호출
			responseData = dSCAutrRolMgmt.dSAutrRolLstPaging(reqDs, onlineCtx);
			IRecordSet msgListRs = responseData.getRecordSet("RS_AUTR_ROL_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(msgListRs, reqDs, msgTotalCnt);
		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;
	}

	/**
	 * 권한역할등록
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegAutrRol(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();

		try {
			// 필수값 체크
			if ( StringUtils.isEmpty(requestData.getField("AUTR_ROL_ID")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("AUTR_ROL_ID") }); // 필수입력항목 {0}이/가 누락되었습니다.
			} else if ( StringUtils.isEmpty(requestData.getField("AUTR_ROL_NM")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("AUTR_ROL_NM") }); // 필수입력항목 {0}이/가 누락되었습니다.
			} else if ( StringUtils.isEmpty(requestData.getField("USE_YN")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("USE_YN") });      // 필수입력항목 {0}이/가 누락되었습니다.
			}

			// 1. DU lookup
			DSCAutrRolMgmt dSCAutrRolMgmt = (DSCAutrRolMgmt) lookupDataUnit(DSCAutrRolMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dSCAutrRolMgmt.dSAutrRolValidate(requestData, onlineCtx);
			if ( valDS.getIntField("ROW_CNT") > 0 ) {
				throw new BizRuntimeException("DMS00003"); //중복입력 된 데이터가 존재합니다.
			}
			// 3. 업무 DM호출
			responseData = dSCAutrRolMgmt.dIAutrRol(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 권한역할수정
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdAutrRol(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 필수값 체크
			if ( StringUtils.isEmpty(requestData.getField("AUTR_ROL_ID")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("AUTR_ROL_ID") }); // 필수입력항목 {0}이/가 누락되었습니다.
			} else if ( StringUtils.isEmpty(requestData.getField("AUTR_ROL_NM")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("AUTR_ROL_NM") }); // 필수입력항목 {0}이/가 누락되었습니다.
			} else if ( StringUtils.isEmpty(requestData.getField("USE_YN")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("USE_YN") });      // 필수입력항목 {0}이/가 누락되었습니다.
			}
			// 1. DU lookup
			DSCAutrRolMgmt dSCAutrRolMgmt = (DSCAutrRolMgmt) lookupDataUnit(DSCAutrRolMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dSCAutrRolMgmt.dSAutrRolValidate(requestData, onlineCtx);
			if ( valDS.getIntField("ROW_CNT") == 0 ) {
				throw new BizRuntimeException("DMS00004"); //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 3. 업무 DM호출
			responseData = dSCAutrRolMgmt.dUAutrRol(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 권한역할삭제
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fDelAutrRol(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 필수값 체크
			if ( StringUtils.isEmpty(requestData.getField("AUTR_ROL_ID")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("AUTR_ROL_ID") }); // 필수입력항목 {0}이/가 누락되었습니다.
			}
			// 1. DU lookup
			DSCAutrRolMgmt dSCAutrRolMgmt = (DSCAutrRolMgmt) lookupDataUnit(DSCAutrRolMgmt.class);
			DSCFrmMgmt dTB_FRM01 = (DSCFrmMgmt) lookupDataUnit(DSCFrmMgmt.class);
			DSCDataMgmt dTB_Data01 = (DSCDataMgmt) lookupDataUnit(DSCDataMgmt.class);
			// 2-1. Validation DM호출
			IDataSet valDS = dSCAutrRolMgmt.dSAutrRolValidate(requestData, onlineCtx);
			if ( valDS.getIntField("ROW_CNT") == 0 ) {
				throw new BizRuntimeException("DMS00004"); //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 2-2. Validation DM호출(사용자권한역할)
			IDataSet valDS2 = dSCAutrRolMgmt.dSAutrRolValidateUserAutr(requestData, onlineCtx);
			if ( Integer.parseInt(valDS2.getField("ROW_CNT")) != 0 ) {
				throw new BizRuntimeException("DMS00005", new String[] { HpcUtils.getLangMsg("USER_AUTR_ROL_MGMT") }); //{0}에 데이터가 존재합니다.\n\n해당 데이터 삭제 후 재작업 하십시오.
			}
			// 2-3. Validation DM호출(화면권한역할)
			IDataSet valDS3 = dTB_FRM01.dSFrmValidateFrmAutr(requestData, onlineCtx);
			if ( Integer.parseInt(valDS3.getField("ROW_CNT")) != 0 ) {
				throw new BizRuntimeException("DMS00005", new String[] { HpcUtils.getLangMsg("FRM_AUTR_ROL_MGMT") }); //{0}에 데이터가 존재합니다.\n\n해당 데이터 삭제 후 재작업 하십시오.
			}
			/*
			// 2-3. Validation DM호출(데이터접근 브랜드/가맹점리스트)
			IDataSet valDS4 = dTB_Data01.dSDataLst(requestData, onlineCtx);
			if ( valDS4.getRecordSet("RS_DATA_AUTR_ROL").getRecordCount()>0 ) {
				throw new BizRuntimeException("HPC00708"); 
				//데이터권한역할관리에 데이터가 존재합니다. 해당 데이터 삭제 후 재작업 하십시오.
			}			
			*/
			// 3. 업무 DM호출
			responseData = dSCAutrRolMgmt.dDAutrRol(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

}
