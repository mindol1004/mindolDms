package fwk.channel.handler.internal;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.ITransaction;
import nexcore.framework.core.data.IValueObject;
import nexcore.framework.core.exception.BaseRuntimeException;
import nexcore.framework.core.exception.SystemRuntimeException;
import nexcore.framework.core.prototype.IMessageCoded;
import nexcore.framework.core.transform.FlatTransformer;
import nexcore.framework.core.transform.FlatUtil;
import nexcore.framework.core.transform.IFlatHeaderParser;
import nexcore.framework.core.util.ByteArrayWrap;
import nexcore.framework.online.channel.core.IRequestContext;
import nexcore.framework.online.channel.core.internal.DefaultRequestContext;
import nexcore.framework.online.channel.handler.internal.NewStandardFlatHandler;
import nexcore.framework.online.channel.util.WebUtils;
import fwk.constants.DmsConstants;
import fwk.flat.FlatHeaderParser;

public class HpcFlatHandler extends NewStandardFlatHandler {

	/**
	 * HPC 프로젝트가 EAI에 대한 개발을 함께 진행하는 것이 아니기 때문에 개발된 EAI에 대한 표준전문을 별도로 Handling 해줘야 함. 
	 * 이에 따라 호출 Command ID에 따라 FlatHeader를 구성하는 로직을 Customizing함. 
	 * by PSI. 2014.06.17
	 */
	@Override
	public IRequestContext getRequestContext(Object readProtocol,Object writeProtocol) {

        HttpServletRequest  request     = (HttpServletRequest)  readProtocol;
        HttpServletResponse response    = (HttpServletResponse) writeProtocol;

        String commandId     = WebUtils.getCommandId(request);
        
        ByteArrayWrap       requestRawBA    = null;
        IOnlineContext      onlineCtx       = null;
        IValueObject        requestData     = null;

        DataInputStream     svltIn          = null;
        
        Map                 headerInfo      = null;
        boolean isEai = commandId.indexOf(DmsConstants.EAI_COMMAND) != -1;
        IFlatHeaderParser  parser = ((FlatTransformer)frontDataTransformer).getHeaderParser();
        
        try {
            svltIn       = new DataInputStream(request.getInputStream());
            
            //EAI일경우에는 EAI용 전문파서수행 by PSI. 2014.06.18
            if(isEai) {
            	requestRawBA = ((FlatHeaderParser)parser).readEaiTotalData(svltIn);
            } else {
            	requestRawBA = frontDataTransformer.readTotalData(svltIn);
            }
        	
            
            // 수신된 전문을 hex 로 로그함.
            if (transformLogger.isDebugEnabled()) {
                transformLogger.debug(FlatUtil.printBytesDataToHex(requestRawBA, "Request Raw Data"));
            }
            
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(requestRawBA.getByteArray(), requestRawBA.getOffset(), requestRawBA.getLength()));

            //EAI일경우에는 EAI용 전문파서수행 by PSI. 2014.06.18
            if(isEai) {
            	headerInfo = ((FlatHeaderParser)parser).parseEaiHeader(in);
            } else {
            	 headerInfo   = frontDataTransformer.parseHeader(in);
            }
            // ***** 1. raw 데이타의 헤더를 파싱하여 OnlineContext 생성.
           
            
            onlineCtx = (IOnlineContext)headerInfo.get(IOnlineContext.class);

            // 파싱된 헤더 정보를 로그함.
            if (transformLogger.isDebugEnabled()) {
                transformLogger.debug("\n==================== Request Attributes ====================\n"+onlineCtx.getAttributesAll());
            }

            // 요청 전문에 대한 FRONT RAW 로그를 남김.
            if (frontLog != null) {
                ITransaction tran = onlineCtx.getTransaction();
                frontLog.logRequest(isEai?DmsConstants.EAI_CHN_CD:"WEB", tran.getTxId(), tran.getRequestId(), tran.getStartTime().getTime(), requestRawBA, frontDataTransformer.getTrailerBytes());
            }
            
            // ***** 2 본문 변환하여 DataSet 리턴. 
            requestData = frontDataTransformer.transformRequestToValueObject(onlineCtx, in);

            // 변환된 요청전문 DataSet을 로그함.
            if (logger.isDebugEnabled()) {
                logger.debug("\n==================== Request DataSet===================\n"+requestData);
            }
            
        }catch(Exception e) {
            boolean isErrorResponse = Boolean.parseBoolean(request.getAttribute("isErrorResponse").toString());
            if (isErrorResponse) {
                if (headerInfo == null) {
                    headerInfo = frontDataTransformer.makeBlankHeader();
                    onlineCtx = (IOnlineContext)headerInfo.get(IOnlineContext.class);
                }
                request.setAttribute("nexcore.online.context", onlineCtx);
            }
            if (transformLogger.isErrorEnabled()) {
                transformLogger.error("Request data parse failed." + (requestRawBA == null ? "" : "\n"+FlatUtil.printBytesDataToHex(requestRawBA, "Request raw data")), e);
            }
            if (e instanceof BaseRuntimeException) {
                throw (BaseRuntimeException)e;
            }else if (e instanceof IMessageCoded) {
                IMessageCoded em = (IMessageCoded)e;
                throw new SystemRuntimeException(em.getMessageId(), em.getMessageParams(), e);
            }else {
                throw new SystemRuntimeException("SKFS1048", new String[]{e.toString(), this.getClass().getName()}, e);
            }
        }

        IRequestContext rctx = new DefaultRequestContext(commandId, requestData, onlineCtx, readProtocol, writeProtocol);
        request.setAttribute("nexcore.request.context", rctx);
        return rctx;
    }
}
