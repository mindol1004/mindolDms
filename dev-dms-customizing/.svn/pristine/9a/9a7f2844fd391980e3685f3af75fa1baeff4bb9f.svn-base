/**
 * 
 */
package fwk.erfif.sapjco;

import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 황문수
 *
 */
public class CallInfo extends HashSet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4231130378738671502L;
	
	public static String ERP_SPCL_ID = "erp_spcl_id";	// 쓰레드 처리시 로그에 남길 키값
	public static String ERP_SPCL_NM = "erp_spcl_nm";	// 쓰레드 처리시 로그에 남길 이름
	public static String RECORD_SET_ID = "recordSetId";
	public static String SQL_ID = "sqlId";
	public static String TABLE_ID = "tableId";
	public static String ERROR_RETURN_TABLE = "I_ERET";	// ERP에서 에러를 리턴할 시 주는 테이블 명칭
	
	private CallInfo() {}

	/**
	 * 
	 * @return
	 */
	public static CallInfo create() {
		return new CallInfo();
	}
	
	/**
	 * 주어진 파라미터로 CallInfo 객체를 생성한다.
	 * @param recordSetId : table에 데이터를 레코드셋 ID. SapCaller에서 이 ID로 DataSet에서 recordSet을 얻어온다.
	 * @param tableId : recordSetId로 얻어온 recordSet의 데이터를 넘길 SAP table ID.
	 * @param paramList : 이 레코드셋과 같이 넘길 파라미터.
	 * @return
	 */
	public static CallInfo create(String recordSetId, String tableId, HashMap paramList) {
		CallInfo callInfo = new CallInfo();
		
		if(StringUtils.isEmpty(recordSetId)) {
			throw new RuntimeException("CallInfo creation Exception..\nrecordSetId는 필수입력사항입니다.");
		}
		
		if(tableId == null || "".equals(tableId)) {
			throw new RuntimeException("CallInfo creation Exception..\ntableId는 필수입력사항입니다.");
		}
		
		if(paramList == null) {
			paramList = new HashMap();
		}
		
		if(StringUtils.isNotEmpty(recordSetId)) paramList.put(CallInfo.RECORD_SET_ID, recordSetId);
		//if(!(sqlId == null || sqlId.equals(""))) paramList.put(CallInfo.SQL_ID, sqlId);
		paramList.put(CallInfo.TABLE_ID, tableId);
		
		callInfo.add(paramList);
		
		return callInfo;
	}
	
	/**
	 * add Object
	 * @param recordSetId
	 * @param tableId
	 * @param paramList
	 */
	public void addInfo(String recordSetId, String tableId, HashMap paramList) {
		if(StringUtils.isEmpty(recordSetId)) {
			throw new RuntimeException("Add info Exception..\nrecordSetId는 필수입력사항입니다.");
		}
		
		if(tableId == null || "".equals(tableId)) {
			throw new RuntimeException("Add info Exception..\ntableId는 필수입력사항입니다.");
		}
		
		if(paramList == null) {
			paramList = new HashMap();
		}
		
		if(StringUtils.isNotEmpty(recordSetId)) paramList.put(CallInfo.RECORD_SET_ID, recordSetId);
		//if(!(sqlId == null || sqlId.equals(""))) paramList.put(CallInfo.SQL_ID, sqlId);
		paramList.put(CallInfo.TABLE_ID, tableId);
		
		add(paramList);
	}
}
