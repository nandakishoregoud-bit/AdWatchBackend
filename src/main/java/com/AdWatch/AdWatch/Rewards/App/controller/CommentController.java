package com.AdWatch.AdWatch.Rewards.App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AdWatch.AdWatch.Rewards.App.Dto.CommentDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.CommentsDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.DTOConverter;
import com.AdWatch.AdWatch.Rewards.App.Dto.QuestionCommentDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.UserDTO;
import com.AdWatch.AdWatch.Rewards.App.entity.Comment;
import com.AdWatch.AdWatch.Rewards.App.entity.Question;
import com.AdWatch.AdWatch.Rewards.App.entity.User;
import com.AdWatch.AdWatch.Rewards.App.service.CommentService;
import com.AdWatch.AdWatch.Rewards.App.service.LikeService;
import com.AdWatch.AdWatch.Rewards.App.service.QuestionService;
import com.AdWatch.AdWatch.Rewards.App.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
public class CommentController {

	@Autowired
	private LikeService likeService;
	
    private final CommentService commentService;
    private final QuestionService questionService;
    private final UserService userService;

    public CommentController(CommentService commentService, QuestionService questionService, UserService userService) {
        this.commentService = commentService;
        this.questionService = questionService;
        this.userService = userService;
    }

    //To get comments for a question without question
    @GetMapping("/{questionId}/comments")
    public List<CommentsDTO> getComments(@PathVariable Long questionId) {
        Question question = findQuestionById(questionId);

        List<Comment> comments = commentService.getCommentsForQuestion(questionId);

        return comments.stream()
                .map(DTOConverter::convertToCommentsDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{questionId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long questionId, @PathVariable Long commentId) {
        Question question = findQuestionById(questionId); // Find the question

        // Get the comment by its ID
        Comment comment = commentService.getCommentById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // Convert Comment entity to CommentDTO
        CommentDTO commentDTO = DTOConverter.convertToCommentDTO(comment); 

        return ResponseEntity.ok(commentDTO); // Return the CommentDTO
    }


    
    //To post a comment for question
    @PostMapping("/{questionId}/comment/{userId}")
    public ResponseEntity<String> postComment(@PathVariable Long questionId, @RequestBody Comment comment, @PathVariable Long userId) {
        System.out.println("Backend API hit: Question ID: " + questionId + ", User ID: " + userId);
        
        Question question = findQuestionById(questionId);
        User user = findUserById(userId);

        // Check if the user is blocked by the question owner 
        if (commentService.isUserBlocked(question.getUser(), user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are blocked from commenting on this user's questions.");
        }

        // Check if the user is globally blocked from posting comments
        if (user.isBlocked()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are blocked from posting comments.");
        }

        // Check for duplicate comment
        if (commentService.isDuplicateComment(userId, questionId, comment.getText())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You have already posted this comment.");
        }

        System.out.println("Received: " + comment);

        comment.setUser(user);
        comment.setQuestion(question);

        commentService.postComment(comment);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment submitted successfully!");
    }


    
    //To like a comment
    @PostMapping("/{commentId}/commentlike/{userId}")
    public ResponseEntity<String> likeComment(@PathVariable Long commentId, @PathVariable Long userId) {
        User user = findUserById(userId);
        String response = likeService.likeComment(commentId, user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{commentId}/commentdislike/{userId}")
    public ResponseEntity<String> dislikeComment(@PathVariable Long commentId, @PathVariable Long userId) {
        User user = findUserById(userId);
        String response = likeService.dislikeComment(commentId, user);
        return ResponseEntity.ok(response);
    }

    // To delete a comment
    @DeleteMapping("/{questionId}/comments/{userId}/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long questionId,
            @PathVariable Long userId,
            @PathVariable Long commentId) {
        Question question = findQuestionById(questionId);
        User user = findUserById(userId);
        
        // Check if the comment exists
        Comment comment = commentService.getCommentById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // Check if the user is the one who posted the comment or if the user is an admin
        if (!comment.getUser().equals(user)) {
            return ResponseEntity.status(403).body("You are not authorized to delete this comment.");
        }

        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted successfully.");
    }

    
    
    private User findUserById(Long userId) {
        return userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Question findQuestionById(Long questionId) {
        return questionService.getQuestion(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

}
