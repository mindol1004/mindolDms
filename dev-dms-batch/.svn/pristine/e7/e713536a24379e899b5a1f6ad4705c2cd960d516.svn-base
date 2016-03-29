package dms.constants.enums.sapjco.elem;

/**
 * 지급보류 PAY_RSV
 * @author greatjin
Payment block   Description
    Free for payment
A   현업부서지급보류
B   구매부서지급보류
C   자금에서지급보류
D   법인카드지급보류
E   보증보험미비
W   채권채무상계대상
X   입금예정일미정(AR)
Y   거래중지(RM팀)
Z   채권(가)압류
 *
 */
public enum SAP_PAY_RSV {
	  INC_DEPT       ("A") //현업부서지급보류
	, CAO_SETOFF     ("W") //채권채무상계대상
	;
	private String code;
		
	SAP_PAY_RSV(String code)
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
