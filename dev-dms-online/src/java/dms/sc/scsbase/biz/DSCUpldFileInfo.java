package dms.sc.scsbase.biz;

import fwk.utils.HpcUtils;
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
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: [DU]업로드파일정보테이블</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-08-06 17:56:08</li>
 * <li>작성자 : admin (admin)</li>
 * </ul>
 *
 * @author admin (admin)
 */
public class DSCUpldFileInfo extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSCUpldFileInfo() {
		super();
	}

	/**
	 * 업로드한 파일정보를 DB에 등록한다
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIFileInfo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		int insertCnt = dbInsert("IFileInfo", requestData.getFieldMap(), onlineCtx);
		if ( insertCnt == 1 ) {
			responseData.putField("isSuccess", true);
		} else {
			responseData.putField("isSuccess", false);
		}

		//  if(insertCnt != 1) {
		//  	throw new BizRuntimeException("SKFS4015", new String[]{HpcUtils.getLangMsg("[review]insert건 수 :["+insertCnt+"]")});
		//  }

		return responseData;
	}

	/**
	 * 업로드한 파일의 정보를 저장하기 위해 채번을 실시한다
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFileSeqNo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		IRecord record = dbSelectSingle("SFileSeqNo", requestData.getFieldMap(), onlineCtx);
		responseData.putFieldMap(record);
		return responseData;
	}

	/**
	 * 등록된 파일정보를 삭제한다
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDFileInfo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		int deleteCnt = dbDelete("DFileInfo", requestData.getFieldMap(), onlineCtx);

		return responseData;
	}

	/**
	 * <pre>파일정보조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-06-22 17:04:00
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFileInfo(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    // 1.쿼리문 호출
	 	IRecordSet rsReturn = dbSelect("SFileInfo", requestData, onlineCtx);
	 	// 2.결과값 셋팅
	 	responseData.putRecordSet("NC_FILE_LIST", rsReturn);
	    return responseData;
	}

}
