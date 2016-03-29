package dms.pr.prspmbase.biz;

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
 * <li>단위업무명: [DU]매입교품관리</li>
 * <li>설  명 : <pre>매입교품관리</pre></li>
 * <li>작성일 : 2015-07-10 09:05:46</li>
 * <li>작성자 : 양재석 (jsyang)</li>
 * </ul>
 *
 * @author 양재석 (jsyang)
 */
public class DPRExpartMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DPRExpartMgmt(){
		super();
	}

	/**
	 * <pre>매입교품조회총건수</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-16 13:35:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : STA_IN_DT [조회입고시작일]
	 *	- field : END_IN_DT [조회입고종료일]
	 *	- field : IN_DEALCO_CD [입고처코드]
	 *	- field : IN_PLC_NM [입고처명]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : EQP_MDL_NM [모델명]
	 *	- field : EQP_SER_NO [단말기일련번호]
	 *	- field : EQP_IMEI_NO [단말기IMEI번호]
	 *	- field : INVE_ST_CD [재고상태코드]
	 *	- field : BOX_NO [BOX NO]
	 *	- field : OP_CL_CD [업무구분코드]
	 *	- field : EXPART_YN [교품여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_LIST
	 *		- field : IN_DT [입고일자]
	 *		- field : IN_DEALCO_CD [입고처코드]
	 *		- field : IN_PLC_NM [입고처명]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_COLOR_CD [단말기색상코드]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EQP_IMEI_NO [단말기IMEI번호]
	 *		- field : INVE_ST_CD [재고상태코드]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : LAST_EXPART_DT [최종교품일자]
	 *		- field : EXPART_EQP_SER_NO [교품단말기일련번호]
	 *		- field : EXPART_EQP_IMEI_NO [교품단말기IMEI번호]
	 *		- field : EXPART_EQP_COLOR_CD [교품단말기색상코드]
	 *		- field : EXPART_IN_DT [교품입고일자]
	 * </pre>
	 */
	public IDataSet dSExpartEqpTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출     
 		IRecord record = dbSelectSingle("SExpartEqpTotCnt", requestData, onlineCtx);
 		// 2.결과값 셋팅     
 		responseData.putFieldMap(record);

	    return responseData;
	}

	/**
	 * <pre>매입교품조회목록</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-16 13:35:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : STA_IN_DT [조회입고시작일]
	 *	- field : END_IN_DT [조회입고종료일]
	 *	- field : IN_DEALCO_CD [입고처코드]
	 *	- field : IN_PLC_NM [입고처명]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : EQP_MDL_NM [모델명]
	 *	- field : EQP_SER_NO [단말기일련번호]
	 *	- field : EQP_IMEI_NO [단말기IMEI번호]
	 *	- field : INVE_ST_CD [재고상태코드]
	 *	- field : BOX_NO [BOX NO]
	 *	- field : OP_CL_CD [업무구분코드]
	 *	- field : EXPART_YN [교품여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_LIST
	 *		- field : IN_DT [입고일자]
	 *		- field : IN_DEALCO_CD [입고처코드]
	 *		- field : IN_PLC_NM [입고처명]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_COLOR_CD [단말기색상코드]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EQP_IMEI_NO [단말기IMEI번호]
	 *		- field : INVE_ST_CD [재고상태코드]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : LAST_EXPART_DT [최종교품일자]
	 *		- field : EXPART_EQP_SER_NO [교품단말기일련번호]
	 *		- field : EXPART_EQP_IMEI_NO [교품단말기IMEI번호]
	 *		- field : EXPART_EQP_COLOR_CD [교품단말기색상코드]
	 *		- field : EXPART_IN_DT [교품입고일자]
	 * </pre>
	 */
	public IDataSet dSExpartEqpPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
 		IRecordSet rsReturn = dbSelect("SExpartEqpPaging", requestData, onlineCtx);
 		// 2.결과값 셋팅
 		responseData.putRecordSet("RS_EQP_LIST", rsReturn);

	    return responseData;
	}

	/**
	 * <pre>매입교품대상단말기상태수정</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-16 13:35:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUExpartEqpAssetInfoUpd(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
		
	    try {		
	    	dbInsert("UExpartEqpAssetInfoUpd", requestData, onlineCtx);	
	    }
	    catch ( BizRuntimeException e ) {
			throw e;
		} 
	
	    return responseData;
	}

	/**
	 * <pre>교품정보등록</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-16 13:35:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIExpartEqpReg(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
		
	    try {		
	    	dbInsert("IExpartEqpInfoRgt", requestData, onlineCtx);	
	    }
	    catch ( BizRuntimeException e ) {
			throw e;
		} 
	
	    return responseData;
    }

	/**
	 * <pre>[DM]동일교품단말기건수</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-16 13:35:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSChkExpartEqp(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SChkExpartEqp", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
	    return responseData;
	}

	/**
	 * <pre>[DM]교품단말기입고등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-07-16 13:35:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIExpartEqpInReg(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
		
	    try {		
	    	dbInsert("IExpartEqpInReg", requestData, onlineCtx);	
	    }
	    catch ( BizRuntimeException e ) {
			throw e;
		} 
	
	    return responseData;
	}

	/**
	 * <pre>[DM]교품단말기출고등록</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-07-16 13:35:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIExpartEqpOutReg(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
		
	    try {		
	    	dbInsert("IExpartEqpOutReg", requestData, onlineCtx);	
	    }
	    catch ( BizRuntimeException e ) {
			throw e;
		} 
	
	    return responseData;
	}

	/**
	 * <pre>[DM]교품단말기출고일련번호</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-07-16 13:35:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EQP_OUT_NO [단말기출고번호]
	 * </pre>
	 */
	public IDataSet dSEqpOutNum(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SEqpOutNum", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
	    return responseData;
	}

	/**
	 * <pre>[DM]동일교품단말기자산건수</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-07-16 17:33:00
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSChkExpartEqpInve(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SChkExpartEqpInve", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
	    return responseData;
	}

	/**
	 * <pre>[DM]교폼단말기정보삭제</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-07-16 13:35:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ASSET_NO [자산번호]
	 *	- field : EQP_EXPART_DT [단말기교품일자]
	 *	- field : LS_CHG_USER_ID [최종변경사용자ID]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDExpartEqpInfoDel(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {		
	    	dbInsert("DExpartEqpInfoDel", requestData, onlineCtx);	
	    }
	    catch ( BizRuntimeException e ) {
			throw e;
		} 
	
	    return responseData;
	}

	/**
	 * <pre>[DM]교품단말기최종교품일_지정일외</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-07-16 13:35:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ASSET_NO [자산번호]
	 *	- field : EQP_EXPART_DT [단말기교품일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EQP_EXPART_DT [단말기교품일자]
	 * </pre>
	 */
	public IDataSet dSInqExpartEqpLastExDt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {		
		    // 1.쿼리문 호출     
	        IRecord record = dbSelectSingle("SInqExpartEqpLastExDt", requestData, onlineCtx);
	        // 2.결과값 셋팅     
	        responseData.putFieldMap(record);
	    }
	    catch ( BizRuntimeException e ) {
			throw e;
		} 
	
	    return responseData;
	}

}
