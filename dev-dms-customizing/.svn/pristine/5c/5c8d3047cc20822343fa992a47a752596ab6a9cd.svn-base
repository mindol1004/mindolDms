package fwk.batch.agent;

import java.util.Date;
import java.util.Locale;

import nexcore.framework.bat.IBatchContext;
import nexcore.framework.bat.base.IOnlineContextMaker;
import nexcore.framework.bat.util.Util;
import nexcore.framework.core.data.Channel;
import nexcore.framework.core.data.IChannel;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRuntimeContext;
import nexcore.framework.core.data.ITerminal;
import nexcore.framework.core.data.ITransaction;
import nexcore.framework.core.data.OnlineContext;
import nexcore.framework.core.data.RuntimeContext;
import nexcore.framework.core.data.Terminal;
import nexcore.framework.core.data.Transaction;
import nexcore.framework.core.data.user.UserInfo;
import nexcore.framework.core.log.LogManager;

import org.apache.commons.lang.time.FastDateFormat;

import fwk.common.internal.CommonAreaHelper;
import fwk.common.internal.ImplCommonArea;

public class OnlineContextMaker implements IOnlineContextMaker {

	private String agentIp; // agent IP

	public String getAgentIp() {
		return this.agentIp;
	}

	public void setAgentIp(String agentIp) {
		this.agentIp = agentIp;
	}

	public void init() {
		this.agentIp = Util.getLocalIp();
	}

	public void destroy() {
	}

	/**
	 * 배치에서 온라인컴포넌트를 호출하기 위해 IOnlineContext 객체를 생성함.
	 */
	public IOnlineContext makeOnlineContext(IBatchContext context) {
		IOnlineContext onlineCtx = null;

		if (context.getJobExecution().isOnDemand() && context.getJobExecution().getOptionalData()!=null) { // (온디맨드일때는 OnlineContext를 재사용)
			onlineCtx = (IOnlineContext)Util.bytesToObject(context.getJobExecution().getOptionalData());
//			CommonAreaHelper.create(onlineCtx); // TODO 확인. 필요없을것 같은데 이 라인은 왜만들었지?
		}else {
			// **** ITransaction
			ITransaction transaction    = new Transaction(
					context.getJobExecution().getJobExecutionId(),     // request id
					context.getJobExecution().getJobId(),              // tx id
					false,                                             // is deferred
					new Date(context.getJobExecution().getStartTime()),// start time
					true);                                             // is main

			// **** IChannel
			IChannel channel            = new Channel("BATC", "BATC", IChannel.PROTOCOL_OTHER, IChannel.MSG_XML); // 메세지 타입은 정확하지 않다.

			// **** ITerminal
			ITerminal terminal          = new Terminal("BATC", "BATC", 1000);

			// **** IUserInfo
			UserInfo userInfo          = new UserInfo();
			userInfo.setIp(      context.getOperatorIp());   // BATCH ADMIN IP를 여기에 넣는다.
			userInfo.setLocale(  Locale.getDefault());
			userInfo.setLoginId( context.getOperatorId());

			// **** IRuntimeContext
			IRuntimeContext runtimeContext = new RuntimeContext(null, null);

			onlineCtx = new OnlineContext(transaction, userInfo, runtimeContext, channel, terminal);

			CommonAreaHelper.createInit(onlineCtx);
			ImplCommonArea ica = CommonAreaHelper.getImpl(onlineCtx);


			// ■■■■■■■■■■■■■■■■■■■■
			// 헤더 변환
			// ■■■■■■■■■■■■■■■■■■■■
			String yyyyMMddHHmmssSSS = FastDateFormat.getInstance("yyyyMMddHHmmssSSS").format(System.currentTimeMillis());

			ica.setGlobId(context.getJobExecution().getJobExecutionId());

			// 공통부 - 전송시스템정보내용
			ica.setIpad(this.agentIp);

			ica.setEnvDvcd(context.getInParameter("STAGE_CODE")); // 환경정보구분코드
			ica.setFrstTrnmChnlCd("BATC"); // 최초전송채널코드

			ica.setTrnmChnlCd("BATC"); // 전송채널코드
			ica.setTrnmNodeNo(1); // 전송노드번호
			ica.setMciTrnmNodeNo(1); // MCI 전송노드번호

			ica.setMesgDmndDttm(yyyyMMddHHmmssSSS); // 전문요청일시

			// 공통부 - 직원정보내용
			ica.setDeptCd(""); // 부서코드
			ica.setUserNo(getBatchCallUserId(context)); // 사용자번호
			ica.setBrCd(""); // 부점코드

		}

		onlineCtx.setAttribute(LogManager.THIS_DEV_LOGGER_KEY, context.getLogger());

		return onlineCtx;
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
			return "batch01";
		}
		return usrNo;
	}
}
