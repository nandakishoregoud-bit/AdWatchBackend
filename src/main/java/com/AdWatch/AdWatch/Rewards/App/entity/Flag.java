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
    @JoinColumn(name = "comment_id", nullable = true)
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = true)
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

	

	// Primary constructor: Can flag either a comment or a question
	public Flag(Question question, Comment comment, User user, String reason) {
	    this.question = question;
	    this.comment = comment;
	    this.user = user;
	    this.reason = reason;
	}

	// Constructor for flagging only a question
	public Flag(Question question, User user, String reason) {
	    this(question, null, user, reason);
	}

	// Constructor for flagging only a comment
	public Flag(Comment comment, User user, String reason) {
	    this(null, comment, user, reason);
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
