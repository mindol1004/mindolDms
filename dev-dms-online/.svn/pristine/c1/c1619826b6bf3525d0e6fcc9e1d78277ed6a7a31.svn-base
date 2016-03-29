package dms.sc.scsbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.constants.DmsConstants;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: FSCUpFileInfMgmt</li>
 * <li>설  명 : 업로드 파일에 대한 정보를 관리하기 위한 클래스</li>
 * <li>작성일 : 2014-08-06 17:07:15</li>
 * <li>작성자 : admin (admin)</li>
 * </ul>
 *
 * @author admin (admin)
 */
public class FSCUpFileInfMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCUpFileInfMgmt() {
		super();
	}

	/**
	 * <pre>업로드된 파일정보를 DB에 등록한다</pre>
	 *
	 * @author admin (admin)
	 * @since 2015-06-22 17:04:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : NC_FILE_LIST
	 *		- field : UPLD_FILE_NO [업로드파일번호]
	 *		- field : FILE_ID [파일ID]
	 *		- field : FILE_NM [파일명]
	 *		- field : FILE_TYP_VAL [파일유형값]
	 *		- field : FILE_SIZE [파일크기]
	 *		- field : FS_REG_USER_ID [최초등록자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegUpFileInfo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet reqCloneDs = (IDataSet) requestData.clone();
		IDataSet responseData = new DataSet();
		IDataSet regFileInfoDs = null;
		IRecordSet upFileListRs = null;
		DSCUpldFileInfo dSCUpldFileInfo = (DSCUpldFileInfo) lookupDataUnit(DSCUpldFileInfo.class);
		try {
			//채번시퀀스는 반드시 존재한다
			long upldFileNo = dSCUpldFileInfo.dSFileSeqNo(reqCloneDs, onlineCtx).getLongField("UPLD_FILE_NO");

			upFileListRs = reqCloneDs.getRecordSet(DmsConstants.UPLOAD_FILE_LIST);
			boolean isSuccess = true;
			if ( upFileListRs != null ) {//업로드한 파일이 여러개인경우
				for ( int i = 0 ; i < upFileListRs.getRecordCount() ; i++ ) {
					regFileInfoDs = new DataSet();
					regFileInfoDs.putFieldMap(reqCloneDs.getFieldMap());
					regFileInfoDs.putFieldMap(upFileListRs.getRecord(i));
					regFileInfoDs.putField("UPLD_FILE_NO", upldFileNo);//채번번호 설정
					responseData = dSCUpldFileInfo.dIFileInfo(regFileInfoDs, onlineCtx);
					isSuccess = (Boolean) responseData.getObjectField("isSuccess");
					if ( !isSuccess ) {
						throw new BizRuntimeException("DMS00009", new String[] {});    // INSERT 시 오류 발생. 잠시 후 다시 이용해 주세요.
					}
				}
			}
			//	    	else {//업로드한 파일이 한 개인 경우
			//	    		reqCloneDs.putField("UPLD_FILE_NO", upldFileNo);//채번번호 설정
			//	    		dSCUpldFileInfo.dIFileInfo(reqCloneDs, onlineCtx);
			//	    	}
			responseData.putField("UPLD_FILE_NO", upldFileNo);//채번번호를 return해준다.
		} catch ( BizRuntimeException e ) {
			throw e;
			//throw new BizRuntimeException("SKFE5007", new String[]{e.getMessage()},e);
		}
		return responseData;
	}

	/**
	 * <pre>업로드된 파일정보를 삭제한다.</pre>
	 *
	 * @author admin (admin)
	 * @since 2015-06-22 17:04:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : UPLD_FILE_NO [업로드파일번호]
	 *	- field : FILE_ID [파일ID]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fDelUpFileInfo(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		DSCUpldFileInfo dSCUpldFileInfo = (DSCUpldFileInfo) lookupDataUnit(DSCUpldFileInfo.class);
		try {
			dSCUpldFileInfo.dDFileInfo(requestData, onlineCtx);

		} catch ( BizRuntimeException e ) {
			throw e;
			//throw new BizRuntimeException("SKFE5007", new String[]{""}, e);
		}
		return responseData;
	}

	/**
	 * <pre>업로드파일정보조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-06-22 17:04:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : UPLD_FILE_NO [업로드파일번호]
	 *	- field : FILE_ID [파일ID]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : NC_FILE_LIST
	 *		- field : UPLD_FILE_NO [업로드파일번호]
	 *		- field : FILE_ID [파일ID]
	 *		- field : FILE_NM [파일명]
	 *		- field : FILE_TYP_VAL [파일유형값]
	 *		- field : FILE_SIZE [파일크기]
	 *		- field : FS_REG_USER_ID [최초등록자ID]
	 *		- field : FS_REG_DTM [최초등록일시]
	 *		- field : LS_CHG_USER_ID [최종변경자ID]
	 *		- field : LS_CHG_DTM [최종변경일시]
	 * </pre>
	 */
	public IDataSet fInqUpFileInfo(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    try {
			// 1. DU lookup
	    	DSCUpldFileInfo dSCUpldFileInfo = (DSCUpldFileInfo) lookupDataUnit(DSCUpldFileInfo.class);
			// 3. LISTDM호출
			responseData = dSCUpldFileInfo.dSFileInfo(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
	    return responseData;
	}

}
