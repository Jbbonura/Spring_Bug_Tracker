package com.codingdojo.bugtracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.bugtracker.models.Ticket;
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
		
	//DELETE
		public void deleteTicket(Long id) {
			ticketRepo.deleteById(id);
		}
}	
