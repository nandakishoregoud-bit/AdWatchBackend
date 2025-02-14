package com.AdWatch.AdWatch.Rewards.App.Dto;

public class UserRegistrationDTO {

	private String name;
    private String email;
    private String password;
    private String referralCode;
    private Long referredBy;
    private String profilePicUrl; // New field for profile picture

    
	public String getProfilePicUrl() {
		return profilePicUrl;
	}
	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}
	public Long getReferredBy() {
		return referredBy;
	}
	public void setReferredBy(Long referredBy) {
		this.referredBy = referredBy;
	}
	public String getReferralCode() {
		return referralCode;
	}
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}
