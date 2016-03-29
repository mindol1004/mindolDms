package nexcore.framework;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

import nexcore.framework.bat.IBatchContext;
import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.bat.base.AbsRecordHandler;
import nexcore.framework.bat.util.CSVFileTool;
import nexcore.framework.bat.util.SAMFileTool;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.exception.BizRuntimeException;

/**
 * <ul>
 * <li>업무 그룹명 : NEXBANK/샘플</li>
 * <li>서브 업무명 : BEDU002</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2013-05-16</li>
 * <li>작성자 : admin (admin)</li>
 * </ul>
 */
public class BEDU002 extends AbsBatchComponent {
	
	public BEDU002() {
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
		
		//CSV(구분자방식) 파일 처리를 위한 준비 >>> 고정길이방식의 파일은 가이드문서를 참조바람
        final CSVFileTool csvtool = new CSVFileTool();
        OutputStream fout = null;
        
        try {
        	File file = new File(context.getInParameter("FILE")); // input file을 파라미터에서 가져온다.
			fout = new BufferedOutputStream(new FileOutputStream(file));
        	csvtool.setOutputStream(fout);
        	csvtool.setEncoding("MS949");  // 파일인코딩 설정
        	
        	// 파일 레이아웃 정의
        	csvtool.addColumnInfo("ACNT_NO", SAMFileTool.TYPE_STRING);
        	csvtool.addColumnInfo("TYPE", SAMFileTool.TYPE_STRING);
        	csvtool.addColumnInfo("AMOUNT", SAMFileTool.TYPE_STRING);
        	csvtool.addColumnInfo("TX_TIMESTAMP", SAMFileTool.TYPE_STRING);
        	csvtool.setDelimiterChar(',');
        	csvtool.initialize();
        	
        	// 진행률 표시 설정
        	IRecord rec = dbSelectSingle("S001", new HashMap(), context);
        	context.setProgressTotal(rec.getLong(0));
        	
        	dbSelect("S002", new HashMap(), makeRecordHandler(csvtool), context);  //DB 조회
        } catch (FileNotFoundException e) {
    		throw new BizRuntimeException("M00009", e);
        } catch (Exception e) {
        	throw new BizRuntimeException("M00001", e);
        } finally {
        	try {
        		if (fout != null) fout.close();
        	} catch (Exception e) {
        		throw new BizRuntimeException("M00001", e);
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
    
    public AbsRecordHandler makeRecordHandler(final CSVFileTool csvtool) {
    	
    	AbsRecordHandler rh = new AbsRecordHandler(batchContext) {
    		
    		@Override
    		public void handleRecord(IRecord arg0) {
    			try {
    				// 필요시 데이터 가공...
    				
    				batchContext.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시 설정
    				csvtool.writeRecordToOut(arg0); // file write...
    				
    			} catch (Exception e) {
    				throw new BizRuntimeException("M00001", e); //오류가 발생했습니다.
    			}
    		}
    	};
    	
    	return rh;
    }

}
