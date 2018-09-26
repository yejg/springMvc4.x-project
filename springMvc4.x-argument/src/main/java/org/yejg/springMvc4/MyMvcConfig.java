package org.yejg.springMvc4;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.yejg.springMvc4.resolver.UserResolver;

@Configuration
@EnableWebMvc
@ComponentScan("org.yejg.springMvc4")
public class MyMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		super.addArgumentResolvers(argumentResolvers);
		// 添加自定义参数解析器，使用场景：从cookie中提取User对象，并塞到action/RequestMapping 的方法中
		argumentResolvers.add(new UserResolver());
	}

	@Bean
	public MappingJackson2HttpMessageConverter converter() {
		// 使用 @ResponseBody 返回对象时，需要告诉springmvc用什么转化器把对象转json
		// 使用MappingJackson2HttpMessageConverter需要依赖jackson-databind
		return new MappingJackson2HttpMessageConverter();
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}
}