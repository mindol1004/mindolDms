package fwk.outbound.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import nexcore.framework.integration.tcp.ITCPConnection;
import nexcore.framework.integration.tcp.TCPConnectionException;

import org.apache.commons.logging.Log;

/**
 * HTTP 통신을 통해 전문송수신 클래스
 */
public class OutboundHttpConnection implements ITCPConnection {

	private final static int BUFFER_SIZE = 1024;
	private final static String REQUEST_METHOD_POST = "POST";
	private final static String CONTENT_TYPE_KEY = "Content-type";
	private final static String CONTENT_TYPE_VALUE = "application/octet-stream";

	private HttpURLConnection conn;
	private BufferedOutputStream bos;
	private BufferedInputStream bis;

	private String hostName;
	private int connectTimeout;
	private int blockTimeout;
	private int defaultReadTimeout;
	
	/**
	 * URL을 등록한다.
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	/**
	 * 포트정보 - TCP일때만 사용함.
	 * @deprecated
	 */
	public void setPort(int port) {
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public void setBlockTimeout(int blockTimeout) {
		this.blockTimeout = blockTimeout;
	}

	public void setDefaultReadTimeout(int defaultReadTimeout) {
		this.defaultReadTimeout = defaultReadTimeout;
	}

	public void setLogger(Log logger) {
	}

	public void setPoolName(String poolName) {
	}

	public void setPoolSeq(int poolSeq) {
	}

	public void connect() {
	}
	
	private void connectImpl(int readTimeout) {
		URL url = null;
		try {
			url = new URL(hostName);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setConnectTimeout(connectTimeout);
			if(readTimeout > 0){
				conn.setReadTimeout(readTimeout);
			}
			conn.setRequestMethod(REQUEST_METHOD_POST);
			conn.setRequestProperty(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE);
		} catch (Exception e) {
			throw new RuntimeException("Can not connect to '" + hostName + "'", e);
		}
	}
	
	public void reconnect() {
	}

	public void close() {
		if(conn != null){
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception e) {
				    throw new RuntimeException(e);
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (Exception e) {
				    throw new RuntimeException(e);
				}
			}
			try {
				conn.disconnect();
			} catch (Exception e) {
			    throw new RuntimeException(e);
			}
			conn = null;
		}
	}

	public boolean isConnected() {
		return conn != null;
	}

	public String getPoolName() {
		return null;
	}

	public String getName() {
		return null;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public int getBlockTimeout() {
		return blockTimeout;
	}

	public int getDefaultReadTimeout() {
		return defaultReadTimeout;
	}

	public long getReconnectCount() {
		return 0;
	}

	public void send(byte[] data) {
		send(data, 0, data.length);
	}

	public void send(byte[] data, int offset, int length) {
		connectImpl(-1);
		try{
			bos = new BufferedOutputStream(conn.getOutputStream());
			bos.write(data, offset, length);
			bos.flush();
	
			int responseCode = conn.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new IOException("ResponseCode is not OK. responseCode=[" + responseCode + "].\n" + conn.getResponseMessage());
			}
		} catch(Exception e){
			throw new RuntimeException("RequestData send failed.", e);
		}
	}

	public void send(Object data) {
		throw new TCPConnectionException("Can not use object send.");
	}

	public byte[] call(byte[] data) {
		return call(data, 0, data.length, defaultReadTimeout);
	}

	public byte[] call(byte[] data, int timeout) {
		return call(data, 0, data.length, timeout);
	}

	public byte[] call(byte[] data, int offset, int length) {
		return call(data, offset, length, defaultReadTimeout);
	}

	public byte[] call(byte[] data, int offset, int length, int timeout) {
		connectImpl(timeout);
		try {
			send(data, offset, length);
			return read();
		} catch(RuntimeException e){
			throw (RuntimeException)e;
		} catch(Exception e){
			throw new RuntimeException("ResponseData read failed.", e);
		} finally {
			close();
		}
	}

	public Object call(Object data) {
		throw new TCPConnectionException("Can not use object call.");
	}

	public Object call(Object data, int timeout) {
		throw new TCPConnectionException("Can not use object call.");
	}

	private byte[] read() throws IOException {
		ByteArrayOutputStream output = null;
		try {
			bis = new BufferedInputStream(conn.getInputStream());
			output = new ByteArrayOutputStream();
			byte[] buf = new byte[BUFFER_SIZE];
			int len = -1;
			while ((len = bis.read(buf)) != -1) {
				output.write(buf, 0, len);
			}
			output.flush();
			return output.toByteArray();
		} catch(SocketTimeoutException e){
			throw new SocketTimeoutException("ReadTimeout(" + conn.getReadTimeout() + "ms) occurred.");
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (Exception e) {
				    throw new RuntimeException(e);
				}
			}
		}
	}
}
