package fwk.channel.web;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import nexcore.framework.core.ServiceConstants;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordHeader;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ITransaction;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.data.user.IUserInfo;
import nexcore.framework.core.ioc.ComponentRegistry;
import nexcore.framework.core.log.LogManager;
import nexcore.framework.core.message.IMessage;
import nexcore.framework.core.message.IMessageManager;
import nexcore.framework.core.service.front.DefaultFrontDataLog;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.online.channel.core.IRequestContext;
import nexcore.framework.online.channel.core.IResponseContext;
import nexcore.framework.online.channel.core.RenderException;
import nexcore.framework.online.channel.web.XPlatformView;

import org.apache.commons.logging.Log;

import com.tobesoft.xplatform.data.DataTypes;
import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import com.tobesoft.xplatform.tx.HttpPlatformResponse;
import com.tobesoft.xplatform.tx.PlatformException;
import com.tobesoft.xplatform.tx.PlatformType;

import fwk.channel.XplatformHeaderSpec;
import fwk.common.CommonArea;
import fwk.common.CommonUtils;
import fwk.utils.HpcUtils;
import fwk.utils.PagenateUtils;

/**
 * NEXCORE에서 제공하는 View인 XplatformView와 큰 차이는 없으나 
 * Xplatform용으로 데이터 전환하는 부분을 메소드로 분리하고 
 * 기타 AMS용 파라미터를 함께 넣어주기 위해 상속받아 처리함. 
 * @author 박세일
 *
 */
public class HpcXplatformView extends XPlatformView {

	private Log logger = LogManager.getFwkLog();
	private DefaultFrontDataLog frontDataLog;
	
	public void setFrontDataLog(DefaultFrontDataLog frontDataLog) {
		this.frontDataLog = frontDataLog;
	}
	
	@Override
	public void render(IRequestContext requestCtx, IResponseContext responseCtx) throws RenderException {
		
		HttpServletResponse httpResp = (HttpServletResponse)responseCtx.getWriteProtocol();
		IDataSet resp = (IDataSet)responseCtx.getBizData();
		ITransaction tr = requestCtx.getOnlineContext().getTransaction();
		IOnlineContext onlineCtx = responseCtx.getOnlineContext();
		IUserInfo userInfo = (IUserInfo)onlineCtx.getUserInfo();
		PlatformData xpData = new PlatformData();
		VariableList xpVars = xpData.getVariableList();
		HttpPlatformResponse xpResp = null;// request 의 타입을 판단하기 위해서 사용. (XML, BINARY, BINARY COMPRESS)
		try{
            // BINARY, XML 여부에 따라서 Response 를 생성한다.
            if(PlatformType.CONTENT_TYPE_BINARY.equals(requestCtx.getOnlineContext().getAttribute("CONTENT_TYPE"))) {  // BINARY Response 생성 
                xpResp = new HttpPlatformResponse(httpResp, PlatformType.CONTENT_TYPE_BINARY, PlatformType.DEFAULT_CHAR_SET);
            } else {// XML Response 생성
                xpResp = new HttpPlatformResponse(httpResp, PlatformType.CONTENT_TYPE_XML, PlatformType.DEFAULT_CHAR_SET);
            }
            // COMPRESS Protocol Type을 추가한다.
            if(PlatformType.PROTOCOL_TYPE_ZLIB.equals(requestCtx.getOnlineContext().getAttribute("PROTOCOL_TYPE"))) {// BINARY Response 생성
                xpResp.addProtocolType(PlatformType.PROTOCOL_TYPE_ZLIB);
            } 
            //CommonArea에 저장되어 있는 연동정보를 Xplatform VariableList에 담는다
            convertHeaderToXpfVariableList(onlineCtx, xpVars);//sets form variables
            
            IResultMessage resultMsg = (IResultMessage)(resp.getResultMessage());
            if(resultMsg == null){
                resultMsg = new ResultMessage(IResultMessage.FAIL, 
                        "No message is set in the response dataset from server.", null);
            }
            
            //@fixme the following code should be enhanced to use dependency injection.
            IMessageManager msgMgr = (IMessageManager)(ComponentRegistry.lookup(ServiceConstants.MESSAGE));
            IMessage msg = msgMgr.getMessage(resultMsg.getMessageId(), requestCtx.getOnlineContext().getUserInfo().getLocale());
            String msgStr = msg.getName(resultMsg.getMessageParams());
            
            xpVars.add(this.getMsgIdVarName(), resultMsg.getMessageId());
            xpVars.add(this.getMsgVarName(), msgStr);
            
            //DataSet을 XPlatform parameter 양식으로 변환함.
            if(resultMsg.getStatus() == IResultMessage.OK){
                xpVars.add(this.getMsgFlagVarName(), "OK");
                xpVars.add(ERR_CODE_VARIABLE_NAME, "0");
                xpVars.add(ERR_MSG_VARIABLE_NAME, "OK");
                convertDataSetToXplatformDs(resp, xpData, onlineCtx);
            } else{ // in case of failure at server application
                xpVars.add(this.getMsgFlagVarName(), "ERROR");
                xpVars.add(ERR_CODE_VARIABLE_NAME, "-1");
                xpVars.add(ERR_MSG_VARIABLE_NAME, "ERROR");
            }
                        
            xpResp.setData(xpData);
            xpResp.sendData();
        }catch(Exception ex){
            if(ex instanceof RuntimeException) {
                xpVars.add(this.getMsgIdVarName(), "SKFE5009");
                xpVars.add(this.getMsgVarName(), ex.getMessage());
                xpVars.add(this.getMsgFlagVarName(), "ERROR");
                xpVars.add(ERR_CODE_VARIABLE_NAME, "-1");
                xpVars.add(ERR_MSG_VARIABLE_NAME, "ERROR");
                xpResp.setData(xpData);
                try {
                    xpResp.sendData();
                } catch (PlatformException e) {
                    logger.error("Exception at writing and sending response data", ex);
                    throw new RenderException("SKFS1011", new String[] {ex.getLocalizedMessage()}, e);
                }
            } else {
                logger.error("Exception at writing and sending response data", ex);
                throw new RenderException("SKFS1011", new String[] {ex.getLocalizedMessage()}, ex);
            }
        }
	}
	
	/**
	 * DataSet을 XPlatform 양식으로 변환하기 위한 작업을 실시.
	 * @param resultMsg
	 * @param resp
	 * @param xpData
	 * @param xpVars
	 */
	protected void convertDataSetToXplatformDs(IDataSet resp, PlatformData xpData, IOnlineContext onlineCtx) {
		Set keys = null;
		String txId = onlineCtx.getTransaction().getTxId();
        String globId = CommonUtils.getCommonArea(onlineCtx).getGlobId();
		com.tobesoft.xplatform.data.DataSet xpDataset = null;
		try {
    		//convert field-map.
    		Map fields = resp.getFieldMap();
    		if(fields != null && fields.size() > 0){
    			keys = fields.keySet();
    			xpDataset = new com.tobesoft.xplatform.data.DataSet(this.getLinearDatasetName());
    			byte []byteArrClass = {0x00};
    		    String blobName = byteArrClass.getClass().getName();
    			for(Object key: keys){ //builds column headers for XPlatform dataset
    				Object value = fields.get((String)key);
    				if(value == null || !blobName.equals(value.getClass().getName())){
    					xpDataset.addColumn((String)key, DataTypes.STRING, 255);
    				}
    				else {
    					xpDataset.addColumn((String)key, DataTypes.BLOB, 255);
    				}
    			}
    			
    			int no = xpDataset.newRow(); //this can be non-zero ?
    			for(Object key: keys){ //appends a single row for XPaltform dataset
    				Object value = fields.get((String)key);
    				if(value == null || !blobName.equals(value.getClass().getName())){
    					xpDataset.set(no, (String)key, value);
    				}
    				else {
    					xpDataset.set(no, (String)key, (byte[])value);
    				}
    			}
    			xpData.addDataSet(xpDataset);
    		}
    		
    		//convert record-sets one after another
    		Iterator recordSetIds = resp.getRecordSetIds();
    		String recordSetId = null;
    		IRecordSet recordSet = null;
    		IRecord record = null;
    		IRecordHeader header = null;
    		while(recordSetIds.hasNext()){ //for each record-set
    			recordSetId = (String)(recordSetIds.next());
    			
    			recordSet = resp.getRecordSet(recordSetId);
    			xpDataset = new com.tobesoft.xplatform.data.DataSet(recordSetId);
    			
    			//builds headers for XPlatform dataset
    			for(int i = 0, n = recordSet.getHeaderCount(); i < n; i++){
    				header = recordSet.getHeader(i);
    				
    				xpDataset.addColumn(header.getName(), 
    						this.getXPlatformDataType(header.getType()),
    						this.getXPatformDataSize(header.getType()));
    			}
    //			if(recordSet.getTotalRecordCount()!= 0 && recordSet.getPageNo()>=1 ) {
    //				xpDataset.addColumn(PagenateUtils.PAGE_NO, this.getXPlatformDataType(DataTypes.INT), this.getXPatformDataSize(Types.INTEGER));
    //				xpDataset.addColumn(PagenateUtils.RC_COUNT_PER_PAGE,this.getXPlatformDataType(DataTypes.INT), this.getXPatformDataSize(Types.INTEGER));
    //				xpDataset.addColumn(PagenateUtils.TOT_RCD_CNT, this.getXPlatformDataType(DataTypes.INT), this.getXPatformDataSize(Types.INTEGER));
    //			}
    			
    			//builds rows for XPlatform dataset
    			for(int i = 0, n = recordSet.getRecordCount(); i < n; i++){ //for each record
    				record = recordSet.getRecord(i);
    				
    				int no = xpDataset.newRow();
    				for(int j = 0, m = recordSet.getHeaderCount(); j < m; j++){ //for each column
    					header = recordSet.getHeader(j);
    					
    					if(this.getXPlatformDataType(header.getType()) != DataTypes.BLOB)
    					{
    						xpDataset.set(no, header.getName(), record.get(j));
    					}
    					else
    					{
    						xpDataset.set(no, header.getName(), record.getByteArray(j));
    					}
    				}
    			}
    			if(recordSet.getTotalRecordCount()!= 0 && recordSet.getPageNo()>=1 ) {
    //				xpDataset.set(0, PagenateUtils.PAGE_NO, recordSet.getPageNo());
    //				xpDataset.set(0, PagenateUtils.RC_COUNT_PER_PAGE, recordSet.getRecordCountPerPage());
    //				xpDataset.set(0, PagenateUtils.TOT_RCD_CNT, recordSet.getTotalRecordCount());
    				/**
    				 * 심상준과장의 요청에 따라 grid type의 return아닌 Argument type의 return이 되도록 변경함. 2014.08.20 by PSI
    				 * 아래의 내용은 RecordSet일 경우에만 취득되므로 별도 Method로 빼지 않고 해당 메소드에서 처리함.   
    				 */
    				xpData.getVariableList().add(PagenateUtils.PAGE_NO, recordSet.getPageNo());
    				xpData.getVariableList().add(PagenateUtils.RC_COUNT_PER_PAGE, recordSet.getRecordCountPerPage());
    				xpData.getVariableList().add(PagenateUtils.TOT_RCD_CNT, recordSet.getTotalRecordCount());//Xplatform Argument에도 전체건수 입력
    				
    			} else {
    			    xpData.getVariableList().add(PagenateUtils.TOT_RCD_CNT, 0);//Xplatform Argument에도 전체건수 입력
    			}
    			xpData.addDataSet(xpDataset);
    		}
    	} catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("[" + globId  + "] ["+ txId + "] Exception on parsing request data.", e);
            }
            throw new RuntimeException("SKFE5009", e);
        }
	}
	
	/**
	 * CommonArea에 담겨 있는 거래정보를 VariableList에 담는다. 
	 * @param onlineCtx
	 * @param xpVars
	 */
	protected void convertHeaderToXpfVariableList(IOnlineContext onlineCtx, VariableList xpVars) {
		CommonArea ca = CommonUtils.getCommonArea(onlineCtx);
		ITransaction tr = onlineCtx.getTransaction();
		xpVars.add(this.getTrIdVarName(), tr.getTxId());
		xpVars.add(this.getStartDateVarName(), tr.getStartTime());
		xpVars.add(this.getEndDateVarName(), tr.getEndTime());
		xpVars.add(XplatformHeaderSpec.GLOB_ID.xpfName(), ca.getGlobId());
		xpVars.add(XplatformHeaderSpec.IPAD.xpfName(), ca.getIpad());
		xpVars.add(XplatformHeaderSpec.PRCM_MAC.xpfName(), ca.getPrcmMac());
		xpVars.add(XplatformHeaderSpec.TRN_TRNM_NO.xpfName(), ca.getTrnTrnmNo());
		xpVars.add(XplatformHeaderSpec.SSO_SESN_KEY.xpfName(), ca.getSsoSesnKey());
		xpVars.add(XplatformHeaderSpec.ENV_DVCD.xpfName(), ca.getEnvDvcd());
		xpVars.add(XplatformHeaderSpec.MESG_VRSN_DVCD.xpfName(), ca.getMesgVrsnDvcd());
		xpVars.add(XplatformHeaderSpec.SCRN_NO.xpfName(), ca.getScrnNo());
		xpVars.add(XplatformHeaderSpec.TRN_PTRN_DVCD.xpfName(), ca.getTrnPtrnDvcd());
		xpVars.add(XplatformHeaderSpec.COMP_CD.xpfName(), ca.getCompCd());
		xpVars.add(XplatformHeaderSpec.DEPT_CD.xpfName(), ca.getDeptCd());
		xpVars.add(XplatformHeaderSpec.BR_CD.xpfName(), ca.getBrCd());
		xpVars.add(XplatformHeaderSpec.USER_NO.xpfName(), ca.getUserNo());
		xpVars.add(XplatformHeaderSpec.USER_LOCALE.xpfName(), ca.getUserLocale());
		xpVars.add(XplatformHeaderSpec.CTI_YN.xpfName(), ca.getCtiYn());
		xpVars.add(XplatformHeaderSpec.WAS_INSTANCE_ID.xpfName(), BaseUtils.getFwkId());
	}
}
