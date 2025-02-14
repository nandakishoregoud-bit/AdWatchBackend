package com.AdWatch.AdWatch.Rewards.App.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.AdWatch.AdWatch.Rewards.App.entity.Question;
import com.AdWatch.AdWatch.Rewards.App.entity.User;
import com.AdWatch.AdWatch.Rewards.App.repository.QuestionRepository;
import com.AdWatch.AdWatch.Rewards.App.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private  QuestionRepository questionRepository;

	@Override
	public List<Question> getQuestions(String category) {
	    Sort sort = Sort.by(Sort.Direction.DESC, "createdAt"); // Sorting by createdAt DESC
	    
	    if (category != null) {
	        return questionRepository.findByCategory(category, sort); // Apply sorting
	    }
	    return questionRepository.findAll(sort); // Apply sorting to all results
	}


	@Override
	public Optional<Question> getQuestion(Long id) {
        return questionRepository.findById(id);
    }

	@Override
	public Question postQuestion(Question question) {
        return questionRepository.save(question);
    }

	@Override
	// Service
	public boolean hasDuplicateQuestion(Long userId, String title) {
	    List<Question> questions = questionRepository.findByUserIdAndTitle(userId, title);
	    return !questions.isEmpty(); // True if duplicates exist
	}


	//delete
	@Override
	public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

	//get all question of a user
	@Override
	public List<Question> getQuestionsByUser(User user) {
		Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
		return questionRepository.findByUser(user, sort);
		
	}

	@Override
	public List<Question> getTopQuestionsOfTheDay() {
		Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        // Logic to fetch top 10 questions of the day
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        return questionRepository.findTop10ByCreatedAtAfter(startOfDay,sort);
    }

	@Override
    public List<Question> getTopQuestionsOfTheWeek() {
		Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        // Logic to fetch top 10 questions of the week
        LocalDateTime startOfWeek = LocalDate.now().minusWeeks(1).atStartOfDay();
        return questionRepository.findTop10ByCreatedAtAfter(startOfWeek,sort);
    }

	@Override
    public List<Question> getTopQuestionsOfTheMonth() {
		Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        // Logic to fetch top 10 questions of the month
        LocalDateTime startOfMonth = LocalDate.now().minusMonths(1).atStartOfDay();
        return questionRepository.findTop10ByCreatedAtAfter(startOfMonth,sort);
    }
}
