package fwk.file.internal;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.time.FastDateFormat;

import fwk.constants.DmsConstants;
import fwk.utils.HpcUtils;
import nexcore.framework.core.file.UploadedFileReference;
import nexcore.framework.core.file.internal.DefaultUploadedFileManager;
import nexcore.framework.core.util.FileUtils;
import nexcore.framework.core.util.StringUtils;

public class HpcUploadedFileManager extends DefaultUploadedFileManager {

    final String INTER_PATH_STR = "inter";//웹으로 업로드 되는 경우에는 inter폴더 하위에 생성되도록 함.
    /**
     * 서버에서 생성한 파일을 WEB을 통해 다운로드 할수 있도록 파일업로드하는 방식과 동일하게 
     * 파일Path및 파일ID를 구성하고 해당되는 정보를 return해줄 수 있도록 구성함. 
     *  
     * @param file 임시폴더에 생성된 파일객체
     */
    public Map<String, String> storeFilesForDownload(File tempFile) {
        Date currentDate = new Date();
        Map<String, String> fileInfoMap = new HashMap<String, String>();
        String dailyUploadFilePath = getDailyUploadFilePath("", currentDate);
        String dailyFilePrefix = getDailyUploadFilePrefix("", currentDate);
        String fileFullName = makeFileFullName(dailyUploadFilePath, dailyFilePrefix);
        String oriFileName = tempFile.getName();
        File file = createFile(fileFullName);
        FileUtils.copyFile(tempFile, file);
        fileInfoMap.put(DmsConstants.NC_UPLOADED_FILE_ID, file.getName());//파일의 ID
        fileInfoMap.put(DmsConstants.NC_FIELD_NAME, "svrFile0");//가상의 필드명. 큰 의미 없음.
        fileInfoMap.put(DmsConstants.NC_UPLOADED_FILE_NAME, tempFile.getName());//파일의 실제명
        fileInfoMap.put(DmsConstants.NC_UPLOADED_FILE_FULL_NAME, file.getAbsolutePath());//파일이 존재하는 full path
        if((oriFileName.toLowerCase().indexOf(".jpg") != -1) || (oriFileName.toLowerCase().indexOf(".gif") != -1)
            || (oriFileName.toLowerCase().indexOf(".bmp") != -1) || (oriFileName.toLowerCase().indexOf(".png") != -1)) {
            fileInfoMap.put(DmsConstants.CONTENT_TYPE, "IMG");//이미지 여부
        } else {
            fileInfoMap.put(DmsConstants.CONTENT_TYPE, "DAT");//이미지 여부
        }
        
        fileInfoMap.put(DmsConstants.SIZE, file.length()+"");//사이즈
        fileInfoMap.put(DmsConstants.STATUS, UploadedFileReference.STATUS_SUCCESS);//성공여부
        return fileInfoMap;
    }
    
    /**
     * 일별로 관리되는 영구 저장소
     * 
     * @param category
     *            어플리케이션코드
     * @param date
     *            날짜정보
     * @return 일별로 관리되는 영구 저장소
     */
    protected String getDailyUploadFilePath(String category, Date date) {
        
        final String  DAILY_STORED_PATH_STR = "yyyyMMdd";
        FastDateFormat dailyStoredPathFormat = FastDateFormat.getInstance(DAILY_STORED_PATH_STR);
        StringBuilder sb = new StringBuilder();
        String rootDir = getRootDirectory();
        sb.append(rootDir);
        if (!rootDir.endsWith("/") && !rootDir.endsWith("\\")) {
            sb.append(SystemUtils.FILE_SEPARATOR);
        }
        if(category.trim().length() > 0){
            sb.append(StringUtils.replaceAll(category, "-", SystemUtils.FILE_SEPARATOR));
            sb.append(SystemUtils.FILE_SEPARATOR);
        }
        sb.append(INTER_PATH_STR);
        sb.append(SystemUtils.FILE_SEPARATOR);
        sb.append(dailyStoredPathFormat.format(date));
        return sb.toString();
    }
    
    /**
     * 업로드 파일 전체 경로
     * 
     * @param uploadedFileId
     *            업로드 파일 아이디
     * @return 업로드 파일 전체 경로
     */
    protected String getFileFullName(String uploadedFileId) {
        final String         TEMP_FILE_PREXFIX = "TMP";
        //runtimeMode_category_yyyyMMdd_이름
        List<String> tokens = StringUtils.stringToList(uploadedFileId, '_');
        //////////////////////////////////
        if (tokens == null || tokens.size() < 4) {
            throw new RuntimeException("Invalid upload file id. [" + uploadedFileId + "]");
        }

        boolean isTempFile = false;
        String runtimeMode = tokens.remove(0);
        if(TEMP_FILE_PREXFIX.equals(runtimeMode)){
            isTempFile = true;
            runtimeMode = tokens.remove(0);
        }
        String category = tokens.remove(0);
        String dailyCode = tokens.remove(0);
        //      String fileName = tokens.remove(0);

        String rootDir = isTempFile ? getTempDirectory() : getRootDirectory();
        if (!rootDir.endsWith("/") && !rootDir.endsWith("\\")) {
            rootDir += SystemUtils.FILE_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(rootDir);
        if(category.trim().length() > 0){
            sb.append(StringUtils.replaceAll(category, "-", SystemUtils.FILE_SEPARATOR));
            sb.append(SystemUtils.FILE_SEPARATOR);
        }

        sb.append(INTER_PATH_STR);
        sb.append(SystemUtils.FILE_SEPARATOR);
        sb.append(dailyCode);
        sb.append(SystemUtils.FILE_SEPARATOR);
        sb.append(uploadedFileId);
        
        return sb.toString();
    }
}
