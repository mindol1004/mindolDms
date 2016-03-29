package dms.constants.enums.sapjco.elem;

/**
 * 사업장, 세적지
 * @author greatjin
 *
 */
public enum SAP_BP {
	 SKCC_HQ ("1100") //SKCC
	;
	private String code;
		
	SAP_BP(String code)
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
