<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="nexcore.framework.core.Constants"%>
<%@ page import="nexcore.framework.core.code.ICode"%>
<%@ page import="nexcore.framework.core.data.user.IUserInfo"%>
<%@ page import="nexcore.framework.core.util.BaseUtils"%>
<%@ page import="nexcore.framework.online.channel.util.WebUtils"%>
<%
	IUserInfo userInfo = WebUtils.getSessionUserInfo(request);
	if(userInfo == null){
		response.sendRedirect("Login.jsp");
	}
	
	String user_locale = request.getParameter("user_locale");
	if(user_locale != null && user_locale.trim().length() > 0){
		userInfo.setLocale(BaseUtils.asLocale(user_locale));
	}
%>
<html>
<head>
<title>[SAMPLE] - MAIN - NEXCORE J2EE Framework</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/debug/common.css" type="text/css">
<script type="text/javascript" language="javascript">
	function doLogout(){
		document.MyForm.<%= Constants.TRANSACTION_ID %>.value="";
		document.MyForm.<%= Constants.TARGET %>.value="redirect:sample/Login.jsp";
		document.MyForm.target = "_self";
		document.MyForm.action = "<%=request.getContextPath()%>/logout.cmd";
		document.MyForm.submit();
	}
	function doLocale(){
		document.MyForm.<%= Constants.TRANSACTION_ID %>.value="";
		document.MyForm.<%= Constants.TARGET %>.value="redirect:sample/Main.jsp?user_locale=" + document.MyForm.user_locale.value;
		document.MyForm.target = "_self";
		document.MyForm.action = "<%=request.getContextPath()%>/standard.cmd";
		document.MyForm.submit();
	}
</script>
</head>
<body>
<h1>[SAMPLE] - MAIN</h1>
<form name="MyForm" method="post">
<input type="hidden" name="<%= Constants.TRANSACTION_ID %>">
<input type="hidden" name="<%= Constants.TARGET %>">
<table width="1000">
	<tr>
		<td align="right">
			ID:[<%= userInfo == null ? "" : userInfo.getLoginId() %>]
			&nbsp;&nbsp;&nbsp;LoginTime:[<%= userInfo == null ? "" : userInfo.getLoginTime() %>]
			&nbsp;&nbsp;&nbsp;My Locale:
			<select name="user_locale" onchange="javascript:doLocale();">
				<option value="ko_KR" <%= userInfo != null ? ("ko_KR".equals(userInfo.getLocale().toString()) ? "selected" : "") : "" %>>ko_KR</option>
				<option value="en_US" <%= userInfo != null ? ("en_US".equals(userInfo.getLocale().toString()) ? "selected" : "") : "" %>>en_US</option>
			</select>
			&nbsp;&nbsp;&nbsp;System Locale:[<%= BaseUtils.getDefaultLocale() %>]
			&nbsp;&nbsp;&nbsp;<button onClick="javascript:doLogout();">Logout</button> 
		</td>
	</tr>
</table>
<table width="1000" height="700" border="1">
	<colgroup>
		<col width="200">
		<col width="800">
	</colgroup>
	<tr>
		<td valign="top" align="left">
			<br>&nbsp;&nbsp;■ <a href="FileUpload.jsp" target="mainframe">FILE UPLOAD/DOWNLOAD</a>
			<br>&nbsp;&nbsp;■ <a href="Code.jsp"       target="mainframe">CODE</a>
			<br>&nbsp;&nbsp;■ <a href="Message.jsp"    target="mainframe">MESSAGE</a>
			<br>&nbsp;&nbsp;■ <a href="Label.jsp"      target="mainframe">LABEL</a>
		</td>
		<td valign="top" align="left">
			<iframe name="mainframe" width="795" height="695"></iframe>
		</td>
	</tr>
</table>
</form>
</html>