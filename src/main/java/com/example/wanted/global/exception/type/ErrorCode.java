package com.example.wanted.global.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

  INTERNAL_SERVER_ERROR("내부 서버에 오류가 발생했습니다."),
  INVALID_REQUEST("잘못된 요청입니다."),

  // recruitment
  INVALID_RECRUITMENT_ID("찾을 수 없는 채용공고 번호입니다."),

  // company
  INVALID_COMPANY_ID("찾을 수 없는 회사 번호입니다."),

  // member
  INVALID_MEMBER_ID("찾을 수 없는 회원 번호입니다."),

  // history
  ALREADY_RECRUITMENT_MEMBER("이미 지원한 공고입니다."),
  ;

  private final String description;
}