package com.AdWatch.AdWatch.Rewards.App.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdWatch.AdWatch.Rewards.App.entity.Comment;
import com.AdWatch.AdWatch.Rewards.App.entity.Flag;
import com.AdWatch.AdWatch.Rewards.App.entity.Question;
import com.AdWatch.AdWatch.Rewards.App.entity.User;
import com.AdWatch.AdWatch.Rewards.App.repository.BlockedUserRepository;
import com.AdWatch.AdWatch.Rewards.App.repository.CommentRepository;
import com.AdWatch.AdWatch.Rewards.App.repository.FlagRepository;
import com.AdWatch.AdWatch.Rewards.App.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private BlockedUserRepository blockedUserRepository;

	@Autowired
	private  FlagRepository flagRepository;
	@Override
	 public List<Comment> getCommentsForQuestion(Long questionId) {
        return commentRepository.findByQuestionId(questionId);
    }

	@Override
	public Comment postComment(Comment comment) {
        return commentRepository.save(comment);
    }
    
	@Override
	public boolean isDuplicateComment(Long userId, Long questionId, String text) {
	    List<Comment> existingComments = commentRepository.findByUserIdAndQuestionId(userId, questionId);
	    return existingComments.stream()
	            .anyMatch(comment -> comment.getText().equalsIgnoreCase(text)); // Case-insensitive check
	}

	@Override
	public boolean isUserBlocked(User owner, User user) {
        return blockedUserRepository.existsByOwnerAndBlockedUser(owner, user);

	}

	@Override
	public void deleteComment(Long commentId) {
	    Comment comment = commentRepository.findById(commentId)
	            .orElseThrow(() -> new RuntimeException("Comment not found"));
	    commentRepository.delete(comment);
	}


	@Override
	public Optional<Comment> getCommentById(Long commentId) {
	    return commentRepository.findById(commentId); // Return Optional directly from repository
	}

}
