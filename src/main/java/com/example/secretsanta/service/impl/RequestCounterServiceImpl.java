package com.example.secretsanta.service.impl;

import com.example.secretsanta.service.RequestCounterService;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

@Service
public class RequestCounterServiceImpl implements RequestCounterService {
  private AtomicInteger requestCount = new AtomicInteger(0);

  @Override
  public void incrementRequestCount() {
    requestCount.incrementAndGet();
  }

  @Override
  public int getRequestCount() {
    return requestCount.get();
  }
}
