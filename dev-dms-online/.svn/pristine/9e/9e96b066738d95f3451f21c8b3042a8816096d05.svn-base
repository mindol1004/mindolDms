package dms.nr.nrsplbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;

import org.apache.commons.lang.StringUtils;

import fwk.utils.HpcUtils;
import fwk.utils.PagenateUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]단말기정책관리</li>
 * <li>설  명 : <pre>단말기정책관리FU</pre></li>
 * <li>작성일 : 2015-07-10 10:07:07</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class FNREqpPolyMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNREqpPolyMgmt(){
		super();
	}

	/**
	 * <pre>단말기별 정책정보를 조회한다</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-16 14:15:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : POLY_NM [정책명]
	 *	- field : RENTAL_POLY_EFF_PRD_STA_DT [유효기간시작일]
	 *	- field : RENTAL_POLY_EFF_PRD_END_DT [유효기간종료일]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : TB_RENTAL_SALE_POLY
	 *		- field : RENTAL_POLY_NO [렌탈정책번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : POLY_NM [정책명]
	 *		- field : OUT_PRC [출고가]
	 *		- field : EXPT_DSPSL_PRC [예상매각가]
	 *		- field : RENTAL_PRN [렌탈원금]
	 *		- field : RENTAL_FEE [렌탈요금]
	 *		- field : CNTRT_PRD [계약기간코드]
	 *		- field : RENTAL_POLY_EFF_PRD_STA_DT [유효기간시작일]
	 *		- field : RENTAL_POLY_EFF_PRD_END_DT [유효기간종료일]
	 *		- field : OP_CL_CD [업무구분코드]
	 *		- field : CAPA_CD [용량코드]
	 *		- field : FS_REG_USER_ID [최초등록사용자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경사용자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 *		- field : RENTAL_POLY_END_YN [정책사용여부]
	 *		- field : URGT_YN [정책긴급적용여부]
	 * </pre>
	 */
	public IDataSet fInqEqpPolyLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet usrListRs = null;
		int intTotalCnt = 0;

		try {
			// 1. DU lookup
			DNREqpPolyMgmt dNREqpPolyMgmt = (DNREqpPolyMgmt) lookupDataUnit(DNREqpPolyMgmt.class);
			
			// 2. TOTAL COUNT DM호출
			dsCnt = dNREqpPolyMgmt.dSEqpPolyTotCnt(requestData, onlineCtx);
			intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);

			// 3. LISTDM호출
			responseData = dNREqpPolyMgmt.dSEqpPolyLstPaging(reqDs, onlineCtx);
			usrListRs = responseData.getRecordSet("RS_EQP_POLY_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(usrListRs, reqDs, intTotalCnt);
			
		} catch ( BizRuntimeException e ) {
			throw e; //시스템 오류가 발생하였습니다.
		}
	
	    return responseData;
	}

	/**
	 * <pre>입력된 내용으로 단말정책정보를 등록한다</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-16 14:15:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_POLY_LIST
	 *		- field : POLY_NM [정책명]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : OUT_PRC [출고가]
	 *		- field : RENTAL_PRN [렌탈원금]
	 *		- field : RENTAL_FEE [렌탈요금]
	 *		- field : EXPT_DSPSL_PRC [예상매각가]
	 *		- field : RENTAL_POLY_NO [렌탈정책번호]
	 *		- field : RENTAL_POLY_EFF_PRD_STA_DT [유효기간시작일]
	 *		- field : RENTAL_POLY_EFF_PRD_END_DT [유효기간종료일]
	 *		- field : CNTRT_PRD [계약기간코드]
	 *		- field : OP_CL_CD [업무구분코드]
	 *		- field : CAPA_CD [용량코드]
	 *		- field : RENTAL_POLY_END_YN [정책사용여부]
	 *		- field : URGT_YN [정책긴급적용여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegEqpPoly(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    try {
	    	// 필수값 체크
			if ( StringUtils.isEmpty(requestData.getField("EQP_MDL_CD")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("EQP_MDL_CD") }); // 필수입력항목 {0}이/가 누락되었습니다.
			} 
	    	//정책종료여부 판단
			if("Y".equals(requestData.getField("RENTAL_POLY_END_YN"))){
				throw new BizRuntimeException("DMS00119",new String[] { HpcUtils.getLangMsg("정책종료여부") ,"사용"});//{0}는 {1}만 입력가능합니다.
            }
			// 1. DU lookup
			DNREqpPolyMgmt dNREqpPolyMgmt = (DNREqpPolyMgmt) lookupDataUnit(DNREqpPolyMgmt.class);
			
			// 긴급아니면 유효시작일 유효성 체크
			if( "N".equals(requestData.getField("URGT_YN") ) ){
				if( Integer.parseInt(DateUtils.getCurrentDate("yyyyMMdd")) >= Integer.parseInt(requestData.getField("RENTAL_POLY_EFF_PRD_STA_DT")) ){
	                throw new BizRuntimeException("DMS00069"); //이전날짜는 선택이 불가능합니다.	            	
	            }
				dNREqpPolyMgmt.dUEqpPolyDate(requestData, onlineCtx);
			//긴급이면 중복체크	
			}else if( "Y".equals(requestData.getField("URGT_YN") ) ){
				IDataSet dsRtn = dNREqpPolyMgmt.dSEqpPolyDateIChk(requestData, onlineCtx);
				if( dsRtn.getIntField("RENTAL_DT_CNT") > 0){
					/* TODO : 에러코드입력 필요함({유효기간}에  중복 데이터가 있습니다). 현재는 중복데이타가 있습니다로 사용.*/
					throw new BizRuntimeException("DMS00003");
				}
			
			}
           
            dNREqpPolyMgmt.dIEqpPoly(requestData, onlineCtx);
			
		} catch ( BizRuntimeException e ) {
			throw e;
		}
	    
	    return responseData;
	}

	/**
	 * <pre>입력된 내용으로 단말정책정보를 수정한다</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-16 14:15:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_POLY_LIST
	 *		- field : RENTAL_FEE [렌탈요금]
	 *		- field : CNTRT_PRD [계약기간코드]
	 *		- field : RENTAL_POLY_EFF_PRD_STA_DT [유효기간시작일]
	 *		- field : RENTAL_POLY_EFF_PRD_END_DT [유효기간종료일]
	 *		- field : OUT_PRC [출고가]
	 *		- field : EXPT_DSPSL_PRC [예상매각가]
	 *		- field : RENTAL_POLY_NO [렌탈정책번호]
	 *		- field : RENTAL_PRN [렌탈원금]
	 *		- field : RENTAL_POLY_END_YN [정책종료여부]
	 *		- field : URGT_YN [정책긴급적용여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdEqpPoly(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
	    	// 필수값 체크
			if ( StringUtils.isEmpty(requestData.getField("EQP_MDL_CD")) ) {
				throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("EQP_MDL_CD") }); // 필수입력항목 {0}이/가 누락되었습니다.
			} 
	    	
			// 1. DU lookup
			DNREqpPolyMgmt dNREqpPolyMgmt = (DNREqpPolyMgmt) lookupDataUnit(DNREqpPolyMgmt.class);
			
			 // 유효시작일 유효성 체크
			IDataSet dsRtn = dNREqpPolyMgmt.dSEqpPolyDateUChk(requestData, onlineCtx);
            if( dsRtn.getIntField("RENTAL_DT_CNT") > 0){
                /* TODO : {0} 건은 처리가 불가능 합니다..*/
                throw new BizRuntimeException("DMS00087",new String[] { "기등록된정책" });
            }
            
			// 2. 업무 DM호출
            
            responseData = dNREqpPolyMgmt.dUEqpPoly(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
	
	    return responseData;
	}
  
}
