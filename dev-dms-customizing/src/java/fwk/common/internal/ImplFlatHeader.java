package fwk.common.internal;

import java.util.ArrayList;
import java.util.List;

import fwk.common.TrtmRsltMsg;
import fwk.flat.FlatHeader;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class ImplFlatHeader  extends FlatHeader  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8626737278897632145L;
	
	private int whlMesgLen;
	private int stndHdrLen;
	private String globId;
	private int prgsSrno;
	private String ipad;
	private String prcmMac;
	private String trnTrnmNo;
	private String ssoSesnKey;
	private String frstTrnmChnlCd;
	private String trnmChnlCd;
	private int trnmNodeNo;
	private int mciTrnmNodeNo;
	private String envDvcd;
	private String mesgDmndDttm;
	private String mesgVrsnDvcd;
	private String trnCd;
	private String scrnNo;
	private String mesgRespDttm;
	private String trnPtrnDvcd;
	private String mesgDvcd;
	private String mesgTycd;
	private int mesgCntySrno;
	private String trtmRsltCd;
	private String cmpgRelmUseDvcd;
	private String compCd;
	private String deptCd;
	private String brCd;
	private String userNo;
	private String userLocale;
	private String ctiYn;
	private String cshnOcrnYn;
	private double cashAmt;
	private double pointAmt;
	private String xtisCd;
	private String bzwrSvrCd;
	private String otsdMesgCd;
	private String otsdMesgTrtmCd;
	private String otsdTrnUnqNo;
	private String otsdRespTrnCd;
	private String chnlMsgCd;
//	private String eaiGlobId; //EAI Global ID
//	private String eaiIntfId; //EAI 인터페이스 ID
//	private String eaiRecvSvcid; //EAI 결과 수신서비스 ID
	private String sprChrsCntn;
	
	//VOC연계로 인한 항목추가 (14.11.10 PSI)
	private String reqBrndCd;
	private String reqChnlCd;
	private String isBckOffice;
	
	// 메시지부 (별도로 관리, 입력전문의 메시지는 관리하지 않음)
	private List<TrtmRsltMsg> msgList;


	/**
	 * 전체전문길이
	 * @return 전체전문길이
	 */
	public int getWhlMesgLen() {
		return whlMesgLen;
	}
	
	/**
	 * 전체전문길이
	 * @param whlMesgLen 전체전문길이
	 */
	public void setWhlMesgLen(int whlMesgLen) {
		this.whlMesgLen = whlMesgLen;
	}

	/**
	 * 표준헤더부길이
	 * @return 표준헤더부길이
	 */
	public int getStndHdrLen() {
		return stndHdrLen;
	}

	/**
	 * 표준헤더부길이
	 * @param stndHdrLen 표준헤더부길이
	 */
	public void setStndHdrLen(int stndHdrLen) {
		this.stndHdrLen = stndHdrLen;
	}

	/**
	 * 글로벌 ID
	 * @return 글로벌 ID
	 */
	public String getGlobId() {
		return globId;
	}

	/**
	 * 글로벌 ID
	 * @param globId 글로벌 ID
	 */
	public void setGlobId(String globId) {
		this.globId = globId;
	}

	/**
	 * 진행일련번호
	 * @return 진행일련번호
	 */
	public int getPrgsSrno() {
		return prgsSrno;
	}

	/**
	 * 진행일련번호
	 * @param prgsSrno 진행일련번호
	 */
	public void setPrgsSrno(int prgsSrno) {
		this.prgsSrno = prgsSrno;
	}

	/**
	 * IP주소
	 * @return IP주소
	 */
	public String getIpad() {
		return ipad;
	}

	/**
	 * IP주소
	 * @param ipad IP주소
	 */
	public void setIpad(String ipad) {
		this.ipad = ipad;
	}

	/**
	 * PC MAC주소
	 * @return PC MAC주소
	 */
	public String getPrcmMac() {
		return prcmMac;
	}

	/**
	 * PC MAC주소
	 * @param prcmMac PC MAC주소
	 */
	public void setPrcmMac(String prcmMac) {
		this.prcmMac = prcmMac;
	}

	/**
	 * 거래단말번호
	 * @return 거래단말번호
	 */
	public String getTrnTrnmNo() {
		return trnTrnmNo;
	}
	
	/**
	 * 거래단말번호
	 * @param trnTrnmNo 거래단말번호
	 */
	public void setTrnTrnmNo(String trnTrnmNo) {
		this.trnTrnmNo = trnTrnmNo;
	}

	/**
	 * SSO 세션 KEY
	 * @return SSO 세션 KEY
	 */
	public String getSsoSesnKey() {
		return ssoSesnKey;
	}

	/**
	 * SSO 세션 KEY
	 * @param ssoSesnKey SSO 세션 KEY
	 */
	public void setSsoSesnKey(String ssoSesnKey) {
		this.ssoSesnKey = ssoSesnKey;
	}

	/**
	 * 최초전송채널코드
	 * @return 최초전송채널코드
	 */
	public String getFrstTrnmChnlCd() {
		return frstTrnmChnlCd;
	}

	/**
	 * 최초전송채널코드
	 * @param frstTrnmChnlCd 최초전송채널코드
	 */
	public void setFrstTrnmChnlCd(String frstTrnmChnlCd) {
		this.frstTrnmChnlCd = frstTrnmChnlCd;
	}

	/**
	 * 전송채널코드
	 * @return 전송채널코드
	 */
	public String getTrnmChnlCd() {
		return trnmChnlCd;
	}

	/**
	 * 전송채널코드
	 * @param trnmChnlCd 전송채널코드
	 */
	public void setTrnmChnlCd(String trnmChnlCd) {
		this.trnmChnlCd = trnmChnlCd;
	}

	/**
	 * 전송노드번호
	 * @return 전송노드번호
	 */
	public int getTrnmNodeNo() {
		return trnmNodeNo;
	}

	/**
	 * 전송노드번호
	 * @param trnmNodeNo 전송노드번호
	 */
	public void setTrnmNodeNo(int trnmNodeNo) {
		this.trnmNodeNo = trnmNodeNo;
	}

	/**
	 * MCI 전송노드번호
	 * @return MCI 전송노드번호
	 */
	public int getMciTrnmNodeNo() {
		return mciTrnmNodeNo;
	}

	/**
	 * MCI 전송노드번호
	 * @param mciTrnmNodeNo MCI 전송노드번호
	 */
	public void setMciTrnmNodeNo(int mciTrnmNodeNo) {
		this.mciTrnmNodeNo = mciTrnmNodeNo;
	}

	/**
	 * 환경구분코드
	 * @return 환경구분코드
	 */
	public String getEnvDvcd() {
		return envDvcd;
	}

	/**
	 * 환경구분코드
	 * @param envDvcd 환경구분코드
	 */
	public void setEnvDvcd(String envDvcd) {
		this.envDvcd = envDvcd;
	}

	
	/**
	 * 전문요청일시
	 * @return 전문요청일시
	 */
	public String getMesgDmndDttm() {
		return mesgDmndDttm;
	}

	/**
	 * 전문요청일시
	 * @param mesgDmndDttm 전문요청일시
	 */
	public void setMesgDmndDttm(String mesgDmndDttm) {
		this.mesgDmndDttm = mesgDmndDttm;
	}

	/**
	 * 전문버전구분코드
	 * @return 전문버전구분코드
	 */
	public String getMesgVrsnDvcd() {
		return mesgVrsnDvcd;
	}

	/**
	 * 전문버전구분코드
	 * @param mesgVrsnDvcd 전문버전구분코드
	 */
	public void setMesgVrsnDvcd(String mesgVrsnDvcd) {
		this.mesgVrsnDvcd = mesgVrsnDvcd;
	}

	/**
	 * 거래코드
	 * @return 거래코드
	 */
	public String getTrnCd() {
		return trnCd;
	}

	/**
	 * 거래코드
	 * @param trnCd 거래코드
	 */
	public void setTrnCd(String trnCd) {
		this.trnCd = trnCd;
	}

	/**
	 * 화면번호
	 * @return 화면번호
	 */
	public String getScrnNo() {
		return scrnNo;
	}

	/**
	 * 화면번호
	 * @param scrnNo 화면번호
	 */
	public void setScrnNo(String scrnNo) {
		this.scrnNo = scrnNo;
	}

	/**
	 * 전문응답일시
	 * @return 전문응답일시
	 */
	public String getMesgRespDttm() {
		return mesgRespDttm;
	}

	/**
	 * 전문응답일시
	 * @param mesgRespDttm 전문응답일시
	 */
	public void setMesgRespDttm(String mesgRespDttm) {
		this.mesgRespDttm = mesgRespDttm;
	}

	/**
	 * 거래유형구분코드
	 * @return
	 */
	public String getTrnPtrnDvcd() {
		return trnPtrnDvcd;
	}

	/**
	 * 거래유형구분코드
	 * @param trnPtrnDvcd 거래유형구분코드
	 */
	public void setTrnPtrnDvcd(String trnPtrnDvcd) {
		this.trnPtrnDvcd = trnPtrnDvcd;
	}

	/**
	 * 전문구분코드
	 * @return 전문구분코드
	 */
	public String getMesgDvcd() {
		return mesgDvcd;
	}

	/**
	 * 전문구분코드
	 * @param mesgDvcd 전문구분코드
	 */
	public void setMesgDvcd(String mesgDvcd) {
		this.mesgDvcd = mesgDvcd;
	}

	/**
	 * 전문유형코드
	 * @return 전문유형코드
	 */
	public String getMesgTycd() {
		return mesgTycd;
	}

	/**
	 * 전문유형코드
	 * @param mesgTycd 전문유형코드
	 */
	public void setMesgTycd(String mesgTycd) {
		this.mesgTycd = mesgTycd;
	}

	/**
	 * 전문연속일련번호
	 * @return 전문연속일련번호
	 */
	public int getMesgCntySrno() {
		return mesgCntySrno;
	}

	/**
	 * 전문연속일련번호
	 * @param mesgCntySrno 전문연속일련번호
	 */
	public void setMesgCntySrno(int mesgCntySrno) {
		this.mesgCntySrno = mesgCntySrno;
	}
	

	/**
	 * 처리결과코드
	 * @return 처리결과코드
	 */
	public String getTrtmRsltCd() {
		return trtmRsltCd;
	}

	/**
	 * 처리결과코드
	 * @param trtmRsltCd 처리결과코드
	 */
	public void setTrtmRsltCd(String trtmRsltCd) {
		this.trtmRsltCd = trtmRsltCd;
	}

	/**
	 * 캠패인영역사용구분코드
	 * @return 캠패인영역사용구분코드
	 */
	public String getCmpgRelmUseDvcd() {
		return cmpgRelmUseDvcd;
	}

	/**
	 * 캠패인영역사용구분코드
	 * @param cmpgRelmUseDvcd 캠패인영역사용구분코드
	 */
	public void setCmpgRelmUseDvcd(String cmpgRelmUseDvcd) {
		this.cmpgRelmUseDvcd = cmpgRelmUseDvcd;
	}

	/**
	 * 회사코드
	 * @return 회사코드
	 */
	public String getCompCd() {
		return compCd;
	}

	/**
	 * 회사코드
	 * @param compCd 회사코드
	 */
	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}

	/**
	 * 부서코드
	 */
	public String getDeptCd() {
		return deptCd;
	}

	/**
	 * 부서코드
	 * @param deptCd 부서코드
	 */
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	/**
	 * 부점코드
	 * @return 부점코드
	 */
	public String getBrCd() {
		return brCd;
	}

	/**
	 * 부점코드
	 * @param brCd 부점코드
	 */
	public void setBrCd(String brCd) {
		this.brCd = brCd;
	}

	/**
	 * 사용자번호
	 * @return 사용자번호
	 */
	public String getUserNo() {
		return userNo;
	}

	/**
	 * 사용자번호
	 * @param userNo 사용자번호
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	/**
	 * 사용자로케일
	 * @return 사용자로케일
	 */
	public String getUserLocale() {
		return userLocale;
	}

	/**
	 * 사용자로케일
	 * @param userLocale 사용자로케일
	 */
	public void setUserLocale(String userLocale) {
		this.userLocale = userLocale;
	}
	
	/**
	 * CTI여부
	 */
	public String getCtiYn() {
		return ctiYn;
	}

	/**
	 * CTI여부
	 * @param ctiYn CTI여부
	 */
	public void setCtiYn(String ctiYn) {
		this.ctiYn = ctiYn;
	}
	
	/**
	 * 시재발생여부
	 * @return 시재발생여부
	 */
	public String getCshnOcrnYn() {
		return cshnOcrnYn;
	}

	/**
	 * 시재발생여부
	 * @param cshnOcrnYn 시재발생여부
	 */
	public void setCshnOcrnYn(String cshnOcrnYn) {
		this.cshnOcrnYn = cshnOcrnYn;
	}

	/**
	 * 현금금액
	 * @return 현금금액
	 */
	public double getCashAmt() {
		return cashAmt;
	}

	/**
	 * 현금금액
	 * @param cashAmt 현금금액
	 */
	public void setCashAmt(double cashAmt) {
		this.cashAmt = cashAmt;
	}

	/**
	 * 포인트금액
	 * @return 포인트금액
	 */
	public double getPointAmt() {
		return pointAmt;
	}

	/**
	 * 포인트금액
	 * @param pointAmt 포인트금액
	 */
	public void setPointAmt(double pointAmt) {
		this.pointAmt = pointAmt;
	}


	/**
	 * 대외기관코드
	 * @return 대외기관코드
	 */
	public String getXtisCd() {
		return xtisCd;
	}

	/**
	 * 대외기관코드
	 * @param xtisCd 대외기관코드
	 */
	public void setXtisCd(String xtisCd) {
		this.xtisCd = xtisCd;
	}

	/**
	 * 업무서버코드
	 * @return 업무서버코드
	 */
	public String getBzwrSvrCd() {
		return bzwrSvrCd;
	}

	/**
	 * 업무서버코드
	 * @param bzwrSvrCd 업무서버코드
	 */
	public void setBzwrSvrCd(String bzwrSvrCd) {
		this.bzwrSvrCd = bzwrSvrCd;
	}

	/**
	 * 대외전문코드
	 * @return 대외전문코드
	 */
	public String getOtsdMesgCd() {
		return otsdMesgCd;
	}

	/**
	 * 대외전문코드
	 * @param otsdMesgCd 대외전문코드
	 */
	public void setOtsdMesgCd(String otsdMesgCd) {
		this.otsdMesgCd = otsdMesgCd;
	}

	/**
	 * 대외전문처리코드
	 * @return 대외전문처리코드
	 */
	public String getOtsdMesgTrtmCd() {
		return otsdMesgTrtmCd;
	}

	/**
	 * 대외전문처리코드
	 * @param otsdMesgTrtmCd 대외전문처리코드
	 */
	public void setOtsdMesgTrtmCd(String otsdMesgTrtmCd) {
		this.otsdMesgTrtmCd = otsdMesgTrtmCd;
	}

	/**
	 * 대외거래고유번호
	 * @return 대외거래고유번호
	 */
	public String getOtsdTrnUnqNo() {
		return otsdTrnUnqNo;
	}

	/**
	 * 대외거래고유번호
	 * @param otsdTrnUnqNo 대외거래고유번호
	 */
	public void setOtsdTrnUnqNo(String otsdTrnUnqNo) {
		this.otsdTrnUnqNo = otsdTrnUnqNo;
	}

	/**
	 * 대외응답거래코드
	 * @return 대외응답거래코드
	 */
	public String getOtsdRespTrnCd() {
		return otsdRespTrnCd;
	}

	/**
	 * 대외응답거래코드
	 * @param otsdRespTrnCd 대외응답거래코드
	 */
	public void setOtsdRespTrnCd(String otsdRespTrnCd) {
		this.otsdRespTrnCd = otsdRespTrnCd;
	}

//	/**
//	 * EAI Global ID
//	 */
//	public String getEaiGlobId() {
//		return getEai;
//	}
	
//	/**
//	 * EAI Global ID
//	 * @param eaiGlobId
//	 */
//	public void setEaiGlobId(String eaiGlobId) {
//		this.eaiGlobId = eaiGlobId;
//	}
	
//	/**
//	 *EAI 인터페이스 ID 
//	 * @return
//	 */
//	public String getEaiIntfId() {
//		return eaiIntfId;
//	}
//
//	/**
//	 * EAI 인터페이스 ID
//	 * @param eaiIntfId
//	 */
//	public void setEaiIntfId(String eaiIntfId) {
//		this.eaiIntfId = eaiIntfId;
//	}

//	/**
//	 * 결과수신서비스ID
//	 * @return
//	 */
//	public String getEaiRecvSvcid() {
//		return eaiRecvSvcid;
//	}

//	/**
//	 * 결과수신서비스ID
//	 * @param eaiRecvSvcid
//	 */
//	public void setEaiRecvSvcid(String eaiRecvSvcid) {
//		this.eaiRecvSvcid = eaiRecvSvcid;
//	}

	/**
	 * 채널메시지코드
	 * @return 채널메시지코드
	 */
	public String getChnlMsgCd() {
		return chnlMsgCd;
	}

	/**
	 * 채널메시지코드
	 * @param chnlMsgCd 채널메시지코드
	 */
	public void setChnlMsgCd(String chnlMsgCd) {
		this.chnlMsgCd = chnlMsgCd;
	}

	
	
	/**
	 * 요청브랜드코드
	 *  
	 * @return String
	 */
	public String getReqBrndCd() {
        return reqBrndCd;
    }

    /**
     * 요청브랜드코드
     *  
     * @param reqBrndCd void
     */
    public void setReqBrndCd(String reqBrndCd) {
        this.reqBrndCd = reqBrndCd;
    }

    /**
     * UI요청채널코드
     *  
     * @return String
     */
    public String getReqChnlCd() {
        return reqChnlCd;
    }

    /**
     * UI요청채널코드
     *  
     * @param reqChnlCd void
     */
    public void setReqChnlCd(String reqChnlCd) {
        this.reqChnlCd = reqChnlCd;
    }

    /**
     * 백오피스여부
     *  
     * @return String
     */
    public String getIsBckOffice() {
        return isBckOffice;
    }

    /**
     * 백오피스여부
     *  
     * @param isBckOffice void
     */
    public void setIsBckOffice(String isBckOffice) {
        this.isBckOffice = isBckOffice;
    }

    /**
	 * 예비문자열내용
	 * @return 예비문자열내용
	 */
	public String getSprChrsCntn() {
		return sprChrsCntn;
	}

	/**
	 * 예비문자열내용
	 * @param sprChrsCntn 예비문자열내용
	 */
	public void setSprChrsCntn(String sprChrsCntn) {
		this.sprChrsCntn = sprChrsCntn;
	}
	/**
	 * 메시지건수
	 * @return 메시지건수
	 */
	public int getMsgCcnt(){
		return msgList == null ? 0 : msgList.size();
	}
	
	/**
	 * 메시지
	 * @param index 순번
	 * @return 메시지
	 */
	public TrtmRsltMsg getMsg(int index){
		if(msgList != null && index < msgList.size()){
			return msgList.get(index);
		}
		return null;
	}
	
	/**
	 * 메시지 등록
	 * @param msg 메시지
	 */
	public void addMsg(TrtmRsltMsg msg){
		if(msgList == null){
			msgList = new ArrayList<TrtmRsltMsg>(10);
		}
		msgList.add(msg);
	}
	
	/**
	 * 메시지목록 조회
	 * @return 메시지목록
	 */
	public List<TrtmRsltMsg> getMsgList(){
		return msgList;
	}
	
	/**
	 * 메시지목록 등록
	 * @param msgList 메시지목록
	 */
	public void setMsgList(List<TrtmRsltMsg> msgList){
		this.msgList = msgList;
	}
	
	/**
	 * 메시지 목록 초기화
	 */
	public void clearMsgList(){
		if(msgList != null){
			msgList.clear();
		}
	}
	
	/**
	 * 객체 복제
	 */
	public ImplFlatHeader clone() {
		ImplFlatHeader entity = (ImplFlatHeader)cloneBean();

		// 메시지 복제대상 아님.
		entity.msgList = null;
		return entity;
	}
	
}
