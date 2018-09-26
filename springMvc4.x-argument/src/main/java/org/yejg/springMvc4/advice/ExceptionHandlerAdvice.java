package org.yejg.springMvc4.advice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	// form参数校验 失败异常 处理
	@ResponseBody
	@ExceptionHandler(value = BindException.class)
	public List<ObjectError> exception(BindException exception, HttpServletRequest request) {
		return exception.getAllErrors();
	}

	// 单个参数校验失败异常处理
	@ResponseBody
	@ExceptionHandler(value = ConstraintViolationException.class)
	public List<String> exception(ConstraintViolationException exception, HttpServletRequest request) {
		Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
		
		List<String> msgList = new ArrayList<>();
		for (ConstraintViolation<?> constraintViolation : constraintViolations) {
			msgList.add(constraintViolation.getMessage());
		}
		return msgList;
	}
}
