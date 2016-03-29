<%@ page contentType="text/json; charset=UTF-8" pageEncoding="UTF-8" 
%><%@page import="java.util.*"
%><%@page import="nexcore.framework.core.data.*"
%><%@page import="nexcore.framework.core.data.json.*"
%><%@page import="nexcore.framework.core.file.*"
%><%@page import="nexcore.framework.core.util.*"
%><%@page import="nexcore.framework.online.channel.util.*"
%><%@page import="net.sf.json.util.JSONUtils"
%><%@page import="com.tobesoft.xplatform.data.PlatformData"
%><%@page import="com.tobesoft.xplatform.data.VariableList"
%><%@page import="com.tobesoft.xplatform.tx.HttpPlatformResponse"
%><%@page import="com.tobesoft.xplatform.tx.PlatformType"
%><%@page import="com.tobesoft.xplatform.data.DataTypes"
%><%@page import="com.tobesoft.xplatform.data.DataSet"
%><%@page import="nexcore.framework.online.channel.core.RenderException"
%><%@page import="org.apache.commons.logging.Log"
%><%@page import="nexcore.framework.core.log.LogManager"
%><%@page import="fwk.constants.DmsConstants"
%>

<%ITransaction transaction = WebUtils.getResultTransaction(request);
	Log logger = LogManager.getFwkLog();
	List<UploadedFileReference> uploadedFiles = WebUtils.getResultUploadedFileReferences(request);
	PlatformData xpData = new PlatformData();
	VariableList xpVars = xpData.getVariableList();
	DataSet xpDataset = null;
	final String[] fileInfoColumn = new String[]{DmsConstants.NC_UPLOADED_FILE_ID , DmsConstants.NC_FIELD_NAME,
	    																		DmsConstants.NC_UPLOADED_FILE_NAME, DmsConstants.NC_UPLOADED_FILE_FULL_NAME,
	    																		DmsConstants.CONTENT_TYPE, DmsConstants.SIZE, DmsConstants.STATUS};
	
    if(uploadedFiles != null){
    	xpDataset = new DataSet("ncFieldMap");
		int len = uploadedFiles.size(); 
		//컬럼만들기
		for(int i=0; i<fileInfoColumn.length; i++) {
			xpDataset.addColumn(fileInfoColumn[i], DataTypes.STRING, 255);
		}
		
		int no = 0;
		for(int i=0; i<len; i++){
			no = xpDataset.newRow();
			UploadedFileReference uf = uploadedFiles.get(i);
			xpDataset.set(no, fileInfoColumn[0], uf.getFileId());
			xpDataset.set(no, fileInfoColumn[1], uf.getFieldName());
			xpDataset.set(no, fileInfoColumn[2], uf.getOriginalName());
			xpDataset.set(no, fileInfoColumn[3], uf.getOriginalFullName());
			xpDataset.set(no, fileInfoColumn[4], uf.getContentType());
			xpDataset.set(no, fileInfoColumn[5], uf.getSize());
			xpDataset.set(no, fileInfoColumn[6], uf.getStatus());
		}
		xpData.addDataSet(xpDataset);
		
		try{
			HttpPlatformResponse xpResp = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML, PlatformType.DEFAULT_CHAR_SET);
			//xpResp.addProtocolType(PlatformType.PROTOCOL_TYPE_ZLIB);
			xpResp.setData(xpData);
			xpResp.sendData();
		} catch(Exception ex){
			logger.error("Exception at writing and sending response data", ex);
			throw new RenderException("SKFS1011", new String[] {ex.getLocalizedMessage()}, ex);
		}
	}

%>