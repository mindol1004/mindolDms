package dms.ep.epbcsbase.biz;

import fwk.common.CommonArea;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [PU]재감정회수일자누락목록</li>
 * <li>설  명 : <pre>[PU]재감정회수일자누락목록</pre></li>
 * <li>작성일 : 2015-08-18 15:26:34</li>
 * <li>작성자 : 김상오 (myneti99)</li>
 * </ul>
 *
 * @author 김상오 (myneti99)
 */
public class PEPEqpRJdgMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PEPEqpRJdgMgmt(){
		super();
	}

	/**
	 * <pre>재감정회수일자누락목록</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-08-18 15:26:34
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CLCT_FROM_DT [회수일자]
	 *	- field : CLCT_TO_DT [회수일자]
	 *	- field : CNSL_MGMT_NO [접수관리번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_CLCT_LST
	 *		- field : CNSL_MGMT_NO [상담관리번호]
	 *		- field : CLCT_DT [회수일자]
	 *		- field : EQP_COLOR_CD [단말기색상코드]
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : SKN_CL [SKN구분]
	 *		- field : PROGR_ST [진행상태]
	 *		- field : ONING_DT [개통일자]
	 *		- field : ERR_CHK [ERR_CHK]
	 * </pre>
	 */
	public IDataSet pInqEqpClctOmitList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSCSBase", "fInqEqpClctOmitList", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        // 2. 결과값 리턴
        responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
    
        return responseData;
	}

    /**
	 * <pre>재감정회수일자확정</pre>
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-08-18 15:26:34
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_CLCT_LST
	 *		- field : CNSL_MGMT_NO [상담관리번호]
	 *		- field : CLCT_DT [회수일자]
	 *		- field : EQP_COLOR_CD [단말기색상코드]
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : SKN_CL [SKN구분]
	 *		- field : PROGR_ST [진행상태]
	 *		- field : CHECKED [CHECKED]
	 *		- field : ONING_DT [개통일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pUpdEqpClctOmitLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);// 처리 결과값을 responseData에 넣어서 리턴하십시요 
        IRecordSet rs = requestData.getRecordSet("RS_EQP_CLCT_LST");    
        try {
            requestData.putField("USER_NO", ca.getUserNo());
            // 1. FM 호출
            for(int i=0;i<rs.getRecordCount();i++){
                if("1".equals(rs.getRecord(i).get("CHECKED"))){
                    requestData.putFieldMap(rs.getRecord(i));
                    responseData = callSharedBizComponentByDirect("ep.EPSCSBase", "fUpdEqpClctOmit", requestData, onlineCtx);
                }
            }
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
	 * <pre>재감정회수일자누락목록조회엑셀업로드</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-18 15:26:34
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RQ_EQP_CLCT_LST
	 *		- field : CNSL_MGMT_NO [접수관리번호]
	 *		- field : CLCT_DT [회수일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_CLCT_LST
	 *		- field : CNSL_MGMT_NO [상담관리번호]
	 *		- field : CLCT_DT [회수일자]
	 *		- field : EQP_COLOR_CD [단말기색상코드]
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : SKN_CL [SKN구분]
	 *		- field : PROGR_ST [진행상태]
	 *		- field : ONING_DT [개통일자]
	 *		- field : ERR_CHK [에러체크]
	 * </pre>
	 */
	public IDataSet pInqEqpClctOmitListExcel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSCSBase", "fInqEqpClctOmitListExcel", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        // 2. 결과값 리턴
        responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
    
        return responseData;
    }
  
}
