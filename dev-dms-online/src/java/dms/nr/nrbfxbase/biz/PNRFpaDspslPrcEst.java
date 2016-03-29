package dms.nr.nrbfxbase.biz;

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
 * <li>단위업무명: [PU]FPA매각가산정</li>
 * <li>설  명 : <pre>[PU]FPA매각가산정</pre></li>
 * <li>작성일 : 2015-10-20 10:49:49</li>
 * <li>작성자 : 문재웅 (jaiwoong)</li>
 * </ul>
 *
 * @author 문재웅 (jaiwoong)
 */
public class PNRFpaDspslPrcEst extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNRFpaDspslPrcEst(){
		super();
	}

    /**
	 * <pre>[PM]FPA매각가산정조회</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-10-20 10:49:49
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : DSPSL_YM [정산년월]
	 *	- field : EQP_MDL_CD [단말기코드]
	 *	- field : EQP_MDL_NM [모델명]
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_FPA_DSPSL_PRC_EST_LIST
	 *		- field : DSPSL_DT [매각일자]
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : CNTRT_NO [계약번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : OUT_PRC [출고가]
	 *		- field : EXPT_DSPSL_PRC [예상매각가격]
	 *		- field : DSPSL_PRC [매각가격]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : RENTAL_POLY_NO [렌탈정책]
	 *		- field : RENTAL_CNTRT_STA_DT [렌탈계약시작일자]
	 *		- field : RENTAL_CNTRT_END_DT [렌탈계약종료일자]
	 * </pre>
	 */
	public IDataSet pFpaDspslPrcEstLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSFXBase", "fFpaDspslPrcEstLst", requestData, onlineCtx);
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
	 * <pre>[PM]FPA매각가산정전체엑셀조회</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-10-20 10:49:49
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : DSPSL_YM [정산년월]
	 *	- field : EQP_MDL_CD [단말기코드]
	 *	- field : EQP_MDL_NM [모델명]
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_FPA_DSPSL_PRC_EST_ALL_EXCEL
	 *		- field : DSPSL_DT [매각일자]
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : CNTRT_NO [계약번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : OUT_PRC [출고가]
	 *		- field : EXPT_DSPSL_PRC [예상매각가격]
	 *		- field : DSPSL_PRC [매각가격]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : RENTAL_POLY_NO [렌탈정책]
	 *		- field : RENTAL_CNTRT_STA_DT [렌탈계약시작일자]
	 *		- field : RENTAL_CNTRT_END_DT [렌탈계약종료일자]
	 * </pre>
	 */
	public IDataSet pFpaDspslPrcEstAllExcel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSFXBase", "fFpaDspslPrcEstAllExcel", requestData, onlineCtx);
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
	 * <pre>[PM]FPA매각가생성/전표생성/전표취소</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-10-20 10:49:49
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : DSPSL_YM [매각년월]
	 *	- field : IS_RECALL [재집계/전표생성 구분값]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pFpaDspslPrcEstOnlineRecall(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. FM 호출
            
            if("01".equals(requestData.getField("IS_RECALL"))){ //재집계            
                responseData = callSharedBizComponentByDirect("nr.NRSFXBase", "fFpaDspslPrcEstOnlineRecall", requestData, onlineCtx);
            }else if("02".equals(requestData.getField("IS_RECALL"))) {//전표생성
                responseData = callSharedBizComponentByDirect("nr.NRSFXBase", "fFpaDspslPrcEstDeprOnlineRecall", requestData, onlineCtx);
            }else if("03".equals(requestData.getField("IS_RECALL"))) {//전표취소
                responseData = callSharedBizComponentByDirect("nr.NRSFXBase", "fFpaDspslPrcEstDeprCnclOnlineRecall", requestData, onlineCtx);
            }
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }

        return responseData;
    }
  
}
