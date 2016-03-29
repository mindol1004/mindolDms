package fwk.channel.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.ITransaction;
import nexcore.framework.core.util.ByteArrayWrap;
import nexcore.framework.online.channel.core.IRequestContext;
import nexcore.framework.online.channel.core.IResponseContext;
import nexcore.framework.online.channel.core.RenderException;
import nexcore.framework.online.channel.web.NewStandardJsonView;
import fwk.channel.XplatformHeaderSpec;
import fwk.channel.web.data.HpcJsonProcessor;
import fwk.common.CommonArea;
import fwk.common.CommonUtils;
import fwk.constants.DmsConstants;

public class HpcJsonView extends NewStandardJsonView {

    public void setJsonProcessor(HpcJsonProcessor jsonProcessor) {
        this.jsonProcessor = jsonProcessor;
    }
    @Override
    public void render(IRequestContext requestCtx, IResponseContext responseCtx) throws RenderException {
        HttpServletRequest  request  = (HttpServletRequest) responseCtx.getReadProtocol();
        HttpServletResponse response = (HttpServletResponse)responseCtx.getWriteProtocol();
        IOnlineContext onlineCtx = (IOnlineContext)responseCtx.getOnlineContext();
        CommonArea ca = CommonUtils.getCommonArea(onlineCtx);
        IDataSet  errResponseData = (IDataSet)onlineCtx.getAttribute(DmsConstants.RETURN_DATASET);//에러지만, 업무데이터를 내보내야하는 경우 설정된 값을 가져옴. 
        try {
            
            JSONObject responseJson  = createResponseJSONObject(errResponseData!=null?errResponseData:responseCtx.getBizData(), onlineCtx);
            responseJson.put(XplatformHeaderSpec.IPAD.xpfName(), ca.getIpad());
            responseJson.put(XplatformHeaderSpec.PRCM_MAC.xpfName(), ca.getPrcmMac());
            responseJson.put(XplatformHeaderSpec.ENV_DVCD.xpfName(), ca.getEnvDvcd());
            responseJson.put(XplatformHeaderSpec.USER_NO.xpfName(), ca.getUserNo());
            String rawString = responseJson.toString();

            // 디버그 로그용 hex 를 찍기 위한 버퍼
            if (transformLogger.isDebugEnabled()) {
                transformLogger.debug("Response Data\n" + rawString);
            }
    
            // 응답 전문에 대한 FRONT RAW 로그를 남김.
            if (frontLog != null) {
                long endTime = System.currentTimeMillis();
                // onlineCtx 에 있는 transaction의 getEndTime() 은 변환 이전의 시각이므로 정확하지 않다, 여기서 변환 후의 시각을 다시 구해서 로그한다.
                ITransaction tran = responseCtx.getOnlineContext().getTransaction();
                frontLog.logResponse("WEB", tran.getTxId(), tran.getRequestId(), endTime, (endTime-tran.getStartTime().getTime()), new ByteArrayWrap(rawString.getBytes(encoding)));
            }
    
            writeResponseData(response, rawString);
        }catch(Exception e) {
            handleException(e);
        }
    }
}
