package dms.nr.nrbxmbase.biz;

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
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [PU]대리점판매수수료정산관리</li>
 * <li>설  명 : <pre>대리점판매수수료정산관리</pre></li>
 * <li>작성일 : 2015-08-05 10:33:55</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class PNRAgnSellComMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNRAgnSellComMgmt(){
		super();
	}

	/**
	 * <pre>대리점판매수수료정산정보조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-08-05 10:33:55
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : XCL_YM [정산년월]
	 *	- field : AGN_ORG_CD [대리점코드]
	 *	- field : ORG_NM [대리점명]
	 *	- field : YYYYMM [필드1]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : DEBT_DEALCO_CD [대리점코드]
	 *		- field : ORG_NM [대리점명]
	 *		- field : DEBT_ITM_AMT [수수료]
	 *		- field : DEBT_XCL_YM [판매정산년월]
	 *		- field : SLIP_NO [전표번호]
	 *		- field : SLIP_ST_CD [전표상태]
	 *		- field : TAX_BILL_ST_CD [세금계산서상태코드]
	 *		- field : TAX_BILL_NO [세금계산서번호]
	 *		- field : DEBT_BIZ_REG_NO [사업자번호]
	 *		- field : YYYYMM [필드1]
	 *		- field : YYYY [필드1]
	 *		- field : M_CNT [필드1]
	 *		- field : M_PRC [필드1]
	 *		- field : DEALCO_FISCL_ST_CD [ERP거래처코드]
	 *		- field : DEBT_ITM_AMT_CNT [건수]
	 *	- record : RS_SUM_LIST
	 *		- field : M_PRC [총금액]
	 * </pre>
	 */
	public IDataSet pInqAgnSellComLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		
	    try {
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("nr.NRSXMBase", "fInqAgnSellComLst", requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 2. 결과값 리턴
		responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
	
	    return responseData;
	}

	/**
	 * <pre>대리점판매수수료정산정보상세 조회</pre>
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-08-05 10:33:55
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : XCL_YM [정산년월]
	 *	- field : DEBT_DEALCO_CD [대리점코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_AGN_SELL_COM_DTL
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : ORG_NM [거래처명]
	 *		- field : XCL_AMT [수수료]
	 *		- field : XCL_DT [정산년월]
	 *		- field : NEW_CNTRT_DT [계약일]
	 * </pre>
	 */
	public IDataSet pInqAgnSellComDtl(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		
	    try {
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("nr.NRSXMBase", "fInqAgnSellComDtl", requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 2. 결과값 리턴
		responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
	
	    return responseData;
	}

    /**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-08-05 10:33:55
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : XCL_YM [정산년월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveAgnSellReBatch(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSXMBase", "fSaveAgnSellReBatch", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        // 2. 결과값 리턴
        responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
        return responseData;
    }

    /**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-08-05 10:33:55
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : DEBT_DEALCO_CD [대리점코드]
	 *		- field : ORG_NM [대리점명]
	 *		- field : DEBT_ITM_AMT [수수료]
	 *		- field : DEBT_XCL_YM [정산년월]
	 *		- field : DEBT_BIZ_REG_NO [사업자번호]
	 *		- field : SLIP_NO [전표번호]
	 *		- field : XCL_ST_CD [정산상태]
	 *		- field : YYYYMM [필드1]
	 *		- field : M_CNT [필드1]
	 *		- field : M_PRC [필드1]
	 *		- field : DEALCO_FISCL_ST_CD [ERP거래처상태코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveAgnSellSlip(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSXMBase", "fSaveAgnSellSlip", requestData, onlineCtx);
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
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-08-05 10:33:55
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : DEBT_DEALCO_CD [대리점코드]
	 *		- field : ORG_NM [대리점명]
	 *		- field : DEBT_ITM_AMT [판매수수료]
	 *		- field : DEBT_XCL_YM [정산년월]
	 *		- field : DEBT_BIZ_REG_NO [사업자번호]
	 *		- field : SLIP_NO [전표번호]
	 *		- field : XCL_ST_CD [정산상태]
	 *		- field : YYYY [필드1]
	 *		- field : DEALCO_FISCL_ST_CD [필드1]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveAgnSellSlipDel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSXMBase", "fSaveAgnSellSlipDel", requestData, onlineCtx);
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
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-08-05 10:33:55
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : XCL_YM [년월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pInqAgnSellAllExcel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSXMBase", "fInqAgnSellAllExcel", requestData, onlineCtx);
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
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-05 10:33:55
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : YYYY [필드1]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pInqAgnSellSlipStSearch(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSXMBase", "fInqAgnSellSlipStSearch", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        return responseData;
	}
  
}
