package com.jjundol.aop.service;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SampleServiceImpl implements SampleService {

	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		log.info("Target JointPoint : SampleServiceImpl > doAdd");
		return Integer.parseInt(str1) + Integer.parseInt(str2);
	}

}
