package com.codingdojo.bugtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.bugtracker.models.Ticket;
import com.codingdojo.bugtracker.models.Ticket.TicketStatus;
import com.codingdojo.bugtracker.models.Ticket.TicketType;

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
	
	@Query("SELECT t FROM Ticket t WHERE ticketType = ?1")
	List<Ticket> findTicketByType(TicketType type);	
	
	@Query("SELECT t from Ticket t WHERE submitter_id = ?1")
	List<Ticket> findTicketsBySubmitter(Long id);
	
	@Query("SELECT count(*) from Ticket WHERE ticketStatus = ?1")
	int findAdminTicketCount(TicketStatus status);
	
	@Query("SELECT count(*) from Ticket WHERE ticketStatus = ?1 AND assigned_dev_id = ?2")
	int findDevTicketCount(TicketStatus status, Long id);
	
	@Query("SELECT count(*) from Ticket WHERE priority = ?1")
	int findAdminTicketPriorityCount(int priority);
	
	@Query("SELECT count(*) from Ticket WHERE priority = ?1 AND assigned_dev_id = ?2")
	int findDevTicketPriorityCount(int priority, Long id);
	
	@Query("SELECT count(*) from Ticket where ticketType = ?1")
	int findAdminTicketTypeCount(TicketType type);
	
	@Query("SELECT count(*) from Ticket WHERE ticketType = ?1 AND assigned_dev_id = ?2")
	int findDevTicketTypeCount(TicketType type, Long id);
}

