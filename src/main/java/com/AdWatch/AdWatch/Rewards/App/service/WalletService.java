package com.AdWatch.AdWatch.Rewards.App.service;

import java.util.List;

import com.AdWatch.AdWatch.Rewards.App.Dto.RewardDto;

public interface WalletService {

	double getWalletBalance(Long userId);

	/* void redeemPoints(Long userId, Double points, String rewardType); */
    List<RewardDto> getRewardHistory(Long userId);
	void redeemPoint(Long userId, Double points, String rewardType, String upiId, String bankAccountNumber,
			String bankIFSC, String paypalEmail, String amazonPayNumber);
    
}
