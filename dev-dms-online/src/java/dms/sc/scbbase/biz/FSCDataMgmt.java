package dms.sc.scbbase.biz;

import java.util.Map;

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
 * <li>단위업무명: [FU]데이터접근권한관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-12-11 13:33:29</li>
 * <li>작성자 : 임지후 (jihooyim)</li>
 * </ul>
 *
 * @author 임지후 (jihooyim)
 */
public class FSCDataMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCDataMgmt() {
		super();
	}

	/**
	 * 데이터권한역할목록을 조회한다.
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-12-11 13:33:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqDataLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone

		try {
			// 1. DU lookup
			DSCDataMgmt dTB_DATA_AUTR_ROL01 = (DSCDataMgmt) lookupDataUnit(DSCDataMgmt.class);

			// 2. LIST DM호출
			responseData = dTB_DATA_AUTR_ROL01.dSDataLst(reqDs, onlineCtx);

		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 데이터권한역할수정
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-12-11 13:33:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdDataLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet responseHis = new DataSet();
		try {
			// 1. DU lookup
			DSCDataMgmt dTB_DATA_AUTR_ROL01 = (DSCDataMgmt) lookupDataUnit(DSCDataMgmt.class);

			// 2. Validation DM호출
			IDataSet valDS = dTB_DATA_AUTR_ROL01.dSDataLstChk(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004"); //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 3. 업무 DM호출
			responseData = dTB_DATA_AUTR_ROL01.dUDataLstUpd(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 데이터권한역할삭제
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-12-11 13:33:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fDelDataLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCDataMgmt dTB_DATA_AUTR_ROL01 = (DSCDataMgmt) lookupDataUnit(DSCDataMgmt.class);

			// 2. Validation DM호출
			IDataSet valDS = dTB_DATA_AUTR_ROL01.dSDataLstChk(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004"); //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 3. 업무 DM호출
			responseData = dTB_DATA_AUTR_ROL01.dDDataLstDel(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;

	}

	/**
	 * 데이터권한역할등록
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-12-11 13:33:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegDataLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCDataMgmt dTB_DATA_AUTR_ROL01 = (DSCDataMgmt) lookupDataUnit(DSCDataMgmt.class);

			//2. 업무 DM호출
			responseData = dTB_DATA_AUTR_ROL01.dIDataLstReg(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

}
