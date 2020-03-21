package com.jjundol.aop;

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
	 *  AspectJ 표현식 execution :  맨 앞의 * 은 접근제한자, 맨 뒤의 * 은 클래스 이름과 메서드 이름
	 */
	@Before( "execution(* com.jjundol.aop.servivce.SampleService*.*(..))" )
	public void logBefore() {
		log.info("logBefore ============");
	}
	
}
