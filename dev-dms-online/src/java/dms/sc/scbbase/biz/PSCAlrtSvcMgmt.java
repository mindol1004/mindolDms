package dms.sc.scbbase.biz;

import java.util.ArrayList;
import java.util.List;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;
import fwk.common.CommonArea;
import fwk.constants.DmsConstants;
import fwk.utils.HpcUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: PSCAlrtSvcMgmt</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-09-22 16:34:56</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class PSCAlrtSvcMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PSCAlrtSvcMgmt() {
		super();
	}

	/**
	 * 알림서비스목록조회
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-09-22 16:34:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ALRT_SVC_NO [알림서비스번호]
	 *	- field : ALRT_SVC_NM [알림서비스명]
	 *	- field : ALRT_WORK_CL_CD [알림업무구분코드]
	 *	- field : ALRT_CHNL_CL_CD [알림채널구분코드]
	 *	- field : ALRT_CONS_CL_CD [알림제약구분코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_ALRT_SVC_LIST
	 *		- field : ALRT_SVC_NO [알림서비스번호]
	 *		- field : ALRT_SVC_NM [알림서비스명]
	 *		- field : ALRT_WORK_CL_CD [알림업무구분코드]
	 *		- field : ALRT_CHNL_CL_CD [알림채널구분코드]
	 *		- field : ALRT_CONS_CL_CD [알림제약구분코드]
	 *		- field : RETN_AGR_APLY_YN [수신동의적용여부]
	 *		- field : ALRT_TITE_NM [알림제목명]
	 *		- field : ALRT_MSG_CTT [알림메시지내용]
	 *		- field : ANSW_TEL_NO [회신전화번호]
	 *		- field : ANSW_EML_ADDR [회신이메일주소]
	 *		- field : ANSW_TEL_NO_BEFORE [회신전화번호copy]
	 *		- field : ANSW_EML_ADDR_BEFORE [회신이메일주소copy]
	 *		- field : USE_YN [사용여부]
	 * </pre>
	 */
	public IDataSet pInqAlrtSvcLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCAlrtSvcMgmt fSCAlrtSvcMgmt = (FSCAlrtSvcMgmt) lookupFunctionUnit(FSCAlrtSvcMgmt.class);
			// 2. FM 호출
			if ( requestData.getField("ANSW_TEL_NO") != null ) {
				requestData.putField("ANSW_TEL_NO_ENPT", HpcUtils.encodeByAES(requestData.getField("ANSW_TEL_NO")));
			}
			if ( requestData.getField("ANSW_EML_ADDR") != null ) {
				requestData.putField("ANSW_EML_ADDR_ENPT", HpcUtils.encodeByAES(requestData.getField("ANSW_EML_ADDR")));
			}
			responseData = fSCAlrtSvcMgmt.fInqAlrtSvcLst(requestData, onlineCtx);
			// 3. 결과값 리턴
			responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

	/**
	 * 알림서비스저장
	 *
	 * @author 임지후 (jihooyim)
	 * @since 2014-09-22 16:34:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_ALRT_SVC_LIST
	 *		- field : ALRT_SVC_NO [알림서비스번호]
	 *		- field : ALRT_SVC_NM [알림서비스명]
	 *		- field : ALRT_WORK_CL_CD [알림업무구분코드]
	 *		- field : ALRT_CHNL_CL_CD [알림채널구분코드]
	 *		- field : ALRT_CONS_CL_CD [알림제약구분코드]
	 *		- field : RETN_AGR_APLY_YN [수신동의적용여부]
	 *		- field : ALRT_TITE_NM [알림제목명]
	 *		- field : ALRT_MSG_CTT [알림메시지내용]
	 *		- field : ANSW_TEL_NO [회신전화번호]
	 *		- field : ANSW_EML_ADDR [회신이메일주소]
	 *		- field : ANSW_TEL_NO_ENPT [회신전화번호암호화]
	 *		- field : ANSW_EML_ADDR_ENPT [회신이메일주소암호화]
	 *		- field : USE_YN [사용여부]
	 *		- field : ANSW_TEL_NO_CHANGE_YN [전화번호변경여부]
	 *		- field : ANSW_EML_ADDR_CHANGE_YN [이메일변경여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveAlrtSvc(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);
		try {
			// 1. FU lookup
			FSCAlrtSvcMgmt fSCAlrtSvcMgmt = (FSCAlrtSvcMgmt) lookupFunctionUnit(FSCAlrtSvcMgmt.class);
			// 2. 입력 RS설정
			//2015.02.03 전화번호, 이메일주소가 마스킹없이 완전히 수정된 경우에만 DB에 반영하도록 체크컬럼 추가  
			requestData.putFieldMap(requestData.getRecordSet("RS_ALRT_SVC_LIST").getRecordMap(0));
			if ( !StringUtils.isEmpty(requestData.getField("ANSW_TEL_NO"))
				&& (StringUtils.equals("I", ca.getTrnPtrnDvcd()) || StringUtils.equals("Y", requestData.getField("ANSW_TEL_NO_CHANGE_YN"))) ) {
				requestData.putField("ANSW_TEL_NO_ENPT", HpcUtils.encodeByAES(requestData.getField("ANSW_TEL_NO")));
				requestData.putField("ANSW_TEL_NO_MASK", HpcUtils.maskingTelNo(requestData.getField("ANSW_TEL_NO")));
			}
			if ( !StringUtils.isEmpty(requestData.getField("ANSW_EML_ADDR"))
				&& (StringUtils.equals("I", ca.getTrnPtrnDvcd()) || StringUtils.equals("Y", requestData.getField("ANSW_EML_ADDR_CHANGE_YN"))) ) {
				requestData.putField("ANSW_EML_ADDR_ENPT", HpcUtils.encodeByAES(requestData.getField("ANSW_EML_ADDR")));
				requestData.putField("ANSW_EML_ADDR_MASK", HpcUtils.maskingEmail(requestData.getField("ANSW_EML_ADDR")));
			}
			requestData.putField("USERNO", ca.getUserNo());
			// 3. 레코드셋 상태에 따른 분기
			if ( "I".equals(ca.getTrnPtrnDvcd()) ) { // INSERT	
				fSCAlrtSvcMgmt.fRegAlrtSvc(requestData, onlineCtx);
			} else if ( "U".equals(ca.getTrnPtrnDvcd()) ) { // UPDATE	
				fSCAlrtSvcMgmt.fUpdAlrtSvc(requestData, onlineCtx);
			}
			// 4. 결과값 리턴
			responseData.setOkResultMessage("DMS00000", null);  //정상 처리되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e);  //시스템 오류
		}
		return responseData;
	}

	/**
	 * 알림요청브랜드목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ALRT_SVC_NO [알림서비스번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_REQ_BRN_LIST
	 *		- field : ALRT_SVC_NO [알림서비스번호]
	 *		- field : ALRT_SNO [알림일련번호]
	 *		- field : REQ_BRND_CD [요청브랜드코드]
	 *		- field : USE_YN [사용여부]
	 * </pre>
	 */
	public IDataSet pInqAlrtReqBrndLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCAlrtSvcMgmt fSCAlrtSvcMgmt = (FSCAlrtSvcMgmt) lookupFunctionUnit(FSCAlrtSvcMgmt.class);
			// 2. FM 호출
			responseData = fSCAlrtSvcMgmt.fInqAlrtReqBrndLst(requestData, onlineCtx);
			// 3. 결과값 리턴
			responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

	/**
	 * 알림요청브랜드저장
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_REQ_BRN_LIST
	 *		- field : ALRT_SVC_NO [알림서비스번호]
	 *		- field : ALRT_SNO [알림일련번호]
	 *		- field : REQ_BRND_CD [요청브랜드코드]
	 *		- field : USE_YN [사용여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveAlrtReqBrnd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);
		try {
			// 1. FU lookup
			FSCAlrtSvcMgmt fSCAlrtSvcMgmt = (FSCAlrtSvcMgmt) lookupFunctionUnit(FSCAlrtSvcMgmt.class);
			// 2. 입력 RS설정
			IRecordSet rs = requestData.getRecordSet("RS_REQ_BRN_LIST");
			IDataSet reqDS = (IDataSet) requestData.clone();
			IRecord record = null;
			for ( int i = 0 ; i < rs.getRecordCount() ; i++ ) {
				record = rs.getRecord(i);
				IDataSet reqDataSet = new DataSet();
				reqDS.putFieldMap(record);
				reqDS.putFieldMap(rs.getRecordMap(i));
				reqDS.putField("USERNO", ca.getUserNo());
				// 3. 레코드셋 상태에 따른 분기
				if ( StringUtils.equals(DmsConstants.STATUS_INSERTED, record.get(DmsConstants.RECORD_STATUS)) ) { // INSERT
					fSCAlrtSvcMgmt.fRegAlrtReqBrnd(reqDS, onlineCtx);
				} else if ( StringUtils.equals(DmsConstants.STATUS_UPDATED, record.get(DmsConstants.RECORD_STATUS)) ) { // UPDATE
					fSCAlrtSvcMgmt.fUpdAlrtReqBrnd(reqDS, onlineCtx);
				} else if ( StringUtils.equals(DmsConstants.STATUS_DELETED, record.get(DmsConstants.RECORD_STATUS)) ) { // DELETE
					fSCAlrtSvcMgmt.fDelAlrtReqBrnd(reqDS, onlineCtx);
				}
			}
			// 4. 결과값 리턴
			responseData.setOkResultMessage("DMS00000", null);  //정상 처리되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e);  //시스템 오류
		}
		return responseData;
	}

	/**
	 *
	 *
	 * @author admin (admin)
	 * @since 2015-06-22 16:59:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ALRT_WORK_CL_CD [알림업무구분코드]
	 *	- field : CHRG_DEPT_CL_CD [과금부서구분코드]
	 *	- field : REVR_CL_CD [수신자구분코드]
	 *	- field : RETN_TEL_NO [수신전화번호]
	 *	- field : ALRT_MSG_ID [알림메시지ID]
	 *	- field : ATTACH_FILE1 [첨부파일경로1]
	 *	- field : ATTACH_FILE2 [첨부파일경로2]
	 *	- field : ATTACH_FILE3 [첨부파일경로3]
	 *	- record : RS_MSG_PARAM
	 *		- field : MSG_PARAMS [파라미터]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSendSmsTest(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);
		IDataSet reqDs =(IDataSet) requestData.clone();
		try {
		    
            List<String> svcLst = new ArrayList<String>();
            IRecordSet rsSvcMgmtNoList = requestData.getRecordSet("RS_MSG_PARAM");     
            
            for (int i = 0; i < rsSvcMgmtNoList.getRecordCount(); i++) {
                svcLst.add(rsSvcMgmtNoList.get(i, "MSG_PARAMS"));
            }     
            String[] sSvcList = svcLst.toArray(new String[svcLst.size()]);         
            reqDs.putField("MSG_PARAMS", sSvcList);
			reqDs.putField("USER_NO", ca.getUserNo());
						
			callSharedBizComponentByDirect("sc.SCSBase", "fSndAlrt", reqDs, onlineCtx);
			//결과값 리턴
			responseData.setOkResultMessage("DMS00000", null); //정상 조회되었습니다.
	    } catch (BizRuntimeException e) {
	    	throw e;
	    } catch (Exception e) {
	    	throw new BizRuntimeException("DMS00009", e);
	    } 	    
	    return responseData;
	}

    /**
	 * <pre>DMS 메인화면에서 SMS전송 요청시 호출되는 pm</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-06-22 16:59:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_RETN_TEL_LIST
	 *		- field : RETN_TEL_NO [수신번호]
	 *	- field : CHRG_DEPT_CL_CD [과금부서구분코드]
	 *	- field : MSG_CTT [메세지내용]
	 *	- field : ATTACH_FILE1 [첨부파일1]
	 *	- field : ATTACH_FILE2 [첨부파일2]
	 *	- field : ATTACH_FILE3 [첨부파일3]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSendSMS(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);
        IDataSet reqDs =(IDataSet) requestData.clone();
        try {
            //
            List<String> svcLst = new ArrayList<String>();
            //기본값 설정
            reqDs.putField("ALRT_WORK_CL_CD", "MANL");    //알림업무구분   공통코드그룹 : DMS259  / 코드값 : SMS_수동발송
            reqDs.putField("REVR_CL_CD", "10");           //알림수신자구분 공통코드그룹 : DMS014  / 코드값 : 계약자
            reqDs.putField("USER_NO", ca.getUserNo()); //사용자번호 
              
            //수신번호별 sms전송 fm호출
            IRecordSet rsRetnTelList = requestData.getRecordSet("RS_RETN_TEL_LIST");     
            for (int i = 0; i < rsRetnTelList.getRecordCount(); i++) {
                reqDs.putField("RETN_TEL_NO", rsRetnTelList.get(i, "RETN_TEL_NO"));
                callSharedBizComponentByDirect("sc.SCSBase", "fSndAlrt", reqDs, onlineCtx);
            }                 
            //결과값 리턴
            responseData.setOkResultMessage("DMS00000", null); //정상 조회되었습니다.
        } catch (BizRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new BizRuntimeException("DMS00009", e);
        }       
        return responseData;
    }

}
