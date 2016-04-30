package no.jansoren.mymicroservice.qtest.services;

import javax.ws.rs.core.Response;
import no.bouvet.jsonclient.JsonClient;

public class MymicroserviceService {
  private final JsonClient jsonClient = new JsonClient();

  public Response welcomeMessage() {
    return jsonClient.http().get("http://localhost:8080/").object(Response.class);
  }
}
