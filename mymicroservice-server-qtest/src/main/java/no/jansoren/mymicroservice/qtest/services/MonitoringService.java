package no.jansoren.mymicroservice.qtest.services;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MonitoringService {
  private WebTarget target;

  public MonitoringService() {
    Client client = ClientBuilder.newClient();
    target = client.target("http://localhost:8080").path("/monitoring");
  }

  public List getEventLog() {
    Response response = target.path("/eventlog").request(MediaType.APPLICATION_JSON_TYPE).get();
    return response.readEntity(List.class);
  }
}
