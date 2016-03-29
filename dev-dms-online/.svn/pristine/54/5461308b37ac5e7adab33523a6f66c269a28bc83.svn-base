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
 * <li>단위업무명: [DU]재감정재고관리</li>
 * <li>설  명 : <pre>[DU]재감정재고관리</pre></li>
 * <li>작성일 : 2015-08-19 13:30:57</li>
 * <li>작성자 : 김윤환 (kyh0904)</li>
 * </ul>
 *
 * @author 김윤환 (kyh0904)
 */
public class DEPRJdgInveMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPRJdgInveMgmt(){
		super();
	}

	/**
	 * <pre>[DM]재감정현황조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-08-19 13:30:57
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSRJdgPrstListPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecordSet rsReturn = dbSelect("SRJdgPrstListPaging", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putRecordSet("RS_JDG_PRST_LST", rsReturn);
    
        return responseData;
	}

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-08-19 13:30:57
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSRJdgPrstListTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SRJdgPrstListTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>[DM]재감정재고취소목록페이징조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-09-21 10:18:59
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSRJdgInveCnclPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SRJdgInveCnclPaging", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_RJDG_INVE_CNCL_LIST", rsReturn);
        return responseData;
    }

    /**
	 * <pre>[DM]재감정재고취소목록총건수</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-09-21 10:19:32
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSRdgInveCnclTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SRJdgInveCnclTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>[DM]재고삭제</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-09-21 10:19:59
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDTbEpDisDis(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        dbUpdate("DTbEpDisDis", requestData, onlineCtx);
        return responseData;
    }

    /**
	 * <pre>[DM]재고금액삭제</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-09-21 10:20:11
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDTbEpDisDisAmt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        dbUpdate("DTbEpDisDisAmt", requestData, onlineCtx);
        return responseData;
    }

    /**
	 * <pre>[DM]상품재고삭제</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-09-21 10:20:21
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDTbEpDisProdDis(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        dbUpdate("DTbEpDisProdDis", requestData, onlineCtx);
        return responseData;
    }

    /**
	 * <pre>[DM]매입관리진행상태수정</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-09-21 10:20:31
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUTbEpCstPrchsProgrSt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        dbUpdate("UTbEpCstPrchsProgrSt", requestData, onlineCtx);
        return responseData;
    }
  
}
