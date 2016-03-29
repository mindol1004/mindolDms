<%@ page contentType="text/html; charset=UTF-8" pageEncoding="EUC-KR"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*" %>
<%@page import="javax.servlet.ServletOutputStream"%>
<%@page import="nexcore.framework.core.component.*"%>
<%@page import="nexcore.framework.core.ioc.*"%>
<html>
<head>
<title>[<%=System.getProperty("system.id")%>] - TRANSACTION LIST</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/debug/common.css" type="text/css">
<script>
	function doSelect(obj) {
	    document.location.href = "txlist.jsp?compFqId="+obj.value;
    }
</script>
</head>
<h1>[<%=System.getProperty("system.id")%>] - TRANSACTION LIST : (<%= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) %>)</h1>
<body>
<%
	String compFqId = request.getParameter("compFqId");
	IBizComponentMetaDataRegistry cmdRegistry = (IBizComponentMetaDataRegistry ) ComponentRegistry.lookup("nc.core.IBizComponentMetaDataRegistry");
	List<IComponentMetaData> cmdList = (List<IComponentMetaData>)cmdRegistry.getAllComponentMetaData();
%>

	<B>COMPONENT LIST</B> : 
	<select name="compFqId" onchange="javascript:doSelect(this);return false;">
		<option value="">ALL</option>
<% 
    for (IComponentMetaData cmd : cmdList) {
%>    	
    	<option value="<%= cmd.getFqId() %>" <%= cmd.getFqId().equals(compFqId) ? "selected" : "" %>><%= cmd.getName() %> (<%= cmd.getFqId() %>)</option>
<%
    }
%>
	</select>
	<table cellpadding="2" cellspacing="0" class="contents">
		<tr>
			<th>COMPONENT</th>
			<th>TX ID</th>
			<th>TX NAME</th>
			<th>FIXED LENGTH</th>
			<th>UNIT.METHOD</th>
		</tr>
<%
	IComponentMetaData cmd = null;
	if(compFqId != null && compFqId.length() > 0){
		cmd = cmdRegistry.getComponentMetaData(compFqId);
	}
	
	if(cmd != null){
		out.println(printComponentMetaData(cmd));
	}
	else {
		for (IComponentMetaData c : cmdList) {
        	out.println(printComponentMetaData(c));
        }
	}
%>
	</table>
</body>
</html>
<%!
    String printComponentMetaData(IComponentMetaData cmd) throws Exception {
		StringBuilder buff = new StringBuilder();
		
		int count = cmd.getMethodMetaDataMap().size();
		
		buff.append("<tr>");
		buff.append("  <td rowspan='"+ count +"'>");
		buff.append(cmd.getName());
		buff.append("<br> &nbsp;&nbsp;- v." + cmd.getLoadedVersionNo() + " ("+ (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(cmd.getLoadedTimeMillis()))+")");
		buff.append("<br> &nbsp;&nbsp;- COMP FQ ID : " + cmd.getFqId());
		buff.append("<br> &nbsp;&nbsp;- PACKAGE &nbsp;&nbsp;&nbsp; : " + cmd.getPackageName());
		buff.append("<br> &nbsp;&nbsp;- TX COUNT &nbsp;&nbsp; : " + count);
		buff.append("  </td>");
		
		boolean first = true;
		Iterator iter = new TreeMap(cmd.getMethodMetaDataMap()).entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            IMethodMetaData mmd = (IMethodMetaData)entry.getValue();
            if(!first){
        		buff.append("        <tr>");
            }
    		buff.append("          <td>"+entry.getKey()+"</td>");
    		buff.append("          <td>"+mmd.getMethodName()+"</td>");
    		buff.append("          <td align='center'>"+ (mmd.isFixedLengthed() ? "Y" : "&nbsp;")+"</td>");
    		buff.append("          <td>"+mmd.getBizUnitId()+"."+mmd.getId()+"()"+"</td>");
    		buff.append("        </tr>");
    		first = false;
        }
		
		return buff.toString();
    }
%>