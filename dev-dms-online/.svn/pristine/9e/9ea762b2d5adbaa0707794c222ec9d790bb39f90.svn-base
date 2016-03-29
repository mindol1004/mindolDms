package dms.bc.bcbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/업무공통</li>
 * <li>단위업무명: [DU]메인화면관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-12-09 16:50:14</li>
 * <li>작성자 : 정동현 (jjddhh123)</li>
 * </ul>
 *
 * @author 정동현 (jjddhh123)
 */
public class DBCMainMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DBCMainMgmt(){
		super();
	}

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-12-09 16:51:22
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSMthSaleRate(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecordSet rsReturn = dbSelect("SMthSaleRate", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putRecordSet("RS_MTH_SALE_RATE", rsReturn);
        
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-12-09 16:50:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSPmthSaleRate(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        IRecordSet rsReturn = dbSelect("SPmthSaleRate", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putRecordSet("RS_PMTH_SALE_RATE", rsReturn);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-12-10 13:05:51
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSMthPrchsRate(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        IRecordSet rsReturn = dbSelect("SMthPrchsRate", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putRecordSet("RS_MTH_PRCHS_RATE", rsReturn);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-12-10 13:07:12
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSPmthPrchsRate(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
        IRecordSet rsReturn = dbSelect("SPmthPrchsRate", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putRecordSet("RS_PMTH_PRCHS_RATE", rsReturn);
    
        return responseData;
    }
  
}
