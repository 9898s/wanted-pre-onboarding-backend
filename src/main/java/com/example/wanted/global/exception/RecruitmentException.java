package com.example.wanted.global.exception;

import com.example.wanted.global.exception.type.ErrorCode;
import lombok.Getter;

@Getter
public class RecruitmentException extends RuntimeException {

  private final ErrorCode errorCode;
  private final String errorMessage;

  public RecruitmentException(ErrorCode errorCode) {
    this.errorCode = errorCode;
    this.errorMessage = errorCode.getDescription();
  }
}
