package fwk.resolver;

import java.util.List;

import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.core.util.StringUtils;
import nexcore.framework.coreext.pojo.resolver.impl.DefaultIdentityResolver;
import fwk.common.CommonUtils;
import fwk.constants.DmsConstants;

/**
 * 아이디 관련 Resolver
 */
public class IdentityResolver extends DefaultIdentityResolver {
	
	private static IdentityResolver instance;
	private static String SYSTEM_UNIQUE_NO;

	public static IdentityResolver getInstance() {
    	return instance;
    }
	
	public IdentityResolver(){
		super.setGlobalSequenceMax(99);
		super.setAsyncSequenceMax(99);
		instance = this;
	}
	
	/**
	 * 글로벌 ID 생성
	 */
    public String newGlobalId(IOnlineContext onlineCtx) {
    	return newGlobalId(CommonUtils.CHNL_CD.FWKC.name());
    }

	/**
	 * 글로벌 ID 생성
	 */
    public String newGlobalId(String chalId) {
    	int seq = getGlobalSequence();
        
        // 시스템일시(17)+시스템환경코드(1)+시스템코드(2)+채널코드(4)+회사코드(3)+일련번호(2)
        StringBuilder buff = new StringBuilder(32);
        buff.append(getYyyyMMddHHmmssSSS());
        buff.append(BaseUtils.getRuntimeMode());
        buff.append(DmsConstants.SPC_COMPANY_CD);
        buff.append(chalId);
        buff.append(DmsConstants.HPC_SYSTEM_CD);
        buff.append(seq < 10 ? "0" + seq : seq);
        return buff.toString();
    }

    /**
     * 비동기 실행 키 생성
     * @see nexcore.framework.coreext.pojo.resolver.IIdentityResolver#newAsyncKey(nexcore.framework.core.data.IOnlineContext, java.lang.String)
     */
    public String newAsyncKey(IOnlineContext onlineCtx, String targetTxId) {
//    	CommonArea ca = CommonUtils.getCommonArea(onlineCtx);
        int seq = getAsyncSequence();
        
        // <yyyyMMddHHmmssSSS(17)><일련번호(2)>_<은행코드(3)>_<현재 WAS ID>_<현재 거래아이디>_<호출 거래아이디>_<글로벌아이디>
        StringBuilder buff = new StringBuilder(80);
        buff.append(getYyyyMMddHHmmssSSS());
        buff.append(seq < 10 ? "0" + seq : seq);
        buff.append("_");
//        buff.append(ca.getBankCd());     // 은행코드 (3)
//        buff.append("_");
        buff.append(BaseUtils.getCurrentWasInstanceId());
        buff.append("_");
        buff.append(onlineCtx.getTransaction().getTxId());
        buff.append("_");
        buff.append(targetTxId);
        buff.append("_");
        buff.append(onlineCtx.getTransaction().getRequestId());
        return buff.toString();
    }
    
    /**
     * 비동기 실행키 기준으로 파일 경로를 조회한다.
     * @see nexcore.framework.coreext.pojo.resolver.IIdentityResolver#getFilePathByAsyncKey(java.lang.String)
     */
    public String getFilePathByAsyncKey(String asyncKey) {
    	List<String> tokens = StringUtils.stringToList(asyncKey, '_');
    	if(tokens.size() >= 2){
	    	String yyyyMMddHHmmssSSS = tokens.remove(0);
	    	String bankCd = tokens.remove(0);
	    	
	        StringBuilder buff = new StringBuilder();
	        buff.append(bankCd); //은행코드
	        buff.append("/");
	        buff.append(yyyyMMddHHmmssSSS.substring(0, 4));
	        buff.append("/");
	        buff.append(yyyyMMddHHmmssSSS.substring(4, 6));
	        buff.append("/");
	        buff.append(yyyyMMddHHmmssSSS.substring(6, 8));
	        buff.append("/");
	        buff.append(asyncKey);
	        return buff.toString();
    	}
    	else {
    		return asyncKey;
    	}
    }
    
    /**
     * 시스템 고유 번호
     */
    private static String getSystemUniqueNo() {
    	if(SYSTEM_UNIQUE_NO == null){
    		String nodeNo = BaseUtils.getCurrentWasInstanceId();
            int len = nodeNo.length();
            if(len > 9){
            	SYSTEM_UNIQUE_NO = nodeNo.substring(len-9);
            }
            else {
            	SYSTEM_UNIQUE_NO = StringUtils.lpadByte(nodeNo, '0', 9);
            }
    	}
    	return SYSTEM_UNIQUE_NO;
    }
    
}
