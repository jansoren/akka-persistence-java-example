package no.jansoren.mymicroservice.eventsourcing;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Projection {

    private HashMap<Class<? extends Event>, Method> eventClassMethods;

    public Projection() {
        eventClassMethods = new HashMap<>();
        Class<? extends Projection> projectionClass = this.getClass();
        for (Method method : projectionClass.getMethods()) {
            EventHandler eventHandlerAnnotation = method.getAnnotation(EventHandler.class);
            if (eventHandlerAnnotation != null) {
                Class<?>[] types = method.getParameterTypes();
                Class<? extends Event> handledType = (Class<? extends Event>) types[0];
                eventClassMethods.put(handledType, method);
            }
        }
    }

    public void handleEvent(Event event) {
        Method method = findHandleEventMethod(eventClassMethods, event);
        if (method != null) {
            try {
                method.invoke(this, event);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Method findHandleEventMethod(Map<Class<? extends Event>, Method> handlers, Event event) {
        Method method = null;

        Class<?> theclass = event.getClass();
        while (method == null && theclass != Object.class){
            method = handlers.get(theclass);
            theclass = theclass.getSuperclass();
        }
        return method;
    }
}
