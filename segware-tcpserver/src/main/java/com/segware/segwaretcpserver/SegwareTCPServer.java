package com.segware.segwaretcpserver;

import com.segware.segwaretcpserver.gateway.database.DataSource;
import com.segware.segwaretcpserver.gateway.mina.codec.PDUCodecFactory;
import com.segware.segwaretcpserver.gateway.mina.ServerHandler;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SegwareTCPServer {

    private static final int PORT = 5050;

    public static void main( String[] args ) throws IOException {
        DataSource.getEntityManagerFactory();
        setupMina();
    }

    private static void setupMina() throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("pdu-codec", new ProtocolCodecFilter(new PDUCodecFactory()));

        acceptor.setHandler(new ServerHandler());
        acceptor.bind(new InetSocketAddress(PORT));
    }
}
