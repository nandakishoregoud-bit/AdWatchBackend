package com.AdWatch.AdWatch.Rewards.App.Dto;

public class ReferralResponseDTO {

	private Long id;
    private Long referrerId;
    private Long referredUserId;
    private int bonusPoints;
    private boolean milestoneAchieved;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getReferrerId() {
		return referrerId;
	}
	public void setReferrerId(Long referrerId) {
		this.referrerId = referrerId;
	}
	public Long getReferredUserId() {
		return referredUserId;
	}
	public void setReferredUserId(Long referredUserId) {
		this.referredUserId = referredUserId;
	}
	public int getBonusPoints() {
		return bonusPoints;
	}
	public void setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
	}
	public boolean isMilestoneAchieved() {
		return milestoneAchieved;
	}
	public void setMilestoneAchieved(boolean milestoneAchieved) {
		this.milestoneAchieved = milestoneAchieved;
	}

    // Getters and Setters
    
	
}
