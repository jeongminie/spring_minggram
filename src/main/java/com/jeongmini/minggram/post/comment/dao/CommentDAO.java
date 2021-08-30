package com.jeongmini.minggram.post.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jeongmini.minggram.post.comment.model.Comment;

@Repository
public interface CommentDAO {
	
	public int insertComment(
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("postId") int postId,
			@Param("comment") String comment);
	
	public List<Comment> selectCommentListByPostId(
			@Param("postId") int postId);

}
