package com.example.wanted.recruitment.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class DetailRecruitmentResponse {

  private Long id;
  private String companyName;
  private String nation;
  private String location;
  private String position;
  private long reward;
  private String skill;
  private String content;
  private List<Long> recruitmentIdList;

  public static DetailRecruitmentResponse from(DetailRecruitmentDto detailRecruitmentDto) {

    return DetailRecruitmentResponse.builder()
        .id(detailRecruitmentDto.getId())
        .companyName(detailRecruitmentDto.getCompany().getName())
        .nation(detailRecruitmentDto.getCompany().getNation())
        .location(detailRecruitmentDto.getCompany().getLocation())
        .position(detailRecruitmentDto.getPosition())
        .reward(detailRecruitmentDto.getReward())
        .skill(detailRecruitmentDto.getSkill())
        .content(detailRecruitmentDto.getContent())
        .recruitmentIdList(detailRecruitmentDto.getRecruitmentIdList())
        .build();
  }
}
