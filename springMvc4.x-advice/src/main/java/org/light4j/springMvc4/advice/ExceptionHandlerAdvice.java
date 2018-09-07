package org.light4j.springMvc4.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	/*	@ExceptionHandler 注释的方法可以具有灵活的输入参数：
		  - 异常参数：包括一般的异常或特定的异常（即自定义异常），如果注解没有指定异常类，会默认进行映射。
		  - 请求或响应对象 (Servlet API or Portlet API)： 你可以选择不同的类型，如ServletRequest/HttpServletRequest或PortleRequest/ActionRequest/RenderRequest。
		  - Session对象(Servlet API or Portlet API)： HttpSession或PortletSession。
		  - WebRequest或NativeWebRequest 
		  - Locale
		  - InputStream/Reader 
		  - OutputStream/Writer 
		  - Model
		  
		 方法返回值可以为：
		  - ModelAndView对象
		  - Model对象
		  - Map对象
		  - View对象
		  - String对象
		  - 还有@ResponseBody、HttpEntity<?>或ResponseEntity<?>，以及void
	*/
	// 用于全局处理控制器里面的异常。
	@ExceptionHandler(value = Exception.class)
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("error");// error页面
		modelAndView.addObject("errorMessage", exception.getMessage());
		return modelAndView;
	}

	// @ModelAttribute本来的作用是绑定键值对到Model里，此处是让全局的@RequestMapping都能获得在此处设置的键值对
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("msg", "额外信息");
	}

	// 用来设置WebDataBinder，WebDataBinder用来自动绑定前台请求参数到Model中。
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		// 忽略request请求参数中的id
		webDataBinder.setDisallowedFields("id");
	}
}
