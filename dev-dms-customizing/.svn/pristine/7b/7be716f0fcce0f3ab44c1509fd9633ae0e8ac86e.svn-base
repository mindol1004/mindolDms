package fwk.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import nexcore.framework.bat.util.SAMFileTool;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;

import org.apache.commons.logging.Log;

public class JarFileUtils {
    
    /**
     * jar파일 처리
     * paramMap values 는 아래참조
     * FILE_LOC : jar파일이 있는 디렉토리
     * PROC_DT : 처리일자
     */
    public IDataSet extractJarFiles(SAMFileTool body, Map<String, String> paramMap, Log log) throws BizRuntimeException {
        
        java.util.jar.JarFile jar = null;
        java.io.InputStream is = null;
        java.io.FileOutputStream fos = null;
        
        String samPath=paramMap.get("FILE_LOC")+"SAM/";
        File dirSam=new File(samPath);
        if (!dirSam.exists()) {
            try{
                dirSam.mkdir();
            } 
            catch(SecurityException se){
                throw new BizRuntimeException("DMS00009", se); //오류가 발생했습니다.
            }
        }
        String imgPath=paramMap.get("FILE_LOC")+"IMG/";
        File dirImg=new File(imgPath);
        if (!dirImg.exists()) {
            try{
                dirImg.mkdir();
            } 
            catch(SecurityException se){
                throw new BizRuntimeException("DMS00009", se); //오류가 발생했습니다.
            }
        }
        
        String path=paramMap.get("FILE_LOC");
        File dirFile=new File(path);
        File []fileList=dirFile.listFiles();
        for(File tempFile : fileList) {
          if(tempFile.isFile()) {
            String jarFile=tempFile.getName();
            try {
                jar = new java.util.jar.JarFile(path+jarFile);
            
                java.util.Enumeration<java.util.jar.JarEntry> enumEntries = jar.entries();
                while (enumEntries.hasMoreElements()) {
                    try {
                        java.util.jar.JarEntry file = (java.util.jar.JarEntry) enumEntries.nextElement();
                        if (!file.isDirectory()) {
                            String fileName = file.getName();
                            String extension = "";
                            int i = fileName.lastIndexOf('.');
                            if (i > 0) {
                                extension = fileName.substring(i+1);
                            }
                            java.io.File f = null;
                            if ("tif".equals(extension)) {
                                f = new java.io.File(path+"IMG/" + fileName);
                            } else if ("dat".equals(extension)) {
                                f = new java.io.File(path+"SAM/" + fileName);
                            }
                            if (f != null) {
                                is = jar.getInputStream(file); // get the input stream
                                fos = new java.io.FileOutputStream(f);
                                while (is.available() > 0) {  // write contents of 'is' to 'fos'
                                    fos.write(is.read());
                                }
                            }
                        }
                    } catch (Exception e) {
                        log.error("Exception:["+e.toString()+"]");
                    } finally {
                        try {
                            if (fos != null) fos.close();
                        } catch(Exception e) {
                            log.error("Exception:["+e.toString()+"]");
                        }
                        try {
                            if (is != null) is.close();
                        } catch(Exception e) {
                            log.error("Exception:["+e.toString()+"]");
                        }
                    }
                }
            } catch (IOException e) {
                log.error("Exception:["+e.toString()+"]");
            } finally {
                try {
                    try {
                        if (jar != null) jar.close();
                    } catch(Exception e) {
                        log.error("Exception:["+e.toString()+"]");
                    }
                    
                    String backPath=path+"JAR/";
                    File backDir=new File(backPath);
                    if (!backDir.exists()) {
                        try{
                            backDir.mkdir();
                        } 
                        catch(SecurityException se){
                            throw new BizRuntimeException("DMS00009", se); //오류가 발생했습니다.
                        }
                    }
                    File fileToMove = new File(path+"JAR/"+jarFile);
                    log.debug("jarFile:["+path+"JAR/"+jarFile+"]");
                    tempFile.renameTo(fileToMove);
                } catch(Exception e) {
                    log.error("Exception:["+e.toString()+"]");
                }
            }
          }
        }
        
        return listFile(body, paramMap, log);
    }
    
    private int totProcessCnt = 0;
    
    /**
     * 인터페이스파일처리
     */
    private IDataSet listFile(SAMFileTool body, Map<String, String> paramMap, Log log) throws BizRuntimeException {
        
        IDataSet responseData = new DataSet();
        totProcessCnt = 0;
        String path=paramMap.get("FILE_LOC")+"SAM/";
        File dirFile=new File(path);
        File []fileList=dirFile.listFiles();
        int failCnt = 0;
        int seq = 0;
        String sfYN = "";
        StringBuffer sb = new StringBuffer();
        for(File tempFile : fileList) {
          if(tempFile.isFile()) {
            String tempFileName=tempFile.getName();
            paramMap.put("FILE_NAME", tempFileName);
            sfYN = "S ";
            seq++;
            try{
                responseData.putRecordSet(String.valueOf(seq), readFile(body, paramMap, tempFile, log));
            } 
            catch(BizRuntimeException be){
                log.error(be.getMessage());
                failCnt++;
                sfYN = "F ";
            }
            if(!"".equals(sb.toString())) {
                sb.append(",  ");
            }
            sb.append(tempFileName);sb.append(" : ");sb.append(sfYN);
          }
        }
        responseData.putField("TOT_PROC_CNT", totProcessCnt);
        responseData.putField("PROC_FILE_NM", sb.toString());
        if (failCnt > 0) {
            responseData.putField("BAT_TASK_PROC_ST_CD", "F");
        } else {
            responseData.putField("BAT_TASK_PROC_ST_CD", "S");
        }
        
        //이미지 복사후 삭제
        String imgPath=paramMap.get("FILE_LOC")+"IMG/";
        File imgDirFile=new File(imgPath);
        File []imgFileList=imgDirFile.listFiles();
        for(File tempImgFile : imgFileList) {
            if(tempImgFile.isFile()) {
                tempImgFile.delete();
            }
        }
        
        return responseData;
    }
    
    /**
     * 파일을 IRecordSet으로 변경
     */
    private IRecordSet readFile(SAMFileTool body, Map<String, String> paramMap, File tempFile, Log log) throws BizRuntimeException {
        
        IRecordSet rs = body.makeRecordSetFromColumnInfoList("BODY");
        
        String path=paramMap.get("FILE_LOC")+"SAM/";
        int ifSeq = 1;
        
        int rLength = Integer.parseInt(paramMap.get("REC_LENG"));
        
        int processCnt = 0;
        BufferedInputStream in = null;
        String fileRename = paramMap.get("FILE_NAME");
        try {
            in = new BufferedInputStream(new FileInputStream(tempFile));
            byte[] readLineBuffer = new byte[2000];
            Map<String, String> sendMap = new HashMap<String, String>();
            Map<String, String> reSendMap = new HashMap<String, String>();
            boolean isFirst = true;
            boolean isSecond = true;
            while(true) {
                // 샘플 파일에서는 매 레코드는 EOL 문자로 구분됨.
                int readLength = SAMFileTool.readToEol(in, readLineBuffer);
                if (readLength == -1) break; // 다읽었음.
                if (readLength < 1) continue; // 의미 없는 공백 라인. skip.
                
                if (readLength != rLength) {
                    throw new BizRuntimeException("DMS00089");  // 전문의 Field 길이와 실제 파일의 각각의 칼럼 길이가 맞지 않습니다.
                }
                
                
                IRecord rec = body.readRecordFromBytes(rs, readLineBuffer, 0, readLength);
                
                //등록만 처리함
                if("A".equals(rec.get("PROC_CL_CD"))) {
                    String imgPath = paramMap.get("FILE_LOC")+"IMG/";
                    
                    StringBuffer sb = new StringBuffer();
                    sb.append(rec.get("IMG_SER_NO").trim()); sb.append("_");
                    sb.append(rec.get("IMG_FILE_NM").trim()); sb.append("_r.tif");
                    String imgFileName = sb.toString();
                    File imgFile = new File(imgPath+imgFileName);
                    
                    String svcNo = rec.get("CUST_ACNT_SVC_MGMT_NO");
                    if(sendMap.containsKey(svcNo)){
                        isFirst = false;
                        if(reSendMap.containsKey(svcNo)){
                            isSecond = true;
                        } else {
                            isSecond = false;
                        }
                    } else {
                        isFirst = true;
                        sendMap.put(svcNo, "1");
                    }
                    imgPath += svcNo + "/";
                    File imgDir=new File(imgPath);
                    if (!imgDir.exists()) {
                        rec.set("ADDT_RETN_YN", "N");
                        try{
                            imgDir.mkdir();
                        } 
                        catch(SecurityException se){
                            throw new BizRuntimeException("DMS00009", se); //오류가 발생했습니다.
                        }
                    } else {
                        if (isFirst || isSecond) {
                            rec.set("ADDT_RETN_YN", "Y");
                            if (isFirst) reSendMap.put(svcNo, "1");
                        } else {
                            rec.set("ADDT_RETN_YN", "N");
                        }
                    }
                    File imgFileToMove = new File(imgPath+imgFileName);
                    if (!imgFileToMove.exists()) {
                        //imgFile.renameTo(imgFileToMove);
                        copyFile(imgFile, imgFileToMove);
                    }
                }
                
                log.debug("RECORD BODY:["+processCnt+"]");
                rec.set("IF_PROC_DT", paramMap.get("PROC_DT"));
                rec.set("IF_FILE_NM", fileRename);
                rec.set("IF_SEQ", ifSeq);
                rs.addRecord(rec);
                processCnt++;
                ifSeq++;
            }
            
            totProcessCnt += processCnt;
            
        } catch(Exception e) {
            log.error("Exception:["+e.toString()+"]");
            if(e.getClass().equals(java.io.EOFException.class)) {
                throw new BizRuntimeException("DMS00089", e);  // 전문의 Field 길이와 실제 파일의 각각의 칼럼 길이가 맞지 않습니다.
            } else {
                throw new BizRuntimeException("DMS00009", e); //오류가 발생했습니다.
            }
        } finally {
            try {
                if (in != null) in.close();
                String backPath=path+"PROC/";
                File backDir=new File(backPath);
                if (!backDir.exists()) {
                    try{
                        backDir.mkdir();
                    } 
                    catch(SecurityException se){
                        throw new BizRuntimeException("DMS00009", se); //오류가 발생했습니다.
                    }
                }
                File fileToMove = new File(path+"PROC/"+fileRename);
                tempFile.renameTo(fileToMove);
            } catch(Exception e) {
                log.error("Exception:["+e.toString()+"]");
                throw new BizRuntimeException("DMS00009", e); //오류가 발생했습니다.
            }
        }
        
        return rs;
    }
    
    /**
     * 파일을 복사
     */
    private void copyFile(File source, File dest) throws IOException {

        InputStream input = null;

        OutputStream output = null;

        try {

            input = new FileInputStream(source);

            output = new FileOutputStream(dest);

            byte[] buf = new byte[1024];

            int bytesRead;

            while ((bytesRead = input.read(buf)) > 0) {

                output.write(buf, 0, bytesRead);

            }

        } finally {

            input.close();

            output.close();

        }

    }
    
    
}
