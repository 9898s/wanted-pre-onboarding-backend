package com.example.wanted.recruitment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class EditRecruitment {

  @Setter
  @Getter
  public static class Request {

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

    private String position;
    private long reward;
    private String content;
    private String skill;

    public static Response from(RecruitmentDto recruitmentDto) {

      return Response.builder()
          .position(recruitmentDto.getPosition())
          .reward(recruitmentDto.getReward())
          .content(recruitmentDto.getContent())
          .skill(recruitmentDto.getSkill())
          .build();
    }
  }
}
