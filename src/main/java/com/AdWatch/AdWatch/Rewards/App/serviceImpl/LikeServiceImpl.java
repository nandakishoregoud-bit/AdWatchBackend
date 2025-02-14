package com.AdWatch.AdWatch.Rewards.App.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdWatch.AdWatch.Rewards.App.entity.Comment;
import com.AdWatch.AdWatch.Rewards.App.entity.LikeDislike;
import com.AdWatch.AdWatch.Rewards.App.entity.Question;
import com.AdWatch.AdWatch.Rewards.App.entity.User;
import com.AdWatch.AdWatch.Rewards.App.repository.CommentRepository;
import com.AdWatch.AdWatch.Rewards.App.repository.LikeDislikeRepository;
import com.AdWatch.AdWatch.Rewards.App.repository.QuestionRepository;
import com.AdWatch.AdWatch.Rewards.App.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService {
	
	 @Autowired
	 private LikeDislikeRepository likeDislikeRepository;
	
	@Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CommentRepository commentRepository;

	@Override
	public String likeQuestion(Long questionId, User user) {
		
		Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        Optional<LikeDislike> existingLike = likeDislikeRepository.findByUserAndQuestion(user, question);

        if (existingLike.isPresent()) {
            if (existingLike.get().getAction().equals("LIKE")) {
                // User already liked the question, so we remove the like
                likeDislikeRepository.delete(existingLike.get());
                question.setLikes(question.getLikes() - 1);
                questionRepository.save(question);
                return "Like removed from the question.";
            } else {
                // User previously disliked, change to like
                existingLike.get().setAction("LIKE");
                question.setLikes(question.getLikes() + 1);
                question.setDislikes(question.getDislikes() - 1);
                likeDislikeRepository.save(existingLike.get());
                questionRepository.save(question);
                return "Question liked!";
            }
        }

        // User has never liked/disliked the question, so we add a new like
        LikeDislike likeDislike = new LikeDislike();
        likeDislike.setUser(user);
        likeDislike.setQuestion(question);
        likeDislike.setAction("LIKE");
        likeDislikeRepository.save(likeDislike);

        question.setLikes(question.getLikes() + 1);
        questionRepository.save(question);

        return "Question liked!";
    }



	@Override
	public String dislikeQuestion(Long questionId, User user) {
		
		Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        Optional<LikeDislike> existingLike = likeDislikeRepository.findByUserAndQuestion(user, question);

        if (existingLike.isPresent()) {
            if (existingLike.get().getAction().equals("DISLIKE")) {
                // User already disliked the question, so we remove the dislike
                likeDislikeRepository.delete(existingLike.get());
                question.setDislikes(question.getDislikes() - 1);
                questionRepository.save(question);
                return "Dislike removed from the question.";
            } else {
                // User previously liked, change to dislike
                existingLike.get().setAction("DISLIKE");
                question.setDislikes(question.getDislikes() + 1);
                question.setLikes(question.getLikes() - 1);
                likeDislikeRepository.save(existingLike.get());
                questionRepository.save(question);
                return "Question disliked!";
            }
        }

        // User has never liked/disliked the question, so we add a new dislike
        LikeDislike likeDislike = new LikeDislike();
        likeDislike.setUser(user);
        likeDislike.setQuestion(question);
        likeDislike.setAction("DISLIKE");
        likeDislikeRepository.save(likeDislike);

        question.setDislikes(question.getDislikes() + 1);
        questionRepository.save(question);

        return "Question disliked!";
    }


	@Override
	public String likeComment(Long commentId, User user) {
		
		Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        Optional<LikeDislike> existingLike = likeDislikeRepository.findByUserAndComment(user, comment);

        if (existingLike.isPresent()) {
            if (existingLike.get().getAction().equals("LIKE")) {
                likeDislikeRepository.delete(existingLike.get());
                comment.setLikes(comment.getLikes() - 1);
                commentRepository.save(comment);
                return "Like removed from the comment.";
            } else {
                existingLike.get().setAction("LIKE");
                comment.setLikes(comment.getLikes() + 1);
                comment.setDislikes(comment.getDislikes() - 1);
                likeDislikeRepository.save(existingLike.get());
                commentRepository.save(comment);
                return "Comment liked!";
            }
        }

        LikeDislike likeDislike = new LikeDislike();
        likeDislike.setUser(user);
        likeDislike.setComment(comment);
        likeDislike.setAction("LIKE");
        likeDislikeRepository.save(likeDislike);

        comment.setLikes(comment.getLikes() + 1);
        commentRepository.save(comment);

        return "Comment liked!";
    }


	@Override
	public String dislikeComment(Long commentId, User user) {
		
		Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        Optional<LikeDislike> existingLike = likeDislikeRepository.findByUserAndComment(user, comment);

        if (existingLike.isPresent()) {
            if (existingLike.get().getAction().equals("DISLIKE")) {
                likeDislikeRepository.delete(existingLike.get());
                comment.setDislikes(comment.getDislikes() - 1);
                commentRepository.save(comment);
                return "Dislike removed from the comment.";
            } else {
                existingLike.get().setAction("DISLIKE");
                comment.setDislikes(comment.getDislikes() + 1);
                comment.setLikes(comment.getLikes() - 1);
                likeDislikeRepository.save(existingLike.get());
                commentRepository.save(comment);
                return "Comment disliked!";
            }
        }

        LikeDislike likeDislike = new LikeDislike();
        likeDislike.setUser(user);
        likeDislike.setComment(comment);
        likeDislike.setAction("DISLIKE");
        likeDislikeRepository.save(likeDislike);

        comment.setDislikes(comment.getDislikes() + 1);
        commentRepository.save(comment);

        return "Comment disliked!";
    }
}

