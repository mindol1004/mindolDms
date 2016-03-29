package dms.nr.nrbeibase.biz;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import fwk.common.CommonArea;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.xml.DataSetXmlTransformer;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [PU]고정자산감가상각조회</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-03 09:39:45</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class PNRComStlMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNRComStlMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-10-16 10:24:37
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : DEPR_STRD_YM [기준년월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_CMMS_LST
	 *		- field : XCL_YM [정산월]
	 *		- field : CNTRT_NO [계약번호]
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : NEW_CNTRTR_NM [고객명]
	 *		- field : NEW_CNTRTR_NM_ENPT [고객명암호화]
	 *		- field : SVC_NO [서비스번호]
	 *		- field : SVC_NO_ENPT [서비스번호암호화]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : OUT_PRC [출고가]
	 *		- field : EQP_INSURE_FEE [단말기보험료]
	 *		- field : EQP_DAY_INSURE_FEE [단말기일보험료]
	 *		- field : SCRB_NOD [일수]
	 *		- field : EQP_INSURE_FEE_TOT [단말기보험료합계]
	 *		- field : RENTAL_CNTRT_STA_DT [계약시작일]
	 *		- field : RENTAL_CNTRT_END_DT [계약종료일]
	 *		- field : SLIP_NO [전표번호]
	 *		- field : SLIP_DT [전표일자]
	 *		- field : SLIP_ST_CD [전표상태]
	 *		- field : TAX_BILL_NO [세금계산서번호]
	 *		- field : TAX_BILL_ST_CD [세금계산서상태]
	 *		- field : OP_PROC_DT [업무처리일]
	 *		- field : OP_TYP_CD [계약상태]
	 *	- record : RS_SUM_LIST
	 *		- field : CNT [총건수]
	 *		- field : AMT [감가상각총액]
	 *		- field : ALLWN_AMT [충당부채총액]
	 * </pre>
	 */
    public IDataSet pInqComStlLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
    		// 1. FM 호출
    		responseData = callSharedBizComponentByDirect("nr.NRSEIBase", "fInqComStlLst", requestData, onlineCtx);
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
	 * @author 이영진 (newnofixing)
	 * @since 2015-10-16 10:24:37
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : DEPR_STRD_YM [감가상각월]
	 *	- field : IS_RECALL [전표발행/취소 구분값]
	 *	- field : YYYY [취소전표처리년도]
	 *	- field : SLIP_NO [전표번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet pComStlOnlineCall(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            
            responseData = callSharedBizComponentByDirect("nr.NRSEIBase", "fComStlOnlineCall", requestData, onlineCtx);
  
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
	 * @author 박홍서 (uni9401)
	 * @since 2015-10-16 10:24:37
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : DEPR_STRD_YM [정산월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet pComStlCallOnline(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);
    
    	try {
    		requestData.putField("USERNO", ca.getUserNo());
    		
    		responseData = callSharedBizComponentByDirect("nr.NRSEIBase", "fComStlCallOnline", requestData, onlineCtx);
    		
    		responseData.setOkResultMessage("DMS00000", null); //정상 처리되었습니다.
    	} catch ( BizRuntimeException e ) {
    		throw e;
    	} catch ( Exception e ) {
    		throw new BizRuntimeException("DMS00009", e); //시스템 오류
    	}
    	
        return responseData;
    }

    /**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-10-20 09:55:40
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : DEPR_STRD_YM [정산월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveXclDept(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSEIBase", "fRegXclDept", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        // 2. 결과값 리턴
        responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
        return responseData;
    }
  
}
