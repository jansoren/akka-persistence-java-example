package no.jansoren.mymicroservice.monitoring;

import no.jansoren.akka.persistence.eventsourcing.EventHandler;
import no.jansoren.akka.persistence.eventsourcing.Projection;
import no.jansoren.mymicroservice.something.SomethingDoneEvent;
import no.jansoren.mymicroservice.somethingelse.SomethingElseDoneEvent;

import java.util.ArrayList;
import java.util.List;

public class EventLogProjection extends Projection {

    private List<String> eventLog = new ArrayList<>();

    @EventHandler
    public void handleEvent(ApplicationHasStartedEvent event) {
        eventLog.add(event.getDescription());
    }

    @EventHandler
    public void handleEvent(SomethingDoneEvent event) {
        eventLog.add(event.getDescription());
    }

    @EventHandler
    public void handleEvent(SomethingElseDoneEvent event) {
        eventLog.add(event.getDescription());
    }

    public List<String> getEventLog() {
        return eventLog;
    }
}
