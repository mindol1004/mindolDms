<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.management.ManagementFactory"%>
<%@ page import="nexcore.framework.core.Constants"%>
<%@ page import="nexcore.framework.core.util.DateUtils"%>
<%@ page import="nexcore.framework.core.util.BaseUtils"%>

<html>
<head>
<title>[<%=System.getProperty("system.id")%>] - Information</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/debug/common.css" type="text/css">
</head>
<body>
	<table width="100%" height="95%" cellpadding="2" cellspacing="0">
		<tr>
			<td align="center" valign="middle">
				<table width="600">
					<tr>
						<td>
							<table width="100%" cellpadding="5" cellspacing="0">
								<tr>
									<td align="right">[<%= DateUtils.dateToString(new Date(), "yyyy/MM/dd HH:mm:ss.SSS") %>]</td>
								</tr>
							</table>
							
							<table cellpadding="2" cellspacing="0" class="contents">
								<colgroup>
									<col width="35%"/>
									<col width="65%"/>
								</colgroup>
								<tr>
									<td colspan="2" align="left"><img alt="" src="NEXCORE_signature.gif"></td>
								</tr>
								<tr>
									<th>NEXCORE HOME</th>
									<td align="left"><%= BaseUtils.getFwkHome() %></td>
								</tr>
								<tr>
									<th>NEXCORE LOG HOME</th>
									<td align="left"><%= System.getProperty("NEXCORE_LOG_HOME") %></td>
								</tr>
								<tr>
									<th>SYSTEM ID</th>
									<td align="left"><%= System.getProperty("system.id") %></td>
								</tr>
								<tr>
									<th>JVM START TIME</th>
									<td align="left"><%= DateUtils.dateToString(new Date(ManagementFactory.getRuntimeMXBean().getStartTime()), "yyyy/MM/dd HH:mm:ss.SSS") %></td>
								</tr>
								<tr>
									<th>LOCALE</th>
									<td align="left"><%= BaseUtils.getDefaultLocale() %> (<%= Locale.getDefault() %>)</td>
								</tr>
								<tr>
									<th>FILE ENCODING</th>
									<td align="left"><%= System.getProperty("file.encoding") %></td>
								</tr>
								<tr>
									<th>JAVA VERSION</th>
									<td align="left"><%= System.getProperty("java.version") %> (<%= System.getProperty("java.vendor") %>)</td>
								</tr>
								<tr>
									<th>JVM VERSION</th>
									<td align="left"><%= System.getProperty("java.vm.name") %> <%= System.getProperty("java.vm.version") %> (<%= System.getProperty("java.vm.vendor") %>)</td>
								</tr>
								<tr>
									<th>OPERATING SYSTEM</th>
									<td align="left"><%= System.getProperty("os.name") %> <%= System.getProperty("os.version") %> (<%= System.getProperty("os.arch") %>)</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
