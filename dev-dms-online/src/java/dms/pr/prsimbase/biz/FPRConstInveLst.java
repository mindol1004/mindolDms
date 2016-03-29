package dms.pr.prsimbase.biz;

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
 * <li>업무 그룹명 : dms/임대R</li>
 * <li>단위업무명: [FU]임대폰분실관리</li>
 * <li>설  명 : <pre>[FU]임대폰분실관리</pre></li>
 * <li>작성일 : 2015-07-22 18:09:54</li>
 * <li>작성자 : 이준우 (elmsliw)</li>
 * </ul>
 *
 * @author 이준우 (elmsliw)
 */
public class FPRConstInveLst extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FPRConstInveLst(){
		super();
	}

	/**
     * <pre>[FM]임대폰회수정보조회</pre>
     *
     * @author 이준우 (elmsliw)
     * @since 2015-07-23 21:24:15
     *
     * @param requestData 요청정보 DataSet 객체
     * <pre>
     *	- field : ST_DT [기간st]
     *	- field : ED_DT [기간ed]
     *	- field : EQP_MDL_CD [모델코드]
     *	- field : EQP_MDL_NM [모델명]
     *	- field : EQP_SER_NO [시리얼넘버]
     *	- field : BOX_NO [BOX_NO]
     *	- field : IN_OBJ_DTL_CD [회수구분코드]
     * </pre>
     * @param onlineCtx   요청 컨텍스트 정보
     * @return 처리결과 DataSet 객체
     * <pre>
     *	- record : RS_RCIBM_LIST
     *		- field : RTN_REG_DT [반납등록일자]
     *		- field : ASSET_NO [자산번호]
     *		- field : EQP_MDL_NM [단말기모델이름]
     *		- field : EQP_COLOR_CD [단말기색상코드]
     *		- field : EQP_SER_NO [단말기일련번호]
     *		- field : EQP_IMEI_NO [단말기IMEI번호]
     *		- field : AGN_CD [대리점코드]
     *		- field : IN_OBJ_DTL_CD [회수구분코드]
     * </pre>
     */
    /**
	 * <pre>[FM]구성품재고조회</pre>
	 *
	 * @author 이준우 (elmsliw)
	 * @since 2015-09-14 16:36:53
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ST_DT [시작일]
	 *	- field : ED_DT [종료일]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : EQP_MDL_NM [모델명]
	 *	- field : CMPT_CD [구성품코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_CIM_LIST
	 *		- field : CMPT_IN_DT [입출고날짜]
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : CMPT_CD [구성품코드]
	 *		- field : IN_QTY [입고재고]
	 *		- field : OUT_QTY [출고재고]
	 *		- field : TOT__QTY [총재고]
	 * </pre>
	 */
    public IDataSet fInqConstInveLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet dsCnt = new DataSet();
        
    	IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
    	IRecordSet rsPagingList = null;
    	
    	int intTotalCnt = 0;
    	
    	try {
    		// 1. DU lookup
    	    DPRConstInveLst dPRConstInveLst = (DPRConstInveLst) lookupDataUnit(DPRConstInveLst.class);
    		
    		// 2. TOTAL COUNT DM호출
    		dsCnt = dPRConstInveLst.dSConstInveLstTotCnt(reqDs, onlineCtx);
    		intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));  
    		PagenateUtils.setPagenatedParamsToDataSet(reqDs);	//카운트 셋
    		
    		// 3. LIST DM호출
    		responseData = dPRConstInveLst.dSConstInveLstPaging(reqDs, onlineCtx);
    
    		rsPagingList = responseData.getRecordSet("RS_CIM_LIST");
    		PagenateUtils.setPagenatedParamToRecordSet(rsPagingList, reqDs, intTotalCnt);	//리스트 셋
    		
    
    	} catch ( BizRuntimeException e ) {
    		throw e;
    	} catch ( Exception e ) {
    		throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
    	}	
    
        return responseData;
    }
  
}
