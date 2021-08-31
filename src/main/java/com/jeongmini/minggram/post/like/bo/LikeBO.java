package com.jeongmini.minggram.post.like.bo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeongmini.minggram.post.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public int addLikeCount(int userId, int postId) {
		return likeDAO.insertLikeCount(userId, postId);
	}
	
	public boolean getLikeCount(int userId, int postId) {
		int count = likeDAO.selectLikeCount(userId, postId);
		
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}

}
