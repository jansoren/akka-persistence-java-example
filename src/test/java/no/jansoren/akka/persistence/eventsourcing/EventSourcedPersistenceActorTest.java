package no.jansoren.akka.persistence.eventsourcing;

import akka.actor.ActorRef;
import akka.actor.Props;
import org.junit.BeforeClass;
import org.junit.Test;

public class EventSourcedPersistenceActorTest extends EventSourcedTestKit {

    private static ActorRef persistenceActor;

    @BeforeClass
    public static void setup() {
        persistenceActor = createEventSourcedPersistenceActor();
    }

    @Test
    public void testThatPersistenceActorIsRunning() {
        persistenceActor.tell(new IsRunning(), getRef());
        expectMsgClass(Yes.class);
    }

    @Test
    public void testThatPersistenceActorShutdown() {
        watch(persistenceActor);
        persistenceActor.tell(new Shutdown(), getRef());
        expectTerminated(persistenceActor);
    }

    protected static ActorRef createEventSourcedPersistenceActor() {
        return createPersistenceActor(Props.create(EventSourcedPersistenceActorMock.class, "persistenceId"));
    }

}
