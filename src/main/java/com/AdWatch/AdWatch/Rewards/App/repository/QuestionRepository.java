package com.AdWatch.AdWatch.Rewards.App.repository;

import com.AdWatch.AdWatch.Rewards.App.entity.Question;
import com.AdWatch.AdWatch.Rewards.App.entity.User;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	List<Question> findByCategory(String category, Sort sort);

	List<Question> findByUserIdAndTitle(Long userId, String title);

	List<Question> findByUser(User user,Sort sort);
	
	 List<Question> findTop10ByCreatedAtAfter(LocalDateTime startDate,Sort sort);

	 
	 List<Question> findByFlaggedTrue();


	
	@Modifying
    @Transactional
    @Query("UPDATE Question q SET q.flagged = :flagged WHERE q.id = :questionId")
	void updateFlaggedStatus(Long questionId, boolean flagged);
}
