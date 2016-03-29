package dms.fw.fwsbase.biz;

import java.util.Map;

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
 * <li>업무 그룹명 : HPC/프레임워크</li>
 * <li>단위업무명: [DU]에러로그</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-06-30 17:57:29</li>
 * <li>작성자 : admin (admin)</li>
 * </ul>
 *
 * @author admin (admin)
 */
public class DXYZTErLog extends fwk.base.DataUnit  {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DXYZTErLog(){
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
	public IDataSet dIErLog(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    int row = dbInsert("IErLog", requestData.getFieldMap(),  onlineCtx);
	    responseData.putField("isSuccess", row > 0);
	    return responseData;
	}
  
}
