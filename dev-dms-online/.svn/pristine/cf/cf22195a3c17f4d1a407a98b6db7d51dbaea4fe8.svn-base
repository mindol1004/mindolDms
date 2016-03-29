package dms.ep.epscsbase.biz;

import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;

import fwk.common.CommonArea;
import fwk.utils.HpcUtils;
import fwk.utils.PagenateUtils;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.data.RecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [FU]중고단말기재고관리</li>
 * <li>설  명 : <pre>[FU]중고단말기재고관리</pre></li>
 * <li>작성일 : 2015-10-13 15:46:54</li>
 * <li>작성자 : 양재석 (jsyang)</li>
 * </ul>
 *
 * @author 양재석 (jsyang)
 */
public class FEPEpEqpDisMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FEPEpEqpDisMgmt(){
		super();
	}

    /**
	 * <pre>중고단말기구성품재고처리</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-10-13 15:48:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fIEpEqpCmptDisProc(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet reqData = new DataSet();
        IDataSet reqDtlData = new DataSet();
        IDataSet resData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);

        try {
            // 입고  상품 리스트. 
            IRecordSet rsProdList = requestData.getRecordSet("SEND_AMT_EQP");
            if(requestData != null && requestData.getRecordSetCount() > 0 && rsProdList != null && rsProdList.getRecordCount() > 0){
                String strOutMgmtNo = ""; //출고관리번호
                String strInOutDtlCl = ""; // 입출고상세구분
                String strCurrDt = DateUtils.getCurrentDate();
                String strOutMgmtSeq = ""; //출고순번
                String strInOutCl = ""; // 입출고구분 
                
                // 입고  마스터 
                IRecordSet rsInMst = requestData.getRecordSet("SEND_AMT_MASTER");
                String sInPlcId = (String) rsInMst.getRecordMap(0).get("CNSL_DEALCO_CD");
                if(sInPlcId == null || "".equals(sInPlcId)){
                    sInPlcId = "11010"; // 본사창고..
                }

                // 1. DU lookup 재고관리
                DEPEpEqpDisMgmt dEPEpEqpDisMgmt = (DEPEpEqpDisMgmt) lookupDataUnit(DEPEpEqpDisMgmt.class);
                DEPRJdgQltyJdgMgmt dEPRJdgQltyJdgMgmt = (DEPRJdgQltyJdgMgmt) lookupDataUnit(DEPRJdgQltyJdgMgmt.class);
                strInOutDtlCl = "213"; //213 구성품출고
                strInOutCl = "200"; // 입출고구분 
                // 2. 출고번호채번
                resData = dEPEpEqpDisMgmt.dSInqDisOutMgmtNo(reqData, onlineCtx);
                strOutMgmtNo = resData.getField("OUT_MGMT_NO").toString();
                // 3. 출고
                reqData.putField("OUT_MGMT_NO", strOutMgmtNo);
                reqData.putField("OUT_CL", strInOutDtlCl);
                reqData.putField("OUT_SCHD_DT", strCurrDt);
                reqData.putField("OUT_DEALCO_ID", sInPlcId);
                reqData.putField("IN_DEALCO_ID", sInPlcId);
                reqData.putField("OUT_CONF_DT", strCurrDt);
                reqData.putField("OUT_CONF_YN", "Y");
                reqData.putField("RMK", "");
                reqData.putField("FS_REG_USER_ID", ca.getUserNo());
                reqData.putField("LS_CHG_USER_ID", ca.getUserNo());
                dEPEpEqpDisMgmt.dIEpEqpDisOutRgst(reqData, onlineCtx);
                // 3.출고상세
                resData = dEPEpEqpDisMgmt.dSInqEpEqpDisOutDtlSeq(reqData, onlineCtx);
                strOutMgmtSeq = resData.getField("OUT_SEQ").toString();
                reqDtlData.putFieldMap(rsProdList.getRecordMap(0)); //단말정보 복사
                if(reqDtlData.getField("CNSL_MGMT_NO") == null || "".equals(reqDtlData.getField("CNSL_MGMT_NO").toString())
                || reqDtlData.getField("CNSL_DT") == null || "".equals(reqDtlData.getField("CNSL_DT").toString())){
                    throw new BizRuntimeException("DMS00142");  //상담정보가 없는 데이터 입니다. 확인 바랍니다.
                }
                reqDtlData.putField("OUT_MGMT_NO", strOutMgmtNo);
                reqDtlData.putField("OUT_SEQ", strOutMgmtSeq);
                reqDtlData.putField("INVE_ST", "01");   //재고상태 가용
                //reqDtlData.putField("OUT_QTY", 0);
                //reqDtlData.putField("UPRC", 0);
                //reqDtlData.putField("AMT", 0);
                reqDtlData.putField("FS_REG_USER_ID", ca.getUserNo());
                reqDtlData.putField("LS_CHG_USER_ID", ca.getUserNo());
                dEPEpEqpDisMgmt.dIEpEqpDisOutDtlRgst(reqDtlData, onlineCtx);
                // 4.입출고이력상세
                reqDtlData.putField("IN_OUT_SEQ", "");
                reqDtlData.putField("IN_MGMT_NO", "");
                reqDtlData.putField("IN_SEQ", "");
                reqDtlData.putField("OUT_MGMT_NO", strOutMgmtNo);
                reqDtlData.putField("OUT_SEQ", strOutMgmtSeq);
                reqDtlData.putField("IN_OUT_DT", strCurrDt);
                reqDtlData.putField("IN_OUT_CL", strInOutCl);
                reqDtlData.putField("IN_OUT_DTL_CL", strInOutDtlCl);
                reqDtlData.putField("PRCH_DEALCO_ID", rsInMst.getRecordMap(0).get("PRCHCO_ID"));
                reqDtlData.putField("OUT_DEALCO_ID", sInPlcId);
                reqDtlData.putField("IN_DEALCO_ID", sInPlcId);
                dEPRJdgQltyJdgMgmt.dIRJdgQltyJdgDisInoutHstRgst(reqDtlData, onlineCtx);
                // 5.구성품정보(단말기) 재고(update)
                reqDtlData.putField("LAST_IN_OUT_DT", strCurrDt);
                reqDtlData.putField("IN_OUT_CL", strInOutCl);
                reqDtlData.putField("IN_OUT_DTL_CL", strInOutDtlCl);
                dEPEpEqpDisMgmt.dUEpEqpDisLastInOutUpd(reqDtlData, onlineCtx);
                
                // 6.구성품정보(단말기) 재고이력(insert)
                dEPEpEqpDisMgmt.dIEpEqpDisHstOutRgst(reqDtlData, onlineCtx);
                
                // 7.상품재고
                resData = dEPEpEqpDisMgmt.dSInqEpEqpProdDisSerNo(reqData, onlineCtx);
                String sProdSerNum = resData.getField("PROD_DIS_SER_NO").toString();
                reqDtlData.putField("BF_SER_NO", reqDtlData.getField("EQP_SER_NO"));
                reqDtlData.putField("PROD_DIS_SER_NO", sProdSerNum); // 일련번호(New) 
                reqDtlData.putField("HLD_DEALCO_ID", sInPlcId);
                reqDtlData.putField("INVE_ST", "01"); // 재고상태 : 가용
                // 단말기등급 rsProdList 에서 복사
                //reqDtlData.putField("EQP_ST","");
                reqDtlData.putField("INVE_AMT", reqDtlData.getField("IN_AMT")); // 재고금액
                reqDtlData.putField("LAST_IN_OUT_DT", strCurrDt);
                reqDtlData.putField("IN_OUT_CL", "100"); // 최종입출고구분 
                reqDtlData.putField("IN_OUT_DTL_CL", "114"); // 최종입출고상세구분
                reqDtlData.putField("FST_IN_CONF_DT", ""); // 최초입고확정일
                reqDtlData.putField("FST_IN_DEALCO_ID", sInPlcId); // 최초입고처
                reqDtlData.putField("PRCHCO_ID", rsInMst.getRecordMap(0).get("PRCHCO_ID")); // 최초매입처
                reqDtlData.putField("LAST_MOV_IN_DT", strCurrDt); // 최종이동입고일
                reqDtlData.putField("CONF_SELL_AMT", reqDtlData.getField("IN_AMT")); // 확정판매금액
                reqDtlData.putField("CONF_SELL_DT", ""); //확정판매일자
                reqDtlData.putField("SELL_YN", "N"); // 판매여부
                reqDtlData.putField("RMK", "");
                //상담관리번호,일자 rsProdList 에서 복사
                //reqDtlData.putField("CNSL_MGMT_NO", "");
                //reqDtlData.putField("CNSL_DT", "");
                dEPEpEqpDisMgmt.dIEpEqpProdDisRgst(reqDtlData, onlineCtx);

                // 8.상품단가 - 상품단가는 단말기만 관리함.
                reqDtlData.putField("OCCR_CL", "01"); // 발생구분 : 01 
                reqDtlData.putField("OCCR_DT", strCurrDt);
                reqDtlData.putField("AMT", reqDtlData.getField("IN_AMT"));
                IDataSet dsCnt = new DataSet();
                dsCnt = dEPEpEqpDisMgmt.dSEpEqpProdDisUnitPrcRgstCnt(reqDtlData, onlineCtx);
                int intDisCnt = Integer.parseInt(dsCnt.getField("CNT"));
                if(intDisCnt <= 0){
                    dEPEpEqpDisMgmt.dIEpEqpProdDisUnitPrcRgst(reqDtlData, onlineCtx);
                }
                
                // 9.상품입출고이력
                reqDtlData.putField("IN_OUT_SEQ", new BigDecimal(1));
                dsCnt = dEPEpEqpDisMgmt.dSEpEqpProdDisInoutHstRgstCnt(reqDtlData, onlineCtx);
                intDisCnt = Integer.parseInt(dsCnt.getField("CNT"));
                if(intDisCnt <= 0){
                    dEPEpEqpDisMgmt.dIEpEqpProdDisInoutHstRgst(reqDtlData, onlineCtx);
                }
            }
        } catch ( BizRuntimeException e ) {
            throw e;
        }
    
        return responseData;
	}

    /**
	 * <pre>[FM]중고단말기구성품재고취소처리</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-10-13 15:59:13
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fIEpEqpCmptDisCnclProc(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        IDataSet reqData = new DataSet();
        IDataSet reqDtlData = new DataSet();
        IDataSet resData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);

        try {
            String sInoutCl = "100"; // 입출고구분 (입고)
            String sInoutDtlCl = "113"; // 입출고상세구분 (구성품출고취소)
            String sCurrDt = DateUtils.getCurrentDate(); // 현재일자        
            // 입고  상품 리스트. 
            IRecordSet rsProdList = requestData.getRecordSet("SEND_AMT_EQP");
            if(requestData != null && requestData.getRecordSetCount() > 0 && rsProdList != null && rsProdList.getRecordCount() > 0){
                // 입고  마스터 
                IRecordSet rsInMst = requestData.getRecordSet("SEND_AMT_MASTER");
                reqData.putFieldMap(rsProdList.getRecordMap(0)); //단말정보 복사
                // 1. DU lookup 재고관리
                DEPEpEqpDisMgmt dEPEpEqpDisMgmt = (DEPEpEqpDisMgmt) lookupDataUnit(DEPEpEqpDisMgmt.class);
                DEPRJdgQltyJdgMgmt dEPRJdgQltyJdgMgmt = (DEPRJdgQltyJdgMgmt) lookupDataUnit(DEPRJdgQltyJdgMgmt.class);
                
                resData = dEPEpEqpDisMgmt.dSInqEpEqpCnclObjProdDis(reqData, onlineCtx);
                IRecordSet rsListTemp = resData.getRecordSet("CNCL_OBJ_PROD_LIST");
                if(rsListTemp != null && rsListTemp.getRecordCount() > 0){ //취소대상 상품이 있는경우만
                    reqData.putFieldMap(rsListTemp.getRecordMap(0));
                    reqData.putField("LS_CHG_USER_ID", ca.getUserNo());
                    //상품 재고 취소
                    dEPEpEqpDisMgmt.dDEpEqpProdDisDel(reqData, onlineCtx);
                    //상품 가격 취소
                    dEPEpEqpDisMgmt.dDEpEqpProdDisUnitPrcDel(reqData, onlineCtx);
                    //상품 구성품 취소
                    dEPEpEqpDisMgmt.dDEpEqpProdDisCpntDel(reqData, onlineCtx);
                    //상품 입출고 상세 이력
                    dEPEpEqpDisMgmt.dDEpEqpProdDisInoutHstDel(reqData, onlineCtx);
                }

                reqData.putFieldMap(rsProdList.getRecordMap(0)); //단말정보 복사
                //입고관리번호채번
                resData = dEPRJdgQltyJdgMgmt.dSInqDisInMgmtNo(reqData, onlineCtx);
                reqData.putField("IN_MGMT_NO", resData.getField("IN_MGMT_NO").toString());
                //입고순번채번
                resData = dEPRJdgQltyJdgMgmt.dSInqDisInDtlSeq(reqData, onlineCtx);
                reqData.putField("IN_SEQ", resData.getField("IN_SEQ").toString());
                //최종입고이력조회
                resData = dEPEpEqpDisMgmt.dSInqEpEqpDisLastInHst(reqData, onlineCtx);
                rsListTemp = resData.getRecordSet("DIS_LAST_IN_HST");
                //
                if(rsListTemp != null && rsListTemp.getRecordCount() > 0){
                    reqDtlData.putFieldMap(rsListTemp.getRecordMap(0));
                    reqData.putField("CURR_DT", sCurrDt);
                    reqData.putField("FS_REG_USER_ID", ca.getUserNo());
                    reqData.putField("LS_CHG_USER_ID", ca.getUserNo());
                    reqData.putField("LAST_IN_MGMT_NO", reqDtlData.getField("IN_MGMT_NO").toString());
                    reqData.putField("INOUT_MGMT_NO", reqDtlData.getField("IN_MGMT_NO").toString());
                    reqData.putField("LAST_IN_SEQ", reqDtlData.getField("IN_SEQ").toString());
                    reqData.putField("LAST_IN_OUT_SEQ", reqDtlData.getField("IN_OUT_SEQ").toString());
                    reqData.putField("IN_OUT_CL", sInoutCl);
                    reqData.putField("IN_OUT_DTL_CL", sInoutDtlCl);
                    //중고단말기재고출고취소입고처리
                    dEPEpEqpDisMgmt.dIEpEqpDisOutCnclInProc(reqData, onlineCtx);
                    //중고단말기재고출고취소입고상세처리
                    dEPEpEqpDisMgmt.dIEpEqpDisOutCnclInDtlProc(reqData, onlineCtx);
                    //중고단말기재고출고취소구성품입고처리
                    dEPEpEqpDisMgmt.dIEpEqpDisOutCnclInCpntProc(reqData, onlineCtx);
                    //재고삭제취소
                    dEPEpEqpDisMgmt.dUEpEqpDisDelCnclUpd(reqData, onlineCtx);
                    //재고삭제취소입출고이력등록
                    dEPEpEqpDisMgmt.dIEpEqpDisDelCnclInoutHstRgst(reqData, onlineCtx);
                    //중고단말기재고출고취소이력등록
                    dEPEpEqpDisMgmt.dIEpEqpDisOutCnclHstRgst(reqData, onlineCtx);
                }
                
            }
        } catch ( BizRuntimeException e ) {
            throw e;
        }
    
        return responseData;
	}
  
}
