package no.jansoren.mymicroservice.monitoring;

import no.jansoren.akka.persistence.eventsourcing.Event;

public class ApplicationHasStartedEvent extends Event {

    @Override
    public String getDescription() {
        return "Application has started";
    }
}
