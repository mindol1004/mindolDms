/**
 * 단말기대금-계좌송금
 */
package dms.utils.sapjco.domain.ep;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import dms.constants.enums.sapjco.elem.SAP_BIZ_AREA;
import dms.constants.enums.sapjco.elem.SAP_BP;
import dms.constants.enums.sapjco.elem.SAP_DEAL_CO_CD;
import dms.constants.enums.sapjco.elem.SAP_PSTNG_KEY;
import dms.constants.enums.sapjco.elem.SAP_SLIP_KINDS;
import dms.constants.enums.sapjco.elem.SAP_WBS_ELEM;
import dms.utils.sapjco.domain.CommonSlipHeader;
import fwk.constants.enums.sapjco.elem.SAP_PAY_COND;
import fwk.constants.enums.sapjco.elem.SAP_PAY_MTHD;
import fwk.erfif.sapjco.domain.CommonSlipItem;



/**
 * @author greatjin
 *
 */
public class AccttrfEPSlip implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CommonSlipHeader header    ;
	CommonSlipItem[] items    = new CommonSlipItem[2] ;
	
	private String custCd; //Customer Code for init
	
	private SAP_SLIP_KINDS slipKind       = SAP_SLIP_KINDS.ACCTTRF_EP; 
	private SAP_SLIP_KINDS cancelSlipKind = SAP_SLIP_KINDS.ACCTTRF_CANCEL_EP;
	private String dmsType      ;
	private String functionName ;
	private String slipType     ;
	private boolean cancelSlipFlag;
	
	
	public AccttrfEPSlip(String zserial, String userNo, String slipDt, String custCd, String amt )
	{
		this.init(zserial, userNo, slipDt, custCd, amt, false);
	}
	

	
	public AccttrfEPSlip(String zserial, String userNo, String slipDt, String custCd, String amt, boolean cancelSlipFlag)
	{
		this.init(zserial, userNo, slipDt, custCd, amt,cancelSlipFlag);
	}
	
	/**
	 * 공통초기화
	 * @param zserial
	 * @param userNo
	 */
	void initCommon(String zserial, String userNo)
	{
		dmsType = slipKind.getDmsType();
		functionName = slipKind.getFuncName();
		slipType = slipKind.getSlipType();
		
		header = new CommonSlipHeader();
		header.setSerNo(zserial);
		header.setDmsTyp(this.dmsType);
		header.setSlipTyp(this.slipType);
		header.setTransCd("FBV1");
		header.setUserNo(userNo);
	}
	
	/**
	 * 초기화
	 * @param amt
	 */
	private void init(String zserial, String userNo, String slipDt, String custCd, String amt, boolean cancelSlipFlag)
	{
		this.cancelSlipFlag = cancelSlipFlag;
		
		if(cancelSlipFlag) this.slipKind = this.cancelSlipKind;
		
		this.initCommon(zserial, userNo);
		
		this.custCd = custCd;
		
		if(StringUtils.isNotEmpty(slipDt)) header.setPstngDt(slipDt);
		if(StringUtils.isNotEmpty(slipDt)) header.setEvdcDt(slipDt);
		header.setHdrTxt("계좌송금("+header.getPstngDt()+")"+(cancelSlipFlag?"반제":""));
		
		int idx = 1; 
		for(int i=0; i<items.length; i++)
		{
			items[i] = new CommonSlipItem();
			items[i].setSerNo(zserial);
			items[i].setDmsSeq(idx++ +"");
			items[i].setBizArea(SAP_BIZ_AREA.DEV_HQ.getCode());
			items[i].setAmt(cancelSlipFlag?Math.abs(Long.parseLong(amt))+"":amt);
		}
		
		initItem0(items[0]);
		initItem1(items[1]);
	}
	
	/**
	 * 감가상각액 아이템 초기화
	 * @param one
	 */
	private void initItem0(CommonSlipItem one)
	{
		if(cancelSlipFlag)
		{
			one.setPstngKey(SAP_PSTNG_KEY.M_DR.getCode());
			one.setPayRsv("A");
		}
		else
		{
			one.setPstngKey(SAP_PSTNG_KEY.DR.getCode());
		}
		one.setDealCoCd("1111111102"); //개인에코폰
		one.setPayMthd(SAP_PAY_MTHD.HANA_FB_CASH.getCode()); //지급방법 하나당좌인출증
		one.setPayCond(SAP_PAY_COND.KRW_1ST.getCode());
		one.setPayAlt(this.custCd);//지급대체인 행복한 에코폰
		one.setDsignField(header.getPstngDt());
		
	}
	
	/**
	 *유형자산처분손실
	 * @param one
	 */
	private void initItem1(CommonSlipItem one)
	{
		if(cancelSlipFlag)
		{
			one.setPstngKey(SAP_PSTNG_KEY.M_CR.getCode());			
		}
		else
		{
			one.setPstngKey(SAP_PSTNG_KEY.CR.getCode());
		}
		one.setDealCoCd(SAP_DEAL_CO_CD.UNCLT_ETC.getCode());
		one.setBp(SAP_BP.SKCC_HQ.getCode());
		one.setDsignField(SAP_WBS_ELEM.ECO_R_BIZ.getCode());
		
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


	public String getFunctionName() {
		return functionName;
	}
	
	

}