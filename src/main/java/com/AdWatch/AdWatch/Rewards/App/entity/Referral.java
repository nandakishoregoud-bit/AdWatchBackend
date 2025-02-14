package com.AdWatch.AdWatch.Rewards.App.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.AdWatch.AdWatch.Rewards.App.repository.UserRepository;

@Entity
@Table(name = "referrals")
public class Referral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referrer_id", nullable = false)
    private User referrer; // The user who referred someone

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referred_user_id", nullable = false)
    private User referredUser; // The user who was referred

    @Column(nullable = false)
    private int bonusPoints; // Points given for successful referral

    @Column(nullable = false)
    private boolean milestoneAchieved = false;
    
    @Column(nullable = false)
    private LocalDateTime referredDate;

	public LocalDateTime getReferredDate() {
		return referredDate;
	}

	public void setReferredDate(LocalDateTime referredDate) {
		this.referredDate = referredDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getReferrer() {
		return referrer;
	}

	public void setReferrer(User referrer) {
		this.referrer = referrer;
	}

	public User getReferredUser() {
		return referredUser;
	}

	public void setReferredUser(User referredUser) {
		this.referredUser = referredUser;
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
	
	public Referral() {}

	public Referral(User referrer, User referredUser) {
	    this.referrer = referrer;
	    this.referredUser = referredUser;
	    this.referredDate = LocalDateTime.now();
	}

	}

