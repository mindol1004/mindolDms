package nexcore.framework;

import java.util.HashMap;
import java.util.Map;

import nexcore.framework.bat.IBatchContext;
import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.core.exception.BizRuntimeException;

import org.apache.commons.logging.Log;

/**
 * <ul>
 * <li>업무 그룹명 : NEXBANK/샘플</li>
 * <li>서브 업무명 : BEDU005</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2013-05-16</li>
 * <li>작성자 : admin (admin)</li>
 * </ul>
 */
public class BEDU005 extends AbsBatchComponent {
	
	public BEDU005() {
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
		Log log = getLog(context);
		
		try {

			// 파라미터 설정
			// => input, output 파라미터를 동일 Map객체를 이용해서 주고 받는다.
			Map map = new HashMap();
			map.put("PL_YEAR", "2015"); // input parameter
			
			// 1.프로시저 호출
			dbSelectSingle("P001", map, context);
			
			// 2.함수 호출
//			map = new HashMap();
//			map.put("PARAM1", "T");
//			Integer value = (Integer)dbExecuteProcedure("F001", map, context);
//			log.debug("################# Func. Result : " + value);
			
		} catch(Exception e) {
			throw new BizRuntimeException("M00001", e); //오류가 발생했습니다.
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
    
}
