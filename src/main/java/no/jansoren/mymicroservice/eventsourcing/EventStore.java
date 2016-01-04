package no.jansoren.mymicroservice.eventsourcing;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.persistence.query.EventEnvelope;
import akka.persistence.query.PersistenceQuery;
import akka.persistence.query.journal.leveldb.javadsl.LeveldbReadJournal;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Source;
import no.jansoren.mymicroservice.MymicroserviceConfiguration;
import no.jansoren.mymicroservice.monitoring.EventLogProjection;
import no.jansoren.mymicroservice.something.SomethingElseProjection;
import no.jansoren.mymicroservice.something.SomethingProjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.runtime.BoxedUnit;

import java.util.ArrayList;
import java.util.List;

public class EventStore {

    private static final Logger LOG = LoggerFactory.getLogger(EventStore.class);

    private ActorRef persistenceActor;
    private LeveldbReadJournal readJournal;

    public EventStore(MymicroserviceConfiguration configuration) {
        LOG.info("Initializing actor system for the event store");
        ActorSystem actorSystem = ActorSystem.create(configuration.getActorSystemName());
        persistenceActor =  actorSystem.actorOf(Props.create(MymicroservicePersistenceActor.class, configuration.getApplicationPersistenceId()), configuration.getPersistenceActorName());
        readJournal = PersistenceQuery.get(actorSystem).getReadJournalFor(LeveldbReadJournal.class, LeveldbReadJournal.Identifier());

        Source<EventEnvelope, BoxedUnit> source = readJournal.eventsByPersistenceId(configuration.getApplicationPersistenceId(), 0, Long.MAX_VALUE);

        List<Projection> projections = new ArrayList<>();
        projections.add(new EventLogProjection());
        projections.add(new SomethingProjection());
        projections.add(new SomethingElseProjection());

        source.runForeach(eventEnvelope -> {
            System.out.println("Event: " + eventEnvelope);
            Event event = (Event)eventEnvelope.event();
            for(Projection projection : projections) {
                projection.handleEvent(event);
            }
        }, ActorMaterializer.create(actorSystem));
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
