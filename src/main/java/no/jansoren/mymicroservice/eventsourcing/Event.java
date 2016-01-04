package no.jansoren.mymicroservice.eventsourcing;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Event implements Serializable {

    private LocalDateTime created;

    public Event() {
        this.created = LocalDateTime.now();
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
