package com.AdWatch.AdWatch.Rewards.App.entity;

import javax.persistence.*;

@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String adUrl;

    @Column(nullable = false)
    private int coinsReward;

    @Column(nullable = false)
    private int duration; // in seconds

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

    // Getters and Setters
}