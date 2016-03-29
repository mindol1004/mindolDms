package fwk.deferred.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import nexcore.framework.deferred.DeferredStatus;
import nexcore.framework.deferred.IDeferredConstants;
import nexcore.framework.deferred.IDeferredSourceQueue;
import nexcore.framework.deferred.processor.AbstractDeferredSourceReader;
import nexcore.framework.integration.ibatis.IRowHandler;
import fwk.deferred.dao.DeferredLogDAO;
import fwk.deferred.dao.TransLogDAO;
import fwk.deferred.helper.DeferredRestoreFileHandler;
import fwk.flat.FlatHeaderSpec;

/**
 * 디퍼드 대상 거래 정보 조회 클래스
 */
public class DeferredSourceReader extends AbstractDeferredSourceReader {

	protected long initSeqNo; // 일련번호 대역 최소값 
	protected long lastSeqNo; // 일련번호 대역 최대값
	private String logBzopDtSelectQuery; //로그 영업일 조회 쿼리
	private Pattern disabledTxIdPattern; //제외대상 거래코드 패턴

	protected String _logBzopDt;
	protected TransLogDAO transLogDAO;
	protected DeferredLogDAO deferredLogDAO;
	protected DeferredLogDAO deferredLogWriteDAO;
	private DeferredRestoreFileHandler restoreFileHandler;

	private long currExecutedSeqNo;

	/**
	 * 일련번호 대역 최소값 
	 * @param initSeqNo 일련번호 대역 최소값
	 */
	public void setInitSeqNo(long initSeqNo) {
		this.initSeqNo = initSeqNo;
	}

	/**
	 * 일련번호 대역 최대값
	 * @param lastSeqNo 일련번호 대역 최대값
	 */
	public void setLastSeqNo(long lastSeqNo) {
		this.lastSeqNo = lastSeqNo;
	}

	/**
	 * 로그 영업일 조회 쿼리
	 * @param logBzopDtSelectQuery 로그 영업일 조회 쿼리
	 */
	public void setLogBzopDtSelectQuery(String logBzopDtSelectQuery) {
		this.logBzopDtSelectQuery = logBzopDtSelectQuery;
	}

	/**
	 * 제외대상 거래코드 패턴
	 * @param disabledTxIdPatternStr 제외대상 거래코드 패턴
	 */
	public void setDisabledTxIdPattern(String disabledTxIdPatternStr) {
		if (disabledTxIdPatternStr != null && disabledTxIdPatternStr.trim().length() > 0) {
			disabledTxIdPattern = Pattern.compile(disabledTxIdPatternStr);
		}
	}

	/**
	 * 거래로그 조회용DAO
	 * @param transLogDAO 거래로그 조회용 DAO
	 */
	public void setTransLogDAO(TransLogDAO transLogDAO) {
		this.transLogDAO = transLogDAO;
	}

	/**
	 * 디퍼드 로그 조회용 DAO
	 * @param deferredLogDAO 디퍼드 로그 조회용 DAO
	 */
	public void setDeferredLogDAO(DeferredLogDAO deferredLogDAO) {
		this.deferredLogDAO = deferredLogDAO;
	}

	/**
	 * 디퍼드 로그 쓰기용 DAO
	 * @param deferredLogWriteDAO 디퍼드 로그 쓰기용 DAO
	 */
	public void setDeferredLogWriteDAO(DeferredLogDAO deferredLogWriteDAO) {
		this.deferredLogWriteDAO = deferredLogWriteDAO;
	}

	/**
	 * 장애 발생시 정보를 파일에 관리하는 모듈
	 * @param restoreFileHandler 장애 발생시 정보를 파일에 관리하는 모듈
	 */
	public void setRestoreFileHandler(DeferredRestoreFileHandler restoreFileHandler) {
		this.restoreFileHandler = restoreFileHandler;
	}

	/**
	 * 초기화 : 일자 전환 등 초기화 필요시 호출 됨
	 */
	public void init() {
		super.init();

		transLogDAO.init();
		deferredLogDAO.init();
		deferredLogWriteDAO.init();

		// 처리대상 로그 영업일자
		getLogBzopDt();

		// 최종 처리 순번 초기화
		currExecutedSeqNo = initSeqNo;
		if (getLogger().isInfoEnabled()) {
			getLogger().info(this, "DeferredSourceReader init. logBzopDt=" + _logBzopDt + ", SeqNo(" + initSeqNo + "~" + (lastSeqNo + 1) + ")");
		}
	}

	/**
	 * 로그 영업일 조회
	 * @return 로그 영업일
	 */
	protected String getLogBzopDt() {
		if (_logBzopDt == null) {
			try {
				_logBzopDt = transLogDAO.getLogBzopDt(logBzopDtSelectQuery);
				getLogger().info(this, "logBzopDt=" + _logBzopDt);
			} catch (Exception e) {
				getLogger().error(this, "logBzopDt load fail.", e);
			}
		}
		return _logBzopDt;
	}

	/**
	 * 디퍼드 처리 상태 복원 
	 */
	public int restore(boolean reprocess) {
		int row = 0;

		// 재처리인 경우에 "Q" 상태를 "R" 상태로 변경한다.
		if (reprocess) {
			String logBzopDt = getLogBzopDt();
			if (logBzopDt == null || logBzopDt.trim().length() < 1) {
				getLogger().error(this, "Can not load logBzopDt.", null);
			} else {
				// 로그 영업일 기준으로 복원일 진행한다.
				row = restoreVolatileLog(logBzopDt, initSeqNo, lastSeqNo);
				if (getLogger().isInfoEnabled()) {
					getLogger().info(this, "restore Count=" + row);
				}
			}
		}

		return row;
	}
	
	/**
	 * 디퍼드 대상 거래로그 조회
	 * @param queue 거래로그 Queue
	 * @param reprocess 재처리 프로세서 여부
	 * @param maxReadSize 최대조회건수
	 * @param failRetryCnt 실패건 재처리 가능 횟수
	 * @param lostRetryCnt 결번건 재처리 가능 횟수
	 */
	public int read(IDeferredSourceQueue queue, boolean reprocess, int maxReadSize, int failRetryCnt, int lostRetryCnt) {
		String logBzopDt = getLogBzopDt();
		if (logBzopDt == null || logBzopDt.trim().length() < 1) {
			getLogger().error(this, "Can not load logBzopDt.", null);
		} else {
			// 재처리
			if (reprocess) {
				return readForReProcessor(queue, logBzopDt, maxReadSize, failRetryCnt, lostRetryCnt);
			}
			// 본처리
			else {
				return readForProcessor(queue, logBzopDt, maxReadSize);
			}
		}

		return -1;
	}
	
	/**
	 * 본처리를 위한 거래로그 조회
	 * @param queue 거래로그 Queue
	 * @param logBzopDt 로그 영업일
	 * @param maxReadSize 최대조회건수
	 * @return 조회 건수
	 */
	private int readForProcessor(final IDeferredSourceQueue queue, final String logBzopDt, int maxReadSize) {
		// trLogLastSeqNo 값
		long lastExecutedSeqNo = getLastExecutedSeqNo(logBzopDt);
//		if (getLogger().isDebugEnabled()) {
//			getLogger().debug(this, "lastExecutedSeqNo=" + lastExecutedSeqNo);
//		}

		if (lastExecutedSeqNo < lastSeqNo) {
			DeferredSourceRowHandler rowHandler = new DeferredSourceRowHandler() {
				public void handleRowImpl(Object obj) {
					handleRowForProcessor(queue, logBzopDt, (Map<String, String>) obj);
				}
			};

			// 현재 처리중인 일련번호보다 큰것중 첫번째 로그를 조회한다.
			transLogDAO.getTransLogList(logBzopDt, lastExecutedSeqNo, lastSeqNo, maxReadSize, rowHandler);

			if (getLogger().isDebugEnabled()) {
				getLogger().debug(this, "logBzopDt="+logBzopDt+", lastExecutedSeqNo="+lastExecutedSeqNo+", lastSeqNo="+lastSeqNo+", handleCount=" + rowHandler.handleCount);
			}

			return rowHandler.handleCount;
		} else {
			getLogger().warn(this, "currExecutedSeqNo[" + currExecutedSeqNo + "] exceed lastSeqNo[" + lastSeqNo + "]. so, can't read anymore.. please check...");
		}
		return 0;
	}

	/**
	 * 거래로그 조회 데이타 처리
	 * @param queue 거래로그 Queue
	 * @param logBzopDt 로그 영업일
	 * @param transLog 거래로그정보
	 */
	private void handleRowForProcessor(IDeferredSourceQueue queue, String logBzopDt, Map<String, String> transLog) {
		long seqNo = Long.parseLong(transLog.get("SEQ_NO")); //일련변호
		String sourceTxId = transLog.get(FlatHeaderSpec.TRN_CD.name()); //거래코드

		// 데이터가 최종 처리 번호 보다 클때 결번 목록 등록
		List<Long> lostSeqNoList = new ArrayList<Long>();
		while (seqNo > (getCurrExecutedSeqNo() + 1)) {
			lostSeqNoList.add(getCurrExecutedSeqNo() + 1);
//			currExecutedSeqNo++;
			plusCurrExecutedSeqNo();
		}

		// 결번 처리
		writeLostSeqNo(logBzopDt, lostSeqNoList);

		// 체크 비대상건
		if (isDisabledDeferredTransaction(sourceTxId)) {
			// 비대상건 등록
			writeNoTarget(logBzopDt, seqNo, sourceTxId);
		} else {
			// 디퍼드 대상 타겟 조회
			List<String> targetList = getTargetList(transLog);

			// 대상 타겟이 없는 경우
			if (targetList == null || targetList.size() < 1) {
				// 비대상건 등록
				writeNoTarget(logBzopDt, seqNo, sourceTxId);
			}
			// 대상 타켓이 존재
			else {
				for (Iterator<String> iter = targetList.iterator(); iter.hasNext();) {
					String targetTxId = iter.next();
					// 대상건 등록
					writeTarget(queue, false, logBzopDt, seqNo, targetTxId, sourceTxId, transLog);
				}
			}
		}

//		currExecutedSeqNo++;
		plusCurrExecutedSeqNo();
	}
	
	private long getCurrExecutedSeqNo(){
		return currExecutedSeqNo;
	}
	
	private long plusCurrExecutedSeqNo(){
		currExecutedSeqNo++;
		getLogger().info(this, "currExecutedSeqNo modified value is " + currExecutedSeqNo);
		return currExecutedSeqNo;
	}

	/**
	 * 재처리를 위한 거래로그 조회
	 * @param queue 거래로그 Queue
	 * @param logBzopDt 로그 영업일
	 * @param maxReadSize 최대조회건수
	 * @param failRetryCnt 실패건 재처리 가능 횟수
	 * @param lostRetryCnt 결번건 재처리 가능 횟수
	 * @return 조회 건수
	 */
	private int readForReProcessor(final IDeferredSourceQueue queue, final String logBzopDt, int maxReadSize, int failRetryCnt, int lostRetryCnt) {
		DeferredSourceRowHandler rowHandler = new DeferredSourceRowHandler() {
			public void handleRowImpl(Object obj) {
				handleRowForReProcessor(queue, logBzopDt, (Map<?, ?>) obj);
			}
		};

		// F(실패),L(결번),R(재처리) 목록 조회
		deferredLogDAO.getDeferredLogForRetry(logBzopDt, initSeqNo, lastSeqNo, maxReadSize, failRetryCnt, lostRetryCnt, rowHandler);

		if (getLogger().isDebugEnabled()) {
			getLogger().debug(this, "logBzopDt="+logBzopDt+", initSeqNo="+initSeqNo+", lastSeqNo="+lastSeqNo+", handleCount=" + rowHandler.handleCount);
		}

		return rowHandler.handleCount;
	}

	/**
	 * 재처리 대상 로그 조회 데이타 처리
	 * @param queue 거래로그 Queue
	 * @param logBzopDt 로그 영업일
	 * @param deferredLog 재처리 로그
	 */
	private void handleRowForReProcessor(IDeferredSourceQueue queue, String logBzopDt, Map<?, ?> deferredLog) {
		long seqNo = (Long) deferredLog.get("SEQ_NO"); //일련번호
		int exeCnt = (Integer) deferredLog.get("EXE_CNT"); //처리 횟수
		DeferredStatus status = DeferredStatus.get((String) deferredLog.get("STATUS")); //처리 상태
		String sourceTxId = (String) deferredLog.get("SOURCE_TR_CD"); //원본 거래코드
		String targetTxId = (String) deferredLog.get("TARGET_TR_CD"); //대상 거래코드
		
		// 거래로그 조회
		Map<String, String> transLog = transLogDAO.getTransLog(logBzopDt, seqNo);

		// 거래로그가 존재하지 않음.
		if (transLog == null) {
			// 실행회수만 증가.
			writeLostSeqNo(logBzopDt, seqNo, targetTxId, status, exeCnt + 1);
		}
		// 거래로그가 존재.
		else {
			// 현재 상태 : 결번
			// 결번에서 재처리로 변경된 건
			if (DeferredStatus.LOST_SEQ.equals(status) || (DeferredStatus.RETRY.equals(status) && IDeferredConstants.DEFERRED_TX_NOT_EXIST.equals(targetTxId))) {
				sourceTxId = transLog.get(FlatHeaderSpec.TRN_CD.name()); //거래코드

				// 디퍼드 대상 타겟 조회
				List<String> targetList = getTargetList(transLog);

				// 디퍼드 대상 아님.
				if (targetList == null || targetList.size() < 1) {
					// 재처리 회수를 증가 하여 로그 업데이트
					writeNoTarget(logBzopDt, seqNo, targetTxId, sourceTxId, status, exeCnt + 1);
				}
				// 디퍼드 대상.
				else {
					for (int i = 0; i < targetList.size(); i++) {
						String newTargetTxId = targetList.get(i);
						writeTarget(queue, true, logBzopDt, seqNo, newTargetTxId, targetTxId, sourceTxId, status, exeCnt, transLog, i);
					}
				}
			}
			// 현재 상태 : 실패
			// 실패에서 재처리로 변경된 건
			else {
				writeTarget(queue, true, logBzopDt, seqNo, targetTxId, sourceTxId, status, exeCnt, transLog);
			}
		}
	}

	/**
	 * 최종 시퀀스 조회
	 * 
	 * TODO 후처리 로그 테이블도 파티션 분리 될때 변경 검토 필요 현재처럼 후처리 로그의 Max 사용하지 않고 원안대로 파티션별 상태
	 * 테이블 가져가는 등의 방식으로
	 * 
	 * @return lastSeq
	 */
	private long getLastExecutedSeqNo(String logBzopDt) {
		long lastExecutedSeqNo = 0L;

		// 메모리 시퀀스가 초기값 보다 클때 메모리 시퀀스+1 사용)
		if (currExecutedSeqNo > initSeqNo) {
			lastExecutedSeqNo = currExecutedSeqNo;
		} else {// 현재 데몬의 처리 범위에 대해서만 조회
			lastExecutedSeqNo = deferredLogDAO.getLastExecutedSeqNo(logBzopDt, initSeqNo, lastSeqNo);
			if (lastExecutedSeqNo == 0) {
				lastExecutedSeqNo = initSeqNo;
			}
			// 최종 시퀀스 업데이트
			currExecutedSeqNo = lastExecutedSeqNo;
		}
		return lastExecutedSeqNo;
	}

	/**
	 * 결번 기록
	 * @param logBzopDt 로그 영업일
	 * @param seqNoList 일련번호 목록
	 */
	private void writeLostSeqNo(String logBzopDt, List<Long> seqNoList) {
		if (seqNoList != null && seqNoList.size() > 0) {
			for (long lostSeqNo : seqNoList) {
				try {
					deferredLogWriteDAO.startSession();
					deferredLogWriteDAO.insert(logBzopDt, lostSeqNo, IDeferredConstants.DEFERRED_TX_NOT_EXIST, IDeferredConstants.DEFERRED_TX_NOT_EXIST, DeferredStatus.LOST_SEQ, "Lost Seq", 1);
					deferredLogWriteDAO.commit();
				} finally {
					if (deferredLogWriteDAO.isSessionStatred()) {
						deferredLogWriteDAO.endSession();
					}
				}
			}
		}
	}

	/**
	 * 결번 기록
	 * @param logBzopDt 로그 영업일
	 * @param seqNo 일련번호
	 * @param targetTxId 대상 거래코드
	 * @param status 상태
	 * @param exeCnt 처리횟수
	 */
	private void writeLostSeqNo(String logBzopDt, long seqNo, String targetTxId, DeferredStatus status, int exeCnt) {
		try {
			// 실행회수만 증가.
			String description = DeferredStatus.LOST_SEQ.equals(status) ? "Lost Seq" : "(" + status + " => " + DeferredStatus.LOST_SEQ + ")";
			deferredLogWriteDAO.startSession();
			deferredLogWriteDAO.update(logBzopDt, seqNo, targetTxId, DeferredStatus.LOST_SEQ, description, exeCnt);
			deferredLogWriteDAO.commit();
		} finally {
			if (deferredLogWriteDAO.isSessionStatred()) {
				deferredLogWriteDAO.endSession();
			}
		}
	}

	/**
	 * 비대상 기록
	 * @param logBzopDt 로그 영업일
	 * @param seqNo 일련번호
	 * @param sourceTxId 원본 거래코드
	 */
	private void writeNoTarget(String logBzopDt, long seqNo, String sourceTxId) {
		try {
			deferredLogWriteDAO.startSession();
			deferredLogWriteDAO.insert(logBzopDt, seqNo, IDeferredConstants.DEFERRED_TX_NOT_EXIST, sourceTxId, DeferredStatus.NOT_DEFERRED, "No Target", 1);
			deferredLogWriteDAO.commit();
		} finally {
			if (deferredLogWriteDAO.isSessionStatred()) {
				deferredLogWriteDAO.endSession();
			}
		}
	}

	/**
	 * 비대상 기록
	 * @param logBzopDt 로그 영업일
	 * @param seqNo 일련번호
	 * @param targetTxId 대상 거래코드
	 * @param sourceTxId 원본 거래코드
	 * @param status 상태
	 * @param exeCnt 처리횟수
	 */
	private void writeNoTarget(String logBzopDt, long seqNo, String targetTxId, String sourceTxId, DeferredStatus status, int exeCnt) {
		try {
			// 재처리 회수를 증가 하여 로그 업데이트
			String description = "(" + status + " => " + DeferredStatus.NOT_DEFERRED + ")";
			deferredLogWriteDAO.startSession();
			deferredLogWriteDAO.update(logBzopDt, seqNo, targetTxId, sourceTxId, DeferredStatus.NOT_DEFERRED, description, exeCnt);
			deferredLogWriteDAO.commit();
		} finally {
			if (deferredLogWriteDAO.isSessionStatred()) {
				deferredLogWriteDAO.endSession();
			}
		}
	}

	/**
	 * 대상 기록
	 * @param queue 거래로그 Queue
	 * @param reprocess 재처리 여부
	 * @param logBzopDt 로그 영업일
	 * @param seqNo 일련번호
	 * @param targetTxId 대상 거래코드
	 * @param sourceTxId 원본 거래코드
	 * @param transLog 거래로그 정보
	 * @return 기록 성공 여부
	 */
	private boolean writeTarget(IDeferredSourceQueue queue, boolean reprocess, String logBzopDt, long seqNo, String targetTxId, String sourceTxId, Map<String, String> transLog) {
		try {
			// 대상을 DB에 기록
			deferredLogWriteDAO.startSession();
			deferredLogWriteDAO.insert(logBzopDt, seqNo, targetTxId, sourceTxId, DeferredStatus.IN_QUEUE, null);
			deferredLogWriteDAO.commit();
			
			// Queue에 등록
			DeferredSourceExtension deferredSource = makeDeferredSource(reprocess, logBzopDt, seqNo, sourceTxId, targetTxId, 0, transLog);
			if (queue.put(deferredSource)) {
				return true;
			}
			else {
				// 등록실패시 실패로 상태를 변경
				deferredLogWriteDAO.update(logBzopDt, seqNo, targetTxId, DeferredStatus.FAIL, "Exception while put DeferredSource Queue.", 0);
				deferredLogWriteDAO.commit();
				return false;
			}
		} finally {
			if (deferredLogWriteDAO.isSessionStatred()) {
				deferredLogWriteDAO.endSession();
			}
		}
	}

	/**
	 * 대상 기록<br>
	 * 현재 DB에 기록되어 있는 거래 정보를 변경
	 * @param queue 거래로그 Queue
	 * @param reprocess 재처리 여부
	 * @param logBzopDt 로그 영업일
	 * @param seqNo 일련번호
	 * @param newTargetTxId 새로운 대상 거래코드
	 * @param oldTargetTxId 현재 대상 거래코드
	 * @param sourceTxId 원본 거래코드
	 * @param status 상태
	 * @param exeCnt 처리횟수
	 * @param transLog 거래로그
	 * @param index 순번
	 * @return 기록 성공 여부
	 */
	private boolean writeTarget(IDeferredSourceQueue queue, boolean reprocess, String logBzopDt, long seqNo, String newTargetTxId, String oldTargetTxId, String sourceTxId, DeferredStatus status, int exeCnt, Map<String, String> transLog, int index) {
		try {
			String description = "(" + status + " => " + DeferredStatus.IN_QUEUE + ")";
			deferredLogWriteDAO.startSession();
			// 호출할 거래코드가 여러개인경우에 첫번째는 업데이트를 하고, 두번째부터는 insert를 한다.
			if (index == 0) {
				deferredLogWriteDAO.update(logBzopDt, seqNo, newTargetTxId, sourceTxId, DeferredStatus.IN_QUEUE, description, exeCnt, oldTargetTxId);
			} else {
				deferredLogWriteDAO.insert(logBzopDt, seqNo, newTargetTxId, sourceTxId, DeferredStatus.IN_QUEUE, description, exeCnt);
			}
			deferredLogWriteDAO.commit();
			DeferredSourceExtension deferredSource = makeDeferredSource(reprocess, logBzopDt, seqNo, sourceTxId, newTargetTxId, exeCnt, transLog);
			if (queue.put(deferredSource)) {
				return true;
			}
			else {
				deferredLogWriteDAO.update(logBzopDt, seqNo, newTargetTxId, DeferredStatus.FAIL, "Exception while put DeferredSource Queue.", exeCnt);
				deferredLogWriteDAO.commit();
				return false;
			}
		} finally {
			if (deferredLogWriteDAO.isSessionStatred()) {
				deferredLogWriteDAO.endSession();
			}
		}
	}

	/**
	 * 대상 기록
	 * @param queue 거래로그 Queue
	 * @param reprocess 재처리 여부
	 * @param logBzopDt 로그 영업일
	 * @param seqNo 일련번호
	 * @param targetTxId 대상 거래코드
	 * @param sourceTxId 원본 거래코드
	 * @param status 상태
	 * @param exeCnt 처리횟수
	 * @param transLog 거래로그
	 * @return 기록 성공 여부
	 */
	private boolean writeTarget(IDeferredSourceQueue queue, boolean reprocess, String logBzopDt, long seqNo, String targetTxId, String sourceTxId, DeferredStatus status, int exeCnt, Map<String, String> transLog) {
		try {
			// DB에 기록
			String description = "(" + status + " => " + DeferredStatus.IN_QUEUE + ")";
			deferredLogWriteDAO.startSession();
			deferredLogWriteDAO.update(logBzopDt, seqNo, targetTxId, DeferredStatus.IN_QUEUE, description, exeCnt);
			deferredLogWriteDAO.commit();

			// Queue에 등록
			DeferredSourceExtension deferredSource = makeDeferredSource(reprocess, logBzopDt, seqNo, sourceTxId, targetTxId, exeCnt, transLog);
			if (queue.put(deferredSource)) {
				return true;
			}
			else {
				// Queue에 등록 실패시 실패로 기록 
				deferredLogWriteDAO.update(logBzopDt, seqNo, targetTxId, DeferredStatus.FAIL, "Exception while put DeferredSource Queue.", exeCnt);
				deferredLogWriteDAO.commit();
				return false;
			}
		} finally {
			if (deferredLogWriteDAO.isSessionStatred()) {
				deferredLogWriteDAO.endSession();
			}
		}
	}

	/**
	 * 디퍼드 처리 상태 복원
	 * @param logBzopDt 로그 영업일
	 * @param from 최소 일련번호
	 * @param to 최대 일련번호
	 * @return 복원 건수
	 */
	public int restoreVolatileLog(String logBzopDt, long from, long to) {
		int row = 0;

		/**
		 * 파일 복원 모듈이 존재하는 경우에는 파일에서 복원
		 */
		if (restoreFileHandler != null) {
			row += restoreVolatileLogFromErrorFile(logBzopDt, from, to);
		}
		
		/**
		 * DB 테이블 기준으로 복원 처리 실행.
		 */
		try {
			deferredLogWriteDAO.startSession();
			row += deferredLogWriteDAO.restoreVolatileLog(logBzopDt, from, to);
			deferredLogWriteDAO.commit();
		} finally {
			if (deferredLogWriteDAO.isSessionStatred()) {
				deferredLogWriteDAO.endSession();
			}
		}

		return row;
	}

	/**
	 * 디퍼드 제외대상 여부 판단
	 * @param sourceTxId 원본 거래코드
	 * @return 디퍼드 제외대상 여부
	 */
	private boolean isDisabledDeferredTransaction(String sourceTxId) {
		if (disabledTxIdPattern != null) {
			return disabledTxIdPattern.matcher(sourceTxId).matches();
		}
		return false;
	}

	/**
	 * Queue에 등록될 정보를 생성
	 * @param reprocess 재처리 여부
	 * @param logBzopDt 로그 영업일
	 * @param seqNo 일련번호
	 * @param sourceTxId 원본 거래코드
	 * @param targetTxId 대상 거래코드
	 * @param exeCnt 실행 횟수
	 * @param transLog 거래로그
	 * @return Queue에 등록될 정보
	 */
	private DeferredSourceExtension makeDeferredSource(boolean reprocess, String logBzopDt, long seqNo, String sourceTxId, String targetTxId, int exeCnt, Map<String, String> transLog) {
		DeferredSourceExtension deferredSource = new DeferredSourceExtension();
		deferredSource.setReprocess(reprocess);
		deferredSource.setStatus(DeferredStatus.IN_QUEUE);
		deferredSource.setLogBzopDt(logBzopDt);
		deferredSource.setSeqNo(seqNo);
		deferredSource.setSourceTxId(sourceTxId);
		deferredSource.setTargetTxId(targetTxId);
		deferredSource.setExeCnt(exeCnt);
		deferredSource.setSource(new HashMap<String, String>(transLog));
		return deferredSource;
	}

	private int restoreCount = 0;

	/**
	 * 파일 기준으로 복원 진행.
	 * @param logBzopDt 로그 영업일
	 * @param from 일련번호 대역 최소값
	 * @param to 일련번호 대역 최대값
	 * @return 복원 건수
	 */
	private int restoreVolatileLogFromErrorFile(final String logBzopDt, long from, long to) {
		restoreCount = 0;
		
		// 로그 영업일에 해당하는 파일 정보 조회 (은행별로 파일은 별도로 관리된다.)
		final Properties props = restoreFileHandler.read(logBzopDt);
		
		if (props != null && props.size() > 0) {
			DeferredSourceRowHandler rowHandler = new DeferredSourceRowHandler() {
				public void handleRowImpl(Object obj) {
					Map<String, Object> map = (Map<String, Object>) obj;
					long seqNo = (Long) map.get("SEQ_NO");
					String targetTxId = (String) map.get("TARGET_TR_CD");
					
					// DB 에서 조회된 데이타를 파일에서 정보 조회
					DeferredRestoreFileHandler.Info info = restoreFileHandler.contains(props, seqNo, targetTxId);
					if (info != null) {
						// 파일 정보를 기준으로 복원 처리
						try {
							deferredLogWriteDAO.startSession();
							int row = deferredLogWriteDAO.updateRestore(logBzopDt, seqNo, targetTxId, DeferredStatus.get(info.status), "restore", info.exeCnt, info.updDate);
							deferredLogWriteDAO.commit();

							restoreCount += row;
							if (row > 0) {
								DeferredSourceReader.this.getLogger().warn(DeferredSourceReader.this, "[RESTORE] logBzopDt=" + logBzopDt + ", seqNo=" + seqNo + ", targetTxId=" + targetTxId + ", " + info);
							}
						} finally {
							if (deferredLogWriteDAO.isSessionStatred()) {
								deferredLogWriteDAO.endSession();
							}
						}
					}
				}
			};
			
			// DB기준 복원대상을 조회하여 파일과 매치후 처리한다.
			deferredLogDAO.getVolatileLog(logBzopDt, from, to, rowHandler);
		}
		return restoreCount;
	}

	private abstract class DeferredSourceRowHandler implements IRowHandler {
		int handleCount;

		public Object getResult() {
			return null;
		}

		public Class<?> getResultType() {
			return null;
		}

		public void handleRow(Object obj) {
			handleCount++;
			handleRowImpl(obj);
		}

		public abstract void handleRowImpl(Object obj);
	}

}
