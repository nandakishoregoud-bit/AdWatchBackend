package com.AdWatch.AdWatch.Rewards.App.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AdWatch.AdWatch.Rewards.App.entity.Transaction;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	@Query("SELECT COUNT(t) FROM Transaction t WHERE t.user.id = :userId AND t.type = 'WATCH_AD' AND t.timestamp BETWEEN :startOfDay AND :endOfDay")
	long countByUserIdAndTypeAndTimestampBetween(
	    @Param("userId") Long userId, 
	    @Param("startOfDay") LocalDateTime startOfDay, 
	    @Param("endOfDay") LocalDateTime endOfDay
	);

	@Query("SELECT MAX(t.timestamp) FROM Transaction t WHERE t.user.id = :userId AND t.type = 'WATCH_AD'")
	LocalDateTime findLastAdWatchTime(@Param("userId") Long userId);

	
}