package no.jansoren.mymicroservice.eventsourcing;

import akka.japi.pf.ReceiveBuilder;
import akka.japi.pf.UnitPFBuilder;
import akka.persistence.AbstractPersistentActor;
import no.jansoren.akka.persistence.eventsourcing.EventSourcedPersistenceActor;
import no.jansoren.akka.persistence.eventsourcing.IsRunning;
import no.jansoren.akka.persistence.eventsourcing.Shutdown;
import no.jansoren.akka.persistence.eventsourcing.Yes;
import no.jansoren.mymicroservice.monitoring.ApplicationHasStartedEvent;
import no.jansoren.mymicroservice.monitoring.ApplicationIsStartingCommand;
import no.jansoren.mymicroservice.something.DoSomethingCommand;
import no.jansoren.mymicroservice.somethingelse.DoSomethingElseCommand;
import no.jansoren.mymicroservice.something.SomethingDoneEvent;
import no.jansoren.mymicroservice.somethingelse.SomethingElseDoneEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

public class MymicroservicePersistenceActor extends EventSourcedPersistenceActor {

    private static final Logger LOG = LoggerFactory.getLogger(MymicroservicePersistenceActor.class);

    public MymicroservicePersistenceActor(String persistenceId) {
        super(persistenceId);
    }

    @Override
    protected PartialFunction<Object, BoxedUnit> buildReceiver(UnitPFBuilder<Object> defaultMatches) {
        return defaultMatches
                .match(ApplicationIsStartingCommand.class, this::handleCommand)
                .match(DoSomethingCommand.class, this::handleCommand)
                .match(DoSomethingElseCommand.class, this::handleCommand)
                .build();
    }

    private void handleCommand(ApplicationIsStartingCommand command) {
        persistAsync(new ApplicationHasStartedEvent(), event -> {

        });
    }

    private void handleCommand(DoSomethingCommand command) {
        persistAsync(new SomethingDoneEvent(), event -> {

        });
    }

    private void handleCommand(DoSomethingElseCommand command) {
        persistAsync(new SomethingElseDoneEvent(), event -> {

        });
    }
}
