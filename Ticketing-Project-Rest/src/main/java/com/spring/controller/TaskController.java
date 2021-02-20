package com.spring.controller;

import com.spring.annotation.DefaultExceptionMessage;
import com.spring.dto.TaskDTO;
import com.spring.entity.ResponseWrapper;
import com.spring.enums.Status;
import com.spring.exception.TicketingProjectException;
import com.spring.service.ProjectService;
import com.spring.service.TaskService;
import com.spring.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @DefaultExceptionMessage(defaultMessage = "Something went wrong,please try again!")
    @Operation(summary = "Read all tasks")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<ResponseWrapper> readAll(){
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved all tasks",taskService.listAllTasks()));
    }

    @GetMapping("/project-manager")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong,please try again!")
    @Operation(summary = "Read all tasks by project manager")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<ResponseWrapper> readAllByProjectManager() throws TicketingProjectException {
        List<TaskDTO> taskList = taskService.listAllTasksByProjectManager();
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved tasks by project manager",taskList));
    }

    @GetMapping("/{id}")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong,please try again!")
    @Operation(summary = "Read task by id")
    @PreAuthorize("hasAnyAuthority('Manager',Employee)")
    public ResponseEntity<ResponseWrapper> readById(@PathVariable("id") Long id) throws TicketingProjectException {
        TaskDTO currentTask = taskService.findById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved task",currentTask));
    }

    @PostMapping
    @DefaultExceptionMessage(defaultMessage = "Something went wrong,please try again!")
    @Operation(summary = "Create a new task")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<ResponseWrapper> create(@RequestBody TaskDTO task){
        TaskDTO createdTask = taskService.save(task);
        return ResponseEntity.ok(new ResponseWrapper("Successfully task created",createdTask));
    }


















}
