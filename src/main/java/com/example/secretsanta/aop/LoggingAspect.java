package com.example.secretsanta.aop;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
  private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

  @Pointcut("@annotation(com.example.secretsanta.aop.Logged)")
  private void allServiceMethods() {}

  @Before("allServiceMethods()")
  public void logBefore(final JoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();
    String methodName = joinPoint.getSignature().getName();
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Method: {}(), args: {}", methodName, Arrays.toString(args));
    }
  }

  @AfterReturning(pointcut = "allServiceMethods()", returning = "result")
  public void logAfter(final JoinPoint joinPoint, final Object result) {
    String methodName = joinPoint.getSignature().getName();
    LOGGER.info("Method: {}(), status: {}", methodName, result);
  }

  @AfterThrowing(pointcut = "allServiceMethods()", throwing = "exception")
  public void logException(final JoinPoint joinPoint, final Throwable exception) {
    String methodName = joinPoint.getSignature().getName();
    LOGGER.error("Exception in method: {}(), message: {}", methodName, exception.getMessage());
  }

  @PostConstruct
  public void initAspect() {
    LOGGER.info("Aspect is initialized");
  }

  @PreDestroy
  public void destroyAspect() {
    LOGGER.info("Aspect is destroyed");
  }
}