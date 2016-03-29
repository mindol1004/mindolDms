package dms.fw.fwsbase.biz;


import fwk.utils.HpcUtils;
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
 * <li>업무 그룹명 : HPC/프레임워크</li>
 * <li>단위업무명: [DU]메시지관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-08-05 14:08:37</li>
 * <li>작성자 : admin (admin)</li>
 * </ul>
 *
 * @author admin (admin)
 */
public class DXYZTMessageTb extends fwk.base.DataUnit  {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DXYZTMessageTb(){
		super();
	}

	/**
	 *
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSMsgListByPaging(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	//    log log = lo
	    
	    IRecordSet rs = dbSelect("SMsgListByPaging", requestData.getFieldMap(), onlineCtx);
	    responseData.putRecordSet("RS_MSG_LIST", rs);
	    return responseData;
	}

	/**
	 *
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : messageId [필드1]
	 *	- field : localeXd [필드2]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSMsgInfo(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    IRecordSet rs = dbSelect("SMsgInfo", requestData.getFieldMap(), onlineCtx);
	    if(rs.getRecordCount() != 1) {
	    	throw new BizRuntimeException("SKFS4031");
	    }
	    responseData.putFieldMap(rs.getRecordMap(0));
	    return responseData;
	}

	/**
	 *
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : messageId [메시지 ID]
	 *	- field : messageName [메시지 Name]
	 *	- field : messageReason [메시지 사유]
	 *	- field : messageTypeXd [메시지 타입]
	 *	- field : messageRemark [메시지 약어]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIMsgInfo(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    int insertCnt = dbInsert("IMsgInfo", requestData.getFieldMap(), onlineCtx);
	    if(insertCnt != 1) {
	    	throw new BizRuntimeException("SKFS4015", new String[]{HpcUtils.getLangMsg("[review](입력건수:"+insertCnt+"건)")});
	    }
	    return responseData;
	}

	/**
	 *
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : messageId [메시지 ID]
	 *	- field : messageName [메시지 Name]
	 *	- field : messageReason [메시지 사유]
	 *	- field : messageTypeXd [메시지 타입]
	 *	- field : messageRemark [메시지 약어]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUMsgInfo(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    int updateCnt = dbUpdate("UMsgInfo", requestData.getFieldMap(), onlineCtx);
	    if(updateCnt != 1) {
	    	throw new BizRuntimeException("SKFS4015", new String[]{HpcUtils.getLangMsg("[review](수정건수:"+updateCnt+"건)")});
	    }
	    return responseData;
	}

	/**
	 *
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : messageId [메시지 ID]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDMsgInfo(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    int deleteCnt = dbDelete("DMsgInfo", requestData.getFieldMap(), onlineCtx);
	    if(deleteCnt != 1) {
	    	throw new BizRuntimeException("SKFS4015", new String[]{HpcUtils.getLangMsg("[review](삭제건수:"+deleteCnt+"건)")});
	    }
	    return responseData;
	}

	/**
	 *
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSMsgCnt(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    IRecord record = dbSelectSingle("SMsgCnt", requestData.getFieldMap(), onlineCtx);
	    if(record.isEmpty()) {
	    	throw new BizRuntimeException("SKFS4031");
	    }
	    responseData.putFieldMap(record);
	    return responseData;
	}

	/**
	 *
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAllMsgList(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    IRecordSet rs = dbSelect("SAllMsgList", requestData.getFieldMap(), onlineCtx);
	    responseData.putRecordSet("RS_MSG_LIST", rs);
	    return responseData;
	}

	/**
	 *
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSExistMsgId(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    // 1. query 호출
	    IRecord record = dbSelectSingle("SExistMsgId", requestData, onlineCtx);
	    // 2. 결과값 셋팅
	    responseData.putFieldMap(record);
	    return responseData;
	}

	/**
	 * 새 메세지ID를 채번한다.
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2015-01-14 13:41:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSNewMsgId(IDataSet requestData, IOnlineContext onlineCtx) {
		  IDataSet responseData = new DataSet();
		    // 1. query 호출
		    IRecord record = dbSelectSingle("SNewMsgId", requestData, onlineCtx);
		    // 2. 결과값 셋팅
		    responseData.putFieldMap(record);
		    return responseData;
	}
  
}