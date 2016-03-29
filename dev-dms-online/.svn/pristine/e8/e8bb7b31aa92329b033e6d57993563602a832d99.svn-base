package dms.ep.epbsxbase.biz;

import fwk.common.CommonArea;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [PU]단말기대금요금선납정산관리</li>
 * <li>설  명 : <pre>단말기대금요금선납정산관리 PU</pre></li>
 * <li>작성일 : 2015-10-23 09:45:07</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class PEPEqpPpayXclMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PEPEqpPpayXclMgmt(){
		super();
	}

    /**
	 * <pre>단말기대금요금선납조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:45:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : STRD_YM [정산년월]
	 *	- field : TYP_CD [유형코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : FEE_PPAY_XCL_NO [요금 선납 정산 번호]
	 *		- field : FEE_PPAY_STRD_YM [요금 선납 기준 년월]
	 *		- field : INVE_TYP_CD [재고 유형 코드]
	 *		- field : FEE_PPAY_AMT [요금 선납 금액]
	 *		- field : FEE_PPAY_XCL_CNT [요금 선납 정산 건수]
	 *		- field : SLIP_DT [요금 선납 전표 일자]
	 *		- field : SLIP_NO [요금 선납 전표 번호]
	 *		- field : SLIP_ST [요금 선납 전표 상태]
	 *		- field : FEE_PPAY_CNCL_SLIP_DT [요금 선납 취소 전표 일자]
	 *		- field : FEE_PPAY_CNCL_SLIP_NO [요금 선납 취소 전표 번호]
	 *		- field : DEALCO_BLICENS_NO [사업자 번호]
	 *		- field : YYYY [전표년도]
	 * </pre>
	 */
	public IDataSet pInqEqpPpayXclList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSSXBase", "fInqEqpPpayXclList", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금요금선납상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:45:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : FEE_PPAY_XCL_NO [정산번호]
	 *	- field : EXCEL_FIRST [시작번호]
	 *	- field : EXCEL_LAST [종료번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_PPAY_DTL
	 *		- field : ROWNO [번호]
	 *		- field : EXCEL_TOTAL_CNT [총건수]
	 *		- field : FEE_PPAY_XCL_NO [요금 선납 정산 번호]
	 *		- field : FEE_PPAY_XCL_DTL_SEQ [요금 선납 정산 상세 순번]
	 *		- field : INVE_TYP_CD [재고 유형 코드]
	 *		- field : CNSL_MGMT_NO [상담 관리 번호]
	 *		- field : PRCH_MGMT_NO [매입 관리 번호]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : EQP_ST [단말기상태]
	 *		- field : REPV_AGN_ORG_CD [대표 대리점 조직 코드]
	 *		- field : CNSL_DT [상담 일자]
	 *		- field : CLCT_DT [회수 일자]
	 *		- field : FEE_DEDC_PROC_DT [요금 공제 처리 일자]
	 *		- field : XCL_AMT [정산 금액]
	 *		- field : SKN_JDG_AMT [SKN 감정 금액]
	 *		- field : SKN_SMPL_JDG_DAMT [SKN 샘플 감정 차액]
	 *		- field : AGN_DDCT_AMT [대리점 차감 금액]
	 *		- field : AGN_EXPNS_CONF_AMT [대리점 비용 확정 금액]
	 *		- field : PPAY_PROC_TS [차수]
	 *		- field : NOT_INVE_SLIP_NO [미착전표번호]
	 *		- field : NOT_INVE_SLIP_DT [미착전표기산일]
	 *		- field : FIX_INVE_SLIP_NO [확정전표번호]
	 *		- field : FIX_INVE_SLIP_DT [확정전표기산일]
	 * </pre>
	 */
	public IDataSet pInqEqpPpayXclDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSSXBase", "fInqEqpPpayXclDtl", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금요금선납재집계</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:45:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : STRD_YM [정산년월]
	 *	- field : TYP_CD [유형코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveEqpPpayXcl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);  
    
        try {
            
            requestData.putField("USERNO", ca.getUserNo());
            
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSSXBase", "fSaveEqpPpayXcl", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금요금선납전표발행</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:45:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : FEE_PPAY_XCL_NO [요금 선납 정산 번호]
	 *		- field : FEE_PPAY_STRD_YM [요금 선납 기준 년월]
	 *		- field : INVE_TYP_CD [재고 유형 코드]
	 *		- field : FEE_PPAY_AMT [요금 선납 금액]
	 *		- field : FEE_PPAY_XCL_CNT [요금 선납 정산 건수]
	 *		- field : SLIP_DT [요금 선납 전표 일자]
	 *		- field : SLIP_NO [요금 선납 전표 번호]
	 *		- field : SLIP_ST [요금 선납 전표 상태]
	 *		- field : FEE_PPAY_CNCL_SLIP_DT [요금 선납 취소 전표 일자]
	 *		- field : FEE_PPAY_CNCL_SLIP_NO [요금 선납 취소 전표 번호]
	 *		- field : DEALCO_BLICENS_NO [사업자 번호]
	 *		- field : YYYY [전표 년도]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveEqpPpayXclSlip(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSSXBase", "fSaveEqpPpayXclSlip", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금요금선납전표취소</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:45:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : FEE_PPAY_XCL_NO [요금 선납 정산 번호]
	 *		- field : FEE_PPAY_STRD_YM [요금 선납 기준 년월]
	 *		- field : INVE_TYP_CD [재고 유형 코드]
	 *		- field : FEE_PPAY_AMT [요금 선납 금액]
	 *		- field : FEE_PPAY_XCL_CNT [요금 선납 정산 건수]
	 *		- field : SLIP_DT [요금 선납 전표 일자]
	 *		- field : SLIP_NO [요금 선납 전표 번호]
	 *		- field : SLIP_ST [요금 선납 전표 상태]
	 *		- field : FEE_PPAY_CNCL_SLIP_DT [요금 선납 취소 전표 일자]
	 *		- field : FEE_PPAY_CNCL_SLIP_NO [요금 선납 취소 전표 번호]
	 *		- field : DEALCO_BLICENS_NO [사업자 번호]
	 *		- field : YYYY [전표년도]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveEqpPpayXclSlipDel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSSXBase", "fSaveEqpPpayXclSlipDel", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금요금선납전표상태재조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:45:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : YYYYMM [정산년월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pInqEqpPpayXclSlipReSearh(IDataSet requestData, IOnlineContext onlineCtx) {
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

    /**
	 * <pre>단말기대금클럽T재집계</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:45:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : STRD_YM [정산년월]
	 *	- field : TYP_CD [유형코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveEqpClubTXcl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);  
        
        try {
            
            requestData.putField("USERNO", ca.getUserNo());
            
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSSXBase", "fSaveEqpClubTXcl", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금클럽T전표발행</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:45:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : FEE_PPAY_XCL_NO [요금 선납 정산 번호]
	 *		- field : FEE_PPAY_STRD_YM [요금 선납 기준 년월]
	 *		- field : INVE_TYP_CD [재고 유형 코드]
	 *		- field : FEE_PPAY_AMT [요금 선납 금액]
	 *		- field : FEE_PPAY_XCL_CNT [요금 선납 정산 건수]
	 *		- field : SLIP_DT [요금 선납 전표 일자]
	 *		- field : SLIP_NO [요금 선납 전표 번호]
	 *		- field : SLIP_ST [요금 선납 전표 상태]
	 *		- field : FEE_PPAY_CNCL_SLIP_DT [요금 선납 취소 전표 일자]
	 *		- field : FEE_PPAY_CNCL_SLIP_NO [요금 선납 취소 전표 번호]
	 *		- field : DEALCO_BLICENS_NO [사업자 번호]
	 *		- field : YYYY [전표 년도]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveEqpClubTXclSlip(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSSXBase", "fSaveEqpClubTXclSlip", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금클럽T전표취소</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:45:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : FEE_PPAY_XCL_NO [요금 선납 정산 번호]
	 *		- field : FEE_PPAY_STRD_YM [요금 선납 기준 년월]
	 *		- field : INVE_TYP_CD [재고 유형 코드]
	 *		- field : FEE_PPAY_AMT [요금 선납 금액]
	 *		- field : FEE_PPAY_XCL_CNT [요금 선납 정산 건수]
	 *		- field : SLIP_DT [요금 선납 전표 일자]
	 *		- field : SLIP_NO [요금 선납 전표 번호]
	 *		- field : SLIP_ST [요금 선납 전표 상태]
	 *		- field : FEE_PPAY_CNCL_SLIP_DT [요금 선납 취소 전표 일자]
	 *		- field : FEE_PPAY_CNCL_SLIP_NO [요금 선납 취소 전표 번호]
	 *		- field : DEALCO_BLICENS_NO [사업자 번호]
	 *		- field : YYYY [전표 년도]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveEqpClubTXclSlipDel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSSXBase", "fSaveEqpClubTXclSlipDel", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금클럽T엑셀업로드검증</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:45:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_IN_EXCEL_LIST
	 *		- field : FEE_PPAY_STRD_YM [정산기준년월]
	 *		- field : CNSL_MGMT_NO [접수관리번호]
	 *		- field : XCL_AMT [정산금액]
	 *		- field : CNSL_DT [상담일자]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : INVE_TYP_CD [재고유형]
	 *		- field : PRCH_MGMT_NO [매입관리번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_ST [단말기등급]
	 *		- field : REPV_AGN_ORG_CD [조직코드]
	 *		- field : CLCT_DT [회수일자]
	 *		- field : FEE_DEDC_PROC_DT [공제처리일자]
	 *		- field : SKN_JDG_AMT [SKN감정금액]
	 *		- field : SKN_SMPL_JDG_DAMT [SKN샘플감정차액]
	 *		- field : AGN_DDCT_AMT [차감금액]
	 *		- field : AGN_EXPNS_CONF_AMT [대리점비용확정금액]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EXCEL_LIST
	 *		- field : FEE_PPAY_STRD_YM [정산기준년월]
	 *		- field : CNSL_MGMT_NO [접수관리번호]
	 *		- field : XCL_AMT [정산금액]
	 *		- field : CNSL_DT [상담일자]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : INVE_TYP_CD [재고유형]
	 *		- field : PRCH_MGMT_NO [매입관리번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_ST [단말기등급]
	 *		- field : REPV_AGN_ORG_CD [조직코드]
	 *		- field : CLCT_DT [회수일자]
	 *		- field : FEE_DEDC_PROC_DT [공제처리일자]
	 *		- field : SKN_JDG_AMT [SKN감정금액]
	 *		- field : SKN_SMPL_JDG_DAMT [SKN샘플감정차액]
	 *		- field : AGN_DDCT_AMT [차감금액]
	 *		- field : AGN_EXPNS_CONF_AMT [대리점비용확정금액]
	 *		- field : ERR_DESC [오류내용]
	 * </pre>
	 */
	public IDataSet pInqEqpClubTXclExcelErrLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSSXBase", "fInqEqpClubTXclExcelErrLst", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금클럽T엑셀저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:45:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_IN_EXCEL_LIST
	 *		- field : FEE_PPAY_STRD_YM [정산기준년월]
	 *		- field : INVE_TYP_CD [재고유형]
	 *		- field : CNSL_MGMT_NO [접수관리번호]
	 *		- field : PRCH_MGMT_NO [매입관리번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : EQP_ST [단말기등급]
	 *		- field : REPV_AGN_ORG_CD [조직코드]
	 *		- field : CNSL_DT [상담일자]
	 *		- field : CLCT_DT [회수일자]
	 *		- field : FEE_DEDC_PROC_DT [공제처리일자]
	 *		- field : XCL_AMT [정산금액]
	 *		- field : SKN_JDG_AMT [SKN감정금액]
	 *		- field : SKN_SMPL_JDG_DAMT [SKN샘플감정차액]
	 *		- field : AGN_DDCT_AMT [차감금액]
	 *		- field : AGN_EXPNS_CONF_AMT [대리점비용확정금액]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveEqpClubTXclExcel(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);
    
        try {
            // 1. FM 호출
            requestData.putField("USERNO", ca.getUserNo());
            responseData = callSharedBizComponentByDirect("ep.EPSSXBase", "fSaveEqpClubTXclExcel", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }
  
}
