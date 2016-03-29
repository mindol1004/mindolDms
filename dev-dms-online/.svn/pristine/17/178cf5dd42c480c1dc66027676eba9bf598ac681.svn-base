package dms.nr.nrseabase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;
import fwk.common.CommonArea;
import fwk.utils.PagenateUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]skn물류외입고조회</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-09-10 16:00:48</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class FNREqpTransprotMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNREqpTransprotMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-10 16:00:48
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
	public IDataSet fEqpTransprotSave(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	 	IDataSet temp = new DataSet();
	 	CommonArea ca = getCommonArea(onlineCtx);
	 	
	 	try {
			    DNREqpTransportMgmt dNREqpTransprotMgmt = (DNREqpTransportMgmt) lookupDataUnit(DNREqpTransportMgmt.class);
		       
			    IRecordSet rs = requestData.getRecordSet("RS_TRANSPORT_LIST");
			    IRecord ir = rs.getRecord(0);
			    temp.putField("EQP_MDL_CD",ir.get("EQP_MDL_CD"));
			    temp.putField("EQP_SER_NO",ir.get("EQP_SER_NO"));
			    temp.putField("SKN_CLCT_DT",ir.get("EQP_RCV_DT"));  //수령일자
			    temp.putField("RTN_REG_DT",ir.get("HDLV_SND_DT")); //발송일자
			    temp.putField("USER_NO", ca.getUserNo());
			   
			    //1.자산번호가 택배용 회수대상인지 확인
		        IDataSet ds = dNREqpTransprotMgmt.dSEqpTransGetAssetNo(requestData, onlineCtx);
		        IRecordSet assetRs = ds.getRecordSet("RS_ASSET_LIST");
		        
		        ir.set("ASSET_NO", assetRs.get(0,"ASSET_NO"));
		        
		        temp.putField("EQP_IMEI_NO",assetRs.get(0,"EQP_IMEI_NO"));
		        temp.putField("EQP_COLOR_CD",assetRs.get(0,"EQP_COLOR_CD"));
		       
		        
		    	dNREqpTransprotMgmt.dIEqpTransprotClctObj(temp, onlineCtx);				//회수테이블 insert
		    	requestData.putField("USER_NO",  ca.getUserNo());
		    	dNREqpTransprotMgmt.dIEqpTransportInfoSave(requestData, onlineCtx);		//운송테이블 저장
			   
	 	} catch ( BizRuntimeException e ) {
			throw e; //시스템 오류가 발생하였습니다.
		}
	
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-10 16:00:48
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
	public IDataSet fInqEqpTransportLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet usrListRs = null;
		int intTotalCnt = 0;

		try {
			// 1. DU lookup
			DNREqpTransportMgmt dNREqpTransportMgmt = (DNREqpTransportMgmt) lookupDataUnit(DNREqpTransportMgmt.class);
			
			// 2. TOTAL COUNT DM호출
			dsCnt = dNREqpTransportMgmt.dSInqEqpTransportLstTotCnt(requestData, onlineCtx);
			intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);

			// 3. LISTDM호출
			responseData = dNREqpTransportMgmt.dSInqEqpTransportLstPaging(reqDs, onlineCtx);
			usrListRs = responseData.getRecordSet("RS_TRANSPORT_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(usrListRs, reqDs, intTotalCnt);
			
		} catch ( BizRuntimeException e ) {
			throw e; //시스템 오류가 발생하였습니다.
		}
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-10 16:00:48
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
	public IDataSet fEqpTransportUpdate(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
		    DNREqpTransportMgmt dNREqpTransprotMgmt = (DNREqpTransportMgmt) lookupDataUnit(DNREqpTransportMgmt.class);
		    
		    if(requestData.getFieldCount() <1){
		    	return requestData;
		    }
		    
		    //1.회수테이블 단말기회수번호 확인
		    IDataSet clctNoDs = dNREqpTransprotMgmt.dSEqpTransGetEqpClctNo(requestData, onlineCtx);
		    requestData.putField("EQP_CLCT_OBJ_NO", clctNoDs.getField("EQP_CLCT_OBJ_NO"));
		    
		    dNREqpTransprotMgmt.dUEqpTransportUpdate(requestData, onlineCtx);
		    
	    } catch ( BizRuntimeException e ) {
			throw e; //시스템 오류가 발생하였습니다.
		}
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-10 16:00:48
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
	public IDataSet fEqpTransportDelete(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
		    DNREqpTransportMgmt dNREqpTransprotMgmt = (DNREqpTransportMgmt) lookupDataUnit(DNREqpTransportMgmt.class);
		    
		    if(requestData.getFieldCount() <1){
		    	return requestData;
		    }
	    
		    //1.회수테이블 단말기회수번호 확인
		    IDataSet clctNoDs = dNREqpTransprotMgmt.dSEqpTransGetEqpClctNo(requestData, onlineCtx);
		    requestData.putField("EQP_CLCT_OBJ_NO", clctNoDs.getField("EQP_CLCT_OBJ_NO"));
		    
		    dNREqpTransprotMgmt.dDEqpTransportDelete(requestData, onlineCtx);
		    
	    } catch ( BizRuntimeException e ) {
			throw e; //시스템 오류가 발생하였습니다.
		}
	
	    return responseData;
	}
  
}
