package fwk.deferred.executor;

import java.util.HashMap;
import java.util.Map;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.util.StringUtils;
import nexcore.framework.coreext.pojo.biz.base.BizServiceLocator;
import nexcore.framework.coreext.pojo.biz.base.BizServiceSyncCaller;
import nexcore.framework.deferred.DeferredStatus;
import nexcore.framework.deferred.IDeferredSource;
import nexcore.framework.deferred.processor.AbstractDeferredExecutor;
import fwk.common.internal.CommonAreaHelper;
import fwk.deferred.dao.DeferredLogDAO;
import fwk.deferred.helper.DeferredRestoreFileHandler;
import fwk.deferred.reader.DeferredSourceExtension;
import fwk.flat.FlatHeaderHelper;

/**
 * 디퍼드 서비스 실행 클래스
 */
public class DeferredExecutor extends AbstractDeferredExecutor {

	private boolean forcedLocalLookup;
	protected DeferredLogDAO deferredLogWriteDAO; // 디퍼드 로그 DAO
	private DeferredRestoreFileHandler restoreFileHandler;

	/**
	 * 로컬 서비스 실행 여부
	 * @param forcedLocalLookup 로컬 서비스 실행 여부
	 */
	public void setForcedLocalLookup(boolean forcedLocalLookup) {
		this.forcedLocalLookup = forcedLocalLookup;
	}

	/**
	 * 디퍼드 로그 기록용 DAO
	 * @param deferredLogWriteDAO 디퍼드 로그 기록용 DAO
	 */
	public void setDeferredLogWriteDAO(DeferredLogDAO deferredLogWriteDAO) {
		this.deferredLogWriteDAO = deferredLogWriteDAO;
	}

	/**
	 * 복원 파일 관리 모듈
	 * @param restoreFileHandler 복원 파일 관리 모듈
	 */
	public void setRestoreFileHandler(DeferredRestoreFileHandler restoreFileHandler) {
		this.restoreFileHandler = restoreFileHandler;
	}

	public void init() {
		super.init();

		deferredLogWriteDAO.init();
		getLogger().info(this, "DeferredExecutor init..");
	}

	/**
	 * 디퍼드 실행 로직 구현 메소드
	 * 
	 * @override
	 * @param source
	 */
	@SuppressWarnings("unchecked")
	public DeferredStatus execute(IDeferredSource deferredSource) {
		DeferredSourceExtension deferredSourceExtension = (DeferredSourceExtension) deferredSource;

		String logBzopDt = deferredSourceExtension.getLogBzopDt();
		long seqNo = deferredSourceExtension.getSeqNo();
		String targetTxId = deferredSourceExtension.getTargetTxId();
		//		String sourceTxId = deferredSourceExtension();
		int exeCnt = deferredSourceExtension.getExeCnt();
		Map<String, String> transLog = (Map<String, String>) deferredSourceExtension.getSource();

		try {
			// 본처리
			/*if (!deferredSourceExtension.isReprocess()) {
				return invoke(logBzopDt, seqNo, targetTxId, exeCnt, transLog);
			}
			// 재처리
			else {
				// 디퍼드 대상 서비스 호출
				return invoke(logBzopDt, seqNo, targetTxId, exeCnt, transLog);
			}*/
		   //2015.10.13 jihooyim code inspector 점검 수정 (if/else문 body에 같은 것 반복 금지)
			return invoke(logBzopDt, seqNo, targetTxId, exeCnt, transLog);
			
		} finally {
			if (deferredLogWriteDAO.isSessionStatred()) {
				deferredLogWriteDAO.endSession();
			}
		}
	}

	/**
	 * 디퍼드 서비스 실행
	 * @param logBzopDt 로그영업일
	 * @param seqNo 일련번호
	 * @param targetTxId 대상 거래코드
	 * @param exeCnt 실행 횟수 
	 * @param transLog 거래로그 정보
	 * @return 실행 상태 코드
	 */
	private DeferredStatus invoke(String logBzopDt, long seqNo, String targetTxId, int exeCnt, Map<String, String> transLog) {
		// 디퍼드 대상 서비스 호출
		DeferredStatus resultStatus = null;
		String description = null;
		try {
			IDataSet responseDataSet = invoke(seqNo, targetTxId, new HashMap<String, String>(transLog));
			resultStatus = DeferredStatus.SUCESS;
			description = responseDataSet.getResultMessage().getMessageId();
		} catch (Throwable t) {
			getLogger().error(this, "exception while invoke DeferredService.", t);
			resultStatus = DeferredStatus.FAIL;
			description = StringUtils.fixLength(t.toString(), 497, "..");
		}

		try {
			// 후처리 실행 결과 DeferredLog에 업데이트
			deferredLogWriteDAO.startSession();
			deferredLogWriteDAO.update(logBzopDt, seqNo, targetTxId, resultStatus, description, (exeCnt + 1));
			deferredLogWriteDAO.commit();
			//			if (getLogger().isDebugEnabled()) {
			//				getLogger().debug(this, "logBzopDt=" + logBzopDt + ", seqNo:" + seqNo + ", targetTxId=" + targetTxId + " execute result=" + resultStatus);
			//			}
		} catch (Throwable t) {
			writeErrorFile(logBzopDt, seqNo, targetTxId, resultStatus, exeCnt + 1);
			if (t instanceof RuntimeException) {
				throw (RuntimeException) t;
			} else {
				throw new RuntimeException(t);
			}
		} finally {
			if (deferredLogWriteDAO.isSessionStatred()) {
				deferredLogWriteDAO.endSession();
			}
		}
		return resultStatus;
	}

	/**
	 * 실제 각 후행/후처리 서비스 호출 구현
	 * 
	 * 원격 연동 거래에 대한 구현 확정시 그부분 참조하여 소스 완성
	 */
	private IDataSet invoke(long seqNo, String targetTxId, Map<String, String> transLog) throws Exception {
		// 온라인 컨텍스트 생성
		IOnlineContext onlineCtx = getOnlineContext(targetTxId, transLog);

		// 요청데이타 생성
		IDataSet requestDataSet = new DataSet();
		requestDataSet.putField("TR_PK", Long.toString(seqNo));

		// 연동거래 호출
		// forcedLocalLookup가 true이면 로컬call이고, 아니면 거래코드에 따른 remote call이다.
		IDataSet responseDataSet = BizServiceSyncCaller.callServiceByRequiresNew(forcedLocalLookup ? BizServiceLocator.createFacadeObject() : null, targetTxId, requestDataSet, onlineCtx, false, false);

		//처리 결과 분석
		IResultMessage reslutMessage = responseDataSet.getResultMessage();
		if (reslutMessage != null) {
			// 처리 결과 코드가 실패 일때 Exception throw..
			if (reslutMessage.getStatus() == IResultMessage.FAIL) {
				throw new Exception("DeferredProcess[" + targetTxId + "] resultCode=" + reslutMessage.getMessageId());
			}
		} else {
			throw new Exception("DeferredProcess[" + targetTxId + "] reslutMessage is null.");
		}

		return responseDataSet;
	}

	/**
	 * 디퍼드 서비스용 온라인 컨텍스트 생성
	 * @param targetTxId 대상 거래코드
	 * @param transLog 거래로그 정보
	 * @return 온라인 컨텍스트
	 */
	private IOnlineContext getOnlineContext(String targetTxId, Map<String, String> transLog) {
		transLog.put("TRN_CD", targetTxId);
		IOnlineContext onlineCtx = FlatHeaderHelper.makeOnlineContext(transLog, true);
		CommonAreaHelper.create(onlineCtx);
		return onlineCtx;
	}

	/**
	 * 장애시 복원 파일에 저장
	 * @param logBzopDt 로그 영업일
	 * @param seqNo 일련번호
	 * @param targetTxId 대상 거래코드
	 * @param resultStatus 처리 상태
	 * @param exeCnt 실행 횟수
	 */
	private void writeErrorFile(String logBzopDt, long seqNo, String targetTxId, DeferredStatus resultStatus, int exeCnt) {
		try {
			if (restoreFileHandler != null) {
				restoreFileHandler.write(logBzopDt, seqNo, targetTxId, resultStatus, exeCnt);
			}
		} catch (Exception e) {
			getLogger().error(this, "writeErrorFile fail.", e);
		}
	}

}
