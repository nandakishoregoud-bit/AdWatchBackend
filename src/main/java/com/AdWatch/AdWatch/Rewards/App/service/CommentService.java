package com.AdWatch.AdWatch.Rewards.App.service;

import java.util.List;
import java.util.Optional;

import com.AdWatch.AdWatch.Rewards.App.entity.Comment;
import com.AdWatch.AdWatch.Rewards.App.entity.User;

public interface CommentService {

	public List<Comment> getCommentsForQuestion(Long questionId);
	
	 public Comment postComment(Comment comment);

	public boolean isDuplicateComment(Long userId, Long questionId, String text);
	
	
	
	public boolean isUserBlocked(User owner, User user);

	public void deleteComment(Long commentId);

	// Add the method to get a comment by its ID
    Optional<Comment> getCommentById(Long commentId);
	
}
