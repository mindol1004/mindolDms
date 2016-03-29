<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	response.setStatus(HttpServletResponse.SC_OK);
%>
<html>
<head>
<title>SERVER ERROR</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/debug/common.css" type="text/css">
</head>
<body>
<h2>SERVER ERROR : 서버관리자에게 문의하세요</h2>
</body>
</html>
