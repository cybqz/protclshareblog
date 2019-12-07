package com.cyb.blogserver.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.common.Tips;

public interface LoginServices {
    
	public Tips login(User user);
	
	public void loginWithQQ(HttpServletRequest request, HttpServletResponse response, HttpSession session);
	
	public Tips afterLoginWithQQ(HttpServletRequest request);
	
	public User loginWithWebChat();
	
	public Tips logout(User user);
}