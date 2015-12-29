package no.jansoren.mymicroservice;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import no.jansoren.mymicroservice.eventsourcing.EventStore;
import no.jansoren.mymicroservice.health.ActorSystemHealthCheck;
import no.jansoren.mymicroservice.something.SomethingResource;

public class MymicroserviceApplication extends Application<MymicroserviceConfiguration> {

    private EventStore eventStore;

    public static void main(final String[] args) throws Exception {
        new MymicroserviceApplication().run(args);
    }

    @Override
    public String getName() {
        return "Mymicroservice";
    }

    @Override
    public void initialize(final Bootstrap<MymicroserviceConfiguration> bootstrap) {

    }

    @Override
    public void run(final MymicroserviceConfiguration configuration, final Environment environment) {
        eventStore = new EventStore(configuration);

        environment.healthChecks().register("ActorSystemHealthCheck", new ActorSystemHealthCheck(eventStore));

        environment.jersey().register(new SomethingResource(getName()));
    }
}
