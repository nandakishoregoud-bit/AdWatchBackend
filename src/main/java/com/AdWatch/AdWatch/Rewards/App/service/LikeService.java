package com.AdWatch.AdWatch.Rewards.App.service;

import com.AdWatch.AdWatch.Rewards.App.entity.Comment;
import com.AdWatch.AdWatch.Rewards.App.entity.Question;
import com.AdWatch.AdWatch.Rewards.App.entity.User;

public interface LikeService  {
	
	public String likeQuestion(Long questionId, User user);
	
	public String dislikeQuestion(Long questionId, User user);
	
	public String likeComment(Long commentId, User user);
	
	public String dislikeComment(Long commentId, User user);
	
	

}
