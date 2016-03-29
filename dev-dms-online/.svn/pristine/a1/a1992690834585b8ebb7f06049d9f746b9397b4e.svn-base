package dms.nr.nrsxmbase.biz;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.data.xml.DataSetXmlTransformer;
import nexcore.framework.core.exception.BizRuntimeException;

import org.apache.commons.logging.Log;

import fwk.common.CommonArea;
import fwk.utils.PagenateUtils;

/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]매입취소정산관리</li>
 * <li>설  명 : <pre>[FU]매입취소정산관리</pre></li>
 * <li>작성일 : 2015-12-01 15:16:43</li>
 * <li>작성자 : 문재웅 (jaiwoong)</li>
 * </ul>
 *
 * @author 문재웅 (jaiwoong)
 */
public class FNRPrchCnclXclMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNRPrchCnclXclMgmt(){
		super();
	}

    /**
	 * <pre>[FU]매입취소정산조회</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-12-01 15:19:38
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqPrchCnclXcllst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet dsCnt = new DataSet();
        IRecordSet usrListRs = null;
        int intTotalCnt = 0;

        try {
            // 1. DU lookup
            DNRPrchCnclXclMgmt dNRPrchCnclXclMgmt = (DNRPrchCnclXclMgmt) lookupDataUnit(DNRPrchCnclXclMgmt.class);

            // 2. TOTAL COUNT DM호출
            dsCnt = dNRPrchCnclXclMgmt.dSPrchCnclXclTotCnt(requestData, onlineCtx);
            IRecordSet sumRs = dsCnt.getRecordSet("RS_SUM_LIST");
            
            intTotalCnt = Integer.parseInt(sumRs.get(0,"TOTAL_CNT"));
            PagenateUtils.setPagenatedParamsToDataSet(requestData);
            
            // 3. LISTDM호출
            responseData = dNRPrchCnclXclMgmt.dSPrchCnclXclPaging(requestData, onlineCtx);
            usrListRs = responseData.getRecordSet("RS_PRCH_CNCL_XCL_LIST");
            
            // 4.결과값 리턴
            responseData.putRecordSet("RS_SUM_LIST", sumRs);
            PagenateUtils.setPagenatedParamToRecordSet(sumRs, requestData, intTotalCnt);
            
            responseData.putRecordSet("RS_PRCH_CNCL_XCL_LIST",usrListRs);
            PagenateUtils.setPagenatedParamToRecordSet(usrListRs, requestData, intTotalCnt);
            
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>[FM]매입취소정산전표관리</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-12-08 18:01:27
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSlipPrchCnclXcl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();

        try {
            CommonArea ca = getCommonArea(onlineCtx); 

            ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
            DataSetXmlTransformer.dataSetToXml(requestData, bout, "UTF-8");
            String dsXml = bout.toString("UTF-8");
            
            if("02".equals(requestData.getField("IS_RECALL"))) {//전표생성
                // call on-demand batch job
                HashMap params = new HashMap<String,String>();
                params.put("TASK_ID", "EPR010");
                params.put("TASK_NM", "전표발행");
                params.put("USER_NO", ca.getUserNo());
                params.put("COMPONENTNAME_LOCAL_ONLY", "dms.inf.EPR010");               
                params.put("POST_SLIP_DATASET", dsXml);
                String jobExecutionId = callBatchJob("EPR010", params, onlineCtx);
                waitBatchJobEnd(jobExecutionId, 10000);
                int result = getJobReturnCode(jobExecutionId);
            
                if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.
            }else if("03".equals(requestData.getField("IS_RECALL"))) {//전표취소
                
                // call on-demand batch job
                HashMap params = new HashMap<String,String>();
                params.put("TASK_ID", "EPR011");
                params.put("TASK_NM", "전표삭제");
                params.put("USER_NO", ca.getUserNo());
                params.put("COMPONENTNAME_LOCAL_ONLY", "dms.inf.EPR011");               
                params.put("POST_SLIP_DATASET", dsXml);
                String jobExecutionId = callBatchJob("EPR011", params, onlineCtx);
                waitBatchJobEnd(jobExecutionId, 10000);
                int result = getJobReturnCode(jobExecutionId);
            
                if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.                
            }

        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;    
    }
}
