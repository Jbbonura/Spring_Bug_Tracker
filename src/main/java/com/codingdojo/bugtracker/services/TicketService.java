package com.codingdojo.bugtracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.bugtracker.models.Ticket;
import com.codingdojo.bugtracker.models.Ticket.TicketStatus;
import com.codingdojo.bugtracker.models.Ticket.TicketType;
import com.codingdojo.bugtracker.repositories.TicketRepository;

@Service
public class TicketService {
	@Autowired
	private TicketRepository ticketRepo;
	
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
		
}	
