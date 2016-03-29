package fwk.batch.scheduler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import nexcore.framework.bat.JobExecution;
import nexcore.framework.bat.controller.IJobEndPostProcessor;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.util.PaddableDataOutputStream;
import fwk.common.TrtmRsltMsg;
import fwk.common.internal.ImplFlatHeader;
import fwk.flat.FlatHeaderHelper;
import fwk.flat.FlatHeaderSpec;
import fwk.outbound.push.OutboundPusher;

public class OndemandPushSender implements IJobEndPostProcessor {

	public void init() {
	}
	
	public void destroy() {
	}
	
	public boolean doPostProcess(JobExecution jobexe) {
		try {
			if (jobexe.isOnDemand()) {
				byte[] pushData = makePushData(jobexe);
	
				OutboundPusher op = new OutboundPusher("127.0.0.1", 40001);
				op.push(pushData);
				//2015.10.13 jihooyim code inspector 점검 수정 (02-2.제거되지 않고 남은 디버그 코드(print))
				//System.out.println("온디멘드 결과 PUSH 전송."+new String(pushData));
			}
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	private byte[] makePushData(JobExecution jobexe) throws IOException {

		Map<String, String> headers = new HashMap<String, String>();
		
		headers.put(FlatHeaderSpec.IPAD.name(), "127.0.0.1"); // 아이피

		headers.put(FlatHeaderSpec.TRNM_CHNL_CD.name(), "MCI"); // 전송채널코드
		headers.put(FlatHeaderSpec.TRNM_NODE_NO.name(), "01"); // 전송노드번호
		headers.put(FlatHeaderSpec.MCI_TRNM_NODE_NO.name(), "01"); // MCI 전송노드번호

		headers.put(FlatHeaderSpec.MESG_DVCD.name(), "P"); // 전문구분코드 (Q:요청', 'R:응답')
		headers.put(FlatHeaderSpec.MESG_TYCD.name(), "M"); // 전문유형코드

		ImplFlatHeader entity = FlatHeaderHelper.toHeader(headers, new ImplFlatHeader());

		ByteArrayOutputStream    bout = new ByteArrayOutputStream();
		PaddableDataOutputStream out  = new PaddableDataOutputStream(bout, "ms949");
		
		if (jobexe.getReturnCode() == 0) {
			entity.addMsg(new TrtmRsltMsg("NCOM0001", "배치 처리 정상 완료됐습니다 ["+jobexe.getJobId()+"]"));
		}else {
			entity.addMsg(new TrtmRsltMsg("XYZE0010", "배치 처리 에러입니다 ["+jobexe.getJobId()+"] ("+jobexe.getErrorMsg()+")"));
		}
		
		FlatHeaderHelper.toStream(entity, (IResultMessage) null, 0, out, false);
		out.writeStringWithLPadding("@@", 2, (byte) 0x20);
		
		// 전문 종료 지시자 append
		//out.write('@');
		//out.write('@');
		out.flush();
		
		return bout.toByteArray();
	}
}
