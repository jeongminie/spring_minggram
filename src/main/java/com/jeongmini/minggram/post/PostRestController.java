package com.jeongmini.minggram.post;

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
import org.springframework.web.multipart.MultipartFile;

import com.jeongmini.minggram.post.bo.PostBO;
import com.jeongmini.minggram.post.comment.bo.CommentBO;
import com.jeongmini.minggram.post.like.bo.LikeBO;
import com.jeongmini.minggram.post.model.Post;

@RestController
@RequestMapping("/post")
public class PostRestController {
	@Autowired
	private PostBO postBO;

	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@PostMapping("/create")
	public Map<String, String> create(
			@RequestParam("content") String content,
			HttpServletRequest request,
			@RequestParam(value="file", required=false) MultipartFile file
			) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
	
		int count = postBO.addPost(userId, userName, content, file);
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;

	}
	
	 @GetMapping("/delete") 
	 public Map<String, String> delete(
			 @RequestParam("id") int id, 
			 HttpServletRequest request 
			 ) {
		
		Post post = new Post();
		HttpSession session = request.getSession(); 
		int userId = (Integer)session.getAttribute("userId");
		
		int deletePost = postBO.deletePost(id, userId);
		int deleteLike = likeBO.deletePostLike(id);
		int deleteComment = commentBO.deletePostComment(id);
		
		Map<String, String> result = new HashMap<>();
	 
		if(deletePost == 1 && deleteLike == 1 && deleteComment == 1) { 
			result.put("result", "success"); 
		} else {
			result.put("result", "fail"); 
		} 
		
		return result; 
		}
	 
}
