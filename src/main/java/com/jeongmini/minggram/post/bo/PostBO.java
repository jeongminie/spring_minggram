package com.jeongmini.minggram.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jeongmini.minggram.common.FileManagerService;
import com.jeongmini.minggram.post.comment.bo.CommentBO;
import com.jeongmini.minggram.post.comment.model.Comment;
import com.jeongmini.minggram.post.dao.PostDAO;
import com.jeongmini.minggram.post.like.bo.LikeBO;
import com.jeongmini.minggram.post.like.model.Like;
import com.jeongmini.minggram.post.model.Post;
import com.jeongmini.minggram.post.model.PostWithComments;

@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	public int addPost(int userId, String userName, String content, MultipartFile file) {
		FileManagerService fileManager = new FileManagerService();
		
		String filePath = fileManager.saveFile(userId, file);
		if(filePath == null) {
			return -1;
		}
		
		return postDAO.insertPost(userId, userName, content, filePath);
	}
	
	public int deletePost(int id, int userId) {
		return postDAO.deletePost(id, userId);
	}
	
	public List<PostWithComments> getPostList(int userId) {
		List<Post> postList = postDAO.selectTimeLine();
		//반복문 안에서 만들면 하나만 만들어짐 
		List<PostWithComments> postWithCommentsList = new ArrayList<>();
		
		for(Post post : postList) {
			List<Comment> commnetList = commentBO.getCommentListByPostId(post.getId()); //포스트 리스트 안에 있는 하나하나씩 아이디로 빼오기
			boolean existLike = likeBO.getLikeCount(userId, post.getId());
			int likesCount = likeBO.gettotalLikeCount(post.getId());
			Like likeUser = likeBO.getLikeUser(post.getId());
			
			//포스트와 코멘트를 하나의 묶음으로
			PostWithComments postWithComments = new PostWithComments();
			
			postWithComments.setPost(post);
			postWithComments.setCommentList(commnetList);
			postWithComments.setExistLike(existLike);
			postWithComments.setLikesCount(likesCount);
			postWithComments.setLikeUser(likeUser);
			
			postWithCommentsList.add(postWithComments);
		}
		
		return postWithCommentsList;
	}

}
