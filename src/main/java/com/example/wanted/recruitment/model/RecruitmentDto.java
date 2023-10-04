package com.example.wanted.recruitment.model;

import com.example.wanted.company.entity.Company;
import com.example.wanted.recruitment.entity.Recruitment;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RecruitmentDto {

  private Long id;
  private Company company;
  private String position;
  private long reward;
  private String content;
  private String skill;
  private LocalDateTime createDateTime;
  private LocalDateTime updateDateTime;

  public static RecruitmentDto fromEntity(Recruitment recruitment) {

    return RecruitmentDto.builder()
        .id(recruitment.getId())
        .company(recruitment.getCompany())
        .position(recruitment.getPosition())
        .reward(recruitment.getReward())
        .content(recruitment.getContent())
        .skill(recruitment.getSkill())
        .createDateTime(recruitment.getCreateDateTime())
        .updateDateTime(recruitment.getUpdateDateTime())
        .build();
  }
}
