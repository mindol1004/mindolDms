package dms.nr.nrseibase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [DU]고정자산감가상각</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-03 09:48:37</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class DNRComStlMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DNRComStlMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-10-16 10:25:12
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dSComStlLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
      	IRecordSet rsReturn = dbSelect("SComStlPaging", requestData, onlineCtx);
      	// 2.결과값 셋팅
      	responseData.putRecordSet("RS_CMMS_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-10-16 10:25:12
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dSComStlTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
      	IRecordSet rsReturn = dbSelect("SComStlTotCnt", requestData, onlineCtx);
      	// 2.결과값 셋팅
      	responseData.putRecordSet("RS_SUM_LIST", rsReturn);  
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-10-16 10:25:12
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dDComStl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        dbDelete("DComStl", requestData, onlineCtx);
        
        return responseData;
    }

    /**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-10-16 10:25:12
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIComStl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        dbInsert("IComStl", requestData, onlineCtx);

        return responseData;
    }

    /**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-10-16 10:25:12
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dSCntForValid(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecord record = dbSelectSingle("SCntForValid", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putFieldMap(record); 
        
        return responseData;
    }
  
}
