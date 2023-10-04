package com.example.wanted.recruitment.controller;

import com.example.wanted.recruitment.model.AddRecruitment;
import com.example.wanted.recruitment.model.EditRecruitment;
import com.example.wanted.recruitment.model.RecruitmentDto;
import com.example.wanted.recruitment.model.RecruitmentResponse;
import com.example.wanted.recruitment.service.RecruitmentService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @PutMapping("/{id}")
  public ResponseEntity<?> editRecruitment(@PathVariable Long id,
      @RequestBody EditRecruitment.Request request) {
    RecruitmentDto recruitmentDto = recruitmentService.editRecruitment(id, request);

    return ResponseEntity.ok().body(EditRecruitment.Response.from(recruitmentDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteRecruitment(@PathVariable Long id) {
    recruitmentService.deleteRecruitment(id);

    return ResponseEntity.ok().body(HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<?> allRecruitment() {
    List<RecruitmentResponse> recruitmentResponseList = recruitmentService.allRecruitment().stream()
        .map(RecruitmentResponse::from)
        .collect(Collectors.toList());

    return ResponseEntity.ok().body(recruitmentResponseList);
  }
}
