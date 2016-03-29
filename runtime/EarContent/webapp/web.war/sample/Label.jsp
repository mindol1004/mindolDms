<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="nexcore.framework.core.util.BaseUtils"%>
<%@ page import="nexcore.framework.core.label.ILabel"%>
<%@ page import="nexcore.framework.online.channel.util.WebUtils"%>
<html>
<head>
<title>[SAMPLE] - LABEL - NEXCORE J2EE Framework</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/debug/common.css" type="text/css">
<script>
</script>
</head>
<body>
<h1>[SAMPLE] - LABEL</h1>
<form name="MyForm" method="post">
<b>LABEL ('sample.label1')</b>
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
		<td><%= WebUtils.getLabelName("sample.label1", request)%></td>
	</tr>
	<tr>
		<th>System</th>
		<td><%= WebUtils.getLabelName("sample.label1")%></td>
	</tr>
	<tr>
		<th>ko_KR</th>
		<td><%= WebUtils.getLabelName("sample.label1", BaseUtils.asLocale("ko_KR"))%></td>
	</tr>
	<tr>
		<th>en_US</th>
		<td><%= WebUtils.getLabelName("sample.label1", BaseUtils.asLocale("en_US"))%></td>
	</tr>
</table>
</form>
</html>