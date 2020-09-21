package com.segware.segwaretcpserver.gateway.mina.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class CommandCodecFactory implements ProtocolCodecFactory {
    private ProtocolEncoder pduEncoder;
    private ProtocolDecoder pduDecoder;

    public CommandCodecFactory() {
        this.pduEncoder = new CommandEncoder();
        this.pduDecoder = new CommandDecoder();
    }

    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return pduEncoder;
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return pduDecoder;
    }
}
