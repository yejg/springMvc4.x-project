package org.yejg.springMvc4.action;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.yejg.springMvc4.form.CheckForm;
import org.yejg.springMvc4.model.User;

@Validated
@RestController
public class ArgumentResolverController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/index")
	public String hello() {
		return "index";
	}

	// 入参的User通过UserResolver注入进来
	@ResponseBody
	@RequestMapping("/testArgumentResolver")
	public User testArgumentResolver(User user, String userName) {
		logger.info(user.getUserName() + "\t" + userName);
		return user;
	}

	@ResponseBody
	@RequestMapping("/testArgumentCheck")
	public User testArgumentCheck(User user, @NotNull(message = "userName不能为空") String userName, @NotNull String address) {// @Validated写在参数前无效，一定要写在类头上
		logger.info(user.getUserName() + "\t" + userName);
		return user;
	}

	@ResponseBody
	@RequestMapping("/testArgumentCheckForm")
	public User testArgumentCheckForm(User user, @Validated CheckForm form) {// @Validated不可少，否则不会校验，即使类头上写了也没用
		logger.info(user.getUserName() + "\t" + form.getId());
		return user;
	}
}
