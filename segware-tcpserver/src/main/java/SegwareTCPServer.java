import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SegwareTCPServer {

    private static final int PORT = 5050;

    public static void main( String[] args ) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();

        acceptor.setHandler(  new ServerHandler() );
        acceptor.bind( new InetSocketAddress(PORT) );
    }
}
