package nexcore.framework;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import nexcore.framework.bat.IBatchContext;
import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.bat.base.AutoCommitRecordHandler;
import nexcore.framework.bat.base.DummyRecordHandler;
import nexcore.framework.bat.util.CSVFileTool;
import nexcore.framework.bat.util.SAMFileTool;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.BaseUtils;

import org.apache.commons.logging.Log;

/**
 * <ul>
 * <li>업무 그룹명 : NEXBANK/샘플</li>
 * <li>서브 업무명 : BEDU003</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2013-05-16</li>
 * <li>작성자 : admin (admin)</li>
 * </ul>
 */
public class BEDU003 extends AbsBatchComponent {
	
	public BEDU003() {
		super();
	}
	
    /**
     * 배치 전처리 메소드. 
     * 여기서 Exception 발생시 execute() 메소드는 실행되지 않고, afterExecute() 는 실행됨
     */
    public void beforeExecute(IBatchContext context) {
    
    }

    /**
     * 배치 메인 메소드
     */
	public void execute(final IBatchContext context) {
        //SAM 파일 처리를 위한 준비
		final SAMFileTool samTool = new SAMFileTool();
    	samTool.setEncoding("UTF-8");
    	samTool.addColumnInfo("ID", 10, SAMFileTool.TYPE_STRING);
    	samTool.addColumnInfo("NAME", 20, SAMFileTool.TYPE_STRING);
        
    	//File file = new File(context.getInParameter("FILE")); // input file을 파라미터에서 가져온다.
    	File file = new File("c:/projects/file.dat");
		BufferedInputStream in = null;
		
        try {
        	//처리 진행률 표시를 위한 설정 (전체건수)
        	in = new BufferedInputStream(new FileInputStream(file));
        	samTool.setInputStream(in);
        	samTool.initialize();
        	DummyRecordHandler dhr = new DummyRecordHandler();
        	samTool.readRecordSetFromInputStream("RS1", dhr);
        	context.setProgressTotal(dhr.getCurrentSize()); // 전체건수 설정
        	
        	//본업무 처리 :: 파일을 읽어서 처리하기
        	in = new BufferedInputStream(new FileInputStream(file));
        	samTool.setInputStream(in);
        	samTool.initialize();
        	samTool.readRecordSetFromInputStream("RS1", makeRecordHandler(context)); // 파일읽기
        } catch(Exception e) {
        	throw new BizRuntimeException("M00001", e); //오류가 발생했습니다.
        } finally {
        	try {
        		if (in != null) in.close();
        	} catch(Exception e) {
        		throw new BizRuntimeException("M00001", e); //오류가 발생했습니다.
        	}
        }
        
	}
	
	/**
	 * 배치 후처리 메소드. 
	 * beforeExecute(), execute() 의 Exception 발생 여부와 관계없이 이 메소드는 실행됨
	 */
    public void afterExecute(IBatchContext context) {
        if (super.exceptionInExecute == null) {
            // execute() 정상인 경우
            
        }else {
            // execute() 에서 에러 발생할 경우
            
        }
    }
    
    public AutoCommitRecordHandler makeRecordHandler(IBatchContext context) {
    	
    	AutoCommitRecordHandler rh = new AutoCommitRecordHandler(this, context) {
    		Log log = getLog(context);
    		int cnt = 0;
    		
    		@Override
    		public void handleRecord(IRecord row) {
    			
    			//디버깅 목적으로 로그출력...
    			cnt++;
    			if (log.isDebugEnabled()) {
    				log.debug(cnt + " :: ID >> " + row.get("ID"));
    				log.debug(cnt + " :: NAME >> " + row.get("NAME"));
    			}
    			
    			//본업무 처리로직 구현
    			dbInsert("I001", row, context); // DB 입력
    			context.setProgressCurrent(getCurrentRecordCount()); //처리 진행율 표시
    		}
    	};
    	
    	// RecordHandler 속성 설정
    	rh.setAddBatchMode(true);
    	rh.setCommitCount(100);  // commit count 설정
    	rh.setStopWhenException(false);
    	return rh;
    }

}
