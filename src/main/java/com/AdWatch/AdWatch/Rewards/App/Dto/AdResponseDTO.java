package com.AdWatch.AdWatch.Rewards.App.Dto;

public class AdResponseDTO {

	private Long id;
    private String adUrl;
    private int coinsReward;
    private int duration;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAdUrl() {
		return adUrl;
	}
	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}
	public int getCoinsReward() {
		return coinsReward;
	}
	public void setCoinsReward(int coinsReward) {
		this.coinsReward = coinsReward;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
    
}
