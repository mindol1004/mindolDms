package dms.bc.bcbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/업무공통</li>
 * <li>단위업무명: [FU]메인화면관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-12-09 16:49:59</li>
 * <li>작성자 : 정동현 (jjddhh123)</li>
 * </ul>
 *
 * @author 정동현 (jjddhh123)
 */

public class FBCMainMgmt extends fwk.base.FunctionUnit {
	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FBCMainMgmt(){
		super();
	}

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-12-09 16:49:59
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
	public IDataSet fInqMthSaleRate(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        try {
            // 1. DU lookup
            DBCMainMgmt dBCMainMgmt = (DBCMainMgmt)lookupDataUnit(DBCMainMgmt.class);
            
            // 2. LISTDM호출
            responseData.putRecordSet(dBCMainMgmt.dSMthSaleRate(requestData, onlineCtx).getRecordSet("RS_MTH_SALE_RATE")); //월별판매현황      
            responseData.putRecordSet(dBCMainMgmt.dSPmthSaleRate(requestData, onlineCtx).getRecordSet("RS_PMTH_SALE_RATE")); //전월판매현황
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-12-09 16:49:59
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
	public IDataSet fInqMthPrchsRate(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        try {
            // 1. DU lookup
            DBCMainMgmt dBCMainMgmt = (DBCMainMgmt)lookupDataUnit(DBCMainMgmt.class);
            
            // 2. LISTDM호출
            responseData.putRecordSet(dBCMainMgmt.dSMthPrchsRate(requestData, onlineCtx).getRecordSet("RS_MTH_PRCHS_RATE")); //월별판매현황      
            responseData.putRecordSet(dBCMainMgmt.dSPmthPrchsRate(requestData, onlineCtx).getRecordSet("RS_PMTH_PRCHS_RATE")); //전월판매현황
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
        return responseData;
    }
  
}
