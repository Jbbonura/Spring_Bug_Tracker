package com.codingdojo.bugtracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.bugtracker.models.Project;
import com.codingdojo.bugtracker.repositories.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepo;
	
	//CREATE
		public Project createProject(Project newProject) {
			return projectRepo.save(newProject);
		}
	
	//READ
		
		//read one
		public Project getOneProject(Long id) {
			return projectRepo.findById(id).orElse(null);
		}
	
		//read all
		public List<Project> getAllProjects() {
			return projectRepo.findAll();
		}
	
	//DELETE
		public void deleteProject(Long id) {
			projectRepo.deleteById(id);
		}
		
	//GET COUNT
		public int projectCount() {
			//fetch all projects
			List<Project> projects = getAllProjects();
			//count number of projects
			int count = 0;
			for(Project project : projects) {
				count++;
			}
			return count;
		}
}
