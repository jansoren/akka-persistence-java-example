package no.jansoren.mymicroservice.monitoring;

import no.jansoren.mymicroservice.eventsourcing.EventStore;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/monitoring")
@Produces(MediaType.APPLICATION_JSON)
public class MonitoringResource {

    private EventStore eventStore;

    public MonitoringResource(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @GET
    @Path("/eventlog")
    public Response getEventLog() {
        EventLogProjection projection = eventStore.getProjection(EventLogProjection.class);
        List<String> eventLog = projection.getEventLog();
        return Response.ok(eventLog).build();
    }
}
