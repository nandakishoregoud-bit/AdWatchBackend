package com.AdWatch.AdWatch.Rewards.App.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdWatch.AdWatch.Rewards.App.entity.Comment;
import com.AdWatch.AdWatch.Rewards.App.entity.Flag;
import com.AdWatch.AdWatch.Rewards.App.entity.Question;
import com.AdWatch.AdWatch.Rewards.App.entity.User;
import com.AdWatch.AdWatch.Rewards.App.repository.CommentRepository;
import com.AdWatch.AdWatch.Rewards.App.repository.FlagRepository;
import com.AdWatch.AdWatch.Rewards.App.repository.QuestionRepository;
import com.AdWatch.AdWatch.Rewards.App.service.FlagService;
import com.AdWatch.AdWatch.Rewards.App.service.QuestionService;
import com.AdWatch.AdWatch.Rewards.App.service.UserService;

@Service
public class FlagServiceImpl implements FlagService {

	@Autowired
	private  FlagRepository flagRepository;
	
	@Autowired
    private  CommentRepository commentRepository;
    
	@Autowired
	private  QuestionRepository questionRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private QuestionService questionService;

 
    
	@Override
	public void flagComment(Long questionId, Long commentId,Long userId, String reason) {
		
		 Comment comment = commentRepository.findById(commentId)
	                .orElseThrow(() -> new RuntimeException("Comment not found"));

	        // Save the flag
		 User user = findUserById(userId);
		 Question question = findQuestionById(questionId);
	        Flag flag = new Flag(question,comment,user, reason);
	        flagRepository.save(flag);

	        // Update the comment's flagged status
	        comment.setFlagged(true);
	        commentRepository.save(comment);
	}

	 private Question findQuestionById(Long questionId) {
        return questionService.getQuestion(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

	private User findUserById(Long userId) {
	        return userService.getUserById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	    }

	@Override
	public void flagQuestion(Long questionId,Long userId, String reason) {
		
		 Question question = questionRepository.findById(questionId)
	                .orElseThrow(() -> new RuntimeException("Question not found"));

		 User user = findUserById(userId);
	        // Save the flag
	        Flag flag = new Flag(question,user, reason);
	        flagRepository.save(flag);

	        // Update the question's flagged status
	        question.setFlagged(true);
	        questionRepository.save(question);
	        
	}
	
	@Override
    public void unflagComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // Set the comment as unflagged
        comment.setFlagged(false);
        commentRepository.save(comment);
    }
	
	 @Override
	    public void unflagQuestion(Long questionId) {
	        Question question = questionRepository.findById(questionId)
	                .orElseThrow(() -> new RuntimeException("Question not found"));

	        // Set the question as unflagged
	        question.setFlagged(false);
	        questionRepository.save(question);
	    }

}
