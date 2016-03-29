package fwk.common;

import java.util.List;
import java.util.Locale;

import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.core.util.DateUtils;
import fwk.common.internal.ImplCommonArea;
import fwk.common.internal.ImplFlatHeader;
import fwk.flat.FlatHeader;

/**
 * 아웃바운드 헤더
 */
public final class OutboundHeader implements java.io.Serializable {

	private static final long serialVersionUID = 1808334607862303263L;
	
	private String orgGlobId; //현재 거래의 글로벌ID
	private String orgTxId; //현재 거래의 거래코드
	private ImplFlatHeader flatHeader;
	private String yyyyMMddHHmmssSSS; //현재시각
	private IResultMessage resultMessage;

	/**
	 * 생성자
	 * 
	 * @param onlineCtx
	 *            온라인컨텍스트
	 */
	public OutboundHeader(IOnlineContext onlineCtx) {
		init(onlineCtx);
		setRequest();
	}
	
	public FlatHeader getFlatHeader(){
		return flatHeader;
	}

	/**
	 * 요청전문으로 설정
	 */
	public void setRequest() {
		setMesg(CommonUtils.MESG_DVCD.Q.name(), "1"); // 전문구분코드 ('Q:요청', 'R:응답', 'P:푸시') 
	}

	/**
	 * 응답전문으로 설정
	 */
	public void setResponse() {
		setMesg(CommonUtils.MESG_DVCD.R.name(), "1"); // 전문구분코드 ('Q:요청', 'R:응답', 'P:푸시') 
	}
	
	/**
	 * PUSH전문으로 설정
	 */
	public void setPush(String mesgTycd) {
		setMesg(CommonUtils.MESG_DVCD.P.name(), mesgTycd); // 전문구분코드 ('Q:요청', 'R:응답', 'P:푸시') 
	}
	
	
	/**
	 * 표준전문헤더 메시지부 등록
	 * 
	 * @param messageId
	 *            메시지아이디
	 */
	public final void addMsg(String messageId) {
		addMsg(messageId, null);
	}

	/**
	 * 표준전문헤더 메시지부 등록
	 * 
	 * @param messageId
	 *            메시지아이디
	 * @param messageParams
	 *            메시지 생성 파라미터
	 */
	public final void addMsg(String messageId, String[] messageParams) {
		Locale locale = flatHeader.getUserLocale() == null || flatHeader.getUserLocale().trim().length() < 1 ? null : BaseUtils.asLocale(flatHeader.getUserLocale());
		flatHeader.addMsg(new TrtmRsltMsg(messageId, BaseUtils.getMessage(messageId, locale, messageParams)));
	}
	
	public final void clearMsgList(){
		flatHeader.clearMsgList();
	}

    public List<TrtmRsltMsg> getMsgList(){
    	return flatHeader.getMsgList();
    }

	/**
	 * 응답전문일때, 업무수행 결과를 성공으로 표시하기 위한 메시지
     * @param messageId     메시지 아이디
     * @param messageParams 메시지 파라미터
     */
    public void setOkResultMessage(String messageId, String[] messageParams){
    	resultMessage = new ResultMessage(IResultMessage.OK, messageId, messageParams);
    }
    
	/**
	 * 응답전문일때, 업무수행 결과를 실패로 표시하기 위한 메시지
     * @param messageId     메시지 아이디
     * @param messageParams 메시지 파라미터
     * @param th            에러 메시지 표현할 때 관련 예외
     */
    public void setFailResultMessage(String messageId, String[] messageParams, Throwable th){
    	flatHeader.clearMsgList();
    	resultMessage = new ResultMessage(IResultMessage.FAIL, messageId, messageParams, th);
    }
    
    public IResultMessage getResultMessage(){
    	return resultMessage;
    }
    
	/**
	 * 글로벌 ID를 강제로 변경한다.
	 * @param globId 글로벌 ID
	 */
	public void setGlobId(String globId){
		flatHeader.setGlobId(globId);
		// 진행일련번호 - 전문전송시 +1되므로 전문상으로는 999임.
		flatHeader.setPrgsSrno(998);                 
	}

	/**
	 * 최초전송채널코드
	 * @param frstTrnmChnlCd 최초전송채널코드
	 */
	public void setFrstTrnmChnlCd(String frstTrnmChnlCd){
		flatHeader.setFrstTrnmChnlCd(frstTrnmChnlCd);
	}

	/**
	 * MCI 전송노드번호
	 * @param mciTrnmNodeNo MCI 전송노드번호
	 */
	public void setMciTrnmNodeNo(int mciTrnmNodeNo){
		flatHeader.setMciTrnmNodeNo(mciTrnmNodeNo);
	}
	
	/**
	 * 거래코드
	 * 
	 * @param trnCd
	 *            거래코드
	 */
	public void setTrnCd(String trnCd) {
		flatHeader.setTrnCd(trnCd);
	}

	/**
	 * IP주소
	 * 
	 * @param ipad
	 *            IP주소
	 */
	public void setIpAddr(String ipad) {
		flatHeader.setIpad(ipad);
	}

	/**
	 * 부점코드
	 * 
	 * @param brCd
	 *            부점코드
	 */
	public void setBrCd(String brCd) {
		flatHeader.setBrCd(brCd);
	}
	
	/**
	 * 사용자번호
	 * @param userNo 사용자번호
	 */
	public void setUsrNo(String userNo) {
		flatHeader.setUserNo(userNo);
	}
	
	/**
	 * 화면번호
	 * @param scrnNo 화면번호
	 */
	public void setScrnNo(String scrnNo){
		flatHeader.setScrnNo(scrnNo);
	}
	
	/**
	 * 대외기관코드
	 * @param xtisCd 대외기관코드
	 */
	public void setXtisCd(String xtisCd){
		flatHeader.setXtisCd(xtisCd);
	}

	/**
	 * 업무서버코드 
	 * @param bzwrSvrCd 업무서버코드
	 */
	public void setBzwrSvrCd(String bzwrSvrCd){
		flatHeader.setBzwrSvrCd(bzwrSvrCd);
	}

	/**
	 * 대외전문코드
	 * @param otsdMesgCd 대외전문코드
	 */
	public void setOtsdMesgCd(String otsdMesgCd){
		flatHeader.setOtsdMesgCd(otsdMesgCd);
	}

	/**
	 * 대외전문처리코드
	 * @param otsdMesgTrtmCd 대외전문처리코드
	 */
	public void setOtsdMesgTrtmCd(String otsdMesgTrtmCd){
		flatHeader.setOtsdMesgTrtmCd(otsdMesgTrtmCd);
	}

	/**
	 * 대외거래고유번호
	 * @param otsdTrnUnqNo 대외거래고유번호
	 */
	public void setOtsdTrnUnqNo(String otsdTrnUnqNo){
		flatHeader.setOtsdTrnUnqNo(otsdTrnUnqNo);
	}

	/**
	 * 대외응답거래코드
	 * @param otsdRespTrnCd 대외응답거래코드
	 */
	public void setOtsdRespTrnCd(String otsdRespTrnCd){
		flatHeader.setOtsdRespTrnCd(otsdRespTrnCd);
	}

	/**
	 * 채널메시지코드
	 * @param chnlMsgCd 채널메시지코드
	 */
	public void setChnlMsgCd(String chnlMsgCd){
		flatHeader.setChnlMsgCd(chnlMsgCd);
	}

	/**
	 * 예비문자열내용
	 * @param sprChrsCntn 예비문자열내용
	 */
	public void setSprChrsCntn(String sprChrsCntn){
		flatHeader.setSprChrsCntn(sprChrsCntn);
	}
	
	/**
	 * 현재 거래의 글로벌ID
	 * @return
	 */
	public String getOrgGlobId(){
		return orgGlobId;
	}
	
	/**
	 * 현재 거래의 거래코드
	 * @return
	 */
	public String getOrgTxId(){
		return orgTxId;
	}
	
	/**
	 * 초기화
	 */
 	private void init(IOnlineContext onlineCtx) {
		this.flatHeader = makeFlatHeader(onlineCtx);
		setMesg("Q", "1"); // 요청응답구분 ('Q:요청', 'R:응답', 'P:푸시') 
	}

    /**
     * 전문구분
     */
	private void setMesg(String mesgDvcd, String mesgTycd){
		flatHeader.setMesgDvcd(mesgDvcd);  // 전문구분코드 ('Q:요청', 'R:응답', 'P:푸시')
		flatHeader.setMesgTycd(mesgTycd);                 // 전문유형코드
		if(CommonUtils.MESG_DVCD.Q.name().equals(mesgDvcd)){
			flatHeader.setMesgRespDttm(null);                // 전문응답일시
		}
		else{
			flatHeader.setMesgRespDttm(yyyyMMddHHmmssSSS);   // 전문응답일시
		}
	}

	private ImplFlatHeader makeFlatHeader(IOnlineContext onlineCtx) {
		if(onlineCtx == null){
			throw new RuntimeException("IOnlineContext is null.");
		}

		CommonArea ca = CommonUtils.getCommonArea(onlineCtx);
		if(ca == null){
			throw new RuntimeException("CommonArea is null.");
		}
		
		ImplFlatHeader orgFlatHeader = ((ImplCommonArea)ca).existsFlatHeader();
		if(orgFlatHeader == null){
			throw new RuntimeException("FlatHeader is null.");
		}
		
		// 현재 거래 정보를 관리한다.
		orgGlobId = onlineCtx.getTransaction().getRequestId();
		orgTxId = onlineCtx.getTransaction().getTxId();
		
		// 현재 FlatHeader를 복제한다.
		ImplFlatHeader flatHeader = orgFlatHeader.clone();

		yyyyMMddHHmmssSSS = DateUtils.getCurrentDate("yyyyMMddHHmmssSSS"); //현재시각
		
		flatHeader.setWhlMesgLen(-1);   // 전체전문길이
		flatHeader.setStndHdrLen(-1);   // 표준헤더부길이
		
		if(flatHeader.getFrstTrnmChnlCd() == null || flatHeader.getFrstTrnmChnlCd().trim().length() < 1){
			flatHeader.setFrstTrnmChnlCd(CommonUtils.CHNL_CD.FWKC.name()); //최초전송채널코드
		}
		flatHeader.setTrnmChnlCd(CommonUtils.CHNL_CD.FWKC.name()); // 전송채널코드
		flatHeader.setTrnmNodeNo(0);   // 전송노드번호

		if(flatHeader.getMesgDmndDttm() == null){
			flatHeader.setMesgDmndDttm(yyyyMMddHHmmssSSS); // 전문요청일시
		}

		flatHeader.setMesgCntySrno(0); // 전문연속일련번호
		
		return flatHeader;
	}
	
}
