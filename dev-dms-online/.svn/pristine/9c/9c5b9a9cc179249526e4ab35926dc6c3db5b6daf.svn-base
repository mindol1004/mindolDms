package dms.pr.prsimbase.biz;

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
 * <li>업무 그룹명 : dms/임대R</li>
 * <li>단위업무명: [FU]SKT교품반환등록</li>
 * <li>설  명 : <pre>SKT교품반환등록</pre></li>
 * <li>작성일 : 2015-08-31 17:59:43</li>
 * <li>작성자 : 양재석 (jsyang)</li>
 * </ul>
 *
 * @author 양재석 (jsyang)
 */
public class FPRSKTEqpExpartBackReg extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FPRSKTEqpExpartBackReg(){
		super();
	}

    /**
	 * <pre>SKT교품반환등록조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-31 17:59:43
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
	public IDataSet fInqSKTEqpExpartBackRegList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet dsCnt = new DataSet();
       
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        IRecordSet rsPagingList = null;
       
        int intTotalCnt = 0;
       
        try {
            // 1. DU lookup
            DPRSKTEqpExpartBackReg dPRSKTEqpExpartBackReg = (DPRSKTEqpExpartBackReg) lookupDataUnit(DPRSKTEqpExpartBackReg.class);
           
            // 2. TOTAL COUNT DM호출
            dsCnt = dPRSKTEqpExpartBackReg.dSSKTEqpExpartBackRegTotCnt(reqDs, onlineCtx);
            intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));  
            PagenateUtils.setPagenatedParamsToDataSet(reqDs);  //카운트 셋
           
            // 3. LIST DM호출
            responseData = dPRSKTEqpExpartBackReg.dSSKTEqpExpartBackRegPaging(reqDs, onlineCtx);
   
            rsPagingList = responseData.getRecordSet("RS_EXPART_BACK_LIST");
            PagenateUtils.setPagenatedParamToRecordSet(rsPagingList, reqDs, intTotalCnt);  //리스트 셋
               
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
       return responseData;
    }

    /**
	 * <pre>[FM]매입단말기조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-31 17:59:43
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
	public IDataSet fInqInEqpList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        try {
            // 1. DU lookup
            DPRSKTEqpExpartBackReg dPRSKTEqpExpartBackReg = (DPRSKTEqpExpartBackReg) lookupDataUnit(DPRSKTEqpExpartBackReg.class);
            // 2. LISTDM호출
            responseData = dPRSKTEqpExpartBackReg.dSInqInEqpList(requestData, onlineCtx);
    
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }       
        return responseData;
    }

    /**
	 * <pre>[FM]교품대상단말기등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-01 09:58:51
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
	public IDataSet fExpartObjEqpReg(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            CommonArea ca = getCommonArea(onlineCtx);
            IRecordSet reqSet = requestData.getRecordSet("RQ_EXPART_BACK_LIST");
            // 1. DU lookup
            DPRSKTEqpExpartBackReg dPRSKTEqpExpartBackReg = (DPRSKTEqpExpartBackReg) lookupDataUnit(DPRSKTEqpExpartBackReg.class);
            for(int i = 0; i < reqSet.getRecordCount(); i++){
                requestData.putFieldMap(reqSet.getRecordMap(i));
                //0. 최초등록사용자ID,최종변경사용자ID
                requestData.putField("FS_REG_USER_ID", ca.getUserNo());
                requestData.putField("LS_CHG_USER_ID", ca.getUserNo());
                //1.교품단말기계약생성
                dPRSKTEqpExpartBackReg.dIExpartEqpCntrtRgst(requestData, onlineCtx);
                //2.교품단말기계약상세생성
                dPRSKTEqpExpartBackReg.dIExpartEqpCntrtDtlRgst(requestData, onlineCtx);
                dPRSKTEqpExpartBackReg.dDExpartEqpCntrtDtlDel(requestData, onlineCtx); //이전단말기계약상세삭제, 단말기계약삭제는 아래 계약정보수정에서 처리
                //3.출고일련번호채번
                IDataSet valDS = dPRSKTEqpExpartBackReg.dSExpartObjEqpOutNum(requestData, onlineCtx);
                requestData.putField("EQP_OUT_NO", valDS.getField("EQP_OUT_NO"));
                //4.교품단말기출고등록
                requestData.putField("EQP_OUT_DT", requestData.getField("EXPART_DT"));
                requestData.putField("ORG_ASSET_NO", requestData.getField("ASSET_NO"));
                requestData.putField("ASSET_NO", requestData.getField("EX_ASSET_NO"));
                requestData.putField("EQP_SER_NO", requestData.getField("EX_EQP_SER_NO"));
                requestData.putField("OUT_DTL_CD", "10"); //출고상세코드 : SKT출고
                requestData.putField("OUT_DEALCO_CD", "0000000000"); //거래처코드 : SKT
                requestData.putField("CHRG_DEPT_CD", ""); //부서코드
                dPRSKTEqpExpartBackReg.dIExpartObjEqpOutReg(requestData, onlineCtx);
                //5.교품단말기자산정보수정
                requestData.putField("LAST_IN_OUT_NO", requestData.getField("EQP_OUT_NO"));
                requestData.putField("INVE_ST_CD", "20"); //재고상태코드 : 출고(20)
                dPRSKTEqpExpartBackReg.dUExpartObjEqpAssetInfoUpd(requestData, onlineCtx);
                //6.원단말기교품계약정보수정
                dPRSKTEqpExpartBackReg.dUExpartEqpCntrtInfoUpd(requestData, onlineCtx);
            }
        } catch ( BizRuntimeException e ) {
            throw e;
        }
    
        return responseData;
    }

    /**
	 * <pre>반환확정단말기등록</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-08-31 17:59:43
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
	public IDataSet fBackObjEqpReg(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        try {
            CommonArea ca = getCommonArea(onlineCtx);
            IRecordSet reqSet = requestData.getRecordSet("RQ_BACK_LIST");
            // 1. DU lookup
            DPRSKTEqpExpartBackReg dPRSKTEqpExpartBackReg = (DPRSKTEqpExpartBackReg) lookupDataUnit(DPRSKTEqpExpartBackReg.class);
            for(int i = 0; i < reqSet.getRecordCount(); i++){
                requestData.putFieldMap(reqSet.getRecordMap(i));
                //0. 최초등록사용자ID,최종변경사용자ID
                requestData.putField("FS_REG_USER_ID", ca.getUserNo());
                requestData.putField("LS_CHG_USER_ID", ca.getUserNo());

                //1.출고일련번호채번
                IDataSet valDS = dPRSKTEqpExpartBackReg.dSExpartObjEqpOutNum(requestData, onlineCtx);
                requestData.putField("EQP_OUT_NO", valDS.getField("EQP_OUT_NO"));
                //2.반환단말기출고등록
                requestData.putField("EQP_OUT_DT", requestData.getField("EQP_OUT_DT")); //출고날짜는 오늘날짜로
                requestData.putField("ASSET_NO", requestData.getField("ASSET_NO"));
                requestData.putField("EQP_MDL_CD", requestData.getField("EQP_MDL_CD"));
                requestData.putField("EQP_SER_NO", requestData.getField("EQP_SER_NO"));
                requestData.putField("OUT_DTL_CD", "50"); //출고상세코드 : SKT반환출고
                requestData.putField("OUT_DEALCO_CD", "0000000000"); //거래처코드 : SKT
                requestData.putField("CHRG_DEPT_CD", ""); //부서코드
                dPRSKTEqpExpartBackReg.dIExpartObjEqpOutReg(requestData, onlineCtx);
            }
        } catch ( BizRuntimeException e ) {
            throw e;
        }
    
        return responseData;
    }
}
