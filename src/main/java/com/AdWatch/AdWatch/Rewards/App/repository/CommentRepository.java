package com.AdWatch.AdWatch.Rewards.App.repository;

import com.AdWatch.AdWatch.Rewards.App.entity.Comment;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByQuestionId(Long questionId);

	List<Comment> findByUserIdAndQuestionId(Long userId, Long questionId);

	@Modifying
    @Transactional
    @Query("UPDATE Comment c SET c.flagged = :flagged WHERE c.id = :commentId")
    void updateFlaggedStatus(Long commentId, boolean flagged);

}
