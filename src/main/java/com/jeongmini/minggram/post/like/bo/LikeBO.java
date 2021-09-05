package com.jeongmini.minggram.post.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeongmini.minggram.post.like.dao.LikeDAO;
import com.jeongmini.minggram.post.like.model.Like;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public boolean addLikeCount(int userId, String userName, int postId) {	
		if(this.getLikeCount(userId, postId)) { //라이크인 상태
			likeDAO.deleteLikeCount(userId, postId);
			return false;
		} else {
			likeDAO.insertLikeCount(userId, userName, postId);
			return true;
		}
	}
	
	public boolean getLikeCount(int userId, int postId) {
		int count = likeDAO.selectLikeCount(userId, postId);
		
		if(count >= 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public int gettotalLikeCount(int postId) {
		int count = likeDAO.selectTotalLikeCount(postId);
		
		return count;
	}
	
	public Like getLikeUser(int postId) {
		return likeDAO.selectLikeUser(postId);
	}
	
	public int deletePostLike(int postId) {
		return likeDAO.deletePostLike(postId);
	}

}
