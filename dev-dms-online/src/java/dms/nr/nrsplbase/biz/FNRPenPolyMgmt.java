package dms.nr.nrsplbase.biz;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import fwk.utils.HpcUtils;
import fwk.utils.PagenateUtils;
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
 * <li>단위업무명: [FU]위약금정책관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-07-10 10:16:06</li>
 * <li>작성자 : 김혁범 (kezmain1)</li>
 * </ul>
 *
 * @author 김혁범 (kezmain1)
 */
public class FNRPenPolyMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNRPenPolyMgmt(){
		super();
	}

	

	/**
	 *
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-16 14:15:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : OP_CL_CD [업부구분코드]
	 *	- field : PEN_POLY_EFF_PRD_STA_DT [유효기간시작일자]
	 *	- field : PEN_POLY_EFF_PRD_END_DT [유효기간종료일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_STD_LIST
	 *		- field : OP_CL_CD [업무구분]
	 *		- field : PEN_POLY_EFF_PRD_STA_DT [유효기간시작일자]
	 *		- field : PEN_POLY_EFF_PRD_END_DT [유효기간종료일자]
	 *		- field : TOT_RENTAL_FEE_RT [총렌탈료비율]
	 *		- field : REM_RENTAL_FEE_RT [잔여렌탈료비율]
	 *		- field : DAY_RENTAL_FEE_RT [일렌탈료비율]
	 *		- field : PEN_POLY_APLY_DT [일자]
	 *		- field : RENTAL_FEE_RT [렌탈료비율]
	 *		- field : FS_REG_DTM [최초등록일자]
	 * </pre>
	 */


	public IDataSet fInqPenPolyLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet usrListRs = null;
		int intTotalCnt = 0;

		try {
			// 1. DU lookup
			DNRPenPolyMgmt dNRPenPolyMgmt = (DNRPenPolyMgmt) lookupDataUnit(DNRPenPolyMgmt.class);
			
			// 2. TOTAL COUNT DM호출
			dsCnt = dNRPenPolyMgmt.dSPenPolyTotCnt(requestData, onlineCtx);
			intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);

			// 3. LISTDM호출
			responseData = dNRPenPolyMgmt.dSPenPolyLstPaging(reqDs, onlineCtx);
			usrListRs = responseData.getRecordSet("RS_PEN_POLY_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(usrListRs, reqDs, intTotalCnt);
			
		} catch ( BizRuntimeException e ) {
			throw e; //시스템 오류가 발생하였습니다.
		}
	
	    return responseData;

	}

	/**
	 *
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-16 14:15:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_PEN_POLY_LIST
	 *		- field : OP_CL_CD [업무구분코드]
	 *		- field : PEN_POLY_EFF_PRD_STA_DT [유효기간시작일자]
	 *		- field : PEN_POLY_EFF_PRD_END_DT [유효기간종료일자]
	 *		- field : TOT_RENTAL_FEE_RT [총렌탈료비율]
	 *		- field : REM_RENTAL_FEE_RT [잔여렌탈료비율]
	 *		- field : DAY_RENTAL_FEE_RT [일렌탈료비율]
	 *		- field : PEN_POLY_APLY_DT [일자]
	 *		- field : RENTAL_FEE_RT [렌탈료비율]
	 *		- field : PEN_POLY_RMK [비고]
	 *		- field : FS_REG_DTM [등록일]
	 *		- field : PEN_POLY_NO [위약금정책번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegPenPoly(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
	    	
			// 1. DU lookup
			DNRPenPolyMgmt dNRPenPolyMgmt = (DNRPenPolyMgmt) lookupDataUnit(DNRPenPolyMgmt.class);
			
			// 유효시작일 유효성 체크
			IDataSet dsRtn = dNRPenPolyMgmt.dSChkPolyFrDt(requestData, onlineCtx);
            if( dsRtn.getIntField("DUP_CNT") > 0){
                /* TODO : 에러코드입력 필요함({유효기간}에  주복 데이터가 있습니다). 현재는 중복데이타가 있습니다로 사용.*/
                throw new BizRuntimeException("DMS00003");
            }
			// 3. 업무 DM호출
			
			IDataSet dsSeq = dNRPenPolyMgmt.dSPenPolySeq(requestData, onlineCtx);
			requestData.putField("PEN_POLY_NO", dsSeq.getField("PEN_POLY_NO"));
			responseData = dNRPenPolyMgmt.dIPenPoly(requestData, onlineCtx);
			dNRPenPolyMgmt.dUPenPolyDate(requestData, onlineCtx);
			
			
		} catch ( BizRuntimeException e ) {
			throw e;
		}
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-16 14:15:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_PEN_POLY_LIST
	 *		- field : REM_RENTAL_FEE_RT [잔여렌탈료합계액]
	 *		- field : PEN_POLY_APLY_DT [일자]
	 *		- field : PEN_POLY_EFF_PRD_STA_DT [유효기간시작일자]
	 *		- field : PEN_POLY_EFF_PRD_END_DT [유효기간종료일자]
	 *		- field : PEN_POLY_RMK [비고]
	 *		- field : DAY_RENTAL_FEE_RT [일렌탈료비용]
	 *		- field : RENTAL_FEE_RT [연체료비용]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdPenPoly(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
			// 1. DU lookup
			DNRPenPolyMgmt dNRPenPolyMgmt = (DNRPenPolyMgmt) lookupDataUnit(DNRPenPolyMgmt.class);
			
			 // 유효시작일 유효성 체크
			IDataSet dsRtn = dNRPenPolyMgmt.dSChkPolyToDt(requestData, onlineCtx);
            if( dsRtn.getIntField("DUP_CNT") > 0){
                /* TODO : 에러코드입력 필요함({유효기간}에  주복 데이터가 있습니다). 현재는 중복데이타가 있습니다로 사용.*/
                throw new BizRuntimeException("DMS00003");
            }
            
			// 3. 업무 DM호출

            dNRPenPolyMgmt.dUPenPolyDate(requestData, onlineCtx);
			responseData = dNRPenPolyMgmt.dUPenPoly(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
	
	    return responseData;
	}
  
}
