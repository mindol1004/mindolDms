package dms.ep.epsbibase.biz;

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
 * <li>단위업무명: [DU]업무목표관리</li>
 * <li>설  명 : <pre>업무목표관리</pre></li>
 * <li>작성일 : 2015-12-11 17:38:04</li>
 * <li>작성자 : 박민정 (dangnagu2)</li>
 * </ul>
 *
 * @author 박민정 (dangnagu2)
 */
public class DEPOpTargMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPOpTargMgmt(){
		super();
	}

    /**
	 * <pre>업무목표조회</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-12-11 17:38:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSOpTargLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecordSet rsReturn = dbSelect("SOpTargLst", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putRecordSet("RS_OP_TARG_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>업무목표등록</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-12-14 16:16:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIOpTargReg(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        dbInsert("IOpTargReg", requestData, onlineCtx);   
        return responseData;
    }

    /**
	 * <pre>업무목표중복체크</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-12-14 17:32:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSOpTargChk(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        // 1.쿼리문 호출
            IRecord record = dbSelectSingle("SOpTargChk", requestData, onlineCtx);
            // 2.결과값 셋팅
            responseData.putFieldMap(record);
                
        return responseData;
    }

    /**
	 * <pre>업무목표수정</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-12-14 22:30:27
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUpdOpTargUpd(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        try {       
            dbInsert("UpdOpTargUpd", requestData, onlineCtx);  
        }
        catch ( BizRuntimeException e ) {
            throw e;
        } 
    
        return responseData;
    }
  
}
