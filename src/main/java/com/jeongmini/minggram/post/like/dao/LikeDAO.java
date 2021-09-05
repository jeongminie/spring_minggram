package com.jeongmini.minggram.post.like.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jeongmini.minggram.post.like.model.Like;


@Repository
public interface LikeDAO {
	
	public int insertLikeCount (@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("postId") int postId);
	
	public int selectLikeCount (
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	public int deleteLikeCount (
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	public int selectTotalLikeCount(
			@Param("postId") int postId);
			
	public Like selectLikeUser(
			@Param("postId") int postId
			);	
	
	public int deletePostLike(
			@Param("postId") int postId);
	
	

}
