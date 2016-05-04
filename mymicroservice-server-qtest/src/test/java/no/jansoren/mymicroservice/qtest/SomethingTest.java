package no.jansoren.mymicroservice.qtest;

import no.jansoren.mymicroservice.qtest.services.SomethingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SomethingTest {


    private SomethingService service;

    @Before
    public void setUp() {
        service = new SomethingService();
    }

    @Test
    public void testSomething() {
        Integer something = service.getSomething();
        Assert.assertNotNull(something);
    }
}
