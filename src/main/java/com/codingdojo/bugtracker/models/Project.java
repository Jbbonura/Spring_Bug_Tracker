package com.codingdojo.bugtracker.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="projects")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Member variables
	@NotNull(message="Name is required.")
	@Size(min = 1, max = 45, message="Project name cannot be more than 45 characters.")
	private String name;
	
	//set up description with type text?
	@NotNull(message="Description is required.")
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	//Relationship
	
	//Many to Many with Users w/ no middle model
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "dev_assigned_to_project",
			joinColumns = @JoinColumn(name = "project_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private List<User> assignedManager;
	
	//Many to One with Users
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="manager_id")
	private User manager;
	
	//Many to Many with User w/ middle model of Tickets
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "tickets",
			joinColumns = @JoinColumn(name = "project_id"),
			inverseJoinColumns = @JoinColumn(name = "submitter_id")
			)
	private List<Ticket> projectsTickets;
	
	//Empty Constructor
	public Project() {
		super();
	}
	//Full Constructor
	public Project(
			@NotNull(message = "Name is required.") @Size(min = 1, max = 45, message = "Project name cannot be more than 45 characters.") String name,
			@NotNull(message = "Description is required.") String description) {
		super();
		this.name = name;
		this.description = description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public List<User> getAssignedManager() {
		return assignedManager;
	}
	public void setAssignedManager(List<User> assignedManager) {
		this.assignedManager = assignedManager;
	}
	public User getManager() {
		return manager;
	}
	public void setManager(User manager) {
		this.manager = manager;
	}
	public List<Ticket> getProjectsTickets() {
		return projectsTickets;
	}
	public void setProjectsTickets(List<Ticket> projectsTickets) {
		this.projectsTickets = projectsTickets;
	}
	
	//ADDITIONAL GETTERS AND SETTERS
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
}
