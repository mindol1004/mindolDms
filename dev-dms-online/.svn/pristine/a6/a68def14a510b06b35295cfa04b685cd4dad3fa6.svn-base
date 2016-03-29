package dms.ep.epbfebase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.common.CommonArea;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [PU]FPA단말기관리</li>
 * <li>설  명 : <pre>FPA단말기관리</pre></li>
 * <li>작성일 : 2016-02-16 10:51:17</li>
 * <li>작성자 : 양재석 (jsyang)</li>
 * </ul>
 *
 * @author 양재석 (jsyang)
 */
public class PEPFPAEqpMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PEPFPAEqpMgmt(){
		super();
	}

    /**
	 * <pre>FPA단말기판매평균금액목록조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2016-02-16 10:51:17
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SELL_FR_DT [조회판매시작일자]
	 *	- field : SELL_TO_DT [조회판매종료일자]
	 *	- field : EQP_MDL_CD [단말기모델코드]
	 *	- field : SEND_YN [전송여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_FPA_EQP_SELL_LIST
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : APPLY_DT [적용일자]
	 *		- field : EQP_BKAG_AMT [단말기파손금액]
	 *		- field : SELL_STA_DT [판매시작일자]
	 *		- field : SELL_END_DT [판매종료일자]
	 *		- field : NORM_SELL_TOT_AMT [정상판매합계금액]
	 *		- field : NORM_SELL_QTY [정상판매수량]
	 *		- field : BKAG_SELL_TOT_AMT [파손판매합계금액]
	 *		- field : BKAG_SELL_QTY [파손판매수량]
	 *		- field : SELL_FREQ [판매횟수]
	 *		- field : NORM_SELL_AVG_AMT [정상판매평균금액]
	 *		- field : BKAG_SELL_AVG_AMT [파손판매평균금액]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : MFACT_CD [제조사코드]
	 *		- field : SEND_NM [전송자명]
	 *		- field : SEND_DTM [전송일시]
	 * </pre>
	 */
	public IDataSet pSInqFPAEqpSellAvgAmtList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            responseData = callSharedBizComponentByDirect("ep.EPSFEBase", "fSInqFPAEqpSellAvgAmtList", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }       
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2016-02-16 10:51:17
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_FPA_EQP_SELL_LIST
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : APPLY_DT [적용일자]
	 *		- field : EQP_BKAG_AMT [단말기파손금액]
	 *		- field : SELL_STA_DT [판매시작일자]
	 *		- field : SELL_END_DT [판매종료일자]
	 *		- field : NORM_SELL_TOT_AMT [정상판매합계금액]
	 *		- field : NORM_SELL_QTY [정상판매수량]
	 *		- field : BKAG_SELL_TOT_AMT [파손판매합계금액]
	 *		- field : BKAG_SELL_QTY [파손판매수량]
	 *		- field : SELL_FREQ [판매횟수]
	 *		- field : NORM_SELL_AVG_AMT [정상판매평균금액]
	 *		- field : BKAG_SELL_AVG_AMT [파손판매평균금액]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : MFACT_CD [제조사코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pIFPAEqpSellAvgAmtSaveSend(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        IDataSet reqData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);// 처리 결과값을 responseData에 넣어서 리턴하십시요 
        IRecordSet rs = requestData.getRecordSet("RS_FPA_EQP_SELL_LIST");    
        reqData.putField("USER_NO", ca.getUserNo());
            // 1. FM 호출
        try {
            for(int i=0;i<rs.getRecordCount();i++){
                reqData.putFieldMap(rs.getRecord(i));
                responseData = callSharedBizComponentByDirect("ep.EPSFEBase", "fIFPAEqpSellAvgAmtSaveSend", reqData, onlineCtx);
            }
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }       
    
        return responseData;
    }

    /**
	 * <pre>FPA단말기판매평균단가전송이력조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2016-02-16 10:51:17
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : APLY_FR_DT [기준시작일자]
	 *	- field : APLY_TO_DT [기준종료일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_FPA_EQP_SELL_SEND_LIST
	 *		- field : APPLY_STRD_DT [기준일자]
	 *		- field : SELL_GRADE_DAMT_TOT [평균금액차액합계]
	 *		- field : SELL_GRADE_DAMT_AVG [차액금액평균]
	 *		- field : EQP_CNT [단말기갯수]
	 *		- field : SEND_NM [전송자]
	 *		- field : SEND_DTM [전송일시]
	 * </pre>
	 */
	public IDataSet pSInqFPAEqpSellAvgSendList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            responseData = callSharedBizComponentByDirect("ep.EPSFEBase", "fSInqFPAEqpSellAvgSendList", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }       
    
        return responseData;
    }
  
}
