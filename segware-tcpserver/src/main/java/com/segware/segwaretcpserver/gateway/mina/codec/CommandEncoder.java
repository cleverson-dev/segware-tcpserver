package com.segware.segwaretcpserver.gateway.mina.codec;

import com.segware.segwaretcpserver.model.command.Command;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class CommandEncoder extends ProtocolEncoderAdapter {
    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        Command pdu = (Command) o;
        IoBuffer ioBuffer = IoBuffer.allocate(pdu.getLength(), false);
        encodePDU(ioBuffer, pdu);
        ioBuffer.flip();
        protocolEncoderOutput.write(ioBuffer);
    }

    private void encodePDU(IoBuffer ioBuffer, Command pdu) {
        encodeInit(ioBuffer, pdu);
        encodeBytes(ioBuffer, pdu);
        encodeFrame(ioBuffer, pdu);
        encodeData(ioBuffer, pdu);
        encodeCrc(ioBuffer, pdu);
        encodeEnd(ioBuffer, pdu);
    }

    private void encodeInit(IoBuffer ioBuffer, Command pdu) {
        ioBuffer.put(pdu.getInit().toByte());
    }

    private void encodeBytes(IoBuffer ioBuffer, Command pdu) {
        ioBuffer.put(pdu.getBytes().toByte());
    }

    private void encodeFrame(IoBuffer ioBuffer, Command pdu) {
        ioBuffer.put(pdu.getFrame().toByte());
    }

    private void encodeData(IoBuffer ioBuffer, Command pdu) {
        ioBuffer.put(pdu.getData().toByteArray());
    }

    private void encodeCrc(IoBuffer ioBuffer, Command pdu) {
        ioBuffer.put(pdu.getCrc().toByte());
    }

    private void encodeEnd(IoBuffer ioBuffer, Command pdu) {
        ioBuffer.put(pdu.getEnd().toByte());
    }
}
