package fwk.channel;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.util.BaseUtils;
import nexcore.framework.core.util.DateUtils;
import fwk.constants.DmsConstants;
import fwk.flat.FlatHeaderSpec;
import fwk.utils.HpcUtils;

public class JsonHeaderHelper {

    public static Map<String, String> toJsonHeaderMap(IOnlineContext onlineCtx, JSONObject rootJson) {
        Map<String, String> attributes = new HashMap<String, String>();
        
        attributes.put(FlatHeaderSpec.WHL_MESG_LEN.name(),"0" );  // 전체전문길이
        attributes.put(FlatHeaderSpec.STND_HDR_LEN.name(),"0" );  // 표준헤더부길이
        
        attributes.put(FlatHeaderSpec.GLOB_ID.name(),onlineCtx.getTransaction().getRequestId());
        attributes.put(FlatHeaderSpec.PRGS_SRNO.name(),"0" );  // 진행일련번호
        
     // 전송시스템정보내용
        attributes.put(FlatHeaderSpec.IPAD.name(), rootJson.getString(XplatformHeaderSpec.IPAD.xpfName()));
        attributes.put(FlatHeaderSpec.PRCM_MAC.name(), rootJson.getString(XplatformHeaderSpec.PRCM_MAC.xpfName()));
        attributes.put(FlatHeaderSpec.TRN_TRNM_NO.name(),  "0");  
        attributes.put(FlatHeaderSpec.SSO_SESN_KEY.name(),  "0");
        attributes.put(FlatHeaderSpec.FRST_TRNM_CHNL_CD.name(),  DmsConstants.VOC_CHN_CD);//최초전송채널코드
        attributes.put(FlatHeaderSpec.TRNM_CHNL_CD.name(),  DmsConstants.VOC_CHN_CD);//전송채널코드
        attributes.put(FlatHeaderSpec.TRNM_NODE_NO.name(),  HpcUtils.getWasNodeNo()+"");//WAS노드번호
        attributes.put(FlatHeaderSpec.MCI_TRNM_NODE_NO.name(),  "0");//MCI노드번호
        attributes.put(FlatHeaderSpec.ENV_DVCD.name(),  rootJson.getString(XplatformHeaderSpec.ENV_DVCD.xpfName()));//환경구분코드
        
      //처리내용
        attributes.put(FlatHeaderSpec.MESG_DMND_DTTM.name(),  DateUtils.dateToString(onlineCtx.getTransaction().getStartTime(), "yyyyMMddHHmmssSSS")); // 전문요청일시
        attributes.put(FlatHeaderSpec.MESG_VRSN_DVCD.name(),  ""); // 전문버전구분코드
        attributes.put(FlatHeaderSpec.TRN_CD.name(),  onlineCtx.getTransaction().getTxId()); //거래코드
        attributes.put(FlatHeaderSpec.SCRN_NO.name(),  ""); // 화면번호
        attributes.put(FlatHeaderSpec.MESG_RESP_DTTM.name(), ""); // 전문응답일시
        attributes.put(FlatHeaderSpec.TRN_PTRN_DVCD.name(),  ""); // 거래유형구부코드
        
     // FLAG정보
        attributes.put(FlatHeaderSpec.MESG_DVCD.name(), "");// 전문구분코드
        attributes.put(FlatHeaderSpec.MESG_TYCD.name(), ""); // 전문유형코드
        attributes.put(FlatHeaderSpec.MESG_CNTY_SRNO.name(), ""); // 전문연속일련번호
        attributes.put(FlatHeaderSpec.TRTM_RSLT_CD.name(), ""); // 처리결과코드
        attributes.put(FlatHeaderSpec.CMPG_RELM_USE_DVCD.name(), ""); // 캠페인영역사용구분코드
        
        //직원정보내용
        attributes.put(FlatHeaderSpec.COMP_CD.name(), ""); 
        attributes.put(FlatHeaderSpec.DEPT_CD.name(), ""); 
        attributes.put(FlatHeaderSpec.BR_CD.name(), ""); 
        attributes.put(FlatHeaderSpec.USER_NO.name(), rootJson.getString(XplatformHeaderSpec.USER_NO.xpfName())); 
        attributes.put(FlatHeaderSpec.USER_LOCALE.name(), BaseUtils.getDefaultLocale().toString()); 
        attributes.put(FlatHeaderSpec.CTI_YN.name(), "N"); 

        //시제정보
        attributes.put(FlatHeaderSpec.CSHN_OCRN_YN.name(), ""); // 시제발생여부
        attributes.put(FlatHeaderSpec.CASH_AMT.name(), "");// 현금금액
        attributes.put(FlatHeaderSpec.POINT_AMT.name(), ""); //포인트금액
        
        //채널거래정보
        attributes.put(FlatHeaderSpec.XTIS_CD.name(), ""); // 대외기관코드
        attributes.put(FlatHeaderSpec.BZWR_SVR_CD.name(), ""); // 업무서버코드
        attributes.put(FlatHeaderSpec.OTSD_MESG_CD.name(), "");  // 대외전문코드
        attributes.put(FlatHeaderSpec.OTSD_MESG_TRTM_CD.name(), "");  //대외전문처리코드
        attributes.put(FlatHeaderSpec.OTSD_TRN_UNQ_NO.name(), ""); // 대외거래고유번호
        attributes.put(FlatHeaderSpec.OTSD_RESP_TRN_CD.name(), ""); //대외응답거래코드
        attributes.put(FlatHeaderSpec.CHNL_MSG_CD.name(), ""); //채널메시지코드
        return attributes;
    }
}
