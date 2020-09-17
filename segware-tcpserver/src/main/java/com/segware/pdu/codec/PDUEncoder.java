package com.segware.pdu.codec;

import com.segware.pdu.ProtocolDataUnit;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class PDUEncoder extends ProtocolEncoderAdapter {
    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        ProtocolDataUnit pdu = (ProtocolDataUnit) o;
        IoBuffer ioBuffer = IoBuffer.allocate(pdu.getLength(), false);
        encodePDU(ioBuffer, pdu);
    }

    private void encodePDU(IoBuffer ioBuffer, ProtocolDataUnit pdu) {
        encodeInit(ioBuffer, pdu);
        encodeBytes(ioBuffer, pdu);
        encodeFrame(ioBuffer, pdu);
        encodeData(ioBuffer, pdu);
        encodeCrc(ioBuffer, pdu);
        encodeEnd(ioBuffer, pdu);
    }

    private void encodeInit(IoBuffer ioBuffer, ProtocolDataUnit pdu) {
        ioBuffer.put(pdu.getInit().toByte());
    }

    private void encodeBytes(IoBuffer ioBuffer, ProtocolDataUnit pdu) {
        ioBuffer.put(pdu.getBytes().toByte());
    }

    private void encodeFrame(IoBuffer ioBuffer, ProtocolDataUnit pdu) {
        ioBuffer.put(pdu.getFrame().toByte());
    }

    private void encodeData(IoBuffer ioBuffer, ProtocolDataUnit pdu) {
        ioBuffer.put(pdu.getData().toByteArray());
    }

    private void encodeCrc(IoBuffer ioBuffer, ProtocolDataUnit pdu) {
        ioBuffer.put(pdu.getCrc().toByte());
    }

    private void encodeEnd(IoBuffer ioBuffer, ProtocolDataUnit pdu) {
        ioBuffer.put(pdu.getEnd().toByte());
    }
}
