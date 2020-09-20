package com.segware.segwaretcpserver.gateway.mina.codec;

import com.segware.segwaretcpserver.gateway.database.CommandRepositoryImpl;
import com.segware.segwaretcpserver.model.command.ProtocolDataUnit;
import com.segware.segwaretcpserver.model.command.field.*;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.io.IOException;

public class PDUDecoder extends CumulativeProtocolDecoder {
    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput)
            throws Exception {

        if (ioBuffer.remaining() >= ProtocolDataUnit.FIXED_FIELDS_LENGTH) {
            decodeInit(ioBuffer);
            Bytes bytes = decodeBytes(ioBuffer);
            Frame frame = decodeFrame(ioBuffer);
            Data data = decodeData(ioBuffer, bytes);
            CRC8 crc8 = decodeCRC(ioBuffer);
            decodeEnd(ioBuffer);

            ProtocolDataUnit pdu = frame.getCommand(data, CommandRepositoryImpl.getInstance());
            validateCRC8(crc8, pdu.getCrc());

            protocolDecoderOutput.write(pdu);

            return true;
        } else {
            return false;
        }
    }

    private void decodeInit(IoBuffer ioBuffer) throws IOException {
        if (Init.isValid(ioBuffer.get()))
            return;
        else
            throw new IOException("Byte assumed as INIT hasn't a recognizable value.");
    }

    private Bytes decodeBytes(IoBuffer ioBuffer) {
        return new Bytes(ioBuffer.get());
    }

    private Frame decodeFrame(IoBuffer ioBuffer) throws IOException {
        try {
            return Frame.fromByte(ioBuffer.get());
        } catch (IllegalArgumentException e) {
            throw new IOException("Byte assumed as FRAME hasn't a recognizable value.");
        }
    }

    private Data decodeData(IoBuffer ioBuffer, Bytes bytes) throws IOException {
        if (ioBuffer.remaining() == expectedBufferRemaining(bytes)) {
            int dataLength = expectedDataLength(bytes);
            byte[] data = new byte[dataLength];
            ioBuffer.get(data, 0, dataLength);
            return new Data(data);
        } else {
            throw new IOException("Expected buffer size doesn't corresponds to actual buffer size.");
        }
    }

    private int expectedBufferRemaining(Bytes bytes) {
        return bytes.asInt() - ProtocolDataUnit.HEADER_FIELDS_LENGTH;
    }

    private int expectedDataLength(Bytes bytes) {
        return bytes.asInt() - ProtocolDataUnit.FIXED_FIELDS_LENGTH;
    }

    private CRC8 decodeCRC(IoBuffer ioBuffer) {
        return new CRC8(ioBuffer.get());
    }

    private void decodeEnd(IoBuffer ioBuffer) throws IOException {
        if (End.isValid(ioBuffer.get()))
            return;
        else
            throw new IOException("Byte assumed as END hasn't a recognizable value.");
    }

    private void validateCRC8(CRC8 receveidCRC8, CRC8 expectedCRC8) throws IOException {
        if (receveidCRC8.equals(expectedCRC8))
            return;
        else
            throw new IOException("Byte assumed as CRC hasn't a valid value.");
    }
}
