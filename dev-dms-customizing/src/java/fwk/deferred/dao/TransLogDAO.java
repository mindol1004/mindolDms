package fwk.deferred.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nexcore.framework.deferred.dao.DeferredDAO;
import nexcore.framework.integration.ibatis.IRowHandler;

/**
 * 거래로그 DAO
 */
public class TransLogDAO extends DeferredDAO {

	// SQL Map id
	private static final String TRANS_LOG = "deferred.translog";

	private static final String SELECT_LOG_BZOP_DT = TRANS_LOG + ".SELECT_LOG_BZOP_DT";
	private static final String SELECT_MULTI_TRANS_LOG = TRANS_LOG + ".SELECT_MULTI_TRANS_LOG";
	private static final String SELECT_SINGLE_TRANS_LOG_STMT = TRANS_LOG + ".SELECT_TRNS_LOG";

	public void init(){
	}

	/**
	 * 로그 영업일 조회
	 * @return
	 */
	public String getLogBzopDt(String logBzopDtSelectQuery){
		String logBzopDt = null;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("INVOKE_QUERY", logBzopDtSelectQuery);
		List<Map<String, Object>> list = sqlManager.queryForList(SELECT_LOG_BZOP_DT, param);
		if (list != null && list.size() > 0) {
			Map<String, Object> row = list.get(0);
			if (row != null)
				logBzopDt = (String) row.get("LOG_BZOP_DT");
		}
		return logBzopDt;
	}


//	/**
//	 * 거래로그 범위 조회
//	 */
//	@SuppressWarnings("unchecked")
//	public List<Map<String, String>> getTransLogList(String logBsnDt, long from, long to, int maxRead) {
//		// 거래로그 Query 조건
//		Map<String, Object> queryParam = new HashMap<String, Object>();
//		queryParam.put("LOG_BSN_DT", logBsnDt);
//		queryParam.put("FROM_SEQ_NO", from);
//		queryParam.put("TO_SEQ_NO", to);
//		queryParam.put("MAX_READ", maxRead);
//		return sqlManager.queryForList(SELECT_MULTI_TRANS_LOG, queryParam);
//	}

	/**
	 * 거래로그 범위 조회
	 */
	public void getTransLogList(String logBzopDt, long from, long to, int maxRead, IRowHandler rowHandler) {
		// 거래로그 Query 조건
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("LOG_BZOP_DT", logBzopDt);
		queryParam.put("FROM_SEQ_NO", from);
		queryParam.put("TO_SEQ_NO", to);
		queryParam.put("MAX_READ", maxRead);
		sqlManager.queryWithRowHandler(SELECT_MULTI_TRANS_LOG, queryParam, rowHandler);
	}

	/**
	 * 거래로그 단건 조회
	 */
	public Map<String, String> getTransLog(String logBzopDt, long seqNo) {
		// 거래로그 Query 조건
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("LOG_BZOP_DT", logBzopDt);
		queryParam.put("SEQ_NO", seqNo);
		List<Map<String, String>> list = sqlManager.queryForList(SELECT_SINGLE_TRANS_LOG_STMT, queryParam);
		if (list != null && list.size() > 0) {
			return (Map<String, String>) list.get(0);
		}
		return null;
	}

	/**
	 * Select 된 데이터에 대한 Mark 실행
	 * 
	 */
	public void mark(String logBsnDt, long seqNo, String status) {
		// 순차 키 로직을 사용 하지 않을시 거래로그 Mark 로직(아마도 거래로그 업데이트) 이 이부분에 들어가야 함. 
	}

}
