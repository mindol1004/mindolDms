package fwk.channel.handler.internal;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import nexcore.framework.core.data.IChannel;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRuntimeContext;
import nexcore.framework.core.data.ITerminal;
import nexcore.framework.core.data.ITransaction;
import nexcore.framework.core.data.OnlineContext;
import nexcore.framework.core.data.user.IUserInfo;
import nexcore.framework.online.channel.handler.internal.NewStandardJsonHandler;
import fwk.channel.JsonHeaderHelper;
import fwk.channel.web.data.HpcJsonProcessor;

public class HpcJsonHandler extends NewStandardJsonHandler {
    
//    public HpcJsonProcessor jsonProcessor;
    
    public void setJsonProcessor(HpcJsonProcessor jsonProcessor) {
        this.jsonProcessor = jsonProcessor;
    }

    @Override
    protected IOnlineContext createOnlineContext(JSONObject rootJson, HttpServletRequest request) {
        IUserInfo userInfo = createUserInfo(rootJson, request);
        ITransaction transaction = createTransaction(rootJson, request);
        IChannel channel = createChannel(rootJson, request);
        ITerminal terminal = createTerminal(rootJson, request);
        IRuntimeContext runtimeCtx = createRuntimeContext(rootJson, request, transaction);
        
        OnlineContext onlineCtx = new OnlineContext(transaction, userInfo, runtimeCtx, channel, terminal);
        Map<String, String> attributes = JsonHeaderHelper.toJsonHeaderMap(onlineCtx, rootJson);
        
        if(attributes != null){
            onlineCtx.setAttributesAll(attributes);
        }
        return onlineCtx;
    }
   
}
