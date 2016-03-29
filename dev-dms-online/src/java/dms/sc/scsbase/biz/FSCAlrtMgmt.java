package dms.sc.scsbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;
import nexcore.framework.core.util.StringUtils;
import nexcore.framework.core.util.BaseUtils;

import org.apache.commons.logging.Log;

import com.surem.api.mms.SureMMSAPI;
import com.surem.api.sms.SureSMSAPI;
import com.surem.net.SendReport;
import com.surem.net.mms.SureMMSDeliveryReport;
import com.surem.net.sms.SureSMSDeliveryReport;

import fwk.code.internal.HpcCode;
import fwk.constants.DmsConstants;
import fwk.utils.HpcUtils;
import fwk.utils.PagenateUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/업무공통</li>
 * <li>단위업무명: FSCAlrtMgmt</li>
 * <li>설 명 : 알림관리</li>
 * <li>작성일 : 2014-08-21 13:50:46</li>
 * <li>작성자 : 김석영 (kimsukyoung)</li>
 * </ul>
 * 
 * @author 김석영 (kimsukyoung)
 */
public class FSCAlrtMgmt extends fwk.base.FunctionUnit {

    /**
     * 이 클래스는 Singleton 객체로 수행됩니다. 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
     */

    // 알림구분코드(ALRT_WORK_CL_CD)
    private final static String ALRT_WORK_CL_CD_SMS = "S"; // 알림채널구분코드 : SMS

    private final static String ALRT_WORK_CL_CD_LMS = "L"; // 알림채널구분코드 : LMS
    
    private final static String ALRT_WORK_CL_CD_MMS = "M"; // 알림채널구분코드 : MMS

    private final static String ALRT_WORK_CL_CD_EMAIL = "E"; // 알림채널구분코드 : EMAIL

    // 수신자구분코드
    private final static String REVR_CL_CNTRTR = "10";// 계약자

    private final static String REVR_CL_USER = "20";// DMS사용자

    private final static String REVR_CL_DEAL_CO = "30";// 대리점주

    /**
     * Default Constructor
     */
    public FSCAlrtMgmt() {
        super();
    }

    /**
	 * <pre>알림발송</pre>
	 *
	 * @author admin (admin)
	 * @since 2015-07-13 12:41:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CHRG_DEPT_CL_CD [과금부서구분코드]
	 *	- field : ALRT_WORK_CL_CD [알림업무구분코드]
	 *	- field : REVR_CL_CD [수신자구분코드]
	 *	- field : RETN_TEL_NO [수신전화번호]
	 *	- field : ALRT_MSG_ID [알림메시지ID(공통메시지)]
	 *	- field : MSG_PARAMS [알림메세지 파라미터[]]
	 *	- field : MSG_CTT [메세지내용(사용자가 직접 메세지를 보내는 경우)]
	 *	- field : ATTACH_FILE1 [첨부파일경로1]
	 *	- field : ATTACH_FILE2 [첨부파일경로2]
	 *	- field : ATTACH_FILE3 [첨부파일경로3]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : SUCCESS [발송성공여부]
	 * </pre>
	 */
    public IDataSet fSndAlrt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet reqData = (IDataSet) requestData.clone();
        Log log = getLog(onlineCtx);
        try {
            // *************************************************************************************************************************
            // 입력 파라미터 유효성 체크 START
            // *************************************************************************************************************************
            // 1. 필수값 입력여부 체크 [DMS00002 : 필수입력항목 {0}이/가 누락되었습니다.]                        
            if (StringUtils.isEmpty(reqData.getField("REVR_CL_CD"))) {
                throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("REVR_CL_CD") });
            } else if (StringUtils.isEmpty(reqData.getField("RETN_TEL_NO"))) {
                throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("RETN_TEL_NO") });
            } else if (StringUtils.isEmpty(reqData.getField("CHRG_DEPT_CL_CD"))) {
               // TODO 임시테스트코드 입력, 추후 삭제예정
                reqData.putField("CHRG_DEPT_CL_CD", "ECO"); 
                // TODO : 공통 lang 프로퍼티에 과금부서구분코드 추가하기
                // throw new BizRuntimeException("DMS00002", new String[] {HpcUtils.getLangMsg("CHRG_DEPT_CL_CD") });
            } else if (StringUtils.isEmpty(reqData.getField("ALRT_WORK_CL_CD"))) {
                // TODO 알림업무구분코드 임시 defalut코드 입력, 추후 삭제예정
                reqData.putField("ALRT_WORK_CL_CD", "AUTO"); 
                // throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("ALRT_WORK_CL_CD") }); 
            } 
          
            //메세지내용 조합 및 글자수 반환 private 메서드 호출
           int iMsgCttLen = _setMessgeCTT(reqData , onlineCtx); 
                        
            // 2. 입력값 Check [DMS00086 : 유효한 코드가 아닙니다.]
            // 알림업무구분코드
            if (HpcUtils.getCode("DMS259", reqData.getField("ALRT_WORK_CL_CD")) == null) {
                throw new BizRuntimeException("DMS00086", new String[] { HpcUtils.getLangMsg("ALRT_WORK_CL_CD") });
            }
            // 수신자구분코드
            if (HpcUtils.getCode("DMS014", reqData.getField("REVR_CL_CD")) == null) {
                throw new BizRuntimeException("DMS00086", new String[] { HpcUtils.getLangMsg("REVR_CL_CD") });
            }
            // 과금부서구분코드
            if (HpcUtils.getCode("DMS258", reqData.getField("CHRG_DEPT_CL_CD")) == null) {
                throw new BizRuntimeException("입력한 과금부서구분코드[CHRG_DEPT_CL_CD]는 유효한 코드가 아닙니다.");
                // TODO: 공통 메세지프로퍼티 추가하기
                // throw new BizRuntimeException("DMS00086", new String[] {
                // HpcUtils.getLangMsg("CHRG_DEPT_CL_CD") });
            }

            // *************************************************************************************************************************
            // SMS API 호출용 파라미터 값 설정
            // *************************************************************************************************************************
           //  String recvMphNo = HpcUtils.decodeByAES(reqData.getField("RETN_TEL_NO_ENPT")); // 수신전화번호(복호화)
            String recvMphNo =requestData.getField("RETN_TEL_NO"); // 수신전화번호
            recvMphNo = StringUtils.trim(recvMphNo.replaceAll("-", "")); // "-"제거,trim            
            reqData.putField("RETN_TEL_NO", recvMphNo);
            
            // MMS전송대상여부 (첨부파일 존재여부로 판단)
            Boolean bMMSType = (StringUtils.isNotEmpty(reqData.getField("ATTACH_FILE1"))
                                          || StringUtils.isNotEmpty(reqData.getField("ATTACH_FILE2")) 
                                          || StringUtils.isNotEmpty(reqData.getField("ATTACH_FILE3")));

            String sChrgDeptClCd = reqData.getField("CHRG_DEPT_CL_CD"); // 과금부서구분코드
            // 과금부서구분코드 Item값
            HpcCode cdInfo = HpcUtils.getCode("DMS258", sChrgDeptClCd);
            String sChrgUserCd = cdInfo.getMgmtItemCd1(); //과금사용자코드
            String sChrgDeptCd = cdInfo.getMgmtItemCd2(); //과금부서코드
            String callNumber = cdInfo.getMgmtItemCd3();  //발신전화번호
            String sMsgSenderNm = cdInfo.getMgmtItemCd4(); //발신자명
            String sMsgTitle = cdInfo.getMgmtItemCd5(); // 메세지제목(MMS,LMS인 경우 필수항목) 
            
            log.debug("과금부서구분코드 사용자입력값 CHRG_DEPT_CL_CD  [" + sChrgDeptClCd + "]");
            log.debug("ItemCd1 사용자코드  [" + sChrgUserCd + "]");
            log.debug("ItemCd2 부서코드 [" + sChrgDeptCd + "]");
            log.debug("ItemCd3 발신전화번호 [" + callNumber + "]");
            log.debug("ItemCd4 발신자명  [" + sMsgSenderNm + "]");
            log.debug("ItemCd5 메세지타이틀  [" + sMsgTitle + "]");

            if (StringUtils.isEmpty(sChrgUserCd) 
                || StringUtils.isEmpty(sChrgDeptCd)
                || StringUtils.isEmpty(callNumber)
                || StringUtils.isEmpty(sMsgSenderNm)) {
                throw new BizRuntimeException("DMS00162");//과금부서구분코드 등록 상세정보를 확인해주세요.
            }
            if (StringUtils.isEmpty(sMsgTitle) && (bMMSType || (!bMMSType && iMsgCttLen > 90 ))) {
                throw new BizRuntimeException("DMS00163");//LMS/MMS 전송시 메세지 제목을 입력해주세요.
            }
            
            reqData.putField("CHRG_USER_CD", sChrgUserCd);
            reqData.putField("CHRG_DEPT_CD", sChrgDeptCd);
            reqData.putField("CALL_NUMBER", callNumber);
            reqData.putField("MSG_SENDER_NM", sMsgSenderNm);
            reqData.putField("MSG_TITL", sMsgTitle);
            
             // *************************************************************************************************************************
            // SMS전송 API 호출
            // *************************************************************************************************************************            
            //SMS/LMS/MMS 전송 분기처리
            if(bMMSType){
                //1. MMS 전송 [글자수와 상관없이 첨부파일이 존재하는 경우]   
                //첨부파일 경로 생성
                _setAttachFilePath(reqData, onlineCtx);
                _sendMessage(ALRT_WORK_CL_CD_MMS, reqData, onlineCtx);
            } else {
                if(iMsgCttLen <= 90){
                   //2. SMS 전송 [90자 이하인 경우]
                   _sendMessage(ALRT_WORK_CL_CD_SMS, reqData, onlineCtx);
               } else {
                   //3. LMS 전송 [90자 초과]                    
                   _sendMessage(ALRT_WORK_CL_CD_LMS, reqData, onlineCtx);                   
               }
            }                                    
        } catch (BizRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }

        responseData.putField(DmsConstants.IS_SUCCESS, true);
        return responseData;
    }

    /**
     * 알림서비스발송이력조회
     * 
     * @author 이유미 (leeyoumee)
     * @since 2014-08-21 13:50:46
     * 
     * @param requestData
     *            요청정보 DataSet 객체
     * 
     *            <pre>
     * - field : ALRT_SND_REQ_MM [알림발송요청월]
     * - field : ALRT_SVC_NO [알림서비스번호]
     * - field : REVR_CL_CD [수신자구분코드]
     * - field : REVR_NO [수신자번호]
     * - field : RETN_TEL_NO [수신전화번호]
     * - field : RETN_TEL_NO_ENPT [수신전화번호암호화]
     * - field : RETN_EML_ADDR [수신이메일주소]
     * - field : RETN_EML_ADDR_ENPT [수신이메일주소암호화]
     * </pre>
     * @param onlineCtx
     *            요청 컨텍스트 정보
     * @return 처리결과 DataSet 객체
     * 
     *         <pre>
     * - record : RS_ALRT_SVC_SND_LIST
     * 	- field : ALRT_SND_REQ_DTM [알림발송요청일시]
     * 	- field : ALRT_SVC_NO [알림서비스번호]
     * 	- field : ALRT_SND_REQ_CHNL_CD [알림발송요청채널코드]
     * 	- field : ALRT_SND_REQ_CHNL_CD_NM [알림발송요청채널코드명]
     * 	- field : ALRT_WORK_CL_CD [알림업무구분코드]
     * 	- field : ALRT_WORK_CL_CD_NM [알림업무구분코드명]
     * 	- field : ALRT_CHNL_CL_CD [알림채널구분코드]
     * 	- field : ALRT_CHNL_CL_CD_NM [알림채널구분코드명]
     * 	- field : REVR_CL_CD [수신자구분]
     * 	- field : REVR_NO [수신자번호]
     * 	- field : RETN_TEL_NO [수신전화번호]
     * 	- field : RETN_EML_ADDR [수신이메일주소]
     * 	- field : ALRT_MSG_CTT [알림메시지내용]
     * 	- field : SND_ST_CD [전송결과코드]
     * 	- field : SND_FAIL_RSN_CTT [발송실패사유내용]
     * </pre>
     */
    public IDataSet fInqAlrtSvcSndHst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        IDataSet dsCnt = new DataSet();
        IDataSet reqDs = (IDataSet) requestData.clone(); // 원거래의 DataSet 훼손을 막기
                                                         // 위한 clone
        IRecordSet alrtSvcHstRs = null;
        int alrtSvcHstTtCnt = 0;
        try {
            // 1. DU lookup
            DSCAlrtMgmt dSCAlrtMgmt = (DSCAlrtMgmt) lookupDataUnit(DSCAlrtMgmt.class);
            // 2. TOTAL COUNT DM호출
            dsCnt = dSCAlrtMgmt.dSAlrtSvcSndHstTotCnt(requestData, onlineCtx);
            alrtSvcHstTtCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
            PagenateUtils.setPagenatedParamsToDataSet(reqDs);
            // 3. LIST DM 호출
            responseData = dSCAlrtMgmt.dSAlrtSvcSndHstPaging(reqDs, onlineCtx);
            alrtSvcHstRs = responseData.getRecordSet("RS_ALRT_SVC_SND_LIST");
            PagenateUtils.setPagenatedParamToRecordSet(alrtSvcHstRs, reqDs, alrtSvcHstTtCnt);
        } catch (BizRuntimeException e) {
            throw e;
        }
        return responseData;
    }

    /**
     * <pre>
     * 알림발송결과조회
     * </pre>
     * 
     * @author admin (admin)
     * @since 2015-07-13 12:41:50
     * 
     * @param requestData
     *            요청정보 DataSet 객체
     * @param onlineCtx
     *            요청 컨텍스트 정보
     * @return 처리결과 DataSet 객체
     * 
     *         <pre>
     * - field : SUCCESS [발송성공여부]
     * </pre>
     */
    public IDataSet fInqAlrtRslt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        final IDataSet reqData = (IDataSet) requestData.clone();
        final IOnlineContext oCtx = onlineCtx;
        try {
            SureSMSAPI messageApi = new SureSMSAPI() {

                @Override
                public void report(SureSMSDeliveryReport dr) {
                    DSCAlrtMgmt dSCAlrtMgmt = (DSCAlrtMgmt) lookupDataUnit(DSCAlrtMgmt.class);
                    reqData.putField("ALRT_SND_NO", dr.getMember());
                    reqData.putField("TRMS_RSLT_CD", dr.getErrorCode());
                    if ("2".equals(dr.getResult())) {
                        reqData.putField("TRMS_RSLT_YN", "Y");
                    } else {
                        reqData.putField("TRMS_RSLT_YN", "N");
                    }
                    // 1. DU lookup
                    dSCAlrtMgmt.dUAlrtRslt(reqData, oCtx);
                }
            };

            messageApi.recvReport(DateUtils.getCurrentDate(), "skeco", "XA-UXH-JM");

            SureMMSAPI mms = new SureMMSAPI() {

                @Override
                public void report(SureMMSDeliveryReport dr) {
                    DSCAlrtMgmt dSCAlrtMgmt = (DSCAlrtMgmt) lookupDataUnit(DSCAlrtMgmt.class);
                    reqData.putField("ALRT_SND_NO", dr.getMember());
                    reqData.putField("TRMS_RSLT_CD", dr.getErrorCode());
                    if ("2".equals(dr.getResult())) {
                        reqData.putField("TRMS_RSLT_YN", "Y");
                    } else {
                        reqData.putField("TRMS_RSLT_YN", "N");
                    }
                    // 1. DU lookup
                    dSCAlrtMgmt.dUAlrtRslt(reqData, oCtx);
                }
            };

            mms.recvReport(DateUtils.getCurrentDate(), "skeco", "XA-UXH-JM");
        } catch (BizRuntimeException e) {
            throw e;
        }

        responseData.putField(DmsConstants.IS_SUCCESS, true);
        return responseData;
    }
    
    /**
     * private메서드
     * 메세지내용 조합
     * 
     * ALRT_MSG_ID 값이 있는 경우 메세지ID와 파라미터로 메세지를 조합하거나 
     * 사용자가 입력한 MSG_CTT값이 있는 경우 최종 메세지내용을 SMS전송용 데이터셋에 설정한다.
     * 
     * @param requestData
     * @param onlineCtx
     * @throws UnsupportedEncodingException 
     * return int 글자수 
     */
    private int _setMessgeCTT(IDataSet requestData, IOnlineContext onlineCtx) throws UnsupportedEncodingException {
        Log log = getLog(onlineCtx);     
       
        int iLen = 0;
        String msgCtt = "";
        
        if(StringUtils.isNotEmpty(requestData.getField("MSG_CTT"))){
            msgCtt = requestData.getField("MSG_CTT");
        } else {        
            //메시지ID+파라미터 조합
            String[] msgParams = (String[]) requestData.getObjectField("MSG_PARAMS"); //메시지파라미터(String[])
            msgCtt = HpcUtils.getMessage(requestData.getField("ALRT_MSG_ID"), msgParams); // 메시지조립작업          
        }
        
        if (StringUtils.isEmpty(msgCtt)) {
            throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("ALRT_MSG_CTT") });
        }
        requestData.putField("TOTAL_MSG_CTT", msgCtt);
        
        try{      
            iLen =  msgCtt.getBytes("MS949").length;
        } catch (UnsupportedEncodingException e) {
            throw e;
        } 
        // log.debug("UTF8 LENGTH["+msgCtt.getBytes("UTF8").length+"]");
        log.debug("메시지 최종 내용 : [" + msgCtt + "]");
        log.debug("메시지 글자수 :  MS949 LENGTH[" + iLen+ "]");

        
        return iLen;
       
    } 
    
    /**
     * private메서드
     * 첨부파일 파라미터 경로 세팅 
     * 
     * @param requestData
     * @param onlineCtx
     */
    private void _setAttachFilePath(IDataSet requestData,  IOnlineContext onlineCtx) {
        try{      
                for(int i=1;i<=3;i++){
                    _getFilePathRename("ATTACH_FILE"+i, requestData, onlineCtx);
                }           
        } catch (BizRuntimeException e) {
            throw e;
        }   
    } 
    
    /**
     * private메서드
     * 첨부파일 파일 원복
     * 
     * @param requestData
     * @param onlineCtx
     */
/*    private void _resetFileNm(IDataSet requestData,  IOnlineContext onlineCtx) {
        String sOldColNm = "";
        String sNewColNm = "";
        try{      
            
            for(int i=1;i<=3;i++)
            { 
                sOldColNm = "ATTACH_FILE"+i+"_OLD";
                sNewColNm = "ATTACH_FILE"+i+"_NEW";
                if(requestData.containsField(sOldColNm) 
                   && requestData.containsField(sNewColNm)
                   && StringUtils.isNotEmpty(requestData.getField(sOldColNm))
                   && StringUtils.isNotEmpty(requestData.getField(sNewColNm)) )
                {                        
                    _setFileNmRename(requestData.getField(sNewColNm), requestData.getField(sOldColNm) , onlineCtx);
                }                   
            }           
        } catch (BizRuntimeException e) {
            throw e;
        }   
    }*/
    /**
     * private메서드
     * MMS전송API에서 파일첨부가 가능하도록 
     * 서버상의 첨부파일을 확장자가 있는 파일명으로 변경한다.
     * 
     * @param 컬럼명
     * @param requestData
     * @param onlineCtx
     * return String 파일경로 
     */
    private void _getFilePathRename(String targetColNm, IDataSet requestData, IOnlineContext onlineCtx) {
        Log log = getLog(onlineCtx);     
        String rsPath = "";
        log.debug(targetColNm+" : [" +requestData.getField(targetColNm)+ "]");
      
        try{        
            
            if(!requestData.containsField(targetColNm) || StringUtils.isEmpty(requestData.getField(targetColNm))){
                return;
            }
            
            String[] sAttachFileArray = requestData.getField(targetColNm).split("\\|\\|", 2);     
            
            if(sAttachFileArray.length < 2){
                return;
            }   
            
            String sFileId = sAttachFileArray[0];
            String sFileNm = sAttachFileArray[1];
            log.debug("sFileId : [" +sFileId+ "]");
            log.debug("sFileNm : [" + sFileNm + "]");
            
          //MMS전송API에서 파일첨부가 가능하도록 서버상의 첨부파일을 확장자가 있는 파일명으로 변경함
            String sDate = "";                
            String regex = "\\d{8}";                             
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(sFileId);                         
            while(m.find() && StringUtils.isEmpty(sDate)){
                sDate= m.group();                   
            }                   
            log.debug("m.group();    : [" +   sDate + "]");           
            if(StringUtils.isEmpty(sDate)){    
               return;
            } 
            String sFolderClWord = "/"; 
            StringBuffer sb = new StringBuffer();
            //서버파일경로 
            sb.append(BaseUtils.getConfiguration("file.upload.root.folder."+BaseUtils.getRuntimeMode()));
            sb.append(sFolderClWord);
            sb.append(sDate.substring(0, 4));
            sb.append(sFolderClWord);
            sb.append(sDate.substring(4, 6));
            sb.append(sFolderClWord);
            sb.append(sDate.substring(6, 8));
            sb.append(sFolderClWord);
            log.debug("basic File Path : [" +   sb.toString() + "]");           
            
            String sOldFilePath = sb.toString()+sFileId;
            String sNewFilePath = sb.toString()+sFileId+sFileNm;
            
            /*//기존 경로 파라미터 입력 (메세지 전송 후 원복작업)
            requestData.putField(targetColNm+"_OLD", sOldFilePath);
            log.debug(targetColNm+"_OLD"+" : [" +requestData.getField(targetColNm+"_OLD")+ "]");*/
            
            //첨부파일 renaming
            rsPath = _setFileNmRename(sOldFilePath, sNewFilePath, onlineCtx);      
            //신규 경로 파라미터 입력       
            requestData.putField(targetColNm+"_NEW", rsPath);      
            log.debug(targetColNm+"_NEW"+" : [" +requestData.getField(targetColNm+"_NEW")+ "]");
            
        } catch (BizRuntimeException e) {
            throw e;
        }        
        
        
    }
    /**
     * private메서드
     * 서버상의 첨부파일명 변경처리
     * 
     * @param 기존 파일 경로
     * @param 새 파일 경로
     * @param onlineCtx
     * return String 파일경로 
     */
    private String _setFileNmRename(String oldFileNm, String newFileNm , IOnlineContext onlineCtx) {
        Log log = getLog(onlineCtx);
        String rsPath = "";
        try {   
            
                File originFile = new File(oldFileNm);
                File fileToMove = new File(newFileNm);
                log.debug("old File Path : [" +   originFile.toString() + "]");      
                log.debug("new File Path : [" +   fileToMove.toString() + "]");         
                
                if (!originFile.exists() && !fileToMove.exists()) {
                    log.debug("[파일 rename 실패!! 기존 경로에 해당파일 존재하지 않음. ]");      
                    return "";
                } else if(!originFile.exists() && fileToMove.exists()){
                    rsPath = fileToMove.toString();  
                    log.debug("[sms 일괄전송으로 기존 rename된 파일 사용]");      
                } else {
                    //첨부파일 renaming   
                    originFile.renameTo(fileToMove);
                    rsPath = fileToMove.toString();  
                    log.debug("[파일 rename 성공]");   
                }
            
            } catch (BizRuntimeException e) {
                throw e;
            }   
        
        return rsPath;
    }
    /**
     * private메서드
     * SMS 전송 API호출 
     * 
     * @param 메세지전송구분코드
     * @param requestData
     * @param onlineCtx
     */
    private void _sendMessage(String alrtMsgCd, IDataSet requestData,  IOnlineContext onlineCtx) {
        Log log = getLog(onlineCtx);
        try {   
                //알림발송번호채번 서비스 호출 
                DSCAlrtMgmt dSCAlrtMgmt = (DSCAlrtMgmt) lookupDataUnit(DSCAlrtMgmt.class);
                String alrtSndNo = dSCAlrtMgmt.dSAlrtSndNoNEW(requestData, onlineCtx).getField("ALRT_SND_NO");
                requestData.putField("ALRT_SND_NO", alrtSndNo);
                
                //SMS전송 API 레포트
                SendReport sReport;               
                //알림전송구분코드 (*메세지 글자수 및 첨부파일여부에 따라 fm에서 직접 판단함 )
                requestData.putField("ALRT_MSG_CD", alrtMsgCd); 
                String recvMphNo = requestData.getField("RETN_TEL_NO");        
                String callNumber = requestData.getField("CALL_NUMBER");
                
                if(ALRT_WORK_CL_CD_SMS.equals(alrtMsgCd)){
                    //1. SMS 송신
                    SureSMSAPI messageApi = new SureSMSAPI() {
                        public void report(SureSMSDeliveryReport deliveryReport) {
                        }
                    };
                    sReport = messageApi.sendMain(Integer.parseInt(requestData.getField("ALRT_SND_NO")),
                                                            requestData.getField("CHRG_USER_CD"),
                                                            requestData.getField("CHRG_DEPT_CD"),
                                                            "DMS",
                                                            recvMphNo.substring(0, 3),
                                                            recvMphNo.substring(3, 7),
                                                            recvMphNo.substring(7),
                                                            requestData.getField("MSG_SENDER_NM"),
                                                            callNumber.substring(0, 3),
                                                            callNumber.substring(3, 7),
                                                            callNumber.substring(7),
                                                            requestData.getField("TOTAL_MSG_CTT"),
                                                            "00000000",
                                                            "000000");   
                } else {
                    //2. MMS,LMS 송신
                    SureMMSAPI mms = new SureMMSAPI(){
                            @Override
                            public void report(SureMMSDeliveryReport dr) {
                            }
                    };            
                    sReport = mms.sendMain(Integer.parseInt(requestData.getField("ALRT_SND_NO")),
                                                    requestData.getField("CHRG_USER_CD"),
                                                    requestData.getField("CHRG_DEPT_CD"),
                                                    recvMphNo,
                                                    callNumber,
                                                    "000000",
                                                    requestData.getField("MSG_TITL"),
                                                    requestData.getField("TOTAL_MSG_CTT"),
                                                    StringUtils.nvlEmpty(requestData.getField("ATTACH_FILE1_NEW"), ""),
                                                    StringUtils.nvlEmpty(requestData.getField("ATTACH_FILE2_NEW"), ""),
                                                    StringUtils.nvlEmpty(requestData.getField("ATTACH_FILE3_NEW"), ""));
                 }
                log.debug("메세지 전송구분 "+requestData.getField("ALRT_WORK_CL_CD"));
                log.debug("STATUS[" + sReport.getStatus() + "]");
                log.debug("sReport.toString[" + sReport.toString() + "]");
                log.debug("recvMphNo[" + recvMphNo + "]");
                
                /* SureM 전송Api 전송결과 코드 
                 * 
                    메세지 전송결과코드 
                    (예약설정:'R', 즉시전송설정:'0', 전송요청완료:'Y', 전송요청실패:'N', 전송결과성공:'2', 전송결과실패:'4', 전송결과유효기값초과:'6', 사용자임의대기설정:'H')    
                    d. ErrCode : Result가 .N.인 경우, 슈어엠에서 응답값으로 보내주는 리턴값을 셋팅
                      * 리턴값
                    ( ErrCode ) . Result 가 .N. 인 경우 자동으로 셋팅 됨
                     1 : 슈어엠 서버로 전송 성공
                    -1 : INVALID_SOCKET ( socket 에러 or connect 에러 )
                    -2 : 잘못된PACKET_SIZE를 슈어엠 서버로 전송할 때
                    -3 : 잘못된PACKET_SIZE를 전송 받았을 때
                    67 (C) : 잔액부족
                    68 (D) : 고객사 사용자 ID 이상
                    73 (I) : 미등록 회원 또는 기본료 미납
                    77 (M) : 호출할 메시지의 내용이 없음
                    78 (N) : 국번 or 전화번호 뒤 4자리 이상
                    80 (P) : 이통사 번호 이상
                    82 (R) : .700.,.800. 금지업체
                    84 (T) : 예약일자 이상
                    85 (U) : 호출 URL 이 없음
                    99 (c) : 기타 오류
                    41 : 예약취소시 동일 데이터 찾을 수 없음.
                */                
                
                 //SMS전송 성공시 알림발송내역이력 등록
                if (StringUtils.equals(sReport.getStatus(), "O")) {
                    IDataSet reqAlrtHstDs = (IDataSet) requestData.clone();
                    
                    //알림 전송 이력등록시 수신번호암호화값 입력
                    reqAlrtHstDs.putField("RETN_TEL_NO_ENPT",  HpcUtils.encodeByAES(recvMphNo)); //암호화처리
                    reqAlrtHstDs.putField("RETN_TEL_NO", HpcUtils.maskingTelNo(recvMphNo)); //마스킹처리
                    reqAlrtHstDs.putField("ALRT_MSG_CTT",  requestData.getField("TOTAL_MSG_CTT"));
                    //알림전송 이력등록 dm 서비스 호출
                    dSCAlrtMgmt.dIAlrtSndItm(reqAlrtHstDs, onlineCtx);
                } else {
                    throw new BizRuntimeException("DMS00164");//SMS 전송 실패하였습니다.
                }
                
        } catch (BizRuntimeException e) {
            throw e;
        }   
    }
}