package com.codingdojo.bugtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.bugtracker.models.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	List<Project> findAll();
	
	@Query("SELECT p from Project p WHERE manager_id = ?1")
	List<Project> findProjectsByManager(Long manager_id);
}