package org.light4j.springMvc4.web;

import javax.servlet.http.HttpServletRequest;

import org.light4j.springMvc4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // ①
@RequestMapping("/anno") //②
public class DemoAnnoController {

    @RequestMapping(produces = "text/plain;charset=UTF-8")  // ③
    public @ResponseBody String index(HttpServletRequest request) { // ④
        return "url:" + request.getRequestURL() + " can access";
    }

    // ⑤
	@RequestMapping(value = "/pathvar/{str}", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String demoPathVar(@PathVariable String str, // ③
			HttpServletRequest request) {
		// 请求【http://localhost/springMvc4.x-pathMatchParameter/anno/pathvar/xxx.yy】时，str得到的值为xxx，"."后面的yy被忽略
		// 可以在MyMvcConfig中复写configurePathMatch方法，设置【configurer.setUseSuffixPatternMatch(false);】
		return "url:" + request.getRequestURL() + " can access,str: " + str;
	}

    @RequestMapping(value = "/requestParam", produces = "text/plain;charset=UTF-8") //⑥
    public @ResponseBody String passRequestParam(Long id,
            HttpServletRequest request) {

        return "url:" + request.getRequestURL() + " can access,id: " + id;
    }

    @RequestMapping(value = "/obj", produces = "application/json;charset=UTF-8")//⑦
    @ResponseBody // ⑧
    public String passObj(DemoObj obj, HttpServletRequest request) {

         return "url:" + request.getRequestURL() 
                    + " can access, obj id: " + obj.getId()+" obj name:" + obj.getName();

    }

    @RequestMapping(value = { "/name1", "/name2" }, produces = "text/plain;charset=UTF-8")//⑨
    public @ResponseBody String remove(HttpServletRequest request) {

        return "url:" + request.getRequestURL() + " can access";
    }
}