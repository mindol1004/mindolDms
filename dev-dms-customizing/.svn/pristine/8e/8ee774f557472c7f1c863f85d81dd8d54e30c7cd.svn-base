package fwk.sftp;

import java.util.Properties;

import org.apache.commons.logging.Log;

import nexcore.framework.core.exception.FwkRuntimeException;
import nexcore.framework.core.log.LogManager;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SFTPConnManager {

    private String host;
    private String userName;
    private String keyfilePath;
    private int sessionTimeout;
    private int connectTimeout;
    private int serverAliveInterval;
    private int serverAliveCountMax;
    
    private final int PORT = 22;
    private final String SFTP_CHN_CD = "sftp";
    private JSch jsch;
    private Session session;
    private Log log = LogManager.getFwkLog();;


    public Session getSession() {
        return session;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public void setKeyfilePath(String keyfilePath) {
        this.keyfilePath = keyfilePath;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setServerAliveInterval(int serverAliveInterval) {
        this.serverAliveInterval = serverAliveInterval;
    }
    public void setServerAliveCountMax(int serverAliveCountMax) {
        this.serverAliveCountMax = serverAliveCountMax;
    }
    public void setSessionTimeout(String sessionTimeout) {
        this.sessionTimeout = Integer.parseInt(sessionTimeout);
    }
    public void setConnectTimeout(String connectTimeout) {
        this.connectTimeout = Integer.parseInt(connectTimeout);
    }
    public void init() {
        getJSch();//JSch 인스턴스 생성
    }
    
    public JSch getJSch() {
        try {
            if(jsch==null ){
                if(log.isInfoEnabled()) log.info("JSch Instance Create");
                jsch = new JSch();
                jsch.addIdentity(keyfilePath);
                
                if(log.isInfoEnabled()) log.info("JSch RSA private key set");
            }
        } catch (JSchException e) {
            throw new FwkRuntimeException("SKFE5010", new String[]{"SFTP"}, e);
        }
        return jsch;
    }

    public synchronized ChannelSftp connect() {
        Properties config = new Properties(); 
        config.put("StrictHostKeyChecking", "no");//SSH로 접속할 때 서버에서 연결여부를 물어보는 구문을 skip하기 위함.  꼭 설정해야함. 
        ChannelSftp channel = null;
        try {
            if(session == null || !session.isConnected()) {
                session=getJSch().getSession(userName, host, PORT);
                session.setConfig(config);
//                session.setConfig("GSSAPIAuthentication","no"); 
                session.setServerAliveInterval(serverAliveInterval); 
                session.setServerAliveCountMax(serverAliveCountMax); 
                session.setConfig("TCPKeepAlive","yes");
                session.setTimeout(sessionTimeout);//15분으로 설정
//                session.connect(connectTimeout);//10초로 설정
                session.connect();//10초로 설정
                if(log.isInfoEnabled()) log.info("JSch Session  connect success");
            }
            channel = (ChannelSftp) session.openChannel(SFTP_CHN_CD);
            channel.connect(connectTimeout);
            if(log.isInfoEnabled()) log.info("SFTP Channel  connect success");
        } catch (JSchException e) {
            if(session != null&& session.isConnected()) {
                session.disconnect();
                if(log.isErrorEnabled()) log.error("Session was disconnected by Exception");
            }
            throw new FwkRuntimeException("SKFE5010", new String[]{"SFTP"}, e);
        }
        return channel;
    }


}
