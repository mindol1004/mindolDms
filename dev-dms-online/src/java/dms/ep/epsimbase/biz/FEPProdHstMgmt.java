package dms.ep.epsimbase.biz;

import java.util.Map;

import fwk.utils.PagenateUtils;
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
 * <li>업무 그룹명 : dms/중고폰</li>
 * <li>단위업무명: [FU]박스재고관리</li>
 * <li>설  명 : <pre>[FU]박스재고관리</pre></li>
 * <li>작성일 : 2015-07-22 18:09:54</li>
 * <li>작성자 : 이준우 (elmsliw)</li>
 * </ul>
 *
 * @author 이준우 (elmsliw)
 */
public class FEPProdHstMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FEPProdHstMgmt(){
		super();
	}

	/**
	 * <pre>[FM]상품이력조회</pre>
	 *
	 * @author 이준우 (elmsliw)
	 * @since 2015-08-31 10:26:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : BF_SER_NO [박스번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_PH_LIST
	 *		- field : FS_REG_DTM [발생일자]
	 *		- field : OUT_DEALCO_NM [출고처]
	 *		- field : IN_DEALCO_NM [입고처]
	 *		- field : IN_OUT_DT [입출고일자]
	 *		- field : IN_OUT_NM [입출고구분]
	 *		- field : IN_OUT_DTL_NM [입출고상세구분]
	 *		- field : EQP_SER_NO [상품일련번호]
	 *		- field : FS_REG_USER_NM [발생자]
	 *		- field : CNSL_MGMT_NO [상담관리번호]
	 * </pre>
	 */
    public IDataSet fInqProdHstLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet dsCnt = new DataSet();
        
    	IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
    	IRecordSet rsPagingList = null;
    	int intTotalCnt = 0;
    	
    	try {
    		// 1. DU lookup
    	    DEPProdHstMgmt dEPProdHstMgmt = (DEPProdHstMgmt) lookupDataUnit(DEPProdHstMgmt.class);
    		
    		// 2. TOTAL COUNT DM호출
    		dsCnt = dEPProdHstMgmt.dSProdHstLstTotCnt(reqDs, onlineCtx);
    		intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));

    		PagenateUtils.setPagenatedParamsToDataSet(reqDs);
    		
    		// 3. LISTDM호출
    		responseData = dEPProdHstMgmt.dSProdHstLstPaging(reqDs, onlineCtx);
    
    		rsPagingList = responseData.getRecordSet("RS_PH_LIST");
    		PagenateUtils.setPagenatedParamToRecordSet(rsPagingList, reqDs, intTotalCnt);
            
    	} catch ( BizRuntimeException e ) {
    		throw e;
    	} catch ( Exception e ) {
    		throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
    	}	
    
        return responseData;
    }

    /**
	 * <pre>[FM]상품정보조회</pre>
	 *
	 * @author 이준우 (elmsliw)
	 * @since 2015-08-31 10:26:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : BF_SER_NO [이전시리얼번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_PH_INFO
	 *		- field : EQP_MDL_CD [단말기 모델 코드]
	 *		- field : EQP_MDL_NM [단말기 모델명]
	 *		- field : EQP_COLOR_CD [단말기 색상 코드]
	 *		- field : EQP_COLOR_NM [단말기생상]
	 *		- field : EQP_SER_NO [단말기 일련 번호]
	 *		- field : EQP_ST [단말기 상태]
	 *		- field : INVE_ST [재고 상태]
	 *		- field : BF_SER_NO [이전 일련 번호]
	 *		- field : CNSL_MGMT_NO [상담 관리 번호]
	 *		- field : HOLD_AMT [유보금]
	 * </pre>
	 */
    public IDataSet fInqProdHstInfo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
    	IDataSet reqDs = (IDataSet) requestData.clone();
    	
    	try {
    		// 1. DU lookup
    	    DEPProdHstMgmt dEPProdHstMgmt = (DEPProdHstMgmt) lookupDataUnit(DEPProdHstMgmt.class);
    
    		// 2. 상품정보조회
            responseData = dEPProdHstMgmt.dSProdHstInfo(reqDs, onlineCtx);
            
    	} catch ( BizRuntimeException e ) {
    		throw e;
    	} catch ( Exception e ) {
    		throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
    	}	
    
        return responseData;
    }
  
}
