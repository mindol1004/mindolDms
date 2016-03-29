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
 * <li>단위업무명: FSCMenuMgmt</li>
 * <li>설  명 : 메뉴관리</li>
 * <li>작성일 : 2014-07-30 13:15:32</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class FSCMenuMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCMenuMgmt() {
		super();
	}

	/**
	 * 메뉴목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqMenuLst(IDataSet requestData, IOnlineContext onlineCtx) {

		IDataSet responseData = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone

		try {
			// 1. DU lookup
			DSCMenuMgmt dSCMenuMgmt = (DSCMenuMgmt) lookupDataUnit(DSCMenuMgmt.class);
			// 2. LISTDM호출
			responseData = dSCMenuMgmt.dSMenuLst(reqDs, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e; //시스템 오류가 발생하였습니다.
		}

		return responseData;
	}

	/**
	 * 메뉴등록
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegMenu(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCMenuMgmt dSCMenuMgmt = (DSCMenuMgmt) lookupDataUnit(DSCMenuMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dSCMenuMgmt.dSMenuValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) > 0 ) {
				throw new BizRuntimeException("DMS00003"); //중복입력 된 데이터가 존재합니다.\n\n데이터를 확인해 주세요.
			}
			// 3. 업무 DM호출
			responseData = dSCMenuMgmt.dIMenu(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 메뉴수정
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdMenu(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCMenuMgmt dSCMenuMgmt = (DSCMenuMgmt) lookupDataUnit(DSCMenuMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dSCMenuMgmt.dSMenuValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004"); //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 3. 업무 DM호출
			responseData = dSCMenuMgmt.dUMenu(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;

	}

	/**
	 * 메뉴삭제
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fDelMenu(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCMenuMgmt dSCMenuMgmt = (DSCMenuMgmt) lookupDataUnit(DSCMenuMgmt.class);
			// 2. Validation DM호출
			IDataSet valDS = dSCMenuMgmt.dSMenuValidate(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) == 0 ) {
				throw new BizRuntimeException("DMS00004"); //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
			}
			// 3. 업무 DM호출
			responseData = dSCMenuMgmt.dDMenu(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;

	}

}
