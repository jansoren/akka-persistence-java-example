package no.jansoren.akka.persistence.eventsourcing;

import akka.japi.pf.UnitPFBuilder;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

public class EventSourcedPersistenceActorMock extends EventSourcedPersistenceActor {

    public EventSourcedPersistenceActorMock(String persistenceId) {
        super(persistenceId);
    }

    @Override
    protected PartialFunction<Object, BoxedUnit> buildReceiver(UnitPFBuilder<Object> defaultMatches) {
        return defaultMatches.build();
    }
}
