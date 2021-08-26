package com.jeongmini.minggram.post.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeongmini.minggram.post.bo.PostBO;
import com.jeongmini.minggram.post.comment.bo.CommentBO;
import com.jeongmini.minggram.post.comment.model.Comment;
import com.jeongmini.minggram.post.model.Post;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentBO commentBO;
	private PostBO postBO;
	
	@GetMapping("/list")
	public String commentListView(Model model){
		List<Comment> comment = commentBO.getComment();
		List<Post> post = postBO.getTimeLine();
		
		List<Object> newList = new ArrayList<Object>();
		newList.addAll(post);
		newList.addAll(comment);
		
		model.addAttribute("comment", comment);
		return "post/createView";
				
		
	}
}
