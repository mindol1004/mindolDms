package fwk.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import nexcore.framework.core.exception.FwkRuntimeException;
import nexcore.framework.core.ioc.ComponentRegistry;

import org.apache.commons.lang.StringUtils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import fwk.constants.DmsConstants;
import fwk.sftp.SFTPConnManager;

public class SFTPUtils {

    /**
     * 파일을 FTP서버에 업로드한다. 
     *  
     * @param upFileFulPth 업로드할 파일의 파일명까지 포함된 전체 경로(ex. /app/runtime/.../test.txt)
     * @param chngDir 업로드할 서버에서, cd(Change Directory)를 해야하는 경우 
     */
    public static void put(String upFileFulPth, String chngDir) {
        File file = new File(upFileFulPth);
        put(file, chngDir);
    }
    
    /**
     * 파일을 FTP서버에 업로드한다. 
     * @param file 업로드할 파일의 파일
     * @param chngDir 업로드할 서버에서, cd(Change Directory)를 해야하는 경우 
     */
    public static void put(File file, String chngDir) {
        ChannelSftp channel =  getConnect();
        if(!file.isFile() || !file.exists() ) {
            throw new FwkRuntimeException("SKFE5004", new String[]{"SFTP upload","No upload File"});
        }
        
        InputStream localFileStream = null;
        try {
            localFileStream = new FileInputStream(file);
            if(StringUtils.isNotEmpty(chngDir)) channel.cd(chngDir);//디렉토리 변경
            channel.put(localFileStream, file.getName());
        } catch (FileNotFoundException e) {
            throw new FwkRuntimeException("SKFE5004", new String[]{"SFTP upload","No upload file.[path : "+file.getAbsolutePath()+"]"}, e);
        } catch (SftpException e) {
            throw new FwkRuntimeException("SKFE5005", new String[]{"SFTP upload"}, e);
        } finally {
            if(localFileStream != null) {
                try {
                    localFileStream.close();
                } catch (IOException e) {
                    throw new FwkRuntimeException("SKFE5005", new String[]{"SFTP upload"}, e);
                }
            }
            disConnect(channel);
        }
    }
    /**
     * FTP서버에서 파일을 다운로드한다.
     *  
     * @param saveFileFulPth FTP서버에서 다운로드시 저장할 경로와 파일명이 포함된 전체 경로(ex. C:\\projects\\runtime\\...\\test.txt)
     * @param svrFileFulPth 다운로드하고자 하는 서버에 존재하는 파일의 경로와 파일명이 포함된 전체 경로 
     * @param chnPth  다운로드 시, cd(Change Directory)를 해야하는 경우 
     */
    public static void get(String saveFileFulPth, String svrFileFulPth, String chngDir) {
        ChannelSftp channel =  getConnect();
        File file = new File(saveFileFulPth);
        if(!file.exists()) {
            File parentPath = new File(file.getParent());
            parentPath.mkdirs();
        }
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            if(StringUtils.isNotEmpty(chngDir)) channel.cd(chngDir);//디렉토리 변경
            channel.get(svrFileFulPth, os);
        } catch (SftpException e) {
            throw new FwkRuntimeException("SKFE5005", new String[]{"SFTP upload"}, e);
        } catch (FileNotFoundException e) {
            throw new FwkRuntimeException("SKFE5004", new String[]{"SFTP upload","No upload file.[path : "+saveFileFulPth+"]"}, e);
        }  finally {
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    throw new FwkRuntimeException("SKFE5005", new String[]{"SFTP upload"}, e);
                }
            }
            disConnect(channel);
        }
    }
    
    /**
     * FTP서버의 지정된 폴더에 파일리스트를 취득한다. 
     *  
     * @param chngDir
     * @return String
     */
    public static Vector<LsEntry> ls(String chngDir, String fileNamePttrn) {
        ChannelSftp channel =  getConnect();
        Vector<LsEntry> vt = null;
        try {
            channel.cd(chngDir);
            vt = channel.ls(fileNamePttrn);
        } catch (SftpException e) {
            if(2==e.id) {
                return new Vector<LsEntry>(0);
            } else {
                throw new FwkRuntimeException("SKFE5005", new String[]{"SFTP command fail. [Change Dir:"+chngDir+", File name pattern:"+fileNamePttrn+"]"}, e);
            }
            
        } finally {
            disConnect(channel);
        }
        return vt;
    }
    
    /**
     * 지정된 디렉토리로 변경한다.
     *  
     * @param chngDir void
     */
//    public static void cd(String chngDir) {
//        ChannelSftp channel =  getConnect();
//        if(StringUtils.isNotEmpty(chngDir)) {
//            try {
//                channel.cd(chngDir);
//            } catch (SftpException e) {
//                throw new FwkRuntimeException("SKFE5005", new String[]{"This Directory not exist. ["+chngDir+"]"}, e);
//            }
//        }
//    }
    
    public static ChannelSftp getConnect() {
        SFTPConnManager manager =  (SFTPConnManager)ComponentRegistry.lookup(DmsConstants.SFTP_MANAGER);
        ChannelSftp channel = manager.connect();
        return channel;
    }
    
    public synchronized static void disConnect(ChannelSftp channel) {
        channel.disconnect();
    }
}
