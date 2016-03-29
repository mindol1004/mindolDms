<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="nexcore.framework.core.code.ICode"%>
<%@ page import="nexcore.framework.core.util.BaseUtils"%>
<%@ page import="nexcore.framework.online.channel.util.WebUtils"%>
<html>
<head>
<title>[SAMPLE] - CODE - NEXCORE J2EE Framework</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/debug/common.css" type="text/css">
<script>
</script>
</head>
<body>
<h1>[SAMPLE] - CODE</h1>
<form name="MyForm" method="post">
<b>CODE ('SKF0111', 'AP')</b>
<table cellpadding="2" cellspacing="0" class="contents">
	<colgroup>
		<col width="40%">
		<col width="60%">
	</colgroup>
	<tr>
		<th>Locale</th>
		<th>Value</th>
	</tr>
	<tr>
		<th>User</th>
		<td><%= WebUtils.getCodeValue("SKF0111", "AP", request) %></td>
	</tr>
	<tr>
		<th>System</th>
		<td><%= WebUtils.getCodeValue("SKF0111", "AP") %></td>
	</tr>
	<tr>
		<th>ko_KR</th>
		<td><%= WebUtils.getCodeValue("SKF0111", "AP", BaseUtils.asLocale("ko_KR")) %></td>
	</tr>
	<tr>
		<th>en_US</th>
		<td><%= WebUtils.getCodeValue("SKF0111", "AP", BaseUtils.asLocale("en_US")) %></td>
	</tr>
</table>
<br>
<b>CODE LIST ('SKF0111')</b>
<table cellpadding="2" cellspacing="0" class="contents">
	<colgroup>
		<col width="40%">
		<col width="60%">
	</colgroup>
	<tr>
		<th>Locale</th>
		<th>Value</th>
	</tr>
	<tr>
		<th>User</th>
		<td>
			<select>
<%
	List<ICode> codeList = WebUtils.getCodes("SKF0111");
	if(codeList != null){
		for(ICode c : codeList){		
%>
				<option value="<%= c.getId() %>">[<%= c.getId() %>] <%= c.getValue(WebUtils.getLocale(request)) %></option>
<%
		}
	}
%>	
			</select>
		</td>
	</tr>
	<tr>
		<th>System</th>
		<td>
			<select>
<%
	if(codeList != null){
		for(ICode c : codeList){		
%>
				<option value="<%= c.getId() %>">[<%= c.getId() %>] <%= c.getValue() %></option>
<%
		}
	}
%>	
			</select>
		</td>
	</tr>
	<tr>
		<th>ko_KR</th>
		<td>
			<select>
<%
	if(codeList != null){
		for(ICode c : codeList){		
%>
				<option value="<%= c.getId() %>">[<%= c.getId() %>] <%= c.getValue(BaseUtils.asLocale("ko_KR")) %></option>
<%
		}
	}
%>	
			</select>
		</td>
	</tr>
	<tr>
		<th>en_US</th>
		<td>
			<select>
<%
	if(codeList != null){
		for(ICode c : codeList){		
%>
				<option value="<%= c.getId() %>">[<%= c.getId() %>] <%= c.getValue(BaseUtils.asLocale("en_US")) %></option>
<%
		}
	}
%>	
			</select>
		</td>
	</tr>
</table>
</form>
</html>