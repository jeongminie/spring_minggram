package com.jeongmini.minggram.post;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeongmini.minggram.post.bo.PostBO;
import com.jeongmini.minggram.post.model.PostWithComments;

@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/create_view")
	public String createView(Model model) {
		
		List<PostWithComments> post = postBO.getPostList();
		int count = Collections.frequency(post, post);
		
		model.addAttribute("post", post);
		model.addAttribute("count", count);
		return "post/createView";
	}
}
