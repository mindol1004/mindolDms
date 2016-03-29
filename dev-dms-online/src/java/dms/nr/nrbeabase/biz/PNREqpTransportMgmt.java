package dms.nr.nrbeabase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.common.CommonArea;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [PU]SKN물류외 입고</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-09-10 15:47:05</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class PNREqpTransportMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNREqpTransportMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-10 15:47:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ST_DT [수취일자검색시작일]
	 *	- field : ED_DT [수취일자검색종료일]
	 *	- field : ST_DT_SEND [발송일자검색시작일]
	 *	- field : ED_DT_SEND [발송일자검색종료일]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : EQP_SER_NO [단말일련번호]
	 *	- field : HDLV_SNDR_NM [발송자명]
	 *	- field : EQP_WAYBIL_NO [단말기운송장번호(k)]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_TRANSPORT_LIST
	 *		- field : ASSET_NO [자산번호]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : EQP_RCV_DT [단말기수취일자]
	 *		- field : WAYBIL_NO [운송장번호]
	 *		- field : HDLVCO_CD [택배사코드]
	 *		- field : HDLVCO_NM [택배사명]
	 *		- field : HDLV_AMT [택배비]
	 *		- field : HDLV_SNDR_NM [택배발송자명]
	 *		- field : HDLV_SND_DT [택배발송일자]
	 *		- field : HDLV_SND_RSN [택배발송사유]
	 *		- field : EQP_WAYBIL_NO [회수번호]
	 *		- field : IN_OBJ_DTL_CD [회수구분코드]
	 * </pre>
	 */
	public IDataSet pInqEqpTransportLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();

	    try {
			// 1. FM 호출
    		responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fInqEqpTransportLstPaging", requestData, onlineCtx);
			
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 3. 결과값 리턴
		responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-10 15:47:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_TRANSPORT_LIST
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : EQP_RCV_DT [단말기수취일자]
	 *		- field : WAYBIL_NO [운송장번호]
	 *		- field : HDLVCO_CD [택배사코드]
	 *		- field : HDLV_AMT [택배비]
	 *		- field : HDLV_SNDR_NM [택배발송자명]
	 *		- field : HDLV_SND_DT [택배발송일자]
	 *		- field : HDLV_SND_RSN [택배발송사유]
	 *		- field : CHK [저장 업데이트체크]
	 *		- field : EQP_WAYBIL_NO [단말운송번호]
	 *		- field : IN_OBJ_DTL_CD [회수구분코드]
	 *		- field : EQP_IMEI_NO [IMEI번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pEqpTransportSave(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	    
	    try {
	    	IRecordSet rs = requestData.getRecordSet("RS_TRANSPORT_LIST");
	    	
	    	if(rs == null){
				return responseData;
			}
	    	requestData.putFieldMap( rs.getRecordMap(0) );
	    	requestData.putField("USER_NO", ca.getUserNo());
	    	// 1. FM 호출
	    	if ("I".equals(rs.get(0, "CHK"))){
	    		
	    		responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fEqpTransprotSave",requestData, onlineCtx);
	    		
	    	}else if ("U".equals(rs.get(0, "CHK"))){
    			
	    		responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fEqpTransportUpdate", requestData, onlineCtx); 
    			
	    	}     			    	    	   			    	
    		
			
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 3. 결과값 리턴
		responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-10 15:47:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_TRANSPORT_DELETE
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : EQP_RCV_DT [단말기수취일자]
	 *		- field : WAYBIL_NO [운송장번호]
	 *		- field : HDLVCO_CD [택배사코드]
	 *		- field : HDLV_AMT [택배비]
	 *		- field : HDLV_SNDR_NM [택배발송자명]
	 *		- field : HDLV_SND_DT [택배발송일자]
	 *		- field : HDLV_SND_RSN [택배발송사유]
	 *		- field : CHK [저장 업데이트체크]
	 *		- field : EQP_WAYBIL_NO [단말운송번호]
	 *		- field : IN_OBJ_DTL_CD [회수구분코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pEqpTransportDelete(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	
	    try {
	    		IRecordSet rs = requestData.getRecordSet("RS_TRANSPORT_DELETE");
	    	
		    	if(rs == null){
					return responseData;
				}
	    	
		    requestData.putFieldMap( rs.getRecordMap(0) );
		    requestData.putField("USER_NO", ca.getUserNo());
		    
			// 1. FM 호출
    		responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fEqpTransportDelete", requestData, onlineCtx);
			
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
			  
	
	    return responseData;
	}
  
}