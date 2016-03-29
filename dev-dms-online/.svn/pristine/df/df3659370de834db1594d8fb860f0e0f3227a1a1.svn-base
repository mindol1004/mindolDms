package dms.sc.scbbase.biz;

import java.util.Map;

import fwk.utils.HpcUtils;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: PSCZipMgmt</li>
 * <li>설  명 : 우편번호관리</li>
 * <li>작성일 : 2014-08-21 15:08:00</li>
 * <li>작성자 : 심상준 (simair)</li>
 * </ul>
 *
 * @author 심상준 (simair)
 */
public class PSCZipMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PSCZipMgmt() {
		super();
	}

	/**
	 * 우편번호조회
	 *
	 * @author 이유미 (leeyoumee)
	 * @since 2014-08-21 15:08:00
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : BLGR_BDIO_NM [건물명]
	 *	- field : EUMD_NM [읍면동명]
	 *	- field : CTPB_NM [시군구명]
	 *	- field : CTAP_NM [시도명]
	 *	- field : ROAD_NM [도로명]
	 *	- field : LTNO_MANO_INFO [지번]
	 *	- field : LTNO_SBNO_INFO [지번상세]
	 *	- field : BDIO_MANO_CTT [건물본번]
	 *	- field : BDIO_SBNO_CTT [건물부번]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_ADDR_LIST
	 *		- field : POST_NO [필드1]
	 *		- field : ADDR1 [필드2]
	 *		- field : ADDR3 [필드3]
	 *		- field : ADDR2 [필드4]
	 * </pre>
	 */
	public IDataSet pInqZip(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCZipMgmt fSCZipMgmt = (FSCZipMgmt) lookupFunctionUnit(FSCZipMgmt.class);
			// 2. FM 호출
			responseData = fSCZipMgmt.fInqZip(requestData, onlineCtx);
			// responseData.putRecordSet("RS_ADDR_LIST", responseData.getRecordSet("rsAddrList"));
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
	 * 우편번호조회(시/도)
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_ADDR_DIV
	 *		- field : CTAP_NM [시도명]
	 * </pre>
	 */
	public IDataSet pInqZipDiv(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCZipMgmt fSCZipMgmt = (FSCZipMgmt) lookupFunctionUnit(FSCZipMgmt.class);
			// 2. FM 호출
			responseData = fSCZipMgmt.fInqZipDiv(requestData, onlineCtx);
			//responseData.putRecordSet("RS_ADDR_DIV", responseData.getRecordSet("rsAddrDiv"));
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
	 * 우편번호(시/군/구)조회
	 *
	 * @author
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CTAP_NM [필드1]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_ADDR_DTL
	 *		- field : CTPB_NM [시군구명]
	 * </pre>
	 */
	public IDataSet pInqZipDivDtl(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. FU lookup
			FSCZipMgmt fSCZipMgmt = (FSCZipMgmt) lookupFunctionUnit(FSCZipMgmt.class);
			// 2. FM 호출
			responseData = fSCZipMgmt.fInqZipDivDtl(requestData, onlineCtx);
			// responseData.putRecordSet("RS_ADDR_DTL", responseData.getRecordSet("rsAddrDtl"));
			// 3. 결과값 리턴
			responseData.setOkResultMessage("DMS00001", null); //정상 조회되었습니다.
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); //시스템 오류
		}
		return responseData;
	}

}
