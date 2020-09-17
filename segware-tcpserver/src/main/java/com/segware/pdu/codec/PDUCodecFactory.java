package com.segware.pdu.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class PDUCodecFactory implements ProtocolCodecFactory {
    private ProtocolEncoder pduEncoder;
    private ProtocolDecoder pduDecoder;

    public PDUCodecFactory() {
        this.pduEncoder = new PDUEncoder();
        this.pduDecoder = new PDUDecoder();
    }

    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return pduEncoder;
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return pduDecoder;
    }
}
