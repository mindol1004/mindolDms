package dms.nr.nrseabase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.utils.HpcUtils;
import fwk.utils.PagenateUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]단말기회수정보관리</li>
 * <li>설  명 : <pre>단말기회수정보관리FU</pre></li>
 * <li>작성일 : 2015-07-17 16:39:51</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class FNREqpClctInfoMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNREqpClctInfoMgmt(){
		super();
	}

	/**
	 * <pre>단말기회수정보조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 16:39:51
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : NEW_CNTRTR_NM [고객명]
	 *	- field : SVC_NO [이동전화번호]
	 *	- field : SKN_SDT [계약시작일]
	 *	- field : SKN_EDT [계약종료일]
	 *	- field : RTN_STA_DT [반납시작일]
	 *	- field : RTN_END_DT [반납종료일]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : IN_STA_DT [물류센터회수시작일]
	 *	- field : IN_END_DT [물류센터회수종료일]
	 *	- field : IN_OBJ_DTL_CD [회수상태]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_CLCT_INFO_LIST
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : NEW_CNTRTR_NM [고객명]
	 *		- field : SVC_NO [이동전화번호]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_COLOR_CD [색상코드]
	 *		- field : PET_NM [펫네임]
	 *		- field : RENTAL_CNTRT_PRD [계약기간코드]
	 *		- field : NEW_CNTRT_DT [계약일]
	 *		- field : RENTAL_CNTRT_STA_DT [계약시작일]
	 *		- field : RENTAL_CNTRT_END_DT [계약종료일]
	 *		- field : EQP_PRCH_AMT [매입금액]
	 *		- field : FISCL_REMPRC [잔존가]
	 *		- field : RTN_REG_DT [단말반납일]
	 *		- field : EQP_RTN_YN [단말반납여부]
	 *		- field : EQP_IN_DT [R센터회수일]
	 *		- field : CAPA_CD [용량코드]
	 *		- field : SKN_CLCT_DT [SKN입고일]
	 *		- field : CLCT_OBJ_REG_DT [회수대상등록일]
	 * </pre>
	 */
	public IDataSet fInqEqpClctInfoLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    IDataSet dsCnt = new DataSet();
        requestData.putField("SVC_NO", HpcUtils.encodeByAES(requestData.getField("SVC_NO")));
        requestData.putField("NEW_CNTRTR_NM", HpcUtils.encodeByAES(requestData.getField("NEW_CNTRTR_NM")));
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet usrListRs = null;
		int intTotalCnt = 0;

		try {
			// 1. DU lookup
			DNREqpClctInfoMgmt dNREqpClctInfoMgmt = (DNREqpClctInfoMgmt) lookupDataUnit(DNREqpClctInfoMgmt.class);
			
			// 2. TOTAL COUNT DM호출
			dsCnt = dNREqpClctInfoMgmt.dSEqpClctInfoTotCnt(requestData, onlineCtx);
			intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);

			// 3. LISTDM호출
			responseData = dNREqpClctInfoMgmt.dSEqpClctInfoLstPaging(reqDs, onlineCtx);
			usrListRs = responseData.getRecordSet("RS_EQP_CLCT_INFO_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(usrListRs, reqDs, intTotalCnt);
			
		} catch ( BizRuntimeException e ) {
			throw e; //시스템 오류가 발생하였습니다.
		}
	
	    return responseData;
	}

	/**
	 * <pre>단말기회수정보상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 16:39:51
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_CLCT_INFO_DTL
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : NEW_CNTRTR_NM [고객명]
	 *		- field : SVC_NO [이동전화번호]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : COLOR_CD [색상코드]
	 *		- field : PET_NM [펫네임]
	 *		- field : CNTRT_PRD [계약기간코드]
	 *		- field : CNTRT_STA_DT [계약시작일]
	 *		- field : CNTRT_END_DT [계약종료일]
	 *		- field : EQP_PRCH_AMT [매입금액]
	 *		- field : FISCL_REMPRC [잔존가]
	 *		- field : CAPA_CD [용량코드]
	 *		- field : EQP_RTN_DT [반납일]
	 *		- field : EQP_RTN_YN [단말반납여부]
	 *		- field : NEW_CNTRT_DT [계약일]
	 *		- field : EQP_IN_DT [R센터입고일]
	 *		- field : SKN_CLCT_DT [물류센터회수일]
	 * </pre>
	 */
	public IDataSet fInqEqpClctInfoDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
	        requestData.putField("SVC_NO", HpcUtils.encodeByAES(requestData.getField("SVC_NO"))); //이동전화암호화인코딩
 			// 1. DU lookup
	    	DNREqpClctInfoMgmt dNREqpClctInfoMgmt = (DNREqpClctInfoMgmt) lookupDataUnit(DNREqpClctInfoMgmt.class);

 			// 2. LISTDM호출
 			responseData = dNREqpClctInfoMgmt.dSEqpClctInfoDtl(requestData, onlineCtx);
 			
 		} catch ( BizRuntimeException e ) {
 			throw e; //시스템 오류가 발생하였습니다.
 		} 
	
	    return responseData;
	}
  
}
