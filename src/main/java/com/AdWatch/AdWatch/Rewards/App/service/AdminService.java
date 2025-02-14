package com.AdWatch.AdWatch.Rewards.App.service;



import com.AdWatch.AdWatch.Rewards.App.Dto.FlagReasonDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.FlaggedCommentDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.FlaggedQuestionDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.UserProfileDTO;

import java.util.List;

public interface AdminService {
   
	
	List<UserProfileDTO> getAllUsers();
	
    List<FlaggedQuestionDTO> getAllFlaggedQuestions();
    

    List<FlagReasonDTO> getFlagReasonsByQuestionId(Long questionId);
    
    List<FlaggedCommentDTO> getAllFlaggedComments();

    

    List<FlagReasonDTO> getFlagReasonsByCommentId(Long commentId);
    
    boolean blockUser(Long userId);

    boolean unblockUser(Long userId);

    
    List<UserProfileDTO> getAllBlockedUsers();
    
    public void deleteCommentById(Long commentId);
    
    public void deleteQuestionById(Long questionId);
    
    public void unflagQuestionById(Long questionId);
    
    public void unflagCommentById(Long commentId);

}

