package com.example.wanted.recruitment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RecruitmentResponse {

  private Long id;
  private String companyName;
  private String nation;
  private String location;
  private String position;
  private long reward;
  private String skill;

  public static RecruitmentResponse from(RecruitmentDto recruitmentDto) {

    return RecruitmentResponse.builder()
        .id(recruitmentDto.getId())
        .companyName(recruitmentDto.getCompany().getName())
        .nation(recruitmentDto.getCompany().getNation())
        .location(recruitmentDto.getCompany().getLocation())
        .position(recruitmentDto.getPosition())
        .reward(recruitmentDto.getReward())
        .skill(recruitmentDto.getSkill())
        .build();
  }
}
