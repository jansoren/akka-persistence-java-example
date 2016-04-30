package no.jansoren.mymicroservice.qtest.services;

import javax.ws.rs.core.Response;
import no.bouvet.jsonclient.JsonClient;

public class MonitoringService {
  private final JsonClient jsonClient = new JsonClient();

  public Response getEventLog() {
    return jsonClient.http().get("http://localhost:8080/monitoring/eventlog").object(Response.class);
  }
}
