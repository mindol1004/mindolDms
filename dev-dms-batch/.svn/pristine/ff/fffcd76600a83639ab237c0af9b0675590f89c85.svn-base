/**
 * 재고정산_확정(중고)
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
import fwk.erfif.sapjco.domain.CommonSlipItem;


/**
 * @author greatjin
 *
 */
public class StockFixEPSlip implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CommonSlipHeader header    ;
	CommonSlipItem[] items    = new CommonSlipItem[3] ;
	
	private String deptCd; //Customer Code for init
	private boolean cancelSlipFlag = false;
	private boolean sknClFlag      = false; //비현장 
	
	private SAP_SLIP_KINDS slipKinds       = SAP_SLIP_KINDS.STOCK_FIX_EP       ; 
	private SAP_SLIP_KINDS cancelSlipKinds = SAP_SLIP_KINDS.STOCK_FIX_CANCEL_EP; 
	private String dmsType      ;
	private String functionName ;
	private String slipType     ;
	
	

	
	public StockFixEPSlip(String zserial, String userNo, String slipDt, String deptCd, String amt, String lossAmt, String txt, boolean sknClFlag)
	{
		this.init(zserial, userNo, slipDt, deptCd, amt, lossAmt, txt, sknClFlag, false);
	}
	
	public StockFixEPSlip(String zserial, String userNo, String slipDt, String deptCd, String amt, String lossAmt,  String txt, boolean sknClFlag, boolean cancelSlipFlag)
	{
		this.init(zserial, userNo, slipDt, deptCd, amt, lossAmt, txt, sknClFlag, cancelSlipFlag);
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
	private void init(String zserial, String userNo, String slipDt, String deptCd, String amt, String lossAmt, String txt, boolean sknClFlag, boolean cancelSlipFlag)
	{
		if(cancelSlipFlag) this.slipKinds = this.cancelSlipKinds;
		this.sknClFlag  = sknClFlag;
		
		this.initCommon(zserial, userNo);
		
		this.deptCd = deptCd;
		this.cancelSlipFlag = cancelSlipFlag;
		
		if(StringUtils.isNotEmpty(slipDt)) header.setPstngDt(slipDt);
		if(StringUtils.isNotEmpty(slipDt)) header.setEvdcDt(slipDt);
		
		String headerText = txt+"확정재고("+SAPUtils.getYYMMDD_YYYYMMDD(header.getPstngDt())+")"+(cancelSlipFlag?"반제":"");
		String itmText    = headerText;
		header.setHdrTxt(headerText);
		
		int idx = 1; 
		for(int i=0; i<items.length; i++)
		{
			items[i] = new CommonSlipItem();
			items[i].setSerNo(zserial);
			items[i].setDmsSeq(idx++ +"");
			items[i].setBizArea(SAP_BIZ_AREA.DEV_HQ.getCode());
			items[i].setTxt(itmText);
		}
		
		long totalAmt = Long.parseLong(SAPUtils.nvl(amt, "0")) + Long.parseLong(SAPUtils.nvl(lossAmt, "0"));
		
		
		initItem0(items[0]); //상품 선 a 반 a
		
		if(this.sknClFlag)//즉시 상품
		{
		    initItem1SknCl(items[1]); //개인
		    initItem2(items[2]); //충당부채손실 l 손실 l
		}
		else
		{
	        initItem1(items[1]); //미착품 선 t  반 t
	        initItem2(items[2]); //충당부채손실 l 손실 l
		}
		
		items[0].setAmt(cancelSlipFlag?Math.abs(Long.parseLong(amt))+"":amt);
		items[1].setAmt((cancelSlipFlag?Math.abs(Long.parseLong(amt)):totalAmt)+"");
		items[2].setAmt(cancelSlipFlag?"0":lossAmt);
	}
	
	/**
	 * 감가상각액 아이템 초기화
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
		one.setDealCoCd(SAP_DEAL_CO_CD.GOODS.getCode());
		one.setDsignField(SAP_WBS_ELEM.ECO_R_BIZ.getCode());
		one.setBp(SAP_BP.SKCC_HQ.getCode()); 
	}
	
	/**
	 *유형자산처분손실
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
	    one.setDealCoCd(SAP_DEAL_CO_CD.NOT_ARRIVAL_GOODS.getCode());
		one.setDsignField(SAP_WBS_ELEM.ECO_R_BIZ.getCode());
		one.setBp(SAP_BP.SKCC_HQ.getCode()); 
	}
	
	
	   /**
     *유형자산처분손실
     * @param one
     */
    private void initItem1SknCl(CommonSlipItem one)
    {
        if(cancelSlipFlag) //취소 전표
        {
            one.setPstngKey(SAP_PSTNG_KEY.M_DR.getCode());
        }
        else
        {
            one.setPstngKey(SAP_PSTNG_KEY.DR.getCode());
        }
        
        one.setDealCoCd("1111111102"); //개인에코폰
        one.setPayCond(SAP_PAY_COND.KRW_WD_CASH.getCode());                 
        one.setPayRsv("W"); //지급보류
        one.setDsignField(SAP_WBS_ELEM.ECO_R_BIZ   .getCode());
        one.setPayMthd   (SAP_PAY_MTHD.HANA_FB_CASH.getCode());
    }
	
	/**
	 * 유형자산 취득계정
	 * @param one
	 */
	private void initItem2(CommonSlipItem one)
	{
		if(cancelSlipFlag) //취소 전표
		{
			one.setPstngKey(SAP_PSTNG_KEY.M_CR.getCode());
		}
		else
		{
			one.setPstngKey(SAP_PSTNG_KEY.CR.getCode());
		}
		one.setDealCoCd(SAP_DEAL_CO_CD.LCEL.getCode());
		one.setDsignField(header.getPstngDt());
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
