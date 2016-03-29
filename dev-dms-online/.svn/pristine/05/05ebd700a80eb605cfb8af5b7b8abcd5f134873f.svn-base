package dms.nr.nrsscbase.biz;

import java.util.Map;

import fwk.utils.HpcUtils;
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
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]계약손해배상금관리</li>
 * <li>설  명 : <pre>계약손해배상금관리FU</pre></li>
 * <li>작성일 : 2015-07-17 11:21:09</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class FNRCntrtCmpMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNRCntrtCmpMgmt(){
		super();
	}

	/**
	 * <pre>계약손해배상금조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 11:21:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : OP_PROC_STA_DT [해지조회시작일]
	 *	- field : OP_PROC_END_DT [해지조회종료일]
	 *	- field : NEW_CNTRTR_NM [고객명]
	 *	- field : SVC_NO [회선번호]
	 *	- field : AGN_CD [반납대리점코드]
	 *	- field : EQP_JDG_RSLT_CD [손해배상금구분]
	 *	- field : OP_CL_CD [업무구분]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_CNTRT_CMP_LIST
	 *		- field : ASSET_NO [자산번호]
	 *		- field : EQP_JDG_SEQ [단말기감정순번]
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : NEW_CNTRTR_NM [고객명]
	 *		- field : SVC_NO [회선번호]
	 *		- field : EQP_CMP_AMT_TOT [단말기배상금액합계]
	 *		- field : EQP_JDG_RSLT_CD [단말기감정결과코드]
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_MDL_NM [단말기보델명]
	 *		- field : PET_NM [단말기펫네임]
	 *		- field : CNTRT_PRD [계약기간]
	 *		- field : CNTRT_STA_DT [계약시작일]
	 *		- field : CNTRT_END_DT [계약종료일]
	 *		- field : AGN_CD [대리점코드]
	 *		- field : AGN_NM [대리점명]
	 *		- field : CNTRT_ST [계약상태]
	 *		- field : OP_DT [해지일]
	 *		- field : EQP_JDG_DT [단말기감정일자]
	 *		- field : JDG_CHRGR_NO [감정담당자번호]
	 *		- field : JDG_CHRGR_NM [감정담당자명]
	 *		- field : EQP_IN_DT [단말기입고일자]
	 * </pre>
	 */
	public IDataSet fInqCntrtCmpLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    IDataSet dsCnt = new DataSet();
	    requestData.putField( "SVC_NO", HpcUtils.encodeByAES(requestData.getField("SVC_NO")) );
	    requestData.putField( "NEW_CNTRTR_NM", HpcUtils.encodeByAES(requestData.getField("NEW_CNTRTR_NM")) );
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        IRecordSet cmpPolyListRs = null;
        int intTotalCnt = 0;

        try {
            // 1. DU lookup
            DNRCntrtCmpMgmt dNRCntrtCmpMgmt = (DNRCntrtCmpMgmt) lookupDataUnit(DNRCntrtCmpMgmt.class);
            
            // 2. TOTAL COUNT DM호출
            dsCnt = dNRCntrtCmpMgmt.dSCntrtCmpTotCnt(requestData, onlineCtx);
            intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
            PagenateUtils.setPagenatedParamsToDataSet(reqDs);

            // 3. LISTDM호출
            responseData = dNRCntrtCmpMgmt.dSCntrtCmpLstPaging(reqDs, onlineCtx);
            cmpPolyListRs = responseData.getRecordSet("RS_CNTRT_CMP_LIST");
            PagenateUtils.setPagenatedParamToRecordSet(cmpPolyListRs, reqDs, intTotalCnt);
            
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
	    return responseData;
	}

	/**
	 * <pre>계약손해배상금상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 11:21:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ASSET_NO [자산번호]
	 *	- field : EQP_JDG_SEQ [단말기감정순번]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_CNTRT_CMP_DTL
	 *		- field : DCP_NO [필드1]
	 *		- field : INSP_CL_CD [필드1]
	 *		- field : INSP_ITM_CD [필드2]
	 *		- field : INDF_AMT [필드3]
	 *		- field : INSP_DTL_ITM_CD [필드2]
	 *		- field : DEL_YN [필드4]
	 * </pre>
	 */
	public IDataSet fInqCntrtCmpDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        try {
            // 1. DU lookup
            DNRCntrtCmpMgmt dNRCntrtCmpMgmt = (DNRCntrtCmpMgmt) lookupDataUnit(DNRCntrtCmpMgmt.class);

            // 2. 상세DM호출
            responseData = dNRCntrtCmpMgmt.dSCntrtCmpDtl(requestData, onlineCtx);            
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
        
	    return responseData;
	}
  
}
