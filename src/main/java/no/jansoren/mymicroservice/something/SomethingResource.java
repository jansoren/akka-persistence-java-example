package no.jansoren.mymicroservice.something;

import no.jansoren.mymicroservice.eventsourcing.EventStore;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class SomethingResource {

    private String applicationName;
    private EventStore eventStore;

    public SomethingResource(String applicationName, EventStore eventStore) {
        this.applicationName = applicationName;
        this.eventStore = eventStore;
    }

    @GET
    public String welcomeMessage() {
        return "You are now prepared to implement your dropwizard " + applicationName + " application. Have fun :-)";
    }

    @GET
    @Path("/dosomething")
    public Response doSomething() {
        eventStore.tell(new DoSomethingCommand(), null);
        return Response.ok().build();
    }

    @GET
    @Path("/dosomethingelse")
    public Response doSomethingElse() {
        eventStore.tell(new DoSomethingElseCommand(), null);
        return Response.ok().build();
    }
/*
    @GET
    @Path("/something")
    public int getSomething() {


        eventStore.tell(new DoSomethingCommand(), null);
        return Response.ok().build();
    }

    @GET
    @Path("/somethingelse")
    public Response getSomethingElse() {
        eventStore.tell(new DoSomethingElseCommand(), null);
        return Response.ok().build();
    }

    @GET
    @Path("/somethingboth")
    public Response getSomethingBoth() {
        eventStore.tell(new DoSomethingElseCommand(), null);
        return Response.ok().build();
    }
   */
}
