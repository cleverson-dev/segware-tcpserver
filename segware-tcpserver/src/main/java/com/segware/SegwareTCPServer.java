package com.segware;

import com.segware.pdu.codec.PDUCodecFactory;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SegwareTCPServer {

    private static final int PORT = 5050;

    public static void main( String[] args ) throws IOException, ClassNotFoundException {
        setupMina();
        setupH2();
    }

    private static void setupMina() throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("pdu-codec", new ProtocolCodecFilter(new PDUCodecFactory()));

        acceptor.setHandler(new ServerHandler());
        acceptor.bind(new InetSocketAddress(PORT));
    }

    // TODO: export information to properties file
    private static void setupH2() throws ClassNotFoundException {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:~/message-db";

        final String USER = "admin";
        final String PASS = "pass";

        Class.forName(JDBC_DRIVER);

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.println("Successful connection!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
