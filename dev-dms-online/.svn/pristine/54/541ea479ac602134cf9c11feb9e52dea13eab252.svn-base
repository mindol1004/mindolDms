package dms.nr.nrsplbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.utils.PagenateUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]단말기준정보관리</li>
 * <li>설  명 : <pre>단말기준정보관리FU</pre></li>
 * <li>작성일 : 2015-07-09 16:59:20</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class FNREqpStdMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNREqpStdMgmt(){
		super();
	}

	/**
	 * <pre>단말기준정보조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-16 14:15:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : OP_CL_CD [업무구분코드]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : EQP_MDL_NM [모델명]
	 *	- field : MFACT_CD [제조사코드]
	 *	- field : ESI_USE_YN [사용여부]
	 *	- field : POP_YN [팝업여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_STD_LIST
	 *		- field : OP_CL_CD [업무구분코드]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : MFACT_CD [제조사코드]
	 *		- field : LAUNC_DT [출시일]
	 *		- field : PET_NM [펫네임]
	 *		- field : CAPA_CD [저장용량코드]
	 *		- field : ESI_USE_YN [사용여부]
	 *		- field : ESI_RMK [비고]
	 *		- field : MDL_NO [모델번호]
	 *		- field : FS_REG_USER_ID [최초등록사용자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경사용자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 * </pre>
	 */
	public IDataSet fInqEqpStdLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet rsList = null;
		int intTotalCnt = 0;

		try {
			// 1. DU lookup
			DNREqpStdMgmt dNREqpStdMgmt = (DNREqpStdMgmt) lookupDataUnit(DNREqpStdMgmt.class);
			
			// 2. TOTAL COUNT DM호출
			dsCnt = dNREqpStdMgmt.dSEqpStdTotCnt(requestData, onlineCtx);
			intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);

			// 3. LISTDM호출
			responseData = dNREqpStdMgmt.dSEqpStdLstPaging(reqDs, onlineCtx);
			rsList = responseData.getRecordSet("RS_EQP_STD_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(rsList, reqDs, intTotalCnt);
			
		} catch ( BizRuntimeException e ) {
			throw e; //시스템 오류가 발생하였습니다.
		}
	
	    return responseData;
	}

	/**
	 * <pre>단말기준정보저장</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-16 14:15:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_STD_FRM
	 *		- field : OP_CL_CD [업무구분코드]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : PET_NM [펫네임]
	 *		- field : MFACT_CD [제조사코드]
	 *		- field : LAUNC_DT [출시일]
	 *		- field : CAPA_CD [저장용량코드]
	 *		- field : ESI_USE_YN [사용여부]
	 *		- field : ESI_RMK [비고]
	 *		- field : MDL_NO [모델번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegEqpStd(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
	    	
	    	// 1. DU lookup
	    	DNREqpStdMgmt dNREqpStdMgmt = (DNREqpStdMgmt) lookupDataUnit(DNREqpStdMgmt.class);
			
	    	// 2. Validation DM호출
			IDataSet valDS = dNREqpStdMgmt.dSEqpStdListChk(requestData, onlineCtx);
			if ( Integer.parseInt(valDS.getField("ROW_CNT")) > 0 ) {
				throw new BizRuntimeException("DMS00003"); //중복입력 된 데이터가 존재합니다.
			}
			// 3. 업무 DM호출
			responseData = dNREqpStdMgmt.dIEqpStd(requestData, onlineCtx);
			
		} catch ( BizRuntimeException e ) {
			throw e;
		}
	
	    return responseData;
	}

	/**
	 * <pre>단말기준정보수정</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-16 14:15:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_STD_FRM
	 *		- field : OP_CL_CD [업무구분코드]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : PET_NM [펫네임]
	 *		- field : MFACT_CD [제조사코드]
	 *		- field : LAUNC_DT [출시일]
	 *		- field : CAPA_CD [저장용량코드]
	 *		- field : ESI_USE_YN [사용여부]
	 *		- field : ESI_RMK [비고]
	 *		- field : MDL_NO [모델번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdEqpStd(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {	    	
			// 1. DU lookup
	    	DNREqpStdMgmt dNREqpStdMgmt = (DNREqpStdMgmt) lookupDataUnit(DNREqpStdMgmt.class);
			// 2. 업무 DM호출
			responseData = dNREqpStdMgmt.dUEqpStd(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
	
	    return responseData;
	}
  
}
