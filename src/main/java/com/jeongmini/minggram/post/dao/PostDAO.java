package com.jeongmini.minggram.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jeongmini.minggram.post.model.Post;

@Repository
public interface PostDAO {
	
	public int insertPost(
			@Param("userId") int id,
			@Param("userName") String userName,
			@Param("content") String content,
			@Param("imagePath") String imagePath
			);
	
	public List<Post> selectTimeLine();
	

}
