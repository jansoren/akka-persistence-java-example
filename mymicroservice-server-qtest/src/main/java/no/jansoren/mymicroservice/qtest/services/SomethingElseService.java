package no.jansoren.mymicroservice.qtest.services;

import java.lang.String;

public class SomethingElseService {
  public String doSomethingElse() {
    return "http://localhost:8080/somethingelse/do";
  }

  public String getSomethingElse() {
    return "http://localhost:8080/somethingelse/get";
  }
}
