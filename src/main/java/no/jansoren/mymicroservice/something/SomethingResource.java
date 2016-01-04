package no.jansoren.mymicroservice.something;

import no.jansoren.mymicroservice.eventsourcing.EventStore;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/something")
@Produces(MediaType.APPLICATION_JSON)
public class SomethingResource {

    private EventStore eventStore;

    public SomethingResource(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @GET
    @Path("/do")
    public Response doSomething() {
        eventStore.tell(new DoSomethingCommand(), null);
        return Response.ok().build();
    }

    @GET
    @Path("/get")
    public Response getSomething() {
        SomethingProjection projection = eventStore.getProjection(SomethingProjection.class);
        int somethingDoneCounter = projection.getSomethingDoneCounter();
        return Response.ok(somethingDoneCounter).build();
    }

}
