package fwk.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import nexcore.framework.bat.util.SAMFileTool;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.core.util.StringUtils;

public class IFDBToFileUtils {
    
    /**
     * DB TO FILE Header parse
     */
    public OutputStream HeaderParse(Map<String, String> paramMap) throws Exception {
        
        OutputStream fout = null;
        String fileLoc = StringUtils.nvl(paramMap.get("FILE_LOC"));
        String taskId = StringUtils.nvl(paramMap.get("TASK_ID"));
        String procDt = StringUtils.nvl(paramMap.get("PROC_DT"));
        String fileSeq = StringUtils.nvl(paramMap.get("FILE_SEQ"));
        String fileDt = StringUtils.nvl(paramMap.get("FILE_DT"));
        if ("".equals(fileDt)) fileDt = procDt;
        StringBuffer bf1 = new StringBuffer();
        bf1.append(fileLoc);
        bf1.append(taskId);
        bf1.append(".SKCC.");
        bf1.append(fileDt);
        bf1.append(".dat");
        String ifFile = bf1.toString();
        
        StringBuffer bf2 = new StringBuffer();
        bf2.append(taskId);
        bf2.append(".SKCC.");
        bf2.append(fileDt);
        bf2.append("_");
        bf2.append(fileSeq);
        String ifFileNmSeq = bf2.toString();
        
        String path=paramMap.get("FILE_LOC");
        File dirFile=new File(path);
        if (!dirFile.exists()) {
            try{
                dirFile.mkdir();
            } 
            catch(SecurityException se){
                throw new BizRuntimeException("DMS00009", se); //오류가 발생했습니다.
            }
        }
        
        File file = new File(ifFile); // input file을 파라미터에서 가져온다.
        fout = new BufferedOutputStream(new FileOutputStream(file));
        
        SAMFileTool header = new SAMFileTool();
        header.setOutputStream(fout);
        header.setEncoding(BaseUtils.getConfiguration("interface.file.encoding"));
        header.addColumnInfo("REC_CL_CD",     1, SAMFileTool.TYPE_STRING);
        header.addColumnInfo("FILE_NM",       30, SAMFileTool.TYPE_STRING);
        header.addColumnInfo("PROC_DT",       8, SAMFileTool.TYPE_STRING);
        header.initialize();
        
        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("REC_CL_CD", "H");
        headerMap.put("FILE_NM", ifFileNmSeq);
        headerMap.put("PROC_DT", procDt);

        header.writeMapToOut(headerMap);
        
        return fout;
    }
    
    /**
     * DB TO TAIL parse
     */
    public void tailParse(Map<String, String> paramMap, OutputStream fout) throws BizRuntimeException {
        
        String processCnt = StringUtils.nvl(paramMap.get("TOT_REC_CNT"));
        String ifFileNmSeq = StringUtils.nvl(paramMap.get("FILE_NM_SEQ"));
        String fileLoc = StringUtils.nvl(paramMap.get("FILE_LOC"));
        String taskId = StringUtils.nvl(paramMap.get("TASK_ID"));
        String procDt = StringUtils.nvl(paramMap.get("PROC_DT"));
        String fileDt = StringUtils.nvl(paramMap.get("FILE_DT"));
        String taskSTCD = StringUtils.nvl(paramMap.get("PROC_ST_CD"));
        if ("".equals(fileDt)) fileDt = procDt;
        StringBuffer bf = new StringBuffer();
        bf.append(fileLoc);
        bf.append(taskId);
        bf.append(".SKCC.");
        bf.append(fileDt);
        bf.append(".dat");
        String ifFile = bf.toString();
        
        try {
            SAMFileTool tail = new SAMFileTool();
            tail.setOutputStream(fout);
            tail.setEncoding(BaseUtils.getConfiguration("interface.file.encoding"));
            tail.addColumnInfo("REC_CL_CD",     1, SAMFileTool.TYPE_STRING);
            tail.addColumnInfo("FILE_NM",       30, SAMFileTool.TYPE_STRING);
            tail.addColumnInfo("TOT_REC_CNT",   10, SAMFileTool.TYPE_STRING);
            tail.initialize();
            
            Map<String, String> tailMap = new HashMap<String, String>();
            tailMap.put("REC_CL_CD", "T");
            tailMap.put("FILE_NM", ifFileNmSeq);
            tailMap.put("TOT_REC_CNT", StringUtils.lpad(processCnt, 10, "0"));
            
            tail.writeMapToOut(tailMap);
            
        } catch (FileNotFoundException e) {
            throw new BizRuntimeException("DMS00009", e);
        } catch (Exception e) {
            throw new BizRuntimeException("DMS00009", e);
        } finally {
            try {
                if (fout != null) fout.close();
                if ("F".equals(taskSTCD)) {
                    File file = new File(ifFile);
                    file.delete();
                }
            } catch (Exception e) {
                throw new BizRuntimeException("DMS00009", e);
            }
        }
    }
    
    /**
     * DB TO FILE Header parse
     */
    public OutputStream HeaderParseData(Map<String, String> paramMap) throws Exception {
        
        OutputStream fout = null;
        String fileLoc = StringUtils.nvl(paramMap.get("FILE_LOC"));
        String taskId = StringUtils.nvl(paramMap.get("TASK_ID"));
        String procDt = StringUtils.nvl(paramMap.get("PROC_DT"));
        String fileSeq = StringUtils.nvl(paramMap.get("FILE_SEQ"));
        String fileDt = StringUtils.nvl(paramMap.get("FILE_DT"));
        if ("".equals(fileDt)) fileDt = procDt;
        StringBuffer bf1 = new StringBuffer();
        bf1.append(fileLoc);
        bf1.append(taskId);
        bf1.append(".SKCC.");
        bf1.append(fileDt);
        bf1.append(".dat");
        String ifFile = bf1.toString();
        
        StringBuffer bf2 = new StringBuffer();
        bf2.append(taskId);
        bf2.append(".SKCC.");
        bf2.append(fileDt);
        bf2.append("_");
        bf2.append(fileSeq);
        String ifFileNmSeq = bf2.toString();
        
        String path=paramMap.get("FILE_LOC");
        File dirFile=new File(path);
        if (!dirFile.exists()) {
            try{
                dirFile.mkdir();
            } 
            catch(SecurityException se){
                throw new BizRuntimeException("DMS00009", se); //오류가 발생했습니다.
            }
        }
        
        File file = new File(ifFile); // input file을 파라미터에서 가져온다.
        fout = new BufferedOutputStream(new FileOutputStream(file));
        
        return fout;
    }
    
    /**
     * DB TO TAIL parse
     */
    public void tailParseData(Map<String, String> paramMap, OutputStream fout) throws BizRuntimeException {
        
        String fileLoc = StringUtils.nvl(paramMap.get("FILE_LOC"));
        String taskId = StringUtils.nvl(paramMap.get("TASK_ID"));
        String procDt = StringUtils.nvl(paramMap.get("PROC_DT"));
        String fileDt = StringUtils.nvl(paramMap.get("FILE_DT"));
        String taskSTCD = StringUtils.nvl(paramMap.get("PROC_ST_CD"));
        if ("".equals(fileDt)) fileDt = procDt;
        StringBuffer bf = new StringBuffer();
        bf.append(fileLoc);
        bf.append(taskId);
        bf.append(".SKCC.");
        bf.append(fileDt);
        bf.append(".dat");
        String ifFile = bf.toString();
        
        try {
            if (fout != null) fout.close();
            if ("F".equals(taskSTCD)) {
                File file = new File(ifFile);
                file.delete();
            }
        } catch (Exception e) {
            throw new BizRuntimeException("DMS00009", e);
        }
    }    
}
