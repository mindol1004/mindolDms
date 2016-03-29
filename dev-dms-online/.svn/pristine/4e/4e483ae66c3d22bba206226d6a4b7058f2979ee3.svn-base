package dms.nr.nrsfxbase.biz;

import java.util.Map;

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
 * <li>단위업무명: [DU]FPA매각가산정</li>
 * <li>설  명 : <pre>[DU]FPA매각가산정</pre></li>
 * <li>작성일 : 2015-10-20 10:52:01</li>
 * <li>작성자 : 문재웅 (jaiwoong)</li>
 * </ul>
 *
 * @author 문재웅 (jaiwoong)
 */
public class DNRFpaDspslPrcEst extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DNRFpaDspslPrcEst(){
		super();
	}

    /**
	 * <pre>[DM]FPA매각가산정 총 건수</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-10-20 10:53:20
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaDspslPrcEstTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SFpaDspslPrcEstTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SUM_LIST", rsReturn); 
    
        return responseData;
    }

    /**
	 * <pre>[DM]FPA매각가산정조회</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-10-20 10:53:43
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaDspslPrcEstLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SFpaDspslPrcEstLstPaging", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_FPA_DSPSL_PRC_EST_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>[DM]FPA매각가산정전체엑셀조회</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-10-20 13:45:51
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaDspslPrcEstAllExcel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SFpaDspslPrcEstAllExcel", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_FPA_DSPSL_PRC_EST_ALL_EXCEL", rsReturn);
    
        return responseData;
    }
  
}
