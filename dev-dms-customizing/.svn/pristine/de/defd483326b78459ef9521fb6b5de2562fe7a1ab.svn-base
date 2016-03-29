package fwk.flat;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

import nexcore.framework.core.ServiceConstants;
import nexcore.framework.core.component.IBizComponentMetaDataRegistry;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.ioc.ComponentRegistry;
import nexcore.framework.core.log.LogManager;
import nexcore.framework.core.service.front.IByteArrayMemoryPool;
import nexcore.framework.core.util.BufferReuseByteArrayOutputStream;
import nexcore.framework.core.util.ByteArrayWrap;
import nexcore.framework.core.util.PaddableDataOutputStream;

import org.apache.commons.logging.Log;

import fwk.common.FlatData;
import fwk.common.OutboundEaiHeader;
import fwk.common.OutboundHeader;
import fwk.common.internal.ImplEaiFlatData;
import fwk.common.internal.ImplFlatData;
import fwk.common.internal.ImplFlatHeader;

/**
 * 전문 헬퍼 클래스
 */
public final class FlatHelper {

	private static Log _logger;

	private static IBizComponentMetaDataRegistry cmdRegistry;
	private static IByteArrayMemoryPool byteArrayMemoryPool;
	private static byte[] trailerBytes;
	private static String encoding;
	private static int headerLengthAbout = 400;

	public static String getEncoding() {
		prepare();
		return encoding;
	}

	public static ByteArrayWrap transformTotalByteArray(OutboundHeader header, ByteArrayWrap userByteArrayWrap) {
		prepare();
		byte[] headerBuffer = null;
		try {
			// -------------- HEADER 생성 --------------
			headerBuffer = byteArrayMemoryPool.getByteArray(headerLengthAbout);
			BufferReuseByteArrayOutputStream headerBOut = new BufferReuseByteArrayOutputStream(headerBuffer);
			PaddableDataOutputStream headerPOut = new PaddableDataOutputStream(headerBOut, encoding);
			
			int bodyLength = userByteArrayWrap == null ? 0 : (header.getResultMessage() == null || header.getResultMessage().getStatus() == IResultMessage.OK ? userByteArrayWrap.getLength() : 0);
			FlatHeaderHelper.toStream((ImplFlatHeader)header.getFlatHeader(), header.getResultMessage(), bodyLength, headerPOut);
			int headerLength = headerBOut.size();
			int totalLength = headerLength + bodyLength + (trailerBytes == null ? 0 : trailerBytes.length);
			ByteBuffer bb = ByteBuffer.allocate(totalLength);
			bb.put(headerBOut.getByteArray(), 0, headerBOut.size());
			if (bodyLength > 0) {
				bb.put(userByteArrayWrap.getByteArray(), userByteArrayWrap.getOffset(), bodyLength);
			}

			// tailer 출력. 
			if (trailerBytes != null) {
				// tailer 는 UserData 길이에 포함되지 않는다. 
				// TotalLength에 포함이 필요한 경우는 headerParser에서 tailer 만큼 add 하여 계산해야함.
				bb.put(trailerBytes);
			}
			return new ByteArrayWrap(bb.array());
		} catch (IOException e) {
			throw new RuntimeException("Can not convert Stream", e);
		} finally {
			byteArrayMemoryPool.returnByteArray(headerBuffer);
		}
	}

	public static ByteArrayWrap transformTotalByteArrayForEai(OutboundEaiHeader header, ByteArrayWrap userByteArrayWrap) {
		prepare();
		byte[] headerBuffer = null;
		try {
			// -------------- HEADER 생성 --------------
			headerBuffer = byteArrayMemoryPool.getByteArray(headerLengthAbout);
			BufferReuseByteArrayOutputStream headerBOut = new BufferReuseByteArrayOutputStream(headerBuffer);
			PaddableDataOutputStream headerPOut = new PaddableDataOutputStream(headerBOut, encoding);
			
			int bodyLength = userByteArrayWrap == null ? 0 : ("0".equals(header.getTgrmPrcRsltDcd()) ? userByteArrayWrap.getLength() : 0);
			FlatHeaderHelper.toEaiStream(header.getEaiHeader(),  bodyLength, headerPOut);
			int headerLength = headerBOut.size();
			int totalLength = headerLength + bodyLength + (trailerBytes == null ? 0 : trailerBytes.length);
			ByteBuffer bb = ByteBuffer.allocate(totalLength);
			bb.put(headerBOut.getByteArray(), 0, headerBOut.size());
			if (bodyLength > 0) {
				bb.put(userByteArrayWrap.getByteArray(), userByteArrayWrap.getOffset(), bodyLength);
			}

			// tailer 출력. 
			if (trailerBytes != null) {
				// tailer 는 UserData 길이에 포함되지 않는다. 
				// TotalLength에 포함이 필요한 경우는 headerParser에서 tailer 만큼 add 하여 계산해야함.
				bb.put(trailerBytes);
			}
			return new ByteArrayWrap(bb.array());
		} catch (IOException e) {
			throw new RuntimeException("Can not convert Stream", e);
		} finally {
			byteArrayMemoryPool.returnByteArray(headerBuffer);
		}
	}
	
	public static FlatData transformFlatData(ByteArrayWrap totalByteArrayWrap) {
		prepare();
		byte[] buffer = null;
		try {
			buffer = byteArrayMemoryPool.getByteArray(2048); // 이 버퍼는 필드 하나를 읽기 위해 잠시 사용하는 버퍼이므로 2K 정도면 충분할듯하다.

			ImplFlatData flatData = new ImplFlatData();

			// 업무전문 분석
			ByteArrayWrap bodyByteArrayWrap = FlatHeaderHelper.getBodyByteArrayWrap(totalByteArrayWrap, encoding);
			ByteBuffer bb = ByteBuffer.allocate(bodyByteArrayWrap.getLength());
			bb.put(bodyByteArrayWrap.getByteArray(), bodyByteArrayWrap.getOffset(), bodyByteArrayWrap.getLength());
			flatData.setBodyByteArrayWrap(new ByteArrayWrap(bb.array()));

			// 헤더 분석
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(totalByteArrayWrap.getByteArray(), totalByteArrayWrap.getOffset(), totalByteArrayWrap.getLength()));
			Map<String, String> headers = FlatHeaderHelper.toHeaderMap(in, buffer, encoding);
			flatData.setFlatHeader(FlatHeaderHelper.toHeader(headers, new ImplFlatHeader(), true));

			return flatData;
		} catch (Exception e) {
			throw new RuntimeException("Can not transform ByteArrayWrap to IDataSet", e);
		} finally {
			byteArrayMemoryPool.returnByteArray(buffer);
		}
	}
	
	public static ImplEaiFlatData transformEaiFlatData(ByteArrayWrap totalByteArrayWrap) {
		prepare();
		byte[] buffer = null;
		try {
			buffer = byteArrayMemoryPool.getByteArray(2048); // 이 버퍼는 필드 하나를 읽기 위해 잠시 사용하는 버퍼이므로 2K 정도면 충분할듯하다.

			ImplEaiFlatData eaiFlatData = new ImplEaiFlatData();

			// 헤더 분석
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(totalByteArrayWrap.getByteArray(), totalByteArrayWrap.getOffset(), totalByteArrayWrap.getLength()));
			Map<String, String> headers = FlatHeaderHelper.toEaiHeaderMap(in, buffer, encoding);
			eaiFlatData.setEaiHeader(headers);
			
			boolean isOK = "0".equals(headers.get(EaiFlatHeaderSpec.TGRM_PRCRSLT_DCD.name()))?true:false;//거래 정상여부
			// 업무전문 분석
			ByteArrayWrap bodyByteArrayWrap = FlatHeaderHelper.getEaiBodyByteArrayWrap(totalByteArrayWrap, encoding, isOK);
			ByteBuffer bb = ByteBuffer.allocate(bodyByteArrayWrap.getLength());
			bb.put(bodyByteArrayWrap.getByteArray(), bodyByteArrayWrap.getOffset(), bodyByteArrayWrap.getLength());
			eaiFlatData.setBodyByteArrayWrap(new ByteArrayWrap(bb.array()));

			return eaiFlatData;
		} catch (Exception e) {
			throw new RuntimeException("Can not transform ByteArrayWrap to IDataSet", e);
		} finally {
			byteArrayMemoryPool.returnByteArray(buffer);
		}
	}
	
	private static void prepare() {
		if (_logger == null) {
			_logger = LogManager.getTransformLogger();
		}

//		if (ioLayoutRegistry == null) {
//			ioLayoutRegistry = (IBizIoLayoutMetaDataRegistry) ComponentRegistry.lookup(IBizIoLayoutMetaDataRegistry.COMPONENT_ID);
//		}

		if (cmdRegistry == null) {
			cmdRegistry = (IBizComponentMetaDataRegistry) ComponentRegistry.lookup(ServiceConstants.COMPONENT_METADATA_REGISTRY);
		}

		if(encoding == null){
			encoding = FlatHeaderParser.getInstance().getEncoding();	
			trailerBytes = FlatHeaderParser.getInstance().getTrailerBytes();	
		}
		
		if (byteArrayMemoryPool == null) {
			byteArrayMemoryPool = (IByteArrayMemoryPool) ComponentRegistry.lookup(IByteArrayMemoryPool.COMPONENT_ID);
		}
	}

}
