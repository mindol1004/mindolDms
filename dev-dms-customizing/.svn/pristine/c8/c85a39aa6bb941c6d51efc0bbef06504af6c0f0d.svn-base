package fwk.channel.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nexcore.framework.core.Constants;
import nexcore.framework.core.log.LogManager;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.core.util.StringUtils;

import org.apache.commons.logging.Log;

import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import com.tobesoft.xplatform.tx.HttpPlatformResponse;
import com.tobesoft.xplatform.tx.PlatformType;

import fwk.channel.web.HpcXplatformView;


public class LoginCheckFilter implements Filter {
	
	/**
     * 로그를 기록하는 멤버 필드
     */
    Log log = LogManager.getFwkLog();
    
    /**
     * FilterConfig 객체.
     */
    protected FilterConfig config;

	/** 필터링 제외 런타임모드 */
	private String[] ignoreRuntimeModes;
	
	/** 필터링 제외 페이지 목록 */
	public String[] arrPassPageList;
	
	/** XplatformView 인스턴스 */
	private HpcXplatformView view = null;

	/** 로그아웃시 jsp 페이지 설정 */
	private String redirectUrl = null;
	
	/** 에러코드 */
	private String errorCode = null;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.config = filterConfig;
		
		// read config info.
		ignoreRuntimeModes = StringUtils.tokenizeToStringArray(config.getInitParameter("IGNORE_RUNTIME_MODE"), ";");
		arrPassPageList = StringUtils.tokenizeToStringArray(config.getInitParameter("BYPASS_PAGE_LIST"), ";");
		errorCode = StringUtils.nvl(config.getInitParameter("ERROR_CODE"), "-1");

		// redirect url for JSP
		redirectUrl = StringUtils.nvl(config.getInitParameter("REDIRECT_URL"),"index.jsp");
		
		// view page for Xplatform when invalid session 
		String viewId = config.getInitParameter("VIEW_ID");
		if (viewId != null) {
			view = (HpcXplatformView) BaseUtils.lookupWebComponent(viewId, config.getServletContext());
		}
		
		if (log.isInfoEnabled()) {
			log.info(this.config.getFilterName() + " WebFilter created.");
			log.info(this.config.getFilterName() + " byPassPageStr : " + config.getInitParameter("BYPASS_PAGE_LIST"));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String servletPath = req.getServletPath();

		try {
			// 사용자 세션 체크 수행
			if (!isIgnoreRuntimeMode() && !checkPassPage(servletPath)) {
				HttpSession session = req.getSession(false);
				
				// 로그인을 하지 않은 경우, 접속 금지(타임아웃 및 로그인이 안된 상태임)
				if (session == null || session.getAttribute(Constants.USER) == null) {
					if (log.isDebugEnabled()) {
						log.debug("LoginCheckFilter.doFilter() Irregular Access");
					}

					// 세션종료 메시지를 요청에 따라서 분기한다.
					if (servletPath.endsWith(".xpf")) {
						sendXfiServiceLogout(res, errorCode); //세션종료 메시지
					} else if (servletPath.endsWith(".xfdl")){
						sendXfiPageLogout(res, errorCode); //세션종료 메시지
					} else {
						sendJspLogout(req, res);
					}
				    
				    return;
				}
			}
			
			// request 정상수행
			filterChain.doFilter(request, response);
			
		} catch (Exception ex) {
			if (log.isErrorEnabled()) {
				log.error("WebFilter.doFilter() Exception : " + ex.getMessage(), ex);
			}
		}

	}

	/**
	 * 런타임모드가 제외 대상인지를 확인한다.
	 * @return
	 */
	private boolean isIgnoreRuntimeMode() {
		if(ignoreRuntimeModes != null){
			String curr = BaseUtils.getRuntimeMode();
			for(String conf : ignoreRuntimeModes){
				if(conf.equals(curr)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 해당 접속 URL이 필터링 제외 페이지인지 확인한다.
	 * 
	 * @param servletPath
	 * @return
	 */
	private boolean checkPassPage(String servletPath) {
		if (servletPath.endsWith(".xfdl")) {
			if (servletPath.startsWith("/ui/WDFS/form/")) {
				return false;
			} else {
				return true;
			}
		} else {
			for (int i = 0; i < arrPassPageList.length; i++) {
				if (arrPassPageList[i].equals(servletPath)) {
					return true;
				}
			}
			return false;			
		}
	}

	/**
	 * JSP 요청일때 세션종료 처리를 한다.
	 * 설정되어 있는 로그아웃 페이지로 redirect 한다.
	 * 
	 * @param request
	 * @param response
	 * @exception IOException
	 * 
	 */
	private void sendJspLogout(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		try {
			response.sendRedirect(request.getContextPath() + redirectUrl);
		} catch (IOException ex) {
			if (log.isErrorEnabled()) {
				log.error("Exception occurred while writing html to HttpServletResponse Stream.", ex);
			}
			throw ex;
		}
	}
	
	/**
	 * Xplatform 페이지 요청일때 세션종료 처리를 한다.
	 * 페이지를 요청한 경우이므로 세션종료 에러를 http error 코드로 리턴한다.
	 * 
	 * @param response
	 * @throws Exception
	 */
	private void sendXfiPageLogout(HttpServletResponse response, String errorCode)
			throws Exception {
		
		try {
			response.setStatus(Integer.parseInt(errorCode)); //세션아웃 에러코드 전달 
		} catch (Exception ex) {
			if (log.isErrorEnabled()) {
				log.error("Exception occurred while writing html to HttpServletResponse Stream.", ex);
			}
			throw ex;
		}
	}

	/**
	 * Xplatform 서비스 요청일때 세션종료 처리를 한다.
	 * ajax 방식으로 서비스를 요청한 것이므로 XML 로 에러코드를 리턴한다.
	 * 
	 * @param response
	 * @exception IOException
	 */
	private void sendXfiServiceLogout(HttpServletResponse response, String errorCode)
			throws Exception {

		try {
			PlatformData xpData = new PlatformData();
			VariableList xpVars = xpData.getVariableList();
			xpVars.add("ErrorCode", errorCode);
			xpVars.add("ErrorMsg", "session timeout error");

			// request 의 타입을 판단하기 위해서 사용. (XML, BINARY, BINARY COMPRESS)
			HttpPlatformResponse xpResp = new HttpPlatformResponse(
					response, PlatformType.CONTENT_TYPE_XML, PlatformType.DEFAULT_CHAR_SET);
			
			xpResp.setData(xpData);
			xpResp.sendData();

		} catch (Exception ex) {
			if (log.isErrorEnabled()) {
				log.error("Exception occurred while writing xml to Xplatform Stream.", ex);
			}
			throw ex;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
