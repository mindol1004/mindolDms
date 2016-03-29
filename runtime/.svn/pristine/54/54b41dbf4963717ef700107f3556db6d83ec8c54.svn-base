<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="nexcore.framework.online.channel.util.WebUtils"%>

<script type="text/javascript">
<!--
	function doExpand(btn, div){
		var btn   = document.getElementById(btn);
		var style = document.getElementById(div).style;
		var expand = btn.value == '-';
		if(expand){
			expand = false;
			style.display = "none";
			btn.value = "+";
		}
		else {
			expand = true;
			style.display = "block";
			btn.value = "-";
		}
	}
//-->
</script>

<fieldset style="background-color:#FFFFFF;">
<legend><input type="button" id="btn_debug_detail" value="+" onclick="javascript:doExpand('btn_debug_detail', 'div_debug_detail');"> Detail</legend>
<div id="div_debug_detail" style="display:none;">
	<table cellpadding="2" cellspacing="0" class="contents">
		<colgroup>
			<col width="15%">
			<col width="15%">
			<col width="70%">
		</colgroup>
		<tr>
			<th>Type</th>
			<th>Attribute Name</th>
			<th>Attribute Value</th>
		</tr>
		<tr>
			<th rowspan="27">javax.servlet.http.HttpServletRequest</th>
		    <td class="th">getParameters()</td>
		    <td><pre><%=WebUtils.getRequestMessage(request) %></pre></td>
		</tr>
		<tr>
		    <td class="th">getAttributes()</td>
		    <td><pre><%=WebUtils.getRequestAttributeMessage(request) %></pre></td>
		</tr>
		<tr>
		    <td class="th">getHeaders()</td>
		    <td><pre><%=WebUtils.getRequestHeaderMessage(request) %></pre></td>
		</tr>
		<tr>
		    <td class="th">getCookies()</td>
		    <td><pre><%=WebUtils.getRequestCookieMessage(request) %></pre></td>
		</tr>
		<tr>
		    <td class="th">getCharacterEncoding()</td>
		    <td><%=request.getCharacterEncoding()%></td>
		</tr>
		<tr>
		    <td class="th">getContentLength()</td>
		    <td><%=request.getContentLength()%></td>
		</tr>
		<tr>
		    <td class="th">getContentType()</td>
		    <td><%=request.getContentType()%></td>
		</tr>
		<tr>
		    <td class="th">getLocale()</td>
		    <td><%=request.getLocale()%></td>
		</tr>
		<tr>
		    <td class="th">getProtocol()</td>
		    <td><%=request.getProtocol()%></td>
		</tr>
		<tr>
		    <td class="th">getRemoteAddr()</td>
		    <td><%=request.getRemoteAddr()%></td>
		</tr>
		<tr>
		    <td class="th">getRemoteHost()</td>
		    <td><%=request.getRemoteHost()%></td>
		</tr>
		<tr>
		    <td class="th">getScheme()</td>
		    <td><%=request.getScheme()%></td>
		</tr>
		<tr>
		    <td class="th">getServerName()</td>
		    <td><%=request.getServerName()%></td>
		</tr>
		<tr>
		    <td class="th">getServerPort()</td>
		    <td><%=request.getServerPort()%></td>
		</tr>
		<tr>
		    <td class="th">isSecure()</td>
		    <td><%=request.isSecure()%></td>
		</tr>
		<tr>
		    <td class="th">getRequestURL()</td>
		    <td><%=request.getRequestURL()%></td>
		</tr>
		<tr>
		    <td class="th">getRequestURI()</td>
		    <td><%=request.getRequestURI()%></td>
		</tr>
		<tr>
		    <td class="th">getServletPath()</td>
		    <td><%=request.getServletPath()%></td>
		</tr>
		<tr>
		    <td class="th">getContextPath()</td>
		    <td><%=request.getContextPath()%></td>
		</tr>
		<tr>
		    <td class="th">getAuthType()</td>
		    <td><%=request.getAuthType()%></td>
		</tr>
		<tr>
		    <td class="th">getPathInfo()</td>
		    <td><%=request.getPathInfo()%></td>
		</tr>
		<tr>
		    <td class="th">getMethod()</td>
		    <td><%=request.getMethod()%></td>
		</tr>
		<tr>
		    <td class="th">getPathTranslated()</td>
		    <td><%=request.getPathTranslated()%></td>
		</tr>
		<tr>
		    <td class="th">getQueryString()</td>
		    <td><%=request.getQueryString()%></td>
		</tr>
		<tr>
		    <td class="th">getRemoteUser()</td>
		    <td><%=request.getRemoteUser()%></td>
		</tr>
		<tr>
		    <td class="th">getRequestedSessionId()</td>
		    <td><%=request.getRequestedSessionId()%></td>
		</tr>
		<tr>
		    <td class="th">getUserPrincipal()</td>
		    <td><%=request.getUserPrincipal()%></td>
		</tr>
		
		
		<tr>
		    <th rowspan="4">javax.servlet.http.HttpServletResponse</th>
		    <td class="th">getBufferSize()</td>
		    <td><%=response.getBufferSize()%></td>
		</tr>
		<tr>
		    <td class="th">getCharacterEncoding()</td>
		    <td><%=response.getCharacterEncoding()%></td>
		</tr>
		<tr>
		    <td class="th">getLocale()</td>
		    <td><%=response.getLocale()%></td>
		</tr>
		<tr>
		    <td class="th">isCommitted()</td>
		    <td><%=response.isCommitted()%></td>
		</tr>
		

		<tr>
		    <th rowspan="6">javax.servlet.http.HttpSession</th>
		    <td class="th">getAttributes()</td>
		    <td><pre><%=WebUtils.getSessionMessage(session) %></pre></td>
		</tr>
		<tr>
		    <td class="th">getId()</td>
		    <td><%=session.getId() %></td>
		</tr>
		<tr>
		    <td class="th">getCreationTime()</td>
		    <td><%=session.getCreationTime() %></td>
		</tr>
		<tr>
		    <td class="th">getLastAccessedTime()</td>
		    <td><%=new java.util.Date(session.getLastAccessedTime()) %></td>
		</tr>
		<tr>
		    <td class="th">getMaxInactiveInterval()</td>
		    <td><%=session.getMaxInactiveInterval() %></td>
		</tr>
		<tr>
		    <td class="th">isNew()</td>
		    <td><%=session.isNew() %></td>
		</tr>
		

		<tr>
		    <th rowspan="4">javax.servlet.ServletContext</th>
		    <td class="th">getServerInfo()</td>
		    <td><%=application.getServerInfo()%></td>
		</tr>
		<tr>
		    <td class="th">getServletContextName()</td>
		    <td><%=application.getServletContextName()%></td>
		</tr>
		<tr>
		    <td  class="th">getVersion()</td>
		    <td><%=application.getMajorVersion() + "." + application.getMinorVersion()%></td>
		</tr>
		<tr>
		    <td class="th">getAttributes()</td>
		    <td><pre><%=WebUtils.getApplicationAttributeMessage(application) %></pre></td>
		</tr>
		
	</table>
</div>
</fieldset>
