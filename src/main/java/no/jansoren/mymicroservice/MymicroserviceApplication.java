package no.jansoren.mymicroservice;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import no.jansoren.mymicroservice.monitoring.ApplicationIsStartingCommand;
import no.jansoren.mymicroservice.monitoring.ApplicationIsStartingPersistenceActor;

public class MymicroserviceApplication extends Application<MymicroserviceConfiguration> {

    private ActorRef persistenceActor;

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
        persistenceActor = createPersistenceActor(configuration);

        persistenceActor.tell(new ApplicationIsStartingCommand(), null);
    }

    private ActorRef createPersistenceActor(MymicroserviceConfiguration configuration) {
        ActorSystem actorSystem = ActorSystem.create(configuration.getActorSystemName());
        return actorSystem.actorOf(Props.create(ApplicationIsStartingPersistenceActor.class, configuration.getApplicationPersistenceId()), configuration.getPersistenceActorName());
    }

}
