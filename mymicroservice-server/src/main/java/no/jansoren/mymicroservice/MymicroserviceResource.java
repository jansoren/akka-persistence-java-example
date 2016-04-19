package no.jansoren.mymicroservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class MymicroserviceResource {

    private String applicationName;

    public MymicroserviceResource(String applicationName) {
        this.applicationName = applicationName;
    }

    @GET
    public String welcomeMessage() {
        return "You are now prepared to implement your dropwizard " + applicationName + " application. Have fun :-)";
    }

}
