package dms.pr.prsimbase.biz;

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
 * <li>업무 그룹명 : dms/임대R</li>
 * <li>단위업무명: [DU]SKT교품반환등록</li>
 * <li>설  명 : <pre>SKT교품반환등록</pre></li>
 * <li>작성일 : 2015-08-31 15:10:48</li>
 * <li>작성자 : 양재석 (jsyang)</li>
 * </ul>
 *
 * @author 양재석 (jsyang)
 */
public class DPRSKTEqpExpartBackReg extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DPRSKTEqpExpartBackReg(){
		super();
	}

    /**
	 * <pre>[DM]매입단말기조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-31 19:45:31
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqInEqpList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInqInEqpList", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_IN_EQP_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>교품단말기계약등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-31 15:10:48
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIExpartEqpCntrtRgst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IExpartEqpCntrtRgst", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>교품단말기계약상세등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-31 15:10:48
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIExpartEqpCntrtDtlRgst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IExpartEqpCntrtDtlRgst", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>교품대상단말기출고일련번호조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-31 15:10:48
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EQP_OUT_NO [단말기출고번호]
	 * </pre>
	 */
	public IDataSet dSExpartObjEqpOutNum(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SExpartObjEqpOutNum", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>교품대상단말기출고등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-01 09:43:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIExpartObjEqpOutReg(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {       
            dbInsert("IExpartObjEqpOutReg", requestData, onlineCtx);   
        }
        catch ( BizRuntimeException e ) {
            throw e;
        } 
    
        return responseData;
    }

    /**
	 * <pre>[DM]교품대상단말기자산정보수정</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-01 09:49:57
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUExpartObjEqpAssetInfoUpd(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {       
            dbInsert("UExpartObjEqpAssetInfoUpd", requestData, onlineCtx); 
        }
        catch ( BizRuntimeException e ) {
            throw e;
        } 
    
        return responseData;
    }

    /**
	 * <pre>SKT교품반환등록조회목록총건수</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-31 15:10:48
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : TOTAL_CNT [총건수]
	 * </pre>
	 */
	public IDataSet dSSKTEqpExpartBackRegTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SSKTEqpExpartBackRegTotCnt", requestData, onlineCtx);
        
        // 2.결과값 셋팅     
        responseData.putFieldMap(record); 
    
        return responseData;
    }

    /**
	 * <pre>SKT교품반환등록조회목록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-31 15:10:48
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSSKTEqpExpartBackRegPaging(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SSKTEqpExpartBackRegPaging", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_EXPART_BACK_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>교품단말기계약교품정보수정</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-01 11:22:08
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUExpartEqpCntrtInfoUpd(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {       
            dbInsert("UExpartEqpCntrtInfoUpd", requestData, onlineCtx); 
        }
        catch ( BizRuntimeException e ) {
            throw e;
        } 
    
        return responseData;
    }

    /**
	 * <pre>교품단말기계약상세삭제</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-09-02 08:27:25
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDExpartEqpCntrtDtlDel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {       
            dbInsert("DExpartEqpCntrtDtlDel", requestData, onlineCtx); 
        }
        catch ( BizRuntimeException e ) {
            throw e;
        } 
    
        return responseData;
    }
  
}
