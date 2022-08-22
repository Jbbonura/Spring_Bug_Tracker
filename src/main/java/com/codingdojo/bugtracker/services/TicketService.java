package com.codingdojo.bugtracker.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.codingdojo.bugtracker.models.Project;
import com.codingdojo.bugtracker.models.Ticket;
import com.codingdojo.bugtracker.models.Ticket.TicketStatus;
import com.codingdojo.bugtracker.models.Ticket.TicketType;
import com.codingdojo.bugtracker.repositories.TicketRepository;

@Service
public class TicketService {
	@Autowired
	private TicketRepository ticketRepo;
	
	@Autowired
	@Lazy
	private ProjectService projectServ;
	
	//CREATE
		public Ticket createTicket(Ticket newTicket) {
			return ticketRepo.save(newTicket);
		}
	
	//READ
	
		//read one
		public Ticket getOneTicket(Long id) {
			return ticketRepo.findById(id).orElse(null);
		}
		
		//read all
		public List<Ticket> getAllTickets() {
			return ticketRepo.findAll();
		}
		
		//read with specific status
		public List<Ticket> getTicketNotByStatus(TicketStatus status){
			return ticketRepo.findTicketNotByStatus(status);
		}
		public List<Ticket> getTicketByStatusAndId(TicketStatus status, Long id){
			return ticketRepo.findTicketByStatusAndId(status, id);
		}
		public List<Ticket> getTicketByStatus(TicketStatus status) {
			return ticketRepo.findTicketByStatus(status);
		}
		
		//read with specific type
		public List<Ticket> getTicketByType(TicketType type){
			return ticketRepo.findTicketByType(type);
		}
		
		//read by project
		public List<Ticket> getTicketByProject(Long id) {
			return ticketRepo.findTicketByProject(id);
		}
		
		//read by submitter
		public List<Ticket> getTicketBySubmitter(Long id) {
			return ticketRepo.findTicketsBySubmitter(id);
		}
		
		//read and count by status
		public int getAdminTicketStatusCount(TicketStatus status) {
			return ticketRepo.findAdminTicketCount(status);
		}
	
		public int getDevTicketStatusCount(TicketStatus status, Long id) {
			return ticketRepo.findDevTicketCount(status, id);
		}
		
		//read and count by priority
		public int getAdminTicketPriorityCount(int priority) {
			return ticketRepo.findAdminTicketPriorityCount(priority);
		}
		
		public int getDevTicketPriorityCount(int priority, Long id) {
			return ticketRepo.findDevTicketPriorityCount(priority, id);
		}
		
		//read and count by type
		public int getAdminTicketTypeCount(TicketType type) {
			return ticketRepo.findAdminTicketTypeCount(type);
		}
		
		public int getDevTicketTypeCount(TicketType type, Long id) {
			return ticketRepo.findDevTicketTypeCount(type, id);
		}
	//DELETE
		public void deleteTicket(Long id) {
			ticketRepo.deleteById(id);
		}
		
	//GET COUNT
		public int ticketCount() {
			//fetch all unresolved tickets
			List<Ticket> tickets = getTicketNotByStatus(TicketStatus.RESOLVED);
			
			//count number of tickets
			int count = 0;
			for(Ticket ticket : tickets) {
				count++;
			}
			return count;
		}
		
		public int ticketCount(TicketStatus status) {
			//fetch all new tickets
			List<Ticket> tickets = getTicketByStatus(status);
			
			//count number of tickets
			int count = 0;
			for(Ticket ticket : tickets) {
				count++;
			}
			return count;
		}
		
		public int ticketCount(TicketStatus status, Long id) {
			//fetch all tickets with matching status and submitter
			List<Ticket> tickets = getTicketByStatusAndId(status, id);
			
			//count number of tickets
			int count = 0;
			for(Ticket ticket : tickets) {
				count++;
			}
			return count;
			
		}
		
		public int ticketCount(TicketType type) {
			//fetch all tickets with matching type
			List<Ticket> tickets = getTicketByType(type);
			
			//count tickets
			int count = 0;
			for(Ticket ticket : tickets) {
				count++;
			}
			return count;
		}
		
		//PM dashboard calculations
		public HashMap<String, Integer> projectManagerCounts(Long id) {
			//assign variables
			int newTicket = 0;
			int openTicket = 0;
			int inProgressTicket = 0;
			int resolvedTicket = 0;
			int infoRequiredTicket = 0;
			int unresolvedTicket = 0;
			
			int noPriorityTicket = 0;
			int lowPriorityTicket = 0;
			int medPriorityTicket = 0;
			int highPriorityTicket = 0;
			
			int otherTypeTicket = 0;
			int requestTypeTicket = 0;
			int bugTypeTicket = 0;
			
			HashMap<String, Integer> counts = new HashMap<String, Integer>();
			
			//fetch all projects with managers id
			List<Project> projects = projectServ.getAllByManager(id);
			//iterate through projects to grab tickets
			for(Project project : projects) {
				List<Ticket> tickets = getTicketByProject(project.getId());
				for(Ticket ticket : tickets) {
					//get status counts
					if(ticket.getTicketStatus() == TicketStatus.NEW) {
						newTicket++;
						unresolvedTicket++;
						}
					else if(ticket.getTicketStatus() == TicketStatus.OPEN) {
						openTicket++;
						unresolvedTicket++;
					}
					else if(ticket.getTicketStatus() == TicketStatus.IN_PROGRESS) {
						inProgressTicket++;
						unresolvedTicket++;
					}
					else if(ticket.getTicketStatus() == TicketStatus.INFO_REQUIRED) {
						infoRequiredTicket++;
						unresolvedTicket++;
					}
					else {
						resolvedTicket++;
					}
					
					//get priority counts
					if(ticket.getPriority() == 0) {
						noPriorityTicket++;
					}
					else if(ticket.getPriority() == 1) {
						lowPriorityTicket++;
					}
					else if(ticket.getPriority() == 2) {
						medPriorityTicket++;
					}
					else {
						highPriorityTicket++;
					}
					
					//get type counts
					if(ticket.getTicketType() == TicketType.OTHER_COMMENTS) {
						otherTypeTicket++;
					}
					else if(ticket.getTicketType() == TicketType.FEATURE_REQUESTS) {
						requestTypeTicket++;
					}
					else {
						bugTypeTicket++;
					}
					
				}
			}
			//add values to hashmap and return
			counts.put("newTicket", newTicket);
			counts.put("openTicket", openTicket);
			counts.put("inProgressTicket", inProgressTicket);
			counts.put("infoRequiredTicket", infoRequiredTicket);
			counts.put("resolvedTicket", resolvedTicket);
			counts.put("unresolvedTicket", unresolvedTicket);
			counts.put("noPriorityTicket", noPriorityTicket);
			counts.put("lowPriorityTicket", lowPriorityTicket);
			counts.put("medPriorityTicket", medPriorityTicket);
			counts.put("highPriorityTicket", highPriorityTicket);
			counts.put("otherTypeTicket", otherTypeTicket);
			counts.put("requestTypeTicket", requestTypeTicket);
			counts.put("bugTypeTicket", bugTypeTicket);
			
			return counts;
			
		}
		
}	
