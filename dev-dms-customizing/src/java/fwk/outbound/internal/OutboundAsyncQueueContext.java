package fwk.outbound.internal;

import nexcore.framework.core.util.ByteArrayWrap;
import fwk.common.OutboundHeader;

/**
 * Queue에 등록할 아웃바운드 정보
 */
public class OutboundAsyncQueueContext implements java.io.Serializable {

	private static final long serialVersionUID = 3712006185085607289L;

	private OutboundTargetInternal target;
	private OutboundHeader header;
	private ByteArrayWrap byteArrayWrap;

	public OutboundTargetInternal getTarget() {
		return target;
	}

	public void setTarget(OutboundTargetInternal target) {
		this.target = target;
	}

	public void setHeader(OutboundHeader header){
		this.header = header;
	}
	
	public OutboundHeader getHeader(){
		return header;
	}
	
	public ByteArrayWrap getByteArrayWrap() {
		return byteArrayWrap;
	}

	public void setByteArrayWrap(ByteArrayWrap byteArrayWrap) {
		this.byteArrayWrap = byteArrayWrap;
	}
	
}
