package nexcore.framework;

import java.util.HashMap;
import java.util.Map;

import nexcore.framework.bat.IBatchContext;
import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.bat.base.AutoCommitRecordHandler;
import nexcore.framework.bat.base.DBSession;
import nexcore.framework.core.data.IRecord;

/**
 * <ul>
 * <li>업무 그룹명 : NEXBANK/샘플</li>
 * <li>서브 업무명 : BEDU004</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2013-05-16</li>
 * <li>작성자 : admin (admin)</li>
 * </ul>
 */
public class BEDU004 extends AbsBatchComponent {
	
	public BEDU004() {
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
		final DBSession readSession = dbNewSession(context, true);

        Map paramMap = new HashMap();
        paramMap.put("ACNT_NO", context.getInParameter("ACNT_NO"));
        
        IRecord rec = dbSelectSingle("S001", paramMap, context);
		context.setProgressTotal(rec.getLong(0)); // 진행률 표시
		
        // 업무로직 시작
		dbSelect("S002", paramMap, makeMainRecordHandler(context), readSession);
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
    
    public AutoCommitRecordHandler makeMainRecordHandler(IBatchContext context) {
    	
    	// 기본 데이터 소스말고 다른 데이터소스를 지정할때 선언한다.
    	final DBSession writeSession = dbNewSession(context, true, null); 
    	
    	AutoCommitRecordHandler rh = new AutoCommitRecordHandler(this, context) {
    		@Override
    		public void handleRecord(IRecord row) {
    			// 진행률 표시
    			context.setProgressCurrent(getCurrentRecordCount());
    			
    			// 필요시 데이터 가공
    			// 1. 기본 데이터소스를 선택할때.. 마지막 인자값이 context 이다.
    			dbInsert("I001", row, context);
    			
    			// 2. 다른 데이터소스를 선택할때 Session 을 전달해 준다.
    			//dbInsert("I001", row, writeSession);
    		}
    	};
    	
    	rh.setAddBatchMode(false);   //디버깅시에는 false,  ue 로 설정함.
    	rh.setCommitCount(100);
    	rh.setStopWhenException(false);
    	return rh;
    }
}
