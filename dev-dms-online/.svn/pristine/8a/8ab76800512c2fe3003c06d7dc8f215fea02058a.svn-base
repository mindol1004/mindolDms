package dms.ep.epsbibase.biz;

import java.util.Map;

import fwk.common.CommonArea;
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
 * <li>단위업무명: [FU]업무목표관리</li>
 * <li>설  명 : <pre>업무목표관리</pre></li>
 * <li>작성일 : 2015-12-11 17:37:05</li>
 * <li>작성자 : 박민정 (dangnagu2)</li>
 * </ul>
 *
 * @author 박민정 (dangnagu2)
 */
public class FEPOpTargMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FEPOpTargMgmt(){
		super();
	}

    /**
	 * <pre>업무목표조회</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-12-11 17:37:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : OP_CL_CD [업무구분]
	 *	- field : TARG_CL_CD [목표구분]
	 *	- field : FR_YR [시작년도]
	 *	- field : TO_YR [종료년도]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_OP_TARG_LIST
	 *		- field : CHECKED [CHECKED]
	 *		- field : OP_CL_CD [업무구분코드]
	 *		- field : TARG_CL_CD [목표구분코드]
	 *		- field : TARG_YY [목표기준년]
	 *		- field : MTH_1 [1월]
	 *		- field : MTH_2 [2월]
	 *		- field : MTH_3 [3월]
	 *		- field : MTH_4 [4월]
	 *		- field : MTH_5 [5월]
	 *		- field : MTH_6 [6월]
	 *		- field : MTH_7 [7월]
	 *		- field : MTH_8 [8월]
	 *		- field : MTH_9 [9월]
	 *		- field : MTH_10 [10월]
	 *		- field : MTH_11 [11월]
	 *		- field : MTH_12 [12월]
	 * </pre>
	 */
	public IDataSet fInqOpTargList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. DU lookup
            DEPOpTargMgmt dEPOpTargMgmt = (DEPOpTargMgmt)lookupDataUnit(DEPOpTargMgmt.class);

            // 2. LISTDM호출
            responseData = dEPOpTargMgmt.dSOpTargLst(requestData, onlineCtx);                   
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }

    /**
	 * <pre>업무목표등록</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-12-11 17:37:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_OP_TARG_IN_LIST
	 *		- field : OP_CL_CD [업무구분코드]
	 *		- field : TARG_CL_CD [목표구분코드]
	 *		- field : TARG_STRD_YM [목표기준년월]
	 *		- field : TARG_CNT [목표건수]
	 *		- field : DEL_YN [삭제여부]
	 *		- field : FS_REG_USER_ID [최초등록사용자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경사용자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegOpTargIn(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IDataSet dsRetTemp = new DataSet();
        
        try {
            CommonArea ca = getCommonArea(onlineCtx);
            IRecordSet reqSet = requestData.getRecordSet("RS_OP_TARG_IN_LIST");
            // 1. DU lookup
             DEPOpTargMgmt dEPOpTargMgmt = (DEPOpTargMgmt) lookupDataUnit(DEPOpTargMgmt.class);
             for(int i = 0; i < reqSet.getRecordCount(); i++){
                 requestData.putFieldMap(reqSet.getRecordMap(i));
                //2. Validation DM호출
                 dsRetTemp = new DataSet();
                 /*dsRetTemp = dEPOpTargMgmt.dSOpTargChk(requestData,onlineCtx);
                 if(Integer.parseInt(dsRetTemp.getField("ROW_CNT")) > 0){
                     throw new BizRuntimeException("DMS00003"); //중복입력 된 데이터가 존재합니다.
                 }*/
                 requestData.putField("FS_REG_USER_ID", ca.getUserNo());
                 requestData.putField("LS_CHG_USER_ID", ca.getUserNo());
                 //3.등록
                 dsRetTemp = dEPOpTargMgmt.dIOpTargReg(requestData, onlineCtx); 
             }    
   
        } catch ( BizRuntimeException e ) {
            throw e;
        } 
        
        return responseData;
    }

    /**
	 * <pre>업무목표수정</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-12-11 17:37:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_OP_TARG_IN_LIST
	 *		- field : OP_CL_CD [업무구분코드]
	 *		- field : TARG_CL_CD [목표구분코드]
	 *		- field : TARG_STRD_YM [목표기준년월]
	 *		- field : TARG_CNT [목표건수]
	 *		- field : DEL_YN [삭제여부]
	 *		- field : FS_REG_USER_ID [최초등록사용자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경사용자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdOpTargSave(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        IDataSet dsRetTemp = new DataSet();
        
        try {
            CommonArea ca = getCommonArea(onlineCtx);
            IRecordSet reqSet = requestData.getRecordSet("RS_OP_TARG_IN_LIST");
            // DU lookup
             DEPOpTargMgmt dEPOpTargMgmt = (DEPOpTargMgmt) lookupDataUnit(DEPOpTargMgmt.class);
             for(int i = 0; i < reqSet.getRecordCount(); i++){
                 requestData.putFieldMap(reqSet.getRecordMap(i));
                 dsRetTemp = new DataSet();
                 requestData.putField("FS_REG_USER_ID", ca.getUserNo());
                 requestData.putField("LS_CHG_USER_ID", ca.getUserNo());
                 //수정
                 dsRetTemp = dEPOpTargMgmt.dUpdOpTargUpd(requestData, onlineCtx); 
             } 
   
        } catch ( BizRuntimeException e ) {
            throw e;
        } 
        
        return responseData;
    }
  
}
