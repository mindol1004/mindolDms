package fwk.resolver;

import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.LinkServiceType;
import nexcore.framework.core.util.DateUtils;
import nexcore.framework.core.util.InetUtils;
import nexcore.framework.coreext.pojo.resolver.impl.DefaultOnlineContextResolver;
import fwk.common.CommonArea;
import fwk.common.CommonUtils;
import fwk.common.internal.CommonAreaHelper;
import fwk.common.internal.ImplCommonArea;

/**
 * 온라인 컨텍스트 관련 Resolver
 */
public class OnlineContextResolver extends DefaultOnlineContextResolver {

	/**
	 * 부가적인 항목 복제
	 */
	@Override
//    protected void cloneAdditional(IOnlineContext callerOnlineCtx, IOnlineContext calleeOnlineCtx, boolean async, boolean delayAsync, String asyncKey){
	protected void cloneAdditional(IOnlineContext callerOnlineCtx,IOnlineContext calleeOnlineCtx, LinkServiceType linkType) {
    	// 원본
    	CommonArea orgCommonArea = CommonAreaHelper.get(callerOnlineCtx);
    	
    	// 복제
    	ImplCommonArea newCommonArea = CommonAreaHelper.clone(callerOnlineCtx, calleeOnlineCtx);
    	
    	// 현재 시각
		String yyyyMMddHHmmssSSS = DateUtils.dateToString(calleeOnlineCtx.getTransaction().getStartTime(), "yyyyMMddHHmmssSSS");
    	
		// 항목 변경
    	newCommonArea.getFlatHeader().setTrnCd(calleeOnlineCtx.getTransaction().getTxId()); // 거래코드

		// 비동기 지연 연동거래
		if(LinkServiceType.DELAY_ASYNC == linkType){
			newCommonArea.getFlatHeader().setGlobId(calleeOnlineCtx.getTransaction().getRequestId()); //글로벌 ID
			
			// 공통부 - 전송시스템정보내용
			newCommonArea.getFlatHeader().setIpad             (InetUtils.getLocalHostAddr()); // IP주소
			newCommonArea.getFlatHeader().setPrcmMac          ("");                         // PC MAC주소
			newCommonArea.getFlatHeader().setFrstTrnmChnlCd   (CommonUtils.CHNL_CD.FWKC.name());                         // 최초전송채널코드
			newCommonArea.getFlatHeader().setTrnmChnlCd       (CommonUtils.CHNL_CD.FWKC.name());                         // 전송채널코드
			newCommonArea.getFlatHeader().setTrnmNodeNo       (0); // 전송노드번호
			
			// 공통부 - 전문처리내용
			newCommonArea.getFlatHeader().setMesgDmndDttm    (yyyyMMddHHmmssSSS); // 전문요청일시
//			newca.getCommonAreaHeader().setScrnNo           ("");   // 화면ID
//			newCommonArea.getFlatHeader().setMesgCntySrno    (0);    // 전문연속일련번호

			// 공통부 - FLAG정보
			newCommonArea.getFlatHeader().setMesgDvcd       (CommonUtils.MESG_DVCD.Q.name());     // 전문구분코드
			newCommonArea.getFlatHeader().setMesgTycd       ("1"); // 전문유형코드
			newCommonArea.getFlatHeader().setMesgCntySrno   (0);   // 전문연속일련번호
			newCommonArea.getFlatHeader().setTrtmRsltCd     ("");  // 처리결과구분
		}
		
		newCommonArea.setSvcStrnDttm(yyyyMMddHHmmssSSS);                                // 서비스시작일시

		newCommonArea.setItlkDpth(orgCommonArea.getItlkDpth() + 1);     // 연동단계일련번호
		newCommonArea.setMvTrnCd     (orgCommonArea.getTrnCd());        // 기동거래코드
		newCommonArea.setSyncDv      (LinkServiceType.ASYNC == linkType || LinkServiceType.DELAY_ASYNC == linkType ? "A" : "S");                     // SYNC/ASYNC
		
//		newCommonArea.getCommonAreaFwk().setAsyncKey   (asyncKey);                                            // 비동기 연동거래 KEY(거래로그 항목 아님)    	
		
    }
    
    /**
     * 부가적인 항목 역복제
     */
	@Override
	protected void recoveryAdditional(IOnlineContext callerOnlineCtx, IOnlineContext calleeOnlineCtx, LinkServiceType linkType) {
    	CommonAreaHelper.recover(calleeOnlineCtx, callerOnlineCtx);
    }

}
