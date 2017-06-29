package no.jansoren.akka.persistence.eventsourcing;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import org.junit.AfterClass;

public class EventSourcedTestKit extends TestKit {

    protected static final ActorSystem actorSystem = ActorSystem.create("testActorSystem");

    public EventSourcedTestKit() {
        super(actorSystem);
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(actorSystem);
    }

    protected static ActorRef createPersistenceActor(Props props) {
        return actorSystem.actorOf(props);
    }

}
