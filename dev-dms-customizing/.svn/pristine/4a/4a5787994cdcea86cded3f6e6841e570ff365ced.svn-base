package fwk.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.core.util.DateUtils;
import fwk.common.internal.ImplCommonArea;
import fwk.constants.DmsConstants;
import fwk.flat.EaiFlatHeaderSpec;
import fwk.flat.FlatHeaderHelper;
import fwk.utils.HpcUtils;

public class OutboundEaiHeader  {

	private Map<String, String>eaiHeader;
	private String orgGlobId; //현재 거래의 글로벌ID
	private String orgTxId; //현재 거래의 거래코드
	/**
	 * Sync거래
	 */
	public static final int SYNC = 0;
	
	/**
	 * Async거래
	 */
	public static final int ASYNC = 1;
	
	/**
	 *Async거래시 양방향 
	 */
	public static final int BOTH_WAY=0; 
	
	/**
	 *Async거래시 단방향 
	 */
	public static final int ONE_WAY=1;
	
	/**
	 *언어구분코드 EUC-KR
	 */
	public static final int LANG_EUC_KR = 1;
	
	/**
	 *언어구분코드 UTF-8 
	 */
	public static final int LANG_UTF_8 = 2;
	
	/**
	 * 언어구분코드 영어
	 */
	public static final int LANG_EN = 3;
	
	/**
	 *언어구분코드 중국어 
	 */
	public static final int LANG_CN = 4;
	
	/**
	 *언어구분코드 일본어 
	 */
	public static final int LANG_JP = 5;
	
	/**
	 *언어구분코드 EUC-KR에 XML로 전송되는 경우 
	 */
	public static final int LANG_KR_XML=6;
	 
	/**
	 * EAI시뮬레이터 연계
	 */
	public static final int IS_TEST = 1;
	/**
	 * 생성자
	 * @param rcveSvcId : 호출할 시스템의 서비스ID
	 * @param rsltRcevSvcId : Async에 대한 응답시 호출될 업무소스의 TransactionID 9자리문자. ex)XYZT00101 
	 * @param eaiIntfId : EAI 인터페이스 ID
	 * @param onlineCtx : OnlineContext
	 */
	public OutboundEaiHeader(String rcveSvcId, String rsltRcevSvcId, String eaiIntfId, IOnlineContext onlineCtx ) {
		init(onlineCtx, rcveSvcId, rsltRcevSvcId, eaiIntfId);
	}
	
	public Map<String, String>getEaiHeader() {
		if(this.eaiHeader==null) {
			this.eaiHeader = new HashMap<String, String>();
		}
		return this.eaiHeader;
	}

	/**
	 * 전송시스템코드를 설정한다. 만약 'trmsSysCd'가 empty라면 
	 * 기 정의된 전송시스템코드인 'CLHPC'코드를 자동으로 설정한다.
	 * @param trmsSysCd
	 */
	private void setTrmsSysCd(String trmsSysCd) {
		if(StringUtils.isEmpty(trmsSysCd)) {
			getEaiHeader().put(EaiFlatHeaderSpec.TRMS_SYS_CD.name(), DmsConstants.SPC_COMPANY_CD+DmsConstants.HPC_SYSTEM_CD);
		} else {
			getEaiHeader().put(EaiFlatHeaderSpec.TRMS_SYS_CD.name(), trmsSysCd);
		}
	}
	
	/**
	 * Sync/Async거래여부 정의
	 * Sync거래일 경우에는 OutboundEaiHeader.SYNC 파라미터로 하고
	 * Async거래일 경우에는 OutboundEaiHeader.ASYNC 파라미터로 한다.
	 * callEai()를 호출하냐, sendEai()를 호출하냐에 따라 자동셋팅되도록 변경 
	 * @param trSyncDcd
	 */
//	public void setTrSyncDcd(int trSyncDcd) {
//		switch (trSyncDcd) {
//		case 0:
//			getEaiHeader().put(EaiFlatHeaderSpec.TR_SYNC_DCD.name(), HpcConstants.SYNC_CD_STR);
//			break;
//
//		default:
//			getEaiHeader().put(EaiFlatHeaderSpec.TR_SYNC_DCD.name(), HpcConstants.ASYNC_CD_STR);
//			break;
//		}
//	}
	
	/**
	 * 비동기 거래구분코드 설정
	 * 양방향일 경우에는 OutboundEaiHeader.BOTH_WAY를 파라미터로 하고
	 * 단방향일 경우에는 OutboundEaiHeader.ONE_WAY를 파라미터로 한다.
	 * @param asyncTrDcd
	 */
	public void setAsyncTrDcd(int asyncTrDcd) {
		switch (asyncTrDcd) {
		case 0:
			getEaiHeader().put(EaiFlatHeaderSpec.ASYNC_TR_DCD.name(), DmsConstants.BOTH_WAY);
			break;

		default:
			getEaiHeader().put(EaiFlatHeaderSpec.ASYNC_TR_DCD.name(), DmsConstants.ONE_WAY);
			break;
		}
	}
	
	/**
	 *  호출할 시스템의 서비스ID. 
	 * @param rvceSvcId
	 */
	private void setRcveSvcId(String rvceSvcId) {
		getEaiHeader().put(EaiFlatHeaderSpec.RCVE_SVCID.name(), rvceSvcId);
	}
	
	/**
	 * Async로 응답을 받는 경우에는 Target시스템에서 호출할 거래코드를 정의해준다. 
	 * @param rsltRcevSvcId
	 */
	private void setRsltRcevSvcId(String rsltRcevSvcId) {
		getEaiHeader().put(EaiFlatHeaderSpec.RSLT_RCEV_SVCID.name(), rsltRcevSvcId);
	}
	
	/**
	 * EAI 인터페이스 ID를 설정한다. 
	 * @param eaiIntfId
	 */
	private void setEaiIntfId(String eaiIntfId) {
		getEaiHeader().put(EaiFlatHeaderSpec.EAI_INTF_ID.name(), eaiIntfId);
	}
	
	/**
	 * EAI 인터페이스 ID를 return한다. 
	 * @return
	 */
	public String getEaiIntfId() {
		return getEaiHeader().get(EaiFlatHeaderSpec.EAI_INTF_ID.name());
	}
	
	/**
	 * 요청응답구분코드를 return한다.
	 * @return
	 */
	public String getReqRspDcd() {
		return getEaiHeader().get(EaiFlatHeaderSpec.REQ_RSP_DCD.name());
	}
	
	/**
	 * 처리결과구분코드를 return한다 
	 * @return
	 */
	public String getTgrmPrcRsltDcd() {
		return getEaiHeader().get(EaiFlatHeaderSpec.TGRM_PRCRSLT_DCD.name());
	}
	/**
	 * 전문버전번호를 설정한다.
	 * @param trgrmVerNo
	 */
	private void setTgrmVerNo(String trgrmVerNo) {
		getEaiHeader().put(EaiFlatHeaderSpec.TGRM_VER_NO.name(), trgrmVerNo);
	}
	
	/**
	 * 언어구분코드 설정
	 * OutboundEaiHeader.LANG_EUC_KR = 1;
	 * OutboundEaiHeader.LANG_UTF_8 = 2;
	 * OutboundEaiHeader.LANG_EN = 3;
	 * OutboundEaiHeader.LANG_CN = 4;
	 * OutboundEaiHeader.LANG_JP = 5;
	 * OutboundEaiHeader.LANG_KR_XML = 6;
	 * @param langDcd
	 */
	public void setLangDcd(int langDcd) {
		switch (langDcd) {
		case 1:
			getEaiHeader().put(EaiFlatHeaderSpec.LANG_DCD.name(), "01");
			break;
		case 2:
			getEaiHeader().put(EaiFlatHeaderSpec.LANG_DCD.name(), "02");		
			break;
		case 3:
			getEaiHeader().put(EaiFlatHeaderSpec.LANG_DCD.name(), "03");
			break;
		case 4:
			getEaiHeader().put(EaiFlatHeaderSpec.LANG_DCD.name(), "04");
			break;
		case 5:
			getEaiHeader().put(EaiFlatHeaderSpec.LANG_DCD.name(), "05");
			break;
		case 6:
			getEaiHeader().put(EaiFlatHeaderSpec.LANG_DCD.name(), "06");
			break;
		default:
			getEaiHeader().put(EaiFlatHeaderSpec.LANG_DCD.name(), "01");
			break;
		}
	}
	
	/**
	 * 테스트 구분코드
	 * EAI시뮬레이터 연계시 OutboundEaiHeader.IS_TEST를 파라미터로 입력
	 * @param testDcd
	 */
	public void setTestDcd(int testDcd) {
		switch (testDcd) {
		case 1:
			getEaiHeader().put(EaiFlatHeaderSpec.TEST_DCD.name(), "1");
			break;

		default:
			getEaiHeader().put(EaiFlatHeaderSpec.TEST_DCD.name(), "0");
			break;
		}
	}
	
	
	/**
	 * 원거래의 Glob ID
	 * @return
	 */
	public String getOrgGlobId() {
		return orgGlobId;
	}

	/**
	 * 원거래의 Glob ID
	 * @param orgGlobId
	 */
	private void setOrgGlobId(String orgGlobId) {
		this.orgGlobId = orgGlobId;
	}

	/**
	 * 원거래의 거래코그
	 * @return
	 */
	public String getOrgTxId() {
		return orgTxId;
	}

	/**
	 * 원거래의 거래코그
	 * @param orgTxId
	 */
	private void setOrgTxId(String orgTxId) {
		this.orgTxId = orgTxId;
	}

	/**
	 * 초기화
	 */
 	private void init(IOnlineContext onlineCtx, String rcveSvcId, String rsltRcevSvcId, String eaiIntfId) {
		ImplCommonArea ca = (ImplCommonArea)CommonUtils.getCommonArea(onlineCtx);
		this.eaiHeader = FlatHeaderHelper.toEaiHeaderMap(ca, null, false);
		getEaiHeader().put(EaiFlatHeaderSpec.REQ_RSP_DCD.name(), DmsConstants.REQ_CD_STR);
		setRcveSvcId(rcveSvcId);
		setRsltRcevSvcId(rsltRcevSvcId);
		setEaiIntfId(eaiIntfId);
		setTrmsSysCd("");
		setTgrmVerNo(BaseUtils.getRuntimeMode()+"50");
		setOrgGlobId(ca.getGlobId());
		setOrgTxId(ca.getTrnCd());
	}
}
