package com.example.wanted.global.exception;

import static com.example.wanted.global.exception.type.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.example.wanted.global.exception.type.ErrorCode.INVALID_REQUEST;

import com.example.wanted.global.exception.model.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(RecruitmentException.class)
  public ResponseEntity<?> customerExceptionHandler(RecruitmentException exception) {
    ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(),
        exception.getErrorMessage());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CompanyException.class)
  public ResponseEntity<?> companyExceptionHandler(CompanyException exception) {
    ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(),
        exception.getErrorMessage());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MemberException.class)
  public ResponseEntity<?> memberExceptionHandler(MemberException exception) {
    ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(),
        exception.getErrorMessage());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HistoryException.class)
  public ResponseEntity<?> historyExceptionHandler(HistoryException exception) {
    ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(),
        exception.getErrorMessage());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<?> handleDataIntegrityViolationException() {
    ErrorResponse errorResponse = new ErrorResponse(INVALID_REQUEST,
        INVALID_REQUEST.getDescription());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> exceptionHandler() {
    ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR,
        INTERNAL_SERVER_ERROR.getDescription());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
