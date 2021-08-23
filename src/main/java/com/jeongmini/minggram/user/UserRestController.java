package com.jeongmini.minggram.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeongmini.minggram.user.bo.UserBO;
import com.jeongmini.minggram.user.model.User;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private UserBO userBO;
	
	@PostMapping("/sign_up")
	public Map<String, String> signUp(
			@RequestParam("name") String name,
			@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			@RequestParam("email") String email
			) {
		
		Map<String, String> result = new HashMap<>();
		int count = userBO.signUp(name, userName, password, email);
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}	
		return result;
	}
	
	@GetMapping("/is_duplicate_id")
	public Map<String, Boolean> isDuplication(
			@RequestParam("userName") String userName) {
		Map<String, Boolean> result = new HashMap<>();
		if(userBO.existUserName(userName)) {
			result.put("duplication", true);
		} else {
			result.put("duplication", false);
		}
		
		return result;
		
		//result.put("is_duplication", userBO.existUserName(userName));
	}
	
	@PostMapping("/sign_in")
	public Map<String, String> signIn(
			@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			HttpServletRequest request) {
		Map<String, String> result = new HashMap<>();
		User user = userBO.signIn(userName, password);
		
		if(user != null) {
			result.put("result", "success");
			
			HttpSession session = request.getSession();
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("name", user.getName());
		} else {
			result.put("result", "fail");
		}
		
		return result;
		
	}
}