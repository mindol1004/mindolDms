package fwk.message;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import fwk.message.internal.HpcMessage;
import nexcore.framework.core.message.IMessage;
import nexcore.framework.core.message.internal.DefaultMessageManager;
import nexcore.framework.core.message.internal.Message;

public class HpcMessageManager extends DefaultMessageManager {

    /**
     * msgKey에 대응되는 MessageBundle을 DB로부터 얻음.
     * 
     * @param msgKey
     * @return Map&lt;Locale, IMessage&gt;
     */
    protected Map<Locale, IMessage> getLocaledMessageFromDb(String msgKey) {
        Map<Locale, IMessage> messageBundle = new HashMap<Locale, IMessage>();
        List tmpBundle = sqlManager.queryForList("hpc.message.getMessageBundle", msgKey);
        for (Iterator it = tmpBundle.iterator(); it.hasNext();) {
            Map each = (Map) it.next();
            IMessage message = fromMapToMessage(each);
            messageBundle.put(message.getLocale(), message);
        }

        return messageBundle;
    }
    
    /**
     * Map을 IMessage로 바꾼다. ibatis에서 직접 Map이 아닌 IMessage 객체를 받는다면 없어져야 할 함수.
     * 
     * @param map
     * @return
     */
    protected IMessage fromMapToMessage(Map map) {
        HpcMessage message = new HpcMessage();
        message.setId((String) map.get("ID"));
        message.setLocaleXd((String) map.get("LOCALE_XD"));
        message.setName((String) map.get("NAME"));
        message.setReason((String) map.get("REASON"));
        message.setRemark((String) map.get("REMARK"));
        message.setMessageTypeXd((String) map.get("TYPE_XD"));
        message.setPosRespCd((String) map.get("POS_RESP_CD"));
        message.setCoRespCd((String) map.get("CO_RESP_CD"));
        message.setCoRespDtlCd((String) map.get("CO_RESP_DTL_CD"));
        return message;
    }
}
