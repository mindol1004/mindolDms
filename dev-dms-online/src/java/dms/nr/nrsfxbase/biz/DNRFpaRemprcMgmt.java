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
 * <li>단위업무명: [DU]FPA잔존가관리</li>
 * <li>설  명 : <pre>[DU]FPA잔존가관리</pre></li>
 * <li>작성일 : 2015-10-15 13:35:47</li>
 * <li>작성자 : 문재웅 (jaiwoong)</li>
 * </ul>
 *
 * @author 문재웅 (jaiwoong)
 */
public class DNRFpaRemprcMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DNRFpaRemprcMgmt(){
		super();
	}

    /**
	 * <pre>[DM]FPA잔존가모델 총 건수</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-10-15 13:37:41
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaRemprcEqpMdlTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SFpaRemprcEqpMdlTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SUM_LIST", rsReturn); 
    
        return responseData;
    }

    /**
	 * <pre>[DM]FPA잔존가모델조회</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-10-15 13:38:07
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaRemprcEqpMdlLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SFpaRemprcEqpMdlLstPaging", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_FPA_REMPRC_EQP_MDL_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>[DM]FPA잔존가조회</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-10-15 13:38:33
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFpaRemprcLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SSFpaRemprcLst", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_FPA_REMPRC_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>[DM]FPA잔존가 등록/수정</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-10-16 10:23:48
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIFpaRemprcSave(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();

        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        dbInsert("IFpaRemprc", requestData, onlineCtx);
        
        return responseData;
    }
  
}
