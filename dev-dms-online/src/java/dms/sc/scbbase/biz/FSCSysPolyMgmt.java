package dms.sc.scbbase.biz;

import java.util.Map;

import org.omg.CORBA.INTERNAL;

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
 * <li>단위업무명: FSCSysPolyMgmt</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-07-30 11:02:11</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class FSCSysPolyMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCSysPolyMgmt() {
		super();
	}

	/**
	 * [FU 시스템정책관리] 시스템정책 목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqSysPolyLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone(); //원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet sysPolyLstRs = null;
		int sysPolyTtCnt = 0;
		try {
			// 1. DU lookup
			DSCSysPolyMgmt dTB_SYS_POLY01 = (DSCSysPolyMgmt) lookupDataUnit(DSCSysPolyMgmt.class);
			// 2. TOTAL COUNT DM호출
			dsCnt = dTB_SYS_POLY01.dSSysPolyLstTotCnt(requestData, onlineCtx);
			sysPolyTtCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);
			//3. LIST DM호출
			responseData = dTB_SYS_POLY01.dSSysPolyLstPaging(reqDs, onlineCtx);
			sysPolyLstRs = responseData.getRecordSet("RS_SYS_POLY_MGMT_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(sysPolyLstRs, reqDs, sysPolyTtCnt);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * [FU 시스템정책관리] 시스템정책 등록  
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegSysPoly(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCSysPolyMgmt dTB_SYS_POLY01 = (DSCSysPolyMgmt) lookupDataUnit(DSCSysPolyMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dTB_SYS_POLY01.dSSysPolyLstValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) > 0 ) {
				throw new BizRuntimeException("DMS00003"); //중복 된 데이터가 존재합니다.
			}
			//3. 업무 DM호출
			responseData = dTB_SYS_POLY01.dISysPoly(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * [FU 시스템정책관리] 시스템정책 수정
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdSysPoly(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone(); //원거래의 DataSet의 훼손을 막기 위한 Clone
		try {
			// 1. DU lookup
			DSCSysPolyMgmt dTB_SYS_POLY01 = (DSCSysPolyMgmt) lookupDataUnit(DSCSysPolyMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dTB_SYS_POLY01.dSSysPolyLstValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004"); //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 3. 업무 DM호출
			reqDs.removeRecordSet("RS_SYS_POLY_MGMT_LIST");
			responseData = dTB_SYS_POLY01.dUSysPoly(reqDs, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

}
