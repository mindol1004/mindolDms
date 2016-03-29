package dms.sc.scbbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;

import nexcore.framework.core.util.StringUtils;

import fwk.utils.PagenateUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/시스템공통</li>
 * <li>단위업무명: 일반게시판관리</li>
 * <li>설  명 : <pre>일반게시판관리</pre></li>
 * <li>작성일 : 2015-07-02 17:32:01</li>
 * <li>작성자 : 박홍서 (uni9401)</li>
 * </ul>
 *
 * @author 박홍서 (uni9401)
 */
public class FSCBrdMgmt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FSCBrdMgmt(){
		super();
	}

	/**
	 * <pre>일반게시판 리스트 조회</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-02 17:39:21
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqBrdLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
		IDataSet dsCnt = new DataSet();
		IRecordSet rsPagingList = null;
		int intTotalCnt = 0;
		
		try {
			// 1. DU lookup
			DSCBrdMgmt dSCBrdMgmt = (DSCBrdMgmt) lookupDataUnit(DSCBrdMgmt.class);
			// 2. TOTAL COUNT DM호출
			dsCnt = dSCBrdMgmt.dSBrdTotCnt(requestData, onlineCtx);
			intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(requestData);
		
			// 3. LISTDM호출
			responseData = dSCBrdMgmt.dSBrdPaging(requestData, onlineCtx);
		
			rsPagingList = responseData.getRecordSet("RS_BRD_LIST");
			PagenateUtils.setPagenatedParamToRecordSet(rsPagingList, requestData, intTotalCnt);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
	    return responseData;
	}

	/**
	 * <pre>일반게시판 입력</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-02 17:40:03
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegBrd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCBrdMgmt dSCBrdMgmt = (DSCBrdMgmt) lookupDataUnit(DSCBrdMgmt.class);
			// 2. 공지사항번호 채번
			IDataSet seqNoDs = dSCBrdMgmt.dSBrdSeq(requestData, onlineCtx);
			requestData.putField("BLTN_BRD_NUM", seqNoDs.getField("BLTN_BRD_NUM"));
			if(StringUtils.isEmpty(requestData.getField("ORG_BLTN_BRD_NUM"))) {
		    	requestData.putField("ORG_BLTN_BRD_NUM", seqNoDs.getField("BLTN_BRD_NUM"));
		    	requestData.putField("SUP_BLTN_BRD_NUM", "-1");
			}
			// 3. 업무 DM호출
			responseData = dSCBrdMgmt.dIBrd(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * <pre>일반게시판 수정</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-02 17:40:31
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdBrd(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCBrdMgmt dSCBrdMgmt = (DSCBrdMgmt) lookupDataUnit(DSCBrdMgmt.class);
			// 3. 업무 DM호출
			responseData = dSCBrdMgmt.dUBrd(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}

	/**
	 * <pre>일반게시판 조회수 증가</pre>
	 *
	 * @author 박홍서 (uni9401)
	 * @since 2015-07-02 17:41:03
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fUpdBrdIncreBrwsCnt(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		try {
			// 1. DU lookup
			DSCBrdMgmt dSCBrdMgmt = (DSCBrdMgmt) lookupDataUnit(DSCBrdMgmt.class);
			// 3. 업무 DM호출
			responseData = dSCBrdMgmt.dUBrdIncreBrwsCnt(requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		}
		return responseData;
	}
  
}
