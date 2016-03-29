package dms.nr.nrseabase.biz;

import java.util.Map;

import fwk.utils.PagenateUtils;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.RecordSet;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]매입정보관리</li>
 * <li>설  명 : <pre>매입정보관리FU</pre></li>
 * <li>작성일 : 2015-07-17 15:37:15</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class FNRPrchInfoMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNRPrchInfoMgmt(){
		super();
	}

	/**
	 * <pre>매입정보 페이징 조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 15:37:15
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
	public IDataSet fInqPrchInfoLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IDataSet dsCnt = new DataSet();
        IRecordSet rsPagingList = null;
        int intTotalCnt = 0;
        String sSumOutPrc;
        
        try {
            // 1. DU lookup
            DNRPrchInfoMgmt dNRPrchInfoMgmt = (DNRPrchInfoMgmt) lookupDataUnit(DNRPrchInfoMgmt.class);
            // 2. TOTAL COUNT DM호출
            dsCnt = dNRPrchInfoMgmt.dSPrchInfoTotCnt(requestData, onlineCtx);
            intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));            
            PagenateUtils.setPagenatedParamsToDataSet(requestData);
            // 3. LISTDM호출
            responseData = dNRPrchInfoMgmt.dSPrchInfoLstPaging(requestData, onlineCtx);
            rsPagingList = responseData.getRecordSet("RS_PRCH_LIST");

            PagenateUtils.setPagenatedParamToRecordSet(rsPagingList, requestData, intTotalCnt);
        } catch ( BizRuntimeException e ) {
            throw e;
        }
	    return responseData;
	}

	/**
	 * <pre>매입정보상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 15:37:15
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
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : EQP_COLOR_CD [색상]
	 *		- field : PRCH_AMT [매입가]
	 *		- field : SPLY_PRC [공급가]
	 *		- field : SRTX_AMT [부가세]
	 * </pre>
	 */
	public IDataSet fInqPrchInfoDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        // 1. DU lookup
        DNRPrchInfoMgmt dNRPrchInfoMgmt = (DNRPrchInfoMgmt) lookupDataUnit(DNRPrchInfoMgmt.class);
        // 2. LISTDM호출
        responseData = dNRPrchInfoMgmt.dSPrchInfoDtl(requestData, onlineCtx);

	    return responseData;
	}

    /**
	 * <pre>매입정보 리스트 모두조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-17 15:37:15
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
	public IDataSet fInqAllPrchInfoLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        try {
            // 1. DU lookup
            DNRPrchInfoMgmt dNRPrchInfoMgmt = (DNRPrchInfoMgmt) lookupDataUnit(DNRPrchInfoMgmt.class);
            // 3. LISTDM호출 (RS_SKN_DTL_LST)
            responseData = dNRPrchInfoMgmt.dSPrchInfoLst(requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        }
        return responseData;
    }
  
}
