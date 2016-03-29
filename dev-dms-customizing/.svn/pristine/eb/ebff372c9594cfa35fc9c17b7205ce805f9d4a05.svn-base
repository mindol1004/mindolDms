package fwk.common.internal;

import java.util.Map;

import nexcore.framework.core.util.ByteArrayWrap;
import fwk.common.FlatData;
import fwk.flat.FlatHeader;

public class ImplEaiFlatData extends FlatData {

	private FlatHeader flatHeader;
	private ByteArrayWrap bodyByteArrayWrap;
	private Map<String, String>eaiHeader;

	/**
	 * 표준전문헤더
	 * 
	 * @return 표준전문헤더
	 */
	public FlatHeader getFlatHeader() {
		return flatHeader;
	}

	/**
	 * 표준전문헤더
	 * 
	 * @param flatHeaer
	 *            표준전문헤더
	 */
	public void setFlatHeader(FlatHeader flatHeader) {
		this.flatHeader = flatHeader;
	}

	
	/**
	 * EAI전문헤더
	 * @return
	 */
	public Map<String, String> getEaiHeader() {
		return eaiHeader;
	}

	/**
	 * EAI전문헤더
	 * @param eaiHeader
	 */
	public void setEaiHeader(Map<String, String> eaiHeader) {
		this.eaiHeader = eaiHeader;
	}

	/**
	 * 전문 데이타부
	 * 
	 * @return 전문 데이타부
	 */
	public ByteArrayWrap getBodyByteArrayWrap() {
		return bodyByteArrayWrap;
	}

	/**
	 * 전문 데이타부
	 * 
	 * @param bodyByteArrayWrap
	 *            전문 데이타부
	 */
	public void setBodyByteArrayWrap(ByteArrayWrap bodyByteArrayWrap) {
		this.bodyByteArrayWrap = bodyByteArrayWrap;
	}
	
}
