package com.AdWatch.AdWatch.Rewards.App.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.AdWatch.AdWatch.Rewards.App.entity.Question;
import com.AdWatch.AdWatch.Rewards.App.entity.User;

public interface QuestionService {

	public List<Question> getQuestions(String category);
	
	public Optional<Question> getQuestion(Long id);
	
	public Question postQuestion(Question question);
	
	public boolean hasDuplicateQuestion(Long userId, String title);

	public void deleteQuestion(Long questionId);

	public List<Question> getQuestionsByUser(User user);
	
	public List<Question> getTopQuestionsOfTheDay();
	
	public List<Question> getTopQuestionsOfTheWeek();
	
	public List<Question> getTopQuestionsOfTheMonth();

	
}
