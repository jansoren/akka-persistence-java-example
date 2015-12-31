package no.jansoren.mymicroservice.eventsourcing;

import akka.persistence.UntypedPersistentActor;

import java.lang.reflect.ParameterizedType;

@Deprecated // this class was an idea of a solution. Remove when example milestone is finished
public abstract class CommandHandler<T> extends UntypedPersistentActor {
    @Override
    public void onReceiveRecover(Object msg) throws Exception {
        if(getTClassType().isInstance(msg)) {
            handleRecover(msg);
        }
    }

    @Override
    public void onReceiveCommand(Object msg) throws Exception {
        if(getTClassType().isInstance(msg)) {
            handleCommand(msg);
        }
    }

    public abstract String persistenceId();

    protected abstract void handleCommand(Object p0);

    protected abstract void handleRecover(Object msg);

    private Class<?> getTClassType() {
        ParameterizedType pt = (ParameterizedType)this.getClass().getGenericSuperclass();
        return (Class<?>)pt.getActualTypeArguments()[0];
    }
}
