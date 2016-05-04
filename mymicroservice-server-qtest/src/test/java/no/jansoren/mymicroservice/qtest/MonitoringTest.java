package no.jansoren.mymicroservice.qtest;

import no.jansoren.mymicroservice.qtest.services.MonitoringService;
import no.jansoren.mymicroservice.qtest.services.SomethingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MonitoringTest {

    private MonitoringService service;

    @Before
    public void setUp() {
        service = new MonitoringService();
    }

    @Test
    public void testEventLog() {
        List eventLog = service.getEventLog();
        Assert.assertNotNull(eventLog);
    }
}
