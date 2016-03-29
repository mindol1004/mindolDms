/**
 * 재고이관(에코폰→임대R)(중고)
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
import fwk.erfif.sapjco.domain.CommonSlipItem;


/**
 * @author greatjin
 *
 */
public class StockEPSlip implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CommonSlipHeader header    ;
	CommonSlipItem[] items    = new CommonSlipItem[2] ;
	
	private String deptCd; //Customer Code for init
	private boolean cancelSlipFlag = false; 
	
	private SAP_SLIP_KINDS slipKinds       = SAP_SLIP_KINDS.STOCK_EP       ; 
	private SAP_SLIP_KINDS cancelSlipKinds = null; 
	private String dmsType      ;
	private String functionName ;
	private String slipType     ;
	

	
	public StockEPSlip(String zserial, String userNo, String slipDt, String deptCd, String amt)
	{
		this.init(zserial, userNo, slipDt, deptCd, amt, false);
	}
	
	public StockEPSlip(String zserial, String userNo, String slipDt, String deptCd, String amt, boolean cancelSlipFlag)
	{
		this.init(zserial, userNo, slipDt, deptCd, amt, cancelSlipFlag);
	}
	
	/**
	 * 공통초기화
	 * @param zserial
	 * @param userNo
	 */
	void initCommon(String zserial, String userNo)
	{
		dmsType = slipKinds.getDmsType();
		functionName = slipKinds.getFuncName();
		slipType = slipKinds.getSlipType();
		
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
	private void init(String zserial, String userNo, String slipDt, String deptCd, String amt, boolean cancelSlipFlag)
	{
		if(cancelSlipFlag) this.slipKinds = this.cancelSlipKinds;
		
		this.initCommon(zserial, userNo);
		
		this.deptCd = deptCd;
		this.cancelSlipFlag = cancelSlipFlag;
		
		if(StringUtils.isNotEmpty(slipDt)) header.setPstngDt(slipDt);
		if(StringUtils.isNotEmpty(slipDt)) header.setEvdcDt(slipDt);
		header.setHdrTxt("재고이관("+header.getPstngDt()+")"+(cancelSlipFlag?"반제":""));
		
		int idx = 1; 
		for(int i=0; i<items.length; i++)
		{
			items[i] = new CommonSlipItem();
			items[i].setSerNo(zserial);
			items[i].setDmsSeq(idx++ +"");
			items[i].setBizArea(SAP_BIZ_AREA.DEV_HQ.getCode());
			items[i].setBp(SAP_BP.SKCC_HQ.getCode());
			items[i].setWbsElem(SAP_WBS_ELEM.ECO_R_BIZ.getCode());
			items[i].setDsignField(SAP_WBS_ELEM.ECO_R_BIZ.getCode());
				items[i].setAmt(amt);
		}
		initItem0(items[0]);
		initItem1(items[1]);
	}
	
	/**
	 * 리스자산임대
	 * @param one
	 */
	private void initItem0(CommonSlipItem one)
	{
		if(cancelSlipFlag) //취소 전표
		{
			one.setPstngKey(SAP_PSTNG_KEY.RB_C.getCode());
		}
		else
		{
			one.setPstngKey(SAP_PSTNG_KEY.CR.getCode());
		}
		one.setDealCoCd(SAP_DEAL_CO_CD.LEASE_ASSET_PR.getCode());
	}
	
	/**
	 * 상품
	 * @param one
	 */
	private void initItem1(CommonSlipItem one)
	{
		if(cancelSlipFlag) //취소 전표
		{
			one.setPstngKey(SAP_PSTNG_KEY.CR.getCode());
		}
		else
		{
			one.setPstngKey(SAP_PSTNG_KEY.RB_C.getCode());
		}
		one.setDealCoCd(SAP_DEAL_CO_CD.GOODS.getCode());
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
