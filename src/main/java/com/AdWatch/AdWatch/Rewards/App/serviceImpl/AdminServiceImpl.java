package com.AdWatch.AdWatch.Rewards.App.serviceImpl;


import com.AdWatch.AdWatch.Rewards.App.Dto.FlagReasonDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.FlaggedCommentDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.FlaggedQuestionDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.QuestionDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.UserProfileDTO;
import com.AdWatch.AdWatch.Rewards.App.entity.Comment;
import com.AdWatch.AdWatch.Rewards.App.entity.Question;
import com.AdWatch.AdWatch.Rewards.App.entity.User;
import com.AdWatch.AdWatch.Rewards.App.repository.CommentRepository;
import com.AdWatch.AdWatch.Rewards.App.repository.QuestionRepository;
import com.AdWatch.AdWatch.Rewards.App.repository.UserRepository;
import com.AdWatch.AdWatch.Rewards.App.service.AdminService;
import com.AdWatch.AdWatch.Rewards.App.service.BlockedUserService;
import com.AdWatch.AdWatch.Rewards.App.repository.FlagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FlagRepository flagRepository;

    @Override
    public List<UserProfileDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserProfileDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCoins(),
                user.getAdsWatched(),
                user.getReferralCode(),
                user.getReferredBy(),
                user.getProfilePicUrl(),
                user.isBlocked()
        )).collect(Collectors.toList());
    }

    @Override
    public List<FlaggedQuestionDTO> getAllFlaggedQuestions() {
        return questionRepository.findByFlaggedTrue().stream().map(question ->
                new FlaggedQuestionDTO(
                        question.getId(),
                        question.getTitle(),
                        question.getDescription(),
                        question.getCategory(),
                        question.getLikes(),
                        question.getDislikes(),
                        question.isFlagged(),
                        question.getCreatedAt(),
                        question.getUpdatedAt(),
                        question.getUserDTO()
                )).collect(Collectors.toList());
    }
    
    @Override
    public List<FlagReasonDTO> getFlagReasonsByQuestionId(Long questionId) {
        return flagRepository.findByQuestionId(questionId).stream().map(flag ->
                new FlagReasonDTO(
                        flag.getId(),
                        flag.getReason(),
                        flag.getUser().getId(),
                        flag.getUser().getName()
                )).collect(Collectors.toList());
    }
    
    @Transactional
    @Override
    public void deleteQuestionById(Long questionId) {
    	
    	// First, delete all related flags
        flagRepository.deleteByQuestionId(questionId);
        
        questionRepository.deleteById(questionId);
        
    }
    
    @Transactional
    @Override
    public void unflagQuestionById(Long questionId) {
        
    	// Delete all flags associated with this question
        flagRepository.deleteByQuestionId(questionId);

        // Check if the question still has any flags
        boolean hasFlags = flagRepository.existsByQuestionId(questionId);

        if (!hasFlags) {
            // If no flags remain, update the question's flagged status
        	questionRepository.updateFlaggedStatus(questionId, false);
;
        }
    }
    
    
    @Override
    public List<FlaggedCommentDTO> getAllFlaggedComments() {
        return flagRepository.findFlaggedComments().stream().map(flag ->
                new FlaggedCommentDTO(
                        flag.getComment().getId(),
                        flag.getComment().getText(),
                        flag.getComment().getCreatedAt(),
                        flag.getUser().getId(),
                        flag.getUser().getName(),
                        flag.getReason()
                )).collect(Collectors.toList());
    }

    
    
    @Override
    public List<FlagReasonDTO> getFlagReasonsByCommentId(Long commentId) {
        return flagRepository.findByCommentId(commentId).stream().map(flag ->
                new FlagReasonDTO(
                        flag.getId(),
                        flag.getReason(),
                        flag.getUser().getId(),
                        flag.getUser().getName()
                )).collect(Collectors.toList());
    }
    
    @Transactional
    @Override
    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Transactional
    @Override
    public void unflagCommentById(Long commentId) {
        
    	// Delete all flags associated with this comment
        flagRepository.deleteByCommentId(commentId);

        // Check if the comment still has any flags
        boolean hasFlags = flagRepository.existsByCommentId(commentId);

        if (!hasFlags) {
            // If no flags remain, update the comment's flagged status
            commentRepository.updateFlaggedStatus(commentId, false);
        }
        
    }

    
    
    @Override
    public boolean blockUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!user.isBlocked()) { // Check if the user is already blocked
                user.setBlocked(true);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    
    @Override
    public boolean unblockUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.isBlocked()) { // Check if the user is already unblocked
                user.setBlocked(false);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    
    @Override
    public List<UserProfileDTO> getAllBlockedUsers() {
        List<User> blockedUsers = userRepository.findByBlockedTrue();
        
        return blockedUsers.stream()
            .map(user -> new UserProfileDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCoins(),
                user.getAdsWatched(),
                user.getReferralCode(),
                user.getReferredBy(),
                user.getProfilePicUrl(),
                user.isBlocked()
            ))
            .collect(Collectors.toList());
    }


}
