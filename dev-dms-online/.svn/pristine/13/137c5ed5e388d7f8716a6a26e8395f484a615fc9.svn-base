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
import nexcore.framework.core.util.DateUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]손해배상금정책 관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-07-10 09:43:49</li>
 * <li>작성자 : 안진갑 (bella21cjk)</li>
 * </ul>
 *
 * @author 안진갑 (bella21cjk)
 */
public class FNRCmpPolyMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNRCmpPolyMgmt(){
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
	 *	- field : OP_CL_CD [업무구분]
	 *	- field : EQP_MDL_CD [단말기 코드]
	 *	- field : DCP_EFF_PRD_STA_DT [유효시작일자]
	 *	- field : DCP_EFF_PRD_END_DT [유효종료일자]
	 *	- field : DCP_NO [손해배상금정책번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_CMP_POLY_LIST
	 *		- field : DCP_NO [손해배상금정책번호]
	 *		- field : OP_CL_CD [업무구분코드]
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : UNRTN [미반납]
	 *		- field : DCP_EFF_PRD_STA_DT [유효기간시작일]
	 *		- field : DCP_EFF_PRD_END_DT [유효기간종료일]
	 *		- field : FS_REG_DT [등록일]
	 * </pre>
	 */
	public IDataSet fInqCmpPolyLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
		IDataSet dsCnt = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		IRecordSet cmpPolyListRs = null;
		int intTotalCnt = 0;

		try {
			// 1. DU lookup
			DNRCmpPolyMgmt dNRCmpPolyMgmt = (DNRCmpPolyMgmt) lookupDataUnit(DNRCmpPolyMgmt.class);
			
			// 2. TOTAL COUNT DM호출
			dsCnt = dNRCmpPolyMgmt.dSCmpPolyTotCnt(requestData, onlineCtx);
			intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(reqDs);

			// 3. LISTDM호출
			responseData = dNRCmpPolyMgmt.dSCmpPolyLstPaging(reqDs, onlineCtx);
			cmpPolyListRs = responseData.getRecordSet("RS_CMP_POLY_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(cmpPolyListRs, reqDs, intTotalCnt);
			
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
	 *	- record : RS_CMP_POLY_DTL
	 *		- field : DCP_NO [손해배상금정책번호]
	 *		- field : INSP_DTL_ITM_CD [점검세부항목코드]
	 *		- field : INDF_AMT [변상금]
	 *		- field : DCP_ITM_RMK [비고]
	 *		- field : DCP_ITM_USE_YN [사용여부]
	 *	- field : DCP_NO [손해배상금정책번호]
	 *	- field : EQP_MDL_CD [단말기모델코드]
	 *	- field : DCP_EFF_PRD_STA_DT [유효기간시작일]
	 *	- field : DCP_EFF_PRD_END_DT [유효기간종료일]
	 *	- field : OP_CL_CD [업무구분코드]
	 *	- field : UNRTN [미반납]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegCmpPoly(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IDataSet reqDs = new DataSet();
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    try {	    	
			// 1. DU lookup
			DNRCmpPolyMgmt dNRCmpPolyMgmt = (DNRCmpPolyMgmt) lookupDataUnit(DNRCmpPolyMgmt.class);
			
			//유효시작일 유효성 체크
            if( Integer.parseInt(DateUtils.getCurrentDate("yyyyMMdd")) >= Integer.parseInt(requestData.getField("DCP_EFF_PRD_STA_DT")) ){
                throw new BizRuntimeException("DMS00069"); //이전날짜는 선택이 불가능합니다.
            };
            
            // 유효시작일 중복 체크
			IDataSet dsRtn = dNRCmpPolyMgmt.dSChkDupFrDt(requestData, onlineCtx);
            if( dsRtn.getIntField("DUP_CNT") > 0){
                /* TODO : 에러코드입력 필요함({유효기간}에  주복 데이터가 있습니다). 현재는 중복데이타가 있습니다로 사용.*/
                throw new BizRuntimeException("DMS00003");
            }
			
			// 2. 손해배상금정책 채번
			String DCP_NO = dNRCmpPolyMgmt.dSCmpPolyNo(requestData, onlineCtx).getField("DCP_NO");
			String USER_NO = requestData.getField("USER_NO");
			
			// 3. 업무 DM호출
			// 손해배상금 등록
			requestData.putField("DCP_NO", DCP_NO);			
			responseData = dNRCmpPolyMgmt.dICmpPoly(requestData, onlineCtx);
			
			//점검항목등록
			IRecordSet rs = requestData.getRecordSet("RS_CMP_POLY_DTL");           
			for(int idx=0; idx<rs.getRecordCount(); ++idx){
				reqDs.initField();
				reqDs.putFieldMap( rs.getRecord(idx) );
				if( StringUtils.isEmpty(reqDs.getField("INDF_AMT")) ){
				    reqDs.putField("INDF_AMT", "0");
				}
				reqDs.putField("DCP_NO", DCP_NO);
				reqDs.putField("USER_NO", USER_NO);
				responseData = dNRCmpPolyMgmt.dICmpPolyDtl(reqDs, onlineCtx);
			}
			
	        //이전정책종료일 변경
            responseData = dNRCmpPolyMgmt.dUPreCmpPoly(requestData, onlineCtx);
            
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
	 *	- record : RS_CMP_POLY_DTL
	 *		- field : DCP_NO [손해배상금정책번호]
	 *		- field : INSP_DTL_ITM_CD [점검세부항목코드]
	 *		- field : INDF_AMT [변상금]
	 *		- field : DCP_ITM_RMK [비고]
	 *		- field : DCP_ITM_USE_YN [사용여부]
	 *	- field : DCP_NO [손해배상금정책번호]
	 *	- field : EQP_MDL_CD [단말기모델코드]
	 *	- field : DCP_EFF_PRD_STA_DT [유효기간시작일]
	 *	- field : DCP_EFF_PRD_END_DT [유효기간종료일]
	 *	- field : OP_CL_CD [업무구분코드]
	 *	- field : UNRTN [미반납]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdCmpPoly(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IDataSet reqDs = new DataSet();
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    try {
			// 1. DU lookup
			DNRCmpPolyMgmt dNRCmpPolyMgmt = (DNRCmpPolyMgmt) lookupDataUnit(DNRCmpPolyMgmt.class);
			
			//유효시작일 유효성 체크
            if( Integer.parseInt(DateUtils.getCurrentDate("yyyyMMdd")) >= Integer.parseInt(requestData.getField("DCP_EFF_PRD_STA_DT")) ){
                throw new BizRuntimeException("DMS00069"); //이전날짜는 선택이 불가능합니다.
            };
            
			// 유효시작일 중복 체크
	        IDataSet dsRtn = dNRCmpPolyMgmt.dSChkDupFrDt2(requestData, onlineCtx);
	        if( dsRtn.getIntField("DUP_CNT") > 0){
	            /* TODO : 에러코드입력 필요함({유효기간}에  중복 데이터가 있습니다). 현재는 중복데이타가 있습니다로 사용.*/
	            throw new BizRuntimeException("DMS00003");
	        }
	            
			// 3. 업무 DM호출
			responseData = dNRCmpPolyMgmt.dUCmpPoly(requestData, onlineCtx);
	        //점검항목등록
	        String USER_NO = requestData.getField("USER_NO");
            IRecordSet rs = requestData.getRecordSet("RS_CMP_POLY_DTL");           
            for(int idx=0; idx<rs.getRecordCount(); ++idx){
                reqDs.initField();
                reqDs.putFieldMap( rs.getRecord(idx) );
                reqDs.putField("USER_NO", USER_NO);
                responseData = dNRCmpPolyMgmt.dUCmpPolyDtl(reqDs, onlineCtx);
            }
            
            //이전정책종료일 변경
            responseData = dNRCmpPolyMgmt.dUPreCmpPoly(requestData, onlineCtx);
            
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
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EPQ_CONS_PEN
	 *		- field : DCP_NO [손해배상정책번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : INSP_CL_CD [구분]
	 *		- field : INSP_ITM_CD [항목]
	 *		- field : INSP_DTL_ITM_CD [점검항목]
	 *		- field : INDF_AMT [금액]
	 *		- field : DCP_ITM_RMK [비고]
	 *		- field : DCP_ITM_USE_YN [사용여부]
	 *		- field : MGMT_ITEM_CD1 [관리코드1]
	 *		- field : MGMT_ITEM_CD2 [관리코드2]
	 * </pre>
	 */
	public IDataSet fInqEqpConsPen(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
		IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
		try {
			// 1. DU lookup
			DNRCmpPolyMgmt dNRCmpPolyMgmt = (DNRCmpPolyMgmt) lookupDataUnit(DNRCmpPolyMgmt.class);

			// 2. LISTDM호출
			responseData = dNRCmpPolyMgmt.dSEqpConsPen(reqDs, onlineCtx);
			
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
	 *	- field : DCP_NO [손해배상금정책번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_CMP_POLY_DTL
	 *		- field : DCP_NO [손해배상금정책번호]
	 *		- field : INSP_CL_CD [구분]
	 *		- field : INSP_ITM_CD [항목]
	 *		- field : INSP_DTL_ITM_CD [세부상목]
	 *		- field : INDF_AMT [손해배상금]
	 *		- field : DCP_ITM_USE_YN [비고]
	 *		- field : DCP_ITM_RMK [사용여부]
	 * </pre>
	 */
	public IDataSet fInqCmpPolyDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
		try {
			// 1. DU lookup
			DNRCmpPolyMgmt dNRCmpPolyMgmt = (DNRCmpPolyMgmt) lookupDataUnit(DNRCmpPolyMgmt.class);

			// 2. LISTDM호출
			responseData = dNRCmpPolyMgmt.dSCmpPolyDtl(requestData, onlineCtx);
			
		} catch ( BizRuntimeException e ) {
			throw e; //시스템 오류가 발생하였습니다.
		}	
	    return responseData;
	}

    /**
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-07-16 14:15:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_CMP_POLY_DTL
	 *		- field : DCP_NO [손해배상금정책번호]
	 *		- field : INSP_DTL_ITM_CD [점검세부항목코드]
	 *		- field : INDF_AMT [변상금]
	 *		- field : DCP_ITM_RMK [비고]
	 *		- field : DCP_ITM_USE_YN [사용여부]
	 *	- field : DCP_NO [손해배상금정책번호]
	 *	- field : EQP_MDL_CD [단말기모델코드]
	 *	- field : DCP_EFF_PRD_STA_DT [유효기간시작일]
	 *	- field : DCP_EFF_PRD_END_DT [유효기간종료일]
	 *	- field : OP_CL_CD [업무구분코드]
	 *	- field : UNRTN [미반납]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fDelCmpPoly(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet reqDs = new DataSet();
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        try {            
            // 1. DU lookup
            DNRCmpPolyMgmt dNRCmpPolyMgmt = (DNRCmpPolyMgmt) lookupDataUnit(DNRCmpPolyMgmt.class);
            
            // 현재일이 유효시작일 이후면 삭제할수 없음
            reqDs.putField("DCP_NO", requestData.getField("DCP_NO"));
            reqDs.putField("OP_CL_CD", requestData.getField("OP_CL_CD"));
            PagenateUtils.setPagenatedParamsToDataSet(reqDs);
            responseData = dNRCmpPolyMgmt.dSCmpPolyLstPaging(reqDs, onlineCtx);
            IRecordSet rs = responseData.getRecordSet("RS_CMP_POLY_LIST");
            
            if( Integer.parseInt(DateUtils.addDay(DateUtils.getCurrentDate("yyyyMMdd"), 2) ) > Integer.parseInt(rs.get(0, "DCP_EFF_PRD_STA_DT")) ){
                throw new BizRuntimeException("DMS00025"); //삭제할 수 없는 데이터 입니다.
            };
            
            // 2. 삭제
            responseData = dNRCmpPolyMgmt.dDCmpPolyDtl(requestData, onlineCtx);            
            responseData = dNRCmpPolyMgmt.dDCmpPoly(requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }   
        return responseData;
    }
  
}
