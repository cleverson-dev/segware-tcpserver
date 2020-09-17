package com.segware;

import com.segware.pdu.codec.PDUCodecFactory;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class SegwareTCPServer {

    private static final int PORT = 5050;

    public static void main( String[] args ) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("pdu-codec", new ProtocolCodecFilter(new PDUCodecFactory()));

        acceptor.setHandler(new ServerHandler());
        acceptor.bind(new InetSocketAddress(PORT));
    }
}
