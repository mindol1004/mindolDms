package dms.sc.scbbase.biz;

import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;

/**
 * <ul>
 * <li>업무 그룹명 : HPC/서비스공통</li>
 * <li>단위업무명: PSCNetworkMgmt</li>
 * <li>설  명 : 통신망관리를 담당하는 PU</li>
 * <li>작성일 : 2014-12-02 17:05:44</li>
 * <li>작성자 : 권용하 (kyh)</li>
 * </ul>
 *
 * @author 권용하 (kyh)
 */
public class PSCNetworkMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PSCNetworkMgmt() {
		super();
	}

	/**
	 * 통신망관리를 담당하는 PM
	 *
	 * @author 권용하 (kyh)
	 * @since 2014-12-02 17:05:44
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : INST_CD [기관코드]
	 *	- field : TLGM_NO [전문번호]
	 *	- field : TLGM_CHNL_CD [전문채널코드]
	 *	- field : TRS_DT [전송일자]
	 *	- field : TRS_TM [전송시각]
	 *	- field : TRAC_NO [추적번호]
	 *	- field : RPS_CD [응답코드]
	 *	- field : DATA_LEN [데이터길이]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : INST_CD [기관코드]
	 *	- field : TLGM_NO [전문번호]
	 *	- field : TLGM_CHNL_CD [전문채널코드]
	 *	- field : TRS_DT [전송일자]
	 *	- field : TRS_TM [전송시각]
	 *	- field : TRAC_NO [추적번호]
	 *	- field : RPS_CD [응답코드]
	 *	- field : DATA_LEN [데이터길이]
	 * </pre>
	 */
	public IDataSet pNetworkMgmt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = (IDataSet) requestData.clone();
		try {

			responseData.putField("RPS_CD", "00");

		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 구통신망관리 PM
	 *
	 * @author 권용하 (kyh)
	 * @since 2014-12-02 17:05:44
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : TLGM_NO [전문번호]
	 *	- field : INST_CD [기관코드]
	 *	- field : TRS_DT [전송일자]
	 *	- field : TRS_TM [전송시각]
	 *	- field : TRAC_NO [추적번호]
	 *	- field : TLGM_CL [전문구분]
	 *	- field : RPS_CD [응답코드]
	 *	- field : DATA_LEN [데이터길이]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : TLGM_NO [전문번호]
	 *	- field : INST_CD [기관코드]
	 *	- field : TRS_DT [전송일자]
	 *	- field : TRS_TM [전송시각]
	 *	- field : TRAC_NO [추적번호]
	 *	- field : TLGM_CL [전문구분]
	 *	- field : RPS_CD [응답코드]
	 *	- field : DATA_LEN [데이터길이]
	 * </pre>
	 */
	public IDataSet pNetworkMgmtOld(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = (IDataSet) requestData.clone();
		try {
			responseData.putField("RPS_CD", "00");
			responseData.putField("RPS_MSG_CTT", "정상처리되었습니다");
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * 제휴사통신망관리
	 *
	 * @author 권용하 (kyh)
	 * @since 2015-01-09 15:20:28
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : RCGN_KEY [제휴사식별키]
	 *	- field : INST_CD [기관코드]
	 *	- field : TLGM_NO [전문번호]
	 *	- field : TLGM_CHNL_CD [전문채널코드]
	 *	- field : TRS_DT [전송일자]
	 *	- field : TRS_TM [전송시각]
	 *	- field : TRAC_NO [추적번호]
	 *	- field : TLGM_CL_CD [전문구분코드]
	 *	- field : RPS_CD [응답코드]
	 *	- field : RPS_MSG_CTT [응답메시지내용]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : RCGN_KEY [제휴사식별키]
	 *	- field : INST_CD [기관코드]
	 *	- field : TLGM_NO [전문번호]
	 *	- field : TLGM_CHNL_CD [전문채널코드]
	 *	- field : TRS_DT [전송일자]
	 *	- field : TRS_TM [전송시각]
	 *	- field : TRAC_NO [추적번호]
	 *	- field : TLGM_CL_CD [전문구분코드]
	 *	- field : RPS_CD [응답코드]
	 *	- field : RPS_MSG_CTT [응답메시지내용]
	 * </pre>
	 */
	public IDataSet pCoNetworkMgmt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = (IDataSet) requestData.clone();
		try {
			responseData.putField("RPS_CD", "00");
			responseData.putField("RPS_MSG_CTT", "정상처리되었습니다");
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

}
