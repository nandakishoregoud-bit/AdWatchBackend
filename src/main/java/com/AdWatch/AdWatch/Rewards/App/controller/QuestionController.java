package com.AdWatch.AdWatch.Rewards.App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AdWatch.AdWatch.Rewards.App.entity.Comment;
import com.AdWatch.AdWatch.Rewards.App.entity.Question;
import com.AdWatch.AdWatch.Rewards.App.entity.User;
import com.AdWatch.AdWatch.Rewards.App.Dto.CommentDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.DTOConverter;
import com.AdWatch.AdWatch.Rewards.App.Dto.QuestionDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.UserDTO;
import com.AdWatch.AdWatch.Rewards.App.service.CommentService;
import com.AdWatch.AdWatch.Rewards.App.service.LikeService;
import com.AdWatch.AdWatch.Rewards.App.service.QuestionService;
import com.AdWatch.AdWatch.Rewards.App.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

	@Autowired
	private LikeService likeService;
	
    private final QuestionService questionService;
    private final UserService userService;
    private final CommentService commentService;

    public QuestionController(QuestionService questionService, UserService userService, CommentService commentService) {
        this.questionService = questionService;
        this.userService = userService;
        this.commentService = commentService;
    }

    
    //To get All questions or with category(optional)
    @GetMapping
    public List<QuestionDTO> getQuestions(@RequestParam(required = false) String category) {
    	
    	System.out.println("reqest resived : " +category);
        List<Question> questions = questionService.getQuestions(category);
        return questions.stream()
                        .map(DTOConverter::convertToQuestionDTO)
                        .collect(Collectors.toList());
    }


    //To post a new question 
    @PostMapping("/post/{userId}")
    public ResponseEntity<String> postQuestion(@RequestBody QuestionDTO questionDTO, @PathVariable Long userId) {
        User user = findUserById(userId);
        
     // Check if the user is blocked
        if (user.isBlocked()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are blocked from posting questions.");
        }

        // Check for duplicate question
        if (questionService.hasDuplicateQuestion(userId, questionDTO.getTitle())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("You have already posted a question with this title.");
        }

        // Create a new Question from the DTO
        Question question = new Question();
        question.setTitle(questionDTO.getTitle());
        question.setDescription(questionDTO.getDescription());
        question.setCategory(questionDTO.getCategory());
        question.setUser(user);

        // Save the question to the database
        Question savedQuestion = questionService.postQuestion(question);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Question successfully posted with ID: " + savedQuestion.getId());
    }


    //To get a question with commets.
    @GetMapping("/{questionId}")
    public QuestionDTO getQuestion(@PathVariable Long questionId) {
    	System.out.println("question ID : " + questionId);
        Question question = findQuestionById(questionId);
        List<Comment> comments = commentService.getCommentsForQuestion(questionId);
        List<CommentDTO> commentDTOs = comments.stream()
                .map(DTOConverter::convertToCommentDTO)
                .collect(Collectors.toList());
        return DTOConverter.convertToQuestionDTO(question, commentDTOs);
    }

    //To delete a post
    @DeleteMapping("/{questionId}/delete/{userId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId, @PathVariable Long userId) {
        Question question = findQuestionById(questionId);
        User user = findUserById(userId);

        if (!question.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are not the owner of this question");
        }

        questionService.deleteQuestion(questionId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Question successfully deleted");
    }

    //To get all Questions for that user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByUser(@PathVariable Long userId) {
        User user = findUserById(userId);
        List<Question> userQuestions = questionService.getQuestionsByUser(user);
        List<QuestionDTO> questionDTOs = userQuestions.stream()
                .map(question -> DTOConverter.convertToQuestionDTO(question, null))
                .collect(Collectors.toList());
        return ResponseEntity.ok(questionDTOs);
 
    }
    
    //To like a question
    @PostMapping("/{questionId}/questionlike/{userId}")
    public ResponseEntity<String> likeQuestion(@PathVariable Long questionId, @PathVariable Long userId) {
        User user = findUserById(userId);
        String response = likeService.likeQuestion(questionId, user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{questionId}/questiondislike/{userId}")
    public ResponseEntity<String> dislikeQuestion(@PathVariable Long questionId, @PathVariable Long userId) {
        User user = findUserById(userId);
        String response = likeService.dislikeQuestion(questionId, user);
        return ResponseEntity.ok(response);
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
