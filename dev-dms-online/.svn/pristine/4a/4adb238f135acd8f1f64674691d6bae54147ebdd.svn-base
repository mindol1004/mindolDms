package dms.sc.scbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.RecordHeader;
import nexcore.framework.core.data.RecordSet;
import nexcore.framework.core.exception.BizRuntimeException;

import org.apache.commons.lang.StringUtils;

import fwk.utils.HpcUtils;
import fwk.utils.PagenateUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: FSCUserMgmt</li>
 * <li>설  명 : 사용자관리</li>
 * <li>작성일 : 2014-07-22 17:07:18</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class FSCUserMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCUserMgmt() {
		super();
	}

	/**
	 * 시용자등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegUser(IDataSet requestData, IOnlineContext onlineCtx) {

		IDataSet responseData = new DataSet();
		IDataSet reqData = (IDataSet) requestData.clone();
		try {
			// 1. DU lookup
			DSCUserMgmt dTB_USER01 = (DSCUserMgmt) lookupDataUnit(DSCUserMgmt.class);

			DSCLginOp dTB_USER02 = (DSCLginOp) lookupDataUnit(DSCLginOp.class);
			IDataSet userNoDs = dTB_USER02.dSUserNo(requestData, onlineCtx);
			reqData.putField("USER_NO", userNoDs.getField("USER_NO"));
			// 2. Validation DM호출
			IDataSet valDS = dTB_USER01.dSUserChk(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) > 0 ) {
				throw new BizRuntimeException("DMS00003"); //중복입력 된 데이터가 존재합니다.
			}

			IDataSet valId = dTB_USER01.dSLgidChk(requestData, onlineCtx);
			if ( Integer.parseInt(valId.getField("LGIN_ID_CNT")) > 0 ) {
				throw new BizRuntimeException("DMS00060"); //이미 존재하는 로그인 ID입니다.
			}
			// 3. 초기 비밀번호 생성 : 2014-10-14 by 이유미
			//2015.03.10 임지후 (비밀번호 생성규칙 변경 : 로그인ID+1234 )  
			String newPwd = reqData.getField("LGIN_ID");
			newPwd = newPwd.concat("1234");
			reqData.putField("PWD", HpcUtils.encryptTextBySHA256(newPwd));

			// 4. 업무 DM호출
			responseData = dTB_USER01.dIUserReg(reqData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;

	}

	/**
	 * 사용자목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqUserLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet usrListRs = null;
		int usrTotalCnt = 0;

		try {
			// 1. DU lookup
			DSCUserMgmt dTB_USER01 = (DSCUserMgmt) lookupDataUnit(DSCUserMgmt.class);

			// 2. TOTAL COUNT DM호출
			dsCnt = dTB_USER01.dSUserCntInq(requestData, onlineCtx);
			usrTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);

			// 3. LISTDM호출
			responseData = dTB_USER01.dSUserListInq(reqDs, onlineCtx);
			usrListRs = responseData.getRecordSet("RS_USER_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(usrListRs, reqDs, usrTotalCnt);
		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;

	}

	/**
	 * 사용자수정
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdUser(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();

		IDataSet responseData1 = new DataSet();
		IDataSet responseData2 = new DataSet();

		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IDataSet reqDs2 = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone

		try {
			// 1. DU lookup
			DSCUserMgmt dTB_USER01 = (DSCUserMgmt) lookupDataUnit(DSCUserMgmt.class);
			DSCUserHisMgmt dTH_USER_HST01 = (DSCUserHisMgmt) lookupDataUnit(DSCUserHisMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dTB_USER01.dSUserChk(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004 "); //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 3. History 이력등록 DM호출
			responseData2 = dTH_USER_HST01.dIUserHisReg(reqDs2, onlineCtx);
			// 4. 사용자 정보 수정 DM호출
			responseData1 = dTB_USER01.dUUserUpd(reqDs, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;

	}

	/**
	 * 로그인ID중복체크
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fChkLoginID(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCUserMgmt dTB_USER01 = (DSCUserMgmt) lookupDataUnit(DSCUserMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dTB_USER01.dSLgidChk(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("LGIN_ID_CNT")) > 0 ) {
				throw new BizRuntimeException("DMS00060"); //이미 존재하는 로그인 ID입니다.
			}
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 비밀번호변경
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fChgPwd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet responseData2 = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();
		IDataSet reqDs2 = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		try {
			// 1. DU lookup
			DSCUserMgmt dTB_USER01 = (DSCUserMgmt) lookupDataUnit(DSCUserMgmt.class);
			DSCUserHisMgmt dTH_USER_HST01 = (DSCUserHisMgmt) lookupDataUnit(DSCUserHisMgmt.class);

			//2. 비밀번호 확인 DM호출
			IDataSet hpNoChk = dTB_USER01.dSUserListbyLgId(requestData, onlineCtx);
			IRecordSet userRs = hpNoChk.getRecordSet("RS_USER_LIST");

			if ( userRs.getRecordCount() == 1 ) {
				String hpNo = userRs.get(0, "HP_NO");
				//2-1.현재 PWD CHECK
				if ( !reqDs.getField("PWD_ENPT_ORG").equals(userRs.get(0, "PWD")) ) {
					throw new BizRuntimeException("DMS00058"); //현재 비밀번호를 확인해주세요.
				}
				//2-2.휴대폰번호 포함 CHECK
				if ( hpNo != null && hpNo.length() >= 8 ) {
					if ( reqDs.getField("PWD_NEW").indexOf(hpNo.substring(hpNo.length() - 4)) != -1 ) {
						throw new BizRuntimeException("DMS00059"); //비밀번호는 전화번호를 포함할 수 없습니다.
					}
				}
				// 3. History 이력등록 DM호출
				reqDs2.putField("USER_NO", userRs.get(0, "USER_NO"));
				responseData2 = dTH_USER_HST01.dIUserHisReg(reqDs2, onlineCtx);
				// 4. 업무 DM호출
				responseData = dTB_USER01.dUPwdChg(reqDs, onlineCtx);
			} else {
				throw new BizRuntimeException("DMS00010"); //사용자정보를 확인해주세요.
			}
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 내정보변경
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fChgMyInfo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet responseData2 = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();
		IDataSet reqDs2 = (IDataSet) requestData.clone();
		try {
			// 1. DU lookup
			DSCUserMgmt dTB_USER01 = (DSCUserMgmt) lookupDataUnit(DSCUserMgmt.class);
			DSCUserHisMgmt dTH_USER_HST01 = (DSCUserHisMgmt) lookupDataUnit(DSCUserHisMgmt.class);
			//2. 비밀번호 확인 DM호출
			IDataSet hpNoChk = dTB_USER01.dSUserListbyLgId(requestData, onlineCtx);
			IRecordSet userRs = hpNoChk.getRecordSet("RS_USER_LIST");

			if ( userRs.getRecordCount() == 1 ) {
				String hpNo = userRs.get(0, "HP_NO");
				//2-1.현재 PWD CHECK
				if ( !reqDs.getField("PWD_ENPT_ORG").equals(userRs.get(0, "PWD")) ) {
					throw new BizRuntimeException("DMS00058"); //현재 비밀번호를 확인해주세요.
				}
				/*
				 * //2-2.휴대폰번호 포함 CHECK if(hpNo!=null && hpNo.length()>=8){ if(reqDs.getField("PWD_NEW").indexOf(hpNo.substring(hpNo.length()-4)) != -1){ throw
				 * new BizRuntimeException("HPC00225"); //비밀번호는 전화번호를 포함할 수 없습니다. } }
				 *///화면에서 CHECK함.
				// 3. History 이력등록 DM호출
				reqDs2.putField("USER_NO", userRs.get(0, "USER_NO"));
				responseData2 = dTH_USER_HST01.dIUserHisReg(reqDs2, onlineCtx);
				// 4. 업무 DM호출
				responseData = dTB_USER01.dUChgMyInfo(reqDs, onlineCtx);
			} else {
				throw new BizRuntimeException("DMS00010"); //사용자정보를 확인해주세요.
			}
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 사용자조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqUser(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet usrListRs = null;
		int usrTotalCnt = 0;

		try {
			// 1. DU lookup
			DSCUserMgmt dTB_USER01 = (DSCUserMgmt) lookupDataUnit(DSCUserMgmt.class);

			// 2. LISTDM호출
			responseData = dTB_USER01.dSUserListbyLgId(reqDs, onlineCtx);
			usrListRs = responseData.getRecordSet("RS_USER_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(usrListRs, reqDs, usrTotalCnt);
		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;
	}

	/**
	 * 사용자암호컬럼조회
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2015-01-06 14:59:00
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqUserSecure(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet rsDataSet;
		IRecordSet tempRs;
		IRecordSet returnRs;
		IRecord tempRecord;
		IRecord tempRecord2;
		try {
			// 1. DU lookup
			DSCUserMgmt dTB_USER01 = (DSCUserMgmt) lookupDataUnit(DSCUserMgmt.class);

			// 2. LISTDM호출			
			if ( !StringUtils.isEmpty(requestData.getField("TARGET_COL_ID")) ) {
				rsDataSet = dTB_USER01.dInqUserSecure(requestData, onlineCtx);
				tempRs = rsDataSet.getRecordSet("RS_USER_INFO");
				if ( tempRs.getRecordCount() > 0 ) {
					tempRecord = tempRs.getRecord(0);
					returnRs = new RecordSet("RS_USER_INFO");
					returnRs.addHeader(new RecordHeader(requestData.getField("TARGET_COL_ID")));
					returnRs.newRecord(0);
					returnRs.getRecord(0).set(requestData.getField("TARGET_COL_ID"), tempRecord.get(requestData.getField("TARGET_COL_ID")));
					responseData.putRecordSet(returnRs);
				}
			} else {
				responseData = dTB_USER01.dInqUserSecure(requestData, onlineCtx);
			}

		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;
	}

}
