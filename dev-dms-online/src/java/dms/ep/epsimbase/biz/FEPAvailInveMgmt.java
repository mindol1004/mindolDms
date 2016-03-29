package dms.ep.epsimbase.biz;

import java.util.Map;

import fwk.common.CommonArea;
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
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [FU]가용재고관리</li>
 * <li>설  명 : <pre>가용재고관리</pre></li>
 * <li>작성일 : 2015-08-24 09:17:05</li>
 * <li>작성자 : 김상오 (myneti99)</li>
 * </ul>
 *
 * @author 김상오 (myneti99)
 */
public class FEPAvailInveMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FEPAvailInveMgmt(){
		super();
	}

    /**
	 * <pre>가용재고조회</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-08-24 09:17:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : HLD_DEALCO_ID [보유처]
	 *	- field : AFFIL_AGN [보유팀]
	 *	- field : SELLYN [판매여부]
	 *	- field : EQP_MDL_CD [단말기 모델코드]
	 *	- field : MFACT_CD [제조사]
	 *	- field : EQP_ST [단말기등급]
	 *	- field : EQP_SER_NO [단말기일련번호]
	 *	- field : BF_SER_NO [단말기 이전일련번호]
	 *	- field : CNSL_MGMT_NO [상담관리번호]
	 *	- field : BOX_NO [Box No]
	 *	- field : SALEFROMDTM [판매기간 시작]
	 *	- field : SALETODTM [판매기간 종료]
	 *	- field : SKN_ST [재고상태]
	 *	- field : ECO_FU_YN [에코폰4U 접수여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_AVAIL_INVE_LIST
	 *		- field : CHEKED [체크]
	 *		- field : PROD_CL [상품구분]
	 *		- field : MFACT_CD [제조사코드]
	 *		- field : EQP_MDL_CD [단말기 모델코드]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : EQP_COLOR_CD [단말기 색상코드]
	 *		- field : COLOR_NM [모델색상명]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : BF_SER_NO [단말기 이전일련번호]
	 *		- field : HLD_DEALCO_ID [보유처 ID]
	 *		- field : HLD_DEALCO_NM [보유처명]
	 *		- field : EQP_ST [단말기 상태등급]
	 *		- field : EQP_ST_DTAIL [단말기상태세부]
	 *		- field : SELLYN [판매여부]
	 *		- field : SALEPLC [매출처코드]
	 *		- field : SALEPLC_NM [매출처명]
	 *		- field : SALEPLC_UKEY [판매처]
	 *		- field : CONF_SELL_AMT [확정판매금액]
	 *		- field : CONF_SELL_DT [확정판매일자]
	 *		- field : HOLD_AMT [유보금액]
	 *		- field : INVE_AMT [재고금액]
	 *		- field : TOT_DIS_AMT [재고금액2]
	 *		- field : CNSL_MGMT_NO [상담 관리 번호]
	 *		- field : PRCHS_DT [매입일자]
	 *		- field : SKN_ST [재고상태]
	 *		- field : PROGR_ST [진행상태]
	 *		- field : BOX_NO [박스 번호]
	 *		- field : LAST_IN_OUT_DT [반품일자]
	 *		- field : IMEI [IMEI]
	 *		- field : CNSGN_SELL_YN [위탁 판매 여부]
	 *		- field : CNSGN_SELL_DT [위탁 판매 일자]
	 *		- field : CNSGN_SELL_CNCL_DT [위탁 판매 취소 일자]
	 *		- field : CNSGNPLC [위탁처코드]
	 *		- field : CNSGNPLC_NM [위탁처명]
	 *		- field : CNSL_DEALCO_CD [상담처코드]
	 *		- field : CNSL_DEALCO_NM [상담처명]
	 *		- field : AFFIL_AGN [대리점코드]
	 *		- field : AFFIL_AGN_NM [대리점명]
	 *		- field : AGN_ORG_CD [대리점UkeyCode]
	 *		- field : CNSL_DT [상담일자]
	 *		- field : IN_CONF_DT [입고확정일자]
	 *		- field : TLY_DT [검수일자]
	 *		- field : RMT_DT [송금일자]
	 *		- field : FEE_DEDC_PROC_DT [요금공제처리일자]
	 *		- field : BOX_PROC_CHRGR [박스 처리 담당자]
	 *		- field : BOX_PROC_DT [박스 처리 일자]
	 *		- field : SCRN_AFIMG_YN [화면잔상여부]
	 *		- field : EVALCNSLR_SUGG [평가원의견]
	 *		- field : SELL_GRADE [판매등급]
	 *		- field : HEADQ_NM [마케팅본부]
	 *		- field : MKT_TEAM_NM [마케팅팀명]
	 * </pre>
	 */
	/**
	 * <pre>가용재고조회</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-08-24 09:17:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : HLD_DEALCO_ID [보유처]
	 *	- field : AFFIL_AGN [보유팀]
	 *	- field : SELLYN [판매여부]
	 *	- field : EQP_MDL_CD [단말기 모델코드]
	 *	- field : MFACT_CD [제조사]
	 *	- field : EQP_ST [단말기등급]
	 *	- field : EQP_SER_NO [단말기일련번호]
	 *	- field : BF_SER_NO [단말기 이전일련번호]
	 *	- field : CNSL_MGMT_NO [상담관리번호]
	 *	- field : BOX_NO [Box No]
	 *	- field : SALEFROMDTM [판매기간 시작]
	 *	- field : SALETODTM [판매기간 종료]
	 *	- field : SKN_ST [재고상태]
	 *	- field : ECO_FU_YN [에코폰4U 접수여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_AVAIL_INVE_LIST
	 *		- field : CHEKED [체크]
	 *		- field : PROD_CL [상품구분]
	 *		- field : MFACT_CD [제조사코드]
	 *		- field : EQP_MDL_CD [단말기 모델코드]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : EQP_COLOR_CD [단말기 색상코드]
	 *		- field : COLOR_NM [모델색상명]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : BF_SER_NO [단말기 이전일련번호]
	 *		- field : HLD_DEALCO_ID [보유처 ID]
	 *		- field : HLD_DEALCO_NM [보유처명]
	 *		- field : EQP_ST [단말기 상태등급]
	 *		- field : EQP_ST_DTAIL [단말기상태세부]
	 *		- field : SELLYN [판매여부]
	 *		- field : SALEPLC [매출처코드]
	 *		- field : SALEPLC_NM [매출처명]
	 *		- field : SALEPLC_UKEY [판매처]
	 *		- field : CONF_SELL_AMT [확정판매금액]
	 *		- field : CONF_SELL_DT [확정판매일자]
	 *		- field : HOLD_AMT [유보금액]
	 *		- field : INVE_AMT [재고금액]
	 *		- field : TOT_DIS_AMT [재고금액2]
	 *		- field : SELL_AMT [판매가]
	 *		- field : CNSL_MGMT_NO [상담 관리 번호]
	 *		- field : PRCHS_DT [매입일자]
	 *		- field : SKN_ST [재고상태]
	 *		- field : PROGR_ST [진행상태]
	 *		- field : BOX_NO [박스 번호]
	 *		- field : LAST_IN_OUT_DT [반품일자]
	 *		- field : IMEI [IMEI]
	 *		- field : CNSGN_SELL_YN [위탁 판매 여부]
	 *		- field : CNSGN_SELL_DT [위탁 판매 일자]
	 *		- field : CNSGN_SELL_CNCL_DT [위탁 판매 취소 일자]
	 *		- field : CNSGNPLC [위탁처코드]
	 *		- field : CNSGNPLC_NM [위탁처명]
	 *		- field : CNSL_DEALCO_CD [상담처코드]
	 *		- field : CNSL_DEALCO_NM [상담처명]
	 *		- field : AFFIL_AGN [대리점코드]
	 *		- field : AFFIL_AGN_NM [대리점명]
	 *		- field : AGN_ORG_CD [대리점UkeyCode]
	 *		- field : CNSL_DT [상담일자]
	 *		- field : IN_CONF_DT [입고확정일자]
	 *		- field : TLY_DT [검수일자]
	 *		- field : RMT_DT [송금일자]
	 *		- field : FEE_DEDC_PROC_DT [요금공제처리일자]
	 *		- field : BOX_PROC_CHRGR [박스 처리 담당자]
	 *		- field : BOX_PROC_DT [박스 처리 일자]
	 *		- field : SCRN_AFIMG_YN [화면잔상여부]
	 *		- field : EVALCNSLR_SUGG [평가원의견]
	 *		- field : SELL_GRADE [판매등급]
	 *		- field : HEADQ_NM [마케팅본부]
	 *		- field : MKT_TEAM_NM [마케팅팀명]
	 * </pre>
	 */
	public IDataSet fInqAvailInveList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        IDataSet dsCnt = new DataSet();
           
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        IRecordSet rsPagingList = null;
       
        int intTotalCnt = 0;
       
        try {
            // 1. DU lookup
            DEPAvailInveMgmt dEPAvailInveMgmt = (DEPAvailInveMgmt) lookupDataUnit(DEPAvailInveMgmt.class);
           
            // 2. TOTAL COUNT DM호출
            dsCnt = dEPAvailInveMgmt.dSAvailInveListTotCnt(reqDs, onlineCtx);
            intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));  
            PagenateUtils.setPagenatedParamsToDataSet(reqDs);  //카운트 셋
           
            // 3. LIST DM호출
            responseData = dEPAvailInveMgmt.dSAvailInveListPaging(reqDs, onlineCtx);
   
            rsPagingList = responseData.getRecordSet("RS_AVAIL_INVE_LIST");
            PagenateUtils.setPagenatedParamToRecordSet(rsPagingList, reqDs, intTotalCnt);  //리스트 셋
               
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
       return responseData;
    }

    /**
	 * <pre>상품코드변경목록수정</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-08-24 09:17:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_PROD_CD_CHG_LIST
	 *		- field : BF_EQP_MDL_CD [이전모델코드]
	 *		- field : BF_EQP_SER_NO [이전단말기일련번호]
	 *		- field : EQP_COLOR_CD [단말기 색상코드]
	 *		- field : CNSL_MGMT_NO [상담관리번호]
	 *		- field : EQP_SER_NO [상품일련번호]
	 *		- field : AF_EQP_MDL_CD [이후모델코드]
	 *		- field : AF_EQP_SER_NO [이후단말기일련번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSaveCdChgLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();	   
        IRecordSet rsList = null;
        IDataSet rsDset = new DataSet();
        IDataSet responsePData = new DataSet();
        IRecordSet rsPList = null;
        IDataSet rsPDset = new DataSet();
    
        try {   
            CommonArea ca = getCommonArea(onlineCtx);
            IRecordSet reqSet = requestData.getRecordSet("RS_PROD_CD_CHG_LIST");
            // 1. DU lookup
            DEPAvailInveMgmt dEPAvailInveMgmt = (DEPAvailInveMgmt) lookupDataUnit(DEPAvailInveMgmt.class);
            
            for(int i = 0; i < reqSet.getRecordCount(); i++){
                requestData.putFieldMap(reqSet.getRecordMap(i));
                requestData.putField("LS_CHG_USER_ID", ca.getUserNo());
                //*이전소스 프로시저 순서대로
                //상품코드변경이력목록건수
                IDataSet valDS = dEPAvailInveMgmt.dSCdChgLogCnt(requestData, onlineCtx);
                if ( Integer.parseInt(valDS.getField("LOG_CNT")) > 0 ) {
                    dEPAvailInveMgmt.dUCdChgLogUpd(requestData, onlineCtx); //상품코드변경이력수정
                }else{
                    dEPAvailInveMgmt.dICdChgLogReg(requestData, onlineCtx); //상품코드변경이력등록
                }                
                
                //매입관리목록수정
                dEPAvailInveMgmt.dUCdChgPrchsMgmtUpd(requestData, onlineCtx);
                
                //재고목록건수
                IDataSet vblDS = dEPAvailInveMgmt.dSCdChgDisCnt(requestData, onlineCtx);
                if ( Integer.parseInt(vblDS.getField("DIS_CNT")) > 0 ) {
                    dEPAvailInveMgmt.dUCdChgDisUpd(requestData, onlineCtx); //재고목록수정
                }
                //재고금액목록건수
                IDataSet vclDS = dEPAvailInveMgmt.dSCdChgDisAmtCnt(requestData, onlineCtx);
                if ( Integer.parseInt(vclDS.getField("DIS_AMT_CNT")) > 0 ) {
                    dEPAvailInveMgmt.dUCdChgDisAmtUpd(requestData, onlineCtx); //재고금액목록수정
                }
                //재고이력목록건수
                IDataSet vdlDS = dEPAvailInveMgmt.dSCdChgDisHstCnt(requestData, onlineCtx);
                if ( Integer.parseInt(vdlDS.getField("DIS_HST_CNT")) > 0 ) {
                    dEPAvailInveMgmt.dUCdChgDisHstUpd(requestData, onlineCtx); //재고이력목록수정
                }
                //입출고이력상세조회
                responseData = dEPAvailInveMgmt.dSCdChgInoutHstLst(requestData, onlineCtx);
                rsList = responseData.getRecordSet("RS_DIS_INOUT_HST_LIST");//입출고이력상세목록
                               
                if(rsList != null){
                    for(int j = 0; j < rsList.getRecordCount(); j++){
                        rsDset.putFieldMap(rsList.getRecordMap(j));
                        rsDset.putField("AF_EQP_MDL_CD" , requestData.getField("AF_EQP_MDL_CD"));
                        rsDset.putField("AF_EQP_SER_NO" , requestData.getField("AF_EQP_SER_NO"));
                        rsDset.putField("LS_CHG_USER_ID" , requestData.getField("LS_CHG_USER_ID")); 
                        //입고상세수정
                        if(rsDset.getField("INOUTH_IN_MGMT_NO") != null && !"".equals(rsDset.getField("INOUTH_IN_MGMT_NO"))){
                            dEPAvailInveMgmt.dUCdChgInDtlUpd(rsDset, onlineCtx);
                        }
                        //출고상세수정
                        if(rsDset.getField("INOUTH_OUT_MGMT_NO") != null && !"".equals(rsDset.getField("INOUTH_OUT_MGMT_NO"))){
                            dEPAvailInveMgmt.dUCdChgOutDtlUpd(rsDset, onlineCtx);
                        }
                        //입출고이력상세수정
                        dEPAvailInveMgmt.dUCdChgInoutHstUpd(rsDset, onlineCtx);                             
                     }
                 }
                 //구성품조회
                responsePData = dEPAvailInveMgmt.dSCdChgProdCpntLst(requestData, onlineCtx);
                rsPList = responsePData.getRecordSet("RS_DIS_PROD_CPNT_LIST");//구성품목록
                if(rsPList != null){
                    for(int j = 0; j < rsPList.getRecordCount(); j++){
                        rsPDset.putFieldMap(rsPList.getRecordMap(j));
                        rsPDset.putField("AF_EQP_MDL_CD" , requestData.getField("AF_EQP_MDL_CD"));
                        rsPDset.putField("BF_EQP_MDL_CD" , requestData.getField("BF_EQP_MDL_CD"));
                        //구성품코드수정
                        dEPAvailInveMgmt.dUCdChgProdCpntCdUpd(rsPDset, onlineCtx);        
                    }
                }    
               //구성품목록건수
                IDataSet velDS = dEPAvailInveMgmt.dSCdChgProdCpntCnt(requestData, onlineCtx);
                if ( Integer.parseInt(velDS.getField("DIS_PROD_CPNT_CNT")) > 0 ) {
                    dEPAvailInveMgmt.dUCdChgProdCpntUpd(requestData, onlineCtx); //구성품목록수정
                }
                //상품재고목록건수
                IDataSet vflDS = dEPAvailInveMgmt.dSCdChgProdDisCnt(requestData, onlineCtx);
                if ( Integer.parseInt(vflDS.getField("DIS_PROD_DIS_CNT")) > 0 ) {
                    dEPAvailInveMgmt.dUCdChgProdDisUpd(requestData, onlineCtx); //상품재고목록수정
                }
                //상품입출고상세목록건수
                IDataSet vglDS = dEPAvailInveMgmt.dSCdChgProdInoutHstCnt(requestData, onlineCtx);
                if ( Integer.parseInt(vglDS.getField("DIS_PROD_INOUT_HST_CNT")) > 0 ) {
                    dEPAvailInveMgmt.dUCdChgProdInoutHstUpd(requestData, onlineCtx); //상품입출고상세목록수정
                }
                //상품단가목록건수
                IDataSet vhlDS = dEPAvailInveMgmt.dSCdChgProdUnitPrcCnt(requestData, onlineCtx);
                if ( Integer.parseInt(vhlDS.getField("DIS_PROD_UNIT_PRC_CNT")) > 0 ) {
                    dEPAvailInveMgmt.dUCdChgProdUnitPrcUpd(requestData, onlineCtx); //상품단가목록수정
                }            
             }
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
         }    
        return responseData;
    }
  
}