package fwk.utils;

import java.util.HashMap;

import nexcore.framework.core.parameter.WasInstance;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.core.util.StringUtils;
import fwk.outbound.jmx.JMXConnection;

public class JMXUtils {

	public static final long connectTimeout = 5 * 1000;
	public static final long readTimeout = 5 * 1000;
	
	public static JMXConnection createJMXConnection(WasInstance wasInstance) throws Exception{
		HashMap<String, Object> h = new HashMap<String, Object>();
		h.put(JMXConnection.CONNECTION_TIMEOUT, connectTimeout);
		h.put(JMXConnection.READ_TIMEOUT, readTimeout);
		return new JMXConnection(getServiceUrl(wasInstance), h);
	}

	public static String getServiceUrl(WasInstance instance) {
		String url = BaseUtils.getConfiguration("nexcore.framework.jmx.serviceurl.template");
		url = StringUtils.replaceAll(url, "{ip}", instance.getServerIpAddress()+"");
		url = StringUtils.replaceAll(url, "{port}", instance.getNcJmxPort() + "");
		return url;
	}
}
