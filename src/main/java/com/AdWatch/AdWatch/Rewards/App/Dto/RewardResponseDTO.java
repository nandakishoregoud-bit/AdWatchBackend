package com.AdWatch.AdWatch.Rewards.App.Dto;

import java.time.LocalDateTime;

public class RewardResponseDTO {

	private Long id;
	private String rewardType;
	private int pointsRedeemed;
	private LocalDateTime redeemedAt;
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRewardType() {
		return rewardType;
	}

	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}

	public int getPointsRedeemed() {
		return pointsRedeemed;
	}

	public void setPointsRedeemed(int pointsRedeemed) {
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

}
