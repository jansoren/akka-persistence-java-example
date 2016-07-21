package no.jansoren.mymicroservice.qtest.services;

import java.lang.Integer;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class SomethingService {
  private WebTarget target;

  public SomethingService() {
    Client client = ClientBuilder.newClient();
    target = client.target("http://localhost:8080").path("/something");
  }

  public Response doSomething() {
    Response response = target.path("/do").request(MediaType.APPLICATION_JSON_TYPE).get();
    return (Response)response.getEntity();
  }

  public Integer getSomething() {
    Response response = target.path("/get").request(MediaType.APPLICATION_JSON_TYPE).get();
    return (Integer)response.getEntity();
  }
}
