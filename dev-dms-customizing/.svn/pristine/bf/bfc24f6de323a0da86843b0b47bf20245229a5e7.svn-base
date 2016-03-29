package fwk.channel.web;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.ITransaction;
import nexcore.framework.core.prototype.IMessageCoded;
import nexcore.framework.core.transform.FlatUtil;
import nexcore.framework.core.util.ByteArrayWrap;
import nexcore.framework.online.channel.core.IRequestContext;
import nexcore.framework.online.channel.core.IResponseContext;
import nexcore.framework.online.channel.core.RenderException;
import nexcore.framework.online.channel.util.WebUtils;
import nexcore.framework.online.channel.web.NewStandardFlatView;
import fwk.constants.DmsConstants;
import fwk.transform.HpcFlatTransformer;

public class HpcFlatView extends NewStandardFlatView {

	@Override
	public void render(IRequestContext requestCtx, IResponseContext responseCtx) throws RenderException {

        HttpServletRequest  request  = (HttpServletRequest) responseCtx.getReadProtocol();
        HttpServletResponse response = (HttpServletResponse)responseCtx.getWriteProtocol();
        response.setContentType("application/octet-stream");
        String commandId     = WebUtils.getCommandId(request);
        boolean isEai = commandId.indexOf(DmsConstants.EAI_COMMAND) != -1;
        OutputStream servletOutputStream = null;
        ByteArrayWrap responseRawBA = null;
        IOnlineContext onlineCtx = responseCtx.getOnlineContext();
        IDataSet  errResponseData = (IDataSet)onlineCtx.getAttribute(DmsConstants.RETURN_DATASET);
        try {
        	if(isEai) {
        		responseRawBA = ((HpcFlatTransformer)frontDataTransformer).transformValueObjectToEaiResponse(onlineCtx, errResponseData!=null?errResponseData:responseCtx.getBizData());
        	} else {
        	    /**
        	     * 업무Error가 발생한 상태면서 response로 업무전문을 보내야하기 떄문에 __RETURN_DATASET__이 설정된 경우에는 해당 DataSet을 넘긴다. 
        	     */
        		responseRawBA = frontDataTransformer.transformValueObjectToResponse(onlineCtx, errResponseData!=null?errResponseData:responseCtx.getBizData());
        	}
        	
            
            // 디버그 로그용 hex 를 찍기 위한 버퍼
            if (logger.isDebugEnabled()) {
                logger.debug("\n==================== Response Attributes ====================\n"+responseCtx.getOnlineContext().getAttributesAll());
//                logger.debug(FlatUtil.printBytesDataToHex(responseRawBA, "Response Data"));
            }
            if (transformLogger.isDebugEnabled()) {
                transformLogger.debug(FlatUtil.printBytesDataToHex(responseRawBA, "Response Data"));
            }
            
            // 응답 전문에 대한 FRONT RAW 로그를 남김.
            if (frontLog != null) {
                long endTime = System.currentTimeMillis();
                // onlineCtx 에 있는 transaction의 getEndTime() 은 변환 이전의 시각이므로 정확하지 않다, 여기서 변환 후의 시각을 다시 구해서 로그한다.
                ITransaction tran = responseCtx.getOnlineContext().getTransaction();
                frontLog.logResponse(isEai?DmsConstants.EAI_CHN_CD:"WEB", tran.getTxId(), tran.getRequestId(), endTime, (endTime-tran.getStartTime().getTime()), responseRawBA);
            }

            // HTTP 헤더에 Content-Length 표시. 
            response.setContentLength(responseRawBA.getLength());
            
            servletOutputStream = response.getOutputStream();
            // 본문을 write한다.
            servletOutputStream.write(responseRawBA.getByteArray(), responseRawBA.getOffset(), responseRawBA.getLength());
            servletOutputStream.flush();
        }catch(Exception e) {
            // 전문 변환에서 에러발생하거나, ServletOutputStream 으로 write하다가 에러발생. 
            // 여기서 에러발생하면 FlatErrorView 로 다시 한번 write시도함. (헤더만 write).
            if (logger.isErrorEnabled()) {
                logger.error("Exception on writing response data.", e);
            }
            
            if (e instanceof IMessageCoded) {
                IMessageCoded em = (IMessageCoded)e;
                throw new RenderException(em.getMessageId(), em.getMessageParams(), e);
            }else {
//                throw new RenderException("Exception on writing response data.", e);
	            throw new RenderException("SKFS1010", new String[] {"FLAT", e.getLocalizedMessage()}, e);
	        }
        }
	}
}
