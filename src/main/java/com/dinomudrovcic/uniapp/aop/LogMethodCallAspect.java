package com.dinomudrovcic.uniapp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogMethodCallAspect {

    @Before("execution(* com.dinomudrovcic.uniapp.service..*(..))")
    public void before(JoinPoint joinPoint) {
        log.info("{} method called with arguments {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* com.dinomudrovcic.uniapp.service..*(..))",
        returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        log.info("{} method returned {}", joinPoint.getSignature().getName(), result);
    }

}
