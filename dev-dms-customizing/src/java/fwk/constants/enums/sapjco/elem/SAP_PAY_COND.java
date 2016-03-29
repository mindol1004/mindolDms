package fwk.constants.enums.sapjco.elem;

/**
 * 지급조건
 * @author greatjin
 *
 */
public enum SAP_PAY_COND {
	 KRW_1ST ("A1F0") // 원화F/B 1차R
	;
	private String code;
		
	SAP_PAY_COND(String code)
	{
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	
	public String toString()
	{
		return code;
	}
		
}
