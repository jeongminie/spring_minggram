package com.jeongmini.minggram.post.like.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jeongmini.minggram.post.like.model.Like;

@Repository
public interface LikeDAO {
	
	public int insertLikeCount (@Param("userId") int userId,
			@Param("postId") int postId);
	
	public List<Like> selectLikeCount (@Param("userId") int userId,
			@Param("postId") int postId);
	

}
