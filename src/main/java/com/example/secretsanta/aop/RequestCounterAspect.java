package com.example.secretsanta.aop;

import com.example.secretsanta.service.RequestCounterService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestCounterAspect {
  private RequestCounterService requestCounterService;

  public RequestCounterAspect(RequestCounterService requestCounterService) {
    this.requestCounterService = requestCounterService;
  }

  @Around(
      "@within(com.example.secretsanta.aop.RequestStats) ||"
          + " @annotation(com.example.secretsanta.aop.RequestStats)")
  public Object incrementRequestStats(ProceedingJoinPoint joinPoint) throws Throwable {
    requestCounterService.incrementRequestCount();
    return joinPoint.proceed();
  }
}