package fwk.batch.agent;

import java.util.Map;

import fwk.flat.FlatHeaderSpec;
import nexcore.framework.bat.IBatchContext;
import nexcore.framework.bat.base.IDBInputParameterWrapper;
import nexcore.framework.core.data.CaseIgnoreHashMap;

public class DBInputParameterWrapper implements IDBInputParameterWrapper {

	public void init() {
	}

	public void destroy() {
	}

	private String shorten(String s, int len) {
		if (s.length() < len/2) {
			return s;
		}else {
			byte[] bb = s.getBytes();
			if (bb.length <= len) {
				return s;
			}else {
				return new String (bb, 0, len).trim();
			}
		}
	}

	private String getBatchCallUserId(IBatchContext context) {
		String usrNo = context.getJobExecution().getOperatorId();
		/*
		 * context.getJobExecution().getOperatorId() 에는 다음 값이 들어있음.
		 *
		 * 1) 온디멘드 배치 실행 : "OD:"+단말 로그인 ID 6자리
		 * 2) 스케줄러 자동 실행 : 스케줄러 서버 ID ("djobs01", "rjobs01")
		 * 3) 스케줄러 강제 실행 : 강제 실행한 운영자의 스케줄러 로그인 ID.
		 */
		if (usrNo == null || usrNo.indexOf("jobs") > -1) {
//			if(bankCd.equalsIgnoreCase("028")){
//				usrNo= "888100";
//			}else if (bankCd.equalsIgnoreCase("023")){
//				usrNo= "888200";
//			}else if (bankCd.equalsIgnoreCase("406")){
//				usrNo= "888300";
//			}else if (bankCd.equalsIgnoreCase("621")){
//				usrNo= "888400";
//			}else {
//				usrNo= "888100"; // TODO 이부분 공통팀과 상의하여 교정해야 함
//			}
			return "batch01";
		}else if (usrNo.startsWith("OD:")) {
			// 로컬 온디멘드이고, 로그인 하지 않은 경우 => "LOCBAT"
			if(usrNo.indexOf("OD:Anonymous") > -1 && "L".equals(context.getJobExecution().getInParameters().get("STAGE_CODE")))
				return "LOCBAT"; // 온디맨드 호출일 경우 "OD:"를 때어냄
			return usrNo.substring(3);
		}else {
			if (usrNo.length() > 6) {
				throw new RuntimeException("사용자ID 오류 ["+usrNo+"]");
			}else {
				return usrNo;
			}
		}
	}

	public Object filter(final Object param, final IBatchContext context) {
		if (param == null || param instanceof Map) {
			// param 이 IRecord 일 경우는 map.put 으로는 값들이 들어가지 않는다. (헤더에정의되지 않은 값이므로) 그래서 아래와 같이 한다.
			return new CaseIgnoreHashMap() {
				private static final long serialVersionUID = 1L;

				public Object get(Object key) {
					if ("lastChngGuid".equalsIgnoreCase(String.valueOf(key))) { // GUID
						return shorten(context.getJobExecution().getJobExecutionId(), FlatHeaderSpec.GLOB_ID.length());
					} else if ("lastChngUserNo".equalsIgnoreCase(String.valueOf(key))) { // 사용자ID
						return getBatchCallUserId(context);
					}  else {
						return param == null ? null : ((Map) param).get(key);
					}
				}

				public Object put(Object key, Object value) {
					return param == null ? null : ((Map) param).put(key, value);
				}

				public void putAll(Map m) {
					if (param != null) {
						((Map) param).putAll(m);
					}
				}

				public Object remove(Object arg0) {
					return param == null ? null : ((Map) param).remove(arg0);
				}
			};
		}
		return param;
	}

}
