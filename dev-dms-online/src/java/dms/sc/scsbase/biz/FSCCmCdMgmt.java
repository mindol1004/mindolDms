package dms.sc.scsbase.biz;

import fwk.utils.HpcUtils;
import java.util.ArrayList;
import java.util.List;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.RecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;
import fwk.code.internal.HpcCode;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: FSCCmCdMgmt</li>
 * <li>설  명 : 공통코드관리</li>
 * <li>작성일 : 2014-09-25 19:00:31</li>
 * <li>작성자 : 김석영 (kimsukyoung)</li>
 * </ul>
 *
 * @author 김석영 (kimsukyoung)
 */
public class FSCCmCdMgmt extends fwk.base.FunctionUnit {

	private final String[]	RS_HEADER_NM	= new String[] { "CM_GRP_CD_ID", "CM_GRP_CD_NM", "CM_GRP_CD_DESC", "MGMT_CD_YN", "MGMT_ITEM_NM1", "MGMT_ITEM_NM2",
		"MGMT_ITEM_NM3", "MGMT_ITEM_NM4", "MGMT_ITEM_NM5", "MGMT_ITEM_NM6", "MGMT_ITEM_NM7", "MGMT_ITEM_NM8", "MGMT_ITEM_NM9", "MGMT_ITEM_NM10", "CM_CD_ID",
		"SUP_CM_GRP_CD_ID", "SUP_CM_CD_ID", "CM_CD_NM", "CM_CD_DESC", "CM_CD_SORT_ORD", "MGMT_ITEM_CD1", "MGMT_ITEM_CD2", "MGMT_ITEM_CD3", "MGMT_ITEM_CD4",
		"MGMT_ITEM_CD5", "MGMT_ITEM_CD6", "MGMT_ITEM_CD7", "MGMT_ITEM_CD8", "MGMT_ITEM_CD9", "MGMT_ITEM_CD10" };

	/**
	 * Default Constructor
	 */
	public FSCCmCdMgmt() {
		super();
	}

	/**
	 * 공통코드조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CM_GRP_CD_ID [공통그룹코드ID]
	 *	- field : CM_CD_ID [공통코드ID]
	 *	- field : MGMT_ITEM_CD1 [관리항목코드1]
	 *	- field : MGMT_ITEM_CD2 [관리항목코드2]
	 *	- field : MGMT_ITEM_CD3 [관리항목코드3]
	 *	- field : MGMT_ITEM_CD4 [관리항목코드4]
	 *	- field : MGMT_ITEM_CD5 [관리항목코드5]
	 *	- field : MGMT_ITEM_CD6 [관리항목코드6]
	 *	- field : MGMT_ITEM_CD7 [관리항목코드7]
	 *	- field : MGMT_ITEM_CD8 [관리항목코드8]
	 *	- field : MGMT_ITEM_CD9 [관리항목코드9]
	 *	- field : MGMT_ITEM_CD10 [관리항목코드10]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_CM_CD_LST
	 *		- field : CM_GRP_CD_ID [공통그룹코드ID]
	 *		- field : CM_GRP_CD_NM [공통그룹코드명]
	 *		- field : CM_GRP_CD_DESC [공통그룹코드설명]
	 *		- field : MGMT_CD_YN [관리코드여부]
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
	 *		- field : CM_CD_ID [공통코드ID]
	 *		- field : CM_CD_NM [공통코드명]
	 *		- field : CM_CD_DESC [공통코드설명]
	 *		- field : CM_CD_SORT_ORD [공통코드정렬순서]
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
	 * </pre>
	 */
	public IDataSet fInqCmCd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		//        String grpCdId = requestData.getField("CM_GRP_CD_ID");
		//        String cdId = requestData.getField("CM_CD_ID");
		//        
		//        if (StringUtils.isEmpty(grpCdId)) {
		//            throw new BizRuntimeException("DMS00002", new String[] { HpcUtils.getLangMsg("CM_GRP_CD_ID") }); // 필수입력항목 {0}이/가 누락되었습니다.
		//        }
		//        IRecordSet rs = new RecordSet("RS_CM_CD_LST", RS_HEADER_NM);

		try {
			List<HpcCode> cmCdList = new ArrayList<HpcCode>();
			//승인업무처리시에는 DB를 조회할 수 있도록 cache를 통한 조회 삭제
			//            if(StringUtils.isNotEmpty(cdId)) {
			//                HpcCode hpcCode = HpcUtils.getCode(grpCdId, cdId);
			//                convertHpcCodeToRecordSet(rs.newRecord(), hpcCode);
			//                cmCdList = new ArrayList<HpcCode>();
			//                cmCdList.add(hpcCode);
			//            } else { 
			//                cmCdList = HpcUtils.getCodes(requestData.getField("CM_GRP_CD_ID"));
			//                HpcCode hpcCode = null;
			//                for(int i=0; i<cmCdList.size(); i++ ) {
			//                    hpcCode = cmCdList.get(i);
			//                    convertHpcCodeToRecordSet(rs.newRecord(), hpcCode);
			//                }
			//            }
			//            responseData.putRecordSet("RS_CM_CD_LST", rs);
			responseData.putField("CM_CD_LST", cmCdList);

			DSCCmCdMgmt dSCCmCdMgmt = (DSCCmCdMgmt) lookupDataUnit(DSCCmCdMgmt.class);
			responseData = dSCCmCdMgmt.dSCmCd(requestData, onlineCtx);
			IRecordSet cmCdRs = responseData.getRecordSet("RS_CM_CD_LST");
			HpcCode cmCd = null;
			for ( int i = 0 ; i < cmCdRs.getRecordCount() ; i++ ) {
				cmCd = new HpcCode();
				IRecord record = cmCdRs.getRecord(0);
				cmCd.setGroupCdId(record.get("CM_GRP_CD_ID"));
				cmCd.setGroupCdNm(record.get("CM_GRP_CD_NM"));
				cmCd.setGroupCdDesc(record.get("CM_GRP_CD_DESC"));
				cmCd.setMgmtCdYn(record.get("MGMT_CD_YN"));
				cmCd.setCodeId(record.get("CM_CD_ID"));
				cmCd.setCodeNm(record.get("CM_CD_NM"));
				cmCd.setCodeDesc(record.get("CM_CD_DESC"));
				cmCd.setSortOrd(record.getInt("CM_CD_SORT_ORD"));
				cmCd.setSupCmGrpCdId(record.get("SUP_CM_GRP_CD_ID"));
				cmCd.setSupCmCdId(record.get("SUP_CM_CD_ID"));
				cmCd.setMgmtItemCd1(record.get("MGMT_ITEM_CD1"));
				cmCd.setMgmtItemCd2(record.get("MGMT_ITEM_CD2"));
				cmCd.setMgmtItemCd3(record.get("MGMT_ITEM_CD3"));
				cmCd.setMgmtItemCd4(record.get("MGMT_ITEM_CD4"));
				cmCd.setMgmtItemCd5(record.get("MGMT_ITEM_CD5"));
				cmCd.setMgmtItemCd6(record.get("MGMT_ITEM_CD6"));
				cmCd.setMgmtItemCd7(record.get("MGMT_ITEM_CD7"));
				cmCd.setMgmtItemCd8(record.get("MGMT_ITEM_CD8"));
				cmCd.setMgmtItemCd9(record.get("MGMT_ITEM_CD9"));
				cmCd.setMgmtItemCd10(record.get("MGMT_ITEM_CD10"));
				cmCdList.add(cmCd);
			}

		} catch ( BizRuntimeException e ) {
			throw e;
		}

		return responseData;
	}
}
