package com.xdlab.taskmaster.controller;

import com.xdlab.taskmaster.entity.Task;
import com.xdlab.taskmaster.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(Long id){
        Optional<Task> task = taskService.getTaskById(id);

        if (task.isPresent()){
            return ResponseEntity.ok(task.get());
        } else {
          return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        return ResponseEntity.ok(taskService.saveTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task){
        Optional<Task> recent = taskService.getTaskById(id);

        if (recent.isPresent()){
            Task updatedTask = recent.get();
            updatedTask.setName(task.getName());
            updatedTask.setDescription(task.getDescription());
            updatedTask.setCompleted(task.getCompleted());
            updatedTask.setDueDate(task.getDueDate());
            return ResponseEntity.ok(taskService.saveTask(updatedTask));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
