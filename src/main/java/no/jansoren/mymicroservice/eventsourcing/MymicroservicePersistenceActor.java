package no.jansoren.mymicroservice.eventsourcing;

import akka.japi.Procedure;
import akka.persistence.UntypedPersistentActor;
import no.jansoren.mymicroservice.monitoring.ApplicationHasStartedEvent;
import no.jansoren.mymicroservice.monitoring.ApplicationIsStartingCommand;
import no.jansoren.mymicroservice.something.DoSomethingCommand;
import no.jansoren.mymicroservice.something.DoSomethingElseCommand;
import no.jansoren.mymicroservice.something.SomethingDoneEvent;
import no.jansoren.mymicroservice.something.SomethingElseDoneEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MymicroservicePersistenceActor extends UntypedPersistentActor {

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
    public void onReceiveRecover(Object msg) throws Exception {
        LOG.debug("onReceiveRecover: " + msg);
    }

    @Override
    public void onReceiveCommand(Object msg) throws Exception {
        LOG.debug("Command: " + msg);
        if (msg instanceof IsRunning) {
            sender().tell(new Yes(), self());
        } else if (msg instanceof Shutdown) {
            context().stop(self());
        } else if (msg instanceof ApplicationIsStartingCommand) {
            ApplicationHasStartedEvent event = new ApplicationHasStartedEvent();
            persist(event, new Procedure<ApplicationHasStartedEvent>() {
                @Override
                public void apply(ApplicationHasStartedEvent event1) throws Exception {

                    //state.update(event1);

                    if (event.equals(event1)) {
                        getContext().system().eventStream().publish(event1);
                    }

                }
            });
        } else if (msg instanceof DoSomethingCommand) {
            persist(new SomethingDoneEvent(), new Procedure<SomethingDoneEvent>() {
                @Override
                public void apply(SomethingDoneEvent event) throws Exception {
                    getContext().system().eventStream().publish(event);
                }
            });
        } else if (msg instanceof DoSomethingElseCommand) {
            persist(new SomethingElseDoneEvent(), new Procedure<SomethingElseDoneEvent>() {
                @Override
                public void apply(SomethingElseDoneEvent event) throws Exception {

                }
            });
        } else if (msg.equals("snap")) {
            // IMPORTANT: create a copy of snapshot
            // because ExampleState is mutable !!!
            // saveSnapshot(state.copy());
        } else if (msg.equals("print")) {
            // System.out.println(state);
        } else {
            unhandled(msg);
        }

    }


}
