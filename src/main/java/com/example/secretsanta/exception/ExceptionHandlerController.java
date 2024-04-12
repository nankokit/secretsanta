package com.example.secretsanta.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ExceptionDetail> notFound(NotFoundException excepcion, WebRequest request) {
    ExceptionDetail details =
        new ExceptionDetail(new Date(), excepcion.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ExceptionDetail> badRequest(
      BadRequestException exception, WebRequest request) {
    ExceptionDetail details =
        new ExceptionDetail(new Date(), exception.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionDetail> internalServiceException(
      Exception exception, WebRequest request) {
    ExceptionDetail details =
        new ExceptionDetail(new Date(), exception.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}