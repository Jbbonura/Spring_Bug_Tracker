package com.codingdojo.bugtracker.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Member Variables
	@NotNull(message="Comment is empty")
	@Size(min = 1, max = 255, message="Comment has a 255 character max.")
	private String comment;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	//Relationship
	
	//Joining many to many between users and tickets
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User commentingUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ticket_id")
	private Ticket commentedTicket;
	
	//Empty Constructor
	public Comment() {
		super();
	}
	//Full Constructor
		public Comment(
			@NotNull(message = "Comment is empty") @Size(min = 1, max = 255, message = "Comment has a 255 character max.") String comment) {
		super();
		this.comment = comment;
	}

	//Getters and Setters
	public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public Date getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		public Date getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}
		public User getCommentingUser() {
			return commentingUser;
		}
		public void setCommentingUser(User commentingUser) {
			this.commentingUser = commentingUser;
		}
		public Ticket getCommentedTicket() {
			return commentedTicket;
		}
		public void setCommentedTicket(Ticket commentedTicket) {
			this.commentedTicket = commentedTicket;
		}
		
	//Additional Getters and Setters
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
}
