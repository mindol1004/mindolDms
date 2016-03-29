package dms.constants.enums.sapjco.elem;

import dms.utils.sapjco.domain.AssetDisposalSlip;
import dms.utils.sapjco.domain.AssetRetirementSlip;
import dms.utils.sapjco.domain.CancelChargeSlip;
import dms.utils.sapjco.domain.PanaltyFeeSlip;
import dms.utils.sapjco.domain.RentalARSlip;
import dms.utils.sapjco.domain.RentalBillingSlip;
import dms.utils.sapjco.domain.ReturnCommissionSlip;
import dms.utils.sapjco.domain.SalesCommissionSlip;
import dms.utils.sapjco.domain.ep.AccttrfEPSlip;
import dms.utils.sapjco.domain.ep.AgencyCommissionEPSlip;
import dms.utils.sapjco.domain.ep.E4UAPEPSlip;
import dms.utils.sapjco.domain.ep.InsalesEPSlip;
import dms.utils.sapjco.domain.ep.OutsalesEPSlip;
import dms.utils.sapjco.domain.ep.PrediscEPSlip;
import dms.utils.sapjco.domain.ep.PrepaidEPSlip;
import dms.utils.sapjco.domain.ep.StockEPSlip;
import dms.utils.sapjco.domain.ep.StockFixEPSlip;
import dms.utils.sapjco.domain.ep.StockYetEPSlip;
import dms.utils.sapjco.domain.nr.AssetAmtAANRSlip;
import dms.utils.sapjco.domain.nr.AssetDepreciationNRSlip;
import dms.utils.sapjco.domain.nr.AssetDeptTransferNRSlip;
import dms.utils.sapjco.domain.nr.AssetInvTransferNRSlip;
import dms.utils.sapjco.domain.nr.BondDisposal1NRSlip;
import dms.utils.sapjco.domain.nr.BondDisposal2NRSlip;
import dms.utils.sapjco.domain.nr.CommissionNRSlip;
import dms.utils.sapjco.domain.nr.InsRefundARNRSlip;
import dms.utils.sapjco.domain.nr.InsfeeARNRSlip;
import dms.utils.sapjco.domain.pr.AgencyAmtAPPRSlip;
import dms.utils.sapjco.domain.pr.AssetDepreciationPRSlip;
import dms.utils.sapjco.domain.pr.AssetDisposalPRSlip;
import dms.utils.sapjco.domain.pr.AssetRetirementPRSlip;
import dms.utils.sapjco.domain.pr.RentalPRSlip;
import fwk.erfif.sapjco.domain.AgencyAmtAPSlip;

/**
 * slip kinds 
 * 전표종류 (TB_SLIP_TYP)
 * @author greatjin
 *
 */
public enum SAP_SLIP_KINDS {
    ASSET_AMT_AA_NR             ("Z_FI_RFC_DMS_MBAP"                 , "NA", "KR", AssetAmtAANRSlip.class       ) //신_단말기자산취득
,   ASSET_AMT_AA_CANCEL_NR      ("Z_FI_RFC_DMS_ASSET_ACQ_CANCEL"     , "N3", "KR", AssetAmtAANRSlip.class       ) //신_단말기자산취득_취소
,   AGENCY_AMT_AP_NR            ("Z_FI_RFC_DMS_MBCREDIT_AP"          , "NB", "KR", AgencyAmtAPSlip.class        ) //신_대리점대금정산AP
,   AGENCY_AMT_AP_CANCEL_NR     ("Z_FI_RFC_DMS_AGENCY_RETURN"        , "N5", "KR", AgencyAmtAPSlip.class        ) //신_대리점대금정산AP_취소
,   INS_COMMISSION_AP_NR        ("Z_FI_RFC_DMS_INS_COMMISSION"       , "NC", "YC", SalesCommissionSlip.class    ) //신_보증보험료AP(발생)
,   INS_COMMISSION_AR_NR        ("Z_FI_RFC_DMS_INSFEE_CLOSE"         , "NY", "SA", InsfeeARNRSlip.class         ) //신_보증보험료AR(해지)
,   INS_BENEFIT_AP_NR           ("Z_FI_RFC_DMS_INS_BENEFIT_AP"       , "NS", "YC", SalesCommissionSlip.class    ) //신_보증보험금AP(발생)
,   INS_BENEFIT_AR_NR           ("Z_FI_RFC_DMS_INS_REFUND"           , "NZ", "DR", InsRefundARNRSlip.class      ) //신_보증보험금AR(지급)
,   FPA_COMMISSION_NR           ("Z_FI_RFC_DMS_FPA_COMMISSION"       , "ND", "YC", CommissionNRSlip.class       ) //신_FPA수수료AP
,   MBINS_COMMISSION_NR         ("Z_FI_RFC_DMS_MBINS_COMMISSION"     , "NU", "YC", CommissionNRSlip.class       ) //신_단말기보험료AP
,   SALES_COMMISSION            ("Z_FI_RFC_DMS_SALES_COMMISSION"     , "NE", "YC", SalesCommissionSlip.class    ) //신_판매수수료AP
,   RETURN_COMMISSION           ("Z_FI_RFC_DMS_MBRETURN_COMMISSION"  , "NG", "YC", ReturnCommissionSlip.class   ) //신_회수수수료
,   COLLECTION_COMIISSION_NR    ("Z_FI_RFC_DMS_COLLECTION_COMMISSION", "NH", "YC", CommissionNRSlip.class       ) //신_추심수수료AP 
,   RENTAL_AR                   ("Z_FI_RFC_DMS_RENTAL_AR"            , "NI", "DR", RentalARSlip.class           ) //신_매출추정AR
,   RENTAL_BILLING              ("Z_FI_RFC_DMS_RENTAL_BILLING"       , "NJ", "DR", RentalBillingSlip.class      ) //신_매출확정(청구)
,   CANCEL_CHARGE               ("Z_FI_RFC_DMS_CANCEL_CHARGE"        , "NL", "DR", CancelChargeSlip.class       ) //신_위약금
,   PANALTY_FEE                 ("Z_FI_RFC_DMS_PANALTY_FEE"          , "NM", "DR", PanaltyFeeSlip.class         ) //신_변상금
,   ASSET_DEPRECIATION_NR       ("Z_FI_RFC_DMS_ASSET_DEPRECIATIO"    , "NO", "AF", AssetDepreciationNRSlip.class) //신_감가상각
,   ASSET_DEPRECIATION_CANCEL_NR("Z_FI_RFC_DMS_ASSET_DEPR_CANCEL"    , "N4", "AF", AssetDepreciationNRSlip.class) //신_감가상각_취소
,   ASSET_RETIREMENT            ("Z_FI_RFC_DMS_ASSET_RETIREMENT"     , "NP", "AR", AssetRetirementSlip.class    ) //신_자산제각 
,   ASSET_DISPOSAL              ("Z_FI_RFC_DMS_ASSET_DISPOSAL"       , "NQ", "AR", AssetDisposalSlip.class      ) //신_자산매각
,   BOND_DISPOSAL1_NR           ("Z_FI_RFC_DMS_BOND_DISPOSAL1"       , "NW", "DR", BondDisposal1NRSlip.class    ) //신_미납채권제각1(대>제)
,   BOND_DISPOSAL2_NR           ("Z_FI_RFC_DMS_BOND_DISPOSAL2"       , "NX", "DR", BondDisposal2NRSlip.class    ) //신_미납채권제각2(대<제)
,   ASSET_INV_TRANSFER_NR       ("Z_FI_RFC_DMS_INV_TRANSFER"         , "N2", "SA", AssetInvTransferNRSlip.class ) //신_자산전환(D팀재고)
,   ASSET_DEPT_TRANSFER_NR      ("Z_FI_RFC_DMS_DEPT_TRANSFER"        , "N1", "SA", AssetDeptTransferNRSlip.class) //신_자산이관(에코팀)

,   RENTAL_PR                   ("Z_FI_RFC_DMS_RENTAL_R"             , "PB", "DR", RentalPRSlip.class           ) //임_단말기AR
,   REPAIR_PR                   ("Z_FI_RFC_DMS_REPAIR_R"             , "PM", "DR", RentalPRSlip.class           ) //임_단말기수리AR
,   REPROD_PR                   ("Z_FI_RFC_DMS_REPROD_R"             , "PR", "DR", RentalPRSlip.class           ) //임_재상품AR
,   TRANSAMT_PR                 ("Z_FI_RFC_DMS_TRANSAMT_R"           , "PC", "DR", RentalPRSlip.class           ) //임_운송비AR
,   AGENCY_AMT_AP_PR            ("Z_FI_RFC_DMS_ASSET_ACQ_R"          , "PA", "KR", AgencyAmtAPPRSlip.class      ) //임_대리점대금정산AP
,   ASSET_DEPRECIATION_PR       ("Z_FI_RFC_DMS_ASSET_DEPREC_R"       , "PO", "AF", AssetDepreciationPRSlip.class) //임_감가상각 
,   ASSET_RETIRE_PR             ("Z_FI_RFC_DMS_ASSET_RETIRE_R"       , "PP", "AR", AssetRetirementPRSlip.class  ) //임_자산제각
,   ASSET_DISPOSAL_PR           ("Z_FI_RFC_DMS_ASSET_TRANSFER_R"     , "PQ", "AR", AssetDisposalPRSlip.class    ) //임_자산매각

,   PREDISC_EP                  ("Z_FI_RFC_DMS_PREDISC_E"            , "EA", "KR", PrediscEPSlip.class          ) //단말기대금(선할인)
,   PREPAID_EP                  ("Z_FI_RFC_DMS_PREPAID_E"            , "EB", "KR", PrepaidEPSlip.class          ) //단말기대금(요금선납)
,   ACCTTRF_EP                  ("Z_FI_RFC_DMS_ACCTTRF_E"            , "EC", "KR", AccttrfEPSlip.class          ) //단말기대금(계좌송금)
,   E4UAP_EP                    ("Z_FI_RFC_DMS_E4UAP_E"              , "EP", "KR", E4UAPEPSlip.class            ) //단말기대금(E4U)
,   CLUBT_EP                    ("Z_FI_RFC_DMS_CLUBT_E"              , "ED", "KR", PrepaidEPSlip.class          ) //단말기대금(클럽T)
,   INSALES_EP                  ("Z_FI_RFC_DMS_INSALES_E"            , "EG", "DR", InsalesEPSlip.class          ) //단말기판매(국내)
,   OUTSALES_EP                 ("Z_FI_RFC_DMS_OUTSALES_E"           , "EH", "DR", OutsalesEPSlip.class         ) //단말기판매(해외)
,   AGENCY_COMMISSION_EP        ("Z_FI_RFC_DMS_AGENCY_E"             , "EF", "KR", AgencyCommissionEPSlip.class ) //대리점수수료
,   STOCK_YET_EP                ("Z_FI_RFC_DMS_GR_E"                 , "EI", "ST", StockYetEPSlip.class         ) //재고정산(미착)
,   STOCK_YET_CANCEL_EP         ("Z_FI_RFC_DMS_INV_CANCEL_E"         , "EK", "KR", StockYetEPSlip.class         ) //재고정산(확정, 재감정완료 후)_취소
,   STOCK_FIX_EP                ("Z_FI_RFC_DMS_IR_E"                 , "EJ", "AB", StockFixEPSlip.class         ) //재고정산(확정)
,   STOCK_FIX_CANCEL_EP         ("Z_FI_RFC_DMS_APPRAISE_E"           , "EL", "ST", StockFixEPSlip.class         ) //재고정산(미착,입고완료 후)_취소
,   ACCTTRF_CANCEL_EP           ("Z_FI_RFC_DMS_IV_CANCEL_E"          , "EM", "KR", AccttrfEPSlip.class          ) //단말기대금(계좌송금,실지급매입발행 후)_취소
,   E4UAP_CANCEL_EP             ("Z_FI_RFC_DMS_E4U_CANCEL_E"         , "EN", "KR", E4UAPEPSlip.class            ) //단말기대금(E4U)_취소
,   STOCK_EP                    ("Z_FI_RFC_DMS_STOCK_E"              , "EO", "SA", StockEPSlip.class            ) //재고이관(에코폰→임대R)
,   INCOST_EP                   ("Z_FI_RFC_DMS_INCOST_E"             , "EQ", "AB", StockEPSlip.class            ) //단말기매출원가(국내)
,   OUTCOST_EP                  ("Z_FI_RFC_DMS_OUTCOST_E"            , "ER", "AB", StockEPSlip.class            ) //단말기매출원가(해외)


	;
	
	private String funcName;
	private String dmsType;
	private String slipType;
	private Class  clz;
	
	SAP_SLIP_KINDS(String funcName, String dmsType, String slipType, Class clz)
	{
		this.funcName  = funcName;
		this.dmsType   = dmsType;
		this.slipType  = slipType;
		this.clz       = clz     ;
	}

	public String getFuncName() {
		return funcName;
	}

	public String getDmsType() {
		return dmsType;
	}


	public String getSlipType() {
		return slipType;
	}

	public Class getClz() {
		return clz;
	}

}
