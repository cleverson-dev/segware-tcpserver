package com.segware.segwaretcpserver.segwaretcpserver.model.command;

import org.apache.mina.core.session.IoSession;

public interface PDURequest {
    void execute(IoSession session);
}
