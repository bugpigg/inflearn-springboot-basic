package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* com.example..*(..))") // 적용할 부분에 대한 어노테이션, 패키지 밑에 다적용하겠다.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
       try {
           return joinPoint.proceed();
       } finally {
           long finish = System.currentTimeMillis();
           long timeMs = finish - start;
           System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
       }
    }
}
