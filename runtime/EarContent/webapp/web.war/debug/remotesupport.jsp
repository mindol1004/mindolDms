<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="nexcore.framework.core.util.*"%>

<%
	// 호스트명
	String userInfo = null;
	try{
		java.net.InetAddress ia = java.net.InetAddress.getLocalHost();
		userInfo = ia.getHostName() + "(" + ia.getHostAddress() + ")";
	}catch(Exception e){
	}
	
	// 로컬 시스템 여부
	boolean isLocalhost = "localhost".equals(BaseUtils.getCurrentWasInstanceId());

	// 프레임워크 홈 디렉터리 조회
	String fwkHome = BaseUtils.getFwkHome();
	if(isLocalhost){
		fwkHome = new File(fwkHome).getParent();
	}

	// 시스템 속성에서 조회
//	String logHome = "/logs/nexcore/" + BaseUtils.getCurrentWasInstanceId();
	String logHome = System.getProperty("NEXCORE_LOG_HOME");

 	// 로그 객체를 분석하여 조회
	if(logHome == null || logHome.trim().length() < 1){
		org.apache.log4j.Logger logger = org.apache.log4j.LogManager.exists(nexcore.framework.core.log.LogManager.BASE_FWK_LOGGER_NAME);
		if(logger != null){
			Enumeration appenders = logger.getAllAppenders();
			while (appenders.hasMoreElements()) {
				org.apache.log4j.Appender a = (org.apache.log4j.Appender) appenders.nextElement();
	            if(a instanceof org.apache.log4j.FileAppender){
	            	org.apache.log4j.FileAppender fa = (org.apache.log4j.FileAppender)a;
	            	File f = new File(fa.getFile());
	            	logHome = f.getParent();
	            	break;
	            }
	            else if(a instanceof org.apache.log4j.AsyncAppender){
	            	org.apache.log4j.AsyncAppender aa = (org.apache.log4j.AsyncAppender)a;
	            	Enumeration refs = aa.getAllAppenders();
	            	while (refs.hasMoreElements()) {
	            		a = (org.apache.log4j.Appender) refs.nextElement();
	            		if(a instanceof org.apache.log4j.FileAppender){
	    	            	org.apache.log4j.FileAppender fa = (org.apache.log4j.FileAppender)a;
	    	            	File f = new File(fa.getFile());
	    	            	logHome = f.getParent();
	    	            	break;
	    	            }
	            	}
	            	break;
	            }
	        }
		}
	}
	
	fwkHome = fwkHome.replaceAll("\\\\", "\\/");
	if(!fwkHome.endsWith("/")){
		fwkHome += "/";
	}
	
	logHome = logHome.replaceAll("\\\\", "\\/");
	if(!logHome.endsWith("/")){
		logHome += "/";
	}

	// 입력 파라미터
	String kind = request.getParameter("kind");
	String curr = request.getParameter("curr");
	String mode = request.getParameter("mode");
	String name = request.getParameter("name");
	
	boolean isLog = "log".equals(kind);
	
	if(curr == null){
		curr = "";
	}
	if(name == null){
		name = "";
	}

	if(curr.indexOf("../") > -1 || name.indexOf("../") > -1){
		curr = "";
		name = "";
	}

	boolean isLogSelected = false;
	if("up".equals(mode)){
		int index = curr.lastIndexOf("/");
		if(index > 0){
			curr = curr.substring(0, index);
		}
		else{
			curr = "";
		}
	}
	else if("dn".equals(mode)){
		if(isLog && "__loghome".equals(curr) && "__logsub".equals(name)){
			isLogSelected = true;
			curr = "";
			name = "";
		}
		if(!"".equals(curr)){
			curr += "/";
		}
		curr += name;
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss,SSS");
	
	boolean isHome = ((curr == null || "".equals(curr)) && !isLogSelected);
%>

<head>
<title>[<%= userInfo == null ? "" : userInfo %>] - REMOTE SUPPORT</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/debug/common.css" type="text/css">
<script>
	function fileDown(kind, curr, name) {
		document.StateForm.kind.value=kind;
		document.StateForm.curr.value=curr;
		document.StateForm.name.value=name;
		document.StateForm.target = "TaskFrame";
		document.StateForm.action = "remotesupportdown.jsp";
		document.StateForm.submit();
    }
</script>
</head>
<form name="StateForm" method="post">
	<input type="hidden" name="kind">
	<input type="hidden" name="curr">
	<input type="hidden" name="name">
</form>
<h1>[<%= userInfo == null ? "" : userInfo %>] - REMOTE SUPPORT : (<%= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) %>)</h1>
<b><a href="remotesupport.jsp">[HOME]</a> <%= isHome ? "" : isLogSelected ? "LOG" : "SOURCE" %> RELATIVE PATH : [<%= curr %>]</b>
<table cellpadding="2" cellspacing="0" class="contents">
	<colgroup>
		<col width="5%"/>
		<col width="55%"/>
		<col width="5%"/>
		<col width="10%"/>
		<col width="25%"/>
	</colgroup>
	<tr style="background-color:gray; font-weight:bold;">
		<th>No</th>
		<th>Name</th>
		<th>Kind</th>
		<th>Size (bytes)</th>
		<th>Last Modified</th>
	</tr>
<%
	if(!"".equals(curr) || isLogSelected){
%>
	<tr>
		<td align="center">0</td>
		<td align="left">&nbsp;<a href="remotesupport.jsp?kind=<%= kind %>&curr=<%= curr %>&mode=up"><b>..</b></a></td>
		<td align="center">dir</td>
		<td align="right">&nbsp;</td>
		<td align="center">&nbsp;</td>
	</tr>
<%	
	}

	int index = 0;

	// 최상위 
	if(isHome){
		File dir = null;

		// log
		dir = new File(logHome);
		if(dir.exists()){
%>
		<tr>
			<td align="center"><%= (++index) %></td>
			<td align="left">&nbsp;
				<a href="remotesupport.jsp?kind=log&curr=__loghome&mode=dn&name=__logsub"><%= "LOG HOME" %></a>
			</td>
			<td align="center"><%= dir.isDirectory() ? "dir" : "file" %></td>
			<td align="right"><%= dir.isFile() ? dir.length() : "" %>&nbsp;</td>
			<td align="center"><%= sdf.format(new Date(dir.lastModified())) %></td>
		</tr>
<%
		}
		
		// home
		dir = new File(fwkHome);
		if(dir.exists()){
			File[] files = dir.listFiles();
			FileUtils.sortFiles(files);
			if(files != null){
				File f = null;
				int len = files.length;
				for(int i=0; i<len; i++){
					f = files[i];
%>
		<tr>
			<td align="center"><%= (++index) %></td>
			<td align="left">&nbsp;
<%
		if(f.isDirectory()){
%>
				<a href="remotesupport.jsp?kind=app&curr=<%= curr %>&mode=dn&name=<%=f.getName()%>"><%= f.getName() %></a>
<%		
		}
		else{
%>
				<a href="javascript:fileDown('app', '<%= curr %>', '<%=f.getName()%>');"><%= f.getName() %></a>
<%		
		}
%>			
			</td>
			<td align="center"><%= f.isDirectory() ? "dir" : "file" %></td>
			<td align="right"><%= f.isFile() ? f.length() : "" %>&nbsp;</td>
			<td align="center"><%= sdf.format(new Date(f.lastModified())) %></td>
		</tr>
<%
				}
			}
		}
	}
	else{
		File dir = new File((isLog ? logHome : fwkHome) + curr);
		if(dir.exists()){
			File[] files = dir.listFiles();
			FileUtils.sortFiles(files);
			if(files != null){
				File f = null;
				int len = files.length;
				for(int i=0; i<len; i++){
					f = files[i];
%>
		<tr>
			<td align="center"><%= (++index) %></td>
			<td align="left">&nbsp;
<%
		if(f.isDirectory()){
%>
				<a href="remotesupport.jsp?kind=<%= kind %>&curr=<%= curr %>&mode=dn&name=<%=f.getName()%>"><%= f.getName() %></a>
<%		
		}
		else{
%>
				<a href="javascript:fileDown('<%= kind %>', '<%= curr %>', '<%=f.getName()%>');"><%= f.getName() %></a>
<%		
		}
%>			
			</td>
			<td align="center"><%= f.isDirectory() ? "dir" : "file" %></td>
			<td align="right"><%= f.isFile() ? f.length() : "" %>&nbsp;</td>
			<td align="center"><%= sdf.format(new Date(f.lastModified())) %></td>
		</tr>
<%
				}
			}
		}
	}
%>
</table>
<IFRAME name="TaskFrame" id="TaskFrame" style="WIDTH: 1; HEIGHT: 1" src="about:blank" frameBorder="1"></IFRAME>