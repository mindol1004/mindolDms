package fwk.outbound.push;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.util.PaddableDataOutputStream;
import fwk.common.TrtmRsltMsg;
import fwk.common.internal.ImplFlatHeader;
import fwk.flat.FlatHeaderHelper;
import fwk.flat.FlatHeaderSpec;
import fwk.outbound.tcpip.OutboundTCPConnection;

/**
 * 배치 스케줄러에서 단말로 푸시하기 위한 기능을 제공한다.
 * 
 * @author 차지환
 * 
 */
public final class OutboundPusher {

	private String host;
	private int port;

	private int connectTimeout = 10000;
	private int blockTimeout = 31000;

	public OutboundPusher(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public void setBlockTimeout(int blockTimeout) {
		this.blockTimeout = blockTimeout;
	}

	public void push(byte[] requestBytes) throws IOException {
		push(requestBytes, 0, requestBytes.length);
	}
	
	public void push(byte[] requestBytes, int offset, int length) throws IOException {
		OutboundTCPConnection conn = null;
		try {
			conn = new OutboundTCPConnection();
			conn.setConnectTimeout(connectTimeout);
			conn.setBlockTimeout(blockTimeout);
			//			conn.setDefaultReadTimeout(readTimeout); // 싱크일때만 의미 있음.

			conn.setHostName(host);
			conn.setPort(port);
			conn.connect();

			conn.send(requestBytes, offset, length);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Map<String, String> headers = new HashMap<String, String>();
		
		headers.put(FlatHeaderSpec.IPAD.name(), "127.0.0.1"); // 아이피

		headers.put(FlatHeaderSpec.TRNM_CHNL_CD.name(), "MCI"); // 전송채널코드
		headers.put(FlatHeaderSpec.TRNM_NODE_NO.name(), "01"); // 전송노드번호
		headers.put(FlatHeaderSpec.MCI_TRNM_NODE_NO.name(), "01"); // MCI 전송노드번호

		headers.put(FlatHeaderSpec.MESG_DVCD.name(), "P"); // 전문구분코드 (Q:요청', 'R:응답')
		headers.put(FlatHeaderSpec.MESG_TYCD.name(), "M"); // 전문유형코드

		ImplFlatHeader entity = FlatHeaderHelper.toHeader(headers, new ImplFlatHeader());

		entity.addMsg(new TrtmRsltMsg("00000000", "테스트메시지."));
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		PaddableDataOutputStream out = new PaddableDataOutputStream(bout, "ms949");
		
		
		FlatHeaderHelper.toStream(entity, (IResultMessage) null, 0, out);

		// userdata write
//		out.write(userData);
		out.writeStringWithLPadding("@@", 2, (byte) 0x20);

		out.flush();
		bout.close();

		byte[] bytes = bout.toByteArray();
		
		OutboundPusher op = new OutboundPusher("127.0.0.1", 40001);
		op.push(bytes);
	}
}
