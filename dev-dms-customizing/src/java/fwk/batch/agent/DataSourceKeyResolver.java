package fwk.batch.agent;

import nexcore.framework.bat.IBatchContext;
import nexcore.framework.bat.base.IDataSourceKeyResolver;
import nexcore.framework.core.util.StringUtils;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : 금융 프레임워크 </li>
 * <li>서브 업무명 : 배치 코어</li>
 * <li>설  명 : DataSourceKey 생성기</li>
 * <li>작성일 : 2012. 1. 06.</li>
 * <li>작성자 : 정호철</li>
 * </ul>
 */

public class DataSourceKeyResolver implements IDataSourceKeyResolver {
	
	public void init() {
	}

	public void destroy() {
	}
	
	private String getAppCode(IBatchContext context) {
		return context.getInParameter("APP_CODE");
	}

	/**
	 * 기본 DataSourceKey
	 * @param context
	 * @return 조립된 DataSourceKey 
	 */
	public String getDefaultDataSourceKey(IBatchContext context) {
		return getAppCode(context);  // 업무코드 
	}
	
	/**
	 * 기타 DataSourceKey 생성기 
	 * @param context 
	 * @param isXA 
	 * @param optionString
	 * @return 조립된 DataSourceKey 
	 */
	public String getOtherDataSourceKey(IBatchContext context, boolean isXA, String optionString) {
		/**
		 * KEY 에는 BCV, BCVWK, EDW, ODS 등의 값들이 올 수 있음
		 */
		String dsKey;
		
		if (StringUtils.isEmpty(optionString)) { // optionString이 없는 경우
			// DEFAULT DSKEY 에 밑에서 XA 여부만 조립함
			dsKey = getDefaultDataSourceKey(context);
		}else { // optionString 이 있는 경우
			dsKey = optionString;
		}
		
		return isXA ? dsKey : dsKey + "_NonXA";
	}

	// 테스트
/*
	public static void main(String[] args) {
		DataSourceKeyResolver t = new DataSourceKeyResolver();
		
		Map param = new HashMap();
		param.put("APP_CODE",  "XYZ");
		param.put("BANK_CODE", "028");
		
		JobExecution jobexe = new JobExecution();
		jobexe.setInParameters(param);
		
		BatchContext context = new BatchContext();
		context.setJobExecution(jobexe);
		
		context.setDefaultDataSourceKey(t.getDefaultDataSourceKey(context));
		
		System.out.println( t.getDefaultDataSourceKey(context) );

		System.out.println( t.getOtherDataSourceKey(context, true, null) );
		System.out.println( t.getOtherDataSourceKey(context, false, "") );

		System.out.println( t.getOtherDataSourceKey(context, true, "BCV") );
		System.out.println( t.getOtherDataSourceKey(context, false, "BCV") );
		
		System.out.println( t.getOtherDataSourceKey(context, true, "ODS") );
		System.out.println( t.getOtherDataSourceKey(context, false, "ODS") );

		System.out.println( t.getOtherDataSourceKey(context, true, "BANK_406") );
		System.out.println( t.getOtherDataSourceKey(context, false, "BANK_406") );
		
		System.out.println( t.getOtherDataSourceKey(context, true, "BANK_406_BCV") );
		System.out.println( t.getOtherDataSourceKey(context, false, "BANK_406_BCV") );

		System.out.println( t.getOtherDataSourceKey(context, true, "BANK_621_BCVWK") );
		System.out.println( t.getOtherDataSourceKey(context, false, "BANK_621_BCVWK") );
	}
	*/
}


