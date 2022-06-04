package ua.helper.humanhelper.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LogExecutionTimeAspect {

  private static final Logger LOG = LoggerFactory.getLogger(LogExecutionTimeAspect.class);

  @Around("@annotation(ua.helper.humanhelper.aspect.annotation.LogExecutionTime)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    final StopWatch stopWatch = new StopWatch();

    stopWatch.start();

    Object proceed = joinPoint.proceed();

    stopWatch.stop();

    LOG.info("\"{}\" executed in {} ms", joinPoint.getSignature(), stopWatch.getTotalTimeMillis());

    return proceed;
  }
}
