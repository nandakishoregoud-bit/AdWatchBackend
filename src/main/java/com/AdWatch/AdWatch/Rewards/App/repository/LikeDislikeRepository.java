package com.AdWatch.AdWatch.Rewards.App.repository;

import com.AdWatch.AdWatch.Rewards.App.entity.LikeDislike;
import com.AdWatch.AdWatch.Rewards.App.entity.User;
import com.AdWatch.AdWatch.Rewards.App.entity.Question;
import com.AdWatch.AdWatch.Rewards.App.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeDislikeRepository extends JpaRepository<LikeDislike, Long> {

    Optional<LikeDislike> findByUserAndQuestion(User user, Question question);

    Optional<LikeDislike> findByUserAndComment(User user, Comment comment);
}
