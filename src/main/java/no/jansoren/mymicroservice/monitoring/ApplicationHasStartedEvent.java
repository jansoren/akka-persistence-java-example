package no.jansoren.mymicroservice.monitoring;

import no.jansoren.mymicroservice.eventsourcing.Event;

public class ApplicationHasStartedEvent extends Event {

    @Override
    public String getDescription() {
        return "Application has started";
    }
}
