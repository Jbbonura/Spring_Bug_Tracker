package com.codingdojo.bugtracker.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.bugtracker.models.LoginUser;
import com.codingdojo.bugtracker.models.Project;
import com.codingdojo.bugtracker.models.Ticket;
import com.codingdojo.bugtracker.models.Ticket.TicketStatus;
import com.codingdojo.bugtracker.models.User;
import com.codingdojo.bugtracker.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	//LOGIN AND REGISTRATION LOGIC
	
		//CHECK EMAIL
		public boolean checkEmailExists(String email) {
			Optional<User> potentialUser = userRepo.findByEmail(email);
			
			if(potentialUser.isPresent()) {
				return true;
			}
			else {
				return false;
			}
		}
		
		public User findUserByEmail(String email) {
			return userRepo.findByEmail(email).orElse(null);
		}
		
		//Register
		public User register(User newUser, BindingResult result) {
			
			//Is the email already taken?
			
			
			if(this.checkEmailExists(newUser.getEmail())) {
				result.rejectValue("email", "Exists", "Email already exists");
			}
			
			//Does the entered password match the confirmation password?
			if(!newUser.getPassword().equals(newUser.getConfirm())) {
				result.rejectValue("confirm", "Matches", "The confirm password must match password!");
			}
			
			if(result.hasErrors()) {
				return null;
			}
			
			//Hashing password and creating a user if no errors
			String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hashed);
			
			return userRepo.save(newUser);
		}
		
		//Login
		public User login(LoginUser newLoginObject, BindingResult result) {
			//Does a user with that email exist in the database?
			if(!this.checkEmailExists(newLoginObject.getEmail())) {
				result.rejectValue("email", "Exists", "INVALID CREDENTIALS");
				return null;
			}
			
			User user = this.findUserByEmail(newLoginObject.getEmail());
			//If so, is the password the right password for that email?
			if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
				result.rejectValue("password", "Matches", "Invalid Password!");
				return null;
			}
			
			return user;
		}
		
	//READ
	
		//read one
		public User getOneUser(Long id) {
			return userRepo.findById(id).orElse(null);
		}
		
		//read all
		public List<User> getAllUsers() {
			return userRepo.findAll();
		}
		
		//read with allowed permessions
		public List<User> getUsersWithPermission(int role) {
			return userRepo.getUserWhereIdLessThan(role);
		}
		
		//read users assigned projects
		public List<Project> getUsersAssignedProjects(Long id) {
			User user = getOneUser(id);
			return user.getAssignedProjects();
		}
		
		//read users managed projects
		public List<Project> getUsersManagedProjects(Long id) {
			User user = getOneUser(id);
			return user.getManagedProjects();
		}
		//read all non admin and submitter users
		public List<User> getDevs() {
			return userRepo.getDevs();
		}
		
	//DELETE
		public void deleteUser(Long id) {
			userRepo.deleteById(id);
		}
		
	//Count users assigned and managed projects
		public int countUsersProjects(Long id) {
			//fetch users projects 
			List<Project> userAssigned = getUsersAssignedProjects(id);
			List<Project> userManaged = getUsersManagedProjects(id);
			
			//count the projects
			int count = 0;
			for (Project project : userAssigned) {
				count++;
			}
			for (Project project : userManaged) {
				count++;
			}
			return count;
		}
	//Retrieve users unresolved tickets
		public List<Ticket> unresolvedAssignedTickets(Long id){
			//fetch users tickets
			User user = getOneUser(id);
			List<Ticket> assignedTickets = user.getAssignedTickets(); 
			int index = 0;
			for (Ticket ticket : assignedTickets) {
				if (ticket.getTicketStatus() == TicketStatus.RESOLVED) {
					assignedTickets.remove(index);
				}
				index++;
			}
			return assignedTickets;
		}
	//Count of unresolved tickets
		public int unresolvedAssignedTicketCount(Long id){
			List<Ticket> assignedTickets = unresolvedAssignedTickets(id);
			int count = 0;
			for (Ticket ticket : assignedTickets) {
				count++;
			}
			return count;
		}
		
		//retreive users resolved tickets
		public List<Ticket> resolvedAssignedTickets(Long id) {
			//fetch users tickets
			User user = getOneUser(id);
			List<Ticket> assignedTickets = user.getAssignedTickets();
			
			//remove all tickets not finished
			int index = 0;
			for(Ticket ticket : assignedTickets) {
				if (ticket.getTicketStatus() != TicketStatus.RESOLVED) {
					assignedTickets.remove(index);
				}
				index++;
			}
			return assignedTickets;
		}
		
		//Count resolved tickets
		public int resolvedAssignedTicketCount(Long id) {
			List<Ticket> assignedTickets = resolvedAssignedTickets(id);
			int count = 0;
			for (Ticket ticket : assignedTickets) {
				count++;
			}
			return count;
		}
		
		//concat assigned project's tickets
		public List<Ticket> allAssignedProjectsTickets(Long id) {
			User user = getOneUser(id);
			List<Project> managedProjects = user.getManagedProjects();
			List<Ticket> allTickets = new ArrayList<>(); 
			
			for(Project project : managedProjects) {
				System.out.println(project.getId());
				List<Ticket> projectTickets = project.getProjectsTickets();
				for(Ticket ticket : projectTickets) {
					System.out.println(ticket.getTitle());
					System.out.println(ticket.getProject().getId());
				}
				allTickets.addAll(projectTickets);
				
			}
			
			
			return allTickets;
			
		}
}
