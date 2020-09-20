package com.segware.pdu.commands;

import org.apache.mina.core.session.IoSession;

public interface PDURequest {
    void execute(IoSession session);
}
