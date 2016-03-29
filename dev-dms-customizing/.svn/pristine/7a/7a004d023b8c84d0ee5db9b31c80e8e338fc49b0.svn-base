package fwk.common;

/**
 * 처리결과 메시지
 */
public final class TrtmRsltMsg implements java.io.Serializable {
	
	private static final long serialVersionUID = 4469075940328688546L;

	private String msgCd                ; // 메시지 코드
	private String msgCntn              ; // 메시지 내용   
	private int    erorOcrnPrrmLine     ; // 오류발생 프로그램 라인
	private String erorOcrnPrrmNm       ; // 오류발생 프로그램명
	
	/**
	 * 생성자
	 * @param msgCd 메시지 코드
	 * @param msgCntn 메시지 내용   
	 */
	public TrtmRsltMsg(String msgCd, String msgCntn){
		this(msgCd, msgCntn, 0, null);
	}
	
	/**
	 * 생성자
	 * @param msgCd 메시지 코드
	 * @param msgCntn 메시지 내용   
	 * @param erorOcrnPrrmLine 오류발생 프로그램 라인
	 * @param erorOcrnPrrmNm 오류발생 프로그램명
	 */
	public TrtmRsltMsg(String msgCd, String msgCntn, int erorOcrnPrrmLine, String erorOcrnPrrmNm){
		this.msgCd = msgCd;
		this.msgCntn = msgCntn;
		this.erorOcrnPrrmLine = erorOcrnPrrmLine;
		this.erorOcrnPrrmNm = erorOcrnPrrmNm;
	}

	/**
	 * 메시지 코드
	 * @return 메시지 코드
	 */
	public String getMsgCd() {
		return msgCd;
	}

	/**
	 * 메시지 내용  
	 * @return 메시지 내용  
	 */
	public String getMsgCntn() {
		return msgCntn;
	}

	/**
	 * 오류발생 프로그램 라인
	 * @return 오류발생 프로그램 라인
	 */
	public int getErorOcrnPrrmLine() {
		return erorOcrnPrrmLine;
	}

	/**
	 * 오류발생 프로그램명
	 * @return 오류발생 프로그램명
	 */
	public String getErorOcrnPrrmNm() {
		return erorOcrnPrrmNm;
	}

}
