package dms.sc.scbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.RecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;
import fwk.common.CommonArea;
import fwk.constants.DmsConstants;

/**
 * <ul>
 * <li>업무 그룹명 : dms/시스템공통</li>
 * <li>단위업무명: [PU]공지사항관리</li>
 * <li>설  명 : <pre>공지사항관리</pre></li>
 * <li>작성일 : 2015-06-29 10:05:10</li>
 * <li>작성자 : 개발자 (developer)</li>
 * </ul>
 *
 * @author 개발자 (developer)
 */
public class PSCBrdMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PSCBrdMgmt(){
		super();
	}

	/**
	 * <pre>일반게시판 리스트 조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-02 17:44:46
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : BLTN_BRD_ID [게시ID]
	 *	- field : BLTN_BRD_TYPE [게시유형]
	 *	- field : TITL [제목]
	 *	- field : CTT [내용]
	 *	- field : FS_REG_USER_ID [작성자ID]
	 *	- field : FS_REG_USER_NM [작성자명]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_BRD_LIST
	 *		- field : BLTN_BRD_ID [게시판ID]
	 *		- field : BLTN_BRD_NUM [게시판번호]
	 *		- field : BLTN_BRD_TYPE [게시유형]
	 *		- field : TITL [제목]
	 *		- field : CTT [내용]
	 *		- field : BLTN_BRD_LVL [게시판레벨]
	 *		- field : BRWS_CNT [조회수]
	 *		- field : ORG_BLTN_BRD_NUM [원게시판번호]
	 *		- field : SUP_BLTN_BRD_NUM [상위게시판번호]
	 *		- field : UPLD_FILE_NO [업로드파일번호]
	 *		- field : USE_YN [사용여부]
	 *		- field : LVL_SORT_SEQ [레벨정렬순서]
	 *		- field : FS_REG_USER_ID [최초등록자ID]
	 *		- field : FS_REG_DTM [최종등록일시]
	 *		- field : LS_CHG_USER_ID [최종수정자ID]
	 *		- field : LS_CHG_DTM [최종수정일시]
	 *		- field : FS_REG_USER_NM [최초등록자명]
	 * </pre>
	 */
	public IDataSet pInqBrdLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCBrdMgmt fSCBrdMgmt = (FSCBrdMgmt) lookupFunctionUnit(FSCBrdMgmt.class);
			// 2. FM 호출
			responseData = fSCBrdMgmt.fInqBrdLst(requestData, onlineCtx);
			// 3. 결과값 리턴
			responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

	/**
	 * <pre>일반게시판 입력/수정</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-02 17:44:46
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_BRD
	 *		- field : BLTN_BRD_ID [게시판ID]
	 *		- field : BLTN_BRD_NUM [게시판번호]
	 *		- field : BLTN_BRD_TYPE [게시유형]
	 *		- field : TITL [제목]
	 *		- field : CTT [내용]
	 *		- field : BLTN_BRD_LVL [게시판레벨]
	 *		- field : BRWS_CNT [조회수]
	 *		- field : ORG_BLTN_BRD_NUM [원게시판번호]
	 *		- field : SUP_BLTN_BRD_NUM [상위게시판번호]
	 *		- field : UPLD_FILE_NO [업로드파일번호]
	 *		- field : USE_YN [사용여부]
	 *		- field : LVL_SORT_SEQ [레벨정렬순서]
	 *		- field : FS_REG_USER_ID [최초등록자ID]
	 *		- field : FS_REG_DTM [최종등록일시]
	 *		- field : LS_CHG_USER_ID [최종수정자ID]
	 *		- field : LS_CHG_DTM [최종수정일시]
	 *		- field : FS_REG_USER_NM [최초등록자명]
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
	public IDataSet pSaveBrd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);
		IRecordSet upFileListRs = null;
		try {
			// 1. FU lookup
			FSCBrdMgmt fSCBrdMgmt = (FSCBrdMgmt) lookupFunctionUnit(FSCBrdMgmt.class);
			// 2. 입력 RS설정
			requestData.putFieldMap(requestData.getRecordSet("RS_BRD").getRecordMap(0));
			requestData.putField("USER_NO", ca.getUserNo());
			
			//파일업로드 DB삭제 FM호출
			if(!StringUtils.isEmpty(requestData.getField("UPLD_FILE_NO"))) {
				callSharedBizComponentByDirect("sc.SCSBase", "fDelUpFileInfo", requestData, onlineCtx);
			}
			
			upFileListRs = requestData.getRecordSet(DmsConstants.UPLOAD_FILE_LIST);
			if(upFileListRs.getRecordCount() == 0) {
			    requestData.putField("UPLD_FILE_NO", "-1");
			} else {
    			//파일업로드 DB등록 FM호출
    			IDataSet fileInfo = callSharedBizComponentByDirect("sc.SCSBase", "fRegUpFileInfo", requestData, onlineCtx);
    			requestData.putField("UPLD_FILE_NO", fileInfo.getField("UPLD_FILE_NO"));
			}
		
			if ( "I".equals(ca.getTrnPtrnDvcd()) ) { // INSERT
				fSCBrdMgmt.fRegBrd(requestData, onlineCtx);
			} else if ( "U".equals(ca.getTrnPtrnDvcd()) ) { // UPDATE
				fSCBrdMgmt.fUpdBrd(requestData, onlineCtx);
			}
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 4. 결과값 리턴
		responseData.setOkResultMessage("DMS00000", null); // 정상 처리되었습니다.
		return responseData;
	}

	/**
	 * <pre>일반게시판 조회수 증가</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-02 17:44:46
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_BRD
	 *		- field : BLTN_BRD_ID [게시판ID]
	 *		- field : BLTN_BRD_NUM [게시판번호]
	 *		- field : UPLD_FILE_NO [업로드파일번호]
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
	public IDataSet pSaveBrdIncreBrwsCnt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCBrdMgmt fSCBrdMgmt = (FSCBrdMgmt) lookupFunctionUnit(FSCBrdMgmt.class);
			// 2. 입력 RS설정
			requestData.putFieldMap(requestData.getRecordSet("RS_BRD").getRecordMap(0));
			fSCBrdMgmt.fUpdBrdIncreBrwsCnt(requestData, onlineCtx);
			// 3. 업로드파일 조회
			responseData = callSharedBizComponentByDirect("sc.SCSBase", "fInqUpFileInfo", requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		return responseData;
	}
}
