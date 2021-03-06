package dms.nr.nrseabase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.utils.PagenateUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]단말기감정내역관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-11 17:04:06</li>
 * <li>작성자 : 김혁범 (kezmain1)</li>
 * </ul>
 *
 * @author 김혁범 (kezmain1)
 */
public class FNREqpJdgDTlInfoMgnt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNREqpJdgDTlInfoMgnt(){
		super();
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-11 17:04:47
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqReqpJdgDtlInfoLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
		  IDataSet dsCnt = new DataSet();
		  IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		  IRecordSet usrListRs = null;
		  int intTotalCnt = 0;
		  
		  try {
				// 1. DU lookup
			  DNREqpJdgDTlInfoMgnt dNREqpJdgDTlInfoMgnt = (DNREqpJdgDTlInfoMgnt) lookupDataUnit(DNREqpJdgDTlInfoMgnt.class);
				
				// 2. TOTAL COUNT DM호출
				dsCnt = dNREqpJdgDTlInfoMgnt.dSEqpJdgDTlInfoTotCnt(requestData, onlineCtx);
				intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
				PagenateUtils.setPagenatedParamsToDataSet(reqDs);

				// 3. LISTDM호출
				responseData = dNREqpJdgDTlInfoMgnt.dSEqpJdgDTlInfoLstPaging(reqDs, onlineCtx);
				usrListRs = responseData.getRecordSet("RS_JDG_DTL_INFO_LIST");
				PagenateUtils.setPagenatedParamToRecordSet(usrListRs, reqDs, intTotalCnt);
				
				
			} catch ( BizRuntimeException e ) {
				throw e; //시스템 오류가 발생하였습니다.
			}
	
	    return responseData;
	}
  
}
