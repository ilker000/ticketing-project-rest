package com.spring.controller;

import com.spring.annotation.DefaultExceptionMessage;
import com.spring.dto.ProjectDTO;
import com.spring.dto.TaskDTO;
import com.spring.dto.UserDTO;
import com.spring.entity.ResponseWrapper;
import com.spring.enums.Status;
import com.spring.exception.TicketingProjectException;
import com.spring.service.ProjectService;
import com.spring.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/project")
@Tag(name = "Project Controller",description = "Project API")
public class ProjectController {


    private ProjectService projectService;
    private UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }


    @GetMapping
    @Operation(summary = "Read all projects")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, try again!")
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public ResponseEntity<ResponseWrapper> readAll(){
        List<ProjectDTO> projectDTOS = projectService.listAllProjects();
        return ResponseEntity.ok(new ResponseWrapper("Project are retrieved",projectDTOS));
    }

    @GetMapping("/{projectcode}")
    @Operation(summary = "Read by project code")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, try again!")
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public ResponseEntity<ResponseWrapper> readByProjectCode(@PathVariable("projectcode") String projectcode){
        ProjectDTO projectDTO = projectService.getByProjectCode(projectcode);
        return ResponseEntity.ok(new ResponseWrapper("Project is retrieved",projectDTO));
    }

    @PostMapping
    @Operation(summary = "Create project")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, try again!")
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public ResponseEntity<ResponseWrapper> create(@RequestBody ProjectDTO projectDTO) throws TicketingProjectException {
        ProjectDTO createdProject = projectService.save(projectDTO);
        return ResponseEntity.ok(new ResponseWrapper("Project is retrieved",projectDTO));
    }

    @PutMapping
    @Operation(summary = "Update project")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, try again!")
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public ResponseEntity<ResponseWrapper> updateProject(@RequestBody ProjectDTO projectDTO) throws TicketingProjectException {
        ProjectDTO updatedProject = projectService.update(projectDTO);
        return ResponseEntity.ok(new ResponseWrapper("Project is updated",updatedProject));
    }

    @DeleteMapping("/{projectcode}")
    @Operation(summary = "Delete project")
    @DefaultExceptionMessage(defaultMessage = "Failed to delete project!")
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public ResponseEntity<ResponseWrapper> deleteProject(@PathVariable("projectcode") String projectcode) throws TicketingProjectException {
        projectService.delete(projectcode);
        return ResponseEntity.ok(new ResponseWrapper("Project is deleted"));
    }

    @PutMapping("/complete/{projectcode}")
    @Operation(summary = "Complete project")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, try again!")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<ResponseWrapper> completeProject(@PathVariable("projectcode") String projectcode) throws TicketingProjectException {
        ProjectDTO projectDTO = projectService.complete(projectcode);
        return ResponseEntity.ok(new ResponseWrapper("Project is completed",projectDTO));
    }

    @GetMapping("/details")
    @Operation(summary = "Read all project details")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, try again!")
    @PreAuthorize("hasAuthority('Manager')")
    public ResponseEntity<ResponseWrapper> readAllProjectDetails() throws TicketingProjectException {
        List<ProjectDTO> projectDTOs = projectService.listAllProjectDetails();
        return ResponseEntity.ok(new ResponseWrapper("Projects are retrieved with details",projectDTOs));
    }
}





















