/*----------------------------------------------------------------------------------------------
 * desc   : gfn_check
 * param   : 1. Array [ String 체크항목ID] 
 * return  : Boolean
-----------------------------------------------------------------------------------------------*/
function gfn_check(typeArray,alertDivId){
	var sTypeArray = typeArray;
	var sType = "";	
	var obj = "";	
	var sTypeNm  = "";	
	var alertTags;
	for(var i=0, len=sTypeArray.length; i<len; i++){
		sType = sTypeArray[i];	
		obj =  $("input[name='"+sType+"']");
		sTypeNm =obj.attr("alt");
		
		if (!obj.val()) {
			gfn_alert(sTypeNm+"를 입력하세요.",alertDivId);
			obj.focus();		 
			return false;
		} 
	}	
	return true;
}
/*----------------------------------------------------------------------------------------------
 * desc   : gfn_alert
 * param   : 1. String msg
 * 				2. Stirng alertDivId - target alert div Id  
 * return  : void
-----------------------------------------------------------------------------------------------*/
function gfn_alert(msg,alertDivId){
	var alertTags = msg;
	var showId;
	
	if(gfn_isNull(alertDivId)){
		showId = ".alertSearchZone";
	} else {
		showId = "#"+alertDivId;
	}
	
/*	$('#alertZone').empty();
	$('#alertZone').append(alertTags);	
	$('#alertZone').fadeIn();
	setTimeout(function(){ $('#alertZone').fadeOut(); }, 3000);		*/
	
	$(showId).attr("data-content", msg);
	$(showId).popover('show');
	setTimeout(function(){ $(showId).popover('hide'); }, 2000);		
}

/*----------------------------------------------------------------------------------------------
 * desc   : 날짜, 기간 유효성검사
 * param : 1. Object obj				
 *               1) alertContent (null값 일때 alert내용) 
 *               2) startDate (시작일 or 단일날짜)
 *               3) endDate (종료일)
 *               4) startDateName (시작일 or 단일날짜의 focus 대상 input Name)
 *               5) endDateName (종료일의 focus 대상 input Name)
 *               6) mode (1: 단일날짜 체크 / 2: 단일 월 체크 / 3: 일자 기간체크 / 4 : 월 기간체크 )
 * return : void
-----------------------------------------------------------------------------------------------*/
function gfn_checkPeriod(obj){
	var sAlertContent = obj.alertContent; 
	var sStartDate = obj.startDate.replace("-",""); 
	var sEndDate = obj.endDate.replace("-","");
	var sStartDateName = obj.startDateName;
	var sEndDateName = obj.endDateName;
	var iMode = obj.mode;
	
	if(iMode < 3){
		if(!_checkDate(sStartDate,sStartDateName,sAlertContent,iMode))
			return false;
	} else {
		//시작일
		if(!_checkDate(sStartDate,sStartDateName,sAlertContent,iMode))		
			return false;
		//종료일
		if(!_checkDate(sEndDate,sStartDateName,sAlertContent,iMode))
			return false;		
		
		//기간체크
		if(sStartDate > sEndDate) {
			_alertInput(sStartDate,"시작일이 종료일보다 큽니다.");
			return false;		
		}
	}
	
	return true;
}
/*----------------------------------------------------------------------------------------------
 * desc   : 날짜 값 유효성체크
 * return : Boolean
-----------------------------------------------------------------------------------------------*/
function _checkDate(date,dateName,alertContent,mode){
	var sDate = date;
	var sDateName = dateName;
	var sAlertContent = alertContent;
	//1.null체크
	if(gfn_isNull(sDate)){
		_alertInput(sDateName,sAlertContent);
		return false;
	}
	//2.length체크
	if(mode%2 ==1){
		//일자 
		if(sDate.length != 8){
			_alertInput(sDateName,sAlertContent);
			return false;
		} 
		if(!/^[0-9]{4}(0+[1-9]|1+[012])(0[1-9]|[12][0-9]|3[0-1])$/.test(sDate)){
			_alertInput(sDateName,sAlertContent);
			return false;
		}
	} else {
		//월
		if(sDate.length != 6){
			_alertInput(sDateName,sAlertContent);
			return false;
		}
		//형식
		if(!/^[0-9]{4}(0+[1-9]|1+[012])$/.test(sDate)){
			_alertInput(sDateName,sAlertContent);
			return false;
		}		
	}
	return true;
}
/*----------------------------------------------------------------------------------------------
 * desc   : input focus 및 div alert
 * return : void
-----------------------------------------------------------------------------------------------*/
function _alertInput(sDateName,sAlertContent){
	$("input[name='"+sDateName+"']").focus();
	gfn_alert(sAlertContent);
}

/*----------------------------------------------------------------------------------------------
 * desc   : 값이 빈값인지 체크한다.
 * param : 1. String sValue
 * return : Boolean
-----------------------------------------------------------------------------------------------*/
function gfn_isNull(sValue){
	if(new String(sValue).valueOf() == "undefined" || new String(sValue).valueOf() == "[Undefined]") return true;
	if(sValue == null) return true;
	if(("x"+sValue == "xNaN") && (String(sValue.length).valueOf() == "undefined"))
		return true;
	if(String(sValue).length == 0) return true;
  
    //Trim 기능 추가  
    var sArg = new String(sValue);
	if (sArg.replace(/(^\s*)|(\s*$)/g, "").length == 0 ) return true;
	
 	return false;
}
/*----------------------------------------------------------------------------------------------
 * desc   : 세션을 체크한다.
 * param : 1. String messageId
 *            2. String message
 *            3. String type(popup 인 경우 해당팝업 종료후 로그인페이지로 이동 
 * return : void
-----------------------------------------------------------------------------------------------*/
function checkSessionAfterService(messageId,message,type){
	if(messageId=="DMS00017"){
	   //세션종료시 로그인페이지로 포워딩
	   alert(message);
	   
	   if(type=="popup"){
		   this.close();
		   window.opener.location.href='jsp/login.jsp';
	   } else {
		   location.href='jsp/login.jsp';
	   }	   
	} else if(messageId == "DMS00009"){ 
		//조회 후 시스템오류 메세지발생시 종료
		 alert(message);
		 if(type=="popup"){
		   this.close();
		 }	 	      
	} else if(messageId == "DMS00157"){ 
		//조회 후 웹에서 접근 불가능한 게시물 필터링
		 alert(message);
		 if(type=="popup"){
		   this.close();
		 }	 	      
	}			
}
