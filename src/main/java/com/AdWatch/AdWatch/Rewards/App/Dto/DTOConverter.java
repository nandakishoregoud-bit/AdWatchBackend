package com.AdWatch.AdWatch.Rewards.App.Dto;

import java.time.LocalDateTime;
import java.util.List;

import com.AdWatch.AdWatch.Rewards.App.entity.Comment;
import com.AdWatch.AdWatch.Rewards.App.entity.Question;

public class DTOConverter {
	public static QuestionDTO convertToQuestionDTO(Question question, List<CommentDTO> commentDTOs) {
        // Assuming Question has a getLikes() method
        int likes = question.getLikes(); 
        int dislikes = question.getDislikes();
        return new QuestionDTO(
                question.getId(),
                question.getTitle(),
                question.getDescription(),
                question.getCategory(),
                new UserDTO(question.getUser().getId(), question.getUser().getName()),
                commentDTOs,
                likes , // Pass likes to the constructor
                dislikes,
                question.getCreatedAt(),
                question.getUpdatedAt()
        );
    }

    public static CommentDTO convertToCommentDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getText(),
                new UserDTO(comment.getUser().getId(), comment.getUser().getName()),
                comment.getUpdatedAt()
        );
    }
    
    public static CommentsDTO convertToCommentsDTO(Comment comment) {
        return new CommentsDTO(
                comment.getId(),
                comment.getText(),
                comment.getUser().getId(),
                comment.getUser().getName(),
                comment.getUpdatedAt()
        );
    }
    
    public static QuestionDTO convertToQuestionDTO(Question question) {
        return new QuestionDTO(
            question.getId(),
            question.getTitle(),
            question.getDescription(),
            question.getCategory(),
            new UserDTO(question.getUser().getId(), question.getUser().getName()),
            null,  // No comments
            question.getLikes(),
            question.getDislikes(),
            question.getCreatedAt(),
            question.getUpdatedAt()
        );
    }

   
}

