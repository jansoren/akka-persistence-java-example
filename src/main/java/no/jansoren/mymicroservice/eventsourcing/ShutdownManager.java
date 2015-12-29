package no.jansoren.mymicroservice.eventsourcing;

import io.dropwizard.lifecycle.Managed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShutdownManager implements Managed {

    private static final Logger LOG = LoggerFactory.getLogger(ShutdownManager.class);

    private EventStore eventStore;

    public ShutdownManager(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void start() throws Exception {
        
    }

    @Override
    public void stop() throws Exception {
        LOG.info("Shutting down actor system for the event store");
        eventStore.tell(new Shutdown(), null);
    }
}
