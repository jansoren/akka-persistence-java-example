package no.jansoren.akka.persistence.eventsourcing;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class EventSourcedPersistenceActorTest extends JavaTestKit{


    protected static final ActorSystem actorSystem = ActorSystem.create("testActorSystem");

    public EventSourcedPersistenceActorTest() {
        super(actorSystem);
    }

    @BeforeClass
    public static void setup() {
    }

    @AfterClass
    public static void teardown() {
        JavaTestKit.shutdownActorSystem(actorSystem);
    }

    @Test
    public void testThatPersistenceActorIsRunning() {
        final ActorRef persistenceActor = createEventSourcedPersistenceActor();

        persistenceActor.tell(new IsRunning(), getRef());

        expectMsgClass(duration("3 seconds"), Yes.class);
    }

    protected ActorRef createEventSourcedPersistenceActor() {
        return createPersistenceActor(Props.create(EventSourcedPersistenceActorMock.class, "persistenceId"));
    }

    protected ActorRef createPersistenceActor(Props props) {
        return actorSystem.actorOf(props);
    }
}
