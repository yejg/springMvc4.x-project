package com.cairh.xpe.context.config.action.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

//@NoLogin
@RestController
public class TestDeferred {
	Logger logger = LoggerFactory.getLogger(getClass());

	private DeferredResult<String> deferredResult = new DeferredResult<>();

	@RequestMapping("/testDeferredResult")
	public DeferredResult<String> testDeferredResult() {
		return deferredResult;
	}

	/**
	 * 对DeferredResult的结果进行设置
	 * @return
	 */
	@RequestMapping("/setDeferredResult")
	public String setDeferredResult() {
		deferredResult.setResult("Test result!");
		return "succeed";
	}
}
