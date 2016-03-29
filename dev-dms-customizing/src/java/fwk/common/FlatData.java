package fwk.common;

import fwk.flat.FlatHeader;
import nexcore.framework.core.util.ByteArrayWrap;

/**
 * 표준전문
 */
public abstract class FlatData {

	/**
	 * 표준전문헤더
	 * 
	 * @return 표준전문헤더
	 */
	public abstract FlatHeader getFlatHeader();
	
	/**
	 * 전문 데이타부
	 * 
	 * @return 전문 데이타부
	 */
	public abstract ByteArrayWrap getBodyByteArrayWrap();

}
