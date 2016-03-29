package fwk.outbound.internal;

import nexcore.framework.core.util.ByteArrayWrap;
import nexcore.framework.integration.tcp.ITCPConnection;

/**
 * 아웃바운드 Connection
 */
public class OutboundConnection {

	private ITCPConnection conn;

	OutboundConnection(ITCPConnection tcpConn) {
		this.conn = tcpConn;
	}
	
	public void send(ByteArrayWrap data) {
		conn.send(data.getByteArray(), data.getOffset(), data.getLength());
	}

	public byte[] call(ByteArrayWrap data, int timeout) {
		if(timeout == -1 || timeout == -1000){
			timeout = conn.getDefaultReadTimeout();
		}
		return conn.call(data.getByteArray(), data.getOffset(), data.getLength(), timeout);
	}

	ITCPConnection getTcpConnection(){
		return conn;
	}
	
}
