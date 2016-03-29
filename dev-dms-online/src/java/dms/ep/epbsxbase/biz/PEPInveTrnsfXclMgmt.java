package dms.ep.epbsxbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [PU]재고이관정산관리</li>
 * <li>설  명 : <pre>재고이관정산관리 PU</pre></li>
 * <li>작성일 : 2015-11-19 18:10:24</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class PEPInveTrnsfXclMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PEPInveTrnsfXclMgmt(){
		super();
	}

    /**
	 * <pre>재고이관정산관리조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-19 18:10:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : EQP_SER_NO [일련번호]
	 *	- field : CNSL_MGMT_NO [접수관리번호]
	 *	- field : CONF_STA_DT [시작일자]
	 *	- field : CONF_END_DT [종료일자]
	 *	- field : SLIP_ST [전표상태]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_INVE_XCL_LIST
	 *		- field : INVE_MOV_XCL_NO [재고이관정산번호]
	 *		- field : INVE_MOV_DT [이관일자]
	 *		- field : CNSL_MGMT_NO [접수관리번호]
	 *		- field : PRCH_MGMT_NO [매입관리번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : INVE_AMT [재고금액]
	 *		- field : SLIP_DT [전표처리일]
	 *		- field : SLIP_NO [전표번호]
	 *		- field : SLIP_ST [전표상태]
	 *		- field : YYYY [전표처리년도]
	 * </pre>
	 */
	public IDataSet pInqInveTrnsfXclList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSSXBase", "fInqInveTrnsfXclList", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        } 
    
        return responseData;
    }

    /**
	 * <pre>재고이관정산전표발행</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-19 18:10:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : INVE_MOV_XCL_NO [재고이동정산번호]
	 *		- field : INVE_MOV_DT [이관일자]
	 *		- field : CNSL_MGMT_NO [접수관리번호]
	 *		- field : PRCH_MGMT_NO [매입관리번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : INVE_AMT [정산금액]
	 *		- field : SLIP_DT [전표처리일]
	 *		- field : SLIP_NO [전표번호]
	 *		- field : SLIP_ST [전표상태]
	 *		- field : YYYY [전표처리년도]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveInveTrnsfXclSlip(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSSXBase", "fSaveInveTrnsfXclSlip", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>재고이관정산전표취소</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-19 18:10:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : INVE_MOV_XCL_NO [재고이동정산번호]
	 *		- field : INVE_MOV_DT [이관일자]
	 *		- field : CNSL_MGMT_NO [접수관리번호]
	 *		- field : PRCH_MGMT_NO [매입관리번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : INVE_AMT [정산금액]
	 *		- field : SLIP_DT [전표처리일]
	 *		- field : SLIP_NO [전표번호]
	 *		- field : SLIP_ST [전표상태]
	 *		- field : YYYY [전표처리년도]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveInveTrnsfXclSlipDel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSSXBase", "fSaveInveTrnsfXclSlipDel", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>재고이관정산전표상태재조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-19 18:10:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : STA_DT [시작일]
	 *	- field : END_DT [종료일]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pInqInveTrnsfXclSlipReSearh(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSXMBase", "fSlipInfoSynchronization", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }
  
}
