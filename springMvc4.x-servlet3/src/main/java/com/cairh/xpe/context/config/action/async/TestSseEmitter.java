package com.cairh.xpe.context.config.action.async;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

//@NoLogin
@RestController
public class TestSseEmitter {

	// 第一步访问：http://backend.xpe.com/testSseEmitter
	// 第二步连续访问：http://backend.xpe.com/setSseEmitter
	// 第三步访问：http://backend.xpe.com/completeSseEmitter
	// 可以看到结果，只有当第三步执行后，第一步的访问才算结束。

	Logger logger = LoggerFactory.getLogger(getClass());

	private SseEmitter sseEmitter = new SseEmitter();

	/**
	 * 返回SseEmitter对象
	 * @return
	 */
	@RequestMapping("/testSseEmitter")
	public SseEmitter testSseEmitter() {
		return sseEmitter;
	}

	/**
	 * 向SseEmitter对象发送数据
	 * @return
	 */
	@RequestMapping("/setSseEmitter")
	public String setSseEmitter() {
		try {
			sseEmitter.send(System.currentTimeMillis());
		} catch (IOException e) {
			logger.error("IOException!", e);
			return "error";
		}

		return "Succeed!";
	}

	/**
	 * 将SseEmitter对象设置成完成
	 * @return
	 */
	@RequestMapping("/completeSseEmitter")
	public String completeSseEmitter() {
		sseEmitter.complete();

		return "Succeed!";
	}
}
