package dms.fw.fwsbase.biz;

import fwk.common.internal.CommonAreaHelper;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;


/**
 * <ul>
 * <li>업무 그룹명 : HPC/프레임워크</li>
 * <li>단위업무명: [FU]CommonArea관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-06-11 18:25:47</li>
 * <li>작성자 : admin (admin)</li>
 * </ul>
 *
 * @author admin (admin)
 */
public class FFWKCMgmtCommonArea extends fwk.base.FunctionUnit  {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FFWKCMgmtCommonArea(){
		super();
	}

	/**
	 *
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fCommonAreaInit(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    // CommonArea의 초기값을 설정한다.
    	// CommonArea가 존재하지 않는 경우에는 생성한후 초기값을 설정한다.
    	CommonAreaHelper.createInit(onlineCtx);
	
	    return responseData;
	}
  
}
