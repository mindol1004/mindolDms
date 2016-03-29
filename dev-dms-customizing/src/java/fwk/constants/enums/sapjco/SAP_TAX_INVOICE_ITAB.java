package fwk.constants.enums.sapjco;

/**
 * sap_slip_header
 * @author greatjin
 *
 */
public enum SAP_TAX_INVOICE_ITAB {
	    SAP_CLNT           ("MANDT   ".trim(), "sapClnt          ".trim()) //SAPClient                      
	  , CO_CD              ("BUKRS   ".trim(), "coCd             ".trim()) //회사코드                       
	  , PROC_ID            ("ZPSID   ".trim(), "procId           ".trim()) //ProcessID(8)                   
	  , EXTRA_NO           ("ZETCN   ".trim(), "extraNo          ".trim()) //Extranumber(3)                 
	  , DIRC_CL            ("ZSIGN   ".trim(), "dircCl           ".trim()) //역방향/정방향구분자            
	  , AR_AP_CL           ("ZARAP   ".trim(), "arApCl           ".trim()) //AR/AP구분                      
	  , FISCL_YY           ("FJAHR   ".trim(), "fisclYy          ".trim()) //FiscalYear                     
	  , SLIP_NO            ("BELNR   ".trim(), "slipNo           ".trim()) //AccountingDocumentNumber       
	  , POSTING_DT         ("BUDAT   ".trim(), "postingDt        ".trim()) //PostingDateintheDocument       
	  , BLNK_TNO           ("ZNCNT   ".trim(), "blnkTno          ".trim()) //공란수                         
	  , TAXBILL_KIND       ("ZDTIG   ".trim(), "taxbillKind      ".trim()) //세금계산서종류                 
	  , TAXBILL_BOOKTYP_NO ("ZVLID   ".trim(), "taxbillBooktypNo ".trim()) //세금계산서권번호               
	  , TAXBILL_HO_NO      ("ZBKID   ".trim(), "taxbillHoNo      ".trim()) //세금계산서호번호               
	  , TAXBILL_SER_NO     ("ZSQNO   ".trim(), "taxbillSerNo     ".trim()) //세금계산서일련번호             
	  , REF_ORD_NO         ("ZRFNO   ".trim(), "refOrdNo         ".trim()) //참조주문번호                   
	  , REF_ORD_DAY        ("ZRFDT   ".trim(), "refOrdDay        ".trim()) //참조주문일                     
	  , REF_INVC_NO        ("ZRINO   ".trim(), "refInvcNo        ".trim()) //참조송장번호                   
	  , REF_INVC_DAY       ("ZRIDT   ".trim(), "refInvcDay       ".trim()) //참조송장일                     
	  , ETC_REF_NO         ("ZETNO   ".trim(), "etcRefNo         ".trim()) //기타참조번호                   
	  , SPLYR_BIZ_REG_NO   ("ZSPNO   ".trim(), "splyrBizRegNo    ".trim()) //공급자사업자등록번호           
	  , SPLYR_REPVE_NAME   ("ZSPNM   ".trim(), "splyrRepveName   ".trim()) //공급자대표자성명               
	  , DEALCO_ACNT_NO     ("LIFNR   ".trim(), "dealcoAcntNo     ".trim()) //AccountNumberofVendororCreditor
	  , SPLYR_TRD          ("ZSPCN   ".trim(), "splyrTrd         ".trim()) //공급자상호                     
	  , SPLYR_BIZKND       ("ZSPBT   ".trim(), "splyrBizknd      ".trim()) //공급자업종                     
	  , SPLYR_BIZTYP       ("ZSPIT   ".trim(), "splyrBiztyp      ".trim()) //공급자업태                     
	  , SPLYR_ADDR         ("ZSPAR   ".trim(), "splyrAddr        ".trim()) //공급자주소                     
	  , SPLYR_CHRG_DEPT_NM ("ZSPRP   ".trim(), "splyrChrgDeptNm  ".trim()) //공급자담당부서명               
	  , SPLYR_CHRGR_NM     ("ZSPRN   ".trim(), "splyrChrgrNm     ".trim()) //공급자담당자명                 
	  , SPLYR_CHRGR_TEL_NO ("ZSPRT   ".trim(), "splyrChrgrTelNo  ".trim()) //공급자담당자전화번호           
	  , SPLYR_CHRGR_EMAIL  ("ZSPRE   ".trim(), "splyrChrgEmail   ".trim()) //공급자담당자이메일             
	  , SPLYED_BIZ_REG_NO  ("ZBYNO   ".trim(), "splyedBizRegNo   ".trim()) //공급받는자사업자등록번호       
	  , SPLYED_REPVE       ("ZBYNM   ".trim(), "splyredRepve     ".trim()) //공급받는자대표자성명           
	  , CUST_NO            ("KUNNR   ".trim(), "custNo           ".trim()) //CustomerNumber1                
	  , SPLYED_TRD         ("ZBYCN   ".trim(), "splyedTrd        ".trim()) //공급받는자상호                 
	  , SPLYED_BIZKND      ("ZBYBT   ".trim(), "splyredBizknd    ".trim()) //공급받는자업종                 
	  , SPLYED_BIZTYP      ("ZBYIT   ".trim(), "splyredBiztyp    ".trim()) //공급받는자업태                 
	  , SPLYED_ADDR        ("ZBYAR   ".trim(), "splyredAddr      ".trim()) //공급받는자주소                 
	  , SPLYED_CHRG_DEPT_NM("ZBYRP   ".trim(), "splyredChrgDeptNm".trim()) //공급받는자담당부서명           
	  , SPLYED_CHRGR_NM    ("ZBYRN   ".trim(), "splyredChrgrNm   ".trim()) //공급받는자담당자명             
	  , SPLYED_CHRGR_TEL_NO("ZBYRT   ".trim(), "splyredChrgrTelNo".trim()) //공급받는자담당자전화번호       
	  , SPLYED_CHRGR_EMAIL ("ZBYRE   ".trim(), "splyredChrgrEmail".trim()) //공급받는자담당자이메일         
	  , ELEC_TAXBILL_CL    ("ZSTAT   ".trim(), "elecTaxbillCl    ".trim()) //전자세금계산서심사구분         
	  , TAXBILL_ISUE_DT    ("ZMKDT   ".trim(), "tabillIsueDt     ".trim()) //세금계산서발행일자             
	  , RCP_INV_CL         ("ZRPTP   ".trim(), "rcpInvGubun      ".trim()) //영수/청구구분                  
	  , HDR_TXT            ("ZREMK   ".trim(), "hrdTxt           ".trim()) //헤더텍스트                     
	  , LOCAL_CUR          ("HWAER   ".trim(), "localCur         ".trim()) //LocalCurrency                  
	  , SPLY_AMT           ("HWBAS   ".trim(), "splyAmt          ".trim()) //공급가액                       
	  , TAX_AMT            ("HWSTE   ".trim(), "taxAmt           ".trim()) //세액                           
	  , GROSS_AMT          ("ZTTAT   ".trim(), "grossAmt         ".trim()) //총금액                         
	  , WAY_BAS_UNIT       ("MEINS   ".trim(), "wasBasUnit       ".trim()) //BaseUnitofMeasure              
	  , GROSS_QTY          ("ZTTQT   ".trim(), "grossQty         ".trim()) //총수량                         
	  , REC_CRE_DAY        ("ZCRDT   ".trim(), "recCreDay        ".trim()) //Record생성일                   
	  , REC_CRE_TMS        ("ZCRTM   ".trim(), "recCreTms        ".trim()) //Record생성시간                 
	  , REC_CRTR           ("ZCRUS   ".trim(), "recCrtr          ".trim()) //Record생성인                   
	  , REC_RECNT_CHG_DT   ("ZLCDT   ".trim(), "recRecntChgDt    ".trim()) //Record마지막변경일             
	  , REC_RECNT_CHG_TMS  ("ZLCTM   ".trim(), "recRecntChgTms   ".trim()) //Record마지막변경시간           
	  , REC_RECNT_CHG_USER ("ZLCUS   ".trim(), "recRecntChgUser  ".trim()) //Record마지막변경인             
	  , CNCL_REJT_RSN      ("ZRESO   ".trim(), "cnclRejtRsn      ".trim()) //취소/거부사유                  
	  , TAXBILL_BRWS_TTR   ("ZPRCN   ".trim(), "taxbillBrwsTtr   ".trim()) //세금계산서조회횟수             
	  , RMK                ("BIGO    ".trim(), "rmk              ".trim()) //비고                           
	  , TAXBILL_ST         ("STATUS  ".trim(), "taxbillSt        ".trim()) //CharacterFieldwithLength5      
	  , TAXBILL_RSLT       ("RESULT  ".trim(), "taxbillRslt      ".trim()) //CharacterFieldwithLength5      
	  , TAXBILL_ID         ("SBID    ".trim(), "taxbillId        ".trim()) //ID                             
	  , IP_ADDR            ("ZSAPBCIP".trim(), "ipAddr           ".trim()) //IP                             
	  , RVR_EMPNO          ("EMPNO1  ".trim(), "rvrEmpno         ".trim()) //검토자사번                     
	  , APRV_EMPNO         ("EMPNO2  ".trim(), "aprvEmpno        ".trim()) //승인자사번                     
	  , RSV_ST             ("ZDELAY  ".trim(), "rsvSt            ".trim()) //보류상태FLAG('X'=보류)         
	  , RSV_RSN            ("ZDMEMO  ".trim(), "rsvRsn           ".trim()) //보류사유                       
	  , DTI_SIGN_DT        ("ZSGDT   ".trim(), "dtiSignDt        ".trim()) //DTI서명일자               	    
	;
    
	private String sapCol  ;
	private String var     ;
	
	SAP_TAX_INVOICE_ITAB(String sapCol, String var)
	{
		this.sapCol = sapCol;
		this.var    = var;
		
	}
	
	

	public String getSapCol() {
		return sapCol;
	}



	public String getVar() {
		return var;
	}



	public String toString()
	{
		return sapCol + var;
	}
	
}
