package com.example.wanted.recruitment.model;

import com.example.wanted.company.entity.Company;
import com.example.wanted.recruitment.entity.Recruitment;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class DetailRecruitmentDto {

  private Long id;
  private Company company;
  private String position;
  private long reward;
  private String content;
  private String skill;
  private LocalDateTime createDateTime;
  private LocalDateTime updateDateTime;
  private List<Long> recruitmentIdList;

  public static DetailRecruitmentDto from(Recruitment recruitment,
      List<Long> recruitmentIdList) {

    return DetailRecruitmentDto.builder()
        .id(recruitment.getId())
        .company(recruitment.getCompany())
        .position(recruitment.getPosition())
        .reward(recruitment.getReward())
        .content(recruitment.getContent())
        .skill(recruitment.getSkill())
        .createDateTime(recruitment.getCreateDateTime())
        .updateDateTime(recruitment.getUpdateDateTime())
        .recruitmentIdList(recruitmentIdList)
        .build();
  }
}
