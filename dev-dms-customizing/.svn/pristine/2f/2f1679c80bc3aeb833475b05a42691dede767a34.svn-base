package fwk.utils;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nexcore.framework.core.log.LogManager;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.logging.Log;




public class DomainUtils {
	
	private static Log LOGGER = LogManager.getFwkLog();
	
	/**
	 * key, object를 전달받아 map에 put해서 리턴
	 * @param String name
	 * @param Object value
	 * @return Map<String,Object> map
	 */
	public Map<String,Object> makeMap(String name, Object value){
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put(name, value);
		return map;
	}
	
	/**
	 * get List Map From the fields
	 * @param List list
	 * @return List<Map<String,String>>
	 */	
	@SuppressWarnings({"rawtypes"})
	public List<Map<String,String>> getListMapFromDo(List list,  List<String> fieldNames){
		List<Map<String,String>> retMapList = null;
		
		if(list != null){
			retMapList = new ArrayList<Map<String,String>>();
			for(Object o : list){
				retMapList.add( getDoToHashmap(o, fieldNames) );
			}
		}
		return retMapList;
	}
	
	/**
	 * get Do To Hashmap
	 * @param Object doObject
	 * @param List<String> fieldNames
	 * @return Map<String,String>
	 */		
	public Map<String,String> getDoToHashmap(Object doObject, List<String> fieldNames){
		String 	strVal = null;
		Map<String,String> map = new HashMap<String,String>();
		
		for(String fieldName : fieldNames ){
			strVal= getFieldValue(doObject,fieldName);
			map.put(fieldName, strVal);
		}
		return map;
	}
	
	/**
	 * get Field Value
	 * @param Object doObject
	 * @param String fieldName
	 * @return String strVal
	 */			
	public String getFieldValue (Object doObject, String fieldName){
		Field   field = null;
		String 	strVal = null;
		field = searchField(doObject.getClass(), fieldName);
		try {
			if(field!=null){
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				strVal = (String)field.get(doObject);
			}else{
				strVal = String.valueOf( PropertyUtils.getProperty(doObject, fieldName) );
			}
		} catch (IllegalArgumentException e) {
			LOGGER.debug(e.getMessage());
		} catch (IllegalAccessException e) {
			LOGGER.debug(e.getMessage());
		} catch (InvocationTargetException e) {
			LOGGER.debug(e.getMessage());
		} catch (NoSuchMethodException e) {
			LOGGER.debug(e.getMessage());
		}
		
		return strVal;
	}
	
	/**
	 * search Field
	 * @param Class<? extends Object> doClass
	 * @param String fieldName
	 * @return String field
	 */		
	@SuppressWarnings({"rawtypes"})
	public static Field searchField(Class<? extends Object> doClass,
			String fieldName) {
		Field field = null;
		Class doSuperClass = doClass;
		while ((field == null) && (doSuperClass != null)) {
			try {
				field = doSuperClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				doSuperClass = doSuperClass.getSuperclass();
			}
		}
		return field;
	}

	

	/**
	 * Domain Object에 get을 읽어 HashMap<String, String>에 key value를 set 한다. 
	 * @param {@link java.lang.Object} obj
	 * @return {@link java.util.HashMap} map
	 */
	public static HashMap<String, String> invokeDomainToMap(Object obj) {
		return invokeDomainToMap(obj, true);
	}


	
	/**
	 * Domain Object에 get을 읽어 HashMap<String, String>에 key value를 set 한다.
	 * @param {@link java.lang.Object} obj
	 * @param {@link boolean} isLowerCase
	 * @return {@link java.util.HashMap} map
	 */
	public static HashMap<String, String> invokeDomainToMap(Object obj, boolean isLowerCase) {
		HashMap<String, String> result = new HashMap<String, String>();
		Method[] methods = obj.getClass().getMethods();
        
		for(Method method : methods) {
			String methodName = method.getName();
			
			if (!"getClass".contains(methodName) && methodName.indexOf("get") == 0) {
				try {
					String key = null;
					if (isLowerCase) {
						key = (methodName.substring(3, 4)).toLowerCase() + methodName.substring(4);					
					}
					else {
						key = (methodName.substring(3, 4)) + methodName.substring(4);
					}
					
					Object tmpObj = method.invoke(obj);
					if(tmpObj instanceof String)
					{
						String value = (String)tmpObj;					
						result.put(key, value);
					}
				}
				catch(IllegalAccessException lae) {
					LOGGER.debug("[IllegalAccessException]"+lae.getMessage());
				}
				catch(InvocationTargetException ite) {
					LOGGER.debug("[InvocationTargetException]"+ite.getMessage());
				}
				catch(Exception e) {
					LOGGER.debug("[Exception]"+e.getMessage());
				}
			}
        }
		
		return result;
	}
	
	
	/**
	 * Map에 값이 있는 것들만 추출한다.(null 제외,"" 포함)
	 * @param map
	 * @return
	 */
	public static HashMap<String, String> extractRealvalueFromMap(HashMap<String,String> map)
	{
		HashMap<String,String> returnMap = map;
		
		if(   map!=null
		   && map.size()>0
		  )
		{
			returnMap = new HashMap<String,String>();
			Iterator<String> iter = map.keySet().iterator();
			while(iter.hasNext())
			{
				String key = (String)iter.next();
				if(map.containsKey(key))
				{
					String value = map.get(key);
					boolean valueFlag = (value!=null);
					if(valueFlag)
					{
						returnMap.put(key, value);
					}
				}
				   
			}
		}
		return returnMap;
	}
	
	/**
	 * Object에 값이 있는 것들만 추출한다.(null 제외,"" 포함)
	 * @param map
	 * @return
	 */
	public static HashMap<String,String> extractRealvalueMapFromDo(Object obj)
	{
		return extractRealvalueFromMap(invokeDomainToMap(obj));
	}
	

	/**
	 * Map이 null인지 체크
	 * @param map
	 * @return
	 */
	public static boolean isNotEmpty(@SuppressWarnings("rawtypes") Map map)
	{
		boolean returnFlag = false;
		
		if(map!=null && map.size()>0)
		{
			returnFlag = true;
		}
		return returnFlag;
	}

	/**
	 * Map의 key의 첫번째 글자를 대,소문자로 강제 변환한다.
	 * @param map
	 * @param isLowerCase
	 * @return
	 */
	public static HashMap<String, String> convertKeyCaseFirstLetter(HashMap<String, String> map, boolean isLowerCase)
	{
		HashMap<String,String> returnMap = map;
		
		if(   map!=null
		   && map.size()>0
		  )
		{
			returnMap = new HashMap<String,String>();
			Iterator<String> iter = map.keySet().iterator();
			while(iter.hasNext())
			{
				String key = (String)iter.next();
				String convertKey = key;
				if(key.length() > 0)
				{
					if(isLowerCase)
						convertKey = key.substring(0, 1).toLowerCase() + ((key.length() > 1)?key.substring(1):"");
					else
						convertKey = key.substring(0, 1).toUpperCase() + ((key.length() > 1)?key.substring(1):"");
				}
				if(map.containsKey(key))
				{
					String value = map.get(key);
					returnMap.put(convertKey, value);
				}
				   
			}
		}
		return returnMap;
	}	
	
	
	/**
	 * HashMap의 key value를 읽어 Domain Object에 set 한다.
	 * @param {@link java.lang.Object} obj
	 * @param {@link java.util.HashMap} map
	 * @return {@link java.lang.Object} obj
	 */
	public static Object invokeMapToDomain(Object obj, Map map) {
		return invokeMapToDomain(obj,map,false);
	}
	
	
	/**
	 * HashMap의 key value를 읽어 Domain Object에 set 한다.
	 * @param {@link java.lang.Object} obj
	 * @param {@link java.util.HashMap} map
	 * @return {@link java.lang.Object} obj
	 */
	public static Object invokeMapToDomain(Object obj, Map map, boolean underScoreToCamelFlag) {
		Method[] methods = obj.getClass().getMethods();
		
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {	
			String key = iterator.next();
			String methodName = "set"+(underScoreToCamelFlag?underScoreToCamel(key):key);
			
			for(Method method : methods) {
				if(method.getName().equalsIgnoreCase(methodName)) {
					try {						
						method.invoke(obj, new Object[]{map.get(key)});
					}
					catch(IllegalAccessException lae) {
						LOGGER.debug("[IllegalAccessException]"+lae.getMessage());
					}
					catch(InvocationTargetException ite) {
						LOGGER.debug("[InvocationTargetException]"+ite.getMessage());
					}
					catch(Exception e) {
						LOGGER.debug("[Exception]"+e.getMessage());
					}
				}
	        }
		}
		
		return obj;
	}
	
	/**
	 * camelToUnderScore
	 * @param camelString
	 * @return
	 */
	public static String camelToUnderScore(String camelString)
	{
		return camelString.replaceAll("([^_A-Z])([A-Z])", "$1_$2");
	}
	
	/**
	 * underScoreToCamel
	 * @param underScoreString
	 * @return
	 */
	public static String underScoreToCamel(String underScoreString)
	{
		return StringUtils.remove(WordUtils.capitalizeFully(underScoreString, '_'), "_");
	}
	
}


