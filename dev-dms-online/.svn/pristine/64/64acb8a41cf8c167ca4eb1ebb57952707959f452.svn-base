package dms.nr.nrbscbase.biz;

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
 * <li>단위업무명: [PU]계약손해배상금관리</li>
 * <li>설  명 : <pre>계약손해배상금관리PU</pre></li>
 * <li>작성일 : 2015-07-17 10:28:12</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class PNRCntrtCmpMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNRCntrtCmpMgmt(){
		super();
	}

	/**
	 * <pre>계약손해배상금조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 10:28:12
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
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : EQP_SER_NO [단말기일련번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_CNTRT_CMP_LIST
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : NEW_CNTRTR_NM [고객명]
	 *		- field : SVC_NO [서비스번호]
	 *		- field : DCP_CL [손해배상금정책구분]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : DMS_DMG_CMP_AMT [손해배상금]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : PET_NM [펫네임]
	 *		- field : RENTAL_CNTRT_STA_DT [렌탈시작일자]
	 *		- field : RENTAL_CNTRT_END_DT [렌탈종료일자]
	 *		- field : AGN_CD [대리점코드]
	 *		- field : AGN_NM [대리점명]
	 *		- field : CNTRT_PRD [계약기간]
	 *		- field : OP_TYP_CD [업무처리상태]
	 *		- field : OP_PROC_DT [해지일]
	 * </pre>
	 */
	public IDataSet pInqCntrtCmpLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
       try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSSCBase", "fInqCntrtCmpLst", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
	        
	    return responseData;
	}

	/**
	 * <pre>계약손해배상금상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 10:28:12
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ASSET_NO [자산번호]
	 *	- field : EQP_JDG_SEQ [단말기감정순번]
	 *	- field : DCP_NO [필드1]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_CNTRT_CMP_DTL
	 *		- field : DCP_NO [손해배상금정책번호]
	 *		- field : INSP_CL_CD [구분]
	 *		- field : INSP_ITM_CD [점검사항]
	 *		- field : EQP_INDF_AMT [손해배상금]
	 *		- field : INSP_DTL_ITM_CD [세부점검사항]
	 *		- field : DEL_YN [삭제여부]
	 * </pre>
	 */
	public IDataSet pInqCntrtCmpDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
       try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSSCBase", "fInqCntrtCmpDtl", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
        
        // 2. 결과값 리턴
        responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
	    return responseData;
	}
  
}
