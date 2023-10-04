package com.example.wanted.history.model;

import com.example.wanted.history.entity.History;
import com.example.wanted.member.entity.Member;
import com.example.wanted.recruitment.entity.Recruitment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class HistoryDto {

  private Long id;
  private Member member;
  private Recruitment recruitment;

  public static HistoryDto fromEntity(History history) {

    return HistoryDto.builder()
        .id(history.getId())
        .member(history.getMember())
        .recruitment(history.getRecruitment())
        .build();
  }
}
