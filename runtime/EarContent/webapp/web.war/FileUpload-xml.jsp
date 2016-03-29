<%@page contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8" 
%><%@page import="java.util.*"
%><%@page import="nexcore.framework.core.data.*"
%><%@page import="nexcore.framework.core.data.xml.*"
%><%@page import="nexcore.framework.core.file.*"
%><%@page import="nexcore.framework.core.util.*"
%><%@page import="nexcore.framework.online.channel.util.*"
%><%@page import="org.apache.commons.lang.StringEscapeUtils"
%><%ITransaction transaction = WebUtils.getResultTransaction(request);
	List<UploadedFileReference> uploadedFiles = WebUtils.getResultUploadedFileReferences(request);
	IDataSet resultData = WebUtils.getResultDataSet(request);
	Locale locale = WebUtils.getLocale(pageContext);
	
	// print
	out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
	out.println("<"+JdomProcessor.ELM_RESPONSE+">");
	
	out.println("\t<"+JdomProcessor.ELM_TX+">");
	out.println("\t\t<"+JdomProcessor.ELM_TX_ID+">"+ StringEscapeUtils.escapeXml(transaction == null ? "" : transaction.getTxId() == null ? "" : transaction.getTxId()) + "</"+JdomProcessor.ELM_TX_ID+">");
	out.println("\t\t<"+JdomProcessor.ELM_TX_START_DT+">"+ StringEscapeUtils.escapeXml("N/A") + "</"+JdomProcessor.ELM_TX_START_DT+">");
	out.println("\t\t<"+JdomProcessor.ELM_TX_END_DT+">"+ StringEscapeUtils.escapeXml("N/A") + "</"+JdomProcessor.ELM_TX_END_DT+">");
	out.println("\t</"+JdomProcessor.ELM_TX+">");

	out.println("\t<uploadedFiles>");
	if(uploadedFiles != null){
		for(UploadedFileReference uf : uploadedFiles){
			out.println("\t\t<uploadedFile>");
			out.println("\t\t\t<fileId>" + StringEscapeUtils.escapeXml(uf.getFileId()) + "</fileId>");
			out.println("\t\t\t<fieldName>" + StringEscapeUtils.escapeXml(uf.getFieldName()) + "</fieldName>");
			out.println("\t\t\t<originalName>" + StringEscapeUtils.escapeXml(uf.getOriginalName()) + "</originalName>");
			out.println("\t\t\t<originalFullName>" + StringEscapeUtils.escapeXml(uf.getOriginalFullName()) + "</originalFullName>");
			out.println("\t\t\t<contentType>" + StringEscapeUtils.escapeXml(uf.getContentType()) + "</contentType>");
			out.println("\t\t\t<size>" + StringEscapeUtils.escapeXml(uf.getSize() + "") + "</size>");
			out.println("\t\t\t<status>" + StringEscapeUtils.escapeXml(uf.getStatus()) + "</status>");
			out.println("\t\t\t<failMessage>" + StringEscapeUtils.escapeXml(uf.getFailMessage()) + "</status>");
			out.println("\t\t</uploadedFile>");
		}
	}
	out.println("\t</uploadedFiles>");
	
	out.println("</"+JdomProcessor.ELM_RESPONSE+">");
%>