package com.example.springaop.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DataService {

  public String getData() {
    log.info("Executing getData() method...");
    return "Some Data";
  }

  public String saveData(String data) {
    log.info("Executing saveData() method with args: " + data);
    if ("error".equals(data)) {
      throw new RuntimeException("Simulated Error");
    }
    return "Saved: " + data;
  }
}
