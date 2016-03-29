/**
 * 
 */
package dms.utils.sapjco.domain.nr;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import dms.constants.enums.sapjco.elem.SAP_BIZ_AREA;
import dms.constants.enums.sapjco.elem.SAP_BP;
import dms.constants.enums.sapjco.elem.SAP_DEAL_CO_CD;
import dms.constants.enums.sapjco.elem.SAP_SLIP_KINDS;
import dms.constants.enums.sapjco.elem.SAP_WBS_ELEM;
import dms.utils.sapjco.domain.CommonSlipHeader;
import fwk.constants.enums.sapjco.elem.SAP_PSTNG_KEY;
import fwk.erfif.sapjco.domain.CommonSlipItem;



/**
 * 보증보험금_지급 AR 
 * @author greatjin
 *
 */
public class InsRefundARNRSlip implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CommonSlipHeader header    ;
	CommonSlipItem[] items    = new CommonSlipItem[2] ;
	
	private String custCd; //Customer Code for init
	
	private SAP_SLIP_KINDS slipKind = SAP_SLIP_KINDS.INS_BENEFIT_AR_NR; 
	private String dmsType          = slipKind.getDmsType();
	private String functionName     = slipKind.getFuncName();
	private String slipType         = slipKind.getSlipType();
	
	/**
	 * Constructor
	 * @param zserial
	 * @param lginId
	 * @param slipDt
	 * @param custCd
	 * @param netAmt
	 */
	public InsRefundARNRSlip(SAP_SLIP_KINDS slipKind, String zserial, String lginId, String slipDt, String custCd, String netAmt)
	{
		this.init(slipKind, zserial, lginId,  slipDt, custCd, netAmt);
	}
	
	/**
	 * 초기화
	 * @param amt
	 */
	private void init(SAP_SLIP_KINDS slipKind, String zserial, String lginId, String slipDt, String custCd, String netAmt)
	{
		this.slipKind =slipKind; 
		this.dmsType = slipKind.getDmsType();
		this.functionName = slipKind.getFuncName();
		this.slipType = slipKind.getSlipType();
		
		header = new CommonSlipHeader();
		header.setSerNo(zserial);
		header.setDmsTyp(this.dmsType);
		header.setSlipTyp(this.slipType);
		header.setUserNo(lginId);
		header.setTransCd("FBV1");
		
		this.custCd   = custCd;
		
		if(StringUtils.isNotEmpty(slipDt)) header.setPstngDt(slipDt);
		if(StringUtils.isNotEmpty(slipDt)) header.setEvdcDt(slipDt);
		
		int idx = 1; 
		for(int i=0; i<items.length; i++)
		{
			items[i] = new CommonSlipItem();
			items[i].setSerNo(zserial);
			items[i].setDmsSeq(idx++ +"");
			items[i].setBp(SAP_BP.SKCC_HQ.getCode());
			items[i].setBizArea(SAP_BIZ_AREA.DEV_HQ.getCode());
			items[i].setAmt(netAmt);
			items[i].setWbsElem   (SAP_WBS_ELEM.DEVICE_R_BIZ.getCode());
			items[i].setDsignField(SAP_WBS_ELEM.DEVICE_R_BIZ.getCode());
			
		}
		
		initDr(items[0],header);
		initCr(items[1],header);
	}
	
	/**
	 * 차변 초기화
	 * @param one
	 */
	private void initDr(CommonSlipItem one, CommonSlipHeader header)
	{
		one.setPstngKey(SAP_PSTNG_KEY.CR.getCode());
		one.setDealCoCd(SAP_DEAL_CO_CD.ETC_INCOME.getCode());
	}
	
	/**
	 * 대변초기화
	 * @param one
	 */
	private void initCr(CommonSlipItem one, CommonSlipHeader header)
	{
		one.setPstngKey(SAP_PSTNG_KEY.RB_C.getCode());
		one.setDealCoCd(SAP_DEAL_CO_CD.AP_ETC.getCode());
	}
	
    public CommonSlipItem getDr()
    {
    	return this.items[0];
    }
	
    public CommonSlipItem getCr()
    {
    	return this.items[1];
    }
    
    

	public CommonSlipHeader getHeader() {
		return header;
	}

	public void setHeader(CommonSlipHeader header) {
		this.header = header;
	}

	public CommonSlipItem[] getItems() {
		return items;
	}

	public void setItems(CommonSlipItem[] items) {
		this.items = items;
	}

	public String getCustCd() {
		return custCd;
	}

	public void setCustCd(String custCd) {
		this.custCd = custCd;
	}

	public String getFunctionName() {
		return functionName;
	}
	
	

}
