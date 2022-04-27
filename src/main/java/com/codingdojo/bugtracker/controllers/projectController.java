package com.codingdojo.bugtracker.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.bugtracker.models.Project;
import com.codingdojo.bugtracker.models.User;
import com.codingdojo.bugtracker.services.ProjectService;
import com.codingdojo.bugtracker.services.UserService;

@Controller
public class projectController {
	@Autowired
	public ProjectService projectServ;
	@Autowired
	public UserService userServ;
	
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
}
