package com.codingdojo.bugtracker.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.bugtracker.models.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long>{
	List<Ticket> findAll();
}
