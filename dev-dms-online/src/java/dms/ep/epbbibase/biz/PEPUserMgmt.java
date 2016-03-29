package dms.ep.epbbibase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [PU]사용자관리</li>
 * <li>설  명 : <pre>사용자관리 PU</pre></li>
 * <li>작성일 : 2015-09-16 14:17:44</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class PEPUserMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PEPUserMgmt(){
		super();
	}

    /**
	 * <pre>사용자조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-09-16 14:17:44
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : DEALCO_CD [거래처코드]
	 *	- field : USER_NO [사용자번호]
	 *	- field : DUTYP_CD [근무지코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_USER_LIST
	 *		- field : USER_NO [사용자ID]
	 *		- field : USER_NM [사용자명]
	 * </pre>
	 */
	public IDataSet pInqUserMgmtLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSBIBase", "fInqUserMgmtLst", requestData, onlineCtx);
            
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }
  
}
