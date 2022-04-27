package com.codingdojo.bugtracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.bugtracker.models.Ticket;
import com.codingdojo.bugtracker.models.Ticket.TicketStatus;
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
		public List<Ticket> getTicketByStatus(TicketStatus status){
			return ticketRepo.findTicketByStatus(status);
		}
		
	//DELETE
		public void deleteTicket(Long id) {
			ticketRepo.deleteById(id);
		}
		
	//GET COUNT
		public int ticketCount(TicketStatus status) {
			//fetch all open tickets
			List<Ticket> tickets = getTicketByStatus(status);
			
			//count number of tickets
			int count = 0;
			for(Ticket ticket : tickets) {
				count++;
			}
			return count;
		}
}	
