package com.AdWatch.AdWatch.Rewards.App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import com.AdWatch.AdWatch.Rewards.App.Dto.FlagRequest;
import com.AdWatch.AdWatch.Rewards.App.service.CommentService;
import com.AdWatch.AdWatch.Rewards.App.service.FlagService;
import com.AdWatch.AdWatch.Rewards.App.service.QuestionService;


@RestController
@RequestMapping("/api/")
public class FlagController {

	@Autowired
    private  FlagService flagService;

    

    @PostMapping("flag/{questionId}/comment/{commentId}/{userId}")
    public ResponseEntity<String> flagComment(@PathVariable Long questionId, @PathVariable Long commentId,@PathVariable Long userId, @RequestBody FlagRequest flagRequest) {
        
    	System.out.println("reason : "  + flagRequest.getReason());
    	
    	flagService.flagComment(questionId,commentId,userId, flagRequest.getReason());
        return ResponseEntity.ok("Comment has been flagged with reason: " + flagRequest.getReason());
    }

    @PostMapping("flag/{questionId}/question/{userId}")
    public ResponseEntity<String> flagQuestion(@PathVariable Long questionId,@PathVariable Long userId, @RequestBody FlagRequest flagRequest) {
        flagService.flagQuestion(questionId,userId, flagRequest.getReason());
        return ResponseEntity.ok("Question has been flagged with reason: " + flagRequest.getReason());
    }
    
 // Unflag Comment
    @PostMapping("/comment/unflag/{commentId}")
    public ResponseEntity<String> unflagComment(@PathVariable Long commentId) {
        flagService.unflagComment(commentId);
        return ResponseEntity.ok("Comment has been unflagged.");
    }
    
 // Unflag Question
    @PostMapping("/question/unflag/{questionId}")
    public ResponseEntity<String> unflagQuestion(@PathVariable Long questionId) {
        flagService.unflagQuestion(questionId);
        return ResponseEntity.ok("Question has been unflagged.");
    }
}

