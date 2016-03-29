/**
 * 
 */
package fwk.erfif.sapjco.domain;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import fwk.constants.enums.sapjco.SAP_SLIP_KINDS;
import fwk.constants.enums.sapjco.elem.SAP_GL_DIRC;
import fwk.constants.enums.sapjco.elem.SAP_PAY_COND;
import fwk.constants.enums.sapjco.elem.SAP_PAY_MTHD;
import fwk.constants.enums.sapjco.elem.SAP_PSTNG_KEY;
import fwk.utils.SAPUtils;


/**
 * @author greatjin
 *
 */
public class AssetAmtAPSlip implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CommonSlipHeader header    ;
	CommonSlipItem[] items    = new CommonSlipItem[2] ;
	
	private String custCd; //Customer Code for init
	private String dmsType = SAP_SLIP_KINDS.ASSET_AMT_AP.getDmsType();
	
	public AssetAmtAPSlip(String zserial)
	{
		this.init(zserial);
	}
	
	public AssetAmtAPSlip(String zserial, String custCd, String amt)
	{
		this.init(zserial, custCd, amt);
	}
	
	public AssetAmtAPSlip(String zserial, String slipDt, String custCd, String amt)
	{
		this.init(zserial, slipDt, custCd, amt);
	}
	
	/**
	 * 초기화
	 */
	private void init(String zserial)
	{
		this.init(zserial, null, null);
	}
	
	private void init(String zserial, String custCd, String amt)
	{
		this.init(zserial, null, custCd, amt);
	}
	
	/**
	 * 초기화
	 * @param amt
	 */
	private void init(String zserial, String slipDt, String custCd, String amt)
	{
		header = new CommonSlipHeader();
		header.setSerNo(zserial);
		header.setDmsTyp(this.dmsType);
		header.setTransCd("FBV1");
		
		this.custCd = custCd;
		
		if(StringUtils.isNotEmpty(slipDt)) header.setPstngDt(slipDt);
		if(StringUtils.isNotEmpty(slipDt)) header.setEvdcDt(slipDt);
		
		int idx = 1; 
		for(int i=0; i<items.length; i++)
		{
			items[i] = new CommonSlipItem();
			items[i].setSerNo(zserial);
			items[i].setDmsSeq(idx++ +"");
			items[i].setBizArea("5018");
			items[i].setBp("1100");			
			items[i].setDsignField("22101185-D001-02");
			if(StringUtils.isNotEmpty(amt))   items[i].setAmt(amt);
		}
		
		initDr(items[0]);
		initCr(items[1]);
	}
	
	/**
	 * 차변 초기화
	 * @param one
	 */
	private void initDr(CommonSlipItem one)
	{
		
		SAPUtils.debug("initDr one============================"+one);
		one.setPstngKey(SAP_PSTNG_KEY.DR.getCode());
		if(StringUtils.isNotEmpty(this.custCd))
		{
			one.setDealCoCd(custCd);
		}
		else
		{
			one.setDealCoCd("1111111103");
		}
		one.setPayCond(SAP_PAY_COND.KRW_1ST.getCode());
		one.setPayMthd(SAP_PAY_MTHD.HANA_FB_CASH.getCode());
	}
	
	/**
	 * 대변초기화
	 * @param one
	 */
	private void initCr(CommonSlipItem one)
	{
		one.setPstngKey(SAP_PSTNG_KEY.CR.getCode());
		one.setDealCoCd(SAP_GL_DIRC.LEASE_ASSET_NR.getCode());
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
	
	

}
