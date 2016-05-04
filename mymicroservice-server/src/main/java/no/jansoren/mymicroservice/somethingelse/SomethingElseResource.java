package no.jansoren.mymicroservice.somethingelse;

import no.jansoren.mymicroservice.eventsourcing.EventStore;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/somethingelse")
@Produces(MediaType.APPLICATION_JSON)
public class SomethingElseResource {

    private EventStore eventStore;

    public SomethingElseResource(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @GET
    @Path("/do")
    public Response doSomethingElse() {
        eventStore.tell(new DoSomethingElseCommand(), null);
        return Response.ok().build();
    }

    @GET
    @Path("/get")
    public Integer getSomethingElse() {
        SomethingElseProjection projection = eventStore.getProjection(SomethingElseProjection.class);
        int somethingElseDoneCounter = projection.getSomethingElseDoneCounter();
        return somethingElseDoneCounter;
    }
}
