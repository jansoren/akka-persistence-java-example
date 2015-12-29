package no.jansoren.mymicroservice.monitoring;

import akka.japi.Procedure;
import akka.persistence.UntypedPersistentActor;

public class ApplicationIsStartingPersistenceActor extends UntypedPersistentActor {

    private String persistenceId;
    

    public ApplicationIsStartingPersistenceActor(String persistenceId) {
        this.persistenceId = persistenceId;
    }

    @Override
    public String persistenceId() {
        return persistenceId;
    }

    @Override
    public void onReceiveRecover(Object msg) throws Exception {
        System.out.println("onReceiveRecover: " + msg);
    }

    @Override
    public void onReceiveCommand(Object msg) throws Exception {
        System.out.println(msg);
        if (msg instanceof ApplicationIsStartingCommand) {
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
