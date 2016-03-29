package fwk.flat;

import java.util.List;

import fwk.common.AbsEntity;
import fwk.common.TrtmRsltMsg;

public abstract class FlatHeader  extends AbsEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2431793767938239440L;

	/**
	 * 전체 전문길이
	 * @return
	 */
	public abstract int getWhlMesgLen();
	
	/**
	 * 표준헤더부길이
	 * @return 표준헤더부길이
	 */
	public abstract int getStndHdrLen();
	
	/**
	 * Global ID 취득
	 * @return
	 */
	public abstract String getGlobId();


	/**
	 * 진행일련번호	
	 * @return
	 */
	public abstract int getPrgsSrno();

	/**
	 * IP 주소 취득
	 * @return
	 */
	public abstract String getIpad();

	/**
	 * Mac Addr 취득
	 * @return
	 */
	public abstract String getPrcmMac();

	/**
	 * 거래단말번호
	 * @return 거래단말번호
	 */
	public abstract String getTrnTrnmNo();
	
	/**
	 * SSO 키 취득
	 * @return
	 */
	public abstract String getSsoSesnKey();

	/**
	 * 최초 채널코드 설정
	 * @return
	 */
	public abstract String getFrstTrnmChnlCd();

	/**
	 * 전송채널코드 취득
	 * @return
	 */
	public abstract String getTrnmChnlCd();

	/**
	 * 전송노드번호
	 * @return 전송노드번호
	 */
	public abstract int getTrnmNodeNo();
	
	/**
	 * MCI 전송노드번호
	 * @return MCI 전송노드번호
	 */
	public abstract int getMciTrnmNodeNo() ;
	
	/**
	 * 환경구분코드 취득
	 * @return
	 */
	public abstract String getEnvDvcd();

	/**
	 * 거래요청일시 취득
	 * @return
	 */
	public abstract String getMesgDmndDttm();


	/**
	 * 인터페이스 구분코드
	 * 표준전문버전 예: v0.99->099, v1.00 -> 100, v1.01 -> 101
	 * @return
	 */
	public abstract String getMesgVrsnDvcd();


	/**
	 * 거래코드
	 * @return
	 */
	public abstract String getTrnCd();


	/**
	 * 화면번호
	 * @return
	 */
	public abstract String getScrnNo() ;


	/**
	 * 전문응답일시 취득
	 * @return
	 */
	public abstract String getMesgRespDttm();

	/**
	 * 거래유형구분코드
	 * @return
	 */
	public abstract String getTrnPtrnDvcd();
	
	/**
	 * 전문구분 코드 Q:요청, R:응답, P:Push메시지
	 * @return
	 */
	public abstract String getMesgDvcd();


	/**
	 * 전문유형코드
	 *  전문구분이 "요청(Q)" 일 때
   		- 1:일반거래전문, 2:대량입력시작, 3:대량입력중간, 9:대량입력마지막
	 * 전문구분이 "응답(R)" 일 때
   		- 1:일반거래전문, 2:대량출력시작, 3:대량출력중간, 9:대량출력마지막, D:Dummy
	 * 전문구분이 "푸쉬(P)" 일 때
   		- S:일반화면Push, M:메시지Push, L:링크메시지 Push, O: Modal 메시지 Push, X:전일자시작, 
   		- Y:전일자종료, H:CRM숨김, D:CRM표시, I:인터넷뱅킹Push(MCI에서 단말이 아닌 인터넷 뱅킹으로 전문 전달)
	 * @return
	 */
	public abstract String getMesgTycd();

	/**
	 * 전문연속일련번호
	 * @return 전문연속일련번호
	 */
	public abstract int getMesgCntySrno();
	
	/**
	 * 처리결과코드
	 * @return
	 */
	public abstract String getTrtmRsltCd();


	/**
	 * 캠패인영역사용구분코드	
	 * @return
	 */
	public abstract String getCmpgRelmUseDvcd();


	/**
	 * 계열사코드	ex)파리바게뜨, 베스킨라빈스 등등
	 * @return
	 */
	public abstract String getCompCd();


	/**
	 * 부서코드
	 * @return
	 */
	public abstract String getDeptCd();

	/**
	 * 부점코드
	 * @return
	 */
	public abstract String getBrCd();

	/**
	 * 사번
	 * @return
	 */
	public abstract String getUserNo();

	/**
	 * 사용자로케일
	 * @return 사용자로케일
	 */
	public abstract String getUserLocale();

	/**
	 * CTI여부
	 * @return
	 */
	public abstract String getCtiYn();

	/**
	 * 시재발생여부
	 * @return 시재발생여부
	 */
	public abstract String getCshnOcrnYn();
	
	/**
	 * 현금금액
	 * @return 현금금액
	 */
	public abstract double getCashAmt();
	
	/**
	 * 포인트금액
	 * @return 포인트금액
	 */
	public abstract double getPointAmt();
	
	/**
	 * 대외기관코드 취득
	 * @return
	 */
	public abstract String getXtisCd();


	/**
	 * 업무서버코드 취득
	 * @return
	 */
	public abstract  String getBzwrSvrCd();

	/**
	 * 대외전문코드 취득
	 * @return
	 */
	public abstract String getOtsdMesgCd();

	/**
	 * 대외전문처리코드 취득. 개설거래를 위한 코드. FEP에서 셋팅해서 넘겨줌
	 * @return
	 */
	public abstract String getOtsdMesgTrtmCd();


	/**
	 * 대외거래고유번호 취득
	 * @return
	 */
	public abstract String getOtsdTrnUnqNo();


	/**
	 * 대외응답거래코드 취득
	 * @return
	 */
	public abstract String getOtsdRespTrnCd();

//	/**
//	 * EAI Global ID
//	 * @return
//	 */
//	public abstract String getEaiGlobId();
//	
//	/**
//	 * EAI 인터페이스 ID
//	 * @return
//	 */
//	public abstract String getEaiIntfId();
//	
//	/**
//	 * EAI 결과수신서비스ID
//	 * @return
//	 */
//	public abstract String getEaiRecvSvcid();
//	
	/**
	 * 채널메시지코드 취득. 채널로 전송할 메시지코드 셋팅 - 업무팀에서 셋팅
	 * @return
	 */
	public abstract String getChnlMsgCd();

	/**
	 * 예비문자열내용
	 * @return 예비문자열내용
	 */
	public abstract String getSprChrsCntn();
	
	/**
	 * 메시지건수
	 * @return 메시지건수
	 */
	public abstract int getMsgCcnt();
	
	/**
	 * 메시지
	 * @param index 순번
	 * @return 메시지
	 */
	public abstract TrtmRsltMsg getMsg(int index);
	
	/**
	 * 메시지 등록
	 * @param msg 메시지
	 */
	public abstract void addMsg(TrtmRsltMsg msg);
	
	/**
	 * 메시지목록 조회
	 * @return 메시지목록
	 */
	public abstract List<TrtmRsltMsg> getMsgList();
	
	/**
	 * 메시지 목록 초기화
	 */
	public abstract void clearMsgList();
}
