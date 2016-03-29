package fwk.outbound.internal;

import fwk.common.CommonUtils;


/**
 * 아웃바운드 대상 채널 및 유형 관리
 */
public class OutboundTargetInternal implements java.io.Serializable {
	
	private static final long serialVersionUID = 4477530619985212365L;
	
	public final static OutboundTargetInternal MCI_PUSH = new OutboundTargetInternal(CommonUtils.CHNL_CD.MCI.name(), "PUSH");
	public final static OutboundTargetInternal MCI_MASS = new OutboundTargetInternal(CommonUtils.CHNL_CD.MCI.name(), "MASS");
	public final static OutboundTargetInternal FEP_SYNC = new OutboundTargetInternal(CommonUtils.CHNL_CD.TEFP.name(), "SYNC");
	public final static OutboundTargetInternal FEP_ASYNC = new OutboundTargetInternal(CommonUtils.CHNL_CD.TEFP.name(), "ASYNC");
	public final static OutboundTargetInternal EAI_SYNC = new OutboundTargetInternal(CommonUtils.CHNL_CD.EAII.name(), "SYNC");
	public final static OutboundTargetInternal EAI_ASYNC = new OutboundTargetInternal(CommonUtils.CHNL_CD.EAII.name(), "ASYNC");
	
	private final String name;
	private final String chnlCd;
	private final String type;
	
	private OutboundTargetInternal(String chnlCd, String type){
		this.name = chnlCd + "_" + type;
		this.chnlCd = chnlCd;
		this.type = type;
	}
	
	public String getName() { return name; }
	public String getChannelCode() { return chnlCd; }
	public String getChannelType() { return type; }
	
	public boolean equals(OutboundTargetInternal compare){
		if(compare == null){
			return false;
		}
		return name.equals(compare.getName());
	}
	
}
