package fwk.handler;


import java.security.MessageDigest;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.CryptoUtils;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * DB 데이터 암호화를 위한 Ibatis handler
 * @author admin
 *
 */
public class Aes128CryptHandler implements TypeHandlerCallback {
	
	/*
	 * (non-Javadoc)
	 * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#getResult(com.ibatis.sqlmap.client.extensions.ResultGetter)
	 */
	public Object getResult(ResultGetter getter) throws SQLException {
		String value = getter.getString();
		return value;
	}
	
	/* (non-Javadoc)
	 * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#setParameter(com.ibatis.sqlmap.client.extensions.ParameterSetter, java.lang.Object)
	 */
	public void setParameter(ParameterSetter setter, Object obj) throws SQLException {
		String encodedStr = "";
		String tempStr = "";
		if(obj instanceof String){
		    tempStr = (String)obj;
		    if(StringUtils.isNotEmpty(tempStr)) {
		        encodedStr  = CryptoUtils.encode(tempStr);
		        setter.setObject(encodedStr);         
		    } else {
		        setter.setObject(obj);//parameter값이 null이거나 Empty String일 경우에는 기존 obj그대로 set한다.
		    }
		} else {
		    setter.setObject(obj);//parameter값이 String 타입의 변수가 아닐 경우에는 기존 obj그대로 set한다.
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#valueOf(java.lang.String)
	 */
	public Object valueOf(String value) {
		return value;
	}
}