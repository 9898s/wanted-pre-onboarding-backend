package com.example.wanted.history.controller;

import com.example.wanted.history.model.AddHistory;
import com.example.wanted.history.model.HistoryDto;
import com.example.wanted.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/history")
@RestController
public class HistoryController {

  private final HistoryService historyService;

  @PostMapping
  public ResponseEntity<?> addHistory(@RequestBody AddHistory.Request request) {
    HistoryDto historyDto = historyService.addHistory(request);

    return ResponseEntity.ok().body(AddHistory.Response.from(historyDto));
  }
}
