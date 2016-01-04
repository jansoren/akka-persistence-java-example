package no.jansoren.mymicroservice.monitoring;

import no.jansoren.mymicroservice.eventsourcing.EventHandler;
import no.jansoren.mymicroservice.eventsourcing.Projection;
import no.jansoren.mymicroservice.something.SomethingDoneEvent;
import no.jansoren.mymicroservice.something.SomethingElseDoneEvent;

import java.util.ArrayList;
import java.util.List;

public class EventLogProjection extends Projection {

    private List<String> eventLog = new ArrayList<>();


    @EventHandler
    public void handleEvent(ApplicationHasStartedEvent event) {

    }

    @EventHandler
    public void handleEvent(SomethingDoneEvent event) {

    }

    @EventHandler
    public void handleEvent(SomethingElseDoneEvent event) {
        
    }

}
