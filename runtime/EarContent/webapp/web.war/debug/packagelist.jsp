<%@ page contentType="text/html; charset=UTF-8" pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*" %>
<%@page import="javax.servlet.ServletOutputStream"%>
<%@page import="nexcore.framework.core.component.*"%>
<%@page import="nexcore.framework.core.ioc.*"%>
<html>
<head>
<title>[<%=System.getProperty("system.id")%>] - PACKAGE LIST</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/debug/common.css" type="text/css">
<script>
</script>
</head>
<body>
<h1>[<%=System.getProperty("system.id")%>] - PACKAGE LIST : (<%= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) %>)</h1>
<table cellpadding="2" cellspacing="0" class="contents">
	<tr>
		<th>Name</th>
		<th>Spec Title</th>
		<th>Spec Version</th>
		<th>Spec Vendor</th>
		<th>Impl Title</th>
		<th>Impl Version</th>
		<th>Impl Vendor</th>
	</tr>
<%
	List<String> keys = new ArrayList<String>();
	Map<String, Package> map = new HashMap<String, Package>();
	Package[] pkgs = Package.getPackages();
	for(Package pkg : pkgs){
		keys.add(pkg.getName());
		map.put(pkg.getName(), pkg);
	}
	Collections.sort(keys);
	for(String key : keys){
		Package pkg = map.get(key);
%>
	<tr>
		<td><%= pkg.getName() %></td>
		<td><%= pkg.getSpecificationTitle() %></td>
		<td><%= pkg.getSpecificationVersion() %></td>
		<td><%= pkg.getSpecificationVendor() %></td>
		<td><%= pkg.getImplementationTitle() %></td>
		<td><%= pkg.getImplementationVersion() %></td>
		<td><%= pkg.getImplementationVendor() %></td>
	</tr>
<%	
	}
%>
</table>
</body>
</html>
