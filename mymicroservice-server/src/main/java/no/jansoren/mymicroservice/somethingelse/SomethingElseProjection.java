package no.jansoren.mymicroservice.somethingelse;

import no.jansoren.akka.persistence.eventsourcing.EventHandler;
import no.jansoren.akka.persistence.eventsourcing.Projection;

public class SomethingElseProjection extends Projection {

    private int somethingElseDoneCounter = 0;

    @EventHandler
    public void handleEvent(SomethingElseDoneEvent event) {
        somethingElseDoneCounter++;
    }

    public int getSomethingElseDoneCounter() {
        return somethingElseDoneCounter;
    }
}
