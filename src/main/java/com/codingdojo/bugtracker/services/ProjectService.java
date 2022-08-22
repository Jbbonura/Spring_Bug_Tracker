package com.codingdojo.bugtracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.bugtracker.models.Project;
import com.codingdojo.bugtracker.models.Ticket;
import com.codingdojo.bugtracker.models.Ticket.TicketStatus;
import com.codingdojo.bugtracker.repositories.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepo;
	@Autowired
	public TicketService ticketServ;
	
	//CREATE
		public Project createProject(Project newProject) {
			return projectRepo.save(newProject);
		}
	
	//READ
		
		//read one
		public Project getOneProject(Long id) {
			Project project = projectRepo.findById(id).orElse(null);
			return project;
		}
	
		//read all
		public List<Project> getAllProjects() {
			return projectRepo.findAll();
		}
		
		//read all by manager
		public List<Project> getAllByManager(Long id) {
			return projectRepo.findProjectsByManager(id);
		}
	
	//DELETE
		public void deleteProject(Long id) {
			projectRepo.deleteById(id);
		}
		
	//GET COUNT
		public int projectCount() {
			//fetch all projects
			List<Project> projects = getAllProjects();
			//count number of projects
			int count = 0;
			for(Project project : projects) {
				count++;
			}
			return count;
		}
	
		//Get unresolved projectTicketCount
		public int unresolvedProjectTicketCount(Long id) {
			Project project = getOneProject(id);
			List<Ticket> tickets = ticketServ.getTicketByProject(id);
			int count = 0;
			int index = 0;
			
			for (Ticket ticket : tickets) {
				if(ticket.getTicketStatus() == TicketStatus.RESOLVED) {
					tickets.remove(index);
				}
				else {
					count++;
				}
				index++;
			}
			return count;
		}
		
		public int resolvedProjectTicketCount(Long id) {
			Project project = getOneProject(id);
			List<Ticket> tickets = ticketServ.getTicketByProject(id);
			int count= 0;
			int index= 0;
			
			for(Ticket ticket : tickets) {
				if(ticket.getTicketStatus() != TicketStatus.RESOLVED) {
					tickets.remove(index);
				}
				else {
					count++;
				}
				index++;
			}
			return count;
		}
		
		
}
