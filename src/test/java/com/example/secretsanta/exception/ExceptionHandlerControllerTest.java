package com.example.secretsanta.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

class ExceptionHandlerControllerTest {
  @InjectMocks private ExceptionHandlerController exceptionHandlerController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testNotFoundException() {
    // Arrange
    NotFoundException exception = new NotFoundException("Not found");
    WebRequest request = mock(WebRequest.class);
    when(request.getDescription(false)).thenReturn("Description");

    // Act
    ResponseEntity<ExceptionDetail> response =
        exceptionHandlerController.notFound(exception, request);

    // Assert
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    ExceptionDetail body = response.getBody();
    assertEquals(new Date().toString(), body.getTimestamp().toString());
    assertEquals("Not found", body.getMessage());
    assertEquals("Description", body.getDetails());
  }

  @Test
  void testBadRequestException() {
    // Arrange
    BadRequestException exception = new BadRequestException("Bad request");
    WebRequest request = mock(WebRequest.class);
    when(request.getDescription(false)).thenReturn("Description");

    // Act
    ResponseEntity<ExceptionDetail> response =
        exceptionHandlerController.badRequest(exception, request);

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    ExceptionDetail body = response.getBody();
    assertEquals(new Date().toString(), body.getTimestamp().toString());
    assertEquals("Bad request", body.getMessage());
    assertEquals("Description", body.getDetails());
  }

  @Test
  void testInternalServiceException() {
    // Arrange
    Exception exception = new Exception("Internal server error");
    WebRequest request = mock(WebRequest.class);
    when(request.getDescription(false)).thenReturn("Description");

    // Act
    ResponseEntity<ExceptionDetail> response =
        exceptionHandlerController.internalServiceException(exception, request);

    // Assert
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    ExceptionDetail body = response.getBody();
    assertEquals(new Date().toString(), body.getTimestamp().toString());
    assertEquals("Internal server error", body.getMessage());
    assertEquals("Description", body.getDetails());
  }
}