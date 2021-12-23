package kr.ac.inha.board.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggerAspect {
	@Around("execution(* kr.ac.inha.board.board..controller.*Controller.*(..)) or "
			+ "execution(* kr.ac.inha.board.board..service.*Service.*(..)) or "
			+ "execution(* kr.ac.inha.board.board..mapper.*Mapper.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		String type = "";
		String name = joinPoint.getSignature().getDeclaringTypeName();

		if (name.indexOf("Controller") > -1) {
			type = "Controller : ";
		} else if (name.indexOf("Service") > -1) {
			type = "Service : ";
		} else if (name.indexOf("Mapper") > -1) {
			type = "Mapper : ";
		}

		log.debug("Start => " + type + name + "." + joinPoint.getSignature().getName() + "()");

		Object obj = joinPoint.proceed();

		log.debug("End => " + type + name + "." + joinPoint.getSignature().getName() + "()");

		return obj;
	}
}
