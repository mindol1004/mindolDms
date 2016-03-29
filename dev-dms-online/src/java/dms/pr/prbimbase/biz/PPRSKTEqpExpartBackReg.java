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
 * <li>단위업무명: [PU]SKT교품반환등록</li>
 * <li>설  명 : <pre>[FU]SKT교품반환등록</pre></li>
 * <li>작성일 : 2015-08-31 18:49:27</li>
 * <li>작성자 : 양재석 (jsyang)</li>
 * </ul>
 *
 * @author 양재석 (jsyang)
 */
public class PPRSKTEqpExpartBackReg extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PPRSKTEqpExpartBackReg(){
		super();
	}

    /**
	 * <pre>SKT교품반환등록조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-31 18:49:27
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CLCT_FR_DT [회수조회시작일자]
	 *	- field : CLCT_TO_DT [회수조회종료일자]
	 *	- field : ACCEPT_FR_DT [확정조회시작일자]
	 *	- field : ACCEPT_TO_DT [확정조회종료일자]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : EQP_SER_NO [단말기일련번호]
	 *	- field : AGN_CD [대리점코드]
	 *	- field : PROC_ST [처리상태]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EXPART_BACK_LIST
	 *		- field : ROWNO [ROWNO]
	 *		- field : EQP_IN_DT [회수일자]
	 *		- field : AGN_CD [대리점코드]
	 *		- field : AGN_NM [대리점명]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EQP_COLOR_CD [단말기색상코드]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : EQP_JDG_RSLT_CD [감정결과]
	 *		- field : EX_EQP_MDL_NM [교품모델명]
	 *		- field : EX_EQP_SER_NO [교품단말기일련번호]
	 *		- field : EX_EQP_COLOR_CD [교품단말기색상코드]
	 *		- field : EXPART_DT [교품일자]
	 *		- field : PROC_ST [처이상태]
	 *		- field : EXP_DT [경과기간]
	 *		- field : CNTRT_NO [계약번호]
	 *		- field : EX_EQP_IMEI_NO [EX_EQP_IMEI_NO]
	 *		- field : EX_ASSET_NO [교품단말기자산번호]
	 * </pre>
	 */
	public IDataSet pInqSKTEqpExpartBackRegList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 2. FM 호출
            responseData = callSharedBizComponentByDirect("pr.PRSIMBase", "fInqSKTEqpExpartBackRegList", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>[PM]매입단말기조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-31 18:49:27
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : EQP_SER_NO [단말기일련번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_IN_EQP_LIST
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_IMEI_NO [EQP_IMEI_NO]
	 *		- field : EQP_COLOR_CD [색상코드]
	 *		- field : EQP_IN_DT [입고일자]
	 *		- field : ASSET_NO [자산번호]
	 * </pre>
	 */
	public IDataSet pInqInEqpList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 2. FM 호출
            responseData = callSharedBizComponentByDirect("pr.PRSIMBase", "fInqInEqpList", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>SKT교품반환등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-31 18:49:27
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RQ_EXPART_BACK_LIST
	 *		- field : ASSET_NO [자산번호]
	 *		- field : CNTRT_NO [계약번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_COLOR_CD [색상코드]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EX_EQP_SER_NO [교품단말기일련번호]
	 *		- field : EX_EQP_IMEI_NO [교품단말기IMEI번호]
	 *		- field : EX_EQP_COLOR_CD [교품단말기색상]
	 *		- field : EX_ASSET_NO [교품자산번호]
	 *		- field : EXPART_DT [교품일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pExpartObjEqpReg(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("pr.PRSIMBase", "fExpartObjEqpReg", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        // 3. 결과값 리턴
        responseData.setOkResultMessage("DMS00000", null); // 정상처리회되었습니다.
    
        return responseData;
    }

    /**
	 * <pre>반환확정단말기등록</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-08-31 18:49:27
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RQ_BACK_LIST
	 *		- field : EQP_OUT_DT [출고일자]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pBackObjEqpReg(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("pr.PRSIMBase", "fBackObjEqpReg", requestData, onlineCtx);
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
