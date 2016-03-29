package fwk.message.internal;

import nexcore.framework.core.message.internal.Message;

public class HpcMessage extends Message {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3038171341801311542L;
    
    protected String posRespCd;
    protected String coRespCd;
    protected String coRespDtlCd;
    
    public String getPosRespCd() {
        return posRespCd;
    }
    public void setPosRespCd(String posRespCd) {
        this.posRespCd = posRespCd;
    }
    public String getCoRespCd() {
        return coRespCd;
    }
    public void setCoRespCd(String coRespCd) {
        this.coRespCd = coRespCd;
    }
    public String getCoRespDtlCd() {
        return coRespDtlCd;
    }
    public void setCoRespDtlCd(String coRespDtlCd) {
        this.coRespDtlCd = coRespDtlCd;
    }
    
    
}
