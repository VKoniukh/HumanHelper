package ua.helper.humanhelper.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Aspect
@Component
public class ControllerLogAspect {

  private static final Logger LOG = LoggerFactory.getLogger(ControllerLogAspect.class);

  private final ObjectMapper objectMapper;

  public ControllerLogAspect(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Pointcut("within(ua.helper.humanhelper.controller..*) ")
  public void pointcut() {}

  @Before("pointcut()")
  public void logMethod(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    final Optional<Annotation> mappingOptional =
        Arrays.stream(signature.getMethod().getDeclaredAnnotations())
            .filter(
                it ->
                    Arrays.stream(it.annotationType().getDeclaredAnnotations())
                        .anyMatch(t -> t.annotationType().equals(RequestMapping.class)))
            .findAny();
    if (mappingOptional.isEmpty()) {
      LOG.info("Error, should not happened. You bad programmer");
      return;
    }

    Map<String, Object> parameters = getParameters(joinPoint);
    final Annotation mapping = mappingOptional.get();

    final RequestMethod method =
        mappingOptional
            .get()
            .annotationType()
            .getDeclaredAnnotation(RequestMapping.class)
            .method()[0];

    final String className = mapping.annotationType().getName();

    try {
      LOG.info(
          "==> path(s): [-], method(s): {}, arguments: {} ",
          method,
          objectMapper.writeValueAsString(parameters));
    } catch (JsonProcessingException e) {
      LOG.error("Error while converting", e);
    }
  }

  @AfterReturning(pointcut = "pointcut()", returning = "entity")
  public void logMethodAfter(JoinPoint joinPoint, ResponseEntity<?> entity) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    RequestMapping mapping = signature.getMethod().getAnnotation(RequestMapping.class);

    try {
      LOG.info(
          "<== path(s): {}, method(s): {}, retuning: {}",
          mapping.path(),
          mapping.method(),
          objectMapper.writeValueAsString(entity));
    } catch (JsonProcessingException e) {
      LOG.error("Error while converting", e);
    }
  }

  private Map<String, Object> getParameters(JoinPoint joinPoint) {
    CodeSignature signature = (CodeSignature) joinPoint.getSignature();

    HashMap<String, Object> map = new HashMap<>();

    String[] parameterNames = signature.getParameterNames();

    for (int i = 0; i < parameterNames.length; i++) {
      map.put(parameterNames[i], joinPoint.getArgs()[i]);
    }

    return map;
  }
}
