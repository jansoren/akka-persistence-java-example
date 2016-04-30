package no.jansoren.mymicroservice.qtest.services;

import javax.ws.rs.core.Response;
import no.bouvet.jsonclient.JsonClient;

public class SomethingService {
  private final JsonClient jsonClient = new JsonClient();

  public Response doSomething() {
    return jsonClient.http().get("http://localhost:8080/something/do").object(Response.class);
  }

  public Response getSomething() {
    return jsonClient.http().get("http://localhost:8080/something/get").object(Response.class);
  }
}
