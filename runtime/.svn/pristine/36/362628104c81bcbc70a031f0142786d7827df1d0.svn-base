<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="nexcore.framework.core.util.*"%>
<% 
	boolean isLocalhost = "localhost".equals(BaseUtils.getCurrentWasInstanceId());
	
	// 프레임워크 홈 디렉터리 조회
	String fwkHome = BaseUtils.getFwkHome();
	if(isLocalhost){
		fwkHome = new File(fwkHome).getParent();
	}
	
	// 시스템 속성에서 조회
	//String logHome = "/logs/nexcore/" + BaseUtils.getCurrentWasInstanceId();
	String logHome = System.getProperty("NEXCORE_LOG_HOME");
	
		// 로그 객체를 분석하여 조회
	if(logHome == null || logHome.trim().length() < 1){
		org.apache.log4j.Logger logger = org.apache.log4j.LogManager.exists(nexcore.framework.core.log.LogManager.BASE_FWK_LOGGER_NAME);
		if(logger != null){
			Enumeration appenders = logger.getAllAppenders();
			while (appenders.hasMoreElements()) {
				org.apache.log4j.Appender a = (org.apache.log4j.Appender) appenders.nextElement();
	            if(a instanceof org.apache.log4j.FileAppender){
	            	org.apache.log4j.FileAppender fa = (org.apache.log4j.FileAppender)a;
	            	File f = new File(fa.getFile());
	            	logHome = f.getParent();
	            	break;
	            }
	            else if(a instanceof org.apache.log4j.AsyncAppender){
	            	org.apache.log4j.AsyncAppender aa = (org.apache.log4j.AsyncAppender)a;
	            	Enumeration refs = aa.getAllAppenders();
	            	while (refs.hasMoreElements()) {
	            		a = (org.apache.log4j.Appender) refs.nextElement();
	            		if(a instanceof org.apache.log4j.FileAppender){
	    	            	org.apache.log4j.FileAppender fa = (org.apache.log4j.FileAppender)a;
	    	            	File f = new File(fa.getFile());
	    	            	logHome = f.getParent();
	    	            	break;
	    	            }
	            	}
	            	break;
	            }
	        }
		}
	}
	
	fwkHome = fwkHome.replaceAll("\\\\", "\\/");
	if(!fwkHome.endsWith("/")){
		fwkHome += "/";
	}
	
	logHome = logHome.replaceAll("\\\\", "\\/");
	if(!logHome.endsWith("/")){
		logHome += "/";
	}

	// 입력 파라미터
	String kind = request.getParameter("kind");
	String curr = request.getParameter("curr");
	String name = request.getParameter("name");

	boolean isLog = "log".equals(kind);
	
	if(curr == null){
		curr = "";
	}
	if(curr.indexOf("../") > -1 || name.indexOf("../") > -1){
		return;
	}
	
	if(!"".equals(curr)){
		curr += "/";
	}
	
	String filePath = (isLog ? logHome : fwkHome) + curr;
	String fileName = name;
	
	//응답 헤더의 Content-Type을 세팅한다. 
	response.setContentType("application/x-msdownload"); 
	//위 세팅으로 안될 경우에 사용.
	//response.setContentType("application/octet-stream");
	
	//Content-Disposition 세팅하기위해 file 이름을 변환한다.
	String convName1 = java.net.URLEncoder.encode(new String(fileName .getBytes("8859_1"), "euc-kr"),"UTF-8");
	
	//Content-Disposition 헤더에 파일 이름 세팅. 
	response.setHeader("Content-Disposition", "attachment;filename=" + convName1 + ";");
	//위 세팅으로 안될 경우에 사용.
	//response.setHeader("Content-Disposition","attachment;fileName=\""+Convfilename+"\";");
	
	
	// 폴더에 있는 파일 가져오기 위해 다른 방법으로 변환
	String  convName2 = new String(fileName.getBytes("8859_1"), "euc-kr");
	File file = new File(filePath+convName2);
	
	// 사용자에게 보내주기 위해 스트림객체 생성
	byte b[] = new byte[(int)file.length()];    
	if (file.length() > 0 && file.isFile()) // 0byte이상이고, 해당 파일이 존재할 경우
	{
		 BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file)); 
		
		 // 인풋객체생성
		 BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream()); 
		
		 // 응답객체생성
		
		 
		int read = 0;
		try {
		 while ((read = fin.read(b)) != -1){
		     outs.write(b,0,read);
		 }
		 outs.close();
		 fin.close();
		} catch (Exception e) {
		 System.out.println("download error : " + e.getMessage()); 
		} finally {
		 if(outs!=null) outs.close();
		 if(fin!=null) fin.close();
		}
	}
    out.clear();
    out = pageContext.pushBody();
    
  /* 위 내용대신 아래내용을 써도 무방.같은 결과: byte b[]부분부터 대체
 byte[] byteStream = new byte[(int)file.length()];
 FileInputStream fileStream = new FileInputStream(file);
 int i=0;
 int j=0;
 while( (i=fileStream.read()) != -1 ){
  byteStream[j] = (byte)i;
  j++;
 }
 OutputStream outStream = response.getOutputStream();
 outStream.write(byteStream);
 outStream.close();
 */
    
%>
