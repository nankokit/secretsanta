package com.example.secretsanta.exception;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ExceptionDetail {
  private Date timestamp;
  private String message;
  private String details;
}