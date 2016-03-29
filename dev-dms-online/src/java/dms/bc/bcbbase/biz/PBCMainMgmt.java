package dms.bc.bcbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/업무공통</li>
 * <li>단위업무명: [PU]메인화면관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-12-09 16:44:43</li>
 * <li>작성자 : 정동현 (jjddhh123)</li>
 * </ul>
 *
 * @author 정동현 (jjddhh123)
 */
public class PBCMainMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PBCMainMgmt(){
		super();
	}

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-12-09 16:44:43
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_PMTH_SALE_RATE
	 *		- field : MONTH [판매년월]
	 *		- field : LABEL [판매월]
	 *		- field : VAL [판매건수]
	 *		- field : EXPR_VAL [필드1]
	 *		- field : COLOR [색상]
	 *	- record : RS_MTH_SALE_RATE
	 *		- field : MONTH [판매년월]
	 *		- field : LABEL [판매월]
	 *		- field : VAL [판매건수]
	 *		- field : EXPR_VAL [필드1]
	 *		- field : COLOR [색상]
	 * </pre>
	 */
	public IDataSet pInqMthSaleRate(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        try {
            // 1. FU lookup
            FBCMainMgmt fBCMainMgmt = (FBCMainMgmt) lookupFunctionUnit(FBCMainMgmt.class);
            // 2. FM 호출
            responseData = fBCMainMgmt.fInqMthSaleRate(requestData, onlineCtx);
            // responseData.putRecordSet("RS_ADDR_DTL", responseData.getRecordSet("rsAddrDtl"));
            // 3. 결과값 리턴
            responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
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
	 * @author 정동현 (jjddhh123)
	 * @since 2015-12-09 16:44:43
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_PMTH_PRCHS_RATE
	 *		- field : MONTH [판매년월]
	 *		- field : LABEL [판매월]
	 *		- field : VAL [판매건수]
	 *		- field : EXPR_VAL [필드1]
	 *		- field : COLOR [색상]
	 *	- record : RS_MTH_PRCHS_RATE
	 *		- field : MONTH [판매년월]
	 *		- field : LABEL [판매월]
	 *		- field : VAL [판매건수]
	 *		- field : EXPR_VAL [필드1]
	 *		- field : COLOR [색상]
	 * </pre>
	 */
	public IDataSet pInqMthPrchsRate(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        try {
            // 1. FU lookup
            FBCMainMgmt fBCMainMgmt = (FBCMainMgmt) lookupFunctionUnit(FBCMainMgmt.class);
            // 2. FM 호출
            responseData = fBCMainMgmt.fInqMthPrchsRate(requestData, onlineCtx);
            // responseData.putRecordSet("RS_ADDR_DTL", responseData.getRecordSet("rsAddrDtl"));
            // 3. 결과값 리턴
            responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
        return responseData;
    }
  
}
