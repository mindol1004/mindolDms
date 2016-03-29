package dms.utils.sapjco.domain.nr;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import dms.constants.enums.sapjco.elem.SAP_BIZ_AREA;
import dms.constants.enums.sapjco.elem.SAP_BP;
import dms.constants.enums.sapjco.elem.SAP_DEAL_CO_CD;
import dms.constants.enums.sapjco.elem.SAP_PAY_COND;
import dms.constants.enums.sapjco.elem.SAP_PSTNG_KEY;
import dms.constants.enums.sapjco.elem.SAP_SLIP_KINDS;
import dms.constants.enums.sapjco.elem.SAP_WBS_ELEM;
import dms.utils.sapjco.domain.CommonSlipHeader;
import fwk.erfif.sapjco.domain.CommonSlipItem;


/**
 * 미납채권 제각 1
 * @author greatjin
 *
 */
public class BondDisposal1NRSlip implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CommonSlipHeader header    ;
	CommonSlipItem[] items    = new CommonSlipItem[2] ;
	
	private String custCd; //Customer Code for init
	
	private SAP_SLIP_KINDS slipKind       = SAP_SLIP_KINDS.BOND_DISPOSAL1_NR; 
    private SAP_SLIP_KINDS cancelSlipKind = SAP_SLIP_KINDS.BOND_DISPOSAL1_NR;
    
    private String dmsType;
    private String functionName ;
    private String slipType ;
    private boolean cancelSlipFlag;
	

	
	public BondDisposal1NRSlip(String zserial, String userNo, String slipDt, String custCd, String amt, String txt)
	{
		this.init(zserial, userNo, slipDt, custCd, amt, txt, false);
	}
	
    public BondDisposal1NRSlip(String zserial, String userNo, String slipDt, String custCd, String amt, String txt, boolean cancelFlag)
    {
        this.init(zserial, userNo, slipDt, custCd, amt, txt,true);
    }
	
	/**
	 * 공통초기화
	 * @param zserial
	 * @param userNo
	 */
	void initCommon(String zserial, String userNo)
	{
		dmsType      = slipKind.getDmsType();
		functionName = slipKind.getFuncName();
		slipType     = slipKind.getSlipType();
		
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
	private void init(String zserial, String userNo, String slipDt, String custCd, String amt, String txt, boolean cancelSlipFlag)
	{
	    this.cancelSlipFlag = cancelSlipFlag;
	       
	    if(cancelSlipFlag) this.slipKind = this.cancelSlipKind;
	        
		this.initCommon(zserial, userNo);
		
		header.setHdrTxt(txt+ (cancelSlipFlag?"취소":""));
		
		this.custCd = custCd;
		
		if(StringUtils.isNotEmpty(slipDt)) header.setPstngDt(slipDt);
		if(StringUtils.isNotEmpty(slipDt)) header.setEvdcDt(slipDt);
		
		int idx = 1; 
		for(int i=0; i<items.length; i++)
		{
			items[i] = new CommonSlipItem();
			items[i].setSerNo(zserial);
			items[i].setDmsSeq(idx++ +"");
            items[i].setBizArea(SAP_BIZ_AREA.DEV_HQ.getCode());
			items[i].setBp(SAP_BP.SKCC_HQ.getCode());			
			items[i].setDsignField(SAP_WBS_ELEM.DEVICE_R_BIZ.getCode());
			if(StringUtils.isNotEmpty(amt))   items[i].setAmt(amt);
			items[i].setTxt(txt + (cancelSlipFlag?"취소":""));
		}
		
		initItem0(items[0]);
		initItem1(items[1]);

	}
	
	/**
	 * 거래처
	 * @param one
	 */
	private void initItem0(CommonSlipItem one)
	{
       if(cancelSlipFlag)
        {
            one.setPstngKey(SAP_PSTNG_KEY.M_CR.getCode());          
        }
        else
        {
            one.setPstngKey(SAP_PSTNG_KEY.R11.getCode());
        }
       
		one.setDealCoCd(this.custCd);
		one.setPayCond(SAP_PAY_COND.AT_ONCE_CASH_IN.getCode());
		one.setRef1(this.custCd);
	}
	
	/**
	 * 대손충당금 - 외상매출금
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
	       
		one.setDealCoCd(SAP_DEAL_CO_CD.AFBD.getCode());
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
