package dms.sc.scbbase.biz;

import java.util.Map;

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
 * <li>단위업무명: DTB_DATA_AUTR_ROL01</li>
 * <li>설  명 : 사용자데이터권한역할관리</li>
 * <li>작성일 : 2014-09-11 15:06:22</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class DSCUserAutrDataRolMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCUserAutrDataRolMgmt() {
		super();
	}

	/**
	 * 사용자데이터권한역할조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSUserDataRolListInq(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SUserDataRolListInq", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_USER_DATA_ROL_LIST", rsReturn);
		return responseData;
	}

	/**
	 * 사용자데이터권한전체건수조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSUserDataRolCnt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출	    
		IRecord record = dbSelectSingle("SUserDataRolCnt", requestData, onlineCtx);
		// 2.결과값 셋팅	    
		responseData.putFieldMap(record);
		return responseData;

	}

	/**
	 * 사용자접근가능 브랜드목록조회
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2015-01-26 15:45:03
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dInqUsrDataRolBrndLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출	    
		IRecordSet rsReturn = dbSelect("SUsrDataRolBrndLstInq", requestData.getFieldMap(), onlineCtx);
		// 2.결과값 셋팅	    
		responseData.putRecordSet("RS_AUTR_BRND", rsReturn);
		return responseData;
	}

	/**
	 * 사용자접근가능 가맹점목록조회
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-09-11 15:06:22
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dInqUsrDataRolMchtLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출	    
		IRecordSet rsReturn = dbSelect("SUsrDataRolMchtLstInq", requestData.getFieldMap(), onlineCtx);
		// 2.결과값 셋팅	    
		responseData.putRecordSet("RS_AUTR_MCHT", rsReturn);
		return responseData;
	}

}
