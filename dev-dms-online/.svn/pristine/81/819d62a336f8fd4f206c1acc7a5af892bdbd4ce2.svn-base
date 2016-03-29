package dms.sc.scbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: 개인정보조회이력관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-09-05 16:13:43</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 정혜미 (junghaemi)
 */
public class DSCPrnInfoInqHstMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCPrnInfoInqHstMgmt() {
		super();
	}

	/**
	 * 개인정보 조회이력 목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSPrnInfoInqHstPasing(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SPrnInfoInqHstPasing", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_PRN_INFO_INQ_HST", rsReturn);
		return responseData;
	}

	/**
	 * 개인정보조회이력 전체건수조회
	 *
	 * @author 
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSPrnInfoInqHstTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		// 1.쿼리문 호출
		IRecord record = dbSelectSingle("SPrnInfoInqHstTotCnt", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putFieldMap(record);
		return responseData;
	}

}
