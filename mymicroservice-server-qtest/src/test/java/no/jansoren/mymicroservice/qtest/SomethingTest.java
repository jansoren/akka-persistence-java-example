package no.jansoren.mymicroservice.qtest;

import no.jansoren.mymicroservice.qtest.services.SomethingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class SomethingTest {


    private SomethingService service;

    @Before
    public void setUp() {
        service = new SomethingService();
    }

    @Test
    public void testSomething() {
        Response something = service.getSomething();
        Object entity = something.getEntity();
        Assert.assertNotNull(entity);
    }
}
