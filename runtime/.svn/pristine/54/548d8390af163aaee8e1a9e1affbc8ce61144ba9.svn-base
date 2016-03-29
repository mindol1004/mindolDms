<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="nexcore.framework.core.util.BaseUtils"%>
<%@ page import="nexcore.framework.online.channel.util.WebUtils"%>
<html>
<head>
<title>[SAMPLE] - MESSAGE - NEXCORE J2EE Framework</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/debug/common.css" type="text/css">
<script>
</script>
</head>
<body>
<h1>[SAMPLE] - MESSAGE</h1>
<form name="MyForm" method="post">
<b>MESSAGE ('XYZE0001')</b>
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
		<td><%= WebUtils.getMessage("XYZE0001", request) %></td>
	</tr>
	<tr>
		<th>System</th>
		<td><%= WebUtils.getMessage("XYZE0001") %></td>
	</tr>
	<tr>
		<th>ko_KR</th>
		<td><%= WebUtils.getMessage("XYZE0001", BaseUtils.asLocale("ko_KR")) %></td>
	</tr>
	<tr>
		<th>en_US</th>
		<td><%= WebUtils.getMessage("XYZE0001", BaseUtils.asLocale("en_US")) %></td>
	</tr>
</table>
<br>
<b>MESSAGE ('FWKE0002') - without Parameter</b>
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
		<td><%= WebUtils.getMessage("FWKE0002", request) %></td>
	</tr>
	<tr>
		<th>System</th>
		<td><%= WebUtils.getMessage("FWKE0002") %></td>
	</tr>
	<tr>
		<th>ko_KR</th>
		<td><%= WebUtils.getMessage("FWKE0002", BaseUtils.asLocale("ko_KR")) %></td>
	</tr>
	<tr>
		<th>en_US</th>
		<td><%= WebUtils.getMessage("FWKE0002", BaseUtils.asLocale("en_US")) %></td>
	</tr>
</table>
<br>
<b>MESSAGE ('FWKE0002', {'ROLE'}) - with Parameter</b>
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
		<td><%= WebUtils.getMessage("FWKE0002", new String[]{"'ROLE'"}, request) %></td>
	</tr>
	<tr>
		<th>System</th>
		<td><%= WebUtils.getMessage("FWKE0002", new String[]{"'ROLE'"}) %></td>
	</tr>
	<tr>
		<th>ko_KR</th>
		<td><%= WebUtils.getMessage("FWKE0002", new String[]{"'ROLE'"}, BaseUtils.asLocale("ko_KR")) %></td>
	</tr>
	<tr>
		<th>en_US</th>
		<td><%= WebUtils.getMessage("FWKE0002", new String[]{"'ROLE'"}, BaseUtils.asLocale("en_US")) %></td>
	</tr>
</table>
</form>
</html>