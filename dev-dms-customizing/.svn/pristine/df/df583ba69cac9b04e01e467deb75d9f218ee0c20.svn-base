package fwk.resolver;

import java.util.Map;

import nexcore.framework.core.data.CaseIgnoreHashMap;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.coreext.pojo.resolver.impl.DefaultDBAccessableResolver;
import fwk.common.CommonArea;
import fwk.common.CommonUtils;

public class DBAccessableResolver extends DefaultDBAccessableResolver {

	 public Object resolveParam(final IOnlineContext onlineCtx, final Object param) {
	        final CommonArea ca = CommonUtils.getCommonArea(onlineCtx);
	        if (ca != null) {
	            if (param == null || param instanceof Map) {
	                // param 이 IRecord 일 경우는 map.put 으로는 값들이 들어가지 않는다. (헤더에정의되지 않은 값이므로) 그래서 아래와 같이 한다.
	                return new CaseIgnoreHashMap() {
	                    private static final long serialVersionUID = 1L;

	                    public Object get(Object key) {                    	     
	                        if ("lastChngGuid".equalsIgnoreCase(String.valueOf(key))) { // GUID
	                            return ca.getGlobId();                                    
	                        } else if ("caUserId".equalsIgnoreCase(String.valueOf(key))) { // 사용자ID
	                            return ca.getUserNo();
	                        } else if ("lsChgUserId".equalsIgnoreCase(String.valueOf(key))) { // 사용자ID
	                            return ca.getUserNo();
	                        } else {
	                            return param == null ? null : ((Map) param).get(key);
	                        }
	                    }

	                    public Object put(Object key, Object value) {
	                        return param == null ? null : ((Map) param).put(key, value);
	                    }

	                    public void putAll(Map m) {
	                        if (param != null) {
	                            ((Map) param).putAll(m);
	                        }
	                    }

	                    public Object remove(Object arg0) {
	                        return param == null ? null : ((Map) param).remove(arg0);
	                    }
	                };
	            }
	        }
	        return param;
	    }
}
