package no.jansoren.mymicroservice.qtest.services;

import java.lang.Integer;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class SomethingElseService {
  private WebTarget target;

  public SomethingElseService() {
    Client client = ClientBuilder.newClient();
    target = client.target("http://localhost:8080").path("/somethingelse");
  }

  public Response doSomethingElse() {
    Response response = target.path("/do").request(MediaType.APPLICATION_JSON_TYPE).get();
    return response.readEntity(Response.class);
  }

  public Integer getSomethingElse() {
    Response response = target.path("/get").request(MediaType.APPLICATION_JSON_TYPE).get();
    return response.readEntity(Integer.class);
  }
}
