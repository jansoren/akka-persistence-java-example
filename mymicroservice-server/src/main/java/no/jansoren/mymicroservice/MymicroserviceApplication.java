package no.jansoren.mymicroservice;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import no.jansoren.mymicroservice.eventsourcing.EventStore;
import no.jansoren.mymicroservice.eventsourcing.ShutdownManager;
import no.jansoren.mymicroservice.health.ActorSystemHealthCheck;
import no.jansoren.mymicroservice.monitoring.ApplicationIsStartingCommand;
import no.jansoren.mymicroservice.monitoring.MonitoringResource;
import no.jansoren.mymicroservice.something.SomethingResource;
import no.jansoren.mymicroservice.somethingelse.SomethingElseResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MymicroserviceApplication extends Application<MymicroserviceConfiguration> {

    private static final Logger LOG = LoggerFactory.getLogger(MymicroserviceApplication.class);
    public static final String APPLICATION_NAME = "Mymicroservice";

    private EventStore eventStore;

    public static void main(final String[] args) throws Exception {
        new MymicroserviceApplication().run(args);
    }

    @Override
    public String getName() {
        return APPLICATION_NAME;
    }

    @Override
    public void initialize(final Bootstrap<MymicroserviceConfiguration> bootstrap) {

    }

    @Override
    public void run(final MymicroserviceConfiguration configuration, final Environment environment) {
        eventStore = new EventStore(configuration);

        environment.healthChecks().register("ActorSystemHealthCheck", new ActorSystemHealthCheck(eventStore));

        environment.lifecycle().manage(new ShutdownManager(eventStore));

        environment.jersey().register(new MymicroserviceResource(getName()));
        environment.jersey().register(new MonitoringResource(eventStore));
        environment.jersey().register(new SomethingResource(eventStore));
        environment.jersey().register(new SomethingElseResource(eventStore));

        eventStore.tell(new ApplicationIsStartingCommand(), null);
    }
}
