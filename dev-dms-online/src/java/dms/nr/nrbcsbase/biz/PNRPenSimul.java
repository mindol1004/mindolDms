package dms.nr.nrbcsbase.biz;

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
 * <li>단위업무명: [PU]위약금시뮬레이션</li>
 * <li>설  명 : <pre>[PU]위약금시뮬레이션</pre></li>
 * <li>작성일 : 2015-08-10 18:23:01</li>
 * <li>작성자 : 문재웅 (jaiwoong)</li>
 * </ul>
 *
 * @author 문재웅 (jaiwoong)
 */
public class PNRPenSimul extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNRPenSimul(){
		super();
	}

    /**
	 * <pre>[PM]위약금기본정보조회</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-08-10 18:23:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CNTRT_NO [계약번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_PEN_BASE_INFO
	 *		- field : TOT_RENTAL_FEE_RT [총렌탈료비율]
	 *		- field : REM_RENTAL_FEE_RT [잔여렌탈료비율]
	 *		- field : DAY_RENTAL_FEE_RT [일렌탈료비율]
	 *		- field : RENTAL_FEE_RT [렌탈료비율(연체가산 월렌탈료)]
	 *		- field : PEN_POLY_APLY_DT [위약금정책반영일자:반납지연기준일수]
	 *		- field : RENTAL_PRN [렌탈원금]
	 *		- field : RENTAL_FEE [렌탈요금]
	 * </pre>
	 */
	public IDataSet pPenBaseInfo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSCSBase", "fPenBaseInfo", requestData, onlineCtx);
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
	 * <pre>[PM]위약금시뮬레이션조회</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-08-10 18:23:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CNTRT_NO [계약번호]
	 *	- field : SVC_NO [서비스번호]
	 *	- field : RENTAL_CNTRT_STA_DT [렌탈계약시작일자]
	 *	- field : RENTAL_CNTRT_END_DT [렌탈계약종료일자]
	 *	- field : TERM_SCHD_DT [해지예정일]
	 *	- field : RTN_SCHD_DAY [반납예정일]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_PEN_SIMUL
	 *		- field : RTN_SCHD_DAY [반납예정일]
	 *		- field : DAY_RENTAL_COST_AMT [일렌탈료]
	 *		- field : REM_DAY [잔여일수]
	 *		- field : RENTAL_FEE [렌탈요금:월렌탈료]
	 *		- field : REM_RENTAL_COST_AMT [잔여렌탈료]
	 *		- field : SUSPR_PEN [중도해지위약금]
	 *		- field : PEN_PRD_FR [손해배상금 위약금부과기간FROM]
	 *		- field : PEN_PRD_TO [손해배상금 위약금부과기간TO]
	 *		- field : EQIP_USE_MM_TS [단말기사용월수]
	 *		- field : UNRTN_AMT [손해배상금]
	 *		- field : RTN_DLY_PEN_EX_PRD_FR_DT [반납지연위약금면제기간FROM]
	 *		- field : RTN_DLY_PEN_EX_PRD_TO_DT [반납지연위약금면제기간TO]
	 *		- field : RTN_DLY_PEN_APLY_PRD_FR_DT [반납지연위약금적용기간FROM]
	 *		- field : RTN_DLY_PEN_APLY_PRD_TO_DT [반납지연위약금적용기간TO]
	 *		- field : RTN_DLY_CNT [반납지연일수]
	 *		- field : REAL_DAY_CNT [반납지연위약금 실제기간일수]
	 *		- field : RTN_DLY_PEN [반납지연위약금]
	 *		- field : ARR_ADD_AMT [연체가산금]
	 * </pre>
	 */
	public IDataSet pPenSimul(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSCSBase", "fPenSimul", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        // 3. 결과값 리턴
        responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
    
        return responseData;
    }
  
}
