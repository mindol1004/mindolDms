package dms.ep.epscsbase.biz;

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
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]단말기매입관리</li>
 * <li>설  명 : <pre>[DU]단말기매입관리</pre></li>
 * <li>작성일 : 2015-08-21 15:39:31</li>
 * <li>작성자 : 양재석 (jsyang)</li>
 * </ul>
 *
 * @author 양재석 (jsyang)
 */
public class DEPEqpPrchMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPEqpPrchMgmt(){
		super();
	}

    /**
	 * <pre>[DM]단말기매입대상목록조회총건수</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-21 15:39:31
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : TOTAL_CNT [총건수]
	 * </pre>
	 */
	public IDataSet dSEqpPrchObjListTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SInqEqpPrchObjListTotCnt", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>[DM]단말기매입대상조회목록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-21 15:39:31
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpPrchObjListPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInqEqpPrchObjListPaging", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("PRCH_OBJ_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>[DM]단말기매입확인조회목록총건수</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-21 15:39:31
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : TOTAL_CNT [총건수]
	 * </pre>
	 */
	public IDataSet dSEqpPrchRegListTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SInqEqpPrchRegListTotCnt", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>[DM]단말기매입확인조회목록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-24 20:29:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpPrchRegListPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInqEqpPrchRegListPaging", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("PRCH_REG_LIST", rsReturn);

        return responseData;
    }
  
}
