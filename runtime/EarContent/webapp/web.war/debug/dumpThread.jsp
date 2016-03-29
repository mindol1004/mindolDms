<%@ page contentType="text/html; charset=UTF-8" pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.concurrent.*" %>
<%@ page import="java.util.concurrent.atomic.*" %>
<%@ page import="java.lang.management.*" %>

<html>
<title>[<%=System.getProperty("system.id")%>] - THREAD DUMP</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/debug/common.css" type="text/css">
<body>
<h1>[<%=System.getProperty("system.id")%>] - THREAD DUMP : (<%= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) %>)</h1>
<%
        Map<String,Long> map = new HashMap();
		MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
		//System.out.println("heap      > "+mem.getHeapMemoryUsage());
		//System.out.println("non heap  > "+mem.getNonHeapMemoryUsage());

		MemoryUsage heapMem  = mem.getHeapMemoryUsage();
		MemoryUsage nonHeapMem = mem.getNonHeapMemoryUsage();
		map.put("HEAP_INIT",          heapMem.getInit());
		map.put("HEAP_USED",          heapMem.getUsed());
		map.put("HEAP_USED_PER",      heapMem.getUsed()*100/heapMem.getCommitted());
		map.put("HEAP_COMMITTED",     heapMem.getCommitted());
		map.put("HEAP_COMMITTED_PER", heapMem.getCommitted()*100/heapMem.getMax());
		map.put("HEAP_MAX",           heapMem.getMax());
		
		map.put("NONHEAP_INIT",          nonHeapMem.getInit());
		map.put("NONHEAP_USED",          nonHeapMem.getUsed());
		map.put("NONHEAP_USED_PER",      nonHeapMem.getUsed()*100/heapMem.getCommitted());
		map.put("NONHEAP_COMMITTED",     nonHeapMem.getCommitted());
		map.put("NONHEAP_COMMITTED_PER", nonHeapMem.getCommitted()*100/heapMem.getMax());
		map.put("NONHEAP_MAX",           nonHeapMem.getMax());
%>
<%!
    String printWarnColor(long usedPer) {
        if (usedPer > 80) {
            return "#FF0000";
        }else if (usedPer > 70) {
            return "#FF5555";
        }else if (usedPer > 60) {
            return "#FFAAAA";
        }else {
            return "#FFFFFF";
        }
    }
%>
<fieldset style="background-color:#FFFFFF;">
<legend>Memory</legend>
<table cellpadding="2" cellspacing="0" class="contents">
	<colgroup>
		<col width="20%">
		<col width="20%">
		<col width="20%">
		<col width="20%">
		<col width="20%">
	</colgroup>
    <tr>
        <th>JVM Memory</th>
        <th>INIT</th>
        <th>USED</th>
        <th>COMMITTED</th>
        <th>MAX</th>
    </tr>
    <tr align="right">
        <td class="th">Heap</TD>
        <td><%=map.get("HEAP_INIT")/1024/1024%> M </TD>
        <td bgcolor="<%=printWarnColor(map.get("HEAP_USED_PER"))%>"><%=map.get("HEAP_USED")/1024/1024%> M (<%=map.get("HEAP_USED_PER")%>%)</TD>
        <td><%=map.get("HEAP_COMMITTED")/1024/1024%> M (<%=map.get("HEAP_COMMITTED_PER")%>%)</TD>
        <td><%=map.get("HEAP_MAX")/1024/1024%> M </TD>
    </tr>
    <tr align="right">
        <td class="th">Non-Heap</TD>
        <td><%=map.get("NONHEAP_INIT")/1024/1024%> M </TD>
        <td bgcolor="<%=printWarnColor(map.get("NONHEAP_USED_PER"))%>"><%=map.get("NONHEAP_USED")/1024/1024%> M (<%=map.get("NONHEAP_USED_PER")%>%)</TD>
        <td><%=map.get("NONHEAP_COMMITTED")/1024/1024%> M (<%=map.get("NONHEAP_COMMITTED_PER")%>%)</TD>
        <td><%=map.get("NONHEAP_MAX")/1024/1024%> M </TD>
    </tr>
</table>
</fieldset>
<br>
<fieldset style="background-color:#FFFFFF;">
<legend>Thread Summary</legend>
<div id="summary"></div>
</fieldset>
<br>
<fieldset style="background-color:#FFFFFF;">
<legend>Thread List</legend>
<%
	Map<Thread.State, AtomicInteger> stateMap = new LinkedHashMap<Thread.State, AtomicInteger>();
	stateMap.put(Thread.State.NEW,           new AtomicInteger(0));
	stateMap.put(Thread.State.BLOCKED,       new AtomicInteger(0));
	stateMap.put(Thread.State.RUNNABLE,      new AtomicInteger(0));
	stateMap.put(Thread.State.TIMED_WAITING, new AtomicInteger(0));
	stateMap.put(Thread.State.WAITING,       new AtomicInteger(0));
	stateMap.put(Thread.State.TERMINATED,    new AtomicInteger(0));

	Map<Thread, StackTraceElement[]> dump = Thread.getAllStackTraces();
	int i=0;
	StringBuilder buff = new StringBuilder();
	for (Map.Entry<Thread, StackTraceElement[]> entry : dump.entrySet()) {
	    Thread t = entry.getKey();
	    
	    stateMap.get(t.getState()).incrementAndGet();
	    
	    buff.setLength(0);
	    printThreadDump(buff, t, entry.getValue());
	    out.println("<pre>");
	    out.println((++i) + " : " + buff.toString());
	    out.println("</pre>");
	}
	
	buff.setLength(0);
	buff.append("<table cellpadding=\"2\" cellspacing=\"0\" class=\"contents\">");
	buff.append("<colgroup><col width=\"16.5%\"><col width=\"16.5%\"><col width=\"16.5%\"><col width=\"16.5%\"><col width=\"16.5%\"><col width=\"16.5%\"></colgroup>");
	buff.append("<tr><th>NEW</th><th>RUNNABLE</th><th>BLOCKED</th><th>WAITING</th><th>TIMED_WAITING</th><th>TERMINATED</th></td>");
	buff.append("<tr>");
	buff.append("<td align=\"center\">").append(stateMap.get(Thread.State.NEW)).append("</td>");
	buff.append("<td align=\"center\">").append(stateMap.get(Thread.State.RUNNABLE)).append("</td>");
	buff.append("<td align=\"center\">").append(stateMap.get(Thread.State.BLOCKED)).append("</td>");
	buff.append("<td align=\"center\">").append(stateMap.get(Thread.State.WAITING)).append("</td>");
	buff.append("<td align=\"center\">").append(stateMap.get(Thread.State.TIMED_WAITING)).append("</td>");
	buff.append("<td align=\"center\">").append(stateMap.get(Thread.State.TERMINATED)).append("</td>");
	buff.append("</tr>");
	buff.append("</table>");
%>    
</fieldset>
</body>
<script>
    document.getElementById("summary").innerHTML='<%=buff.toString()%>';
</script>
</html>

<%!
void printThreadDump(StringBuilder buff, Thread t, StackTraceElement[] traces){
    buff.append("\"").append(t.getName()).append("\"");
    if (t.isDaemon()) {
        buff.append(" daemon");
    }
    buff.append(" ").append(t.getState());
    buff.append(" id=").append(t.getId());
    buff.append(" priority=").append(t.getPriority());
    buff.append(" hashcode=").append(t.hashCode());
    ThreadGroup tg = t.getThreadGroup();
    buff.append(" threadGroup=").append(tg != null ? tg.getName() : "");
    buff.append(" class=").append(t.getClass().getName());

    if (traces != null) {
        for (int i = 0; i < traces.length; i++) {
            buff.append("\n\tat ");
            buff.append(traces[i].toString());
        }
    }
};
%>