package fwk.utils;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.log.LogManager;
import nexcore.framework.core.log.impl.ExtendedLog;

public class HpcLogUtils {
    private static final String OPERATOR_LOG_NAME = "__operlog";
    public static final int ERROR_LEVEL=0;
    public static final int INFO_LEVEL=1;
    public static final int DEBUG_LEVEL=2;
    
    public static org.apache.commons.logging.Log getOperLog(IOnlineContext context) {
        Log log = (Log)context.getAttribute(OPERATOR_LOG_NAME);
        if(log != null){
            return log;
        }
        
        String identifier = context.getTransaction().getTxId();
        String loggerName = OPERATOR_LOG_NAME+ "." +identifier;
        org.apache.log4j.Logger logger = org.apache.log4j.LogManager.exists(loggerName);
        if (logger == null) {
            logger = org.apache.log4j.Logger.getLogger(loggerName);
            LogManager.inheritAppender(logger, identifier);
            
            org.apache.log4j.Logger baseLogger = org.apache.log4j.LogManager.exists(OPERATOR_LOG_NAME);
            if(baseLogger != null){
                logger.setLevel(baseLogger.getLevel());
            }
        }

        log = new ExtendedLog(context, Logger.getLogger(loggerName));
        context.setAttribute(OPERATOR_LOG_NAME, log);
        
        return log;
    }

    /**
     * DataSet에 담긴 정보들을 출력하기 위한 메소드
     *  
     * @param dataSet 업무파라미터들이 정의되어 있는 DataSet
     * @param onlineCtx
     * @param onlyPrintField 필드만 출력할 것인지 여부
     */
    public static void writeOperLog(IDataSet dataSet, IOnlineContext onlineCtx, boolean onlyPrintField, int logLevel) {
        Log log = getOperLog(onlineCtx);
        Map map = dataSet.getFieldMap();
        Iterator<IRecordSet> iter = null;
        if(onlyPrintField) {
            if(map!=null) {
                if(ERROR_LEVEL==logLevel) {
                    if(log.isErrorEnabled())log.error(map);
                } else if(INFO_LEVEL == logLevel) {
                    if(log.isInfoEnabled())log.info(map);
                } else {
                    if(log.isDebugEnabled())log.debug(map);
                }
            }
        } else {
            if(map!=null) {
                if(log.isInfoEnabled()) log.info(map);
                iter = dataSet.getRecordSets();
                IRecordSet rs = null;
                while(iter.hasNext()) {
                    rs = iter.next();
                    if(rs!=null && rs.getRecordCount() >0 ) { //업무팀 권용하 과장의 요청으로 Record 건수가 없는 경우는 출력하지 않도록 함.
                        writeOperLog(rs, onlineCtx, logLevel);
                    }
                }
            }
        }
    }
    
    /**
     * RecordSet에 담긴 정보들을 출력하기 위한 메소드
     *  
     * @param recordSet
     * @param onlineCtx 
     */
    public static final void writeOperLog(IRecordSet recordSet, IOnlineContext onlineCtx, int logLevel) {
        Log log = getOperLog(onlineCtx);
        if(ERROR_LEVEL==logLevel) {
            if(log.isErrorEnabled())log.error(recordSet.toString());
        } else if(INFO_LEVEL == logLevel) {
            if(log.isInfoEnabled())log.info(recordSet.toString());
        } else {
            if(log.isDebugEnabled())log.debug(recordSet.toString());
        }
    }
    
    /**
     * DataSet의 key 배열을 넘겨주면 DataSet에서 해당 key값에 대한 value를 출력한다. 
     * @param keyArr
     * @param dataSet
     * @param onlineCtx void
     */
    public  static void writeOperLog(String[] keyArr, IDataSet dataSet,  IOnlineContext onlineCtx,  int logLevel) {
        Log log = getOperLog(onlineCtx);
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        if(keyArr != null) {
            for(int i=0; i<keyArr.length; i++) {
                if(i!=0) sb.append(", ");
                sb.append(keyArr[i]);
                sb.append("=");
                sb.append(dataSet.getField(keyArr[i])); 
            }
        }
        sb.append("}");
        if(ERROR_LEVEL==logLevel) {
            if(log.isErrorEnabled())log.error(sb.toString());
        } else if(INFO_LEVEL == logLevel) {
            if(log.isInfoEnabled())log.info(sb.toString());
        } else {
            if(log.isDebugEnabled())log.debug(sb.toString());
        }
    }
    
    /**
     * 넘겨받은 String 파라미터를 운영자 로그에 바로 출력한다.
     *  
     * @param str
     * @param onlineCtx void
     */
    public  static final void writeOperLog(String str, IOnlineContext onlineCtx, int logLevel) {
        Log log = getOperLog(onlineCtx);
        if(ERROR_LEVEL==logLevel) {
            if(log.isErrorEnabled())log.error(str);
        } else if(INFO_LEVEL == logLevel) {
            if(log.isInfoEnabled())log.info(str);
        } else {
            if(log.isDebugEnabled())log.debug(str);
        }
    }
}
