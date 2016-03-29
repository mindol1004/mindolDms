package fwk.outbound.internal;

import nexcore.framework.core.ioc.ComponentRegistry;
import nexcore.framework.integration.tcp.ITCPConnectionManager;

/**
 * 아웃바운드 Connection 정보를 매핑한다.
 */
public class OutboundConnectionFactory {
	
	private static ITCPConnectionManager tcpConnectionManager;
	
	public static OutboundConnection get(String targetName){
		prepare();
		return new OutboundConnection(tcpConnectionManager.getConnection(targetName));
	}
	
	public static boolean isExist(String targetName){
		prepare();
		return tcpConnectionManager.isExist(targetName) || tcpConnectionManager.isExistMeta(targetName);
	}
	
	public static void destroy(OutboundConnection conn){
		if (conn != null) {
			prepare();
			tcpConnectionManager.destroyConnection(conn.getTcpConnection());
		}
	}
	
	public static void release(OutboundConnection conn){
		if (conn != null) {
			prepare();
			tcpConnectionManager.releaseConnection(conn.getTcpConnection());
		}
	}
	
	private static void prepare(){
		if(tcpConnectionManager == null){
			tcpConnectionManager = (ITCPConnectionManager) ComponentRegistry.lookup(ITCPConnectionManager.COMPONENT_ID);
		}
	}
	
}
