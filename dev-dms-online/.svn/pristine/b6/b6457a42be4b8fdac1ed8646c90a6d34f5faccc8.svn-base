package dms.nr.nrseabase.biz;

import java.util.HashMap;
import java.util.Map;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.RecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [DU]단말기감정내역등록</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-12 20:54:01</li>
 * <li>작성자 : 김혁범 (kezmain1)</li>
 * </ul>
 *
 * @author 김혁범 (kezmain1)
 */
public class DNREqpJdgDTlInfoRgst extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DNREqpJdgDTlInfoRgst(){
		super();
	}

	/**
	 *단말기 감정내역등록
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-12 20:54:34
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUReqpJdgRgst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();

	    // 1.쿼리문 호출
        dbUpdate("UReqpJdgRgst", requestData, onlineCtx);
        
	    return responseData;
	}

	/**
	 *단말기 감정내역상세등록
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-12 20:54:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUReqpJdgRgstDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
        dbUpdate("UReqpJdgRgstDtl", requestData, onlineCtx);
	
	    return responseData;
	}

	/**
	 *단말기 감정내역등록조회
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-12 20:55:17
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSReqpJdgDtlRgstLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
	    /*감정내역등록조회*/
        IRecordSet rsReturn = dbSelect("SReqpJdgDtlRgstLst", requestData, onlineCtx);        
        responseData.putRecordSet("RS_REQP_JDG_LIST", rsReturn);
     
        /*감정내역등록조회시 익일영업일구하기계산로직*/
        IRecordSet rs = dbSelect("SReqpJdgDtlHolyDt", requestData, onlineCtx);
	    responseData.putRecordSet("RS_REQP_JDG_HOLY_DT", rs);
	    requestData.putField("HOLY_DT", rs.getRecord(0));
	    
	    /*파손감정중 아이클라우드체크시 로직처리*/
	    IRecordSet rs2 = dbSelect("SReqpJdgDtlUseDt", requestData, onlineCtx);
	    responseData.putRecordSet("RS_REQP_JDG_USE_DT", rs2);
	    
	    /*파손감정중 변상금액Max한도계산 로직처리*/
	    IRecordSet rs3 = dbSelect("SReqpJdgDtlUseDt2", requestData, onlineCtx);
	    responseData.putRecordSet("RS_REQP_JDG_USE_DT2", rs3);
	    
	    return responseData;
	}

	/**
	 *자산 재고상태업데이트(감정코드->40(감정완료)로 업데이트)
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-19 14:46:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUEqpAssetRgst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    /*단말기 감정완료시(정상,파손) 자산테이블 재고상태코드 '감정완료'로 상태값 업데이트*/
        dbUpdate("UEqpAssetRgst",requestData,onlineCtx);
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-09-09 17:02:45
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIRentalDcp(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    /*단말기 감정완료시(파손) 손해배상금정책테이블에 해당계약건 등록(정상감정시 삭제)*/
        dbInsert("IRentalDcp",requestData,onlineCtx); 
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-09-22 15:34:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSReqpJdgDtlRgstLst2(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    /*감정결과,변상금액,보정금액,최초감정일 조회로직*/
        IRecordSet rsReturn = dbSelect("SReqpJdgDtlRgstLst2", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_REQP_JDG_LIST2", rsReturn);
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-09-25 09:31:44
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDRentalDcp(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
        dbDelete("DRentalDcp",requestData,onlineCtx);
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-11-24 18:26:55
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUReqpJdgRgstDtlZero(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	 // 1.쿼리문 호출
        dbUpdate("UReqpJdgRgstDtlZero", requestData, onlineCtx);
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-12-02 16:54:00
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpEvalInfoRegYn(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    /*IF_신규_단말기평가정보등록테이블에 존재여부 확인*/
        IRecordSet rsReturn = dbSelect("SEqpEvalInfoRegYn", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_EQP_EVAL_INFO_REG_YN", rsReturn);
	
	    return responseData;
	}
  
}
