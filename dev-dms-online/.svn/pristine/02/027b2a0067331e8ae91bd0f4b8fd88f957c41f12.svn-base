package dms.nr.nrseabase.biz;

import java.util.Map;

import org.apache.commons.logging.Log;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [DU]단말제각관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-06 08:56:38</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class DNREqpExcMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DNREqpExcMgmt(){
		super();
	}

	/**
	 * <pre>[DM]단말제각현황조회</pre>
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-08-06 08:56:38
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpExcLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
	  	//IRecordSet rsReturn = dbSelect("SEqpExcLstPaging", requestData, onlineCtx);
	  	IRecordSet rsReturn = dbSelect("SEqpExcLst", requestData, onlineCtx);
	  	// 2.결과값 셋팅
	  	responseData.putRecordSet("RS_EQP_EXC_LIST", rsReturn);
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-08-06 09:11:35
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpExcTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    // 1.쿼리문 호출
	  	IRecordSet rsReturn = dbSelect("SEqpExcTotCnt", requestData, onlineCtx);
	  	// 2.결과값 셋팅
	  	responseData.putRecordSet("RS_SUM_LIST", rsReturn);  
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-30 15:40:25
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUPowerExcController(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    IRecordSet powerExc = requestData.getRecordSet("RS_POWER_EXC_LIST");
	    
	    Map userPlus = null;
	    for(int i=0; i<powerExc.getRecordCount(); i++)
	    {
	    	userPlus = powerExc.getRecordMap(i);
	    	userPlus.put("USER_NO", requestData.getField("USER_NO"));
		    // 1.쿼리문 호출
	    	dbUpdate("UPowerExcController", userPlus, onlineCtx);
	    }
	
	    return responseData;
	}

    /**
	 * <pre>[DM]단말제각현황전체다운로드</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-10-26 11:10:51
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpExcAllExcel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();

        //1. 쿼리문호출
        IRecordSet rsReturn = dbSelect("SEqpExcAllExcel", requestData.getFieldMap(), onlineCtx);
        //IRecordSet rsReturn = dbSelect("SEqpExcAllExcel", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_ALL_EXCEL_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>[DM]단말제각전표처리현황</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-11-06 10:03:43
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpExcSum(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SEqpExcSum", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SUM_LIST", rsReturn);  
        
        return responseData;
    }

    /**
	 * <pre>[DM]제각마감자산처분삭제</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-11-06 13:50:28
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDExcClsAssetDispo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        dbDelete("DExcClsAssetDispo", requestData, onlineCtx);
        return responseData;
    }

    /**
	 * <pre>[DM]제각마감자산처분상세등록</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-08-06 08:56:38
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIExcClsAssetDispoDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        IRecordSet Dspal = requestData.getRecordSet("RS_SLIP_EXC_CLS_ASSET_DISPO_LIST");
        
        Map assetDispo = null;
        for(int i=0; i<Dspal.getRecordCount(); i++)
        {
            assetDispo = Dspal.getRecordMap(i);
            assetDispo.put("LST_DEPR_DT", requestData.getField("LST_DEPR_DT"));
            assetDispo.put("USER_NO", requestData.getField("USER_NO"));
            // 1.쿼리문 호출
            dbInsert("IExcClsAssetDispoDtl", assetDispo, onlineCtx);
        }
    
        return responseData;
    }

    /**
	 * <pre>[DM]제각마감자산처분등록</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-11-06 13:51:09
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIExcClsAssetDispo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
        Log log =getLog(onlineCtx);
        log.debug("dIExcClsAssetDispo() requestData:"+requestData);
        int cnt = dbInsert("IExcClsAssetDispo", requestData, onlineCtx);
        log.debug("dIExcClsAssetDispo() cnt:"+cnt);
        
        return responseData;
    }
  
}
