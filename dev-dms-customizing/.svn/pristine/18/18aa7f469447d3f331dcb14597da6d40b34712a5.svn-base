package fwk.channel.command;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nexcore.framework.core.Constants;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.exception.BaseException;
import nexcore.framework.core.exception.BaseRuntimeException;
import nexcore.framework.core.exception.FwkRuntimeException;
import nexcore.framework.core.file.UploadedFileUtils;
import nexcore.framework.core.log.LogManager;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.core.util.StringUtils;
import nexcore.framework.online.channel.core.ICommandViewMap;
import nexcore.framework.online.channel.core.IRequestContext;
import nexcore.framework.online.channel.core.IResponseContext;
import nexcore.framework.online.channel.core.command.StdCommand;
import nexcore.framework.online.channel.util.WebUtils;

import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.Log;

/**
 * 파일ID 를 가지고 파일 다운로드 처리를 하는 커맨드
 * 
 * 파일ID 는 파일 전체경로를 포함하며 디렉토리 구분은 "_" 로 된다.
 * ex) aaa/bbb/test.dat >>>  aaa_bbb_test.dat
 * 
 */
public class FileDownloadCommand extends StdCommand {

	private Log logger = LogManager.getFwkLog();
	
	public IResponseContext execute(IRequestContext requestCtx, ICommandViewMap cmdViewMap) throws BaseException, BaseRuntimeException {
        HttpServletRequest request = (HttpServletRequest)requestCtx.getReadProtocol();
        HttpServletResponse response = (HttpServletResponse)requestCtx.getWriteProtocol();
        
        // 요청 데이타
        IDataSet requestData = (IDataSet) requestCtx.getBizData();

    	// 1.거래 ID 에 따른 비즈니스 서비스를 호출한다.
		IDataSet responseData = (IDataSet)invoke(requestCtx.getOnlineContext(), requestCtx.getBizData());

    	// 성공
    	if(responseData != null 
    			&& (responseData.getResultMessage() == null || responseData.getResultMessage().getStatus() != IResultMessage.FAIL)){
    		try {
    			// 2.실제 파일다운로드 처리를 실행한다. 
    			doActionForFiledownload(request, response);
    			return getResponseContext(Constants.SUCCESS_VIEW_ID, requestCtx, responseData, cmdViewMap);
    			
    		} catch (Exception ex) {
    			if (logger.isErrorEnabled()) {
    	        	logger.error("### Custom filedownload ### Exception occurred while downloading file.", ex);
    	        }
                request.setAttribute("nexcore.bizlogic.exception", ex);  
    			return getResponseContext(Constants.FAIL_VIEW_ID, requestCtx, responseData, cmdViewMap);
    		}
    	}
    	// 실패
    	else {
    		return getResponseContext(requestCtx, responseData, cmdViewMap);
    	}
	}
	
	private void doActionForFiledownload(HttpServletRequest request, HttpServletResponse response) {
		// 파일정보에 대한 키값을 가져온다.
		String fileId = request.getParameter("file_id");
		
		// fileId 인자값은 file path + "_" + file name 으로 전달된다고 간주함
		List<String> tokens = StringUtils.stringToList(fileId, '/');
		if (tokens == null || tokens.size() < 2) {
			throw new RuntimeException("Invalid upload file id. [" + fileId + "]");
		}
		
		// 파일 루트경로 설정 (환경설정 파일에 정의함)
		String rootDir = BaseUtils.getConfiguration("file.ukey.data.folder."+BaseUtils.getRuntimeMode());
		String filePath = "";
		String fileName = "";
		
		for (int i=0; i<tokens.size(); i++) {
			if (i == tokens.size()-1) {
				fileName = tokens.get(i);
			} else {
				filePath += tokens.get(i) + SystemUtils.FILE_SEPARATOR;
			}
		}
		
		// 파일정보 키값인 파일ID 에 대한 전체 경로를 나타낸다.
		String file = rootDir + SystemUtils.FILE_SEPARATOR + filePath + fileName;
		
		InputStream is = null;
		try {
			// 파일다운로드 초기화 및 실행
			is = new BufferedInputStream(new FileInputStream(new File(file)));
			WebUtils.presetForDownload(request, response, fileName);
			Streams.copy(is, response.getOutputStream(), false, new byte[UploadedFileUtils.getDownloadBufferSize()]);
		} catch (Exception ex) {
	        throw new FwkRuntimeException("SKFS5013", new String[] {fileId}, ex);
	    }
		finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
				  //2015.10.13 jihooyim code inspector 점검 수정 (02. 오류 상황 대응 부재)
				    if (logger.isErrorEnabled())  logger.error("conn.close error");
				}
			}
		}
	}
}
