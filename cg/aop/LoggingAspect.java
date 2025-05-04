package com.cg.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger =
    		LoggerFactory.getLogger(LoggingAspect.class);
 
	@Before("execution(* com.example.serviceImpl..*(..))")
	public void log2(JoinPoint jp) {
		System.out.println("***logs***"+jp.getSignature().toShortString());
	    logger.info("Executing method: {}", jp.getSignature().toShortString());
	}
	@After("execution(* com.example.serviceImpl..*(..))")
	public void log3(JoinPoint jp) {
		System.out.println("***After***");
	}
}