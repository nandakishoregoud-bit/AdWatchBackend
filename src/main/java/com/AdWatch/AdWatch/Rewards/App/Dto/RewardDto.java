package com.AdWatch.AdWatch.Rewards.App.Dto;

import java.time.LocalDateTime;

import com.AdWatch.AdWatch.Rewards.App.entity.Reward;

public class RewardDto {
    private String rewardType;
    private Double pointsRedeemed;
    private LocalDateTime redeemedAt;
    private String status;

    // Constructor that maps from Reward entity
    public RewardDto(Reward reward) {
        this.rewardType = reward.getRewardType();
        this.pointsRedeemed = reward.getPointsRedeemed();
        this.redeemedAt = reward.getRedeemedAt();
        this.status = reward.getStatus();
    }

	public String getRewardType() {
		return rewardType;
	}

	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}

	public Double getPointsRedeemed() {
		return pointsRedeemed;
	}

	public void setPointsRedeemed(Double pointsRedeemed) {
		this.pointsRedeemed = pointsRedeemed;
	}

	public LocalDateTime getRedeemedAt() {
		return redeemedAt;
	}

	public void setRedeemedAt(LocalDateTime redeemedAt) {
		this.redeemedAt = redeemedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    // Getters and setters...
}

