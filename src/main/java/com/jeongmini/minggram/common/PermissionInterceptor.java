package com.jeongmini.minggram.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PermissionInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle (HttpServletRequest request,
			HttpServletResponse response,
			Object handler) throws IOException {
		HttpSession session = request.getSession();
		
		String uri = request.getRequestURI();
		
		if(session.getAttribute("userId") == null) {
			if(uri.startsWith("/post")) {
				response.sendRedirect("/user/signIn_view");
				return false;
				}
			
		} else {
			if(uri.startsWith("/user")) {
				response.sendRedirect("/post/create_view");
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response,
			Object handler, ModelAndView modelAndView) {
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response,
			Object handler, Exception ex) {
		
	}

}
