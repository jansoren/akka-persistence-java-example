package no.jansoren.mymicroservice.something;

import no.jansoren.akka.persistence.eventsourcing.Event;

public class SomethingDoneEvent extends Event {
    @Override
    public String getDescription() {
        return "Something has been done";
    }
}
