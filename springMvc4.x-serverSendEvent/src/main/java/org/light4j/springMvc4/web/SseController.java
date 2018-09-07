package org.light4j.springMvc4.web;

import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SseController {
	
	@ResponseBody
	@RequestMapping(value="/push",produces="text/event-stream") //①
	public String push(){
		 Random r = new Random();
        try {
                Thread.sleep(5000);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }   
        return "data:Testing 1,2,3" + r.nextInt() +"\n\n";
	}
	
	@RequestMapping(value = "/pushV2", produces = "text/event-stream") // ①
	public void pushV2(HttpServletResponse response) {
		response.setContentType("text/event-stream");
		response.setCharacterEncoding("utf-8");
		int count = 0;
		while (true) {
			Random r = new Random();
			try {
				Thread.sleep(1000);
				PrintWriter pw = response.getWriter();
				// 如果浏览器直接关闭，需要check一下
				if (pw.checkError()) {
					System.out.println("客户端主动断开连接");
					return;
				}
				pw.write("data:Testing 1,2,3" + r.nextInt() + "\n\n");
				pw.flush();
				count++;
				if(count>5){
					pw.close();
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
