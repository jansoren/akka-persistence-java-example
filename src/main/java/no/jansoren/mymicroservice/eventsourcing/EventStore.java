package no.jansoren.mymicroservice.eventsourcing;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import no.jansoren.mymicroservice.MymicroserviceConfiguration;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

public class EventStore {

    private ActorRef persistenceActor;

    public EventStore(MymicroserviceConfiguration configuration) {
        ActorSystem actorSystem = ActorSystem.create(configuration.getActorSystemName());
        persistenceActor =  actorSystem.actorOf(Props.create(MymicroservicePersistenceActor.class, configuration.getApplicationPersistenceId()), configuration.getPersistenceActorName());
    }

    public ActorRef getPersistenceActor() {
        return persistenceActor;
    }

    public void tell(Object msg, ActorRef sender) {
        persistenceActor.tell(msg, sender);
    }

    public Object ask(Object msg) throws Exception {
        Future<Object> askEventStore = Patterns.ask(persistenceActor, msg, 3000);
        return Await.result(askEventStore, Duration.create("3 seconds"));
    }
}
