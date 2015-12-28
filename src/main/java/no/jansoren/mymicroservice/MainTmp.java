package no.jansoren.mymicroservice;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.persistence.query.EventEnvelope;
import akka.persistence.query.PersistenceQuery;
import akka.persistence.query.journal.leveldb.javadsl.LeveldbReadJournal;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Source;
import no.jansoren.mymicroservice.monitoring.ApplicationIsStartingCommand;
import no.jansoren.mymicroservice.monitoring.ApplicationIsStartingPersistenceActor;
import scala.runtime.BoxedUnit;

public class MainTmp {

    public static void main(String... args) throws Exception {
        ActorSystem system = ActorSystem.create("example");
        ActorRef persistentActor = system.actorOf(Props.create(ApplicationIsStartingPersistenceActor.class, "sample-id-1"), "persistentActor-4-java");

        persistentActor.tell(new ApplicationIsStartingCommand(), null);

        Thread.sleep(1000);

        LeveldbReadJournal readJournal = PersistenceQuery.get(system).getReadJournalFor(LeveldbReadJournal.class, LeveldbReadJournal.Identifier());

        Source<EventEnvelope, BoxedUnit> source = readJournal.eventsByPersistenceId("sample-id-1", 0, Long.MAX_VALUE);
        source.runForeach(event -> System.out.println("Event: " + event), ActorMaterializer.create(system));

        Thread.sleep(2000);

        system.terminate();
    }

}
