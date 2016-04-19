package no.jansoren.mymicroservice.qtest.services;

import java.lang.String;

public class SomethingService {
  public String doSomething() {
    return "http://localhost:8080/something/do";
  }

  public String getSomething() {
    return "http://localhost:8080/something/get";
  }
}
