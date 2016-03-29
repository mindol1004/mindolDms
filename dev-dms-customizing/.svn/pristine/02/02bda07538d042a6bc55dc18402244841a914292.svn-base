package fwk.common.internal;

import java.util.Map;

import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.core.util.ByteArrayWrap;
import fwk.channel.XplatformHeaderSpec;
import fwk.common.CommonArea;
import fwk.common.CommonUtils;
import fwk.common.TrtmRsltMsg;
import fwk.constants.DmsConstants;
import fwk.flat.EaiFlatHeaderSpec;
import fwk.flat.FlatHeader;
import fwk.flat.FlatHeaderHelper;
import fwk.flat.FlatHeaderSpec;

public abstract class CommonAreaHelper {

	/**
	 * Map에 존재하는 CommonArea 조회
	 */
	public static CommonArea get(Map<?,?> map){
		return getImpl(map);
	}

	/**
	 * OnlineContext에 존재하는 CommonArea 조회
	 */
	public static CommonArea get(IOnlineContext onlineCtx){
		return getImpl(onlineCtx);
	}
	
	/**
	 * FlatHeader를 조회
	 */
	public static FlatHeader getFlatHeader(IOnlineContext onlineCtx){
		ImplCommonArea ca = getImpl(onlineCtx);
		if(ca != null){
			return ca.getFlatHeader();
		}
		return null;
	}
	
	/**
	 * FlatHeader를 조회
	 */
	public static FlatHeader existsFlatHeader(IOnlineContext onlineCtx){
		ImplCommonArea ca = getImpl(onlineCtx);
		if(ca != null){
			return ca.existsFlatHeader();
		}
		return null;
	}
	
	/**
	 * CommonArea를 OnlineContext에 설정
	 */
	public static void set(IOnlineContext onlineCtx, CommonArea ca){
		onlineCtx.setAttribute(CommonUtils.COMMONAREA_KEY, ca);
	}
	
	public static ImplCommonArea getImpl(IOnlineContext onlineCtx){
		return (ImplCommonArea)onlineCtx.getAttribute(CommonUtils.COMMONAREA_KEY);
	}

	public static ImplCommonArea getImpl(Map<?,?> map){
		return (ImplCommonArea)map.get(CommonUtils.COMMONAREA_KEY);
	}
	
	/**
	 * OnlineContext의 Attribute에 존재하는 항목으로 CommonArea를 생성하고 OnlineContext에 등록한다.
	 */
	public static CommonArea create(IOnlineContext onlineCtx){
		Map attributes = onlineCtx.getAttributesAll();

		String globId = (String)attributes.get(FlatHeaderSpec.GLOB_ID.name());
		if(globId == null || globId.trim().length() < 1){
			attributes.put(FlatHeaderSpec.GLOB_ID.name(), onlineCtx.getTransaction().getRequestId());
		}
		String trnCd = (String)attributes.get(FlatHeaderSpec.TRN_CD.name());
		if(trnCd == null || trnCd.trim().length() < 1){
			attributes.put(FlatHeaderSpec.TRN_CD.name(), onlineCtx.getTransaction().getTxId());
		}
		
		ImplCommonArea ca = new ImplCommonArea();
		toEntity(attributes, ca);
		onlineCtx.setAttributesAll(attributes);
		set(onlineCtx, ca);
		
		return ca;
	}

	/**
	 * CommonArea를 초기화 한다.
	 * CommonArea가 존재하지 않으면, OnlineContext의 Attribute에 존재하는 항목으로 FlatHeader만 설정하여 생성한다.
	 */
	public static void createInit(IOnlineContext onlineCtx) {
		ImplCommonArea ca = (ImplCommonArea)getImpl(onlineCtx);
		if(ca == null){
			ca = new ImplCommonArea();
			Map attributes = onlineCtx.getAttributesAll();
			
			String globId = (String)attributes.get(FlatHeaderSpec.GLOB_ID.name());
			String trnCd = (String)attributes.get(FlatHeaderSpec.TRN_CD.name());
			String frtChnCd = (String)attributes.get(FlatHeaderSpec.FRST_TRNM_CHNL_CD.name());
			if(globId == null || globId.trim().length() < 1){
				attributes.put(FlatHeaderSpec.GLOB_ID.name(), onlineCtx.getTransaction().getRequestId());
			}
			if(trnCd == null || trnCd.trim().length() < 1){
				attributes.put(FlatHeaderSpec.TRN_CD.name(), onlineCtx.getTransaction().getTxId());
			}
			
			FlatHeaderHelper.toHeader(attributes, ca.getFlatHeader());
			ca.putEaiHeader((Map<String, String>)attributes.remove(DmsConstants.EAI_HEADER_STR));
			ca.setInptMesg((ByteArrayWrap)attributes.remove("__FLAT__"));
			
			if(DmsConstants.XFLAT_CHN_CD.equals(frtChnCd)) {//백오피스나 VOC에서 들어오는 경우
			    ca.setReqBrndCd((String)attributes.get(XplatformHeaderSpec.REQ_BRND_CD.name()));
			    ca.setReqChnlCd((String)attributes.get(XplatformHeaderSpec.REQ_CHNL_CD.name()));
			    ca.setIsBckOffice((String)attributes.get(XplatformHeaderSpec.IS_BCK_OFFICE.name()));
			}
			
			onlineCtx.setAttributesAll(attributes);
			set(onlineCtx, ca);
		}
		init(ca, onlineCtx);
	}
	
	public static void finish(IOnlineContext onlineCtx, Throwable throwable){
	}
	
//	public static CommonAreaImpl clone(IOnlineContext onlineCtx){
//		CommonAreaImpl ca = getImpl(onlineCtx);
//		return ca.clone();
//	}
	
	public static ImplCommonArea clone(IOnlineContext sourceOnlineCtx, IOnlineContext targetOnlineCtx) {
		ImplCommonArea sourceCommonArea = getImpl(sourceOnlineCtx);
		ImplCommonArea targetCommonArea = sourceCommonArea.clone();
		set(targetOnlineCtx, targetCommonArea);
		return targetCommonArea;
	}

	public static void recover(IOnlineContext sourceOnlineCtx, IOnlineContext targetOnlineCtx){
		ImplCommonArea sourceCommonArea = getImpl(sourceOnlineCtx);
		ImplCommonArea targetCommonArea = getImpl(targetOnlineCtx);
		
		if(sourceCommonArea != null && targetCommonArea != null){
			targetCommonArea.recover(sourceCommonArea);
		}
	}
	
	/**
	 * 초기화
	 */
	private static void init(ImplCommonArea entity, IOnlineContext onlineCtx) {
		String yyyyMMddHHmmssSSS = nexcore.framework.core.util.DateUtils.dateToString(onlineCtx.getTransaction().getStartTime(), "yyyyMMddHHmmssSSS");

		entity.setSvcStrnDttm(yyyyMMddHHmmssSSS); //서비스시작일시
		entity.setSyncDv(onlineCtx.getTransaction().isAsyncTransaction() ? "A" : "S"); //SYNC/ASYNC 구분
//		entity.setWasInstId(BaseUtils.getCurrentWasInstanceId()); //WAS INSTANCE ID
		if(entity.getFrstTrnCd() == null){
			entity.setFrstTrnCd(onlineCtx.getTransaction().getTxId()) ; //최초거래코드
		}
	}
	
	public static Map<String, String> toMap(Map<String, String> map, ImplCommonArea entity) {
		if (entity != null && map != null) {
			// I.거래일반
		    put(map, CommonAreaSpec.GLOB_ID              , entity.getGlobId()); //글로벌 ID
		    put(map, CommonAreaSpec.PRGS_SRNO            , entity.getPrgsSrno()); //진행일련번호
		    put(map, CommonAreaSpec.ENV_DVCD             , entity.getEnvDvcd()); //환경구분코드
		    put(map, CommonAreaSpec.TRTM_RSLT_CD         , entity.getTrtmRsltCd()); //처리결과코드
		    put(map, CommonAreaSpec.COMP_CD              , entity.getCompCd()); //은행코드
		    put(map, CommonAreaSpec.TRN_CD               , entity.getTrnCd()); //거래코드
		    put(map, CommonAreaSpec.SCRN_NO              , entity.getScrnNo()); //화면번호
		    put(map, CommonAreaSpec.MESG_VRSN_DVCD       , entity.getMesgVrsnDvcd()); //전문버전구분코드
		    put(map, CommonAreaSpec.MESG_DVCD            , entity.getMesgDvcd()); //전문구분코드
		    put(map, CommonAreaSpec.MESG_TYCD            , entity.getMesgTycd()); //전문유형코드
		    put(map, CommonAreaSpec.MESG_CNTY_SRNO       , entity.getMesgCntySrno()); //전문연속일련번호
		    put(map, CommonAreaSpec.CMPG_RELM_USE_DVCD   , entity.getCmpgRelmUseDvcd()); //캠패인영역사용구분코드
		    put(map, CommonAreaSpec.XTIS_CD              , entity.getXtisCd()); //대외기관코드
		    put(map, CommonAreaSpec.BZWR_SVR_CD          , entity.getBzwrSvrCd()); //업무서버코드
		    put(map, CommonAreaSpec.OTSD_MESG_CD         , entity.getOtsdMesgCd()); //대외전문코드
		    put(map, CommonAreaSpec.OTSD_MESG_TRTM_CD    , entity.getOtsdMesgTrtmCd()); //대외전문처리코드
		    put(map, CommonAreaSpec.OTSD_TRN_UNQ_NO      , entity.getOtsdTrnUnqNo()); //대외거래고유번호
		    put(map, CommonAreaSpec.OTSD_RESP_TRN_CD     , entity.getOtsdRespTrnCd()); //대외응답거래코드
		    put(map, CommonAreaSpec.CHNL_MSG_CD          , entity.getChnlMsgCd()); //채널메시지코드
		    put(map, CommonAreaSpec.MSG_CCNT             , entity.getMsgCcnt()); //메시지건수
		    for(int i=1; i<=entity.getMsgCcnt(); i++){
		    	TrtmRsltMsg o = entity.getMsg(i-1);
				put(map, CommonAreaSpec.MSG_CD               , i, o.getMsgCd());   //메시지코드n
				put(map, CommonAreaSpec.MSG_CNTN             , i, o.getMsgCntn());   //메시지내용n
				put(map, CommonAreaSpec.EROR_OCRN_PRRM_LINE  , i, o.getErorOcrnPrrmLine());   //오류발생 LINEn
				put(map, CommonAreaSpec.EROR_OCRN_PRRM_NM    , i, o.getErorOcrnPrrmNm());   //오류발생 APP명n
			}
		    put(map, CommonAreaSpec.SPR_CHRS_CNTN        , entity.getSprChrsCntn()); //예비문자열내용

		    // II.전송시스템
			put(map, CommonAreaSpec.IPAD                 , entity.getIpad()); // IP주소
			put(map, CommonAreaSpec.PRCM_MAC             , entity.getPrcmMac()); // PC MAC주소
			put(map, CommonAreaSpec.TRN_TRNM_NO          , entity.getTrnTrnmNo()); // 거래단말번호
			put(map, CommonAreaSpec.SSO_SESN_KEY         , entity.getSsoSesnKey()); // SSO 세션 KEY
			put(map, CommonAreaSpec.FRST_TRNM_CHNL_CD    , entity.getFrstTrnmChnlCd()); // 최초전송채널코드
			put(map, CommonAreaSpec.TRNM_CHNL_CD         , entity.getTrnmChnlCd()); // 전송채널코드
			put(map, CommonAreaSpec.TRNM_NODE_NO         , entity.getTrnmNodeNo()); // 전송노드번호
			put(map, CommonAreaSpec.MCI_TRNM_NODE_NO     , entity.getMciTrnmNodeNo()); // MCI 전송노드번호
			
			//#################
			// III.연동
			//#################
			put(map, CommonAreaSpec.ITLK_DPTH            , entity.getItlkDpth()); // 연동깊이
			put(map, CommonAreaSpec.FRST_TRN_CD          , entity.getFrstTrnCd()); // 최초거래코드
			put(map, CommonAreaSpec.MV_TRN_CD            , entity.getMvTrnCd()); // 기동거래코드
			put(map, CommonAreaSpec.SYNC_DV              , entity.getSyncDv()); // Sync/Async구분
//			put(map, CommonAreaSpec.WAS_INST_ID          , entity.getWasInstId()); // WAS인스턴스ID

			//#################
			// IV.일자
			//#################
			put(map, CommonAreaSpec.MESG_DMND_DTTM       , entity.getMesgDmndDttm());   // 전문요청일시          
			put(map, CommonAreaSpec.MESG_RESP_DTTM       , entity.getMesgRespDttm());   // 전문응답일시          
			put(map, CommonAreaSpec.TRN_DT               , entity.getTrnDt());   // 거래일자          
			put(map, CommonAreaSpec.LOG_BZOP_DT          , entity.getLogBzopDt());   // 로그영업일자      
			put(map, CommonAreaSpec.SVC_STRN_DTTM        , entity.getSvcStrnDttm());   // 서비스시작일시    
			put(map, CommonAreaSpec.SVC_END_DTTM         , entity.getSvcEndDttm());   // 서비스종료일시    

			//#################
			// V.사용자
			//#################
			put(map, CommonAreaSpec.BR_CD                , entity.getBrCd());   // 부점코드          
			put(map, CommonAreaSpec.COMP_CD            , entity.getCompCd());   // 회사코드          
			put(map, CommonAreaSpec.USER_NO              , entity.getUserNo());   // 사용자번호          
			put(map, CommonAreaSpec.USER_LOCALE          , entity.getUserLocale());   // 사용자로케일     
			put(map, CommonAreaSpec.DEPT_CD          , entity.getDeptCd());   // 부서코드
			put(map, CommonAreaSpec.DEPT_DVCD          , entity.getDeptDvcd());   // 부서구분코드
			put(map, CommonAreaSpec.USER_CL_CD          , entity.getUserClCd());   // 사용자구분코드
			put(map, CommonAreaSpec.CTI_YN          , entity.getCtiYn());   // CTI여부
			
			/**
			 * 2014-09-11 공통팀 요청에 따라 추가생성함. By PSI
			 */
			put(map, CommonAreaSpec.JOBR_CD          , entity.getJobrCd());   // 직급코드
			put(map, CommonAreaSpec.JTIL_CD          , entity.getJtilCd());   // 직책코드
			put(map, CommonAreaSpec.CO_CD          , entity.getCoCd());   // 회사코드
			put(map, CommonAreaSpec.DEALCO_CD          , entity.getDealCd());   // 대리점코드
			put(map, CommonAreaSpec.EMP_NO          , entity.getEmpNo());   // 사원번호
			
			
			/**
             * 2014-11-11 공통팀 요청에 따라 추가생성함. By PSI
             */
			put(map, CommonAreaSpec.REQ_BRND_CD          , entity.getReqBrndCd());   // 요청브랜드코드
			put(map, CommonAreaSpec.REQ_CHNL_CD          , entity.getReqChnlCd());   // UI요청채널코드
			put(map, CommonAreaSpec.IS_BCK_OFFICE          , entity.getIsBckOffice());   // 백오피스여부
			
			
			//#################
			// VI.시재
			//#################
			put(map, CommonAreaSpec.CSHN_OCRN_YN         , entity.getCshnOcrnYn());   // 시재발생여부          
			put(map, CommonAreaSpec.CASH_AMT             , entity.getCashAmt()+"");   // 현금금액          
			put(map, CommonAreaSpec.POINT_AMT            , entity.getPointAmt()+"");   // 포인트금액          

			
			put(map, CommonAreaSpec.EAI_GLOB_ID            , entity.getEaiHeaderValue(EaiFlatHeaderSpec.TGRM_DDTM.name()) +
																									 entity.getEaiHeaderValue(EaiFlatHeaderSpec.TGRM_CRT_SYSNM.name()) +
																									 entity.getEaiHeaderValue(EaiFlatHeaderSpec.TGRM_CRT_NO.name()));
			put(map, CommonAreaSpec.EAI_INTF_ID,  entity.getEaiHeaderValue(EaiFlatHeaderSpec.EAI_INTF_ID.name()));
			put(map, CommonAreaSpec.EAI_RCEV_SVCID, entity.getEaiHeaderValue(EaiFlatHeaderSpec.RSLT_RCEV_SVCID.name()));

			//#################
			// IX.업무개별
			//#################
//			put(map, CommonAreaSpec.BZWR_INCS_DATA       , entity.getBzwrIncsData());   // 업무개별데이터          
		}
		return map;
	}
	
	public static ImplCommonArea toEntity(Map<String, String> map, ImplCommonArea entity) {
		if (entity != null && map != null) {
	        // I.거래일반
	        entity.setGlobId  ( removeString (map, CommonAreaSpec.GLOB_ID             )); //글로벌 ID
	        entity.setPrgsSrno( removeInt    (map, CommonAreaSpec.PRGS_SRNO           )); //진행일련번호
	        entity.setEnvDvcd( removeString (map, CommonAreaSpec.ENV_DVCD            )); //환경구분코드
	        entity.setTrtmRsltCd( removeString (map, CommonAreaSpec.TRTM_RSLT_CD        )); //처리결과코드
	        entity.setTrnCd( removeString (map, CommonAreaSpec.TRN_CD              )); //거래코드
	        entity.setScrnNo( removeString (map, CommonAreaSpec.SCRN_NO             )); //화면번호
	        entity.setMesgVrsnDvcd( removeString (map, CommonAreaSpec.MESG_VRSN_DVCD      )); //전문버전구분코드
	        entity.setMesgDvcd( removeString (map, CommonAreaSpec.MESG_DVCD           )); //전문구분코드
	        entity.setMesgTycd( removeString (map, CommonAreaSpec.MESG_TYCD      )); //전문유형코드
	        entity.setMesgCntySrno( removeInt (map, CommonAreaSpec.MESG_CNTY_SRNO      )); //전문연속일련번호
	        entity.setCmpgRelmUseDvcd( removeString (map, CommonAreaSpec.CMPG_RELM_USE_DVCD  )); //캠패인영역사용구분코드
	        entity.setXtisCd( removeString (map, CommonAreaSpec.XTIS_CD             )); //대외기관코드
	        entity.setBzwrSvrCd( removeString (map, CommonAreaSpec.BZWR_SVR_CD         )); //업무서버코드
	        entity.setOtsdMesgCd( removeString (map, CommonAreaSpec.OTSD_MESG_CD        )); //대외전문코드
	        entity.setOtsdMesgTrtmCd( removeString (map, CommonAreaSpec.OTSD_MESG_TRTM_CD   )); //대외전문처리코드
	        entity.setOtsdTrnUnqNo( removeString (map, CommonAreaSpec.OTSD_TRN_UNQ_NO     )); //대외거래고유번호
	        entity.setOtsdRespTrnCd( removeString (map, CommonAreaSpec.OTSD_RESP_TRN_CD    )); //대외응답거래코드
	        entity.setChnlMsgCd( removeString (map, CommonAreaSpec.CHNL_MSG_CD         )); //채널메시지코드
			int msgCcnt = removeInt(map, CommonAreaSpec.MSG_CCNT); // 메시지건수
			for (int i = 1; i <= msgCcnt; i++) {
				entity.addMsg(new TrtmRsltMsg(
						removeString(map, CommonAreaSpec.MSG_CD, i),           // 메시지코드
						removeString(map, CommonAreaSpec.MSG_CNTN, i),         // 메시지내용
						removeInt(map, CommonAreaSpec.EROR_OCRN_PRRM_LINE, i), // 오류발생프로그램라인
						removeString(map, CommonAreaSpec.EROR_OCRN_PRRM_NM, i) // 오류발생프로그램명
						));
			}
	        entity.setSprChrsCntn( removeString (map, CommonAreaSpec.SPR_CHRS_CNTN       )); //예비문자열내용

	        // II.전송시스템
	        entity.setIpad( removeString (map, CommonAreaSpec.IPAD                )); //IP주소
	        entity.setPrcmMac( removeString (map, CommonAreaSpec.PRCM_MAC            )); //PC MAC주소
	        entity.setTrnTrnmNo( removeString (map, CommonAreaSpec.TRN_TRNM_NO         )); //거래단말번호
	        entity.setSsoSesnKey( removeString (map, CommonAreaSpec.SSO_SESN_KEY        )); //SSO 세션 KEY
	        entity.setFrstTrnmChnlCd( removeString (map, CommonAreaSpec.FRST_TRNM_CHNL_CD   )); //최초전송채널코드
	        entity.setTrnmChnlCd( removeString (map, CommonAreaSpec.TRNM_CHNL_CD        )); //전송채널코드
	        entity.setTrnmNodeNo( removeInt (map, CommonAreaSpec.TRNM_NODE_NO        )); //전송노드번호

	        // III.연동
	        entity.setItlkDpth( removeInt (map, CommonAreaSpec.ITLK_DPTH           )); // 연동깊이      
	        entity.setFrstTrnCd( removeString (map, CommonAreaSpec.FRST_TRN_CD         )); // 최초거래코드  
	        entity.setMvTrnCd( removeString (map, CommonAreaSpec.MV_TRN_CD           )); // 기동거래코드  
	        entity.setSyncDv( removeString (map, CommonAreaSpec.SYNC_DV             )); // Sync/Async구분
//	        entity.setWasInstId( removeString (map, CommonAreaSpec.WAS_INST_ID         )); // WAS인스턴스ID 

	        // IV.일자
	        entity.setMesgDmndDttm( removeString (map, CommonAreaSpec.MESG_DMND_DTTM      )); // 전문요청일시      
	        entity.setMesgRespDttm( removeString (map, CommonAreaSpec.MESG_RESP_DTTM      )); // 전문응답일시      
	        entity.setTrnDt( removeString (map, CommonAreaSpec.TRN_DT              )); // 거래일자          
	        entity.setLogBzopDt( removeString (map, CommonAreaSpec.LOG_BZOP_DT         )); // 로그영업일자      
	        entity.setSvcStrnDttm( removeString (map, CommonAreaSpec.SVC_STRN_DTTM       )); // 서비스시작일시    
	        entity.setSvcEndDttm( removeString (map, CommonAreaSpec.SVC_END_DTTM        )); // 서비스종료일시    

	        // V.사용자
	        entity.setCompCd( removeString (map, CommonAreaSpec.COMP_CD             )); //은행코드
	        entity.setBrCd( removeString (map, CommonAreaSpec.BR_CD               )); //부점코드          
	        entity.setUserNo( removeString (map, CommonAreaSpec.USER_NO             )); //사용자번호        
	        entity.setUserLocale( removeString (map, CommonAreaSpec.USER_LOCALE             )); //사용자로케일        
	        entity.setDeptCd( removeString (map, CommonAreaSpec.DEPT_CD             )); //부서코드      
	        entity.setDeptDvcd( removeString (map, CommonAreaSpec.DEPT_DVCD          )); //부서구분코드        
	        entity.setUserClCd( removeString (map, CommonAreaSpec.USER_CL_CD          )); //사용자구분코드      
	        entity.setCtiYn( removeString (map, CommonAreaSpec.CTI_YN          )); //상위부점코드      
	        
	        // VI.시재
	        entity.setCshnOcrnYn( removeString (map, CommonAreaSpec.CSHN_OCRN_YN        )); //시재발생여부
	        entity.setCashAmt( removeDouble (map, CommonAreaSpec.CASH_AMT            )); //현금금액    
	        entity.setPointAmt( removeDouble (map, CommonAreaSpec.POINT_AMT           )); //타점금액1   

	        // IX.업무개별
//			entity.setBzwrIncsData( removeString (map, CommonAreaSpec.BZWR_INCS_DATA   )); //업무개별데이터  
		}
		return entity;
	}
	
	private static String removeString(Map<String, String> map, CommonAreaSpec spec) {
		return map.remove(spec.name());
	}

	private static String removeString(Map<String, String> map, CommonAreaSpec spec, int index) {
		return map.remove(spec.name() + index);
	}

	private static int removeInt(Map<String, String> map, CommonAreaSpec spec) {
		return parseInt(map.remove(spec.name()));
	}

	private static int removeInt(Map<String, String> map, CommonAreaSpec spec, int index) {
		return parseInt(map.remove(spec.name() + index));
	}

	private static long removeLong(Map<String, String> map, CommonAreaSpec spec) {
		return parseLong(map.remove(spec.name()));
	}

	private static double removeDouble(Map<String, String> map, CommonAreaSpec spec) {
        return parseDouble(map.remove(spec.name()));
    }
	
	private static int parseInt(String value) {
		if (value == null || "".equals(value)) {
			return 0;
		}
		return Integer.parseInt(value.trim());
	}

	private static long parseLong(String value) {
		if (value == null || "".equals(value)) {
			return 0;
		}
		return Long.parseLong(value.trim());
	}

	private static Double parseDouble(String value) {
        if (value == null || "".equals(value)) {
            return 0.0;
        }
        return Double.parseDouble(value.trim());
    }
//	private static double readDouble(String value) {
//		if (value == null || "".equals(value)) {
//			return 0;
//		}
//		return Double.parseDouble(value.trim());
//	}
	
	private static void put(Map<String, String> map, CommonAreaSpec spec, String value){
		map.put(spec.name(), value);
	}

	private static void put(Map<String, String> map, CommonAreaSpec spec, int value){
		map.put(spec.name(), String.valueOf(value));
	}

	private static void put(Map<String, String> map, CommonAreaSpec spec, long value){
		map.put(spec.name(), String.valueOf(value));
	}

	private static void put(Map<String, String> map, CommonAreaSpec spec, int index, int value){
		map.put(spec.name() + index, String.valueOf(value));
	}

	private static void put(Map<String, String> map, CommonAreaSpec spec, int index, String value){
		map.put(spec.name() + index, value);
	}

}
