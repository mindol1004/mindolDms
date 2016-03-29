package dms.ep.epsbibase.biz;

import java.util.Map;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]바코드매입정보관리</li>
 * <li>설  명 : <pre>[DU]바코드매입정보관리</pre></li>
 * <li>작성일 : 2015-08-26 18:40:10</li>
 * <li>작성자 : 양재석 (jsyang)</li>
 * </ul>
 *
 * @author 양재석 (jsyang)
 */
public class DEPBarCodePrchsInfoMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPBarCodePrchsInfoMgmt(){
		super();
	}

    /**
	 * <pre>바코드매입정보조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-26 18:40:10
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqBarCodePrchsInfoList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInqBarCodePrchsInfoList", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("BARCODE_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>[DM]바코드매입상세조회(구성품)</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-27 09:35:33
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqBarCodePrchsDtlList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInqBarCodePrchsDtlList", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("BARCODE_DTL_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>바코드매입RD데이터조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2015-08-27 11:10:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqBarCodePrchsRDData(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SInqBarCodePrchsRDData", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("BARCODE_RD_LIST", rsReturn);
    
        return responseData;
    }
  
}
