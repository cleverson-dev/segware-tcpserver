package com.segware;

import com.segware.pdu.ProtocolDataUnit;
import com.segware.pdu.commands.A0PDU;
import com.segware.pdu.commands.A1PDU;
import com.segware.pdu.commands.A2PDU;
import com.segware.pdu.structure.*;
import com.segware.pdu.structure.data.*;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.TransportMetadata;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.core.write.WriteRequestQueue;
import org.junit.Test;

import java.net.SocketAddress;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.*;

public class ServerHandlerTest {

    @Test
    public void shouldReturnAckWhenReceiveTextMsg() {
        ServerHandler serverHandler = new ServerHandler();
        IoSession ioSession = getMockedIoSession();
        ProtocolDataUnit pdu_0xA1 = new A1PDU(Data.fromString("Hello world!"));

        serverHandler.messageReceived(ioSession, pdu_0xA1);

        ProtocolDataUnit pdu_0xA0 = new A0PDU();
        ProtocolDataUnit writtenPdu = (ProtocolDataUnit) ioSession.getCurrentWriteMessage();
        assertThat(writtenPdu, is(equalTo(pdu_0xA0)));
    }

    @Test
    public void shouldReturnAckWhenReceiveUserInf() {
        ServerHandler serverHandler = new ServerHandler();
        IoSession ioSession = getMockedIoSession();

        UserInformation userInformation = new UserInformation(new Name("Michel Reips"));
        userInformation.setAge(new Age(32));
        userInformation.setWeight(new Weight(122));
        userInformation.setHeight(new Height(195));

        ProtocolDataUnit pdu_0xA2 = new A2PDU(userInformation);

        serverHandler.messageReceived(ioSession, pdu_0xA2);

        ProtocolDataUnit pdu_0xA0 = new A0PDU();
        ProtocolDataUnit writtenPdu = (ProtocolDataUnit) ioSession.getCurrentWriteMessage();
        assertThat(writtenPdu, is(equalTo(pdu_0xA0)));
    }

    @Test
    public void shouldReturnDateTimeWhenReceiveDateTimeRequest() {
        ServerHandler serverHandler = new ServerHandler();
        IoSession ioSession = getMockedIoSession();
        ProtocolDataUnit requestPDU_0xA3 = new A3PDU(new Data("America/Sao_Paulo".getBytes()));

        serverHandler.messageReceived(ioSession, requestPDU_0xA3);
        ProtocolDataUnit writtenPdu = (ProtocolDataUnit) ioSession.getCurrentWriteMessage();

        Data writtenPduData = writtenPdu.getData();
        DateTime dateTime = new DateTime(writtenPduData);
        assertThat(dateTime.getDay(), is(both(greaterThanOrEqualTo(1)).and(lessThanOrEqualTo(31))));
        assertThat(dateTime.getMonth(), is(both(greaterThanOrEqualTo(1)).and(lessThanOrEqualTo(31))));
        assertThat(dateTime.getYear(), is(both(greaterThanOrEqualTo(1)).and(lessThanOrEqualTo(2100))));
        assertThat(dateTime.getHour(), is(both(greaterThanOrEqualTo(1)).and(lessThanOrEqualTo(24))));
        assertThat(dateTime.getMinute(), is(both(greaterThanOrEqualTo(1)).and(lessThanOrEqualTo(59))));
        assertThat(dateTime.getSecond(), is(both(greaterThanOrEqualTo(1)).and(lessThanOrEqualTo(59))));

        assertThat(writtenPdu.getInit(), is(equalTo(Init.getInstance())));
        assertThat(writtenPdu.getBytes(), is(equalTo(DateTime.LENGTH)));
        assertThat(writtenPdu.getFrame(), is(equalTo(Frame.GET_CURRENT_DATE_TIME)));
        assertThat(writtenPdu.getCrc(), is(equalTo(CRC8.calculate(DateTime.LENGTH,
                Frame.GET_CURRENT_DATE_TIME, writtenPduData))));
        assertThat(writtenPdu.getEnd(), is(equalTo(End.getInstance())));
    }

    private IoSession getMockedIoSession() {
        return new IoSession() {
            private ProtocolDataUnit pdu;

            public long getId() {
                return 0;
            }

            public IoService getService() {
                return null;
            }

            public IoHandler getHandler() {
                return null;
            }

            public IoSessionConfig getConfig() {
                return null;
            }

            public IoFilterChain getFilterChain() {
                return null;
            }

            public WriteRequestQueue getWriteRequestQueue() {
                return null;
            }

            public TransportMetadata getTransportMetadata() {
                return null;
            }

            public ReadFuture read() {
                return null;
            }

            public WriteFuture write(Object o) {
                this.pdu = (ProtocolDataUnit) o;
                return null;
            }

            public WriteFuture write(Object o, SocketAddress socketAddress) {
                return null;
            }

            public CloseFuture close(boolean b) {
                return null;
            }

            public CloseFuture closeNow() {
                return null;
            }

            public CloseFuture closeOnFlush() {
                return null;
            }

            public CloseFuture close() {
                return null;
            }

            public Object getAttachment() {
                return null;
            }

            public Object setAttachment(Object o) {
                return null;
            }

            public Object getAttribute(Object o) {
                return null;
            }

            public Object getAttribute(Object o, Object o1) {
                return null;
            }

            public Object setAttribute(Object o, Object o1) {
                return null;
            }

            public Object setAttribute(Object o) {
                return null;
            }

            public Object setAttributeIfAbsent(Object o, Object o1) {
                return null;
            }

            public Object setAttributeIfAbsent(Object o) {
                return null;
            }

            public Object removeAttribute(Object o) {
                return null;
            }

            public boolean removeAttribute(Object o, Object o1) {
                return false;
            }

            public boolean replaceAttribute(Object o, Object o1, Object o2) {
                return false;
            }

            public boolean containsAttribute(Object o) {
                return false;
            }

            public Set<Object> getAttributeKeys() {
                return null;
            }

            public boolean isConnected() {
                return false;
            }

            public boolean isActive() {
                return false;
            }

            public boolean isClosing() {
                return false;
            }

            public boolean isSecured() {
                return false;
            }

            public CloseFuture getCloseFuture() {
                return null;
            }

            public SocketAddress getRemoteAddress() {
                return null;
            }

            public SocketAddress getLocalAddress() {
                return null;
            }

            public SocketAddress getServiceAddress() {
                return null;
            }

            public void setCurrentWriteRequest(WriteRequest writeRequest) {

            }

            public void suspendRead() {

            }

            public void suspendWrite() {

            }

            public void resumeRead() {

            }

            public void resumeWrite() {

            }

            public boolean isReadSuspended() {
                return false;
            }

            public boolean isWriteSuspended() {
                return false;
            }

            public void updateThroughput(long l, boolean b) {

            }

            public long getReadBytes() {
                return 0;
            }

            public long getWrittenBytes() {
                return 0;
            }

            public long getReadMessages() {
                return 0;
            }

            public long getWrittenMessages() {
                return 0;
            }

            public double getReadBytesThroughput() {
                return 0;
            }

            public double getWrittenBytesThroughput() {
                return 0;
            }

            public double getReadMessagesThroughput() {
                return 0;
            }

            public double getWrittenMessagesThroughput() {
                return 0;
            }

            public int getScheduledWriteMessages() {
                return 0;
            }

            public long getScheduledWriteBytes() {
                return 0;
            }

            public Object getCurrentWriteMessage() {
                return pdu;
            }

            public WriteRequest getCurrentWriteRequest() {
                return null;
            }

            public long getCreationTime() {
                return 0;
            }

            public long getLastIoTime() {
                return 0;
            }

            public long getLastReadTime() {
                return 0;
            }

            public long getLastWriteTime() {
                return 0;
            }

            public boolean isIdle(IdleStatus idleStatus) {
                return false;
            }

            public boolean isReaderIdle() {
                return false;
            }

            public boolean isWriterIdle() {
                return false;
            }

            public boolean isBothIdle() {
                return false;
            }

            public int getIdleCount(IdleStatus idleStatus) {
                return 0;
            }

            public int getReaderIdleCount() {
                return 0;
            }

            public int getWriterIdleCount() {
                return 0;
            }

            public int getBothIdleCount() {
                return 0;
            }

            public long getLastIdleTime(IdleStatus idleStatus) {
                return 0;
            }

            public long getLastReaderIdleTime() {
                return 0;
            }

            public long getLastWriterIdleTime() {
                return 0;
            }

            public long getLastBothIdleTime() {
                return 0;
            }
        };
    }
}
