package fwk.channel;


public enum XplatformHeaderSpec {

	GLOB_ID								("globId", false),
	IPAD										("ipad", false),
	PRCM_MAC							("macAddr", false),
	TRN_TRNM_NO					("trnTrnmNo", false),
	SSO_SESN_KEY					("ssoSesnKey", false),
	ENV_DVCD							("envDvcd", false),
	MESG_DMND_DTTM			("ncStartDate", false),
	MESG_VRSN_DVCD			("mesgVrsnDvcd", false),
	TRN_CD									("ncTrId", false),
	SCRN_NO								("scrnNo", false),
	MESG_RESP_DTTM			("ncEndDate", false),
	TRN_PTRN_DVCD				("trnPtrnDvcd", false),
	COMP_CD								("compCd", false),
	DEPT_CD								("deptCd", false),
	BR_CD									("brCd", false),
	USER_NO								("userNo", false),
	USER_LOCALE						("userLocale", false),
	CTI_YN									("ctiYn", false),
	
	//WAS Instance정보를 전달하기 위해 spec추가(15.02.23 by PSI)
	WAS_INSTANCE_ID           ("wasInstanceId", false),
	 
	//VOC추가로 인한 spec추가(14.11.10 by PSI)
    REQ_BRND_CD                  ("reqBrndCd"  ,false), //요청브랜드코드
    REQ_CHNL_CD                  ("reqChnlCd"  ,false), //UI요청채널코드
    IS_BCK_OFFICE                 ("isBckOffice"  ,false), //백오피스여부
	;		
	private final String xpfName;
    private final boolean number;
    private final int max;
    private final boolean child;
    private int offset;
    
    XplatformHeaderSpec(String xpfName, boolean number) {
    	this(xpfName, number, false);
    }

    XplatformHeaderSpec(String xpfName, boolean number, boolean child) {
    	this(xpfName, number, child, 0);
    }

    XplatformHeaderSpec(String xpfName, boolean number, int max) {
    	this(xpfName, number, false, max);
    }
    
    XplatformHeaderSpec(String xpfName, boolean number, boolean child, int max) {
        this.xpfName = xpfName;
        this.number = number;
        this.child = child;
        this.max = max;
    }
    
    public String xpfName() { return xpfName; }
    public boolean isNumber() { return number; }
    public int max() { return max; }
    public boolean isChild() { return child; }
    public int offset() { return offset; }
    
    
}
