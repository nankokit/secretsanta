package com.example.secretsanta.exception;

public class NotFoundException extends RuntimeException {

  public NotFoundException(String message, Long id) {
    super(message + Long.toString(id));
  }

  public NotFoundException(String message) {
    super(message);
  }
}