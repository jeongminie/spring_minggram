package com.jeongmini.minggram.post.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeongmini.minggram.post.comment.bo.CommentBO;

@RestController
@RequestMapping("/post")
public class CommentRestController {
	@Autowired
	private CommentBO commentBO;
	
	@GetMapping("/comment/create")
	public Map<String, String> commentUpload(
			@RequestParam("postId") int postId,
			@RequestParam("comment") String comment,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		
		int count = commentBO.addComment(userId, userName, postId, comment);
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;

		
	}

}
