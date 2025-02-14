package com.AdWatch.AdWatch.Rewards.App.Dto;

public class RewardRequestDTO {

	private Long userId;
	private int points;
	private String rewardType;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getRewardType() {
		return rewardType;
	}

	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}

	// Getters and Setters

}
