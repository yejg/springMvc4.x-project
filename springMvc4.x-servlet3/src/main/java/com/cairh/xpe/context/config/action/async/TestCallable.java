package com.cairh.xpe.context.config.action.async;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@NoLogin
@RestController
public class TestCallable {

	Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/testCallable")
	public Callable<String> testCallable() {
		logger.info("Controller开始执行！");
		Callable<String> callable = new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(5000);

				logger.info("实际工作执行完成！");

				return "succeed!";
			}
		};

		logger.info("Controller执行结束！");
		return callable;
	}

}
