package com.codingdojo.bugtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.bugtracker.models.Comment;

@Repository
public interface CommentRepository extends CrudRepository <Comment, Long>{
	List<Comment> findAll();
	
	@Query("SELECT c FROM Comment c WHERE ticket_id IS ?1")
	List<Comment> findCommentByTicket(Long id);
}
