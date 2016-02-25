package no.jansoren.mymicroservice.health;

import com.codahale.metrics.health.HealthCheck;
import no.jansoren.mymicroservice.eventsourcing.EventStore;
import no.jansoren.akka.persistence.eventsourcing.IsRunning;
import no.jansoren.akka.persistence.eventsourcing.Yes;

public class ActorSystemHealthCheck extends HealthCheck {

    private EventStore eventStore;

    public ActorSystemHealthCheck(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    protected Result check() throws Exception {
        try {
            Object askIsRunning = eventStore.ask(new IsRunning());
            if(askIsRunning instanceof Yes) {
                return Result.healthy();
            }
            return Result.unhealthy("EventStore is not running properly. " + askIsRunning);
        } catch (Exception e) {
            return Result.unhealthy("EventStore did not respond within 3 seconds. " + e.getMessage());
        }
    }
}
