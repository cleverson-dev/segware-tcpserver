package com.segware.segwaretcpserver.gateway.database;

import com.segware.segwaretcpserver.gateway.database.entities.A1RequestEntity;
import com.segware.segwaretcpserver.gateway.database.entities.A2RequestEntity;
import com.segware.segwaretcpserver.gateway.database.entities.A3RequestEntity;
import com.segware.segwaretcpserver.model.command.*;

public class CommandRepositoryImpl implements CommandRepository {
    private static CommandRepositoryImpl commandRepository = new CommandRepositoryImpl();

    public static CommandRepositoryImpl getInstance() {
        return commandRepository;
    }

    @Override
    public void persist(A1Request a1Request, A0Response a0Response) {
        new A1RequestEntity(a1Request, a0Response).persist();
    }

    @Override
    public void persist(A2Request a2Request, A0Response a0Response) {
        new A2RequestEntity(a2Request, a0Response).persist();
    }

    @Override
    public void persist(A3Request a3Request, A3Response a3Response) {
        new A3RequestEntity(a3Request, a3Response).persist();
    }
}
