package com.codingdojo.bugtracker.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tickets")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Member Variables
	@NotNull(message="Title is required.")
	@Size(min = 2, max = 45, message="Title must be between 2 and 45 characters.")
	private String title;
	
		//set up description with type text?
	@NotEmpty(message="Description is required.")
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@NotNull(message="Priority is requried.")
	@Max(4)
	private int priority;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ticketStatus")
	private TicketStatus ticketStatus = TicketStatus.NEW;
	
	public enum TicketStatus {
		NEW,
		OPEN,
		IN_PROGRESS,
		RESOLVED,
		INFO_REQUIRED;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="ticketType")
	private TicketType ticketType;
	
	public enum TicketType {
		BUGS_AND_ERRORS,
		FEATURE_REQUESTS,
		OTHER_COMMENTS;
	}
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	//Relationship
	
	//Many to Many with Users w/ middle model of comments
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "comments",
			joinColumns = @JoinColumn(name = "ticket_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private List<User> commentingUsers;
	
	//Many to One with users
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assignedDev_id")
	private User assignedDev;
	
	//Joining many to many between users and projects
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "submitter_id")
	private User submitter;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	//Empty Constructor
	public Ticket() {
		super();
	}
	
	//Full Constructor
	public Ticket(
			@NotNull(message = "Title is required.") @Size(min = 2, max = 45, message = "Title must be between 2 and 45 characters.") String title,
			@NotNull(message = "Description is required.") String description,
			@NotEmpty(message = "Priority is requried.") @Max(4) int priority, TicketStatus ticketStatus,
			TicketType ticketType) {
		super();
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.ticketStatus = ticketStatus;
		this.ticketType = ticketType;
	}
	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
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

	public List<User> getCommentingUsers() {
		return commentingUsers;
	}

	public void setCommentingUsers(List<User> commentingUsers) {
		this.commentingUsers = commentingUsers;
	}

	public User getAssignedDev() {
		return assignedDev;
	}

	public void setAssignedDev(User assignedDev) {
		this.assignedDev = assignedDev;
	}

	public User getSubmitter() {
		return submitter;
	}

	public void setSubmitter(User submitter) {
		this.submitter = submitter;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	//Additional Getters and Setters
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
		this.ticketStatus = TicketStatus.NEW;
	}
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
	
}
