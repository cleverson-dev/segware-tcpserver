package com.segware.segwaretcpserver.gateway.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataSource {
    private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public static EntityManagerFactory getEntityManagerFactory() {
        return factory;
    }

    public static void shutdown() {
        factory.close();
    }
}
