/**
 * 자산매각
 */
package dms.utils.sapjco.domain;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import dms.constants.enums.sapjco.elem.SAP_BIZ_AREA;
import dms.constants.enums.sapjco.elem.SAP_BP;
import dms.constants.enums.sapjco.elem.SAP_DEAL_CO_CD;
import dms.constants.enums.sapjco.elem.SAP_PSTNG_KEY;
import dms.constants.enums.sapjco.elem.SAP_SLIP_KINDS;
import dms.constants.enums.sapjco.elem.SAP_WBS_ELEM;
import dms.utils.SAPUtils;
import fwk.erfif.sapjco.domain.CommonSlipItem;


/**
 * @author greatjin
 *
 */
public class AssetDisposalSlip implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CommonSlipHeader header    ;
	CommonSlipItem[] items    = new CommonSlipItem[4] ;
	
	private String deptCd; //Customer Code for init
	
	private SAP_SLIP_KINDS slipKinds =SAP_SLIP_KINDS.ASSET_RETIREMENT; 
	private String dmsType      ;
	private String functionName ;
	private String slipType     ;
	

	/**
	 * 
	 * @param zserial
	 * @param userNo
	 * @param slipDt
	 * @param deptCd
	 * @param amt     취득가액
	 * @param prcAmt  감누액
	 * @param ldtaAmt  유형자산처분손실 (잔존가)
	 * @param allWinAmt 충당부채기타 
	 */
	public AssetDisposalSlip(String zserial, String userNo, String slipDt, String deptCd, String amt, String prcAmt, String ldtaAmt, String allWinAmt)
	{
		this.init(zserial, userNo, slipDt, deptCd, amt, prcAmt, ldtaAmt, allWinAmt);
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
	 * 
	 * @param zserial
	 * @param userNo
	 * @param slipDt
	 * @param deptCd
	 * @param amt     취득가액
	 * @param prcAmt  감누액
	 * @param ldtaAmt  유형자산처분손실 (잔존가)
	 * @param allWinAmt 충당부채기타 
	 */
	private void init(String zserial, String userNo, String slipDt, String deptCd, String amt, String prcAmt, String ldtaAmt, String allWinAmt)
	{
		this.initCommon(zserial, userNo);
		
		this.deptCd = deptCd;
		
		if(StringUtils.isNotEmpty(slipDt)) header.setPstngDt(slipDt);
		if(StringUtils.isNotEmpty(slipDt)) header.setEvdcDt(slipDt);
		
		int idx = 1; 
		for(int i=0; i<items.length; i++)
		{
			items[i] = new CommonSlipItem();
			items[i].setSerNo(zserial);
			items[i].setDmsSeq(idx++ +"");
			items[i].setBp(SAP_BP.SKCC_HQ.getCode());			
		}
		
		initItem0(items[0]);
		items[0].setAmt(prcAmt);
		initItem1(items[1]);
		items[1].setAmt(ldtaAmt);
		initItem2(items[2]);
		items[2].setAmt(allWinAmt);
		initItem3(items[3]);
		items[3].setAmt(amt);
	}
	
	/**
	 * 감가상각액 아이템 초기화
	 * @param one
	 */
	private void initItem0(CommonSlipItem one)
	{
		
		SAPUtils.debug("initItem0 one============================"+one);
		one.setPstngKey(SAP_PSTNG_KEY.CR.getCode());
		one.setDealCoCd(SAP_DEAL_CO_CD.ASSET_DEPRECIATION_SUM_AMT_NR.getCode());
	}
	
	/**
	 *유형자산처분손실
	 * @param one
	 */
	private void initItem1(CommonSlipItem one)
	{
		one.setPstngKey(SAP_PSTNG_KEY.CR.getCode());
		one.setDealCoCd(SAP_DEAL_CO_CD.LDTA.getCode());
		one.setBizArea(SAP_BIZ_AREA.DEV_HQ.getCode());
		one.setWbsElem(SAP_WBS_ELEM.DEVICE_R_BIZ.getCode());
	}
	
	/**
	 * 충당부채 기타 
	 * @param one
	 */
	private void initItem2(CommonSlipItem one)
	{
		one.setPstngKey(SAP_PSTNG_KEY.CR.getCode());
		one.setDealCoCd(SAP_DEAL_CO_CD.ALLWN_ETC.getCode());
		one.setBizArea(SAP_BIZ_AREA.DEV_HQ.getCode());
		
	}
	
	/**
	 * 유형자산 취득계정
	 * @param one
	 */
	private void initItem3(CommonSlipItem one)
	{
		one.setPstngKey(SAP_PSTNG_KEY.RB_C.getCode());
		one.setDealCoCd(SAP_DEAL_CO_CD.LEASE_ASSET_NR.getCode());
		one.setWbsElem(SAP_WBS_ELEM.DEVICE_R_BIZ.getCode());
		
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
