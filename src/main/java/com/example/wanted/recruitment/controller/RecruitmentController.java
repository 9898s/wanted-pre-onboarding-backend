package com.example.wanted.recruitment.controller;

import com.example.wanted.recruitment.model.AddRecruitment;
import com.example.wanted.recruitment.model.RecruitmentDto;
import com.example.wanted.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/recruit")
@RestController
public class RecruitmentController {

  private final RecruitmentService recruitmentService;

  @PostMapping
  public ResponseEntity<?> addRecruitment(@RequestBody AddRecruitment.Request request) {
    RecruitmentDto recruitmentDto = recruitmentService.addRecruitment(request);

    return ResponseEntity.ok().body(AddRecruitment.Response.from(recruitmentDto));
  }
}
