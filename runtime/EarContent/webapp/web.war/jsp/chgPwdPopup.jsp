<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="nexcore.framework.core.Constants"%>
<%@ page import="nexcore.framework.core.util.BaseUtils"%>
<%@ page import="nexcore.framework.core.data.user.IUserInfo"%>
<%@ page import="nexcore.framework.online.channel.util.WebUtils"%>
<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%@page import="nexcore.framework.core.data.IDataSet" %>
<%@page import="nexcore.framework.core.data.IRecordSet" %>
<%@page import="nexcore.framework.core.data.IRecord" %>
<%@page import="nexcore.framework.core.data.IResultMessage" %>
<%@page import="nexcore.framework.core.util.BaseUtils" %>
<%@page import="nexcore.framework.core.util.ExceptionUtil" %>
<%@page import="nexcore.framework.core.code.ICode"%>
<%@page import="fwk.utils.HpcUtils"%>
<%@page import="fwk.code.HpcCodeManager"%>
<%@page import="fwk.code.internal.HpcCode"%>
<%@page import="nexcore.framework.core.util.StringUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="transactionId" value="<%= Constants.TRANSACTION_ID %>" />
<c:set var="target" value="<%= Constants.TARGET %>" />
<!DOCTYPE html>
<html>
<head>
	<title>비밀번호변경</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" >
	<!-- libraryList include  start -->
	<link href=" ${contextPath}/ext/css/bootstrap.css" rel="stylesheet" type="text/css"/>
	<link href="${contextPath}/ext/css/common.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${contextPath}/ext/js/jquery-1.9.1.js"></script>		
	<script type="text/javascript" src="${contextPath}/ext/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${contextPath}/ext/js/common.js"></script>
	<!-- libraryList include  end -->
<%
	IUserInfo userInfo = (IUserInfo)WebUtils.getSessionUserInfo(request);
	String  loginId = "";
	if (userInfo != null) {
	    loginId = StringUtils.nvlObject(userInfo.get("loginId").toString(), "");
	} 
%>
</head>
<body>
<div class="popupDiv">
<form class="form-inline" name="pwdForm" method="post">
	 <input type="hidden" name="${transactionId}">
	<input type="hidden" name="${target}">	
	<input type="hidden" name="USER_NO">	
	<div class="popupWrapDiv well">
		<div class="form-group form-group-custom">
	    <label  class="formLabelStyle formFont"  for="login id">로그인ID</label>
	    <input type="text" class="form-control"  id="LGIN_ID"  name="LGIN_ID"  alt="로그인ID" required>
	  </div>
	  <div class="form-group form-group-custom">
	    <label class="formLabelStyle formFont"  for="old password">현재 비밀번호</label>
	    <input type="password" class="form-control" id="PWD_ORG" name="PWD_ORG" alt="현재 비밀번호"  required>
	  </div>
	  <div class="form-group form-group-custom">
	    <label class="formLabelStyle formFont" for="new password">새 비밀번호</label>
	    <input type="password" class="form-control" id="PWD_NEW" name="PWD_NEW"  alt="새 비밀번호"  required>
	  </div>
	  <div class="form-group form-group-custom">
	    <label class="formLabelStyle formFont"  for="new password for check">새 비밀번호확인</label>
	    <input type="password" class="form-control" id="PWD_CHK" name="PWD_CHK" alt="확인용 새 비밀번호"   required>
	  </div>
	  <div id="alertZoneWrap" class="form-group form-group-custom" >
	  	 <div class="alertSearchZone"  title="Warning!"  data-placement="bottom" data-toggle="popover" data-content="" ></div>
	  </div>
	  <div class="formFont formInfoDiv">
	    <p>비밀번호 생성 규칙 : 로그인ID 포함 불가.</p>
	    <p>영문, 숫자, 특수문자 포함 8 ~ 20 자리.</p>
	     <p>3자리이상 동일 문자나 연속되는 문자 사용 불가. </p>
	     <p>비밀번호 변경주기 : 3개월</p>
	  </div>
	  
	</div>
  	<div class="btnDiv" >
      <input type="button" value="확인" alt="submit" onClick="fn_chkPwd()" class="btn btn-success  btn-xs" />
      <input type="button" value="취소" onclick="fn_close()" class="btn btn-default  btn-xs" />
    </div>
</form>
</div>
<%
	//get data
	IDataSet responseData = WebUtils.getResultDataSet(request);
	if(responseData != null){
		IResultMessage resultMessage = responseData.getResultMessage();
		String Message = BaseUtils.getMessage(resultMessage.getMessageId(), resultMessage.getMessageParams());
		//메세지출력
		out.println( "<script>");
		out.println( "gfn_alert('"+Message+"');");
		//비밀번호 변경 성공여부 확인 
		if(resultMessage.getStatus() != IResultMessage.FAIL){  		  
		    out.println( "this.close()");
		} 
		out.println( "</script>"); 		    
	}	
%>
<script>
	$(document).ready(function() {
		var loginId = "<%=loginId%>";
		var loginForm = opener.document.loginForm;
		if(gfn_isNull(loginId) && !gfn_isNull(loginForm)){
			loginId = loginForm.USER_ID.value;
		} 
		document.pwdForm.LGIN_ID.value = loginId;	
				
		if(!gfn_isNull(document.pwdForm.LGIN_ID.value)){
			$("#PWD_ORG").focus();
		} else {
			$("#LGIN_ID").focus();
		}
		
	});
	//확인버튼 클릭시 이벤트
	function fn_chkPwd(){
		if(!gfn_check(["LGIN_ID","PWD_ORG","PWD_NEW","PWD_CHK"])){
			return false;	
		}			
		if ($("input[name='PWD_NEW']").val() != $("input[name='PWD_CHK']").val()) {
			gfn_alert("입력하신 비밀번호가 일치하지않습니다.");
			$("input[name='PWD_NEW']").val("");
			$("input[name='PWD_CHK']").val("");
			$("input[name='PWD_NEW']").focus();
			return false;
		}
		
		if ($("input[name='PWD_NEW']").val().indexOf($("input[name='LGIN_ID']").val())!=-1) {
			gfn_alert("로그인ID 는 비밀번호에 포함될 수 없습니다.");
			$("input[name='PWD_NEW']").val("");
			$("input[name='PWD_CHK']").val("");
			$("input[name='PWD_NEW']").focus();
			return false;
		} 
		
		//입력값 trim 처리 
		document.pwdForm.PWD_ORG.value = document.pwdForm.PWD_ORG.value.trim();
		document.pwdForm.PWD_NEW.value = document.pwdForm.PWD_NEW.value.trim();
		document.pwdForm.PWD_CHK.value = document.pwdForm.PWD_CHK.value.trim();
		
		//비밀번호 변경 서비스 호출
		fn_chgPwd();
		
	}
	//비밀번호 변경 서비스 호출
	function fn_chgPwd(){				
		document.pwdForm.${transactionId}.value="PSCUserMgmt_pChgPwd";
		document.pwdForm.${target}.value="forward:jsp/chgPwdPopup.jsp";
		document.pwdForm.target = "_self";
		document.pwdForm.action = "${contextPath}/login.cmd";
		document.pwdForm.submit();
	}
	
	//취소버튼 클릭시 이벤트
	function fn_close(){
		this.close();
	}	

</script>
    
</body>
</html>