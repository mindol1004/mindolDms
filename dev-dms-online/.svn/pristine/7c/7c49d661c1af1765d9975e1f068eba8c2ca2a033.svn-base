package dms.sc.scbbase.biz;

import java.util.ArrayList;
import java.util.List;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordHeader;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.RecordHeader;
import nexcore.framework.core.data.RecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;

import org.apache.commons.logging.Log;

import fwk.code.internal.HpcCode;
import fwk.common.CommonArea;
import fwk.utils.HpcUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: [PU]공통코드관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2014-07-25 15:56:13</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class PSCCmCdMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PSCCmCdMgmt() {
		super();
	}

	/**
	 * 공통코드목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CM_GRP_CD_ID [공통그룹코드ID]
	 *	- field : CM_CD_ID [공통코드ID]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_CM_CD_LIST
	 *		- field : CM_GRP_CD_ID [공통그룹코드ID]
	 *		- field : CM_GRP_CD_NM [공통그룹코드명]
	 *		- field : CM_CD_ID [공통코드ID]
	 *		- field : CM_CD_NM [공통코드명]
	 *		- field : CM_CD_DESC [공통코드설명]
	 *		- field : CM_CD_SORT_ORD [공통코드정렬순서]
	 *		- field : USE_YN [사용여부]
	 *		- field : SUP_CM_GRP_CD_ID [상위공통그룹코드ID]
	 *		- field : SUP_CM_CD_ID [상위공통코드ID]
	 *		- field : MGMT_ITEM_CD1 [관리항목코드1]
	 *		- field : MGMT_ITEM_CD2 [관리항목코드2]
	 *		- field : MGMT_ITEM_CD3 [관리항목코드3]
	 *		- field : MGMT_ITEM_CD4 [관리항목코드4]
	 *		- field : MGMT_ITEM_CD5 [관리항목코드5]
	 *		- field : MGMT_ITEM_CD6 [관리항목코드6]
	 *		- field : MGMT_ITEM_CD7 [관리항목코드7]
	 *		- field : MGMT_ITEM_CD8 [관리항목코드8]
	 *		- field : MGMT_ITEM_CD9 [관리항목코드9]
	 *		- field : MGMT_ITEM_CD10 [관리항목코드10]
	 *		- field : LS_CHG_USER_ID [최종등록사용자ID]
	 *		- field : LS_CHG_DTM [최종등록일시]
	 * </pre>
	 */
	public IDataSet pInqCmCdLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCCmCdMgmt fSC002 = (FSCCmCdMgmt) lookupFunctionUnit(FSCCmCdMgmt.class);
			// 2. FM 호출
			responseData = fSC002.fInqCmCdLst(requestData, onlineCtx);
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
	 * 공통코드저장
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_CM_CD_LIST
	 *		- field : CM_GRP_CD_ID [공통그룹코드ID]
	 *		- field : CM_CD_ID [공통코드ID]
	 *		- field : CM_CD_NM [공통코드명]
	 *		- field : CM_CD_DESC [공통코드설명]
	 *		- field : CM_CD_SORT_ORD [공통코드정렬순서]
	 *		- field : USE_YN [사용여부]
	 *		- field : SUP_CM_GRP_CD_ID [상위공통그룹코드ID]
	 *		- field : SUP_CM_CD_ID [상위공통코드ID]
	 *		- field : MGMT_ITEM_CD1 [관리항목코드1]
	 *		- field : MGMT_ITEM_CD2 [관리항목코드2]
	 *		- field : MGMT_ITEM_CD3 [관리항목코드3]
	 *		- field : MGMT_ITEM_CD4 [관리항목코드4]
	 *		- field : MGMT_ITEM_CD5 [관리항목코드5]
	 *		- field : MGMT_ITEM_CD6 [관리항목코드6]
	 *		- field : MGMT_ITEM_CD7 [관리항목코드7]
	 *		- field : MGMT_ITEM_CD8 [관리항목코드8]
	 *		- field : MGMT_ITEM_CD9 [관리항목코드9]
	 *		- field : MGMT_ITEM_CD10 [관리항목코드10]
	 *		- field : CM_CD_ID_OLD [필드1]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveCmCd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);
		try {
			// 1. FU lookup
			FSCCmCdMgmt fSC002 = (FSCCmCdMgmt) lookupFunctionUnit(FSCCmCdMgmt.class);
			// 2. 입력 RS설정
			requestData.putFieldMap(requestData.getRecordSet("RS_CM_CD_LIST").getRecordMap(0));
			requestData.putField("USERNO", ca.getUserNo());
			// 3. 레코드셋 상태에 따른 분기
			if ( "I".equals(ca.getTrnPtrnDvcd()) ) { // INSERT
				fSC002.fRegCmCd(requestData, onlineCtx);
			} else if ( "U".equals(ca.getTrnPtrnDvcd()) ) { // UPDATE
				fSC002.fUpdCmCd(requestData, onlineCtx);
			}
			// 4. 결과값 리턴
			responseData.setOkResultMessage("DMS00000", null);//정상 처리되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

	/**
	 * 공통코드조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CM_GRP_CD_ID [필드1]
	 *	- field : FLAG [필드2]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : OUTPUT1
	 *		- field : CM_GRP_CD_ID [필드1]
	 *		- field : CM_CD_ID [필드2]
	 *		- field : CM_CD_NM [필드3]
	 *		- field : CM_CD_DESC [필드4]
	 *		- field : CM_CD_SORT_ORD [필드5]
	 *		- field : SUP_CM_GRP_CD_ID [필드6]
	 *		- field : SUP_CM_CD_ID [필드7]
	 *		- field : MGMT_ITEM_CD1 [필드8]
	 *		- field : MGMT_ITEM_CD2 [필드9]
	 *		- field : MGMT_ITEM_CD3 [필드1]
	 *		- field : MGMT_ITEM_CD4 [필드2]
	 *		- field : MGMT_ITEM_CD5 [필드3]
	 *		- field : MGMT_ITEM_CD6 [필드4]
	 *		- field : MGMT_ITEM_CD7 [필드5]
	 *		- field : MGMT_ITEM_CD8 [필드6]
	 *		- field : MGMT_ITEM_CD9 [필드7]
	 *		- field : MGMT_ITEM_CD10 [필드1]
	 *	- record : OUTPUT2
	 *		- field : CM_GRP_CD_ID [필드1]
	 *		- field : CM_CD_ID [필드2]
	 *		- field : CM_CD_NM [필드3]
	 *		- field : CM_CD_DESC [필드4]
	 *		- field : CM_CD_SORT_ORD [필드5]
	 *		- field : SUP_CM_GRP_CD_ID [필드6]
	 *		- field : SUP_CM_CD_ID [필드7]
	 *		- field : MGMT_ITEM_CD1 [필드8]
	 *		- field : MGMT_ITEM_CD2 [필드9]
	 *		- field : MGMT_ITEM_CD3 [필드1]
	 *		- field : MGMT_ITEM_CD4 [필드2]
	 *		- field : MGMT_ITEM_CD5 [필드3]
	 *		- field : MGMT_ITEM_CD6 [필드4]
	 *		- field : MGMT_ITEM_CD7 [필드5]
	 *		- field : MGMT_ITEM_CD8 [필드6]
	 *		- field : MGMT_ITEM_CD9 [필드7]
	 *		- field : MGMT_ITEM_CD10 [필드1]
	 *	- record : OUTPUT3
	 *		- field : CM_GRP_CD_ID [필드1]
	 *		- field : CM_CD_ID [필드2]
	 *		- field : CM_CD_NM [필드3]
	 *		- field : CM_CD_DESC [필드4]
	 *		- field : CM_CD_SORT_ORD [필드5]
	 *		- field : SUP_CM_GRP_CD_ID [필드6]
	 *		- field : SUP_CM_CD_ID [필드7]
	 *		- field : MGMT_ITEM_CD1 [필드8]
	 *		- field : MGMT_ITEM_CD2 [필드9]
	 *		- field : MGMT_ITEM_CD3 [필드1]
	 *		- field : MGMT_ITEM_CD4 [필드2]
	 *		- field : MGMT_ITEM_CD5 [필드3]
	 *		- field : MGMT_ITEM_CD6 [필드4]
	 *		- field : MGMT_ITEM_CD7 [필드5]
	 *		- field : MGMT_ITEM_CD8 [필드6]
	 *		- field : MGMT_ITEM_CD9 [필드7]
	 *		- field : MGMT_ITEM_CD10 [필드1]
	 *	- record : OUTPUT4
	 *		- field : CM_GRP_CD_ID [필드1]
	 *		- field : CM_CD_ID [필드2]
	 *		- field : CM_CD_NM [필드3]
	 *		- field : CM_CD_DESC [필드4]
	 *		- field : CM_CD_SORT_ORD [필드5]
	 *		- field : SUP_CM_GRP_CD_ID [필드6]
	 *		- field : SUP_CM_CD_ID [필드7]
	 *		- field : MGMT_ITEM_CD1 [필드8]
	 *		- field : MGMT_ITEM_CD2 [필드9]
	 *		- field : MGMT_ITEM_CD3 [필드1]
	 *		- field : MGMT_ITEM_CD4 [필드2]
	 *		- field : MGMT_ITEM_CD5 [필드3]
	 *		- field : MGMT_ITEM_CD6 [필드4]
	 *		- field : MGMT_ITEM_CD7 [필드5]
	 *		- field : MGMT_ITEM_CD8 [필드6]
	 *		- field : MGMT_ITEM_CD9 [필드7]
	 *		- field : MGMT_ITEM_CD10 [필드1]
	 *	- record : OUTPUT5
	 *		- field : CM_GRP_CD_ID [필드1]
	 *		- field : CM_CD_ID [필드2]
	 *		- field : CM_CD_NM [필드3]
	 *		- field : CM_CD_DESC [필드4]
	 *		- field : CM_CD_SORT_ORD [필드5]
	 *		- field : SUP_CM_GRP_CD_ID [필드6]
	 *		- field : SUP_CM_CD_ID [필드7]
	 *		- field : MGMT_ITEM_CD1 [필드8]
	 *		- field : MGMT_ITEM_CD2 [필드9]
	 *		- field : MGMT_ITEM_CD3 [필드1]
	 *		- field : MGMT_ITEM_CD4 [필드2]
	 *		- field : MGMT_ITEM_CD5 [필드3]
	 *		- field : MGMT_ITEM_CD6 [필드4]
	 *		- field : MGMT_ITEM_CD7 [필드5]
	 *		- field : MGMT_ITEM_CD8 [필드6]
	 *		- field : MGMT_ITEM_CD9 [필드7]
	 *		- field : MGMT_ITEM_CD10 [필드1]
	 *	- record : OUTPUT6
	 *		- field : CM_GRP_CD_ID [필드1]
	 *		- field : CM_CD_ID [필드2]
	 *		- field : CM_CD_NM [필드3]
	 *		- field : CM_CD_DESC [필드4]
	 *		- field : CM_CD_SORT_ORD [필드5]
	 *		- field : SUP_CM_GRP_CD_ID [필드6]
	 *		- field : SUP_CM_CD_ID [필드7]
	 *		- field : MGMT_ITEM_CD1 [필드8]
	 *		- field : MGMT_ITEM_CD2 [필드9]
	 *		- field : MGMT_ITEM_CD3 [필드1]
	 *		- field : MGMT_ITEM_CD4 [필드2]
	 *		- field : MGMT_ITEM_CD5 [필드3]
	 *		- field : MGMT_ITEM_CD6 [필드4]
	 *		- field : MGMT_ITEM_CD7 [필드5]
	 *		- field : MGMT_ITEM_CD8 [필드6]
	 *		- field : MGMT_ITEM_CD9 [필드7]
	 *		- field : MGMT_ITEM_CD10 [필드1]
	 *	- record : OUTPUT7
	 *		- field : CM_GRP_CD_ID [필드1]
	 *		- field : CM_CD_ID [필드2]
	 *		- field : CM_CD_NM [필드3]
	 *		- field : CM_CD_DESC [필드4]
	 *		- field : CM_CD_SORT_ORD [필드5]
	 *		- field : SUP_CM_GRP_CD_ID [필드6]
	 *		- field : SUP_CM_CD_ID [필드7]
	 *		- field : MGMT_ITEM_CD1 [필드8]
	 *		- field : MGMT_ITEM_CD2 [필드9]
	 *		- field : MGMT_ITEM_CD3 [필드1]
	 *		- field : MGMT_ITEM_CD4 [필드2]
	 *		- field : MGMT_ITEM_CD5 [필드3]
	 *		- field : MGMT_ITEM_CD6 [필드4]
	 *		- field : MGMT_ITEM_CD7 [필드5]
	 *		- field : MGMT_ITEM_CD8 [필드6]
	 *		- field : MGMT_ITEM_CD9 [필드7]
	 *		- field : MGMT_ITEM_CD10 [필드1]
	 *	- record : OUTPUT8
	 *		- field : CM_GRP_CD_ID [필드1]
	 *		- field : CM_CD_ID [필드2]
	 *		- field : CM_CD_NM [필드3]
	 *		- field : CM_CD_DESC [필드4]
	 *		- field : CM_CD_SORT_ORD [필드5]
	 *		- field : SUP_CM_GRP_CD_ID [필드6]
	 *		- field : SUP_CM_CD_ID [필드7]
	 *		- field : MGMT_ITEM_CD1 [필드8]
	 *		- field : MGMT_ITEM_CD2 [필드9]
	 *		- field : MGMT_ITEM_CD3 [필드1]
	 *		- field : MGMT_ITEM_CD4 [필드2]
	 *		- field : MGMT_ITEM_CD5 [필드3]
	 *		- field : MGMT_ITEM_CD6 [필드4]
	 *		- field : MGMT_ITEM_CD7 [필드5]
	 *		- field : MGMT_ITEM_CD8 [필드6]
	 *		- field : MGMT_ITEM_CD9 [필드7]
	 *		- field : MGMT_ITEM_CD10 [필드1]
	 *	- record : OUTPUT9
	 *		- field : CM_GRP_CD_ID [필드1]
	 *		- field : CM_CD_ID [필드2]
	 *		- field : CM_CD_NM [필드3]
	 *		- field : CM_CD_DESC [필드4]
	 *		- field : CM_CD_SORT_ORD [필드5]
	 *		- field : SUP_CM_GRP_CD_ID [필드6]
	 *		- field : SUP_CM_CD_ID [필드7]
	 *		- field : MGMT_ITEM_CD1 [필드8]
	 *		- field : MGMT_ITEM_CD2 [필드9]
	 *		- field : MGMT_ITEM_CD3 [필드1]
	 *		- field : MGMT_ITEM_CD4 [필드2]
	 *		- field : MGMT_ITEM_CD5 [필드3]
	 *		- field : MGMT_ITEM_CD6 [필드4]
	 *		- field : MGMT_ITEM_CD7 [필드5]
	 *		- field : MGMT_ITEM_CD8 [필드6]
	 *		- field : MGMT_ITEM_CD9 [필드7]
	 *		- field : MGMT_ITEM_CD10 [필드1]
	 *	- record : OUTPUT10
	 *		- field : CM_GRP_CD_ID [필드1]
	 *		- field : CM_CD_ID [필드2]
	 *		- field : CM_CD_NM [필드3]
	 *		- field : CM_CD_DESC [필드4]
	 *		- field : CM_CD_SORT_ORD [필드5]
	 *		- field : SUP_CM_GRP_CD_ID [필드6]
	 *		- field : SUP_CM_CD_ID [필드7]
	 *		- field : MGMT_ITEM_CD1 [필드8]
	 *		- field : MGMT_ITEM_CD2 [필드9]
	 *		- field : MGMT_ITEM_CD3 [필드1]
	 *		- field : MGMT_ITEM_CD4 [필드2]
	 *		- field : MGMT_ITEM_CD5 [필드3]
	 *		- field : MGMT_ITEM_CD6 [필드4]
	 *		- field : MGMT_ITEM_CD7 [필드5]
	 *		- field : MGMT_ITEM_CD8 [필드6]
	 *		- field : MGMT_ITEM_CD9 [필드7]
	 *		- field : MGMT_ITEM_CD10 [필드1]
	 * </pre>
	 */
	public IDataSet pInqCmCd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();

		try {
			// 1. 변수 선언 초기화
			String[] arrGrpCdId = StringUtils.nvl(requestData.getField("CM_GRP_CD_ID"), "").split(",");
			String[] arrFlag = StringUtils.nvl(requestData.getField("FLAG"), "").split(",");

			String cmmnCodeTyCd = null;
			String flag = null;
			IRecordSet rs = null;
			// 2. 공통코드 조회
			for ( int i = 0 ; i < arrGrpCdId.length ; i++ ) {
				cmmnCodeTyCd = arrGrpCdId[i];
				//그룹코드 NULL일경우 exception
				if ( StringUtils.isEmpty(cmmnCodeTyCd) )
					throw new BizRuntimeException("DMS00009");
				flag = i < arrFlag.length ? arrFlag[i] : null;
				rs = _convertListToRecordSet(cmmnCodeTyCd, "OUTPUT" + (i + 1), flag);
				responseData.putRecordSet(rs);
			}
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
	 * 공통코드 RS로 변환
	 *
	 * @author
	 *
	 * @param cmmnCodeTyCd 공통그룹코드
	 * @param rsNm   resultset이름
	 * @param flag   추가레코드플래그
	 * @return 처리결과 IRecordSet 객체
	 */
	private static IRecordSet _convertListToRecordSet(String cmmnCodeTyCd, String rsNm, String flag) {

		List<HpcCode> lsCode = new ArrayList<HpcCode>(HpcUtils.getCodes(cmmnCodeTyCd));

		//플래그에 따라 0번째 row 셋팅
		if ( StringUtils.equals("S", flag) || StringUtils.equals("A", flag) ) {
			HpcCode hCd = new HpcCode();
			String lblChoice = "선택";
			String lblAll = "전체";
			//hCd = lsCode.get(0);
			hCd.setGroupCdId(cmmnCodeTyCd);
			hCd.setCodeId(StringUtils.equals("A", flag) ? "%" : "");
			hCd.setCodeNm(StringUtils.equals("S", flag) ? lblChoice : lblAll);
			hCd.setCodeDesc("0");
			lsCode.add(0, hCd);
		}

		IRecordSet rsCmmnCode = new RecordSet(rsNm);

		//레코드 헤더 생성
		List<IRecordHeader> rhList = new ArrayList<IRecordHeader>();
		rhList.add(new RecordHeader("CM_GRP_CD_ID"));
		rhList.add(new RecordHeader("CM_CD_ID"));
		rhList.add(new RecordHeader("CM_CD_NM"));
		rhList.add(new RecordHeader("CM_CD_DESC"));
		rhList.add(new RecordHeader("CM_CD_SORT_ORD"));
		rhList.add(new RecordHeader("SUP_CM_GRP_CD_ID"));
		rhList.add(new RecordHeader("SUP_CM_CD_ID"));
		rhList.add(new RecordHeader("MGMT_ITEM_CD1"));
		rhList.add(new RecordHeader("MGMT_ITEM_CD2"));
		rhList.add(new RecordHeader("MGMT_ITEM_CD3"));
		rhList.add(new RecordHeader("MGMT_ITEM_CD4"));
		rhList.add(new RecordHeader("MGMT_ITEM_CD5"));
		rhList.add(new RecordHeader("MGMT_ITEM_CD6"));
		rhList.add(new RecordHeader("MGMT_ITEM_CD7"));
		rhList.add(new RecordHeader("MGMT_ITEM_CD8"));
		rhList.add(new RecordHeader("MGMT_ITEM_CD9"));
		rhList.add(new RecordHeader("MGMT_ITEM_CD10"));
		rsCmmnCode.addHeaders(rhList);

		IRecord record = null;

		for ( HpcCode hpcCd : lsCode ) {
			record = rsCmmnCode.newRecord();
			_setCodeRecord(record, hpcCd);
		}

		return rsCmmnCode;
	}

	/**
	 * 레코드셋 셋팅
	 *
	 * @author
	 *
	 * @param record 레코드
	 * @param hpcCd  공통코드  객체
	 * @return 
	 */
	private static void _setCodeRecord(IRecord record, HpcCode hpcCd) {
		record.set("CM_GRP_CD_ID", hpcCd.getGroupCdId());
		record.set("CM_CD_ID", hpcCd.getCodeId());
		record.set("CM_CD_NM", hpcCd.getCodeNm());
		record.set("CM_CD_DESC", hpcCd.getCodeDesc());
		record.set("CM_CD_SORT_ORD", hpcCd.getSortOrd());
		record.set("SUP_CM_GRP_CD_ID", hpcCd.getSupCmGrpCdId());
		record.set("SUP_CM_CD_ID", hpcCd.getSupCmCdId());
		record.set("MGMT_ITEM_CD1", hpcCd.getMgmtItemCd1());
		record.set("MGMT_ITEM_CD2", hpcCd.getMgmtItemCd2());
		record.set("MGMT_ITEM_CD3", hpcCd.getMgmtItemCd3());
		record.set("MGMT_ITEM_CD4", hpcCd.getMgmtItemCd4());
		record.set("MGMT_ITEM_CD5", hpcCd.getMgmtItemCd5());
		record.set("MGMT_ITEM_CD6", hpcCd.getMgmtItemCd6());
		record.set("MGMT_ITEM_CD7", hpcCd.getMgmtItemCd7());
		record.set("MGMT_ITEM_CD8", hpcCd.getMgmtItemCd8());
		record.set("MGMT_ITEM_CD9", hpcCd.getMgmtItemCd9());
		record.set("MGMT_ITEM_CD10", hpcCd.getMgmtItemCd10());
	}

	/**
	 * [PU 공통코드관리] 공통코드 검색
	 *
	 * @author 심상준 (simair)
	 * @since 2014-07-25 15:56:13
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CM_GRP_CD_ID [공통그룹코드ID]
	 *	- field : CM_GRP_CD_NM [공통그룹코드명]
	 *	- field : CM_CD_ID [공통코드ID]
	 *	- field : CM_CD_NM [공통코드명]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_SUP_CM_CD_LIST
	 *		- field : CM_GRP_CD_ID [공통그룹코드ID]
	 *		- field : CM_GRP_CD_NM [공통그룹코드명]
	 *		- field : CM_CD_ID [공통코드ID]
	 *		- field : CM_CD_NM [공통코드명]
	 *		- field : CM_CD_DESC [공통코드설명]
	 *		- field : MGMT_ITEM_CD1 [관리항목코드1]
	 *		- field : MGMT_ITEM_CD2 [관리항목코드2]
	 *		- field : MGMT_ITEM_CD3 [관리항목코드3]
	 *		- field : MGMT_ITEM_CD4 [관리항목코드4]
	 *		- field : MGMT_ITEM_CD5 [관리항목코드5]
	 *		- field : MGMT_ITEM_CD6 [관리항목코드6]
	 *		- field : MGMT_ITEM_CD7 [관리항목코드7]
	 *		- field : MGMT_ITEM_CD8 [관리항목코드8]
	 *		- field : MGMT_ITEM_CD9 [관리항목코드9]
	 *		- field : MGMT_ITEM_CD10 [관리항목코드10]
	 * </pre>
	 */
	public IDataSet pSrchCmCd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCCmCdMgmt fSC002 = (FSCCmCdMgmt) lookupFunctionUnit(FSCCmCdMgmt.class);
			// 2. FM 호출
			responseData = fSC002.fSrchCmCd(requestData, onlineCtx);
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
	 * 공통코드반영
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pAplyCmCd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		HpcUtils.codeRefresh();
		return responseData;
	}

	/**
	 * 공통그룹코드목록조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CM_GRP_CD_ID [공통그룹코드ID]
	 *	- field : CM_GRP_CD_NM [공통그룹코드명]
	 *	- field : USE_YN [사용여부]
	 *	- field : MGMT_CD_YN [관리여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_CM_GRP_CD_LIST
	 *		- field : CM_GRP_CD_ID [공통그룹코드ID]
	 *		- field : CM_GRP_CD_NM [공통그룹코드명]
	 *		- field : CM_GRP_CD_DESC [공통그룹코드설명]
	 *		- field : USE_YN [사용여부]
	 *		- field : MGMT_CD_YN [관리여부]
	 *		- field : MGMT_ITEM_NM1 [관리항목명1]
	 *		- field : MGMT_ITEM_NM2 [관리항목명2]
	 *		- field : MGMT_ITEM_NM3 [관리항목명3]
	 *		- field : MGMT_ITEM_NM4 [관리항목명4]
	 *		- field : MGMT_ITEM_NM5 [관리항목명5]
	 *		- field : MGMT_ITEM_NM6 [관리항목명6]
	 *		- field : MGMT_ITEM_NM7 [관리항목명7]
	 *		- field : MGMT_ITEM_NM8 [관리항목명8]
	 *		- field : MGMT_ITEM_NM9 [관리항목명9]
	 *		- field : MGMT_ITEM_NM10 [관리항목명10]
	 *		- field : LS_CHG_USER_ID [최종등록사용자ID]
	 *		- field : LS_CHG_DTM [최종등록일시]
	 * </pre>
	 */
	public IDataSet pInqCmGrpCdLst(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCCmGrpCdMgmt fSC001 = (FSCCmGrpCdMgmt) lookupFunctionUnit(FSCCmGrpCdMgmt.class);
			// 2. FM 호출
			responseData = fSC001.fInqCmGrpCdLst(requestData, onlineCtx);
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
	 * 공통그룹코드저장
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_CM_GRP_CD_LIST
	 *		- field : CM_GRP_CD_ID [공통그룹코드ID]
	 *		- field : CM_GRP_CD_NM [공통그룹코드명]
	 *		- field : CM_GRP_CD_DESC [공통그룹코드설명]
	 *		- field : USE_YN [사용여부]
	 *		- field : MGMT_CD_YN [관리여부]
	 *		- field : MGMT_ITEM_NM1 [관리항목명1]
	 *		- field : MGMT_ITEM_NM2 [관리항목명2]
	 *		- field : MGMT_ITEM_NM3 [관리항목명3]
	 *		- field : MGMT_ITEM_NM4 [관리항목명4]
	 *		- field : MGMT_ITEM_NM5 [관리항목명5]
	 *		- field : MGMT_ITEM_NM6 [관리항목명6]
	 *		- field : MGMT_ITEM_NM7 [관리항목명7]
	 *		- field : MGMT_ITEM_NM8 [관리항목명8]
	 *		- field : MGMT_ITEM_NM9 [관리항목명9]
	 *		- field : MGMT_ITEM_NM10 [관리항목명10]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSaveCmGrpCd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		CommonArea ca = getCommonArea(onlineCtx);
		try {
			// 1. FU lookup
			FSCCmGrpCdMgmt fSC001 = (FSCCmGrpCdMgmt) lookupFunctionUnit(FSCCmGrpCdMgmt.class);
			// 2. 입력 RS설정
			requestData.putFieldMap(requestData.getRecordSet("RS_CM_GRP_CD_LIST").getRecordMap(0));
			requestData.putField("USERNO", ca.getUserNo());
			// 3. 레코드셋 상태에 따른 분기
			if ( "I".equals(ca.getTrnPtrnDvcd()) ) { // INSERT	
				fSC001.fRegCmGrpCd(requestData, onlineCtx);
			} else if ( "U".equals(ca.getTrnPtrnDvcd()) ) { // UPDATE
				fSC001.fUpdCmGrpCd(requestData, onlineCtx);
			}
			// 4. 결과값 리턴
			responseData.setOkResultMessage("DMS00000", null);//정상 처리되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

}
