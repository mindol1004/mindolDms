package fwk.deferred.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nexcore.framework.core.util.DateUtils;
import nexcore.framework.deferred.DeferredStatus;
import nexcore.framework.deferred.dao.DeferredDAO;
import nexcore.framework.integration.ibatis.IRowHandler;

/**
 * 디퍼드 로그 DAO
 */
public class DeferredLogDAO extends DeferredDAO {

	// SQL Map id
	private static final String DEFERRED_LOG = "deferred.log";

	private static final String UPDATE_DEFERRED_LOG_STMT = DEFERRED_LOG + ".UPDATE_DEFERRED_LOG";
	private static final String UPDATE_DEFERRED_LOG_STMT1 = DEFERRED_LOG + ".UPDATE_DEFERRED_LOG1";
	private static final String UPDATE_DEFERRED_LOG_STMT2 = DEFERRED_LOG + ".UPDATE_DEFERRED_LOG2";
	private static final String SELECT_DEFERRED_LOG_STMT = DEFERRED_LOG + ".SELECT_DEFERRED_LOG";
	private static final String INSERT_DEFERRED_LOG_STMT = DEFERRED_LOG + ".INSERT_DEFERRED_LOG";
	private static final String SELECT_DEFERRED_REP_LOG_STMT = DEFERRED_LOG + ".SELECT_REP_DEFERRED_LOG";
	private static final String SELECT_LAST_DEFERRED_LOG_STMT = DEFERRED_LOG + ".SELECT_LAST_DEFERRED_LOG";
	private static final String UPDATE_VOLATILE_LOG = DEFERRED_LOG + ".UPDATE_VOLATILE_LOG";
	private static final String SELECT_VOLATILE_LOG = DEFERRED_LOG + ".SELECT_VOLATILE_LOG";
	private static final String UPDATE_RESTORE_LOG = DEFERRED_LOG + ".UPDATE_RESTORE_LOG";

	public void init() {
	}

	/**
	 * deferred log 의 최대 값 조회
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public long getLastExecutedSeqNo(String logBzopDt, long from, long to) {
		long lastSequence = 0;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("LOG_BZOP_DT", logBzopDt);
		param.put("FROM_SEQ", from);
		param.put("TO_SEQ", to);
		List<Map<String, Object>> list = sqlManager.queryForList(SELECT_LAST_DEFERRED_LOG_STMT, param);
		if (list != null && list.size() > 0) {
			Map<String, Object> row = list.get(0);
			if (row != null)
				lastSequence = (Long) row.get("LAST_SEQUENCE_NO");
		}
		return lastSequence;
	}

	/**
	 * Select 된 데이터에 대한 후행/후처리 로그 저장
	 * 
	 * @param reqDS
	 * @param context
	 * @param status
	 * @param description
	 */
	public boolean exists(String logBzopDt, long seqNo) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("LOG_BZOP_DT", logBzopDt);
		param.put("SEQ_NO", seqNo);
		List list = sqlManager.queryForList(SELECT_DEFERRED_LOG_STMT, param);
		return list != null && list.size() > 0;
	}

	/**
	 * deferred log 상태 업데이트
	 */
	public int update(String logBzopDt, long seqNo, String targetTxId, DeferredStatus status, String decription, int exeCnt) {
		// 후 deferred log 상태 업데이트
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("LOG_BZOP_DT", logBzopDt);
		param.put("SEQ_NO", seqNo);
		param.put("TARGET_TR_CD", targetTxId);
		param.put("STATUS", status.getId());
		param.put("EXE_CNT", exeCnt);
		param.put("DESCRIPTION", decription);

		String yyyyMMddHHmmssSSS = DateUtils.getCurrentDate("yyyyMMddHHmmssSSS");
		param.put("CR_DATE", yyyyMMddHHmmssSSS);
		param.put("UP_DATE", yyyyMMddHHmmssSSS);
		return sqlManager.update(UPDATE_DEFERRED_LOG_STMT, param);
	}

	/**
	 * deferred log 상태 업데이트
	 */
	public int update(String logBzopDt, long seqNo, String targetTxId, String sourceTxId, DeferredStatus status, String decription, int exeCnt) {
		// 후 deferred log 상태 업데이트
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("LOG_BZOP_DT", logBzopDt);
		param.put("SEQ_NO", seqNo);
		param.put("TARGET_TR_CD", targetTxId);
		param.put("SOURCE_TR_CD", sourceTxId);
		param.put("STATUS", status.getId());
		param.put("EXE_CNT", exeCnt);
		param.put("DESCRIPTION", decription);

		String yyyyMMddHHmmssSSS = DateUtils.getCurrentDate("yyyyMMddHHmmssSSS");
		param.put("CR_DATE", yyyyMMddHHmmssSSS);
		param.put("UP_DATE", yyyyMMddHHmmssSSS);
		return sqlManager.update(UPDATE_DEFERRED_LOG_STMT1, param);
	}

	/**
	 * deferred log 상태 업데이트
	 * 
	 * @param seqNo
	 * @param targetTxId
	 * @param status
	 * @param decription
	 * @param exeCnt
	 */
	public int update(String logBzopDt, long seqNo, String targetTxId, String sourceTxId, DeferredStatus status, String decription, int exeCnt, String keyTxId) {
		// deferred log 상태 업데이트
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("LOG_BZOP_DT", logBzopDt);
		param.put("SEQ_NO", seqNo);
		param.put("TARGET_TR_CD", targetTxId);
		param.put("SOURCE_TR_CD", sourceTxId);
		param.put("STATUS", status.getId());
		param.put("EXE_CNT", exeCnt);
		param.put("DESCRIPTION", decription);
		param.put("KEY_TARGET_TR_CD", keyTxId);

		String yyyyMMddHHmmssSSS = DateUtils.getCurrentDate("yyyyMMddHHmmssSSS");
		param.put("CR_DATE", yyyyMMddHHmmssSSS);
		param.put("UP_DATE", yyyyMMddHHmmssSSS);
		return sqlManager.update(UPDATE_DEFERRED_LOG_STMT2, param);
	}

	/**
	 * Select 된 데이터에 대한 후행/후처리 로그 저장
	 * 
	 * @param reqDS
	 * @param context
	 * @param status
	 */
	public int insert(String logBzopDt, long seqNo, String targetTxId, String srcTxID, DeferredStatus status) {
		return insert(logBzopDt, seqNo, targetTxId, srcTxID, status, null);
	}

	/**
	 * Select 된 데이터에 대한 후행/후처리 로그 저장
	 * 
	 * @param reqDS
	 * @param context
	 * @param status
	 * @param description
	 */
	public int insert(String logBzopDt, long seqNo, String targetTxId, String sourceTxId, DeferredStatus status, String description) {
		return insert(logBzopDt, seqNo, targetTxId, sourceTxId, status, description, 0);
	}

	/**
	 * Select 된 데이터에 대한 후행/후처리 로그 저장
	 * 
	 * @param reqDS
	 * @param context
	 * @param status
	 * @param description
	 */
	public int insert(String logBzopDt, long seqNo, String targetTxId, String sourceTxId, DeferredStatus status, String description, int exeCnt) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("LOG_BZOP_DT", logBzopDt);
		param.put("SEQ_NO", seqNo);
		param.put("TARGET_TR_CD", targetTxId);
		param.put("SOURCE_TR_CD", sourceTxId);
		param.put("STATUS", status.getId());
		param.put("DESCRIPTION", description);
		param.put("EXE_CNT", exeCnt);

		String yyyyMMddHHmmssSSS = DateUtils.getCurrentDate("yyyyMMddHHmmssSSS");
		param.put("CR_DATE", yyyyMMddHHmmssSSS);
		param.put("UP_DATE", yyyyMMddHHmmssSSS);
		return sqlManager.update(INSERT_DEFERRED_LOG_STMT, param);
	}

	//	/**
	//	 * 재처리를 위한 Deferred Log 조회
	//	 * 
	//	 * @return
	//	 */
	//	@SuppressWarnings("unchecked")
	//	public List<Map<String, Object>> getDeferredLogForRetry(String logBzopDt, long from, long to, int maxRetryCnt, int maxRead) {
	//		// 재처리 Query 조건
	//		Map<String, Object> param = new HashMap<String, Object>();
	//		param.put("LOG_BZOP_DT", logBzopDt);
	//		param.put("STATUS_FAIL", DeferredStatus.FAIL.getId());
	//		param.put("STATUS_LOST", DeferredStatus.LOST_SEQ.getId());
	//		param.put("MAX_RETRY", maxRetryCnt);
	//		param.put("STATUS_RETRY", DeferredStatus.RETRY.getId());
	//		param.put("FROM_SEQ", from);
	//		param.put("TO_SEQ", to);
	//		param.put("MAX_READ", maxRead);
	//		return sqlManager.queryForList(SELECT_DEFERRED_REP_LOG_STMT, param);
	//	}

	/**
	 * 재처리를 위한 Deferred Log 조회
	 * 
	 * @return
	 */
	public void getDeferredLogForRetry(String logBzopDt, long from, long to, int maxRead, int failRetryCnt, int lostRetryCnt, IRowHandler rowHandler) {
		// 재처리 Query 조건
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("LOG_BZOP_DT", logBzopDt);
		param.put("STATUS_FAIL", DeferredStatus.FAIL.getId());
		param.put("STATUS_LOST", DeferredStatus.LOST_SEQ.getId());
		param.put("FAIL_RETRY", failRetryCnt);
		param.put("LOST_RETRY", lostRetryCnt);
		param.put("STATUS_RETRY", DeferredStatus.RETRY.getId());
		param.put("FROM_SEQ", from);
		param.put("TO_SEQ", to);
		param.put("MAX_READ", maxRead);
		sqlManager.queryWithRowHandler(SELECT_DEFERRED_REP_LOG_STMT, param, rowHandler);
	}

	/**
	 * 재처리를 위한 Deferred Log 조회
	 * 
	 * @return
	 */
	public void getVolatileLog(String logBzopDt, long from, long to, IRowHandler rowHandler) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("LOG_BZOP_DT", logBzopDt);
		param.put("FROM_SEQ", from);
		param.put("TO_SEQ", to);
		param.put("FROM_STATUS", DeferredStatus.IN_QUEUE.getId());
		param.put("TO_STATUS", DeferredStatus.RETRY.getId());
		sqlManager.queryWithRowHandler(SELECT_VOLATILE_LOG, param, rowHandler);
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @param maxRead
	 * @return
	 */
	public int restoreVolatileLog(String logBzopDt, long from, long to) {
		// Q 상태로 남아있는 데이터를  'R' 로 업데이트
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("LOG_BZOP_DT", logBzopDt);
		param.put("FROM_SEQ", from);
		param.put("TO_SEQ", to);
		param.put("FROM_STATUS", DeferredStatus.IN_QUEUE.getId());
		param.put("TO_STATUS", DeferredStatus.RETRY.getId());

		String yyyyMMddHHmmssSSS = DateUtils.getCurrentDate("yyyyMMddHHmmssSSS");
		param.put("CR_DATE", yyyyMMddHHmmssSSS);
		param.put("UP_DATE", yyyyMMddHHmmssSSS);
		return sqlManager.update(UPDATE_VOLATILE_LOG, param);
	}

	/**
	 * deferred log 상태 업데이트
	 */
	public int updateRestore(String logBzopDt, long seqNo, String targetTxId, DeferredStatus status, String decription, int exeCnt, String updDate) {
		// 후 deferred log 상태 업데이트
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("LOG_BZOP_DT", logBzopDt);
		param.put("SEQ_NO", seqNo);
		param.put("TARGET_TR_CD", targetTxId);
		param.put("FROM_STATUS", DeferredStatus.IN_QUEUE.getId());
		param.put("TO_STATUS", status.getId());
		param.put("EXE_CNT", exeCnt);
		param.put("DESCRIPTION", decription);
		param.put("UP_DATE", updDate);
		return sqlManager.update(UPDATE_RESTORE_LOG, param);
	}

}
