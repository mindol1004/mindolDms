package dms.nr.nrsisbase.biz;

import java.util.Map;

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
 * <li>단위업무명: [FU]보증보험관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-18 12:27:41</li>
 * <li>작성자 : 안진갑 (bella21cjk)</li>
 * </ul>
 *
 * @author 안진갑 (bella21cjk)
 */
public class FNRInsuInfoMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNRInsuInfoMgmt(){
		super();
	}

    /**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-08-18 12:27:41
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : RENTAL_CNTRT_STA_DT [계약일조회 시작일]
	 *	- field : RENTAL_CNTRT_END_DT [계약일조회 종료일]
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : INSURE_MGMT_NO [보험관리번호]
	 *	- field : USCAN_FNSH_YN [USCAN완료 여부]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : SCRB_FNSH_YN [가입완료여부]
	 *	- field : INCMP_ITM [미완료 항목]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_INSU_INFO_LIST
	 *		- field : RENTAL_CNTRT_STA_DT [계약일자]
	 *		- field : SVC_MGMT_NO [서비스관리버호]
	 *		- field : INSURE_MGMT_NO [보험관리번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : SCRB_FNSH_YN [가입완료여부]
	 *		- field : UKEY_SCRB_DT [UKEY가입일자]
	 *		- field : UKEY_INSURE_SCRB_AMT [UKEY보험가입금액]
	 *		- field : UKEY_INSURE_FEE [UKEY보험요금]
	 *		- field : SGI_SCRB_DT [SGI가입일자]
	 *		- field : SGI_INSURE_SCRB_AMT [SGI보험가입금액]
	 *		- field : SGI_INSURE_FEE [SGI보험요금]
	 *		- field : USCAN_FNSH_YN [USCAN완료여부]
	 * </pre>
	 */
	public IDataSet fInqInsuInfoLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        IDataSet dsCnt = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        int intTotalCnt = 0;

        try {
            // 1. DU lookup
            DNRInsuInfoMgmt dNRInsuInfoMgmt = (DNRInsuInfoMgmt) lookupDataUnit(DNRInsuInfoMgmt.class);
            
            // 2. TOTAL COUNT DM호출
            dsCnt = dNRInsuInfoMgmt.dSInsuInfoTotCnt(requestData, onlineCtx);
            intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
            PagenateUtils.setPagenatedParamsToDataSet(reqDs);

            // 3.보증보험 금액 합계
            responseData = dNRInsuInfoMgmt.dSInsuInfoSum(requestData, onlineCtx);
            
            // 4. LISTDM호출
            IRecordSet insuInfoLstRs = dNRInsuInfoMgmt.dSInsuInfoLstPaging(reqDs, onlineCtx).getRecordSet("RS_INSU_INFO_LIST");
            responseData.putRecordSet(insuInfoLstRs);
            PagenateUtils.setPagenatedParamToRecordSet(insuInfoLstRs, reqDs, intTotalCnt);            
            
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
        return responseData;
    }
  
}
