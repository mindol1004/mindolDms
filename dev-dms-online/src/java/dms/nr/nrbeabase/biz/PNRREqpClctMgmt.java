package dms.nr.nrbeabase.biz;

import fwk.common.CommonArea;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [PU]R센터입고관리</li>
 * <li>설  명 : <pre>R센터입고관리</pre></li>
 * <li>작성일 : 2015-08-11 19:12:57</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class PNRREqpClctMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNRREqpClctMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-08-11 19:12:57
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ST_DT [회수시작일]
	 *	- field : ED_DT [회수종료일]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : ED_DT_IN [입고종료일]
	 *	- field : EQP_SER_NO [단말일련번호]
	 *	- field : ST_DT_IN [입고시작일]
	 *	- field : INVE_ST_CD [재고상태코드]
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : EQP_IMEI_NO [IMEI 번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_RCIBM_LIST
	 *		- field : EQP_IN_DT [입고일]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_COLOR_CD [모델코드]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EQP_IMEI_NO [IME번호]
	 *		- field : AGN_CD [반납대리점코드]
	 *		- field : IN_OBJ_DTL_CD [회수구분]
	 *		- field : SKN_CLCT_DT [회수일]
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 * </pre>
	 */
	public IDataSet pInqREqpClctLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
			//1. FU lookup
			responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fInqREqpClctLst", requestData, onlineCtx);
		
	    } catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
		   	throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-08-11 19:12:57
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_REQP_CLCT_HANDLE
	 *		- field : ASSET_NO [자산번호]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_COLOR_CD [색상]
	 *		- field : EQP_SER_NO [단말일련번호]
	 *		- field : EQP_IMEI_NO [IME번호]
	 *		- field : INVE_ST_CD [재고상태코드]
	 *		- field : AGN_CD [대리점코드]
	 *		- field : CHECKED [체크박스]
	 *		- field : EQP_CLCT_OBJ_NO [회수상태코드]
	 *		- field : IN_OBJ_DTL_CD [회수구분코드]
	 *		- field : INVE_DEL_YN [감정데이터삭제여부]
	 *		- field : EQP_IN_DT [입고일]
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pUREqpClct(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);// 처리 결과값을 responseData에 넣어서 리턴하십시요 
 		
	    try {
	    	requestData.putField("USER_NO", ca.getUserNo());
			//1. FU lookup
			responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fUREqpClct", requestData, onlineCtx);
		
	    } catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
		   	throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
	
	    return responseData;
	}
  
}