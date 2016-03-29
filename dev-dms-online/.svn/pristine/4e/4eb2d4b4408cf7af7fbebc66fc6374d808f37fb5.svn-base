package dms.ep.epscsbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]판매등급현황관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-09-03 15:54:12</li>
 * <li>작성자 : 정동현 (jjddhh123)</li>
 * </ul>
 *
 * @author 정동현 (jjddhh123)
 */
public class DEPSellGradeMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPSellGradeMgmt(){
		super();
	}

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-09-03 15:54:12
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSSellGradeListPaging(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecordSet rsReturn = dbSelect("SSellGradeListPaging", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putRecordSet("RS_SALE_GRADE_LST", rsReturn);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-09-03 15:54:12
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSSellGradeListTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SSellGradeListTotCnt", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-09-10 13:56:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSSellGrade(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출     
        IRecordSet rsReturn = dbSelect("SSellGrade", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putRecordSet("RS_SALE_GRADE", rsReturn);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-09-03 15:54:12
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SALE_GRADE
	 *		- field : CNCL_MGMT_NO [상담관리번호]
	 *		- field : SEQ [순번]
	 *		- field : PWR_ST [전원불량]
	 *		- field : USIM_ST [USIM불량]
	 *		- field : RECG_ST [충전불량]
	 *		- field : SBELL_ST [벨소리불량]
	 *		- field : VIBR_ST [진동불량]
	 *		- field : SPK_ST [스피커불량]
	 *		- field : ILRM_SENSOR_ST [조도센서불량]
	 *		- field : GRCP_ST [자이로센서불량]
	 *		- field : APRC_SENSOR_ST [근접센서불량]
	 *		- field : CAMR_ST [카메라불량]
	 *		- field : BATER_ST [B/T불량]
	 *		- field : AFIMG_ST [잔상불량]
	 *		- field : TOUCH_ST [터치불량]
	 *		- field : WIFI_ST [WIFI불량]
	 *		- field : STAIN_ST [얼룩]
	 *		- field : BRUI_ST [멍]
	 *		- field : SQUS_ST [눌림]
	 *		- field : LQFD_INFLO_ST [액상유입]
	 *		- field : LED_ST [LED파손]
	 *		- field : BUTN_ST [버튼불량]
	 *		- field : BOTM_TOUCH_ST [하단터치메뉴]
	 *		- field : BODY_ST [몸체미세휨]
	 *		- field : DMB_ST [DMB안테나손상]
	 *		- field : LCGLS_ST [액정유리]
	 *		- field : CHIP_ST [이나감]
	 *		- field : CAMR_WINDO_ST [카메라윈도우손상]
	 *		- field : EDGE_ST [테두리및몸체]
	 *		- field : BACK_CL [매입불가여부]
	 *		- field : BACK_ST [매입불가]
	 *		- field : SELL_GRADE [판매등급]
	 *		- field : FST_REG_USER_ID [최초 등록 사용자 ID]
	 *		- field : LAST_CHG_USER_ID [최종 변경 사용자 ID]
	 *		- field : PRCH_GRADE [매입등급]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dISellGrade(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        // 1.쿼리문 호출
        dbInsert("ISellGrade", requestData, onlineCtx);
        
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-09-11 10:43:39
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUCnslSellGrade(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        // 1.쿼리문 호출
        dbUpdate("UCnslSellGrade", requestData, onlineCtx);
        
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-09-11 10:45:27
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUPrchSellGrade(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        // 1.쿼리문 호출
	    dbUpdate("UPrchSellGrade", requestData, onlineCtx);
        
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-09-11 10:50:08
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUSellGradeDelYn(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        // 1.쿼리문 호출
        dbUpdate("USellGradeDelYn", requestData, onlineCtx);
        
        return responseData;
    }
  
}
