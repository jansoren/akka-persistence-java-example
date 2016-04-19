package no.jansoren.akka.persistence.eventsourcing;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import org.junit.AfterClass;

public class EventSourcedTestKit extends JavaTestKit {

    protected static final ActorSystem actorSystem = ActorSystem.create("testActorSystem");

    public EventSourcedTestKit() {
        super(actorSystem);
    }

    @AfterClass
    public static void teardown() {
        JavaTestKit.shutdownActorSystem(actorSystem);
    }

    protected static ActorRef createPersistenceActor(Props props) {
        return actorSystem.actorOf(props);
    }

}
