package com.codingdojo.bugtracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.bugtracker.models.Comment;
import com.codingdojo.bugtracker.repositories.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepo;
	
	//CREATE
		public Comment createComment(Comment newComment) {
			return commentRepo.save(newComment);
		}
		
	//READ
	
		//read one
		public Comment getOneComment(Long id) {
			return commentRepo.findById(id).orElse(null);
		}
	
		//read all
		public List<Comment> getAllComments() {
			return commentRepo.findAll();
		}
	
	//DELETE
		public void deleteComment(Long id) {
			commentRepo.deleteById(id);
		}
}
