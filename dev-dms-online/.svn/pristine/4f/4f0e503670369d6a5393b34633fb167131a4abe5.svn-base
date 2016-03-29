package dms.ep.epbimbase.biz;

import java.text.SimpleDateFormat;
import java.util.Date;

import fwk.common.CommonArea;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [PU]재고이관관리</li>
 * <li>설  명 : <pre>재고이관관리 PU</pre></li>
 * <li>작성일 : 2015-11-17 11:08:55</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class PEPInveTrnsfMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PEPInveTrnsfMgmt(){
		super();
	}

    /**
	 * <pre>재고이관등록</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-17 11:08:55
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_PR_DIS_LIST
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : PROD_SER_NO [상품일련번호]
	 *		- field : EQP_COLOR_CD [모델색상]
	 *		- field : INVE_ST [재고상태]
	 *		- field : IN_OUT_CL [입출고구분]
	 *		- field : IN_OUT_DTL_CL [입출고상세구분]
	 *		- field : USERNO [사용자번호]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : EQP_IN_NO [입고번호]
	 *		- field : IMEI [IMEI]
	 *		- field : PRCH_AMT [매입가]
	 *		- field : PRCH_MGMT_NO [매입관리번호]
	 *		- field : CNSL_MGMT_NO [상담관리번호]
	 *		- field : SELL_UPRC [판매단가]
	 *		- field : SELL_PCOST [판매원가]
	 *		- field : SELL_MRGN [판매마진]
	 *		- field : SELL_CMMS [판매수수료]
	 *		- field : SURTAX [부가세]
	 *		- field : SALE_QTY [판매수량]
	 *		- field : PROD_CL [상품구분]
	 *		- field : SELL_AMT [판매가]
	 *		- field : FIX_PRC_YN [고정가여부]
	 *		- field : HLD_DEALCO_ID [보유처ID]
	 *		- field : INVE_MOV_XCL_NO [재고이동정산번호]
	 *		- field : PROD_OUT_MGMT_NO [출고관리번호]
	 *		- field : IN_OUT_SEQ [입출고번호]
	 *		- field : SELL_MGMT_NO [판매번호]
	 *		- field : GNRL_SELL_CHG_SEQ [판매변경순번]
	 *		- field : OUT_DEALCO_ID [출고거래처ID]
	 *		- field : SELL_DTM [판매일자]
	 *		- field : RMK [비고]
	 *		- field : EAPRV_COND_CD [전자결재조건코드]
	 *		- field : OUT_CL [출고구분]
	 *		- field : SELL_YN [판매여부]
	 *		- field : TRMS_CL [전송구분]
	 *		- field : EQP_ST [단말상태]
	 *		- field : INVE_AMT [재고금액]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveInveTrnsf(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);
    
        try {
            
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
            long currentTime = System.currentTimeMillis();
            
            requestData.putField("SELL_DTM", df.format(new Date(currentTime)));
            requestData.putField("OUT_CL", "401");
            requestData.putField("IN_OUT_CL", "400");
            requestData.putField("IN_OUT_DTL_CL", "401");
            requestData.putField("SELL_DEALCO_CD", "11010");
            requestData.putField("USERNO", ca.getUserNo());
            
            // 1. FM 호출
            callSharedBizComponentByDirect("ep.EPSIMBase", "fRegInveTrnsf", requestData, onlineCtx);
            
            requestData.putRecordSet("RS_SALE_LIST", requestData.getRecordSet("RS_PR_DIS_LIST"));
            callSharedBizComponentByDirect("ep.EPSESBase", "fRegDisSaleOut", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>재고이관조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-17 11:08:55
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : EQP_SER_NO [일련번호]
	 *	- field : PROD_SER_NO [상품일련번호]
	 *	- field : CNSL_MGMT_NO [접수관리번호]
	 *	- field : CONF_STA_DT [이관시작일]
	 *	- field : CONF_END_DT [이관종료일]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_PR_DIS_LIST
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_COLOR_CD [모델색상]
	 *		- field : PROD_SER_NO [상품일련번호]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : EQP_ST [단말기상태]
	 *		- field : INVE_ST [재고상태]
	 *		- field : HLD_DEALCO_ID [보유거래처ID]
	 *		- field : HLD_DEALCO_NM [보유거래처명]
	 *		- field : CNSL_MGMT_NO [상담관리번호]
	 *		- field : IN_OUT_CL [입출고구분]
	 *		- field : IN_OUT_DTL_CL [입출고상세구분]
	 *		- field : MFACT_CD [제조사코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : INVE_AMT [재고금액]
	 *		- field : CONF_SELL_DT [확정판매일자]
	 * </pre>
	 */
	public IDataSet pInqInveTrnsfList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSIMBase", "fInqInveTrnsfList", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }
  
}
