import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ServerHandler extends IoHandlerAdapter {

    @Override
    public void messageReceived(IoSession session, Object message) {
        String str = message.toString();

        System.out.println("Message received: " + str);

        session.write("ACK: " + str);

        System.out.println("Message written...");
    }
}
