package com.segware.segwaretcpserver.model.command;

import org.apache.mina.core.session.IoSession;

public interface CommandRequest {
    void execute(IoSession session);
}
