package com.example.app.resource;

import com.example.app.persistence.DatabaseUtils;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.spi.TestContainerFactory;

import javax.ws.rs.core.Application;

public abstract class ResourceTest extends JerseyTest {


    @Override
    public void setUp() throws Exception {
        super.setUp();
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createInitialState();
    }

}
