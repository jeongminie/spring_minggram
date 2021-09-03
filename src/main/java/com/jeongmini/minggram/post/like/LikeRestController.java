package com.jeongmini.minggram.post.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeongmini.minggram.post.like.bo.LikeBO;
import com.jeongmini.minggram.post.like.model.Like;

@RestController
@RequestMapping("/post")
public class LikeRestController {
	@Autowired
	private LikeBO likeBO;
	
	@GetMapping("/like")
	public Map<String, Boolean> countLike (
			@RequestParam("postId") int postId,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		//Like like = likeBO.getLikeUser(userId, postId);
		
		boolean likes = likeBO.addLikeCount(userId, userName, postId);
		
		Map<String, Boolean> result = new HashMap<>();
		
		result.put("like", likes);
		
		return result;
		
	}

}
