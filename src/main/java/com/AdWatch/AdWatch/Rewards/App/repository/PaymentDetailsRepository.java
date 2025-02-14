package com.AdWatch.AdWatch.Rewards.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AdWatch.AdWatch.Rewards.App.entity.PaymentDetails;
import com.AdWatch.AdWatch.Rewards.App.entity.User;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {
    PaymentDetails findByUser(User user);
}

