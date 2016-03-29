package dms.nr.nrbeabase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [PU]매입정보관리</li>
 * <li>설  명 : <pre>매입정보관리PU</pre></li>
 * <li>작성일 : 2015-07-17 15:17:20</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class PNRPrchInfoMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNRPrchInfoMgmt(){
		super();
	}

	/**
	 * <pre>매입정보 페이징 조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 15:17:20
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : AGN_CD [대리점코드]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : PRCH_STA_DT [계약일조회시작일자]
	 *	- field : PRCH_END_DT [계약일조회종료일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_PRCH_LIST
	 *		- field : PRCH_DT [매입일자]
	 *		- field : PRCHCO_CD [매입처코드]
	 *		- field : PRCHCO_NM [매입처명]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : PET_NM [펫네임]
	 *		- field : PRCH_AMT [매입금액]
	 *		- field : PRCH_QTY [매입수량]
	 * </pre>
	 */
	public IDataSet pInqPrchInfoLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    try {
            // 1. FM 호출 ("RS_PRCH_PG RS_SUM_PRC")
            responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fInqPrchInfoLst", requestData, onlineCtx);
            // 2. 결과값 리턴
            responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
	    return responseData;
	}

	/**
	 * <pre>매입정보상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 15:17:20
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : PRCH_DT [매입일자]
	 *	- field : PRCHCO_CD [매입처코드]
	 *	- field : EQP_MDL_CD [단말기코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_PRCH_DTL
	 *		- field : SVC_MGMT_NO [필드1]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : EQP_COLOR_CD [색상]
	 *		- field : PRCH_AMT [매입가]
	 *		- field : SPLY_PRC [공급가]
	 *		- field : SURTAX_AMT [부가세]
	 *		- field : PRCH_ST_CD [필드1]
	 * </pre>
	 */
	public IDataSet pInqPrchInfoDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
       try {
            // 1. FM 호출 ("RS_PRCH_PG RS_SUM_PRC")
            responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fInqPrchInfoDtl", requestData, onlineCtx);
            // 2. 결과값 리턴
            responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
	    return responseData;
	}

    /**
	 * <pre>매입정보 모두 조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-17 15:17:20
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : AGN_CD [대리점코드]
	 *	- field : AGN_NM [대리점명]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : EQP_MDL_NM [모델명]
	 *	- field : CNTRT_FR_DTM [계약일조회시작일자]
	 *	- field : CNTRT_TO_DTM [계약일조회종료일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_PRCH_LST
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : AGN_CD [대리점코드]
	 *		- field : AGN_NM [대리점명]
	 *		- field : AGN_SEQ [대리점SEQ]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : COLOR_CD [색상코드]
	 *		- field : PET_NM [펫네임]
	 *		- field : CNTRT_DTM [계약일]
	 *		- field : PRCH_AMT [매입금액]
	 *		- field : FPA_AMT [FPA금액]
	 *		- field : FS_REG_USER_ID [최초등록자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 * </pre>
	 */
	public IDataSet pInqAllPrchInfoLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        try {
            // 1. FM 호출 ("RS_PRCH_LST")
            responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fInqAllPrchInfoLst", requestData, onlineCtx);
            // 2. 결과값 리턴
            responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
        return responseData;
    }
  
}
