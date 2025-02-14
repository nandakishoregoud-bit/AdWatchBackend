package com.AdWatch.AdWatch.Rewards.App.Dto;

public class ReferralRequestDTO {

	private Long referrerId;
    private Long referredUserId;
    
    
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
    
    
}
