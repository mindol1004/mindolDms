package dms.ep.epbbibase.biz;

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
 * <li>단위업무명: [PU]업무목표관리</li>
 * <li>설  명 : <pre>업무목표관리</pre></li>
 * <li>작성일 : 2015-12-11 17:36:14</li>
 * <li>작성자 : 박민정 (dangnagu2)</li>
 * </ul>
 *
 * @author 박민정 (dangnagu2)
 */
public class PEPOpTargMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PEPOpTargMgmt(){
		super();
	}

    /**
	 * <pre>업무목표조회</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-12-11 17:36:14
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
	public IDataSet pInqOpTargList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSBIBase", "fInqOpTargList", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        // 2. 결과값 리턴
        responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
    
        return responseData;
    }

    /**
	 * <pre>업무목표등록</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-12-11 17:36:14
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
	public IDataSet pSaveOpTargIn(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);

        try {
            // 1. FM 호출
              responseData = callSharedBizComponentByDirect("ep.EPSBIBase", "fRegOpTargIn", requestData, onlineCtx);

        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        // 3. 결과값 리턴
        responseData.setOkResultMessage("DMS00000", null); // 정상처리회되었습니다.
        
        return responseData;
    }

    /**
	 * <pre>업무목표상태저장</pre>
	 *
	 * @author 박민정 (dangnagu2)
	 * @since 2015-12-11 17:36:14
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
	public IDataSet pInOpTargStatSave(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);

        try {
            // 1. FM 호출
              responseData = callSharedBizComponentByDirect("ep.EPSBIBase", "fUpdOpTargSave", requestData, onlineCtx);

        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        // 3. 결과값 리턴
        responseData.setOkResultMessage("DMS00000", null); // 정상처리회되었습니다.
        
        return responseData;
    }
  
}
