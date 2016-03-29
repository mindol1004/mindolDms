package fwk.common.internal;

import nexcore.framework.core.util.ByteArrayWrap;
import fwk.common.FlatData;
import fwk.flat.FlatHeader;

public class ImplFlatData extends FlatData {

	private FlatHeader flatHeader;
	private ByteArrayWrap bodyByteArrayWrap;

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
