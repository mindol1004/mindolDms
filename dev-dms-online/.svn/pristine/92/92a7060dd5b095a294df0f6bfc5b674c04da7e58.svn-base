package dms.ep.epssxbase.biz;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.RecordHeader;
import nexcore.framework.core.data.xml.DataSetXmlTransformer;
import nexcore.framework.core.exception.BizRuntimeException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import fwk.common.CommonArea;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [FU]단말기대금요금선납정산관리</li>
 * <li>설  명 : <pre>단말기대금요금선납정산관리 FU</pre></li>
 * <li>작성일 : 2015-10-23 09:53:14</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class FEPEqpPpayXclMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FEPEqpPpayXclMgmt(){
		super();
	}

    /**
	 * <pre>단말기대금요금선납조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:53:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : STRD_YM [정산년월]
	 *	- field : TYP_CD [유형코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : FEE_PPAY_XCL_NO [요금 선납 정산 번호]
	 *		- field : FEE_PPAY_STRD_YM [요금 선납 기준 년월]
	 *		- field : INVE_TYP_CD [재고 유형 코드]
	 *		- field : FEE_PPAY_AMT [요금 선납 금액]
	 *		- field : FEE_PPAY_XCL_CNT [요금 선납 정산 건수]
	 *		- field : SLIP_DT [요금 선납 전표 일자]
	 *		- field : SLIP_NO [요금 선납 전표 번호]
	 *		- field : SLIP_ST [요금 선납 전표 상태]
	 *		- field : FEE_PPAY_CNCL_SLIP_DT [요금 선납 취소 전표 일자]
	 *		- field : FEE_PPAY_CNCL_SLIP_NO [요금 선납 취소 전표 번호]
	 *		- field : DEALCO_BLICENS_NO [사업자 번호]
	 *		- field : YYYY [전표년도]
	 * </pre>
	 */
	public IDataSet fInqEqpPpayXclList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        
        try {
            // 1. DU lookup
            DEPEqpPpayXclMgmt dEPEqpPpayXclMgmt = (DEPEqpPpayXclMgmt) lookupDataUnit(DEPEqpPpayXclMgmt.class);

            // 2. LISTDM호출
            responseData = dEPEqpPpayXclMgmt.dSEqpPpayXclList(reqDs, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금요금선납상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:53:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : FEE_PPAY_XCL_NO [정산번호]
	 *	- field : EXCEL_FIRST [시작번호]
	 *	- field : EXCEL_LAST [종료번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_PPAY_DTL
	 *		- field : ROWNO [번호]
	 *		- field : EXCEL_TOTAL_CNT [총건수]
	 *		- field : FEE_PPAY_XCL_NO [요금 선납 정산 번호]
	 *		- field : FEE_PPAY_XCL_DTL_SEQ [요금 선납 정산 상세 순번]
	 *		- field : INVE_TYP_CD [재고 유형 코드]
	 *		- field : CNSL_MGMT_NO [상담 관리 번호]
	 *		- field : PRCH_MGMT_NO [매입 관리 번호]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : EQP_ST [단말기상태]
	 *		- field : REPV_AGN_ORG_CD [대표 대리점 조직 코드]
	 *		- field : CNSL_DT [상담 일자]
	 *		- field : CLCT_DT [회수 일자]
	 *		- field : FEE_DEDC_PROC_DT [요금 공제 처리 일자]
	 *		- field : XCL_AMT [정산 금액]
	 *		- field : SKN_JDG_AMT [SKN 감정 금액]
	 *		- field : SKN_SMPL_JDG_DAMT [SKN 샘플 감정 차액]
	 *		- field : AGN_DDCT_AMT [대리점 차감 금액]
	 *		- field : AGN_EXPNS_CONF_AMT [대리점 비용 확정 금액]
	 *		- field : PPAY_PROC_TS [차수]
	 * </pre>
	 */
	public IDataSet fInqEqpPpayXclDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        
        try {
            // 1. DU lookup
            DEPEqpPpayXclMgmt dEPEqpPpayXclMgmt = (DEPEqpPpayXclMgmt) lookupDataUnit(DEPEqpPpayXclMgmt.class);

            // 2. LISTDM호출
            responseData = dEPEqpPpayXclMgmt.dSEqpPpayXclDtl(reqDs, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금요금선납재집계</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:53:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : STRD_YM [정산년월]
	 *	- field : TYP_CD [유형코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSaveEqpPpayXcl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        
        try {
            // 1. DU lookup
            DEPEqpPpayXclMgmt dEPEqpPpayXclMgmt = (DEPEqpPpayXclMgmt) lookupDataUnit(DEPEqpPpayXclMgmt.class);
            
            IDataSet dsCnt = dEPEqpPpayXclMgmt.dSEqpPpayXclChk(reqDs, onlineCtx);
            if( dsCnt.getIntField("CNT") > 0){
                throw new BizRuntimeException("DMS00150"); //진행중인 전표가 있습니다.
            }
            
            IRecordSet seqRs = dEPEqpPpayXclMgmt.dSEqpPpayXclSeq(reqDs, onlineCtx).getRecordSet("RS_EQP_PPAY_SEQ");
            IDataSet seqDs = null;
            
            if (seqRs != null) {
                for (int i = 0; i < seqRs.getRecordCount(); i++) {
                    seqDs = new DataSet();
                    seqDs.putFieldMap(seqRs.getRecordMap(i));
                    seqDs.putField("USERNO", requestData.getField("USERNO"));

                    dEPEqpPpayXclMgmt.dUEqpPpayXclDel(seqDs, onlineCtx);
                    dEPEqpPpayXclMgmt.dUEqpPpayXclDtlDel(seqDs, onlineCtx);
                }
            }

            // 2. DM호출
            dEPEqpPpayXclMgmt.dIEqpPpayXcl(reqDs, onlineCtx);
            dEPEqpPpayXclMgmt.dIEqpPpayXclDtl(reqDs, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금요금선납전표발행</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:53:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : FEE_PPAY_XCL_NO [요금 선납 정산 번호]
	 *		- field : FEE_PPAY_STRD_YM [요금 선납 기준 년월]
	 *		- field : INVE_TYP_CD [재고 유형 코드]
	 *		- field : FEE_PPAY_AMT [요금 선납 금액]
	 *		- field : FEE_PPAY_XCL_CNT [요금 선납 정산 건수]
	 *		- field : SLIP_DT [요금 선납 전표 일자]
	 *		- field : SLIP_NO [요금 선납 전표 번호]
	 *		- field : SLIP_ST [요금 선납 전표 상태]
	 *		- field : FEE_PPAY_CNCL_SLIP_DT [요금 선납 취소 전표 일자]
	 *		- field : FEE_PPAY_CNCL_SLIP_NO [요금 선납 취소 전표 번호]
	 *		- field : DEALCO_BLICENS_NO [사업자 번호]
	 *		- field : YYYY [전표 년도]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSaveEqpPpayXclSlip(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        CommonArea ca = getCommonArea(onlineCtx);

        try {
            
            reqDs.putField("USERNO", ca.getUserNo());
            reqDs.putField("SLIP_TYPE", "EP_XP");

            ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
            DataSetXmlTransformer.dataSetToXml(reqDs, bout, "UTF-8");
            String dsXml = bout.toString("UTF-8");

            // call on-demand batch job
            Map<String ,String> params = new HashMap<String ,String>();
            params.put("TASK_ID", "EPR010");
            params.put("TASK_NM", "전표발행");
            params.put("USER_NO", ca.getUserNo());
            params.put("COMPONENTNAME_LOCAL_ONLY", "dms.inf.EPR010");               
            params.put("POST_SLIP_DATASET", dsXml);
            String jobExecutionId = callBatchJob("EPR010", params, onlineCtx);
            waitBatchJobEnd(jobExecutionId, 10000);
            int result = getJobReturnCode(jobExecutionId);

            if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금요금선납전표취소</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:53:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : FEE_PPAY_XCL_NO [요금 선납 정산 번호]
	 *		- field : FEE_PPAY_STRD_YM [요금 선납 기준 년월]
	 *		- field : INVE_TYP_CD [재고 유형 코드]
	 *		- field : FEE_PPAY_AMT [요금 선납 금액]
	 *		- field : FEE_PPAY_XCL_CNT [요금 선납 정산 건수]
	 *		- field : SLIP_DT [요금 선납 전표 일자]
	 *		- field : SLIP_NO [요금 선납 전표 번호]
	 *		- field : SLIP_ST [요금 선납 전표 상태]
	 *		- field : FEE_PPAY_CNCL_SLIP_DT [요금 선납 취소 전표 일자]
	 *		- field : FEE_PPAY_CNCL_SLIP_NO [요금 선납 취소 전표 번호]
	 *		- field : DEALCO_BLICENS_NO [사업자 번호]
	 *		- field : YYYY [전표 년도]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSaveEqpPpayXclSlipDel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        CommonArea ca = getCommonArea(onlineCtx);

        try {
            reqDs.putField("USERNO", ca.getUserNo());
            reqDs.putField("SLIP_TYPE", "EP_XP");

            ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
            DataSetXmlTransformer.dataSetToXml(reqDs, bout, "UTF-8");
            String dsXml = bout.toString("UTF-8");

            // call on-demand batch job
            Map<String ,String> params = new HashMap<String ,String>();
            params.put("TASK_ID", "EPR011");
            params.put("TASK_NM", "전표취소");
            params.put("USER_NO", ca.getUserNo());
            params.put("COMPONENTNAME_LOCAL_ONLY", "dms.inf.EPR011");               
            params.put("POST_SLIP_DATASET", dsXml);
            String jobExecutionId = callBatchJob("EPR011", params, onlineCtx);
            waitBatchJobEnd(jobExecutionId, 10000);
            int result = getJobReturnCode(jobExecutionId);

            if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금클럽T재집계</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:53:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : STRD_YM [정산년월]
	 *	- field : TYP_CD [유형코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSaveEqpClubTXcl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        
        try {
            // 1. DU lookup
            DEPEqpPpayXclMgmt dEPEqpPpayXclMgmt = (DEPEqpPpayXclMgmt) lookupDataUnit(DEPEqpPpayXclMgmt.class);
            
            IDataSet dsCnt = dEPEqpPpayXclMgmt.dSEqpPpayXclChk(reqDs, onlineCtx);
            if( dsCnt.getIntField("CNT") > 0){
                throw new BizRuntimeException("DMS00150"); //진행중인 전표가 있습니다.
            }
            
            IRecordSet seqRs = dEPEqpPpayXclMgmt.dSEqpPpayXclSeq(reqDs, onlineCtx).getRecordSet("RS_EQP_PPAY_SEQ");
            IDataSet seqDs = null;
            
            if (seqRs != null) {
                for (int i = 0; i < seqRs.getRecordCount(); i++) {
                    seqDs = new DataSet();
                    seqDs.putFieldMap(seqRs.getRecordMap(i));
                    seqDs.putField("USERNO", requestData.getField("USERNO"));

                    dEPEqpPpayXclMgmt.dUEqpPpayXclDel(seqDs, onlineCtx);
                    dEPEqpPpayXclMgmt.dUEqpPpayXclDtlDel(seqDs, onlineCtx);
                }
            }

            // 2. DM호출
            dEPEqpPpayXclMgmt.dIEqpClubTXcl(reqDs, onlineCtx);
            dEPEqpPpayXclMgmt.dIEqpClubTXclDtl(reqDs, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금클럽T전표발행</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:53:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : FEE_PPAY_XCL_NO [요금 선납 정산 번호]
	 *		- field : FEE_PPAY_STRD_YM [요금 선납 기준 년월]
	 *		- field : INVE_TYP_CD [재고 유형 코드]
	 *		- field : FEE_PPAY_AMT [요금 선납 금액]
	 *		- field : FEE_PPAY_XCL_CNT [요금 선납 정산 건수]
	 *		- field : SLIP_DT [요금 선납 전표 일자]
	 *		- field : SLIP_NO [요금 선납 전표 번호]
	 *		- field : SLIP_ST [요금 선납 전표 상태]
	 *		- field : FEE_PPAY_CNCL_SLIP_DT [요금 선납 취소 전표 일자]
	 *		- field : FEE_PPAY_CNCL_SLIP_NO [요금 선납 취소 전표 번호]
	 *		- field : DEALCO_BLICENS_NO [사업자 번호]
	 *		- field : YYYY [전표 년도]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSaveEqpClubTXclSlip(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        CommonArea ca = getCommonArea(onlineCtx);

        try {
            
            reqDs.putField("USERNO", ca.getUserNo());
            reqDs.putField("SLIP_TYPE", "EP_XT");

            ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
            DataSetXmlTransformer.dataSetToXml(reqDs, bout, "UTF-8");
            String dsXml = bout.toString("UTF-8");

            // call on-demand batch job
            Map<String ,String> params = new HashMap<String ,String>();
            params.put("TASK_ID", "EPR010");
            params.put("TASK_NM", "전표발행");
            params.put("USER_NO", ca.getUserNo());
            params.put("COMPONENTNAME_LOCAL_ONLY", "dms.inf.EPR010");               
            params.put("POST_SLIP_DATASET", dsXml);
            String jobExecutionId = callBatchJob("EPR010", params, onlineCtx);
            waitBatchJobEnd(jobExecutionId, 10000);
            int result = getJobReturnCode(jobExecutionId);

            if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금클럽T전표취소</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-11-02 16:16:49
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : FEE_PPAY_XCL_NO [요금 선납 정산 번호]
	 *		- field : FEE_PPAY_STRD_YM [요금 선납 기준 년월]
	 *		- field : INVE_TYP_CD [재고 유형 코드]
	 *		- field : FEE_PPAY_AMT [요금 선납 금액]
	 *		- field : FEE_PPAY_XCL_CNT [요금 선납 정산 건수]
	 *		- field : SLIP_DT [요금 선납 전표 일자]
	 *		- field : SLIP_NO [요금 선납 전표 번호]
	 *		- field : SLIP_ST [요금 선납 전표 상태]
	 *		- field : FEE_PPAY_CNCL_SLIP_DT [요금 선납 취소 전표 일자]
	 *		- field : FEE_PPAY_CNCL_SLIP_NO [요금 선납 취소 전표 번호]
	 *		- field : DEALCO_BLICENS_NO [사업자 번호]
	 *		- field : YYYY [전표 년도]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSaveEqpClubTXclSlipDel(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        CommonArea ca = getCommonArea(onlineCtx);

        try {
            reqDs.putField("USERNO", ca.getUserNo());
            reqDs.putField("SLIP_TYPE", "EP_XT");

            ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
            DataSetXmlTransformer.dataSetToXml(reqDs, bout, "UTF-8");
            String dsXml = bout.toString("UTF-8");

            // call on-demand batch job
            Map<String ,String> params = new HashMap<String ,String>();
            params.put("TASK_ID", "EPR011");
            params.put("TASK_NM", "전표취소");
            params.put("USER_NO", ca.getUserNo());
            params.put("COMPONENTNAME_LOCAL_ONLY", "dms.inf.EPR011");               
            params.put("POST_SLIP_DATASET", dsXml);
            String jobExecutionId = callBatchJob("EPR011", params, onlineCtx);
            waitBatchJobEnd(jobExecutionId, 10000);
            int result = getJobReturnCode(jobExecutionId);

            if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금클럽T엑셀업로드검증</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:53:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_IN_EXCEL_LIST
	 *		- field : FEE_PPAY_STRD_YM [정산기준년월]
	 *		- field : CNSL_MGMT_NO [접수관리번호]
	 *		- field : XCL_AMT [정산금액]
	 *		- field : CNSL_DT [상담일자]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : INVE_TYP_CD [재고유형]
	 *		- field : PRCH_MGMT_NO [매입관리번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_ST [단말기등급]
	 *		- field : REPV_AGN_ORG_CD [조직코드]
	 *		- field : CLCT_DT [회수일자]
	 *		- field : FEE_DEDC_PROC_DT [공제처리일자]
	 *		- field : SKN_JDG_AMT [SKN감정금액]
	 *		- field : SKN_SMPL_JDG_DAMT [SKN샘플감정차액]
	 *		- field : AGN_DDCT_AMT [차감금액]
	 *		- field : AGN_EXPNS_CONF_AMT [대리점비용확정금액]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EXCEL_LIST
	 *		- field : FEE_PPAY_STRD_YM [정산기준년월]
	 *		- field : CNSL_MGMT_NO [접수관리번호]
	 *		- field : XCL_AMT [정산금액]
	 *		- field : CNSL_DT [상담일자]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : INVE_TYP_CD [재고유형]
	 *		- field : PRCH_MGMT_NO [매입관리번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_ST [단말기등급]
	 *		- field : REPV_AGN_ORG_CD [조직코드]
	 *		- field : CLCT_DT [회수일자]
	 *		- field : FEE_DEDC_PROC_DT [공제처리일자]
	 *		- field : SKN_JDG_AMT [SKN감정금액]
	 *		- field : SKN_SMPL_JDG_DAMT [SKN샘플감정차액]
	 *		- field : AGN_DDCT_AMT [차감금액]
	 *		- field : AGN_EXPNS_CONF_AMT [대리점비용확정금액]
	 *		- field : ERR_DESC [오류내용]
	 * </pre>
	 */
	public IDataSet fInqEqpClubTXclExcelErrLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        
        String FEE_PPAY_STRD_YM = "";
    
        try {

            // 1. DU lookup
            DEPEqpPpayXclMgmt dEPEqpPpayXclMgmt = (DEPEqpPpayXclMgmt) lookupDataUnit(DEPEqpPpayXclMgmt.class);
            
            IRecordSet excelRs = reqDs.getRecordSet("RS_IN_EXCEL_LIST");
            excelRs.addHeader(new RecordHeader("ERR_DESC"));

            if (excelRs != null) {
                
                IRecord excelRd = null;
                for (int i = 0; i < excelRs.getRecordCount(); i++) {
                    excelRd = excelRs.getRecord(i);
                    
                    if(excelRd.get("CNSL_MGMT_NO") == null){
                        excelRd.set("ERR_DESC", "접수관리번호가 없습니다.");
                        continue;
                    }
                    
                    int chkCnsl = 0;
                    for (int k = 0; k < excelRs.getRecordCount(); k++) {
                        if(StringUtils.equals(excelRd.get("CNSL_MGMT_NO").trim(), excelRs.get(k, "CNSL_MGMT_NO").trim())){
                            chkCnsl++;
                        }
                    }
                    
                    if(chkCnsl > 1){
                        excelRd.set("ERR_DESC", "중복된 접수관리번호가 있습니다.");
                        continue;
                    }
                    
                    if(excelRd.get("FEE_PPAY_STRD_YM") == null){
                        excelRd.set("ERR_DESC", "정산기준년월이 없습니다.");
                        continue;
                    }
                    
                    if(excelRd.get("FEE_PPAY_STRD_YM").trim().length() != 6){
                        excelRd.set("ERR_DESC", "정산기준년월은 (YYYYMM)으로 입력 하셔야 합니다.");
                        continue;
                    }
                    
                    if(!StringUtils.isNumeric(excelRd.get("FEE_PPAY_STRD_YM").trim())){
                        excelRd.set("ERR_DESC", "정산기준년월은 숫자형식으로 입력 하셔야 합니다.");
                        continue;
                    }
                    
                    if(i==0){
                        FEE_PPAY_STRD_YM = excelRd.get("FEE_PPAY_STRD_YM").trim();
                    }
                    
                    if(!StringUtils.equals(FEE_PPAY_STRD_YM, excelRd.get("FEE_PPAY_STRD_YM").trim())){
                        excelRd.set("ERR_DESC", "정산기준년월이 일치하지 않습니다.");
                        continue;
                    }
                    
                    if(excelRd.get("XCL_AMT") == null){
                        excelRd.set("ERR_DESC", "정산금액이 없습니다.");
                        continue;
                    }
                    
                    if(!NumberUtils.isNumber(excelRd.get("XCL_AMT").trim())){
                        excelRd.set("ERR_DESC", "정산금액은 숫자형식으로 입력 하셔야 합니다.");
                        continue;
                    }
                    
                    reqDs.putField("CNSL_MGMT_NO", excelRd.get("CNSL_MGMT_NO").trim());
                    IDataSet xclDs = dEPEqpPpayXclMgmt.dSEqpClubTXclExcelList(reqDs, onlineCtx);
                    if (xclDs.getFieldCount() == 0) {
                        excelRd.set("ERR_DESC", "데이터가 없습니다.");
                        continue;
                    }
                    
                    excelRd.set("FEE_PPAY_STRD_YM", excelRd.get("FEE_PPAY_STRD_YM").trim()); //정산기준년월
                    excelRd.set("INVE_TYP_CD", xclDs.getField("INVE_TYP_CD")); //재고유형
                    excelRd.set("CNSL_MGMT_NO", xclDs.getField("CNSL_MGMT_NO")); //접수관리번호
                    excelRd.set("PRCH_MGMT_NO", xclDs.getField("PRCH_MGMT_NO")); //매입관리번호
                    excelRd.set("EQP_MDL_CD", xclDs.getField("EQP_MDL_CD")); //모델코드
                    excelRd.set("EQP_MDL_NM", xclDs.getField("EQP_MDL_NM")); //모델명
                    excelRd.set("EQP_SER_NO", xclDs.getField("EQP_SER_NO")); //일련번호
                    excelRd.set("EQP_ST", xclDs.getField("EQP_ST")); //단말기등급
                    excelRd.set("REPV_AGN_ORG_CD", xclDs.getField("REPV_AGN_ORG_CD")); //조직코드
                    excelRd.set("CNSL_DT", xclDs.getField("CNSL_DT")); //상담일자
                    excelRd.set("CLCT_DT", xclDs.getField("CLCT_DT")); //회수일자
                    excelRd.set("FEE_DEDC_PROC_DT", xclDs.getField("FEE_DEDC_PROC_DT")); //공제처리일자
                    excelRd.set("XCL_AMT", excelRd.get("XCL_AMT").trim()); //정산금액
                    excelRd.set("SKN_JDG_AMT", xclDs.getField("SKN_JDG_AMT")); //SKN감정금액
                    excelRd.set("SKN_SMPL_JDG_DAMT", xclDs.getField("SKN_SMPL_JDG_DAMT")); //SKN샘플감정차액
                    excelRd.set("AGN_DDCT_AMT", xclDs.getField("AGN_DDCT_AMT")); //차감금액
                    excelRd.set("AGN_EXPNS_CONF_AMT", xclDs.getField("AGN_EXPNS_CONF_AMT")); //대리점비용확정금액
                }
            }
            
            responseData.putRecordSet("RS_EXCEL_LIST", excelRs);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금클럽T엑셀저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 09:53:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_IN_EXCEL_LIST
	 *		- field : FEE_PPAY_STRD_YM [정산기준년월]
	 *		- field : INVE_TYP_CD [재고유형]
	 *		- field : CNSL_MGMT_NO [접수관리번호]
	 *		- field : PRCH_MGMT_NO [매입관리번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : EQP_ST [단말기등급]
	 *		- field : REPV_AGN_ORG_CD [조직코드]
	 *		- field : CNSL_DT [상담일자]
	 *		- field : CLCT_DT [회수일자]
	 *		- field : FEE_DEDC_PROC_DT [공제처리일자]
	 *		- field : XCL_AMT [정산금액]
	 *		- field : SKN_JDG_AMT [SKN감정금액]
	 *		- field : SKN_SMPL_JDG_DAMT [SKN샘플감정차액]
	 *		- field : AGN_DDCT_AMT [차감금액]
	 *		- field : AGN_EXPNS_CONF_AMT [대리점비용확정금액]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSaveEqpClubTXclExcel(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
        IDataSet delDs = null;
        IDataSet seqDs = null;
        IDataSet xclDs = null;
        IDataSet xclDtlDs = null;
        IRecord excelRd = null;
        
        String FEE_PPAY_STRD_YM = "";
        String INVE_TYP_CD = "";
        int FEE_PPAY_XCL_CNT = 0;
        int FEE_PPAY_AMT = 0;
        int FEE_PPAY_XCL_DTL_SEQ = 1;

        try {
            // 1. DU lookup
            DEPEqpPpayXclMgmt dEPEqpPpayXclMgmt = (DEPEqpPpayXclMgmt) lookupDataUnit(DEPEqpPpayXclMgmt.class);
            
            IRecordSet excelRs = reqDs.getRecordSet("RS_IN_EXCEL_LIST");
            
            if(excelRs != null){
                
                for (int i = 0; i < excelRs.getRecordCount(); i++) {
                    excelRd = excelRs.getRecord(i);
                    FEE_PPAY_XCL_CNT++;
                    FEE_PPAY_AMT += Integer.parseInt(excelRd.get("XCL_AMT"));
                    FEE_PPAY_STRD_YM = excelRd.get("FEE_PPAY_STRD_YM");
                    INVE_TYP_CD = excelRd.get("INVE_TYP_CD");
                }
                
                reqDs.putField("STRD_YM", FEE_PPAY_STRD_YM);
                reqDs.putField("TYP_CD", "20");
                IDataSet dsCnt = dEPEqpPpayXclMgmt.dSEqpPpayXclChk(reqDs, onlineCtx);
                if( dsCnt.getIntField("CNT") > 0){
                    throw new BizRuntimeException("DMS00150"); //진행중인 전표가 있습니다.
                }
                
                IRecordSet seqRs = dEPEqpPpayXclMgmt.dSEqpPpayXclSeq(reqDs, onlineCtx).getRecordSet("RS_EQP_PPAY_SEQ");
                
                if (seqRs != null) {
                    for (int i = 0; i < seqRs.getRecordCount(); i++) {
                        delDs = new DataSet();
                        delDs.putFieldMap(seqRs.getRecordMap(i));
                        delDs.putField("USERNO", requestData.getField("USERNO"));

                        dEPEqpPpayXclMgmt.dUEqpPpayXclDel(delDs, onlineCtx);
                        dEPEqpPpayXclMgmt.dUEqpPpayXclDtlDel(delDs, onlineCtx);
                    }
                }
            
                seqDs = new DataSet();
                seqDs = dEPEqpPpayXclMgmt.dSEqpClubTXclSeq(reqDs, onlineCtx);
                String seq = seqDs.getField("FEE_PPAY_XCL_NO");
                
                xclDs = new DataSet();
                xclDs.putField("FEE_PPAY_XCL_NO", seq);
                xclDs.putField("FEE_PPAY_STRD_YM", FEE_PPAY_STRD_YM);
                xclDs.putField("INVE_TYP_CD", INVE_TYP_CD);
                xclDs.putField("FEE_PPAY_XCL_CNT", FEE_PPAY_XCL_CNT);
                xclDs.putField("FEE_PPAY_AMT", FEE_PPAY_AMT);
                xclDs.putField("FEE_PPAY_TYP_CD", "20");
                xclDs.putField("USERNO", reqDs.getField("USERNO"));
                
                dEPEqpPpayXclMgmt.dIEqpClubTXclExcel(xclDs, onlineCtx);
                
                for (int i = 0; i < excelRs.getRecordCount(); i++) {
                    excelRd = excelRs.getRecord(i);
                    xclDtlDs = new DataSet();
                    xclDtlDs.putField("FEE_PPAY_XCL_NO", seq);
                    xclDtlDs.putField("FEE_PPAY_XCL_DTL_SEQ", FEE_PPAY_XCL_DTL_SEQ++);
                    xclDtlDs.putField("INVE_TYP_CD", excelRd.get("INVE_TYP_CD"));
                    xclDtlDs.putField("CNSL_MGMT_NO", excelRd.get("CNSL_MGMT_NO"));
                    xclDtlDs.putField("PRCH_MGMT_NO", excelRd.get("PRCH_MGMT_NO"));
                    xclDtlDs.putField("EQP_MDL_CD", excelRd.get("EQP_MDL_CD"));
                    xclDtlDs.putField("EQP_SER_NO", excelRd.get("EQP_SER_NO"));
                    xclDtlDs.putField("EQP_ST", excelRd.get("EQP_ST"));
                    xclDtlDs.putField("REPV_AGN_ORG_CD", excelRd.get("REPV_AGN_ORG_CD"));
                    xclDtlDs.putField("CNSL_DT", excelRd.get("CNSL_DT"));
                    xclDtlDs.putField("CLCT_DT", excelRd.get("CLCT_DT"));
                    xclDtlDs.putField("FEE_DEDC_PROC_DT", excelRd.get("FEE_DEDC_PROC_DT"));
                    xclDtlDs.putField("XCL_AMT", excelRd.get("XCL_AMT"));
                    xclDtlDs.putField("SKN_JDG_AMT", excelRd.get("SKN_JDG_AMT"));
                    xclDtlDs.putField("SKN_SMPL_JDG_DAMT", excelRd.get("SKN_SMPL_JDG_DAMT"));
                    xclDtlDs.putField("AGN_DDCT_AMT", excelRd.get("AGN_DDCT_AMT"));
                    xclDtlDs.putField("AGN_EXPNS_CONF_AMT", excelRd.get("AGN_EXPNS_CONF_AMT"));
                    xclDtlDs.putField("USERNO", reqDs.getField("USERNO"));
                    
                    dEPEqpPpayXclMgmt.dIEqpClubTXclExcelDtl(xclDtlDs, onlineCtx);
                }
            }
            
        } catch ( BizRuntimeException e ) {
            throw e;
        }
    
        return responseData;
    }
  
}
