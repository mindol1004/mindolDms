package dms.pr.prbimbase.biz;

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
 * <li>업무 그룹명 : dms/임대R</li>
 * <li>단위업무명: [PU]단말기매각관리</li>
 * <li>설  명 : <pre>단말기매각관리</pre></li>
 * <li>작성일 : 2015-09-01 17:16:20</li>
 * <li>작성자 : 양재석 (jsyang)</li>
 * </ul>
 *
 * @author 양재석 (jsyang)
 */
public class PPREqpDpspslMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PPREqpDpspslMgmt(){
		super();
	}

    /**
	 * <pre>[PM]매각대상단말기조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-01 17:16:20
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CLCT_FR_DT [회수조회시작일자]
	 *	- field : CLCT_TO_DT [회수조회종료일자]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : EQP_SER_NO [단말기일련번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_DPSPSL_EQP_LIST
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EQP_COLOR_CD [색상코드]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : EQP_IN_DT [회수입고일자]
	 *		- field : FISCL_REMPRC [회계잔존가]
	 *		- field : EQP_JDG_RSLT_CD [단말기감정결과코드]
	 *		- field : EQP_OUT_DT [출고일자(매각일자)]
	 *		- field : CONF_YN [확정여부]
	 *		- field : EXP_DT [경과기간]
	 *		- field : RENT_END_DT [계약종료일]
	 *		- field : CNTRT_NO [계약번호]
	 * </pre>
	 */
	public IDataSet pInqDpspslObjEqpList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 2. FM 호출
            responseData = callSharedBizComponentByDirect("pr.PRSIMBase", "fInqDpspslObjEqpList", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>[PM]매각대상단말기등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-01 17:16:20
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RQ_DPSPSL_EQP_LIST
	 *		- field : ASSET_NO [자산번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EQP_COLOR_CD [색상코드]
	 *		- field : EQP_OUT_DT [출고일자]
	 *		- field : IN_DEALCO_CD [입고거래처코드]
	 *		- field : IN_CHRG_DEPT_CD [입고부서코드]
	 *		- field : OUT_DEALCO_CD [출고거래처코드]
	 *		- field : OUT_CHRG_DEPT_CD [출고부서코드]
	 *		- field : IN_DTL_CD [입고상세코드]
	 *		- field : OUT_DTL_CD [출고상세코드]
	 *		- field : CNTRT_NO [계약번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pDpspslObjEqpReg(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("pr.PRSIMBase", "fDpspslObjEqpReg", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        // 3. 결과값 리턴
        responseData.setOkResultMessage("DMS00000", null); // 정상처리회되었습니다.
    
        return responseData;
    }
  
}
