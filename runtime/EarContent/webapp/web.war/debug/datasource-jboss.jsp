<%@page contentType="text/html; charset=UTF-8" pageEncoding="EUC-KR"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*" %>
<%@page import="java.rmi.*"%>
<%@page import="javax.naming.*"%>
<%@page import="javax.management.*"%>
<%
	String[] attr_names = {"AvailableConnectionCount","BackGroundValidationMillis","BlockingTimeoutMillis","ConnectionCount","ConnectionCreatedCount","ConnectionDestroyedCount","Criteria","IdleTimeoutMinutes","InUseConnectionCount","MaxConnectionsInUseCount","MaxSize","MinSize","Name","PoolJndiName","State","StateString","StatisticsFormatter"};
	Map<String, Map<String, Object>> dataSourceMap = new LinkedHashMap<String, Map<String, Object>>();
	//List<String> attrKeys = new ArrayList<String>();
   	InitialContext ctx = null;
	try {
		
		ctx = new InitialContext();
    	MBeanServerConnection server = (MBeanServerConnection) ctx.lookup("jmx/invoker/RMIAdaptor");
    	Set<?> names = server.queryNames(new ObjectName("jboss.jca:service=ManagedConnectionPool,name=*"), null);
    	if (names != null) {
			ObjectName name = null;
  			for (Iterator<?> i = names.iterator(); i.hasNext();) {
  				name = (ObjectName) i.next();
  				Map<String, Object> attributeMap = new HashMap<String, Object>();
		    	dataSourceMap.put(name.getKeyProperty("name"), attributeMap);
  				for(String attr_name : attr_names){
  					Object value = server.getAttribute(name, attr_name);
  					attributeMap.put(attr_name, value);
  				}
  				
  				
  				/*
  				MBeanInfo info = server.getMBeanInfo(name);
		    	MBeanAttributeInfo[] attrs = info.getAttributes();
		    	Map<String, Object> attributeMap = new HashMap<String, Object>();
		    	//System.out.println("#######################################################");
		    	dataSourceMap.put(name.getKeyProperty("name"), attributeMap);
		    	//System.out.println(name.getKeyProperty("name"));
		    	//System.out.println("#######################################################");
		    	if(attrs != null){
		    		
		    		for(MBeanAttributeInfo attr : attrs){
		    			if(attr.isReadable()){
		    				Object value = server.getAttribute(name, attr.getName());
		    				//System.out.println(attr.getName() + "=" + value);
		    				if(value != null && (value.getClass().isPrimitive() || value instanceof String || value instanceof Number) ){
		    					attributeMap.put(attr.getName(), value + "(" + value.getClass().getName() + ")");
		    					if(!attrKeys.contains(attr.getName())){
		    						attrKeys.add(attr.getName());
		    					}
		    				}
		    			}
		    		}
		    	}
		    	*/
			 }
		}
	}
	finally{
		if(ctx != null){
			try{
				ctx.close();
			}catch(Exception e){}
		}
	}
	
	List<String> dataSourceKeys = new ArrayList(dataSourceMap.keySet());
	Collections.sort(dataSourceKeys);
%>
<html>
<head>
<title>[<%=System.getProperty("system.id")%>] - DATASOURCE INFORMATION - JBOSS</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/debug/common.css" type="text/css">
<script>
</script>
</head>
<body>
<h1>[<%=System.getProperty("system.id")%>] - DATASOURCE INFORMATION - JBOSS : (<%= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) %>)</h1>
<table cellpadding="2" cellspacing="0" class="contents">
<%
	if(dataSourceKeys.size() > 0){
%>
	<tr>
		<th>DataSource</th>
<%
		for(String attr_name : attr_names){
%>
		<th><%= attr_name %></th>
<%
		}

		int i=0;
		for(String dataSourceKey : dataSourceKeys){
			Map<String, Object> attributeMap = dataSourceMap.get(dataSourceKey);
%>
	<tr>
		<td class="td<%= (i%2) == 0 ? "1" : "2" %>"><%= dataSourceKey %></td>
<%
			for(String attr_name : attr_names){
%>
		<td class="td<%= (i%2) == 0 ? "1" : "2" %>" align="center"><%= attributeMap.get(attr_name) %></td>				
<%				
			}
%>		
	<tr>
<%			
			i++;
		}
	}
%>
</table>
</body>
</html>
