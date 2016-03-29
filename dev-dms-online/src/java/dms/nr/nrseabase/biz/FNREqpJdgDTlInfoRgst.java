package dms.nr.nrseabase.biz;

import java.util.Map;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.common.CommonArea;
import fwk.constants.DmsConstants;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]단말기감정내역등록</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-12 20:42:24</li>
 * <li>작성자 : 김혁범 (kezmain1)</li>
 * </ul>
 *
 * @author 김혁범 (kezmain1)
 */
public class FNREqpJdgDTlInfoRgst extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNREqpJdgDTlInfoRgst(){
		super();
	}

	/**
	 *단말기감정내역등록조회
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-12 20:44:43
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqReqpJdgDtlRgstLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        IDataSet dsCnt = new DataSet();
        IRecordSet rsList = null;
        int intTotalCnt = 0;
        try {
            // 1. DU lookup
        	DNREqpJdgDTlInfoRgst dNREqpJdgDTlInfoRgst = (DNREqpJdgDTlInfoRgst) lookupDataUnit(DNREqpJdgDTlInfoRgst.class);
            // 2. LISTDM호출
            responseData = dNREqpJdgDTlInfoRgst.dSReqpJdgDtlRgstLst(requestData, onlineCtx);
            
            //단말기감정내역관리 감정데이터 호출
			IDataSet dsRtn2 = dNREqpJdgDTlInfoRgst.dSReqpJdgDtlRgstLst2(requestData, onlineCtx);
			responseData.putRecordSet(dsRtn2.getRecordSet("RS_REQP_JDG_LIST2"));
			
			 //감정내역등록조회시 IF_신규_단말기평가정보등록테이블에 존재하는지 여부 검사
			IDataSet dsRtn3 = dNREqpJdgDTlInfoRgst.dSEqpEvalInfoRegYn(requestData, onlineCtx);
			responseData.putRecordSet(dsRtn3.getRecordSet("RS_EQP_EVAL_INFO_REG_YN"));
    
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }        
	
	    return responseData;
	}

	/**
	 *단말기감정내역등록
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-12 20:42:24
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegReqpJdgRgst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    IRecordSet rs = requestData.getRecordSet("RS_REQP_JDG_LIST");
	    IDataSet paramData = new DataSet();
        IRecordSet upFileListRs = null;
        String fileNo = "";
        CommonArea ca = getCommonArea(onlineCtx);
        requestData.putField("USER_NO", ca.getUserNo());
	
	    try {
	    	
	    	upFileListRs = requestData.getRecordSet(DmsConstants.UPLOAD_FILE_LIST);

    		if(upFileListRs.getRecordCount() == 0) {
    			requestData.putField("UPLD_FILE_NO", "-1");
    		} else {
    			//파일업로드 DB등록 FM호출
    			IDataSet fileInfo = callSharedBizComponentByDirect("sc.SCSBase", "fRegUpFileInfo", requestData, onlineCtx);
    			requestData.putField("UPLD_FILE_NO", fileInfo.getField("UPLD_FILE_NO"));
    			fileNo = fileInfo.getField("UPLD_FILE_NO");
    		}
    		
    		requestData.putField("userNo", ca.getUserNo());
    		requestData.putField("UPLD_FILE_NO", fileNo);
    		
    		IRecord ir = null;
    		
    		DNREqpJdgDTlInfoRgst dNREqpJdgDTlInfoRgst = (DNREqpJdgDTlInfoRgst) lookupDataUnit(DNREqpJdgDTlInfoRgst.class);
    		
    		// 마스터등록
    		responseData = dNREqpJdgDTlInfoRgst.dUReqpJdgRgst(requestData, onlineCtx); //감정저장
            responseData = dNREqpJdgDTlInfoRgst.dUEqpAssetRgst(requestData, onlineCtx); //자산테이블:재고상태업데이트('40'감정완료)
            responseData = dNREqpJdgDTlInfoRgst.dIRentalDcp(requestData, onlineCtx); //NR_렌탈손해배상금테이블 업데이트
    		
            // 디테일 등록
            IDataSet paramMap = new DataSet();
    		for(int i=0; i<rs.getRecordCount(); i++){
    			ir = rs.getRecord(i);
				Map paramMap1 = rs.getRecordMap(i);
				paramMap1.put("ASSET_NO", requestData.getField("ASSET_NO"));
				paramMap1.put("CNTRT_NO", requestData.getField("CNTRT_NO"));
				paramMap1.put("EQP_JDG_SEQ", requestData.getField("EQP_JDG_SEQ"));
				paramMap1.put("EQP_IN_DT", requestData.getField("EQP_IN_DT"));
				paramMap1.put("DCP_NO", requestData.getField("DCP_NO"));
				paramMap1.put("PEN", requestData.getField("EQP_CMP_REVIS_AMT"));
				paramMap1.put("userNo",ca.getUserNo());
				
				
				paramMap.putFieldMap(paramMap1);
				
				/*
				if("0".equals(requestData.getField("EQP_CMP_REVIS_AMT") ) ){ //보정금액이 '0'으로 저장시 
					responseData = dNREqpJdgDTlInfoRgst.dUReqpJdgRgstDtlZero(paramMap, onlineCtx);  //감정상세저장
				}else{
					responseData = dNREqpJdgDTlInfoRgst.dUReqpJdgRgstDtl(paramMap, onlineCtx);  //감정상세저장
				}
				*/
				
				responseData = dNREqpJdgDTlInfoRgst.dUReqpJdgRgstDtl(paramMap, onlineCtx);  //감정상세저장
    		}
    		if("01".equals(requestData.getField("EQP_JDG_RSLT_CD") ) ){
    			responseData = dNREqpJdgDTlInfoRgst.dDRentalDcp(requestData, onlineCtx); //파손->정상 감정시 NR_렌탈손해배상금테이블 데이터 삭제	
    		}
        } catch ( BizRuntimeException e ) {
            throw e;
        } 
	
	    return responseData;
	}

	/**
	 *단말기감정내역상세등록
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-12 20:45:56
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fRegReqpJdgRgstDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
            // 1. DU lookup
	    	DNREqpJdgDTlInfoRgst dNREqpJdgDTlInfoRgst = (DNREqpJdgDTlInfoRgst) lookupDataUnit(DNREqpJdgDTlInfoRgst.class);
            
            responseData = dNREqpJdgDTlInfoRgst.dUReqpJdgRgstDtl(requestData, onlineCtx);       
    
        } catch ( BizRuntimeException e ) {
            throw e;
        } 
	
	    return responseData;
	}
  
}
