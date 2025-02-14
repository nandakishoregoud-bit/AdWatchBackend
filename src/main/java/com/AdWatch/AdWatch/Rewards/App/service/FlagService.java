package com.AdWatch.AdWatch.Rewards.App.service;

public interface FlagService {

	public void flagComment(Long questionId, Long commentId,Long userId, String reason);
	
	public void flagQuestion(Long questionId, Long userId, String reason);
	
	void unflagComment(Long commentId);
	
	void unflagQuestion(Long questionId);
}
