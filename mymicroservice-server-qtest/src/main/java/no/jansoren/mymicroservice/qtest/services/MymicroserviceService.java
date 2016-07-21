package no.jansoren.mymicroservice.qtest.services;

import java.lang.String;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MymicroserviceService {
  private WebTarget target;

  public MymicroserviceService() {
    Client client = ClientBuilder.newClient();
    target = client.target("http://localhost:8080").path("/");
  }

  public String welcomeMessage() {
    Response response = target.path("").request(MediaType.APPLICATION_JSON_TYPE).get();
    return (String)response.getEntity();
  }
}
