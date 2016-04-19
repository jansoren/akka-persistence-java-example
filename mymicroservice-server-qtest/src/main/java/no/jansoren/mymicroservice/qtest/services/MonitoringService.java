package no.jansoren.mymicroservice.qtest.services;

import java.lang.String;

public class MonitoringService {
  public String getEventLog() {
    return "http://localhost:8080/monitoring/eventlog";
  }
}
