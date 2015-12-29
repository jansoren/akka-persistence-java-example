package no.jansoren.mymicroservice.something;

import com.codahale.metrics.annotation.Timed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class SomethingResource {

    private String applicationName;

    public SomethingResource(String applicationName) {
        this.applicationName = applicationName;
    }

    @GET
    @Timed
    public String welcomeMessage() {
        return "You are now prepared to implement your dropwizard " + applicationName + " application. Have fun :-)";
    }
}
