package no.jansoren.mymicroservice.eventsourcing;

import akka.japi.pf.ReceiveBuilder;
import akka.persistence.AbstractPersistentActor;
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

public class MymicroservicePersistenceActor extends AbstractPersistentActor {

    private static final Logger LOG = LoggerFactory.getLogger(MymicroservicePersistenceActor.class);

    private String persistenceId;

    public MymicroservicePersistenceActor(String persistenceId) {
        this.persistenceId = persistenceId;
    }

    @Override
    public String persistenceId() {
        return persistenceId;
    }

    @Override
    public PartialFunction<Object, BoxedUnit> receiveRecover() {
        LOG.debug("receiveRecover");
        return buildReceiver();
    }

    @Override
    public PartialFunction<Object, BoxedUnit> receiveCommand() {
        LOG.debug("receiveCommand");
        return buildReceiver();
    }

    private PartialFunction<Object, BoxedUnit> buildReceiver() {
        return ReceiveBuilder
                .match(IsRunning.class, this::handleCommand)
                .match(Shutdown.class, this::handleCommand)
                .match(ApplicationIsStartingCommand.class, this::handleCommand)
                .match(DoSomethingCommand.class, this::handleCommand)
                .match(DoSomethingElseCommand.class, this::handleCommand)
                .build();
    }

    private void handleCommand(IsRunning command) {
        sender().tell(new Yes(), self());
    }

    private void handleCommand(Shutdown command) {
        context().stop(self());
    }

    private void handleCommand(ApplicationIsStartingCommand command) {
        persistAsync(new ApplicationHasStartedEvent(), event -> {
            
        });
    }

    private void handleCommand(Command command) {
        persistAsync(new SomethingDoneEvent(), event -> {

        });
    }

    private void handleCommand(DoSomethingElseCommand command) {
        persistAsync(new SomethingElseDoneEvent(), event -> {

        });
    }
}
