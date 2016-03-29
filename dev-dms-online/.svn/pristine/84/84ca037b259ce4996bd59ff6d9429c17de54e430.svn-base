package dms.fw.fwsbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.BaseUtils;

import org.apache.commons.lang.StringUtils;

import fwk.common.CommonArea;
import fwk.utils.PagenateUtils;


/**
 * <ul>
 * <li>업무 그룹명 : HPC/프레임워크</li>
 * <li>단위업무명: FFWKCMessageMgmt</li>
 * <li>설  명 : FWK 메시지 테이블관리를 하기 위한 FU</li>
 * <li>작성일 : 2014-08-06 09:49:53</li>
 * <li>작성자 : admin (admin)</li>
 * </ul>
 *
 * @author admin (admin)
 */
public class FFWKCMessageMgmt extends fwk.base.FunctionUnit  {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FFWKCMessageMgmt(){
		super();
	}

	/**
	 * 메시지조회 FM
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : MESSAGE_ID [메시지 ID]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : LOCALE_XD [로케일코드]
	 *	- field : MESSAGE_ID [메시지 ID]
	 *	- field : MESSAGE_NAME [메시지 Name]
	 *	- field : MESSAGE_REASON [메시지 사유]
	 *	- field : MESSAGE_TYPE_XD [메시지 타입]
	 *	- field : MESSAGE_REMARK [메시지 약어]
	 *	- field : BIZ_GROUP_XD [업무그룹코드]
	 *	- field : FS_REG_USER_ID [최초등록자ID]
	 *	- field : FS_REG_DTM [최초등록일]
	 *	- field : LS_CHG_USER_ID [수정자ID]
	 *	- field : LS_CHG_DTM [수정일]
	 * </pre>
	 */
	public IDataSet fSelectMessage(IDataSet requestData, IOnlineContext onlineCtx){
		IDataSet msgReqDs = (IDataSet)requestData.clone();
	    IDataSet responseData = new DataSet();
	    DXYZTMessageTb dXYZTMessageTb = (DXYZTMessageTb)lookupDataUnit(DXYZTMessageTb.class);
	    try {
	    	_setLocale(msgReqDs, onlineCtx);
	    	responseData = dXYZTMessageTb.dSMsgInfo(msgReqDs, onlineCtx);
	    	
	    } catch (BizRuntimeException e) {
	    	throw new BizRuntimeException("SKFE2101", e);
	    }    
	    return responseData;
	}

	/**
	 * 페이징 처리된 메시지를 여러 검색조건으로 찾을 수 있도록 구성함.
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-08-06 09:49:53
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : MESSAGE_ID [메시지 ID]
	 *	- field : MESSAGE_NAME [메시지 Name]
	 *	- field : MESSAGE_TYPE_XD [메시지 타입]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : msgList
	 *		- field : LOCALE_XD [로케일코드]
	 *		- field : MESSAGE_ID [메시지 ID]
	 *		- field : MESSAGE_NAME [메시지 Name]
	 *		- field : MESSAGE_REASON [메시지 사유]
	 *		- field : MESSAGE_TYPE_XD [메시지 타입]
	 *		- field : MESSAGE_REMARK [메시지 약어]
	 *		- field : BIZ_GROUP_XD [업무그룹코드]
	 *		- field : FS_REG_USER_ID [최초등록자ID]
	 *		- field : FS_REG_DTM [최초등록일]
	 *		- field : LS_CHG_USER_ID [수정자ID]
	 *		- field : LS_CHG_DTM [수정일]
	 *		- field : POS_RESP_CD [POS응답코드]
	 *		- field : CO_RESP_CD [제휴응답코드]
	 *		- field : CO_RESP_DTL_CD [제휴응답상세코드]
	 * </pre>
	 */
	public IDataSet fSelectMessageList(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    IDataSet msgCntResDs = new DataSet();
	    IDataSet msgReqDs = (IDataSet)requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
	    IRecordSet msgListRs = null;
	    int msgTotalCnt = 0;
	    DXYZTMessageTb dXYZTMessageTb = (DXYZTMessageTb)lookupDataUnit(DXYZTMessageTb.class);
	    try {
	    	_setLocale(msgReqDs, onlineCtx);//CommonArea에 있는 로케일 설정
	    	msgCntResDs = dXYZTMessageTb.dSMsgCnt(msgReqDs, onlineCtx);
	    	msgTotalCnt = Integer.parseInt(msgCntResDs.getField("msgTotalCnt"));//메시지 총건수 취득
	    	
	    	PagenateUtils.setPagenatedParamsToDataSet(msgReqDs);//UI에서 넘오온 현재 페이지와 페이지당 건수를 설정한다.
	    	
	    	responseData = dXYZTMessageTb.dSMsgListByPaging(msgReqDs, onlineCtx);//메시지전체 목록을 취득한다.
	    	msgListRs = responseData.getRecordSet("RS_MSG_LIST");
	    	PagenateUtils.setPagenatedParamToRecordSet(msgListRs, msgReqDs, msgTotalCnt);//
	    } catch (BizRuntimeException e) {
	    	throw new BizRuntimeException("SKFE2101", e);
	    } 
	    return responseData;
	}

	/**
	 * 메시지 등록 FM
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-08-06 09:49:53
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : MESSAGE_ID [메시지 ID]
	 *	- field : MESSAGE_NAME [메시지 Name]
	 *	- field : MESSAGE_REASON [메시지 사유]
	 *	- field : MESSAGE_TYPE_XD [메시지 타입]
	 *	- field : MESSAGE_REMARK [메시지 약어]
	 *	- field : POS_RESP_CD [POS응답코드]
	 *	- field : CO_RESP_CD [제휴응답코드]
	 *	- field : CO_RESP_DTL_CD [제휴응답상세코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegMessage(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    IDataSet msgReqDs = (IDataSet)requestData.clone();
	    DXYZTMessageTb dXYZTMessageTb = (DXYZTMessageTb)lookupDataUnit(DXYZTMessageTb.class);
	    
	    // Validation DM호출 2015.01.14 메세지ID MAX+1 자동 채번으로 변경됨
	   /* IDataSet valDS = dXYZTMessageTb.dSExistMsgId(requestData, onlineCtx);
	    if(Integer.parseInt(valDS.getField("ROW_CNT"))>0){
	    	throw new BizRuntimeException("DMS00003"); // 중복입력 된 데이터가 존재합니다.\n\n데이터를 확인해 주세요.
	    }
	    */
	    try {
	    	//새 메세지ID 채번 
	    	 IDataSet rsUserDS = dXYZTMessageTb.dSNewMsgId(requestData, onlineCtx);
	    	 msgReqDs.putField("MESSAGE_ID_NEW", rsUserDS.getField("MESSAGE_ID_NEW"));
	    	_setLocale(msgReqDs, onlineCtx);
	    	//메세지 등록dm호출
	    	dXYZTMessageTb.dIMsgInfo(msgReqDs, onlineCtx);
	    } catch (BizRuntimeException e) {
	    	throw new BizRuntimeException("SKFE2101", e);
	    }    
	    return responseData;
	}

	/**
	 * 메시지 수정FM
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-08-06 09:49:53
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : LOCALE_XD [로케일코드]
	 *	- field : MESSAGE_ID [메시지 ID]
	 *	- field : MESSAGE_NAME [메시지 Name]
	 *	- field : MESSAGE_REASON [메시지 사유]
	 *	- field : MESSAGE_TYPE_XD [메시지 타입]
	 *	- field : MESSAGE_REMARK [메시지 약어]
	 *	- field : POS_RESP_CD [POS응답코드]
	 *	- field : CO_RESP_CD [제휴응답코드]
	 *	- field : CO_RESP_DTL_CD [제휴응답상세코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdateMessage(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    IDataSet msgReqDs = (IDataSet)requestData.clone();
	    DXYZTMessageTb dXYZTMessageTb = (DXYZTMessageTb)lookupDataUnit(DXYZTMessageTb.class);
	    
	    // Validation DM호출
	    IDataSet valDS = dXYZTMessageTb.dSExistMsgId(requestData, onlineCtx);
	    if(Integer.parseInt(valDS.getField("ROW_CNT")) ==0){
	    	throw new BizRuntimeException("DMS00004"); //데이터가 존재하지 않습니다.\n\n데이터를 확인해 주세요.
	    }
	    
	    try {
	    	if(StringUtils.isNotEmpty(requestData.getField("LOCALE_XD"))) {
	    		msgReqDs.putField("LOCALE_XD", requestData.getField("LOCALE_XD"));
	    	} else {
	    		_setLocale(msgReqDs, onlineCtx);
	    	}
	    	
	    	dXYZTMessageTb.dUMsgInfo(msgReqDs, onlineCtx);
	    } catch (BizRuntimeException e) {
	    	throw new BizRuntimeException("SKFE2101", e);
	    }	    
	    return responseData;
	}

	/**
	 * 메시지 삭제FM
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : MESSAGE_ID [메시지 ID]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fDeleteMessage(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    IDataSet msgReqDs = (IDataSet)requestData.clone();
	    DXYZTMessageTb dXYZTMessageTb = (DXYZTMessageTb)lookupDataUnit(DXYZTMessageTb.class);
	    try {
	    	_setLocale(msgReqDs, onlineCtx);
	    	dXYZTMessageTb.dDMsgInfo(msgReqDs, onlineCtx);
	    } catch (BizRuntimeException e) {
	    	throw new BizRuntimeException("SKFE2101", e);
	    } 	    
	    return responseData;
	}
	
	/**
	 * 메시지 전체 목록을 조회한다
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSelectMsaageListForAll(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    IDataSet msgReqDs = (IDataSet)requestData.clone();
	    DXYZTMessageTb dXYZTMessageTb = (DXYZTMessageTb)lookupDataUnit(DXYZTMessageTb.class);
	    try {
	    	_setLocale(msgReqDs, onlineCtx);
	    	responseData = dXYZTMessageTb.dSAllMsgList(msgReqDs, onlineCtx);
	    } catch (BizRuntimeException e) {
	    	throw new BizRuntimeException("SKFE2101", e);
	    }     
	    return responseData;
	}
  
	/**
	 * 로케일을 설정하기 위한 private메소드
	 *  
	 * @param requestData
	 * @param onlineCtx void
	 */
	private void _setLocale(IDataSet requestData, IOnlineContext onlineCtx) {
		CommonArea ca = getCommonArea(onlineCtx);
		if(ca==null || StringUtils.isEmpty(ca.getUserLocale())) {
			requestData.putField("LOCALE_XD", BaseUtils.getDefaultLocale().toString());
    	} else {
    		requestData.putField("LOCALE_XD", ca.getUserLocale());
    	}
		
	}
}