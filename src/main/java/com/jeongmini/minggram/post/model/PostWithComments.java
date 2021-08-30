package com.jeongmini.minggram.post.model;

import java.util.List;

import com.jeongmini.minggram.post.comment.model.Comment;

public class PostWithComments {
	
	//포스트와 코멘트 리스트 묶은걸 저장하는 클래스
	//포스트는 하나씩 이니까 리스트 아님
	private Post post;
	private List<Comment> commentList;
	//댓글은 여러개니까 리스트 
	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	
}
