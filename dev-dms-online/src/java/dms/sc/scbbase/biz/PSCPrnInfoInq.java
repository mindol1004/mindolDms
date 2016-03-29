package dms.sc.scbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: PSCPrnInfoInq</li>
 * <li>설  명 : 개인정보조회</li>
 * <li>작성일 : 2014-08-11 11:08:36</li>
 * <li>작성자 : 김석영 (kimsukyoung)</li>
 * </ul>
 *
 * @author 김석영 (kimsukyoung)
 */
public class PSCPrnInfoInq extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PSCPrnInfoInq() {
		super();
	}

	/**
	 * <pre>개인정보조회</pre>
	 *
	 * @author admin (admin)
	 * @since 2015-06-22 16:59:55
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_PRN_INFO_INQ
	 *		- field : PRN_INFO_INQ_ITEM_CD [개인정보조회항목코드]
	 *		- field : PRN_INFO_INQ_CTT [개인정보조회내용]
	 *	- field : FRM_ID [화면ID]
	 *	- field : INQ_OBJ_NO [조회대상번호]
	 *	- field : REG_HST_YN [이력등록여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_PARAM
	 *		- field : PRN_INFO_INQ_ITEM_CD [개인정보조회항목코드]
	 *		- field : PRN_INFO_INQ_ITEM_NM [개인정보조회항목명]
	 *		- field : PRN_INFO_INQ_CTT [개인정보조회내용]
	 * </pre>
	 */
	public IDataSet pInqPrnInfo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();

		try {
		    
            responseData = callSharedBizComponentByDirect("sc.SCSBase", "fInqPrnInfo", requestData, onlineCtx);
            /*
			//개인정보이력조회시 이력등록여부 파라미터 값에 따라 FM 호출 분기처리
			if ( StringUtils.equals(requestData.getField("REG_HST_YN"), "N") ) {
				// 개인정보조회_다건조회_이력등록제외
				responseData = callSharedBizComponentByDirect("sc.SCSBase", "fInqPrnInfoNoRegHst", requestData, onlineCtx);
			} else {
				// 개인정보조회_다건조회
				responseData = callSharedBizComponentByDirect("sc.SCSBase", "fInqPrnInfoForFrm", requestData, onlineCtx);
			}
			*/
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e);	// 시스템 오류
		}
		responseData.setOkResultMessage("DMS00000", null); // 정상 처리되었습니다.
		return responseData;
	}

}
