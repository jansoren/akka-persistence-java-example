package no.jansoren.mymicroservice.eventsourcing;

import no.jansoren.akka.persistence.eventsourcing.Projection;
import org.junit.Test;

public class ProjectionTest {

    @Test
    public void testProjectionNoMethods() {
        Projection projection = new Projection();
    }
}
