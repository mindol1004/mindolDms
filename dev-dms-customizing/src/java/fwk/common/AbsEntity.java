package fwk.common;

import nexcore.framework.core.log.LogManager;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;

/**
 * 엔터티 상위 클래스
 */
public abstract class AbsEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = 6608819629123236459L;
	private Log log = LogManager.getFwkLog();

	/**
	 * 객체를 복사하여 리턴
	 */
	protected Object cloneBean() {
		try {
			return BeanUtils.cloneBean(this);
		} catch (Exception e) {
		    //2015.10.13 jihooyim code inspector 점검 수정 (02. 오류 상황 대응 부재)
		    if (log.isErrorEnabled())  log.error("BeanUtils.cloneBean error");
		}
		return null;
	}

	/**
	 * 객체의 내부 속성을 문자열로 변환해서 리턴
	 */
	public String toDetailString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

//	/**
//	 * 객체의 내부 속성 값들이 일치하는지 확인
//	 */
//	public boolean equals(Object o) {
//		return EqualsBuilder.reflectionEquals(this, o);
//	}
//
//	/**
//	 * HashCode를 생성해서 리턴
//	 */
//	public int hashCode() {
//		return HashCodeBuilder.reflectionHashCode(this);
//	}

}
