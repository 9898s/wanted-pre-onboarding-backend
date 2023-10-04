package com.example.wanted.global.exception.model;

import com.example.wanted.global.exception.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ErrorResponse {

  private ErrorCode errorCode;
  private String errorMessage;
}