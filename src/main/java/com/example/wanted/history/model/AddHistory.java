package com.example.wanted.history.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AddHistory {

  @Getter
  public static class Request {

    private Long recruitmentId;
    private Long memberId;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  @Getter
  public static class Response {

    private Long recruitmentId;
    private Long memberId;

    public static Response from(HistoryDto historyDto) {

      return Response.builder()
          .recruitmentId(historyDto.getRecruitment().getId())
          .memberId(historyDto.getMember().getId())
          .build();
    }
  }
}
