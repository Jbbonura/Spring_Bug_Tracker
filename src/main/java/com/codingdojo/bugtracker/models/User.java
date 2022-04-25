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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Member Variables
	@NotNull(message="Name is required.")
	@Size(min = 1, max = 45, message="Name cannot be more than 45 characters.")
	private String name;
	
	@NotNull(message="Email is required.")
	@Email(message="Please enter a valid email")
	private String email;
	
	@NotNull(message="Password is required.")
	@Size(min = 8, max = 128, message="Password must be between 8 and 128 characters")
	private String password;
	
	@Transient
	@NotNull(message="Confirm Password is required.")
	private String confirm;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role")
	private Role role;
	
	//ROLE CLASS?
	public enum Role {
			ADMIN,
			PROJECT_MANAGER,
			DEVELOPER,
			SUBMITTER;		
	}
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	//RELATIONSHIPS
	
	//Many to Many with Tickets w/ middle model of comments
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "comments",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "ticket_id")
			)
	private List<Ticket> commentedTickets;
	
	//Many to Many with Tickets w/ no middle model
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "dev_assigned_to_ticket",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "ticket_id")
			)
	private List<Ticket> assignedTickets;
	
	//Many To Many with Project w/ middle model of tickets
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "tickets",
			joinColumns = @JoinColumn(name = "submitter_id"),
			inverseJoinColumns = @JoinColumn(name = "project_id")
			)
	private List<Ticket> submittedTickets;
	
	//Many to Many with Projects w/ no middle model
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "dev_assigned_to_project",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "project_id")
			)
	private List<Project> assignedProjects;
	
	//One to Many with Projects
	@OneToMany(mappedBy="manager", fetch=FetchType.LAZY)
	private List<Project> managedProjects;
	
	//Empty Constructor
	public User() {
		super();
	}
	
	//Full Constructor
	public User(
			@NotNull(message = "Name is required.") @Size(min = 1, max = 45, message = "Name cannot be more than 45 characters.") String name,
			@NotNull(message = "Email is required.") @Email(message = "Please enter a valid email") String email,
			@NotNull(message = "Password is required.") @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters") String password,
			@NotNull(message = "Confirm Password is required.") String confirm, Role role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
		this.role = role;
	}
	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public List<Ticket> getCommentedTickets() {
		return commentedTickets;
	}

	public void setCommentedTickets(List<Ticket> commentedTickets) {
		this.commentedTickets = commentedTickets;
	}

	public List<Ticket> getAssignedTickets() {
		return assignedTickets;
	}

	public void setAssignedTickets(List<Ticket> assignedTickets) {
		this.assignedTickets = assignedTickets;
	}

	public List<Ticket> getSubmittedTickets() {
		return submittedTickets;
	}

	public void setSubmittedTickets(List<Ticket> submittedTickets) {
		this.submittedTickets = submittedTickets;
	}

	public List<Project> getAssignedProjects() {
		return assignedProjects;
	}

	public void setAssignedProjects(List<Project> assignedProjects) {
		this.assignedProjects = assignedProjects;
	}

	public List<Project> getManagedProjects() {
		return managedProjects;
	}

	public void setManagedProjects(List<Project> managedProjects) {
		this.managedProjects = managedProjects;
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
