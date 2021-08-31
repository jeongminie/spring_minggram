package com.jeongmini.minggram.post.like.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeongmini.minggram.post.like.dao.LikeDAO;
import com.jeongmini.minggram.post.like.model.Like;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public int addLikeCount(int userId, int postId) {
		return likeDAO.insertLikeCount(userId, postId);
	}
	
	public List<Like> getLikeCount(int userId, int postId) {
		return likeDAO.selectLikeCount(userId, postId);

	}

}
