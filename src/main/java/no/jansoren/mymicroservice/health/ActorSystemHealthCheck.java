package no.jansoren.mymicroservice.health;

import com.codahale.metrics.health.HealthCheck;
import no.jansoren.mymicroservice.eventsourcing.EventStore;
import no.jansoren.mymicroservice.monitoring.ApplicationIsStartingCommand;

public class ActorSystemHealthCheck extends HealthCheck {

    private EventStore eventStore;

    public ActorSystemHealthCheck(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    protected Result check() throws Exception {
        try {
            Object ask = eventStore.ask(new ApplicationIsStartingCommand());
            return Result.healthy();
        } catch (Exception e) {
            return Result.unhealthy("EventStore did not respond within 3 seconds");
        }
    }
}
