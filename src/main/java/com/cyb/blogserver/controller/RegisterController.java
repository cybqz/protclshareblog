package com.cyb.blogserver.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.service.UserServices;

@Controller
@RequestMapping(value="/registerController")
public class RegisterController {
	
	@Autowired
	private UserServices userSerivces;
	
	@RequestMapping(value="/register")
	@ResponseBody
	public Tips register (User user, HttpSession session) {
		Tips tips = new Tips("false", false);
		if(user != null) {
			String username = user.getUserName();
			int sex = user.getSex();
			if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(user.getPassword()) &&
			   (sex == 0 || sex == 1)) {
				//检查用户名是否存在
				List<User> users = userSerivces.selectByUserName(username);
				if(users != null && users.size() > 0) {
					tips.setMsg("用户已存在！");
				}else {
					String url = session.getServletContext().getRealPath("/");
					user.setId(UUID.randomUUID().toString());
					int count = userSerivces.insert(user, url);
					if(count > 0) {
						tips = new Tips("true", true);
					}
				}
			}
		}
		return tips;
	}
}
