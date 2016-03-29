package dms.constants.enums.sapjco.elem;

/**
 * 지급조건 ZTERM
 * @author greatjin
 *
 */
public enum SAP_PAY_COND {
	  KRW_1ST         ("A1F0") //원화F/B 1차R
	, KRW_WD_CASH     ("A1Z0") //원화인출증 현금
	, AT_ONCE_CASH_IN ("I100") //결제일에 현금입금
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
