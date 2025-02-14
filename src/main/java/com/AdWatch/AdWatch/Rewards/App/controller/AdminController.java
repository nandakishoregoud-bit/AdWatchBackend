package com.AdWatch.AdWatch.Rewards.App.controller;


import com.AdWatch.AdWatch.Rewards.App.Dto.FlagReasonDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.FlaggedCommentDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.FlaggedQuestionDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.UserProfileDTO;
import com.AdWatch.AdWatch.Rewards.App.entity.User;
import com.AdWatch.AdWatch.Rewards.App.service.AdminService;
import com.AdWatch.AdWatch.Rewards.App.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public List<UserProfileDTO> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/flagged-questions")
    public List<FlaggedQuestionDTO> getAllFlaggedQuestions() {
        return adminService.getAllFlaggedQuestions();
    }
    
    @GetMapping("/flagged-questions/{questionId}/reasons")
    public List<FlagReasonDTO> getFlagReasonsByQuestionId(@PathVariable Long questionId) {
        return adminService.getFlagReasonsByQuestionId(questionId);
    }
    
    @DeleteMapping("/flagged-questions/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
    	
    	System.out.println("question id : " + questionId);
    	
        adminService.deleteQuestionById(questionId);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/flagged-questions/{questionId}/unflag")
    public ResponseEntity<Void> unflagQuestion(@PathVariable Long questionId) {
        adminService.unflagQuestionById(questionId);
        return ResponseEntity.ok().build();
    }

    

    
    @GetMapping("/flagged-comments")
    public List<FlaggedCommentDTO> getAllFlaggedComments() {
        return adminService.getAllFlaggedComments();
    }

    
    @GetMapping("/flagged-comments/{commentId}/reasons")
    public List<FlagReasonDTO> getFlagReasonsByCommentId(@PathVariable Long commentId) {
        return adminService.getFlagReasonsByCommentId(commentId);
    }
    
    @DeleteMapping("/flagged-comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        adminService.deleteCommentById(commentId);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/flagged-comments/{commentId}/unflag")
    public ResponseEntity<Void> unflagComment(@PathVariable Long commentId) {
        adminService.unflagCommentById(commentId);
        return ResponseEntity.ok().build();
    }

    

    @PutMapping("/block-user/{userId}")
    public ResponseEntity<String> blockUser(@PathVariable Long userId) {
        boolean isBlocked = adminService.blockUser(userId);
        if (isBlocked) {
            return ResponseEntity.ok("User with ID " + userId + " has been blocked successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or already blocked.");
        }
    }
    
    @PutMapping("/unblock-user/{userId}")
    public ResponseEntity<String> unblockUser(@PathVariable Long userId) {
        boolean isUnblocked = adminService.unblockUser(userId);
        if (isUnblocked) {
            return ResponseEntity.ok("User with ID " + userId + " has been unblocked successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or already unblocked.");
        }
    }

    @GetMapping("/blocked-users")
    public ResponseEntity<List<UserProfileDTO>> getAllBlockedUsers() {
        List<UserProfileDTO> blockedUsers = adminService.getAllBlockedUsers();
        return ResponseEntity.ok(blockedUsers);
    }


}
