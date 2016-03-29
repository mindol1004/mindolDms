package dms.bi.bibbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.utils.HpcUtils;
import fwk.utils.PagenateUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/기준정보</li>
 * <li>단위업무명: [FU]단말기모델관리</li>
 * <li>설  명 : <pre>단말기모델관리</pre></li>
 * <li>작성일 : 2015-06-29 10:55:57</li>
 * <li>작성자 : 개발자 (developer)</li>
 * </ul>
 *
 * @author 개발자 (developer)
 */
public class FBIEqpMdlMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FBIEqpMdlMgmt(){
		super();
	}

	/**
	 * <pre>단말기모델조회</pre>
	 *
	 * @author 개발자 (developer)
	 * @since 2015-06-29 10:55:57
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_MDL_LIST
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_CL_CD [단말기종류코드]
	 *		- field : NW_MTHD_CD [네트웍방식]
	 *		- field : MFACT_CD [제조사코드]
	 *		- field : FST_RGST_DT [출시일]
	 *		- field : CNSG_DT [단종일]
	 *		- field : DEL_YN [삭제여부]
	 *		- field : FS_REG_USER_ID [최초등록사용자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경사용자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 *		- field : LAUNC_DT [출시일자]
	 *		- field : PROD_CL [상품구분]
	 *		- field : COMC [통신방식]
	 *		- field : SLFCO_CL [자사구분]
	 *		- field : HLMT_PRC [상한가격]
	 *		- field : OUT_PRC [출고가격]
	 *		- field : FIX_PRC_YN [고정가격여부]
	 * </pre>
	 */
	public IDataSet fInqEqpMdlLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet usrListRs = null;
		int usrTotalCnt = 0;

		try {
			// 1. DU lookup
			DBIEqpMdlMgmt dBIEqpMdlMgmt = (DBIEqpMdlMgmt) lookupDataUnit(DBIEqpMdlMgmt.class);
			
			// 2. TOTAL COUNT DM호출
			dsCnt = dBIEqpMdlMgmt.dSEqpMdlTotCnt(requestData, onlineCtx);
			usrTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);

			// 3. LISTDM호출
			responseData = dBIEqpMdlMgmt.dSEqpMdlPaging(reqDs, onlineCtx);
			usrListRs = responseData.getRecordSet("RS_EQP_MDL_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(usrListRs, reqDs, usrTotalCnt);
		} catch ( BizRuntimeException e ) {
			throw e; //시스템 오류가 발생하였습니다.
		}

		return responseData;
	}

	/**
	 * <pre>단말기모델 입력</pre>
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-06-29 10:55:57
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_MDL_LIST
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_CL_CD [단말기구분]
	 *		- field : NW_MTHD_CD [네트웍방식]
	 *		- field : MFACT_CD [제조사코드]
	 *		- field : FST_RGST_DT [출시일]
	 *		- field : CNSG_DT [단종일]
	 *		- field : DEL_YN [삭제여부]
	 *		- field : FS_REG_USER_ID [최초등록사용자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종수정사용자ID]
	 *		- field : LS_CHG_DTM [최종수정일시]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegEqpMdl(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DBIEqpMdlMgmt dBIEqpMdlMgmt = (DBIEqpMdlMgmt) lookupDataUnit(DBIEqpMdlMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dBIEqpMdlMgmt.dSEqpMdlListChk(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) > 0 ) {
				throw new BizRuntimeException("DMS00003"); //중복입력 된 데이터가 존재합니다.
			}
			// 3. 업무 DM호출
			responseData = dBIEqpMdlMgmt.dIEqpMdl(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * <pre>단말기모델수정</pre>
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-07-01 08:26:13
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdEqpMdl(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DBIEqpMdlMgmt dBIEqpMdlMgmt = (DBIEqpMdlMgmt) lookupDataUnit(DBIEqpMdlMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dBIEqpMdlMgmt.dSEqpMdlListChk(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004"); //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 3. 업무 DM호출
			responseData = dBIEqpMdlMgmt.dUEqpMdl(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * <pre>단말기모델삭제</pre>
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-06-29 10:55:57
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_MDL_LIST
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_CL_CD [단말기구분]
	 *		- field : NW_MTHD_CD [네트웍방식]
	 *		- field : MFACT_CD [제조사코드]
	 *		- field : FST_RGST_DT [출시일]
	 *		- field : CNSG_DT [단종일]
	 *		- field : DEL_YN [삭제여부]
	 *		- field : FS_REG_USER_ID [최초등록사용자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종수정사용자ID]
	 *		- field : LS_CHG_DTM [최종수정일시]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fDelEqpMdl(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DBIEqpMdlMgmt dBIEqpMdlMgmt = (DBIEqpMdlMgmt) lookupDataUnit(DBIEqpMdlMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dBIEqpMdlMgmt.dSEqpMdlListChk(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004"); //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 3. 업무 DM호출
			responseData = dBIEqpMdlMgmt.dDEqpMdl(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

    /**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-08-04 15:37:06
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fEqpColorLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        try {
            // 1. DU lookup
            DBIEqpMdlMgmt dBIEqpMdlMgmt = (DBIEqpMdlMgmt) lookupDataUnit(DBIEqpMdlMgmt.class);
            // 2. 업무 DM호출
            responseData = dBIEqpMdlMgmt.dSEqpColorLst(requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        }
        return responseData;
    }

    /**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-08-04 17:43:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fDelEqpColor(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        try {
            // 1. DU lookup
            DBIEqpMdlMgmt dBIEqpMdlMgmt = (DBIEqpMdlMgmt) lookupDataUnit(DBIEqpMdlMgmt.class);
            // 2. 업무 DM호출
            responseData = dBIEqpMdlMgmt.dDEqpColor(requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        }
        return responseData;
    }

    /**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-08-04 17:43:51
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegEqpColor(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        try {
            // 1. DU lookup
            DBIEqpMdlMgmt dBIEqpMdlMgmt = (DBIEqpMdlMgmt) lookupDataUnit(DBIEqpMdlMgmt.class);
            // 2. 업무 DM호출
            responseData = dBIEqpMdlMgmt.dIEqpColor(requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        }
        return responseData;
    }
  
}
