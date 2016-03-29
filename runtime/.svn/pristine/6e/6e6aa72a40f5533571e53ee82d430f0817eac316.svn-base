<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="nexcore.framework.core.*"%>
<%@ page import="nexcore.framework.core.data.user.IUserInfo"%>
<%@ page import="nexcore.framework.core.file.*"%>
<%@ page import="nexcore.framework.core.prototype.*"%>
<%@ page import="nexcore.framework.core.util.*"%>
<%@ page import="nexcore.framework.online.channel.util.*"%>
<%
	IUserInfo userInfo = WebUtils.getSessionUserInfo(request);
	if(userInfo == null){
		response.sendRedirect("Login.jsp");
	}
	
	List<UploadedFileReference> uploadedFiles = WebUtils.getResultUploadedFileReferences(request);
%>
<html>
<head>
<title>[SAMPLE] - FILE UPLOAD/DOWNLOAD - NEXCORE J2EE Framework</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/debug/common.css" type="text/css">
<script>
	function doUpload(target){
		document.MyForm.<%=Constants.TRANSACTION_ID%>.value="fwk.FWKSBase#fFWK09FileUpload";
		document.MyForm.<%=Constants.TARGET%>.value=target;
		document.MyForm.<%=Constants.UPLOAD_CATEGORY%>.value="XYZ";
		document.MyForm.target = "_self";
		document.MyForm.action = "<%=request.getContextPath()%>/standard.cmd";
		document.MyForm.submit();
	}
	function doDownload(fileId, fileName){
		document.MyForm.<%=Constants.TRANSACTION_ID%>.value="fwk.FWKSBase#fFWK09FileDownload";
		document.MyForm.<%=Constants.UPLOADED_FILE_ID%>.value=fileId;
		document.MyForm.<%=Constants.UPLOADED_FILE_NAME%>.value=fileName;
		document.MyForm.target = "_self";
		document.MyForm.action = "<%=request.getContextPath()%>/filedownload.cmd";
		document.MyForm.submit();
	}
	function doDelete(fileId){
		document.MyForm.<%=Constants.TRANSACTION_ID%>.value="fwk.FWKSBase#fFWK09FileDelete";
		document.MyForm.<%=Constants.UPLOADED_FILE_ID%>.value=fileId;
		document.MyForm.<%=Constants.UPLOADED_FILE_NAME%>.value="";
		document.MyForm.target = "_self";
		document.MyForm.action = "<%=request.getContextPath()%>/filedelete.cmd";
		document.MyForm.submit();
	}
	
	var fileSeq = 0;
	function addFileRow(){
		var table = document.getElementById("FILE_UPLOAD_TABLE").getElementsByTagName("TBODY")[0];

		var newIndex = fileSeq++;

		var row = document.createElement("tr");
		row.setAttribute("align", "center");
		row.setAttribute("id", "FILE_TR_"+newIndex);

		var col1= document.createElement("td");
		
		col1.innerHTML = "<input type=\"file\" id=\"FILE_"+newIndex+"\" name=\"FILE_"+newIndex+"\" >";

		var col2= document.createElement("td");
		col2.innerHTML = "<input type=\"button\" onclick=\"javascript:delFileRow('"+newIndex+"');\" value=\"－\">";
		
		row.appendChild(col1);
		row.appendChild(col2);
		
		table.appendChild(row);
		//table.insertBefore(row, table.firstChild.nextSibling);
	}
	function delFileRow(idx) {
		var table   = document.getElementById("FILE_UPLOAD_TABLE").getElementsByTagName("TBODY")[0];
		var delRow  = document.getElementById('FILE_TR_' + idx);
		table.removeChild(delRow);
	}
</script>
</head>
<body>
<h1><a href="sample/FileUpload.jsp">[SAMPLE] - FILE UPLOAD/DOWNLOAD</a></h1>
<form name="MyForm" method="post" enctype="multipart/form-data">
<input type="hidden" name="<%=Constants.TRANSACTION_ID%>">
<input type="hidden" name="<%=Constants.TARGET%>">
<input type="hidden" name="<%=Constants.UPLOAD_CATEGORY%>">
<input type="hidden" name="<%=Constants.UPLOADED_FILE_ID%>">
<input type="hidden" name="<%=Constants.UPLOADED_FILE_NAME%>">
<%
	// 업로드 파일 존재.
	if(uploadedFiles != null && uploadedFiles.size() > 0){
%>
<table cellpadding="2" cellspacing="0" class="contents">
	<tr>
		<th>File ID</th>
		<th>Original filename</th>
		<th>Original filename(include full path)</th>
		<th>Form field name</th>
		<th>Size</th>
		<th>Content type</th>
		<th>Status</th>
		<th>Fail message</th>
		<th>Action</th>
	</tr>
<%
	for(UploadedFileReference uf : uploadedFiles){
%>	
	<tr>
		<td><%= uf.getFileId() %></td>
		<td><%= uf.getOriginalName() %></td>
		<td><%= uf.getOriginalFullName() %></td>
		<td><%= uf.getFieldName() %></td>
		<td align="right"><%= uf.getSize() %></td>
		<td align="center"><%= uf.getContentType() %></td>
		<td align="center"><%= uf.getStatus() %></td>
		<td><%= uf.getFailMessage() %></td>
		<td align="center">
			<button onClick="javascript:doDownload('<%= uf.getFileId() %>', '<%= uf.getOriginalName() %>');return false;">Download</button>
			<button onClick="javascript:doDelete('<%= uf.getFileId() %>');return false;">Delete</button>
		</td>
	</tr>
<%
		}
%>	
</table>
<%		
	}
	else {
%>
<fieldset style="background-color:#FFFFFF;">
<table width="100%">
	<tr>
		<td align="right">
			<button onClick="javascript:doUpload('forward:sample/FileUpload.jsp');">Upload(JSP)</button>
			<button onClick="javascript:doUpload('forward:FileUpload-xml.jsp');">Upload(XML response)</button>
			<button onClick="javascript:doUpload('forward:FileUpload-json.jsp');">Upload(JSON response)</button>
		</td>
	</tr>
</table>
</fieldset>
<table id="FILE_UPLOAD_TABLE"  cellpadding="2" cellspacing="0" class="contents">
	<colgroup>
		<col width="90%">
		<col width="10%">
	</colgroup>
	<tr>
		<th>Selected File</th>
		<th><input type="button" onclick="javascript:addFileRow();" value="＋"></th>
	</tr>
</table>
<%		
	}
%>

</form>
</html>