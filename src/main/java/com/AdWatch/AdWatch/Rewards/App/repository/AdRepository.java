package com.AdWatch.AdWatch.Rewards.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AdWatch.AdWatch.Rewards.App.entity.Ad;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
	
}