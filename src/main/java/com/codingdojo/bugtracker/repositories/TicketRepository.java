package com.codingdojo.bugtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.bugtracker.models.Ticket;
import com.codingdojo.bugtracker.models.Ticket.TicketStatus;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long>{
	List<Ticket> findAll();
	
	@Query("SELECT t FROM Ticket t WHERE ticketStatus IS NOT ?1")
	List<Ticket> findTicketNotByStatus(TicketStatus status);
	
	@Query("Select t FROM Ticket t WHERE ticketStatus = ?1")
	List<Ticket> findTicketByStatus(TicketStatus status);
	
	@Query("SELECT t FROM Ticket t WHERE ticketStatus IS NOT ?1 AND submitter_id = ?2")
	List<Ticket> findTicketByStatusAndId(TicketStatus status, Long id);
	
	@Query("SELECT t FROM Ticket t WHERE project_id IS ?1")
	List<Ticket> findTicketByProject(Long id);
	
}

