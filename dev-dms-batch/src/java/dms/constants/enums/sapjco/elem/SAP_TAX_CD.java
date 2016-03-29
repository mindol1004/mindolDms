package dms.constants.enums.sapjco.elem;

/**
 * 세믁코드
 * @author greatjin
 *
 */
public enum SAP_TAX_CD {
	 INTAX10_TAXBILL ("V0") //매입부가사 10%-세금계산서 일반
  ,  OUTTAX10        ("A0") //매출부가세(10%)-세금계산서(일반) 10
  ,  OUTTAX0_LOCAL   ("A1") //매출부가세(0%)-세금계산서(영세율-local) 11
  ,  OUTTAX0_DIRECT  ("A2") //매출부가세(0%)-영세율(direct)
  ,  OUTTAX10_DIRECT ("A9") //매출부가세(10%)-세금계산서(기타수익)  19
	;
	private String code;
		
	SAP_TAX_CD(String code)
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
