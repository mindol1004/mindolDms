package fwk.outbound.internal;

import nexcore.framework.core.service.asyncqueue.IAsyncQueueContext;
import nexcore.framework.core.service.asyncqueue.IAsyncQueueListener;

/**
 * Queue에 등록된 아웃바운드 정보를 처리하는  MDB가 이 클래스를 호출하여 아웃바운드 전문 전송을 처리한다.  
 */
public class OutboundAsyncQueueListener implements IAsyncQueueListener {

	public Object handleQueue(IAsyncQueueContext queueContext) {
		OutboundAsyncQueueContext context = (OutboundAsyncQueueContext)queueContext.getQueueMessage();

		// 전송
		OutboundSender.send(context.getTarget(), context.getHeader(), context.getByteArrayWrap());
		
		return "OK";
	}

}
