package com.jeongmini.minggram.post.like.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface LikeDAO {
	
	public int insertLikeCount (@Param("userId") int userId,
			@Param("postId") int postId);
	
	public int selectLikeCount (
			@Param("userId") int userId,
			@Param("postId") int postId);
	

}
