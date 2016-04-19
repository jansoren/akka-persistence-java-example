package no.jansoren.mymicroservice.something;

import no.jansoren.akka.persistence.eventsourcing.EventHandler;
import no.jansoren.akka.persistence.eventsourcing.Projection;

public class SomethingProjection extends Projection {

    private int somethingDoneCounter = 0;

    @EventHandler
    public void handleEvent(SomethingDoneEvent event) {
        somethingDoneCounter++;
    }

    public int getSomethingDoneCounter() {
        return somethingDoneCounter;
    }
}
