package com.jeongmini.minggram.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/signIn_view")
	public String signInView() {
		return "user/signIn";
	}
	
	@GetMapping("/signUp_view")
	public String signUnView() {
		return "user/signUp";
	}
	
	@GetMapping("/sign_out")
	public String signOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		session.removeAttribute("name");
		
		return "redirect:/user/signIn_view";
	}
}