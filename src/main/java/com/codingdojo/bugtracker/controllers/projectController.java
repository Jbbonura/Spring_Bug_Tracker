package com.codingdojo.bugtracker.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.bugtracker.models.Project;
import com.codingdojo.bugtracker.models.Ticket;
import com.codingdojo.bugtracker.models.User;
import com.codingdojo.bugtracker.services.ProjectService;
import com.codingdojo.bugtracker.services.TicketService;
import com.codingdojo.bugtracker.services.UserService;

@Controller
public class projectController {
	@Autowired
	public ProjectService projectServ;
	@Autowired
	public UserService userServ;
	@Autowired
	public TicketService ticketServ;
	
	@RequestMapping("/project/new")
	public String createProjectForm(
			HttpSession session,
			Model model) {
		
		//make sure user is logged in
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		}
		//add model for data binding
		model.addAttribute("newProject", new Project());
		
		//add users to model with appropriate permissions
		List<User> users= userServ.getUsersWithPermission(2);
		model.addAttribute("users", users);
		
		
		return "addProject.jsp";
	}
	@PostMapping("/project/new")
	public String createProject(
			@Valid @ModelAttribute("newProject") Project filledProject,
			BindingResult result,
			Model model) {
		
		//Validate
		if(result.hasErrors()) {
			List<User> users= userServ.getUsersWithPermission(2);
			model.addAttribute("users", users);
			return "addProject.jsp";
		}
		else {
			Project project= projectServ.createProject(filledProject);
			//WHERE I WANT IT TO GO
			//return "redirect:/project/{project.id}";
			
			//WHERE IT WILL GO FOR NOW
			return "redirect:/";
		
		}
	}
	
	@RequestMapping("/projects")
	public String showProjects(
			HttpSession session,
			Model model) {
		
		//make sure user is logged in
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		}
		
		if(session.getAttribute("user_role").equals(0)) {
			//get all projects
			List<Project> projects = projectServ.getAllProjects();
			model.addAttribute("projects", projects);
		}
		else if(session.getAttribute("user_role").equals(1)) {
			List<Project> projects = projectServ.getAllByManager((Long) session.getAttribute("user_id"));
			model.addAttribute("projects", projects);
		}
		else if(session.getAttribute("user_role").equals(2)) {
			List<Project> projectsBulk = projectServ.getAllProjects();
			List<Project> projects = new ArrayList<>();
			for(Project project : projectsBulk) {
				System.out.println(project);
				Long id = project.getId();
				List<Ticket> tickets = ticketServ.getTicketByProject(id);
				for(Ticket ticket : tickets) {
					User userAssigned = ticket.getAssignedDev();
					User userSubmitted = ticket.getSubmitter();
					
					if(session.getAttribute("user_id") == userAssigned.getId() || session.getAttribute("user_id") == userSubmitted.getId()) {
						projects.add(project);
						//System.out.println(project);
						break;
					}
					
				}
				
			}
			model.addAttribute("projects", projects);
			
		}
		
		
		return "projects.jsp";
	}
	
	@RequestMapping("/project/{id}")
	public String showProject(
			@PathVariable("id") Long id,
			HttpSession session,
			Model model) {
		
		//make sure user is logged in
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		}
		
		//get project 
		Project project = projectServ.getOneProject(id);
		model.addAttribute("project", project);
		
		//get project manager info
		User manager = project.getManager();
		model.addAttribute("manager", manager);
		
		//get ticket by project
		List<Ticket> tickets = ticketServ.getTicketByProject(id);
		model.addAttribute("tickets", tickets);
		
		return "project.jsp";
	}
	
}
