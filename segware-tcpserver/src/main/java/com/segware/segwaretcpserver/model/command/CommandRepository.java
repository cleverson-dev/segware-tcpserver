package com.segware.segwaretcpserver.model.command;

public interface CommandRepository {
    void persist(A1Request a1Request, A0Response a0Response);
    void persist(A2Request a2Request, A0Response a0Response);
    void persist(A3Request a3Request, A3Response a3Response);
}
