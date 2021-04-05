package com.sa.SBSecureApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		
		return "home.jsp";
	}
	
	/**below login logout methods are for our custom login logout page in app*/
	@RequestMapping("/login")
	public String loginPage() {
		return "login.jsp";
	}
	
	@RequestMapping("/logout-success")
	public String logoutPage() {
		return "logout.jsp";
	}
}
