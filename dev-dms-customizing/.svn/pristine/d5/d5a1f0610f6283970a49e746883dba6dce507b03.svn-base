package fwk.resource.bundle;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import nexcore.framework.core.exception.FwkRuntimeException;

public class HpcResourceBundle {

//    private Locale currentLocales;
    private String currentLocaleStr;
    private String bundleName;
    private String bundlePath;
    private ResourceBundle msgBundle; 
    private Map<String, ResourceBundle> resourceBundleMap;
    
    public String getBundlePath() {
        return bundlePath;
    }

    public void setBundlePath(String bundlePath) {
        this.bundlePath = bundlePath;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

//    public Locale getCurrentLocales() {
//        return currentLocales;
//    }

    public void setLocale(String locale) {
        this.currentLocaleStr = locale;
//        this. currentLocales = new Locale(locale);
    }

    public void init()  {
        resourceBundleMap = new HashMap<String, ResourceBundle>();
            loadResource(currentLocaleStr);
    }
    
    private void loadResource(String localeStr) {
        URL resourceUrl = null;
        File resourceFile = new File(bundlePath);
        try {
            resourceUrl = resourceFile.toURI().toURL();    
            Locale locale = new Locale(localeStr);
            URLClassLoader urlLoader = new URLClassLoader(new URL[]{resourceUrl});
            msgBundle = ResourceBundle.getBundle (bundleName, locale, urlLoader);
            resourceBundleMap.put(localeStr, msgBundle);
        } catch(MalformedURLException e) {
            throw new FwkRuntimeException ("SKFE5005", new String[]{"hpcLangBundle load"}, e);
        }
    }
    
    public String getBundleMsg(String key){
        return getBundleMsg(currentLocaleStr, key);
    }
    
    public String getBundleMsg(String localeStr, String key)  {
        ResourceBundle rb = resourceBundleMap.get(localeStr);
        if(rb == null) {
            loadResource(localeStr);
            rb = resourceBundleMap.get(localeStr);
        }
        return rb.getString(key);
    }
    
//    public static void main(String[]args) {
//        String bundlePath = "C:/projects/hpc/workspace/runtime/EarContent/APP-INF/classes/config/parameter/";
//        String bundleName = "hpcMsgBundle";
//        String localeStr = "ko";
//        HpcResourceBundle hrb = new HpcResourceBundle();
//        hrb.setBundleName(bundleName);
//        hrb.setBundlePath(bundlePath);
//        hrb.setLocale(localeStr);
//        hrb.init();
//        System.out.println("번들한글출력 : "+hrb.getBundleMsg("DEPT_CD"));
//        System.out.println("번들영어출력 : "+hrb.getBundleMsg("en", "EMP_NO"));
//        System.out.println("번들한글출력 : "+hrb.getBundleMsg("ko", "EMP_NO"));
//        System.out.println("번들일어출력 : "+hrb.getBundleMsg("ja", "EMP_NO"));
//        
//    }
}
