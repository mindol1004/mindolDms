package fwk.common;

import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.util.BaseUtils;

/**
 * 확장 유틸리티 클래스
 */
/**
 * @author Administrator
 *
 */
public final class CommonUtils {

	/**
	 * COMMON AREA 관리키
	 */
	public final static String COMMONAREA_KEY = "__CA__";
	
	/**
	 * 채널코드
	 */
	public static enum CHNL_CD           {FWKC, MCI, TEFP, EAII};
	
	/**
	 * 전문구분코드
	 */
	public static enum MESG_DVCD         {Q, R, P};      // 전문구분코드 (Q:요청, R:응답, P:Push)
	
	/**
	 * 동기비동기구분
	 */
	public static enum SYNC_ASYNC        {S, A};               // 동기비동기구분 (S:동기, A:비동기)
	
	/**
	 *Y/N 여부
	 */
	public static enum Y_N        {Y, N};               // Y,N
	/**
	 * 생성자
	 */
	private CommonUtils(){}
	
	/**
	 * CommonArea 조회
	 * 
	 * @param onlineCtx
	 *            온라인 컨텍스트
	 * @return 조회된 CommonArea
	 */
	public static CommonArea getCommonArea(IOnlineContext onlineCtx) {
		return (CommonArea)onlineCtx.getAttribute(COMMONAREA_KEY);
	}
	
	/**
	 * 운영시스템 여부 
	 * @return 운영시스템 여부 
	 */
	public static final boolean isRealSystem(){
		// (R:운영, T:테스트, D:개발, L:로컬)
		return "R".equals(BaseUtils.getRuntimeMode());
	}
	
	/**
	 * 테스트시스템 여부
	 * @return 테스트시스템 여부
	 */
	public static final boolean isTestSystem(){
		// (R:운영, T:테스트, D:개발, L:로컬)
		return "T".equals(BaseUtils.getRuntimeMode());
	}
	
	/**
	 * 개발시스템 여부
	 * @return 개발시스템 여부
	 */
	public static final boolean isDevSystem(){
		// (R:운영, T:테스트, D:개발, L:로컬)
		return "D".equals(BaseUtils.getRuntimeMode());
	}
	
	/**
	 * 로컬시스템 여부
	 * @return 로컬시스템 여부
	 */
	public static final boolean isLocalSystem(){
		// (R:운영, T:테스트, D:개발, L:로컬)
		return "localhost".equals(BaseUtils.getCurrentWasInstanceId()) || "L".equals(BaseUtils.getRuntimeMode());
	}
	
//	/**
//	 * 업로드 파일을 데이타 영역에 복사한다.<b> 데이타 파일이 존재하는 경우에는 덮어쓴다.
//	 * 
//	 * @param uploadFileId
//	 *            업로드 파일 아이디
//	 * @param sysCd
//	 *            시스템 코드(계정계, 정보계)
//	 * @param applCd
//	 *            어플리케이션 코드 (CBM, CUS 등)
//	 * @param fileName
//	 *            저장될 파일 이름
//	 * @throws IOException
//	 */
//	public static void fileCopyUploadToData(String uploadFileId, String sysCd, String applCd, String fileName) throws IOException {
//		fileCopyUploadToData(uploadFileId, sysCd, applCd, fileName, true);
//	}

//	/**
//	 * 업로드 파일을 데이타 영역에 복사한다.
//	 * 
//	 * @param uploadFileId
//	 *            업로드 파일 아이디
//	 * @param sysCd
//	 *            시스템 코드(계정계, 정보계)
//	 * @param applCd
//	 *            어플리케이션 코드 (CBM, CUS 등)
//	 * @param fileName
//	 *            저장될 파일 이름
//	 * @param overwrite
//	 *            덮어쓰기 여부 ('false'인 경우 데이타파일이 존재하면 예외가 발생한다.)
//	 * @throws IOException
//	 */
//	public static void fileCopyUploadToData(String uploadFileId, String sysCd, String applCd, String fileName, boolean overwrite) throws IOException {
//		FileResourceManager.uploadToData(uploadFileId, sysCd, applCd, fileName, overwrite, false);
//	}

//	/**
//	 * 업로드 파일을 데이타 영역에 이동한다.<br>
//	 * 데이타 파일이 존재하는 경우에는 덮어쓴다.
//	 * 
//	 * @param uploadFileId
//	 *            업로드 파일 아이디
//	 * @param sysCd
//	 *            시스템 코드(계정계, 정보계)
//	 * @param applCd
//	 *            어플리케이션 코드 (CBM, CUS 등)
//	 * @param fileName
//	 *            저장될 파일 이름
//	 * @throws IOException
//	 */
//	public static void fileMoveUploadToData(String uploadFileId, String sysCd, String applCd, String fileName) throws IOException {
//		fileMoveUploadToData(uploadFileId, sysCd, applCd, fileName, true);
//	}

//	/**
//	 * 업로드 파일을 데이타 영역에 이동한다.
//	 * 
//	 * @param uploadFileId
//	 *            업로드 파일 아이디
//	 * @param sysCd
//	 *            시스템 코드(계정계, 정보계)
//	 * @param applCd
//	 *            어플리케이션 코드 (CBM, CUS 등)
//	 * @param fileName
//	 *            저장될 파일 이름
//	 * @param overwrite
//	 *            덮어쓰기 여부 ('false'인 경우 데이타파일이 존재하면 예외가 발생한다.)
//	 * @throws IOException
//	 */
//	public static void fileMoveUploadToData(String uploadFileId, String sysCd, String applCd, String fileName, boolean overwrite) throws IOException {
//		FileResourceManager.uploadToData(uploadFileId, sysCd, applCd, fileName, overwrite, true);
//	}

//	/**
//	 * 업로드 파일 전체 경로
//	 * 
//	 * @param uploadFileId 업로드 파일 아이디
//	 * @return 업로드 파일 전체 경로
//	 */
//	public static String getUploadFileFullPath(String uploadFileId) {
//		return FileResourceManager.lookupUploadFileFullName(uploadFileId);
//	}
//
//	/**
//	 * 업로드 파일 조회
//	 * 
//	 * @param uploadFileId 업로드 파일 아이디
//	 * @return 업로드 파일 객체
//	 */
//	public static File getUploadFile(String uploadFileId) {
//		return FileResourceManager.lookupUploadFile(uploadFileId);
//	}
//
//	
//	/**
//	 * 업로드 파일 루트 경로
//	 * 
//	 * @return 업로드 파일 루트 경로
//	 */
//	public static String getUploadFileRootPath() {
//		return FileResourceManager.getFileUploadRootPath();
//	}

//	/**
//	 * 데이타 파일 루트 경로
//	 * 
//	 * @return 데이타 파일 루트 경로
//	 */
//	public static String getDataFileRootPath() {
//		return FileResourceManager.getDataFileRootPath();
//	}
	
//	/**
//	 * 데이타 파일 전체 경로
//	 * @param sysCd
//	 *            시스템 코드(계정계, 정보계)
//	 * @param applCd
//	 *            어플리케이션 코드 (CBM, CUS 등)
//	 * @param fileName
//	 *            파일 이름
//	 * @return 데이타 파일 전체 경로
//	 */
//	public static String getDataFileFullPath(String sysCd, String applCd, String fileName){
//		return FileResourceManager.makeDataFileFullName(sysCd, applCd, fileName);
//	}

//	/**
//	 * 데이타 파일을 업로드 파일로 복사
//	 * @param dataFileFullPath 데이타 파일 전체 경로
//	 * @param applCd
//	 *            어플리케이션 코드 (CBM, CUS 등)
//	 * @param onlineCtx
//	 * @return 업로드 파일 아이디
//	 * @throws IOException
//	 */
//	public static String fileCopyDataToUpload(String dataFileFullPath,  String applCd, IOnlineContext onlineCtx) throws IOException {
//		return copyDataToUpload(dataFileFullPath, applCd, onlineCtx, true);
//	}
//
//	/**
//	 * 데이타 파일을 업로드 파일로 복사
//	 * @param dataFileFullPath 데이타 파일 전체 경로
//	 * @param applCd
//	 *            어플리케이션 코드 (CBM, CUS 등)
//	 * @param onlineCtx
//	 * @param overwrite
//	 *            덮어쓰기 여부 ('false'인 경우 데이타파일이 존재하면 예외가 발생한다.)
//	 * @return 업로드 파일 아이디
//	 * @throws IOException
//	 */
//	public static String fileCopyDataToUpload(String dataFileFullPath, String applCd, IOnlineContext onlineCtx, boolean overwrite) throws IOException {
//		CommonArea ca = CommonAreaUtils.getCommonArea(onlineCtx);
//		return FileResourceManager.dataToUpload(dataFileFullPath, ca.getCommonAreaHeader().getDvlEnvInfDstcd(), ca.getCommonAreaHeader().getBnkCd(), applCd, overwrite, false);
//	}
//
//	/**
//	 * 데이타 파일을 업로드 파일로 이동
//	 * @param dataFileFullPath 데이타 파일 전체 경로
//	 * @param applCd
//	 *            어플리케이션 코드 (CBM, CUS 등)
//	 * @param onlineCtx
//	 * @return 업로드 파일 아이디
//	 * @throws IOException
//	 */
//	public static String fileMoveDataToUpload(String dataFileFullPath,  String applCd, IOnlineContext onlineCtx) throws IOException {
//		return moveDataToUpload(dataFileFullPath, applCd, onlineCtx, true);
//	}
//
//	/**
//	 * 데이타 파일을 업로드 파일로 이동
//	 * @param dataFileFullPath 데이타 파일 전체 경로
//	 * @param applCd
//	 *            어플리케이션 코드 (CBM, CUS 등)
//	 * @param onlineCtx
//	 * @param overwrite
//	 *            덮어쓰기 여부 ('false'인 경우 데이타파일이 존재하면 예외가 발생한다.)
//	 * @return 업로드 파일 아이디
//	 * @throws IOException
//	 */
//	public static String fileMoveDataToUpload(String dataFileFullPath,  String applCd, IOnlineContext onlineCtx, boolean overwrite) throws IOException {
//		CommonArea ca = CommonAreaUtils.getCommonArea(onlineCtx);
//		return FileResourceManager.dataToUpload(dataFileFullPath, ca.getCommonAreaHeader().getDvlEnvInfDstcd(), ca.getCommonAreaHeader().getBnkCd(), applCd, overwrite, true);
//	}
	
}
