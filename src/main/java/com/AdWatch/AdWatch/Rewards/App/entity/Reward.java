package com.AdWatch.AdWatch.Rewards.App.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rewards")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Linked to the User entity

    @Column(nullable = false)
    private String rewardType; // e.g., PayPal, gift card

    @Column(nullable = false)
    private Double pointsRedeemed;

    @Column(nullable = false)
    private LocalDateTime redeemedAt;

    @Column(nullable = false)
    private String status; // e.g., PENDING, SUCCESS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

    // Getters and Setters
	
	// Default constructor
    public Reward() {}

    // Constructor with parameters
    public Reward(User user, String rewardType, Double pointsRedeemed, LocalDateTime redeemedAt, String status) {
        this.user = user;
        this.rewardType = rewardType;
        this.pointsRedeemed = pointsRedeemed;
        this.redeemedAt = redeemedAt;
        this.status = status;
    }

}

