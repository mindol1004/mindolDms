<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="nexcore.framework.core.Constants"%>
<%@ page import="nexcore.framework.core.util.BaseUtils"%>
<%@ page import="nexcore.framework.core.data.user.IUserInfo"%>
<%@ page import="nexcore.framework.online.channel.util.WebUtils"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@ page import="nexcore.framework.core.*"%>
<%@ page import="nexcore.framework.core.file.*"%>
<%@ page import="nexcore.framework.core.prototype.*"%>
<%@ page import="nexcore.framework.online.channel.util.*"%>
<%@page import="nexcore.framework.core.data.IDataSet"%>
<%@page import="nexcore.framework.core.data.IRecordSet"%>
<%@page import="nexcore.framework.core.data.IRecord"%>
<%@page import="nexcore.framework.core.data.IResultMessage"%>
<%@page import="nexcore.framework.core.util.BaseUtils"%>
<%@page import="nexcore.framework.core.util.ExceptionUtil"%>
<%@page import="nexcore.framework.core.code.ICode"%>
<%@page import="fwk.utils.HpcUtils"%>
<%@page import="fwk.code.HpcCodeManager"%>
<%@page import="fwk.code.internal.HpcCode"%>
<%@page import="nexcore.framework.core.util.StringUtils"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<c:set var="transactionId" value="<%= Constants.TRANSACTION_ID %>" />
<c:set var="target" value="<%= Constants.TARGET %>" />
<%			
	String cntrNo = StringUtils.nvlObject(request.getParameter("cntrNo"), "");	 	
	String message = null;
	IResultMessage resultMessage = null;
	IDataSet responseData = WebUtils.getResultDataSet(request);	
	IRecordSet rsList = null;
	IRecord record = null;
	//결과값
 	String APPLF_SUBMIT_DTM = "";
 	String APPLF_RMK = "";
 	String AA = "";
 	String DMS253_01 = "";
 	String DMS253_02 = "";
 	String DMS253_03 = "";
 	String DMS253_04 = "";
 	String DMS253_05 = "";
 	String AB = "";
 	String DMS254_02 = "";
 	String DMS254_03 = "";
 	String DMS254_04 = "";
 	String AC = "";
 	String AC_IMGYN = "";
 	String AD = "";
 	String AD_IMGYN = "";
 	String BA = "";
 	String DMS255_01 = "";
 	String DMS255_02 = "";
 	String DMS255_03 = "";
 	String DMS255_04 = "";
 	String DMS255_05 = "";
 	String BB = "";
 	String DMS256_01 = "";
 	String DMS256_02 = "";
 	String DMS256_03 = "";
 	String DMS256_04 = "";
 	String BC = "";
 	String BC_IMGYN = "";
 	String BD = "";
 	String BD_IMGYN = "";
 	String CA = "";
 	String CA_IMGYN = "";
 	String CB = "";
 	String CB_IMGYN = "";
 	String CC = "";
 	String CC_IMGYN = "";
 	String CD = "";
 	String CD_IMGYN = "";

	if(responseData != null){  
	  //**********************************************************************************************************//
	    resultMessage = responseData.getResultMessage();
	    message = BaseUtils.getMessage(resultMessage.getMessageId(), resultMessage.getMessageParams());	    	  
	  //**********************************************************************************************************//	    
	    rsList = responseData.getRecordSet("RS_NR_APPLF_DTL");
	     if (rsList != null && rsList.getRecordCount() >0){	
	         record = rsList.getRecord(0);
	         //신청서제출일
	         APPLF_SUBMIT_DTM = StringUtils.nvlObject(record.get("APPLF_SUBMIT_DTM"), "");
	         APPLF_SUBMIT_DTM = APPLF_SUBMIT_DTM.substring(0,4) + "-" + APPLF_SUBMIT_DTM.substring(4,6) + "-" + APPLF_SUBMIT_DTM.substring(6,8);
	         
	         //비고
	         APPLF_RMK = StringUtils.nvlObject(record.get("APPLF_RMK"), "");
	      
	         //체크박스 표시여부 판단 
	         AA = StringUtils.nvlObject("Y".equals(record.get("AA"))? "checked" : "" , "");
	         DMS253_01 = StringUtils.nvlObject("Y".equals(record.get("DMS253_01"))? "checked" : "" , "");
	         DMS253_02 = StringUtils.nvlObject("Y".equals(record.get("DMS253_02"))? "checked" : "" , "");
	         DMS253_03 = StringUtils.nvlObject("Y".equals(record.get("DMS253_03"))? "checked" : "" , "");
	         DMS253_04 = StringUtils.nvlObject("Y".equals(record.get("DMS253_04"))? "checked" : "" , "");
	         DMS253_05 = StringUtils.nvlObject("Y".equals(record.get("DMS253_05"))? "checked" : "" , "");
	         AB = StringUtils.nvlObject("Y".equals(record.get("AB"))? "checked" : "" , "");
	         DMS254_02 = StringUtils.nvlObject("Y".equals(record.get("DMS254_02"))? "checked" : "" , "");
	         DMS254_03 = StringUtils.nvlObject("Y".equals(record.get("DMS254_03"))? "checked" : "" , "");
	         DMS254_04 = StringUtils.nvlObject("Y".equals(record.get("DMS254_04"))? "checked" : "" , "");
	         AC = StringUtils.nvlObject("Y".equals(record.get("AC"))? "checked" : "" , "");
	         AC_IMGYN = StringUtils.nvlObject("Y".equals(record.get("AC_IMGYN"))? "checked" : "" , "");
	         AD = StringUtils.nvlObject("Y".equals(record.get("AD"))? "checked" : "" , "");
	         AD_IMGYN = StringUtils.nvlObject("Y".equals(record.get("AD_IMGYN"))? "checked" : "" , "");
	         BA = StringUtils.nvlObject("Y".equals(record.get("BA"))? "checked" : "" , "");
	         DMS255_01 = StringUtils.nvlObject("Y".equals(record.get("DMS255_01"))? "checked" : "" , "");
	         DMS255_02 = StringUtils.nvlObject("Y".equals(record.get("DMS255_02"))? "checked" : "" , "");
	         DMS255_03 = StringUtils.nvlObject("Y".equals(record.get("DMS255_03"))? "checked" : "" , "");
	         DMS255_04 = StringUtils.nvlObject("Y".equals(record.get("DMS255_04"))? "checked" : "" , "");
	         DMS255_05 = StringUtils.nvlObject("Y".equals(record.get("DMS255_05"))? "checked" : "" , "");
	         BB = StringUtils.nvlObject("Y".equals(record.get("BB"))? "checked" : "" , "");
	         DMS256_01 = StringUtils.nvlObject("Y".equals(record.get("DMS256_01"))? "checked" : "" , "");
	         DMS256_02 = StringUtils.nvlObject("Y".equals(record.get("DMS256_02"))? "checked" : "" , "");
	         DMS256_03 = StringUtils.nvlObject("Y".equals(record.get("DMS256_03"))? "checked" : "" , "");
	         DMS256_04 = StringUtils.nvlObject("Y".equals(record.get("DMS256_04"))? "checked" : "" , "");
	         BC = StringUtils.nvlObject("Y".equals(record.get("BC"))? "checked" : "" , "");
	         BC_IMGYN = StringUtils.nvlObject("Y".equals(record.get("BC_IMGYN"))? "checked" : "" , "");
	         BD = StringUtils.nvlObject("Y".equals(record.get("BD"))? "checked" : "" , "");
	         BD_IMGYN = StringUtils.nvlObject("Y".equals(record.get("BD_IMGYN"))? "checked" : "" , "");
	         CA = StringUtils.nvlObject("Y".equals(record.get("CA"))? "checked" : "" , "");
	         CA_IMGYN = StringUtils.nvlObject("Y".equals(record.get("CA_IMGYN"))? "checked" : "" , "");
	         CB = StringUtils.nvlObject("Y".equals(record.get("CB"))? "checked" : "" , "");
	         CB_IMGYN = StringUtils.nvlObject("Y".equals(record.get("CB_IMGYN"))? "checked" : "" , "");
	         CC = StringUtils.nvlObject("Y".equals(record.get("CC"))? "checked" : "" , "");
	         CC_IMGYN = StringUtils.nvlObject("Y".equals(record.get("CC_IMGYN"))? "checked" : "" , "");
	         CD = StringUtils.nvlObject("Y".equals(record.get("CD"))? "checked" : "" , "");
	         CD_IMGYN = StringUtils.nvlObject("Y".equals(record.get("CD_IMGYN"))? "checked" : "" , "");
		}	
	} 
%>
<c:set var="title" value="U.SCAN 정보" />
<c:set var="cntrNo" value="<%=cntrNo%>" />
<!DOCTYPE html>
<html>
<head>
<title>${title}</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- libraryList include  start -->
<link href=" ${contextPath}/ext/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/ext/css/common.css" rel="stylesheet"	type="text/css" />
<!-- libraryList include  end -->
</head>
<body>
<form name="AppIfItmChkPopupForm" method="post">  
	<input type="hidden" name="${transactionId}">
	<input type="hidden" name="${target}">
	<input type="hidden" name="CNTRT_NO" value="${cntrNo}">
</form>
	<div class="container-fluid container-fluid-popup-custom">
		<div class="panel-default panel">
			<!-- Default panel contents -->
			<div class="panel-heading popup-title-text">${title}</div>

			<!-- Table -->
			<table class="table table-bordered panel-in-table ">		
				 <colgroup>
							<col width="15%" />
		            		<col width="85%" />
				</colgroup>		
				<tbody>
					<tr>
						<th scope="row" style="padding-left:20px; border-right:1px solid transparent;min-width:80px;">신청서제출일</th>
						<td style="padding-left:20px; border-left:1px solid transparent;"><%=APPLF_SUBMIT_DTM%></td>
					</tr>
					<tr>
						<th scope="row" style="padding-left:20px; border-right:1px solid transparent;min-width:80px;">비고</th>
						<td style="padding-left:20px; border-left:1px solid transparent;padding-top: 8px;padding-bottom: 0px;">
							<form>
								<textarea class="form-control"  style="width:95%;" readOnly><%=APPLF_RMK%></textarea>
							</form>
						</td>
					</tr>
				</tbody>
			</table>
			
			<!-- Table -->
			<table class="table table-bordered panel-in-table panel-in-table-left-border table-td-padding-1px">
				<colgroup>
						<col width="20%" />
						<col width="10%" />
	            		<col width="70%" />
				</colgroup>		
				<thead>
					<tr>
						<th style="text-align: center;">종류</th>
						<th style="text-align: center;">PAGE</th>
						<th style="text-align: center;">체크박스 부여 대상</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row" rowspan="11" style="text-align: center;vertical-align: middle;">이용계약서</th>
						<td rowspan="5" style="text-align: center;vertical-align: middle;">							 
						        <input type="checkbox" name= "AA"  <%=AA%>  ></input>
						        <span>1</span>
						</td>
						<td style="padding-left:20px;">
						        <input type="checkbox" name= "DMS253_01"  <%=DMS253_01%> ></input>
						        <span>가입신청고객 정보</span>		
						</td>
					</tr>
					<tr>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "DMS253_02"  <%=DMS253_02%> ></input>
							<span>위임대리인 정보</span>
						</td>
					</tr>
					<tr>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "DMS253_03"  <%=DMS253_03%> ></input>
							<span>법정대리인 정보</span>
						</td>
					</tr>
					<tr>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "DMS253_04"  <%=DMS253_04%> ></input>
							<span>체인지업 단말정보</span>
						</td>
					</tr>
					<tr>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "DMS253_05"  <%=DMS253_05%> ></input>
							<span>약관 및 계약 중요내용 설명</span>
						</td>
					</tr>
					<tr>
						<td rowspan="4"  style="text-align: center;vertical-align: middle;">
							<input type="checkbox" name= "AB"  <%=AB%> ></input>
							<span> 2</span>
						</td>
						<td style="padding-left:20px;"> 
							<input type="checkbox" name= "DMS253_03"  <%=DMS253_03%> ></input>
							<span>개인정보 수집, 이용동의(필수동의)</span>
					    </td>
					</tr>
					<tr>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "DMS254_02"  <%=DMS254_02%> ></input>
							<span>서비스 제공을 위한 개인정보 위탁 동의(필수동의) <br />
							   &nbsp;&nbsp;&nbsp;필수적인 제공에 관한 동의</span>
						</td>
					</tr>
					<tr>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "DMS254_03"  <%=DMS254_03%> ></input>
							<span>개인신용정보 조회 동의(필수동의)</span>
						</td>
					</tr>
					<tr>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "DMS254_04"  <%=DMS254_04%> ></input>
							<span>서비스 제공을 위한 개인정보 제3자 제공 동의(필수동의)</span>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;vertical-align: middle;">
							<input type="checkbox" name= "AC"  <%=AC%> ></input>
							<span> 3</span>
						</td>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "AC_IMGYN"  <%=AC_IMGYN%> ></input>
							<span> 오류여부</span>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;vertical-align: middle;">
							<input type="checkbox" name= "AD"  <%=AD%> ></input>
							<span> 4</span>
						</td>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "AD_IMGYN"  <%=AD_IMGYN%> ></input>
							<span>오류여부</span>
						</td>
					</tr>
					<tr>
						<th scope="row" rowspan="11" style="text-align: center;vertical-align: middle;">명의변경 계약서</th>
						<td rowspan="5" style="text-align: center;vertical-align: middle;">
							<input type="checkbox" name= "BA"  <%=BA%> ></input>
							<span> 1</span>
						</td>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "DMS255_01"  <%=DMS255_01%> ></input>
							<span>명의변경 정보</span>
						</td>
					</tr>
					<tr>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "DMS255_02"  <%=DMS255_02%> ></input>
							<span>법정대리인 정보</span>
						</td>
					</tr>
					<tr>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "DMS255_03"  <%=DMS255_03%> ></input>
							<span>위임대리인 정보(받는 분)</span>
						</td>
					</tr>
					<tr>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "DMS255_04"  <%=DMS255_04%> ></input>
							<span>체인지업 단말정보</span>
						</td>
					</tr>
					<tr>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "DMS255_05"  <%=DMS255_05%> ></input>
							<span>약관 및 계약 중요내용 설명</span>
						</td>
					</tr> 
					<tr>
						<td rowspan="4" style="text-align: center;vertical-align: middle;">
							<input type="checkbox" name= "BB"  <%=BB%> ></input>
							<span>2</span>
						</td>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "DMS256_01"  <%=DMS256_01%> ></input>
							<span>개인정보 수집, 이용동의(필수동의)</span>
						</td>
					</tr>
					<tr>
						
						<td style="padding-left:20px;">
							<input type="checkbox" name= "DMS256_02"  <%=DMS256_02%> ></input>
							<span>서비스 제공을 위한 개인정보 위탁 동의(필수동의) <br />
							          &nbsp;&nbsp;&nbsp;필수적인 제공에 관한 동의</span>
					    </td>
					</tr>
					<tr>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "DMS256_03"  <%=DMS256_03%> ></input>
							<span>개인신용정보 조회 동의(필수동의)</span>
						</td>
					</tr>
					<tr>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "DMS256_04"  <%=DMS256_04%> ></input>
							<span>서비스 제공을 위한 개인정보 제3자 제공 동의(필수동의)</span>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">
							<input type="checkbox" name= "BC"  <%=BC%> ></input>
							<span>3</span>
						</td>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "BC_IMGYN"  <%=BC_IMGYN%> ></input>
							<span>오류여부</span>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">
							<input type="checkbox" name= "BD"  <%=BD%> ></input>
							<span>4</span>
						</td>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "BD_IMGYN"  <%=BD_IMGYN%> ></input>
							<span>오류여부</span>
						</td>
					</tr>
					<tr>
						<th scope="row" rowspan="4" style="text-align: center;vertical-align: middle;">구비서류</th>
						<td style="text-align: center;">
							<input type="checkbox" name= "CA"  <%=CA%> ></input>
							<span>1</span>
						</td>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "CA_IMGYN"  <%=CA_IMGYN%> ></input>
							<span>오류여부</span>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">
							<input type="checkbox" name= "CB"  <%=CB%> ></input>
							<span>2</span>
						</td>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "CB_IMGYN"  <%=CB_IMGYN%> ></input>
							<span>오류여부</span>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">
							<input type="checkbox" name= "CC"  <%=CC%> ></input>
							<span>3</span>
						</td>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "CC_IMGYN"  <%=CC_IMGYN%> ></input>
							 <span>오류여부</span>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">
							<input type="checkbox" name= "CD"  <%=CD%> ></input>
							<span>4</span>
						</td>
						<td style="padding-left:20px;">
							<input type="checkbox" name= "CD_IMGYN"  <%=CD_IMGYN%> ></input>
							<span>오류여부</span>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="popup-close-button-div">
				<button class="btn btn-default"  onclick="fn_close()">Close</button>
			</div>
		</div>
	</div>	
	<script type="text/javascript" src="${contextPath}/ext/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="${contextPath}/ext/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="${contextPath}/ext/js/common.js"></script>
	<script>	
		$(document).ready(function() {
			//$('body').css("overflow", "hidden");		
		<%		
			if(responseData != null){  
			    out.println("checkSessionAfterService('"+resultMessage.getMessageId()+"','"+message+"','popup');");
			} else {
			    //공지사항내용조회
			    out.println("goSearch();"); 
			}
		 	/* 
		    if(rsList == null){
		 	  	//공지사항내용조회
			    out.println("goSearch();"); 
			 }   */
		%>
			//체크박스 및 textarea 클릭시 값변경 금지 
			$(":checkbox").on('click', function(event){
				return false;
			});
		});			
		
		//공지팝업조회
		function goSearch(){ 		
			document.AppIfItmChkPopupForm.${transactionId}.value="PNRLoanMgmt_pInqApplfChkLstDtl";
			document.AppIfItmChkPopupForm.${target}.value="forward:jsp/popup/AppIfItmChkPopup.jsp";		
			document.AppIfItmChkPopupForm.target = "_self";
			document.AppIfItmChkPopupForm.action = "${contextPath}/standard.cmd";
			document.AppIfItmChkPopupForm.submit();
		}
		
		//닫기 이벤트
		function fn_close(){
			this.close();
		} 
	</script>   
</body>
</html>