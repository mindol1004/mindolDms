package dms.ep.epbimbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [PU]재고상품관리</li>
 * <li>설  명 : <pre>재고상품관리 PU</pre></li>
 * <li>작성일 : 2015-09-08 14:39:31</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class PEPInveProdDisMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PEPInveProdDisMgmt(){
		super();
	}

    /**
	 * <pre>재고상품조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-08 14:39:31
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : HLD_DEALCO_ID [출고처ID]
	 *	- field : HLD_DEALCO_NM [출고처명]
	 *	- field : PROD_SER_NO [상품일련번호]
	 *	- field : MFACT_CD [제조사코드]
	 *	- field : EQP_SER_NO [일련번호]
	 *	- field : CNSL_MGMT_NO [접수관리번호]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : BOX_NO [BOX NO]
	 *	- field : SELL_TYP [판매유형]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_INVE_DIS_LIST
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_COLOR_CD [모델색상]
	 *		- field : PROD_SER_NO [상품일련번호]
	 *		- field : EQP_ST [단말기상태]
	 *		- field : INVE_ST [재고상태]
	 *		- field : HLD_DEALCO_ID [보유거래처ID]
	 *		- field : HLD_DEALCO_NM [보유거래처명]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : UPDATE_COUNT [UPDATE COUNT]
	 *		- field : CNSL_MGMT_NO [상담관리번호]
	 *		- field : CONF_SELL_AMT [확정판매금액]
	 *		- field : PROD_CL [상품구분]
	 *		- field : MFACT_CD [제조사코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : LAUNC_DT [출시일]
	 *		- field : PRCH_MGMT_NO [매입관리번호]
	 *		- field : BOX_NO [BOX_NO]
	 *		- field : SELL_CHG_HST_CL [구분]
	 *		- field : SALE_QTY [판매수량]
	 *		- field : FIX_PRC_YN [고정가여부]
	 *		- field : SELL_PCOST [판매원가]
	 *		- field : SELL_UPRC [판매단가]
	 *		- field : SELL_MRGN [판매마진]
	 *		- field : SELL_CMMS [판매수수료]
	 *		- field : SURTAX [부가세]
	 *		- field : SELL_AMT [판매가(부가세포함)]
	 * </pre>
	 */
	public IDataSet pInqInveProdDisList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSIMBase", "fInqInveProdDisList", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>재고상품체크</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-08 14:39:31
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_INVE_DIS_LIST
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : PROD_SER_NO [일련번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_INVE_ERR_LIST
	 *		- field : ERR_MSG [에러메세지]
	 * </pre>
	 */
	public IDataSet pInqInveProdDisChkList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSIMBase", "fInqInveProdDisChkList", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }
  
}
