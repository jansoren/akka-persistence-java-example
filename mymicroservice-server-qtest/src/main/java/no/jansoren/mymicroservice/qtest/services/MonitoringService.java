package no.jansoren.mymicroservice.qtest.services;

import java.util.List;
import no.bouvet.jsonclient.JsonClient;

public class MonitoringService {
  private final JsonClient jsonClient = new JsonClient();

  public List getEventLog() {
    return jsonClient.http().get("http://localhost:8080/monitoring/eventlog").object(List.class);
  }
}
