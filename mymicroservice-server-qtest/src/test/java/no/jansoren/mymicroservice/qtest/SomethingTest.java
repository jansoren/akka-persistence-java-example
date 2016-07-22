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
    public void testGetSomething() {
        Integer something = service.getSomething();
        Assert.assertNotNull(something);
    }

    @Test
    public void testDoSomething() {
        Response something = service.doSomething();
        Assert.assertNull(something);
    }
}
