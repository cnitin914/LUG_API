package com.lug;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MethodAspect {

	@Before("execution(* com.lug.controller.UserController.*(..))")
	public void start(JoinPoint j) {
		System.out.println("From aspectj start");
	}
	
	@After("execution(* com.lug.controller.UserController.*(..))")
	public void end(JoinPoint j) {
		System.out.println("From aspectj end");
	}
	
	@AfterReturning(pointcut="execution(* com.lug.controller.UserController.*(..))",returning="result")
	public void afterReturn(JoinPoint j, Object result) {
		System.out.println("From aspectj after returning");
		System.out.println("Output = " + result);
	}
}
