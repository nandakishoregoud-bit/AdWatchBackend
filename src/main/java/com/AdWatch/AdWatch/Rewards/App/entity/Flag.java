package com.AdWatch.AdWatch.Rewards.App.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Flag {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Comment comment;

    @ManyToOne
    private Question question;

    private String reason;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// No-args constructor (required by JPA)
    public Flag() {}

    // Constructor for flagging a comment
    public Flag(Question question,Comment comment, User user, String reason) {
        this.question = question;
    	this.comment = comment;
        this.reason = reason;
        this.user = user;
    }

    // Constructor for flagging a question
    public Flag(Question question,User user, String reason) {
        this.question = question;
        this.user = user;
        this.reason = reason;
    }


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

    // Getters and setters
    
    
}
