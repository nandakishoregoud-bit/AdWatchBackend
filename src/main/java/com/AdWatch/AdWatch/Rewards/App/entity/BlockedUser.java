package com.AdWatch.AdWatch.Rewards.App.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Entity
public class BlockedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User owner; // The owner of the question who blocks the user

    @ManyToOne
    private User blockedUser; // The user who is blocked from commenting

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getBlockedUser() {
		return blockedUser;
	}

	public void setBlockedUser(User blockedUser) {
		this.blockedUser = blockedUser;
	}

    // Constructors, getters, setters...
    
    
}
