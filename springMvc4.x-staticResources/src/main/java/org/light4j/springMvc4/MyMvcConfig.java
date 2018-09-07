package org.light4j.springMvc4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
// @EnableWebMvc开启SpringMvc支持，若无此句，重写WebMvcConfigurerAdapter的方法无效。
@EnableWebMvc
@ComponentScan("org.light4j.springMvc4")
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
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 程序的静态文件(js,css,图片)等需要直接访问，通过addResourceHandler即可实现
		// addResourceLocations指的是文件放置的目录，addResourceHandler指的是对外暴露的访问路径。
		// 如果注释掉下面这行代码，直接访问[http://localhost:8080/springMvc4.x-staticResources/assets/js/jquery.js]会提示404
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");// ③
	}
}