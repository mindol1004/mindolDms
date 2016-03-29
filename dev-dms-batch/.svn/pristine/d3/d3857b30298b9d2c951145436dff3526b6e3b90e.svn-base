/**
 * 대맂머 수수료
 */
package dms.utils.sapjco.domain.ep;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import dms.constants.enums.sapjco.elem.SAP_BIZ_AREA;
import dms.constants.enums.sapjco.elem.SAP_BP;
import dms.constants.enums.sapjco.elem.SAP_DEAL_CO_CD;
import dms.constants.enums.sapjco.elem.SAP_PAY_COND;
import dms.constants.enums.sapjco.elem.SAP_PAY_MTHD;
import dms.constants.enums.sapjco.elem.SAP_PSTNG_KEY;
import dms.constants.enums.sapjco.elem.SAP_SLIP_KINDS;
import dms.constants.enums.sapjco.elem.SAP_WBS_ELEM;
import dms.utils.SAPUtils;
import dms.utils.sapjco.domain.CommonSlipHeader;
import fwk.constants.enums.DMS_MGMT_DEPT;
import fwk.constants.enums.sapjco.elem.SAP_TAX_CD;
import fwk.erfif.sapjco.domain.CommonSlipItem;



/**
 * @author greatjin
 *
 */
public class AgencyCommissionEPSlip implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CommonSlipHeader header    ;
	CommonSlipItem[] items    = new CommonSlipItem[2] ;
	
	private String custCd; //Customer Code for init
	
	private SAP_SLIP_KINDS slipKind =SAP_SLIP_KINDS.AGENCY_COMMISSION_EP; 
	private String dmsType      ;
	private String functionName ;
	private String slipType     ;
	

	
	public AgencyCommissionEPSlip(String zserial, String userNo, String slipDt, String custCd, String netAmt, String taxAmt, String amt)
	{
		this.init(zserial, userNo, slipDt, custCd, netAmt, taxAmt, amt);
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
	private void init(String zserial, String userNo, String slipDt, String custCd, String netAmt, String taxAmt, String amt)
	{
		this.initCommon(zserial, userNo);
		
		this.custCd = custCd;
		
		if(StringUtils.isNotEmpty(slipDt)) header.setPstngDt(slipDt);
		if(StringUtils.isNotEmpty(slipDt)) header.setEvdcDt(slipDt);
		header.setHdrTxt("에코폰 "+SAPUtils.getMM_YYYYMMDD(header.getPstngDt())+"월 "+this.custCd+"인센티브");
		
		int idx = 1; 
		for(int i=0; i<items.length; i++)
		{
			items[i] = new CommonSlipItem();
			items[i].setSerNo(zserial);
			items[i].setDmsSeq(idx++ +"");
			
			items[i].setBp(SAP_BP.SKCC_HQ.getCode());               //사업장
			items[i].setDsignField(SAP_WBS_ELEM.ECO_R_BIZ.getCode());
			items[i].setTxt("에코폰 "+SAPUtils.getMM_YYYYMMDD(header.getPstngDt())+" "+this.custCd+"인센티브");
			items[i].setTaxCd(SAP_TAX_CD.INTAX10_TAXBILL.getCode());
		}
		
		initItem0(items[0]);
		items[0].setAmt(amt);
		items[0].setTaxAmt(taxAmt);
		
		initItem1(items[1]);
		items[1].setAmt(netAmt);
//		initItem2(items[2]);
//		items[2].setAmt(taxAmt);
	}
	
	/**
	 * 거래처
	 * @param one
	 */
	private void initItem0(CommonSlipItem one)
	{
		one.setPstngKey(SAP_PSTNG_KEY.DR.getCode());
		one.setDealCoCd(this.custCd); //개인에코폰
		one.setPayMthd(SAP_PAY_MTHD.HANA_FB_CASH.getCode());
        one.setPayCond(SAP_PAY_COND.KRW_1ST.getCode());
		one.setBizArea(SAP_BIZ_AREA.DEV_HQ.getCode());
	}
	
	/**
	 * 지급수수료
	 * @param one
	 */
	private void initItem1(CommonSlipItem one)
	{
		one.setPstngKey(SAP_PSTNG_KEY.CR.getCode());
		one.setDealCoCd(SAP_DEAL_CO_CD.PAY_COMMISSION.getCode());
		one.setWbsElem(SAP_WBS_ELEM.ECO_R_BIZ.getCode());
		one.setCostCntr(DMS_MGMT_DEPT.ECO_BUSINESS.getCode());
		one.setRef2(this.custCd);
	}

//	/**
//	 * 매입부가세
//	 * @param one
//	 */
//	private void initItem2(CommonSlipItem one)
//	{
//		one.setPstngKey(SAP_PSTNG_KEY.CR.getCode());
//		one.setDealCoCd(SAP_DEAL_CO_CD.UNCLT_ETC.getCode());
//		one.setPrfitlsCntr(SAP_BIZ_AREA.DEV_HQ.getCode()+"0");
//	}

	
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