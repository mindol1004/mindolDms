package fwk.channel.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import nexcore.framework.core.Constants;
import nexcore.framework.core.file.UploadedFileReference;
import nexcore.framework.core.file.UploadedFileUtils;
import nexcore.framework.online.channel.web.filter.FileUploadFilter;
import nexcore.framework.online.channel.web.helper.MultipartRequestWrapper;

public class HpcFileUploadFilter extends FileUploadFilter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if (MultipartRequestWrapper.isMultipartContent(httpServletRequest)) {
			MultipartRequestWrapper wrapper = null;
			try{
				wrapper = new MultipartRequestWrapper(
																						httpServletRequest, 
																						UploadedFileUtils.getTempDirectory(),
																						UploadedFileUtils.getMaxFileSize(),
																						UploadedFileUtils.getMaxEachFileSize(),
																						UploadedFileUtils.getEncoding()
											);
				
				List<FileItem> files = wrapper.getFiles();
				if(files != null && files.size() > 0){
					String category = wrapper.getParameter(Constants.UPLOAD_CATEGORY);
					List<UploadedFileReference> uploadFiles = UploadedFileUtils.storeFiles(files, category);
					httpServletRequest.setAttribute(Constants.UPLOADED_FILE, uploadFiles);
				}
				
//				httpServletRequest = wrapper;
			}
			catch(Throwable e){
				throw new RuntimeException("File upload processing error.", e);
			}
			finally{
				if(wrapper != null){
					wrapper.release();
				}
			}
		}
		
		chain.doFilter(httpServletRequest, response);
		
	}

}
