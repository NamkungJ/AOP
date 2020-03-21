package com.jjundol.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect		// 해당 클래스는 Aspect를 구현한 것임을 나타내기 위함
@Log4j
@Component	// Spring에서 Bean으로 인식하기 위함
public class LogAdvice {
	// 로그를 기록하는 일은 반복적이며 핵심적인 로직이 아니지만, 필요는 한 관심사임
	
	/*
	 *  BeforeAdvice를 구현한 메서드에 추가
	 *  AspectJ 표현식 execution :  맨 앞의 * 은 접근제한자, 맨 뒤의 * 은 클래스 이름과 메서드 이름 = PointCut
	 */
	@Before( "execution(* com.jjundol.aop.service.SampleService*.*(..))" )
	public void logBefore() {
		log.info("Advice @Before :  logBefore ============");
	}
	
	@Before( "execution(* com.jjundol.aop.service.SampleService*.doAdd(String, String)) && args(str1, str2)" )
	public void logBeforeWithParam(String str1, String str2) {
		log.info("Advice @Before : logBeforeWithParam str1 : " + str1);
		log.info("Advice @Before : logBeforeWithParam str2 : " + str2);
	}
	
	@AfterThrowing( pointcut = "execution(* com.jjundol.aop.service.SampleService*.*(..))", throwing = "exception" )
	public void logException(Exception exception) {
		log.info("Advice @AfterThrowing :logException Exception!" + exception);	
	}
	
	@Around( "execution(* com.jjundol.aop.service.SampleService*.*(..))" )
	public Object logTime(ProceedingJoinPoint pjp) {
		
		long start = System.currentTimeMillis();
		
		log.info("@Around Target : " + pjp.getTarget());
		log.info("@Around Param : " + pjp.getArgs());
		
		// invoke method
		Object result = null;
		
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		log.info("@Around TIME : " + (end - start));
		
		return result;		
	}
	
}
