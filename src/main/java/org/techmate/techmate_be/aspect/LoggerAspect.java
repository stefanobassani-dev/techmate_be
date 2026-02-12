package org.techmate.techmate_be.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggerAspect {

//    @Around("execution(* org.techmate.techmate_be.service..*(..))")
    @Around("@annotation(org.techmate.techmate_be.annotation.Logging)")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String fullMethod = className + "." + methodName;

        log.info("-> Entering: {}()", fullMethod);
        try {
            Object result = joinPoint.proceed();
            log.info("<- Exiting: {}()", fullMethod);
            return result;
        } catch (Throwable ex) {
            log.error("Error in {}: {}", fullMethod, ex.getMessage(), ex);
            throw ex;
        }
    }
}
