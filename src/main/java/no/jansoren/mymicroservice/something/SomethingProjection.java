package no.jansoren.mymicroservice.something;

import no.jansoren.mymicroservice.eventsourcing.EventHandler;
import no.jansoren.mymicroservice.eventsourcing.Projection;

public class SomethingProjection extends Projection {

    private int somethingDoneCounter = 0;

    @EventHandler
    public void handleEvent(SomethingDoneEvent event) {
        somethingDoneCounter++;
    }

}
