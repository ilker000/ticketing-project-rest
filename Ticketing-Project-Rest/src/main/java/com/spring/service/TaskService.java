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

    TaskDTO update(TaskDTO dto) throws TicketingProjectException;

    void delete(long id) throws TicketingProjectException;

    int totalNonCompletedTasks(String projectCode);
    int totalCompletedTasks(String projectCode);

    void deleteByProject(ProjectDTO project);

    List<TaskDTO> listAllByProject(ProjectDTO project);

    List<TaskDTO> listAllTasksByStatusIsNot(Status status) throws TicketingProjectException;

    List<TaskDTO> listAllTasksByProjectManager() throws TicketingProjectException;

    TaskDTO updateStatus(TaskDTO dto) throws TicketingProjectException;

//    List<TaskDTO> listAllTasksByStatus(Status status);

    List<TaskDTO> readAllByEmployee(User assignedEmployee);
}