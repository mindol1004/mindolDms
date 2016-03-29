package dms.sc.scbbase.biz;

import java.util.Map;

import org.apache.commons.logging.Log;

import fwk.utils.PagenateUtils;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.BizRuntimeException;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: FSCFrmAutrRolMgmt</li>
 * <li>설 명 : 화면권한역할관리</li>
 * <li>작성일 : 2014-07-21 15:38:14</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 * 
 * @author 심상준 (simair)
 */
public class FSCFrmAutrRolMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCFrmAutrRolMgmt() {
		super();
	}

	/**
	 * 화면권한역할목록조회
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqFrmAutrRolLst(IDataSet requestData, IOnlineContext onlineCtx) {

		IDataSet responseData = new DataSet();
		IDataSet responseDataY = new DataSet();
		IDataSet responseDataN = new DataSet();  //

		IDataSet dsCntY = new DataSet();
		//IDataSet dsCntN = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet frmRolListY = null;
		int frmRolTotalCntY = 0;
		int frmRolTotalCntN = 0;

		try {
			// 1. DU lookup
			DSCFrmAutrRolMgmt dTB_FRM_AUTR_ROL01 = (DSCFrmAutrRolMgmt) lookupDataUnit(DSCFrmAutrRolMgmt.class);

			// 2. TOTAL COUNT DM호출
			dsCntY = dTB_FRM_AUTR_ROL01.dSFrmRolListCntbyYes(requestData, onlineCtx);
			frmRolTotalCntY = Integer.parseInt(dsCntY.getField("TOTAL_CNT_Y"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);

			// 3. LISTDM호출
			responseDataY = dTB_FRM_AUTR_ROL01.dSFrmRolListbyYes(reqDs, onlineCtx);
			responseDataN = dTB_FRM_AUTR_ROL01.dSFrmRolListbyNo(reqDs, onlineCtx); //
			/*
			 * 
			 * frmRolTotalCntN = Integer.parseInt(dsCntY.getField("TOTAL_CNT_N"));
			 * 
			 * if ( frmRolTotalCntN ==0 ){ responseDataN = dTB_FRM_AUTR_ROL01.dTB_FRM_AUTR_ROL_S005(requestData, onlineCtx); }else{
			 * 
			 * }
			 */

			for ( int i = 0 ; i < 2 ; i++ ) {
				if ( i == 0 ) {
					responseData.putRecordSet("RS_FRM_ROL_LIST_Y", responseDataY.getRecordSet("RS_FRM_ROL_LIST_Y"));
				} else if ( i == 1 ) {

					responseData.putRecordSet("RS_FRM_ROL_LIST_N", responseDataN.getRecordSet("RS_FRM_ROL_LIST_N"));
				}
			}

		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;
	}

	/**
	 * 화면권한역할등록
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegFrmAutrRol(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCFrmAutrRolMgmt dTB_FRM_AUTR_ROL01 = (DSCFrmAutrRolMgmt) lookupDataUnit(DSCFrmAutrRolMgmt.class);
			// 2. DM호출
			responseData = dTB_FRM_AUTR_ROL01.dUFrmRolListReg(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 화면권한역할수정
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdFrmAutrRol(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();

		try {
			// 1. DU lookup
			DSCFrmAutrRolMgmt dTB_FRM_AUTR_ROL01 = (DSCFrmAutrRolMgmt) lookupDataUnit(DSCFrmAutrRolMgmt.class);
			// 2. DM호출
			responseData = dTB_FRM_AUTR_ROL01.dUFrmRolListUpd(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 화면권한역할삭제
	 * 
	 * @author
	 * 
	 * @param requestData
	 *            요청정보 DataSet 객체
	 * @param onlineCtx
	 *            요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fDelFrmAutrRol(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCFrmAutrRolMgmt dTB_FRM_AUTR_ROL01 = (DSCFrmAutrRolMgmt) lookupDataUnit(DSCFrmAutrRolMgmt.class);
			// 2. DM호출
			responseData = dTB_FRM_AUTR_ROL01.dDFrmRolListDel(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 화면권한세부역할삭제
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fDelFrmAutrRoldtl(IDataSet requestData, IOnlineContext onlineCtx) {

		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCFrmAutrRolMgmt dTB_FRM_AUTR_ROL01 = (DSCFrmAutrRolMgmt) lookupDataUnit(DSCFrmAutrRolMgmt.class);
			// 2. DM호출
			responseData = dTB_FRM_AUTR_ROL01.dDFrmRolListDtlDel(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 *
	 *
	 * @author 심상준 (simair)
	 * @since 2014-12-12 13:29:19
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqFrmAutrRol(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone(); // 원거래의 DataSet의 훼손을 막기위한 Clone

		try {
			// 1. DU lookup
			DSCFrmAutrRolMgmt dTB_FRM_AUTR_ROL01 = (DSCFrmAutrRolMgmt) lookupDataUnit(DSCFrmAutrRolMgmt.class);
			// 2. DM호출
			responseData = dTB_FRM_AUTR_ROL01.dSFrmRolbyUser(reqDs, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;
	}

	/**
	 *
	 *
	 * @author 심상준 (simair)
	 * @since 2014-12-16 15:01:31
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqMenuRolLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone(); // 원거래의 DataSet의 훼손을 막기위한 Clone

		try {
			// 1. DU lookup
			DSCFrmAutrRolMgmt dTB_FRM_AUTR_ROL01 = (DSCFrmAutrRolMgmt) lookupDataUnit(DSCFrmAutrRolMgmt.class);
			// 2. DM호출
			responseData = dTB_FRM_AUTR_ROL01.dSMenuRolLst(reqDs, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;
	}

	/**
	 * 권한있는메뉴와 권한없는메뉴리스트를 조회한다.
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2015-03-04 16:43:00
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqAutrRolMenuLst(IDataSet requestData, IOnlineContext onlineCtx) {

		IDataSet responseData = new DataSet();
		IDataSet responseDataY = new DataSet();
		IDataSet responseDataN = new DataSet();  
		try {
			// 1. DU lookup
			DSCFrmAutrRolMgmt dTB_FRM_AUTR_ROL01 = (DSCFrmAutrRolMgmt) lookupDataUnit(DSCFrmAutrRolMgmt.class);

			// 2. LISTDM호출
			responseDataY = dTB_FRM_AUTR_ROL01.dSAutrRolMenubyYes(requestData, onlineCtx);
			responseDataN = dTB_FRM_AUTR_ROL01.dSAutrRolMenuPopup(requestData, onlineCtx); 
			
			responseData.putRecordSet("RS_FRM_ROL_LIST_Y", responseDataY.getRecordSet("RS_FRM_ROL_LIST_Y"));
			responseData.putRecordSet("RS_FRM_ROL_LIST_P", responseDataN.getRecordSet("RS_FRM_ROL_LIST_P"));
				
		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;
	}

	/**
	 * 화면상세권한조회
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2015-03-06 13:53:40
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqFrmDtlAutr(IDataSet requestData, IOnlineContext onlineCtx) {

		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCFrmAutrRolMgmt dTB_FRM_AUTR_ROL01 = (DSCFrmAutrRolMgmt) lookupDataUnit(DSCFrmAutrRolMgmt.class);

			// 2. LISTDM호출
			responseData = dTB_FRM_AUTR_ROL01.dSFrmDtlAutr(requestData, onlineCtx);
				
		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;
	}

}
