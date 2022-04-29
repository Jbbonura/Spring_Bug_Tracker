package com.codingdojo.bugtracker.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.bugtracker.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	List<User> findAll();
	Optional<User> findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE role < ?1")
	List<User> getUserWhereIdLessThan(int role);

	@Query("SELECT u FROM User u WHERE role = 1 OR role = 2")
	List<User> getDevs();
	
}
