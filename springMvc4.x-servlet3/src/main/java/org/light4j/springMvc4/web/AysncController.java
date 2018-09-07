package org.light4j.springMvc4.web;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.light4j.springMvc4.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;


@Controller
public class AysncController {
    @Autowired
    PushService pushService; //①

	@RequestMapping("/callable")
	@ResponseBody
	public Callable<String> callableCall() {
		Callable<String> callable = new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(5000);

				System.out.println("实际工作执行完成！");

				return "succeed!";
			}
		};

		System.out.println("Controller执行结束！");
		return callable;
	}
    
    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredCall() { //②
    	// DeferredResult一般用于异步返回单个结果，起效果类似于返回Callable<String>
        return pushService.getAsyncUpdate();
    }
    
    // Server-Sent Events
    @RequestMapping("/sseEmitter")
    @ResponseBody
	public SseEmitter sseEmitterCall() {
    	// SseEmitter用于异步返回多个结果，直到调用sseEmitter.complete()结束返回
		SseEmitter sseEmitter = new SseEmitter();
		Thread t = new Thread(new TestRun(sseEmitter));
		t.start();
		return sseEmitter;
	}
    
	class TestRun implements Runnable {

		private SseEmitter sseEmitter;
		private int times = 0;

		public TestRun(SseEmitter sseEmitter) {
			this.sseEmitter = sseEmitter;
		}

		@Override
		public void run() {
			while (true) {
				try {
					System.out.println("当前times=" + times);
					sseEmitter.send(System.currentTimeMillis());
					times++;
					Thread.sleep(1000);
					if (times > 4) {
						System.out.println("发送finish事件");
						sseEmitter.send(SseEmitter.event().name("finish").id("6666").data("哈哈"));
						System.out.println("调用complete");
						sseEmitter.complete();
						System.out.println("complete！times=" + times);
						break;
					}
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

