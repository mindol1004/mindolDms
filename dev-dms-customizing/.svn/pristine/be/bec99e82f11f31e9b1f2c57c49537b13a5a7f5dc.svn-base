package fwk.outbound.internal;

import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BaseRuntimeException;
import nexcore.framework.core.exception.SystemRuntimeException;
import nexcore.framework.core.ioc.ComponentRegistry;
import nexcore.framework.core.service.asyncqueue.IAsyncQueueManager;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.core.util.ByteArrayWrap;
import fwk.common.OutboundHeader;

public final class OutboundAsyncQueueSender {

	private static IAsyncQueueManager asyncQueueManager;
    private static String fepQueueName;
    private static String mciQueueName;
    private static String eaiQueueName;

	public static void putQueue(OutboundTargetInternal target, OutboundHeader header, ByteArrayWrap byteArrayWrap, IOnlineContext onlineCtx){
		prepare();
		try {
			// 대상채널 유효성 검사 - 유효하지 않는 채널이면 에러 발생함.
			OutboundSender.getTargetName(target, header.getFlatHeader());
			
			// marked rollback only 체크
//			BizServiceLocator.checkRollbackOnly(onlineCtx);
			
			OutboundAsyncQueueContext context = new OutboundAsyncQueueContext();
			context.setTarget(target);
			context.setHeader(header);
			context.setByteArrayWrap(byteArrayWrap);
			
			if(OutboundTargetInternal.FEP_ASYNC.equals(target)){
				asyncQueueManager.putXA(fepQueueName, context, onlineCtx);
			}
			else if(OutboundTargetInternal.MCI_PUSH.equals(target)){
				asyncQueueManager.putXA(mciQueueName, context, onlineCtx);
			}
			else if(OutboundTargetInternal.EAI_ASYNC.equals(target)){
				asyncQueueManager.putXA(eaiQueueName, context, onlineCtx);
			}
			else {
				throw new RuntimeException("지원하지 않는 아웃바운드 채널입니다.");
			}
		} catch (Exception e) {
			if (e instanceof BaseRuntimeException) {
				throw (BaseRuntimeException) e;
			} else {
				Throwable cause = e.getCause();
				if (cause != null && cause instanceof BaseRuntimeException) {
					throw (BaseRuntimeException) cause;
				}
			}
			throw new SystemRuntimeException("SKFS1005", null, e);
		}	
	}
	
	protected static void prepare(){
        if(asyncQueueManager == null){
        	asyncQueueManager = (IAsyncQueueManager) ComponentRegistry.lookup(IAsyncQueueManager.COMPONENT_ID);
        }
        if(fepQueueName == null){
        	fepQueueName = BaseUtils.getConfiguration("online.internal.async.queuename.outbound.fep");
        }
        if(mciQueueName == null){
        	mciQueueName = BaseUtils.getConfiguration("online.internal.async.queuename.outbound.mci");
        }
        if(eaiQueueName == null){
        	eaiQueueName = BaseUtils.getConfiguration("online.internal.async.queuename.outbound.eai");
        }
    }
	
}
