package fwk.channel;

import java.util.HashMap;
import java.util.Map;

import nexcore.framework.core.data.IOnlineContext;

import com.tobesoft.xplatform.data.VariableList;

import fwk.constants.DmsConstants;
import fwk.flat.FlatHeaderSpec;
import fwk.utils.HpcUtils;

public class XplatformHeaderHelper {

    public static  Map<String, String> toXpfHeaderMap(IOnlineContext onlineCtx, VariableList xpVars) {
        Map<String, String> attributes = new HashMap<String, String>();
        
        attributes.put(FlatHeaderSpec.WHL_MESG_LEN.name(),"0" );  // 전체전문길이
        attributes.put(FlatHeaderSpec.STND_HDR_LEN.name(),"0" );  // 표준헤더부길이
        
        // 거래번호
        attributes.put(FlatHeaderSpec.GLOB_ID.name(), onlineCtx.getTransaction().getRequestId());  // 글로벌 ID
        attributes.put(FlatHeaderSpec.PRGS_SRNO.name(),"0" );  // 진행일련번호
        
        // 전송시스템정보내용
        attributes.put(FlatHeaderSpec.IPAD.name(), onlineCtx.getChannel().getRequestSystemXd());  // IP주소
        attributes.put(FlatHeaderSpec.PRCM_MAC.name(),  xpVars.getString(XplatformHeaderSpec.PRCM_MAC.xpfName()));  // PC MAC주소
        attributes.put(FlatHeaderSpec.TRN_TRNM_NO.name(),  xpVars.getString(XplatformHeaderSpec.TRN_TRNM_NO.xpfName()));  
        attributes.put(FlatHeaderSpec.SSO_SESN_KEY.name(),  xpVars.getString(XplatformHeaderSpec.SSO_SESN_KEY.xpfName()));
        attributes.put(FlatHeaderSpec.FRST_TRNM_CHNL_CD.name(),  DmsConstants.XFLAT_CHN_CD);//최초전송채널코드
        attributes.put(FlatHeaderSpec.TRNM_CHNL_CD.name(),  DmsConstants.XFLAT_CHN_CD);//전송채널코드
        attributes.put(FlatHeaderSpec.TRNM_NODE_NO.name(),  HpcUtils.getWasNodeNo()+"");//WAS노드번호
        attributes.put(FlatHeaderSpec.MCI_TRNM_NODE_NO.name(),  "0");//MCI노드번호
        attributes.put(FlatHeaderSpec.ENV_DVCD.name(),  xpVars.getString(XplatformHeaderSpec.ENV_DVCD.xpfName()));//환경구분코드
        
        //VOC 채널연계로 인한 항목추가
        attributes.put(XplatformHeaderSpec.REQ_BRND_CD.name(),  xpVars.getString(XplatformHeaderSpec.REQ_BRND_CD.xpfName()));//요청브랜드코드
        attributes.put(XplatformHeaderSpec.REQ_CHNL_CD.name(),  xpVars.getString(XplatformHeaderSpec.REQ_CHNL_CD.xpfName()));//UI요청채널코드
        attributes.put(XplatformHeaderSpec.IS_BCK_OFFICE.name(),  xpVars.getString(XplatformHeaderSpec.IS_BCK_OFFICE.xpfName()));//백오피스여부
        //전문처리내용
        attributes.put(FlatHeaderSpec.MESG_DMND_DTTM.name(),  xpVars.getString(XplatformHeaderSpec.MESG_DMND_DTTM.xpfName())); // 전문요청일시
        attributes.put(FlatHeaderSpec.MESG_VRSN_DVCD.name(),  xpVars.getString(XplatformHeaderSpec.MESG_VRSN_DVCD.xpfName())); // 전문버전구분코드
        attributes.put(FlatHeaderSpec.TRN_CD.name(),  xpVars.getString(XplatformHeaderSpec.TRN_CD.xpfName())); // 전문요청일시
        attributes.put(FlatHeaderSpec.SCRN_NO.name(),  xpVars.getString(XplatformHeaderSpec.SCRN_NO.xpfName())); // 화면번호
        attributes.put(FlatHeaderSpec.MESG_RESP_DTTM.name(), ""); // 전문응답일시
        attributes.put(FlatHeaderSpec.TRN_PTRN_DVCD.name(),  xpVars.getString(XplatformHeaderSpec.TRN_PTRN_DVCD.xpfName())); // 거래유형구부코드
        
        // FLAG정보
        attributes.put(FlatHeaderSpec.MESG_DVCD.name(), "");// 전문구분코드
        attributes.put(FlatHeaderSpec.MESG_TYCD.name(), ""); // 전문유형코드
        attributes.put(FlatHeaderSpec.MESG_CNTY_SRNO.name(), ""); // 전문연속일련번호
        attributes.put(FlatHeaderSpec.TRTM_RSLT_CD.name(), ""); // 처리결과코드
        attributes.put(FlatHeaderSpec.CMPG_RELM_USE_DVCD.name(), ""); // 캠페인영역사용구분코드
        
        //직원정보내용
        attributes.put(FlatHeaderSpec.COMP_CD.name(), xpVars.getString(XplatformHeaderSpec.COMP_CD.xpfName())); 
        attributes.put(FlatHeaderSpec.DEPT_CD.name(), xpVars.getString(XplatformHeaderSpec.DEPT_CD.xpfName())); 
        attributes.put(FlatHeaderSpec.BR_CD.name(), xpVars.getString(XplatformHeaderSpec.BR_CD.xpfName())); 
        attributes.put(FlatHeaderSpec.USER_NO.name(), xpVars.getString(XplatformHeaderSpec.USER_NO.xpfName())); 
        attributes.put(FlatHeaderSpec.USER_LOCALE.name(), xpVars.getString(XplatformHeaderSpec.USER_LOCALE.xpfName())); 
        attributes.put(FlatHeaderSpec.CTI_YN.name(), xpVars.getString(XplatformHeaderSpec.CTI_YN.xpfName())); 

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
//      attributes.put(FlatHeaderSpec.EAI_GLOB_ID.name(), ""); //EAI Global ID
//      attributes.put(FlatHeaderSpec.EAI_INTF_ID.name(), ""); //EAI 인터페이스ID
//      attributes.put(FlatHeaderSpec.EAI_RECV_SVCID.name(), ""); //EAI 결과수신서비스ID
        
        // FILLER
        attributes.put(FlatHeaderSpec.SPR_CHRS_CNTN.name(), ""); //포인트금액

        return attributes;
    }
}
