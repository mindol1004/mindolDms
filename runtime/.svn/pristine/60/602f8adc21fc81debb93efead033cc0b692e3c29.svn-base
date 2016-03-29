<%@ page contentType="text/json; charset=UTF-8" pageEncoding="UTF-8" 
%><%@page import="java.util.*"
%><%@page import="nexcore.framework.core.data.*"
%><%@page import="nexcore.framework.core.data.json.*"
%><%@page import="nexcore.framework.core.file.*"
%><%@page import="nexcore.framework.core.util.*"
%><%@page import="nexcore.framework.online.channel.util.*"
%><%@page import="com.fasterxml.jackson.databind.node.*"
%><%
	ITransaction transaction = WebUtils.getResultTransaction(request);
	List<UploadedFileReference> uploadedFiles = WebUtils.getResultUploadedFileReferences(request);
	IDataSet resultData = WebUtils.getResultDataSet(request);
	Locale locale = WebUtils.getLocale(pageContext);

    // print 
    ObjectNode transactionJson = new ObjectNode(JsonNodeFactory.instance);
    transactionJson.put(JacksonProcessor.ELM_TX_ID, transaction == null ? "" : transaction.getTxId() == null ? "" : transaction.getTxId());
    
    ArrayNode uploadedFilesJson = new ArrayNode(JsonNodeFactory.instance);
    if(uploadedFiles != null){
		for(UploadedFileReference uf : uploadedFiles){
			ObjectNode uploadedFileJson = new ObjectNode(JsonNodeFactory.instance);
			uploadedFileJson.put("fileId", uf.getFileId());
			uploadedFileJson.put("fieldName", uf.getFieldName());
			uploadedFileJson.put("originalName", uf.getOriginalName());
			uploadedFileJson.put("originalFullName", uf.getOriginalFullName());
			uploadedFileJson.put("contentType", uf.getContentType());
			uploadedFileJson.put("size", uf.getSize());
			uploadedFileJson.put("status", uf.getStatus());
			uploadedFileJson.put("failMessage", uf.getFailMessage());
			
			uploadedFilesJson.add(uploadedFileJson);
		}
    }
    
    ObjectNode responseJson = new ObjectNode(JsonNodeFactory.instance);
    responseJson.set(JacksonProcessor.ELM_TX, transactionJson);
    responseJson.set("uploadedFiles", uploadedFilesJson);
    
    out.println(responseJson.toString());
%>