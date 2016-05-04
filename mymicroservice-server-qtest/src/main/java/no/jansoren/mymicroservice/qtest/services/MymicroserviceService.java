package no.jansoren.mymicroservice.qtest.services;

import java.lang.String;
import no.bouvet.jsonclient.JsonClient;

public class MymicroserviceService {
  private final JsonClient jsonClient = new JsonClient();

  public String welcomeMessage() {
    return jsonClient.http().get("http://localhost:8080/").object(String.class);
  }
}
