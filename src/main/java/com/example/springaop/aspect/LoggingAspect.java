package com.example.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

  private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  // Around advice: Most powerful, runs around the method. Can control execution.
  @Around("execution(* com.example.springaop.service.DataService.*(..))")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();

    logger.info("Around (Before) execution: {}", joinPoint.getSignature().getName());

    try {
      // Proceed with original method execution
      Object result = joinPoint.proceed();

      long timeTaken = System.currentTimeMillis() - startTime;
      logger.info("Around (After) execution: {} took {} ms", joinPoint.getSignature().getName(), timeTaken);

      return "something else";
    } catch (Throwable e) {
      logger.error("Around (After Throwing): {} failed", joinPoint.getSignature().getName());
      throw e;
    }
  }
}
