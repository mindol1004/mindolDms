<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="pageName" value="지급금액 조회" />	
	<!-- top file include  start -->
	<%@ include file="include/top.jsp" %>	
	<!-- top page include  end -->	
<%		
	//조회조건값  
	String _PAY_FR_YM = request.getParameter("PAY_FR_YM_PARAM");  //조회시작년월
	String _PAY_TO_TM = request.getParameter("PAY_TO_YM_PARAM"); //조회종료년월
	String _PAY_YM_TS = request.getParameter("PAY_YM_TS"); 			 //지급차수
			
	if(StringUtils.isEmpty(_PAY_FR_YM)){
	 	 _PAY_FR_YM = todate;
	}	
	
	if(StringUtils.isEmpty(_PAY_TO_TM)){
	    _PAY_TO_TM = todate;
	}
	//지급차수 공통코드 리스트
	List<HpcCode> codelist = HpcUtils.getCodes("DMS119");
	Iterator<HpcCode> _codelist = codelist.iterator();
	
	//리스트 결과 
	IDataSet responseData = WebUtils.getResultDataSet(request);	
	IResultMessage resultMessage = null;
	IRecordSet rsList = null;
	
	String message = null;
	String sNoData = "";
	int _recordCnt = 0; //데이터건수
	int _totalCnt = 0;	//총건수
	
	if(responseData != null){
	    //**********************************************************************************************************//
	    sNoData = sNoDataMessage;
	    resultMessage = responseData.getResultMessage();
	    message = BaseUtils.getMessage(resultMessage.getMessageId(), resultMessage.getMessageParams());	    
	    //세션체크
	    out.println("<script>");
	    out.println("checkSessionAfterService('"+resultMessage.getMessageId()+"','"+message+"');");
	    out.println( "</script>");
	  //**********************************************************************************************************//
	    if (responseData.getRecordSet("RS_SKN_PG") != null){
		    rsList = responseData.getRecordSet("RS_SKN_PG");
		    _recordCnt = rsList.getRecordCount();
		    _totalCnt = rsList.getTotalRecordCount(); //총건수		    
		}
	} 
%>		
	<!-- content start -->
	<div id="contentDiv"> 	
		<div id ="contentWrapDiv"> 
			<div id ="titleDiv" >
				<p>${pageName}</p>
			</div>
			<div id="searchDiv"  > 
				<div class="searchFormDiv">
					<form name="MyForm" method="post" class="float-left">  
						  <input type="hidden" name="${transactionId}">
						  <input type="hidden" name="${target}">
						  <input type="hidden" name="PAY_FR_YM"  value="">
						  <input type="hidden" name="PAY_TO_YM"  value="">
						  <input type="hidden" name="AGN_CD"  value="${agnOrgCd}">
						  <input type="hidden" name="DEALCO_CD"  value="">						  
						  <input type="hidden" name="page" value="<%=_page%>"> 
						  <input type="hidden" name="page_size" value="<%=_page_size%>">
  
		  				 <div class="formGroup">
			          		<div class="formGroupLabel"><label class="formFontStyle"  for="기준년월">기준년월</label></div>
			          		<div class="input-append date"  id="fromDt" data-date="" data-date-format="yyyy-mm" data-date-viewmode="months" data-date-minviewmode="months">
									<input type="text"  name="PAY_FR_YM_PARAM" class="formFontStyle input-width-100 enterKeyupTarget"  value="<%=_PAY_FR_YM %>"  >
									<span class="add-on"><i class="icon-calendar"></i></span>
						 	</div>
						 	<div>~</div>
			          		<div class="input-append date"  id="toDt" data-date="" data-date-format="yyyy-mm" data-date-viewmode="months" data-date-minviewmode="months">
									<input type="text"  name="PAY_TO_YM_PARAM"   class="input-sm formFontStyle input-width-100 enterKeyupTarget"  value="<%=_PAY_TO_TM %>"  >
									<span class="add-on"><i class="icon-calendar"></i></span>
						 	 </div>
	          			</div> 
	          		<div class="formGroup">	
						<div class="formGroupLabel"><label class="formFontStyle"  for="지급차수">지급차수</label></div>
						<div class="input-append">
          					<select name="PAY_YM_TS"  class="formFontStyle input-width-150" >
          						<option value="%">전체</option>
							<%
							while (_codelist.hasNext()) {
								HpcCode c = (HpcCode) _codelist.next();
							%>
								<option value="<%=c.getCodeId() %>" <%if(c.getCodeId().equals(_PAY_YM_TS)) {%>selected<% } %>><%=c.getCodeNm() %></option>
							<%
							}
							%>
							</select>        
						</div>
					</div>  		
					<div class="formGroup float-left  alertSearchZone"  title="Warning!"  data-placement="right" data-toggle="popover" data-content="" ></div> 
					</form> 	
					<div class="searchBtnDiv btn-group float-right" >
					      <button type="button" onclick="formEmpty();" class="btn btn-default  btn-sm" >초기화</button>
					      <button type="button" class="btn btn-success  btn-sm"  onclick="fn_beforeSearch();" >검색</button>
				    </div>		
		      </div>	
			</div>
			<div id ="listDiv" >
				<!-- 대리점정보  -->
				<div id="DealInfoDiv"> 
					<table class="table table-bordered table-striped listTable">
						<colgroup>
							<col width="10%" />
							<col width="10%" />
							<col width="10%" />
							<col width="20%" />
							<col width="10%" />
							<col width="15%" />
							<col width="10%" />
							<col width="15%" />
						</colgroup>
						<thead>
							<tr style="background-color:#f9f9f9;">
								<th style="text-align: center;" >대리점코드</th>
								<th style="text-align: center;font-weight:normal;">${dealCoCd}</th>
								<th style="text-align: center;">대리점명</th> 
								<th style="text-align: center;font-weight:normal;">${dealCoNm}</th>
								<th style="text-align: center;" >대표자명</th>
								<th style="text-align: center;font-weight:normal;">${dealCoRepveNm}</th>
								<th style="text-align: center;">사업자번호</th>
								<th style="text-align: center;font-weight:normal;">${dealCoBlicensNo}</th>
							</tr>
						</thead>
					</table>
				</div>
				<div id="listTitleDiv">
					<p class="listTitleP float-left">총 <%=_totalCnt %> 건</p> 
					<p class="float-right listTitleInfo" >*부가세(V.A.T) 포함가<!--  <a href="javascript:void(0);"><img src="/ext/img/btn_excel_download.gif" align="absmiddle"/></a> --></p>
				</div>
				<div id="listTableDiv">
					<table class="listTable table table-bordered table-hover table-striped">
						<colgroup>
							<col width="2%" />
		            		<col width="5%" />
		            		<col width="5%" />
		            		<col width="5%" />
		            		<col width="5%" />
		            		<col width="5%" />
		            		<col width="5%" />
		            		<col width="5%" />
		            		<col width="5%" />
		            		<col width="5%" />
		            		<col width="5%" />
		            		<col width="5%" />
		            		<col width="5%" />
		            		<col width="5%" />
		            		<col width="5%" />
		            		<col width="6%" />
		            		<col width="6%" />
		            		<col width="5%" />
		            		<col width="6%" />
						</colgroup>
						<thead>
							<tr>
								<th style="text-align: center;" rowspan="2">NO.</th>
		            			<th style="text-align: center;" rowspan="2">기준년월</th>
		            			<th  style="text-align: center;" rowspan="2">지급차수</th>
		            			<th  style="text-align: center;" colspan="4">실적( A )</th>
		            			<th  style="text-align: center;" colspan="4">환수( B )</th>
		            			<th  style="text-align: center;" colspan="4">계( C = A - B )</th>
		            			<th  style="text-align: center;" rowspan="2">SKN개통여신</br>정산금액( D )</th>
		            			<th  style="text-align: center;" rowspan="2">대리점 실지급금</br>( C - D )</th>
		            			<th  style="text-align: center;" rowspan="2">지급은행명</th> 
		            			<th  style="text-align: center;" rowspan="2">지급계좌</th>
							</tr>
							<tr>
								<th style="text-align: center;">건수</th> <!-- 실적( A ) -->
								<th style="text-align: center;">매입가</th>
								<th style="text-align: center;">부가세</th>
								<th style="text-align: center;">금액</th>
								<th style="text-align: center;">건수</th> <!-- 환수( B ) -->
								<th style="text-align: center;">매입가</th>
								<th style="text-align: center;">부가세</th>
								<th style="text-align: center;">금액</th>
								<th style="text-align: center;">건수</th> <!-- 계( C = A - B ) -->
								<th style="text-align: center;">할부원금</th>
								<th style="text-align: center;">부가세</th>
								<th style="text-align: center;">금액</th>
		         			</tr>
						</thead>
						<tbody>
						<%
							if(_recordCnt==0){
						 %>	    
						 	<tr>
								<td colspan="19" ><p class="text-center table-noDataInfo"><%=sNoData%></p></td>								
							</tr>
						<% } else {
						    	
						    	String payYm = "";
						    	String payYmTsNm = "";
						    	for(int i=0; i<_recordCnt; i++){
									IRecord record = rsList.getRecord(i);
									 //기준년월
									payYm = StringUtils.nvlObject(record.get("PAY_YM"), "");
									if(StringUtils.isNotEmpty(payYm)){
									    payYm = payYm.substring(0,4) + "-" + payYm.substring(4,6);
									}
									//지급차수 코드명 
									payYmTsNm = StringUtils.nvlObject(record.get("PAY_YM_TS"), "");
									_codelist = codelist.iterator(); //iterator 초기화
									while (_codelist.hasNext()) {
										HpcCode c = (HpcCode) _codelist.next();
										if(c.getCodeId().equals(payYmTsNm)){
										    payYmTsNm = c.getCodeNm();
										}
									}
						%>
							<tr>
								<td style="text-align: center;"><%=record.get("ROWNO")%></td>	
		            			<td style="text-align: center;"><%=payYm%></td>
		            			<td style="text-align: center;"><%=payYmTsNm%></td>
		            			<td style="text-align: center;"><%=StringUtils.nvlObject(record.get("PRCH_CNT"), "")%></td>
		            			<td style="text-align: center;"><%=df.format(Integer.parseInt(record.get("SPLY_PRC")))%></td> 
		            			<td style="text-align: center;"><%=df.format(Integer.parseInt(record.get("SURTAX_AMT")))%></td>
		            			<td style="text-align: center;"><%=df.format(Integer.parseInt(record.get("SUM_AGN_CRD")))%></td>
		            			<td style="text-align: center;"><%=StringUtils.nvlObject(record.get("CNT"), "")%></td>
		            			<td style="text-align: center;"><%=df.format(Integer.parseInt(record.get("SPLY")))%></td>
		            			<td style="text-align: center;"><%=df.format(Integer.parseInt(record.get("SURTAX")))%></td>
		            			<td style="text-align: center;"><%=df.format(Integer.parseInt(record.get("SUM_REDEMPTION")))%></td>
		            			<td style="text-align: center;"><%=StringUtils.nvlObject(record.get("SUB_CNT"), "")%></td>
		            			<td style="text-align: center;"><%=df.format(Integer.parseInt(record.get("SUB_SPLY_PRC")))%></td>
		            			<td style="text-align: center;"><%=df.format(Integer.parseInt(record.get("SUB_SURTAX_AMT")))%></td>
		            			<td style="text-align: center;"><%=df.format(Integer.parseInt(record.get("SUM_CRD_SALE")))%></td>
		            			<td style="text-align: center;"><%=df.format(Integer.parseInt(record.get("AGN_CRD_AMT")))%></td>
		            			<td style="text-align: center;"><%=df.format(Integer.parseInt(record.get("AGN_PAY_PRC")))%></td>
		            			<td style="text-align: center;"><%=StringUtils.nvlObject(record.get("BANK"), "")%></td>
		            			<td style="text-align: center;"><%=StringUtils.nvlObject(record.get("COIL"), "")%></td>
							</tr> 
							<%
									}//for
								}
							%>
						</tbody>
					</table>
					<%-- <table class="table table-bordered table-hover table-striped listTable">
						<colgroup>
							<col/>
							<col/>
							<col/>
							<col/>
							<col/>
							<col/>
						</colgroup>
						<thead>
							<tr>
								<th style="text-align: center;" width="2%" >NO.</th>
								<th style="text-align: center;">기준년월</th>
								<th style="text-align: center;">지급차수</th>
								<th style="text-align: center;">대리점판매금액</th>
								<th style="text-align: center;">대리점여신금액</th>
								<th style="text-align: center;">여신판매차액</th>
							</tr>
						</thead>
						<tbody>
						<%
							if(_recordCnt==0){
						 %>	    
						 	<tr>
								<td colspan="6" ><p class="text-center table-noDataInfo"><%=sNoData%></p></td>								
							</tr>
						<% } else {
					    	for(int i=0; i<_recordCnt; i++){
								IRecord record = rsList.getRecord(i);
						%>
							<tr>
								<td style="text-align: center;"><%=record.get("ROWNO")%></td>	
								<td style="text-align: center;"><%=record.get("PAY_YM").substring(0,4) + "-" + record.get("PAY_YM").substring(4,6)%></td>
								<td style="text-align: center;"><%=Integer.parseInt(record.get("PAY_YM_TS")) + "차"%></td>
								<td style="text-align: right;padding-right:5px;"><%=df.format(Integer.parseInt(record.get("AGN_SALE_AMT")))%></td>
								<td style="text-align: right;padding-right:5px;"><%=df.format(Integer.parseInt(record.get("AGN_CRD_AMT")))%></td>
								<td style="text-align: right;padding-right:5px;"><%=df.format(Integer.parseInt(record.get("CRD_SALE_DAMT")))%></td>
							</tr>
							<%
									}//for
								}
							%>
						</tbody>
					</table> --%>
				</div>
				<!-- paging navi start -->
				<div class="page-wrap"></div>
				<!-- paging navi end -->
			</div>
		</div>
		<!-- content end -->
		<!-- footer file include  start -->
		<%@ include file="include/footer.jsp" %>	
		<!-- footer page include  end -->	
	</div>
	<script>	
		$(document).ready(function() {
			//1.달력초기화
			$('#fromDt').datepicker();
			$('#toDt').datepicker();
			
			//2. 페이징초기화 			
			var page = <%=_page%>;
			var pageCnt = <%=_page_size%>;			
			var totalCnt = <%=_totalCnt%>;
	
			if(totalCnt >0){
				 $(".page-wrap").pager({ 
							pagenumber: page, 
							pagecount: pageCnt, 
							totalcount: totalCnt,
							buttonClickCallback: 
							function(pagenumber){ 
								goSearchPage(pagenumber);
							}
				});
				$(".page-wrap .page-list li ").css("margin","0 2px");		
			}	
			
			//3. 조회조건항목에서 엔터키 입력시 조회 이벤트 추가
			$(".enterKeyupTarget").keyup(function(event){ 
				if( event.which == 13){
					fn_beforeSearch();
				}
			});
		});
		
		//조회 버튼 클릭시 이벤트
		function fn_beforeSearch(){
			document.MyForm.PAY_FR_YM.value = document.MyForm.PAY_FR_YM_PARAM.value;
			document.MyForm.PAY_TO_YM.value = document.MyForm.PAY_TO_YM_PARAM.value;		
			document.MyForm.PAY_FR_YM.value = document.MyForm.PAY_FR_YM.value.replace("-","");
			document.MyForm.PAY_TO_YM.value = document.MyForm.PAY_TO_YM.value.replace("-","");	
			
			//검색조건 유효성검사	
			var obj = { 	alertContent	:"기준년월을 확인해주세요.",
							startDate:document.MyForm.PAY_FR_YM.value,
							endDate:document.MyForm.PAY_TO_YM.value,
							startDateName:"PAY_FR_YM_PARAM",
							endDateName:"PAY_TO_YM_PARAM",
							mode:4 //기간 월 체크
			             };
			
			if(!gfn_checkPeriod(obj)){
				return false;
			}
			
			//조회서비스 호출
			goSearch();
		}
		//다른 페이지이동시 이벤트
		function goSearchPage(page){
			goSearch(page);
		}
		
		//조회서비스 호출 이벤트
 		function goSearch(nPage){			
			//페이지 설정
			if(nPage != null){
				document.MyForm.page.value = nPage;
			}
			document.MyForm.${transactionId}.value="PNRLoanMgmt_pInqSKNLoanLstWeb";
			document.MyForm.${target}.value="forward:jsp/SknCrdLstM.jsp";		
			document.MyForm.target = "_self";
			document.MyForm.action = "${contextPath}/standard.cmd";
			document.MyForm.submit();
		} 
		//초기화 버튼 클릭시 이벤트
		function formEmpty(){
			//document.MyForm.reset();
			document.MyForm.PAY_FR_YM_PARAM.value ="<%=todate%>";
			document.MyForm.PAY_TO_YM_PARAM.value = "<%=todate%>";	
			
			$("select[name=PAY_YM_TS] option[value='%']").attr("selected",true);
		}		
	</script>	
</body>
</html>