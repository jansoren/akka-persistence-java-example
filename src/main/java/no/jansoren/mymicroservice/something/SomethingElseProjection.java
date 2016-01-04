package no.jansoren.mymicroservice.something;

import no.jansoren.mymicroservice.eventsourcing.EventHandler;
import no.jansoren.mymicroservice.eventsourcing.Projection;

public class SomethingElseProjection extends Projection {

    private int somethingElseDoneCounter = 0;

    @EventHandler
    public void handleEvent(SomethingElseDoneEvent event) {
        somethingElseDoneCounter++;
    }

}
