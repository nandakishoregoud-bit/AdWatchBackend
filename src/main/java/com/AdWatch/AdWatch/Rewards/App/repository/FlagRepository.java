package com.AdWatch.AdWatch.Rewards.App.repository;


import com.AdWatch.AdWatch.Rewards.App.entity.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlagRepository extends JpaRepository<Flag, Long> {
	@Query("SELECT f FROM Flag f WHERE f.question IS NOT NULL")
    List<Flag> findFlaggedQuestions();
    
	
	@Query("SELECT f FROM Flag f WHERE f.comment IS NOT NULL")
    List<Flag> findFlaggedComments();
	
	List<Flag> findByQuestionId(Long questionId);
	

    List<Flag> findByCommentId(Long commentId);


	void deleteByQuestionId(Long questionId);


	void deleteByCommentId(Long commentId);


	boolean existsByQuestionId(Long questionId);


	boolean existsByCommentId(Long commentId);
}

