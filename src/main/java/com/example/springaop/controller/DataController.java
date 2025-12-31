package com.example.springaop.controller;

import com.example.springaop.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

  private final DataService dataService;

  @Autowired
  public DataController(DataService dataService) {
    this.dataService = dataService;
  }

  @GetMapping("/data")
  public String getData() {
    return dataService.getData();
  }

  @GetMapping("/save")
  public String saveData(@RequestParam String input) {
    return dataService.saveData(input);
  }
}
