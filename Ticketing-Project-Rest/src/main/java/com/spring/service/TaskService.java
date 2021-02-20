package com.spring.service;

import com.spring.dto.ProjectDTO;
import com.spring.dto.TaskDTO;
import com.spring.entity.Task;
import com.spring.entity.User;
import com.spring.enums.Status;
import com.spring.exception.TicketingProjectException;

import java.util.List;

public interface TaskService {

    TaskDTO findById(Long id) throws TicketingProjectException;

    List<TaskDTO> listAllTasks();

    TaskDTO save(TaskDTO dto);

    void update(TaskDTO dto);

    void delete(long id);

    int totalNonCompletedTasks(String projectCode);
    int totalCompletedTasks(String projectCode);

    void deleteByProject(ProjectDTO project);

    List<TaskDTO> listAllByProject(ProjectDTO project);

    List<TaskDTO> listAllTasksByStatusIsNot(Status status);

    List<TaskDTO> listAllTasksByProjectManager() throws TicketingProjectException;

    void updateStatus(TaskDTO dto);

    List<TaskDTO> listAllTasksByStatus(Status status);

    List<TaskDTO> readAllByEmployee(User assignedEmployee);
}