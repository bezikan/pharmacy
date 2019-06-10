package com.example.pharmacy.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MeetingsAspect {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.example.pharmacy.controller.MeetingsController.*(..))")
    public void before(JoinPoint joinPoint) {
        logger.info("aspect");
        logger.info("allowed execution for {}", joinPoint);
    }

}
