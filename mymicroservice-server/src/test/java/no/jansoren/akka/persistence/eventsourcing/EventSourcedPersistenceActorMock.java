package no.jansoren.akka.persistence.eventsourcing;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import akka.japi.pf.UnitPFBuilder;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

public class EventSourcedPersistenceActorMock extends EventSourcedPersistenceActor {

    public EventSourcedPersistenceActorMock(String persistenceId) {
        super(persistenceId);
    }

    @Override
    protected Receive buildReceiver(ReceiveBuilder defaultMatches) {
        return defaultMatches.build();
    }
}
