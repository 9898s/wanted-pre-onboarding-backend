package com.example.wanted.recruitment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AddRecruitment {

  @Getter
  public static class Request {

    private Long companyId;
    private String position;
    private long reward;
    private String content;
    private String skill;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  @Getter
  public static class Response {

    private Long companyId;
    private String position;
    private long reward;
    private String content;
    private String skill;

    public static Response from(RecruitmentDto recruitmentDto) {

      return Response.builder()
          .companyId(recruitmentDto.getCompany().getId())
          .position(recruitmentDto.getPosition())
          .reward(recruitmentDto.getReward())
          .content(recruitmentDto.getContent())
          .skill(recruitmentDto.getSkill())
          .build();
    }
  }
}
