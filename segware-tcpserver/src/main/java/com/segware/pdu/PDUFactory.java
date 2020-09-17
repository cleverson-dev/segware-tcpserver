package com.segware.pdu;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class PDUFactory implements ProtocolCodecFactory {

    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return null;
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return null;
    }
}
