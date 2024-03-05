package stepup.study.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    private final ConcurrentHashMap<Class<?>, ZonedDateTime> timing = new ConcurrentHashMap<>();
    @Before("execution(* stepup.study.services.DataTransformationService.*(..))")
    public void logBeforeAllMethods(JoinPoint joinPoint) {
        if (joinPoint.getTarget().getClass().isAnnotationPresent(LogTransformation.class)){
            timing.put(joinPoint.getTarget().getClass(),ZonedDateTime.now());
        }
    }
    @After("execution(* stepup.study.services.DataTransformationService.*(..))")
    public void logAfterAllMethods(JoinPoint joinPoint) throws Throwable {
        if (joinPoint.getTarget().getClass().isAnnotationPresent(LogTransformation.class)){
            log.info(String.format("Starts at: %s, class: %s, input: %s, output: %s",
                    timing.get(joinPoint.getTarget().getClass()),
                    joinPoint.getTarget().getClass().getName(),
                    Arrays.deepToString(joinPoint.getArgs()),
                    ((MethodInvocationProceedingJoinPoint) joinPoint).proceed()
            ));
        }
    }
}