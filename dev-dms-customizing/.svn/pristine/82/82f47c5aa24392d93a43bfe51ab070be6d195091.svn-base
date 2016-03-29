package fwk.transform;

import java.nio.ByteBuffer;
import java.util.List;

import fwk.flat.FlatHeaderParser;
import nexcore.framework.core.component.IMethodMetaData;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.IValueObject;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.SystemRuntimeException;
import nexcore.framework.core.prototype.IMessageCoded;
import nexcore.framework.core.transform.FlatBulkTransformer;
import nexcore.framework.core.transform.FlatConstants;
import nexcore.framework.core.transform.FlatTransformerUtil;
import nexcore.framework.core.transform.IFlatHeaderParser;
import nexcore.framework.core.util.BufferReuseByteArrayOutputStream;
import nexcore.framework.core.util.ByteArrayWrap;
import nexcore.framework.core.util.PaddableDataOutputStream;

public class HpcFlatTransformer extends FlatBulkTransformer {


	public ByteArrayWrap transformValueObjectToEaiResponse(IOnlineContext onlineCtx, IValueObject valueObject) {
		prepare();
		try {
			return transformHeaderBodyToEaiResponse(onlineCtx, valueObject);
		}catch(Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("Cannot convert to response data. ", e);
			}
			// 에러 응답전문을 write하도록 뿌리도록 ResponseContext를 만든다.
			DataSet errords = new DataSet();
			if (e instanceof IMessageCoded) {
				IMessageCoded em = (IMessageCoded)e;
				errords.setResultMessage(ResultMessage.FAIL, em.getMessageId(), em.getMessageParams(), e);
			}else {
				errords.setResultMessage(ResultMessage.FAIL, "SKFS1048", new String[]{e.toString(), this.getClass().getName()}, e);
			}
			try {
				// 에러 전문을 생성함. body 부는 space로 채움.
				return transformHeaderBodyToEaiResponse(onlineCtx, errords);
			}catch(Exception ee) {
				// 에러 전문 생성하다가 오류나면 헤더만 응답한다. 저위 errords 를 뿌린다.
				if (logger.isErrorEnabled()) {
					logger.error("[" + onlineCtx.getTransaction().getRequestId()  + "] ["+ onlineCtx.getTransaction().getTxId() + "] Cannot convert to error response data. ", e);
				}
				return transformHeaderOnlyToResponse(onlineCtx, errords);
			}
		}
	}

	protected ByteArrayWrap transformHeaderBodyToEaiResponse(IOnlineContext onlineCtx, IValueObject valueObject) {
		byte[] userDataBuffer = null;
		byte[] headerBuffer   = null;

		try {
			// -------------- UserData 생성 --------------
			IDataSet        dataSet = (IDataSet)valueObject;
			IMethodMetaData mmd     = null;

			// userdata write 할지 말지.
			boolean userDataWrite = false;

			// [정상 + padding true] || [정상 + padding false] || [에러 + padding true]  => pad
			// [에러 + padding false] => no pad
			if (valueObject.getResultMessage() == null){
				throw new RuntimeException("Value Object (or IDataSet) must have a Message");
			}
			if (valueObject.getResultMessage().getStatus() == IResultMessage.OK || userDataPaddingForErrorResponse) {
				// 실행결과가 정상이거나 padding flag 가 true 일 경우는 userdata 를 생성한다.
				userDataWrite = true;
			}

			mmd = (IMethodMetaData)onlineCtx.getAttribute("nexcore.method.metadata");
			// TransactionId 가 잘못된 경우. Meta정보가 없으니 UserData 를 write할 수 없다. 그냥 Header 만 write한다.
			//	            userDataWrite = (mmd != null);

			int userDataLength = 0;
			BufferReuseByteArrayOutputStream userDataBOut = null;
			PaddableDataOutputStream         userDataPOut = null;
			// UserData write 여부 체크
			// 2010.06.21 응답전문 생성시 업무데이타부 리턴(분석) 여부를 체크하는 로직 추가.
			if (userDataWrite && mmd != null && isForceWriteUserData(onlineCtx)) {
				List fmdList = mmd.getOutputIoMetaData().getFlMetaDataList();
				if (fmdList == null) {
					// TODO BaseRuntimeException 으로 변경
					throw new RuntimeException("MethodMetaData's OutputIOMetadata not found. txid=["+onlineCtx.getTransaction().getTxId()+"] ");
				}

				/*
				 * 일단 BufferReuseByteArrayOutputStream으로 userdata 부분을 먼저 버퍼링하고
				 * 그 결과 값을 가지고 Header 포함 최종 전문을 write한다. 
				 * 이유로는 일반적으로 Header 에 전문 전체길이를 포함하게 되므로, userdata 부분을 먼저 따져봐야지 
				 * 전체 길이를 알 수 있다. 
				 */
				userDataBuffer = byteArrayMemoryPool.getByteArray(totalLengthAbout); // 설정파일에서 지정한 totalLengthAbout 만큼 버퍼를 잡는다. 
				userDataBOut = new BufferReuseByteArrayOutputStream(userDataBuffer);
				userDataPOut = new PaddableDataOutputStream(userDataBOut, encoding);

				if(transformLogger.isDebugEnabled()){
					transformLogger.debug("\n==================== FlatTransformer transformHeaderBodyToResponse ===================");
				}
				FlatTransformerUtil.transformDataSetToStream(onlineCtx, dataSet, mmd.getOutputIoMetaData(), encoding, userDataPOut, transformLogger);
				userDataPOut.flush();
				userDataLength = userDataPOut.size();
			}

			// userData 길이정보를 OnlineContext의 Attribute 로 준다.
			onlineCtx.setAttribute(FlatConstants.USERDATA_LENGTH, new Integer(userDataLength));

			// -------------- HEADER 생성 --------------
			headerBuffer = byteArrayMemoryPool.getByteArray(headerLengthAbout);
			BufferReuseByteArrayOutputStream hdrBOut = new BufferReuseByteArrayOutputStream(headerBuffer);
			PaddableDataOutputStream         hdrPOut = new PaddableDataOutputStream(hdrBOut, encoding);

			ByteArrayWrap bodyByteArrayWrap;
			if(userDataBOut != null){
				bodyByteArrayWrap =  new ByteArrayWrap(userDataBOut.getByteArray(), 0, userDataLength);
			}
			else{
				bodyByteArrayWrap =  new ByteArrayWrap(EMPTY_BYTES, 0, userDataLength);
			}

			// 헤더파서를 이용하여 헤더 생성.
			((FlatHeaderParser)headerParser).writeEaiHeader(
					onlineCtx.getAttributesAll(),
					dataSet.getResultMessage(),
					bodyByteArrayWrap,
					hdrPOut,
					IFlatHeaderParser.TR_TYPE_RESPONSE);

			// TR_TYPE을 Response로 설정한다.
			onlineCtx.setAttribute(FlatConstants.TR_TYPE, FlatConstants.TR_TYPE_RESPONSE);

			// 최종 응답 생성 --------------------------
			int headerLength = hdrBOut.size();
			ByteBuffer bb = ByteBuffer.allocate(trailer==null ? headerLength + userDataLength : headerLength + userDataLength + trailerBytes.length);
			// 헤더를 먼저 write하고,
			bb.put(hdrBOut.getByteArray(), 0, headerLength);
			// 본문을 write한다.
			if(userDataLength > 0){
				bb.put(userDataBOut.getByteArray(), 0, userDataLength);
			}

			// tailer 출력. 
			if (trailer != null)  { 
				// tailer 는 UserData 길이에 포함되지 않는다. 
				// TotalLength에 포함이 필요한 경우는 headerParser에서 tailer 만큼 add 하여 계산해야함.
				bb.put(trailerBytes);
			}

			return new ByteArrayWrap(bb.array());
		}catch(Exception e) {
			// 에러가 나면 여기서 에러전문을 만든다.
			if (e instanceof IMessageCoded) {
				IMessageCoded em = (IMessageCoded)e;
				throw new SystemRuntimeException(em.getMessageId(), em.getMessageParams(), e);
			}else {
				throw new RuntimeException("Exception on converting response data.", e);
			}
		}finally {
			byteArrayMemoryPool.returnByteArray(userDataBuffer);
			byteArrayMemoryPool.returnByteArray(headerBuffer);
		}
	}
}
