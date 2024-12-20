package com.xdlab.taskmaster.controller;

import com.xdlab.taskmaster.entity.Project;
import com.xdlab.taskmaster.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(value = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(Long id){
        Optional<Project> project = projectService.getProjectById(id);

        if (project.isPresent()){
            return ResponseEntity.ok(project.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Project createProject(@RequestBody Project project){
        return projectService.saveProject(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project){
        Optional<Project> recent = projectService.getProjectById(id);
        if (recent.isPresent()){
            Project updatedProject = recent.get();
            updatedProject.setName(project.getName());
            updatedProject.setDescription(project.getDescription());
            updatedProject.setTasks(project.getTasks());
            return ResponseEntity.ok(projectService.saveProject(updatedProject));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
