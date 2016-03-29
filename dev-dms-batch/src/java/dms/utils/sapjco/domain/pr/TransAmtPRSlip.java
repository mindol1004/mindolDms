/**
 * 
 */
package dms.utils.sapjco.domain.pr;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import dms.constants.enums.sapjco.elem.SAP_BIZ_AREA;
import dms.constants.enums.sapjco.elem.SAP_BP;
import dms.constants.enums.sapjco.elem.SAP_DEAL_CO_CD;
import dms.constants.enums.sapjco.elem.SAP_PSTNG_KEY;
import dms.constants.enums.sapjco.elem.SAP_SLIP_KINDS;
import dms.constants.enums.sapjco.elem.SAP_WBS_ELEM;
import dms.utils.SAPUtils;
import dms.utils.sapjco.domain.CommonSlipHeader;
import fwk.erfif.sapjco.domain.CommonSlipItem;



/**
 * 운송비 정산 매입 ar 전표
 * @author greatjin
 *
 */
public class TransAmtPRSlip implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CommonSlipHeader header    ;
	CommonSlipItem[] items    = new CommonSlipItem[2] ;
	
	private String custCd; //Customer Code for init
	
	private SAP_SLIP_KINDS slipKinds =SAP_SLIP_KINDS.TRANSAMT_PR; 
	private String dmsType = slipKinds.getDmsType();
	private String functionName = slipKinds.getFuncName();
	private String slipType = slipKinds.getSlipType();
	
	
	public TransAmtPRSlip(String zserial, String userNo, String slipDt, String custCd, String amt, String refTxt)
	{
		this.init(zserial, userNo, slipDt, custCd, amt, refTxt);
	}
	
	
	/**
	 * 초기화
	 * @param amt
	 */
	private void init(String zserial, String userNo , String slipDt, String custCd, String amt, String refTxt)
	{
		header = new CommonSlipHeader();
		header.setSerNo(zserial);
		header.setDmsTyp(this.dmsType);
		header.setSlipTyp(this.slipType);
		header.setRef(refTxt);  //잠조키
		header.setTransCd("FBV1");//트랜잭션코드
		header.setUserNo(userNo);
		
		this.custCd = SAPUtils.nvl(custCd,SAP_DEAL_CO_CD.PERSONAL.getCode());
		
		if(StringUtils.isNotEmpty(slipDt)) header.setPstngDt(slipDt); //전기일
		if(StringUtils.isNotEmpty(slipDt)) header.setEvdcDt(slipDt);  //증빙일
		
		int idx = 1; 
		for(int i=0; i<items.length; i++) //공통
		{
			items[i] = new CommonSlipItem();
			items[i].setSerNo(zserial);
			items[i].setDmsSeq(idx++ +"");
			items[i].setBp(SAP_BP.SKCC_HQ.getCode());               //사업장			
			if(StringUtils.isNotEmpty(amt))   items[i].setAmt(amt); //금액
		}
		
		initDr(items[0]);
		initCr(items[1]);
		items[1].setDsignField(header.getPstngDt());
	}
	
	/**
	 * 차변 초기화
	 * @param one
	 */
	private void initDr(CommonSlipItem one)
	{
		one.setPstngKey(SAP_PSTNG_KEY.RB_D.getCode());
		one.setDealCoCd(custCd);
		one.setBizArea(SAP_BIZ_AREA.DEV_HQ.getCode());
		one.setPayCond("I100");
		one.setDsignField(SAP_WBS_ELEM.ECO_R_BIZ.getCode());
	}
	
	/**
	 * 대변초기화
	 * @param one
	 */
	private void initCr(CommonSlipItem one)
	{
		one.setPstngKey(SAP_PSTNG_KEY.RB_C.getCode());
		one.setDealCoCd(SAP_DEAL_CO_CD.ETC_INCOME.getCode());
		one.setWbsElem(SAP_WBS_ELEM.ECO_R_BIZ.getCode());
		one.setRef1(custCd);
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
