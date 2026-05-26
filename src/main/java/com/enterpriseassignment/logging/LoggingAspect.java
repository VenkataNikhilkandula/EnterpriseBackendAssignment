package com.enterpriseassignment.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    
    @Pointcut("execution(* com.projectmanagement.controller..*(..))")
    public void controllerLayer() {
    }

    
    @Pointcut("execution(* com.projectmanagement.service..*(..))")
    public void serviceLayer() {
    }

    
    @Pointcut("execution(* com.projectmanagement.repository..*(..))")
    public void repositoryLayer() {
    }

    
    @Before("controllerLayer() || serviceLayer()")
    public void logMethodEntry(JoinPoint joinPoint) {

        log.info(
                "Entering Method: {}.{}() with arguments = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs())
        );
    }

    
    @AfterReturning(
            pointcut =
                    "controllerLayer() || serviceLayer()",
            returning = "result"
    )
    public void logMethodExit(
            JoinPoint joinPoint,
            Object result) {

        log.info(
                "Exiting Method: {}.{}() with result = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                result
        );
    }

    
    @AfterThrowing(
            pointcut =
                    "controllerLayer() || serviceLayer()",
            throwing = "exception"
    )
    public void logException(
            JoinPoint joinPoint,
            Exception exception) {

        log.error(
                "Exception in {}.{}() with message = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                exception.getMessage(),
                exception
        );
    }

    
    @Around("serviceLayer()")
    public Object logExecutionTime(
            ProceedingJoinPoint joinPoint)
            throws Throwable {

        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long executionTime =
                System.currentTimeMillis() - startTime;

        log.info(
                "Execution Time: {}.{}() executed in {} ms",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                executionTime
        );

        return result;
    }

    
    @Before("repositoryLayer()")
    public void logRepositoryAccess(
            JoinPoint joinPoint) {

        log.debug(
                "Repository Access: {}.{}()",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName()
        );
    }
}